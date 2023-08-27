package com.baosight.wilp.mp.cg.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.mp.lj.domain.MpContract;
import com.baosight.wilp.mp.lj.domain.MpContractFile;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;
import  com.baosight.wilp.mp.common.MpUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * 采购计划生成合同页面
 * <p>1.初始化查询 initLoad
 * <p>2.保存合同信息</>
 *
 * @Title: ServiceMPCG0104.java
 * @ClassName: ServiceMPCG0104
 * @Package：com.baosight.wilp.mp.cg.service
 * @author: lyf
 * @date: 2022年10月19日 下午1:23:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceMPCG0104 extends ServiceBase {
	/**
	 * @param inInfo id 主键
	 *               type 操作类型
	 * @return EiInfo
	 * @Title: initLoad
	 * @Description: 初始化查询
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 1.获取参数
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		// 实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new MpContract().eiMetadata);
		// 2.如果参数存在
		if (StringUtils.isNotBlank(id)) {
			// 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			// 3.调用查询方法
			List<HashMap<String, String>> ContractDetails = dao.query("MPCG02.query", map);
			EiBlock column = new EiBlock("Details");
			// 4.数据返回
			column.setRows( ContractDetails);
			inInfo.setBlock(column);
		}
		block.setCell(0, "type", type);
		// 返回参数
		return inInfo;

	}

	/**
	 * @param inInfo id 主键
	 * @return EiInfo
	 * @Title: saveMaterialContent
	 * @Description: 保存弹窗信息
	 */
	public EiInfo saveMaterialContent(EiInfo inInfo){
		// 1.获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		// 2.物资信息
		List<Map<String, Object>>  htaObj = (List<Map<String, Object>>) inInfo.get("hta");


		/************校验采购合同必填项***************/

		Map<String,Object> fields = VerificationContract(param);
		Object s = fields.get("status");
		if("300".equals(s)){
			inInfo.setMsg((String) fields.get("Msy"));
			return inInfo;
		}

		/************校验物资数量是否小于等于0***************/

		List<String> MaterialInformation = new ArrayList<>();
		for(Map<String,Object> MaterialNum : htaObj ){
			MaterialInformation.add((String) MaterialNum.get("num"));
			for(int i=0;i<MaterialInformation.size();i++){
				Double num = Double.valueOf((MaterialInformation.get(i)));
				if(num<=0){
					inInfo.setStatus(500);
					inInfo.setMsg("物资数量不能小于或等于0");
					return  inInfo;
				}
			}
		}

		/************附件信息***************/
		List<Map<String, Object>> fileObj = (List<Map<String, Object>>) inInfo.get("file");
		/************删除信息***************/
		List<Map<String, Object>> deleteFile = (List<Map<String, Object>>) inInfo.get("deleteFile");
		MpContract htgl07 = new MpContract();
		htgl07.fromMap(param);
		//4.获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		/************保存合同信息***************/
		if (StringUtils.isNotBlank(htgl07.getId())) {
			String id =htgl07.getId();
			/************获取合同状态***************/
			Map<String,Object> CollectionContractStates =  getsContractStatus(htaObj);
            Map<String,Object> d = new HashMap<>();
            d.put("statusCode",CollectionContractStates.get("statusCode"));
            d.put("statusName",CollectionContractStates.get("statusName"));
            d.put("id",id);
			param.put("statusCode","10");
			param.put("statusName","新建");
            param.put("recCreator",staffByUserId.get("workNo"));
            param.put("recCreatorName",staffByUserId.get("name"));
            param.put("recCreateTime",DateUtils.curDateTimeStr19());
			param.put("supplierName",param.get("supplierNum_textField"));
			param.put("manageDeptName",param.get("manageDeptNum_textField"));
			param.put("managerName",param.get("managerNum_textField"));
			Map<String, Object> outInfo = GenerateContract(Collections.singletonList(param),htaObj,fileObj,deleteFile);
			/************更新采购计划表状态***************/
            dao.update(".MPCG02.updatePurchaseStatus",d);
			inInfo.setStatus(Integer.parseInt((String) outInfo.get("status")));
			inInfo.setMsg((String) outInfo.get("msy"));
		}
		return inInfo;
	}



	/**
	 * @param
	 * @return EiInfo
	 * @Title: getsContractStatus
	 * @Description: 获取合同状态
	 */

	public Map<String,Object>  getsContractStatus(List<Map<String, Object>> contDetailList){
		Map<String, Object>  collectionStates = new HashMap<>();
		int k = 0;
		int j = 0;
		Map<String,Object> Status1 = new HashMap<>();
		for(Map<String,Object> status :contDetailList){
			Status1.put("num",status.get("num"));
			Status1.put("contedNum",status.get("contedNum"));
			Double b = Double.parseDouble((String) Status1.get("num"));
			String m = (String) Status1.get("contedNum");
			if(null == m){
				String n = String.valueOf(0.0);
				Status1.put("contedNum",n);
			}
			Double c = Double.parseDouble((String) Status1.get("contedNum")) + 1;
			BigDecimal data1 = new BigDecimal(b);
			BigDecimal data2 = new BigDecimal(c);
			if(data1.compareTo(data2) < 1){
			}else {
				j+=1;
			}
			k++;
		}
		if(j!=0){
			collectionStates.put("statusCode","40");
			collectionStates.put("statusName","部分生成合同");
		}else {
			collectionStates.put("statusCode","50");
			collectionStates.put("statusName","已生成合同");
		}
		return collectionStates;
	}

	/**
	 * @param
	 * @return EiInfo
	 * @Title: GenerateContract
	 * @Description: 生成合同接口
	 */

	public Map<String, Object> GenerateContract(List<Map<String, Object>> param, List<Map<String, Object>> matList,List<Map<String, Object>> fileObj,List<Map<String, Object>> deleteFile){
		Map<String, Object> outInfo = new HashMap<>();
		/************************* 合同基本信息***************/
		try {
			Map<String, Object> contractInformation = new HashMap<>();
			int i = 0;
			int j = 0;
			BigDecimal contCost;
			for (Map<String, Object> information : param) {
				//合同金额
				contCost = com.baosight.iplat4j.core.util.StringUtils.isNotEmpty((String) information.get("contCost")) ? new BigDecimal((String) information.get("contCost")) : new BigDecimal(0);
				;
				contractInformation.clear();
				contractInformation.put("id", UUID.randomUUID().toString());
				contractInformation.put("contNo", information.get("contNo"));
				contractInformation.put("contName", information.get("contName"));
				contractInformation.put("contClassify", information.get("contClassify"));
				contractInformation.put("contType", information.get("contType"));
				contractInformation.put("statusCode", information.get("statusCode"));
				contractInformation.put("statusName", information.get("statusName"));
				contractInformation.put("supplierNum", information.get("supplierNum"));
				contractInformation.put("supplierName", information.get("supplierName"));
				contractInformation.put("signDate", information.get("signDate"));
				contractInformation.put("contCost", contCost);
				contractInformation.put("validDate", information.get("validDate"));
				contractInformation.put("overDate", information.get("overDate"));
				contractInformation.put("currencyCode", information.get("currencyCode"));
				contractInformation.put("currencyName", information.get("currencyName"));
				contractInformation.put("managerNum", information.get("managerNum"));
				contractInformation.put("managerName", information.get("managerName"));
				contractInformation.put("manageDeptNum", information.get("manageDeptNum"));
				contractInformation.put("manageDeptName", information.get("manageDeptName"));
				contractInformation.put("purchaseWay", information.get("purchaseWay"));
				contractInformation.put("purchaseWayName", information.get("purchaseWayName"));
				contractInformation.put("payWay", information.get("payWay"));
				contractInformation.put("payWayName", information.get("payWayName"));
				contractInformation.put("validLimit", information.get("validLimit"));
				contractInformation.put("remark", information.get("remark"));
				contractInformation.put("recCreator", information.get("recCreator"));
				contractInformation.put("recCreatorName", information.get("recCreatorName"));
				contractInformation.put("recCreateTime", information.get("recCreateTime"));
				dao.insert("MPCG02.insert", contractInformation);
				i++;
			}

			/*************************物资基本信息***********************/
			Map<String, Object> materialInformation = new HashMap<>();
			for (Map<String, Object> materialList : matList) {
				materialInformation.put("id", UUID.randomUUID().toString());
				materialInformation.put("purchasePlanId", materialList.get("purchaseId"));
				materialInformation.put("contId", contractInformation.get("id"));
				materialInformation.put("contNo", contractInformation.get("contNo"));
				materialInformation.put("matNum", materialList.get("matNum"));
				materialInformation.put("matName", materialList.get("matName"));
				materialInformation.put("matTypeId", materialList.get("matTypeId"));
				materialInformation.put("matTypeName", materialList.get("matTypeName"));
				materialInformation.put("matSpec", materialList.get("matSpec"));
				materialInformation.put("matModel", materialList.get("matModel"));
				materialInformation.put("unit", materialList.get("unit"));
				materialInformation.put("price", materialList.get("price"));
//				materialInformation.put("num", materialList.get("num"));
				materialInformation.put("contedNum",materialList.get("contedNum"));

                List<Map<String,Object>> jk = Collections.singletonList(materialInformation);
				/*************************已生成合同数量***********************/
				List<String> contractsGeneratedNum = new ArrayList<>();
				for(Map<String,Object> contractsGenerated : jk){
					contractsGeneratedNum.add((String) contractsGenerated.get("contedNum"));
					if(contractsGeneratedNum.contains(null)){
						double n = 1;
						materialInformation.put("contedNum",n);
					} else {
						double contractNum = Double.parseDouble(contractsGeneratedNum.get(0)) + 1;
						materialInformation.put("contedNum",contractNum);
					}
				}
				/********************* 向合同明细表里插入物资信息************************/
				dao.insert("MPCG02.insertDetail", materialInformation);
				/********************* 更新采购计划明细表已生成合同数量************************/
				dao.update("MPCG02.updateContedNum",materialInformation);
				j++;
			}


			/********************* 附件基本信息************************/
			saveFile(fileObj, (String) contractInformation.get("id"), deleteFile);

			/********************* 返回值 ************************/
			outInfo.put("msy", "成功生成合同");
			outInfo.put("status", "0");
		}catch (Exception e) {
			outInfo.put("msy", "生成合同失败");
			outInfo.put("status", "-1");
		}
		return  outInfo;
	}

	/**
	 * @param info supplierName 供应商
	 * @return EiInfo
	 * supplier 供应商编码
	 * supplierName 供应商
	 * @Title: querySupplier
	 * @Description: 查询供应商
	 * <p>1.调用分页接口构建map
	 * <p>2.如果blockId相等
	 * <p>3.获取block中的数据的集合
	 * <p>4.获取集合中的数据
	 * <p>5.调用改造供应商接口并返回
	 * <p>6.如果供应商信息
	 * <p>7.获取供应商信息
	 * <p>8.如果list为空
	 * <p>9.遍历供应商信息
	 * <p>10.返回封装的方法：构建返回结果EiInfo
	 * <p>11.调用改造供应商接口并返回
	 * <p>12.如果供应商信息
	 * <p>13.获取供应商信息
	 * <p>14.如果list为空
	 * <p>15.遍历供应商信息
	 * <p>16.返回封装的方法：构建返回结果EiInfo
	 * 调用档案中心
	 * 查询供应商
	 */
	public EiInfo querySupplier(EiInfo info) {
		// 1.调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "supplier");
		// 获取blockId
		String blockId = info.getBlockIds();
		// 2.如果blockId相等
		if (blockId.equals("inqu_status,supplier")) {
			// 实例化info
			EiInfo outinfo = new EiInfo();
			// 实例化block
			EiBlock block = new EiBlock("supplier");
			// 3.获取block中的数据的集合
			List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
			// 4.获取集合中的数据
			String name = (String) listMap.get(0).get("supplierName");
			// 设置supplierName
			map.put("supplierName", name);
			// 实例化list
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 初始化查询总数为0
			int count = 0;
			// 5.调用改造供应商接口并返回
			EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
			// 6.如果供应商信息
			if (outInfo.getBlock("supplier") != null) {
				// 7.获取供应商信息
				list = outInfo.getBlock("supplier").getRows();
				// 8.如果list为空
				if (list.isEmpty()) {
					// 返回封装的方法：构建返回结果EiInfo
					return info;
				}
				// 9.遍历供应商信息
				for (Map<String, Object> map2 : list) {
					// 将key：supplierCode改为key：supplierNum
					map2.put("surpNum", map2.get("supplierCode"));
					map2.put("surpName", map2.get("supplierName"));
				}
				// 获取供应商信息总数
				count = (int) outInfo.getBlock("supplier").getAttr().get("count");
				// 10.返回封装的方法：构建返回结果EiInfo
				return CommonUtils.BuildOutEiInfo(info, "supplier", CommonUtils.createBlockMeta(list.get(0)), list, count);
			} else {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
		}
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 初始化查询总数为0
		int count = 0;
		// 11.调用改造供应商接口并返回
		EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
		// 12.如果供应商信息
		if (outInfo.getBlock("supplier") != null) {
			// 13.获取供应商信息
			list = outInfo.getBlock("supplier").getRows();
			// 14.如果list为空
			if (list.isEmpty()) {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
			// 15.遍历供应商信息
			for (Map<String, Object> map2 : list) {
				// 将key：supplierCode改为key：supplierNum
				map2.put("surpNum", map2.get("supplierCode"));
				map2.put("surpName", map2.get("supplierName"));
			}
			// 获取供应商信息总数
			count = (int) outInfo.getBlock("supplier").getAttr().get("count");
			// 16.返回封装的方法：构建返回结果EiInfo
			return CommonUtils.BuildOutEiInfo(info, "supplier", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			// 返回封装的方法：构建返回结果EiInfo
			return info;
		}
	}




	/**
	 * @param info deptNum 科室编码
	 *             deptName 科室名称
	 * @return EiInfo
	 * contDeptNum 科室编码
	 * contDeptName 科室名称
	 * @Title: queryContCostNum
	 * @Description: 接口改造所属科室
	 * <p>1.调用分页接口构建map
	 * <p>2.调用远程服务获取改造科室接口
	 * <p>3.如果存在科室信息
	 * <p>4.科室信息存储在list
	 * <p>5.循环
	 * <p>6.如果list为空
	 * <p>7.返回封装的方法：构建返回结果EiInfo
	 * 调用档案中心
	 * 查询科室
	 */
	public EiInfo queryContCostNum(EiInfo info) {
		// 1.调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "contDept");
		map.put("deptNum", map.get("manageDeptNum"));
		map.put("deptName", map.get("manageDeptName"));
		// 实例化List
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 初始化总数为0
		int count = 0;
		// 2.调用远程服务获取改造科室接口
		EiInfo outInfo = BaseDockingUtils.getDeptAllPage(map, "contDept");
		// 3.如果存在科室信息
		if (outInfo.getBlock("contDept") != null) {
			// 4.科室信息存储在list
			list = outInfo.getBlock("contDept").getRows();
			// 5.循环
			list.forEach(map2 -> {
				map2.put("manageDeptNum", map2.get("deptNum"));
				map2.put("manageDeptName", map2.get("deptName"));
			});
			// 6.如果list为空
			if (list.isEmpty()) {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
			// 科室信息总数存在count
			count = (int) outInfo.getBlock("contDept").getAttr().get("count");
			// 7.返回封装的方法：构建返回结果EiInfo
			return CommonUtils.BuildOutEiInfo(info, "contDept", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			// 返回封装的方法：构建返回结果EiInfo
			return info;
		}
	}

	/**
	 * @param info userName 用户名
	 * @return EiInfo
	 * work 工号
	 * name 用户名
	 * @Title: queryAdmin
	 * @Description: 接口改造合同管理员
	 * <p>1.调用分页接口构建map
	 * <p>2.获取blockId
	 * <p>3.如果blockId相等
	 * <p>4.获取block中的数据的集合
	 * <p>5.获取集合中的数据
	 * <p>6.调用改造人员接口并返回
	 * <p>7.如果存在人员信息
	 * <p>8.获取人员信息
	 * <p>9.如果list为空
	 * <p>10.返回封装的方法：构建返回结果EiInfo
	 * <p>11.调用改造人员接口并返回
	 * <p>12.调用改造人员接口并返回
	 * <p>13.如果存在人员信息
	 * <p>14.获取人员信息
	 * <p>15.返回封装的方法：构建返回结果EiInfo
	 * 调用档案中心
	 * 查询人员
	 */
	public EiInfo queryAdmin(EiInfo info) {
		// 1.调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "contAdmin");
		map.put("userName", map.get("name"));
		// 2.获取blockId
		String blockId = info.getBlockIds();
		// 3.如果blockId相等
		if (blockId.equals("inqu_status,person")) {
			// 实例化info
			EiInfo outinfo = new EiInfo();
			// 实例化block
			EiBlock block = new EiBlock("person");
			// 4.获取block中的数据的集合
			List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
			// 5.获取集合中的数据
			String name = (String) listMap.get(0).get("name");
			// 设置userName
			map.put("userName", name);
			// 实例化List
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 初始化查询总数为0
			int count = 0;
			// 6.调用改造人员接口并返回
			EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "person");
			// 7.如果存在人员信息
			if (outInfo.getBlock("person") != null) {
				// 8.获取人员信息
				list = outInfo.getBlock("person").getRows();
				// 9.如果list为空
				if (list.isEmpty()) {
					// 返回封装的方法：构建返回结果EiInfo
					return info;
				}
				// 获取人员信息总数
				count = (int) outInfo.getBlock("person").getAttr().get("count");
				// 10.返回封装的方法：构建返回结果EiInfo
				return CommonUtils.BuildOutEiInfo(info, "person", CommonUtils.createBlockMeta(list.get(0)), list, count);
			} else {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
		}
		// 11.调用改造人员接口并返回
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 初始化查询总数为0
		int count = 0;
		// 12.调用改造人员接口并返回
		EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "contAdmin");
		// 13.如果存在人员信息
		if (outInfo.getBlock("contAdmin") != null) {
			// 14.获取人员信息
			list = outInfo.getBlock("contAdmin").getRows();
			// 如果list为空
			if (list.isEmpty()) {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
			// 获取人员信息总数
			count = (int) outInfo.getBlock("contAdmin").getAttr().get("count");
			// 15.返回封装的方法：构建返回结果EiInfo
			return CommonUtils.BuildOutEiInfo(info, "contAdmin", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			// 返回封装的方法：构建返回结果EiInfo
			return info;
		}
	}







	/**
	 * @param inInfo attachId 附件ID
	 *               datagroupCode 账套
	 * @Title: queryFile
	 * @Description: 查询项目附件信息
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
		return queryTabGrid(inInfo, "CMDJ0101.queryFileList", "CMDJ0101.queryFileCount", "resultD", new MpContractFile().eiMetadata);
	}


	public EiInfo queryMaterial(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "MPCG02.queryMaterialList", "MPCG02.queryMaterialCount", "Details", new MpPurchasePlanDetail().eiMetadata);
	}




	/**
	 * 保存合同附件
	 *
	 * @param fileObj id 主键
	 *                contPk 项目主键
	 *                attachId 附件ID
	 *                attachName 附件名称
	 *                attachPath 附件路径
	 *                attachSize 附件大小
	 *                attachDesc 附件说明
	 *                archiveFlag 归档标记
	 *                datagroupCode 账套
	 *                recCreator 记录创建责任者
	 *                recCreateTime 记录创建时间
	 *                recRevisor 记录修改责任者
	 *                recReviseTime 记录修改时间
	 * @Title: saveFile
	 * <p>1.如果入参不为空
	 * <p>2.判断要删除的文件是否为空
	 * <p>3.循环修改删除文件状态
	 * <p>4.删除旧的附件信息
	 * <p>5.增强循环
	 * <p>6.实体转换为参数
	 * <p>7.获取当前登录用户信息
	 * <p>8.调用插入方法
	 * <p>9.物理删除文件
	 * @return: void
	 */
	private void saveFile(Object fileObj, String id, List<Map<String, Object>> deleteFile) {
		String path = System.getProperty("user.dir");
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 1.如果入参不为空
		if (fileObj != null) {
			// 参数赋值
			list = (List<Map<String, Object>>) fileObj;
		}
		//2.判断要删除的文件是否为空
		List<String> filePath = new ArrayList<>();
		if (!deleteFile.isEmpty()) {
			//3.循环修改删除文件状态
			for (Map<String, Object> map : deleteFile) {
				map.put("contId", id);
				Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
				map.put("recRevisor", (String) staffByUserId.get("name"));
				map.put("recReviseTime", DateUtils.curDateTimeStr19());
				dao.update("MPCG02.updateFile", map);
				filePath.add(path + File.separator + map.get("attachPath"));
			}
		}
		// 4.删除旧的附件信息
		dao.delete("MPCG02.deleteFile", id);
		// 5.增强循环
		for (Map<String, Object> map : list) {
			// 实例化实体
			MpContractFile att = new MpContractFile();
			// 6.实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setContId(id);
			att.setDataGroupCode(DatagroupUtil.getDatagroupCode());
			//7.获取当前登录用户信息
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			att.setRecCreator((String) staffByUserId.get("name"));
			att.setArchiveFlag("1");
			// 8.调用插入方法
			dao.insert("MPCG02.insertFile", att);
		}
		//9.物理删除文件
		if (!filePath.isEmpty()) {
			//循环删除文件
			for (String fileP : filePath) {
				//获取文件
				File file = new File(fileP);
				//判断是否存在
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}



	/**
	 * @param inInfo querySql 查询sql
	 *               countSql 统计sql
	 *               resultBlock blockId
	 *               blockMeta EiBlockMeta
	 * @Title: queryTabGrid
	 * @Description: TabGrid查询方法
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
	 * @param
	 * @return EiInfo
	 * @Title: VerificationContract
	 * @Description: 校验合同必填项
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> VerificationContract(Map<String, Object> param) {
		Map<String, Object>  verificationReturn = new HashMap<>();
		String  contNo = (String) param.get("contNo");
		String 	contName = (String) param.get("contName");
		String  contClassify = (String) param.get("contClassify");
		String  contType  = (String) param.get("contType");
		String  contCost  = (String) param.get("contCost");
		String  signDate  = (String) param.get("signDate");
		String  validDate  = (String) param.get("validDate");
		String  overDate  = (String) param.get("overDate");
		String  manageDeptNum  = (String) param.get("manageDeptNum");
		String  managerNum  = (String) param.get("managerNum");
		String  errorMsy = "";
		if(StringUtils.isBlank(contNo)){
			errorMsy = errorMsy + "合同号不能为空";
		}
		if(StringUtils.isBlank(contName)){
			errorMsy = errorMsy + "合同名称不能为空";
		}
		if(StringUtils.isBlank(contClassify)){
			errorMsy = errorMsy + "合同分类不能为空";
		}
		if(StringUtils.isBlank(contType)){
			errorMsy = errorMsy + "合同类型不能为空";
		}
		if(StringUtils.isBlank(contCost)){
			errorMsy = errorMsy + "金额";
		}
		if(StringUtils.isBlank(signDate)){
			errorMsy = errorMsy + "合同签订日期";
		}
		if(StringUtils.isBlank(validDate)){
			errorMsy = errorMsy + "合同生效日期";
		}
		if(StringUtils.isBlank(overDate)){
			errorMsy = errorMsy + "合同终止日期";
		}
		if(StringUtils.isBlank(manageDeptNum)){
			errorMsy = errorMsy + "合同所属部门";
		}
		if(StringUtils.isBlank(managerNum)){
			errorMsy = errorMsy + "管理员";
		}
		if(errorMsy.length()>0){
			verificationReturn.put("Msy",errorMsy);
			verificationReturn.put("status","300");
		}else{
			verificationReturn.put("status","400");
		}
		return verificationReturn;
	}


}
