package com.tunaPasta19.activity;

import android.app.Activity;
import android.graphics.BlurMaskFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaColorPickerDialog;
import com.tunaPasta19.tuna.TunaDraw;

/**
 * @author Tunasashimi
 * @date 11/3/15 20:43
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class CustomCakeProcessTest extends Activity {

    private static final int COLOR_MENU_ID = Menu.FIRST;
    private static final int EMBOSS_MENU_ID = Menu.FIRST + 1;
    private static final int BLUR_MENU_ID = Menu.FIRST + 2;
    private static final int ERASE_MENU_ID = Menu.FIRST + 3;
    private static final int SRCATOP_MENU_ID = Menu.FIRST + 4;

    private EmbossMaskFilter embossMaskFilter;
    private BlurMaskFilter blurMaskFilter;

    private TunaDraw tunaPainting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.customcakeprocesstest);

        embossMaskFilter = new EmbossMaskFilter(new float[]{1, 1, 1}, 0.4f, 6, 3.5f);
        blurMaskFilter = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);

        tunaPainting =  findViewById(R.id.tunaPainting);
        tunaPainting.setTunaPaintingListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, COLOR_MENU_ID, 1, "Color");
        menu.add(Menu.NONE, EMBOSS_MENU_ID, 2, "Emboss");
        menu.add(Menu.NONE, BLUR_MENU_ID, 3, "Blur");
        menu.add(Menu.NONE, ERASE_MENU_ID, 4, "Erase");
        menu.add(Menu.NONE, SRCATOP_MENU_ID, 5, "SrcATop");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        tunaPainting.getTunaPaintingPaint().setXfermode(null);
        tunaPainting.getTunaPaintingPaint().setAlpha(0xFF);

        switch (item.getItemId()) {
            case COLOR_MENU_ID:
                TunaColorPickerDialog tunaColorPicker = new TunaColorPickerDialog(this,
                        tunaPainting.getTunaPaintingPaint().getColor(),
                        new TunaColorPickerDialog.TunaColorSelectListener() {
                            @Override
                            public void tunaColorSelect(int color) {
                                tunaPainting.getTunaPaintingPaint().setColor(color);
                            }
                        });
                tunaColorPicker.show();
                return true;
            case EMBOSS_MENU_ID:
                if (tunaPainting.getTunaPaintingPaint().getMaskFilter() != embossMaskFilter) {
                    tunaPainting.getTunaPaintingPaint().setMaskFilter(embossMaskFilter);
                } else {
                    tunaPainting.getTunaPaintingPaint().setMaskFilter(null);
                }
                return true;
            case BLUR_MENU_ID:
                if (tunaPainting.getTunaPaintingPaint().getMaskFilter() != blurMaskFilter) {
                    tunaPainting.getTunaPaintingPaint().setMaskFilter(blurMaskFilter);
                } else {
                    tunaPainting.getTunaPaintingPaint().setMaskFilter(null);
                }
                return true;
            case ERASE_MENU_ID:
                tunaPainting.getTunaPaintingPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                return true;
            case SRCATOP_MENU_ID:
                tunaPainting.getTunaPaintingPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                tunaPainting.getTunaPaintingPaint().setAlpha(0x80);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
