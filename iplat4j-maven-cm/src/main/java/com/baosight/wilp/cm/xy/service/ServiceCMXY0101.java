package com.baosight.wilp.cm.xy.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cm.domain.ContractPayment;
import com.baosight.wilp.cm.domain.ContractPaymentDetails;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 该页面为付款协议定义
 * 主要包含对付款协议的新增、删除、编辑、查看功能
 * 合同模块：初始化查询、保存弹窗信息、保存付款协议明细、查询付款详情、生成合同编号、TabGrid查询方法
 * <p>1.初始化查询 initLoad
 * <p>2.保存弹窗信息 save
 * <p>3.保存付款协议明细 savePay
 * <p>4.查询付款详情 queryPay
 * <p>5.生成合同编号 generateCode
 * <p>6.TabGrid查询方法 queryTabGrid
 *
 * @Title: ServiceCMXY0101.java
 * @ClassName: ServiceCMXY0101
 * @Package：com.baosight.wilp.cm.xy.service
 * @author: zhaow
 * @date: 2021年8月30日 下午4:31:50
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMXY0101 extends ServiceBase {
	/**
	 * @param :EiInfo id 主键
	 *               type 操作类型
	 * @return EiInfo
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * 付款协议定义弹出界面
	 * 通过付款协议操作类型
	 * 进行合同操作
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		// 获取参数
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		// 实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new ContractPayment().eiMetadata);
		// 如果参数不为空
		if (StringUtils.isNotBlank(id)) {
			// 实例化map
			Map<String, String> map = new HashMap<>();
			// 设置map
			map.put("id", id);
			// 调用查询方法
			List<ContractPayment> list = dao.query("CMXY01.queryId", map);
			// 数据返回
			block.addRows(list);
		}
		inInfo.addBlock(block);
		return inInfo;
	}

	/**
	 * @param: EiInfo id 主键
	 *               recCreator 创建人
	 *               recCreateTime 创建时间
	 *               recRevsior 修改人
	 *               recReviseTime 修改时间
	 *               archiveFlag 归档标记
	 *               payTypeNum 合同协议编码
	 *               payTypeName 合同协议名称
	 *               remark 备注
	 * @Title: save
	 * @Description: 弹窗页面保存
	 * 付款协议定义界面
	 * 获取填写信息
	 * 保存弹窗信息
	 * @return: EiInfo
	 */
	public EiInfo save(EiInfo inInfo) {
		// 获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		Object payObj = inInfo.get("pay");
		// 实例化实体
		ContractPayment htgl04 = new ContractPayment();
		// 实体赋值
		htgl04.fromMap(param);
		// 如果参数为空
		if (StringUtils.isBlank(htgl04.getId())) {
			htgl04.setId(UUID.randomUUID().toString());
			htgl04.setPayTypeNum(generateCode());
			htgl04.setRecCreateTime(DateUtils.curDateTimeStr19());
			htgl04.setRecCreator(UserSession.getUser().getUsername());
			// 保存项目
			dao.insert("CMXY01.insert", htgl04);
		} else {
			htgl04.setRecRevsior(UserSession.getUser().getUsername());
			htgl04.setRecReviseTime(DateUtils.curDateTimeStr19());
			// 更新项目
			dao.insert("CMXY01.update", htgl04);
		}
		// 调用保存方法
		savePay(payObj, htgl04.getId(),htgl04.getPayTypeNum());
		return inInfo;

	}

	/**
	 * @param payObj id 主键
	 *               payTypeNum 付款类型编码
	 *               lastTime 距离上次付款时间
	 *               payRate 付款比例
	 * @Title: savePay
	 * @Description: 保存付款协议明细
	 * 付款协议界面弹框
	 * 获取填写信息及付款协议详情
	 * 保存弹窗信息
	 * @return: void
	 */
	private void savePay(Object payObj, String id,String payTypeNum) {
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果参数不为空
		if (payObj != null) {
			// 参数类型转化
			list = (List<Map<String, Object>>) payObj;
			// 调用删除方法
			dao.delete("CMXY0101.delete", payTypeNum);
		}
		// 增强循环
		int i = 1;
		for (Map<String, Object> map : list) {
			// 实例化实体
			ContractPaymentDetails att = new ContractPaymentDetails();
			// 实体赋值
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setNo(i);
			att.setPayTypeNum(payTypeNum);
			att.setRecCreator(UserSession.getUser().getUsername());
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 调用查询方法
			dao.insert("CMXY0101.insert", att);
			i++;
		}
	}

	/**
	 * @param: EiInfo payTypeNum 付款类型编码
	 * @return EiInfo
	 * id 主键
	 * recCreator 创建人
	 * recCreateTime 创建时间
	 * recRevsior 修改人
	 * recReviseTime 修改时间
	 * no 序号
	 * lastTime 距离上次付款时间
	 * payRate 付款比例
	 * remark 备注
	 * @Title: queryPay
	 * @Description: 查询付款详情
	 * 付款协议定义界面
	 * 通过付款类型编码查询信息
	 * 回显合同付款协议
	 */
	public EiInfo queryPay(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "CMXY0101.query", "CMXY0101.count", "resultA", new ContractPaymentDetails().eiMetadata);
	}

	/**
	 * @param
	 * @Title: generateCode
	 * @Description: 生成合同编号
	 * 通过获取当前时间
	 * 判断今天是否存在合同编号，不存在就返回新的合同号
	 * 存在合同编号，对合同编号进行累加
	 * @return: String
	 * String 合同编号
	 */
	private String generateCode() {
		// 加锁
		synchronized (dao) {
			// 调用时间接口
			String date = DateUtils.curDateStr8();
			// 调用查询方法
			List<String> list = dao.query("CMXY01.queryContTypeNum", "CP" + date);
			// 如果结果为空
			if (list == null || list.size() == 0 || list.get(0) == null) {
				// 返回合同号
				return "CP" + date + "0001";
			} else {
				// 获取最大合同号
				String maxNo = list.get(0);
				// 对最大合同号进行拆分
				maxNo = maxNo.substring(2);
				// 返回合同号
				return "CP" + (Long.parseLong(maxNo) + 1L);
			}
		}
	}

	/**
	 * TabGrid查询方法
	 *
	 * @param: EiInfo querySql 查询sql
	 *               countSql 统计sql
	 *               resultBlock blockId
	 *               blockMeta EiBlockMeta
	 * @Title: queryTabGrid
	 * @return: EiInfo
	 */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql, String countSql, String resultBlock, EiBlockMeta blockMeta) {
		// 调用封装方法构造map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		//查询数据
		List<?> list = dao.query(querySql, map, (Integer) map.get("offset"), (Integer) map.get("limit"));
		//获取总数
		int count = dao.count(countSql, map);
		//数据返回
		return CommonUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}
}
