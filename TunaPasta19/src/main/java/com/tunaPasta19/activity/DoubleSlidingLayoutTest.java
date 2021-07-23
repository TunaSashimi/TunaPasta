package com.tunaPasta19.activity;

import android.os.Bundle;

import com.tunaPasta19.R;
import com.tunaPasta19.widget.DoubleSlidingLayoutLeftFragment;
import com.tunaPasta19.widget.DoubleSlidingLayoutRightFragment;
import com.tunaPasta19.widget.DoubleSlidingLayoutSlidingMenu;
import com.tunaPasta19.widget.DoubleSlidingLayoutViewPageFragment;
import com.tunaPasta19.widget.DoubleSlidingLayoutViewPageFragment.MyPageChangeListener;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class DoubleSlidingLayoutTest extends FragmentActivity {

    DoubleSlidingLayoutSlidingMenu mSlidingMenu;
    DoubleSlidingLayoutLeftFragment leftFragment;
    DoubleSlidingLayoutRightFragment rightFragment;
    DoubleSlidingLayoutViewPageFragment viewPageFragment;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.doubleslidinglayouttest);

        init();

        initListener();

    }

    private void init() {

        mSlidingMenu = findViewById(R.id.slidingMenu);

        mSlidingMenu.setLeftView(getLayoutInflater().inflate(R.layout.doubleslidinglayouttestleftframe, null));
        mSlidingMenu.setRightView(getLayoutInflater().inflate(R.layout.doubleslidinglayouttestrightframe, null));
        mSlidingMenu.setCenterView(getLayoutInflater().inflate(R.layout.doubleslidinglayouttestcenterframe, null));

        FragmentTransaction fragementtransaction = this.getSupportFragmentManager().beginTransaction();

        leftFragment = new DoubleSlidingLayoutLeftFragment();
        fragementtransaction.replace(R.id.left_frame, leftFragment);

        rightFragment = new DoubleSlidingLayoutRightFragment();
        fragementtransaction.replace(R.id.right_frame, rightFragment);

        viewPageFragment = new DoubleSlidingLayoutViewPageFragment();
        fragementtransaction.replace(R.id.center_frame, viewPageFragment);
        fragementtransaction.commit();
    }

    private void initListener() {

        viewPageFragment.setMyPageChangeListener(new MyPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (viewPageFragment.isFirst()) {
                    mSlidingMenu.setCanSliding(true, false);
                } else if (viewPageFragment.isEnd()) {
                    mSlidingMenu.setCanSliding(false, true);
                } else {
                    mSlidingMenu.setCanSliding(false, false);
                }
            }
        });
    }

    public void showLeft() {
        mSlidingMenu.showLeftView();
    }

    public void showRight() {
        mSlidingMenu.showRightView();
    }
}
