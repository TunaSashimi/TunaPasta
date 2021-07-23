package com.tunaPasta19.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.entity.HomeCard;
import com.tunaPasta19.tuna.TunaAnimation;
import com.tunaPasta19.tuna.TunaDownload;
import com.tunaPasta19.tuna.TunaView;
import com.tunaPasta19.tuna.TunaView.TunaAnimateEndListener;
import com.tunaPasta19.tuna.TunaView.TunaTouchType;
import com.tunaPasta19.tuna.TunaView.TunaTouchUpListener;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TunaHomeTest extends Activity {

    private boolean useArrayMethod = true;

    private RelativeLayout relative, relativeLayout, relativeTop, relativeBottom;

    private TunaView tunaUser, tunaActivity, tunaTravel;

    //
    private ImageView imageBottom, imageHalo;
    private View viewHide, viewSwitch;
    private ViewPager viewPager;

    private List<View> viewList = new ArrayList<View>();
    private List<HomeCard> homeCardList = new ArrayList<HomeCard>();

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        public void startUpdate(View arg0) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(viewList.get(arg1));
            return viewList.get(arg1);
        }

        @Override
        public int getCount() {
            if (viewList != null) {
                return viewList.size();
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

        public float getPageWidth(int position) {
            return 0.31f;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunahometest);

        viewHide = findViewById(R.id.viewHide);
        viewSwitch = findViewById(R.id.viewSwitch);

        relative = findViewById(R.id.relative);
        relativeLayout = findViewById(R.id.relativeLayout);
        relativeTop = findViewById(R.id.relativeTop);
        relativeBottom = findViewById(R.id.relativeBottom);

        tunaUser = findViewById(R.id.tunaUser);
        tunaActivity = findViewById(R.id.tunaActivity);
        tunaTravel = findViewById(R.id.tunaTravel);

        TunaDownload tunaDownloadTop = findViewById(R.id.tunaDownloadTop);
        TunaDownload tunaDownloadTopImage = findViewById(R.id.tunaDownloadTopImage);
        TextView textDownloadTop = findViewById(R.id.textDownloadTop);
        homeCardList.add(new HomeCard(tunaDownloadTop, tunaDownloadTopImage, textDownloadTop));

        //
        viewPager = findViewById(R.id.viewPager);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.tuna_layout_marginBetween));

        //
        LayoutInflater inflater = LayoutInflater.from(this);
        viewList.add(inflater.inflate(R.layout.tunahomeitem01, null));
        viewList.add(inflater.inflate(R.layout.tunahomeitem02, null));
        viewList.add(inflater.inflate(R.layout.tunahomeitem03, null));
        viewList.add(inflater.inflate(R.layout.tunahomeitem04, null));

        //
        int listSize = viewList.size();
        for (int i = 0; i < listSize; i++) {
            final int p = i;
            View view = viewList.get(p);
            TunaDownload tunaDownload = view.findViewById(R.id.tunaDownload);
            TunaDownload tunaDownloadImage = view.findViewById(R.id.tunaDownloadImage);
            TextView textDownload = view.findViewById(R.id.textDownload);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (p) {
                        case 0:
                            playFadeAnimation(new TunaAnimateEndListener() {
                                @Override
                                public void tunaAnimateEnd(View v) {
                                    Toast.makeText(TunaHomeTest.this, "0", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case 1:
                            playFadeAnimation(new TunaAnimateEndListener() {
                                @Override
                                public void tunaAnimateEnd(View v) {
                                    Toast.makeText(TunaHomeTest.this, "1", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case 2:
                            playFadeAnimation(new TunaAnimateEndListener() {
                                @Override
                                public void tunaAnimateEnd(View v) {
                                    Toast.makeText(TunaHomeTest.this, "2", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case 3:
                            playFadeAnimation(new TunaAnimateEndListener() {
                                @Override
                                public void tunaAnimateEnd(View v) {
                                    Toast.makeText(TunaHomeTest.this, "3", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        default:
                            break;
                    }
                }
            });
            homeCardList.add(new HomeCard(tunaDownload, tunaDownloadImage, textDownload));
        }

        //
        viewPager.setAdapter(mPagerAdapter);

        imageHalo = (ImageView) findViewById(R.id.imageHalo);
        imageBottom = (ImageView) findViewById(R.id.imageBottom);

        //
        tunaUser.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                tunaUser.setTunaTouchType(TunaTouchType.NONE);
                playCoverAnimation(new TunaAnimateEndListener() {
                    @Override
                    public void tunaAnimateEnd(View v) {
                        tunaUser.setTunaTouchType(TunaTouchType.EDGE);
                        imageBottom.setVisibility(View.INVISIBLE);
                        imageHalo.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        //
        tunaTravel.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                tunaTravel.setTunaTouchType(TunaTouchType.NONE);
                playCoverAnimation(new TunaAnimateEndListener() {
                    @Override
                    public void tunaAnimateEnd(View v) {
                        tunaTravel.setTunaTouchType(TunaTouchType.EDGE);
                        imageBottom.setVisibility(View.INVISIBLE);
                        imageHalo.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        //
        tunaActivity.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                homeCardList.get(0).setHomeCardInformation("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/8E388876128641C298310B99DAC5E495",
                    "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/D5FE3681A4954594847280215245F065", "全功能订车");
                homeCardList.get(1).setHomeCardInformation("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/B9EF659A73E841DC8BFA62430F06E273",
                    "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/64BFB06B353A475DB21C80AFD3BAEFDF", "接车");
            }
        });

        //
        relativeTop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playFadeAnimation(null);
            }
        });
        //
        relativeBottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeBottom.setEnabled(false);
                playBallShapeAnimation(new TunaAnimateEndListener() {
                    @Override
                    public void tunaAnimateEnd(View v) {
                        relativeBottom.setEnabled(true);
                    }
                });
            }
        });

        if (useArrayMethod) {
            // you can pass an array method recommended
            TunaAnimation.playOrderLoadAnimation(new View[]{relativeTop, viewPager, relativeBottom}, TunaAnimation.tunahome_loadLayoutPara, new TunaAnimateEndListener() {
                @Override
                public void tunaAnimateEnd(View v) {
                    Toast.makeText(TunaHomeTest.this, "播放完毕", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            // One needs to be converted into playOrderLoadAnimation of view,
            // and is usually the first conversion!
            TunaAnimation.playOrderLoadAnimation(Arrays.asList((View) relativeTop, viewPager, relativeBottom), TunaAnimation.tunahome_loadLayoutPara, new TunaAnimateEndListener() {
                @Override
                public void tunaAnimateEnd(View v) {
                    Toast.makeText(TunaHomeTest.this, "播放完毕", Toast.LENGTH_LONG).show();
                }
            });
        }
        setTimerTask();
    }

    private static final int PLAY_HALO_ANIMATION = 0;
    private Timer timer = new Timer();

    private void setTimerTask() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(PLAY_HALO_ANIMATION);
            }
        }, 0, 4000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PLAY_HALO_ANIMATION:
                    TunaAnimation.playObjectAnimation(imageHalo, TunaAnimation.tunahome_haloPara, true, null);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    //
    private void playFadeAnimation(TunaAnimateEndListener tunaAnimateEndListener) {
        TunaAnimation.playObjectAnimation(relativeLayout, TunaAnimation.tunahome_relaviteLayoutPara, tunaAnimateEndListener);
        TunaAnimation.playObjectAnimation(relativeBottom, TunaAnimation.tunahome_relaviteLayoutPara);
    }

    //
    private void playCoverAnimation(TunaAnimateEndListener tunaAnimateEndListener) {
        imageBottom.setVisibility(View.VISIBLE);
        imageHalo.setVisibility(View.INVISIBLE);
        //
        TunaAnimation.playObjectAnimation(relative, TunaAnimation.tunahome_relavitePara, tunaAnimateEndListener);
        //
        TunaAnimation.playToBottomAnimation(viewSwitch, TunaAnimation.tunahome_viewHidePara, true, null);
    }

    //
    private void playBallShapeAnimation(TunaAnimateEndListener tunaAnimateEndListener) {
        imageBottom.setVisibility(View.VISIBLE);
        imageHalo.setVisibility(View.INVISIBLE);

        TunaAnimation.playToCenterAnimation(TunaHomeTest.this, relativeBottom, false, TunaAnimation.tunahome_relativeBottomPara, tunaAnimateEndListener);
        TunaAnimation.playToBottomAnimation(viewHide, TunaAnimation.tunahome_viewHidePara, true, new TunaAnimateEndListener() {
            @Override
            public void tunaAnimateEnd(View v) {
                imageBottom.setVisibility(View.INVISIBLE);
                imageHalo.setVisibility(View.VISIBLE);
            }
        });
        TunaAnimation.playToCenterAnimation(TunaHomeTest.this, imageBottom, false, TunaAnimation.tunahome_imageBottomPara, null);
        TunaAnimation.playObjectAnimation(relativeLayout, TunaAnimation.tunahome_relaviteLayoutPara);
    }
}
