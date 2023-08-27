package com.baosight.wilp.im.kp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 
 * 巡检卡片新增跳转页面、卡片保存
 * <p>1.卡片新增跳转页面 initLoad
 * <p>2.卡片保存  saveResult
 * 
 * @Title: ServiceDIKP02.java
 * @ClassName: ServiceDIKP02
 * @Package：com.baosight.wilp.di.kp.service
 * @author: LENOVO
 * @date: 2021年8月10日 上午10:58:32
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMKP02 extends ServiceBase{
	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
	
	/**
	 * 卡片新增跳转页面
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		/*List query = dao.query("DIKP01.queryInfo", new HashMap());
		
		EiBlock eiBlock = new EiBlock(new EiBlockMeta());
        eiBlock.setRows(query);
        HashMap<String, EiBlock> hashMap = new HashMap<String,EiBlock>();
        hashMap.put("result", eiBlock);
        inInfo.setBlocks(hashMap);
		*/
		
		return inInfo;
	}
	
    /*@Override
    public EiInfo query(EiInfo inInfo) {
    	Map blocks = inInfo.getBlocks();
        EiBlock eiBlock = (EiBlock)blocks.get("inqu_status");
        List rows = eiBlock.getRows();
        Map map = (Map)rows.get(0);
    	String cardcode = (String) map.get("cardcode");
    	String cardname = (String) map.get("cardname");
    	HashMap<String,Object> maps = new HashMap<String, Object>();
    	maps.put("cardcode", cardcode);
    	maps.put("cardname", cardname);
    	
    	
    	List query = dao.query("DIKP01.queryInfo", maps);
    	int count=0;
    	if(!query.isEmpty()) {
    		count=query.size();
    	}*/
//        eiBlock.setRows(query);
//        HashMap<String, EiBlock> hashMap = new HashMap<String,EiBlock>();
//        hashMap.put("result", eiBlock);
//        inInfo.setBlocks(hashMap);
//		
//		
//		return inInfo;
    /*	return DeviceEiUtil.buildResult(inInfo, query, count, "result");
    }*/
	
    /*@Override
    public EiInfo delete(EiInfo inInfo) {
    	EiInfo outInfo = super.delete(inInfo, "DIKP01.delete");
    	return outInfo;
    }
    
    @Override
    public EiInfo update(EiInfo inInfo) {
    	EiInfo outInfo = super.update(inInfo, "DIKP01.update");
    	return outInfo;
    }
    
    @Override
    public EiInfo insert(EiInfo inInfo) {
    	EiBlock block = inInfo.getBlock("result");
        List rows = block.getRows();
        Map<String, String> paramMap = (Map)rows.get(0);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        paramMap.put("id", uuid);
    	EiInfo outInfo = super.insert(inInfo, "DIKP01.insert");
    	return outInfo;
    }*/
	

	/**
	 * 卡片保存
	 * <p>1.从inInfo里面获取数据
	 * <p>2.获取代码编号
	 * <p>3.获取操作人
	 * <p>4.生成主键uuid
	 * <p>5.保存卡片项目方法
	 * @Title: saveResult 
	 * @param inInfo
	 * cardname   卡片名称
	 * memo        备注
	 * cardcode    卡片编码
	 * createtime  创建时间
	 * createman   创建人
	 * cardtype    卡片类型
	 * insptype     巡检类型
	 * status       卡片状态
	 * @return 
	 * 保存成功
	 * @return: EiInfo
	 */
	public EiInfo saveResult(EiInfo inInfo){
		SimpleDateFormat sp=new SimpleDateFormat();
		// 从inInfo里面获取数据
		Map<String, Object> attr = inInfo.getAttr();	
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);

		Map<String,Object> hashMap = new HashMap<String, Object>();
		// 获取代码编号
		String cardCode = generateCode();
		// 获取操作人
		String createMan = UserSession.getUser().getUsername();
		Date createTime = new Date();
		// 生成主键uuid
		String cardID = UUID.randomUUID().toString().replaceAll("-","");
		String cardname = String.valueOf(param.get("cardname"));
		String memo = String.valueOf(param.get("memo"));
		
		hashMap.put("cardcode", cardCode);
		hashMap.put("createman", createMan);
		hashMap.put("createtime", createTime);
		hashMap.put("cardid", cardID);
		hashMap.put("cardname", cardname);
		hashMap.put("memo", memo);
		hashMap.put("cardtype", "inspection");
		hashMap.put("insptype", "env");
		hashMap.put("status", 0);
		dao.insert("IMKP02.saveBasicsInfo", hashMap);
	    //保存卡片项目方法
		saveProjectInfo(cardID,inInfo);
		return inInfo;
	}
	
	
	/**
     * 卡片保存项目
     * <p>1.从inInfo里面获取数据
     * <p>2.数据转换 将object对象装换成list
     * <p>3.循环执行保存卡片项目操作
     * @Title: saveProjectInfo 
     * @param inInfo
     * itemidXmid   项目id
     * cardid       卡片 id
     * jitemname    项目名称
     * itemdesc     项目说明
     * referencevalue   参考值
     * actualvalueunit   实际值单位
     * sortno     排序字段
     * @return 
     * 保存成功
     * @return: EiInfo
     */
	public void saveProjectInfo(String moduleId,EiInfo inInfo){
		// 从inInfo里面获取数据
		Map<String, Object> attr = inInfo.getAttr();	
		
		Object object = attr.get("content");
		// 数据转换     object类型转换为list<Map<String, Object>>类型
		List<Map<String,Object>> list = objConvertList(object);
		//循环执行保存卡片项目操作
		for(int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String id = String.valueOf(map.get("idd"));
			String content = String.valueOf(map.get("content"));
			String referencevalue = String.valueOf(map.get("referencevalue"));
			String projectDesc = String.valueOf(map.get("projectDesc"));
			String actualvalueunit = String.valueOf(map.get("actualvalueunit"));
		
			Map<String,Object> hashMap = new HashMap<String, Object>();
			hashMap.put("itemidXmid", id);
			hashMap.put("cardid", moduleId);
			hashMap.put("jitemname", content);
			hashMap.put("itemdesc", projectDesc);
			hashMap.put("referencevalue", referencevalue);
			hashMap.put("actualvalueunit", actualvalueunit);
			hashMap.put("sortno", i);
			dao.insert("IMKP02.saveProjectInfo", hashMap);
		}
	}
	
	
	/**
	 * 卡片基本情况信息编辑
	 * 
	 * @param inInfo
	 * @return
	 */
    /*	public EiInfo updateResult(EiInfo inInfo){
    		// 从inInfo里面获取数据
    		Map<String, Object> attr = inInfo.getAttr();	
    		EiBlock block = inInfo.getBlock("inqu_status");
    		Map<String, Object> param = block.getRow(0);
    
    		// 赋值
    		Map<String,Object> hashMap = new HashMap<String, Object>();
    		String cardCode = generateCode();
    		String createMan = UserSession.getUser().getUsername();
    		Date createTime = new Date();
    		String cardID = UUID.randomUUID().toString().replaceAll("-","");
    		String cardname = String.valueOf(param.get("cardname"));
    		String memo = String.valueOf(param.get("memo"));
    		
    		hashMap.put("cardcode", cardCode);
    		hashMap.put("createman", createMan);
    		hashMap.put("createtime", createTime);
    		hashMap.put("cardid", cardID);
    		hashMap.put("cardname", cardname);
    		hashMap.put("memo", memo);
    		hashMap.put("cardtype", "inspection");
    		hashMap.put("insptype", "env");
    		hashMap.put("status", 0);
    		dao.insert("DIKP02.saveBasicsInfo", hashMap);
    
    		saveProjectInfo(cardID,inInfo);
    		return inInfo;
    	}*/
	
	
	
	
	
	
	/**
	 * 
	 * 获取最大编号
	 *
	 * @Title: generateCode 
	 * @return 
	 * @return: String
	 */
	 private String generateCode() {
	  synchronized (dao) {
	   String date = DateUtils.curDateStr8();
	   List<String> list = dao.query("DIKP02.queryContTypeNum","");
	   if (list == null || list.size() == 0 || list.get(0) == null) {
	    return "CR" + date + "0001";
	   } else {
	    String maxNo = list.get(0);
	    maxNo = maxNo.substring(2);
	    return "CR" + (Long.parseLong(maxNo) + 1L);
	   }
	  }
	 }
	
	
	/**
	 * Object类型转换为List<Map<String,Object>>
	 * @param object
	 * @return list
	 */
	public List<Map<String, Object>> objConvertList(Object object) {
		List<Map<String, Object>> list = new ArrayList();
		if (object instanceof ArrayList<?>) {
			for (Object o : (List<Map<String, Object>>) object) {
				list.add(Map.class.cast(o));
	        }
		}
		return list;
	}

}
