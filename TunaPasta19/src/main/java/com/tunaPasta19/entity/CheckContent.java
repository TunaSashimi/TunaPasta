package com.tunaPasta19.entity;
import java.io.Serializable;
public class CheckContent implements Serializable{		//检查内容列表(车辆系统干部现场工作写实表,车辆系统安全包保检查写实汇总表)
	private static final long serialVersionUID = 1L;
	public String checkDate; // 检查日期
	public String checkTime; // 检查时间段
	public String checkUnit; // 检查单位
	public String checkWorkShop; // 检查车间
	public String workPlace; // 作业地点
	public String existenceProblem; // 存在问题
	public String correctSituation; // 整改情况
}
