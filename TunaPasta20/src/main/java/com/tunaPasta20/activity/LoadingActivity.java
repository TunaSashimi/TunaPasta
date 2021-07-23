package com.tunaPasta20.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.tunaPasta20.R;
import com.tunaPasta20.databinding.ActivityLoadingBinding;
import com.tunaPasta20.model.BindingModel;
import com.tunaPasta20.model.LoadingModel;
import com.tunasushi.view.TLoad;

/**
 * @author TunaSashimi
 * @date 2020-03-04 20:50
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class LoadingActivity extends BindingActivity<ActivityLoadingBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    protected BindingModel getModel() {
        return new LoadingModel(binding, getIntent());
    }

    @Override
    public void onInit() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tView:
                break;
            case R.id.iv_include_left:
                finish();
                break;
            case R.id.btnShow:
                binding.getModel().showLoading();
                break;
            case R.id.btnDismiss:
                binding.getModel().dismissLoading();
                break;
            case R.id.btnFloat:
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Settings.canDrawOverlays(this)) {
                        Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
                        startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 10);
                    } else {
                        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                        TLoad tLoadW = new TLoad(this);
                        tLoadW.setBackgroundColor(Color.WHITE);
                        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                        } else {
                            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                        }
                        layoutParams.format = PixelFormat.RGBA_8888;
                        layoutParams.width = 200;
                        layoutParams.height = 200;
                        windowManager.addView(tLoadW, layoutParams);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "not granted", Toast.LENGTH_SHORT);
                }
            }
        }
    }
}
