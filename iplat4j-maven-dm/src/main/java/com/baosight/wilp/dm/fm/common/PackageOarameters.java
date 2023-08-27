package com.baosight.wilp.dm.fm.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
//import com.baosight.wilp.bm.bd.domain.BMBD05;
//import com.baosight.wilp.bm.bd.domain.BMBD08;
import com.baosight.wilp.common.util.DatagroupUtil;

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
	 * TODO(根据科室名称查询科室编码)
	 *
	 * @Title: getDeptNum 
	 * @param deptName
	 * @return 
	 * @return: String
	 */
//	public static String getDeptNum(String deptName) {
//
//		Map<String, String> map = new HashMap<>();
//		map.put("deptName", deptName);
//		List<BMBD05> list =  dao.query("BMBD05.queryDeptNumByDeptName", map);
//		String deptNum = "";
//		if (CollectionUtils.isEmpty(list)) {
//			deptNum = list.get(0).getDeptNum();
//			
//		}
//		return deptNum;
//	}

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
	 public static Timestamp getData() {
		  Timestamp ts=new Timestamp(new Date().getTime());
		  return ts;
		 }
	
	/**
	 * 
	 * TODO(根据字符串生成编码（字符串首字母大写加上当前年月日时分秒）)
	 *
	 * @Title: getNum 
	 * @param str
	 * @return 
	 * @return: String
	 */
	public static String getNum(String str) {
		return PinYinUtils.toFirstPinYin(str)+PackageOarameters.getData();
	}
	
	/**
	 * 
	 * TODO(查询物资大类名称)
	 *
	 * @Title: getMatClassTypeName 
	 * @param str
	 * @return 
	 * @return: String
	 */
//	public static String getMatClassTypeName(String str) {
//		
//		Map<String, String> map = new HashMap<>();
//		map.put("typecode", str);
//		List<BMBD08> list = dao.query("BMBD08.queryTypeName", map);
//		String matClassTypeName = "";
//		if (!CollectionUtils.isEmpty(list)) {
//			matClassTypeName = list.get(0).getMatClassTypeName();
//
//		}
//		return matClassTypeName;
//	}
	
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
}
