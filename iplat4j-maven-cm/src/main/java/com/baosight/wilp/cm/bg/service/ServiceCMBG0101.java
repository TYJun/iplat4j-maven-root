package com.baosight.wilp.cm.bg.service;

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
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.*;
/**
 * 该页面为合同登记管理
 * 主要包含对合同登记的新增、删除、编辑、查看、审批功能
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
 * @Title: ServiceCMBG0101.java
 * @ClassName: ServiceCMBG0101
 * @Package：com.baosight.wilp.cm.bg.service
 * @author: zhaow
 * @date: 2021年8月30日 上午10:02:05
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMBG0101 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
	 * <p>1.获取参数
	 * <p>2.实例化EiBlock
	 * <p>3.设置EiBlock的值
	 * <p>4.如果参数存在
	 * <p>5.调用查询方法
	 * <p>6.特殊数据回显
     * 合同变更管理弹出界面
     * 通过合同操作类型
     * 进行合同操作
     * @param inInfo
     * id 主键
     * type 操作类型 
     * @return EiInfo
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 1.获取参数
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		// 2.实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		// 3.设置EiBlock的值
		block.addBlockMeta(new ContractBill().eiMetadata);
		// 4.如果参数存在
		if (StringUtils.isNotBlank(id)) {
		    // 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			// 5.调用查询方法
			List<HashMap<String, String>> list = dao.query("CMDJ01.queryId", map);
			// 数据返回
			block.addRows(list);
			// 6.特殊数据回显
			EiColumn column1 = new EiColumn("surpNum_textField");
			EiColumn column2 = new EiColumn("contDeptNum_textField");
			EiColumn column3 = new EiColumn("contAdmin_textField");
			block.addMeta(column1);
			block.addMeta(column2);
			block.addMeta(column3);
			block.setCell(0, "surpNum_textField", list.get(0).get("surpName"));
			block.setCell(0, "contDeptNum_textField", list.get(0).get("contDeptName"));
			block.setCell(0, "contAdmin_textField", list.get(0).get("contAdminName"));
		}
		inInfo.addBlock(block);
		// 返回参数
		return inInfo;

	}

	/**
     * @Title: saveContent
     * @Description: 保存弹窗信息
	 * <p>1.获取表单参数
	 * <p>2.获取tab/grid参数
	 * <p>3.实例化ContractBill
	 * <p>4.实例类参数转换
	 * <p>5.获取当前登录用户信息
	 * <p>6.如果参数为空
	 * <p>7.设置参数
	 * <p>8.保存项目
	 * <p>9.保存合同条款内容
	 * <p>10.保存合同付款内容
	 * <p>11.保存合同费用内容
	 * <p>12.保存合同短信通知人
	 * <p>13.保存附件
     * @param inInfo
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
     * billMakeTime 制表时间
     * @return EiInfo
     */
	public EiInfo saveContent(EiInfo inInfo) {
		// 1.获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		// 2.获取tab/grid参数
		Object htaObj = inInfo.get("hta");
		Object htbObj = inInfo.get("htb");
		Object htcObj = inInfo.get("htc");
		Object hteObj = inInfo.get("hte");
		Object fileObj = inInfo.get("file");
		// 3.实例化ContractBill
		ContractBill htgl07 = new ContractBill();
		// 4.实例类参数转换
		htgl07.fromMap(param);
		htgl07.setContAdminName((String)param.get("contAdmin_textField"));
        htgl07.setContDeptName((String)param.get("contDeptNum_textField"));
		// 5.获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 6.如果参数为空
		if (StringUtils.isBlank(htgl07.getId())) {
		    // 7.设置参数
			htgl07.setId(UUID.randomUUID().toString());
			htgl07.setCurrType("rmb");
			htgl07.setContNo(createContNo());
			htgl07.setRecCreator((String)staffByUserId.get("name"));
			htgl07.setRecCreateTime(DateUtils.curDateTimeStr19());
			htgl07.setBillMaker((String)staffByUserId.get("name"));
			htgl07.setBillMakeTime(new Timestamp(System.currentTimeMillis()));
			htgl07.setContStatus("0");
			// 8.保存项目
			dao.insert("CMDJ01.insert", htgl07);
		} else {
			htgl07.setRecRevisor(UserSession.getUser().getUsername());
			htgl07.setRecReviseTime(DateUtils.curDateTimeStr19());
			dao.update("CMDJ01.update", htgl07);
		}
		// 9.保存合同条款内容
		saveHta(htaObj, htgl07.getId());
        //10.保存合同付款内容
		saveHtb(htbObj, htgl07.getId());
        //11.保存合同费用内容
		saveHtc(htcObj, htgl07.getId());
		//12.保存合同短信通知人
		saveHte(hteObj, htgl07.getId());
		//13.保存附件
		saveFile(fileObj, htgl07.getId());
		return inInfo;
	}
	
	/**
     * @Title: querySupplier
     * @Description: 查询供应商
	 * <p>1.调用分页接口构建map
	 * <p>2.获取blockId
	 * <p>3.如果blockId相等
	 * <p>4.获取block中的数据的集合
	 * <p>5.获取集合中的数据
	 * <p>6.调用改造供应商接口并返回
	 * <p>7.如果供应商信息
	 * <p>8.获取供应商信息
	 * <p>9.如果list为空
	 * <p>10.遍历供应商信息
	 * <p>12.返回封装的方法：构建返回结果EiInfo
	 * <p>13.调用改造供应商接口并返回
	 * <p>14.如果供应商信息
	 * <p>15.获取供应商信息
	 * <p>16.如果list为空
	 * <p>17.遍历供应商信息
	 * <p>18.返回封装的方法：构建返回结果EiInfo
     * 调用档案中心
     * 查询供应商
     * @param info
     * supplierName 供应商
     * @return EiInfo
     * supplier 供应商编码
     * supplierName 供应商
     */
	public EiInfo querySupplier(EiInfo info) {
        // 1.调用分页接口构建map
        Map<String, Object> map = PMUtils.buildParamter(info, "inqu_status", "supplier");
        // 2.获取blockId
        String blockId = info.getBlockIds();
        // 3.如果blockId相等
        if(blockId.equals("inqu_status,supplier")) {
            // 实例化info
            EiInfo outinfo = new EiInfo();
            // 实例化block
            EiBlock block = new EiBlock("supplier");
            // 4.获取block中的数据的集合
            List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
            // 5.获取集合中的数据
            String name = (String) listMap.get(0).get("supplierName");
            // 设置supplierName
            map.put("supplierName", name);
            // 实例化list
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            // 初始化查询总数为0
            int count = 0;
            // 6.调用改造供应商接口并返回
            EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
            // 7.如果供应商信息
            if(outInfo.getBlock("supplier") != null) {
                // 8.获取供应商信息
                list = outInfo.getBlock("supplier").getRows();
                // 9.如果list为空
                if(list.isEmpty()) {
                    // 返回封装的方法：构建返回结果EiInfo
                    return info;
                }
                // 10.遍历供应商信息
                for (Map<String, Object> map2 : list) {
                    // 将key：supplierCode改为key：supplierNum
                    map2.put("surpNum", map2.get("supplierCode"));
                    map2.put("surpName", map2.get("supplierName"));
                }
                // 获取供应商信息总数
                count = (int)outInfo.getBlock("supplier").getAttr().get("count");
                // 12.返回封装的方法：构建返回结果EiInfo
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
        // 13.调用改造供应商接口并返回
        EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
        // 14.如果供应商信息
        if(outInfo.getBlock("supplier") != null) {
            // 15.获取供应商信息
            list = outInfo.getBlock("supplier").getRows();
            // 16.如果list为空
            if(list.isEmpty()) {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
            // 17.遍历供应商信息
            for (Map<String, Object> map2 : list) {
                // 将key：supplierCode改为key：supplierNum
                map2.put("surpNum", map2.get("supplierCode"));
                map2.put("surpName", map2.get("supplierName"));
            }
            // 获取供应商信息总数
            count = (int)outInfo.getBlock("supplier").getAttr().get("count");
            // 18.返回封装的方法：构建返回结果EiInfo
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
		// 设置info参数
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
		// 设置info参数
		inInfo.addBlock("payTypeName").addRows(query);
		inInfo.getBlock("payTypeName").setBlockMeta(new ContractPayment().eiMetadata);
		return inInfo;

	}

	/**
     * @Title: queryContCostNum 
     * @Description: 接口改造所属科室
	 * <p>1.调用分页接口构建map
	 * <p>2.调用远程服务获取改造科室接口
	 * <p>3.获得rows
	 * <p>4.增强循环
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
	    // 实例化新的
	    EiInfo outInfo = new EiInfo();
        // 1.调用分页接口构建map
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "contDept");
        // 2.调用远程服务获取改造科室接口
        outInfo = BaseDockingUtils.getDeptAllPage(map, "contDept");
        // 3.获得rows
        List<Map<String, Object>> contDeptList = outInfo.getBlock("contDept").getRows();
        // 4.增强循环
        contDeptList.forEach(map2 -> {
            map2.put("contDeptNum", map2.get("deptNum"));
            map2.put("contDeptName", map2.get("deptName"));
        });
        // 返回
        return outInfo;
    }

	/**
     * @Title: queryAdmin
     * @Description: 接口改造合同管理员
	 * <p>1.获取blockId
	 * <p>2.如果blockId相等
	 * <p>3.获取block中的数据的集合
	 * <p>4.获取集合中的数据
	 * <p>5.调用改造人员接口并返回
	 * <p>6.调用改造人员接口并返回
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
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "contAdmin");
        // 1.获取blockId
        String blockId = info.getBlockIds();
        // 2.如果blockId相等
        if(blockId.equals("inqu_status,person")) {
            // 实例化info
            EiInfo outinfo = new EiInfo();
            // 实例化block
            EiBlock block = new EiBlock("person");
            // 3.获取block中的数据的集合
            List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
            // 4.获取集合中的数据
            String name = (String) listMap.get(0).get("name");
            // 设置userName
            map.put("userName", name);
            // 5.调用改造人员接口并返回
            return BaseDockingUtils.getStaffAllPage(map,"contAdmin");
        }
        // 6.调用改造人员接口并返回
        return BaseDockingUtils.getStaffAllPage(map, "contAdmin");
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
     * @Title: queryPerson
     * @Description: 查询合同短信人
     * @param inInfo
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
	 * <p>1.加锁
	 * <p>2.调用时间接口
	 * <p>3.调用查询方法
	 * <p>4.如果结果为空
	 * <p>5.获取最大合同号
	 * <p>6.对最大合同号进行拆分
	 * <p>7.返回合同号
     * 通过获取当前时间
     * 判断今天是否存在合同编号，不存在就返回新的合同号
     * 存在合同编号，对合同编号进行累加
     * @param
     * @return: String
     * String 合同编号
     */
	private String createContNo() {
	    // 1.加锁
		synchronized (dao) {
		    // 2.调用时间接口
			String date = DateUtils.curDateStr8();
			// 3.调用查询方法
			List<String> list = dao.query("CMDJ0101.createContNo", "PO" + date);
			// 4.如果结果为空
			if (list == null || list.size() == 0 || list.get(0) == null) {
			    // 返回合同号
				return "PO" + date + "0001";
			} else {
			    // 5.获取最大合同号
				String maxNo = list.get(0);
				// 6.对最大合同号进行拆分
				maxNo = maxNo.substring(2);
				// 7.返回合同号
				return "PO" + (Long.parseLong(maxNo) + 1L) + "";
			}
		}

	}

	/**
     * @Title: saveHta
     * @Description: 保存合同条款内容
	 * <p>1.如果入参不为空
	 * <p>2.调用删除方法
	 * <p>3.增强循环
	 * <p>4.调用插入方法
     * 合同条款定义界面
     * 获取填写信息
     * 保存弹窗信息
     * @param
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
	private void saveHta(Object htaObj, String id) {
	    // 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 1.如果入参不为空
		if (htaObj != null) {
		    // 参数赋值
			list = (List<Map<String, Object>>) htaObj;
			// 2.调用删除方法
			dao.delete("CMDJ01a.delete", id);
		}
		// 3.增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			ContractBillTerms att = new ContractBillTerms();
			// 实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setContNo(id);
			att.setRecCreator(UserSession.getUser().getUsername());
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 4.调用插入方法
			dao.insert("CMDJ01a.insert", att);
		}
	}

	/**
     * @Title: saveHtb
     * @Description: 保存合同付款内容
	 * <p>1.如果入参不为空
	 * <p>2.调用删除方法
	 * <p>3.增强循环
	 * <p>4.调用插入方法
     *合同付款内容
     * 获取填写信息
     * 保存弹窗信息
     * @param
     * id 主键
     * contNo 合同号
     * lastTime 距离上次付款时间
     * payRate 付款比例
     * payAmount 付款金额
     * remark 备注
     * payFlag 付款标记
     * @return: EiInfo
     */
	private void saveHtb(Object htbObj, String id) {
	    // 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 1.如果入参不为空
		if (htbObj != null) {
		    // 参数赋值
			list = (List<Map<String, Object>>) htbObj;
			// 2.调用删除方法
			dao.delete("CMDJ01b.delete", id);
		}
		// 3.增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			ContractBillPayment att = new ContractBillPayment();
			// 实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setContNo(id);
			att.setRecCreator(UserSession.getUser().getUsername());
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 4.调用插入方法
			dao.insert("CMDJ01b.insert", att);
		}
	}

	/**
     * @Title: saveHtc
     * @Description: 保存合同费用内容
	 * <p>1.如果入参不为空
	 * <p>2.调用删除方法
	 * <p>3.增强循环
	 * <p>4.调用插入方法
     * 合同费用定义界面
     * 获取填写信息
     * 保存弹窗信息
     * @param
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
	private void saveHtc(Object htcObj, String id) {
	    // 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 1.如果入参不为空
		if (htcObj != null) {
		    // 参数赋值
			list = (List<Map<String, Object>>) htcObj;
			// 2.调用删除方法
			dao.delete("CMDJ01c.delete", id);
		}
		// 3.增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			ContractBillCost att = new ContractBillCost();
			// 实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setContNo(id);
			att.setRecCreator(UserSession.getUser().getUsername());
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 4.调用插入方法
			dao.insert("CMDJ01c.insert", att);
		}
	}

	/**
     * 保存合同附件
     * @Title: saveFile
	 * <p>1.如果入参不为空
	 * <p>2.删除旧的附件信息
	 * <p>3.增强循环
	 * <p>4.实体转换为参数
	 * <p>5.获取当前登录用户信息
	 * <p>6.新增
     * @param fileObj
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
     * @return: void
     */
	private void saveFile(Object fileObj, String id) {
	    // 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 1.如果入参不为空
		if (fileObj != null) {
		    // 参数赋值
			list = (List<Map<String, Object>>) fileObj;
		}
		// 2.删除旧的附件信息
		dao.delete("CMDJ0101.deleteFile", id);
		// 3.增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			ContractBillFile att = new ContractBillFile();
			// 4.实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setContPk(id);
			att.setDatagroupCode(DatagroupUtil.getDatagroupCode());
			//5.获取当前登录用户信息
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			att.setRecCreator((String)staffByUserId.get("name"));
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 6.新增
			dao.insert("CMDJ0101.insertFile", att);
		}
	}

	/**
     * @Title: saveHte
     * @Description: 保存合同联系人
	 * <p>1.如果入参不为空
	 * <p>2.调用删除方法
	 * <p>3.增强循环
	 * <p>4.调用插入方法
     * @param hteObj
     * id 主键
     * contNo 合同号
     * name 联系人
     * number 联系电话
     * deptNum 科室编码
     * @return: void
     */
	private void saveHte(Object hteObj, String id) {
	    // 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 1.如果入参不为空
		if (hteObj != null) {
		    // 参数赋值
			list = (List<Map<String, Object>>) hteObj;
			 // 2.调用删除方法
			dao.delete("CMDJ01e.delete", id);
		}
		// 3.增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			ContractBillLinkman att = new ContractBillLinkman();
			// 实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setContNo(id);
			att.setRecCreator(UserSession.getUser().getUsername());
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 4.调用插入方法
			dao.insert("CMDJ01e.insert", att);
		}

	}
	
	/**
     * TabGrid查询方法
     * @Title: queryTabGrid 
     * @param
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
}
