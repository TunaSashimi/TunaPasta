package com.tunaPasta05.activity;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.tunaPasta05.R;

public class LoseWeightTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loseweighttest);
    }
    @Override
   	public void onConfigurationChanged(Configuration newConfig) {
   		super.onConfigurationChanged(newConfig);
   	}
    
}