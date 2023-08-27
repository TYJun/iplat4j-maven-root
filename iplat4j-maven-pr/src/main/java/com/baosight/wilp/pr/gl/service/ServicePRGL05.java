package com.baosight.wilp.pr.gl.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.pr.gl.domain.PRGL05;


/**
 * 
 * 综合台账逻辑处理层:页面初始化方法,页面查询方法
 * <p>1.initLoad    页面初始化方法
 * <p>2.query       页面查询方法
 * 
 * @Title: ServicePRGL05.java
 * @ClassName: ServicePRGL05
 * @Package：com.baosight.wilp.pr.gl.service
 * @author: zhangjiaqian
 * @date: 2021年6月24日 下午5:12:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL05 extends ServiceBase{


    /**
     * 
     * 页面初始化方法
     * 
     * @param inInfo
     * <p>1.typecode        分类编码
     * <p>2.beginDate       开始时间
     * <p>3.endDate         结束时间
     * @return
     * <p>1.statusCode      流程状态    
     * <p>2.id              问题id
     * <p>3.dangercode      问题编码
     * <p>4.createman       创建人
     * <p>5.createtime      创建时间
     * <p>6.dangerwhere     问题地点
     * <p>7.contentdesc     描述
     * <p>8.requiredesc     整改要求
     * <p>9.requiredtime    整改时间
     * <p>10.localtypecode  分类名称
     * <p>11.writeMan       整改人
     * <p>12.finishTime     完成时间
     * <p>13.desc1          内容描述
     * <p>14.auditContent   审核内容
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "PRGL05.query", new PRGL05());
        return outInfo;
    }



    /**
     * 
     * 查询方法
     * 
     * @param inInfo
     * <p>1.typecode        分类编码
     * <p>2.beginDate       开始时间
     * <p>3.endDate         结束时间
     * @return
     * <p>1.statusCode      流程状态    
     * <p>2.id              问题id
     * <p>3.dangercode      问题编码
     * <p>4.createman       创建人
     * <p>5.createtime      创建时间
     * <p>6.dangerwhere     问题地点
     * <p>7.contentdesc     描述
     * <p>8.requiredesc     整改要求
     * <p>9.requiredtime    整改时间
     * <p>10.localtypecode  分类名称
     * <p>11.writeMan       整改人
     * <p>12.finishTime     完成时间
     * <p>13.desc1          内容描述
     * <p>14.auditContent   审核内容
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "PRGL05.query", new PRGL05());
        return outInfo;
    }

}
