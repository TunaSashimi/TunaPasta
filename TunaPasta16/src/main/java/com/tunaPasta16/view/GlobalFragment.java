package com.tunaPasta16.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tunaPasta16.R;

public class GlobalFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = this.getDialog().getWindow();
        setWindowStyle(window);
        View view = inflater.inflate(R.layout.fragment_global, null);
        return view;
    }

    /**
     * 设置当前window的样式
     *
     * @param window 可以通过该window设置dialog window
     */
    protected void setWindowStyle(Window window) {
        if (window == null || getContext() == null) {
            return;
        }
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //这里就是直接去掉边距的代码。
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(getLayoutParams(window.getAttributes()));
    }

    /**
     * 设置dialog布局参数
     *
     * @param params 通过该参数设置相应的属性
     */
    protected WindowManager.LayoutParams getLayoutParams(WindowManager.LayoutParams params) {
        if (params == null) {
            return new WindowManager.LayoutParams();
        }
        //注意这里设置的宽高，需要设置成MATCH_PARENT,不然的话就不会起作用，就这一点坑。
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.TOP;
        return params;
    }
}
