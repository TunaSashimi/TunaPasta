package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.tunaPasta17.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioMappedTest extends Activity {

    private static final String TAG = "NioMappedTest";
    String fileInputStreamName = "file01.txt";
    String fileOutputStreamName = "/mnt/sdcard/nemesisJS01_file02.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niomappedtest);

        //以复制一个大小为2.5M的apk文件为例。
        //传统IO方式：耗时94ms
        long startTime01 = System.currentTimeMillis();
        try {
            FileInputStream input = new FileInputStream(fileInputStreamName);
            BufferedInputStream inbuff = new BufferedInputStream(input);
            FileOutputStream out = new FileOutputStream(fileOutputStreamName);
            BufferedOutputStream outbuff = new BufferedOutputStream(out);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = inbuff.read(b)) != -1) {
                outbuff.write(b, 0, len);
            }
            outbuff.flush();
            outbuff.close();
            out.close();
            inbuff.close();
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis() - startTime01;
            Log.d(TAG, "传统IO方式：耗时" + endTime + "ms");
        }

        // nio通道方式：耗时79ms
        long startTime02 = System.currentTimeMillis();
        try {
            //文件(输入、输出) Stream流
            FileInputStream fis = new FileInputStream(fileInputStreamName);
            FileOutputStream fos = new FileOutputStream(fileOutputStreamName);
            //文件(输入、输出) Channel通道
            FileChannel fcIn = fis.getChannel();
            FileChannel fcOut = fos.getChannel();
            //从文件输入通道传输byte数据到文件输出通道
            fcIn.transferTo(0, fcIn.size(), fcOut);
            //关闭文件(输入、输出) Channel通道
            fcOut.close();
            fcIn.close();
            //关闭文件(输入、输出) Stream流
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis() - startTime02;
            Log.d(TAG, "nio通道方式：耗时" + endTime + "ms");
        }

        //内存映射文件方式：耗时35ms
        long startTime = System.currentTimeMillis();
        try {
            //创建源文件和目标文件的随机访问文件对象
            RandomAccessFile raf = new RandomAccessFile(fileInputStreamName, "r");
            RandomAccessFile wraf = new RandomAccessFile(fileOutputStreamName, "rw");
            //创建文件Channel通道
            FileChannel in = raf.getChannel();
            FileChannel out = wraf.getChannel();
            //源文件的通道大小
            long size = in.size();
            //内存映射通道
            MappedByteBuffer inBf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
            //写入到输出通道
            out.write(inBf);
            //关闭输入输出通道
            in.close();
            out.close();
            //关闭随机访问文件对象
            raf.close();
            wraf.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis() - startTime;
            Log.d(TAG, "内存映射文件方式：耗时" + endTime + "ms");
        }

        //通过以上的示例，可以看出对文件进行复制操作，内存映射文件的方式是最快的，
        // nio通道方式仅次其后，传统IO方式相对前面两种较慢。
        // 但是，内存映射文件和nio通道的方式都是基于传统IO的方式进行衍生而来。

        //读文件

        File file = null;

        try {

            RandomAccessFile rRaf = new RandomAccessFile(file, "r");
            FileChannel fcIn = rRaf.getChannel();
            long size = 0;

            size = fcIn.size();

            int len = (int) file.length();
            byte[] bytes = new byte[len];
            MappedByteBuffer inBf = fcIn.map(FileChannel.MapMode.READ_ONLY, 0, size);
            for (int i = 0; i < len; i++) {
                bytes[i] = inBf.get(i);//取出每个元素
            }
            String str = new String(bytes, "utf-8");
            fcIn.close();
            rRaf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //写文件
//        RandomAccessFile wRaf = new RandomAccessFile(file, "rw");
//        FileChannel fcOut = wRaf.getChannel();
//        //fcOut.position(fc.size()); // 移动到文件末尾，进行追加
//        fcOut.write(ByteBuffer.wrap(encrypt.getBytes()));
//        fcOut.close();
//        wRaf.close();
    }
}
