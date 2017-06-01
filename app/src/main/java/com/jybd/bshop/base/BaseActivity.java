package com.jybd.bshop.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jybd.bshop.nohttp.HttpListener;
import com.jybd.bshop.nohttp.HttpResponseListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/1 16:22
 * @Description :activity的基类
 */

public class BaseActivity extends AppCompatActivity {


    /**
     * 请求队列。
     */
    private RequestQueue mQueue;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化请求队列，传入的参数是请求并发值。
        mQueue = NoHttp.newRequestQueue(1);
    }

    /**
     * 发起请求。
     *
     * @param what      what.
     * @param request   请求对象。
     * @param callback  回调函数。
     * @param canCancel 是否能被用户取消。
     * @param isLoading 实现显示加载框。
     * @param <T>       想请求到的数据类型。
     */
    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean
            isLoading) {
//        request.setCancelSign(object);
        mQueue.add(what, request, new HttpResponseListener<T>(this, request, callback, canCancel, isLoading));
    }

}
