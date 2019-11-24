package com.mall.user.service.impl;

import com.mall.constant.RedisKey;
import com.mall.user.mapper.UserMapper;
import com.mall.user.pojo.User;
import com.mall.user.service.UserService;
import com.mall.utils.CodecUtils;
import com.mall.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * program: spring-cloud-mall->UserServiceImpl
 * description: 用户服务接口实现
 * author: gerry
 * created: 2019-11-25 00:25
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public User queryUser(String username, String password) {
        /*
         * 逻辑改变，先去缓存中查询用户数据，查到的话直接返回，查不到再去数据库中查询，然后放入到缓存当中
         */
        //1.缓存中查询
        BoundHashOperations<String, Object, Object> hashOperations =
                this.stringRedisTemplate.boundHashOps(RedisKey.USER_INFO_KEY_PREFIX);
        String userStr = (String) hashOperations.get(username);
        User user;
        if (StringUtils.isEmpty(userStr)) {
            //在缓存中没有查到，去数据库查,查到放入缓存当中
            User record = new User();
            record.setUsername(username);
            user = this.userMapper.selectOne(record);
            hashOperations.put(user.getUsername(), JsonUtils.serialize(user));
        } else {
            user = JsonUtils.parse(userStr, User.class);
        }

        //2.校验用户名
        if (user == null) {
            return null;
        }
        //3. 校验密码
        boolean result = CodecUtils.passwordConfirm(username + password, user.getPassword());
        if (!result) {
            return null;
        }

        //4.用户名密码都正确
        return user;
    }

    @Override
    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        /*
         * 这里面涉及到对缓存的操作：
         * 先把数据存到数据库中，成功后，再让缓存失效。
         */
        //1.读取用户信息
        User user = this.queryUser(username, oldPassword);
        if (user == null) {
            return false;
        }
        //2.更新数据库中的用户信息
        User updateUser = new User();
        updateUser.setId(user.getId());
        //2.1密码加密
        String encodePassword = CodecUtils.passwordBcryptEncode(username.trim(), newPassword.trim());
        updateUser.setPassword(encodePassword);
        this.userMapper.updateByPrimaryKeySelective(updateUser);
        //3.处理缓存中的信息
        BoundHashOperations<String, Object, Object> hashOperations =
                this.stringRedisTemplate.boundHashOps(RedisKey.USER_CODE_PHONE_KEY_PREFIX + username);
        hashOperations.delete(username);
        return true;
    }
}
