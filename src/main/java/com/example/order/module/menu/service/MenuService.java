package com.example.order.module.menu.service;

import com.example.order.dto.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cy
 * @data 2020/6/30 - 15:36
 */
public interface MenuService {
    List<Menu> getMenuByType(Integer type, Integer percode);
}
