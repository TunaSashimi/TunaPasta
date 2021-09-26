package com.tunaPasta19.activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.tunaPasta19.R;
import com.tunaPasta19.tool.MoveBg;

public class MultiTagLayoutTest extends TabActivity {
	TabHost tabHost;
	TabHost.TabSpec tabSpec;
	RadioGroup radioGroup;
	RelativeLayout bottom_layout;
	ImageView img;
	int startLeft;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.multitaglayouttest);

		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("news").setIndicator("News").setContent(new Intent(this, MultiTagLayoutTestNews.class)));
		tabHost.addTab(tabHost.newTabSpec("topic").setIndicator("Topic").setContent(new Intent(this, MultiTagLayoutTestTopic.class)));
		tabHost.addTab(tabHost.newTabSpec("picture").setIndicator("Picture").setContent(new Intent(this, MultiTagLayoutTestPic.class)));
		tabHost.addTab(tabHost.newTabSpec("follow").setIndicator("Follow").setContent(new Intent(this, MultiTagLayoutTestFollow.class)));
		tabHost.addTab(tabHost.newTabSpec("vote").setIndicator("Vote").setContent(new Intent(this, MultiTagLayoutTestVote.class)));

		radioGroup =  findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(checkedChangeListener);

		img = new ImageView(this);
		img.setImageResource(R.drawable.movebackgroundlayouttest_tab_front);//最好在8存pad上面演示改程序,下面的框部分大小固定
		
		bottom_layout =  findViewById(R.id.layout_bottom);
		bottom_layout.addView(img);
	}

	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_news:
				tabHost.setCurrentTabByTag("news");
				MoveBg.moveFrontBg(img, startLeft, 0, 0, 0);
				startLeft = 0;
				break;
			case R.id.radio_topic:
				tabHost.setCurrentTabByTag("topic");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth(), 0, 0);
				startLeft = img.getWidth();
				break;
			case R.id.radio_pic:
				tabHost.setCurrentTabByTag("picture");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 2, 0, 0);
				startLeft = img.getWidth() * 2;
				break;
			case R.id.radio_follow:
				tabHost.setCurrentTabByTag("follow");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 3, 0, 0);
				startLeft = img.getWidth() * 3;
				break;
			case R.id.radio_vote:
				tabHost.setCurrentTabByTag("vote");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 4, 0, 0);
				startLeft = img.getWidth() * 4;
				break;
			}
		}
	};
}