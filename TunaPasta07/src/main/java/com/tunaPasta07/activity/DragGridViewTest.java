package com.tunaPasta07.activity;

import java.util.ArrayList;
import java.util.List;
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

import com.tunaPasta07.R;
import com.tunaPasta07.widget.DragGridView;
import com.tunaPasta07.widget.DragGridView.OnRearrangeListener;

public class DragGridViewTest extends Activity {

    static Random random = new Random();
    static String[] words = "金 枪 鱼 信 息 科 技 有 限 公 司".split(" ");

    DragGridView mDragGridView;
    Button mAddBtn, mViewBtn;
    List<String> poem = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.draggridviewtest);

        mDragGridView = findViewById(R.id.vgv);

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
                String word = words[random.nextInt(words.length)];
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
                new AlertDialog.Builder(DragGridViewTest.this).setTitle("这是你选择的")
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