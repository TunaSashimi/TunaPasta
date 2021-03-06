package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tunaPasta17.R;
import com.tunaPasta17.view.TCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCalendarTest extends Activity {
    public class DayFinish {
        public int day;
        public int all;
        public int finish;

        public DayFinish(int day, int finish, int all) {
            this.day = day;
            this.all = all;
            this.finish = finish;
        }
    }

    private TCalendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcalendartest);
        cal = findViewById(R.id.cal);

        //模拟请求当月数据
        final List<DayFinish> list = new ArrayList();
        list.add(new DayFinish(1, 2, 2));
        list.add(new DayFinish(2, 1, 2));
        list.add(new DayFinish(3, 0, 2));
        list.add(new DayFinish(4, 2, 2));
        list.add(new DayFinish(5, 2, 2));
        list.add(new DayFinish(6, 2, 2));
        list.add(new DayFinish(7, 2, 2));
        list.add(new DayFinish(8, 0, 2));
        list.add(new DayFinish(9, 1, 2));
        list.add(new DayFinish(10, 2, 2));
        list.add(new DayFinish(11, 5, 2));
        list.add(new DayFinish(12, 2, 2));
        list.add(new DayFinish(13, 2, 2));
        list.add(new DayFinish(14, 3, 2));
        list.add(new DayFinish(15, 2, 2));
        list.add(new DayFinish(16, 1, 2));
        list.add(new DayFinish(17, 0, 2));
        list.add(new DayFinish(18, 2, 2));
        list.add(new DayFinish(19, 2, 2));
        list.add(new DayFinish(20, 0, 2));
        list.add(new DayFinish(21, 2, 2));
        list.add(new DayFinish(22, 1, 2));
        list.add(new DayFinish(23, 2, 0));
        list.add(new DayFinish(24, 0, 2));
        list.add(new DayFinish(25, 2, 2));
        list.add(new DayFinish(26, 2, 2));
        list.add(new DayFinish(27, 2, 2));
        list.add(new DayFinish(28, 2, 2));
        list.add(new DayFinish(29, 2, 2));
        list.add(new DayFinish(30, 2, 2));
        list.add(new DayFinish(31, 2, 2));

        cal.setRenwu("2017年1月", list);
        cal.setOnClickListener(new TCalendar.onClickListener() {
            @Override
            public void onLeftRowClick() {
                Toast.makeText(TCalendarTest.this, "点击减箭头", Toast.LENGTH_SHORT).show();
                cal.monthChange(-1);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            TCalendarTest.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cal.setRenwu(list);
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }.start();
            }

            @Override
            public void onRightRowClick() {
                Toast.makeText(TCalendarTest.this, "点击加箭头", Toast.LENGTH_SHORT).show();
                cal.monthChange(1);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            TCalendarTest.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cal.setRenwu(list);
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }.start();
            }

            @Override
            public void onTitleClick(String monthStr, Date month) {
                Toast.makeText(TCalendarTest.this, "点击了标题：" + monthStr, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWeekClick(int weekIndex, String weekStr) {
                Toast.makeText(TCalendarTest.this, "点击了星期：" + weekStr, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDayClick(int day, String dayStr, DayFinish finish) {
                Toast.makeText(TCalendarTest.this, "点击了日期：" + dayStr, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
