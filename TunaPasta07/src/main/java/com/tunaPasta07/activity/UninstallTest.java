package com.tunaPasta07.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tunaPasta07.R;

//需要权限
//<uses-permission android:name="com.android.launcher.permission.READ_SETTINGS" />  

//总结：
//
//通过 PackageInfo  获取具体信息方法：
//
//包名获取方法：packageInfo.packageName
//icon获取获取方法：packageManager.getApplicationIcon(applicationInfo)
//应用名称获取方法：packageManager.getApplicationLabel(applicationInfo)
//使用权限获取方法：packageManager.getPackageInfo(packageName,PackageManager.GET_PERMISSIONS)
//.requestedPermissions
//
//通过 ResolveInfo 获取具体信息方法：
//
//包名获取方法：resolve.activityInfo.packageName
//icon获取获取方法：resolve.loadIcon(packageManager)
//应用名称获取方法：resolve.loadLabel(packageManager).toString()

public class UninstallTest extends Activity {

    PackageManager pm;
    GridView gridview;

    List<ResolveInfo> resolveInfoList = new ArrayList();
    AppAdapter appadpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.uninstalltest);

        Button button01 = findViewById(R.id.button01);
        Button button02 = findViewById(R.id.button02);

        gridview = findViewById(R.id.gridview);

        appadpter = new AppAdapter(this, resolveInfoList);

        gridview.setAdapter(appadpter);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ResolveInfo info = resolveInfoList.get(position);

                uninstallApp(UninstallTest.this, info.activityInfo.applicationInfo.packageName);

                resolveInfoList.remove(info);

                appadpter.notifyDataSetChanged();
            }
        });

        button01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //要用add的方法增加进去的list才会改变
                //	resolveInfoList=getAllPagckage(UninstallTest.this);

                resolveInfoList.clear();
                resolveInfoList.addAll(getAllPagckage(UninstallTest.this));

                appadpter.notifyDataSetChanged();
            }
        });

        button02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                resolveInfoList.clear();
                resolveInfoList.addAll(getfilterPackage(UninstallTest.this));

                appadpter.notifyDataSetChanged();
            }
        });
    }

    // 获取所有应用
    public List<ResolveInfo> getAllPagckage(Context context) {
        pm = context.getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 通过查询，获得所有ResolveInfo对象.
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(mainIntent, PackageManager.GET_GIDS);
        // 调用系统排序，根据name排序
        Collections.sort(resolveInfoList, new ResolveInfo.DisplayNameComparator(pm));
        return resolveInfoList;
    }

    // 过滤出第三方应用
    private List<ResolveInfo> getfilterPackage(Context context) {
        pm = context.getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 通过查询，获得所有ResolveInfo对象.
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(mainIntent, PackageManager.GET_GIDS);

        List<ResolveInfo> list = new ArrayList();
        if (!resolveInfoList.isEmpty()) {
            int size = resolveInfoList.size();
            ResolveInfo resolveInfo;
            for (int i = 0; i < size; i++) {
                resolveInfo = resolveInfoList.get(i);
                // 第三方应用,判断(applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)的值，
                //	该值大于0时，表示获取的应用为系统预装的应用，反之则为手动安装的应用
                if ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                    list.add(resolveInfo);
                }
            }
        }
        return list;
    }

    // 卸载：
    // (注：1、两种方式均可；2、appPackage为resolveInfo.activityInfo.packageName)
    public void uninstallApp(Context context, String appPackage) {
        Uri packageURI = Uri.parse("package:" + appPackage);
        // 自定义卸载
        // Intent uninstallIntent = new Intent(Intent.ACTION_DELETE,
        // packageURI);
        // ((Activity)context).startActivityForResult(uninstallIntent, 0);
        // 调用系统自带卸载操作进行卸载

        Intent uninstallIntent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        context.startActivity(uninstallIntent);
    }

    private class AppAdapter extends BaseAdapter {
        private List<ResolveInfo> apps;
        private Context ctx;

        AppAdapter(Context context, List<ResolveInfo> apps) {
            this.ctx = context;
            this.apps = apps;
        }

        public int getCount() {
            return apps.size();
        }

        public Object getItem(int position) {
            return apps.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflate = ((Activity) ctx).getLayoutInflater();
                convertView = inflate.inflate(R.layout.uninstalltestitem, null);
            }

            ResolveInfo info = apps.get(position);

            ImageView photo = convertView.findViewById(R.id.photo);
            photo.setImageDrawable(info.activityInfo.loadIcon(pm));

            TextView name = convertView.findViewById(R.id.name);
            name.setText(info.activityInfo.loadLabel(pm).toString());

            return convertView;
        }
    }
}
