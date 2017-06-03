/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jybd.bshop.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class containing some static utility methods.
 */
public class Utils {
    public static final int IO_BUFFER_SIZE = 8 * 1024;

    private static final double EARTH_RADIUS = 6378137.0;

    private Utils() {
    }

    public static int WIFI = 2;
    public static int MOBILE_NET = 1;
    public static int NO_NEY = 0;

    /**
     * @return void
     * @Description: 检查网络状态
     */
    public static int checkNetworkState(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        boolean mobileStatus = (mobile == State.CONNECTED || mobile == State.CONNECTING);
        boolean wifiStatus = (wifi == State.CONNECTED || wifi == State.CONNECTING);
        if (!mobileStatus && !wifiStatus) {
            return 0;
        } else if (mobileStatus && !wifiStatus) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * @param context
     * @return void
     * @Description: 隐藏软键盘
     */
    public static void hideIputMethord(Activity context) {
        if (context.getCurrentFocus() != null) {
            ((InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(context.getCurrentFocus()
                                    .getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * @param context
     * @return float
     * @Description: 得到设备密度
     */
    public static float getDensity(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.density;

    }

    /**
     * @param context
     * @return float
     * @Description: 得到设备高度
     */
    public static int getScreenHeight(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels - getStatusBarHeight(context);
    }

    /**
     * @param context
     * @param packageName
     * @return boolean
     * @Description: 判断是否安装了此应用
     */
    public static boolean AHAappInstalledOrNot(Context context,
                                               String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    /**
     * 获取设备UUID
     *
     * @param context
     * @return
     */
    public static String getUUID(Context context) {
        final TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = ""
                + android.provider.Settings.Secure.getString(
                context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId;
    }

    /**
     * @param context
     * @return int
     * @Description: 获取Status Bar高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * @param context
     * @param value
     * @return int
     * @Description: 像素值转化为DP值
     */
    public static int getDPValue(Context context, float value) {
        int dpValue = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, context.getResources()
                        .getDisplayMetrics());
        return dpValue;
    }

    /**
     * @param max
     * @param min
     * @return int
     * @Description: TODO
     */
    public static int getRandom(int max, int min) {
        Random random = new Random();
        int result = random.nextInt(max + 1);// 取小于或等于max的一个随机数
        if (result < min) {
            result = getRandom(max, min);// 如果随机数小于min，则递归
        }
        return result;
    }

    /**
     * @param mail
     * @return boolean
     * @Description: 邮箱验证
     */
    public static boolean isValidEmail(String mail) {
        Pattern pattern = Pattern
                .compile("^[A-Za-z0-9][\\w\\._]*[a-zA-Z0-9]+@[A-Za-z0-9-_]+\\.([A-Za-z]{2,4})((.[A-Za-z]{2,4})*)");
        Matcher mc = pattern.matcher(mail);
        return mc.matches();
    }

    /**
     * @param mail
     * @return boolean
     * @Description: 邮箱验证
     */
    public static boolean isValidPassword(String mail) {
        Pattern pattern = Pattern
                .compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$");
        Matcher mc = pattern.matcher(mail);
        return mc.matches();
    }

    /**
     * 从view 得到图片
     *
     * @param view
     * @return
     */
    public static Bitmap getBitmapFromView(View view) {
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache(true);
        return bitmap;
    }

    /**
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return double meter
     * @Description: Get distance between two geo
     */
    public static double getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static String splitURLLastString(String string) {
        if (string == null) {
            return null;
        }
        String a[] = string.split("/");
        int length = a.length;
        if (length > 0) {
            return a[length - 1];
        }
        return null;
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param context
     * @param className 判断的服务名字：包名+类名
     * @return true 在运行, false 不在运行
     */

    public static boolean isServiceRunning(Context context, String className) {

        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;

            }
        }
        return isRunning;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 检测某Activity是否在当前Task的栈顶
     */

    public static boolean isTopActivy(String cmdName, Context context) {
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Activity.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        String cmpNameTemp = null;
        if (null != runningTaskInfos) {
            cmpNameTemp = (runningTaskInfos.get(0).topActivity).getClassName();
        }
        if (null == cmpNameTemp)
            return false;
        return cmpNameTemp.equals(cmdName);

    }

    /**
     * 判断程序是否在前台
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Log.i("后台", appProcess.processName);
                    return true;
                } else {
                    Log.i("前台", appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Returns a valid DisplayMetrics object
     *
     * @param context valid context
     * @return DisplayMetrics object
     */
    public static DisplayMetrics getDisplayMetrics(final Context context) {
        final WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 日期时间转换为秒
     */
    public static long timeToSec(String date) {
        long date1 = 0;
//		//去除-
//		String dateTime = date.replaceAll( "-","");
//		Calendar c = Calendar.getInstance();
//		try {
//			c.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(dateTime));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		System.out.println("时间转化后的毫秒数为："+c.getTimeInMillis());
//		return c.getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date1 = sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

}
