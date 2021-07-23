package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaScratch;
import com.tunaPasta19.tuna.TunaScratch.onTunaScratchCompleteListener;

public class TunaScratchTest extends Activity {

    private TunaScratch tunaScratch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunascratchtest);

        tunaScratch = findViewById(R.id.tunaScratch);

        tunaScratch.setOnTunaScratchCompleteListener(new onTunaScratchCompleteListener() {
            @Override
            public void onTunaScratchComplete() {
                Toast.makeText(getApplicationContext(), "刮除面积达到阈值", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
