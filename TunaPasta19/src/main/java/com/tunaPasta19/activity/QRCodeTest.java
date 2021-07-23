package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.swetake.util.Qrcode;
import com.tunaPasta19.R;

public class QRCodeTest extends Activity {

    private static Bitmap sourceBitmap, synthesisBitmap;
    private static int sourceBitmapWidth, sourceBitmapHeight;
    private static float sourceBitmapWidthScale, sourceBitmapHeightScale;

    public static final int bitmapWidth = 240;

    private EditText edittext01, edittext02;
    private ImageView image01;

    private static float scale = 0;
    private static Matrix matrix;
    private static Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static int bitmapId = R.drawable.tunasashimi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcodetest);
        image01 = findViewById(R.id.imgview01);
        edittext01 = findViewById(R.id.editText1);
        edittext02 = findViewById(R.id.editText2);

        edittext02.setText("TunaSashimi");
        synthesisBitmap = getQRBitmap(this, "TunaSashimi", 12, Color.BLACK, bitmapId);

        image01.setImageBitmap(synthesisBitmap);
    }

    //获取二维码图片
    public static Bitmap getQRBitmap(Context context, String strEncoding, int qrcodeVersion, int colorFill, int bitmapId) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapWidth, Config.RGB_565);
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
                sourceBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
                sourceBitmapWidth = sourceBitmap.getWidth();
                sourceBitmapHeight = sourceBitmap.getHeight();

                if (sourceBitmapWidth != sourceBitmapHeight) {
                    throw new IllegalArgumentException("The sourceBitmapWidth and sourceBitmapHeight must be equal!");
                }

                scale = (sourceBitmapWidth * 0.08f) / bitmapWidth;

                sourceBitmapWidthScale = sourceBitmapWidth * scale;
                sourceBitmapHeightScale = sourceBitmapHeight * scale;

                matrix = new Matrix();
                matrix.setScale(scale, scale);

                canvas.translate((bitmapWidth - sourceBitmapWidthScale) * 0.5f, (bitmapWidth - sourceBitmapHeightScale) * 0.5f);
                canvas.drawBitmap(sourceBitmap, matrix, paint);
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
                startActivityForResult(new Intent(this, CaptureCodeTest.class), 1);
                break;
            case R.id.button2:
                if (!"".equals(edittext02.getText().toString().trim())) {

                    String souce = edittext02.getText().toString().trim();

                    synthesisBitmap = getQRBitmap(this, souce, 12, Color.BLACK, bitmapId);

                    image01.setImageBitmap(synthesisBitmap);
                } else {
                    Toast.makeText(this, "输入字符串!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
