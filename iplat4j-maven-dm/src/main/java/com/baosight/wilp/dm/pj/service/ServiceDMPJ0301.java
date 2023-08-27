package com.baosight.wilp.dm.pj.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.utils.UUID;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员评价页面评价内容service
 *
 *@ClassName: ServiceDMPJ0301
 *@Package: com.baosight.wilp.dm.pj.service
 */
public class ServiceDMPJ0301 extends ServiceBase {
	/**
	 * 此方法用于评价内容页面初始化
	 *
	 * @Title: initLoad
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

    /**
     * 将管理员的评价内容保存进数据库表宿舍管理员评价表中
     *
     * 1、获取当前用户信息.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMPJ03.insert 进行数据的插入，插入评价信息到宿舍管理员评价表.
     *
     * @Title: insertResult
     * @Param EiInfo
     * @return:EiInfo
     */
	public EiInfo insertResult(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息.
		 */
		// 获取当前登陆工号
		String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
				UserSession.getUser().getUsername():(String)inInfo.get("workNo"); // 评价人工号
		Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
		String manId = inInfo.getString("manId"); // 人员id
		String operationName =  userInfo == null ? "" : userInfo.get("name").toString(); // 评价人名称
		String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      // 操作时间
		String manBehavior = inInfo.get("manBehavior") == null ? "" : inInfo.getString("manBehavior"); // 学生行为评价等级
		String evalContent = inInfo.get("evalContent") == null ? "" : inInfo.getString("evalContent"); // 评价备注栏
		String id = UUID.randomUUID().toString();
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();

		map.put("id",id);
		map.put("manId",manId);
		map.put("manBehavior",manBehavior);
		map.put("evalContent",evalContent);
		map.put("evalCreator",loginName);
		map.put("evalCreateName",operationName);
		map.put("evalTime",operationTime);
		/*
		 * 3、以map作为参数执行 DMPJ03.insert 进行数据的插入，插入评价信息到宿舍管理员评价表.
		 */
		dao.insert("DMPJ03.insert", map);
		/*
		 * 返回操作结果.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.setMsgKey("200");
		outInfo.setMsg("操作成功");
		return outInfo;
	}
	
}
