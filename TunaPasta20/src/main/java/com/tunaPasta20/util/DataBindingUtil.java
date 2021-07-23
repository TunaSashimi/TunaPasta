package com.tunaPasta20.util;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.tunasushi.tool.BitmapTool;
import com.tunasushi.view.TView;
import com.tunasushi.view.TWrap;
import java.util.List;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

/**
 * @author TunaSashimi
 * @date 2020-03-13 18:12
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class DataBindingUtil {
    //这两行虽然在com.tunasushi.tuna的TBinding中有但是要这里再次配置才能生效
    @InverseBindingAdapter(attribute = "select", event = "selectChange")
    public static boolean isSelect(TView t) {
        return t.isSelect();
    }

    @BindingAdapter(value = {"selectChange"})
    public static void setSelectChange(TView t, InverseBindingListener inverseBindingListener) {
        if (inverseBindingListener != null) {
            t.setInverseBindingListener(inverseBindingListener);
        }
    }

    @BindingAdapter({"srcLeftNormal"})
    public static void setSrcLeftNormal(TView t, Bitmap srcLeftNormal) {
        t.setSrcLeft(srcLeftNormal);
    }

    @BindingAdapter({"srcLeftPress"})
    public static void setSrcLeftPress(TView t, Bitmap srcLeftPress) {
        t.setSrcLeftPress(srcLeftPress);
    }

    @BindingAdapter({"srcLeftSelect"})
    public static void setSrcLeftSelect(TView t, Bitmap srcLeftSelect) {
        t.setSrcLeftSelect(srcLeftSelect);
    }

    @BindingAdapter({"srcLeftNormal"})
    public static void setSrcLeftNormal(TView t, Drawable srcLeftNormal) {
        t.setSrcLeft(BitmapTool.drawableToBitmap(srcLeftNormal));
    }

    @BindingAdapter({"srcLeftPress"})
    public static void setSrcLeftPress(TView t, Drawable srcLeftPress) {
        t.setSrcLeftPress(BitmapTool.drawableToBitmap(srcLeftPress));
    }

    @BindingAdapter({"srcLeftSelect"})
    public static void setSrcLeftSelect(TView t, Drawable srcLeftSelect) {
        t.setSrcLeftSelect(BitmapTool.drawableToBitmap(srcLeftSelect));
    }

    @BindingAdapter({"srcRightNormal"})
    public static void setSrcRightNormal(TView t, Bitmap srcRightNormal) {
        t.setSrcRight(srcRightNormal);
    }

    @BindingAdapter({"srcRightPress"})
    public static void setSrcRightPress(TView t, Bitmap srcRightPress) {
        t.setSrcRightPress(srcRightPress);
    }

    @BindingAdapter({"srcRightSelect"})
    public static void setSrcRightSelect(TView t, Bitmap srcRightSelect) {
        t.setSrcRightSelect(srcRightSelect);
    }

    @BindingAdapter({"srcRightNormal"})
    public static void setSrcRightNormal(TView t, Drawable srcRightNormal) {
        t.setSrcRight(BitmapTool.drawableToBitmap(srcRightNormal));
    }

    @BindingAdapter({"srcRightPress"})
    public static void setSrcRightPress(TView t, Drawable srcRightPress) {
        t.setSrcRightPress(BitmapTool.drawableToBitmap(srcRightPress));
    }

    @BindingAdapter({"srcRightSelect"})
    public static void setSrcRightSelect(TView t, Drawable srcRightSelect) {
        t.setSrcRightSelect(BitmapTool.drawableToBitmap(srcRightSelect));
    }

    @BindingAdapter({"wrapTextArray"})
    public static void setWrapTextArray(TWrap tWrap, String[] wrapTextArray) {
        System.out.println("setWrapTextArray==>String[]==>");
        if (wrapTextArray != null) {
            System.out.println("length==>" + wrapTextArray.length);
        }
        tWrap.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        tWrap.setWrapTextArray(wrapTextArray);
    }

    @BindingAdapter({"wrapTextArray"})
    public static void setWrapTextArray(TWrap tWrap, List<String> wrapTextArray) {
        System.out.println("setWrapTextArray==>List==>");
        if (wrapTextArray != null) {
            System.out.println("size==>" + wrapTextArray.size());
        }
    }
}
