package com.tunaPasta15.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class FragmentShowHideTestItem01 extends ListFragment {

	public static final String[] TITLES = { "tittle1", "tittle2", "tittle3", "tittle4", "tittle5", "tittle6", "tittle7", "tittle8" };
	
	int mCurCheckPosition = 0;
	int mShownCheckPosition = -1;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		System.out.println("TitleFragment==>onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("TitleFragment==>onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("TitleFragment==>onCreateView");
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		System.out.println("TitleFragment==>onActivityCreated");
		super.onActivityCreated(savedInstanceState);

		setListAdapter(new ArrayAdapter(getActivity(),
				android.R.layout.simple_list_item_1, TITLES));

		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// showDetails(mCurCheckPosition);
	}

	@Override
	public void onStart() {
		System.out.println("TitleFragment==>onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		System.out.println("TitleFragment==>onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		System.out.println("TitleFragment==>onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		System.out.println("TitleFragment==>onStop");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		System.out.println("TitleFragment==>onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		System.out.println("TitleFragment==>onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		System.out.println("TitleFragment==>onDetach");
		super.onDetach();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt("curChoice", mCurCheckPosition);
		outState.putInt("shownChoice", mShownCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

	void showDetails(int index) {
		mCurCheckPosition = index;
		getListView().setItemChecked(index, true);

		if (mShownCheckPosition != mCurCheckPosition) {
			FragmentShowHideTest activity = (FragmentShowHideTest) getActivity();
			activity.showDetail();
			activity.setText(index);
		}
	}

}
