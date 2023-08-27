package com.baosight.wilp.df.rh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.domain.DfLubricate;
import com.baosight.wilp.df.common.domain.DfLubricateDetail;
import com.baosight.wilp.df.common.domain.DfLubricateExecutor;
import com.baosight.wilp.df.common.util.DFUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 该页面为设备润滑管理
 * 主要包含对保洁信息的查询、保存功能
 * 设备保洁模块：初始化查询、保存弹窗信息、查询科室、查询负责人、查询润滑执行人、创建润滑单号、保存润滑信息、保存润滑执行人、TabGrid查询方法
 * <p>1.初始化查询 initLoad
 * <p>2.保存弹窗信息 saveLubricate
 * <p>3.查询科室 queryCostNum
 * <p>4.查询负责人 queryAdmin
 * <p>5.查询润滑执行人 queryPerson
 * <p>6.创建润滑单号 saveLubricate</>
 * <p>7.保存润滑信息 LubricateInformation
 * <p>7.保存润滑执行人 LubricateExecutor
 * <p>8.TabGrid查询方法 queryTabGrid
 * @Title: ServiceDFRH0101.java
 * @ClassName: ServiceDFRH0101
 * @Package：com.baosight.wilp.df.rh.service
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
public class ServiceDFRH0101 extends ServiceBase {
	/**
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * 润滑登记管理弹出界面
	 * 通过润滑操作类型
	 * 进行润滑操作
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
		block.addBlockMeta(new DfLubricate().eiMetadata);
		// 如果参数存在
		if (StringUtils.isNotBlank(id)) {
		    // 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			// 调用查询方法
			List<HashMap<String, String>> list = dao.query("DFRH01.queryId", map);
			// 数据返回
			block.addRows(list);
            //回显润滑信息
			List<HashMap<String, String>> LubricationExecutor = dao.query("DFRH01.queryLubricationInformation", map);
			inInfo.addRows("resultB",LubricationExecutor);
			//回显润滑执行人
			List<HashMap<String, String>> LubricationInformation = dao.query("DFRH01.queryLubricationExecutor", map);
			inInfo.addRows("resultE",LubricationInformation);
			// 特殊数据回显
			EiColumn column1 = new EiColumn("machineName_textField");
			EiColumn column2 = new EiColumn("lubricateDeptName_textField");
			EiColumn column3 = new EiColumn("lubricateManageName_textField");
//			String surpNum=list.get(0).getSurpNum();
			block.addMeta(column1);
			block.addMeta(column2);
			block.addMeta(column3);
			String contdeptNUm = list.get(0).get("contdeptNUm");
			block.setCell(0, "machineName_textField", list.get(0).get("machineName"));
			block.setCell(0, "lubricateDeptName_textField", list.get(0).get("lubricateDeptName"));
			block.setCell(0, "lubricateManageName_textField", list.get(0).get("lubricateManageName"));
			block.setCell(0, "type",type);
		}
		block.setCell(0, "type", type);
		inInfo.addBlock(block);
		// 返回参数
		return inInfo;

	}

	/**
     * @Title: savelubricate
     * @Description: 保存弹窗信息
     * 保存润滑基本信息
     * 保存润滑执行人
     * 保存润滑信息
     * @param inInfo
     * id 主键
     * machineCode 设备编码
     * machineName 设备名称
     * fixedPlace 安装地点
	 * lubricateDeptName 负责科室
     * lubricateManageName 负责人
     * lubricateDate 润滑日期
     * remark 作业说明
     * @return EiInfo
     */
	public EiInfo savelubricate(EiInfo inInfo) {
		// 获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		// 获取tab/grid参数
		Object htbObj = inInfo.get("htb");
		Object hteObj = inInfo.get("hte");
		// 保存项目
		DfLubricate lubrication = new DfLubricate();
		lubrication.fromMap(param);//fromMap实体类
		//通过设备编码查询设备档案该设备的ID
		String id = (String)param.get("machineName");
		String lubricateNo = (String) param.get("lubricateNo");
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("id",id);
		map.put("lubricateNo",lubricateNo);
		//获取设备ID
		List<Map<String, ?>> result=dao.query("DFBJ01.selectId",map);
		if(result.size()==0) {
			List<Map<String, ?>> resul = dao.query("DFBJ01.selectIdd", map);
			Map resulMap = resul.get(0);
			lubrication.setMachineId((String) resulMap.get("machineId"));
		}else{
			Map stringMap = result.get(0);
			lubrication.setMachineId((String)stringMap.get("machineId"));
		}
		//获取设备编码
		List<Map<String, ?>> deviceCode =dao.query("DFBJ01.selectDeviceCheck",map);
		if(deviceCode.size() > 0){
			Map deviceCodeMap = deviceCode.get(0);
			lubrication.setMachineCode((String)deviceCodeMap .get("machineCode"));
		}else if (deviceCode.size()==0){
			List<Map<String, ?>> deviceCodeTwo =dao.query("DFRH01.selectLubricateCheckTwo",map);
			Map deviceCodeCheckTwo = deviceCodeTwo.get(0);
			lubrication.setMachineCode((String)deviceCodeCheckTwo.get("machineCode"));
		}
		lubrication.setMachineName((String)param.get("machineName_textField"));
		//获取保洁单位
		lubrication.setLubricateDeptName((String)param.get("lubricateDeptName_textField"));
		//获取负责人
		lubrication.setLubricateManageName((String)param.get("lubricateManageName_textField"));
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 如果参数为空
		if (StringUtils.isBlank(lubrication.getId())) {
			lubrication.setId(UUID.randomUUID().toString());
			lubrication.setLubricateNo(createLubricateNo());
			lubrication.setRecCreator((String)staffByUserId.get("name"));
			lubrication.setRecCreateTime(DateUtils.curDateTimeStr19());
//			htgl07.setBillMaker((String)staffByUserId.get("name"));
//			htgl07.setBillMakeTime(new Timestamp(System.currentTimeMillis()));
			// 保存项目
			dao.insert("DFRH01.insert",lubrication);
			//保存设备润滑信息
			LubricationInformation(htbObj,lubrication.getLubricateNo());
			//保存设备润滑执行人 获取lubrication的保洁单号
			LubricationExecutor(hteObj, lubrication.getLubricateNo());
		} else {
			lubrication.setRecRevisor(UserSession.getUser().getUsername());
			lubrication.setRecReviseTime(DateUtils.curDateTimeStr19());
			// 更新项目
			dao.update("DFRH01.update", lubrication);
			//更新润滑信息
			LubricationInformation(htbObj,lubrication.getLubricateNo());
			//更新润滑执行人
			LubricationExecutor(hteObj, lubrication.getLubricateNo());
		}
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
     * name 联系人
     * number 联系电话
     * deptNum 科室编码
     */
	public EiInfo queryPerson(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo, "DFBJ0101.queryPersonList", "DFBJ0101.queryPersonCount", "resultE", new DfLubricateExecutor().eiMetadata);
	}

	/**
     * @Title: createLubricateNo
     * @Description: 生成润滑单号
     * 通过获取当前时间
     * 判断今天是否存在润滑单号，不存在就返回新的润滑单号
     * 存在润滑单号，对润滑单号进行累加
     * @param
     * @return: String
     * String 润滑单号
     */
	private String createLubricateNo() {
	    // 加锁
		synchronized (dao) {
		    // 调用时间接口
			String date = DateUtils.curDateStr8();
			// 调用查询方法
			List<String> list = dao.query("DFRH0101.createLubricateNo", "RH" + date);
			// 如果结果为空
			if (list == null || list.size() == 0 || list.get(0) == null) {
			    // 返回合同号
				return "RH" + date + "0001";
			} else {
			    // 获取最大合同号
				String maxNo = list.get(0);
				// 对最大合同号进行拆分
				maxNo = maxNo.substring(2);
				// 返回合同号
				return "RH" + (Long.parseLong(maxNo) + 1L) + "";
			}
		}

	}


	/**
	 * @Title: LubricationInformation
	 * @Description: 保存润滑信息
	 *润滑信息
	 * @param htbObj
	 * id 主键
	 * lubricateSpot 润滑位置
	 * fillOil 注油量
	 * wasteOil 费油排量
	 * oilType 油脂类型
	 * oilWaterContent 油脂含水量
	 * oilAcidContent 油脂含酸量
	 * eachElementContent 各元素含量
	 * @return: EiInfo
	 */
	private void LubricationInformation(Object htbObj, String id) {
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 1.如果入参不为空
		if (htbObj != null) {
			// 参数赋值
			list = (List<Map<String, Object>>) htbObj;
			// 2.调用删除方法
			dao.delete("DFRH01b.delete", id);
		}
		int i = 0;
		// 3.增强循环
		for (Map<String, Object> map : list) {
			// 实例化实体
			DfLubricateDetail att = new DfLubricateDetail();
			// 实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setLubricateNo(id);
			// 调用插入方法
			dao.insert("DFRH01b.insert", att);
			i++;
		}
	}

	
	/**
	 * @Title: LubricationExecutor
	 * @Description: 保存润滑执行人
	 * @param hteObj
	 * id 主键
	 * lubricateNo 润滑单号
	 * name 联系人
	 * number 联系电话
	 * deptName 科室名称
	 * @return: void
	 */
	private void LubricationExecutor(Object hteObj, String id) {
	    // 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果入参不为空
		if (hteObj != null) {
		    // 参数赋值
			list = (List<Map<String, Object>>) hteObj;
			// 调用删除方法
			dao.delete("DFRH01e.delete", id);
		}
		// 增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			DfLubricateExecutor att = new DfLubricateExecutor();
			// 实体转换为参数
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setLubricateNo(id);
			att.setRecCreator(UserSession.getUser().getUsername());
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 调用插入方法
			dao.insert("DFRH01e.insert", att);
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
