package com.tunaPasta19.activity;

import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaView;

public class TunaMaterialTest extends Activity {

    private TunaView

        tunaMaterialRadio01, tunaMaterialRadio02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunamaterlaitest);

        //
        tunaMaterialRadio01 = findViewById(R.id.tunaMaterialRadio01);
        tunaMaterialRadio02 = findViewById(R.id.tunaMaterialRadio02);

        TunaView.tunaAssociate(Arrays.asList(tunaMaterialRadio01, tunaMaterialRadio02));
    }
}
