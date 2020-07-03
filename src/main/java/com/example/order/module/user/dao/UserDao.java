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

    UserDTO getUserByEmail(@Param("email")String email);

    List<UserDTO> getAllUser();

    int insertAllUser(@Param("userList") List<UserDTO> userDTOList);

    Integer addUser(@Param("user")UserDTO user);

    Integer updateUser(@Param("user")UserDTO user,@Param("pic")String pic);

    List<UserDTO> getUserList(@Param("page")Integer page,@Param("limit")Integer limit,@Param("key")String key);

    Long getCount(@Param("key")String key);

    Long deleteUser(@Param("userId")String userId);
}
