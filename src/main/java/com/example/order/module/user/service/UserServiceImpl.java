package com.example.order.module.user.service;

import com.example.order.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cy
 * @data 2020/6/30 - 0:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserService userService;

    @Override
    public UserDTO getUserById(String userId) {
        return userService.getUserById(userId);
    }
}
