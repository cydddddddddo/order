package com.example.order.module.menu.service;

import com.example.order.dto.Menu;
import com.example.order.module.menu.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cy
 * @data 2020/6/30 - 15:36
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenuByType(Integer type, Integer percode) {
        return menuDao.getMenuByType(type,percode);
    }
}
