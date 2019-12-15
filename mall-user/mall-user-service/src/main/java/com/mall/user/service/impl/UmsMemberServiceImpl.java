package com.mall.user.service.impl;

import com.mall.admin.mapper.UmsMemberLevelMapper;
import com.mall.admin.mapper.UmsMemberMapper;
import com.mall.admin.model.UmsMember;
import com.mall.admin.model.UmsMemberExample;
import com.mall.admin.model.UmsMemberLevel;
import com.mall.admin.model.UmsMemberLevelExample;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.common.base.utils.CodecUtils;
import com.mall.common.redis.constant.RedisKey;
import com.mall.common.redis.enums.CtimsModelEnum;
import com.mall.common.redis.utils.CommonRedisUtils;
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

    private CommonRedisUtils redisUtils;

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
        umsMember.setPassword(null);
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
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);
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
