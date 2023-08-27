package com.baosight.wilp.ms.common.util;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.common.util.DatagroupUtil;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * TODO(工具类 封装一些复用方法)
 * 
 * @Title: PackageOarameters.java
 * @ClassName: PackageOarameters
 * @Package：com.baosight.wilp.bm.bd.common
 * @author: sunkean
 * @date: 2021年2月2日 下午5:17:08
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("unchecked")
public class PackageOarameters {

	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");

	/**
	 * 
	 * TODO(修改EiInfo中默认的limit参数的值，框架规定limit最大为1000)
	 *
	 * @Title: setLimit 
	 * @param inInfo
	 * @param i 
	 * @return: void
	 */
	public static void setLimit(EiInfo inInfo, int i) {

		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		HashMap<String, EiBlock> has = new HashMap<String, EiBlock>();
		hs.put("limit", i);
		EiBlockMeta e = new EiBlockMeta();
		EiBlock eib = new EiBlock(e);
		eib.setAttr(hs);
		has.put("result", eib);
		inInfo.setBlocks(has);
	}
	
	/**
	 * 
	 * TODO(封装查询方法，EiInfo中查询条件区域的值都是封装到inqu中的)
	 *
	 * @Title: setQueryMsg 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo
	 */
	public static EiInfo setQueryMsg(EiInfo inInfo) {
		
		String str = inInfo.getAttr().get("result").toString();
		EiBlockMeta e = new EiBlockMeta();
		EiBlock eib = new EiBlock(e);
		ArrayList<Object> list = new ArrayList<Object>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("parentId", str);
		list.add(map2);
		eib.setRows(list);
		HashMap<String, EiBlock> hs  = new HashMap<String, EiBlock>();
		hs.put("inqu_status", eib);
		inInfo.setBlocks(hs);
		return inInfo;
	}
	

	/**
	 * 
	 * TODO(将rows封装到EiInfo中，数据表格中数据来源于EiInfo中的result区域)
	 *
	 * @Title: setRows 
	 * @param inInfo
	 * @param object 
	 * @return: void
	 */
	public static void setRows(EiInfo inInfo, Object object) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(object);
		EiBlockMeta e = new EiBlockMeta();
		EiBlock eib = new EiBlock(e);
		eib.setRows(list);
		HashMap<String, EiBlock> has = new HashMap<String, EiBlock>();
		has.put("result", eib);
		inInfo.setBlocks(has);
	}
	/**
	 * 
	 * TODO(封装提示信息和状态码，负数为错误码)
	 *
	 * @Title: setMsg 
	 * @param inInfo
	 * @param msg
	 * @return 
	 * @return: EiInfo
	 */
	public static EiInfo setMsg(EiInfo inInfo, int status, String msg) {
	    inInfo.setStatus(status);
		inInfo.setMsg(msg);
		return inInfo;
	}
	
	/**
	 * 
	 * TODO(获取UUID)
	 *
	 * @Title: getUUID 
	 * @return 
	 * @return: String
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	

	/**
	 * 
	 * TODO(获取yyyyMMddHmmss格式日期)
	 *
	 * @Title: getData 
	 * @return 
	 * @return: String
	 */
	public static String getData() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(currentTime);

	}
	/**
     * 
     * TODO(封装账套信息到查询中)
	 * @return 
     *
     * @Title: getDataGroupCode 
     * @return 
     * @return: String
     */
    public static String getDataGroupCode() {
        String dataGroupCode = DatagroupUtil.getDatagroupCode();
        return dataGroupCode;
    }
	
	/**
	 * 
	 * TODO(封装账套信息到查询中)
	 *
	 * @Title: getDataGroupCode 
	 * @return 
	 * @return: String
	 */
	public static EiInfo  setDataGroupCode(EiInfo inInfo) {
	    String dataGroupCode = getDataGroupCode();
        Map map = (Map)inInfo.getBlock("inqu_status").getRows().get(0);
        map.put("dataGroupCode", dataGroupCode);
        ArrayList<Map> arrayList = new ArrayList<Map>();
        arrayList.add(map);
        inInfo.getBlock("inqu_status").setRows(arrayList);
        return inInfo;
	}
    /**
     * TODO(将rows封装到EiInfo中，数据表格中数据来源于EiInfo中的inqu_status区域)
     */
    public static void setInqu_status(EiInfo inInfo, Object object) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(object);
        EiBlockMeta e = new EiBlockMeta();
        EiBlock eib = new EiBlock(e);
        eib.setRows(list);
        HashMap<String, EiBlock> has = new HashMap<String, EiBlock>();
        has.put("inqu_status", eib);
		EiBlock b = inInfo.getBlock("result");
		has.put("result", b);
        inInfo.setBlocks(has);
    }
}
