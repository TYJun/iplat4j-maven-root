package com.baosight.wilp.hr.lz.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.xx.domain.HrMan;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *确认离职子页面
 * <p>1.初始化查询 initLoad
 *
 * @Title: ServiceHRLZ0201.java
 * @ClassName: ServiceHRLZ0201
 * @Package：com.baosight.wilp.hr.lz.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRLZ0201 extends ServiceBase {
    /**
     * 初始化查询
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
     * @return info
     * @see ServiceBase#query(EiInfo)
     */
	@SuppressWarnings("all")
	public EiInfo initLoad(EiInfo info) {
		return info;
	}

}
