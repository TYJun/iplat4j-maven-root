
package com.baosight.wilp.mc.ms.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 消息日志.
 * <p>
 * </p>
 *
 * @Title ServiceMCMS01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 */
public class ServiceMCMS01 extends ServiceBase {
    String projectSchema = PrUtils.getConfigure();
    String platSchema = PlatApplicationContext.getProperty("platSchema");

    @Resource
    Environment environment; // 读取配置使用

    /**
     * 界面初始化
     * 作者：hcd
     * 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 查询方法
     * 作者：hcd
     * 入参：EiInfo(模板编码 "templateCode", 调用模块 "callModule", 发送渠道
     * "deliveryChannel")
     * 出参: EiInfo(消息list)
     * 处理：
     * 1.将入参存入map中
     * 2.调用查询方法查询出符合入参条件的结果集
     * 3.查询分页count
     * 4.将结果集和分页消息封装到EiInfo中的result域
     *
     * @param inInfo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public EiInfo query(EiInfo inInfo) {
        /**
         * 1.将入参存入map中
         */
        String[] param = {"templateCode", "callModule", "deliveryChannel", "receiver", "sendingTimeS",
                "sendingTimeE"};
        List<String> plist = Arrays.asList(param);
        Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);

        /**
         *  2.调用查询方法查询出符合入参条件的结果集
         */
        map.put("projectSchema", projectSchema);
        map.put("platSchema",platSchema);
        List<Map<String, Object>> list = dao.query("MCMS01.queryMessage", map,
                Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
        /**
         * 3.查询分页count
         */
        int count = dao.count("MCMS01.queryMessage", map);


        /**
         *  4.将结果集和分页消息封装到EiInfo中的result域
         */
        if (!CollectionUtils.isEmpty(list)) {
            return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            return inInfo;
        }
    }

    //查询当前用户未读消息的个数
    public EiInfo queryUnreadMsgCount(EiInfo inInfo) {
        Map<String, String> map = inInfo.getAttr();
        map.put("status", "0");
        map.put("projectSchema", projectSchema);
        int count = super.count("MCMS01.queryUnreadMessageCount", map);
        inInfo.set("unreadMsgCount", count);
        return inInfo;
    }

    //查询当前用户的消息明细（分页控制）
    public EiInfo queryCurrUserMsg(EiInfo inInfo) {
        Map<String, String> map = inInfo.getAttr();
        map.put("projectSchema", projectSchema);
        map.put("platSchema",platSchema);
        List<Map<String, Object>> list = dao.query("MCPM01.queryMessage", map,
                Integer.parseInt(map.get("offset")), Integer.parseInt(map.get("limit")));
        int count = dao.count("MCPM01.queryMessage", map);
        if (!CollectionUtils.isEmpty(list)) {
            return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            return inInfo;
        }
    }

    //将当前用户的未读消息设置成已读
    public EiInfo updateMsgReaded(EiInfo inInfo) {
        Map<String, String> map = inInfo.getAttr();
        map.put("projectSchema", projectSchema);
        map.put("status", "1");
        dao.update("MCMS01.updateStatus", map);
        return inInfo;
    }

    //将当前用户的所有未读消息设置成已读
    public EiInfo updateAllMsgRead(EiInfo inInfo) {
        Map<String, String> map = inInfo.getAttr();
        map.put("projectSchema", projectSchema);
        dao.update("MCMS01.setAllRead", map);
        return inInfo;
    }

    /**
     * 重新发送短信
     * 作者：hcd
     * 入参：EiInfo(templateId,message,callModule,deliveryChannel,receiver)
     * 出参：EiInfo(操作结果)
     * 处理：
     * 1.从入参读入参数
     * 2.MCFW01.sendDirect（）方法发送
     * 3.返回结果
     *
     * @param eiInfo
     * @return
     */
    public EiInfo reSendText(EiInfo eiInfo) {

        /**
         * 1.从入参读入参数
         */
        String templateId = eiInfo.getString("templateId");
        String message = eiInfo.getString("message");
        String callModule = eiInfo.getString("callModule");
        String deliveryChannel = eiInfo.getString("deliveryChannel");
        String receiver = eiInfo.getString("receiver");

        /**
         *  2.MCFW01.sendDirect（）方法发送
         */
        eiInfo.set("hospital", environment.getProperty("hospital"));
        eiInfo.set("templateId", templateId);
        eiInfo.set("message", message);
        eiInfo.set("module", callModule);
        eiInfo.set("deliveryChannel", deliveryChannel);
        LinkedList<String> workNoList = new LinkedList<>();
        workNoList.add(receiver);
        eiInfo.set("workNoList", workNoList);
        eiInfo.set(EiConstant.serviceName, "MCFW01");
        eiInfo.set(EiConstant.methodName, "sendDirect");
        EiInfo info = XLocalManager.call(eiInfo);

        /**
         *  3.返回结果
         */
        int status = info.getStatus();
        if (status == 0) {
            info.setMsg("发送成功");
        }
        return info;
    }

}
