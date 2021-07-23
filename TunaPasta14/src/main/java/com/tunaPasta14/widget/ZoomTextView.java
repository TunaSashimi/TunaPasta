package com.tunaPasta14.widget;

import android.widget.TextView;

public class ZoomTextView extends ZoomView<TextView> {
    public static final float MIN_TEXT_SIZE = 10f;
    public static final float MAX_TEXT_SIZE = 100.0f;

    float scale;
    float textSize;

    public ZoomTextView(TextView view, float scale) {
        super(view);
        this.scale = scale;
        textSize = view.getTextSize();
    }

    protected void zoomOut() {
        textSize += scale;
        if (textSize > MAX_TEXT_SIZE) {
            textSize = MAX_TEXT_SIZE;
        }
        view.setTextSize(textSize);
    }

    protected void zoomIn() {
        textSize -= scale;
        if (textSize < MIN_TEXT_SIZE) {
            textSize = MIN_TEXT_SIZE;
        }
        view.setTextSize(textSize);
    }

}
