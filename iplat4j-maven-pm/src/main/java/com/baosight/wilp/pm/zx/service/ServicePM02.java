package com.baosight.wilp.pm.zx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.pm.domain.Tpm01;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/**
 * 
* 工程项目：初始化查询、查询工程项目信息、项目拆分、获取合同号、项目完工、打印任务单
 * <p>1.初始化查询 initLoad
 * <p>2.查询工程项目信息 query
 * <p>3.项目拆分 splitProject
 * <p>4.获取合同号 createProjectNo
 * <p>5.项目完工 finishProject
 * <p>6.打印任务单 printTask
 * 
 * @Title: ServicePM02.java
 * @ClassName: ServicePM02
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午2:48:09
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM02 extends ServiceBase {

    /**
     * 
     * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     * @return inInfo
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo initLoad(EiInfo inInfo) {
        // 调用初始化方法
		return super.initLoad(inInfo,new Tpm01());
	}

    /**
     * 
     * @Title: query
     * @Description: 查询工程项目信息
     * @param inInfo
     * @return
     * @see ServiceBase#query(EiInfo)
     */
    public EiInfo query(EiInfo inInfo) {
        // 调用查询工程项目信息
		return super.query(inInfo,"PM01.query",new Tpm01());
	}

	/**
	 * 项目拆分
	 * @Title: splitProject
	 * @Description: 项目拆分
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo splitProject(EiInfo inInfo) {
		//获取参数
		String id = inInfo.get("id").toString();
		// 实例化map
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置map参数
		map.put("id", id);
		// 查询工程项目信息
		List<Tpm01> projectList = dao.query("PM01.query", map);
		// 实体赋值
		Tpm01 PM01 = projectList.get(0);
		PM01.setParentProjectPk(PM01.getId());
		PM01.setId(UUID.randomUUID().toString());
		PM01.setProjectNo(createProjectNo());
		PM01.setContorName(PM01.getProjectPrincipal());
		PM01.setRecCreator(UserSession.getUser().getUsername());
		PM01.setRecCreateTime(DateUtils.curDateTimeStr19());
		// 数据插入
		dao.insert("PM01.insert",PM01);
		inInfo.setMsg("拆分成功");
		return inInfo;
	}
	
	/**
	 * 获取合同号
	 * @Title: createProjectNo 
	 * @return 
	 * @return: String
	 */
	private String createProjectNo(){
	    // 加锁
		synchronized (dao){
		    // 日期转换
			String date = DateUtils.curDateStr8();
			// 查询合同号
			List<String> list = dao.query("PM0103.queryProjectNo",date);
			// 如果参数为空
			if(list==null || list.size() == 0 || list.get(0) == null){
				// 返回合同号
			    return date+"0001";
			} else {
			    // 获得最大合同号
				String maxNo = list.get(0);
				// 返回合同号
				return (Long.parseLong(maxNo)+1L)+"";
			}
		}
	}
	
	/**
	 * 项目完工
	 * @Title: finishProject
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo finishProject(EiInfo inInfo) {
	    // 获取参数
		String id = inInfo.get("id").toString();
		// 实例化map
		Map<String,Object> map = new HashMap<String, Object>();
		// map赋值
		map.put("id", id);
		map.put("projectStatus", "03");
		map.put("finishDate", DateUtils.curDateStr10());
		// 更新项目状态
		dao.update("PM02.updateProjectStatus", map);
		inInfo.setMsg("完工成功");
		//发送短信
		//SmsCallUtils.smsCall("projectFinish", id);
		return inInfo;
	}
	
	/**
	 * 打印任务单
	 * @Title: printTask
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo printTask(EiInfo inInfo) {
	    // 
		return inInfo;
	}


}
