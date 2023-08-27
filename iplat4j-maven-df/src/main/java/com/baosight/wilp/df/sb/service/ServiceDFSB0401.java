package com.baosight.wilp.df.sb.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.domain.DfConstant;
import com.baosight.wilp.df.common.util.DFUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * (设备台账子页面)
 * <p>1.初始化查询</p>
 * @Title: ServiceDFSB0401.java
 * @ClassName: ServiceDFSB0401
 * @Package：com.baosight.wilp.df.sb.service
 * @author: 24128
 * @date: 2021年10月25日 下午3:05:51
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFSB0401 extends ServiceBase{

	 /**
     * @Title: initLoad
     * @Description: 初始化查询
     *<p>1.获取特种设备的id</p>
     *<p>2.通过设备id查询出设备基本信息</p>
     *<p>3.特种设备关联巡检查询出巡检实绩和基准信息</p>
     * @param info
     * id : 设备id
     * @return info
     * machineTypeId : 设备类型
     * machineCode : 设备编码
     * machineName : 设备名称
     * statusStatus : 设备状态
     * makerBrand : 品牌
     * models : 规格型号
     * fixedTime : 安装日期
     * fixedPlace : 安装地点
     */
     public EiInfo initLoad(EiInfo info) {
         // 实例化map
         Map<String, Object> map = new HashMap<String, Object>();
         // map设置machineId
         map.put("machineId", info.getAttr().get("machineId").toString());
         // 实例化info
         EiInfo outInfo = new EiInfo();
         map.put("offset", 0);
         map.put("limit", 10);
         // 获取设备基础信息
         List<Map<String, String>> list = dao.query("DFSB04.initDeviceForm", map);
         // info设置设备基础信息
         outInfo.setAttr(list.get(0));
         // 返回
         return outInfo;
     }

    /**
     * @Title: queryDiScheme
     * @Description: 查询巡检基准
     * @param info
     * id : 设备id
     * @return info
     * schemeCode : 计划代码
     * schemeName : 计划名称
     * jobContent : 作业说明
     * statusName : 状态
     * createTime : 创建时间
     * createMan : 创建人
     * modifyTime : 修改时间
     * modifyMan : 修改人
     */
    public EiInfo queryDiScheme(EiInfo info) {
        try{
            //存在巡检模块
            if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DI)){
                //参数处理
                Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "diScheme");
                map.put("machineId",info.getString("machineId"));
                //数据查询
                List<Map<String, Object>> list = dao.query("DFSB04.queryDiSchemeList", map);
                int count = dao.count("DFSB04.queryDiSchemeCount", map);
                return CommonUtils.BuildOutEiInfo(info,"diScheme",null,list,count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * @Title: queryDiTask
     * @Description: 查询巡检任务
     * @param info
     * id : 设备id
     * @return info
     * STATUS : 状态
     * taskCode : 任务单号
     * schemeName : 任务名称
     * machineCode : 设备编码
     * machineName : 设备名称
     * jobContent : 作业说明
     * departName : 责任单位科室
     * managerName : 责任人
     * createTime : 创建时间
     * finishTime : 完成时间
     */
    public EiInfo queryDiTask(EiInfo info) {
        try{
            //存在巡检模块
            if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DI)){
                //参数处理
                Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "diTask");
                map.put("machineId",info.getString("machineId"));
                //数据查询
                List<Map<String, Object>> list = dao.query("DFSB04.queryDiTaskList", map);
                int count = dao.count("DFSB04.queryDiTaskCount", map);
                return CommonUtils.BuildOutEiInfo(info,"diTask",null,list,count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * @Title: queryDkScheme
     * @Description: 查询保养基准
     * @param info
     * id : 设备id
     * @return info
     * schemeCode : 计划代码
     * schemeName : 计划名称
     * jobContent : 作业说明
     * statusName : 状态
     * createTime : 创建时间
     * createMan : 创建人
     * modifyTime : 修改时间
     * modifyMan : 修改人
     */
    public EiInfo queryDkScheme(EiInfo info) {
        try{
            //存在巡检模块
            if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)){
                //参数处理
                Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dkScheme");
                map.put("machineId",info.getString("machineId"));
                //数据查询
                List<Map<String, Object>> list = dao.query("DFSB04.queryDkSchemeList", map);
                int count = dao.count("DFSB04.queryDkSchemeCount", map);
                return CommonUtils.BuildOutEiInfo(info,"dkScheme",null,list,count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * @Title: queryDkTask
     * @Description: 查询保养任务
     * @param info
     * id : 设备id
     * @return info
     * STATUS : 状态
     * taskCode : 任务单号
     * schemeName : 任务名称
     * machineCode : 设备编码
     * machineName : 设备名称
     * jobContent : 作业说明
     * departName : 责任单位科室
     * managerName : 责任人
     * createTime : 创建时间
     * finishTime : 完成时间
     */
    public EiInfo queryDkTask(EiInfo info) {
        try{
            //存在保养模块
            if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)){
                //参数处理
                Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dkTask");
                map.put("machineId",info.getString("machineId"));
                //数据查询
                List<Map<String, Object>> list = dao.query("DFSB04.queryDkTaskList", map);
                int count = dao.count("DFSB04.queryDkTaskCount", map);
                return CommonUtils.BuildOutEiInfo(info,"dkTask",null,list,count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * @Title: queryMtTask
     * @Description: 查询维修任务
     * @param info info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryMtTask(EiInfo info) {
        try{
            //存在维修模块
            if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)){
                //参数处理
                Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "mtTask");
                map.put("machineCode",info.getString("machineCode"));
                //数据查询
                List<Map<String, Object>> list = dao.query("DFSB04.queryMtTaskList", map);
                int count = dao.count("DFSB04.queryMtTaskCount", map);
                return CommonUtils.BuildOutEiInfo(info,"mtTask",null,list,count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
