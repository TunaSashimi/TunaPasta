package com.tunaPasta19.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaAnimation;
import com.tunaPasta19.tuna.TunaView;
import com.tunaPasta19.tuna.TunaView.TunaAnimateEndListener;

public class TunaWelcome extends Activity {
    private VideoView videoView;
    private int screenWidth, screenHeight;

    private static final float tunaTopWidthDivieHeight = 480 / 852f;

    private ImageView imageView;
    private TextView textView;

    private TunaView tunaSkip, tunaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunawelcome);

        videoView = findViewById(R.id.videoView);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        tunaSkip = findViewById(R.id.tunaSkip);
        tunaLogin = findViewById(R.id.tunaLogin);

        screenWidth = TunaView.getScreenWidth(this);
        screenHeight = TunaView.getScreenHeight(this);

        TunaView.fillViewAutomatic(videoView, screenWidth, screenHeight, tunaTopWidthDivieHeight);

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.long_480));
        videoView.setOnErrorListener(new OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                        System.out.println("MEDIA_ERROR_UNKNOWN");
                        break;
                    case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                        System.out.println("MEDIA_ERROR_SERVER_DIED");
                        break;
                    case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                        System.out.println("MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK");
                        break;
                    default:
                        break;
                }
                finish();
                return true;
            }
        });

        TunaAnimation.playObjectAnimation(imageView,
            TunaAnimation.tunawelcome_imagePara,
            new TunaAnimateEndListener() {
                @Override
                public void tunaAnimateEnd(View v) {
                    tunaSkip.setVisibility(View.VISIBLE);
                    tunaLogin.setVisibility(View.VISIBLE);
                    videoView.start();
                }
            });

        TunaAnimation.playObjectAnimation(textView,
            TunaAnimation.tunawelcome_textPara);

        videoView
            .setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.long_480));
                    videoView.start();
                }
            });
    }
}
