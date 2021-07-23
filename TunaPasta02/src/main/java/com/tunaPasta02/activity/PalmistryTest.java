package com.tunaPasta02.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.tunaPasta02.R;

public class PalmistryTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    		setContentView(R.layout.palmistrytest);
    }
    @Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
}