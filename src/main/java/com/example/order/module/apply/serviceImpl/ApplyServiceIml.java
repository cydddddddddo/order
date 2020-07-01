package com.example.order.module.apply.serviceImpl;

import com.example.order.dto.Apply;
import com.example.order.module.apply.dao.ApplyDao;
import com.example.order.module.apply.service.ApplyService;
import com.example.order.web.RandomUtil;
import com.example.order.web.ResultInfo;
import com.example.order.web.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class ApplyServiceIml implements ApplyService {

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
}
