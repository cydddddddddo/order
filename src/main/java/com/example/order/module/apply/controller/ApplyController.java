package com.example.order.module.apply.controller;


import com.example.order.dto.*;
import com.example.order.dto.UserDTO;
import com.example.order.module.apply.service.ApplyService;
import com.example.order.module.user.service.MealService;
import com.example.order.util.ResultInfo;
import com.example.order.web.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ApplyController extends BaseController {

    @Autowired
    ApplyService applyService;

    @Autowired
    MealService mealService;

    @RequestMapping("addApply")
    @ResponseBody
    public ResultInfo addApply(){


        return  applyService.addApply("0001");
    }

    //员工查询个人申请表
    @RequestMapping("selectMember")
    @ResponseBody
    public ResultInfo selectMember(@RequestParam(defaultValue = "1")Integer page,Integer limit ){

//        UserDTO user = this.getCurrentUser();

        PageHelper.startPage(page,limit);
        List<Apply> applyList=applyService.selectMember("0001");
        PageInfo<Apply> applyPageInfo = new PageInfo<>(applyList);

        ResultInfo resultInfo=ResultInfo.success();
        Map <String,Object>map=new HashMap();
        map.put("count",(int)applyPageInfo.getTotal());
        map.put("info",(applyPageInfo.getList()));
        resultInfo.setData(map);
        return resultInfo;
}


    //审批
    @RequestMapping("/updateStatus")
    @ResponseBody
    public  ResultInfo updateStatus(String id , int type){



        return  applyService.updateStatus(id,type);

    }
    //批量审批
    @RequestMapping("/updateStatuss")
    @ResponseBody
    public  ResultInfo updateStatus( String[] ids , int type){
        return  applyService.updateStatuss(ids,type);

    }


    @RequestMapping("findMeal" )
    @ResponseBody
    public  ResultInfo findMeal(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10") Integer limit){

        PageHelper.startPage(page,limit);
       List<MealDTO> mealDTOS= mealService.findAll();
        PageInfo<MealDTO> mealDTOPageInfo = new PageInfo<>(mealDTOS);
        Map <String,Object>map=new HashMap();
        map.put("count",(int)mealDTOPageInfo.getTotal());
        map.put("info",(mealDTOPageInfo.getList()));
//        map.put("groupTotal",groupTotal);
        ResultInfo resultInfo=ResultInfo.success();
        resultInfo.setData(map);
        return  resultInfo;
    }


    //选菜
    @RequestMapping("updateDescription" )
    @ResponseBody
    public  ResultInfo updateDescription(String id, String description){

        return  applyService.updateDescription(id,description);
    }


    //组长查看今天的申请
    @RequestMapping("todayGroupApply")
    @ResponseBody
    public  ResultInfo selectGroupApply(Integer page,Integer limit){

        UserDTO user = this.getCurrentUser();
        PageHelper.startPage(page,limit);
        List<UserApply> applyList =applyService.selectGroupApply(user.getUserGroup());
        PageInfo<UserApply> applyPageInfo = new PageInfo<>(applyList);

        ResultInfo resultInfo=ResultInfo.success();
        Map <String,Object>map=new HashMap();
        map.put("count",(int)applyPageInfo.getTotal());
        map.put("info",(applyPageInfo.getList()));
        resultInfo.setData(map);
        return resultInfo;

    }

    @RequestMapping("countByMeal")
    @ResponseBody
    public  ResultInfo countByMeal(String startTime, String endTime, @RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10") Integer limit){

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
    public  ResultInfo countByGroup(String startTime, String endTime, String userGroup, @RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5") Integer limit){

        PageHelper.startPage(page,limit);
        List<CountGroup> countGroups =applyService.countByGroup(startTime,endTime,userGroup);
        //统计组订餐总数
//        int groupTotal=0;
//        for (CountGroup countGroup: countGroups
//             ) {
//            groupTotal+=countGroup.getCount();
//        }
        PageInfo<CountGroup> countGroupPageInfo = new PageInfo<>(countGroups);
        ResultInfo resultInfo=ResultInfo.success();
        Map <String,Object>map=new HashMap();
        map.put("count",(int)countGroupPageInfo.getTotal());
        map.put("info",(countGroupPageInfo.getList()));

//        map.put("groupTotal",groupTotal);
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
