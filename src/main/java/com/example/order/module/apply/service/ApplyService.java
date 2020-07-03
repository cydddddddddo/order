package com.example.order.module.apply.service;


import com.example.order.dto.Apply;
import com.example.order.dto.CountGroup;
import com.example.order.dto.CountMeal;
import com.example.order.dto.UserApply;
import com.example.order.util.ResultInfo;

import java.sql.Timestamp;
import java.util.List;


public interface ApplyService {


    ResultInfo addApply(String userId);

    ResultInfo updateStatus(String id, int type);

    ResultInfo updateDescription(String id, String description);

    ResultInfo updateStatuss(String[] ids, int type);

    List<UserApply> selectGroupApply(String userGroup);


    List<CountMeal> CountByMeal(String startTime, String endTime);

    List<CountGroup> countByGroup(String startTime, String endTime,String userGroup);

    List<Apply> selectMember(String userId);
}
