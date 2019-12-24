package com.mall.user.service.impl;

import com.mall.admin.mapper.UmsMemberLevelMapper;
import com.mall.admin.mapper.UmsMemberMapper;
import com.mall.admin.model.UmsMember;
import com.mall.admin.model.UmsMemberExample;
import com.mall.admin.model.UmsMemberLevel;
import com.mall.admin.model.UmsMemberLevelExample;
import com.mall.auth.entity.UserInfo;
import com.mall.auth.properties.JwtProperties;
import com.mall.auth.utils.JwtUtils;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.common.base.utils.CodecUtils;
import com.mall.common.base.utils.JsonUtils;
import com.mall.common.redis.constant.RedisKey;
import com.mall.common.redis.enums.CtimsModelEnum;
import com.mall.common.redis.utils.CommonRedisUtils;
import com.mall.user.interceptor.LoginInterceptor;
import com.mall.user.service.UmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 会员管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberServiceImpl.class);

    @Autowired
    private UmsMemberMapper memberMapper;

    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private CommonRedisUtils redisUtils;


    @Override
    public String authentication(String username, String password) {
        try {
            //1.调用微服务查询用户信息
            UmsMember user = queryUser(username, password);
            //2.查询结果为空，则直接返回null
            if (user == null) {
                return null;
            }
            //3.查询结果不为空，则生成token
            return JwtUtils.generateToken(new UserInfo(user.getId(), user.getUsername()),
                    jwtProperties.getExpiration(), jwtProperties.getSecret());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UmsMember queryUser(String username, String password) {
        /*
         * 逻辑改变，先去缓存中查询用户数据，查到的话直接返回，查不到再去数据库中查询，然后放入到缓存当中
         */
        //1.缓存中查询
        String userStr = redisUtils.getMapField(CtimsModelEnum.CTIMS_USER_CAP, RedisKey.USER_INFO_KEY_PREFIX, username);
        UmsMember umsMember;
        if (org.apache.commons.lang3.StringUtils.isEmpty(userStr)) {
            //在缓存中没有查到，去数据库查,查到放入缓存当中
            umsMember = getByUsername(username);
            redisUtils.addMap(CtimsModelEnum.CTIMS_USER_CAP, RedisKey.USER_INFO_KEY_PREFIX, JsonUtils.serialize(umsMember), RedisKey.USER_INFO_KEY_SECONDS);

        } else {
            umsMember = JsonUtils.parse(userStr, UmsMember.class);
        }

        //2.校验用户名
        if (umsMember == null) {
            return null;
        }
        //3. 校验密码
        boolean result = CodecUtils.passwordConfirm(username + password, umsMember.getPassword());
        if (!result) {
            return null;
        }

        //4.用户名密码都正确
        return umsMember;
    }

    @Override
    public UmsMember getByUsername(String username) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result register(String username, String password, String telephone, String authCode) {
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            return Result.error(CodeMsg.LOGIN_VERIFY_ERROR);
        }
        //查询是否已有该用户
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        example.or(example.createCriteria().andPhoneEqualTo(telephone));
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            return Result.error(CodeMsg.LOGIN_USER_EXIST);
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPhone(telephone);
        //密码加密
        String encodePassword = CodecUtils.passwordBcryptEncode(umsMember.getUsername().trim(), password.trim());
        umsMember.setPassword(encodePassword);
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        //获取默认会员等级并设置
        UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
        levelExample.createCriteria().andDefaultStatusEqualTo(1);
        List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }
        memberMapper.insert(umsMember);
        return Result.error(CodeMsg.REGISTER_SUCCESS);
    }

    @Override
    public Result generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisUtils.set(CtimsModelEnum.CTIMS_USER_CAP, RedisKey.AUTH_CODE_STRING_KEY_PREFIX + telephone, sb.toString(), RedisKey.AUTH_CODE_EXPIRE_SECONDS);
        return Result.success(sb.toString(), CodeMsg.VERIFY_CODE_SUCCESS.getMsg());
    }

    @Override
    public Result updatePassword(String telephone, String password, String authCode) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(memberList)) {
            return Result.error(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            return Result.error(CodeMsg.LOGIN_VERIFY_ERROR);
        }
        UmsMember umsMember = memberList.get(0);
        //密码加密
        String encodePassword = CodecUtils.passwordBcryptEncode(umsMember.getUsername().trim(), password.trim());
        umsMember.setPassword(encodePassword);
        memberMapper.updateByPrimaryKeySelective(umsMember);
        return Result.success(CodeMsg.CHANGE_PASSWORD_SUCCESS);
    }

    @Override
    public boolean updateUsernamePassword(String username, String oldPassword, String newPassword) {
        /*
         * 这里面涉及到对缓存的操作：
         * 先把数据存到数据库中，成功后，再让缓存失效。
         */
        //1.读取用户信息
        UmsMember umsMember = this.queryUser(username, oldPassword);
        if (umsMember == null) {
            return false;
        }
        //2.更新数据库中的用户信息
        UmsMember record = new UmsMember();
        record.setId(umsMember.getId());
        //2.1密码加密
        String encodePassword = CodecUtils.passwordBcryptEncode(username.trim(), newPassword.trim());
        record.setPassword(encodePassword);
        memberMapper.updateByPrimaryKeySelective(record);
        //3.处理缓存中的信息
        redisUtils.deleteMapField(CtimsModelEnum.CTIMS_USER_CAP, RedisKey.USER_CODE_PHONE_KEY_PREFIX + username, username);
        return true;
    }

    @Override
    public UmsMember getCurrentMember() {
        //获取登录的用户
        UserInfo currentMember = LoginInterceptor.getLoginUser();
        return getById(currentMember.getId());
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public String refreshToken(String token) {
        return JwtUtils.refreshToken(token, jwtProperties.getExpiration(), jwtProperties.getSecret());
    }

    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = redisUtils.get(CtimsModelEnum.CTIMS_USER_CAP, RedisKey.AUTH_CODE_STRING_KEY_PREFIX + telephone);
        return authCode.equals(realAuthCode);
    }

}
