package com.tunaPasta08.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tunaPasta08.R;
import com.tunaPasta08.entity.Actor;
import com.tunaPasta08.widget.MoudleMateView;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class P06_MoudleMateTest extends Activity {
    private MoudleMateView moudleMateView;
    private ImageView[] imfirst, imcolor;// imcolor为新导入的数组
    private ProgressDialog prodialog;
    private int[] hatcolor = {R.drawable.color01, R.drawable.color02,
        R.drawable.color03}, Tshirtcolor = {R.drawable.color02,
        R.drawable.color04}, coatcolor = {R.drawable.color05},
        pantscolor = {R.drawable.color05, R.drawable.color07},
        skirtcolor = {R.drawable.color03, R.drawable.color06};
    private String[] hatstring = {"粉红", "自然白", "米色"}, Tshirtstring = {"浅灰色",
        "卡其绿"}, coatstring = {"黑色"}, pantsstring = {"黑色", "茄紫色"},
        skirtstring = {"驼色", "红色"};
    private PopupWindow popwido, popwidoc; // 一级选项菜单popwido选品牌，二级选项菜单popwidoc选颜色
    private String txt; // 设置加载的asset文件名~
    private int screenWidth, screenHeight; // 按屏幕大小显示的布局
    private Handler handler;
    private double ratio;
    private Message mesg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p06_moudlematetest);
        moudleMateView = findViewById(R.id.view);

        Display display = getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();

        ratio = screenHeight * 0.80 / 0.67 / 814;// 屏幕中的模特全长与实际全长之比,原JSON中的body高度为814，即保存在本地图片比

        imfirst = new ImageView[8];// 给一级图片选项分配内存空间
        imcolor = new ImageView[3];// 给二级图片选项分配内存空间
        // 初始化一级菜单的各个选项
        for (int i = 0; i < imfirst.length; i++) {
            int id = this.getResources().getIdentifier("Im_0" + i, "id", "com.tunaPasta08");// 注意下这里的路径~
            imfirst[i] = findViewById(id);

            imfirst[i].setOnClickListener(new OnClickListener() {// 也就是说，每次点击的时候都会设定7个小分按钮的OnClick
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.Im_00:
                            P06_MoudleMateTest.this.finish();
                            break;
                        case R.id.Im_01:
                            getPopupWindow(R.drawable.b01d);
                            popwido.showAsDropDown(v, 50, -40);
                            break;
                        case R.id.Im_02:
                            getPopupWindow(R.drawable.b02d);
                            popwido.showAsDropDown(v, 50, -40);
                            break;
                        case R.id.Im_03:
                            getPopupWindow(R.drawable.b03d);
                            popwido.showAsDropDown(v, 50, -40);
                            break;
                        case R.id.Im_04:
                            getPopupWindow(R.drawable.b04d);
                            popwido.showAsDropDown(v, 50, -40);
                            break;
                        case R.id.Im_05:
                            getPopupWindow(R.drawable.b05d);
                            popwido.showAsDropDown(v, 50, -40);
                            break;
                        case R.id.Im_06:

                            Builder builder = new Builder(P06_MoudleMateTest.this);
                            builder.setSingleChoiceItems(new String[]{"经典", "大街", "公园"}, 0,
                                new android.content.DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case 0:
                                                moudleMateView.setBackgroundResource(R.drawable.model_bg);
                                                break;
                                            case 1:
                                                moudleMateView.setBackgroundResource(R.drawable.model_bg1);
                                                break;
                                            case 2:
                                                moudleMateView.setBackgroundResource(R.drawable.model_bg0);
                                                break;
                                            default:
                                                break;
                                        }
                                        dialog.dismiss();
                                    }
                                });
                            builder.show();
                            break;

                        case R.id.Im_07:
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        // 切换
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        imfirst[msg.getData().getInt("site")].setBackgroundResource(msg.getData().getInt("background"));
                        break; // 换背景
                    case 1:
                        prodialog.dismiss();
                        moudleMateView.invalidate();
                        break; // 重绘模特
                    case 2:
                        prodialog.dismiss();
                        Toast toast = Toast.makeText(getApplicationContext(), " 网络连接错误,请重试~", android.widget.Toast.LENGTH_LONG);
                        toast.setGravity(android.view.Gravity.CENTER, 0, 0);
                        ImageView add = new ImageView(getApplicationContext());
                        add.setImageResource(R.drawable.weathererr);
                        ((android.widget.LinearLayout) toast.getView()).addView(add, 180, 240);
                        toast.show();
                        break; // 报错提示
                    case 3:
                        prodialog.dismiss();
                        Toast toas = Toast.makeText(getApplicationContext(), " 获取不到图片数据", android.widget.Toast.LENGTH_LONG);
                        toas.setGravity(android.view.Gravity.CENTER, 0, 0);
                        ImageView ad = new ImageView(getApplicationContext());
                        ad.setImageResource(R.drawable.weathererr);
                        ((android.widget.LinearLayout) toas.getView()).addView(ad, 180, 240);
                        toas.show();
                        break; // 报错提示
                }
            }
        };
    }

    // 制作Actor方法,按屏幕的大小制作出合适的Actor
    private Actor actFactory(String origin, String contanct) {
        Actor actor = new Actor(origin, contanct);
        String site = "http://www.l2-fashion.com/hmdrprod?+source=url[file:/dressingroom/"
            + actor.fill + actor.png + "]&scale=width[0],height[" + (int) (actor.size * ratio) + "]&sink=format[png]";
        // System.out.println(site);
        HttpGet request = new HttpGet(site);
        HttpResponse resp;
        try {
            resp = new DefaultHttpClient().execute(request);
            Bitmap bitmap = BitmapFactory.decodeStream(resp.getEntity().getContent());
            actor.candrow = bitmap; // 这里考虑设置背景图片~
            // 返回的actor可能是没有bitmap的~

            if (bitmap != null) {

                handler.sendMessage(mesg);
            } else {

                handler.sendEmptyMessage(3);
            }
        } catch (Exception e) {
            handler.sendEmptyMessage(2);
            e.printStackTrace();
        }
        return actor;
    }

    // 因为PopupWindow会使其他控件失去焦点且不能通过返回键来取消，所以需要以下代码
    private void getPopupWindow(int background) {
        if (popwido != null) {
            popwido.dismiss();
            initPopuptWindow(background);
            return;
        } else {
            initPopuptWindow(background);
        }
    }

    // 注，93321为连衣裙，暂时不用~
    // 获得JSON源字符串的方法~这里的try捕获的是获取asset文件的异常~
    private String getOrigin(String assets) {
        InputStream is;
        String origin = null;
        try {
            is = this.getAssets().open(assets);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuffer buf = new StringBuffer();
            String str = null;
            while ((str = br.readLine()) != null) {
                buf.append(str);
            }
            origin = buf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return origin;
    }

    protected void pictureSetting(View popview, int[] color, final int site,
                                  final int background, final String[] match) {
        // 设置加载的asset文件名~
        switch (site) {
            case 1:
                txt = "97312.txt";
                break;
            case 2:
                txt = "97830.txt";
                break;
            case 3:
                txt = "97652.txt";
                break;
            case 4:
                txt = "75015.txt";
                break;
            case 5:
                txt = "92332.txt";
                break;
        }
        for (int i = 0; i < color.length; i++) {
            int id = P06_MoudleMateTest.this.getResources().getIdentifier("im_color0" + i, "id", "com.nemesisJS08.activity");
            imcolor[i] = popview.findViewById(id);
            imcolor[i].setBackgroundResource(color[i]);
            final int index = i; //
            imcolor[i].setOnClickListener(new OnClickListener() {
                public void onClick(View v) { // 设置加载的asset文件名~//设置匹配色
                    prodialog = new ProgressDialog(P06_MoudleMateTest.this);
                    popwidoc.dismiss();
                    popwido.dismiss();
                    prodialog.setMessage("衣物加载中~~");
                    prodialog.show();
                    new Thread() {
                        public void run() {
                            Bundle bun = new Bundle();// 这里存入数据~~
                            bun.putInt("site", site);
                            bun.putInt("background", background);
                            mesg = new Message();
                            mesg.setData(bun);

                            switch (site) {
                                case 1:
                                    moudleMateView.setTshirt(actFactory(getOrigin(txt), match[index]));
                                    break;
                                case 2:
                                    moudleMateView.setCoat(actFactory(getOrigin(txt), match[index]));
                                    break;
                                case 3:
                                    moudleMateView.setPants(actFactory(getOrigin(txt), match[index]));
                                    break;
                                case 4:
                                    moudleMateView.setSkirt(actFactory(getOrigin(txt), match[index]));
                                    break;
                                case 5:
                                    moudleMateView.setHat(actFactory(getOrigin(txt), match[index]));
                                    break;
                            }
                            handler.sendEmptyMessage(1);
                        }
                    }.start();
                }
            });
        }
    }

    // 初始化第一次弹出菜单的配置~
    protected void initPopuptWindow(final int background) {
        View popupWindow_view = getLayoutInflater().inflate(R.layout.p06_moudlemate_dialog, null, false); // 获取自定义布局文件dialog.xml的视图

        // 给popwido中的linear中设置监听事件~~
        LinearLayout linear = popupWindow_view.findViewById(R.id.popdialog);
        linear.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    popwido.dismiss();
                }
                return false;
            }
        });

        popwido = new PopupWindow(popupWindow_view, (int) (screenWidth * 0.32), (int) (screenHeight * 0.40), true); // 创建PopupWindow实例,设置高度和宽度
        popwido.setBackgroundDrawable(getResources().getDrawable(R.drawable.strbackground));

        // 主要是这行代码的问题!必须加入这句未知的语句代码才能发挥作用,使得popdido相应返回键的指令,就算没有背景也要添加如下语句
        // popwido.setBackgroundDrawable(new BitmapDrawable());

        Button bt_getoff = popupWindow_view.findViewById(R.id.bt_getoff);
        ImageView diaim = popupWindow_view.findViewById(R.id.dialog_im); // 要写完整是哪个布局文件的findViewById！
        diaim.setBackgroundResource(background); // 显示不同图片

        // EditText edittext = (EditText)popupWindow_view.findViewById(R.id.dialog_et);
        // edittext用来控制搜索框，出具体品牌的选项,暂时先去掉!
        ImageView only = popupWindow_view.findViewById(R.id.dialog_im_brand02);
        only.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // 初始化第二次弹出菜单的配置~
                View popupWindow_nextview = getLayoutInflater().inflate(R.layout.p06_moudlemate_choicelog, null, false);
                popwidoc = new PopupWindow(popupWindow_nextview,
                    (int) (screenWidth * 0.32), (int) (screenHeight * 0.40), true); // 这里也考虑按屏幕像素大小设置比例
                popwidoc.setBackgroundDrawable(new BitmapDrawable()); // 设置二级菜单的返回键的问题
                // popwidoc.setBackgroundDrawable(getResources().getDrawable(R.drawable.strbackground));

                LinearLayout linear = popupWindow_nextview.findViewById(R.id.popchoicelog);
                linear.setOnKeyListener(new OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            popwidoc.dismiss();
                        }
                        return false;
                    }
                });

                ImageView style = popupWindow_nextview.findViewById(R.id.dialog_im_style); // 注意输findview的时候要写全~
                switch (background) {
                    case R.drawable.b01d:
                        style.setBackgroundResource(R.drawable.show03);
                        pictureSetting(popupWindow_nextview, Tshirtcolor, 1, background, Tshirtstring);
                        break;
                    case R.drawable.b02d:
                        style.setBackgroundResource(R.drawable.show05);
                        pictureSetting(popupWindow_nextview, coatcolor, 2, background, coatstring);
                        break;
                    case R.drawable.b03d:
                        style.setBackgroundResource(R.drawable.show02);
                        pictureSetting(popupWindow_nextview, pantscolor, 3, background, pantsstring);
                        break;
                    case R.drawable.b04d:
                        style.setBackgroundResource(R.drawable.show04);
                        pictureSetting(popupWindow_nextview, skirtcolor, 4, background, skirtstring);
                        break;
                    case R.drawable.b05d:
                        style.setBackgroundResource(R.drawable.show01);
                        pictureSetting(popupWindow_nextview, hatcolor, 5, background, hatstring);
                        break;
                }
                popwidoc.showAtLocation(findViewById(R.id.framelayout), Gravity.CENTER, 0, 0); // 第二次的时候弹出位置为中央~
            }
        });
        // 重置按钮的监听设置
        bt_getoff.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                popwido.dismiss();
                switch (background) {
                    case R.drawable.b01d:
                        moudleMateView.setTshirt(null);
                        imfirst[1].setBackgroundResource(R.drawable.b01);
                        break;
                    case R.drawable.b02d:
                        moudleMateView.setCoat(null);
                        imfirst[2].setBackgroundResource(R.drawable.b02);
                        break;
                    case R.drawable.b03d:
                        moudleMateView.setPants(null);
                        imfirst[3].setBackgroundResource(R.drawable.b03);
                        break;
                    case R.drawable.b04d:
                        moudleMateView.setSkirt(null);
                        imfirst[4].setBackgroundResource(R.drawable.b04);
                        break;
                    case R.drawable.b05d:
                        moudleMateView.setHat(null);
                        imfirst[5].setBackgroundResource(R.drawable.b05);
                        break;
                }
                moudleMateView.invalidate();
            }
        });
    }
}