package com.baosight.wilp.cm.tk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cm.domain.ContractTerms;
import com.baosight.wilp.cm.domain.ContractTermsDetails;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;
/**
 * 该页面为合同条款定义
 * 主要包含对合同条款的新增、删除、编辑、查看功能
 * 合同模块：初始化查询、保存弹窗信息、查询供应商、查询合同类型、查询付款协议、TabGrid查询方法
 * <p>1.初始化查询 initLoad
 * <p>2.弹窗页面保存 saveResult
 * <p>3.保存条款详细内容 saveContent
 * <p>4.查询条款合同详情 queryDefine
 * <p>5.生成合同编号 generateCode
 * <p>6.TabGrid查询方法 queryTabGrid
 * @Title: ServiceCMTK0101.java
 * @ClassName: ServiceCMTK0101
 * @Package：com.baosight.wilp.cm.tk.service
 * @author: zhaow
 * @date: 2021年8月30日 下午4:13:27
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMTK0101 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同条款定义弹出界面
     * 通过合同操作类型
     * 进行合同操作
     * @param EiInfo
     * id 主键
     * type 操作类型 
     * @return EiInfo
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 获取参数
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		// 实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new ContractTerms().eiMetadata);
		// 如果参数不为空
		if (StringUtils.isNotBlank(id)) {
		    // 实例化map
			Map<String, String> map = new HashMap<>();
			// 设置参数
			map.put("id", id);
			// 调用查询方法
			List<ContractTerms> list = dao.query("CMTK01.queryId", map);
			// 数据返回
			block.addRows(list);
		}
		inInfo.addBlock(block);
		return inInfo;
	}

	/**
     * @Title: saveResult
     * @Description: 弹窗页面保存
     * 合同条款定义界面
     * 获取填写信息
     * 保存弹窗信息
     * @param EiInfo
     * id 主键
     * recCreator 创建人
     * recCreateTime 创建时间
     * recRevsior 修改人
     * recReviseTime 修改时间
     * archiveFlag 归档标记
     * contTermNum 合同条款编码
     * contTermName 合同条款名称
     * contTermContent 合同条款内容
     * remark 备注
     * @return EiInfo
     */
	public EiInfo saveResult(EiInfo inInfo) {
		// 获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		// 实例化map
		Map<String, Object> param = block.getRow(0);
		// 设置参数
		String id = param.get("id").toString();
		String contTermName = param.get("contTermName").toString();
		String remark = param.get("remark").toString();
		Object contentObj = inInfo.get("content");
		// 实例化map
		Map<String, String> map = new HashMap<>();
		String contTermNum = StringUtils.isBlank(param.get("contTermNum").toString())? generateCode():param.get("contTermNum").toString();
		// 如果参数为空
		if (StringUtils.isBlank(id)) {
			map.put("id", UUID.randomUUID().toString());
			map.put("contTermNum", contTermNum);
			map.put("recCreateTime", DateUtils.curDateTimeStr19());
			map.put("recCreator",UserSession.getUser().getUsername());
			map.put("contTermName", contTermName);
			map.put("remark", remark);
			// 保存项目
			dao.insert("CMTK01.insert", map);
		} else {
			map.put("id", id);
			map.put("contTermName", contTermName);
			map.put("remark", remark);
			map.put("recRevsior",UserSession.getUser().getUsername());
			map.put("recReviseTime",UserSession.getUser().getUsername());
			// 更新项目
			dao.insert("CMTK01.update", map);
		}
		saveContent(contentObj,contTermNum);
		return inInfo;
	}
	
	/**
     * @Title: saveContent
     * @Description: 保存条款详细内容
     * 合同条款定义界面
     * 获取填写信息
     * 保存弹窗信息
     * @param EiInfo
     * contentObj 合同条款内容主体
     * contTermNum 合同条款编码
     * @return
     */
	private void saveContent(Object contentObj, String contTermNum) {
	    // 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果参数不为空
		if (contentObj != null) {
		    // 参数类型转化
			list = (List<Map<String, Object>>) contentObj;
			// 调用删除方法
			dao.delete("CMTK0101.delete", contTermNum);
		}
		// 增强循环
		for (Map<String, Object> map : list) {
			map.put("id", UUID.randomUUID().toString());
			map.put("contTermNum", contTermNum);
			map.put("recCreator", UserSession.getUser().getUsername());
			map.put("recCreateTime", DateUtils.curDateTimeStr19());
			// 调用插入方法
			dao.insert("CMTK0101.insert", map);
		}
	}
	
	/**
     * @Title: queryDefine
     * @Description: 查询条款合同详情
     * 合同条款定义界面
     * 通过合同条款编码查询信息
     * 回显合同条款信息
     * @param EiInfo
     * contTermNum 合同条款编码
     * @return EiInfo
     * id 主键
     * recCreator 创建人
     * recCreateTime 创建时间
     * recRevsior 修改人
     * recReviseTime 修改时间
     * archiveFlag 归档标记
     * contTermNum 合同条款编码
     * content 合同内容
     */
	public EiInfo queryDefine(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"CMTK0101.query","CMTK0101.count","resultA",new ContractTermsDetails().eiMetadata);
	}
	
	/**
     * @Title: generateCode
     * @Description: 生成合同编号
     * 通过获取当前时间
     * 判断今天是否存在合同编号，不存在就返回新的合同号
     * 存在合同编号，对合同编号进行累加
     * @param
     * @return: String
     * String 合同编号
     */
	private String generateCode() {
	    // 加锁
		synchronized (dao) {
		    // 调用时间接口
			String date = DateUtils.curDateStr8();
			// 调用查询方法
			List<String> list = dao.query("CMTK01.queryContTypeNum", "CC" + date);
			// 如果结果为空
			if (list == null || list.size() == 0 || list.get(0) == null) {
			    // 返回合同号
				return "CC" + date + "0001";
			} else {
			    // 获取最大合同号
				String maxNo = list.get(0);
				// 对最大合同号进行拆分
				maxNo = maxNo.substring(2);
				// 返回合同号
				return "CC" + (Long.parseLong(maxNo) + 1L);
			}
		}
	}
	
	/**
	 * TabGrid查询方法
	 * @Title: queryTabGrid 
	 * @param EiInfo
	 * querySql 查询sql
	 * countSql 统计sql
	 * resultBlock blockId 
	 * blockMeta EiBlockMeta
	 * @return: EiInfo
	 */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql,String countSql, String resultBlock, EiBlockMeta blockMeta){
	    // 调用封装方法构造map
	    Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		//查询数据
		List<?> list = dao.query(querySql, map, Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		//获取总数
		int count = dao.count(countSql, map);
		//数据返回
		return CommonUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}

}
