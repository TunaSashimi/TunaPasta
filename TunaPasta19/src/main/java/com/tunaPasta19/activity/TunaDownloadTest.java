package com.tunaPasta19.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tunaPasta19.R;
import com.tunaPasta19.adapter.TunaDownloadTestListViewAdapter;
import com.tunaPasta19.application.DataTrans;
import com.tunaPasta19.tuna.TunaDownload;
import com.tunaPasta19.tuna.TunaGif;
import com.tunaPasta19.tuna.TunaRepeat;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TunaDownloadTest extends Activity {

    /**
     * ListView
     */
    private String[] tunaDownloadTestListViewArray = {
        "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/2D869AA12DEE47A8ABF06203A5349EA6",
        "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/8B62B9CC3FC24E308BD0D2C78A4FAF0C",
        "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/B3396D5376824A9BA9BEE32A4B71CE49",
        null,};

    private ListView tunaDownLoadTestListView;
    private TunaDownloadTestListViewAdapter tunaDownloadTestListViewAdapter;

    /**
     * ViewPagerItem
     */
    class ViewPagerItem {
        public String name;
        public String photo;
        public String mark;

        public ViewPagerItem(String name, String photo, String mark) {
            this.name = name;
            this.photo = photo;
            this.mark = mark;
        }
    }

    //
    private List<ViewPagerItem> viewPagerList = new ArrayList<ViewPagerItem>();
    private View[] tunaDownloadViewArray;
    private LayoutInflater layoutInflater;

    //
    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            if (tunaDownloadViewArray[arg1] == null) {
                RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.tunadownloadtestviewpager, null);

                final TunaGif tunaGif = relativeLayout.findViewById(R.id.tunaGif);
                tunaGif.init(R.drawable.tunagiftest_loading);

                tunaDownloadViewArray[arg1] = relativeLayout;
                TunaDownload tunaDownload = relativeLayout.findViewById(R.id.tunaDownload);
                TunaDownload tunaDownloadTop = relativeLayout.findViewById(R.id.tunaDownloadTop);
                ViewPagerItem person = viewPagerList.get(arg1);
                tunaDownload.init(DataTrans.tunaGraphicsMap, person.photo, person.name);
                tunaDownloadTop.init(DataTrans.tunaGraphicsMap, person.mark);

                //
                tunaDownload.setTunaDownloadCompleteListener(new TunaDownload.TunaDownloadCompleteListener() {
                    @Override
                    public void tunaDownloadComplete() {
                        tunaGif.setVisibility(View.GONE);
                    }
                });
            }
            ((ViewPager) arg0).addView(tunaDownloadViewArray[arg1]);
            return tunaDownloadViewArray[arg1];
        }

        @Override
        public int getCount() {
            if (viewPagerList != null) {
                return viewPagerList.size();
            }
            return 0;
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

        setContentView(R.layout.tunadownloadtest);

        /**
         * ListView
         */
        tunaDownloadTestListViewAdapter = new TunaDownloadTestListViewAdapter(this, tunaDownloadTestListViewArray);

        tunaDownLoadTestListView = findViewById(R.id.listView);
        tunaDownLoadTestListView.setAdapter(tunaDownloadTestListViewAdapter);

        /**
         * ViewPager
         */
        layoutInflater = LayoutInflater.from(getApplicationContext());

        viewPagerList.add(new ViewPagerItem("Tuna", "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/B87F71BF53B54A178B99E5A622CD694D",
            "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/9793DAF00C4240C1B06451F85A5FBEF2"));
        viewPagerList.add(new ViewPagerItem("TunaSashimi", "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/A4852AD246A1435EAB6EB94B5D7366A4",
            "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/9793DAF00C4240C1B06451F85A5FBEF2"));
        viewPagerList.add(new ViewPagerItem("金枪鱼", "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/BCB5867982A14B84862547D888BE7894",
            "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/9793DAF00C4240C1B06451F85A5FBEF2"));

        //
        final TunaRepeat tunaRepeat = findViewById(R.id.tunaRepeat);
        tunaRepeat.setTunaRepeatTotal(viewPagerList.size());

        //
        tunaDownloadViewArray = new View[viewPagerList.size()];

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                tunaRepeat.setTunaRepeatCurrentIndex(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();

        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                float MIN_SCALE = 0.85f;
                float MIN_ALPHA = 0.5f;

                //
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                // [-Infinity,-1) ,This page is way off-screen to the left.
                if (position < -1) {
                    view.setAlpha(0);
                    // [-1,1] Modify the default slide transition to shrink the
                    // page as well
                } else if (position <= 1) {
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }
                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);
                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
                } else { // (1,+Infinity] This page is way off-screen to the
                    // right.
                    view.setAlpha(0);
                }
            }
        });
    }
}