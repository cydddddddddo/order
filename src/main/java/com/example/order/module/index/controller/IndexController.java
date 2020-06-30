package com.example.order.module.index.controller;

import com.example.order.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Cy
 * @data 2020/6/30 - 8:45
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping({"sys/toLogin","/"})
    public String toLogin(){
        return "system/index/login";
    }
}
