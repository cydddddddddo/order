package com.example.order.module.user.dao;

import com.example.order.dto.MealDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chencj
 * @description
 * @date 2020/6/30
 */
@Repository
@Mapper
public interface MealDao{
    List<MealDTO> findAll();

    List<MealDTO> findAllTrue();
    int insert(@Param("meal")MealDTO mealDTO);
    void deleteById(@Param("id")Integer id);
    int update(@Param("meal")MealDTO mealDTO);
    int updateStatusById(@Param("id")Integer id,@Param("status")String status);
    MealDTO findOne(@Param("id")Integer id);
}
