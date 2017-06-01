package com.jybd.bshop.ui.home;

import android.os.Bundle;
import android.util.Log;

import com.jybd.bshop.R;
import com.jybd.bshop.base.BaseActivity;
import com.jybd.bshop.common.ConstontUrl;
import com.jybd.bshop.nohttp.HttpListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import javax.net.ssl.SSLContext;

public class MainActivity extends BaseActivity implements HttpListener<String> {
    private static final String TAG = "MainActivity";

    private int requestWhat = 0;

    private int first_load_state = 101;
    private int refresh_state = 102;
    private int load_more_state = 103;
    private int load_state = first_load_state;
    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        requestData();
    }

    private void requestData() {
        if (load_state == first_load_state) {
            isLoading = true;
        }

        Request<String> httpRequest = NoHttp.createStringRequest(ConstontUrl.URL_NOHTTP_METHOD, RequestMethod.POST);
        httpRequest.add("name", "yanzhenjie");// String类型
        httpRequest.add("pwd", 123);
        httpRequest.add("userAge", 20);// int类型
        httpRequest.add("userSex", '1');// char类型，还支持其它类型
        request(requestWhat, httpRequest, this, false, isLoading);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        Log.e(TAG, "onSucceed: " + response.get());
    }

    @Override
    public void onFailed(int what, Response<String> response) {
        Log.e(TAG, "onFailed: " + response.get());
    }
}
