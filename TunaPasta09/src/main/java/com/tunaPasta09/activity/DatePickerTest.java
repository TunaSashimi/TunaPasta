package com.tunaPasta09.activity;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta09.R;
import com.tunaPasta09.adapter.WheelArrayAdapter;
import com.tunaPasta09.adapter.WheelNumbericAdapter;
import com.tunaPasta09.widget.WheelView;

public class DatePickerTest extends Activity {
    private WheelView dateWheel, hourWheel, minWheel;
    private String[] days = new String[90];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.datepickertest);

        dateWheel = findViewById(R.id.dateWheel);
        days = initDate();
        dateWheel.setAdapter(new WheelArrayAdapter(days));

        hourWheel = findViewById(R.id.hoursWheel);
        hourWheel.setAdapter(new WheelNumbericAdapter(0, 23));
        hourWheel.setLabel("时");

        minWheel = findViewById(R.id.minsWheel);
        String mins[] = new String[]{"0", "15", "30", "45"};
        minWheel.setAdapter(new WheelArrayAdapter(mins));
        minWheel.setLabel("分");

        initTime();
    }

    private String[] initDate() {
        for (int i = 0; i < 90; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 1 * i);
            Date date = calendar.getTime();
            int year = calendar.get(Calendar.YEAR);
            int month = date.getMonth() + 1;
            int day = date.getDate();
            days[i] = year + "-" + month + "-" + day;
        }
        return days;

    }

    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        int lastMin = 15 * (minute / 15) + 15 + 30;
        calendar.set(Calendar.MINUTE, 0);
        calendar.add(Calendar.MINUTE, lastMin);

        Date date = calendar.getTime();
        int preDate = date.getDate();
        int hour = date.getHours();
        int min = date.getMinutes();

        Calendar c = Calendar.getInstance();
        int currDate = c.getTime().getDate();
        if (preDate == currDate) {
            dateWheel.setCurrentItem(0);
        } else if (preDate == currDate + 1) {
            dateWheel.setCurrentItem(1);
        } else {
            dateWheel.setCurrentItem(2);
        }

        hourWheel.setCurrentItem(hour);

        if (min == 0) {
            minWheel.setCurrentItem(0);
        } else if (min == 15) {
            minWheel.setCurrentItem(1);
        } else if (min == 30) {
            minWheel.setCurrentItem(2);
        } else {
            minWheel.setCurrentItem(3);
        }
    }
}
