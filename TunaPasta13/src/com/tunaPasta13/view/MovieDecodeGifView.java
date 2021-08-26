package com.tunaPasta13.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import com.tunaPasta13.R;


public class MovieDecodeGifView extends View {
    private long movieStart;
    private Movie movie;

    // 此处必须重写该构造方法
    public MovieDecodeGifView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        // 以文件流（InputStream）读取进gif图片资源
        movie = Movie.decodeStream(getResources().openRawResource(R.raw.tunasashimi0));

        //不是gif的话movie为null
//		System.out.println("movie==>"+movie);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long curTime = SystemClock.uptimeMillis();
        // 第一次播放
        if (movieStart == 0) {
            movieStart = curTime;
        }
        if (movie != null) {
            int duraction = movie.duration();
            int relTime = (int) ((curTime - movieStart) % duraction);
            movie.setTime(relTime);
            movie.draw(canvas, 0, 0);
            // 强制重绘
            invalidate();
        }
        super.onDraw(canvas);
    }
}