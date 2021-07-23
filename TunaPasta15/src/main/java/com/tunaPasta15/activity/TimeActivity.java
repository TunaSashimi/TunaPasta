package com.tunaPasta15.activity;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TimePicker;

import com.tunaPasta15.R;
import com.tunaPasta15.adapter.NumericWheelAdapter;
import com.tunaPasta15.widget.OnWheelChangedListener;
import com.tunaPasta15.widget.OnWheelClickedListener;
import com.tunaPasta15.widget.OnWheelScrollListener;
import com.tunaPasta15.widget.WheelView;

public class TimeActivity extends Activity {
	// Time changed flag
	private boolean timeChanged = false;

	// Time scrolled flag
	private boolean timeScrolled = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.time_layout);

		final WheelView hours = (WheelView) findViewById(R.id.hour);
		hours.setViewAdapter(new NumericWheelAdapter(this, 0, 23));

		final WheelView mins = (WheelView) findViewById(R.id.mins);
		mins.setViewAdapter(new NumericWheelAdapter(this, 0, 59, "%02d"));
		mins.setCyclic(true);

		final TimePicker picker = (TimePicker) findViewById(R.id.time);
		picker.setIs24HourView(true);

		// set current time
		Calendar c = Calendar.getInstance();
		int curHours = c.get(Calendar.HOUR_OF_DAY);
		int curMinutes = c.get(Calendar.MINUTE);

		hours.setCurrentItem(curHours);
		mins.setCurrentItem(curMinutes);

		picker.setCurrentHour(curHours);
		picker.setCurrentMinute(curMinutes);

		// add listeners
		addChangingListener(mins, "min");
		addChangingListener(hours, "hour");

		OnWheelChangedListener wheelListener = new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (!timeScrolled) {
					timeChanged = true;
					picker.setCurrentHour(hours.getCurrentItem());
					picker.setCurrentMinute(mins.getCurrentItem());
					timeChanged = false;
				}
			}
		};
		hours.addChangingListener(wheelListener);
		mins.addChangingListener(wheelListener);

		OnWheelClickedListener click = new OnWheelClickedListener() {
			@Override
			public void onItemClicked(WheelView wheel, int itemIndex) {
				wheel.setCurrentItem(itemIndex, true);
			}
		};
		hours.addClickingListener(click);
		mins.addClickingListener(click);

		OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {
				timeScrolled = true;
			}
			@Override
			public void onScrollingFinished(WheelView wheel) {
				timeScrolled = false;
				timeChanged = true;
				picker.setCurrentHour(hours.getCurrentItem());
				picker.setCurrentMinute(mins.getCurrentItem());
				timeChanged = false;
			}
		};

		hours.addScrollingListener(scrollListener);
		mins.addScrollingListener(scrollListener);

		picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker  view, int hourOfDay, int minute) {
				if (!timeChanged) {
					hours.setCurrentItem(hourOfDay, true);
					mins.setCurrentItem(minute, true);
				}
			}
		});
	}

	/**
	 * Adds changing listener for wheel that updates the wheel label
	 * @param wheel the wheel
	 * @param label the wheel label
	 */
	private void addChangingListener(final WheelView wheel, final String label) {
		wheel.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				//wheel.setLabel(newValue != 1 ? label + "s" : label);
			}
		});
	}
}
