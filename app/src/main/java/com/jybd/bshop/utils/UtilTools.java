package com.jybd.bshop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 辅助类用于一些常规方法
 * @author:wdk
 * @time:2017-06-03
 */
public class UtilTools {

    /**
     * 得到状态栏高度信息
     *
     * @param
     * @return > 0 success; <= 0 fail
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    public static int getScreenWidth(Context context) {
        if (context != null) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm.widthPixels;
        } else {
            return 0;
        }

    }

    public static int getScreenHeight(Context context) {
        if (context != null) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm.heightPixels;
        } else {
            return 0;
        }
    }

    public static float getScreenDensity(Context context, int i) {
        int dp = 0;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        dp = (int) (i * dm.density);
        return dp;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 判断是否有闪光灯
     *
     * @param context
     * @return
     */
    public static boolean isFlashLight(Context context) {
        PackageManager pm = context.getPackageManager();
        FeatureInfo[] features = pm.getSystemAvailableFeatures();
        for (FeatureInfo f : features) {
            if (PackageManager.FEATURE_CAMERA_FLASH.equals(f.name))   //判断设备是否支持闪光灯
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一个对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean empty(Object o) {
        return o == null || "".equals(o.toString().trim())
                || "null".equalsIgnoreCase(o.toString().trim())
                || "undefined".equalsIgnoreCase(o.toString().trim());
    }


    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 判断是否存在Sdcard
     *
     * @return
     */
    public static boolean existsSD() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * @param date  传入的date
     * @param style 要变化成的字符串类型  比如：yyyy-MM-dd HH:mm:ss
     * @return
     * @Description::将date类型转换为字符串
     */
    public static String getStringFromDate(Date date, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);

        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    /**
     * @param dateStr 要转换的字符串
     * @param style   要转换的类型
     * @return
     * @Description:将字符串转换为date类型的方法
     */
    public static Date getDateFromString(String dateStr, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 跳到打电话的界面，但是没有拨出
     *
     * @param phoneNum
     */
    public static void startTel(Context mContext, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    //判断手机格式是否正确

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$"); // 验证手机号
//		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^4,\\D])|(17[0,8]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //判断email格式是否正确
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 判断输入框是否有输入，并提示信息
     *
     * @param w
     * @param displayStr
     * @return
     */
    public static boolean isEmpty(TextView w, String displayStr) {
        if (empty(w.getText().toString().trim())) {
            displayStr = displayStr + "不能为空!";
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(displayStr);
            w.setError(ssbuilder);
            w.setFocusable(true);
            w.requestFocus();
            return true;
        }
        return false;
    }

    /**
     * 删除手机号码中的非数字字符
     *
     * @param phoneNumber
     * @return
     */
    public static String trimPhoneNumber(String phoneNumber) {
        String mobile = "";

        for (int i = 0; i < phoneNumber.length(); i++) {
            if (Character.isDigit(phoneNumber.charAt(i))) {
                mobile += phoneNumber.charAt(i);
            }
        }

        /** 手机号码大于11位时需要截取 */
        if (mobile.length() > 11) {
            mobile = mobile.substring(mobile.length() - 11, mobile.length());
        }

        return mobile;
    }

    /**
     * 保留两位小数
     * @param price
     * @return
     */
    public static String toString(float price) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(price);
    }

}
