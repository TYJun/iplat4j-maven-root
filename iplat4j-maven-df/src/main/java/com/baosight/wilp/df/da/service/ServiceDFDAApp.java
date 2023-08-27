package com.baosight.wilp.df.da.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.domain.DfClean;
import com.baosight.wilp.df.common.domain.DfConstant;
import com.baosight.wilp.df.common.domain.DfLubricate;
import com.baosight.wilp.df.common.util.DFUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 设备App接口/看板服务类
 * All rights Reserved, Designed By www.bonawise.com
 * @author fangjian
 * @version V5.0.3
 * @Title: ServiceDFDAApp.java
 * @ClassName: ServiceDFDAApp
 * @Package com.baosight.wilp.df.da.service
 * @Description: 设备App接口/看板服务类
 * @date: 2021年11月18日 15:10
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDFDAApp extends ServiceBase {

    /**
     * 判断是否存在维修模块
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: isExistMtModule
     * @Description: 判断是否存在维修模块
     **/
    public EiInfo isExistMtModule(EiInfo inInfo) {
        boolean existModule = DFUtils.isExistModule(DfConstant.MODULE_CODE_MT);
        inInfo.set("isExist", existModule);
        return inInfo;
    }

    /**
     * 获取所有设备信息
     *
     * @param inInfo inInfo
     *               machineName: 设备名称
     *               machineId ： 设备ID
     *               spotId : 地点ID
     *               page：页码
     *               size:每页数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * id ： 设备id
     * machineCode : 设备编码
     * machineName : 设备名称
     * models : 规格型号
     * machineTypeId : 设备分类
     * status : 设备状态
     * needScan : 是否扫二维码
     * buyTime : 购买日期
     * fixedTime : 安装日期
     * useTime : 使用日期
     * spotNum : 地点编码
     * spotName : 地点名称
     * building : 楼
     * floor : 层
     * warrantyDate : 质保到期日
     * makerBrand : 品牌
     * supplierName : 供应商名称
     * supplierId : 供应商id
     * manufacturerName : 生产单位
     * maintUnit : 保养单位
     * lastMaintainTime : 上次保养日期
     * maintainRound : 保养周期
     * workNo : 人员工号
     * name : 人员姓名
     * deptNum : 科室编码
     * deptName : 科室名称
     * matNum : 物资编码
     * matName : 物资名称
     * outFactoryNo : 出厂编号
     * useLimit : 折旧年限
     * useFor : 用途
     * machineFolderId : 档案盒号
     * buyMode : 购置方式
     * assetPrice : 资产价格(元)
     * memo : 备注
     * @throws
     * @Title: queryDeviceList
     * @Description: 获取所有设备信息
     **/
    public EiInfo queryDeviceList(EiInfo inInfo) {
        //参数处理
        Map<String, Object> paramter = CommonUtils.buildParamter(inInfo, Arrays.asList("machineName", "machineId", "spotId"));
        //数据查询
        List<Map<String, Object>> list = dao.query("DFDA01.query", paramter);
        if (list != null && list.size() > 0) {
            inInfo.set("list", list);
        } else {
            inInfo.set("list", new ArrayList<>());
        }
        return inInfo;
    }

    /**
     * 获取所有设备信息
     *
     * @param inInfo inInfo
     *               machineName: 设备名称
     *               machineId ： 设备ID
     *               spotId : 地点ID
     *               page：页码
     *               size:每页数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * id ： 设备id
     * machineCode : 设备编码
     *
     * @throws
     * @Title: queryCleaningLedger
     * @Description: 获取所有保洁设备信息
     * */

    public EiInfo queryCleaningLedger(EiInfo inInfo) {
        //参数处理
        Map<String, Object> paramter = CommonUtils.buildParamter(inInfo, Arrays.asList("machineName", "machineId", "spotId"));
        //数据查询
        List<Map<String, Object>> list = dao.query("DFBJ01.query", paramter);
        if (list != null && list.size() > 0) {
            inInfo.set("list", list);
        } else {
            inInfo.set("list", new ArrayList<>());
        }
        return inInfo;
    }

    /**
     * 获取所有设备信息
     *
     * @param inInfo inInfo
     *               machineName: 设备名称
     *               machineId ： 设备ID
     *               spotId : 地点ID
     *               page：页码
     *               size:每页数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * id ： 设备id
     * machineCode : 设备编码
     *
     * @throws
     * @Title: queryLubricateLedger
     * @Description: 获取所有润滑设备信息
     * */
    public EiInfo queryLubricateLedger(EiInfo inInfo) {
        //参数处理
        Map<String, Object> paramter = CommonUtils.buildParamter(inInfo, Arrays.asList("machineName", "machineId", "spotId"));
        //数据查询
        List<Map<String, Object>> list = dao.query("DFRH01.query", paramter);
        if (list != null && list.size() > 0) {
            inInfo.set("list", list);
        } else {
            inInfo.set("list", new ArrayList<>());
        }
        return inInfo;
    }
    
    /**
     * 获取指定设备信息
     *
     * @param inInfo inInfo
     *               machineId : 设备Id
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * id ： 设备id
     * machineCode : 设备编码
     * machineName : 设备名称
     * models : 规格型号
     * machineTypeId : 设备分类
     * status : 设备状态
     * needScan : 是否扫二维码
     * buyTime : 购买日期
     * fixedTime : 安装日期
     * useTime : 使用日期
     * spotNum : 地点编码
     * spotName : 地点名称
     * building : 楼
     * floor : 层
     * warrantyDate : 质保到期日
     * makerBrand : 品牌
     * supplierName : 供应商名称
     * supplierId : 供应商id
     * manufacturerName : 生产单位
     * maintUnit : 保养单位
     * lastMaintainTime : 上次保养日期
     * maintainRound : 保养周期
     * workNo : 人员工号
     * name : 人员姓名
     * deptNum : 科室编码
     * deptName : 科室名称
     * matNum : 物资编码
     * matName : 物资名称
     * outFactoryNo : 出厂编号
     * useLimit : 折旧年限
     * useFor : 用途
     * machineFolderId : 档案盒号
     * buyMode : 购置方式
     * assetPrice : 资产价格(元)
     * memo : 备注
     * @throws
     * @Title: queryDeviceInfo
     * @Description: 获取指定设备信息
     **/
    public EiInfo queryDeviceInfo(EiInfo inInfo) {
        //获取参数
        String machineId = inInfo.getString("machineId");
        //数据查询
        List<Map<String, String>> list = dao.query("DFDA01.queryId", machineId);
        if (CollectionUtils.isEmpty(list)) {
            inInfo.set("device", new HashMap<>(4));
        } else {
            inInfo.set("device", list.get(0));
        }
        return inInfo;
    }

    /**
     * 设备报修
     *
     * @param inInfo inInfo
     *               reqStaffId : 报修人工号
     *               reqStaffName: 报修人姓名
     *               reqTelNum ： 报修电话
     *               reqDeptNum ： 报修科室编码
     *               reqDeptName ：报修科室名称
     *               deviceNum ：设备编码
     *               deviceName ： 设备名称
     *               itemName ： 维修事项（故障描述）
     *               building ：楼
     *               floor ： 层
     *               reqSpotNum：报修地点编码（设备地点编码）
     *               reqSpotName：报修地点名称（楼-层-设备地点名称）
     *               impFlag：是否紧急(N：不紧急，Y:紧急)
     *               comments ： 备注
     *               picList: 图片集合
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: deviceRepair
     * @Description: 设备报修
     **/
    public EiInfo deviceRepair(EiInfo inInfo) {
        //设置固定参数
        inInfo.set("itemTypeName", "设备报修");
        inInfo.set("statusCode", "01");
        inInfo.set("workNo", inInfo.getString("reqStaffId"));
        //调用维修工单登记接口
        EiInfo outInfo = DFUtils.invoke(inInfo, "MTRE0101", "insert", null, null);
        //处理维修工单登记接口返回,调用成功，返回成功信息；调用失败，返回失败信息
        if (outInfo.getStatus() == -1) {
            inInfo.setMsg("报修失败");
            inInfo.setStatus(-1);
        } else {
            inInfo.setMsg("报修成功");
        }

        return inInfo;
    }

    /**
     * 设备保洁
     * @param inInfo inInfo
     *               cleanNo : 保洁单号
     *               machineCode: 设备编码
     *               machineName ： 设备名称
     *               fixedPlace ： 安装地点
     *               cleanDeptName ：负责单位
     *               deptManageName ：负责人
     *               cleanDate ： 保洁时间
     *               remark ：作业说明
     *               machineId : 设备ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: saveDeviceCheck
     * @Description: 设备保洁登记
     **/

    public EiInfo saveDeviceCheck(EiInfo inInfo) {
        HashMap<Object, Object> map = new HashMap<>();
        String cleanNo = createCleanNo();
        String machineCode = inInfo.getAttr().get("machineCode").toString();
        String machineName = inInfo.getAttr().get("machineName").toString();
        String fixedPlace = inInfo.getAttr().get("fixedPlace").toString();
        String cleanDeptName = inInfo.getAttr().get("cleanDeptName").toString();
        String deptManageName = inInfo.getAttr().get("cleanManageName").toString();
        String cleanDate = inInfo.getAttr().get("cleanDate").toString();
        String remark = inInfo.getAttr().get("remark").toString();
        map.put("machineCode",machineCode);
        map.put("machineName", machineName);
        map.put("fixedPlace", fixedPlace);
        map.put("cleanDeptName", cleanDeptName);
        map.put("deptManageName", deptManageName);
        map.put("cleanDate", cleanDate);
        map.put("remark", remark);
        inInfo.setBlocks(map);
        DfClean deviceClean = new DfClean();
        deviceClean.fromMap(map);//fromMap实体类
        //获得设备Id
        List<Map<String, ?>> deviceMachineCode = dao.query("DFDA02.deviceMachineCode",map);
        Map deciceCode = deviceMachineCode.get(0);
        deviceClean.setMachineId((String) deciceCode.get("machineId"));
        //htgl09.setBillNo((String)map.get("billNo_textField"));
        deviceClean.setRecCreator((String) map.get("recCreator_textField"));
        deviceClean.setRecCreateTime((String) map.get("recCreateTime_textField"));
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        // 如果参数为空
        if (StringUtils.isBlank(deviceClean.getId())) {
            deviceClean.setId(UUID.randomUUID().toString());
            deviceClean.setCleanNo(cleanNo);
            //htgl09.setRecCreateName((String)staffByUserId.get("name"));
            deviceClean.setRecCreateTime(DateUtils.curDateTimeStr19());
            //htgl09.setStatusCode("0");
            // 保存项目
            dao.insert("DFBJ01.insert", deviceClean);
        } else {
            deviceClean.setRecRevisor(UserSession.getUser().getUsername());
            deviceClean.setRecReviseTime(DateUtils.curDateTimeStr19());
            // 更新项目
            dao.update("CPDJ01.update", deviceClean);
        }
        //页面返回
        if (inInfo.getStatus() == -1) {
            inInfo.setMsg("登记失败");
            inInfo.setStatus(-1);
        } else {
            inInfo.setMsg("登记成功");
        }
        return inInfo;
        
    }

    /**
     * @Title:  createCleanNo
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
     * 设备润滑
     * @param inInfo inInfo
     *               lubricateNo : 润滑单号
     *               machineCode: 设备编码
     *               machineName ： 设备名称
     *               fixedPlace ： 安装地点
     *               lubricateDeptName ：负责单位
     *               lubricateManageName ：负责人
     *               lubricateDate ： 保洁时间
     *               remark ：作业说明
     *               machineId : 设备ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: saveLubricatCheck
     * @Description: 设备润滑登记
     **/
    public EiInfo saveLubricatCheck(EiInfo inInfo) {
        HashMap<Object, Object> map = new HashMap<>();
        String lubricateNo = createLubricateNo();
        String machineCode = inInfo.getAttr().get("machineCode").toString();
        String machineName = inInfo.getAttr().get("machineName").toString();
        String fixedPlace = inInfo.getAttr().get("fixedPlace").toString();
        String lubricateDeptName = inInfo.getAttr().get("lubricateDeptName").toString();
        String lubricateManageName = inInfo.getAttr().get("lubricateManageName").toString();
        String lubricateDate = inInfo.getAttr().get("lubricateDate").toString();
        String remark = inInfo.getAttr().get("remark").toString();
        map.put("machineCode",machineCode);
        map.put("machineName", machineName);
        map.put("fixedPlace", fixedPlace);
        map.put("lubricateDeptName", lubricateDeptName);
        map.put("lubricateManageName", lubricateManageName);
        map.put("lubricateDate", lubricateDate);
        map.put("remark", remark);
        inInfo.setBlocks(map);
        DfLubricate deviceLubricate = new DfLubricate();
        deviceLubricate.fromMap(map);//fromMap实体类
        //获得设备Id
        List<Map<String, ?>> deviceLubricateCode = dao.query("DFDA02.deviceMachineCode",map);
        Map deviceLubricateCheck = deviceLubricateCode.get(0);
        deviceLubricate.setMachineId((String) deviceLubricateCheck.get("machineId"));
        //htgl09.setBillNo((String)map.get("billNo_textField"));
        deviceLubricate.setRecCreator((String) map.get("recCreator_textField"));
        deviceLubricate.setRecCreateTime((String) map.get("recCreateTime_textField"));
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        // 如果参数为空
        if (StringUtils.isBlank(deviceLubricate.getId())) {
            deviceLubricate.setId(UUID.randomUUID().toString());
            deviceLubricate.setLubricateNo(lubricateNo);
            deviceLubricate.setRecCreateTime(DateUtils.curDateTimeStr19());
            // 保存项目
            dao.insert("DFRH01.insert", deviceLubricate);
        } else {
            deviceLubricate.setRecRevisor(UserSession.getUser().getUsername());
            deviceLubricate.setRecReviseTime(DateUtils.curDateTimeStr19());
            // 更新项目
            dao.update("DFRH01.update", deviceLubricate);
        }
        //页面返回
        if (inInfo.getStatus() == -1) {
            inInfo.setMsg("登记失败");
            inInfo.setStatus(-1);
        } else {
            inInfo.setMsg("登记成功");
        }
        return inInfo;
    }


    /**
     * @Title:  createLubricateNo
     * @Description: 生成润滑单号
     * 通过获取当前时间
     * 判断今天是否存在润滑号，不存在就返回新的润滑号
     * 存在润滑单号，对润滑单号累加
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
                // 获取最大润滑单号
                String maxNo = list.get(0);
                // 对最大润滑单号进行拆分
                maxNo = maxNo.substring(2);
                // 返回润滑单号
                return "RH" + (Long.parseLong(maxNo) + 1L) + "";
            }
        }

    }




}

