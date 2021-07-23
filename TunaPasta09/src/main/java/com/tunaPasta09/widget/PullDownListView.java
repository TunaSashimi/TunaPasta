package com.tunaPasta09.widget;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tunaPasta09.R;


public class PullDownListView extends ListView implements OnScrollListener {

    private static final String TAG = "PullDownListView";

    private final static int RELEASE_REFRESH = 0; // 松开刷新状态
    private final static int PULL_REFRESH = 1;// 下拉刷新状态
    private final static int REFRESHING = 2;// 正在刷新状态
    private final static int DONE = 3;// 完成刷新状态
    private final static int LOADING = 4;// 正在加载状态

    // 实际的padding的距离与界面上偏移距离的比例
    private final static int RATIO = 3;

    private LayoutInflater inflater;

    private LinearLayout headerView;
    private LinearLayout footerView;

    private TextView tipsTextview;
    private TextView lastUpdatedTextView;
    private ImageView arrowImageView;
    private ProgressBar progressBar;

    private RotateAnimation animation;
    private RotateAnimation reverseAnimation;

    private int startY;

    private int headContentWidth, headContentHeight;

    // 用于保证startY的值在一个完整的touch事件中只被记录一次
    private boolean isRecored;

    private int state;

    private boolean isBack;

    private OnRefreshListener refreshListener;

    private boolean canRefresh;

    public PullDownListView(Context context) {
        super(context);
        init(context);
    }

    public PullDownListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflater = LayoutInflater.from(context);

        //
        footerView = (LinearLayout) inflater.inflate(R.layout.pulldownlistviewtestfooter, null);
        footerView.findViewById(R.id.footer_layout).setVisibility(View.GONE);

        headerView = (LinearLayout) inflater.inflate(R.layout.pulldownlistviewtestheader, null);

        arrowImageView = (ImageView) headerView.findViewById(R.id.head_arrowImageView);
        progressBar = (ProgressBar) headerView.findViewById(R.id.head_progressBar);
        tipsTextview = (TextView) headerView.findViewById(R.id.head_tipsTextView);
        lastUpdatedTextView = (TextView) headerView.findViewById(R.id.head_lastUpdatedTextView);

        measureView(headerView);

        //
        headContentHeight = headerView.getMeasuredHeight();
        headContentWidth = headerView.getMeasuredWidth();

        headerView.setPadding(0, -headContentHeight, 0, 0);

        Log.v(TAG, "headContentWidth:" + headContentWidth + " headContentHeight:" + headContentHeight);

        addHeaderView(headerView);
        addFooterView(footerView);

        setOnScrollListener(this);

        animation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(250);
        animation.setFillAfter(true);

        reverseAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setDuration(250);
        reverseAnimation.setFillAfter(true);

        state = DONE;
        canRefresh = false;
    }

    /**
     * firstVisibleItem 表示在当前屏幕显示的第一个listItem在整个listView里面的位置（下标从0开始）
     * visibleItemCount表示在现时屏幕可以见到的ListItem(部分显示的ListItem也算)总数
     * totalItemCount表示ListView的ListItem总数
     * listView.getLastVisiblePosition()表示在现时屏幕最后一个ListItem
     * (最后ListItem显示出来一点都算)在整个ListView的位置（下标从0开始）
     */
    public void onScroll(AbsListView arg0, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisiableItem = firstVisibleItem;
        this.totalItemCount = totalItemCount;
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
    }

    private int scrollState;
    private int firstVisiableItem;
    private int totalItemCount;
    private int lastVisibleItem;

    /**
     * scrollState有三种状态，分别是SCROLL_STATE_IDLE、SCROLL_STATE_TOUCH_SCROLL、
     * SCROLL_STATE_FLING SCROLL_STATE_IDLE是当屏幕停止滚动时
     * SCROLL_STATE_TOUCH_SCROLL是当用户在以触屏方式滚动屏幕并且手指仍然还在屏幕上时（The user is scrolling
     * using touch, and their finger is still on the screen）
     * SCROLL_STATE_FLING是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时（The user had previously
     * been scrolling using touch and had performed a fling）
     */

    public void onScrollStateChanged(AbsListView arg0, int arg1) {
        scrollState = arg1;
        // if (scrollState == SCROLL_STATE_TOUCH_SCROLL && totalItemCount ==
        // lastVisibleItem) {
        // footerView.findViewById(R.id.footer_layout).setVisibility(View.VISIBLE);
        // } else if (scrollState == SCROLL_STATE_IDLE) {
        // footerView.findViewById(R.id.footer_layout).setVisibility(View.GONE);
        // }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (canRefresh) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (firstVisiableItem == 0 && !isRecored) {
                        Log.v(TAG, "TouchDown且firstVisiableItemIndex=0记录当前位置");
                        isRecored = true;
                        startY = (int) event.getY();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (state == PULL_REFRESH) {
                        state = DONE;
                        changeHeaderViewByState();
                        Log.v(TAG, "由下拉刷新状态，到DONE状态");
                    }
                    if (state == RELEASE_REFRESH) {
                        state = REFRESHING;
                        changeHeaderViewByState();
                        onRefresh();
                        Log.v(TAG, "由松开刷新状态，到DONE状态");
                    }
                    isRecored = false;
                    isBack = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    int tempY = (int) event.getY();
                    if (firstVisiableItem == 0 && !isRecored) {
                        Log.v(TAG, "TouchMove且firstVisiableItemIndex=0记录当前位置");
                        isRecored = true;
                        startY = tempY;
                    }
                    if (isRecored && state != REFRESHING && state != LOADING) {
                        // 保证在设置padding的过程中，当前的位置一直是在head，否则如果当列表超出屏幕的话，当在上推的时候，列表会同时进行滚动
                        // 可以松手去刷新了
                        if (state == RELEASE_REFRESH) {
                            // setSelection(0);

                            if (((tempY - startY) / RATIO < headContentHeight) && (tempY - startY) > 0) { // 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
                                state = PULL_REFRESH;
                                changeHeaderViewByState();
                                Log.v(TAG, "由松开刷新状态转变到下拉刷新状态");
                            } else if (tempY - startY <= 0) { // 一下子推到顶了
                                state = DONE;
                                changeHeaderViewByState();
                                Log.v(TAG, "由松开刷新状态转变到DONE状态");
                            } else {
                                // 往下拉了，或者还没有上推到屏幕顶部掩盖head的地步
                                // 不用进行特别的操作，只用更新paddingTop的值就行了
                            }
                        }

                        // 还没有到达显示松开刷新的时候,DONE或者是PULL_To_REFRESH状态
                        if (state == PULL_REFRESH) {
                            setSelection(0);
                            // 下拉到可以进入RELEASE_TO_REFRESH的状态
                            if ((tempY - startY) / RATIO >= headContentHeight) {
                                state = RELEASE_REFRESH;
                                isBack = true;
                                changeHeaderViewByState();
                                Log.v(TAG, "由DONE或者下拉刷新状态转变到松开刷新");
                            }
                            // 上推到顶了
                            else if (tempY - startY <= 0) {
                                state = DONE;
                                changeHeaderViewByState();
                                Log.v(TAG, "由DONE或者下拉刷新状态转变到DONE状态");
                            }
                        }

                        // done状态下
                        if (state == DONE) {
                            if (tempY - startY > 0) {
                                state = PULL_REFRESH;
                                changeHeaderViewByState();
                            }
                        }

                        // 更新headView的size
                        if (state == PULL_REFRESH) {
                            headerView.setPadding(0, -1 * headContentHeight + (tempY - startY) / RATIO, 0, 0);

                        }

                        // 更新headView的paddingTop
                        if (state == RELEASE_REFRESH) {
                            headerView.setPadding(0, (tempY - startY) / RATIO - headContentHeight, 0, 0);
                        }
                    }


                    //
                    if (getLastVisiblePosition() == totalItemCount - 1) {
                        // last item, already pulled up or want to pull up.
                        footerView.setVisibility(View.VISIBLE);
                    } else {
                        footerView.setVisibility(View.GONE);
                    }
                    break;
            }
        }

        return super.onTouchEvent(event);
    }

    // 当状态改变时候调用来更新界面
    private void changeHeaderViewByState() {
        switch (state) {
            case RELEASE_REFRESH:

                Log.v(TAG, "当前状态，松开刷新");

                arrowImageView.setVisibility(View.VISIBLE);
                arrowImageView.clearAnimation();
                arrowImageView.startAnimation(animation);

                tipsTextview.setVisibility(View.VISIBLE);
                tipsTextview.setText("松开刷新");

                lastUpdatedTextView.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);

                break;
            case PULL_REFRESH:

                Log.v(TAG, "当前状态，下拉刷新");

                arrowImageView.setVisibility(View.VISIBLE);
                arrowImageView.clearAnimation();

                tipsTextview.setVisibility(View.VISIBLE);

                tipsTextview.setText("下拉刷新");
                lastUpdatedTextView.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);

                // 是由RELEASE_To_REFRESH状态转变来的
                if (isBack) {
                    isBack = false;
                    arrowImageView.clearAnimation();
                    arrowImageView.startAnimation(reverseAnimation);
                }
                break;
            case REFRESHING:

                Log.v(TAG, "当前状态,正在刷新...");

                headerView.setPadding(0, 0, 0, 0);

                arrowImageView.clearAnimation();
                arrowImageView.setVisibility(View.GONE);

                tipsTextview.setText("正在刷新...");
                lastUpdatedTextView.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.VISIBLE);
                break;
            case DONE:

                Log.v(TAG, "当前状态，done");

                headerView.setPadding(0, -1 * headContentHeight, 0, 0);

                arrowImageView.clearAnimation();
                arrowImageView.setImageResource(R.drawable.arrow_down);

                tipsTextview.setText("下拉刷新");
                lastUpdatedTextView.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);

                break;
        }
    }

    public void setOnRefreshListener(OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
        canRefresh = true;
    }

    public interface OnRefreshListener {
        public void onRefresh();
    }

    public void onRefreshComplete() {
        state = DONE;
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        String date = format.format(new Date());
        lastUpdatedTextView.setText("最近更新:" + date);
        changeHeaderViewByState();
    }

    private void onRefresh() {
        if (refreshListener != null) {
            refreshListener.onRefresh();
        }
    }

    // 此方法直接照搬自网络上的一个下拉刷新的demo，此处是“估计”headView的width以及height
    private void measureView(View view) {
        ViewGroup.LayoutParams playoutParams = view.getLayoutParams();
        if (playoutParams == null) {
            playoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int width = ViewGroup.getChildMeasureSpec(0, 0, playoutParams.width);
        int height;
        int tempHeight = playoutParams.height;
        if (tempHeight > 0) {
            height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(width, height);
    }

    public void setAdapter(BaseAdapter adapter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        String date = simpleDateFormat.format(new Date());
        lastUpdatedTextView.setText("最近更新:" + date);
        super.setAdapter(adapter);
    }

}
