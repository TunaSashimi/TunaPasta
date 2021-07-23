package com.tunaPasta05.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta05.R;

public class UpdateUITest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateuitest);

		// EditText UITxt = (EditText)findViewById(R.id.ui_txt);
		// Button updateUIBtn = (Button)findViewById(R.id.update_ui_btn);
		// updateUIBtn.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View v) {
		// UIHandler UIhandler = new UIHandler();
		// UIThread thread = new UIThread();
		// thread.start();
		// }
		// });
		// }

		// private class UIHandler extends Handler{
		// public void handleMessage(Message msg) {
		// super.handleMessage(msg);
		// Bundle bundle = msg.getData();
		// String color = bundle.getString("color");
		// UITxt.setText(color);
		// }
		// }

		// private class UIThread extends Thread{
		// public void run() {
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// Message msg = new Message();
		// Bundle bundle = new Bundle();
		// bundle.putString("color", "黄色");
		// msg.setData(bundle);
		// MainActivity.this.UIhandler.sendMessage(msg);
		// }

		// FusionField.currentActivity.runOnUiThread(new Runnable(){
		// public void run(){
		// Toast.makeText(getApplicationContext(), ,
		// "Update My UI",Toast.LENGTH_LONG).show();
		// }
		// });

	}
}
