package com.tunaPasta13.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tunaPasta13.R;

/**
 * 
 */
public class FloatingWindowsTest extends Activity  implements OnClickListener{
	private WindowManager windowManager = null;
	private WindowManager.LayoutParams windowManagerParams = null;
	private FloatView floatView = null;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                      WindowManager.LayoutParams. FLAG_FULLSCREEN);//全屏
        setContentView(R.layout.floatingwindowstest);
       	createView(); 
    }

	public void onDestroy() {
		super.onDestroy();
		// 在程序退出(Activity销毁）时销毁悬浮窗口
		windowManager.removeView(floatView);
	}

	private void createView() {
		floatView = new FloatView(getApplicationContext());
		floatView.setOnClickListener(this);
//		floatView.setImageResource(R.drawable.ic_launcher); // 这里简单的用自带的icon来做演示
		// 获取WindowManager
		windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		// 设置LayoutParams(全局变量）相关参数
		windowManagerParams = ((FloatApplication) getApplication()).getWindowParams();
		// 显示myFloatView图像
		windowManager.addView(floatView, windowManagerParams);
	}

	public void onClick(View v) {
		Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
	}
}
