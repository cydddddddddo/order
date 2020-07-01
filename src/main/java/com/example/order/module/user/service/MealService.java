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
    int insert(MealDTO mealDTO);
    void deleteById(String id);
    int update(MealDTO mealDTO);
    int updateStatusById(String id,String status);
    MealDTO findOne(String id);
}
