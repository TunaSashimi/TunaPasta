package com.tunaPasta08.activity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta08.R;

public class P08_SizeChooseTest extends Activity {
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p08_sizechoosetest);
        intent=new Intent(P08_SizeChooseTest.this,P09_ChartsTest.class);
	}
	@SuppressLint("WrongConstant")
	public void onMainBtnClick(View target) {
		switch (target.getId()) {
		case R.id.btn_viewMenCharts:
			intent.setFlags(80);startActivity(intent);
			break;
		case R.id.btn_viewWomenCharts:
			intent.setFlags(81);startActivity(intent);
			break;
		case R.id.btn_viewKidsCharts:
			intent.setFlags(82);startActivity(intent);
			break;
		case R.id.btn_viewCalculator:
			 startActivity(new  Intent(P08_SizeChooseTest.this,P10_ClothesConverterTest.class));
			break;
		case R.id.btn_exit:
			finish();
			break;
		default:
			break;
		}
	}
}