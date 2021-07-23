package com.tunaPasta19.activity;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.tunaPasta19.R;
import com.tunaPasta19.widget.DragGridView;
import com.tunaPasta19.widget.DragGridView.OnRearrangeListener;

public class DragGridViewTest extends Activity {

    private Random random = new Random();
    private String wordsString = "TunaSashimi";
    private String[] wordsArray;

    private DragGridView mDragGridView;
    private Button mAddBtn, mViewBtn;
    private ArrayList<String> poem = new ArrayList<String>();

//	通过继承ViewGroup来实现，我们都知道，ViewGroup可以填充很多个View，因此，
//	我觉得可以类似把GridView的每一个Item填充到我们自定义的ViewGroup中，然后监听长按时间，
//	实现拖动的效果，同时加上动画效果，个人感觉比网上其他实现方式更加简洁和美观，
//	唯一的缺点就是：没有setAdapter的函数，添加的item，需要我们手动add到ViewGroup中，
//	如果item不是特别复杂和繁多，个人觉得也不算什么问题。

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.draggridviewtest);

        wordsArray = new String[wordsString.length()];
        for (int i = 0; i < wordsString.length(); i++) {
            wordsArray[i] = wordsString.charAt(i) + "";
        }

        mDragGridView = (findViewById(R.id.vgv));

        mAddBtn = findViewById(R.id.add_item_btn);
        mViewBtn = findViewById(R.id.view_poem_item);

        mDragGridView.setOnRearrangeListener(new OnRearrangeListener() {
            public void onRearrange(int oldIndex, int newIndex) {

                String word = poem.remove(oldIndex);

                if (oldIndex < newIndex)
                    poem.add(newIndex, word);
                else
                    poem.add(newIndex, word);
            }
        });

        mDragGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mDragGridView.removeViewAt(arg2);
                poem.remove(arg2);
            }
        });

        mAddBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                String word = wordsArray[random.nextInt(wordsArray.length)];
                ImageView imageview = new ImageView(DragGridViewTest.this);
                imageview.setImageBitmap(getBlock(word));
                mDragGridView.addView(imageview);
                poem.add(word);
            }
        });

        mViewBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                String finishedPoem = "";

                for (String s : poem)
                    finishedPoem += s + " ";

                new AlertDialog.Builder(DragGridViewTest.this).setTitle("排列名称:")
                    .setMessage(finishedPoem).show();
            }
        });
    }

    private Bitmap getBlock(String string) {
        Bitmap bmp = Bitmap.createBitmap(150, 150, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        paint.setColor(Color.rgb(random.nextInt(128), random.nextInt(128), random.nextInt(128)));

        paint.setTextSize(24);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        canvas.drawRect(new Rect(0, 0, 150, 150), paint);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(string, 75, 75, paint);

        return bmp;
    }
}