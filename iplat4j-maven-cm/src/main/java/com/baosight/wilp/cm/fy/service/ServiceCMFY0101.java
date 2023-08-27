package com.baosight.wilp.cm.fy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cm.domain.ContractCost;
import com.baosight.xservices.xs.util.UserSession;
/**
 * 该页面为合同费用定义
 * 主要包含对合同费用的新增、删除、编辑、查看功能
 * 合同模块：初始化查询、保存弹窗信息、生成合同编号
 * <p>1.初始化查询 initLoad
 * <p>2.保存弹窗信息 save
 * <p>3.生成合同编号 generateCode
 * @Title: ServiceCMFY0101.java
 * @ClassName: ServiceCMFY0101
 * @Package：com.baosight.wilp.cm.fy.service
 * @author: zhaow
 * @date: 2021年8月30日 下午2:53:09
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMFY0101 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
	 * <p>1.如果参数不为空
	 * <p>2.调用查询方法
     * 合同费用定义弹出界面
     * 通过合同费用操作类型
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
		block.addBlockMeta(new ContractCost().eiMetadata);
		// 1.如果参数不为空
		if (StringUtils.isNotBlank(id)) {
		    // 实例化map
			Map<String, String> map = new HashMap<>();
			// 设置参数
			map.put("id", id);
			// 2.调用查询方法
			List<ContractCost> list = dao.query("CMFY01.queryId", map);
			block.addRows(list);// 数据返回
		}
		inInfo.addBlock(block);
		return inInfo;
	}

	/**
     * @Title: save
     * @Description: 弹窗页面保存
	 * <p>1.获取表单参数
	 * <p>2.如果存在信息
	 * <p>3.保存项目
	 * <p>4.更新项目
     * 合同费用定义界面
     * 获取填写信息
     * 保存弹窗信息
     * @param EiInfo
     * id 主键
     * recCreator 创建人
     * recCreateTime 创建时间
     * recRevsior 修改人
     * recReviseTime 修改时间
     * archiveFlag 归档标记
     * contCostNum 合同费用编码
     * contCostName 合同费用名称
     * remark 备注
     * @return: EiInfo
     */
	public EiInfo save(EiInfo inInfo) {
		// 1.获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		// 实例化实体
		ContractCost htgl03 = new ContractCost();
		// 设置实体参数
		htgl03.fromMap(param);
		// 2.如果存在信息
		if (StringUtils.isBlank(htgl03.getId())) {
			htgl03.setId(UUID.randomUUID().toString());
			htgl03.setContCostNum(generateCode());
			htgl03.setRecCreateTime(DateUtils.curDateTimeStr19());
			htgl03.setRecCreator(UserSession.getUser().getUsername());
			// 3.保存项目
			dao.insert("CMFY01.insert", htgl03);
		}else {
			htgl03.setRecRevisor(UserSession.getUser().getUsername());
		    htgl03.setRecReviseTime(DateUtils.curDateTimeStr19());
		    // 4.更新项目
            dao.insert("CMFY01.update", htgl03);
		}
		return inInfo;
	}
	
	/**
     * @Title: generateCode
     * @Description: 生成合同编号
	 * <p>1.加锁
	 * <p>2.调用时间接口
	 * <p>3.调用查询方法
	 * <p>4.如果结果为空
	 * <p>5.返回合同号
	 * <p>6.获取最大合同号
	 * <p>7.对最大合同号进行拆分
     * 通过获取当前时间
     * 判断今天是否存在合同编号，不存在就返回新的合同号
     * 存在合同编号，对合同编号进行累加
     * @param
     * @return: String
     * String 合同编号
     */
	private String generateCode() {
	    // 1.加锁
		synchronized (dao){
		    // 2.调用时间接口
			String date = DateUtils.curDateStr8();
			// 3.调用查询方法
			List<String> list = dao.query("CMFY01.queryContTypeNum","CE"+date);
			// 4.如果结果为空
			if(list==null || list.size() == 0 || list.get(0) == null){
			    // 5.返回合同号
				return "CE"+date+"0001";
			} else {
			    // 6.获取最大合同号
				String maxNo = list.get(0);
				// 7.对最大合同号进行拆分
				maxNo = maxNo.substring(2);
				// 返回合同号
				return "CE"+ (Long.parseLong(maxNo)+1L);
			}
		}
	}	

}
