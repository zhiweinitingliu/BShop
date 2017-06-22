package com.jybd.bshop.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
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


/**
 * @Author : wdk
 * @Email : a15939582085@126.com
 * created on : 2017/6/21 9:09
 * @Description :闪屏页面
 */

public class SplashActivity extends BaseActivity implements View.OnClickListener {

    private boolean isShowSplash;

    Button sp_jump_btn;

    ImageView sp_bg;

    RelativeLayout rl_splash;

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
        initView();
    }

    private void initView() {
        sp_jump_btn = (Button) findViewById(R.id.sp_jump_btn);
        sp_bg = (ImageView) findViewById(R.id.sp_bg);
        rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
        sp_bg.setOnClickListener(this);
        sp_jump_btn.setOnClickListener(this);
    }


    @Override
    public void initData() {
        //加载网络图片
        loadImage(ConstontUrl.SPLASH_IMAGE_URL_2);
        //本地图片显示2秒，去判断网络闪屏图片是否加载完成，
        handler.sendEmptyMessageDelayed(local_splash_finish, 1500);
    }

    private void startDownTimer() {
        countDownTimer.start();
    }

    @Override
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
                finish();
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
        finish();
        startActivity(new Intent(activity, MainActivity.class));
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }
}
