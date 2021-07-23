package com.tunaPasta06.activity;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import com.tunaPasta06.tool.NativeRequest;
import com.tunaPasta06.tool.PageTurningFactory;
import com.tunaPasta06.widget.PageTurningView;

public class PageTurningTest extends Activity {

    private PageTurningView pageTurningView;

    private Bitmap mCurPageBitmap, mNextPageBitmap;
    private Canvas mCurPageCanvas, mNextPageCanvas;

    private PageTurningFactory pagefactory;

    private int mWidth;
    private int mHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWidth = NativeRequest.getScreenWidth(this);
        mHeight = NativeRequest.getScreenHeight(this);

        pageTurningView = new PageTurningView(this, mWidth, mHeight);
        setContentView(pageTurningView);

        mCurPageBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mNextPageBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);

        pagefactory = new PageTurningFactory(mWidth, mHeight);
        //设置图片的背景颜色,图片难以匹配正好的大小,故先用默认的纯色
        //	pagefactory.setBgBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.shelf_bkg));

        mCurPageCanvas = new Canvas(mCurPageBitmap);
        mNextPageCanvas = new Canvas(mNextPageBitmap);

        //先把assets中的文件放到sd卡中
        NativeRequest.copyAssets2SDCard(this);
        try {
            pagefactory.openbook("/sdcard/AppWidgetProviderIntroduce.txt");
            pagefactory.onDraw(mCurPageCanvas);
        } catch (IOException e1) {
            Toast.makeText(this, "请检查assets中的AppWidgetProviderIntroduce.txt文件是否被拷贝到了sd卡目录中", Toast.LENGTH_LONG).show();
        }

        pageTurningView.setBitmaps(mCurPageBitmap, mCurPageBitmap);

        pageTurningView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionevent) {
                boolean ret = false;
                if (view == pageTurningView) {
                    if (motionevent.getAction() == MotionEvent.ACTION_DOWN) {
                        pageTurningView.abortAnimation();
                        pageTurningView.calcCornerXY(motionevent.getX(), motionevent.getY());

                        pagefactory.onDraw(mCurPageCanvas);
                        if (pageTurningView.DragToRight()) {
                            try {
                                pagefactory.prePage();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            if (pagefactory.isfirstPage())
                                return false;
                            pagefactory.onDraw(mNextPageCanvas);
                        } else {
                            try {
                                pagefactory.nextPage();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            if (pagefactory.islastPage()) {
                                return false;
                            }
                            pagefactory.onDraw(mNextPageCanvas);
                        }
                        pageTurningView.setBitmaps(mCurPageBitmap, mNextPageBitmap);
                    }

                    ret = pageTurningView.doTouchEvent(motionevent);
                    return ret;
                }
                return false;
            }
        });
    }

}