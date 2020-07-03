package com.example.order.module.apply.serviceImpl;

import com.example.order.dto.*;
import com.example.order.module.apply.dao.ApplyDao;
import com.example.order.module.apply.service.ApplyService;
import com.example.order.module.user.dao.MealDao;
import com.example.order.module.user.dao.OverTimeDao;
import com.example.order.util.RandomUtil;
import com.example.order.util.ResultInfo;
import com.example.order.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    ApplyDao applyDao;

    @Autowired
    OverTimeDao overTimeDao;



    @Override
    public ResultInfo addApply(String userId) {

        if (null==userId||userId.length()==0)
        {return ResultInfo.failure();}
        else {

            Apply apply= new  Apply();
//            数据填充
//            得到id
            String id="";
            id= "zdww"+TimeUtil.getTime().replaceAll("[[\\s-:punct:]]", "")+ RandomUtil.getRandomNum();
            apply.setId(id);
            apply.setDate(new Timestamp(System.currentTimeMillis()));
            apply.setUserId(userId);
            apply.setDescription("");
            apply.setStatus(2);
            applyDao.insertApply(apply);
            return  ResultInfo.success();
        }
    }

    @Override
    public ResultInfo updateStatus(String id,int type) {
       if(null==id||id.length()==0||type==0){
           return ResultInfo.failure();
       }
       else {
           applyDao.updateStatus(id,type);
           return  ResultInfo.success();
       }
    }

//    选菜
    @Override
    public ResultInfo updateDescription(String id, String description) {
        if(null==id||id.length()==0||null==description||description.length()==0){
            return  ResultInfo.failure();
        }
        else {

            applyDao.updateDescription(id,description);
            return  ResultInfo.success();
        }

    }

    //批量操作申请
    @Override
    public ResultInfo updateStatuss(String[] ids, int type) {
        if(null==ids||ids.length==0||type==0){
            return  ResultInfo.failure();
        }
        else {
           OverTimeDTO overTimeDTO=overTimeDao.findToday(TimeUtil.getDate());
            System.out.println(TimeUtil.getDate());
            if (null==overTimeDTO||null==overTimeDTO.getDate()){
                 ResultInfo resultInfo=  ResultInfo.failure();
                 resultInfo.setMsg("请先等管理员确定加班补贴类型");
                 return  resultInfo;
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status",type);
            params.put("ids",ids);
            if(overTimeDTO.getType()==0){
                params.put("description","加班费");
            }
            if(overTimeDTO.getType()==1){
                params.put("description","加班餐");
            }

            applyDao.updateStatuss(params);
            return  ResultInfo.success();
        }

    }

    @Override
    public List<UserApply> selectGroupApply(String userGroup) {
        List<String> userIds =applyDao.selectUserIdByGroup(userGroup);
        return  applyDao.selectTodayGroup(userIds);
    }

    @Override
    public List<CountMeal> CountByMeal(String startTime, String endTime) {



             return  applyDao.countByMeal(startTime,endTime);
    }

    @Override
    public List<CountGroup> countByGroup(String startTime, String endTime,String userGroup) {



            return  applyDao.countByGroup(startTime,endTime,userGroup);

    }

    @Override
    public List<Apply> selectMember(String userId) {
        if(null==userId&&userId.length()==0){
            return  null;
        }
        else {
           List<Apply> applyList= applyDao.selectMember(userId);
          return  applyList;
        }

    }


}
