package com.example.order.web;

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
public class ResultInfo {
    private String code;
    private String msg;
    private Object data;

    public static ResultInfo success() {
        return ResultInfo.builder().code("0").msg("ok").build();
    }

    public static ResultInfo failure() {
        return ResultInfo.builder().code("1").msg("failure").build();
    }
}
