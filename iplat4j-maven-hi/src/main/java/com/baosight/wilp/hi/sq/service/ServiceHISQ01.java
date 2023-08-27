package com.baosight.wilp.hi.sq.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该页面为医院标识申请管理
 * <p>1.初始化查询 initLoad
 * <p>2.数据查询 query
 * <p>3.标识审批 examine
 * <p>3.标识撤回 withdraw</>
 * <p>4.标识删除 delete
 * @Title: ServiceHISQ01.java
 * @ClassName: ServiceHISQ01
 * @Package：com.baosight.wilp.hi.sq.service
 * @author: liangyongfei
 * @date: 2022年8月21日 下午1:29:57
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceHISQ01 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 医院标识申请管理界面
     * @param inInfo
     * applyNo 申请单号
     * iconName 标识名称
     * status 申请状态
     * recCreateTimeS 申请日期起
     * recCreateTimeE 申请日期止
     * @return EiInfo
     * id 主键
     * applyNo 申请单号
     * status 状态
     * applyDeptName 申请科室
     * iconName 标识名称
     * iconZhContent 标识中文内容
     * iconEnContent 标识英文内容
     * classifyName 标识分类名称
     * spotName 安装地点
     * iconAmount 数量
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return this.query(inInfo);
	}
	
	 /**
     * @Title: query
     * @Description: 标识申请数据查询
	 * <p>1.创建数组并赋值
	  * <p>2.调用接口创建map
	  * <p>3.调用查询方法
	  * <p>4.如果获取查询信息不为空
	  * <p>5.返回封装方法
     * 医院标识标识申请界面
     * 通过申请单号、标识名称、申请状态、申请日期起、申请日期止条件查询
     * 回显申请单号、状态、申请科室、标识名称、标识中文内容、标识英文内容、标识分类名称、安装地点、数量
	  * @param inInfo
	  * applyNo 申请单号
	  * iconName 标识名称
	  * status 申请状态
	  * recCreateTimeS 申请日期起
	  * recCreateTimeE 申请日期止
	  * @return EiInfo
	  * id 主键
	  * applyNo 申请单号
	  * status 状态
	  * applyDeptName 申请科室
	  * iconName 标识名称
	  * iconZhContent 标识中文内容
	  * iconEnContent 标识英文内容
	  * classifyName 标识分类名称
	  * spotName 安装地点
	  * iconAmount 数量
     */
	public EiInfo query(EiInfo inInfo) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String deptNum = null;
		String deptName = null;
		if(staffByUserId !=null){
			if ("宣传科".equals(staffByUserId.get("deptName"))){
				deptNum = null;
			}else {
				deptNum = (String) staffByUserId.get("deptNum");
//				deptName = (String) staffByUserId.get("deptName");
			}

		}
		// 1.创建数组并赋值
		String[] parameter = {"applyNo","iconName","status","recCreateTimeS","recCreateTimeE"};

		// 数组转list
		List<String> plist = Arrays.asList(parameter);
		// 2.调用接口创建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		map.put("deptNum",deptNum);
//		map.put("deptName",deptName);
		System.out.println("map1:"+map);
		// 3.调用查询方法
		List<Map<String, ?>> result = dao.query("HISQ01.queryF", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 获取查询信息总数
		int count = dao.count("HISQ01.queryF", map);
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
     * @Description: 标识申请提交
	 * <p>1.获取参数(id)</p>
	 * <p>2.调用更新方法</p>
     * 医院标识申请管理界面
     * 通过主键
     * 将符合条件的信息审批
     * @param inInfo
     * id 标识主键
     * @return: EiInfo
     */
	public EiInfo examine(EiInfo inInfo) {
	    // 1、获取参数(id)
		String id = inInfo.getAttr().get("id").toString();
		String status = inInfo.getAttr().get("status").toString();
		if("null".equals(id)){
			inInfo.setMsg("请选择需要提交的记录");
		}else {
			// 实例化map
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			//03为审批通过
			if("03".equals(status)){
					inInfo.setMsg("审核通过的工单不可以再次提交");
				}else {
					// 2.调用更新方法,通过id
					dao.update("HISQ01.examine", map);
				}
		}
		return inInfo;
	}


	/**
	 * @Title: withdraw
	 * @Description: 标识申请撤回
	 * <p>1.获取参数(id)</p>
	 * <p>2.调用更新方法</p>
	 * 医院标识申请管理界面
	 * 通过主键
	 * 将符合条件的信息撤回
	 * @param inInfo
	 * id 标识主键
	 * @return: EiInfo
	 */

	public EiInfo  withdraw (EiInfo inInfo) {
		//1、获取参数(id)
		String id = inInfo.getAttr().get("id").toString();
		String status = inInfo.getAttr().get("status").toString();
		if("null".equals(id)){
			inInfo.setMsg("请选择需要撤回的记录");
		}else {
			//02为待审批
			if("02".equals(status)){
				HashMap<String, String> map = new HashMap<>();
				map.put("id", id);
				//1、调用更新方法，通过id
				dao.update("HISQ01.withdraw", map);
			}else {
				inInfo.setMsg("请选择待审批的工单");
			}
		}
		return inInfo;
	}
	
	/**
     * @Title: delete
     * @Description: 删除
	 * <p>1、获取参数(id)</p>
	 * <p>2、调用删除方法</p>
     * 医院标识申请管理界面
     * 通过主键
     * 将符合条件的信息删除
     * @param inInfo
     * id 主键
     * @return: EiInfo
     */

	public EiInfo delete(EiInfo inInfo) {
		// 1、获取参数(id)
		String id = inInfo.getAttr().get("id").toString();
		String status = inInfo.getAttr().get("status").toString();
		if("null".equals(id)){
			inInfo.setMsg("请选择需要删除的记录");
		}else {
			//01代表新增状态
			if("01".equals(status)){
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("id",id);
				//2、删除医院申请基础信息，通过id
				dao.delete("HISQ01.delete",map);
				//2.1、删除医院申请附件信息，通过id
				dao.delete("HISQ01.deleteFail",map);
			}else {
				inInfo.setMsg("已提交的工单不允许删除");
			}
		}

		return inInfo;
	}
}
