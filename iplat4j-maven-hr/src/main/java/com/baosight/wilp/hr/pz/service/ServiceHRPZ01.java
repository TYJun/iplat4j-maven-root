package com.baosight.wilp.hr.pz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hr.pz.domain.HrPeopleConfig;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *人员配置页面
 * <p>1.初始化查询 initLoad
 * <p>2.页面查询 query
 * <p>3.删除人员配置信息 delete
 * <p>4.调用远程服务获取科室信息 queryDept
 *
 * @Title: ServiceHRPZ01.java
 * @ClassName: ServiceHRPZ01
 * @Package：com.baosight.wilp.hr.pz.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRPZ01 extends ServiceBase {
	/**
	 * 初始化查询
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {
		return this.query(info);
	}

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * @return outInfo
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo query(EiInfo info) {
		EiInfo outInfo = super.query(info,"HRPZ01.query",new HrPeopleConfig());
		return outInfo;
	}

	/**
	 * 删除人员配置信息
	 * delete
	 * @param info id   ：  主键
	 * @return info
	 */
	public EiInfo delete(EiInfo info) {
		// 获取前台传来的id删除一条配置信息
		String id = info.getString("id");
		int num = dao.delete("HRPZ01.delete", id);
		if (num > 0) {
			info.setStatus(1);
			info.setMsg("删除成功");
			return info;
		}
		info.setStatus(-1);
		info.setMsg("删除失败");
		return info;
	}

	/**
	 * @Title: queryDept
	 * <p>1.调用远程服务获取科室信息</p>
	 * @Description: 接口改造(科室)
	 * @param info
	 * @return info
	 * deptNum : 科室编码
	 * deptName : 科室名称
	 */
	public EiInfo queryDept(EiInfo info) {
		// 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
		return BaseDockingUtils.getDeptAllPage(map, "dept");
	}

}
