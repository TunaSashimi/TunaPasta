package com.tunaPasta14.activity;

import java.io.File;
import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tunaPasta14.R;

public class BreakPointsResumeTest extends Activity {
    /**
     * 下载存放地："/sdcard/sodino/"。
     */
    public static final String RES_LOAD_FOLDER = File.separator + "sdcard" + File.separator + "sodino" + File.separator;
    /**
     * 刷新进度。
     */
    public static final int REFRESH = 1;
    public static final int CODE = 10;
    private BeanDownload bean;
    private TextView txtName;
    private TextView txtProgress;
    private TextView txtSize;
    private ProgressBar progressBar;
    private Button btnAction;
    private Handler handler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakpointsresumetest);

        bean = new BeanDownload();
        bean.name = "下载";
        // 请找个可以无需跳转直接下载的地址
        bean.url = "http://note.youdao.com/yws/public/resource/09a99199090b28e47235850f2f2d9d39/66F6E3A01BBA43F38737C08064DF10E3?keyfrom=public";
        bean.state = BeanDownload.STATE_INTERRUPTED;
        bean.size = bean.loadedSize = 0l;
        bean.enable = true;

        txtName = findViewById(R.id.txtName);
        txtName.setText(bean.name);
        txtProgress = findViewById(R.id.txtProgress);
        txtProgress.setText(getProgressTxt(bean));
        txtSize = findViewById(R.id.txtSize);
        txtSize.setText(formatSizeTxt(bean.size));
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(getProgressInt(bean, progressBar.getMax()));
        btnAction = findViewById(R.id.btnAction);
        btnAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("state:" + bean.state);
                switch (bean.state) {
                    case BeanDownload.STATE_LOADING:
                        // 点击了"暂停"
                        pauseDownload();
                        break;
                    case BeanDownload.STATE_INTERRUPTED:
                        // 点击了"继续"
                        doDownload();
                        break;
                    case BeanDownload.STATE_DOWNLOAD_FAIL:
                        // 点击了"重载"
                        reloadDownload();
                        break;
                    case BeanDownload.STATE_COMPLETED:
                        // 点击了"安装"
                        installDownload();
                        break;
                }
            }
        });
        btnAction.setText(getTxt(bean));
        btnAction.setEnabled(isEnable(bean));
        // handler
        handler = new Handler() {
            public void handleMessage(Message msg) {
                txtProgress.setText(getProgressTxt(bean));
                txtSize.setText(formatSizeTxt(bean.size));
                progressBar.setProgress(getProgressInt(bean, progressBar.getMax()));
                btnAction.setText(getTxt(bean));
                btnAction.setEnabled(isEnable(bean));
            }
        };

    }

    private void pauseDownload() {
        bean.enable = false;
        handler.sendEmptyMessage(REFRESH);
    }

    private void doDownload() {
        handler.sendEmptyMessage(REFRESH);
        new DownloadThread().start();
    }

    private void reloadDownload() {
        bean.size = bean.loadedSize = 0;
        bean.enable = true;
        doDownload();
    }

    private void installDownload() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String filePath = RES_LOAD_FOLDER + bean.name;
        intent.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        // 如果仅是简单的startActivity(intent),会造成onCreate()再执行一次。
        BreakPointsResumeTest.this.startActivityForResult(intent, CODE);
    }

    class DownloadThread extends Thread {
        public void run() {
            bean.state = BeanDownload.STATE_LOADING;
            bean.enable = true;
            NetworkTool.download2File(BreakPointsResumeTest.this, bean, handler);

            System.out.println("size:" + bean.size + " loaded:" + bean.loadedSize + " enable:" + bean.enable);
            //

            // 测试“重载”请释放下面代码的注释然后等待下载正常结束
            if (bean.size > 0 && bean.loadedSize == bean.size) {
                String localPath = RES_LOAD_FOLDER + bean.name;
                File tmpFile = new File(localPath + ".tmp");
                tmpFile.renameTo(new File(localPath));
                bean.enable = false;
                bean.state = BeanDownload.STATE_COMPLETED;
            } else {
                if (bean.enable == false) {
                    bean.state = BeanDownload.STATE_INTERRUPTED;
                } else {
                    bean.state = BeanDownload.STATE_DOWNLOAD_FAIL;
                }
            }
            // LogOut.out(this, "state=" + bean.state);
            System.out.println("state=" + bean.state);
            //
            handler.sendEmptyMessage(REFRESH);
        }
    }

    public static String getProgressTxt(BeanDownload bean) {
        String resStr = "0%";
        if (bean.size != 0) {
            double result = bean.loadedSize * 1.0 / bean.size;
            DecimalFormat decFormat = new DecimalFormat("#.#%");
            resStr = decFormat.format(result);
        }
        return resStr;
    }

    private String formatSizeTxt(long size) {
        String sizeTxt = "未知";
        if (size > 0) {
            size = size >> 10;
            sizeTxt = String.valueOf(size) + "k";
        }
        return sizeTxt;
    }

    public static int getProgressInt(BeanDownload bean, int max) {
        int result = (bean.size > 0) ? (int) (bean.loadedSize * max * 1.0 / bean.size) : 0;
        return result;
    }

    private String getTxt(BeanDownload bean) {
        String txt = "安装";
        switch (bean.state) {
            case BeanDownload.STATE_COMPLETED:
                txt = "安装";
                break;
            case BeanDownload.STATE_LOADING:
                txt = "暂停";
                break;
            case BeanDownload.STATE_INTERRUPTED:
                txt = "继续";
                break;
            case BeanDownload.STATE_DOWNLOAD_FAIL:
                txt = "重载";
                break;
        }
        return txt;
    }

    private boolean isEnable(BeanDownload bean) {
        boolean enable = true;
        if (bean.enable == false && bean.state == BeanDownload.STATE_LOADING) {
            enable = false;
        }
        return enable;
    }
}