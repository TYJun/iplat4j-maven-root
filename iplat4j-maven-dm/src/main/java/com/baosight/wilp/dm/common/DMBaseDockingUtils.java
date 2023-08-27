package com.baosight.wilp.dm.common;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 宿舍基础对接工具类
 * 调用基础档案的微服务接口工具.
 * 
 * @Title: DMBaseDockingUtils.java
 * @ClassName: DMBaseDockingUtils
 * @Package：com.baosight.wilp.dm.common
 * @author: fangzekai
 * @date: 2022年02月08日 上午9:27:21
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class DMBaseDockingUtils {
	/**
	 * 查询人员信息（有分页）
	 * 
	 * @Title: queryPersonnel
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		offset		 :分页开始的索引
	 *		limit		 :获取的数量
	 *		workNo		 :员工工号
	 *		userName	 :用户姓名
	 *		deptNum      :科室编码
	 *		datagroupCode:院区编码
	 * @param:  @return
	 * @return: EiInfo  
	 * 		id			：	员工ID
	 *		workNo		：	员工工号 
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 * 		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称 
	 *		idNo		：	身份证号
	 *		type		：	员工类型编码
	 *		staffType	：	员工类型
	 *		isService	：	是否是服务人员
	 *		status		：	员工状态编码
	 *		isStatus	：	员工状态 
	 * @throws
	 */
	public static EiInfo queryPersonnelPage (Map<String, Object> map) {
		//获取参数
		map.put("datagroupCode", map.get("dataGroupCode"));
		map.put("deptNum", map.get("wgroupNum"));
		return BaseDockingUtils.getStaffAllPage(map, "person");
	}
	
	/**
	 * 查询人员信息（无分页）
	 * @Title: queryPersonnelNoPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		workNo		 :员工工号
	 *		userName	 :用户姓名
	 *		datagroupCode:院区编码
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id			：	员工ID
	 *		workNo		：	员工工号 
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 * 		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称 
	 *		idNo		：	身份证号
	 *		type		：	员工类型编码
	 *		staffType	：	员工类型
	 *		isService	：	是否是服务人员
	 *		status		：	员工状态编码
	 *		isStatus	：	员工状态  
	 * @throws
	 */
	public static EiInfo queryPersonnelNoPage (Map<String, Object> map) {
		//获取参数
		map.put("datagroupCode", map.get("dataGroupCode"));
		map.put("workNo", map.get("manNo"));
		List<Map<String, Object>> list = BaseDockingUtils.getStaffAllNoPage(map);
		return CommonUtils.BuildOutEiInfo("person", list);
	}
	
	/**
	 * 查询指定工号的人员信息
	 * 
	 * <p>调用微服务接口S_AC_FW_02，按工号查询人员信息</p>
	 * 
	 * @Title: queryPersonnelByWorkNo
	 * @Description: 查询指定工号的人员信息
	 * @param:  @param workNo : 工号
	 * @param:  @return
	 * @return: Map<String,String> 
	 * 		id			：	员工ID
	 *		workNo		：	员工工号 
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 * 		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称 
	 *		idNo		：	身份证号
	 *		type		：	员工类型编码
	 *		staffType	：	员工类型
	 *		isService	：	是否是服务人员
	 *		status		：	员工状态编码
	 *		isStatus	：	员工状态  
	 * @throws
	 */
	public static Map<String, Object> queryPersonnelByWorkNo(String workNo) {
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		Map<String, Object> rMap = BaseDockingUtils.getStaffByWorkNo(workNo);
		return rMap;
	}

	
	/**
	 * 根据用户所属科室编码获取返回结果workNo字符串
	 * 
	 * <p>调用微服务S_AC_FW_03根据科室编码查询科室下员工信息，遍历员工信息获取工号</p>
	 * 
	 * @Title: getStaffByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum ： 科室编码
	 * @param:  @return 
	 * @return: String  ： 工号字符串
	 * @throws
	 */
	public static String getStaffByDeptNum (String deptNum) {
		return BaseDockingUtils.getStaffWorkNosByDeptNum(deptNum);
	}
	
	/**
	 * 科室查询 -DMRE01
	 * 
	 * <p>调用微服务接口S_AC_FW_05查询科室信息</p>
	 * 
	 * @Title: queryDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
     * 		deptNum：科室编码
     * 		deptName：科室名称
     * 		dataGroupCode： 账套（院区）
     * @param:  @return
     * @return: EiInfo 
     * 		deptNum：科室编码
     * 		deptName：科室名称
	 * @throws
	 */
	public static EiInfo queryDept (Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		//调用微服务接口S_AC_FW_05查询科室信息
		return BaseDockingUtils.getDeptAllPage(map, "dept");
	}

//	/**
//	 * 发送短信
//	 * 
//	 * <p>调用微服务接口S_MC_FW_01发送短信</p>
//	 * 
//	 * @Title: sendMsg
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param:  @param recvList ： 接收人集合
//	 * @param:  @param smsContent ：消息内容
//	 * @param:  @return
//	 * @return: boolean  : 是否成功结果
//	 * @throws
//	 */
//	public static boolean sendMsg(List<Map<String,String>> recvList, String smsContent){
//		List<String> list = listChange(recvList);
//		String templateCode = CSUtils.getCSConfigValueText("dxTemplateCode");
//		List<String> paramList = new ArrayList<>();
//		paramList.add(smsContent);
//		System.out.println("保洁短信参数：workNoList:"+list.toString()+",paramList:"+paramList.toString()+"templateCode:"+templateCode);
//		return BaseDockingUtils.sendMsg(list, paramList, templateCode);
//	}
	
//	/**
//	 * 消息推送
//	 * 
//	 * <p>调用微服务接口S_MC_FW_02发送消息推送</p>
//	 * 
//	 * @Title: pushMsg
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param:  @param recvList ： 接收人集合
//	 * @param:  @param smsContent ： 消息内容
//	 * @param:  @return
//	 * @return: boolean  : 是否成功结果
//	 * @throws
//	 */
//	public static boolean pushMsg(List<Map<String,String>> recvList, String smsContent){
//		String appCode = PlatApplicationContext.getProperty("common_appCode");
//		String templateCode = CSUtils.getCSConfigValueText("tsTemplateCode");
//		List<String> list = listChange(recvList);
//		List<String> paramList = new ArrayList<>();
//		paramList.add(smsContent);
//		System.out.println("保洁消息推送参数：workNoList:"+list.toString()+",paramList:"+paramList.toString()
//			+"templateCode:"+templateCode+",appCode:"+appCode);
//		return BaseDockingUtils.pushMsg(list, paramList, templateCode, appCode, null);
//	}
//	
	/**
	 * 判断EiInfo是否为空 
	 * @Title: isEmptyInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param outInfo : EiInfo对象
	 * @param:  @param blockId : Block的id
	 * @param:  @return
	 * @return: boolean  ： 是否为空结果
	 * @throws
	 */
	public static boolean isEmptyInfo(EiInfo outInfo, String blockId){
		blockId = StringUtils.isBlank(blockId) ? "result" : blockId;
		EiBlock block = outInfo.getBlock(blockId);
		if(block == null){
			return true;
		}
		if(block.getRowCount() > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取工号list
	 * @Title: listChange
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param recvList ： 人员信息集合List
	 * @param:  @return
	 * @return: List<String>  ： 工号集合List
	 * @throws
	 */
	private static List<String> listChange(List<Map<String,String>> recvList) {
		List<String> list = new ArrayList<>();
		recvList.forEach(map -> list.add(map.get("workNo")));
		return list;
	}
	
	/**
	 * 获取指定人员的院区编码
	 * 
	 * <p>调用微服务接口S_AU_FW_03获取人员的院区编码</p>
	 * 
	 * @Title: getDatagroupCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo ： 工号
	 * @param:  @return
	 * @return: String  ： 院区编码
	 * @throws
	 */
	public static String getDatagroupCode (String workNo) {
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		return BaseDockingUtils.getUserGroupByWorkNo(workNo);
	}

	/**
	 * 获取指定科室的院区编码
	 * 
	 * <p>调用微服务接口S_AU_FW_04获取指定科室的院区编码</p>
	 * 
	 * @Title: getDatagroupCodeByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum : 科室编码
	 * @param:  @return
	 * @return: String  ： 账套
	 * @throws
	 */
	public static String getDatagroupCodeByDeptNum(String deptNum) {
		return BaseDockingUtils.getUserGroupByDeptNum(deptNum);
	}

}
