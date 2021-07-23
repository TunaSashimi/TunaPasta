package com.tunaPasta15.activity;

import android.os.Bundle;

import com.tunaPasta15.R;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/*
 * 第一种情况：如果xml将两个fragment同时放到一个container中去，并且在同一时刻只显示一个。那么他们在切换的过程中，不会触发fragment中的常用方法。
 * 第二种情况：xml中只有一个container.两个fragment通过replace方法进行显示。
 * 
 * 如果不加入 transaction.addToBackStack("****");
 *  1.从TitleFragment退回到DetailFragment.执行操作：
     TitleFragment==>onPause
     TitleFragment==>onStop
     TitleFragment==>onDestroyView
     DetailsFragment==>onAttach
     DetailsFragment==>onCreate
     DetailsFragment==>onCreateView
     DetailsFragment==>onActivityCreated
     DetailsFragment==>onStart
     DetailsFragment==>onResume
     TitleFragment==>onDestroy
     TitleFragment==>onDetach
 *  
 *  2.从DetailFragment退回到TitleFragment.
 *   ==>onBackPressed()
     DetailsFragment==>onPause
     DetailsFragment==>onStop
     DetailsFragment==>onDestroyView
     TitleFragment==>onAttach//如果不是第一次加载fragment，onAttach,onCreate也不会执行
     TitleFragment==>onCreate//
     TitleFragment==>onCreateView
     TitleFragment==>onActivityCreated
     TitleFragment==>onStart
     TitleFragment==>onResume
 *  
 *  加入 transaction.addToBackStack("****");
 *  1.从TitleFragment退回到DetailFragment.执行操作：
     TitleFragment==>onPause
     TitleFragment==>onStop
     TitleFragment==>onDestroyView
     DetailsFragment==>onAttach//如果不是第一次加载fragment，onAttach,onCreate也不会执行
     DetailsFragment==>onCreate
     DetailsFragment==>onCreateView
     DetailsFragment==>onActivityCreated
     DetailsFragment==>onStart
     DetailsFragment==>onResume
 */
public class FragmentShowHideTest extends FragmentActivity {

    private FragmentManager manager;
    private FragmentShowHideTestItem01 fragmentShowHideTestItem01;
    private FragmentShowHideTestItem02 fragmentShowHideTestItem02;

    private final int TAG_TITLE = 0;
    private final int TAG_DETAIL = TAG_TITLE + 1;
    private int currentTag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentshowhidetest);

        manager = getSupportFragmentManager();

        //第一种情况
//		fragmentShowHideTestItem01 = (FragmentShowHideTestItem01) manager.findFragmentById(R.id.fragmentShowHideTestItem01);
//		fragmentShowHideTestItem02 = (FragmentShowHideTestItem02) manager .findFragmentById(R.id.fragmentShowHideTestItem02);
//		showTitle();

        //第二种情况
        fragmentShowHideTestItem01 = new FragmentShowHideTestItem01();
        fragmentShowHideTestItem02 = new FragmentShowHideTestItem02();
        showTitle();
    }

    public void showTitle() {
        if (currentTag != TAG_TITLE) {
            currentTag = TAG_TITLE;
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.fragmentshowhidetest_anim_appear, R.anim.fragmentshowhidetest_anim_disappear);

            //第一种情况
//			transaction.show(fragmentShowHideTestItem01);
//			transaction.hide(fragmentShowHideTestItem02);
//			transaction.commit();

            //第二种情况
            transaction.replace(R.id.container, fragmentShowHideTestItem01);
            transaction.addToBackStack("detail");
            transaction.commit();

        }
    }

    public void showDetail() {
        if (currentTag != TAG_DETAIL) {
            currentTag = TAG_DETAIL;
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.fragmentshowhidetest_anim_appear, R.anim.fragmentshowhidetest_anim_disappear);

            //第一种情况
//			transaction.show(fragmentShowHideTestItem02);
//			transaction.hide(fragmentShowHideTestItem01);
//			transaction.commit();

            //第二种情况
            transaction.replace(R.id.container, fragmentShowHideTestItem02);
            transaction.addToBackStack("title");
            transaction.commit();
        }
    }

    public void setText(int index) {
        fragmentShowHideTestItem02.setText(index);
    }

//	@Override
//	public void onBackPressed() {
//		System.out.println("==>onBackPressed()");
//		showTitle();
//	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
