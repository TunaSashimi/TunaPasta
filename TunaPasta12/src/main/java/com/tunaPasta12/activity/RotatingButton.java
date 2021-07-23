package com.tunaPasta12.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import com.tunaPasta12.R;

/**
 * This application demonstrates the ability to transform views in 2D and 3D, scaling them,
 * translating them, and rotating them (in 2D and 3D). Use the seek bars to set the various
 * transform properties of the button.
 */
public class RotatingButton extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotating_view);
        final Button rotatingButton = findViewById(R.id.rotatingButton);
        SeekBar seekBar;
        seekBar = findViewById(R.id.translationX);
        seekBar.setMax(400);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                rotatingButton.setTranslationX((float) progress);
            }
        });
        seekBar = findViewById(R.id.translationY);
        seekBar.setMax(800);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                rotatingButton.setTranslationY((float) progress);
            }
        });
        seekBar = findViewById(R.id.scaleX);
        seekBar.setMax(50);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rotatingButton.setScaleX((float) progress / 10f);
            }
        });
        seekBar = findViewById(R.id.scaleY);

        seekBar.setMax(50);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                rotatingButton.setScaleY((float) progress / 10f);
            }
        });
        seekBar = findViewById(R.id.rotationX);
        seekBar.setMax(360);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // prevent seeking on app creation
                rotatingButton.setRotationX((float) progress);
            }
        });
        seekBar = findViewById(R.id.rotationY);
        seekBar.setMax(360);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // prevent seeking on app creation
                rotatingButton.setRotationY((float) progress);
            }
        });
        seekBar =  findViewById(R.id.rotationZ);
        seekBar.setMax(360);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                rotatingButton.setRotation((float) progress);
            }
        });
    }
}