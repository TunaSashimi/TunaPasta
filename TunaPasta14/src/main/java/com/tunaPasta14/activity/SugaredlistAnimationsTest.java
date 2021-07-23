package com.tunaPasta14.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tunaPasta14.R;
import com.tunaPasta14.widget.NowImageAdapter;
import com.tunaPasta14.widget.PlusImageAdapter;
import com.tunaPasta14.widget.SpeedScrollListener;

public class SugaredlistAnimationsTest extends ListActivity {

    private SpeedScrollListener listener;
    private PlusImageAdapter plusAdapter;
    private NowImageAdapter nowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sugaredlistanimationstest);

        listener = new SpeedScrollListener();
        getListView().setOnScrollListener(listener);
        plusAdapter = new PlusImageAdapter(getApplicationContext(), listener);
        setListAdapter(plusAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sugaredlistanimationstest_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        boolean handled = false;
        switch (item.getItemId()) {
            case R.id.google_plus:
                plusAdapter = new PlusImageAdapter(getApplicationContext(), listener);
                setListAdapter(plusAdapter);
                break;

            case R.id.google_now:
                nowAdapter = new NowImageAdapter(getApplicationContext(), listener);
                setListAdapter(nowAdapter);
                break;
        }
        return handled;
    }

}
