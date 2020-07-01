package com.example.order.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtil {



    /**

     * 获取YYYY-MM-DD HH:mm:ss格式

     * @return

     */

    public static String getTime() {

        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdfTime.format(new Date());

    }

}
