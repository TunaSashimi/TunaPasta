package com.tunaPasta08.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.tunaPasta08.entity.Cloth;

public class FingerDrawView extends View {
    public ArrayList<Cloth> clothList;
    Cloth cloth;

    public FingerDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {                        //迭代绘制里面的每个元素,后加入的元素最后绘制~
        if (clothList != null && clothList.size() > 0) {
            ArrayList<Cloth> clothlists = new ArrayList(clothList);

            Collections.sort(clothlists, new Comparator<Cloth>() {
                @Override
                public int compare(Cloth cloth1, Cloth cloth2) {
                    return (int) (cloth1.timeMiles - cloth2.timeMiles);
                }
            });

            for (int i = 0; i < clothlists.size(); i++) {
                Cloth clo = clothlists.get(i);
                clo.drawable.setBounds(clo.x, clo.y, clo.x + clo.width, clo.y + clo.height);
                clo.drawable.draw(canvas);
            }
        }
    }
}