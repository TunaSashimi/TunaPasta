package com.tunaPasta10.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tunaPasta10.R;

public class EntryAct extends ListActivity {

    private String category = "tunaPasta10.test";
    private PackageManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //跳转的intent
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        if (path == null) {
            path = "";
        } else {
            setTitle(path.substring(1));
        }

        pm = getPackageManager();

        List<Map<String, Object>> data = actionData(path);

        // ListActivity
        setListAdapter(new SimpleAdapter(this, data, R.layout.entryactitem, new String[]{"icon", "title"}, new int[]{R.id.icon, R.id.title}));
    }

    @Override
    protected void onListItemClick(ListView listview, View v, int position, long id) {
        super.onListItemClick(listview, v, position, id);
        Map<?, ?> data = (Map<?, ?>) listview.getItemAtPosition(position);
        Intent intent = (Intent) data.get("intent");
        startActivity(intent);
    }

    /**
     * @param path
     * @return
     */
    private List<Map<String, Object>> actionData(String path) {
        List<Map<String, Object>> actionData = new ArrayList<>();
        path = path + "/";

        Intent main = new Intent();

        main.setAction(Intent.ACTION_MAIN);
        main.addCategory(category);

        @SuppressLint("WrongConstant") List<ResolveInfo> list = pm.queryIntentActivities(main, PackageManager.PERMISSION_GRANTED);

        Map<String, Object> data;
        Set<String> set = new HashSet();

        for (int i = 0; i < list.size(); i++) {
            String label = list.get(i).loadLabel(pm).toString();
            if (!label.startsWith("/")) {
                label = "/" + label;
            }

            if (label.startsWith(path)) {
                String str = label.substring(path.length());
                String[] strArry = str.split("/");
                if (strArry.length > 1 && !set.contains(strArry[0])) {
                    data = new HashMap();
                    data.put("title", strArry[0]);
                    data.put("icon", R.drawable.folder);
                    Intent intent = new Intent(this, EntryAct.class);
                    intent.putExtra("path", path + strArry[0]);
                    data.put("intent", intent);
                    set.add(strArry[0]);
                    actionData.add(data);
                } else if (strArry.length == 1) {
                    data = new HashMap();
                    data.put("title", strArry[0]);
                    data.put("icon", R.drawable.file);
                    Intent intent = new Intent();
                    intent.setClassName(this, list.get(i).activityInfo.name);
                    data.put("intent", intent);
                    actionData.add(data);
                }
            }
        }
        return actionData;
    }
}
