package com.baosight.wilp.df.sb.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.domain.DfConstant;
import com.baosight.wilp.df.common.util.DFUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 特种设备台账查询页面
 * <p>1.页面初始化</p>
 * <p>2.页面查询</p>
 * @Title: ServiceDFSB04.java
 * @ClassName: ServiceDFSB04
 * @Package：com.baosight.wilp.df.sb.service
 * @author: Gcc
 * @date: 2021年10月25日 上午10:53:10
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFSB04 extends ServiceBase {
    /**
     * 页面初始化
     * @Title: initLoad
     * @param: info
     * machineId : 设备id
     * machineCode :设备编码
     * machineName : 设备名称
     * machineTypeId : 设备类型id
     * spotCode : 安装地址编码
     * fixedPlace : 安装地点
     * models : 规格型号
     * STATUS : 设备状态
     * managerDepartId : 管理科室id
     * useDeaprtId : 使用科室id
     * manageDeptName : 管理科室
     * deptName : 使用科室
     * patrolSchemeNum : 巡检基准数
     * schemeTaskNum : 巡检实绩数
     * @return:
     * machineId : 设备id
     * machineCode :设备编码
     * machineName : 设备名称
     * machineTypeId : 设备类型id
     * spotCode : 安装地址编码
     * fixedPlace : 安装地点
     * models : 规格型号
     * STATUS : 设备状态
     * managerDepartId : 管理科室id
     * useDeaprtId : 使用科室id
     * manageDeptName : 管理科室
     * deptName : 使用科室
     * patrolSchemeNum : 巡检基准数
     * schemeTaskNum : 巡检实绩数
     */
    public EiInfo initLoad(EiInfo info) {
        //调用本地查询方法
        return this.query(info);
    }

    /**
     * <p>1.查询设备台账信息</p>
     * @param :info
     * @Title: query
     * @Description: 查询设备台账
     * machineTypeId : 设备类型
     * machineCode : 设备编码
     * machineName : 设备名称
     * deptName : 使用科室
     * manageDeptName : 设备所属科室
     * status : 设备状态
     * @return info
     * machineId : 设备id
     * machineCode :设备编码
     * machineName : 设备名称
     * machineTypeId : 设备类型id
     * spotCode : 安装地址编码
     * fixedPlace : 安装地点
     * models : 规格型号
     * STATUS : 设备状态
     * managerDepartId : 管理科室id
     * useDeaprtId : 使用科室id
     * manageDeptName : 管理科室
     * deptName : 使用科室
     */
    public EiInfo query(EiInfo info) {
        // 设置数组存储参数
        String[] parameter = {"machineTypeId", "machineCode", "machineName", "statusCode"};
        // 将数组转换为list
        List<String> plist = Arrays.asList(parameter);
        // 调用分页接口构建map
        Map<String, Object> map = CommonUtils.buildParamter(info, "result", plist);
        // 查询设备台账信息集合
        List<Map<String, Object>> list = dao.query("DFSB04.query", map);
        //获取设备信息对应巡检数量、保养数量、维修数量
        for (int i = 0; i < list.size(); i++) {
            list.set(i,getCount(list.get(i)));
        }
        // 查询设备台账数量
        int count = dao.count("DFSB01.count", map);
        // 调用分页接口返回
        return CommonUtils.BuildOutEiInfo(info, null, null, list, count);
    }


    /**
     * 设备信息对应巡检数量、保养数量、维修数量
     * @Title: getCount
     * @Description: 设备信息对应巡检数量、保养数量、维修数量
     * @param pMap pMap : 设备信息
     * @return  Map<String, Object>
     * @throws
     **/
    private Map<String, Object> getCount(Map<String, Object> pMap) {
        //设置初始值
        pMap.put("diSchemaCount", 0);
        pMap.put("diTaskCount", 0);
        pMap.put("dkSchemaCount", 0);
        pMap.put("dkTaskCount", 0);
        pMap.put("mtTaskCount", 0);
        //获取参数
        String machineId = pMap.get("machineId").toString();
        String machineCode = pMap.get("machineCode").toString();
        //存在巡检模块,调用接口查询数据
        if (DFUtils.isExistModule(DfConstant.MODULE_CODE_DI)) {
            pMap.putAll(getDiCount(machineId));
        }
        //存在保养模块,调用接口查询数据
        if (DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)) {
            pMap.putAll(getDkCount(machineId));
        }
        //存在维修模块,调用接口查询数据
        if (DFUtils.isExistModule(DfConstant.MODULE_CODE_MT)) {
            pMap.put("mtTaskCount", getMtCount(machineCode));
        }
        return pMap;
    }

    /**
     * 获取巡检数量
     * @Title: getDiCount
     * @Description: 获取巡检数量
     * @param deviceId deviceId : 设备Id
     * @return java.lang.Integer : 巡检数量
     * @throws
     **/
    private Map<String, Object> getDiCount(String deviceId) {
        Map<String, Object> map = new HashMap<>(4);
        try{
            int diSchemaCount = super.count("DFSB04.queryDiSchemaCount", deviceId);
            int diTaskCount = super.count("DFSB04.queryDiTaskCount", deviceId);
            map.put("diSchemaCount", diSchemaCount);
            map.put("diTaskCount", diTaskCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取保养数量
     * @Title: getDkCount
     * @Description: 获取保养数量
     * @param deviceId deviceId : 设备Id
     * @return java.lang.Integer : 保养数量
     * @throws
     **/
    private Map<String, Object> getDkCount(String deviceId) {
        Map<String, Object> map = new HashMap<>(4);
        try{
            int dkSchemaCount = super.count("DFSB04.queryDkSchemaCount", deviceId);
            int dkTaskCount = super.count("DFSB04.queryDkTaskCount", deviceId);
            map.put("dkSchemaCount", dkSchemaCount);
            map.put("dkTaskCount", dkTaskCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取维修任务数量
     * @Title: getMtCount
     * @Description: 获取维修任务数量
     * @param deviceNum deviceNum : 设备Id
     * @return java.lang.Integer : 维修任务数量
     * @throws
     **/
    private Integer getMtCount(String deviceNum) {
        try{
            return super.count("DFSB04.queryMtCount", deviceNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

