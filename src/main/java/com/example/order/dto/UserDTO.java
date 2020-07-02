package com.example.order.dto;

import com.example.order.util.Constast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Cy
 * @data 2020/6/30 - 0:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userName;

    private String userPassword;

    private String userRole;

    private String userSex;

    private String userEmail;

    private String userPic;

    private Timestamp loginTime;

    private String  userGroup;

    public String getUserPic(){
        return Constast.IMAGE_GET_PATH +userPic;
    }
    public void setUserPic(String pic){
        this.userPic = pic;
    }
}
