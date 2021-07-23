package com.tunaPasta05.activity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.tunaPasta05.R;
import com.tunaPasta05.adapter.AutoDeleteAdapter;
public class AutoDeleteTest extends Activity {
	String[] images = { 
			"http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/A7D253A7F62F498A98387BBA4C515DB2",
			"http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/75CEFA29C2934DA6B1CCFFD61287AC50",
			"http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/659B18B278EC48F6813AE10C92D8A149",
			"http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/B1DB205228A04631870C4A73735C26AD",
			"http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/E717B2523E5248308939B4536CB3A46A",
			"http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/7FF4DD047AE2477FBFE17EF1D6E8F8DE",
			"http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/B4BF79165A9E4E938238DDC7CD927F8D",
			"http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/CB4863FA11AD4F2890D7948FFFA8C1DC",
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autodeletetest);

		ListView listview =  findViewById(R.id.listview);
		listview.setAdapter(new AutoDeleteAdapter(this, images));
	}
}