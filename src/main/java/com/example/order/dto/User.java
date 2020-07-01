package com.example.order.dto;


import lombok.Data;

@Data
public class User {

    private String userId;
    private String userName;
    private String userPassword;
    private String userRole;
    private  int  userGroup;
    private String userSex;
    private String userEmail;
    private String userPic;
    private String userTime;

}
