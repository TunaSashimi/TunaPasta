package com.tunaPasta01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta01.R;
import com.tunaPasta01.view.WrapRadioGroup;

public class RadioGroupTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiogrouptest);

        RadioGroup mixedgroup = findViewById(R.id.mixedgroup);

        final RadioButton buttonFemale = findViewById(R.id.buttonFemale);
        final RadioButton buttonMale = findViewById(R.id.buttonMale);

        CheckBox checkSwim = findViewById(R.id.checkSwim);
        CheckBox checkRun = findViewById(R.id.checkRun);
        CheckBox checkRead = findViewById(R.id.checkRead);

        mixedgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (buttonFemale.getId() == checkedId) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                } else if (buttonMale.getId() == checkedId) {
                    Toast.makeText(getApplicationContext(), "male", Toast.LENGTH_SHORT).show();
                }
            }
        });


        checkSwim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "swim is checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "swim is unchecked", Toast.LENGTH_SHORT).show();
                }

            }
        });

        checkRun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "run is checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "run is unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "read is checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "read is unchecked", Toast.LENGTH_SHORT).show();
                }

            }
        });

        final RadioButton unionRadioButton01 =  findViewById(R.id.unionRadioButton01);
        final RadioButton unionRadioButton02 =  findViewById(R.id.unionRadioButton02);
        final RadioButton unionRadioButton03 =  findViewById(R.id.unionRadioButton03);
        final RadioButton unionRadioButton04 =  findViewById(R.id.unionRadioButton04);

        final TextView textCircle =  findViewById(R.id.textCircle);

        WrapRadioGroup wrapRadioGroup =  findViewById(R.id.wrapRadioGroup);
        wrapRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (unionRadioButton01.getId() == checkedId) {
                    textCircle.setBackgroundResource(R.drawable.radiogroup_background_oval_orange);
                } else if (unionRadioButton02.getId() == checkedId) {
                    textCircle.setBackgroundResource(R.drawable.radiogroup_background_oval_green);
                } else if (unionRadioButton03.getId() == checkedId) {
                    textCircle.setBackgroundResource(R.drawable.radiogroup_background_oval_blue);
                } else if (unionRadioButton04.getId() == checkedId) {
                    textCircle.setBackgroundResource(R.drawable.radiogroup_background_oval_purple);
                }
            }
        });
    }
}