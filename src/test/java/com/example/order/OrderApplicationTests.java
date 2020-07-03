
package com.example.order;

import com.example.order.dto.Menu;
import com.example.order.dto.UserDTO;
import com.example.order.module.menu.service.MenuServiceImpl;
import com.example.order.module.user.service.UserServiceImpl;
import com.example.order.util.Constast;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class OrderApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        /*UserDTO userDTO = new UserDTO("a0003","老三",passwordEncoder.encode("123")
                ,"ROLE_mem","woman","123456@qq.com"
                ,null,null,"1");
        userService.addUser(userDTO);*/

        String a = Constast.IMAGE_GET_PATH+"demo";
        System.out.println(a.substring(a.lastIndexOf("/")+1));
    }

}
