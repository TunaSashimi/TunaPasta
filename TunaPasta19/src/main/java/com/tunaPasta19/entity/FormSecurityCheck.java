package com.tunaPasta19.entity;
import java.io.Serializable;
import java.util.List;
public class FormSecurityCheck implements Serializable{		//车辆系统安全包保检查写实汇总表
	private static final long serialVersionUID = 1L;
	public String unit;				// 单位
	public String examiner;		// 检查人
	public String date;				// 日期
	public String implement;		// 落实情况
	public List<CheckContent> checkcontentlist;	//检查内容列表
}
