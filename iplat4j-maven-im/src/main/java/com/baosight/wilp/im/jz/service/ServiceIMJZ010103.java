package com.baosight.wilp.im.jz.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.wilp.im.common.util.DeviceEiUtil;

/**
 * 巡检新增项目：跳转项目初始化、项目查询
 * <p>1.项目初始化 initLoad
 * <p>2.项目查询 query
 * 
 * @Title: ServiceDIJZ010103.java
 * @ClassName: ServiceDIJZ010103
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午5:08:04
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMJZ010103 extends ServiceBase {
	
	protected static final Logger logger = LoggerFactory.getLogger(ServiceIMJZ010103.class);
	
	/**
	 * 跳转项目初始化
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * <p>调用下面query方法
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}
	
	/**
	 * 项目查询
	 * &lt;p&gt;Title: query&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * itemName 项目名称
	 * @return
	 * itemId    项目id
	 * content   巡检项目
	 * referenceValue  巡检项目参考值
	 * projectDesc     项目描述
	 * actualValueUnit  实际值单位
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
		String[] param = {"content","moduleId"};
		Map<String, Object> buildParam = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
		List list = dao.query("IMXM01.queryItem", buildParam);
		int count = dao.count("IMXM01.countItem", buildParam);
		return DeviceEiUtil.buildResult(inInfo, list, count, "result");
	}
	
	/**
	 * 加载分类树的信息
	 * @param inInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public EiInfo queryTree(EiInfo inInfo) 
	{
		//1 获取参数
		String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");

		Map<String, String> queryMap = new HashMap<>();
		if("$".equals(node))
		{
			queryMap.put("parentId", "root");
		} 
		else 
		{
			queryMap.put("parentId", node);
		}
		queryMap.put("dataGroupCode", inInfo.getString("dataGroupCode"));
		//2.查询数据
		List rows = dao.query("IMJZ01.queryProjectTypeTree", queryMap);
		//3 增加节点 block 块
		EiInfo outInfo = new EiInfo();
		EiBlock outBlock = outInfo.addBlock(node);
		outBlock.addRows(rows);
		return outInfo;
	}

}
