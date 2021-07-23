package com.tunaPasta02.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.tunaPasta02.R;
import com.tunaPasta02.adapter.TalkAdapter;
import com.tunaPasta02.entity.TalkEntity;

public class TalkUITest extends Activity {
	private ListView talkListView;
	private ArrayList<TalkEntity> talkEntityList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.talkuitest);

		talkListView =  findViewById(R.id.list);

		talkEntityList = new ArrayList();
		
		TalkEntity d1 = new TalkEntity("我", "2010-04-26", "你好!",R.layout.listsaymeitem01);
		TalkEntity d2 = new TalkEntity("范彬", "2010-04-26", "你好!",R.layout.listsaymeitem02);
		TalkEntity d3 = new TalkEntity("范彬", "2010-04-26", "哪里人氏?",R.layout.listsaymeitem02);
		TalkEntity d4 = new TalkEntity("我", "2010-04-26", "豫州镇县人豫州镇县人\n豫州镇县人豫州镇县人!",R.layout.listsaymeitem01);
		
		talkEntityList.add(d1);
		talkEntityList.add(d2);
		talkEntityList.add(d3);
		talkEntityList.add(d4);

		talkListView.setAdapter(new TalkAdapter(TalkUITest.this, talkEntityList));
	}
}