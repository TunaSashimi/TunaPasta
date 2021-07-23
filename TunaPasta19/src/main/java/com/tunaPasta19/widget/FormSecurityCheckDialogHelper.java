package com.tunaPasta19.widget;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tunaPasta19.R;
import com.tunaPasta19.entity.Constant;
import com.tunaPasta19.entity.FormSecurityCheck;
import com.tunaPasta19.tool.NativeRequest;
public class FormSecurityCheckDialogHelper {	//上下文环境,弹出提示类型,TextView控件,SecurityCheck对象,提示资源图,数组下标(如果有),提示资源数组
	public static void SecurityCheckPrompt(Context context,final int type,final TextView textview,final FormSecurityCheck securitycheck,int resid,final int index,String[] array) {
		//如果为linebtn的日期提示框,textview的日期提示框,textview的时间提示框
		if (Constant.SECURITYCHECK_DATE==type) {
			new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
				public void onDateSet(DatePicker datePicker, int year, int month,int dayOfMonth) {
					textview.setText(year + "年" + (month + 1) + "月" + dayOfMonth+ "日");
					securitycheck.date=year + "年" + (month + 1) + "月" + dayOfMonth+ "日";
				}
			}, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 
			Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
			return;
		}else if (Constant.SECURITYCHECK_CHECKCONTENT_CHECKDATE==type) {
			new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
				public void onDateSet(DatePicker datePicker, int year, int month,int dayOfMonth) {
					textview.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
					securitycheck.checkcontentlist.get(index).checkDate=year + "/" + (month + 1) + "/" + dayOfMonth;
				}
			}, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 
			Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
			return;
		}else if (Constant.SECURITYCHECK_CHECKCONTENT_CHECKTIME==type) {
			new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
				public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
					textview.setText( arg1 + "时" +  arg2 + "分");
					securitycheck.checkcontentlist.get(index).checkTime=arg1 + "时" +  arg2 + "分";
				}}, Calendar.getInstance().get(Calendar.HOUR_OF_DAY),Calendar.getInstance().get(Calendar.MINUTE),true).show();
			return;
		}
		//以下为带提示的对话框情形
		final View view=((Activity) context).getLayoutInflater().inflate(R.layout.formsecuritychecktestitem_dialog_prompt, null);
		final Dialog dialog=new Dialog(context,R.style.Tuna_Dialog_NoTitle);// 后面的一项为设置风格,使对话框无标题栏
		RadioGroup radiogroup=(RadioGroup)view.findViewById(R.id.radiagroup01);
		for (int i = 0; i < array.length; i++) {
			radiogroup.addView(new CustomRadioButton(context, resid, array[i]));
		}
		radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton radio=(RadioButton) view.findViewById(checkedId);
				textview.setText(radio.getText());
				switch (type) {
					case  Constant.SECURITYCHECK_UNIT:securitycheck.unit=radio.getText().toString();break;
					case  Constant.SECURITYCHECK_EXAMINER:securitycheck.examiner=radio.getText().toString();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_CHECKUNIT:securitycheck.checkcontentlist.get(index).checkUnit=radio.getText().toString();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_CHECKWORKSHOP:securitycheck.checkcontentlist.get(index).checkWorkShop=radio.getText().toString();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_WORKPLACE:securitycheck.checkcontentlist.get(index).workPlace=radio.getText().toString();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_EXISTENCEPROBLEM:securitycheck.checkcontentlist.get(index).existenceProblem=radio.getText().toString();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_CORRECTSITUATION:securitycheck.checkcontentlist.get(index).correctSituation=radio.getText().toString();break;
				}
				dialog.dismiss();
			}
		});
		//如果有值要设置edittext
		final EditText edittext01=(EditText) view.findViewById(R.id.edittext01);
		switch (type) {
		case  Constant.SECURITYCHECK_UNIT:NativeRequest.setText(edittext01,securitycheck.unit,null);break;
		case  Constant.SECURITYCHECK_EXAMINER:NativeRequest.setText(edittext01,securitycheck.examiner,null);break;
		case  Constant.SECURITYCHECK_CHECKCONTENT_CHECKUNIT:NativeRequest.setText(edittext01,securitycheck.checkcontentlist.get(index).checkUnit,null);break;
		case  Constant.SECURITYCHECK_CHECKCONTENT_CHECKWORKSHOP:NativeRequest.setText(edittext01,securitycheck.checkcontentlist.get(index).checkWorkShop,null);break;
		case  Constant.SECURITYCHECK_CHECKCONTENT_WORKPLACE:NativeRequest.setText(edittext01,securitycheck.checkcontentlist.get(index).workPlace,null);break;
		case  Constant.SECURITYCHECK_CHECKCONTENT_EXISTENCEPROBLEM:NativeRequest.setText(edittext01,securitycheck.checkcontentlist.get(index).existenceProblem,null);break;
		case  Constant.SECURITYCHECK_CHECKCONTENT_CORRECTSITUATION:NativeRequest.setText(edittext01,securitycheck.checkcontentlist.get(index).correctSituation,null);break;
	}
		Button btn=(Button)view.findViewById(R.id.button01);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				textview.setText(edittext01.getText().toString().trim());
				if (!"".equals(edittext01.getText().toString().trim())) {
					switch (type) {
					case  Constant.SECURITYCHECK_UNIT:securitycheck.unit=edittext01.getText().toString().trim();break;
					case  Constant.SECURITYCHECK_EXAMINER:securitycheck.examiner=edittext01.getText().toString().trim();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_CHECKUNIT:securitycheck.checkcontentlist.get(index).checkUnit=edittext01.getText().toString().trim();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_CHECKWORKSHOP:securitycheck.checkcontentlist.get(index).checkWorkShop=edittext01.getText().toString().trim();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_WORKPLACE:securitycheck.checkcontentlist.get(index).workPlace=edittext01.getText().toString().trim();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_EXISTENCEPROBLEM:securitycheck.checkcontentlist.get(index).existenceProblem=edittext01.getText().toString().trim();break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_CORRECTSITUATION:securitycheck.checkcontentlist.get(index).correctSituation=edittext01.getText().toString().trim();break;
					}
				}else {
					switch (type) {
					case  Constant.SECURITYCHECK_UNIT:securitycheck.unit=null;break;
					case  Constant.SECURITYCHECK_EXAMINER:securitycheck.examiner=null;break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_CHECKUNIT:securitycheck.checkcontentlist.get(index).checkUnit=null;break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_CHECKWORKSHOP:securitycheck.checkcontentlist.get(index).checkWorkShop=null;break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_WORKPLACE:securitycheck.checkcontentlist.get(index).workPlace=null;break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_EXISTENCEPROBLEM:securitycheck.checkcontentlist.get(index).existenceProblem=null;break;
					case  Constant.SECURITYCHECK_CHECKCONTENT_CORRECTSITUATION:securitycheck.checkcontentlist.get(index).correctSituation=null;break;
					}
				}
				dialog.dismiss();
			}
		});
		dialog.setContentView(view);
		dialog.show();
	}}