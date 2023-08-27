package com.baosight.wilp.hr.xx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *入职确认页面初始化
 * <p>1.初始化查询 initLoad
 * @Title: ServiceHRXX0102.java
 * @ClassName: ServiceHRXX0102
 * @Package：com.baosight.wilp.hr.xx.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRXX0102 extends ServiceBase {
    /**
     * 初始化查询
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
     * @return  info
     * @see ServiceBase#query(EiInfo)
     */
	public EiInfo initLoad(EiInfo info){
		return info;
	}

}
