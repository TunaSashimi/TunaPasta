package com.tunaPasta14.activity;

public class BeanDownload{
	/** 正在下载数据。Button应显示“暂停”。 */
	public static final int STATE_LOADING = 0;
	/** 数据全部下载完成。Button应显示“安装”。 */
	public static final int STATE_COMPLETED = 1;
	/** 数据下载过程中被暂停。Button应显示“继续”。 */
	public static final int STATE_INTERRUPTED = 2;
	/** 下载安装包失败。Button应显示“失败”。 */
	public static final int STATE_DOWNLOAD_FAIL = 3;
	public String name;
	public long size;
	public long loadedSize;
	public String url;
	public int state;
	public boolean enable;
}