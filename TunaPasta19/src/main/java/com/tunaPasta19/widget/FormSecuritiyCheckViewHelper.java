package com.tunaPasta19.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.tunaPasta19.R;
import com.tunaPasta19.activity.FormSecurityCheckTest;
import com.tunaPasta19.entity.Constant;
import com.tunaPasta19.entity.FormSecurityCheck;
import com.tunaPasta19.tool.NativeRequest;

public class FormSecuritiyCheckViewHelper {
    //View01_01
    public static View view01_01(final Context context, final FormSecurityCheck securitycheck) {
        View view01_01 = LayoutInflater.from(context).inflate(R.layout.formsecuritychecktestitem_item_row01_01, null);

        final LineButton linebtn01 = (LineButton) view01_01.findViewById(R.id.linebtn01);
        final LineButton linebtn02 = (LineButton) view01_01.findViewById(R.id.linebtn02);
        final LineButton linebtn03 = (LineButton) view01_01.findViewById(R.id.linebtn03);

        NativeRequest.setText((LineButton) view01_01.findViewById(R.id.linebtn01), securitycheck.unit, new OnClickListener() {
            public void onClick(View v) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_UNIT, linebtn01, securitycheck, R.drawable.formsecuritycheckviewhelper_icon_car, Constant.NO_RESID, context.getResources().getStringArray(R.array.formsecuritiycheckviewhelper_unit));
            }
        });
        NativeRequest.setText(linebtn02, securitycheck.examiner, new OnClickListener() {
            public void onClick(View v) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_EXAMINER, linebtn02, securitycheck, R.drawable.formsecuritycheckviewhelper_icon_people, Constant.NO_RESID, context.getResources().getStringArray(R.array.formsecuritiycheckviewhelper_examiner));
            }
        });
        NativeRequest.setText(linebtn03, securitycheck.date, new OnClickListener() {
            public void onClick(View v) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_DATE, linebtn03, securitycheck, Constant.NO_RESID, Constant.NO_RESID, null);
            }
        });
        return view01_01;
    }

    //View01_021
    public static View view01_021(Context context) {
        View view01_021 = LayoutInflater.from(context).inflate(R.layout.formsecuritychecktestitem_item_row01_02, null);
        view01_021.setBackgroundColor(Color.argb(250, 224, 243, 250));
        return view01_021;
    }

    //View01_022
    public static View view01_022(final Context context, final int index, final FormSecurityCheck securitycheck) {
        View view01_022 = LayoutInflater.from(context).inflate(R.layout.formsecuritychecktestitem_item_row01_02, null);
        if (index % 2 == 0) {
            view01_022.setBackgroundColor(Color.argb(250, 255, 255, 255));
        } else {
            view01_022.setBackgroundColor(Color.argb(250, 224, 243, 250));
        }
        ((TextView) view01_022.findViewById(R.id.text01)).setText(index + 1 + "");
        final TextView textview2 = (TextView) view01_022.findViewById(R.id.text02);
        final TextView textview3 = (TextView) view01_022.findViewById(R.id.text03);
        final TextView textview4 = (TextView) view01_022.findViewById(R.id.text04);
        final TextView textview5 = (TextView) view01_022.findViewById(R.id.text05);
        final TextView textview6 = (TextView) view01_022.findViewById(R.id.text06);
        final TextView textview7 = (TextView) view01_022.findViewById(R.id.text07);
        final TextView textview8 = (TextView) view01_022.findViewById(R.id.text08);

        NativeRequest.setText(textview2, securitycheck.checkcontentlist.get(index).checkDate, new OnClickListener() {
            public void onClick(View arg0) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_CHECKCONTENT_CHECKDATE, textview2, securitycheck, Constant.NO_RESID, index, null);
            }
        });
        NativeRequest.setText(textview3, securitycheck.checkcontentlist.get(index).checkTime, new OnClickListener() {
            public void onClick(View arg0) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_CHECKCONTENT_CHECKTIME, textview3, securitycheck, Constant.NO_RESID, index, null);
            }
        });
        NativeRequest.setText(textview4, securitycheck.checkcontentlist.get(index).checkUnit, new OnClickListener() {
            public void onClick(View arg0) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_CHECKCONTENT_CHECKUNIT, textview4, securitycheck, R.drawable.formsecuritycheckviewhelper_icon_car, index, context.getResources().getStringArray(R.array.formsecuritiycheckviewhelper_checkUnit));
            }
        });
        NativeRequest.setText(textview5, securitycheck.checkcontentlist.get(index).checkWorkShop, new OnClickListener() {
            public void onClick(View arg0) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_CHECKCONTENT_CHECKWORKSHOP, textview5, securitycheck, R.drawable.formsecuritycheckviewhelper_icon_car, index, context.getResources().getStringArray(R.array.formsecuritiycheckviewhelper_checkWorkShop));
            }
        });
        NativeRequest.setText(textview6, securitycheck.checkcontentlist.get(index).workPlace, new OnClickListener() {
            public void onClick(View arg0) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_CHECKCONTENT_WORKPLACE, textview6, securitycheck, R.drawable.formsecuritycheckviewhelper_icon_car, index, context.getResources().getStringArray(R.array.formsecuritiycheckviewhelper_workPlace));
            }
        });
        NativeRequest.setText(textview7, securitycheck.checkcontentlist.get(index).existenceProblem, new OnClickListener() {
            public void onClick(View arg0) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_CHECKCONTENT_EXISTENCEPROBLEM, textview7, securitycheck, R.drawable.formsecuritycheckviewhelper_icon_car, index, context.getResources().getStringArray(R.array.formsecuritiycheckviewhelper_existenceProblem));
            }
        });
        NativeRequest.setText(textview8, securitycheck.checkcontentlist.get(index).correctSituation, new OnClickListener() {
            public void onClick(View arg0) {
                FormSecurityCheckDialogHelper.SecurityCheckPrompt(context, Constant.SECURITYCHECK_CHECKCONTENT_CORRECTSITUATION, textview8, securitycheck, R.drawable.formsecuritycheckviewhelper_icon_car, index, context.getResources().getStringArray(R.array.formsecuritiycheckviewhelper_correctSituation));
            }
        });
        return view01_022;
    }

    //View01_03
    public static View view01_03(FormSecurityCheckTest p101_securitycheck, FormSecurityCheck securitycheck) {
        View view01_03 = LayoutInflater.from(p101_securitycheck).inflate(R.layout.formsecuritychecktestitem_item_row01_03, null);
        p101_securitycheck.edittext = view01_03.findViewById(R.id.edit01);
        if (securitycheck.implement != null) {
            p101_securitycheck.edittext.setText(securitycheck.implement);
        }
        return view01_03;
    }

    //View01_04
    public static View view01_04(final Context context) {
        View view01_04 = LayoutInflater.from(context).inflate(R.layout.formsecuritychecktestitem_item_row01_04, null);
        return view01_04;
    }

}