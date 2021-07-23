package com.tunaPasta15.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class VerticalScrollTextView extends TextView {  
	  
    private float step =0f;     
    private Paint mPaint=new Paint(); ;   
    private String text;   
    private float width;   
    private List<String> textList = new ArrayList();    //分行保存textview的显示信息。
   
    public VerticalScrollTextView(Context context, AttributeSet attrs) {   
        super(context, attrs);           
    }   
       
   
    public VerticalScrollTextView(Context context) {   
        super(context);           
    }   
       
    @Override   
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {           
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);   
        width = MeasureSpec.getSize(widthMeasureSpec);      
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);     
        if (widthMode != MeasureSpec.EXACTLY) {      
            throw new IllegalStateException("ScrollLayout only canmCurScreen run at EXACTLY mode!");   
        }         
         
        text=getText().toString();  
        if(text==null|text.length()==0){   
                 
            return ;   
        }         
           
       //下面的代码是根据宽度和字体大小，来计算textview显示的行数。   
   
        textList.clear();   
        StringBuilder builder =null;  
        for(int i=0;i<text.length();i++){  
            if(i%12==0){  
                builder = new StringBuilder();   
            }  
             if(i%12<=11){   
                 builder.append(text.charAt(i));   
             }  
             if(i%12==11){   
                 textList.add(builder.toString());   
             }  
               
        }  
        Log.e("textviewscroll",""+textList.size());   
         

    }   
   
   
    //下面代码是利用上面计算的显示行数，将文字画在画布上，实时更新。   
     @Override   
    public void onDraw(Canvas canvas) {   
       if(textList.size()==0)  return;   
         
       mPaint.setTextSize(40f);//设置字体大小  
        for(int i = 0; i < textList.size(); i++) {   
            canvas.drawText(textList.get(i), 0, this.getHeight()+(i+1)*mPaint.getTextSize()-step+30, mPaint);   
        }   
        invalidate();      
          
        step = step+0.3f;   
        if (step >= this.getHeight()+textList.size()*mPaint.getTextSize()) {   
            step = 0;   
        }           
    }   
   
}  