package com.tunaPasta06.tool;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.BaseAdapter;

import com.tunaPasta06.R;
import com.tunaPasta06.adapter.ImproveListViewTestAdapter.ViewHolder;

public class ImageLoader {
	private static final String TAG = "ImageLoader";
	private static final int MAX_CAPACITY = 10;
	private static final long DELAY_BEFORE_PURGE = 10 * 1000;
	private ConcurrentHashMap<String, SoftReference<Bitmap>> mSoftCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>(
			MAX_CAPACITY / 2);;
	private HashMap<String, Bitmap> mHardCache = new LinkedHashMap<String, Bitmap>(
			MAX_CAPACITY / 2, 0.75f, true) {
		private static final long serialVersionUID = 1L;

		protected boolean removeEldestEntry(Entry<String, Bitmap> eldest) {
			if (size() > MAX_CAPACITY) {
				mSoftCache.put(eldest.getKey(), new SoftReference<Bitmap>(
						eldest.getValue()));
				return true;
			}
			return false;
		};
	};
	private Runnable mClearCache = new Runnable() {
		@Override
		public void run() {
			clear();
		}
	};
	private Handler mPurgeHandler = new Handler();

	private void resetPurgeTimer() {
		mPurgeHandler.removeCallbacks(mClearCache);
		mPurgeHandler.postDelayed(mClearCache, DELAY_BEFORE_PURGE);
	}

	/**
	 * 返回缓存，如果没有则返回null
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap getBitmapFromCache(String url) {
		Bitmap bitmap = null;
		synchronized (mHardCache) {
			bitmap = mHardCache.get(url);
			if (bitmap != null) {
				mHardCache.remove(url);
				mHardCache.put(url, bitmap);
				return bitmap;
			}
		}

		SoftReference<Bitmap> softReference = mSoftCache.get(url);
		if (softReference != null) {
			bitmap = softReference.get();
			if (bitmap == null) {// 已经被gc回收了
				mSoftCache.remove(url);
			}
		}
		return bitmap;
	}

	
	public void loadImage(String url, BaseAdapter adapter, ViewHolder holder) {
		resetPurgeTimer();
		Bitmap bitmap = getBitmapFromCache(url);// 从缓存中读取
		if (bitmap == null) {
			holder.mImageView.setImageResource(R.drawable.icon);
			ImageLoadTask imageLoadTask = new ImageLoadTask();
			imageLoadTask.execute(url, adapter, holder);
		} else {
			holder.mImageView.setImageBitmap(bitmap);
		}

	}

	public void addImage2Cache(String url, Bitmap value) {
		if (value == null || url == null) {
			return;
		}
		synchronized (mHardCache) {
			mHardCache.put(url, value);
		}
	}

	class ImageLoadTask extends AsyncTask<Object, Void, Bitmap> {
		String url;
		BaseAdapter adapter;

		@Override
		protected Bitmap doInBackground(Object... params) {
			url = (String) params[0];
			adapter = (BaseAdapter) params[1];
			Bitmap drawable = loadImageFromInternet(url);// 获取网络图片
			return drawable;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result == null) {
				return;
			}
			addImage2Cache(url, result);// 放入缓存
			adapter.notifyDataSetChanged();
		}
	}

	public Bitmap loadImageFromInternet(String url) {
		Bitmap bitmap = null;
//		HttpClient client = AndroidHttpClient.newInstance("Android");
//		HttpParams params = client.getParams();
//		HttpConnectionParams.setConnectionTimeout(params, 3000);
//		HttpConnectionParams.setSocketBufferSize(params, 3000);
		HttpResponse response = null;
		InputStream inputStream = null;
//		HttpGet httpGet = null;
		try {
//			httpGet = new HttpGet(url);
//			response = client.execute(httpGet);
			int stateCode = response.getStatusLine().getStatusCode();
			if (stateCode != HttpStatus.SC_OK) {
				Log.d(TAG, "func [loadImage] stateCode=" + stateCode);
				return bitmap;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try {
					inputStream = entity.getContent();
					return bitmap = BitmapFactory.decodeStream(inputStream);
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
		} catch (Exception e) {
//			httpGet.abort();
			e.printStackTrace();
		}  finally {
//			((AndroidHttpClient) client).close();
		}
		return bitmap;
	}

	private void clear() {
		mHardCache.clear();
		mSoftCache.clear();
	}
}
