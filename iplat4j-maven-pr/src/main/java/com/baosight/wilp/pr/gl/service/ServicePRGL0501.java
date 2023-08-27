package com.baosight.wilp.pr.gl.service;

import java.util.List;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.pr.gl.domain.PRGL0501;


/**
 * 
 * 综合台账查看详情:页面初始化方法
 * <p>1.initLoad    页面初始化方法
 * 
 * @Title: ServicePRGL0501.java
 * @ClassName: ServicePRGL0501
 * @Package：com.baosight.wilp.pr.gl.service
 * @author: ha'ha'ha
 * @date: 2021年6月24日 下午5:17:50
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL0501 extends ServiceBase{
    
    
    /**
     * 
     * 页面初始化方法
     * 
     * @param inInfo
     * <p>1.id  问题id
     * @return
     * <p>1.dangerclassfullname     问题分类名称
     * <p>2.dangerWhere             问题地点
     * <p>3.paramValue1             问题分类
     * <p>4.requiredTime            要求整改时间
     * <p>5.contentdesc             描述
     * <p>6.requireDesc             整改要求      
     * <p>7.writeman                整改执行人
     * <p>8.finishtime              完成时间
     * <p>9.contentdesc2            整改实绩
     * <p>10.auditcontent           整改评价
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取id
        String id = (String)inInfo.get("id");
        // 判断id是否为空
        if (id.isEmpty()) {
            return inInfo;
        }
        //查询整改信息
        List<PRGL0501> params = dao.query("PRGL0501.query", id);

        //返回参数
        PRGL0501 prgl0501 = params.get(0);
        //问题分类名称
        inInfo.set("dangerclassfullname", prgl0501.getDangerclassfullname());
        //问题地点
        inInfo.set("dangerWhere", prgl0501.getDangerWhere());
        //问题分类
        inInfo.set("paramValue1", prgl0501.getParamValue1());
        //要求整改时间
        inInfo.set("requiredTime", prgl0501.getRequiredTime());
        //描述
        inInfo.set("contentdesc", prgl0501.getContentdesc());
        //整改要求
        inInfo.set("requireDesc", prgl0501.getRequireDesc());
        //整改执行人
        inInfo.set("writeman", prgl0501.getWriteman());
        //完成时间
        inInfo.set("finishtime", prgl0501.getFinishtime());
        //整改实绩
        inInfo.set("contentdesc2", prgl0501.getContentdesc2());
        //整改评价
        inInfo.set("auditcontent", prgl0501.getAuditcontent());
        return inInfo;
    }

    
    

}
