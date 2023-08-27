
package com.baosight.wilp.mc.pm.service;

import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.*;

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
public class ServiceMCPM01 extends ServiceBase {
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
        String[] param = {"callModule", "deliveryChannel",  "sendingTimeS",
                "sendingTimeE"};
        List<String> plist = Arrays.asList(param);
        Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);

        /**
         *  2.调用查询方法查询出符合入参条件的结果集
         */
        map.put("receiver",UserSession.getLoginName());
        map.put("projectSchema", projectSchema);
        map.put("platSchema",platSchema);
        List<Map<String, Object>> list = dao.query("MCPM01.queryMessage", map,
                Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));


        map.put("list",list);
        if(!list.isEmpty()){
            dao.update("MCPM01.setRead", map);
        }

        /**
         * 3.查询分页count
         */
        int count = dao.count("MCPM01.queryMessage", map);


        /**
         *  4.将结果集和分页消息封装到EiInfo中的result域
         */
        if (!CollectionUtils.isEmpty(list)) {
            return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            return inInfo;
        }
    }


}
