package com.tunaPasta08.entity;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//需要的：    图片文件，获取长度，填充字段，x轴偏移量，y轴偏移量(截取比率一般去掉,基本上都是1~,身体特例为0.67~)
public class Actor {
	// 来源文件
	protected String origin; 		// JSON数组的来源
	protected String contanct; 	// 使用JSON数组中的哪一个JSON对象
	// 帮助类
	FileItem file;
	LookletArticleMetaData lookletarticle;
	// 绘画所用的参数
	public String png;
	public int size;
	public String fill;
	public int offsetX;
	public int offsetY;
	public Bitmap candrow; // 新增Bitmap属性candrow
	// 构造方法~
	public Actor(String origin, String contanct) {
		this.origin = origin;
		this.contanct = contanct;
		// 在构造方法里面生成一切!
		try {
			JSONArray allfiles = new JSONArray(origin);
			label: for (int i = 0; i < allfiles.length(); i++) {
				JSONObject oj = allfiles.getJSONObject(i);
				// contains 匹配所需要的 contanct ~ 获取json中所需要的json对象~
				if (oj.toString().contains(contanct)) {
					GsonBuilder gsonb = new GsonBuilder();
					Gson gson = gsonb.create();
					file = gson.fromJson(oj.toString(), FileItem.class);
					// lookletarticle字符串中会自动把用于转义的\去掉~
					lookletarticle = gson.fromJson(file.lookletArticleMetaData,LookletArticleMetaData.class);
					// 这里开始获取参数
					png = lookletarticle.variants.get(0).view.front.image;
					size = Integer.parseInt(lookletarticle.variants.get(0).view.front.crop.height);
					offsetX = Integer.parseInt(lookletarticle.variants.get(0).view.front.crop.x);
					offsetY = Integer.parseInt(lookletarticle.variants.get(0).view.front.crop.y);
					fill = "items/";
					// 到此为止,画图所用的五个参数canvas,
					// png,dress,1,"items/",x,y===>dress=(int)(size*ratio)都解析完成了
					// 然后就是制造一系列的继承Actor画出来~
					break label;
				}
			}
		} catch (org.json.JSONException e) {
			e.printStackTrace();
		}
	}
}