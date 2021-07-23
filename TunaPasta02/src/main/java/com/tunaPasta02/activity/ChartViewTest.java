package com.tunaPasta02.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta02.R;
import com.tunaPasta02.adapter.ValueLabelAdapter;
import com.tunaPasta02.adapter.ValueLabelAdapter.LabelOrientation;
import com.tunaPasta02.widget.ChartView;
import com.tunaPasta02.widget.LinearSeries;
import com.tunaPasta02.widget.LinearSeries.LinearPoint;

public class ChartViewTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chartviewtest);

		// Find the chart view
		ChartView chartView =  findViewById(R.id.chart_view);

		// Create the data points
		LinearSeries series = new LinearSeries();
		series.setLineColor(0xFF0099CC);
		series.setLineWidth(2);

		for (double i = 0d; i <= (2d * Math.PI); i += 0.1d) {
			series.addPoint(new LinearPoint(i, Math.sin(i)));
		}

		// Add chart view data
		chartView.addSeries(series);
		chartView.setLeftLabelAdapter(new ValueLabelAdapter(this, LabelOrientation.VERTICAL));
		chartView.setBottomLabelAdapter(new ValueLabelAdapter(this, LabelOrientation.HORIZONTAL));
	}
}
