package com.example.order.module.apply.serviceImpl;

import com.example.order.dto.Apply;
import com.example.order.dto.CountGroup;
import com.example.order.dto.CountMeal;
import com.example.order.module.apply.dao.ApplyDao;
import com.example.order.module.apply.service.ApplyService;
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

    @Override
    public ResultInfo updateStatuss(String[] ids, int type) {
        if(null==ids||ids.length==0||type==0){
            return  ResultInfo.failure();
        }
        else {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status",type);
            params.put("ids",ids);
            applyDao.updateStatuss(params);
            return  ResultInfo.success();
        }

    }

    @Override
    public List<Apply> selectGroupApply(String userGroup) {
        List<String> userIds =applyDao.selectUserIdByGroup(userGroup);
        return  applyDao.selectTodayGroup(userIds);
    }

    @Override
    public List<CountMeal> CountByMeal(Timestamp startTime, Timestamp endTime) {
        if(null==startTime||null==endTime){
            return null;
        }
        else {

             return  applyDao.countByMeal(startTime,endTime);
        }
    }

    @Override
    public List<CountGroup> countByGroup(Timestamp startTime, Timestamp endTime) {
        if(null==startTime||null==endTime){
            return null;
        }
        else {

            return  applyDao.countByGroup(startTime,endTime);
        }
    }


}
