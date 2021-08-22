package com.tunaPasta17.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta17.R;

public class DataBindingTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databindingtest);
    }

    private void startActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    public void startActivity01(View view) {
        startActivity(DBModelTest.class);
    }

    public void startActivity02(View view) {
        startActivity(DBOnClickTest.class);
    }

    public void startActivity03(View view) {
        startActivity(DBCollectionTest.class);
    }

    public void startActivity04(View view) {
        startActivity(DBObservableTest.class);
    }

    public void startActivity05(View view) {
        startActivity(DBEventTest.class);
    }

    public void startActivity06(View view) {
        startActivity(DBIncludeTest.class);
    }

    public void startActivity07(View view) {
        startActivity(DBAdapterTest.class);
    }

    public void startActivity08(View view) {
        startActivity(DBValuesTest.class);
    }

    public void startActivity09(View view) {
        startActivity(DBFragmentTest.class);
    }

    public void startActivity10(View view) {
        startActivity(DBRecyclerTest.class);
    }

    public void startActivity11(View view) {
        startActivity(DBSeekBarTest.class);
    }

    public void startActivity12(View view) {
    }
}
