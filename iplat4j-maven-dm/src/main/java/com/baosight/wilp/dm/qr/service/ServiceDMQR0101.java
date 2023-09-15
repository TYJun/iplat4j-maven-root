package com.baosight.wilp.dm.qr.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍确认入住子页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMQR0101.java
 * @ClassName: ServiceDMQR0101
 * @Package：com.baosight.wilp.dm.qr.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMQR0101 extends ServiceBase {
    /**
     * 此方法用于宿舍确认入住子页面初始化
     *
     * @Title: initLoad
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

//    /**
//     * 更新操作，更新人员的实际入住时间以及人员的状态
//     * 1、获取当前用户信息.
//     * 2、调用本地服务DMQR0101.updateDormRoomMan更新人员入住/退宿的实际时间以及操作的时间.
//     * 3、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态.
//     * 4、调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
//     * 5、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
//     * 6、返回操作结果.
//     *
//     * @Title: updateOperation
//     * @Param EiInfo
//     * @return: EiInfo
//     */
//    public EiInfo updateOperation(EiInfo inInfo) {
//        /*
//         * 1、获取当前用户信息.
//         */
//        // 获取当前登陆工号
//        String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
//                UserSession.getUser().getUsername():(String)inInfo.get("workNo");
//        Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
//        inInfo.set("operator", loginName);
//        /*
//         * 2、调用本地服务DMQR0101.updateDormRoomMan更新人员入住/退宿的实际时间以及操作的时间.
//         */
//        inInfo.set(EiConstant.serviceName, "DMQR0101");
//        inInfo.set(EiConstant.methodName, "updateDormRoomMan");
//        EiInfo outInfo = XLocalManager.call(inInfo);
//        /*
//         * 3、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态.
//         */
//        inInfo.set("statusCode", "04");
//        inInfo.set(EiConstant.serviceName, "DMRZ01");
//        inInfo.set(EiConstant.methodName, "updateStatusCode");
//        outInfo = XLocalManager.call(inInfo);
//        /*
//         * 4、调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
//         */
//        inInfo.set(EiConstant.serviceName, "DMRZ01");
//        inInfo.set(EiConstant.methodName, "updateLCStatusCode");
//        outInfo = XLocalManager.call(inInfo);
//        /*
//         * 5、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
//         */
//        // 将申请流程插入宿舍操作流程历史表中
//        inInfo.set(EiConstant.serviceName, "DMRZ01");
//        inInfo.set(EiConstant.methodName, "insertLCInfo");
//        outInfo = XLocalManager.call(inInfo);
//        /*
//         * 6、返回操作结果.
//         */
//        outInfo.setMsgKey("200");
//        return outInfo;
//    }


    /**
     * 批量更新操作，更新人员的实际入住时间以及人员的状态
     * 1、获取当前用户信息.
     * 2、调用本地服务DMQR0101.batchUpdateDormRoomMan批量更新人员入住/退宿的实际时间以及操作的时间.
     * 3、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态.
     * 4、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
     * 5、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
     * 6、返回操作结果.
     *
     * @Title: batchUpdateOperation
     * @Param EiInfo
     * @return: EiInfo
     */
    public EiInfo batchUpdateOperation(EiInfo inInfo) {
        /*
         * 1、获取当前用户信息.
         */
        // 获取当前登陆工号
        String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
                UserSession.getUser().getUsername():(String)inInfo.get("workNo");
        Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
        inInfo.set("operator", loginName);
        /*
         * 2、调用本地服务DMQR0101.batchUpdateDormRoomMan批量更新人员入住/退宿的实际时间以及操作的时间.
         */
        inInfo.set(EiConstant.serviceName, "DMQR0101");
        inInfo.set(EiConstant.methodName, "batchUpdateDormRoomMan");
        EiInfo outInfo = XLocalManager.call(inInfo);
        /*
         * 3、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态.
         */
        inInfo.set("statusCode", "04");
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "batchUpdateStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 4、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
         */
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "batchUpdateLCStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 5、调用本地服务DMRZ01.batchInsertLCInfo将申请流程批量插入宿舍操作流程历史表中.
         */
        // 将申请流程插入宿舍操作流程历史表中
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "batchInsertLCInfo");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 6、返回操作结果.
         */
        outInfo.setMsgKey("200");
        outInfo.setMsg("操作成功");
        return outInfo;
    }


    /**
     * 更新宿舍人员绑定关系表的操作.
     * 根据指定id主键更新状态.
     * 1、获取inInfo传来的参数.
     * 2、新建一个map用来存放获取的数据.
     * 3、以map作为参数执行 DMRZ02.updateDormRoomMan 进行实际时间的更新,更新人员入住/退宿的实际时间以及操作的时间.
     * 4、返回一个EiInfo.
     *
     * @Title: updateDormRoomMan
     * @param:  @param inInfo
     *      id : 主键
     *      actualInDate ： 实际入住时间
     *      actualOutDate : 实际退宿时间
     *      lastOperator ： 最后操作人工号
     *      lastOperationName : 最后操作人姓名
     *      lastOperationTime ： 最后操作时间
     *      roomId : 宿舍信息表ID
     *      bedNo ： 床位
     *      keepRoomDays ： 宿舍保留天数
     *      changeRoomStatus : 是否换过宿(0为未换过宿舍，1为换过宿舍)
     *      changeRoomNote ： 调宿备注
     *      applyChangeDate : 申请调宿时间
     *      outRoomStatus ：退宿状态(0为未退宿，1为已退宿)
     *      outRoomNote ： 退宿备注
     *      applyOutDate : 申请退宿时间
     *      checkoutRoomStatus : 是否已经进行退宿检查清单(0为未进行，1已进行)
     *      actualRent ：实际月租
     *      actualManageFee ：实际管理费
     *      evalStatus ： 是否已经进行过评价(0为未评价，1已评价)
     *      evalLevelWy ： 物业管理-评价等级(0-5)
     *      evalLevelRoom : 宿舍情况-评价等级(0-5)
     *      evalContent ： 评价内容
     *
     * @param:  @return
     * @return: EiInfo
     * @throws
     *
     */
    public EiInfo updateDormRoomMan(EiInfo inInfo) {
        /*
         * 1、获取inInfo传来的参数.
         */
        String lastOperator = inInfo.get("operator") == null ? UserSession.getUser().getUsername() : inInfo.getString("operator");        /* 操作人工号*/
        Map<String, Object> operaUserInfo = DMUtils.getUserInfo(lastOperator);
        String lastOperationName =  operaUserInfo== null ? "" : operaUserInfo.get("name").toString(); /*操作人名称*/
        String lastOperationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 操作时间*/
        String id = inInfo.getString("id");
        String roomId = inInfo.getString("roomId");
        String keepRoomDays = inInfo.getString("keepRoomDays");
        String bedNo = inInfo.getString("bedNo");
        String payStatus = inInfo.getString("payStatus");
        String changeRoomStatus = inInfo.getString("changeRoomStatus");
        String changeRoomNote = inInfo.getString("changeRoomNote");
        String applyChangeDate = inInfo.getString("applyChangeDate");
        String outRoomStatus = inInfo.getString("outRoomStatus");
        String outRoomNote = inInfo.getString("outRoomNote");
        String applyOutDate = inInfo.getString("applyOutDate");
        String checkoutRoomStatus = inInfo.getString("checkoutRoomStatus");
        String actualRent = inInfo.getString("actualRent");
        String actualManageFee = inInfo.getString("actualManageFee");
        String annualFee = inInfo.getString("annualFee");
        String evalStatus = inInfo.getString("evalStatus");
        String evalLevelWy = inInfo.getString("evalLevelWy");
        String evalLevelRoom = inInfo.getString("evalLevelRoom");
        String evalContent = inInfo.getString("evalContent");
        String actualInDate = inInfo.getString("actualInDate");
        String actualOutDate = inInfo.getString("actualOutDate");
        /*
         * 2、新建一个map用来存放获取的数据.
         */
        Map<String, String> map = new HashMap<>();

        map.put("id", id);
        map.put("roomId", roomId);
        map.put("keepRoomDays", keepRoomDays);
        map.put("bedNo", bedNo);
        map.put("payStatus", payStatus);
        map.put("changeRoomStatus", changeRoomStatus);
        map.put("changeRoomNote", changeRoomNote);
        map.put("applyChangeDate", applyChangeDate);
        map.put("outRoomStatus", outRoomStatus);
        map.put("outRoomNote", outRoomNote);
        map.put("applyOutDate", applyOutDate);
        map.put("checkoutRoomStatus", checkoutRoomStatus);
        map.put("actualRent", actualRent);
        map.put("actualManageFee", actualManageFee);
        map.put("annualFee", annualFee);
        map.put("evalStatus", evalStatus);
        map.put("evalLevelWy", evalLevelWy);
        map.put("evalLevelRoom", evalLevelRoom);
        map.put("evalContent", evalContent);
        map.put("actualInDate", actualInDate);
        map.put("actualOutDate", actualOutDate);
        map.put("lastOperator", lastOperator);
        map.put("lastOperationName", lastOperationName);
        map.put("lastOperationTime", lastOperationTime);
        /*
         * 3、以map作为参数执行 DMRZ02.updateDormRoomMan 进行实际时间的更新,更新人员入住/退宿的实际时间以及操作的时间.
         */
        dao.update("DMRZ02.updateDormRoomMan", map);
        /*
         * 4、返回一个EiInfo.
         */
        EiInfo outInfo = new EiInfo();
        outInfo.set("id", id);
        outInfo.setMsg("操作成功");
        return outInfo;
    }


    /**
     * 批量更新宿舍人员绑定关系表的操作.
     * 根据指定id主键更新状态.
     * 1、获取inInfo传来的参数.
     * 2、对接收的参数 id字符串 进行一个处理.
     * 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
     * 3、新建一个map用来存放获取的数据.
     * 4、以map作为参数执行 DMRZ02.batchUpdateDormRoomMan 进行实际时间的批量更新,更新人员入住/退宿的实际时间以及操作的时间.
     * 5、返回一个EiInfo.
     *
     * @Title: batchUpdateDormRoomMan
     * @param:  @param inInfo
     *      id : 主键
     *      actualInDate ： 实际入住时间
     *      actualOutDate : 实际退宿时间
     *      lastOperator ： 最后操作人工号
     *      lastOperationName : 最后操作人姓名
     *      lastOperationTime ： 最后操作时间
     *      roomId : 宿舍信息表ID
     *      bedNo ： 床位
     *      keepRoomDays ： 宿舍保留天数
     *      changeRoomStatus : 是否换过宿(0为未换过宿舍，1为换过宿舍)
     *      changeRoomNote ： 调宿备注
     *      applyChangeDate : 申请调宿时间
     *      outRoomStatus ：退宿状态(0为未退宿，1为已退宿)
     *      outRoomNote ： 退宿备注
     *      applyOutDate : 申请退宿时间
     *      checkoutRoomStatus : 是否已经进行退宿检查清单(0为未进行，1已进行)
     *      actualRent ：实际月租
     *      actualManageFee ：实际管理费
     *      evalStatus ： 是否已经进行过评价(0为未评价，1已评价)
     *      evalLevelWy ： 物业管理-评价等级(0-5)
     *      evalLevelRoom : 宿舍情况-评价等级(0-5)
     *      evalContent ： 评价内容
     *
     * @param:  @return
     * @return: EiInfo
     * @throws
     *
     */
    public EiInfo batchUpdateDormRoomMan(EiInfo inInfo) {
        /*
         * 1、获取inInfo传来的参数.
         */
        String lastOperator = inInfo.get("operator") == null ? UserSession.getUser().getUsername() : inInfo.getString("operator");        /* 操作人工号*/
        Map<String, Object> operaUserInfo = DMUtils.getUserInfo(lastOperator);
        String lastOperationName =  operaUserInfo== null ? "" : operaUserInfo.get("name").toString(); /*操作人名称*/
        String lastOperationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 操作时间*/
        String roomId = inInfo.getString("roomId");
        String keepRoomDays = inInfo.getString("keepRoomDays");
        String bedNo = inInfo.getString("bedNo");
        String payStatus = inInfo.getString("payStatus");
        String changeRoomStatus = inInfo.getString("changeRoomStatus");
        String changeRoomNote = inInfo.getString("changeRoomNote");
        String applyChangeDate = inInfo.getString("applyChangeDate");
        String outRoomStatus = inInfo.getString("outRoomStatus");
        String outRoomNote = inInfo.getString("outRoomNote");
        String applyOutDate = inInfo.getString("applyOutDate");
        String checkoutRoomStatus = inInfo.getString("checkoutRoomStatus");
        String actualRent = inInfo.getString("actualRent");
        String actualManageFee = inInfo.getString("actualManageFee");
        String annualFee = inInfo.getString("annualFee");
        String evalStatus = inInfo.getString("evalStatus");
        String evalLevelWy = inInfo.getString("evalLevelWy");
        String evalLevelRoom = inInfo.getString("evalLevelRoom");
        String evalContent = inInfo.getString("evalContent");
        String actualInDate = inInfo.getString("actualInDate");
        String actualOutDate = inInfo.getString("actualOutDate");
        /*
         * 2、对接收的参数 id字符串 进行一个处理.
         *  接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
         */
        // 先实例化 idList。
        List<Map<String, String>> idList = new LinkedList<>();
        // 获取参数的值。
        String id = inInfo.getString("idList");
        // 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作。
        if (StringUtils.isNotBlank(id) && id.split(",").length > 1) {
            // 以一个数组去存分割后的字符串。
            String[] idArray = id.split(",");
            // 遍历该数组的长度。
            for (int i = 0; i < idArray.length; i++) {
                // 实例化一个Map<String,String>类型的idInfo，用来接收拆出来的id。
                Map<String, String> idInfo = new HashMap<>();
                idInfo.put("id", idArray[i]);
                // 将接收到数据的map添加到idList列表中。
                idList.add(idInfo);
            }
            // 处理lenght<1，即当获取的值为一个值的情况。
        }else if(StringUtils.isNotBlank(id)){
            // 实例化一个Map<String,String>类型的idInfo，用来接收单独的id。
            Map<String, String> idInfo = new HashMap<>();
            idInfo.put("id", id);
            // 将接收到数据的map添加到idList列表中。
            idList.add(idInfo);
        }
        /*
         * 3、新建一个map用来存放获取的数据.
         */
        Map<String, Object> map = new HashMap<>();
        map.put("idList", idList);
        map.put("roomId", roomId);
        map.put("keepRoomDays", keepRoomDays);
        map.put("bedNo", bedNo);
        map.put("payStatus", payStatus);
        map.put("changeRoomStatus", changeRoomStatus);
        map.put("changeRoomNote", changeRoomNote);
        map.put("applyChangeDate", applyChangeDate);
        map.put("outRoomStatus", outRoomStatus);
        map.put("outRoomNote", outRoomNote);
        map.put("applyOutDate", applyOutDate);
        map.put("checkoutRoomStatus", checkoutRoomStatus);
        map.put("actualRent", actualRent);
        map.put("actualManageFee", actualManageFee);
        map.put("annualFee", annualFee);
        map.put("evalStatus", evalStatus);
        map.put("evalLevelWy", evalLevelWy);
        map.put("evalLevelRoom", evalLevelRoom);
        map.put("evalContent", evalContent);
        map.put("actualInDate", actualInDate);
        map.put("actualOutDate", actualOutDate);
        map.put("lastOperator", lastOperator);
        map.put("lastOperationName", lastOperationName);
        map.put("lastOperationTime", lastOperationTime);
        /*
         * 4、以map作为参数执行 DMRZ02.batchUpdateDormRoomMan 进行实际时间的批量更新,更新人员入住/退宿的实际时间以及操作的时间.
         */
        dao.update("DMRZ02.batchUpdateDormRoomMan", map);
        /*
         * 5、返回一个EiInfo.
         */
        EiInfo outInfo = new EiInfo();
        outInfo.set("idList", idList);
        return outInfo;
    }



}
