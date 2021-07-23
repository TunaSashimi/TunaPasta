package com.tunaPasta20.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.tunaPasta20.BR;
import com.tunaPasta20.R;
import com.tunaPasta20.data.BindingData;
import com.tunaPasta20.listener.OnBarListener;
import com.tunaPasta20.listener.OnInitListener;
import com.tunaPasta20.model.BindingModel;
import com.tunasushi.view.TView;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author Tunasashimi
 * @date 2020-01-17 11:44
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public abstract class BindingActivity<B extends ViewDataBinding> extends Activity implements OnBarListener, OnInitListener, TView.OnClickListener {
    protected B binding;
    protected View contentView;

    protected abstract int getLayoutId();

    protected abstract BindingModel getModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        initBinding();
        //
        onInit();
    }

    private void initBinding() {
        int layoutId = getLayoutId();
        if (layoutId == 0) {
            return;
        }
        try {
            binding = DataBindingUtil.setContentView(this, layoutId);
            if (binding != null) {
                contentView = binding.getRoot();
                BindingModel baseModel = getModel();
                binding.setVariable(BR.model, baseModel);
                binding.setVariable(BR.onBarListener, this);
                binding.setVariable(BR.onClickListener, this);
                //
                binding.setVariable(BR.data, BindingData.getInstance());
                binding.executePendingBindings();
            } else {
                contentView = LayoutInflater.from(this).inflate(layoutId, null);
                setContentView(contentView);
            }
        } catch (NoClassDefFoundError e) {

//            System.out.println("NoClassDefFoundError==>" + binding);

            contentView = LayoutInflater.from(this).inflate(layoutId, null);
            setContentView(contentView);
        }

//        System.out.println("initBinding==>" + binding);
    }


    @Override
    public Drawable getDrawableLeft() {
        return getResources().getDrawable(R.drawable.bitmap_iv_back);
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
}
