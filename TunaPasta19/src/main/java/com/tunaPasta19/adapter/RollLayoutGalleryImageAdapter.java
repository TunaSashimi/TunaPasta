package com.tunaPasta19.adapter;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.tunaPasta19.R;


public class RollLayoutGalleryImageAdapter extends BaseAdapter {
	private Integer[] myPictures = { R.drawable.image_mygallery01,
			R.drawable.image_mygallery02, R.drawable.image_mygallery03,
			R.drawable.image_mygallery04, R.drawable.image_mygallery05,
			R.drawable.image_mygallery06, R.drawable.image_mygallery07
			};
	private Context context;
	public RollLayoutGalleryImageAdapter(Context context) {
		this.context = context;
	}
	public int getCount() {
		return Integer.MAX_VALUE;
	}
	// 获取图片在库中的位置
	public Object getItem(int position) {
		return position;
	}
	public long getItemId(int position) {
		return position;
	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		ImageView imageview;
		if (convertView == null) {
			imageview = new ImageView(context);
			imageview.setLayoutParams(new Gallery.LayoutParams(200, 200));
			imageview.setAdjustViewBounds(false);
			imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageview.setPadding(18, 18, 18, 18);
		} else {
			imageview = (ImageView) convertView;
		}
		imageview.setImageResource(myPictures[position % myPictures.length]);
		imageview.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				switch (position % myPictures.length) {
//				case 0:NativeRequest.generateCheck(context, "formsecuritycheck.dat", "检查写实汇总", P101_SecurityCheck.class);break;
//				case 1:NativeRequest.generateCheck(context, "formrulesidentify.dat", "质量对规鉴定", P102_RulesIdentify.class);break;
//				case 2:NativeRequest.generateCheck(context, "formqualityaccept.dat", "质量验收", P103_QualityAccept.class);break;
//				case 3:NativeRequest.generateCheck(context, "formrandomcheck.dat", "质量抽查", P104_RandomCheck.class);break;
//				case 4:NativeRequest.generateCheck(context, "formitemcheck.dat", "专项检查", P105_ItemCheck.class);break;
//				case 5:NativeRequest.generateCheck(context, "formpackagecheck.dat", "包保检查", P106_PackageCheck.class);break;
//				case 6:NativeRequest.generateCheck(context, "formadditemcheck.dat", "添乘检查", P107_AddItemCheck.class);break;
//				case 7:NativeRequest.generateCheck(context, "formstandardcheck.dat", "标准化验收", P108_StandardCheck.class);break;
//				case 8:NativeRequest.generateCheck(context, "formsafetyaccess.dat", "安全评估", P109_SafetyAccess.class);break;
//				case 9:NativeRequest.generateCheck(context, "formaccidentanalysis.dat", "事故分析", P110_AccidentAnalysis.class);break;
//				case 10:NativeRequest.generateCheck(context, "formfaultanalysis.dat", "故障分析", P111_FaultAnalysis.class);break;
//				case 11:NativeRequest.generateCheck(context, "formresearchcheck.dat", "工作调研", P112_ReSearchCheck.class);break;
//				case 12:NativeRequest.generateOtherCheck(context);break;
				}
			}
		});
		return imageview;
	}
}