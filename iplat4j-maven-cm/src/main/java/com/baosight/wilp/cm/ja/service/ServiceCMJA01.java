package com.baosight.wilp.cm.ja.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.cm.domain.ContractBill;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.Map;

/**
 * 该页面为合同结案管理
 * 主要包含对合同结案管理的结案、查看功能
 * 合同模块：初始化查询、查询、结案
 * <p>1.初始化查询 initLoad
 * <p>2.查询 query
 * <p>3.结案 end
 * @Title: ServiceCMJA01.java
 * @ClassName: ServiceCMJA01
 * @Package：com.baosight.wilp.cm.ja.service
 * @author: zhaow
 * @date: 2021年8月30日 下午3:11:28
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMJA01 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同结案管理界面
     * 通过合同号、合同名称、合同类型、签订日期起、签订日期止条件查询
     * 回显合同费用编码 、合同费用名称、备注
     * @param EiInfo
     * contNo 合同号
     * contName 合同名称
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
     * checkMaker 审批人
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return query(inInfo);
	}

	/**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同结案管理界面
     * 通过合同号、合同名称、合同类型、签订日期起、签订日期止条件查询
     * 回显合同费用编码 、合同费用名称、备注
     * @param EiInfo
     * contNo 合同号
     * contName 合同名称
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
     * checkMaker 审批人
     */
	public EiInfo query(EiInfo inInfo) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		Map<String, Object> map = new HashMap<>();
//		map.put("contDeptNum",staffByUserId.get("deptNum"));
		EiBlock block = new EiBlock("inqu_status");
		block.addRow(map);
		inInfo.addBlock(block);
		// 调用查询方法
		EiInfo eiInfo = query(inInfo, "CMJA01.query", new ContractBill());
		return eiInfo;
	}
	
	/**
     * @Title: end
     * @Description: 结案
     * 合同结案管理界面
     * 通过合同主键
     * 将符合条件的信息结案
     * @param EiInfo
     * id 合同主键
     * @return: EiInfo
     */
	public EiInfo end(EiInfo inInfo) {
	    // 获取参数
		String id = inInfo.getAttr().get("id").toString();
		// 实例化map
		HashMap<String,String> map = new HashMap<String,String>();
		// 设置参数
		map.put("id",id);
		// 调用更新方法
		dao.update("CMJA01.end",map);
		return inInfo;
	}
}
