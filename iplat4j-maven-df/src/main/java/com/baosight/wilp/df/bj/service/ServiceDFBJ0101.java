package com.baosight.wilp.df.bj.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.domain.DfClean;
import com.baosight.wilp.df.common.domain.DfCleaningExecutor;
import com.baosight.wilp.df.common.util.DFUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 该页面为设备保洁管理
 * 主要包含对保洁信息的查询、保存功能
 * 设备保洁模块：初始化查询、保存弹窗信息、查询科室、查询负责人、查询保洁执行人、创建保洁单号、保存保洁执行人、TabGrid查询方法
 * <p>1.初始化查询 initLoad
 * <p>2.保存弹窗信息 saveClean
 * <p>3.查询科室 queryCostNum
 * <p>4.查询负责人 queryAdmin
 * <p>5.查询保洁执行人 queryPerson
 * <p>6.创建保洁单号 createCleanNo
 * <p>7.保存保洁执行人 cleanIng
 * <p>8.TabGrid查询方法 queryTabGrid
 * @Title: ServiceDFBJ0101.java
 * @ClassName: ServiceDFBJ0101
 * @Package：com.baosight.wilp.df.bj.service
 * @author: liangyongfei
 * @date: 2022年6月27日 下午1:23:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDFBJ0101 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 保洁登记管理弹出界面
     * 通过保洁操作类型
     * 进行保洁操作
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
		block.addBlockMeta(new DfClean().eiMetadata);
		// 如果参数存在
		if (StringUtils.isNotBlank(id)) {
		    // 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			// 调用查询方法
			List<HashMap<String, String>> list = dao.query("DFBJ01.queryId", map);
			// 数据返回
			block.addRows(list);
			// 特殊数据回显
			EiColumn column1 = new EiColumn("machineName_textField");
			EiColumn column2 = new EiColumn("cleanDeptName_textField");
			EiColumn column3 = new EiColumn("deptManageName_textField");
//			String surpNum=list.get(0).getSurpNum();
			block.addMeta(column1);
			block.addMeta(column2);
			block.addMeta(column3);
			String contdeptNUm = list.get(0).get("contdeptNUm");
			block.setCell(0, "machineName_textField", list.get(0).get("machineName"));
			block.setCell(0, "cleanDeptName_textField", list.get(0).get("cleanDeptName"));
			block.setCell(0, "deptManageName_textField", list.get(0).get("deptManageName"));
			block.setCell(0, "type",type);
		}
		block.setCell(0, "type", type);
		inInfo.addBlock(block);
		// 返回参数
		return inInfo;

	}

	/**
     * @Title: saveClean
     * @Description: 保存弹窗信息
     * 保存保洁基础内容
     * 保存保洁执行人
     * @param inInfo
     * id 主键
     * machineCode 设备编码
     * machineName 设备名称
     * fixedPlace 安装地点
	 * cleanDeptName 保洁单位
     * deptManageName 负责人
     * cleanDate 保洁日期
     * remark 作业说明
     * @return EiInfo
     */
	public EiInfo saveClean(EiInfo inInfo) {
		// 获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		// 获取tab/grid参数
		Object hteObj = inInfo.get("hte");
		// 保存项目
		DfClean clean = new DfClean();
		clean.fromMap(param);//fromMap实体类
        //通过设备编码查询设备档案该设备的ID
		String id = (String)param.get("machineName");
		String cleanNo = (String) param.get("cleanNo");
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("id",id);
		map.put("cleanNo",cleanNo);
		//通过设备编码查询设备ID
		List<Map<String, ?>> result=dao.query("DFBJ01.selectId",map);
		if(result.size()==0){
			//通过设备名称查询设备ID
			List<Map<String, ?>> resul =dao.query("DFBJ01.selectIdd",map);
			Map resulMap = resul.get(0);
			clean.setMachineId((String) resulMap.get("machineId"));
		}else {
			Map stringMap = result.get(0);
			clean.setMachineId((String) stringMap.get("machineId"));
		}
		//获取设备编码
		List<Map<String, ?>> deviceCode =dao.query("DFBJ01.selectDeviceCheck",map);
		if(deviceCode.size() > 0){
                Map deviceCodeMap = deviceCode.get(0);
			clean.setMachineCode((String)deviceCodeMap .get("machineCode"));
		}else if (deviceCode.size()==0){
			List<Map<String, ?>> deviceCodeTwo =dao.query("DFBJ01.selectDeviceCheckTwo",map);
			Map deviceCodeCheckTwo = deviceCodeTwo.get(0);
			clean.setMachineCode((String)deviceCodeCheckTwo.get("machineCode"));
		}
		clean.setMachineName((String)param.get("machineName_textField"));
		//获取保洁单位
		clean.setCleanDeptName((String)param.get("cleanDeptName_textField"));
		//获取负责人
		clean.setDeptManageName((String)param.get("deptManageName_textField"));
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 如果参数为空
		if (StringUtils.isBlank(clean.getId())) {
			clean.setId(UUID.randomUUID().toString());
			clean.setCleanNo(createCleanNo());
			clean.setRecCreator((String)staffByUserId.get("name"));
			clean.setRecCreateTime(DateUtils.curDateTimeStr19());
//			htgl07.setBillMaker((String)staffByUserId.get("name"));
//			htgl07.setBillMakeTime(new Timestamp(System.currentTimeMillis()));
			// 保存项目
			dao.insert("DFBJ01.insert", clean);
		} else {
			clean.setRecRevisor(UserSession.getUser().getUsername());
			clean.setRecReviseTime(DateUtils.curDateTimeStr19());
			// 更新项目
			dao.update("DFBJ01.update", clean);
		}
		//保存保洁执行人 获取clean的保洁单号
		cleanIng(hteObj, clean.getCleanNo());
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
            return CommonUtils.BuildOutEiInfo(info, "contDept", DFUtils.createBlockMeta(list.get(0)), list, count);
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
        Map<String, Object> map = DFUtils.buildParamter(info, "inqu_status", "contAdmin");
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
                return CommonUtils.BuildOutEiInfo(info, "person", DFUtils.createBlockMeta(list.get(0)), list, count);
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
            return CommonUtils.BuildOutEiInfo(info, "contAdmin", DFUtils.createBlockMeta(list.get(0)), list, count);
         }else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
         }
    }
	/**
     * @Title: queryPerson
     * @Description: 查询保洁执行人
     * @param inInfo
     * name 联系人
     * @return: EiInfo
     * id 主键
	 * workNo 工号
     * name 姓名
     * number 联系电话
     * deptName 科室编码
     */
	public EiInfo queryPerson(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo, "DFBJ0101.queryPersonList", "DFBJ0101.queryPersonCount", "resultE", new DfCleaningExecutor().eiMetadata);
	}

	/**
     * @Title: createCleanNo
     * @Description: 生成保洁单号
     * 通过获取当前时间
     * 判断今天是否存在保洁单号，不存在就返回新的保洁单号
     * 存在保洁单号，对保洁单号进行累加
     * @param
     * @return: String
     * String 保洁单号
     */
	private String createCleanNo() {
	    // 加锁
		synchronized (dao) {
		    // 调用时间接口
			String date = DateUtils.curDateStr8();
			// 调用查询方法
			List<String> list = dao.query("DFBJ0101.createCleanNo", "BJ" + date);
			// 如果结果为空
			if (list == null || list.size() == 0 || list.get(0) == null) {
			    // 返回合同号
				return "BJ" + date + "0001";
			} else {
			    // 获取最大合同号
				String maxNo = list.get(0);
				// 对最大合同号进行拆分
				maxNo = maxNo.substring(2);
				// 返回合同号
				return "BJ" + (Long.parseLong(maxNo) + 1L) + "";
			}
		}

	}
	/**
	 * @Title: cleanIng
	 * @Description: 保存保洁执行人
	 * @param hteObj
	 * id 主键
	 * cleanNo 保洁单号
	 * name 姓名
	 * number 联系电话
	 * deptName 科室名称
	 * @return: void
	 */
	private void cleanIng(Object hteObj, String id) {
	    // 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果入参不为空
		if (hteObj != null) {
		    // 参数赋值
			list = (List<Map<String, Object>>) hteObj;
			// 调用删除方法
			dao.delete("DFBJ01e.delete", id);
		}
		// 增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			DfCleaningExecutor att = new DfCleaningExecutor();
			// 实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setCleanNo(id);
			att.setRecCreator(UserSession.getUser().getUsername());
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 调用插入方法
			dao.insert("DFBJ01e.insert", att);
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


}
