package com.baosight.wilp.im.kp.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.common.util.DeviceEiUtil;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 
 * 巡检卡片管理：卡片初始化查询、卡片查询、卡片删除、卡片启用、卡片禁用
 * <p>1.卡片初始化查询  initLoad
 * <p>2.卡片查询   query
 * <p>3.卡片删除   delete
 * <p>4.卡片启用   openInfo
 * <p>5.卡片禁用   endInfo
 * 
 * @Title: ServiceDIKP01.java
 * @ClassName: ServiceDIKP01
 * @Package：com.baosight.wilp.di.kp.service
 * @author: LENOVO
 * @date: 2021年8月10日 上午10:18:42
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // gjd
 * <time>    // 2021年8月9日 下午4:59:22
 * <version> // v5.0.0
 * <desc>    // 代码注释
 */
public class ServiceIMKP01 extends ServiceBase{
	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
	
	/**
	 * 卡片初始化查询
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * 无参
	 * @return
	 * cardcode  卡片代码
	 * cardname  卡片名称
	 * status     状态
	 * createtime  创建时间
	 * createman   创建人
	 * modifytime   修改时间
	 * modifyman    修改人
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
//		List query = dao.query("DIKP01.queryInfo", new HashMap());
//		
//		EiBlock eiBlock = new EiBlock(new EiBlockMeta());
//        eiBlock.setRows(query);
//        HashMap<String, EiBlock> hashMap = new HashMap<String,EiBlock>();
//        hashMap.put("result", eiBlock);
//        inInfo.setBlocks(hashMap);

		return query(inInfo);
	}
	
	/**
	 * 卡片查询
	 * &lt;p&gt;Title: query&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * <p>1.获取参数
	 * <p>2.将参数封装并获取分页，执行查询
	 * @param inInfo
	 * cardcode  卡片代码
     * cardname  卡片名称
     * status     状态
	 * @return
	 * cardcode  卡片代码
     * cardname  卡片名称
     * status     状态
     * createtime  创建时间
     * createman   创建人
     * modifytime   修改时间
     * modifyman    修改人
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
//		Map blocks = inInfo.getBlocks();
//        EiBlock eiBlock = (EiBlock)blocks.get("inqu_status");
//        List rows = eiBlock.getRows();
//        Map map = (Map)rows.get(0);
		String cardcode = (String)inInfo.get("cardcode");
		String cardname = (String) inInfo.get("cardname");
		String status = (String) inInfo.get("status");
		Map<String,Object> maps = new HashMap<>();
		maps.put("cardcode", cardcode);
		maps.put("cardname", cardname);
		maps.put("status", status);
		
		inInfo.setAttr(maps);
		String[] param = {"cardcode", "cardname", "status"};
		//将参数封装并获取分页，执行查询
        maps = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
		List query = dao.query("IMKP01.queryInfo", maps);
		int count=dao.count("IMKP01.count", maps);
//        eiBlock.setRows(query);
//        HashMap<String, EiBlock> hashMap = new HashMap<String,EiBlock>();
//        hashMap.put("result", eiBlock);
//        inInfo.setBlocks(hashMap);
//		
//		
//		return inInfo;
		return DeviceEiUtil.buildResult(inInfo, query, count, "result");
	}
	
	/**
	 * 卡片删除
	 * &lt;p&gt;Title: delete&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * <p>1.获取选中行的状态
	 * <p>2.校验状态为启用则不能删除
	 * @param inInfo
	 * id 卡片id
	 * status 卡片状态
	 * @return
	 * 已启用的卡片无法删除
	 * 删除成功
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
	    EiBlock block = inInfo.getBlock("result");
	    Map<String,String> row = block.getRow(0);
	    //获取选中行的状态
	    String status = row.get("status");
	    //判断卡片状态，启用则不能删除
	    if("启用".equals(status)) {
	        inInfo.setStatus(-1);
	        inInfo.setMsg("已启用的卡片无法删除");
	        return inInfo;
	    }
		EiInfo outInfo = super.delete(inInfo, "IMKP01.delete");
		return outInfo;
	}
	
//	@Override
//	public EiInfo update(EiInfo inInfo) {
//		EiInfo outInfo = super.update(inInfo, "DIKP01.update");
//		return outInfo;
//	}
//	
//	@Override
//	public EiInfo insert(EiInfo inInfo) {
//		EiBlock block = inInfo.getBlock("result");
//        List rows = block.getRows();
//        Map<String, String> paramMap = (Map)rows.get(0);
//        String uuid = UUID.randomUUID().toString().replaceAll("-","");
//        paramMap.put("id", uuid);
//		EiInfo outInfo = super.insert(inInfo, "DIKP01.insert");
//		return outInfo;
//	}
	
	/**
	 * 卡片启用
	 * <p>获取登录人
	 * <p>将卡片修改为启用状态,判断是否执行成功
	 * @Title: openInfo 
	 * @param inInfo
	 * cardid   卡片id
	 * modifytime  修改时间
	 * modifyMan   修改人
	 * status      卡片状态
	 * @return 
	 * 开启成功，失败则返回失败执行回滚
	 * @return: EiInfo
	 */
	public EiInfo openInfo(EiInfo inInfo) {
		
		Map<String, Object> attr = inInfo.getAttr();
		String id = String.valueOf(attr.get("id"));
		Date date = new Date();
		//获取登录人
		String modifyMan = UserSession.getUser().getUsername();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("cardid", id);
		map.put("modifytime", date);
		map.put("modifyMan", modifyMan);
		map.put("status", 1);
		
		//将卡片修改为启用状态,判断是否执行成功
		int update = dao.update("IMKP01.statusUpdate", map);
		if(update > 0) {
			inInfo.setMsgKey("200");
			inInfo.setMsg("开启成功");
		}
		return inInfo;
	}
	
	/**
     * 卡片禁用
     * <p>获取登录人
     * <p>将卡片修改为禁用状态,判断是否执行成功
     * @Title: openInfo 
     * @param inInfo
     * cardid   卡片id
     * modifytime  修改时间
     * modifyMan   修改人
     * status      卡片状态
     * @return 
     * 关闭成功，失败则返回失败执行回滚
     * @return: EiInfo
     */
	public EiInfo endInfo(EiInfo inInfo) {
		
		Map<String, Object> attr = inInfo.getAttr();
		String id = String.valueOf(attr.get("id"));
		Date date = new Date();
		//获取登录人
		String modifyMan = UserSession.getUser().getUsername();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("cardid", id);
		map.put("modifytime", date);
		map.put("modifyMan", modifyMan);
		map.put("status", -1);
		
		//将卡片修改为禁用状态,判断是否执行成功
		int update = dao.update("IMKP01.statusUpdate", map);
		if(update > 0) {
			inInfo.setMsgKey("200");
			inInfo.setMsg("关闭成功");
		}
		return inInfo;
	}

}
