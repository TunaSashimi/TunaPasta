package com.tunaPasta06.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.tunaPasta06.R;
import com.tunaPasta06.tool.SmileyParser;

public class SmileyParserTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smileyparsertest);

		TextView tvShow =  findViewById(R.id.tvShow);
		TextView tvSource =  findViewById(R.id.tvSource);
		
		//构建处理对象
		SmileyParser.init(this);		
		
		SmileyParser parser = SmileyParser.getInstance();
		String text = "[鄙视]"+"+"+ "[爱你]"+"+"+"[便便]"+"+"+"[馋嘴]";
		tvSource.setText("原来的输出字符:"+text);
		
		//调用 SmileyParser类的 addSmileySpans()方法处理 文本 
		tvShow.setText(parser.addSmileySpans("SmileyParser类处理后:"+text));
	}
}