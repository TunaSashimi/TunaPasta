package com.tunaPasta07.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.tunaPasta07.R;
import com.tunaPasta07.widget.CustomGragGridView;

public class CustomGragGridViewTest extends Activity {

    private static List<String> list = null;
    //自定义适配器
    private DragGridAdapter adapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.customgraggridviewtest);
        initData();

        CustomGragGridView dragView = findViewById(R.id.drag_grid);

        adapter = new DragGridAdapter(this, list);

        dragView.setAdapter(adapter);
    }

    public void initData() {
        list = new ArrayList();
        for (int i = 0; i < 12; i++) {
            list.add("grid_" + i % 12);
        }
    }

    public class DragGridAdapter extends ArrayAdapter<String> {

        public DragGridAdapter(Context context, List<String> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.customgraggridviewtestitem, null);
            }
            try {
                //跟据文件名获取资源文件中的图片资源
                System.out.println("类名称:" + R.drawable.class);
                System.out.println("字段名:" + getItem(position));
                System.out.println(R.drawable.class.getDeclaredField(getItem(position)));

                Field field = R.drawable.class.getDeclaredField(getItem(position));
                int imageResource = field.getInt(R.drawable.class);
                ImageView imageview = view.findViewById(R.id.drag_grid_item_image);
                imageview.setImageResource(imageResource);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return view;
        }
    }
}