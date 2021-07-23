package com.tunaPasta09.widget;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Handler;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘管理类
 * @author zhangmian
 */
public class SoftKeyboardManager {

    private SharedPreferences mSharedPreferences;
	private InputMethodManager mInputMM;
	private Rect mVisRect = new Rect();
	private Handler mLocalHandler = new Handler();
	private View mRoot;
    private int mSoftKeyboardHeight;
    private int mStatusBarHeight;
	
	private boolean isShow = false;
    private List<Runnable> mAfterKeyboardShownList = new ArrayList();
	private Runnable mAfterKeyboardShownOnce = null;
	private List<Runnable> mAfterKeyboardHiddenList = new ArrayList();
	private Runnable mAfterKeyboardHiddenOnce = null;

	public SoftKeyboardManager(Context context, final View root){
		mInputMM = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		mRoot = root;
        initSizeUtil(context);
		root.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			int lastRootHeight = -1;

			@Override
			public void onGlobalLayout() {
				//r will be populated with the coordinates of your view that area still visible.
				root.getWindowVisibleDisplayFrame(mVisRect);
				int currentRootHeight = root.getRootView().getHeight() - (mVisRect.bottom - mVisRect.top) - mStatusBarHeight;
				if (currentRootHeight == lastRootHeight)
					return;
				if (currentRootHeight > 0) { // if more than 100 pixels, its probably a keyboard...
					isShow = true;
					if (mAfterKeyboardShownOnce != null) {
						mLocalHandler.post(mAfterKeyboardShownOnce);
						mAfterKeyboardShownOnce = null;
					}
					if (mAfterKeyboardShownList != null) {
						for (int i = 0; i < mAfterKeyboardShownList.size(); i++) {
							mLocalHandler.post(mAfterKeyboardShownList.get(i));
						}
					}
					if (mSoftKeyboardHeight != currentRootHeight) {
						setSoftKeyboardHeight(currentRootHeight);
					}
				} else {
					isShow = false;
					if (mAfterKeyboardHiddenOnce != null) {
						mLocalHandler.post(mAfterKeyboardHiddenOnce);
						mAfterKeyboardHiddenOnce = null;
					}
					if (mAfterKeyboardHiddenList != null) {
						for (int i = 0; i < mAfterKeyboardHiddenList.size(); i++) {
							mLocalHandler.post(mAfterKeyboardHiddenList.get(i));
						}
					}
				}
				lastRootHeight = currentRootHeight;
			}
		}); 
	}

    private static final String SHARED_PREF_NAME = "SoftKeyboardManager";
    private static final String SOFT_KEYBOARD_HEIGHT = "SoftKeyboardHeight";

    private void initSizeUtil(Context context){
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            mStatusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        int defaultSoftKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, context.getResources().getDisplayMetrics());
        mSoftKeyboardHeight = mSharedPreferences.getInt(SOFT_KEYBOARD_HEIGHT, defaultSoftKeyboardHeight);
    }

    private void setSoftKeyboardHeight(int softKeyboardHeight){
        mSoftKeyboardHeight = softKeyboardHeight;
        mSharedPreferences.edit().putInt("SoftKeyboardHeight", mSoftKeyboardHeight).commit();
    }

    public int getSoftkeyboardHeight(){
        return mSoftKeyboardHeight;
    }

    public void addAfterKeyboardShownRunnable(Runnable runnable){
        mAfterKeyboardShownList.add(runnable);
    }

	public void addAfterKeyboardHiddenRunnable(Runnable runnable){
		mAfterKeyboardHiddenList.add(runnable);
	}
	/**
	 * 返回当前键盘是否显示
	 * @return
	 */
	public boolean isKeyboardShown(){
		return isShow;
	}
	
	/**
	 * 屏蔽点击EditText后,弹出键盘
	 * @param et 相应的EditText
	 */
	public void ignoreShowKeyboard(EditText et){
		if (android.os.Build.VERSION.SDK_INT <= 10) {
            et.setInputType(InputType.TYPE_NULL);
        } else {
            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(et, false);
            } catch (Exception e) {
//                Logger.LogD(SoftKeyboardManager.class.getSimpleName(), e.getMessage());
            	e.printStackTrace();
            }
        }
	}
	
	public void showKeyboard(View v){
		v.requestFocus();
		if(v instanceof EditText){
			((EditText)v).setSelection(((EditText) v).getText().length());
		}
		mInputMM.showSoftInput(v, 0);
	}
	
	/**
	 * 键盘显示后,运行runnable
	 * @param v
	 * @param runnable
	 */
	public void showKeyboard(View v, Runnable runnable){
		mAfterKeyboardShownOnce = runnable;
		showKeyboard(v);
	}
	
	public void hideKeyboard(View v){
		mInputMM.hideSoftInputFromWindow(v.getWindowToken(), 0);
//		mRoot.findFocus().clearFocus();
		mRoot.requestFocus();
	}
	
	public void hideKeyboard(){
		mInputMM.hideSoftInputFromWindow(mRoot.getWindowToken(), 0);
//		mRoot.findFocus().clearFocus();
		mRoot.requestFocus();
	}

    public void hideKeyboard(Runnable runnable){
        mAfterKeyboardHiddenOnce = runnable;
        hideKeyboard();
    }
	
	public void cleanFocus(View v){
		mRoot.requestFocus();
	}
	
	/**
	 * 键盘隐藏后,运行runnable
	 * @param v
	 * @param runnable
	 */
	public void hideKeyboard(View v, Runnable runnable){
		mAfterKeyboardHiddenOnce = runnable;
		hideKeyboard(v);
	}
}
