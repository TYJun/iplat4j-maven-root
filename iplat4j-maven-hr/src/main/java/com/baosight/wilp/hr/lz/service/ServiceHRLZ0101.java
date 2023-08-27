package com.baosight.wilp.hr.lz.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.xx.domain.HrMan;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 *人员配置页面
 * <p>1.初始化查询 initLoad
 * <p>2.预离职登记 update
 *
 * @Title: ServiceHRLZ0101.java
 * @ClassName: ServiceHRLZ0101
 * @Package：com.baosight.wilp.hr.lz.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRLZ0101 extends ServiceBase {
    /**
     * 初始化查询
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
	 * id  主键
     * @return
     * 		info
     * @see ServiceBase#query(EiInfo)
     */
	@SuppressWarnings("all")
	public EiInfo initLoad(EiInfo info) {
		return info;
	}

	/**预离职登记
	 * update
	 * @param info
	 * rows   获取人员的行
	 * @return info
	 */
	@SuppressWarnings("all")
	public EiInfo update(EiInfo info) {
		// 获取行，更新预离职时间
		List<Map<String,String>> rows = (List<Map<String,String>>)info.get("rows");
		rows.forEach(e->{
			// 更新人员表中预离职时间
			dao.update("HRLZ01.updateQuit",e);
		});
		return info;
	}

}
