package com.jybd.bshop.ui.home.sideFragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jybd.bshop.R;
import com.jybd.bshop.base.BaseFragment;
import com.jybd.bshop.common.ConstontUrl;
import com.jybd.bshop.ui.home.SideHomeActivity;
import com.jybd.bshop.utils.nohttp.HttpListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/2 9:32
 * @Description :首页fragment
 */

public class SideHomeFragment extends BaseFragment implements HttpListener<String> {
    private static final String TAG = "HomeFragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int first_load_state = 101;
    private int refresh_state = 102;
    private int load_more_state = 103;
    private int load_state = first_load_state;
    private int requestWhat = 0;
    private boolean isLoading;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_side_home, container, false);
        ButterKnife.bind(this, view);
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        ((SideHomeActivity) activity).setDrawerToggle(toolbar);
        return view;
    }

    @Override
    public void initData() {
        requestData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        toolbar.inflateMenu(R.menu.main);
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
