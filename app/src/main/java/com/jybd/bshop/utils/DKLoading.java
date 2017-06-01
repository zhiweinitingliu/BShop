package com.jybd.bshop.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.jybd.bshop.R;

/**
 * @Author : wdk
 * @Email :a15939582085@126.com
 * Created on : 2017/5/11 9:24
 * @Description : 进入页面或者是提交下载是的 操作提醒
 */

public abstract class DKLoading extends Dialog {

    public DKLoading(Context context) {
        super(context, R.style.progress_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dk_loading);
    }

    /**
     * 返回的接口
     */
    public abstract void cancle();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancle();
        dismiss();
    }
}
