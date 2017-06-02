package com.jybd.bshop.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jybd.bshop.R;

import java.io.File;


/**
 * 网络图片加载帮助类
 *
 * @author qyf
 */
public class LoadImage {

    /**
     * 加载网络图片
     *
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public static void loadRemoteImg(Context context, ImageView imageView, String imgUrl) {
    }

    /**
     * 加载网络图片
     *
     * @param
     * @param imageView
     * @param imgUrl
     */
    public static void loadRemoteImg(RequestManager requestManager, ImageView imageView, String imgUrl) {
        requestManager
                .load(imgUrl)
                .into(imageView);
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param imageView
     * @param filePath
     */
    public static void loadLocalImg(Context context, ImageView imageView, String filePath) {
        Glide.with(context)
                .load(new File(filePath))
                .into(imageView);
    }

    /**
     * 清除图片的缓存
     *
     * @param context
     */
    public static void clearCache(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void loadRemoteCircleImg(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 判断是否是主线程
     *
     * @return
     */
    public static boolean isInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

}