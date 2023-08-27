package com.baosight.wilp.vi.dj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.vi.common.domain.ViVistingInfo;
import com.baosight.wilp.vi.utils.ViUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.Map;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2023年06月15日 18:51
 */
public class ServiceVIDJ01 extends ServiceBase {
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		String name = "";
		String deptNum = "";
		if (ViUtils.getDataCode("WILP.vi.lookOneself")){
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			String workNo = (String) staffByUserId.get("workNo");
			name = (String) staffByUserId.get("name");
			deptNum = (String) staffByUserId.get("deptNum");
			if ("14802".equals(workNo)){
				inInfo.set("inqu_status-0-superMan","superMan");
				deptNum = "";
				name = "";
			}
		}
		inInfo.set("inqu_status-0-auditorStep","0");
		inInfo.set("inqu_status-0-nterviewerName",name);
		inInfo.set("inqu_status-0-deptNum",deptNum);
		EiInfo outInfo = super.query(inInfo, "VIDJ01.queryViVistingInfo", new ViVistingInfo());
		return outInfo;
	}
}
