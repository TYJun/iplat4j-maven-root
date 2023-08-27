package com.baosight.wilp.dm.xs.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 宿舍换宿审核页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMHS02.java
 * @ClassName: ServiceDMHS02
 * @Package：com.baosight.wilp.dm.hs.service
 * @author: fangzekai
 * @date: 2022年02月09日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXSHS02 extends ServiceBase {
    /**
     * 页面加载
     * 查询宿舍换宿申请信息
     * @param inInfo
     * @return
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 查询宿舍换宿舍审核信息
     * 1、判断账号是否存在问题.
     * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
     * 3、调用本地DMQR01.queryQRInfoList方法查询宿舍换宿申请信息
     * @param inInfo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public EiInfo query(EiInfo inInfo) {
        /*
         * 1、判断账号是否存在问题
         */
        Map<String, Object> userInfo = DMUtils.getUserInfo(null);
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        /*
         * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
         */
        String role = (String) userInfo.get("role");
        // 获取前端的人员大类值（manNature）
        String manNature = inInfo.getString("manNature");
        if(role.contains("ADMIN")){
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null){
                manNature = "学生";
            }
            role = "DMADMIN";
        }else if(role.contains("DMSPR_XSSS")){
            // 为学生审批人时，查询属性隐藏，只查为学生的相关信息。
            manNature = "学生";
            role = "DMSPR_XSSS";
        }else if(role.contains("DMSPR_ZGSS")){
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null){
                manNature = "学生";
            }
            role = "DMSPR_ZGSS";
        }else {
            role = "DMZSR";
        }
        inInfo.set("role",role);
        inInfo.set("manNature", manNature);
        inInfo.set("statusCode","10");
        /*
         * 3、调用本地DMQR01.queryQRInfoList方法查询宿舍换宿申请信息
         */
        inInfo.set(EiConstant.serviceName, "DMQR01");
        inInfo.set(EiConstant.methodName, "queryQRInfoList");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }

    /**
     * 更新操作，通过员工的换宿申请
     * 1、获取当前用户信息.
     * 2、调用本地服务DMHS02.insertHistoryTable将换宿前的相应房间编号以及床位、换宿申请时间、换宿备注插入到人员换宿历史表中进行一个记录。
     * 3、用户换房审核通过之后，对应的宿舍床位数+1
     * 4、调用DMRZ02.updateDormRoomMan 将用户的原宿舍id取消绑定，同时取消床位号，并更新其换宿状态和操作相关。
     * 4、调用本地服务DMQR0101.updateDormRoomMan将用户的原宿舍id取消绑定，同时取消床位号，并更新其换宿状态和操作相关。
     * 5、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态，是状态进入待分配状态.
     * 6、调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
     * 7、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
     * 8、返回操作结果.
     *
     * @Title: update
     * @Param EiInfo
     * @return: EiInfo
     */
    public EiInfo update(EiInfo inInfo) {
        /*
         * 1、获取当前用户信息.
         */
        // 获取当前登陆工号
        String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
                UserSession.getUser().getUsername():(String)inInfo.get("workNo");
        Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
        inInfo.set("operator", loginName);
        String drmId = inInfo.getString("id");
        inInfo.set("drmId", drmId);
        /*
         * 2、调用本地服务DMHS02.insertHistoryTable将换宿前的相应房间编号以及床位、换宿申请时间、换宿备注插入到人员换宿历史表中进行一个记录。
         */
        inInfo.set(EiConstant.serviceName, "DMHS02");
        inInfo.set(EiConstant.methodName, "insertHistoryTable");
        EiInfo outInfo = XLocalManager.call(inInfo);
        /*
         * 3、用户换房审核通过之后，对应的宿舍床位数+1
         */
        inInfo.set(EiConstant.serviceName, "DMXX01");
        inInfo.set(EiConstant.methodName, "increaseDormBedNum");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 4、调用本地服务DMQR0101.updateDormRoomMan将用户的原宿舍id取消绑定，同时取消床位号，并更新其换宿状态和操作相关。
         */
        inInfo.set("changeRoomStatus", "1");
        inInfo.set("payStatus", "0");
        inInfo.set("roomId", "");
        inInfo.set("bedNo", "");
        inInfo.set("actualInDate", "");
        inInfo.set("evalStatus", "0");
        inInfo.set(EiConstant.serviceName, "DMQR0101");
        inInfo.set(EiConstant.methodName, "updateDormRoomMan");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 5、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态.
         */
        inInfo.set("statusCode", "01");
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "updateStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 6、调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
         */
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "updateLCStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 7、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
         */
        // 将申请流程插入宿舍操作流程历史表中
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "insertLCInfo");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 8、返回操作结果.
         */
        outInfo.setMsgKey("200");
        return outInfo;
    }


    /**
     * 批量更新操作，通过员工的换宿申请
     * 1、获取当前用户信息.
     * 2、调用本地服务DMHS02.batchinsertHistoryTable将换宿前的相应房间编号以及床位、换宿申请时间、换宿备注批量插入到人员换宿历史表中进行一个记录。
     * 3、调用本地服务增加宿舍剩余床位操作.
     * 4、调用本地服务DMQR0101.batchUpdateDormRoomMan批量将用户的原宿舍id取消绑定，同时取消床位号，并更新其换宿状态和操作相关。
     * 5、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态，进入待分配状态。
     * 6、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
     * 7、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
     * 8、返回操作结果.
     *
     * @Title: batchUpdate
     * @Param EiInfo
     * @return: EiInfo
     */
    public EiInfo batchUpdate(EiInfo inInfo) {
        /*
         * 1、获取当前用户信息.
         */
        // 获取当前登陆工号
        String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
                UserSession.getUser().getUsername():(String)inInfo.get("workNo");
        Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
        inInfo.set("operator", loginName);
        //  获取前端传来的选中的row列表。
        List<Map<String, Object>> rowList = (List<Map<String, Object>>) inInfo.get("row");
        /*
         * 2、调用本地服务DMHS02.batchinsertHistoryTable将换宿前的相应房间编号以及床位、换宿申请时间、换宿备注批量插入到人员换宿历史表中进行一个记录。
         */
        inInfo.set(EiConstant.serviceName, "DMHS02");
        inInfo.set(EiConstant.methodName, "batchinsertHistoryTable");
        EiInfo outInfo = XLocalManager.call(inInfo);
        /*
         * 3、调用本地服务增加宿舍剩余床位操作.
         */
        String roomIdList = "";
        for (int i = 0; i < rowList.size(); i++) {
            // 当遍历最后一个值时，省略最后的"，"
            if (i == rowList.size() - 1) {
                roomIdList += rowList.get(i).get("roomId");
            } else {
                roomIdList += (String)rowList.get(i).get("roomId") + ',';
            }
        }
        inInfo.set("roomIdList",roomIdList);
        inInfo.set(EiConstant.serviceName, "DMXX01");
        inInfo.set(EiConstant.methodName, "batchIncreaseDormBedNum");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 4、调用本地服务DMQR0101.batchUpdateDormRoomMan批量将用户的原宿舍id取消绑定，同时取消床位号，并更新其换宿状态和操作相关。
         */
        String idList = "";
        for (int i = 0; i < rowList.size(); i++) {
            // 当遍历最后一个值时，省略最后的"，"
            if (i == rowList.size() - 1) {
                idList += rowList.get(i).get("drmId");
            } else {
                idList += (String)rowList.get(i).get("drmId") + ',';
            }
        }
        inInfo.set("idList",idList);
        inInfo.set("changeRoomStatus", "1");
        inInfo.set("payStatus", "0");
        inInfo.set("roomId", "");
        inInfo.set("bedNo", "");
        inInfo.set("actualInDate", "");
        inInfo.set("evalStatus", "0");
        inInfo.set(EiConstant.serviceName, "DMQR0101");
        inInfo.set(EiConstant.methodName, "batchUpdateDormRoomMan");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 5、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态.
         */
        String manIdList = "";
        for (int i = 0; i < rowList.size(); i++) {
            // 当遍历最后一个值时，省略最后的"，"
            if (i == rowList.size() - 1) {
                manIdList += rowList.get(i).get("manId");
            } else {
                manIdList += (String)rowList.get(i).get("manId") + ',';
            }
        }
        inInfo.set("manIdList",manIdList);
        inInfo.set("statusCode", "01");
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "batchUpdateStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 6、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
         */
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "batchUpdateLCStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 7、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
         */
        // 将申请流程插入宿舍操作流程历史表中
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "batchInsertLCInfo");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 8、返回操作结果.
         */
        outInfo.setMsgKey("200");
        return outInfo;
    }

    /**
     * 宿舍信息表保存接口.
     * 对参数处理，然后保存到数据库.
     * 1、获取inInfo传来的参数.
     * 2、新建一个map用来存放获取的数据.
     * 3、以map作为参数执行 DMXX0101.insertDMInfoTable 进行数据的插入，插入宿舍信息表.
     * 4、返回一个EiInfo.
     *
     * @Title: insertHistoryTable
     * @param:  @param inInfo
     *      id ：主键
     *      drmId  : 宿舍人员绑定关系表ID
     *      roomId  : 换宿前的宿舍id
     *      changeRoomNote  : 申请换宿时备注
     *      applyChangeDate : 申请换宿时间
     * @return: EiInfo
     * @throws
     */
    public EiInfo insertHistoryTable(EiInfo inInfo) {
        /*
         * 1、获取inInfo传来的参数.
         */
        String id = UUID.randomUUID().toString();   /*主键*/
        String drmId = inInfo.get("drmId") == null ? "" : inInfo.getString("drmId");     /* 宿舍人员绑定关系表ID*/
        String roomId = inInfo.get("roomId") == null ? "" : inInfo.getString("roomId");     /* 换宿前的宿舍id */
        String bedNo = inInfo.get("bedNo") == null ? "" : inInfo.getString("bedNo");     /* 床位号*/
        String actualInDate = inInfo.get("actualInDate") == null ? "" : inInfo.getString("actualInDate");     /* 实际入住时间*/
        String changeRoomNote = inInfo.get("changeRoomNote") == null ? "" : inInfo.getString("changeRoomNote");     /* 申请换宿时备注*/
        String applyChangeDate = inInfo.get("applyChangeDate") == null ? "" : inInfo.getString("applyChangeDate");     /* 申请换宿时间 */
        /*
         * 2、新建一个map用来存放获取的数据.
         */
        Map<String, String> map = new HashMap<>();
        //map存放获取到的数据数据
        map.put("id",id);
        map.put("drmId",drmId);
        map.put("roomId",roomId);
        map.put("bedNo",bedNo);
        map.put("actualInDate",actualInDate);
        map.put("changeRoomNote",changeRoomNote);
        map.put("applyChangeDate",applyChangeDate);
        /*
         * 3、以map作为参数执行 DMXX0101.insertDMInfoTable 进行数据的插入，插入宿舍信息表.
         */
        dao.insert("DMHS02.insertHistoryTable", map);
        /*
         * 4、返回一个EiInfo.
         */
        EiInfo outInfo = new EiInfo();
        outInfo.set("id", id);
        return outInfo;
    }


    /**
     * 宿舍信息表批量保存接口.
     * 对参数处理，然后保存到数据库.
     * 1、获取前端传来的row列表。
     * 2、以rowList作为参数执行 DMHS02.batchInsertHistoryTable 将信息批量插入到宿舍换宿历史表 .
     * 3、返回一个EiInfo.
     *
     * @Title: batchinsertHistoryTable
     * @param:  @param inInfo
     *      row : 前端传来的List对象列表
     *      id ：主键
     *      drmId  : 宿舍人员绑定关系表ID
     *      roomId  : 换宿前的宿舍id
     *      changeRoomNote  : 申请换宿时备注
     *      applyChangeDate : 申请换宿时间
     * @return: EiInfo
     * @throws
     */
    public EiInfo batchinsertHistoryTable(EiInfo inInfo) {
        // 1、获取前端传来的row列表。
        List<Map<String, Object>> rowList = (List<Map<String, Object>>) inInfo.get("row");
        rowList.forEach(row -> {
            // 对其中两个字段进行重新赋值
            row.put("drmId",row.get("id"));
            row.put("id",UUID.randomUUID().toString());
        });
        /*
         * 2、以rowList作为参数执行 DMHS02.batchInsertHistoryTable 将信息批量插入到宿舍换宿历史表 .
         */
        dao.insert("DMHS02.batchInsertHistoryTable", rowList);
        /*
         * 3、返回一个EiInfo.
         */
        EiInfo outInfo = new EiInfo();
        outInfo.setMsgKey("200");
        return outInfo;
    }
}
