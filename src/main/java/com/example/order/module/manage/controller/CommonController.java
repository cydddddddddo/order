package com.example.order.module.manage.controller;

import com.example.order.dto.User;
import com.example.order.dto.UserDTO;
import com.example.order.module.user.service.MailService;
import com.example.order.module.user.service.UserService;
import com.example.order.util.ResultInfo;
import com.example.order.util.VerificationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Chencj
 * @description 公共接口
 * @date 2020/7/1
 */
@RestController
@Api(tags = "公共api")
public class CommonController {
    @Resource
    private MailService mailService;

    @Resource
    private VerificationUtil verificationUtil;

    @Resource
    private UserService userService;

    @ApiOperation(value = "获取验证码",httpMethod = "GET")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "email",value = "用户邮箱",dataType = "String")
    )
    @GetMapping("/getCode")
    public ResultInfo getCode(@RequestParam("email")String email){
        ResultInfo resultInfo;
        if(!verificationUtil.checkEmail(email)){
            resultInfo=ResultInfo.failure();
            resultInfo.setMsg("邮箱格式错误");
            return resultInfo;
        }
        String code=verificationUtil.signCode(email);
        mailService.sendSimpleMail("",email,"","验证码","你的验证码为："+code+",2分钟有效");
        return ResultInfo.success();
    }

    @ApiOperation(value = "修改密码",httpMethod = "POST")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "email",value = "用户邮箱",dataType = "String")
    )
    @PostMapping("/updatePassword")
    public ResultInfo updatePassword(@RequestParam("email")String email,@RequestParam("password")String password,@RequestParam("code")String code){
        ResultInfo resultInfo;
        if(!verificationUtil.checkEmail(email)){
            resultInfo=ResultInfo.failure();
            resultInfo.setMsg("邮箱格式错误");
            return resultInfo;
        }
        if(!verificationUtil.checkCode(email, code)){
            resultInfo=ResultInfo.failure();
            resultInfo.setMsg("验证码错误");
            return resultInfo;
        }
        UserDTO userDTO=userService.getUserByEmail(email);
        BCryptPasswordEncoder cryptPasswordEncoder=new BCryptPasswordEncoder();
        userDTO.setUserPassword(cryptPasswordEncoder.encode(password));
        userService.updateUser(userDTO,null);
        return ResultInfo.success();
    }
}
