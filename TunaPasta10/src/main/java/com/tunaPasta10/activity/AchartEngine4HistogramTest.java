package com.tunaPasta10.activity;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

//AchartEngineActivityAChartEngine（简称ACE）是Google的一个开源图表库（for Android）。
//它功能强大，支持散点图、折线图、饼图、气泡图、柱状图、短棒图、仪表图等多种图表。
//该项目地址位于： http://code.google.com/p/achartengine/。
//	关于里面类的具体使用，请查看工程下tips关于info.txt文档说明

public class AchartEngine4HistogramTest extends Activity {

	private static final int SERIES_NR = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		getBarChartIntent 方法有点复杂，需要我们准备很多参数传入。
//		其中一个是 XYMultipleSeriesDataset 类型的对象，用于提供图表需要表示的数据集，
//		这里我们用 　   getBarDemoDataset 来得到它。
//		另外一个是 XYMultipleSeriesRenderer 类型的对象，用于提供图表展现时的一些样式，
//		这里我们用 getBarDemoRenderer 方法来得到它。
		
		View histogram = ChartFactory.getBarChartView(this,getBarDemoDataset(), getBarDemoRenderer(), Type.DEFAULT);
		
		setContentView(histogram);
	}


//getLineChartIntent 方法很无聊，使用了一些随机数来作为图表数据。
//	注意柱状图是支持多系列的，这里生成了两个系列的数据。
	
	private XYMultipleSeriesDataset getBarDemoDataset() {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		final int nr = 10;
		Random r = new Random();
		for (int i = 0; i < SERIES_NR; i++) {
			CategorySeries series = new CategorySeries("Demo series " + (i + 1));
			for (int k = 0; k < nr; k++) {
				series.add(100 + r.nextInt() % 100);
			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}

	public XYMultipleSeriesRenderer getBarDemoRenderer() {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r);
		r = new SimpleSeriesRenderer();
		r.setColor(Color.GREEN);
		renderer.addSeriesRenderer(r);
		setChartSettings(renderer);
		return renderer;
	}

//	getBarDemoRenderer 方法构建了一个 XYMultipleSeriesRenderer 用来设置2个系列各自的颜色，
//	然后调用 setChartSettings 方法设置了下坐标轴样式。
	
	private void setChartSettings(XYMultipleSeriesRenderer renderer) {
		renderer.setChartTitle("Chart demo");
		renderer.setXTitle("x values");
		renderer.setYTitle("y values");
		renderer.setXAxisMin(0.5);
		renderer.setXAxisMax(10.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(210);
	}
}