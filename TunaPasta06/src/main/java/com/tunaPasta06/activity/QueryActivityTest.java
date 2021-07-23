package com.tunaPasta06.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tunaPasta06.R;

public class QueryActivityTest extends Activity {
    private PackageManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.queryactivitytest);

        GridView gw = findViewById(R.id.gridView);

        gw.setAdapter(new AppAdapter(this));

    }

    private class AppAdapter extends BaseAdapter {
        private List<ResolveInfo> apps;
        private Context ctx;

        AppAdapter(Context context) {
            this.ctx = context;
            pm = getPackageManager();

            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            apps = pm.queryIntentActivities(intent, 0);

            for (int i = 0; i < apps.size(); i++) {
                ResolveInfo info = apps.get(i);
                if (getPackageName().equals(info.activityInfo.packageName)) {
                    apps.remove(i);
                    break;
                }
            }
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
                View view = inflate.inflate(R.layout.queryactivitytestitem, null);

                ResolveInfo info = apps.get(position);

                ImageView img_photo = view.findViewById(R.id.img_photo);
                img_photo.setImageDrawable(info.activityInfo.loadIcon(pm));

                TextView text_name = view.findViewById(R.id.text_name);
                text_name.setText(info.activityInfo.loadLabel(pm).toString());

                TextView text_package = view.findViewById(R.id.text_package);
                text_package.setText("位置" + info.activityInfo.applicationInfo.packageName);

                return view;
            }
            return convertView;
        }
    }
}
