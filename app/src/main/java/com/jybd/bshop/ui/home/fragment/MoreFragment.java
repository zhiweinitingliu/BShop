package com.jybd.bshop.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jybd.bshop.base.BaseFragment;

/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/2 9:32
 * @Description :促销fragment
 */

public class MoreFragment extends BaseFragment {

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(activity);
        textView.setText("MoreFragment");
        return textView;
    }

    @Override
    public void initData() {

    }
}
