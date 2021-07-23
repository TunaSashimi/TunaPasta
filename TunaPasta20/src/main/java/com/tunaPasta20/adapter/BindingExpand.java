package com.tunaPasta20.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.tunaPasta20.BR;
import com.tunaPasta20.data.BindingData;
import com.tunaPasta20.listener.OnItemClickListener;
import com.tunasushi.view.TView;
import java.util.List;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public class BindingExpand extends BaseExpandableListAdapter {
    private List list;
    protected int itemId01;
    protected int layoutId01;

    protected int itemId02;
    protected int layoutId02;

    protected OnItemClickListener onItemClickListener;

    //
    public ChildCountListener childCountListener;

    public interface ChildCountListener {
        int getChildCount(int groupPosition);
    }

    public ChildListener childListener;

    public interface ChildListener {
        Object getChild(int groupPosition, int childPosition);
    }


    public BindingExpand(List beanList,
                         int layoutId01, int layoutId02,
                         ChildCountListener childCountListener,
                         ChildListener childListener,
                         OnItemClickListener onItemClickListener) {
        this.list = beanList;
        this.itemId01 = BR.bean;
        this.itemId02 = BR.bean;
        this.layoutId01 = layoutId01;
        this.layoutId02 = layoutId02;
        this.childCountListener = childCountListener;
        this.childListener = childListener;
        this.onItemClickListener = onItemClickListener;
    }

    public BindingExpand(List beanList,
                         int itemId01, int layoutId01,
                         int itemId02, int layoutId02,
                         ChildCountListener childCountListener,
                         ChildListener childListener,
                         OnItemClickListener onItemClickListener) {
        this.list = beanList;
        this.itemId01 = itemId01;
        this.itemId02 = itemId02;
        this.layoutId01 = layoutId01;
        this.layoutId02 = layoutId02;
        this.childCountListener = childCountListener;
        this.childListener = childListener;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childrenCount = childCountListener.getChildCount(groupPosition);
        if (childrenCount > 0) {
            return childrenCount;
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childListener.getChild(groupPosition, childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewDataBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId01, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        TView.OnClickListener onClickListener = new TView.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, groupPosition, binding);
            }
        };
        //
        binding.setVariable(itemId01, list.get(groupPosition));
        binding.setVariable(BR.size, list.size());
        binding.setVariable(BR.position, groupPosition);
        binding.setVariable(BR.expanded, isExpanded);
        binding.setVariable(BR.onClickListener, onClickListener);
        //
        binding.setVariable(BR.data, BindingData.getInstance());
        //
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewDataBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId02, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        //
        binding.setVariable(itemId02, childListener.getChild(groupPosition, childPosition));
        //
        binding.setVariable(com.tunaPasta20.BR.data, BindingData.getInstance());
        //
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}