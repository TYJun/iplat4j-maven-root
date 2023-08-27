package com.baosight.wilp.cm.lx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cm.domain.ContractType;
import com.baosight.xservices.xs.util.UserSession;
/**
 * 该页面为合同类型定义
 * 主要包含对合同类型的新增、删除、编辑、查看功能
 * 合同模块：初始化查询、弹窗页面保存、生成合同编号
 * <p>1.初始化查询 initLoad
 * <p>2.保存弹窗信息 save
 * <p>3.生成合同编号 generateCode
 * @Title: ServiceCMLX0101.java
 * @ClassName: ServiceCMLX0101
 * @Package：com.baosight.wilp.cm.lx.service
 * @author: zhaow
 * @date: 2021年8月30日 下午3:52:35
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMLX0101 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
	 * <p>1.实例化EiBlock
	 * <p>2.如果参数不为空
	 * <p>3.调用查询方法
     * 合同类型定义弹出界面
     * 通过合同操作类型
     * 进行合同操作
     * @param EiInfo
     * id 主键
     * type 操作类型 
     * @return EiInfo
     * payType 收付方向
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 获取参数
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		// 1.实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new ContractType().eiMetadata);
		// 2.如果参数不为空
		if (StringUtils.isNotBlank(id)) {
		    // 实例化map
			Map<String, String> map = new HashMap<>();
			// 设置参数
			map.put("id", id);
			// 3.调用查询方法
			List<ContractType> list = dao.query("CMLX01.queryId", map);
			// 数据返回
			block.addRows(list);
		}
		inInfo.addBlock(block);
		return inInfo;
	}

	/**
	 * @Title: save
	 * @Description: 弹窗页面保存
	 * <p>1.获取表单参数
	 * <p>2.将实体进行赋值
	 * <p>3.如果参数不为空
	 * <p>4.保存项目
	 * 合同类型定义界面
     * 获取填写信息
     * 保存弹窗信息
	 * @param EiInfo
	 * id 主键
	 * recCreator 创建人
	 * recCreateTime 创建时间
	 * recRevsior 修改人
	 * recReviseTime 修改时间
	 * archiveFlag 归档标记
	 * contTypeNum 合同类型编码
	 * contTypeName 合同类型名称
	 * payType 收付方向
	 * remark 备注
	 * @return: EiInfo
	 */
	public EiInfo save(EiInfo inInfo) {
		// 1.获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		// 实例化实体
		ContractType htgl01 = new ContractType();
		// 2.将实体进行赋值
		htgl01.fromMap(param);
		// 3.如果参数不为空
		if (StringUtils.isBlank(htgl01.getId())) {
			htgl01.setId(UUID.randomUUID().toString());
			htgl01.setContTypeNum(generateCode());
			htgl01.setRecCreateTime(DateUtils.curDateTimeStr19());
			htgl01.setRecCreator(UserSession.getUser().getUsername());
			// 4.保存项目
			dao.insert("CMLX01.insert", htgl01);
		}else {
			htgl01.setRecRevisor(UserSession.getUser().getUsername());
			htgl01.setRecReviseTime(DateUtils.curDateTimeStr19());
			System.out.print(htgl01.toString());
			// 保存项目
            dao.insert("CMLX01.update", htgl01);
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
	 * <p>5.获取最大合同号
	 * <p>6.对最大合同号进行拆分
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
			List<String> list = dao.query("CMLX01.queryContTypeNum","CT"+date);
			// 4.如果结果为空
			if(list==null || list.size() == 0 || list.get(0) == null){
			    // 返回合同号
				return "CT"+date+"0001";
			} else {
			    // 5.获取最大合同号
				String maxNo = list.get(0);
				// 6.对最大合同号进行拆分
				maxNo = maxNo.substring(2);
				// 返回合同号
				return "CT"+ (Long.parseLong(maxNo)+1L);
			}
		}
	}
}
