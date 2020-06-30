package com.example.order.dto;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class Apply {

    private  String  id;
    private Timestamp date;
    private  String UserId;
    private  int status;
    private  String description;

}
