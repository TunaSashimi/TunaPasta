package com.tunaPasta14.pic;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;

import com.tunaPasta14.R;
import com.tunaPasta14.myinterface.ImageChangeListener;

/**
 * Gallery的适配器类，主要用于加载图片
 *
 * @author lyc
 */
public class GalleryAdapter extends BaseAdapter {

    private Context context;
    private ImageChangeListener changeListener = null;

    /*图片素材*/
    private int images[] = {R.drawable.a, R.drawable.c, R.drawable.e, R.drawable.f, R.drawable.mov001, R.drawable.mov002,
            R.drawable.mov003, R.drawable.mov004, R.drawable.mov005, R.drawable.mov006,
            R.drawable.mov007, R.drawable.mov008
    };

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //每次移动获取图片并重新加载，当图片很多时可以构造函数就把bitmap引入并放入list当中，
        //然后在getview方法当中取来直接用
        if (changeListener != null) {
            changeListener.onChange(position + 1);
        }
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), images[position]);
        MyImageView view = new MyImageView(context, bmp.getWidth(), bmp.getHeight());
        view.setLayoutParams(new Gallery.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        view.setImageBitmap(bmp);
        return view;
    }


    public void setOnPageChangListener(ImageChangeListener listener) {
        this.changeListener = listener;
    }

}
