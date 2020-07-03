package com.example.order.module.apply.controller;


import com.example.order.dto.Apply;
import com.example.order.dto.CountGroup;
import com.example.order.dto.CountMeal;
import com.example.order.dto.UserDTO;
import com.example.order.module.apply.service.ApplyService;
import com.example.order.util.ResultInfo;
import com.example.order.web.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ApplyController extends BaseController {

    @Autowired
    ApplyService applyService;

    @RequestMapping("/addApply")
    @ResponseBody
    public ResultInfo addApply(String userId ){
        return  applyService.addApply(userId);
    }

    /*@RequestMapping("/updateStatus")
    @ResponseBody
    public  ResultInfo updateStatus(String id , int type){
        return  applyService.updateStatus(id,type);

    }*/

    @RequestMapping("/updateStatuss")
    @ResponseBody
    public  ResultInfo updateStatus(String checkIds , int type){
        String[] arr = checkIds.split(",");

        return  applyService.updateStatuss(arr,type);

    }


    @RequestMapping("/updateDescription" )
    @ResponseBody
    public  ResultInfo updateDescription(String id, String description){

        return  applyService.updateDescription(id,description);
    }


    //组长查看今天的申请
    @RequestMapping("/todayGroupApply")
    @ResponseBody
    public  ResultInfo selectGroupApply(Integer page,Integer limit){

        UserDTO user = this.getCurrentUser();

        PageHelper.startPage(page,limit);
        List<Apply> applyList =applyService.selectGroupApply(user.getUserGroup());
        PageInfo<Apply> applyPageInfo = new PageInfo<>(applyList);

        ResultInfo resultInfo=ResultInfo.success();
        Map <String,Object>map=new HashMap();
        map.put("count",(int)applyPageInfo.getTotal());
        map.put("info",(applyPageInfo.getList()));
        resultInfo.setData(map);
        return resultInfo;

    }

    @RequestMapping("countByMeal")
    @ResponseBody
    public  ResultInfo countByMeal(Timestamp startTime, Timestamp endTime, Integer page, Integer limit){

        PageHelper.startPage(page,limit);
        List<CountMeal> countMeals =applyService.CountByMeal(startTime,endTime);
        PageInfo<CountMeal> countMealPageInfo = new PageInfo<>(countMeals);
        ResultInfo resultInfo=ResultInfo.success();
        Map <String,Object>map=new HashMap();
        map.put("count",(int)countMealPageInfo.getTotal());
        map.put("info",(countMealPageInfo.getList()));
        resultInfo.setData(map);
        return resultInfo;
    }

    @RequestMapping("countByGroup")
    @ResponseBody
    public  ResultInfo countByGroup(Timestamp startTime, Timestamp endTime, Integer page, Integer limit){

        PageHelper.startPage(page,limit);
        List<CountGroup> countGroups =applyService.countByGroup(startTime,endTime);
        PageInfo<CountGroup> countGroupPageInfo = new PageInfo<>(countGroups);
        ResultInfo resultInfo=ResultInfo.success();
        Map <String,Object>map=new HashMap();
        map.put("count",(int)countGroupPageInfo.getTotal());
        map.put("info",(countGroupPageInfo.getList()));
        resultInfo.setData(map);
        return resultInfo;
    }




//    @RequestMapping("test")
//    @ResponseBody
//    public ResultInfo test(){
//        ResultInfo resultInfo=ResultInfo.success();
//
//        resultInfo.setData(applyDao.selectUserIdByGroup("man"));
//        return resultInfo;
//    }




}
