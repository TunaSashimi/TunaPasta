package com.tunaPasta06.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.tunaPasta06.R;

public class ImageAdapter extends BaseAdapter {
    // 定义整型数组 即图片源
    private Integer[] mThumbIds = {
            R.drawable.shao1, R.drawable.shao2, R.drawable.shao3, R.drawable.shao4,
            R.drawable.shao5, R.drawable.shao6, R.drawable.shao7, R.drawable.shao8
    };
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    // 获取图片在库中的位置
    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new Gallery.LayoutParams(600, 600));
            imageView.setAdjustViewBounds(false);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(18, 18, 18, 18);
        } else {
            imageView = (ImageView) convertView;
        }
        // 给ImageView设置资源
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}