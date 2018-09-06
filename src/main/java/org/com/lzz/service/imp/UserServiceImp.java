package org.com.lzz.service.imp;

import org.com.lzz.dao.UserDao;
import org.com.lzz.domain.User;
import org.com.lzz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImp implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Cacheable(value = "user", key = "#root.targetClass + #ID", unless = "#result eq null")
    public User getUserById(String ID) {
        User user = userDao.getUserById(ID);
        return user;
    }

    @Override
    @CacheEvict(value = "user", key = "#root.targetClass + #ID", condition = "#result eq 'yes'")
    public String deleteUserById(String ID) {
        return "yes";
    }
}
