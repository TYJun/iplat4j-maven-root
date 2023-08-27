package com.baosight.wilp.hi.xx.service;

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
 * 该页面为医院标识信息管理页面
 * <p>1.初始化查询 initLoad
 * <p>2.标识信息数据查询 query
 * <p>3.标识启用 enable
 * <p>4.标识停用 deactivate
 * <p>5.标识删除 delete
 * @Title: ServiceHIXX01.java
 * @ClassName: ServiceHIXX01
 * @Package：com.baosight.wilp.hi.xx.service
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
public class ServiceHIXX01 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 医院标识信息管理界面
     * @param inInfo
     * iconNum 标识编码
     * iconName 标识名称
     * status 标识状态
     * deptName 使用科室
     * installDateS 安装日期起
     * installDateE 安装日期止
     * @return EiInfo
     * id 主键
     * iconNum 标识编码
     * iconName 标识名称
     * iconZhContent 标识中文内容
     * iconEnContent 标识英文内容
     * classifyName 标识分类名称
     * iconAmount 数量
     * status 状态
     * deptName 使用科室
     * spotName 安装地点
     * installDate 安装日期
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return this.query(inInfo);
	}
	
	 /**
     * @Title: query
     * @Description: 数据查询
	 * <p>1.创建数组并赋值
	  * <p>2.调用接口创建map
	  * <p>3.调用查询方法
	  * <p>4.如果获取查询信息不为空
	  * <p>5.返回封装方法
     * 医院标识信息管理界面
	  * @param inInfo
	  * iconNum 标识编码
	  * iconName 标识名称
	  * status 标识状态
	  * deptName 使用科室
	  * installDateS 安装日期起
	  * installDateE 安装日期止
     * @return EiInfo
	  * id 主键
	  * iconNum 标识编码
	  * iconName 标识名称
	  * iconZhContent 标识中文内容
	  * iconEnContent 标识英文内容
	  * classifyName 标识分类名称
	  * iconAmount 数量
	  * status 状态
	  * deptName 使用科室
	  * spotName 安装地点
	  * installDate 安装日期
     */
	public EiInfo query(EiInfo inInfo) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 1.创建数组并赋值
		String[] parameter = {"iconNum","iconName","deptName","status","installDateS","installDateE"};
		// 数组转list
		List<String> plist = Arrays.asList(parameter);
		// 2.调用接口创建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 3.调用查询方法
		List<Map<String, ?>> result = dao.query("HIXX01.queryF", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 获取查询信息总数
		int count = dao.count("HIXX01.queryF", map);
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
     * @Title: enable
     * @Description: 标识启用
	 * <p>1.获取参数(id)</p>
	 * <p>2.调用更新方法</p>
     * 医院标识管理界面
     * 通过主键
     * 将符合条件的信息启用
     * @param inInfo
     * id 标识主键
     * @return: EiInfo
     */
	public EiInfo enable(EiInfo inInfo) {
	    // 1、获取参数(id)
		String id = inInfo.getAttr().get("id").toString();
		if("null".equals(id)){
			inInfo.setMsg("请选择需要启用的记录");
		}else {
			// 实例化map
			HashMap<String, String> map = new HashMap<String, String>();
			// 赋值map
			map.put("id", id);
			// 2.通过id，将符合条件的标识进行启用
			dao.update("HIXX01.enable", map);
		}
		// 3、返回
		return inInfo;

	}



	/**
	 * @Title: deactivate
	 * @Description: 标识停用
	 * <p>1.获取参数(id)</p>
	 * <p>2.调用更新方法</p>
	 * 医院标识管理界面
	 * 通过主键
	 * 将符合条件的信息停用
	 * @param inInfo
	 * id 标识主键
	 * @return: EiInfo
	 */
	public EiInfo  deactivate (EiInfo inInfo) {
		// 1、获取参数(id)
		String id = inInfo.getAttr().get("id").toString();
		if("null".equals(id)){
			inInfo.setMsg("请选择需要停用的记录");
		}else {
			// 实例化map
			HashMap<String, String> map = new HashMap<String, String>();
			// 赋值map
			map.put("id", id);
			// 2、通过id，将符合条件的标识停用
			dao.update("HIXX01.deactivate", map);
		}
		// 3、返回
		return inInfo;

	}

	
	/**
     * @Title: delete
     * @Description: 删除
     * 医院标识信息管理界面
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
        	//00状态为新建状态
        	if(!"00".equals(status)){
        		inInfo.setMsg("新建的工单才可删除");
			}else {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", id);
				//2、删除医院申请基础信息，通过id
				dao.delete("HIXX01.delete", map);
				//3、删除医院申请附件信息，通过id
				dao.delete("HIXX01.deleteFail", map);
			}
		}
		return inInfo;
	}



}
