package com.baosight.wilp.cm.zh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cm.domain.*;
import com.baosight.wilp.cm.util.PMUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该页面为合同变更管理
 * 主要包含对合同变更的查询、变更、冻结、恢复、终止、查看功能
 * 合同模块：初始化查询、保存弹窗信息、查询供应商、查询合同类型、查询付款协议、接口改造所属科室、接口改造合同管理员、查询合同条款内容、查询付款内容、查询合同费用内容、查询项目附件信息、查询合同短信人、获取合同号、保存合同条款内容、保存合同付款内容、保存合同费用内容 、保存附件、保存合同联系人、TabGrid查询方法 queryTabGrid
 * <p>1.初始化查询 initLoad
 * <p>2.保存弹窗信息 saveContent
 * <p>3.查询供应商 querySupplier
 * <p>4.查询合同类型 getContTypeNum
 * <p>5.查询付款协议 payAgreNum
 * <p>6.接口改造所属科室 queryContCostNum
 * <p>7.接口改造合同管理员 queryAdmin
 * <p>8.查询合同条款内容 queryCont
 * <p>9.查询付款内容 queryPay
 * <p>10.查询合同费用内容 queryCost
 * <p>11.查询项目附件信息 queryFile
 * <p>12.查询合同短信人 queryPerson
 * <p>13.获取合同号 createContNo
 * <p>14.保存合同条款内容 saveHta
 * <p>15.保存合同付款内容 saveHtb
 * <p>16.保存合同费用内容 saveHtc
 * <p>17.保存附件 saveFile
 * <p>18.保存合同联系人 saveHte
 * <p>19.TabGrid查询方法 queryTabGrid
 * @Title: ServiceCMZH0101.java
 * @ClassName: ServiceCMZH0101
 * @Package：com.baosight.wilp.cm.dj.service
 * @author: zhaow
 * @date: 2021年8月30日 下午1:23:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMZH0101 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同登记管理弹出界面
     * 通过合同操作类型
     * 进行合同操作
     * @param inInfo
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
		block.addBlockMeta(new ContractBill().eiMetadata);
		// 如果参数存在
		if (StringUtils.isNotBlank(id)) {
		    // 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			// 调用查询方法
			List<HashMap<String, String>> list = dao.query("CMDJ01.queryId", map);
			// 数据返回
			block.addRows(list);
			// 特殊数据回显
			EiColumn column1 = new EiColumn("surpNum_textField");
			EiColumn column2 = new EiColumn("contDeptNum_textField");
			EiColumn column3 = new EiColumn("contAdmin_textField");
			EiColumn column4 = new EiColumn("schedule_textField");
//			String surpNum=list.get(0).getSurpNum();
			block.addMeta(column1);
			block.addMeta(column2);
			block.addMeta(column3);
			block.addMeta(column4);
			String contdeptNUm = list.get(0).get("contdeptNUm");
			block.setCell(0, "surpNum_textField", list.get(0).get("surpName"));
			block.setCell(0, "contDeptNum_textField", list.get(0).get("contDeptName"));
			block.setCell(0, "contAdmin_textField", list.get(0).get("contAdminName"));
			block.setCell(0, "schedule_textField", list.get(0).get("scheduleName"));
			block.setCell(0, "type", type);
			block.setCell(0, "Pm_textField", list.get(0).get("projectName"));
		}
		block.setCell(0, "type", type);
		inInfo.addBlock(block);
		// 返回参数
		return inInfo;

	}

	/**
     * @Title: querySupplier
     * @Description: 查询供应商
     * 调用档案中心
     * 查询供应商
     * @param info
     * supplierName 供应商
     * @return EiInfo
     * supplier 供应商编码
     * supplierName 供应商
     */
	public EiInfo querySupplier(EiInfo info) {
        // 调用分页接口构建map
        Map<String, Object> map = PMUtils.buildParamter(info, "inqu_status", "supplier");
        // 获取blockId
        String blockId = info.getBlockIds();
        // 如果blockId相等
        if(blockId.equals("inqu_status,supplier")) {
            // 实例化info
            EiInfo outinfo = new EiInfo();
            // 实例化block
            EiBlock block = new EiBlock("supplier");
            // 获取block中的数据的集合
            List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
            // 获取集合中的数据
            String name = (String) listMap.get(0).get("supplierName");
            // 设置supplierName
            map.put("supplierName", name);
            // 实例化list
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            // 初始化查询总数为0
            int count = 0;
            // 调用改造供应商接口并返回
            EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
            // 如果供应商信息
            if(outInfo.getBlock("supplier") != null) {
                // 获取供应商信息
                list = outInfo.getBlock("supplier").getRows();
                // 如果list为空
                if(list.isEmpty()) {
                    // 返回封装的方法：构建返回结果EiInfo
                    return info;
                }
                // 遍历供应商信息
                for (Map<String, Object> map2 : list) {
                    // 将key：supplierCode改为key：supplierNum
                    map2.put("surpNum", map2.get("supplierCode"));
                    map2.put("surpName", map2.get("supplierName"));
                }
                // 获取供应商信息总数
                count = (int)outInfo.getBlock("supplier").getAttr().get("count");
                // 返回封装的方法：构建返回结果EiInfo
                return PMUtils.BuildOutEiInfo(info, "supplier", PMUtils.createBlockMeta(list.get(0)), list, count);
            }else {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
        }
        // 实例化list
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        // 初始化查询总数为0
        int count = 0;
        // 调用改造供应商接口并返回
        EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
        // 如果供应商信息
        if(outInfo.getBlock("supplier") != null) {
            // 获取供应商信息
            list = outInfo.getBlock("supplier").getRows();
            // 如果list为空
            if(list.isEmpty()) {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
            // 遍历供应商信息
            for (Map<String, Object> map2 : list) {
                // 将key：supplierCode改为key：supplierNum
                map2.put("surpNum", map2.get("supplierCode"));
                map2.put("surpName", map2.get("supplierName"));
            }
            // 获取供应商信息总数
            count = (int)outInfo.getBlock("supplier").getAttr().get("count");
            // 返回封装的方法：构建返回结果EiInfo
            return PMUtils.BuildOutEiInfo(info, "supplier", PMUtils.createBlockMeta(list.get(0)), list, count);
         }else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
         }
    }

	/**
     * @Title: getContTypeNum
     * @Description: 查询合同类型
     * 合同类型定义界面
     * 通过合同类型名称条件查询
     * 回显合同类型编码 、合同类型名称、收付方向、备注
     * @param inInfo
     * contTypeName 合同类型名称
     * @return EiInfo
     * contTypeNum 合同类型编码 
     * contTypeName 合同类型名称
     * payType 收付方向
     * remark 备注
     */
	public EiInfo getContTypeNum(EiInfo inInfo) {
	    // 调用查询方法
		List<ContractType> query = dao.query("CMLX01.query", new HashMap<>());
		// 设置参数
		inInfo.addBlock("contTypeName").addRows(query);
		inInfo.getBlock("contTypeName").setBlockMeta(new ContractType().eiMetadata);
		return inInfo;

	}

	/**
     * @Title: payAgreNum
     * @Description: 查询付款协议
     * 付款协议定义界面
     * 通过付款协议条件查询
     * 回显付款协议编码 、付款协议名称、备注
     * @param inInfo
     * payTypeName 合同协议名称
     * @return EiInfo
     * id 主键
     * payTypeNum 合同协议编码
     * payTypeName 合同协议名称
     * remark 备注
     */
	public EiInfo payAgreNum(EiInfo inInfo) {
	    // 调用查询方法
		List<ContractType> query = dao.query("CMXY01.query", new HashMap<>());
		// 设置参数
		inInfo.addBlock("payTypeName").addRows(query);
		inInfo.getBlock("payTypeName").setBlockMeta(new ContractPayment().eiMetadata);
		return inInfo;

	}

	/**
     * @Title: queryContCostNum 
     * @Description: 接口改造所属科室
     * 调用档案中心
     * 查询科室
     * @param info
     * deptNum 科室编码
     * deptName 科室名称
     * @return EiInfo
     * contDeptNum 科室编码
     * contDeptName 科室名称
     */
	public EiInfo queryContCostNum(EiInfo info) {
        // 调用分页接口构建map
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "contDept");
        map.put("deptNum", map.get("contDeptNum"));
        map.put("deptName", map.get("contDeptName"));
        // 实例化List
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        // 初始化总数为0
        int count = 0;
        // 调用远程服务获取改造科室接口
        EiInfo outInfo = BaseDockingUtils.getDeptAllPage(map, "contDept");
        // 如果存在科室信息
        if(outInfo.getBlock("contDept") != null) {
            // 科室信息存储在list
            list = outInfo.getBlock("contDept").getRows();
            // 循环
            list.forEach(map2 -> {
                map2.put("contDeptNum", map2.get("deptNum"));
                map2.put("contDeptName", map2.get("deptName"));
            });
            // 如果list为空
            if(list.isEmpty()) {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
            // 科室信息总数存在count
            count = (int)outInfo.getBlock("contDept").getAttr().get("count");
            // 返回封装的方法：构建返回结果EiInfo
            return CommonUtils.BuildOutEiInfo(info, "contDept", PMUtils.createBlockMeta(list.get(0)), list, count);
        }else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
        }
    }

	/**
     * @Title: queryAdmin
     * @Description: 接口改造合同管理员
     * 调用档案中心
     * 查询人员
     * @param info
     * userName 用户名
     * @return EiInfo
     * work 工号
     * name 用户名
     */
	public EiInfo queryAdmin(EiInfo info) {
        // 调用分页接口构建map
        Map<String, Object> map = PMUtils.buildParamter(info, "inqu_status", "contAdmin");
        map.put("userName", map.get("name"));
        // 获取blockId
        String blockId = info.getBlockIds();
        // 如果blockId相等
        if(blockId.equals("inqu_status,person")) {
            // 实例化info
            EiInfo outinfo = new EiInfo();
            // 实例化block
            EiBlock block = new EiBlock("person");
            // 获取block中的数据的集合
            List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
            // 获取集合中的数据
            String name = (String) listMap.get(0).get("name");
            // 设置userName
            map.put("userName", name);
            // 实例化List
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            // 初始化查询总数为0
            int count = 0;
            // 调用改造人员接口并返回
            EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "person");
            // 如果存在人员信息
            if(outInfo.getBlock("person") != null) {
                // 获取人员信息
                list = outInfo.getBlock("person").getRows();
                // 如果list为空
                if(list.isEmpty()) {
                    // 返回封装的方法：构建返回结果EiInfo
                    return info;
                }
                // 获取人员信息总数
                count = (int)outInfo.getBlock("person").getAttr().get("count");
                // 返回封装的方法：构建返回结果EiInfo
                return CommonUtils.BuildOutEiInfo(info, "person", PMUtils.createBlockMeta(list.get(0)), list, count);
            }else {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
        }
        // 调用改造人员接口并返回
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        // 初始化查询总数为0
        int count = 0;
        // 调用改造人员接口并返回
        EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "contAdmin");
        // 如果存在人员信息
        if(outInfo.getBlock("contAdmin") != null) {
            // 获取人员信息
            list = outInfo.getBlock("contAdmin").getRows();
            // 如果list为空
            if(list.isEmpty()) {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
            // 获取人员信息总数
            count = (int)outInfo.getBlock("contAdmin").getAttr().get("count");
            // 返回封装的方法：构建返回结果EiInfo
            return CommonUtils.BuildOutEiInfo(info, "contAdmin", PMUtils.createBlockMeta(list.get(0)), list, count);
         }else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
         }
    }
	
	
	
	/**
     * @Title: queryCont
     * @Description: 查询条款合同内容
     * 合同条款定义界面
     * 通过合同条款编码查询信息
     * 回显合同条款信息
     * @param inInfo
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
	public EiInfo queryCont(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo, "CMDJ0101.queryContList", "CMDJ0101.queryContCount", "resultA", new ContractBillTerms().eiMetadata);
	}

	/**
     * @Title: queryPay
     * @Description: 查询条款合同内容
     * 合同条款定义界面
     * 通过合同条款编码查询信息
     * 回显合同条款信息
     * @param inInfo
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
	public EiInfo queryPay(EiInfo inInfo) {
		EiBlock eiBlock = (EiBlock) inInfo.getBlocks().get("inqu_status");
		Map map = (Map) eiBlock.getRows().get(0);
		int count = dao.count("CMDJ01b.countPay", map);
		if (count == 0){
			return queryTabGrid(inInfo, "CMDJ01b.query", "CMDJ01b.count", "resultB", new ContractBillPayment().eiMetadata);
		}
	    // 调用封装方法
		return queryTabGrid(inInfo, "CMDJ01b.queryPay", "CMDJ01b.countPay", "resultB", new ContractPaymentDetails().eiMetadata);
	}

	/**
     * @Title: queryCost
     * @Description: 查询合同费用内容
     * 合同费用定义界面
     * 通过合同费用名称条件查询
     * 回显合同费用编码 、合同费用名称、备注
     * @param inInfo
     * contCostName 合同费用名称
     * @return EiInfo
     * id 主键
     * contCostNum 合同费用编码
     * contCostName 合同费用名称
     * remark 备注
     */
	public EiInfo queryCost(EiInfo inInfo) {
		EiBlock eiBlock = (EiBlock) inInfo.getBlocks().get("inqu_status");
		Map map = (Map) eiBlock.getRows().get(0);
		if (StringUtils.isEmpty((String) map.get("id"))){
			return inInfo;
		}
	    // 调用封装方法
		return queryTabGrid(inInfo, "CMDJ01c.queryCost", "CMDJ01c.countCost", "resultC", new CmPayment().eiMetadata);
	}

	/**
     * @Title: queryFile 
     * @Description:  查询项目附件信息
     * @param inInfo
     * attachId 附件ID
     * datagroupCode 账套
     * @return: EiInfo
     * id 主键
     * contPk 项目主键
     * attachId 附件ID
     * attachName 附件名称
     * attachPath 附件路径
     * attachSize 附件大小
     * attachDesc 附件说明
     * archiveFlag 归档标记
     * datagroupCode 账套
     * recCreator 记录创建责任者
     * recCreateTime 记录创建时间
     * recRevisor 记录修改责任者
     * recReviseTime 记录修改时间
     */
	public EiInfo queryFile(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo, "CMDJ0101.queryFileList", "CMDJ0101.queryFileCount", "resultD", new ContractBillFile().eiMetadata);
	}

	/**
	 * @Title: queryDeleteFile
	 * @Description:  查询删除的项目附件信息
	 * @param inInfo
	 * attachId 附件ID
	 * datagroupCode 账套
	 * @return: EiInfo
	 * id 主键
	 * contPk 项目主键
	 * attachId 附件ID
	 * attachName 附件名称
	 * attachPath 附件路径
	 * attachSize 附件大小
	 * attachDesc 附件说明
	 * archiveFlag 归档标记
	 * datagroupCode 账套
	 * recCreator 记录创建责任者
	 * recCreateTime 记录创建时间
	 * recRevisor 记录修改责任者
	 * recReviseTime 记录修改时间
	 */
	public EiInfo queryDeleteFile(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "CMDJ0101.queryDeleteFile", "CMDJ0101.queryDeleteFileCount", "resultF", new ContractBillFile().eiMetadata);
	}

	/**
     * @Title: queryPerson
     * @Description: 查询合同短信人
     * @param inInfo
     * name 联系人
     * @return: EiInfo
     * id 主键
     * name 联系人
     * number 联系电话
     * deptNum 科室编码
     */
	public EiInfo queryPerson(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo, "CMDJ0101.queryPersonList", "CMDJ0101.queryPersonCount", "resultE", new ContractBillLinkman().eiMetadata);
	}

	
	/**
     * @Title: createContNo
     * @Description: 生成合同编号
     * 通过获取当前时间
     * 判断今天是否存在合同编号，不存在就返回新的合同号
     * 存在合同编号，对合同编号进行累加
     * @param
     * @return: String
     * String 合同编号
     */
	private String createContNo() {
	    // 加锁
		synchronized (dao) {
		    // 调用时间接口
			String date = DateUtils.curDateStr8();
			// 调用查询方法
			List<String> list = dao.query("CMDJ0101.createContNo", "PO" + date);
			// 如果结果为空
			if (list == null || list.size() == 0 || list.get(0) == null) {
			    // 返回合同号
				return "PO" + date + "0001";
			} else {
			    // 获取最大合同号
				String maxNo = list.get(0);
				// 对最大合同号进行拆分
				maxNo = maxNo.substring(2);
				// 返回合同号
				return "PO" + (Long.parseLong(maxNo) + 1L) + "";
			}
		}

	}

	/**
     * @Title: queryTabGrid 
     * @Description: TabGrid查询方法
     * @param inInfo
     * querySql 查询sql
     * countSql 统计sql
     * resultBlock blockId 
     * blockMeta EiBlockMeta
     * @return: EiInfo
     */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql, String countSql, String resultBlock,
			EiBlockMeta blockMeta) {
	    // 调用封装方法构造map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		// 查询数据
		List<?> list = dao.query(querySql, map, (Integer) map.get("offset"), (Integer) map.get("limit"));
		// 获取总数
		int count = dao.count(countSql, map);
		// 数据返回
		return CommonUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}

	/**
	 * @Title: queryProject
	 * @Description: TabGrid查询方法
	 * @param inInfo
	 * @return: EiInfo
	 */
	public EiInfo queryProject(EiInfo inInfo) {
		// 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "Pm");
		List<Map<String,Object>> list=new ArrayList<>();
		int count =0;
		//获取所有工程项目
		try {
			list = dao.query("CMDJ0101.queryProjectList", map);
			count = dao.count("CMDJ0101.queryProjectListCount", map);
			if(list.isEmpty()){
				return inInfo;
			}
		}catch (Exception e){
			return inInfo;
		}
		return CommonUtils.BuildOutEiInfo(inInfo, "Pm", PMUtils.createBlockMeta(list.get(0)), list, count);
	}

	/**
	 * @Title: queryFileByProjectId
	 * @Description: TabGrid查询方法
	 * @param inInfo
	 * @return: EiInfo
	 */
	public EiInfo queryFileByProjectId(EiInfo inInfo) {
		// 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "resultG");
		List<Map<String,Object>> list=new ArrayList<>();
		int count =0;
		//获取所有工程项目
		try {
			list = dao.query("CMDJ0101.queryFileByProjectId", map);
			if(!list.isEmpty()){
				count=list.size();
			}else{
				return inInfo;
			}
		}catch (Exception e){
			return inInfo;
		}
		return CommonUtils.BuildOutEiInfo(inInfo, "resultG", PMUtils.createBlockMeta(list.get(0)), list, count);
	}

	/**
	 * 点击工程项目获取附件
	 *
	 * @Title: getProjectFile
	 * @param eiInfo
	 * id   项目id
	 * @return
	 * @return: EiInfo
	 */
	public EiInfo getProjectFile(EiInfo eiInfo) {
		String id = (String)eiInfo.get("id");
		Map map=new HashMap();
		map.put("projectId",id);
		List<Map<String, String>> query  = dao.query("CMDJ0101.queryFileByProjectId", map);
		eiInfo.set("param", query);
		return eiInfo;
	}
}
