package com.tunaPasta10.activity;

import android.app.Activity;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta10.R;
import com.tunaPasta10.widget.PhotoViewAttacher;
import com.tunaPasta10.widget.PhotoViewAttacher.OnMatrixChangedListener;
import com.tunaPasta10.widget.PhotoViewAttacher.OnPhotoTapListener;

public class SimpleSampleTest extends Activity {

    static final String PHOTO_TAP_TOAST_STRING = "Photo Tap! X: %.2f %% Y:%.2f %%";

    private ImageView mImageView;
    private TextView mCurrMatrixTv;

    private PhotoViewAttacher mAttacher;

    private Toast mCurrentToast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplesampletest);

        mImageView = findViewById(R.id.iv_photo);
        mCurrMatrixTv = findViewById(R.id.tv_current_matrix);

        Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
        mImageView.setImageDrawable(bitmap);

        // The MAGIC happens here!
        mAttacher = new PhotoViewAttacher(mImageView);

        // Lets attach some listeners, not required though!
        mAttacher.setOnMatrixChangeListener(new MatrixChangeListener());
        mAttacher.setOnPhotoTapListener(new PhotoTapListener());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Need to call clean-up
        mAttacher.cleanup();
    }

    private class PhotoTapListener implements OnPhotoTapListener {
        @Override
        public void onPhotoTap(View view, float x, float y) {
            float xPercentage = x * 100f;
            float yPercentage = y * 100f;

            if (null != mCurrentToast) {
                mCurrentToast.cancel();
            }

            mCurrentToast = Toast.makeText(SimpleSampleTest.this,
                    String.format(PHOTO_TAP_TOAST_STRING, xPercentage, yPercentage), Toast.LENGTH_SHORT);
            mCurrentToast.show();
        }
    }

    private class MatrixChangeListener implements OnMatrixChangedListener {

        @Override
        public void onMatrixChanged(RectF rect) {
            mCurrMatrixTv.setText(rect.toString());
        }
    }
}
