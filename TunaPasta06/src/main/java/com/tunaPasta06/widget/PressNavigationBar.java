package com.tunaPasta06.widget;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PressNavigationBar extends CanGetSizeLinearLayout {
    private List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

    private int selectedChildViewPosition = 0;
    private PressNavigationBarListener pressNavigationBarListener;

    public int getSelectedChildViewPosition() {
        return this.selectedChildViewPosition;
    }

    public void setSelectedChildViewPosition(int selectedChildViewPosition) {
        this.selectedChildViewPosition = selectedChildViewPosition;
    }

    public PressNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case 0:
                        PressNavigationBar.this.selectedChildViewPosition = PressNavigationBar.this
                                .getTouchPosition(event);
                        PressNavigationBar.this.refreshView();
                        break;
                    case 2:
                        break;
                    case 1:
                }
                if (PressNavigationBar.this.pressNavigationBarListener != null)
                    PressNavigationBar.this.pressNavigationBarListener
                            .onNavigationBarClick(
                                    PressNavigationBar.this.selectedChildViewPosition,
                                    PressNavigationBar.this, event);
                return true;
            }
        });
    }

    public void addChild(List<Map<String, Object>> list) {

        this.list = list;

        for (int i = 0; i < list.size(); i++) {

            FrameLayout frameLayout = new FrameLayout(getContext());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    -1, -1);

            params.weight = 1.0F;

            frameLayout.setLayoutParams(params);

            addView(frameLayout);

            ImageView imageView = new ImageView(getContext());
            FrameLayout.LayoutParams imageViewParams = new FrameLayout.LayoutParams(
                    -1, -1);
            imageView.setLayoutParams(imageViewParams);
            frameLayout.addView(imageView);

            TextView textView = new TextView(getContext());
            FrameLayout.LayoutParams textViewParams = new FrameLayout.LayoutParams(
                    -1, -1);
            textView.setGravity(17);
            textView.setLayoutParams(textViewParams);
            frameLayout.addView(textView);

            Map<?, ?> map = list.get(i);

            String text = (String) map.get("text");

            int textSize = ((Integer) map.get("textSize")).intValue();

            int textColor = ((Integer) map.get("textColor")).intValue();
            int textColorSelected = ((Integer) map.get("textColorSelected"))
                    .intValue();

            int image = ((Integer) map.get("image")).intValue();
            int imageSelected = ((Integer) map.get("imageSelected")).intValue();

            textView.setText(text);
            textView.setTextSize(textSize);

            if (this.selectedChildViewPosition == i) {

                textView.setTextColor(textColorSelected);
                imageView.setBackgroundResource(imageSelected);

            } else {

                textView.setTextColor(textColor);
                imageView.setBackgroundResource(image);

            }
        }
    }

    public void refreshView() {
        removeAllViews();
        addChild(this.list);
    }

    private ViewSizeAndPosition getSelectedChildViewSizeAndPosition() {
        ViewSizeAndPosition viewSizeAndPosition = getViewSizeAndPosition();
        int selectdChildViewWidth = viewSizeAndPosition.getWidth()
                / this.list.size();
        int selectedChildViewHeight = viewSizeAndPosition.getHeight();

        ViewSizeAndPosition selectedChildViewSizeAndPosition = new ViewSizeAndPosition();
        selectedChildViewSizeAndPosition.setWidth(selectdChildViewWidth);
        selectedChildViewSizeAndPosition.setHeight(selectedChildViewHeight);
        selectedChildViewSizeAndPosition.setLeft(selectdChildViewWidth
                * getSelectedChildViewPosition());
        selectedChildViewSizeAndPosition.setTop(0);
        selectedChildViewSizeAndPosition.setRight(selectdChildViewWidth
                * getSelectedChildViewPosition() + selectdChildViewWidth);
        selectedChildViewSizeAndPosition.setBottom(selectedChildViewHeight);

        return selectedChildViewSizeAndPosition;
    }

    private int getTouchPosition(MotionEvent event) {
        ViewSizeAndPosition selectedChildViewSizeAndPosition = getSelectedChildViewSizeAndPosition();
        return T.getInt(
                event.getX() / selectedChildViewSizeAndPosition.getWidth(),
                T.ABANDON);
    }

    static class T {
        public static int ABANDON = 0;

        public static int CARRY = 1;

        public static int getInt(double d, int require) {
            int i = 0;
            if (ABANDON == require) {
                i = (int) d;
            } else if (CARRY == require) {
                int ii = (int) d;
                if (ii != 0) {
                    i = ii + (d % ii > 0.0D ? 1 : 0);
                } else if (d == 0.0D)
                    i = 0;
                else {
                    i = 1;
                }
            }
            return i;
        }
    }

    public void setPressNavigationBarListener(
            PressNavigationBarListener pressNavigationBarListener) {
        this.pressNavigationBarListener = pressNavigationBarListener;
    }

    public interface PressNavigationBarListener {
        void onNavigationBarClick(int paramInt, View paramView,
                                  MotionEvent paramMotionEvent);
    }
}