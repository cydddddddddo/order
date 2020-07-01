package com.example.order.module.user.service;

import com.example.order.dto.OverTimeDTO;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Chencj
 * @description
 * @date 2020/6/30
 */
public interface OverTimeService {
    List<OverTimeDTO> findAll();
    int insert(OverTimeDTO overTimeDTO);
    int updateTypeById(int id,int type);
    OverTimeDTO findToday(String timestamp);
    OverTimeDTO findById(int id);
}
