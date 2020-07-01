package com.example.order.module.user.controller;

import com.example.order.dto.UserDTO;
import com.example.order.module.user.service.UserServiceImpl;
import com.example.order.util.Constast;
import com.example.order.util.ResultInfo;
import com.example.order.util.UploadUtil;
import com.example.order.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2020/7/1 - 1:48
 */


@Controller
public class UserController extends BaseController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /*
     * 上传头像
     * @param file
     * @param model
     * @return*/


    @RequestMapping("upload/image")
    @ResponseBody
    public ResultInfo demo(MultipartFile file, Model model) {
        UserDTO user = this.getCurrentUser();
        try {
            String image = UploadUtil.uploadFile(file, Constast.IMAGE_SET_PATH, user.getUserId());
            user.setUserPic("");

            ResultInfo resultObj = this.updateUser(user, image, null, null, model);
            if (resultObj.equals(ResultInfo.success())) {
                return ResultInfo.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultInfo.failure();
    }

    @RequestMapping("user/update")
    @ResponseBody
    public ResultInfo updateUser(UserDTO user, String pic, String field, String value, Model model) {

        if (field != null) {
            switch (field) {
                case "userRole":
                    user.setUserRole(value);
                    break;
                case "userName":
                    user.setUserName(value);
                    break;
                case "userGroup":
                    user.setUserGroup(value);
                    break;
                case "userSex":
                    user.setUserSex(value);
                    break;
                case "userEmail":
                    user.setUserEmail(value);
                    break;
                default:
                    return ResultInfo.CHANGE_ERROR;
            }
        }

        int num = userService.updateUser(user, pic);

        if (num == 1) {
            return ResultInfo.success();
        } else {
            return ResultInfo.failure();
        }
    }

    @RequestMapping("user/changePwd/change")
    @ResponseBody
    public ResultInfo changePwd(String oldPwd, String newPwd, String realPwd, Model model) {
        //这里堪称薛定谔的bug，好吧其实不是后端的问题，是前端。
        int num = 0;
        UserDTO user = this.getCurrentUser();

        if (oldPwd.equals(newPwd)) {
            //新旧密码是否一致
            return ResultInfo.CHANGE_NEWPWD_FAILED;
        } else if (!realPwd.equals(newPwd)) {
            //确认密码不一致
            return ResultInfo.CHANGE_REALPWD_FAILED;
        } else if (passwordEncoder.matches(oldPwd, user.getUserPassword())) {
            //验证旧密码，对则修改，错则返回错误信息
            user.setUserPassword(passwordEncoder.encode(newPwd));
            //对密码进行修改
            num = userService.updateUser(user, null);
            return ResultInfo.CHANGE_SUCCESS;
        } else {
            //旧密码错误
            return ResultInfo.CHANGE_OLDPWD_FAILED;
        }
    }

    @RequestMapping("user/list")
    @ResponseBody
    public ResultInfo userList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit
            , String key, Model model) {
        List<UserDTO> list = userService.getUserList(page, limit, key);
        Long count = userService.getCount(key);
        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("list", list);
        return new ResultInfo("0", "", map);
    }

    @RequestMapping("user/add")
    public String userAdd(String userId,String userPassword,String userRole,String userName,
                          String userGroup,String userSex,String userEmail,String userPic,Model model) throws Exception {
        Integer num = 0;
        if (userId!=null){
            UserDTO user = new UserDTO(userId,userName,passwordEncoder.encode(userPassword),userRole,userSex
                    ,userEmail,Constast.IMAGE_DEFAULT,null,userGroup);
            num = userService.addUser(user);
            if (num!=0){
                return "user/userManage";
            }
        }

        return "user/userAdd";
    }
}
