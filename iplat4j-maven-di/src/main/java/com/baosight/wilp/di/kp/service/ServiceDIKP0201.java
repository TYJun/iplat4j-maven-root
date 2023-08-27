package com.baosight.wilp.di.kp.service;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.di.kp.domain.DIKP0201;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang.StringUtils;

import java.util.*;


/**
 * 
 * 巡检卡片编辑跳转页面、卡片项目删除、卡片保存
 * <p>1.卡片编辑跳转页面 initLoad
 * <p>2.卡片保存  updateResult
 * <p>3.卡片项目删除  delete
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
public class ServiceDIKP0201 extends ServiceBase{
	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
	
	/**
	 * 卡片编辑跳转页面
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * <p>获取id
	 * <p>获取result模块
	 * <p>判断id是否为空
	 * <p>给result模块中赋值
	 * <p>赋值数据并返回
	 * @param inInfo
	 * id   卡片id
	 * @return
	 * cardname   卡片名称
     * memo        备注
     * jitemname    项目名称
     * itemdesc     项目说明
     * referencevalue   参考值
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 获取id
		String id = inInfo.getAttr().get("id").toString();
		  EiBlock block = new EiBlock("inqu_status");
		  //获取result模块
		  EiBlock addBlock = inInfo.addBlock("result");
		   addBlock.addBlockMeta(new DIKP0201().eiMetadata);
		  Map<String, Object> map = new HashMap<String, Object>();
		  List<Map<String, Object>> listMap = new ArrayList();
		  // 判断id是否为空
		  if (StringUtils.isNotBlank(id)) {
			  
		   List<Map<String, Object>> listBasics = dao.query("DIKP0201.getBasicsInfo", id);
		   List<Map<String, Object>> listProject = dao.query("DIKP0201.getProjectInfo", id);

		   // 给result模块中赋值
		   inInfo.getBlock("result").setRows(listProject);
		   // 赋值数据并返回
		   block.addRows(listBasics);
		  }
		  inInfo.addBlock(block);
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
    	
        eiBlock.setRows(query);
        HashMap<String, EiBlock> hashMap = new HashMap<String,EiBlock>();
        hashMap.put("result", eiBlock);
        inInfo.setBlocks(hashMap);
    	
    	
    	return inInfo;
    }*/
	
	/**
	 * 删除项目
	 * &lt;p&gt;Title: delete&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * itemid  项目id
	 * @return
	 * 删除成功
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = super.delete(inInfo, "DIKP0201.delete");
		return outInfo;
	}

    /*
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
     * <p>2.获取登录人
     * <p>3.修改卡片信息
     * <p>4.调用卡片项目信息方法
     * @Title: updateResult 
     * @param inInfo
     * cardname   卡片名称
     * memo        备注
     * cardid    卡片id
     * modifyTime  修改时间
     * modifyMan   修改人
     * @return 
     * 保存成功
     * @return: EiInfo
     */
	public EiInfo updateResult(EiInfo inInfo){
		// 从inInfo里面获取数据
		Map<String, Object> attr = inInfo.getAttr();	
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);

		Map<String,Object> hashMap = new HashMap<String, Object>();
		// 获取登录人
		String modifyMan = UserSession.getUser().getUsername();
		String cardID = String.valueOf(param.get("cardid"));
		String cardname = String.valueOf(param.get("cardname"));
		String memo = String.valueOf(param.get("memo"));
		Date modifyTime = new Date();
		

		hashMap.put("cardid", cardID);
		hashMap.put("cardname", cardname);
		hashMap.put("memo", memo);
		hashMap.put("modifyTime", modifyTime);
		hashMap.put("modifyMan", modifyMan);
        //修改卡片信息
		int update = dao.update("DIKP0201.updateInfo", hashMap);
		if(update>0) {
		    //调用卡片项目信息方法
			updateProjectInfo(cardID, inInfo);
		}
		return inInfo;
	}
	
	
	/**
     * 卡片保存项目
     * <p>1.从inInfo里面获取数据
     * <p>2.数据转换 将object对象装换成list
     * <p>3.循环执行保存卡片项目操作
     * @Title: updateProjectInfo 
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
	public void updateProjectInfo(String cardID,EiInfo inInfo){
		dao.delete("DIKP0201.deleteProjectInfo", cardID);
		
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
			hashMap.put("cardid", cardID);
			hashMap.put("jitemname", content);
			hashMap.put("itemdesc", projectDesc);
			hashMap.put("referencevalue", referencevalue);
			hashMap.put("actualvalueunit", actualvalueunit);
			hashMap.put("sortno", i);
			dao.insert("DIKP0201.saveProjectInfo", hashMap);
		}
	}
	
	
	
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
	   List<String> list = dao.query("DIKP02.queryContTypeNum", "CR" + date);
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
	 * @return  list
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
	
//	// 分页查询
//	private EiInfo queryTabGrid(EiInfo inInfo, String querySql,String countSql, String resultBlock, EiBlockMeta blockMeta){
//		  Map<String, Object> map = PrUtils.buildParamter(inInfo, "inqu_status", resultBlock);
//		  //查询数据
//		  List<?> list = dao.query(querySql, map, (Integer)map.get("offset"), (Integer)map.get("limit"));
//		  //获取总数
//		  int count = dao.count(countSql, map);
//		  //数据返回
//		  return PrUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
//		 }

}
