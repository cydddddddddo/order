package com.example.order.module.apply.controller;


import com.example.order.module.apply.service.ApplyService;
import com.example.order.web.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplyController {

    @Autowired
    ApplyService applyService;

    @RequestMapping("/addApply/{action}")
    @ResponseBody
    public ResultInfo addApply(@PathVariable(name = "action") String userId ){
        return  applyService.addApply(userId);
    }
}
