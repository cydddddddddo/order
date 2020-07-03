package com.example.order.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class CountGroup {
    private  String  id;
    private Timestamp date;
    private  String UserId;
    private  int status;
    private  String description;
    private String userId;

    private String userName;

    private String userPassword;

    private String userRole;

    private String userSex;

    private String userEmail;

    private String userPic;

    private Timestamp loginTime;

    private String  userGroup;
    private  int count;
}
