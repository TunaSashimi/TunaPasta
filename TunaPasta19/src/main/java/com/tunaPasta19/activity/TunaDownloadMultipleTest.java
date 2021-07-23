package com.tunaPasta19.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tunaPasta19.R;
import com.tunaPasta19.application.DataTrans;
import com.tunaPasta19.tuna.TunaDownload;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TunaDownloadMultipleTest extends Activity {
    private static final int viewPagerpartCount = 3;
    private int viewPagerPartSize;
    private int viewPagerSize;

    private View[] viewPagerListArray;
    private List<ViewPagerPart> viewPagerPartList;

    private LayoutInflater layoutInflater;

    class ViewPagerPart {
        public String card;
        public String icon;

        public ViewPagerPart(String card, String icon) {
            this.card = card;
            this.icon = icon;
        }
    }

    //
    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            if (viewPagerListArray[arg1] == null) {

                ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.tunadownloadmultipleviewpager, null);
                viewPagerListArray[arg1] = viewGroup;

                RelativeLayout relativeLayoutLeft = viewGroup.findViewById(R.id.relativeLayoutLeft);
                RelativeLayout relativeLayoutMiddle = viewGroup.findViewById(R.id.relativeLayoutMiddle);
                RelativeLayout relativeLayoutRight = viewGroup.findViewById(R.id.relativeLayoutRight);

                TunaDownload tunaDownloadCardLeft = viewGroup.findViewById(R.id.tunaDownloadCardLeft);
                TunaDownload tunaDownloadIconLeft = viewGroup.findViewById(R.id.tunaDownloadIconLeft);
                TunaDownload tunaDownloadCardMiddle = viewGroup.findViewById(R.id.tunaDownloadCardMiddle);
                TunaDownload tunaDownloadIconMiddle = viewGroup.findViewById(R.id.tunaDownloadIconMiddle);
                TunaDownload tunaDownloadCardRight = viewGroup.findViewById(R.id.tunaDownloadCardRight);
                TunaDownload tunaDownloadIconRight = viewGroup.findViewById(R.id.tunaDownloadIconRight);

                relativeLayoutLeft.setVisibility(View.VISIBLE);
                ViewPagerPart viewPagerItemLeft = viewPagerPartList.get(arg1 * viewPagerpartCount);
                tunaDownloadCardLeft.init(DataTrans.tunaGraphicsMap, viewPagerItemLeft.card);
                tunaDownloadIconLeft.init(DataTrans.tunaGraphicsMap, viewPagerItemLeft.icon);
                //
                tunaDownloadCardLeft.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
//						System.out.println("tunaDownloadCardLeft");						
                    }
                });

                if (arg1 * viewPagerpartCount + 1 < viewPagerPartSize) {
                    relativeLayoutMiddle.setVisibility(View.VISIBLE);
                    ViewPagerPart viewPagerItemMiddle = viewPagerPartList.get(arg1 * viewPagerpartCount + 1);
                    tunaDownloadCardMiddle.init(DataTrans.tunaGraphicsMap, viewPagerItemMiddle.card);
                    tunaDownloadIconMiddle.init(DataTrans.tunaGraphicsMap, viewPagerItemMiddle.icon);
                    //
                    tunaDownloadCardMiddle.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
//							System.out.println("tunaDownloadCardMiddle");						
                        }
                    });
                } else {
                    relativeLayoutMiddle.setVisibility(View.INVISIBLE);
                }

                if (arg1 * viewPagerpartCount + 2 < viewPagerPartSize) {
                    relativeLayoutRight.setVisibility(View.VISIBLE);
                    ViewPagerPart viewPagerItemRight = viewPagerPartList.get(arg1 * viewPagerpartCount + 2);
                    tunaDownloadCardRight.init(DataTrans.tunaGraphicsMap, viewPagerItemRight.card);
                    tunaDownloadIconRight.init(DataTrans.tunaGraphicsMap, viewPagerItemRight.icon);
                    //
                    tunaDownloadCardRight.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
//							System.out.println("tunaDownloadCardRight");						
                        }
                    });
                } else {
                    relativeLayoutRight.setVisibility(View.INVISIBLE);
                }
            }
            ((ViewPager) arg0).addView(viewPagerListArray[arg1]);
            return viewPagerListArray[arg1];
        }

        @Override
        public int getCount() {
            return viewPagerSize;
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
            arg2 = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunadownloadmultiple);

        layoutInflater = LayoutInflater.from(getApplicationContext());

        viewPagerPartList = new ArrayList<ViewPagerPart>();
        viewPagerPartList.add(new ViewPagerPart("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/8A471DA9338F4EDE94ABCDD9B41E2B74",
            "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/D5FE3681A4954594847280215245F065"));
        viewPagerPartList.add(new ViewPagerPart("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/5BDCB7260B8345F68D17B3D0C9EBAE0C",
            "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/F66785C79844477EAE7B4B445AF98955"));
        viewPagerPartList.add(new ViewPagerPart("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/155DC84294E248EB9B075DB52E49E3C9",
            "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/D19CC847D1314985867806F0DEC9C8B4"));
        viewPagerPartList.add(new ViewPagerPart("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/F3F7F6EA31DF44C0BBE1AAF7E4597809",
            "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/64BFB06B353A475DB21C80AFD3BAEFDF"));

        //
        viewPagerPartSize = viewPagerPartList.size();
        viewPagerSize = viewPagerPartList.size() / viewPagerpartCount + (viewPagerPartList.size() % viewPagerpartCount == 0 ? 0 : 1);
        viewPagerListArray = new View[viewPagerSize];

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        pagerAdapter.notifyDataSetChanged();
    }
}
