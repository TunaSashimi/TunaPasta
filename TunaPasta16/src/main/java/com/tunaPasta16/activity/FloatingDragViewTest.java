package com.tunaPasta16.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.tunaPasta16.R;
import com.tunaPasta16.view.FloatingDragView;

/**
 * 
 */
public class FloatingDragViewTest extends AppCompatActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.floatingdragviewtest);

		FloatingDragView mBtn = findViewById(R.id.img_btn);
		mBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(FloatingDragViewTest.this,"点击了可拖拽按钮的点击事件",Toast.LENGTH_SHORT).show();
			}
		});
    }
}
