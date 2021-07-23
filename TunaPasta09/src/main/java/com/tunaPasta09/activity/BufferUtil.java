package com.tunaPasta09.activity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtil {
	
	//OpenGL 是一个非常底层的画图接口，它所使用的缓冲区存储结构是和我们的 java 程序中不相同的。
	//Java 是大端字节序(BigEdian)，而 OpenGL 所需要的数据是小端字节序(LittleEdian)。所以，我们在将 Java 的缓冲区转化为 OpenGL 可用的缓冲区时需要作一些工作。
	
	public static FloatBuffer floatBuffer;

	public static FloatBuffer fBuffer(float[] a) {
		// 先初始化buffer,数组的长度*4,因为一个float占4个字节
		ByteBuffer mbb = ByteBuffer.allocateDirect(a.length * 4);
		// 数组排列用nativeOrder
		mbb.order(ByteOrder.nativeOrder());
		floatBuffer = mbb.asFloatBuffer();
		floatBuffer.put(a);
		floatBuffer.position(0);
		return floatBuffer;
	}
	
	
    public static IntBuffer iBuffer(int []arr){  
        IntBuffer mBuffer ;          
        //先初始化buffer,数组的长度*4,因为一个int占4个字节  
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);  
        //数组排列用nativeOrder  
        qbb.order(ByteOrder.nativeOrder());  
         
        mBuffer = qbb.asIntBuffer();  
        mBuffer.put(arr);  
        mBuffer.position(0);  
          
        return mBuffer;  
   }
	
}