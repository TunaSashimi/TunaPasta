package com.tunaPasta04.util;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.tunaPasta04.R;


public class Util {

    public static String convertFileSize(long fileSize) {
        String newSize = "";
        if (fileSize < 1024) {
            newSize = fileSize + "B";
        } else if (fileSize >= 1024 && fileSize < 1024 * 1024) {
            newSize = String.valueOf(fileSize / 1024) + "K";
        } else {
            DecimalFormat format = new DecimalFormat("0.00");
            String result = format.format((double) fileSize / (1024 * 1024));
            newSize = result + "M";
        }

        return newSize;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);  
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap resizeBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = (float) newWidth / width;
        float scaleHeight = (float) newHeight / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedMap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);

        return resizedMap;
    }

    public static ArrayList arrayToList(Object[] objs) {
        ArrayList al = new ArrayList();
        for (Object obj : objs) {
            al.add(obj);
        }
        return al;
    }

    public static ArrayList combineArrayList(ArrayList a1, ArrayList a2) {
        for (Object obj : a2) {
            a1.add(obj);
        }
        return a1;
    }

    public static String getMIMEType(File f) {
        String type = "";
        String fileName = f.getName();
        String end = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio";
        } else if (end.equals("3gp") || end.equals("mp4") || end.equals("avi")) {
            type = "video";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp") || end.equals("ico")) {
            type = "image";
        } else if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else {
            type = "*";
        }
        if (!end.equals("apk"))
            type += "/*";
        return type;
    }

    public static Bitmap getFileIcon(Context context, File file) {
        if (file.isDirectory()) {
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.common_folder);
        }
        String fileName = file.getName();
        Bitmap bm = null;
        String end = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.music_style1);
        } else if (end.equals("apk")) {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageArchiveInfo(file.getPath(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                ApplicationInfo info = pi.applicationInfo;
                Drawable icon = pm.getApplicationIcon(info);
                bm = Util.resizeBitmap(Util.drawableToBitmap(icon), 32, 32);
            } else
                bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.apk);
        } else if (end.equals("text") || end.equals("txt")) {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.text);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp") || end.equals("ico")) {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.image);
        } else if (end.equals("rar") || end.equals("zip")) {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.rar);
        } else if (end.equals("jar")) {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.jar);
        } else if (end.equals("rmvb") || end.equals("avi") || end.equals("mp4")) {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.media);
        } else if (end.equals("doc")) {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.word);
        } else if (end.equals("xls")) {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.excel);
        } else {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.unknow);
        }
        return bm;
    }

    public static String getDateString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
