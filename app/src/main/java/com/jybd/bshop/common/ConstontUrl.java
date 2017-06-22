package com.jybd.bshop.common;

import com.jybd.bshop.app.MyShopApplication;

/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/1 16:11
 * @Description :
 */

public class ConstontUrl {

    /**
     * 服务器地址.
     */
    public static final String SERVER = "http://api.nohttp.net/";

    /**
     * 各种方法测试。
     */
    public static final String URL_NOHTTP_METHOD = SERVER + "method";


    public static final String SPLASH_IMAGE_URL_1 = "http://ceshi.jybd.cn/app/ceshi_splash001.jpg";
    public static final String SPLASH_IMAGE_URL_2 = "http://ceshi.jybd.cn/app/ceshi_splash002.jpg";
    //动态闪屏序列化地址
    public static final String SPLASH_IMAGE_DOWNLOADED_PATH= MyShopApplication.getInstance().getFilesDir().getAbsolutePath()+"/bshop";


}
