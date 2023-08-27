package com.baosight.wilp.pr.gl.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import com.baosight.wilp.pr.common.ImConfig;
import com.baosight.wilp.pr.common.TyepCode;
import com.baosight.wilp.pr.gl.domain.PRGL03;



/**
 * 
 * 整改确认
 * <p>1.initLoad    页面初始化加载
 * <p>2.query       页面查询方法
 * <p>3.safty       安全问题分类下拉框 -- 共用
 * 
 * 
 * @Title: ServicePRGL03.java
 * @ClassName: ServicePRGL03
 * @Package：com.baosight.wilp.pr.gl.service
 * @author: zhangjiaqian
 * @date: 2021年6月24日 下午4:21:33
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL03 extends ServiceBase{


    
    /**
     * 
     * 页面初始化加载
     * 
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "PRGL03.query", new PRGL03());
        return outInfo;
    }


    /**
     * 
     * 页面查询方法
     * 
     * @param inInfo
     * <p>1.typecode    问题类型编码
     * <p>2.beginDate   开始时间
     * <p>3.endDate     结束时间
     * @return
     * <p>1.statusCode  流程编码
     * <p>2.dangerCode  问题编码
     * <p>3.dangerclassfullname 问题描述
     * <p>4.typeName    分类名称
     * <p>5.dangerWhere 问题地点
     * <p>6.contentdesc 描述
     * <p>7.realname    创建人
     * <p>8.createTime  创建时间
     * <p>9.writetime   整改时间
     * <p>10.requireDesc整改要求
     * <p>11.requiredTime整改要求时间
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "PRGL03.query", new PRGL03());
        return outInfo;
    }


    /**
     * 安全问题分类下拉框
     * @param inInfo
     * @return
     * <p>1.lable key
     * <p>2.value value
     */
    public EiInfo safty(EiInfo inInfo) {
        List<Map<String,String>> param = new ArrayList<Map<String,String>>();
        try {
            param = (List<Map<String, String>>) TyepCode.dealUseDay("WILP.im.saftyType");
            inInfo.addBlock("saftyType").addRows(param);
            inInfo.getBlock("saftyType").setBlockMeta(new ImConfig().eiMetadata);
        } catch (Exception e) {
            e.printStackTrace();
            return inInfo;
        }
        return inInfo;
    }
}
