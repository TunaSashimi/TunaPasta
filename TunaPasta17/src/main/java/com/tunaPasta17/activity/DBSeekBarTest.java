package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta17.R;
import com.tunaPasta17.databinding.DbBindingSeekbarBinding;
import com.tunaPasta17.model.Progress;

import androidx.databinding.DataBindingUtil;

/**
 * @author TunaSashimi
 * @date 2020-04-11 23:46
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class DBSeekBarTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbBindingSeekbarBinding binding = DataBindingUtil.setContentView(this, R.layout.db_binding_seekbar);
        Progress progress = new Progress();
        binding.setProgress(progress);
        progress.porgress.set(21);
    }
}