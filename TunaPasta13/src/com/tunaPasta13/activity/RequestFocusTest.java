package com.tunaPasta13.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.tunaPasta13.R;

public class RequestFocusTest extends Activity {

    private EditText editText01, editText02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.requestfocustest);

        editText01 = findViewById(R.id.editText01);
        editText02 = findViewById(R.id.editText02);

        editText01.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (EditorInfo.IME_ACTION_NEXT == actionId) {

                    editText02.setFocusable(true);
                    editText02.setFocusableInTouchMode(true);
                    editText02.requestFocus();
                    editText02.requestFocusFromTouch();
                }
                return true;
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText02.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText02, InputMethodManager.SHOW_FORCED);
            }
        });
    }
}
