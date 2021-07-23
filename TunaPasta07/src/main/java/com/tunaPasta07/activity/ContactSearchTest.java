package com.tunaPasta07.activity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tunaPasta07.R;
import com.tunaPasta07.widget.QuickAlphabeticBar;
/**
 * 联系人分章节显示、ListView快速滑动显示联系人首字母、附带字母表快速查找的例子
 */
public class ContactSearchTest extends Activity {
	private BaseAdapter adapter;
	private ListView personList;
	private List<ContentValues> list;
	private AsyncQueryHandler asyncQuery;
	private QuickAlphabeticBar quickAlphabeticBar;
	private static final String NAME = "name", NUMBER = "number",SORT_KEY = "sort_key";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.contentsearchtest);
		
		personList =  findViewById(R.id.listView);
		
		quickAlphabeticBar =  findViewById(R.id.fast_scroller);

		asyncQuery = new ContactAsyncQueryHandler(getContentResolver());
		
		personList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				
//				ContentValues cv = list.get(position);
//				String name = cv.getAsString(NAME);
//				String number = cv.getAsString(NUMBER);
//				System.out.println("dianji");
//				System.out.println(name);
//				System.out.println(number);
				
			}
		});
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		Uri uri = Uri.parse("content://com.android.contacts/data/phones"); // 联系人的Uri
		String[] projection = { "_id", "display_name", "data1", "sort_key" }; // 查询的列
		asyncQuery.startQuery(0, null, uri, projection, null, null,"sort_key COLLATE LOCALIZED asc"); // 按照sort_key升序查询
	}

	private void setAdapter(List<ContentValues> list) {
		adapter = new ListAdapter(this, list);
		personList.setAdapter(adapter);
		quickAlphabeticBar.init(ContactSearchTest.this);
		quickAlphabeticBar.setListView(personList);
		quickAlphabeticBar.setHight(quickAlphabeticBar.getHeight());
		quickAlphabeticBar.setVisibility(View.VISIBLE);
	}

	/*
	 * 移植时只需要提供一个已排序的集合list即可
	 */
	private class ListAdapter extends BaseAdapter{
		private LayoutInflater inflater;
		private List<ContentValues> list;
		private HashMap<String, Integer> alphaIndexer;//保存每个索引在list中的位置【#-0，A-4，B-10】
		private String[] sections;//每个分组的索引表【A,B,C,F...】

		public ListAdapter(Context context, List<ContentValues> list) {
			this.inflater = LayoutInflater.from(context);
			this.list = list; // 该list是已经排序过的集合，有些项目中的数据必须要自己进行排序。
			this.alphaIndexer = new HashMap();
			this.sections = new String[list.size()];

			for (int i =0; i <list.size(); i++) {
				String name = getAlphabetTittle(list.get(i).getAsString(SORT_KEY));
				if(!alphaIndexer.containsKey(name)){//只记录在list中首次出现的位置
					alphaIndexer.put(name, i);
				}
			}
			
			Set<String> sectionLetters = alphaIndexer.keySet();
			ArrayList<String> sectionList = new ArrayList(sectionLetters);
			Collections.sort(sectionList);
			sections = new String[sectionList.size()];
			sectionList.toArray(sections);
			
			quickAlphabeticBar.setAlphaIndexer(alphaIndexer);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.contentsearchtestitem, null);
				holder = new ViewHolder();
				holder.alpha =  convertView.findViewById(R.id.alpha);
				holder.name =  convertView.findViewById(R.id.name);
				holder.number =  convertView.findViewById(R.id.number);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			ContentValues cv = list.get(position);
			String name = cv.getAsString(NAME);
			String number = cv.getAsString(NUMBER);
			holder.name.setText(name);
			holder.number.setText(number);

			// 当前联系人的sortKey
			String currentStr = getAlphabetTittle(list.get(position).getAsString(SORT_KEY));
			// 上一个联系人的sortKey
			String previewStr = (position - 1) >= 0 ? getAlphabetTittle(list.get(position - 1).getAsString(SORT_KEY)) : " ";
			/**
			 * 判断显示#、A-Z的TextView隐藏与可见
			 */
			if (!previewStr.equals(currentStr)) { // 当前联系人的sortKey!=上一个联系人的sortKey，说明当前联系人是新组。
				holder.alpha.setVisibility(View.VISIBLE);
				holder.alpha.setText(currentStr);
			} else {
				holder.alpha.setVisibility(View.GONE);
			}
			return convertView;
		}
	}

	private static class ViewHolder {
		TextView alpha;
		TextView name;
		TextView number;
	}
	
	/**
	 * 提取英文的首字母，非英文字母用#代替。
	 * @param str
	 * @return
	 */
	private String getAlphabetTittle(String str) {
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// 正则表达式，判断首字母是否是英文字母
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase(); // 大写输出
		} else {
			return "#";
		}
	}
	
	/**
	 * 数据库异步查询类AsyncQueryHandler
	 */
	private class ContactAsyncQueryHandler extends AsyncQueryHandler {
		public ContactAsyncQueryHandler(ContentResolver contentResolver) {
			super(contentResolver);
		}
		/**
		 * 查询结束的回调函数
		 */
		@Override
		protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
			if (cursor != null && cursor.getCount() > 0) {
				list = new ArrayList<ContentValues>();
				cursor.moveToFirst();
				for (int i = 0; i < cursor.getCount(); i++) {
					ContentValues cv = new ContentValues();
					cursor.moveToPosition(i);
					String name = cursor.getString(1);
					String number = cursor.getString(2);
					String sortKey = cursor.getString(3);

					if (number.startsWith("+86")) {// 去除多余的中国地区号码标志，对这个程序没有影响。
						cv.put(NAME, name);
						cv.put(NUMBER, number.substring(3));
						cv.put(SORT_KEY, sortKey);
					} else {
						cv.put(NAME, name);
						cv.put(NUMBER, number);
						cv.put(SORT_KEY, sortKey);
					}
					list.add(cv);
				}
				if (list.size() > 0) {
					setAdapter(list);
				}
			}
		}
	}
}