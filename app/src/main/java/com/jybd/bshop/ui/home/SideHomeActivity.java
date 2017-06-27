package com.jybd.bshop.ui.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;

import com.jybd.bshop.R;
import com.jybd.bshop.base.BaseActivity;
import com.jybd.bshop.ui.home.sideFragment.SideCustomerFragment;
import com.jybd.bshop.ui.home.sideFragment.SideHomeFragment;
import com.jybd.bshop.ui.home.sideFragment.SideMarktingFragment;
import com.jybd.bshop.ui.home.sideFragment.SideMoreFragment;
import com.jybd.bshop.utils.StatusBarUtil;
import com.jybd.bshop.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/27 9:05
 * @Description :可以侧滑的首页activity
 */

public class SideHomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "SideHomeActivity";


    private SideCustomerFragment customerFragment;
    private SideHomeFragment homeFragment;
    private SideMarktingFragment marktingFragment;
    private SideMoreFragment moreFragment;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    int alpha_main = 0;
    private int mStatusBarColor;


    @Override
    public void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_side_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mStatusBarColor = getResources().getColor(R.color.colorPrimary);
        StatusBarUtil.setColorForDrawerLayout(activity, drawer, mStatusBarColor, alpha_main);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void initData() {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        homeFragment = new SideHomeFragment();
        fragmentTransaction.add(R.id.frameLayout, homeFragment).commit();
        currentFragment = homeFragment;
    }

    @OnClick(R.id.rbStore)
    void onStore() {//首页fragment
        if (homeFragment == null) {
            homeFragment = new SideHomeFragment();
        }
        Log.e(TAG, "onStore: ");
        addFragment(homeFragment);
    }

    @OnClick(R.id.rbCustomer)
    void onCustomer() {//客户fragment
        if (customerFragment == null) {
            customerFragment = new SideCustomerFragment();
        }
        Log.e(TAG, "onCustomer:");
        addFragment(customerFragment);
    }

    @OnClick(R.id.rbMarkting)
    void onMarkting() {//促销fragment
        if (marktingFragment == null) {
            marktingFragment = new SideMarktingFragment();
        }
        Log.e(TAG, "onMarkting: ");
        addFragment(marktingFragment);
    }

    @OnClick(R.id.rbMore)
    void onMore() {//更多fragment
        if (moreFragment == null) {
            moreFragment = new SideMoreFragment();
        }
        Log.e(TAG, "onMore: ");
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
            fragmentTransaction.hide(currentFragment).add(R.id.frameLayout, addFragment).commit();
        } else {
            fragmentTransaction.hide(currentFragment).show(addFragment).commit();
        }
        currentFragment = addFragment;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
