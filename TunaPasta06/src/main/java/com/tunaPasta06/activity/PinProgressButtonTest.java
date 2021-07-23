package com.tunaPasta06.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.tunaPasta06.R;
import com.tunaPasta06.widget.PinProgressButton;

public class PinProgressButtonTest extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinprogressbuttontest);

        final PinProgressButton pinProgress1 = findViewById(
            R.id.pin_progress_1);
        final PinProgressButton pinProgress2 = findViewById(
            R.id.pin_progress_2);
        final PinProgressButton pinProgress3 = findViewById(
            R.id.pin_progress_3);
        final PinProgressButton pinProgress4 = findViewById(
            R.id.pin_progress_4);

        CompoundButton.OnCheckedChangeListener checkedChangeListener
            = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                updatePinProgressContentDescription((PinProgressButton) compoundButton);
            }
        };

        pinProgress1.setOnCheckedChangeListener(checkedChangeListener);
        pinProgress2.setOnCheckedChangeListener(checkedChangeListener);
        pinProgress3.setOnCheckedChangeListener(checkedChangeListener);
        pinProgress4.setOnCheckedChangeListener(checkedChangeListener);

        SeekBar progressSeekBar = findViewById(R.id.progress_seek_bar);
        progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pinProgress1.setProgress(progress);
                pinProgress2.setProgress(progress);
                pinProgress3.setProgress(progress);
                pinProgress4.setProgress(progress);

                updatePinProgressContentDescription(pinProgress1);
                updatePinProgressContentDescription(pinProgress2);
                updatePinProgressContentDescription(pinProgress3);
                updatePinProgressContentDescription(pinProgress4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        pinProgress1.setProgress(progressSeekBar.getProgress());
        pinProgress2.setProgress(progressSeekBar.getProgress());
        pinProgress3.setProgress(progressSeekBar.getProgress());
        pinProgress4.setProgress(progressSeekBar.getProgress());

        updatePinProgressContentDescription(pinProgress1);
        updatePinProgressContentDescription(pinProgress2);
        updatePinProgressContentDescription(pinProgress3);
        updatePinProgressContentDescription(pinProgress4);
    }

    private void updatePinProgressContentDescription(PinProgressButton button) {
        int progress = button.getProgress();
        if (progress <= 0) {
            button.setContentDescription(getString(button.isChecked()
                ? R.string.content_desc_pinned_not_downloaded
                : R.string.content_desc_unpinned_not_downloaded));
        } else if (progress >= 100) {
            button.setContentDescription(getString(button.isChecked()
                ? R.string.content_desc_pinned_downloaded
                : R.string.content_desc_unpinned_downloaded));
        } else {
            button.setContentDescription(getString(button.isChecked()
                ? R.string.content_desc_pinned_downloading
                : R.string.content_desc_unpinned_downloading));
        }
    }
}
