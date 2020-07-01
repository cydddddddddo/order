package com.example.order.dto;

import lombok.Data;

/**
 * @author Chencj
 *@description 菜品
 * @date 2020/6/30
 */
@Data
public class MealDTO {
    private String id;
    private String name;
    private String description;
    private String picture;
    /**
     *  0 失效 1 有效
     */
    private String status;
}
