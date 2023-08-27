package com.baosight.wilp.cs.re.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.cs.common.CSUtils;
import com.baosight.wilp.cs.common.MTUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 保洁登记子界面Service.
 * 一、页面加载.
 * 二、保洁工单登记.
 * 
 * @Title: ServiceCSRE0101.java
 * @ClassName: ServiceCSRE0101
 * @Package：com.baosight.wilp.cs.re.service
 * @author: fangzekai
 * @date: 2021年11月18日 下午1:56:48
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSRE0101 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
    	// 获取当前人信息
    	Map<String, Object> userInfo = CSUtils.getUserInfo(null);
    	if(userInfo !=null){
    		inInfo.set("guaranteeNum", userInfo.get("workNo"));
        	inInfo.set("guaranteeName", userInfo.get("workNo"));
        	inInfo.set("guaranteeName_textField", userInfo.get("name"));
        	inInfo.set("reqDeptName", userInfo.get("deptNum"));
        	inInfo.set("reqDeptName_textField", userInfo.get("deptName"));
    	}
        return inInfo;
    }
    
	/**
	 * 二、保洁工单登记.
	 *  1、获取当前用户信息，生成工单的工单号.
	 * 	2、调用本地服务CSRE01.insertCSGD将工单信息插入保洁工单主表中.
	 *  3、调用本地服务CSRE01.insertCSGDMX将工单信息插入到保洁工单明细表中.
	 *  4、调用本地服务CSRE01.insertCSGDLC将工单信息插入到保洁工单流程表中.
	 *  5、返回操作结果.
	 * 
	 * @Title: insert
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#insert(EiInfo)
	 */
	public EiInfo insert(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息，生成工单的工单号.
		 */
		// 获取当前登陆工号
		String loginName=StringUtils.isBlank((String)inInfo.get("workNo"))?
	            UserSession.getUser().getUsername():(String)inInfo.get("workNo");
        Map<String, Object> userInfo = CSUtils.getUserInfo(loginName);
		// 调用保洁模块的工具类中的生成工单号方法
		String taskNo = CSUtils.generationSerialNo("cleanService", "CS", "0");
		// 生成主表工单号对应id
		String taskId = UUID.randomUUID().toString();
		inInfo.set("taskNo", taskNo);
		inInfo.set("taskId", taskId);
		// 创建人
        inInfo.set("recCreator", loginName);
		/*
		 * 2、调用本地服务CSRE01.insertCSGD将工单信息插入保洁工单主表中.
		 */
		// 将工单信息插入保洁工单主表
        inInfo.set(EiConstant.serviceName, "CSRE01");
    	inInfo.set(EiConstant.methodName, "insertCSGD");
    	EiInfo outInfo = XLocalManager.call(inInfo);

		/**4.保存上传图片**/
		inInfo.set(EiConstant.serviceName, "CSRE01");
		inInfo.set(EiConstant.methodName, "savePicture");
		outInfo = XLocalManager.call(inInfo);

		/*
		 * 3、调用本地服务CSRE01.insertCSGDMX将工单信息插入到保洁工单明细表中.
		 */
		// 将保洁事项插入保洁明细表
    	inInfo.set(EiConstant.serviceName, "CSRE01");
        inInfo.set(EiConstant.methodName, "insertCSGDMX");
        outInfo = XLocalManager.call(inInfo);
		/*
		 * 4、调用本地服务CSRE01.insertCSGDLC将工单信息插入到保洁工单流程表中.
		 */
		// 操作人
        inInfo.set("operationUserNo", loginName);
        inInfo.set("operationUserName", userInfo == null ? "" : userInfo.get("name"));
        // 插入保洁工单流程表中
        inInfo.set(EiConstant.serviceName, "CSRE01");
        inInfo.set(EiConstant.methodName, "insertCSGDLC");
        outInfo = XLocalManager.call(inInfo);
		/*
		 * 5、返回操作结果.
		 */
		outInfo.addMsg("操作成功");
        outInfo.setMsgKey("200");
        
        return outInfo;
    }

	/**
	 * PC端登记任务工单上传图片时回显
	 *
	 * <p>获取页面参数,将参数中的图片路径，转换成图片base64码，然后返回页面</p>
	 *
	 * @Title: showTempPic
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param info
	 * 		picList：图片信息集合
	 * 			path ： 图片保存路径
	 * @param:  @return
	 * @return: EiInfo
	 * 		 base64 ： 图片base64码
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo showTempPic(EiInfo info) {
		Object object = info.get("picList");
		List<Map<String,String>> list = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
		list.forEach(map ->{
			map.put("base64", CommonUtils.imageToBase64Str(MTUtils.getFilePath(map.get("docId"))));
		});
		info.set("list", list);
		return info;
	}
    

}
