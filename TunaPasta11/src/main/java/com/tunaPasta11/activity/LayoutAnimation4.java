package com.tunaPasta11.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.tunaPasta11.R;

import java.util.List;

public class LayoutAnimation4 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadApps();

        setContentView(R.layout.layout_animation_4);
        GridView grid =  findViewById(R.id.grid);
        grid.setAdapter(new AppsAdapter());

    }

    private List<ResolveInfo> mApps;

    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    public class AppsAdapter extends BaseAdapter {
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(LayoutAnimation4.this);

            ResolveInfo info = mApps.get(position % mApps.size());

            i.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
            i.setScaleType(ImageView.ScaleType.FIT_CENTER);
            final int w = (int) (36 * getResources().getDisplayMetrics().density + 0.5f);
            i.setLayoutParams(new GridView.LayoutParams(w, w));
            return i;
        }


        public final int getCount() {
            return Math.min(32, mApps.size());
        }

        public final Object getItem(int position) {
            return mApps.get(position % mApps.size());
        }

        public final long getItemId(int position) {
            return position;
        }
    }
}
