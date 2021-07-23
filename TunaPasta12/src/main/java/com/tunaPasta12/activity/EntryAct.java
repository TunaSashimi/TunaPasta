package com.tunaPasta12.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta12.R;

public class EntryAct extends Activity {

    private String[] s = {"Patterns", "DrawPoints", "PolyToPoly", "Xfermodes",
            "Vertices", "UnicodeChart", "Typefaces", "TouchPaint",
            "TextAlign", "Sweep", "SurfaceViewOverlay", "ScaleToFit",
            "RoundRects", "Regions", "PurgeableBitmap",
            "CompressedTextureActivity", "CubeMapActivity", "FrameBufferObjectActivity",
            "GLSurfaceViewActivity", "Cube", "MatrixPaletteActivity", "SpriteTextActivity",
            "TouchRotateActivity", "TranslucentGLSurfaceViewActivity",
            "AlertDialogSamples", "RotatingButton", "InputMethodManagerTest",
    };

    private Class<?>[] c = {Patterns.class, DrawPoints.class, PolyToPoly.class, Xfermodes.class,
            Vertices.class, UnicodeChart.class, Typefaces.class, TouchPaintTest.class,
            TextAlign.class, Sweep.class, SurfaceViewOverlay.class, ScaleToFit.class,
            RoundRects.class, Regions.class, PurgeableBitmap.class,
            CompressedTextureActivity.class, CubeMapActivity.class, FrameBufferObjectActivity.class,
            GLSurfaceViewActivity.class, Kube.class, MatrixPaletteActivity.class, SpriteTextActivity.class,
            TouchRotateActivity.class, TranslucentGLSurfaceViewActivity.class,
            AlertDialogSamples.class, RotatingButton.class, InputOpenClosedAutomaticTest.class,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entryact);

        ListView lv = findViewById(R.id.listView);
        List<String> list = new ArrayList();

        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }

        lv.setAdapter(new ArrayAdapter(this, R.layout.entryactitem, list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(EntryAct.this, c[arg2]));
            }
        });
    }
}
