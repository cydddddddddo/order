package com.example.order.module.user.dao;

import com.example.order.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Cy
 * @data 2020/6/30 - 0:48
 */

@Repository
@Mapper
public interface UserDao {

    UserDTO getUserById(@Param("userId")String userId);

    Integer addUser(@Param("user")UserDTO user);
}
