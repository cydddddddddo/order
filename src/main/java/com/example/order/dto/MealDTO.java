package com.example.order.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Chencj
 *@description 菜品
 * @date 2020/6/30
 */
@Data
@ApiModel(value="菜品类",description="请求参数类" )
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
