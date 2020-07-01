package com.example.order.module.user.service;

import com.example.order.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cy
 * @data 2020/6/30 - 0:48
 */
public interface UserService {
    UserDTO getUserById(String userId);
    List<UserDTO> getAllUser();
    int insertAllUser(List<UserDTO> userDTOList);
}
