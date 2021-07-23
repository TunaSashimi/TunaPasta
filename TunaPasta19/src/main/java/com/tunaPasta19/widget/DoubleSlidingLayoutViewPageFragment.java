package com.tunaPasta19.widget;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.tunaPasta19.R;
import com.tunaPasta19.activity.DoubleSlidingLayoutTest;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DoubleSlidingLayoutViewPageFragment extends Fragment {

    private Button showLeft;
    private Button showRight;
    private MyAdapter mAdapter;
    private ViewPager mPager;
    private ArrayList<Fragment> pagerItemList = new ArrayList();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.doubleslidinglayoutviewpagefragment, null);
        showLeft = mView.findViewById(R.id.showLeft);
        showRight = mView.findViewById(R.id.showRight);
        mPager = mView.findViewById(R.id.pager);

        DoubleSlidingLayoutPageFragment01 page1 = new DoubleSlidingLayoutPageFragment01();
        DoubleSlidingLayoutPageFragment02 page2 = new DoubleSlidingLayoutPageFragment02();

        pagerItemList.add(page1);
        pagerItemList.add(page2);
        mAdapter = new MyAdapter(getFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                if (myPageChangeListener != null)
                    myPageChangeListener.onPageSelected(position);

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });

        return mView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        showLeft.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ((DoubleSlidingLayoutTest) getActivity()).showLeft();
            }
        });

        showRight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ((DoubleSlidingLayoutTest) getActivity()).showRight();
            }
        });
    }

    public boolean isFirst() {
        if (mPager.getCurrentItem() == 0)
            return true;
        else
            return false;
    }

    public boolean isEnd() {
        if (mPager.getCurrentItem() == pagerItemList.size() - 1)
            return true;
        else
            return false;
    }

    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return pagerItemList.size();
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            if (position < pagerItemList.size())
                fragment = pagerItemList.get(position);
            else
                fragment = pagerItemList.get(0);

            return fragment;

        }
    }

    private MyPageChangeListener myPageChangeListener;

    public void setMyPageChangeListener(MyPageChangeListener l) {

        myPageChangeListener = l;

    }

    public interface MyPageChangeListener {
        public void onPageSelected(int position);
    }

}
