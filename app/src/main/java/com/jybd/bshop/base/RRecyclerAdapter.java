package com.jybd.bshop.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jybd.bshop.holder.RecyclerHolder;

import java.util.List;

/**
 * @Description RRecyclerAdapter封装
 * @Result 1.只有一种item的情况下，缓存的ViewHolder的数目为RecyclerView在滑动过程中所能在一屏内容纳的最大item个数+2
 * 2.有至少两种item显示的情况下，每种item的ViewHolder的缓存个数为单种item在一屏内最大显示个数+1
 * @Author qyf
 * @Email 1063810202@qq.com
 */
public abstract class RRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {
    protected Context context;
    protected List<T> datas;
    protected int layoutId;
    protected int position;

    public RRecyclerAdapter(Context context){
        this.context=context;
    }

    public RRecyclerAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    public RRecyclerAdapter(Context context, int layoutId, List<T> datas) {
        this.context = context;
        this.layoutId = layoutId;
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), layoutId, null);
        return new RecyclerHolder(context, itemView);

    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        this.position=position;
        convert(holder, datas.get(position));
    }

    public abstract void convert(RecyclerHolder holder, T t);



}