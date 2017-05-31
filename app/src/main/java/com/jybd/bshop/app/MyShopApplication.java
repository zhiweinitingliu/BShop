package com.jybd.bshop.app;

import android.app.Application;

import com.jybd.bshop.BuildConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/5/27 14:24
 * @Description :自定义application
 */

public class MyShopApplication extends Application {
    private static Application _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;

        Logger.setDebug(BuildConfig.DEBUG);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。

        // 一般情况下你只需要这样初始化：
        NoHttp.initialize(this);
    }

    public static Application getInstance() {
        return _instance;
    }
}
