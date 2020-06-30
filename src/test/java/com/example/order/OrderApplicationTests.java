package com.example.order;

import com.example.order.dto.UserDTO;
import com.example.order.module.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class OrderApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void contextLoads() {
        UserDTO userDTO = new UserDTO("a0001","老大",passwordEncoder.encode("123"),"ROLE_man","man","123456789@qq.com",null,null);
        userService.addUser(userDTO);
    }

}
