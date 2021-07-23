package com.tunaPasta14.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.tunaPasta14.R;
import com.tunaPasta14.framgent.Hotel_distance;
import com.tunaPasta14.framgent.Hotel_price;
import com.tunaPasta14.framgent.Hotel_sort;
import com.tunaPasta14.framgent.Hotel_start;
import com.tunaPasta14.framgent.Index_Fragment;
import com.tunaPasta14.util.DummyTabContent;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HotelTab extends FragmentActivity {

	TabHost tabHost;
	TabWidget tabWidget; 
	LinearLayout bottom_layout;
	int CURRENT_TAB = 0;	//设置常量
	Hotel_sort homeFragment;
	Hotel_price wallFragment;
	Hotel_start messageFragment;
	Hotel_distance meFragment;
	Index_Fragment index_Fragment;
	FragmentTransaction ft;
	LinearLayout tabIndicator1,tabIndicator2,tabIndicator3,tabIndicator4,tabIndicator5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hoteltest);
        
        WindowManager.LayoutParams  layoutParams		=getWindow().getAttributes();
        layoutParams.gravity			=Gravity.BOTTOM;
        layoutParams.width				=WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height				=WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        
        
        findTabView();
        tabHost.setup();
        /** 监听*/
        TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener(){
			@Override
			public void onTabChanged(String tabId) {
				
				/**碎片管理*/
				FragmentManager fm =  getSupportFragmentManager();
				homeFragment = (Hotel_sort) fm.findFragmentByTag("home");
				wallFragment = (Hotel_price) fm.findFragmentByTag("wall");
				messageFragment = (Hotel_start) fm.findFragmentByTag("message");
				meFragment = (Hotel_distance) fm.findFragmentByTag("me");
				index_Fragment	=(Index_Fragment) fm.findFragmentByTag("index");
				ft = fm.beginTransaction();
				
				/** 如果存在Detaches掉 */
				if(homeFragment!=null)
					ft.hide(homeFragment);
//					ft.detach(homeFragment);
				
				/** 如果存在Detaches掉 */
				if(wallFragment!=null)
//					ft.detach(wallFragment);
					ft.hide(wallFragment);
				
				/** 如果存在Detaches掉 */
				if(messageFragment!=null)
//					ft.detach(messageFragment);
					ft.hide(messageFragment);
				
				/** 如果存在Detaches掉 */
				if(meFragment!=null)
//					ft.detach(meFragment);
					ft.hide(meFragment);
				
				if(index_Fragment!=null)
//					ft.detach(index_Fragment);
					ft.hide(index_Fragment);
				
				/** 如果当前选项卡是home */
				if(tabId.equalsIgnoreCase("home")){
					isTabHome();
					CURRENT_TAB = 1;
					
				/** 如果当前选项卡是wall */
				}else if(tabId.equalsIgnoreCase("wall")){	
					isTabWall();
					CURRENT_TAB = 2;
					
				/** 如果当前选项卡是message */
				}else if(tabId.equalsIgnoreCase("index")){
					isTabIndex();
					CURRENT_TAB	=	5;
				}else if(tabId.equalsIgnoreCase("message")){	
					isTabMessage();
					CURRENT_TAB = 3;
					
				/** 如果当前选项卡是me */
				}else if(tabId.equalsIgnoreCase("me")){	
					isTabMe();
					CURRENT_TAB = 4;
				}else{
					switch (CURRENT_TAB) {
					case 1:
						isTabHome();
						break;
					case 2:
						isTabWall();
						break;
					case 3:
						isTabMessage();
						break;
					case 4:
						isTabMe();
						break;
					case 5:
						isTabIndex();
						break;
//					default:
//						isTabHome();
//						break;
					}		
					
				}
					ft.commit();	
			}
        	
        };
        tabHost.setOnTabChangedListener(tabChangeListener);
        /**  设置初始化界面  */
        int tag		=getIntent().getIntExtra("tag", 0);
        
        
//        int tadid = 3;  
        
     // 设置mCurrentTab为非-1,addtab时候不会进入setCurrentTab()  
//        try {  
//            Field idcurrent = tabHost.getClass().getDeclaredField("mCurrentTab");  
//            idcurrent.setAccessible(true);  
//            idcurrent.setInt(tabHost, -2);  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        
        initTab();
        
     // 设置mCurrentTab与tadid不同，并且不能数组越界(0-2)，保证第一次进入tab的setCurrentTab()方法正常运行 
//        try {  
//            Field idcurrent = tabHost.getClass().getDeclaredField("mCurrentTab");  
//            idcurrent.setAccessible(true);  
//            if (tag == 0) {  
//                idcurrent.setInt(tabHost, 1);  
//            } else {  
//                idcurrent.setInt(tabHost,0);  
//            }  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
        tabHost.setCurrentTab(0);
        tabHost.setCurrentTab(tag);
    }
    
    //判断当前
    public void isTabHome(){
    	
    	if(homeFragment==null){		
			ft.add(R.id.realtabcontent,new Hotel_sort(), "home");
		}else{
//			ft.attach(homeFragment);	
			ft.show(homeFragment);
		}
    }
    
    public void isTabWall(){
    	
    	if(wallFragment==null){
			ft.add(R.id.realtabcontent,new Hotel_price(), "wall");						
		}else{
//			ft.attach(wallFragment);						
			ft.show(wallFragment);
		}
    }
    
    public void isTabMessage(){
    	
    	if(messageFragment==null){
			ft.add(R.id.realtabcontent,new Hotel_start(), "message");						
		}else{
//			ft.attach(messageFragment);						
			ft.show(messageFragment);
		}
    }
    
    public void isTabMe(){
    	
    	if(meFragment==null){
			ft.add(R.id.realtabcontent,new Hotel_distance(), "me");						
		}else{
//			ft.attach(meFragment);	
			ft.show(meFragment);
		}
    }
    public void isTabIndex(){
    	if(index_Fragment==null){
			ft.add(R.id.realtabcontent,new Index_Fragment(), "index");						
		}else{
//			ft.attach(index_Fragment);	
			ft.show(index_Fragment);
		}
    }
    /**
     * 找到Tabhost布局
     */
    public void findTabView(){
    	
    	 tabHost = (TabHost) findViewById(android.R.id.tabhost);
         tabWidget = (TabWidget) findViewById(android.R.id.tabs);
         LinearLayout layout = (LinearLayout)tabHost.getChildAt(0);
         TabWidget tw = (TabWidget)layout.getChildAt(1);
         
         tabIndicator1 = (LinearLayout) LayoutInflater.from(this)
         		.inflate(R.layout.hotel_tab_indicator, tw, false);
         TextView tvTab1 = (TextView)tabIndicator1.getChildAt(1);
         ImageView ivTab1 = (ImageView)tabIndicator1.getChildAt(0);
         ivTab1.setBackgroundResource(R.drawable.hotel_filter_recommends_normal);
         tvTab1.setText("距离排序");
         
         tabIndicator2 = (LinearLayout) LayoutInflater.from(this)
         		.inflate(R.layout.hotel_tab_indicator, tw, false);
         TextView tvTab2 = (TextView)tabIndicator2.getChildAt(1);
         ImageView ivTab2 = (ImageView)tabIndicator2.getChildAt(0);
         ivTab2.setBackgroundResource(R.drawable.flight_price_sort_disable);
         tvTab2.setText("赛选价格");
         
         tabIndicator4 = (LinearLayout) LayoutInflater.from(this)
         		.inflate(R.layout.hotel_tab_indicator, tw, false);
         TextView tvTab4 = (TextView)tabIndicator4.getChildAt(1);
         ImageView ivTab4 = (ImageView)tabIndicator4.getChildAt(0);
         ivTab4.setBackgroundResource(R.drawable.hotel_filter_level_normal);
         tvTab4.setText("赛选星级");
         
         tabIndicator5 = (LinearLayout) LayoutInflater.from(this)
         		.inflate(R.layout.hotel_tab_indicator, tw, false);
         TextView tvTab5 = (TextView)tabIndicator5.getChildAt(1);
         ImageView ivTab5 = (ImageView)tabIndicator5.getChildAt(0);
         ivTab5.setBackgroundResource(R.drawable.hotel_filter_distance_normal);
         tvTab5.setText("10KM内");
    }
    
    /** 
     * 初始化选项卡
     * 
     * */
    public void initTab(){
    	
        TabHost.TabSpec tSpecHome = tabHost.newTabSpec("home");
        tSpecHome.setIndicator(tabIndicator1);        
        tSpecHome.setContent(new DummyTabContent(getBaseContext()));
        tabHost.addTab(tSpecHome);
        
        TabHost.TabSpec tSpecWall = tabHost.newTabSpec("wall");
        tSpecWall.setIndicator(tabIndicator2);        
        tSpecWall.setContent(new DummyTabContent(getBaseContext()));
        tabHost.addTab(tSpecWall);
        
        TabHost.TabSpec tSpecMessage = tabHost.newTabSpec("message");
        tSpecMessage.setIndicator(tabIndicator4);      
        tSpecMessage.setContent(new DummyTabContent(getBaseContext()));
        tabHost.addTab(tSpecMessage);
        
        TabHost.TabSpec tSpecMe = tabHost.newTabSpec("me");
        tSpecMe.setIndicator(tabIndicator5);        
        tSpecMe.setContent(new DummyTabContent(getBaseContext()));
        tabHost.addTab(tSpecMe);
        
    }
    
}
