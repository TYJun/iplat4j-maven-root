package com.baosight.wilp.cm.dj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 该页面为合同登记管理
 * 主要包含对合同登记的新增、删除、编辑、查看、审批功能
 * 合同模块：初始化查询、合同数据查询、合同审批、合同删除
 * <p>1.初始化查询 initLoad
 * <p>2.合同数据查询 query
 * <p>3.合同审批 examine
 * <p>4.合同删除 delete
 * @Title: ServiceCMDJ01.java
 * @ClassName: ServiceCMDJ01
 * @Package：com.baosight.wilp.cm.dj.service
 * @author: zhangjiaqian
 * @date: 2021年8月24日 下午1:29:57
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMDJ01 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同登记管理界面
     * 通过合同号、合同名称、签订日期起、签订日期止条件查询
     * 回显合同号、合同名称、合同类型、合同签订日期、计划生效日期、计划终止日期、币种、预算、质保期(月)、合同状态、合同管理员、制单人、制单时间
     * @param EiInfo
     * contNo 合同号
     * contName 合同名称
     * contDeptName 合同科室
     * contTypeNum 合同类型
     * contSignTimeS 签订日期起
     * contSignTimeE 签订日期止
     * @return EiInfo
     * id 主键
     * contNo 合同号
     * contName 合同名称
     * contTypeNum 合同类型
     * contSignTime 合同签订日期
     * planTakeefTime 计划生效日期
     * planFinishTime 计划终止日期
     * currType 币种
     * budget 预算
     * quarPeriod 质保期
     * contStatus 合同状态
     * contAdmin 合同管理员
     * billMaker 制单人
     * billMakeTime 制表时间
     */
	public EiInfo initLoad(EiInfo inInfo) {
		//String path=System.getProperty("user.dir");
		//System.out.println(path);
//		CmMsgService cs=new CmMsgService();
//		cs.sendMsg();
	    // 调用查询方法
		return this.query(inInfo);
	}
	
	 /**
     * @Title: query
     * @Description: 合同数据查询
	 * <p>1.创建数组并赋值
	  * <p>2.调用接口创建map
	  * <p>3.调用查询方法
	  * <p>4.如果获取查询信息不为空
	  * <p>5.返回封装方法
     * 合同登记管理界面
     * 通过合同号、合同名称、签订日期起、签订日期止条件查询
     * 回显合同号、合同名称、合同类型、合同签订日期、计划生效日期、计划终止日期、币种、预算、质保期(月)、合同状态、合同管理员、制单人、制单时间
     * @param EiInfo
     * contNo 合同号
     * contName 合同名称
     * contDeptName 合同科室
     * contTypeNum 合同类型
     * contSignTimeS 签订日期起
     * contSignTimeE 签订日期止
     * @return EiInfo
     * id 主键
     * contNo 合同号
     * contName 合同名称
     * contTypeNum 合同类型
     * contSignTime 合同签订日期
     * planTakeefTime 计划生效日期
     * planFinishTime 计划终止日期
     * currType 币种
     * budget 预算
     * quarPeriod 质保期
     * contStatus 合同状态
     * contAdmin 合同管理员
     * billMaker 制单人
     * billMakeTime 制表时间
     */
	public EiInfo query(EiInfo inInfo) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 1.创建数组并赋值
		String[] parameter = {"contNo","contName","contDeptName","contTypeNum","contSignTimeS","contSignTimeE"};
		// 数组转list
		List<String> plist = Arrays.asList(parameter);
		// 2.调用接口创建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
//		map.put("contDeptNum",staffByUserId.get("deptNum"));
		// 3.调用查询方法
		List<Map<String, ?>> result = dao.query("CMDJ01.query", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 获取查询信息总数
		int count = dao.count("CMDJ01.query", map);
		// 4.如果获取查询信息不为空
		if(result != null && result.size() > 0){
		    // 5.返回封装方法
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(result.get(0)), result, count);
		} else { 
		    // 返回参数
			return inInfo; 
		}
	}
	
	/**
     * @Title: examine
     * @Description: 合同审批
	 * <p>1.获取当前登录用户信息</p>
	 * <p>2.调用更新方法</p>
     * 合同登记管理界面
     * 通过合同主键
     * 将符合条件的信息审批
     * @param EiInfo
     * id 合同主键
     * @return: EiInfo
     */
	public EiInfo examine(EiInfo inInfo) {
	    // 获取参数
		String id = inInfo.getAttr().get("id").toString();
		// 实例化map
		HashMap<String,String> map = new HashMap<String,String>();
		// 1.获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//  获取参数
		String examineName = (String)staffByUserId.get("name");
		String examineTime = DateUtils.curDateTimeStr19();
		// 赋值map
		map.put("id",id);
		map.put("checkMaker", examineName);
		map.put("checkTime", examineTime);
		// 2.调用更新方法
		dao.update("CMDJ01.examine",map);
		// 返回参数
		return inInfo;

	}
	
	/**
     * @Title: delete
     * @Description: 删除
     * 合同登记管理界面
     * 通过合同主键
     * 将符合条件的信息删除
     * @param EiInfo
     * id 合同主键
     * @return: EiInfo
     */
	public EiInfo delete(EiInfo inInfo) {
	    // 调用删除方法
		return super.delete(inInfo, "CMDJ01.delete");
	}


}
