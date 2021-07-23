package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaDrag;
import com.tunaPasta19.tuna.TunaView;
import com.tunaPasta19.tuna.TunaView.TunaTouchListener;
import com.tunaPasta19.tuna.TunaView.TunaTouchUpListener;

public class TunaDragTest extends Activity {
    private TunaDrag tunaDragTouchoutable, tunaDragTouchoutUnable;
    private TunaView tunaViewTouchoutableReset, tunaViewTouchoutableGetIndex,
        tunaViewTouchoutUnableReset, tunaViewTouchoutUnableGetIndex;

    private TunaTouchUpListener tunaTouchUpListener = new TunaTouchUpListener() {
        @Override
        public void tunaTouchUp(View v) {
            switch (v.getId()) {
                case R.id.tunaViewTouchoutableReset:
                    tunaDragTouchoutable.setTunaCurrentIndex(0);
                    break;
                case R.id.tunaViewTouchoutableGetIndex:
                    Toast.makeText(TunaDragTest.this, "tunaDragTouchoutable下标为" + tunaDragTouchoutable.getTunaCurrentIndex(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tunaViewTouchoutUnableReset:
                    tunaDragTouchoutUnable.setTunaCurrentIndex(0);
                    break;
                case R.id.tunaViewTouchoutUnableGetIndex:
                    Toast.makeText(TunaDragTest.this, "tunaDragTouchoutUnable下标为" + tunaDragTouchoutUnable.getTunaCurrentIndex(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunadragtest);

        tunaDragTouchoutable = findViewById(R.id.tunaDragTouchoutable);
        tunaDragTouchoutUnable = findViewById(R.id.tunaDragTouchoutUnable);

        tunaViewTouchoutableReset = findViewById(R.id.tunaViewTouchoutableReset);
        tunaViewTouchoutableGetIndex = findViewById(R.id.tunaViewTouchoutableGetIndex);

        tunaViewTouchoutUnableReset = findViewById(R.id.tunaViewTouchoutUnableReset);
        tunaViewTouchoutUnableGetIndex = findViewById(R.id.tunaViewTouchoutUnableGetIndex);

        tunaDragTouchoutable.setTunaTouchListener(new TunaTouchListener() {
            @Override
            public void tunaTouch(View v) {
                tunaDragTouchoutable.setTunaDragCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaDragTouchoutable.getTunaTouchEventX());
            }
        });

        tunaDragTouchoutUnable.setTunaTouchListener(new TunaTouchListener() {
            @Override
            public void tunaTouch(View v) {
                tunaDragTouchoutUnable.setTunaDragCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaDragTouchoutUnable.getTunaTouchEventX());
            }
        });

        tunaViewTouchoutableReset.setTunaTouchUpListener(tunaTouchUpListener);
        tunaViewTouchoutableGetIndex.setTunaTouchUpListener(tunaTouchUpListener);

        tunaViewTouchoutUnableReset.setTunaTouchUpListener(tunaTouchUpListener);
        tunaViewTouchoutUnableGetIndex.setTunaTouchUpListener(tunaTouchUpListener);
    }
}
