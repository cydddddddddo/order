package com.example.order.util;

/**
 * 常量接口
 * @author Cy
 * @data 2020/6/30 - 11:49
 */
public interface Constast {

    /**
     * 状态码
     *
     */
    String OK="0";
    String ERROR="1";

    String NEW_REAL = "2";
    Integer PASSWORD_ERR = 3;


    Integer TYPE_MENU = 1;
    Integer TYPE_PERMISSION = 2;

    Integer PERCODE_STU = 1;
    Integer PERCODE_TEA = 2;
    Integer PERCODE_MAN = 3;

    Integer OPEN_TRUE = 1;
    Integer OPEN_FALSE = 0;

    String IMAGE_GET_PATH = "/resources/images/";
    String IMAGE_SET_PATH = "D:\\Demo\\order\\src\\main\\resources\\static\\resources\\images\\";

    String IMAGE_DEFAULT = "userface3.jpg";
}
