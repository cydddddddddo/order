package com.example.order.module.user.service;

import com.example.order.dto.OverTimeDTO;
import com.example.order.module.user.dao.OverTimeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Chencj
 * @description
 * @date 2020/6/30
 */
@Service
public class OverTimeSeviceImpl implements OverTimeService{
    @Resource
    private OverTimeDao overTimeDao;

    @Override
    public List<OverTimeDTO> findAll() {
        return overTimeDao.findAll();
    }

    @Override
    public int insert(OverTimeDTO overTimeDTO) {
        return overTimeDao.insert(overTimeDTO);
    }

    @Override
    public int updateTypeById(int id, int type) {
        return overTimeDao.updateTypeById(id, type);
    }

    @Override
    public OverTimeDTO findToday(String timestamp) {
        return overTimeDao.findToday(timestamp);
    }

    @Override
    public OverTimeDTO findById(int id) {
        return overTimeDao.findById(id);
    }
}
