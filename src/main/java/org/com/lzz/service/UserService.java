package org.com.lzz.service;

import org.com.lzz.dao.UserDao;
import org.com.lzz.domain.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public User getUserById(String ID);

    public String deleteUserById(String ID);
}
