package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Cy
 * @data 2020/6/30 - 15:24
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private Integer id;

    /**
     * 父菜单id
     */
    private Integer pid;

    /**
     * 权限类型(1:菜单 2:功能)
     */
    private Integer type;

    private String title;

    /**
     * 访问权限
     */
    private Integer percode;

    private String icon;

    private String href;

    private Integer open;

    private Integer available;
}
