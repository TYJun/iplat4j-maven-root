package com.baosight.wilp.dk.jz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.jz.domain.DkCardMaintain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基准增加卡片：跳转卡片初始化、查询卡片
 * <p>1.跳转卡片初始化
 * <p>2.查询卡片
 * @Title: ServiceDKJZ010106.java
 * @ClassName: ServiceDKJZ010106
 * @Package：com.baosight.wilp.dk.jz.service
 * @author: LENOVO
 * @date: 2021年9月14日 下午5:06:34
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKJZ010106 extends ServiceBase{

	
    /**
     * 跳转卡片初始化
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param info
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo info) {
        
         return this.query(info);
    }

    /**
     * 查询卡片
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "DKJZ010106.query", new DkCardMaintain());
        return outInfo;
    }

    /**
     * 通过卡片id获取卡片里的项目
     * <p>获取卡片id
     * <p>通过卡片id获取卡片信息
     *
     * @Title: queryProjectInfo 
     * @param inInfo
     * @return 
     * @return: EiInfo
     */
    public EiInfo queryProjectInfo(EiInfo inInfo) {
        Map<String,String> parm=new HashMap<>();
        //1.获取卡片id
        String cardId=(String)inInfo.get("cardid");
        parm.put("id", cardId);
        //2.通过卡片id获取卡片信息
        List<Map<String,Object>> itemList=dao.query("DKJZ010106.getProjectInfo",parm);
        //3.返回卡片信息到inifo里
        inInfo.set("param", itemList);
        return inInfo;
    }
}
