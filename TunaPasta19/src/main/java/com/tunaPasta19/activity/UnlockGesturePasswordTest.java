package com.tunaPasta19.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.application.DataTrans;
import com.tunaPasta19.tool.LockPatternUtils;
import com.tunaPasta19.widget.LockPatternView;
import com.tunaPasta19.widget.LockPatternView.Cell;

public class UnlockGesturePasswordTest extends Activity {
    private LockPatternView mLockPatternView;
    private int mFailedPatternAttemptsSinceLastTimeout = 0;
    private CountDownTimer mCountdownTimer = null;
    private Handler mHandler = new Handler();
    private TextView mHeadTextView;
    private Animation mShakeAnim;

    private Toast mToast;

    private void showToast(CharSequence message) {
        if (null == mToast) {
            mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(message);
        }

        mToast.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlockgesturepasswordtest);

        mLockPatternView = this.findViewById(R.id.gesturepwd_unlock_lockview);

        mLockPatternView.setOnPatternListener(mChooseNewLockPatternListener);

        mLockPatternView.setTactileFeedbackEnabled(true);

        mHeadTextView = findViewById(R.id.gesturepwd_unlock_text);

        mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.unlockgesturepasswordtest_anim_translate);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!DataTrans.getInstance().getLockPatternUtils().savedPatternExists()) {
            startActivity(new Intent(this, GuideGesturePasswordTest.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountdownTimer != null)
            mCountdownTimer.cancel();
    }

    private Runnable mClearPatternRunnable = new Runnable() {
        public void run() {
            mLockPatternView.clearPattern();
        }
    };

    protected LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() {

        public void onPatternStart() {
            mLockPatternView.removeCallbacks(mClearPatternRunnable);
            patternInProgress();
        }

        public void onPatternCleared() {
            mLockPatternView.removeCallbacks(mClearPatternRunnable);
        }

        public void onPatternDetected(List<LockPatternView.Cell> pattern) {
            if (pattern == null)
                return;
            if (DataTrans.getInstance().getLockPatternUtils().checkPattern(pattern)) {

                mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);

                Intent intent = new Intent(UnlockGesturePasswordTest.this, GuideGesturePasswordTest.class);

                // ????????????Activity
                startActivity(intent);
                showToast("????????????");
                finish();
            } else {

                mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);

                if (pattern.size() >= LockPatternUtils.MIN_PATTERN_REGISTER_FAIL) {
                    mFailedPatternAttemptsSinceLastTimeout++;
                    int retry = LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT
                        - mFailedPatternAttemptsSinceLastTimeout;
                    if (retry >= 0) {
                        if (retry == 0)
                            showToast("??????5?????????????????????30????????????");
                        mHeadTextView.setText("?????????????????????????????????" + retry + "???");
                        mHeadTextView.setTextColor(Color.RED);
                        mHeadTextView.startAnimation(mShakeAnim);
                    }
                } else {
                    showToast("??????????????????????????????");
                }

                if (mFailedPatternAttemptsSinceLastTimeout >= LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT) {
                    mHandler.postDelayed(attemptLockout, 2000);
                } else {
                    mLockPatternView.postDelayed(mClearPatternRunnable, 2000);
                }
            }
        }

        public void onPatternCellAdded(List<Cell> pattern) {

        }

        private void patternInProgress() {
        }
    };
    Runnable attemptLockout = new Runnable() {

        @Override
        public void run() {
            mLockPatternView.clearPattern();
            mLockPatternView.setEnabled(false);
            mCountdownTimer = new CountDownTimer(
                LockPatternUtils.FAILED_ATTEMPT_TIMEOUT_MS + 1, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    int secondsRemaining = (int) (millisUntilFinished / 1000) - 1;
                    if (secondsRemaining > 0) {
                        mHeadTextView.setText(secondsRemaining + " ????????????");
                    } else {
                        mHeadTextView.setText("?????????????????????");
                        mHeadTextView.setTextColor(Color.WHITE);
                    }
                }

                @Override
                public void onFinish() {
                    mLockPatternView.setEnabled(true);
                    mFailedPatternAttemptsSinceLastTimeout = 0;
                }
            }.start();
        }
    };
}
