package com.example.order.module.user.dao;

import com.example.order.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cy
 * @data 2020/6/30 - 0:48
 */

@Repository
@Mapper
public interface UserDao {

    UserDTO getUserById(@Param("userId")String userId);

    List<UserDTO> getAllUser();

    int insertAllUser(@Param("userList") List<UserDTO> userDTOList);
}
