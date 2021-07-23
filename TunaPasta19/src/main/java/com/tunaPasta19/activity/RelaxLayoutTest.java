package com.tunaPasta19.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta19.R;

public class RelaxLayoutTest extends Activity {
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relaxlayouttest);
        intent=new Intent(RelaxLayoutTest.this,RelaxLayoutTestCharacter.class);
        
//		OnClickListener btnOnClickListener=new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(RelaxLayoutTest.this, "按钮被点击", Toast.LENGTH_LONG).show();
//				}
//		};
        
	}
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
			 startActivity(new  Intent(RelaxLayoutTest.this,RelaxLayoutTestCalculate.class));
			break;
		case R.id.btn_support:
			Toast.makeText(RelaxLayoutTest.this, "按钮被点击", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_about:
			Toast.makeText(RelaxLayoutTest.this, "按钮被点击", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_moreapps:
			Toast.makeText(RelaxLayoutTest.this, "按钮被点击", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_exit:
			finish();
			break;
		default:
			break;
		}
	}
}