package com.tunaPasta14.activity;

import java.lang.reflect.Field;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.tunaPasta14.R;

public class HideDatePickerDateTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hidedatepickerdatetest);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button01:
                DatePickerDialog datePicker = new
                        DatePickerDialog(HideDatePickerDateTest.this, null, 0, 0, 0);
                datePicker.show();
                break;
            case R.id.button02:
                DatePicker datePickerHide = new DatePicker(HideDatePickerDateTest.this);
                datePickerHide.setCalendarViewShown(false);

                // 通过反射机制，访问private的mDaySpinner成员，并隐藏它
                try {
                    Field daySpinner = datePickerHide.getClass().getDeclaredField("mDaySpinner");
                    daySpinner.setAccessible(true);
                    ((View) daySpinner.get(datePickerHide)).setVisibility(View.GONE);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                Calendar minCalendar = Calendar.getInstance();
                minCalendar.set(Calendar.HOUR_OF_DAY, 0);
                minCalendar.set(Calendar.MINUTE, 0);
                minCalendar.set(Calendar.SECOND, 0);

                Calendar maxCalendar = Calendar.getInstance();
                maxCalendar.add(Calendar.YEAR, 1);

                Calendar curCalendar = Calendar.getInstance();

                AlertDialog.Builder builder = new AlertDialog.Builder(HideDatePickerDateTest.this);
                builder.setView(datePickerHide);
                builder.setTitle("请选择有效期");
                builder.setPositiveButton("确定", null);

                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

                break;
            default:
                break;
        }
    }
}
