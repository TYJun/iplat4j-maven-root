package com.baosight.wilp.di.jz.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.di.common.domain.DiConstant;
import com.ibatis.sqlmap.engine.exchange.BaseDataExchange;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 巡检短信发送服务类
 *
 * <p>1.巡检任务超期提醒短信</p>
 * <p>2.巡检每日任务量提醒短信</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @author fangjian
 * @version V5.0.2
 * @Title: ServiceSendMsg.java
 * @ClassName: ServiceSendMsg
 * @Package com.baosight.wilp.di.jz.service
 * @Description: TODO
 * @date: 2021年11月08日 14:08
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
@Service
public class ServiceDIJZSendMsg extends ServiceBase {

    /**
     * 巡检任务超期提醒短信定时任务
     *
     * <p>1.获取超期短信配置
     *    2.获取即将超期的巡检任务
     *    3.遍历任务，发送短信
     *      3，1 获取短信发送人
     *      3.2 参数设置
     *      3.3 发送短信
     * </p>
     *
     * @Title: sendTaskOutTimeMsg
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo sendTaskOutTimeMsg(EiInfo inInfo) {
        /***1.获取超期短信配置***/
        //获取配置：巡检任务超期短信接收人配置
        String acceptRole = CommonUtils.getDataCodeItemName("wilp.di.config", "03");
        //获取配置：巡检任务超期短信提前提醒时间(分钟)
        String outTime = CommonUtils.getDataCodeItemName("wilp.di.config", "04");
        //获取配置：巡检任务超期短信模板
        String timeOutTemplate = CommonUtils.getDataCodeItemName("wilp.di.config", "05");
        //获取app编码
        String appNo = "AP00001";
        /***2.获取即将超期的巡检任务***/
        List<Map<String, String>> list = dao.query("DIJZ01.queryComingSoonOutTimeTask",outTime);
        System.out.println("获取即将超期的巡检任务:"+ JSON.toJSONString(list));
        //没有即将超期的任务，结束
        if(list == null || list.isEmpty()){
            return inInfo;
        }
        /***3.遍历任务，发送短信***/
        for (Map<String, String> map : list) {
            //3，1 获取短信发送人
            List<String> recvList = getRecvList(acceptRole, DiConstant.MSG_TYPE_OUTTIME, map);
            //过滤重复工号
            recvList = recvList.stream().distinct().collect(Collectors.toList());
            System.out.println("获取即将超期的巡检任务接收人:"+ JSON.toJSONString(recvList));
            //方法调试使用
            inInfo.set("task",map);
            inInfo.set("works",recvList);
            //3.2 参数设置
            List<String> paramList = new ArrayList<>();
            paramList.add(map.get("taskCode"));
            paramList.add(map.get("taskName"));
            //3.3 发送短信
            BaseDockingUtils.sendMsg(recvList, paramList, timeOutTemplate);
            //确定执行人后将消息推送过去
            BaseDockingUtils.pushMsg(recvList,paramList,timeOutTemplate,appNo,null);
        }
        return inInfo;
    }

    /**
     * 发送每天任务量短信
     *
     * <p>
     *   1.获取每天任务量短信配置
     *   2.获取每人、每天任务量
     *   3.遍历,构建消息发送参数
     *       3，1 获取短信发送人
     *       3.2 参数设置
     *       3.3 发送短信
     * </p>
     *
     * @Title: sendTaskOfDayMsg
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo sendTaskOfDayMsg(EiInfo inInfo){
        /***1.获取每天任务量短信配置***/
        //获取配置：每天任务量短信模板
        String dayWorkTemplate = CommonUtils.getDataCodeItemName("wilp.di.config", "06");
        //获取配置：巡检每天任务量短信接收人配置
        String acceptRole = CommonUtils.getDataCodeItemName("wilp.di.config", "07");
        //获取app编码
        String appNo = "AP00001";
        /***2.获取每人、每天任务量***/
        //获取当日有任务的人员
        List<String> rlist = getRecvList(acceptRole, DiConstant.MSG_TYPE_TASKCOUNT, null);
        //过滤重复数据
        rlist = rlist.stream().distinct().collect(Collectors.toList());
        System.out.println("获取当日有任务的人员:"+JSON.toJSONString(rlist));
        //今日没有需要执行的任务，结束
        if(rlist == null || rlist.isEmpty()){
            return inInfo;
        }
        //遍历人员，获取每个人员的任务数
        List<Map<String, Object>> list = new ArrayList<>(rlist.size());
        for (String workNo : rlist) {
            List<Map<String, Object>> countList = dao.query("DIJZ01.countTaskOfDay", workNo);
            if(countList != null) { list.addAll(countList); }
        }
        System.out.println("获取当日有任务的人员的任务数:"+JSON.toJSONString(list));
        /***3.遍历,构建消息发送参数***/
        for (Map<String, Object> map : list){
            //3.1 构建短信接收人参数
            List<String> recvList = Arrays.asList(new String[]{map.get("workNo").toString()});
            //3.2 构建参数
            String taskCount = String.valueOf(map.get("taskCount"));
            List<String> paramList = Arrays.asList(new String[]{taskCount});
            //3.3 发送短信
            System.out.println("发送每天任务量短信:");
            BaseDockingUtils.sendMsg(recvList, paramList, dayWorkTemplate);
            //确定执行人后将消息推送过去
            BaseDockingUtils.pushMsg(recvList,paramList,dayWorkTemplate,appNo,null);
        }
        return inInfo;
    }

    /**
     * 获取短信发送人
     * @Title: getRecvList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param acceptRole ： 短信接收人编码
     * @param type ： 短信类型
     * @param pMap ： 任务信息
     * @return java.util.List<java.lang.String> ： 接收人工号集合
     * @throws
     **/
    private List<String> getRecvList(String acceptRole,String type, Map<String, String> pMap){
        List<String> recvList = new ArrayList<>();
        if(DiConstant.DI_ACCEPT_ALL.equals(acceptRole)){
            //巡检执行人和巡检负责人
            recvList.addAll(getExcutor(pMap, type));
            recvList.addAll(getPrincipal(pMap, type));
        } else if (DiConstant.DI_ACCEPT_PRINCIPAL.equals(acceptRole)) {
            //巡检负责人
            recvList.addAll(getPrincipal(pMap, type));
        } else if (DiConstant.DI_ACCEPT_EXCUTOR.equals(acceptRole)) {
            //巡检执行人
            recvList.addAll(getExcutor(pMap, type));
        }
        return recvList;
    }

    /**
     * 获取巡检任务执行人信息
     * @Title: getExcutor
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param pMap ： 任务信息
     * @param type ： 短信类型
     * @return java.util.List<java.lang.String> ： 接收人工号集合
     * @throws
     **/
    private List<String> getExcutor(Map<String, String> pMap,String type) {

        List<String> recvList = new ArrayList<>();
        if(DiConstant.MSG_TYPE_OUTTIME.equals(type)){
            //超期提醒短信接收人--巡检执行人
            recvList = dao.query("DIJZ01.queryTaskExcutor", pMap.get("schemeID"));
        } else if(DiConstant.MSG_TYPE_TASKCOUNT.equals(type)) {
            //每日任务总数提醒短信接收人--巡检执行人
            recvList = dao.query("DIJZ01.queryTaskWorkersForExcutor",null);
        }
        return recvList;
    }

    /**
     * 获取巡检任务负责人信息
     * @Title: getPrincipal
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param pMap ：任务信息
     * @param type ：短信类型
     * @return java.util.List<java.lang.String> ： 接收人工号集合
     * @throws
     **/
    private List<String> getPrincipal(Map<String, String> pMap, String type) {
        List<String> recvList = new ArrayList<>();
        if(DiConstant.MSG_TYPE_OUTTIME.equals(type)){
            //超期提醒短信接收人--巡检负责人
            recvList.add(pMap.get("managerNo"));
        } else if(DiConstant.MSG_TYPE_TASKCOUNT.equals(type)) {
            //每日任务总数提醒短信接收人--巡检负责人
            recvList = dao.query("DIJZ01.queryTaskWorkersForPrincipal",null);
        }
        return recvList;
    }

}
