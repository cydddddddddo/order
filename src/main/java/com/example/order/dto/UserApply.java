package com.example.order.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserApply {
    private String userId;
    private String userName;
    private String userPassword;
    private String userRole;
    private  int  userGroup;
    private String userSex;
    private String userEmail;
    private String userPic;
    private String userTime;

    private  String  id;
    private Timestamp date;
    private  String UserId;
    private  int status;
    private  String description;
}
