package com.tunaPasta10.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

public class StaticLayoutTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new StaticLayoutView(this));
    }

}

class StaticLayoutView extends View {
    public StaticLayoutView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint textPaint = new TextPaint();
        textPaint.setARGB(0xFF, 0xFF, 0, 0);
        textPaint.setTextSize(80);
        String aboutTheGame = "到一定长度后能自动换行的文字 ";
        /**
         *
         * aboutTheGame ：要 绘制 的 字符串 ,textPaint(TextPaint 类型)设置了字符串格式及属性
         * 的画笔,240为设置 画多宽后 换行，后面的参数是对齐方式...
         */
        StaticLayout layout = new StaticLayout(aboutTheGame, textPaint, 600,
                Alignment.ALIGN_CENTER, 1.0F, 0.0F, true);
        // 从 (20,80)的位置开始绘制
        canvas.translate(20, 80);
        layout.draw(canvas);
    }
}