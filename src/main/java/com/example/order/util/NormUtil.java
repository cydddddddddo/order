package com.example.order.util;

import com.example.order.dto.UserDTO;


/**
 * @author Cy
 * @data 2020/7/2 - 9:48
 */
public class NormUtil {


    public static UserDTO normUtil(UserDTO user){
        if (user.getUserRole()!=null&&!user.getUserRole().startsWith("ROLE_")){
            String role = user.getUserRole();
            switch (role){
                case "管理员":
                    user.setUserRole("ROLE_man");
                    break;
                case "组长":
                    user.setUserRole("ROLE_lea");
                    break;
                case "组员":
                    user.setUserRole("ROLE_mem");
                    break;
                default:
                    user.setUserRole("ROLE_"+role);
            }
        }
        if (user.getUserSex()!=null&&(("男").equals(user.getUserSex())||("女").equals(user.getUserSex()))){
            if ("男".equals(user.getUserSex())){
                user.setUserSex("man");
            }else {
                user.setUserSex("woman");
            }
        }

        if (user.getUserPic().endsWith("null")){
                user.setUserPic("userface4.jpg");
        }else if (user.getUserPic().startsWith(Constast.IMAGE_GET_PATH)){
            user.setUserPic(user.getUserPic().substring(user.getUserPic().lastIndexOf("/")+1));
        }

        return user;
    }
}
