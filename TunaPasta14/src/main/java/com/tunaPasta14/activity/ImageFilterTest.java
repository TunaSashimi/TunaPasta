package com.tunaPasta14.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.tunaPasta14.R;
import com.tunaPasta14.util.ComicFilter;
import com.tunaPasta14.util.IceFilter;
import com.tunaPasta14.util.ImageCache;
import com.tunaPasta14.util.ImageUtil;
import com.tunaPasta14.util.MoltenFilter;

public class ImageFilterTest extends Activity {

	ImageView imageView1, imageView2;
	Drawable mDrawable;
	Bitmap mBitmap;

	Context mContext;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagefiltertest);
		mContext = this;
		findView();

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		int pos = bundle.getInt("position", 0);
		Bitmap tmpBitmap;
		switch (pos) {
		case 0:
			if (ImageCache.get("IceFilter") != null) {
				tmpBitmap = ImageCache.get("IceFilter");
				imageView2.setImageBitmap(tmpBitmap);
				break;
			}
			tmpBitmap = new IceFilter(mBitmap).imageProcess().getDstBitmap();
			imageView2.setImageBitmap(tmpBitmap);
			ImageCache.put("IceFilter", tmpBitmap);
			break;
		case 1:
			if (ImageCache.get("MoltenFilter") != null) {
				tmpBitmap = ImageCache.get("MoltenFilter");
				imageView2.setImageBitmap(tmpBitmap);
				break;
			}
			tmpBitmap = new MoltenFilter(mBitmap).imageProcess().getDstBitmap();
			imageView2.setImageBitmap(tmpBitmap);
			ImageCache.put("MoltenFilter", tmpBitmap);
			break;
		case 2:
			if (ImageCache.get("ComicFilter") != null) {
				tmpBitmap = ImageCache.get("ComicFilter");
				imageView2.setImageBitmap(tmpBitmap);
				break;
			}
			tmpBitmap = new ComicFilter(mBitmap).imageProcess().getDstBitmap();
			imageView2.setImageBitmap(tmpBitmap);
			ImageCache.put("ComicFilter", tmpBitmap);
			break;
		default:
			imageView2.setImageBitmap(mBitmap);
			break;
		}
	}

	private void findView() {
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		mDrawable = imageView1.getDrawable();
		mBitmap = ImageUtil.readBitMap(mContext, R.drawable.imagefilter);
		imageView2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				imageView1.setVisibility(View.GONE);
			}
		});
	}
}
