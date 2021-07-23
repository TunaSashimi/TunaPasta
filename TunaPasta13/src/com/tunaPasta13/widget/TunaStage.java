package com.tunaPasta13.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.SpinnerAdapter;

import com.tunaPasta13.R;
import com.tunaPasta13.adapter.TunaAbstractImageAdapter;
import com.tunaPasta13.adapter.NemesisReflectingImageAdapter;

public class TunaStage extends Gallery {

    /**
     * Graphics Camera used for transforming the matrix of ImageViews.
     */
    private final Camera mCamera = new Camera();

    /**
     * The maximum angle the Child ImageView will be rotated by.
     */
    private int mMaxRotationAngle = 10;

    /**
     * The maximum zoom on the centre Child.
     */
    private int mMaxZoom = -30;

    /**
     * The Centre of the Coverflow.
     */
    private int mCoveflowCenter;

    /** The image height. */
    private float imageHeight;

    /** The image width. */
    private float imageWidth;

    /** The reflection gap. */
    private float reflectionGap;

    /** The with reflection. */
    private boolean withReflection;

    /** The image reflection ratio. */
    private float imageReflectionRatio;

    /**
     * Gets the image height.
     * 
     * @return the image height
     */
    public float getImageHeight() {
        return imageHeight;
    }

    /**
     * Sets the image height.
     * 
     * @param imageHeight
     *            the new image height
     */
    public void setImageHeight(final float imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * Gets the image width.
     * 
     * @return the image width
     */
    public float getImageWidth() {
        return imageWidth;
    }

    /**
     * Sets the image width.
     * 
     * @param imageWidth
     *            the new image width
     */
    public void setImageWidth(final float imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * Gets the reflection gap.
     * 
     * @return the reflection gap
     */
    public float getReflectionGap() {
        return reflectionGap;
    }

    /**
     * Sets the reflection gap.
     * 
     * @param reflectionGap
     *            the new reflection gap
     */
    public void setReflectionGap(final float reflectionGap) {
        this.reflectionGap = reflectionGap;
    }

    /**
     * Checks if is with reflection.
     * 
     * @return true, if is with reflection
     */
    public boolean isWithReflection() {
        return withReflection;
    }

    /**
     * Sets the with reflection.
     * 
     * @param withReflection
     *            the new with reflection
     */
    public void setWithReflection(final boolean withReflection) {
        this.withReflection = withReflection;
    }

    /**
     * Sets the image reflection ratio.
     * 
     * @param imageReflectionRatio
     *            the new image reflection ratio
     */
    public void setImageReflectionRatio(final float imageReflectionRatio) {
        this.imageReflectionRatio = imageReflectionRatio;
    }

    /**
     * Gets the image reflection ratio.
     * 
     * @return the image reflection ratio
     */
    public float getImageReflectionRatio() {
        return imageReflectionRatio;
    }

    public TunaStage(final Context context) {
        super(context);
        this.setStaticTransformationsEnabled(true);
    }

    public TunaStage(final Context context, final AttributeSet attrs) {
        this(context, attrs, android.R.attr.galleryStyle);
    }

    public TunaStage(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaStage);

        imageWidth = typedArray.getDimension( R.styleable.TunaStage_imageWidth, 320);
        imageHeight = typedArray.getDimension( R.styleable.TunaStage_imageHeight, 480);
        withReflection = typedArray.getBoolean( R.styleable.TunaStage_withReflection, false);
        imageReflectionRatio = typedArray.getFloat( R.styleable.TunaStage_imageReflectionRatio, 0.2f);
        reflectionGap = typedArray.getDimension(R.styleable.TunaStage_reflectionGap, 4);

        setSpacing(-15);
        typedArray.recycle();

        this.setStaticTransformationsEnabled(true);
    }

    /**
     * Get the max rotational angle of the image.
     * 
     * @return the mMaxRotationAngle
     */
    public int getMaxRotationAngle() {
        return mMaxRotationAngle;
    }

    /**
     * Sets the adapter the new adapter
     */

    @Override
    public void setAdapter(final SpinnerAdapter spinnerAdapter) {
        if (!(spinnerAdapter instanceof TunaAbstractImageAdapter)) {
            throw new IllegalArgumentException(
                    "The adapter should derive from "
                            + TunaAbstractImageAdapter.class.getName());
        }
        final TunaAbstractImageAdapter coverAdapter = (TunaAbstractImageAdapter) spinnerAdapter;
        coverAdapter.setWidth(imageWidth);
        coverAdapter.setHeight(imageHeight);
        if (withReflection) {
            final NemesisReflectingImageAdapter reflectAdapter = new NemesisReflectingImageAdapter( coverAdapter);
            reflectAdapter.setReflectionGap(reflectionGap);
            reflectAdapter.setWidthRatio(imageReflectionRatio);
            reflectAdapter.setWidth(imageWidth);
            reflectAdapter.setHeight(imageHeight * (1 + imageReflectionRatio));
            super.setAdapter(reflectAdapter);
        } else {
            super.setAdapter(spinnerAdapter);
        }
    }

    /**
     * Set the max rotational angle of each image.
     * 
     * @param maxRotationAngle
     *            the mMaxRotationAngle to set
     */
    public void setMaxRotationAngle(final int maxRotationAngle) {
        mMaxRotationAngle = maxRotationAngle;
    }

    /**
     * Get the Max zoom of the centre image.
     * 
     * @return the mMaxZoom
     */
    public int getMaxZoom() {
        return mMaxZoom;
    }

    /**
     * Set the max zoom of the centre image.
     * 
     * @param maxZoom
     *            the mMaxZoom to set
     */
    public void setMaxZoom(final int maxZoom) {
        mMaxZoom = maxZoom;
    }

    /**
     * Get the Centre of the Coverflow.
     * //获取父控件中心点 X 的位置  
     * @return The centre of this Coverflow.
     */
    private int getCenterOfCoverflow() {
//      return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
        return ((getWidth() - getPaddingLeft() - getPaddingRight()) >> 1) + getPaddingLeft();  
    }

    /**
     * Get the Centre of the View.
     * 获取 child 中心点 X 的位置  
     * @return The centre of the given view.
     */
    private static int getCenterOfView(final View view) {
//      return view.getLeft() + view.getWidth() / 2;
        return view.getLeft() + (view.getWidth() >> 1);  
    }
    

    /**
     * {@inheritDoc}
     * 
     * @see #setStaticTransformationsEnabled(boolean)
     */
    @Override
    protected boolean getChildStaticTransformation(View child,  Transformation transformation) {
        transformation.clear();
        transformation.setTransformationType(Transformation.TYPE_MATRIX);

        int childCenter = getCenterOfView(child);
        int childWidth = child.getWidth();

        // 计算 child 偏离 父控件中心的 offset 值， -mMaxRotationAngle <= rotationAngle <=
        // mMaxRotationAngle
        float rotationAngle = (mCoveflowCenter - childCenter) * 1f / childWidth * mMaxRotationAngle;
        if (Math.abs(rotationAngle) > mMaxRotationAngle) {
            rotationAngle = rotationAngle < 0 ? -mMaxRotationAngle   : mMaxRotationAngle;
        }

        // 计算 child 偏离 父控件中心的 offset 值， -1 <= offset <= 1
        float offset = (childCenter - mCoveflowCenter) * 1f / mCoveflowCenter;
        offset = Math.min(offset, 1.0f);
        offset = Math.max(offset, -1.0f);

//        System.out.println("mCoveflowCenter====>" + mCoveflowCenter);
//        System.out.println("childWidth====>" + childWidth);
//        System.out.println("childCenter====>" + childCenter);
 //       System.out.println("offset====>" + offset);
 //       System.out.println("rotationAngle========>" + rotationAngle);

         transformViewRotation(child, transformation, rotationAngle);
 //            transformViewRotation(child, transformation, 0);
          //transformViewRoom(child, transformation, offset);
        return true;
    }
    
    
    void transformViewRoom(View child, Transformation transformation, float offset){  
        mCamera.save();  
        Matrix matrix = transformation.getMatrix();  
        
        int halfHeight = child.getLayoutParams().height>> 1;  
        int halfWidth = child.getLayoutParams().height >> 1;  
      
        // 平移 X、Y、Z 轴已达到立体效果  
        mCamera.translate(-offset * 50, 0.0f , Math.abs(offset) * 200);  
        //也可设置旋转效果  
        mCamera.getMatrix(matrix);  
        //以 child 的中心点变换  
        matrix.preTranslate(-halfWidth, -halfHeight);  
        matrix.postTranslate(halfWidth, halfHeight);  
        mCamera.restore();  
    }  
    
     void transformViewRotation(View child,  Transformation transformation,  float rotationAngle) {
        mCamera.save();
        Matrix imageMatrix = transformation.getMatrix();

         int height = child.getLayoutParams().height;

         int width = child.getLayoutParams().width;
         int rotation = (int) Math.abs(rotationAngle);

        mCamera.translate(0.0f, 0.0f, 100.0f);

        // As the angle of the view gets less, zoom in
        if (rotation < mMaxRotationAngle) {
            final float zoomAmount = (float) (mMaxZoom + rotation * 1.5);
            mCamera.translate(0.0f, 0.0f, zoomAmount);
        }

        mCamera.rotateY(rotationAngle);
        mCamera.getMatrix(imageMatrix);

        imageMatrix.preTranslate(-(width / 2.0f), -(height / 2.0f));
        imageMatrix.postTranslate((width / 2.0f), (height / 2.0f));

        mCamera.restore();
    }
    

    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     * 
     * @param w Current width of this view.
     * @param h Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        mCoveflowCenter = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    
    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
        return e2.getX() > e1.getX();
    }
    
    // e1是按下的事件，e2是抬起的事件
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int keyCode;
        if (isScrollingLeft(e1, e2)) {
            keyCode = KeyEvent.KEYCODE_DPAD_LEFT;
        } else {
            keyCode = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(keyCode, null);
        return true;
    }
}