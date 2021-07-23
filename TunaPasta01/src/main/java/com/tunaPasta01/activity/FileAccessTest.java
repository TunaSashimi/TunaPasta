package com.tunaPasta01.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta01.R;
import com.tunaPasta01.entity.FileItem;
import com.tunaPasta01.entity.MetaData;
import com.tunaPasta01.tool.Constant;

public class FileAccessTest extends Activity {
    private ImageView imageView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileaccesstest);

        imageView = findViewById(R.id.filemage01);
        linearLayout = findViewById(R.id.linearlayout);

        //在代码中使用LayoutAnimationController
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.toasttest_anim_set);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        //~设置动画间的显示延迟,没有的话就同时显示效果!
        layoutAnimationController.setDelay(0.5f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(layoutAnimationController);
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.button01:
                readAssetsFile();
                break;
            case R.id.button02:
                writeSystemFile();
                break;
            case R.id.button03:
                readSystemFile();
                break;
            case R.id.button04:
                textSystemFile();
                break;
            case R.id.button05:
                writeSDCardFile();
                break;
            case R.id.button06:
                readSDCardFile();
                break;
            case R.id.button07:
                readSDCardImage();
                break;
            case R.id.button08:
                openAnyType();
                break;
            case R.id.button09:
                useOrgGsonReadJson();
                break;
            default:
                break;
        }
    }

    //读取Assets文件~
    private void readAssetsFile() {
        AssetManager as = this.getAssets();
        try {
            InputStream is = as.open("assets.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuffer s = new StringBuffer();
            String str = null;
            while ((str = br.readLine()) != null) {
                s.append(str + "\n");
            }
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //比较有用的功能,往手机内存写文件
    //所属位置是	/data/data/项目类名/		真机查看需要要获取root~
    private void writeSystemFile() {
        PrintWriter pw = null;
        try {
            FileOutputStream fos = openFileOutput("file01.txt", Context.MODE_PRIVATE);
            pw = new PrintWriter(fos);
            pw.println("文件名称file01.txt");
            pw.println("我写的第一个写入System的文件:");
            pw.flush();
            Toast.makeText(this, "write to File System completed!", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    //从手机内存读文件~
    private void readSystemFile() {
        try {
            FileInputStream fis = this.openFileInput("file01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            StringBuffer sb = new StringBuffer();
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str + "\n");
            }
            Toast.makeText(this, sb, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //判断手机内存上是否存在文件~
    private void textSystemFile() {
        Toast.makeText(this, this.getFilesDir() + "之前写入的文件存在路径" + new File(this.getFilesDir() + "/file01.txt").exists(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, this.getCacheDir() + "之前写入的文件存在路径" + new File(this.getCacheDir() + "/file01.txt").exists(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, "只要不清除数据就一直存在", Toast.LENGTH_LONG).show();
    }

    private void writeSDCardFile() {
        PrintWriter pw = null;
        try {
            FileOutputStream fos = new FileOutputStream("/mnt/sdcard/nemesisJS01_file02.txt");
            pw = new PrintWriter(fos);
            pw.println("在SD卡中写文件,要先在AndroidManifest中进行权限设置");
            pw.flush();
            Toast.makeText(this, "write  File to SDCard completed!", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    private void readSDCardFile() {
        try {
            FileInputStream fis = new FileInputStream("/mnt/sdcard/nemesisJS01_file02.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            StringBuffer sb = new StringBuffer();
            String str = null;
            int i = 0;
            while ((str = br.readLine()) != null && i <= 3) {
                sb.append(str + "\n");
                i++;
            }
            br.close();
            Toast.makeText(this, "read from SD Card:\n" + sb.toString(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    注意区别
//    getExternalCacheDir()  ==>	/mnt/sdcard/Android/data/com.nemesisJS05.activity/cache
//		Environment.getExternalStorageDirectory()  ==>	 /mnt/sdcard

    private void readSDCardImage() {

        copyAssetsPhoto2SDCard();

        try {
            FileInputStream fis = new FileInputStream(getExternalCacheDir() + "/eat.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            imageView.setImageBitmap(bitmap);
            imageView.invalidate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //将assets中的文件拷贝到sd卡中,注意如果有文件夹,要先创建文件夹不然报错: java.io.FileNotFoundException: /mnt/sdcard/word.docx (No such file or directory)
    private void copyAssetsPhoto2SDCard() {
        AssetManager assetmanager = this.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetmanager.open("eat.jpg");
            out = new FileOutputStream(getExternalCacheDir() + "/eat.jpg");
            //copyFile
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openAnyType() {

        copyAssetsFile2SDCard();

        openFileType(new File(getExternalCacheDir() + "/word.docx"));//因为内存文件中无法直接读取
    }

    //将assets中的文件拷贝到sd卡中,注意如果有文件夹,要先创建文件夹不然报错: java.io.FileNotFoundException: /mnt/sdcard/word.docx (No such file or directory)
    private void copyAssetsFile2SDCard() {
        AssetManager assetmanager = this.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetmanager.open("word.docx");
            out = new FileOutputStream(getExternalCacheDir() + "/word.docx");
            //copyFile
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开各类文件
     */
    private void openFileType(File file) {
        Toast.makeText(this, "文件正在打开,请稍后", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), getFileType(file));
        //这里最好try一下，有可能会报错。 //比如说你的File类型是打开邮箱，但是你手机里面没装邮箱客户端，就会报错。
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "文件没有可关联的应用可以打开", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 根据文件后缀名获得对应的FileType类型。
     */
    private String getFileType(File file) {
        String type = "*/*";
        String fName = file.getName();
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") return type;

        //在匹配表中找到对应的File类型。
        for (int i = 0; i < Constant.FileTypes.length; i++) {
            if (end.equals(Constant.FileTypes[i][0]))
                type = Constant.FileTypes[i][1];
        }
        return type;
    }

    //	用org.json包读JSON
    private void useOrgGsonReadJson() {
        try {
            InputStream is = this.getAssets().open("json.txt");
            //注意,编码类型要跟json00.txt一致~~注意,如果eclipse与json00的编码不一致,则报错~
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuffer s = new StringBuffer();
            String str = null;
            while ((str = br.readLine()) != null) {
                s.append(str);
            }
            System.out.println("得到的数据为" + s.toString());
            JSONObject result = new JSONObject(s.toString());//转换为JSONObject
            int num = result.length();
            System.out.println("得到的json对象的长度为" + num);
            //直接形式的JSON读取
            System.out.println(result.get("FLAG"));
            System.out.println(result.get("name"));
            //数组形式的JSON读取
            JSONArray nameList = result.getJSONArray("name");//获取JSONArray
            int length = nameList.length();
            System.out.println("得到的json数组的长度为" + length);
            String last = "";
            for (int i = 0; i < length; i++) {//遍历JSONArray
                JSONObject oj = nameList.getJSONObject(i);
                last += oj.getString("name") + "|";
            }
            System.out.println(last);
            Toast.makeText(this, last, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //	用com.google.gson包读JSON
    private void useGoogleGsonReadJson() {

        FileItem fileItem = null;
        MetaData lookletarticle = null;

        try {
            InputStream is = this.getAssets().open("gson.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuffer buf = new StringBuffer();
            String str;
            while ((str = br.readLine()) != null) {
                buf.append(str);
            }

            org.json.JSONArray jsonArray = new org.json.JSONArray(buf.toString());

            label:
            for (int i = 0; i < jsonArray.length(); i++) {

                org.json.JSONObject oj = jsonArray.getJSONObject(i);

                // 只要json串总包含"白色"这个字段就自动设置fileItem和lookletartucle值并跳出循环
                if (oj.toString().contains("白色")) {
                    com.google.gson.GsonBuilder gsonb = new com.google.gson.GsonBuilder();
                    com.google.gson.Gson gson = gsonb.create();
                    // 用gson工具设置fileItem的值
                    fileItem = gson.fromJson(oj.toString(), FileItem.class);
                    // 用gson工具设置lookletarticle的值
                    lookletarticle = gson.fromJson(fileItem.lookletArticleMetaData, MetaData.class);
                    break label;
                }
            }

            View view = LayoutInflater.from(FileAccessTest.this).inflate(R.layout.fileaccesstestitem, null);

            Dialog dialog = new Dialog(FileAccessTest.this, R.style.NoTitleDialog);

            TextView textview01 = view.findViewById(R.id.textview01);
            TextView textview02 = view.findViewById(R.id.textview02);
            TextView textview03 = view.findViewById(R.id.textview03);
            TextView textview04 = view.findViewById(R.id.textview04);
            TextView textview05 = view.findViewById(R.id.textview05);
            TextView textview06 = view.findViewById(R.id.textview06);
            TextView textview07 = view.findViewById(R.id.textview07);
            TextView textview08 = view.findViewById(R.id.textview08);
            TextView textview09 = view.findViewById(R.id.textview09);
            TextView textview10 = view.findViewById(R.id.textview10);
            TextView textview11 = view.findViewById(R.id.textview11);
            TextView textview12 = view.findViewById(R.id.textview12);
            TextView textview13 = view.findViewById(R.id.textview13);
            TextView textview14 = view.findViewById(R.id.textview14);
            TextView textview15 = view.findViewById(R.id.textview15);
            TextView textview16 = view.findViewById(R.id.textview16);
            TextView textview17 = view.findViewById(R.id.textview17);
            TextView textview18 = view.findViewById(R.id.textview18);
            TextView textview19 = view.findViewById(R.id.textview19);
            TextView textview20 = view.findViewById(R.id.textview20);
            TextView textview21 = view.findViewById(R.id.textview21);

            textview01.setText("根JSON对象");
            textview02.setText(fileItem.name);
            textview03.setText(fileItem.code);
            textview04.setText(fileItem.price);
            textview05.setText(fileItem.swatch.toString());
            textview06.setText(fileItem.swatch.col);
            textview07.setText(fileItem.swatch.fab);
            textview08.setText(fileItem.swatchName);
            textview09.setText(fileItem.swatch.smallFab);
            textview10.setText(fileItem.lookletArticleMetaData);

            textview11.setText("根JSON对象中的lookletArticleMetaData");
            textview12.setText(lookletarticle.id);
            textview13.setText(lookletarticle.pose);
            textview14.setText(lookletarticle.category);
            textview15.setText(lookletarticle.categoryType);
            textview16.setText(lookletarticle.colors.toString());
            textview17.setText(lookletarticle.colors.get(0));

            textview18.setText("根JSON对象中的lookletArticleMetaData中的Variants");
            textview19.setText(lookletarticle.variants.get(0).id);
            textview20.setText(lookletarticle.variants.get(0).view.front.image);
            textview21.setText(lookletarticle.variants.get(0).view.front.crop.height);

            dialog.setContentView(view);
            dialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}