package com.baosight.wilp.ss.bm.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMCpxx02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 菜品组成service
 * 
 * @Title: ServiceSSBMCPXX02.java
 * @ClassName: ServiceSSBMCPXX02
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:05:00
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMCPXX02 extends ServiceBase{
	

    /**
     * 
     * 初始化查询
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMCpxx02());
		return initLoad;
	}

	/**
	 * grid数据集查询
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo query = new EiInfo();
		//获取传参
		Map attr = inInfo.getAttr();
		String relationId = null;
		if(attr.size() > 0) {
			if(attr.containsKey("relationId")) {
				relationId = attr.get("relationId").toString();
			}
		}
		if(!StringUtils.isBlank(relationId)) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("relationId", relationId);
			map.put("sqlName", "SSBMCpxx02.query");
			List<HashMap<String,Object>> listMenuType  = dao.query("SSBMCpxx02.query", map);
			query.addBlock("description").addRows(listMenuType);
		}else {
			query = super.query(inInfo, "SSBMCpxx02.query", new SSBMCpxx02());
			query.addBlock("description").addRows(query.getBlock("result").getRows());
		}
		return query;
	}


	/**
	 * 
	 * 删除数据
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		return super.delete(inInfo, "SSBMCpxx02.delete");
	}


	/**
	 * 
	 * 保存菜品描述
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//将数据填充到result
			inInfo.addBlock("result").addRows(inInfo.getBlock("description").getRows());
			//保存数据
			outInfo = super.insert(inInfo, "SSBMCpxx02.insert");
		}catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("保存失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}
	

	/**
	 * 
	 * 编辑数据
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			
			//将数据填充到result
			inInfo.addBlock("result").addRows(inInfo.getBlock("description").getRows());
			//更新数据
			outInfo = super.update(inInfo, "SSBMCpxx02.update"); 
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("更新失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}
	
}
