package com.tunaPasta10.fireview;

import java.io.InputStream;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

import com.tunaPasta10.R;
import com.tunaPasta10.dot.Dot;
import com.tunaPasta10.dot.DotFactory;
import com.tunaPasta10.dot.LittleDot;
import com.tunaPasta10.tool.NativeRequest;

/**
 * 类名称：FireworkView
 * 类描述：继承android的View，实现烟花效果
 * 创建人：anan
 * 创建时间：2012-12-16 下午1:02:36
 * 修改人：anan
 * 修改时间：2012-12-16 下午1:02:36
 * 修改备注：
 */
public class FireworkView extends View {

    final String LOG_TAG = FireworkView.class.getSimpleName();

    public static final int ID_SOUND_UP = 0;
    public static final int ID_SOUND_BLOW = 1;
    public static final int ID_SOUND_MULTIPLE = 2;
    final static int TIME = 5; // 圈数

    /**
     * 画面中的烟花数
     */
    private Vector<Dot> lList = new Vector<Dot>();

    LittleDot[] ld = new LittleDot[200];
    private DotFactory df = null;

    boolean running = true;

    Bitmap backGroundBitmap;

    Context mContext;

    public static SoundPlay soundPlay;

    public FireworkView(Context context) {
        super(context);
        df = new DotFactory();
        new MyThread().start();
        mContext = context;
        backGroundBitmap = ReadBitMap(mContext, R.drawable.night);
        backGroundBitmap = resizeImage(backGroundBitmap, NativeRequest.getScreenWidth(context), NativeRequest.getScreenHeight(context));
        initSound(mContext);

    }

    public static void initSound(Context context) {
        soundPlay = new SoundPlay();
        soundPlay.initSounds(context);
        soundPlay.loadSfx(context, R.raw.up, ID_SOUND_UP);
        soundPlay.loadSfx(context, R.raw.blow, ID_SOUND_BLOW);
        soundPlay.loadSfx(context, R.raw.multiple, ID_SOUND_MULTIPLE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backGroundBitmap, 0, 0, null);
        synchronized (lList) {
            for (int i = 0; i < lList.size(); i++) {
                lList.get(i).myPaint(canvas, lList);
            }
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Dot dot = null;
            int rand = (int) (Math.random() * 99);

            dot = df.makeDot(mContext, rand, (int) event.getX(), (int) event.getY());
            synchronized (lList) {
                lList.add(dot);
                soundPlay.play(ID_SOUND_UP, 0);
            }
        }
        return super.onTouchEvent(event);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Bitmap ReadBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    public Bitmap resizeImage(Bitmap mBitmap, int w, int h) {
        Bitmap BitmapOrg = mBitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap tmp = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height,
                matrix, true);
        return tmp;
    }

    /**
     * 类名称：MyThread
     * 类描述：重绘线程
     * 创建人：anan
     * 创建时间：2012-12-16 下午1:06:50
     * 修改人：anan
     * 修改时间：2012-12-16 下午1:06:50
     * 修改备注：
     */
    class MyThread extends Thread {
        // 用于控制烟火在空中滞留的时间
        int times = 0;

        public void run() {
            Dot dot = null;
            while (running) {

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e);
                }

                synchronized (lList) {
                    // 防止画面的烟花个数多于50个
                    while (lList.size() > 50) {
                        System.out.println("当前数目超过50");
                        for (int i = 0; i < 10; i++) {
                            lList.remove(i);
                        }
                    }
//					// 自动添加烟火
//					if (lList.size() <= 2) {
//						Dot tmp = null;
//						int rand = (int) (Math.random() * 99);
//						Random random = new Random();
//						tmp = df.makeDot(mContext, rand, random.nextInt(480),
//								50 + random.nextInt(300));
//						lList.add(tmp);
//					}
                }

                for (int i = 0; i < lList.size(); i++) {
                    dot = (Dot) lList.get(i);
                    if (dot.state == 1 && !dot.whetherBlast()) {
                        dot.rise();
                    }
                    // 如果是whetherBlast()返回的是true，那么就把该dot的state设置为2
                    else if (dot.state == 1 && dot.state != 2) {
                        dot.state = 2;
                        soundPlay.play(ID_SOUND_BLOW, 0);
                    } else if (dot.state == 3) {

                    }
                    // 规定，每个爆炸点最多是TIME圈，超过就会消失
                    if (dot.circle >= TIME) {
                        // 在空中滞留一秒才消失
                        if (times >= 10) {
                            dot.state = 4;
                            times = 0;
                        } else {
                            times++;
                        }
                        // dot.state = 4;
                    }
                }
            }
        }
    }
}
