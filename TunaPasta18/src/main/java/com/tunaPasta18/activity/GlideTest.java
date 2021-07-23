package com.tunaPasta18.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tunaPasta18.R;

import java.lang.ref.WeakReference;

/**
 * @author Tunasashimi
 * @date 11/3/15 20:43
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class GlideTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.glidetest);

        ImageView iv_act_gif01 = findViewById(R.id.iv_act_gif01);
        ImageView iv_act_gif02 = findViewById(R.id.iv_act_gif02);
        ImageView iv_act_gif03 = findViewById(R.id.iv_act_gif03);
        ImageView iv_act_gif04 = findViewById(R.id.iv_act_gif04);

        Glide.with(this).load(R.drawable.gif_blink).into(iv_act_gif01);

        Glide.with(this).load(R.drawable.gif_open_mouse).into(iv_act_gif02);

        Glide.with(this).load(R.drawable.gif_shake_head).into(iv_act_gif03);

//        Glide.with(this)
//            .load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2303073818,2669135227&fm=27&gp=0.jpg")
//            .into(iv_act_gif04);

        final WeakReference<ImageView> imageViewWeakReference = new WeakReference<>(iv_act_gif04);
        ImageView target = imageViewWeakReference.get();
        if (target != null) {
            Glide.with(GlideTest.this)
                .load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2303073818,2669135227&fm=27&gp=0.jpg")
                .into(target);
        }
    }

    /**
     * 清除内存缓存.
     */
    public static void clearMemoryCache(Context context) {
        // This method must be called on the main thread.
        System.gc();
        // Glide.get(context).clearMemory();
        Glide.get(context).clearMemory();
    }
}
