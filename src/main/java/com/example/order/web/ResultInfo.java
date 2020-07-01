package com.example.order.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Chencj
 * @description
 * @date 2020/2/7
 */
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@ApiModel(value="基础返回类",description="基础返回类")
public class ResultInfo {
    @ApiModelProperty(example="0")
    private String code;
    @ApiModelProperty(example="提示信息")
    private String msg;
    private Object data;

    public static ResultInfo success() {
        return ResultInfo.builder().code("0").msg("ok").build();
    }

    public static ResultInfo failure() {
        return ResultInfo.builder().code("1").msg("failure").build();
    }
}
