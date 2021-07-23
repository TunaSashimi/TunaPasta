package com.tunaPasta10.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tunaPasta10.R;

public class CheckBoxListTestAdapter extends BaseAdapter {
    // 填充数据的list  
    private ArrayList<String> list;
    // 用来控制CheckBox的选中状况  
    private static HashMap<Integer, Boolean> isSelected;
    // 用来导入布局  
    private LayoutInflater inflater;

    // 构造器  
    public CheckBoxListTestAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap();
        // 初始化数据  
        initDate();
    }

    // 初始化isSelected的数据  
    private void initDate() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 获得ViewHolder对象  
            holder = new ViewHolder();
            // 导入布局并赋值给convertview  
            convertView = inflater.inflate(R.layout.checkboxlisttestitem, null);
            holder.tv = convertView.findViewById(R.id.item_tv);
            holder.cb = convertView.findViewById(R.id.item_cb);
            // 为view设置标签  
            convertView.setTag(holder);
        } else {
            // 取出holder  
            holder = (ViewHolder) convertView.getTag();
        }
        // 设置list中TextView的显示  
        holder.tv.setText(list.get(position));
        // 根据isSelected来设置checkbox的选中状况  
        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        CheckBoxListTestAdapter.isSelected = isSelected;
    }

    public static class ViewHolder {
        TextView tv;
        public CheckBox cb;
    }
}  
