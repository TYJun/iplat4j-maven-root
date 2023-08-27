package com.baosight.wilp.im.jz.service;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.jz.domain.ImDevicePacket;

/**
 * 
 * 设备包添加数据弹窗逻辑处理：跳转设备包初始化、设备包查询
 * <p>1.跳转设备包初始化 initLoad
 * <p>2.设备包查询 query
 * @Title: ServiceDIJZ010105.java
 * @ClassName: ServiceDIJZ010105
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午5:42:19
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMJZ010105 extends ServiceBase{
    
    
    /**
     * 注入dao
     */
    private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
    

    
    /**
     * 跳转设备包初始化
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>调用下面query方法
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "IMJZ010105.queryPacket", new ImDevicePacket());
        return outInfo;
    }
    
    
    /**
     * 设备包查询
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param inInfo
     * packetName   设备包名称
     * @return
     * packetCode   设备包编号
     * packetName   设备包名称
     * packetId     设备包id
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "IMJZ010105.queryPacket", new ImDevicePacket());
        return outInfo;
    }
    
}
