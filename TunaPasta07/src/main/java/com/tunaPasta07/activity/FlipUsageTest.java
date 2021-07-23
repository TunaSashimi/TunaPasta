package com.tunaPasta07.activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tunaPasta07.R;

public class FlipUsageTest extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(
			new SimpleAdapter(
				this, getData(), android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1}
			)
		);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(
			Intent.ACTION_VIEW,
			Uri.parse("http://openaphid.github.com/")
		);
		startActivity(intent);

		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
		@SuppressWarnings("unchecked")
		Intent intent = new Intent(this, (Class<? extends Activity>)map.get("activity"));
		startActivity(intent);
	}

	private List<? extends Map<String, ?>> getData() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		addItem(data, "FlipTextViewTest", FlipTextViewTest.class);
		addItem(data, "FlipButtonTest", FlipButtonTest.class);
		addItem(data, "FlipLayoutTest", FlipLayoutTest.class);
		addItem(data, "FlipEffect", FlipEffect.class);
		addItem(data, "FlipTouch", FlipTouch.class);
		return data;
	}

	private void addItem(List<Map<String, Object>> data, String title, Class<? extends Activity> activityClass) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("activity", activityClass);
		data.add(map);
	}
}
