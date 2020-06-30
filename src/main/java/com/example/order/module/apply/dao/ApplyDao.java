package com.example.order.module.apply.dao;

import com.example.order.dto.Apply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplyDao {
    void  insertApply(Apply apply);
}
