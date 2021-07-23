package com.tunaPasta07.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.swetake.util.Qrcode;
import com.tunaPasta07.R;

public class QRCodeTest extends Activity {
    private EditText edittext01, edittext02;
    private ImageView image01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcodetest);
        image01 =  findViewById(R.id.imgview01);
        edittext01 =  findViewById(R.id.editText1);
        edittext02 =  findViewById(R.id.editText2);
    }

    //获取二维码图片
    public static Bitmap getQRBitmap(Context context, String strEncoding, int qrcodeVersion, int colorFill, int bitmapId) {
        Bitmap bitmap = Bitmap.createBitmap(240, 240, Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        Qrcode qrcode = new Qrcode();                                // 构建QRCode编码对象
        qrcode.setQrcodeVersion(qrcodeVersion);                // 0-20
        byte[] bytesEncoding;
        try {
            bytesEncoding = strEncoding.getBytes("utf-8");    // getBytes
            if (bytesEncoding.length > 0) {
                boolean[][] bEncoding = qrcode.calQrcode(bytesEncoding);// 转化成boolean数组
                int intPadding = 20;
                canvas.drawColor(Color.WHITE);
                Paint mPaint01 = new Paint();
                mPaint01.setColor(colorFill);
                mPaint01.setStrokeWidth(1.0F);
                for (int i = 0; i < bEncoding.length; i++) {// 逐一加载boolean数组
                    for (int j = 0; j < bEncoding.length; j++) {
                        if (bEncoding[j][i]) {
                            canvas.drawRect(new Rect(intPadding + j * 3 + 2,// 绘出条形码方块
                                    intPadding + i * 3 + 2, intPadding + j * 3 + 2 + 3,
                                    intPadding + i * 3 + 2 + 3), mPaint01);
                        }
                    }
                }
                Bitmap logobitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
                canvas.drawBitmap(logobitmap, 120 - logobitmap.getWidth() / 2, 120 - logobitmap.getHeight() / 2, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                Bundle b = data.getExtras();
                String string = b.getString("result");
                edittext01.setText(string);
                break;
        }
    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivityForResult(new Intent(this, CaptureAct.class), 1);
                break;
            case R.id.button2:
                if (!"".equals(edittext02.getText().toString().trim())) {
//				String souce="上海杰之能信息科技有限公司\n地址:上海市闸北区广中西路777弄55号启迪大厦11楼 电话:(021)-66301133 邮箱:info@gener-tech.com";
                    String souce = edittext02.getText().toString().trim();
                    Bitmap bitmap = getQRBitmap(this, souce, 12, Color.BLACK, R.drawable.logo);
                    image01.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(this, "输入字符串！", 1000).show();
                }
                break;
        }
    }
}
