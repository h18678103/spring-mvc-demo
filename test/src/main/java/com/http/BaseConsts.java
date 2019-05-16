package com.http;

/**
 * 常量
 * @author xulin
 * 2016年6月24日
 */
public final class BaseConsts {
    
	/** 编码格式 **/
	public static final String UTF8    = "UTF-8";
	public static final String GBK     = "GBK";
	
	/** 换行符 */
	public static final String CRLF    = "\r\n";   // 0D0A
	public static final String CR      = "\r";     // 0D
	public static final String LF      = "\n";     // 0A
	
	/** byte单位 */
	public static final int KB = 1024;
	
	/** 时间单位 **/
	public static final int MILLISECOND = 1; 				// 毫秒
	public static final int SECOND = 1000 * MILLISECOND; 	// 秒
	public static final int MINUTE = 60 * SECOND; 			// 分钟
	public static final int HOUR = 60 * MINUTE; 			// 小时
	public static final int DAY = 24 * HOUR;                // 天
	
	/** byte类型 */
	public static final byte B0 = 0;
	public static final byte B1 = 1;
	public static final byte BN1 = -1;
}
