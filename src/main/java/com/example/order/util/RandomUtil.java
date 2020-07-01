package com.example.order.util;

import java.util.Random;

public class RandomUtil {


    /**

     * 随机生成六位数验证码

     * @return

     */

    public static int getRandomNum(){

        Random r = new Random();

        return r.nextInt(900000)+100000;//(int)(Math.random()*999999)

    }

}
