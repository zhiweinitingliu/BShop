package com.jybd.bshop.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jybd.bshop.utils.LoadImage;

/**
 * @Description RecyclerHolder封装
 * @Author qyf
 * @Email 1063810202@qq.com
 */
public class RecyclerHolder extends RecyclerView.ViewHolder {
    private Context context;
    private View mConvertView;
    private SparseArray<View> mViews;

    public RecyclerHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.mConvertView = itemView;
        this.mViews = new SparseArray<>();
    }

    public static RecyclerHolder get(Context context, View itemView) {
        RecyclerHolder holder = new RecyclerHolder(context, itemView);
        return holder;
    }

    /**
     * 通过viewId获取控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * @param viewId
     * @param text
     * @return
     */
    public RecyclerHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public RecyclerHolder setText(int viewId, String text,int flag) {
        TextView tv = getView(viewId);
        tv.setText(text);
        tv.getPaint().setFlags(flag);
        return this;
    }

    public RecyclerHolder setText(int viewId, CharSequence text, TextView.BufferType type) {
        TextView tv = getView(viewId);
        tv.setText(text, type);
        return this;
    }

    public void setScore(int viewId, float score) {
        RatingBar rb = getView(viewId);
        rb.setRating(score);

    }

    public RecyclerHolder getViewByPosition(int position) {


        return this;
    }


    /**
     * @param viewId
     * @return
     */
    public String getText(int viewId) {
        TextView tv = getView(viewId);
        return tv.getText().toString().trim();
    }

    public RecyclerHolder setSelected(int viewId, boolean bool) {
        View iv = getView(viewId);
        iv.setSelected(bool);
        return this;
    }

    public RecyclerHolder setImageViewSelected(int viewId, boolean bool) {
        ImageView iv = getView(viewId);
        iv.setSelected(bool);
        return this;
    }

    public boolean isImageViewSelected(int viewId) {
        ImageView iv = getView(viewId);
        return iv.isSelected();
    }

    public RecyclerHolder setImageButtonSelected(int viewId, boolean bool) {
        ImageButton iv = getView(viewId);
        iv.setSelected(bool);
        return this;
    }

    public boolean isImageButtonSelected(int viewId) {
        ImageButton iv = getView(viewId);
        return iv.isSelected();
    }

    public boolean isSelected(int viewId) {
        View iv = getView(viewId);
        return iv.isSelected();
    }

    public RecyclerHolder setImage(int viewId, String url) {
        ImageView view = getView(viewId);
        LoadImage.loadRemoteImg(context, view, url);
        return this;
    }

    public RecyclerHolder setCircleImage(int viewId, String url) {
        ImageView view = getView(viewId);
        LoadImage.loadRemoteCircleImg(context, view, url);
        return this;
    }

    public RecyclerHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public RecyclerHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public RecyclerHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public RecyclerHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public RecyclerHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public RecyclerHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public RecyclerHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(context.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public RecyclerHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public RecyclerHolder setVisible(int position, boolean visible) {
        View view = getView(position);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public RecyclerHolder setVisibleAndClickListener(int position, boolean visible, View.OnClickListener listener) {
        View view = getView(position);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        view.setOnClickListener(listener);
        return this;
    }

    public RecyclerHolder setInVisibleAndClickListener(int position, boolean visible, View.OnClickListener listener) {
        View view = getView(position);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        view.setOnClickListener(listener);
        return this;
    }

    public RecyclerHolder setClickable(int viewId, boolean b) {
        View view = getView(viewId);
        view.setClickable(b);
        return this;
    }

    public RecyclerHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public RecyclerHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public RecyclerHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public RecyclerHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public RecyclerHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public RecyclerHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public RecyclerHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public RecyclerHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public RecyclerHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public RecyclerHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public RecyclerHolder setOnClickListener(int viewId,
                                             View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public RecyclerHolder setOnClickListener(int viewId1, int viewId2, int viewId3,
                                             int viewId4, int viewId5, int viewId6, View.OnClickListener listener) {
        View view1 = getView(viewId1);
        View view2 = getView(viewId2);
        View view3 = getView(viewId3);
        View view4 = getView(viewId4);
        View view5 = getView(viewId5);
        View view6 = getView(viewId6);
        view1.setOnClickListener(listener);
        view2.setOnClickListener(listener);
        view3.setOnClickListener(listener);
        view4.setOnClickListener(listener);
        view5.setOnClickListener(listener);
        view6.setOnClickListener(listener);
        return this;
    }

    public RecyclerHolder setOnTouchListener(int viewId,
                                             View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public RecyclerHolder setOnLongClickListener(int viewId,
                                                 View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public RecyclerHolder setView(int viewId, View view) {
        LinearLayout linearLayout = getView(viewId);
        linearLayout.addView(view);
        return this;
    }

    public RecyclerHolder setAdapter(int viewId, ListAdapter adapter) {
        ListView listView = getView(viewId);
        listView.setAdapter(adapter);
        return this;
    }


}
