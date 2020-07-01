package com.example.order.module.apply.service;


import com.example.order.web.ResultInfo;
import org.springframework.stereotype.Service;

@Service
public interface ApplyService {


    ResultInfo addApply(String userId);
}
