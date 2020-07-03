package com.example.order.module.manage.controller;

import com.example.order.dto.OverTimeDTO;
import com.example.order.module.user.service.OverTimeService;
import com.example.order.util.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chencj
 * @description
 * @date 2020/7/1
 */
@RestController
@Api(value = "补贴api",tags = { "补贴接口" })
@RequestMapping("/overTime")
public class OverTimeController {
    @Resource
    private OverTimeService overTimeService;

    @ApiOperation(value = "查询所有补贴",httpMethod = "GET")
    @GetMapping("/findAll")
    public ResultInfo findAll(){
        ResultInfo resultInfo=ResultInfo.success();
        resultInfo.setData(overTimeService.findAll());
        return resultInfo;
    }
    @ApiOperation(value = "插入补贴",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "补贴时间  格式 2019-01-01", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "补贴方式 1: 加班餐 2：加班费", dataType = "Integer")
    })
    @PostMapping("/insert")
    public ResultInfo insert(@RequestParam("time")String time,@RequestParam("type")int type){
        ResultInfo resultInfo;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today=sdf.format(new Date(System.currentTimeMillis()));
        if(time.compareTo(today)<0){
            resultInfo=ResultInfo.failure();
            resultInfo.setMsg("时间不能小于今天");
            return resultInfo;
        }
        OverTimeDTO overTimeDTO=overTimeService.findToday(time);
        if(overTimeDTO!=null){
            resultInfo=ResultInfo.failure();
            resultInfo.setMsg("今日已添加");
            return resultInfo;
        }
        OverTimeDTO newover=new OverTimeDTO();
        newover.setDate(time);
        newover.setType(type);
        return ResultInfo.success();
    }
    @ApiOperation(value = "更新补贴方式",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "补贴id", dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "补贴方式 1: 加班餐 2：加班费", dataType = "String")
    })
    @PostMapping("/updateType")
    public ResultInfo updateType(@RequestParam("id")int id,@RequestParam("type")int type) {
        ResultInfo resultInfo;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today=sdf.format(new Date(System.currentTimeMillis()));
        OverTimeDTO overTimeDTO=overTimeService.findById(id);
        if(overTimeDTO.getDate().compareTo(today)<0){
            resultInfo=ResultInfo.failure();
            resultInfo.setMsg("往日不可修改");
            return resultInfo;
        }
        overTimeService.updateTypeById(id, type);
        return ResultInfo.success();
    }
}
