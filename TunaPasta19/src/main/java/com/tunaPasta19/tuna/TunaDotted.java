package com.tunaPasta19.tuna;

import android.content.Context;
import android.util.AttributeSet;
/**
 * @author Tunasashimi
 * @date 10/30/15 16:52
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaDotted extends TunaView {

    public TunaDotted(Context context) {
        this(context, null);
    }

    public TunaDotted(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaDotted(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaDotted.class.getSimpleName();
    }

}
