package com.baosight.wilp.im.jz.service;

import com.baosight.iplat4j.core.service.impl.ServiceBase;

/**
 * 基准增加卡片：已不用
 * 
 * @Title: ServiceDIJZ010106.java
 * @ClassName: ServiceDIJZ010106
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月13日 下午2:09:52
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMJZ010106 extends ServiceBase{
	
//	private static Map map = new HashMap();
//
//	protected static final Logger LOGGER = LoggerFactory.getLogger(ServiceDIJZ010106.class);
//	
//	public EiInfo initLoad(EiInfo info) {
//		EiInfo outInfo = super.query(info, "DIJZ010106.query", new DiCard());
//		//增加blockId="R2"的页面  
//		EiBlock result2 = outInfo.addBlock("result2");
//		result2.addBlockMeta(new DiCardRelation().eiMetadata);
//		
//		Map<String, Object> param = new HashMap<>();
//		EiBlock block = info.getBlock("item");
//		Integer offset = 0;
//		Integer limit = 10;
//		if (block != null) {
//			Map blockMap = block.getAttr();
//			Integer offset1 = (Integer) blockMap.get("offset");
//			Integer limit1 = (Integer) blockMap.get("limit");	
//			if (offset1 != null && limit1 != null) {
//				offset = offset1;
//				limit = limit1;
//			}
//		}
//		param.put("offset", offset);
//		param.put("limit", limit);
//		List<Map<String, Object>> exman = dao.query("DIJZ010106.getProjectInfo", param);
//		exman.forEach(m -> {
//			m.put("itemid", m.get("itemid"));
//			m.put("cardid", m.get("cardid"));
//			m.put("content", m.get("content"));
//			m.put("itemdesc", m.get("itemdesc"));
//			m.put("referencevalue", m.get("referencevalue"));
//			m.put("sortno", m.get("sortno"));
//			m.put("actualvalueunit", m.get("actualvalueunit"));
//		});
//		LOGGER.info("设备巡查模块--从卡片中添加项目--项目列表:{}", JSONObject.toJSONString(exman));
//		outInfo.addRows("result2", exman);
//		
//		return outInfo;
//	}
//
//
//	public EiInfo queryProjectInfo(EiInfo inInfo) {
//		EiInfo eiInfo = new EiInfo();
//		Map<String, EiBlock> blocks = eiInfo.getBlocks();
//
//		Map<String, Object> attrMap = inInfo.getAttr();
//		Object object = attrMap.get("param");
//		Map<String, Object> maps = new HashMap();    
//		maps = (Map<String, Object>) object;
//		String cardid= String.valueOf(maps.get("cardid"));
//		//String cardid = "b3ec6ebf83fb4fa886cbcf87dbe74643";
//
//		map.put("cardid", cardid);
//		List<DiCardRelation> query = dao.query("DIJZ010106.getProjectInfo", map);
//		map.remove("cardid");
//		EiBlock eiBlock = new EiBlock(new DiCardRelation().eiMetadata);    	 
//		blocks.put("result2", eiBlock);
//		EiBlock block = inInfo.getBlock("result2");
//		block.setRows(query);
//		return inInfo;
//	}


//	/**
//	 * 
//	 * 查询数据字典分类子项
//	 *
//	 * @Title: queryTrashTypegroup 
//	 * @param inInfo
//	 * @return 
//	 * @return: EiInfo
//	 */
//	public EiInfo queryTypegroup(EiInfo inInfo) {
//		EiInfo initLoad = null;
//		String object = inInfo.get("result").toString();
//		if(StringUtil.isNotEmpty(object)) {
//			String subString = subString(object,"[","]");
//			map.put("id", subString);
//			initLoad = initLoad(inInfo);
//		}else {
//			inInfo.setMsg("请选择需要查看的数据字典分类");
//			inInfo.setStatus(-1);
//		}
//		return initLoad;
//	}
//
//
//
//	/**
//	 * 
//	 * 截取指定String工具类
//	 *
//	 * @Title: subString 
//	 * @param str
//	 * @param strStart
//	 * @param strEnd
//	 * @return 
//	 * @return: String
//	 */
//	public static String subString(String str, String strStart, String strEnd) {
//
//		/* 找出指定的2个字符在 该字符串里面的 位置 */
//		int strStartIndex = str.indexOf(strStart);
//		int strEndIndex = str.indexOf(strEnd);
//
//		/* index 为负数 即表示该字符串中 没有该字符 */
//		if (strStartIndex < 0) {
//			return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
//		}
//		if (strEndIndex < 0) {
//			return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
//		}
//		/* 开始截取 */
//		String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
//		return result;
//	}
//
//
//	/**
//	 * 
//	 * String转map
//	 * @param str
//	 * @return
//	 */
//	public static Map<String,Object> getStringToMap(String str){
//		String replaceAll = str.replaceAll(" ", "");
//		//根据逗号截取字符串数组
//		String[] str1 = replaceAll.split(",");
//		//创建Map对象
//		Map<String,Object> map = new HashMap<>();
//		//循环加入map集合
//		for (int i = 0; i < str1.length; i++) {
//			//根据"="截取字符串数组
//			String[] str2 = str1[i].split("=");
//			//str2[0]为KEY,str2[1]为值
//			map.put(str2[0],str2[1]);
//		}
//		return map;
//	}
//
//
//	/**
//	 * 
//	 * 修改分页
//	 *
//	 * @Title: test 
//	 * @param inInfo
//	 * @param i 
//	 * @return: void
//	 */
//	public void toLimit(EiInfo inInfo,int i) {
//		HashMap<String, Integer> hs = new HashMap<String, Integer>();
//		HashMap<String, EiBlock> has = new HashMap<String, EiBlock>();
//		hs.put("limit", i);
//		EiBlockMeta e = new EiBlockMeta();
//		EiBlock eib = new EiBlock(e);
//		eib.setAttr(hs);
//		has.put("result", eib);
//		inInfo.setBlocks(has);
//	}




//	@Override
//	public EiInfo query(EiInfo inInfo) {
//		Map<String,EiBlock> blocks = inInfo.getBlocks();
//		if(blocks.containsKey("result2")) {
//			//获取分页参数
//			EiBlock eiBlock = blocks.get("result2");
//			Map attr = eiBlock.getAttr();
//			Object object = attr.get("limit");
//			Integer limit = Integer.valueOf(object.toString());
//			List<DiCardRelation> query = null;
//			//根据查询条件判断是否分页查询
//			if(null == limit){
//				//获取查询条件
//				map.put("limit", limit);
//				map.put("removeId", map.get("id"));
//				//用dao查询
//				query = dao.query("DIJZ010106.getProjectInfo", map);
//				map.remove("id");
//				map.remove("limit");
//			}else{
//				//分查询
//				map.put("limit", limit);
//				map.put("id", map.get("removeId"));
//				//用dao查询
//				query = dao.query("DIJZ010106.getProjectInfo", map);
//				map.remove("id");
//				map.remove("limit");
//			}
//			//返回查询结果集
//			EiBlock eiBlock2 = new EiBlock(new DiCardRelation().eiMetadata);
//			blocks.put("result2", eiBlock2);
//			inInfo.getBlock("result2").setRows(query);
//			return inInfo;
//		}
//		EiInfo outInfo = super.query(inInfo, "DIJZ010106.query", new DiCardRelation());
//		return outInfo;
//	}

}
