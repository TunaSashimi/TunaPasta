package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tunaPasta19.R;
import com.tunaPasta19.entity.Constant;
import com.tunaPasta19.entity.FormSecurityCheck;
import com.tunaPasta19.tool.NativeRequest;
import com.tunaPasta19.widget.FormSecuritiyCheckViewHelper;

//保存机制暂定为退出自动保存,用户名不保存
public class FormSecurityCheckTest extends Activity {
    private FormSecurityCheck formsecuritycheck;//类名和保存文件名一致
    public EditText edittext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formsecuritychecktest);

        LinearLayout linearlayout = findViewById(R.id.layout01);

        formsecuritycheck = (FormSecurityCheck) NativeRequest.getInfomation(this, Constant.TYPE_SECURITYCHECK, "formsecuritycheck.dat", null, Constant.ROW_NUMBER);

        linearlayout.addView(FormSecuritiyCheckViewHelper.view01_01(this, formsecuritycheck));
        linearlayout.addView(FormSecuritiyCheckViewHelper.view01_021(this));

        for (int i = 0; i < Constant.ROW_NUMBER; i++) {
            linearlayout.addView(FormSecuritiyCheckViewHelper.view01_022(this, i, formsecuritycheck));
        }

        linearlayout.addView(FormSecuritiyCheckViewHelper.view01_03(this, formsecuritycheck));
        linearlayout.addView(FormSecuritiyCheckViewHelper.view01_04(this));
    }

    protected void onPause() {
        if (!"".equals(edittext.getText().toString().trim())) {
            formsecuritycheck.implement = edittext.getText().toString().trim();
        }
        NativeRequest.saveInfomation(this, formsecuritycheck, "formsecuritycheck.dat");
        super.onPause();
    }

    public void onViewClick(View target) {
        switch (target.getId()) {
        }
    }
}
