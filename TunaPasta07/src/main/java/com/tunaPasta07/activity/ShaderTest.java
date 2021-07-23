package com.tunaPasta07.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.tunaPasta07.widget.ShaderView;

public class ShaderTest extends Activity {
    private ShaderView mGameView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGameView = new ShaderView(this);

        setContentView(mGameView);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        return true;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mGameView == null) {
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return mGameView.onKeyDown(keyCode, event);
    }
}