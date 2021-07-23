package com.tunaPasta19.tuna;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.tunaPasta19.R;


/**
 * o
 *
 * @author Tunasashimi
 * @date 8/22/16 21:10
 * @Copyright 2016 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaTrapezoid extends TunaView {
    public TunaTrapezoid(Context context) {
        this(context, null);
    }

    public TunaTrapezoid(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaTrapezoid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaTrapezoid.class.getSimpleName();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaTrapezoid);

        typedArray.recycle();
    }
}
