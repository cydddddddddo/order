package com.example.order.module.user.service;

import com.example.order.dto.UserDTO;
import com.example.order.module.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cy
 * @data 2020/6/30 - 0:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDTO getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer addUser(UserDTO user) {
        return userDao.addUser(user);

    }
}
