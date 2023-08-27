package com.baosight.wilp.dm.qr.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.dm.common.domain.DMConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍确认入住页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMQR01.java
 * @ClassName: ServiceDMQR01
 * @Package：com.baosight.wilp.dm.qr.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMQR01 extends ServiceBase {
    /**
     * 此方法用于宿舍确认入住页面初始化加载
     *
     * 逻辑处理：
     * 1.调用query方法初始化查询
     *
     * @Title: initLoad
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.set("statusCode","03");
        return query(inInfo);
    }

    /**
     * 此方法用于列表数据查询
     *
     * 逻辑处理：
     * 1.获取前端的人员大类值（manNature）
     * 2.调用本地服务DMQR01.queryQRInfoList()方法进行列表数据查询.
     *
     * @Title: query
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    @SuppressWarnings("unchecked")
    @Override
    public EiInfo query(EiInfo inInfo) {
        Map<String, Object> userInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
        if (userInfo == null || userInfo.isEmpty()) {
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        String role = (String) userInfo.get("role");

        // 1.获取前端的人员大类值（manNature）
        String manNature = inInfo.getString("manNature");
        if (role.contains("ADMIN")) {
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null) {
                manNature = "职工";
            }
            role = "DMADMIN";
        } else if (role.contains("DMSPR_XSSS")) {
            // 为学生审批人时，查询属性隐藏，只查为学生的相关信息。
            manNature = "学生";
            role = "DMSPR_XSSS";
        } else if (role.contains("DMSPR_ZGSS")) {
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null) {
                manNature = "职工";
            }
            role = "DMSPR_ZGSS";
        }
        inInfo.set("role", role);
        inInfo.set("manNature", manNature);
        inInfo.set("workNo", UserSession.getUser().getUsername());
        // 2.调用本地服务DMQR01.queryQRInfoList()方法进行列表数据查询.
        inInfo.set(EiConstant.serviceName, "DMQR01");
        inInfo.set(EiConstant.methodName, "queryQRInfoList");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }


    /**
     * 查询确认入住人员信息
     * 逻辑处理
     *    1.将要查询的参数组成数组并调用工具类转换参数
     *    2.该页面住宿人不可见，故无需兼顾住宿人角色
     *    3.调用DMQR01.queryShouldConfirmList 查询确认入住人员信息
     *
     * @Title: queryQRInfoList
     * @param inInfo
     * @return
     */
    public EiInfo queryQRInfoList(EiInfo inInfo) {
        /*
         * 1、将要查询的参数组成数组并调用工具类转换参数
         */
        String[] param = {"manNo", "manName","deptName","building", "floor","roomNo","statusCode"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        map.put("roomId",inInfo.getString("roomId"));
        // 额外角色参数进行处理
        String role = inInfo.getString("role");
        // 判断人员大类的选择
        List manNatureList = new ArrayList();
        String manNature = inInfo.getString("manNature");
        if (manNature!=null && manNature.equals("学生")){
            String [] XSLX = DMConstant.XSLX;
            manNatureList = Arrays.asList(XSLX);
        }else if (manNature!=null && manNature.equals("职工")){
            String [] ZGLX = DMConstant.ZGLX;
            manNatureList = Arrays.asList(ZGLX);
        }
        // 该页面住宿人不可见，故无需兼顾住宿人角色.
        if(StringUtils.isNotBlank(role) && role.contains("DMADMIN")) {
            map.put("role", role);
            map.put("manNatureList", manNatureList);
        }else if(StringUtils.isNotBlank(role) && role.contains("DMSPR")){
            map.put("role", "DMSPR");
            map.put("manNatureList", manNatureList);
        }
        //查询
        List<Map<String, Object>> list = dao.query("DMQR01.queryShouldConfirmList",map,
                Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
        int count = super.count("DMQR01.countShouldConfirmList",map);
        // 判断是否存在，存在则构建返回对象
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            inInfo.setMsg("没有查询到数据。");
            return inInfo;
        }
    }

    /**
     * 定时任务：当到达宿舍保留时间且员工的住宿状态还没从待入住03变更为流程结束99，
     * 则删除该员工的宿舍绑定关系，回退到待分配的状态。
     * 1.调用DMQR01.queryOverTimeInfo查询到达宿舍保留时间且员工的住宿状态还没从待入住03变更为已入住04的相关信息
     * 2.查询该房间会回退的床位数量，通过roomId回退其的床位数量， + count.
     * 3、以map作为参数
     *   调用DMRZ01.updateLCStatusCode sql更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
     *   执行 DMRZ01.insertDMLCTable 将操作流程信息插入到宿舍操作流程历史表 .
     * 4.调用DMQR01.deleteRoomManDependency批量删除满足的id的宿舍与人员的绑定关系
     * 5.DMQR01.updateStatusCodeBatch调用批量更新人员的状态，从03变为99
     *
     * @Title: timeOutBack
     * @Param EiInfo
     * @return: EiInfo
     */
    public EiInfo timeOutBack(EiInfo inInfo){
        Map<String,Object> map = new HashMap<>();
        EiInfo outInfo = new EiInfo();
        String roomIdList = "";
        /*
         * 1.调用DMQR01.queryOverTimeInfo查询到达宿舍保留时间且员工的住宿状态还没从待入住03变更为已入住04的相关信息
         */
        List<Map<String,String>> listInfo = dao.query("DMQR01.queryOverTimeInfo",null);
        if(listInfo != null && listInfo.size() > 0){
            // 循环遍历
            for (int i = 0; i < listInfo.size(); i++) {
                Map<String,Object> pMap = new HashMap<>();
                String roomId = listInfo.get(i).get("roomId");
                String manId = listInfo.get(i).get("manId");
                // 当遍历最后一个值时，省略最后的"，"
                if (i == listInfo.size() - 1) {
                    roomIdList += roomId;
                } else {
                    roomIdList += roomId + ',';
                }
                /*
                 * 3、以map作为参数
                 * 调用DMRZ01.updateLCStatusCode sql更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
                 * 执行 DMRZ01.insertDMLCTable 将操作流程信息插入到宿舍操作流程历史表 .
                 * 超时直接结束流程！
                 */
                String id = UUID.randomUUID().toString();
//                String statusCode = "01";
                String statusCode = "99";
                List<Map<String, String>> list = dao.query("DMRZ03.queryStatusCodeMeaning", statusCode);
                String statusCodeMeaning = list.get(0).get("codeName");     /* 状态编码 */
                String operator = "overtime";
                String operationName = "超时未入住导致流程完结";
                String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 操作时间*/
                map.put("id",id);
                map.put("manId",manId);
                map.put("statusCode",statusCode);
                map.put("statusCodeMeaning",statusCodeMeaning);
                map.put("isCurrent","1");
                map.put("operator",operator);
                map.put("operationName",operationName);
                map.put("operationTime",operationTime);
                // 调用DMRZ01.updateLCStatusCode sql更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
                dao.update("DMRZ01.updateLCStatusCode", map);
                // 将回退成01状态的流程记录待宿舍流程表中
                dao.insert("DMRZ01.insertDMLCTable", map);
            }
            /*
             * 调用本地服务增加宿舍剩余床位操作.
             */
            inInfo.set("roomIdList",roomIdList);
            inInfo.set(EiConstant.serviceName, "DMXX01");
            inInfo.set(EiConstant.methodName, "batchIncreaseDormBedNum");
            outInfo =XLocalManager.call(inInfo);
            /*
             * 4.调用DMQR01.deleteRoomManDependency批量删除满足的id的宿舍与人员的绑定关系
             */
            dao.delete("DMQR01.deleteRoomManDependency", listInfo);
            /*
             * 5.DMQR01.updateStatusCodeBatch调用批量更新人员的状态，从03变为99
             */
            dao.update("DMQR01.updateStatusCodeBatch", listInfo);
            outInfo.setMsg("定时任务执行成功");
            outInfo.setMsgKey("200");
        } else {
            return inInfo;
        }
        return outInfo;
    }

}
