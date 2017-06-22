package com.jybd.bshop.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jybd.bshop.utils.nohttp.HttpListener;
import com.jybd.bshop.utils.nohttp.HttpResponseListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/2 9:33
 * @Description :baseFragment
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 请求队列。
     */
    private RequestQueue mQueue;
    public Activity activity;

    /**
     * 请求权限时候的请求码
     */
    private final int REQUESTCODE = 123;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化请求队列，传入的参数是请求并发值。
        mQueue = NoHttp.newRequestQueue(1);
        activity = this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        initData();
        return view;
    }

    /**
     * 初始化控件
     *
     * @param inflater           xx
     * @param container          xx
     * @param savedInstanceState xx
     * @return view视图
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    public abstract void initData();

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
        mQueue.add(what, request, new HttpResponseListener<T>(activity, request, callback, canCancel, isLoading));
    }

    /**
     * 将首次加载之后的Loading设置false
     *
     * @param isLoading
     */
    public void setLoading(boolean isLoading) {
        if (isLoading) {
            isLoading = false;
        }
    }

    /**
     * 请求权限
     *
     * @param permission 请求的权限
     */
    public void requestPermission(String permission) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasWriteContactsPermission = activity.checkSelfPermission(permission);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUESTCODE);
            } else {
                execute(permission);
            }
        } else {
            execute(permission);
        }
    }

    /**
     * 请求权限的回调
     *
     * @param requestCode  请求码
     * @param permissions  请求权限回调的权限数组
     * @param grantResults 请求权限回调的结果数组
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUESTCODE:
                try {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        execute(permissions[0]);
                    } else {
                        Toast.makeText(activity, "权限：" + permissions[0].substring(permissions[0].lastIndexOf(".") + 1) + " 被拒绝！\n操作已限制。", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (Exception e) {

                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 已经打开过权限 或者 用户同意次权限时候调用此方法
     *
     * @param permission 请求的权限
     */
    protected void execute(String permission) {

    }

}
