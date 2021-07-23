package com.tunaPasta15.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tunaPasta15.R;

import androidx.fragment.app.Fragment;

public class FragmentShowHideTestItem02 extends Fragment {

	public static final String[] DIALOGUE = { "this is tittle1.",
		"this is tittle2.", "this is tittle3.", "this is tittle4.",
		"this is tittle5.", "this is tittle6.", "this is tittle7.",
		"this is tittle8." };

	private int index;

	public static FragmentShowHideTestItem02 newInstance(int index) {
		FragmentShowHideTestItem02 f = new FragmentShowHideTestItem02();
		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onAttach(Activity activity) {
		System.out.println(" DetailsFragment==>onAttach");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println(" DetailsFragment==>onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println(" DetailsFragment==>onCreateView");
		ScrollView scroller = (ScrollView) inflater.inflate( R.layout.fragmentshowhidetestitem02, null);
		return scroller;
	}

	private TextView textView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		System.out.println(" DetailsFragment==>onActivityCreated");
		super.onActivityCreated(savedInstanceState);
		textView = (TextView) getView().findViewById(R.id.textView);
		textView.setText(DIALOGUE[index]);
	}

	@Override
	public void onStart() {
		System.out.println(" DetailsFragment==>onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		System.out.println(" DetailsFragment==>onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		System.out.println(" DetailsFragment==>onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		System.out.println(" DetailsFragment==>onStop");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		System.out.println(" DetailsFragment==>onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		System.out.println(" DetailsFragment==>onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		System.out.println(" DetailsFragment==>onDetach");
		super.onDetach();
	}

	public void setText(int index) {
		this.index = index;
	}

}
