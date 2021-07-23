package com.tunaPasta08.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.tunaPasta08.R;

public class P09_ChartsTest extends Activity {
	Button  previous,icon1,icon2,icon3,icon4;
	ImageView avator,table;	//考虑下table是否需要使用~
	Spinner spin01,spin02;
	int type;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.p09_chartstest);
		
		avator= findViewById(R.id.img_category);
		icon1= findViewById(R.id.btn_icon1);
		icon2= findViewById(R.id.btn_icon2);
		icon3= findViewById(R.id.btn_icon3);
		icon4= findViewById(R.id.btn_icon4);
		
		type=this.getIntent().getFlags();
		
		 initPicture();
//		//两个标签页
//		spin01=(Spinner) findViewById(R.id.inp_sizeInput);
//		spin02=(Spinner) findViewById(R.id.inp_sizeInput);
	}
	public void onClotheTypeClick(View target) {
		 initPicture();
		switch (target.getId()) {
		case R.id.btn_exit_charts:finish();break;
		case R.id.btn_icon1:
			switch (type) {
			case 80:icon1.setBackgroundResource(R.drawable.ic_man_shirt2);break;
			case 81:icon1.setBackgroundResource(R.drawable.ic_woman_shirt2);break;
			case 82:icon1.setBackgroundResource(R.drawable.ic_kids_shirt2);break;
			};break;
		case R.id.btn_icon2:	
			switch (type) {
			case 80:icon2.setBackgroundResource(R.drawable.ic_man_pants2);break;
			case 81:icon2.setBackgroundResource(R.drawable.ic_woman_blouse2);break;
			case 82:icon2.setBackgroundResource(R.drawable.ic_kids_dress2);break;
			};break;
		case R.id.btn_icon3: 	
			switch (type) {
			case 80:icon3.setBackgroundResource(R.drawable.ic_man_trenchcoat2);break;
			case 81:icon3.setBackgroundResource(R.drawable.ic_woman_dress2);break;
			case 82:icon3.setBackgroundResource(R.drawable.ic_kids_pants2);break;
			};break;
		case R.id.btn_icon4: 	
			switch (type) {
			case 80:icon4.setBackgroundResource(R.drawable.ic_man_jacket2);break;
			case 81:icon4.setBackgroundResource(R.drawable.ic_woman_longsleeves2);break;
			case 82:icon4.setBackgroundResource(R.drawable.ic_kids_shoes2);break;
			};break;
		}
	}
	private void onPlacedLayout(int ava,int ic1,int ic2,int ic3,int ic4){
		avator.setBackgroundResource(ava);
		icon1.setBackgroundResource(ic1);
		icon2.setBackgroundResource(ic2);
		icon3.setBackgroundResource(ic3);
		icon4.setBackgroundResource(ic4);
	}
	private void initPicture(){
		switch (type) {
		case 80:onPlacedLayout(R.drawable.ic_man,R.drawable.ic_man_shirt,R.drawable.ic_man_pants,R.drawable.ic_man_trenchcoat,R.drawable.ic_man_jacket);break;
		case 81:onPlacedLayout(R.drawable.ic_woman,R.drawable.ic_woman_shirt,R.drawable.ic_woman_blouse,R.drawable.ic_woman_dress,R.drawable.ic_woman_longsleeves);break;
		case 82:onPlacedLayout(R.drawable.ic_kids,R.drawable.ic_kids_shirt,R.drawable.ic_kids_dress,R.drawable.ic_kids_pants,R.drawable.ic_kids_shoes);break;
		default:break;
		}
	}
}