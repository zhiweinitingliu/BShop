package com.jybd.bshop.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;

import com.jybd.bshop.R;
import com.jybd.bshop.base.BaseActivity;
import com.jybd.bshop.ui.home.fragment.CustomerFragment;
import com.jybd.bshop.ui.home.fragment.HomeFragment;
import com.jybd.bshop.ui.home.fragment.MarktingFragment;
import com.jybd.bshop.ui.home.fragment.MoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private CustomerFragment customerFragment;
    private HomeFragment homeFragment;
    private MarktingFragment marktingFragment;
    private MoreFragment moreFragment;
    private FragmentManager supportFragmentManager;
    private Fragment currentFragment;

    @BindView(R.id.rbStore)
    RadioButton rbStore;

    @BindView(R.id.rbCustomer)
    RadioButton rbCustomer;

    @BindView(R.id.rbMarkting)
    RadioButton rbMarkting;

    @BindView(R.id.rbMore)
    RadioButton rbMore;

    @Override
    public void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        fragmentTransaction.add(R.id.frameLayout, homeFragment).commit();
        currentFragment = homeFragment;
    }

    @OnClick(R.id.rbStore)
    void onStore() {//首页fragment
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        addFragment(homeFragment);
    }

    @OnClick(R.id.rbCustomer)
    void onCustomer() {//客户fragment
        if (customerFragment == null) {
            customerFragment = new CustomerFragment();
        }
        addFragment(customerFragment);
    }

    @OnClick(R.id.rbMarkting)
    void onMarkting() {//促销fragment
        if (marktingFragment == null) {
            marktingFragment = new MarktingFragment();
        }
        addFragment(marktingFragment);
    }

    @OnClick(R.id.rbMore)
    void onMore() {//更多fragment
        if (moreFragment == null) {
            moreFragment = new MoreFragment();
        }
        addFragment(moreFragment);
    }

    /**
     * 将fragment添加到activity
     *
     * @param addFragment 添加的fragment
     */
    public void addFragment(Fragment addFragment) {
        if (addFragment == currentFragment) {//点击的是当前Fragment
            return;
        }

        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (!addFragment.isAdded()) {//判断需要添加Fragment是否是添加过
            fragmentTransaction.add(R.id.frameLayout, addFragment).commit();
        } else {
            fragmentTransaction.hide(currentFragment).show(addFragment).commit();
        }
        currentFragment = addFragment;
    }


}
