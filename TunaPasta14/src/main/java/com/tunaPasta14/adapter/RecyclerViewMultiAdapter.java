package com.tunaPasta14.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tunaPasta14.R;
import com.tunaPasta14.bean.Person;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Tunasashimi
 * @date 2019-06-30 23:50
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */
public class RecyclerViewMultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;

    private List<Person> mList = new ArrayList<>();

    public RecyclerViewMultiAdapter(Context mContext) {
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    //使用此方法从获取数据
    public void addList(List<Person> list) {
        mList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据不同的viewType，创建并返回影响的ViewHolder
        switch (viewType) {
            case Person.TYPE_ONE:
                return new TypeHolder01(mLayoutInflater.inflate(R.layout.recyclerviewmultitestitem01, parent, false));
            case Person.TYPE_TWO:
                return new TypeHolder02(mLayoutInflater.inflate(R.layout.recyclerviewmultitestitem02, parent, false));
            case Person.TYPE_THREE:
                return new TypeHolder03(mLayoutInflater.inflate(R.layout.recyclerviewmultitestitem03, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TypeAbstartViewHolder) holder).bindHolder(mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //
    class TypeHolder01 extends TypeAbstartViewHolder {
        private ImageView avater;
        private TextView name;

        public TypeHolder01(View itemView) {
            super(itemView);
            avater = itemView.findViewById(R.id.avater);
            name = itemView.findViewById(R.id.name);
        }

        @Override
        public void bindHolder(Person person) {
            avater.setBackgroundResource(person.getAvaterColor());
            name.setText(person.getName());
        }
    }

    //
    class TypeHolder02 extends TypeAbstartViewHolder {
        private ImageView avater;
        private TextView name;
        private TextView content;

        public TypeHolder02(View itemView) {
            super(itemView);
            avater = itemView.findViewById(R.id.avater);
            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);
        }

        @Override
        public void bindHolder(Person person) {
            avater.setBackgroundResource(person.getAvaterColor());
            name.setText(person.getName());
            content.setText(person.getContent());
        }
    }

    //
    class TypeHolder03 extends TypeAbstartViewHolder {
        private ImageView avater;
        private TextView name;
        private TextView content;
        private ImageView iv;

        public TypeHolder03(View itemView) {
            super(itemView);
            avater = itemView.findViewById(R.id.avater);
            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);
            iv = itemView.findViewById(R.id.content_color);
        }

        @Override
        public void bindHolder(Person person) {
            avater.setBackgroundResource(person.getAvaterColor());
            name.setText(person.getName());
            content.setText(person.getContent());
            iv.setBackgroundResource(person.getAvaterColor());
        }
    }
}


