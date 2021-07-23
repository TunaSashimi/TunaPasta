package com.tunaPasta14.adapter;

import android.view.View;

import com.tunaPasta14.bean.Person;

import androidx.recyclerview.widget.RecyclerView;


public abstract class TypeAbstartViewHolder extends RecyclerView.ViewHolder {
    public TypeAbstartViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(Person person);

}
