package com.example.order.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Chencj
 * @description 补贴方式
 * @date 2020/6/30
 */
@Data
public class OverTimeDTO {
    private int id;
    private String date;
    private int type;
}
