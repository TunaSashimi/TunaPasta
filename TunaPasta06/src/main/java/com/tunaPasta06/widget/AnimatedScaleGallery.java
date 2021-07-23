package com.tunaPasta06.widget;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
public class AnimatedScaleGallery extends Gallery implements OnItemSelectedListener{
	private Camera mCamera = new Camera();//相机类
    public  int mMaxRotationAngle = 200;//最大转动角度
    private int mMaxZoom = 780;////最大缩放值
    private int mCoveflowCenter;//半径值
    public AnimatedScaleGallery(Context context) {
        super(context);
        //支持转换 ,执行getChildStaticTransformation方法
        setOnItemSelectedListener(this);
        this.setStaticTransformationsEnabled(true);
    }
    
    public AnimatedScaleGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnItemSelectedListener(this);
        this.setStaticTransformationsEnabled(true);
    }
    
    public AnimatedScaleGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnItemSelectedListener(this);
        this.setStaticTransformationsEnabled(true);
    }
    protected int getChildDrawingOrder(int childCount, int i) {
        int selectedIndex = getSelectedItemPosition() - getFirstVisiblePosition();

        // Just to be safe
        if (selectedIndex < 0)
            return i;

        if (i == childCount - 1) {
            // Draw the selected child last
            return selectedIndex;
        } else if (i >= selectedIndex) {

            return childCount - 1 - (i - selectedIndex);
        } else {
            return i;
        }
    }
    /**
     * 获取旋转最大角度
     * @return
     */
    public int getMaxRotationAngle() {
        return mMaxRotationAngle;
    }
    
    /**
     * 设置旋转最大角度
     * @param maxRotationAngle
     */
    public void setMaxRotationAngle(int maxRotationAngle) {
        mMaxRotationAngle = maxRotationAngle;
    }
    
    /**
     * 获取最大缩放值
     * @return
     */
    public int getMaxZoom() {
        return mMaxZoom;
    }
    
    /**
     * 设置最大缩放值
     * @param maxZoom
     */
    public void setMaxZoom(int maxZoom) {
        mMaxZoom = maxZoom;
    }
    /**
     * 获取半径值
     * @return
     */
    private int getCenterOfCoverflow() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
                        + getPaddingLeft();
    }
    
    /**
     * 
     * @param view
     * @return
     */
    private static int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth() / 2;
    }
    
    
   //控制gallery中每个图片的旋转(重写的gallery中方法)
    protected boolean getChildStaticTransformation(View child, Transformation t) {  
        //取得当前子view的半径值
        final int childCenter = getCenterOfView(child);
        final int childWidth = child.getWidth();
        //旋转角度
        int rotationAngle = 0;
        //重置转换状态
        t.clear();
        //设置转换类型
        t.setTransformationType(Transformation.TYPE_MATRIX);
        //如果图片位于中心位置不需要进行旋转
        if (childCenter == mCoveflowCenter) {
        } else {
            rotationAngle = (int) (((float) (mCoveflowCenter - childCenter) / childWidth) * mMaxRotationAngle);
            transformImageBitmap((ImageView) child, t, Math.abs(rotationAngle));
        }
        return true;
    }
    
    /**
     * 
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCoveflowCenter = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }
    
    
    private void transformImageBitmap(ImageView child, Transformation t,
                    int rotationAngle) {
        //对效果进行保存
        mCamera.save();
        final Matrix imageMatrix = t.getMatrix();
        //图片高度
        final int imageHeight = child.getLayoutParams().height;
        //图片宽度
        final int imageWidth = child.getLayoutParams().width;
        
        //返回旋转角度的绝对值
        final int rotation = Math.abs(rotationAngle);
        
        // 在Z轴上正向移动camera的视角，实际效果为放大图片。
        // 如果在Y轴上移动，则图片上下移动；X轴上对应图片左右移动。
       
        // As the angle of the view gets less, zoom in
        //正中间的图
        if (rotation==0) {
            //-200放大
          //  mCamera.translate(0.0f, 0.0f, -200f);
        }
         //侧面的图
        else{
        	//800是缩小
        	  mCamera.translate(0.0f, 0.0f, rotationAngle*3);
        }
        mCamera.getMatrix(imageMatrix);
         imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
         imageMatrix.postTranslate((imageWidth / 2), (imageHeight / 2));
        mCamera.restore();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}
}

