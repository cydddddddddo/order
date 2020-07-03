package com.example.order.module.apply.dao;

import com.example.order.dto.*;
import com.example.order.dto.Apply;
import com.example.order.dto.CountGroup;
import com.example.order.dto.CountMeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ApplyDao {

    void  insertApply(Apply apply);

    void updateStatus(@Param("id") String id, @Param("type") int type);

    void updateStatuss(Map<String,Object> params);

    void updateDescription(@Param("id") String id, @Param("description") String description);

    List<String> selectUserIdByGroup(@Param("userGroup") String userGroup);

    List<UserApply> selectTodayGroup(List userIds);

    List<CountMeal> countByMeal(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<CountGroup> countByGroup(@Param("startTime") String startTime, @Param("endTime")String endTime,
                                    @Param("userGroup")String userGroup);

    List<Apply> selectMember(String userId);
}
