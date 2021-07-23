package com.tunaPasta19.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tunaPasta19.R;

public class RelaxLayoutTestCalculate extends Activity {
	ImageView avator,table;
	Button  previous,icon1,icon2,icon3,icon4;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relaxlayouttestcalculate);
		
//		avator=(ImageView) findViewById(R.id.img_category);
//		avator.setBackgroundResource(R.drawable.ic_woman);
//		table=(ImageView) findViewById(R.id.img_tableHolder);
//		table.setBackgroundResource(R.drawable.table_womens_shirt_blouse);
//		
//		icon1=(Button) findViewById(R.id.btn_icon1);
//		icon1.setBackgroundResource(R.drawable.ic_woman_shirt);
//		icon2=(Button) findViewById(R.id.btn_icon2);
//		icon2.setBackgroundResource(R.drawable.ic_woman_blouse);
//		icon3=(Button) findViewById(R.id.btn_icon3);
//		icon3.setBackgroundResource(R.drawable.ic_woman_dress);
//		icon4=(Button) findViewById(R.id.btn_icon4);
//		icon4.setBackgroundResource(R.drawable.ic_woman_longsleeves);
	}
	
	public void onViewClick(View target) {
		switch (target.getId()) {
//		case R.id.btn_viewMenCharts: startActivity(new  Intent(P08_SizeChooseAct.this,P17_Charts.class));break;
//		case R.id.btn_viewWomenCharts:startActivity(new  Intent(P08_SizeChooseAct.this,P17_Charts.class));break;
//		case R.id.btn_viewKidsCharts:startActivity(new  Intent(P08_SizeChooseAct.this,P17_Charts.class));break;
//		case R.id.btn_viewCalculator: startActivity(new  Intent(P08_SizeChooseAct.this,P16_ClothesConverter.class));break;
//		case R.id.btn_exit: finish();break;
		default:break;
		}
	}
}