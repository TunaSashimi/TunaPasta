package com.tunaPasta17.calendar;

public interface ConfirmSelectDateCallback {
    void selectSingleDate(DayTimeEntity timeEntity);

    void selectMultDate(DayTimeEntity startTimeEntity, DayTimeEntity endTimeEntity);
}
