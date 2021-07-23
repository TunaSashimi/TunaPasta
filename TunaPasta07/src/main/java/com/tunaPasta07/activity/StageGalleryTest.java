package com.tunaPasta07.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.tunaPasta07.R;
import com.tunaPasta07.adapter.StageImageAdapter;
import com.tunaPasta07.widget.StageGallery;

public class StageGalleryTest extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.stagegallerytest);

		Integer[] images = { R.drawable.item1, R.drawable.item2,
				R.drawable.item3, R.drawable.item4, R.drawable.item5,
				R.drawable.item6, R.drawable.item7, R.drawable.item8,
				R.drawable.item9, R.drawable.item10, R.drawable.item11,
				R.drawable.item12, R.drawable.item13, R.drawable.item14,
				R.drawable.item15, };
		
		StageImageAdapter stageimageAdapter = new StageImageAdapter(this, images);
		stageimageAdapter.createReflectedImages();// 创建倒影效果

		StageGallery galleryFlow =  this.findViewById(R.id.Gallery01);

		galleryFlow.setFadingEdgeLength(0);
		galleryFlow.setSpacing(10); // 图片之间的间距
		galleryFlow.setAdapter(stageimageAdapter);

		galleryFlow.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Toast.makeText(getApplicationContext(),String.valueOf(position), Toast.LENGTH_SHORT).show();
			}
		});
		
		galleryFlow.setSelection(200);
	}

}