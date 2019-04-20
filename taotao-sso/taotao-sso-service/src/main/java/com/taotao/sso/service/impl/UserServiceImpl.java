package com.taotao.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.taotao.manager.mapper.UserMapper;
import com.taotao.manager.model.User;
import com.taotao.redis.RedisUtil;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

@Component
@Service(interfaceClass = UserService.class)
@EnableCaching
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *  检查用户是否存在
     * @param param
     * @param type
     * @return
     */
    @Override
    public Boolean checkUser(String param, Integer type) {
        User user = new User();
        switch (type) {
            case 1:
                user.setUsername(param);
                break;
            case 2:
                user.setPhone(param);
                break;
            case 3:
                user.setEmail(param);
                break;
            default:
                user.setUsername(param);
                break;
        }
        int count = userMapper.selectCount(user);
        if (count > 0) {
            return false;
        }
        return true;
    }

    @Cacheable(value = "User", key = "'UserLoginKey' + #ticket")
    @Override
    public String selectByTicket(String ticket) {
        return null;
    }

    @Override
    public int register(User user) {
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        // md5加密
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        return userMapper.insertSelective(user);
    }

    @Cacheable(value = "User", key = "'UserLoginKey' + #user.username")
    @Override
    public User login(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        User loginUser = userMapper.selectOne(user);
        if (loginUser == null) {
            user.setPhone(user.getUsername());
            user.setUsername(null);
            loginUser = userMapper.selectOne(user);
        }
        return loginUser;
    }
}
