package com.tunaPasta07.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tunaPasta07.R;
import com.tunaPasta07.widget.FlipViewGroup02;

public class FlipTouch extends Activity {
	private FlipViewGroup02 flipViewGroup02;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		flipViewGroup02 = new FlipViewGroup02(this);

		flipViewGroup02.addFlipView(View.inflate(this, R.layout.flippage02, null));
		flipViewGroup02.addFlipView(View.inflate(this, R.layout.flippage01, null));

		setContentView(flipViewGroup02);

		flipViewGroup02.startFlipping(); //make the first_page view flipping
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(
			Intent.ACTION_VIEW, 
			Uri.parse("http://openaphid.github.com/blog/2012/05/21/how-to-implement-flipboard-animation-on-android/")
		);
		startActivity(intent);

		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		flipViewGroup02.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		flipViewGroup02.onPause();
	}
}
