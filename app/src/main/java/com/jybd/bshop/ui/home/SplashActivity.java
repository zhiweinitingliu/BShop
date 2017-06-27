package com.jybd.bshop.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jybd.bshop.R;
import com.jybd.bshop.base.BaseActivity;
import com.jybd.bshop.common.ConstontUrl;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/21 9:09
 * @Description :闪屏页面
 */

public class SplashActivity extends BaseActivity {

    private boolean isShowSplash;

    @BindView(R.id.sp_jump_btn)
    Button sp_jump_btn;

    @BindView(R.id.sp_bg)
    ImageView sp_bg;

    @BindView(R.id.rl_splash)
    RelativeLayout rl_splash;

    private Unbinder unbinder;

    private final int local_splash_finish = 10011;
    private final int local_splash_finish_tow = 10012;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case local_splash_finish://第一次校验网络闪屏图片是否加载完成
                    if (isShowSplash) {
                        rl_splash.setVisibility(View.VISIBLE);
                        startDownTimer();
                    } else {
                        handler.sendEmptyMessageDelayed(local_splash_finish_tow, 1000);
                    }
                    break;
                case local_splash_finish_tow://第二次校验网络闪屏图片是否加载完成
                    if (isShowSplash) {
                        rl_splash.setVisibility(View.VISIBLE);
                        startDownTimer();
                    } else {
                        gotoLoginOrMainActivity();
                    }
                    break;
            }
        }
    };

    @Override
    public void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        //加载网络图片
        if (getRandom() == 0) {
            loadImage(ConstontUrl.SPLASH_IMAGE_URL_1);
        } else {
            loadImage(ConstontUrl.SPLASH_IMAGE_URL_2);
        }

        //本地图片显示2秒，去判断网络闪屏图片是否加载完成，
        handler.sendEmptyMessageDelayed(local_splash_finish, 1500);
    }

    private void startDownTimer() {
        countDownTimer.start();
    }

    @OnClick({R.id.sp_bg, R.id.sp_jump_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sp_bg:
//                gotoWebActivity();
                countDownTimer.cancel();
                Toast.makeText(activity, "我要跳转到webview了", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.sp_jump_btn:
                countDownTimer.cancel();
                gotoLoginOrMainActivity();
                break;
        }
    }

    private CountDownTimer countDownTimer = new CountDownTimer(3200, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            sp_jump_btn.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            sp_jump_btn.setText("跳过(" + 0 + "s)");
            gotoLoginOrMainActivity();
        }
    };

    /**
     * 跳转到首页面
     */
    private void gotoLoginOrMainActivity() {
//        startActivity(new Intent(activity, MainActivity.class));//不带侧滑
        startActivity(new Intent(activity, SideHomeActivity.class));//带侧滑
        overridePendingTransition(R.anim.open_in, R.anim.open_out);
        finish();
    }


    /**
     * 加载图片，不设置内存和sd缓存
     *
     * @param url
     */
    public void loadImage(String url) {
        Glide.with(activity)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(target);
    }

    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            //图片加载完成
            sp_bg.setImageBitmap(bitmap);
            isShowSplash = true;
        }
    };

    private int getRandom() {
        Random random = new Random();
        int result = 0;
        int a = random.nextInt(10);
        if (a % 2 == 0) {
            result = 0;
        } else {
            result = 1;
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }
}
