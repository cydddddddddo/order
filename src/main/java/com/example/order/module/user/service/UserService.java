package com.example.order.module.user.service;

import com.example.order.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Cy
 * @data 2020/6/30 - 0:48
 */
public interface UserService {
    UserDTO getUserById(@Param("userId")String userId);
}
