package com.tunaPasta04.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.tunaPasta04.R;
import com.tunaPasta04.entity.Box;
import com.tunaPasta04.widget.BackFormView;

public class BackFormTest extends Activity {
    private BackFormView view;
    private Box box;
    private boolean flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backfromtest);
        box = new Box(0, 450, 10, 30);
        view =  findViewById(R.id.view);
        view.setBox(box);
        new Thread() {
            @Override
            public void run() {
                for (; !flag; ) {
                    try {
                        if (box.x < 0 || box.x >= 320) {
                            box.flagX = !box.flagX;
                        }
                        box.x += box.flagX ? -20 : 20;
                        view.draw();
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //	320/6=53.3
//			System.out.println(box.x);
            switch (box.x / 53) {
                case 0:
                    System.out.println(0);
                    break;
                case 1:
                    System.out.println(1);
                    break;
                case 2:
                    System.out.println(2);
                    break;
                case 3:
                    System.out.println(3);
                    break;
                case 4:
                    System.out.println(4);
                    break;
                case 5:
                    System.out.println(5);
                    break;
                default:
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void finish() {
        flag = !flag;
        super.finish();
    }
}