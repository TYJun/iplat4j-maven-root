package com.baosight.wilp.pr.gl.service;

import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.pr.gl.domain.PRGL0302;


/**
 * 
 * 查看整改问题详情公共方法:页面初始化方法
 * <p>1.initLoad    页面初始化方法
 * 
 * @Title: ServicePRGL0302.java
 * @ClassName: ServicePRGL0302
 * @Package：com.baosight.wilp.pr.gl.service
 * @author: zhangjiaqian
 * @date: 2021年6月24日 下午4:28:56
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL0302 extends ServiceBase{


    
    /**
     * 
     * 页面初始化加载方法
     * 
     * @param inInfo
     * <p>1.id      问题id
     * @return
     * <p>1.contentType 整改流程状态
     * <p>2.name        操作人
     * <p>3.time        操作时间
     * <p>4.param       备注
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取问题id
        String id = (String)inInfo.get("id");
        //构建查询参数带分页
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "result");
        map.put("id", id);
        List<PRGL0302> param = dao.query("PRGL0302.queryLogs", map);
        //封装参数并返回
        EiBlock R1 = inInfo.addBlock("result");
        R1.addBlockMeta(new PRGL0302().eiMetadata);
        inInfo.getBlock("result").setRows(param);
        return inInfo;
    }
    
}
