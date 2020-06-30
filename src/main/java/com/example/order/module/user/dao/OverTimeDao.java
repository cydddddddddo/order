package com.example.order.module.user.dao;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Chencj
 * @description 补贴方式
 * @date 2020/6/30
 */
@Data
public class OverTimeDao {
    private int id;
    private Timestamp date;
    private int type;
}
