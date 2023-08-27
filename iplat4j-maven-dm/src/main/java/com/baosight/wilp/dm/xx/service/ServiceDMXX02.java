package com.baosight.wilp.dm.xx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.dm.xx.domain.DormsContrastTable;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  宿舍类型与人员类型对照页面service
 *
 * @Title: ServiceDMXX02.java
 * @ClassName: ServiceDMXX02
 * @Package：com.baosight.wilp.dm.xx.service
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXX02 extends ServiceBase {
	/**
	 * 页面初始化加载
	 * @Title: initLoad
	 * @param inInfo
	 * @return
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 宿舍类型与人员类型对照查询
	 * @Title: query
	 * @param inInfo
	 * @return
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "DMXX02.query", new DormsContrastTable());
		return outInfo;
	}

	/**
	 * 宿舍类型与人员类型对照新增
	 * @Title: insertData
	 * @param inInfo
	 * @return
	 */
	public EiInfo insertData(EiInfo inInfo) {
		String id = UUID.randomUUID().toString();	/* 主键*/
		/**
		 * 1.获取dormProperties-宿舍属性、employmentNature职工性质
		 */
		String dormProperties = inInfo.getString("dormProperties"); /* 宿舍属性(学生宿舍/职工宿舍)*/
		String employmentNature = inInfo.getString("employmentNature"); /* 职工性质*/
		String recCreator = UserSession.getLoginName();		/* 创建人*/
		String recCreateName = UserSession.getLoginCName();	/* 创建人*/
        String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());		/* 创建时间*/
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
		map.put("dormProperties", dormProperties);
		map.put("employmentNature", employmentNature);
        map.put("recCreator", recCreator);
        map.put("recCreateName", recCreateName);
        map.put("recCreateTime", recCreateTime);
		/**
		 * 2.调用sql方法DMXX02.insert向员工类型与宿舍类型关联配置表（dorms_contrast_table）插入信息
		 */
		dao.insert("DMXX02.insert",map);
	    EiInfo outInfo = new EiInfo();
	    outInfo.set("id", id);
		outInfo.setMsg("新增数据成功！");
		outInfo.setMsgKey("200");
		return outInfo;
	}

	/**
	 * 宿舍类型与人员类型对照修改
	 * @Title: updateData
	 * @param inInfo
	 * @return
	 */
	public EiInfo updateData(EiInfo inInfo) {
		String id = inInfo.getString("id");	/* 主键*/
		/**
		 * 1.获取dormProperties-宿舍属性、employmentNature职工性质
		 */
		String dormProperties = inInfo.getString("dormProperties"); /* 宿舍属性(学生宿舍/职工宿舍)*/
		String employmentNature = inInfo.getString("employmentNature"); /* 职工性质*/
		String recRevisor = UserSession.getLoginName();		/* 修改人*/
		String recReviseName = UserSession.getLoginCName();	/* 修改人*/
		String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());		/* 修改时间*/
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("dormProperties", dormProperties);
		map.put("employmentNature", employmentNature);
		map.put("recRevisor", recRevisor);
		map.put("recReviseName", recReviseName);
		map.put("recReviseTime", recReviseTime);
		/**
		 * 2.调用sql方法DMXX02.update修改员工类型与宿舍类型关联配置表（dorms_contrast_table）信息
		 */
		dao.insert("DMXX02.update",map);
		EiInfo outInfo = new EiInfo();
		outInfo.set("id", id);
		outInfo.setMsg("更新数据成功！");
		outInfo.setMsgKey("200");
		return outInfo;
	}

}
