package com.jybd.bshop.ui.home.sideFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jybd.bshop.R;
import com.jybd.bshop.base.BaseFragment;

/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/2 9:32
 * @Description :客户fragment
 */

public class SideCustomerFragment extends BaseFragment {

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_side_customer, container, false);
        return view;
    }

    @Override
    public void initData() {

    }
}
