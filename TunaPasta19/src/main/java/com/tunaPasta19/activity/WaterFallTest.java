package com.tunaPasta19.activity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.tunaPasta19.R;
import com.tunaPasta19.entity.WaterFallTestFlowTag;
import com.tunaPasta19.widget.WaterFallTestFlowImageView;
import com.tunaPasta19.widget.WaterFallTestScrollView;
import com.tunaPasta19.widget.WaterFallTestScrollView.OnScrollListener;

public class WaterFallTest extends Activity {
	private WaterFallTestScrollView waterfall_scroll;				//卷轴视图 
	private int scroll_height;										//卷轴视图高度 
	private AssetManager asset_manager;					//资源管理器
	private ArrayList<LinearLayout> waterfall_layout=new ArrayList<LinearLayout>();	//列布局集合
	private List<String> image_filenames;						//图片名字
	private int column_count = 3;									//列总数
	private int item_width;											//每列的宽
	private int page_count = 20;									//单次加载图片数
	private int current_page = 0;									//当前页数
	private int[] bottomIndex= new int[column_count];	//未回收的屏幕最底端图片索引
	private int[] topIndex= new int[column_count];		//未回收的屏幕最顶端图片索引
	private int[] lineIndex= new int[column_count];		//列容量数组
	private int[] column_height= new int[column_count];	//列高度数组
	private int loaded_count = 0;									//已加载总数
	
	private String assetsFolderName = "waterfalltest";//图片存放的assets文件夹名字,有两个地方要引用到所以提出来
	
	@SuppressWarnings("unchecked")
	private HashMap<Integer, Integer>[] pin_mark= new HashMap[column_count];	//存储当前列对应高度
	
	private Handler handler= new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				WaterFallTestFlowImageView flowimageview = (WaterFallTestFlowImageView) msg.obj;
				int h = msg.arg2;
				int columnIndex = GetMinValue(column_height);							// 计算加载用的列值下标
				flowimageview.columnIndex=columnIndex;
				column_height[columnIndex] += h;
				waterfall_layout.get(columnIndex).addView(flowimageview);			//linearlayout加载对应的imageview
				lineIndex[columnIndex]++;															//列容量数组增加
				pin_mark[columnIndex].put(lineIndex[columnIndex],column_height[columnIndex]);//存储列对应高度
				bottomIndex[columnIndex] = lineIndex[columnIndex];					//未回收的屏幕最底端图片索引
				break;
			}
		}
	};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waterfalltest);
		item_width = this.getWindowManager().getDefaultDisplay().getWidth() / column_count;// 根据屏幕计算列宽
		asset_manager = this.getAssets();
		
		for (int i = 0; i < column_count; i++) {
			lineIndex[i] = -1;
			pin_mark[i] = new HashMap<Integer, Integer>();
		}
		InitLayout();
	}
	
	private void InitLayout() {
		LinearLayout waterfall_container =  findViewById(R.id.waterfall_container);
		//0,1,2,创建三排linearlayout
		for (int i = 0; i < column_count; i++) {
			LinearLayout itemLayout = new LinearLayout(this);
			itemLayout.setPadding(2, 2, 2, 2);
			itemLayout.setOrientation(LinearLayout.VERTICAL);
			itemLayout.setLayoutParams(new LinearLayout.LayoutParams(item_width, LayoutParams.WRAP_CONTENT));
			waterfall_layout.add(itemLayout);
			waterfall_container.addView(itemLayout);
		}
		try {																			// 加载所有图片路径,先转成字符串数组,再转成List
			
			image_filenames = Arrays.asList(asset_manager.list(assetsFolderName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddItemToContainer(current_page, page_count);		// 第一次加载,从第0页开始加载20张图片
		
		waterfall_scroll =  findViewById(R.id.waterfall_scroll);
		waterfall_scroll.getView();
		waterfall_scroll.onScrollListener=new OnScrollListener() {
			public void onBottom() {
				AddItemToContainer(++current_page, page_count);	// 滚动到最低端
			}
			
			public void onAutoScroll(int l, int t, int oldl, int oldt) {
				scroll_height = waterfall_scroll.getMeasuredHeight();					//永远都是屏幕长度854
				if (t > oldt) {																				//向下滚动
					if (t > 2 * scroll_height) {														//超过两屏幕后执行代码
						for (int k = 0; k < column_count; k++) {								//三列图片一起回收
							LinearLayout localLinearLayout = waterfall_layout.get(k);
							if (pin_mark[k].get(topIndex[k]) < t - 2* scroll_height) {	// 未回收图片的最高位置<t-两倍屏幕高度
								((WaterFallTestFlowImageView) localLinearLayout.getChildAt(topIndex[k])).recycle();
								topIndex[k]++;
							}
		//可以不采用	Math.min(bottomIndex[k] + 1,lineIndex[k]),但必须保证pin_mark[k].get(Math.min(bottomIndex[k] + 1)!=null
		//且bottomIndex[k] = bottomIndex[k]+1;
							if (pin_mark[k].get(Math.min(bottomIndex[k] + 1,lineIndex[k])) <= t + 3 * scroll_height) {// 最底部的图片位置小于当前t+3*屏幕高度
								((WaterFallTestFlowImageView) waterfall_layout.get(k).getChildAt(Math.min(bottomIndex[k]+1,lineIndex[k]))).Reload();
								bottomIndex[k] = Math.min(bottomIndex[k]+1,lineIndex[k]);
							}
						}
					}
				} else {																						// 向上滚动
					for (int k = 0; k < column_count; k++) {
						LinearLayout localLinearLayout = waterfall_layout.get(k);
						if (pin_mark[k].get(bottomIndex[k]) > t + 3* scroll_height) {
							((WaterFallTestFlowImageView) localLinearLayout.getChildAt(bottomIndex[k])).recycle();
							bottomIndex[k]--;
						}
						if (pin_mark[k].get(Math.max(topIndex[k] - 1, 0)) >= t- 2 * scroll_height) {
							((WaterFallTestFlowImageView) localLinearLayout.getChildAt(Math.max(topIndex[k]-1, 0))).Reload();
							topIndex[k] = Math.max(topIndex[k] - 1, 0);
						}
					}
				}
			}
		};
		
	}
	private void AddItemToContainer(int pageindex, int pagecount) {
		int currentIndex = pageindex * pagecount;					//当前索引值,等于当前页数*一次加载的总数
		int imagecount = 10000;												// 如果为image_filenames.size()即为图片总数
		for (int i = currentIndex; i < pagecount * (pageindex + 1)&& i < imagecount; i++) {
			loaded_count++;
			int randomfilename = new Random().nextInt(image_filenames.size());
			AddImage(image_filenames.get(randomfilename),(int) Math.ceil(loaded_count / (double) column_count),loaded_count);	//返回ceil天花板,来确定第几行
		}
	}
	private void AddImage(String filename, int rowIndex, int id) {
		WaterFallTestFlowImageView item = new WaterFallTestFlowImageView(this);
		item.rowIndex=rowIndex;
		item.setId(id);
		item.viewHandler=handler;
		// 多线程参数
		WaterFallTestFlowTag flowtag = new WaterFallTestFlowTag();
		flowtag.flowId=id;
		flowtag.assetManager=asset_manager;
		flowtag.fileName=assetsFolderName+"/" + filename;
		flowtag.ItemWidth=item_width;
		item.flowTag=flowtag;
		item.LoadImage();
	}
	//返回的是0或1或2
	private int GetMinValue(int[] array) {
		int m = 0;
		int length = array.length;
		for (int i = 0; i < length; ++i) {
			if (array[i] < array[m]) {
				m = i;
			}
		}
		return m;
	}
}
