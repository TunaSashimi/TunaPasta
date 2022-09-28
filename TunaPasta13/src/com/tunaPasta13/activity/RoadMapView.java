package com.tunaPasta13.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class RoadMapView extends View {

	private String[] nodeName = {
			"AB宿舍","CD宿舍","菜市场/网络中心","第二/三食堂"," DG座教室",
			"大礼堂","东门","第四食堂","EF宿舍","E座教室",
			"附中附小","G座宿舍","荷花池","互助学习中心","教师公寓",
			"旧行政楼","科学楼","路口1","路口2","路口3",
			"路口4","路口5","路口6","路口7","路口8",
			"路口9","路口10","路口11","路口12","路口13",
			"路口14","理科楼","留学生宿舍","溜冰场",
			"篮球场","789号楼","路口15","路口16","体育馆",
			"田径场","网球场","校门","学术交流中心","校训碑",
			"新行政中心","校医院","邮局","研究生宿舍","艺术教育中心",
			"艺术学院","游泳池","至诚书院","图书馆","水库"
			};
	private int[] nodeX = {
			389,442,740,527,937,
			760,1194,1085,319,974,
			627,1089,670,779,640,
			812,627,255,208,297,
			384,489,520,1141,918,
			900,803,910,1030,1044, 
			1202,761,1172,494,493,
			359,261,871,313,350,
			529,923,123,847,606,
			292,795,466,277,160,
			247,1141,975,231
			};
	private int[] nodeY = {
			182,188,534,174,103,
			260,192,134,162,250,
			640,79,338,484,405,
			201,134,102,303,247,
			346,199,386,184,72,
			197,425,435,71,97,
			111,74,71,349,290,
			357,166,309,177,300,
			257,483,64,273,217,
			393,453,14,436,86,
			310,136,335,151};
	private String[] nodeName1 = {
			"AB宿舍","CD宿舍","菜市场/网络中心","第二/三食堂"," DG座教室",
			"大礼堂","东门","第四食堂","EF宿舍","E座教室",
			"附中附小","G座宿舍","荷花池","互助学习中心","教师公寓",
			"旧行政楼","科学楼","路口1","路口2","路口3",
			"路口4","路口5","路口6","路口7","路口8",
			"路口9","路口10","路口11","路口12","路口13",
			"路口14","理科楼","留学生宿舍","溜冰场",
			"篮球场","789号楼","路口15","路口16","体育馆",
			"田径场","网球场","校门","学术交流中心","校训碑",
			"新行政中心","校医院","邮局","研究生宿舍","艺术教育中心",
			"艺术学院","游泳池","至诚书院","图书馆","水库"
			}; // 各地点的名字
	private int[] nodeX1 = {
			389,442,740,527,937,
			760,1194,1085,319,974,
			627,1089,670,779,640,
			812,627,255,208,384,
			489,520,1141,918,900,
			803,910,1030,1044,1202,
			761,1172,359,261,871,
			313,923,123,847,606,
			292,795,466,277,160,
			247,1141,975
			};
	private int[] nodeY1 = {
			182,188,534,174,103,
			260,192,134,162,250,
			640,79,338,484,405,
			201,134,102,303,346,
			199,386,184,72,197,
			425,435,71,97,111,
			74,71,357,166,309,
			177,483,64,273,217,
			393,453,14,436,86,
			310,136,335
			};

	private String strPath;
	private List<Integer> nodeId;
	private String choice;//选择自行车或步行

	public RoadMapView(Context context,String choice) {
		super(context);
		this.choice=choice;
	}

	public RoadMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RoadMapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		String[] strName = strPath.split("-");
		nodeId = new ArrayList<Integer>(); // new！！！
		for (int j = 0; j < strName.length; j++) { // 注意循环的顺序
			for (int i = 0; i < nodeName.length; i++)
				if (nodeName[i].equals(strName[j]))
					nodeId.add(i);
		}

		// Paint ppaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// ppaint.setColor(Color.GRAY);
		// ppaint.setStrokeWidth(4);
		Paint lpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		lpaint.setStyle(Paint.Style.STROKE);
		lpaint.setColor(Color.RED);
		lpaint.setStrokeWidth(8);
		// lpaint.setARGB(100, 228, 228, 231);
		lpaint.setStrokeWidth(2);

		int n = 0;
		for (int i = 0; i < nodeId.size() - 1; i++) {
			int x1 = nodeX[nodeId.get(i)], y1 = nodeY[nodeId.get(i)], x2 = nodeX[nodeId.get(i + 1)], y2 = nodeY[nodeId
					.get(i + 1)];
			canvas.drawLine(x1, y1, x2, y2, lpaint);
			n += Math.round(getDistance(nodeX[nodeId.get(i)], nodeY[nodeId.get(i)], nodeX[nodeId.get(i + 1)],
					nodeY[nodeId.get(i + 1)]));

		}
		System.out.println("---------:" + n);
		
		Paint dotpaint = new Paint();
		dotpaint.setAntiAlias(true);
		dotpaint.setColor(Color.BLUE);
		dotpaint.setStyle(Style.FILL);
		dotpaint.setStrokeWidth(3);
		for(int i= 0;i<nodeX.length;i++){
			canvas.drawCircle(nodeX[i], nodeY[i], 5, dotpaint);
		}
		canvas.drawCircle(2, 2, 2, dotpaint);

		//canvas.drawPoint(860, 510, dotpaint);
		// canvas.drawPoint(725, 320, ppaint);
		//
		// canvas.drawLine(860, 510, 725, 320, lpaint);
		// canvas.drawPoint(790, 575, ppaint);
		// canvas.drawPoint(705, 325, ppaint);
		// canvas.drawPoint(390, 170, ppaint);
		// canvas.drawPoint(770, 170, ppaint);
		// canvas.drawPoint(120, 140, ppaint);
		// canvas.drawPoint(285, 170, ppaint);
		// canvas.drawPoint(812, 337, ppaint);
		// canvas.drawPoint(890, 350, ppaint);
		// canvas.drawPoint(963, 480, ppaint);
		// canvas.drawLine(790, 575, 705, 325, lpaint);
		// canvas.drawLine(390, 170, 705, 325, lpaint);
		// canvas.drawLine(770, 170, 705, 325, lpaint);
		// canvas.drawLine(812, 337, 705, 325, lpaint);
		// canvas.drawLine(890, 350, 812, 337, lpaint);
		// canvas.drawLine(963, 480, 890, 350, lpaint);
	}

	public float getDistance(int x1, int y1, int x2, int y2) {
		return (float) (Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2))));
	}

	public void setPath(String strPath) {
		this.strPath = strPath;
	}
}
