package com.example.order.module.user.service;

import com.example.order.dto.MealDTO;
import com.example.order.module.user.dao.MealDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Chencj
 * @description
 * @date 2020/6/30
 */
@Service
public class MealServiceImpl implements MealService {
    @Resource
    private MealDao mealDao;
    @Override
    public List<MealDTO> findAll() {
        return mealDao.findAll();
    }

    @Override
    public List<MealDTO> findAllTrue() {

        return mealDao.findAllTrue();
    }

    @Override
    public int insert(MealDTO mealDTO) {
        return mealDao.insert(mealDTO);
    }

    @Override
    public void deleteById(Integer id) {
         mealDao.deleteById(id);
    }
    @Override
    public int update(MealDTO mealDTO) {
        return mealDao.update(mealDTO);
    }

    @Override
    public int updateStatusById(Integer id, String status) {
        return mealDao.updateStatusById(id, status);
    }

    @Override
    public MealDTO findOne(Integer id) {
        return mealDao.findOne(id);
    }
}
