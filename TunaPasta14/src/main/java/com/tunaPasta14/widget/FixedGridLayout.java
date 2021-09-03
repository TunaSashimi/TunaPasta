package com.tunaPasta14.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tunaPasta14.R;

public class FixedGridLayout extends ViewGroup {
    int mCellWidth;
    int mCellHeight;

    public FixedGridLayout(Context context) {
        super(context);
    }

    public FixedGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Read the resource attributes.
        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.FixedGridLayout);
        mCellWidth = a.getDimensionPixelSize(
                R.styleable.FixedGridLayout_cellWidth, -1);
        mCellHeight = a.getDimensionPixelSize(
                R.styleable.FixedGridLayout_cellHeight, -1);
        a.recycle();

    }

    public void setCellWidth(int px) {
        mCellWidth = px;
        requestLayout();
    }

    public void setCellHeight(int px) {
        mCellHeight = px;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cellWidthSpec = MeasureSpec.makeMeasureSpec(mCellWidth,
                MeasureSpec.AT_MOST);
        int cellHeightSpec = MeasureSpec.makeMeasureSpec(mCellHeight,
                MeasureSpec.AT_MOST);

        int count = getChildCount();
        for (int index = 0; index < count; index++) {
            final View child = getChildAt(index);
            child.measure(cellWidthSpec, cellHeightSpec);
        }
        // Use the size our parents gave us
        setMeasuredDimension(resolveSize(mCellWidth * count, widthMeasureSpec),
                resolveSize(mCellHeight * count, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cellWidth = mCellWidth;
        int cellHeight = mCellHeight;
        int columns = (r - l) / cellWidth;
        if (columns < 0) {
            columns = 1;
        }
        int x = 0;
        int y = 0;
        int i = 0;
        int count = getChildCount();
        for (int index = 0; index < count; index++) {
            final View child = getChildAt(index);

            int w = child.getMeasuredWidth();
            int h = child.getMeasuredHeight();

            int left = x + ((cellWidth - w) / 2);
            int top = y + ((cellHeight - h) / 2);

            child.layout(left, top, left + w, top + h);
            if (i >= (columns - 1)) {
                // advance to next row
                i = 0;
                x = 0;
                y += cellHeight;
            } else {
                i++;
                x += cellWidth;
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int cnt = getChildCount();
        Paint p = new Paint();
        p.setColor(Color.CYAN);

        for (int i = 0; i < cnt; i++) {
            View v = getChildAt(i);
            setStateDrawable((ImageView) v, p);
        }
    }

    private void setStateDrawable(ImageView v, Paint p) {
        BitmapDrawable bd = (BitmapDrawable) v.getDrawable();
        Bitmap b = bd.getBitmap();
        Bitmap bitmap = Bitmap.createBitmap(bd.getIntrinsicWidth(), bd.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(b.extractAlpha(), 0, 0, p);

        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, new BitmapDrawable(bitmap));

        v.setBackgroundDrawable(sld);
    }
}

