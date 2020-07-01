package com.example.order.module.menu.dao;

import com.example.order.dto.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cy
 * @data 2020/6/30 - 15:35
 */
@Repository
@Mapper
public interface MenuDao {

    List<Menu> getMenuByType(@Param("type")Integer type, @Param("percode")Integer percode);

}
