package com.tunaPasta14.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.tunaPasta14.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Tunasashimi
 * @date 2019-06-17 18:38
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<String> dataList;

    public RecycleViewAdapter(Context context, List<String> dataList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleViewHolder holder = new RecycleViewHolder(mLayoutInflater.inflate(R.layout.recyclerviewtestitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter.RecycleViewHolder holder, int position) {
        holder.textView.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //
    public void addData(int position) {
        dataList.add(position, "Insert");
        notifyDataSetChanged();
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public RecycleViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textview);
        }
    }
}
