package com.jybd.bshop.ui.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jybd.bshop.base.BaseFragment;
import com.jybd.bshop.common.ConstontUrl;
import com.jybd.bshop.utils.nohttp.HttpListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/2 9:32
 * @Description :首页fragment
 */

public class HomeFragment extends BaseFragment implements HttpListener<String> {
    private static final String TAG = "HomeFragment";

    private int first_load_state = 101;
    private int refresh_state = 102;
    private int load_more_state = 103;
    private int load_state = first_load_state;
    private int requestWhat = 0;
    private boolean isLoading;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(activity);
        textView.setText("homeFragment");
        return textView;
    }

    @Override
    public void initData() {
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
        setLoading(isLoading);
    }

    @Override
    public void onFailed(int what, Response<String> response) {
        Log.e(TAG, "onFailed: " + response.get());
        setLoading(isLoading);
    }
}
