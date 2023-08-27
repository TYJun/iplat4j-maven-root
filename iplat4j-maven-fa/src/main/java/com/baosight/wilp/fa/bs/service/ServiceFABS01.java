package com.baosight.wilp.fa.bs.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.fa.utils.OneSelfUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 固定资产报损接口.
 * 固定资产报损初始化方法.
 * 固定资产报损初始化查询方法.
 * @author zhaowei
 * @date 2022/8/26 14:52
 */
public class ServiceFABS01 extends ServiceBase{

	/**
	 * 固定资产报损初始化方法
	 * 1.调用固定资产报损方法
	 * @author zhaowei
	 * @date 2022/6/7 11:21
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.调用固定资产报损方法
	    return this.query(info);
	}

	/**
	 * 固定资产报损初始化查询方法
	 * 1.权限判断.
	 * 2.构建前端分页参数.
	 * 3.查询固定资产报损信息.
	 * @author zhaowei
	 * @date 2022/6/7 11:22
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
    public EiInfo query(EiInfo info) {
		// 1.权限判断
		String deptName = OneSelfUtils.specifyDept();
		if (StringUtils.isNotEmpty(deptName)){
			info.set("deptName",deptName);
		}
		// 2.构建前端分页参数
		Map<String, Object> pageMap = new HashMap<>(8);
		pageMap.put("offset",0);
		pageMap.put("limit",10);
		if (info.getBlocks().size() > 0) {
			EiBlock eiBlock = (EiBlock) info.getBlocks().get("result");
			pageMap = eiBlock.getAttr();
		}
		// 3.查询固定资产报损信息
		List<Map<String,Object>> queryFaTransferInfo = dao.query("FABS01.queryFaReimburseInfo", info.getAttr(),(Integer) pageMap.get("offset"),(Integer) pageMap.get("limit"));
		int count = dao.count("FABS01.queryFaReimburseInfo", info.getAttr());
		pageMap.put("count",count);
		info.setRows("result", queryFaTransferInfo);
		info.setAttr(pageMap);
		return info;
    }
}

