package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tunaPasta19.R;

/**
 * @author Tunasashimi
 * @date 11/3/15 20:43
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class CustomFoodTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.customfoodtest);

        Button button01 = findViewById(R.id.button01);
        button01.setOnClickListener(v -> Toast.makeText(CustomFoodTest.this, "蛋饼未开发", Toast.LENGTH_SHORT).show());
        Button button02 = findViewById(R.id.button02);
        button02.setOnClickListener(v -> Toast.makeText(CustomFoodTest.this, "披萨未开发", Toast.LENGTH_SHORT).show());
        Button button03 = findViewById(R.id.button03);
        button03.setOnClickListener(v -> startActivity(new Intent(CustomFoodTest.this, CustomCakeChooseTest.class)));
    }
}
