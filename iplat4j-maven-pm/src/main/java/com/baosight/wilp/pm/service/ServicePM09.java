package com.baosight.wilp.pm.service;

import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.pm.domain.TpmSmsConfig;
import com.baosight.wilp.pm.utils.DataGroupUtils;
/**
 * 
 * 工程项目：初始化查询、数据查询、保存短信配置
 * <p>1.初始化查询 initLoad
 * <p>2.数据查询 query
 * <p>3.保存短信配置 save
 * 
 * @Title: ServicePM09.java
 * @ClassName: ServicePM09
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午5:17:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM09 extends ServiceBase {
	
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     * @return inInfo
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用初始化方法
		return super.initLoad(inInfo,new TpmSmsConfig());
	}

	/**
	 * 数据查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
	    // 查询数据
		EiInfo info = super.query(inInfo,"PM09.query",new TpmSmsConfig());
		// info封装数据
		inInfo.set("data", info.getBlock("result").getRows());
		// 数据返回
		return inInfo;
	}
	
	/**
	 * 保存短信配置
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo save(EiInfo inInfo) {
		//获取参数
		List<Map<String,Object>> list = (List<Map<String, Object>>) inInfo.get("list");
		//遍历保存
		TpmSmsConfig smsConfig = new TpmSmsConfig();
		for (Map<String, Object> map : list) {
			smsConfig.fromMap(map);
			smsConfig.setDataGroupCode(DataGroupUtils.getDataGroup());
			//删除
			dao.delete("PM09.delete", smsConfig.getId());
			dao.insert("PM09.insert", smsConfig);
		}
		inInfo.setMsg("保存成功");
		return inInfo;
	}

}
