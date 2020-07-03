package com.example.order.module.user.service;

import com.example.order.dto.MealDTO;
import com.example.order.module.user.dao.MealDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Chencj
 * @description
 * @date 2020/6/30
 */
public interface MealService {
    List<MealDTO> findAll();
    List<MealDTO> findAllTrue();

    int insert(MealDTO mealDTO);
    void deleteById(Integer id);
    int update(MealDTO mealDTO);
    int updateStatusById(Integer id,String status);
    MealDTO findOne(Integer id);
}
