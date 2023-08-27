package com.baosight.wilp.dm.da.service;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.dm.utils.XServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TODO(档案科室查询service)
 * @Title: ServiceYSDAKS01.java
 * @ClassName:ServiceYSDAKS01
 * @Package：com.baosight.wilp.ys.da.service
 * @author: hou'guang'hui
 * @date: 2021年2月19日 下午3:37:14
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 */

public class ServiceDMDAKS01 extends ServiceBase{
	
    /**
     * @Title: 查询档案中心科室信息;  
     * @param inInfo
     * @return inInfo
     */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiBlock block = inInfo.getBlock("inqu_status");
		EiBlock resultBlock = inInfo.getBlock("result");
		String deptNum = "";
		String deptName = "";
		if(block != null) {
		    deptNum = block.getCellStr(0, "deptNum");
		    deptName = block.getCellStr(0, "deptName");
		}
		
		String offset =  StringUtils.toString(resultBlock.getAttr().get("offset"));
		String limit = StringUtils.toString(resultBlock.getAttr().get("limit"));
		limit = StringUtils.isNotEmpty(limit) ? limit : "10";
		offset = StringUtils.isNotEmpty(offset) ? offset : "0";
		inInfo.set(EiConstant.serviceId, "S_AC_FW_05");
		inInfo.set("offset", offset);
		inInfo.set("limit", limit);
		inInfo.set("deptNum", deptNum);
		inInfo.set("deptName", deptName);
		EiInfo call = XServiceManager.call(inInfo);
		if(call.getStatus() < 0){
			//没查到
			inInfo.setMsg("查询科室信息失败" + call.getMsg());
			inInfo.setStatus(-1);
		}else {
			inInfo.setBlock(call.getBlock("result"));
		}
		return inInfo;
	}

	public EiInfo queryId(EiInfo inInfo) {
		EiBlock block = inInfo.getBlock("inqu_status");
		EiBlock resultBlock = inInfo.getBlock("result");
		String userId = "";
		String workNo = "";
		if(block != null) {
			userId = block.getCellStr(0, "userId");
			workNo = block.getCellStr(0, "workNo");
		}

		String offset =  StringUtils.toString(resultBlock.getAttr().get("offset"));
		String limit = StringUtils.toString(resultBlock.getAttr().get("limit"));
		limit = StringUtils.isNotEmpty(limit) ? limit : "10";
		offset = StringUtils.isNotEmpty(offset) ? offset : "0";
		inInfo.set(EiConstant.serviceId, "S_AC_FW_02");
		inInfo.set("userId", userId);
		inInfo.set("workNo", workNo);
		EiInfo call = XServiceManager.call(inInfo);
		if(call.getStatus() < 0){
			//没查到
			inInfo.setMsg("查询人员失败" + call.getMsg());
			inInfo.setStatus(-1);
		}else {
			inInfo.setBlock(call.getBlock("result"));
		}
		return inInfo;
	}

}
