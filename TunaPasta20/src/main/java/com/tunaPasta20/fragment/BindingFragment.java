package com.tunaPasta20.fragment;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tunaPasta20.BR;
import com.tunaPasta20.data.BindingData;
import com.tunaPasta20.listener.OnBarListener;
import com.tunaPasta20.listener.OnInitListener;
import com.tunaPasta20.listener.OnLoadListener;
import com.tunaPasta20.model.BindingModel;
import com.tunasushi.view.TView;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author TunaSashimi
 * @date 2020-02-20 21:35
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public abstract class BindingFragment<B extends ViewDataBinding> extends Fragment implements OnBarListener, OnInitListener, OnLoadListener, TView.OnClickListener {
    protected B binding;
    protected View contentView;

    protected abstract int getLayoutId();

    protected abstract BindingModel getModel();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        int layoutId = getLayoutId();
        if (contentView == null) {
            try {
                binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
                contentView = binding.getRoot();
                BindingModel bindingModel = getModel();
                binding.setVariable(BR.model, bindingModel);
                binding.setVariable(BR.onBarListener, this);
                binding.setVariable(BR.onClickListener, this);
                //
                binding.setVariable(BR.data, BindingData.getInstance());
                binding.executePendingBindings();
            } catch (NoClassDefFoundError e) {
                contentView = inflater.inflate(layoutId, container, false);
            }
            if (contentView != null) {
                contentView.setClickable(true);
                onInit();
            }
        }
        return contentView;
    }

    @Override
    public Drawable getDrawableLeft() {
        return null;
    }

    @Override
    public Drawable getDrawableRight() {
        return null;
    }

    @Override
    public String getTextCenter() {
        return null;
    }

    @Override
    public String getTextLeft() {
        return null;
    }

    @Override
    public String getTextRight() {
        return null;
    }

    //
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    //
    protected void onVisible() {
    }

    //
    protected void onInVisible() {
    }

    //
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisible() && isVisibleToUser && contentView != null) {
            onLoad();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }
}
