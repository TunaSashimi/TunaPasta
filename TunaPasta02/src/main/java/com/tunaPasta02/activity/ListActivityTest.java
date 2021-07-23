package com.tunaPasta02.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta02.R;

//注意这里Helloworld类不是扩展自Acitvity，而是扩展自ListAcitivty   
public class ListActivityTest extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });

        //
//		TextView View=new TextView(getApplicationContext());
//		View.setText("HeaderView");
//		listView.addHeaderView(View);

        // 如果把要填充到ListView中的元素硬编码到文件会缺乏灵活性！也不符合界面与代码更好地分离的准则！
        // 其实我们可以把要填充到ListView的元素写到res/values/文件中的<string-array>元素中，
        // 然后再源码中动态地读取。这样strings.xml的内容类似下面：
        String[] COUNTRIES = getResources().getStringArray(R.array.countries_array);
        setListAdapter(new ArrayAdapter(this, R.layout.listactivity, COUNTRIES));
    }
}
