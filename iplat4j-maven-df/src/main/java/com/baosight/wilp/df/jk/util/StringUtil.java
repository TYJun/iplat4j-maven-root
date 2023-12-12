package com.baosight.wilp.df.jk.util;

import java.util.Arrays;
import java.util.List;

/**
 * Todo(这里用一句话描述这个方法的作用)
 * 
 * @Title: StringUtil
 * @ClassName: StringUtil.java
 * @Package：com.baosight.wilp.ss.bm.utils
 * @author: xiajunqing
 * @date: 2021/11/20 13:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class StringUtil extends com.baosight.iplat4j.core.util.StringUtils {
	
	/**分割线*/
	public static String separator = "-----------------------------------------";
	
	/**
	 * 将数字转换成指定进制的文本，并按指定的长度
	 * @param iNumber   数字
	 * @param iSysBase  进制
	 * @param iLength   长度
	 * @return
	 */
	public static String getNumberToString(long iNumber,long iSysBase,int iLength)
	{
		String  tNumber="";
		while(iNumber>0)
		{
			int iSub=(int)(iNumber%iSysBase);
			iNumber=iNumber/iSysBase;
			switch(iSub)
			{
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
					tNumber=((char)('0'+iSub))+tNumber;
					break;
				default:
					tNumber=((char)('A'+(iSub-10)))+tNumber;
					break;
			}
		}
		if(iLength<tNumber.length())
		{
			return tNumber;
		}
		while(tNumber.length()<iLength)
		{
			tNumber="0"+tNumber;
		}
		return tNumber;
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static boolean isEmpty(String str) { return (str == null || str.length() == 0); }

	public static boolean isNumber(String str) {return str.matches("[0-9]+"); }

	public static void main(String[] args) {
		System.out.println(isNotEmpty(" "));
		
		String s = "ST001";
		List<String> tempList = Arrays.asList(s.split(","));
		System.out.println(tempList.contains("admin"));
		System.out.println(isBlank(" "));
	}
}
