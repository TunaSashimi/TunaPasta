package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.tunaPasta09.R;
import com.tunaPasta09.widget.AniCreator;

/**
 * 动画部分已经做好了，相关效果已经和UI 对过。客户端直接用就好了。 只需要把AniCreator 导入当前项目即可，附件是演示工程。 动画类型指定
 * <p>
 * public static final int ANIMATION_MODE_DROPDOWN = 100; //上方拉出
 * <p>
 * public static final int ANIMATION_MODE_POPUP = 101; //下方弹出
 * <p>
 * public static final int ANIMATION_MODE_DROPDOWN_RESEVERD = 104; //上方收起
 * <p>
 * public static final int ANIMATION_MODE_POPUP_RESERVED = 105; //下方收起
 * <p>
 * public static final int ANIMATION_MODE_ALPHA_VISABLE = 110; //透明淡入
 * <p>
 * public static final int ANIMATION_MODE_ALPHA_INVISABLE = 111; //透明淡出
 * <p>
 * public static final int ANIMATION_MODE_SILDEOUT_LEFT = 102; //左方收起
 * <p>
 * public static final int ANIMATION_MODE_SLIDIN_LEFT = 106; //左方拉出
 * <p>
 * public static final int ANIMATION_MODE_SLIDEOUT_RIGHT = 103; //右方收起
 * <p>
 * public static final int ANIMATION_MODE_SLIDEIN_RIGHT = 107; //右方拉出
 * <p>
 * public static final int ANIMATION_MODE_ROTATION_CLOCKWISED = 108; //顺时针选装
 * <p>
 * public static final int ANIMATION_MODE_ROTAION_ANTI_CLOCKWISED = 109; //逆时针旋转
 * <p>
 * 注意：控件未封装UI Thread 处理，请保证 其调用在UI Thread 中。 控件未封装延迟处理，请使用Handler.postDelay 方法。
 */

public class AnimatorFunctionTest extends Activity {
    private Button button;
    private ImageView imageview;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatorfunctiontest);

        button = findViewById(R.id.button);
        imageview = findViewById(R.id.imageview);

//		if (AnimationView_dropdown == null) {
//			AnimationView_dropdown = (View) this.findViewById(R.id.AnimationView_dropdown);
//		}

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AniCreator.getInstance().apply_animation_translate(imageview, AniCreator.ANIMATION_MODE_DROPDOWN_RESEVERD, View.GONE, true, null);

            }
        });

    }

}