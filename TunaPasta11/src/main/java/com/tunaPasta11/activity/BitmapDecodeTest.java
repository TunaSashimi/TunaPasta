package com.tunaPasta11.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta11.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class BitmapDecodeTest extends GraphicsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));

        Toast.makeText(this, "建议看下Android2.2Sample/Graphics/BitmapDecode为何效果不同", Toast.LENGTH_LONG).show();
    }

    private static class SampleView extends View {
        private Bitmap mBitmap1;
        private Bitmap mBitmap2;
        private Bitmap mBitmap3;
        private Bitmap mBitmap4;
        private Drawable mDrawable;

        private Movie mMovie;
        private long mMovieStart;

        private static byte[] streamToBytes(InputStream is) {
            ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int len;
            try {
                while ((len = is.read(buffer)) >= 0) {
                    os.write(buffer, 0, len);
                }
            } catch (java.io.IOException e) {
            }
            return os.toByteArray();
        }

        @SuppressLint("ResourceType")
        public SampleView(Context context) {
            super(context);
            setFocusable(true);

            InputStream is;
            is = context.getResources().openRawResource(R.drawable.beach);

            BitmapFactory.Options opts = new BitmapFactory.Options();
            Bitmap bitmap;

            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, opts);

            // now opts.outWidth and opts.outHeight are the dimension of the
            // bitmap, even though bm is null

            opts.inJustDecodeBounds = false;    // this will request the bm
            opts.inSampleSize = 4;             // scaled down by 4
            bitmap = BitmapFactory.decodeStream(is, null, opts);

            mBitmap1 = bitmap;

            // decode an image with transparency
            is = context.getResources().openRawResource(R.drawable.frog);
            mBitmap2 = BitmapFactory.decodeStream(is);

            // create a deep copy of it using getPixels() into different configs
            int w = mBitmap2.getWidth();
            int h = mBitmap2.getHeight();
            int[] pixels = new int[w * h];
            mBitmap2.getPixels(pixels, 0, w, 0, 0, w, h);
            mBitmap3 = Bitmap.createBitmap(pixels, 0, w, w, h,
                    Bitmap.Config.ARGB_8888);
            mBitmap4 = Bitmap.createBitmap(pixels, 0, w, w, h,
                    Bitmap.Config.ARGB_4444);

            mDrawable = context.getResources().getDrawable(R.drawable.button);
            mDrawable.setBounds(150, 20, 300, 100);

            is = context.getResources().openRawResource(R.drawable.animated_gif);
            if (true) {
                mMovie = Movie.decodeStream(is);
            } else {
                byte[] array = streamToBytes(is);
                mMovie = Movie.decodeByteArray(array, 0, array.length);
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(0xFFCCCCCC);

            Paint p = new Paint();
            p.setAntiAlias(true);

//            canvas.drawBitmap(mBitmap1, 10, 10, null);
            canvas.drawBitmap(mBitmap2, 10, 170, null);
            canvas.drawBitmap(mBitmap3, 110, 170, null);
            canvas.drawBitmap(mBitmap4, 210, 170, null);

            mDrawable.draw(canvas);

            long now = android.os.SystemClock.uptimeMillis();
            if (mMovieStart == 0) {   // first time
                mMovieStart = now;
            }
            if (mMovie != null) {
                int dur = mMovie.duration();
                if (dur == 0) {
                    dur = 1000;
                }
                int relTime = (int) ((now - mMovieStart) % dur);
                mMovie.setTime(relTime);


                mMovie.draw(canvas, getWidth() - mMovie.width(), getHeight() - mMovie.height());
                invalidate();
            }
        }
    }
}

