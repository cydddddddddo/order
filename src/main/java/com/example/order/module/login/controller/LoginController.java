package com.example.order.module.login.controller;

import com.example.order.util.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Cy
 * @data 2020/6/30 - 0:41
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public ResultInfo loginSuccess(){
        return ResultInfo.success();
    }

    @RequestMapping("/loginError")
    @ResponseBody
    public ResultInfo failure(){
        return ResultInfo.failure();
    }
}
