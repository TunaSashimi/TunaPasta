package com.tunaPasta05.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

import com.tunaPasta05.R;

public class GalleryChoiceTest extends Activity {
    private Gallery gallery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallerychoicetest);

        gallery = findViewById(R.id.gallery);
        // 设置图片适配器
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setSelection(Integer.MAX_VALUE / 2);//设置初始项
        gallery.setSpacing(20);    //设置边距
    }
}

class ImageAdapter extends BaseAdapter {
    //	private LinearLayout.LayoutParams paramswrap = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    // 声明Context
    private Context context;
    // 图片源数组
//	private Integer[] imageInteger = { R.drawable.img_l2_1, R.drawable.img_l2_2, R.drawable.img_l2_3, R.drawable.img_l2_4};
    private String[] stringArray = {"鞋子", "熊抱", "内衣", "短裤"};

    // 声明 ImageAdapter
    public ImageAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textview = new TextView(context);
        textview.setText(stringArray[position % stringArray.length].toString());
        return textview;

//		ImageView imageView = new ImageView(context);
//imageView.setLayoutParams(paramswrap);
//		// 给ImageView设置资源
//		imageView.setImageResource(imageInteger[position%imageInteger.length]);
//		// 设置比例类型
////		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//		// // 设置布局 图片128x192显示
////		imageView.setLayoutParams(new Gallery.LayoutParams(136, 88));
//		return imageView;
    }
}