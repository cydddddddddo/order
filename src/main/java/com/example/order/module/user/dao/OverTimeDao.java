package com.example.order.module.user.dao;

import com.example.order.dto.OverTimeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Chencj
 * @description
 * @date 2020/6/30
 */
@Repository
@Mapper
public interface OverTimeDao {
    List<OverTimeDTO> findAll();
    int insert(@Param("overTime")OverTimeDTO overTimeDTO);
    int updateTypeById(@Param("id")int id,@Param("type")int type);
    OverTimeDTO findToday(@Param("time")String timestamp);
    OverTimeDTO findById(@Param("id")int id);
}
