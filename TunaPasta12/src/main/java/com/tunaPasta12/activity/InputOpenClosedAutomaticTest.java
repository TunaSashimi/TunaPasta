package com.tunaPasta12.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.tunaPasta12.R;
import com.tunaPasta12.activity.ResizeRelativeLayout.OnResizeRelativeListener;

public class InputOpenClosedAutomaticTest extends Activity {

    private ResizeRelativeLayout resizerelateivelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inputopenclosedautomatictest);

        resizerelateivelayout = findViewById(R.id.resizerelateivelayout);
        resizerelateivelayout.setOnResizeRelativeListener(new OnResizeRelativeListener() {
            @Override
            public void OnResizeRelative(int w, int h, int oldw, int oldh) {
                System.out.println("w====>" + w);
                System.out.println("h====>" + h);
                System.out.println("oldw====>" + oldw);
                System.out.println("oldh====>" + oldh);
            }
        });

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        System.out.println("isOpen====>" + isOpen);
    }
}
