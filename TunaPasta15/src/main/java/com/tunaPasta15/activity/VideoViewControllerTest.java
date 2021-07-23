package com.tunaPasta15.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.tunaPasta15.R;

public class VideoViewControllerTest extends Activity {
	private VideoView videoView;
	private MediaController mController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.videoviewcontrollertest);
		videoView = (VideoView) findViewById(R.id.videoView);
		
		// 实例化MediaController
		mController = new MediaController(this);
		
		//注意这里用的是com.nemesisJS15.widget.FullScreenVideoView和VideoView的区别
		
			// 设置播放视频源的路径
			videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.long_720));
			
			videoView.start();
			videoView .setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mp) {
							mp.start();
							mp.setLooping(true);

						}
					});
			videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer mp) {
							videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.long_720));
							videoView.start();
						}
					});
			
			// 为VideoView指定MediaController
			videoView.setMediaController(mController);
			// 为MediaController指定控制的VideoView
			mController.setMediaPlayer(videoView);
			// 增加监听上一个和下一个的切换事件，默认这两个按钮是不显示的
			mController.setPrevNextListeners(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(VideoViewControllerTest.this, "下一个", Toast.LENGTH_SHORT).show();
				}
			}, new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(VideoViewControllerTest.this, "上一个", Toast.LENGTH_SHORT).show();
				}
			});
	}
	
}
