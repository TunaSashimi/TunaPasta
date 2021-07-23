package com.tunaPasta06.adapter;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.tunaPasta06.R;
import com.tunaPasta06.activity.GalleryAutoChangeTest;

public class GalleryAutoChangeTestAdapter extends BaseAdapter{  
    private List<String> imageUrls;       //图片地址list  
    private Context context;  
    private GalleryAutoChangeTestAdapter self;
    public GalleryAutoChangeTestAdapter(List<String> imageUrls, Context context) {  
        this.imageUrls = imageUrls;  
        this.context = context;  
        this.self = this;
    }  
  
    public int getCount() {  
        return Integer.MAX_VALUE;  
    }  
  
    public Object getItem(int position) {  
        return imageUrls.get(position % imageUrls.size());  
    }  
   
    public long getItemId(int position) {  
        return position;  
    }  
  
    private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				switch (msg.what) {
					case 0: {
						self.notifyDataSetChanged();
					}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e) {
			}
		}
	};
    
    public View getView(int position, View convertView, ViewGroup parent) {  
  
        Bitmap image;  
        if(convertView==null){  
            convertView = LayoutInflater.from(context).inflate(R.layout.galleryautochangetestitem,null); //实例化convertView
            Gallery.LayoutParams params = new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT,Gallery.LayoutParams.MATCH_PARENT);
            convertView.setLayoutParams(params);
            image = ((GalleryAutoChangeTest)context).imagesCache.get(imageUrls.get(position % imageUrls.size())); //从缓存中读取图片  
            if(image==null){  
                //当缓存中没有要使用的图片时，先显示默认的图片  
                image = ((GalleryAutoChangeTest)context).imagesCache.get("background_non_load");  
                //异步加载图片  
                LoadImageTask task = new LoadImageTask(convertView);  
                task.execute(imageUrls.get(position % imageUrls.size()));  
            }  
            convertView.setTag(image);  
  
        }  
       else{  
            image = (Bitmap) convertView.getTag();  
        }  
        TextView textView = (TextView) convertView.findViewById(R.id.gallery_text);  
        textView.setText(String.valueOf(position % imageUrls.size()));
        textView.setBackgroundColor(Color.argb(200, 135, 135, 152));
        ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_image);  
        imageView.setImageBitmap(image);  
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); 
        ((GalleryAutoChangeTest)context).changePointView(position % imageUrls.size());
        return convertView;  
    }  
  
    //加载图片的异步任务  
    class LoadImageTask extends AsyncTask<String,Void,Bitmap>{  
         private View resultView;  
  
        LoadImageTask(View resultView) {  
            this.resultView = resultView;  
        }  
         // doInBackground完成后才会被调用  
        @Override  
            protected void onPostExecute(Bitmap bitmap) {  
                //调用setTag保存图片以便于自动更新图片  
                resultView.setTag(bitmap);  
            }  
            //从网上下载图片  
            @Override  
            protected Bitmap doInBackground(String... params) {  
                Bitmap image=null;  
                   try {  
                       //new URL对象  把网址传入  
                       URL url = new URL(params[0]);  
                       //取得链接  
                       URLConnection conn = url.openConnection();  
                       conn.connect();  
                       //取得返回的InputStream  
                       InputStream is = conn.getInputStream();  
                       //将InputStream变为Bitmap  
                       image = BitmapFactory.decodeStream(is);  
                       is.close();  
                       ((GalleryAutoChangeTest)context).imagesCache.put(params[0],image);   //把下载好的图片保存到缓存中
                       	Message m = new Message();
           				m.what = 0;
           				mHandler.sendMessage(m);
                   } catch (Exception e) {  
                       e.printStackTrace();  
                   }  
  
                return image;  
            }  
    }  
}  
