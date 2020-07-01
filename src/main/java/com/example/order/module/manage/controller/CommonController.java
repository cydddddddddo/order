package com.example.order.module.manage.controller;

import com.example.order.module.user.service.MailService;
import com.example.order.util.ResultInfo;
import com.example.order.util.VerificationUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Chencj
 * @description 公共接口
 * @date 2020/7/1
 */
@RestController
public class CommonController {
    @Resource
    private MailService mailService;

    @Resource
    private VerificationUtil verificationUtil;

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

}
