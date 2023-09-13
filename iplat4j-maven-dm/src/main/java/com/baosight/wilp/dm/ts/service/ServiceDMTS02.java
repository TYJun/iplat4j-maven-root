package com.baosight.wilp.dm.ts.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍退宿审核页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMTS02.java
 * @ClassName: ServiceDMTS02
 * @Package：com.baosight.wilp.dm.ts.service
 * @author: fangzekai
 * @date: 2022年02月09日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMTS02 extends ServiceBase {

    /**
     * 页面初始化加载
     * @Title: initLoad
     * @param inInfo
     * @return
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 宿舍退宿审核查询
     * 逻辑处理
     *   1.将要查询的参数组成数组并调用工具类转换参数
     *   2.获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分
     *   3.调用本地服务DMQR01.queryQRInfoList查询信息
     *
     * @Title: query
     * @param inInfo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public EiInfo query(EiInfo inInfo) {
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
                manNature = "职工";
            }
            role = "DMADMIN";
        }else if(role.contains("DMSPR_XSSS")){
            // 为学生审批人时，查询属性隐藏，只查为学生的相关信息。
            manNature = "学生";
            role = "DMSPR_XSSS";
        }else if(role.contains("DMSPR_ZGSS")){
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null){
                manNature = "职工";
            }
            role = "DMSPR_ZGSS";
        }else {
            role = "DMZSR";
        }
        inInfo.set("role",role);
        inInfo.set("manNature", manNature);
        inInfo.set("statusCode","98");
        inInfo.set(EiConstant.serviceName, "DMQR01");
        inInfo.set(EiConstant.methodName, "queryQRInfoList");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }


    /**
     * 批量更新操作，批量通过员工的退宿申请
     * 1、获取当前用户信息.
     * 2、调用本地服务DMQR0101.updateDormRoomMan 插入实际的退宿同意时间以及退宿状态。
     * 3、用户退宿审核通过之后，对应的宿舍床位数+1
     * 4、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态，使状态进入流程结束状态.
     * 5、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
     * 6、企业微信通知退宿申请申请结果
     * 7、返回操作结果.
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
        /*
         * 2、调用本地服务DMQR0101.updateDormRoomMan 插入实际的退宿同意时间以及退宿状态。
         */
        String actualOutDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 实际退宿时间*/
        inInfo.set("actualOutDate", actualOutDate);
        inInfo.set("outRoomStatus", "1");
        inInfo.set(EiConstant.serviceName, "DMQR0101");
        inInfo.set(EiConstant.methodName, "batchUpdateDormRoomMan");
        EiInfo outInfo = XLocalManager.call(inInfo);
        /*
         * 3、用户退宿审核通过之后，对应的宿舍床位数增加
         */
        inInfo.set(EiConstant.serviceName, "DMXX01");
        inInfo.set(EiConstant.methodName, "batchIncreaseDormBedNum");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 4、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态，使状态进入流程结束状态.
         */
//        inInfo.set("statusCode", "99");
//        inInfo.set(EiConstant.serviceName, "DMRZ01");
//        inInfo.set(EiConstant.methodName, "batchUpdateStatusCode");
//        outInfo = XLocalManager.call(inInfo);
        HashMap<Object, Object> map = new HashMap<>();
        inInfo.set("statusCode", "99");
        map.put("statusCode", "99");
        String statusCode = inInfo.getString("statusCode");
        String manId = inInfo.get("manIdList").toString();
        List<Map<String, String>> manIdList = new LinkedList<>();
        if (StringUtils.isNotBlank(manId) && manId.split(",").length > 1) {
            // 以一个数组去存分割后的字符串。
            String[] manIdArray = manId.split(",");

            // 遍历该数组的长度。
            for (int i = 0; i < manIdArray.length; i++) {
                // 实例化一个Map<String,String>类型的manIdInfo，用来接收拆出来的manId。
                Map<String, String> manIdInfo = new HashMap<>();
                manIdInfo.put("manId", manIdArray[i]);
                // 将接收到数据的map添加到manIdInfo列表中。
                manIdList.add(manIdInfo);
            }
            // 处理lenght<1，即当获取的值为一个值的情况。
        }else if(StringUtils.isNotBlank(manId)){
            // 实例化一个Map<String,String>类型的idInfo，用来接收单独的manId。
            Map<String, String> manIdInfo = new HashMap<>();
            manIdInfo.put("manId", manId);
            // 将接收到数据的map添加到manIdInfo列表中。
            manIdList.add(manIdInfo);
        }
        map.put("manIdList", manIdList);
        map.put("statusCode", statusCode);
        dao.update("DMRZ01.batchUpdateStatusCode", map);
        /*
         * 5、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
         */
        // 将申请流程插入宿舍操作流程历史表中
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "batchInsertLCInfo");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 6、企业微信通知退宿申请结果
         */
        //获取app编码
        String appCode = "AP00002";
        List<String> workNoList =new ArrayList<>();
        List<String> paramList = new ArrayList<>();

        //获取退宿人员工号
        String manNo = inInfo.getString("manNoList");
        // 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作
        if (StringUtils.isNotBlank(manNo) && manNo.split(",").length > 1){
            // 以一个数组去存分割后的字符串。
            String[] manNoArray = manNo.split(",");
            // 遍历该数组的长度。
            for (int i = 0; i < manNoArray.length; i++) {
                // 接收拆出来的manNo
                workNoList.add(manNoArray[i]);

            }// 处理lenght<1，即当获取的值为一个值的情况。
        }else if(StringUtils.isNotBlank(manNo)){
              workNoList.add(manNo);
        }

        //发送的消息
        String smsTemp = "您退宿申请已被审批通过";
        paramList.add(smsTemp);

        //发送企业微信
        BaseDockingUtils.pushWxMsg(workNoList, paramList, "TP00001", appCode);

        /*
         * 7、返回操作结果.
         */
        outInfo.setMsgKey("200");
        outInfo.setMsg("操作成功");
        return outInfo;
    }


    /**
     * 此方法用于获取退宿人员列表.
     *
     * 逻辑处理：
     * 1.获取参数
     * 2.调用sql方法DMTS02.getTSPeopleList查询退宿人员
     *
     * @Title: getTSPeopleList
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    public EiInfo getTSPeopleList(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        Map<String,String> map = new HashMap<>();
        /**
         * 1.获取参数
         */
        String manName = inInfo.getString("manName");
        map.put("manName",manName);
        map.put("statusCode","98");
        /**
         * 2.调用sql方法DMTS02.getTSPeopleList查询退宿人员
         */
        List<Map<String, String>> list = dao.query("DMTS02.getTSPeopleList", map);
        if (CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("未查到数据");
            outInfo.setStatus(-1);
            return outInfo;
        }
        outInfo.setAttr(list.get(0));
        outInfo.setRows("tsPeopleList",list);
        return outInfo;
    }

}
