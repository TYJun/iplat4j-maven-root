package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMTyTmealPic;

import java.util.HashMap;
import java.util.List;


/**
 * 
 * 通用meal图片service
 * 
 * @Title: ServiceSSBMTyTmealPic.java
 * @ClassName: ServiceSSBMTyTmealPic
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:30:06
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMTyTmealPic extends ServiceBase{
	
	/**
	 * 初始化查询
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMTyTmealPic());
		return initLoad;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		//数据查询
		EiInfo outInfo = super.query(inInfo, "SSBMTyTmealPic.query", new SSBMTyTmealPic());
		return outInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<SSBMTyTmealPic> query(String paramString, Object paramObject) {
		return dao.query(paramString, paramObject);
	}
	
	@SuppressWarnings("unchecked")
	public EiInfo getPics(EiInfo inInfo) {
		HashMap<String,Object> paramObject = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		inInfo.set("picList", dao.query("SSBMTyTmealPic.query", paramObject));
		return inInfo;
	}

	public EiInfo insert(EiInfo inInfo) {
		return super.insert(inInfo);
	}
	
	public EiInfo update(EiInfo inInfo) {
		return super.update(inInfo);
	}
	
	public EiInfo delete(EiInfo inInfo) {
		return super.delete(inInfo);
	}
}
