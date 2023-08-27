package com.baosight.wilp.dk.kp.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 保养卡片类：保养卡片页面的初始化方法、查询保养卡片的信息、删除卡片、启用卡片、禁用卡片
 * <p>1.保养卡片页面的初始化方法   initLoad
 * <p>2.查询保养卡片的信息       query
 * <p>3.删除卡片        deleteKP
 * <p>4.启用卡片        startKP
 * <p>5.禁用卡片        stopKP
 * 
 * @Title: ServiceDKKP01.java
 * @ClassName: ServiceDKKP01
 * @Package：com.baosight.wilp.dk.kp.service
 * @author: LENOVO
 * @date: 2021年9月10日 下午3:16:51
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKKP01 extends ServiceBase{

	/**
	 * 保养卡片页面的初始化方法
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}
	
	/**
	 * 查询保养卡片的信息
	 * <p>1.将传递参数分装map
	 * <p>2.使用map参数查询获取保养项目list和count
	 * <p>3.将返回的保养项目list和count添加到EiiNFO并返回客户端
	 * @param
	 * *cardID      卡片id
	 * cardCode    卡片代码
	 * cardName    卡片名称
	 * status       状态
	 * @return
	 * cardIDQ            卡片id
	 * cardCodeQ          卡片代码
	 * cardNameQ          卡片名称
	 * status              状态
	 */
   @Override
   public EiInfo query(EiInfo inInfo) {
   	   //1.将传递参数分装map
       String[] param = {"cardID", "cardCode", "cardName","status"};
       Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
       //2.使用map参数查询获取保养卡片list和count
       List<Map<String, String>> list = dao.query("DKKP01.queryKP", map);
       int count = dao.count("DKKP01.countKP", map);
       //3.将返回的保养卡片list和count添加到EiiNFO并返回客户端
       return DeviceEiUtil.buildResult(inInfo, list, count, "result");
   }
   
   /**
    * 删除卡片
    * <p>1.获取选中的卡片的id
    * <p>2.删除卡片
    * <p>3.循环删除每个卡片里的项目
    *
    * @param:
    * list       卡片id集合
    * @return: EiInfo  
    * 删除成功
    * @throws
    */
   public EiInfo deleteKP(EiInfo inInfo) {
	//1.获取选中的卡片的id
   	List<String> ids = (List<String>) inInfo.get("list");
   	try {
   	//2.删除卡片
   	dao.delete("DKKP01.deleteKP", ids);
   	//3.循环删除每个卡片里的项目
   	for (String id : ids) {
			dao.delete("DKKP01.deleteItem", id);
		}
   	//4.返回删除成功
   	inInfo.setMsg("删除成功");
   	}catch (Exception e) {
	    inInfo.setMsg("删除失败");
	}
   	return inInfo;
   }
   
   /**
     * 启用卡片
     * <p>1.获取选中的卡片id
     * <p>2.修改卡片状态为启用
     * @param
     * *list      卡片id集合
     * @return
     * 启用成功或启用失败
     */
   public EiInfo startKP(EiInfo inInfo) {
	    //1.获取选中的卡片id
	    List<String> ids = (List<String>) inInfo.get("list");
	    try {
	   	//2.修改卡片状态为启用
	   	dao.update("DKKP01.startKP", ids);
	   	//启用成功
	   	inInfo.setMsg("启用成功");
	    }catch (Exception e) {
	    inInfo.setMsg("启用失败");
		}
   	    return inInfo;
   }
   
   /**
     * 禁用卡片
     * <p>1.获取选中的卡片id
     * <p>2.修改卡片状态为禁用
     * @param
     * *list      卡片id集合
     * @return
     * 禁用成功或禁用失败
     */
   public EiInfo stopKP(EiInfo inInfo) {
	    //1.获取选中的卡片id
	    List<String> ids = (List<String>) inInfo.get("list");
	    try {
	   	//2.修改卡片状态为禁用
	   	dao.update("DKKP01.stopKP", ids);
	   	//禁用成功
	   	inInfo.setMsg("禁用成功");
	    }catch (Exception e) {
	    	inInfo.setMsg("禁用失败");
		}
  	    return inInfo;
   }
}
