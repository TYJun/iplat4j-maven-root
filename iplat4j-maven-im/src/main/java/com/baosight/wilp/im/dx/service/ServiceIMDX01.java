package com.baosight.wilp.im.dx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.dx.domain.ImObjectSpot;

/**
 * 
 * 巡检对象管理：基准初始化查询、对象查询、对象删除
 * <p>1.基准初始化查询  initLoad
 * <p>2.基准查询   query
 * <p>3.对象删除   delete
 * 
 * @Title: ServiceIMDX01.java
 * @ClassName: ServiceIMDX01
 * @Package：com.baosight.wilp.im.dx.service
 * @author: zhangjiaqian
 * @date: 2021年9月14日 下午7:29:12
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class ServiceIMDX01 extends ServiceBase{

    
    /**
     * 基准初始化查询
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>1.调用平台封装方法查询并返回
     * @param inInfo
     * 无参
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "IMDX01.query", new ImObjectSpot());
        return outInfo;
    }




    /**
     * 
     * 对象查询
     * <p>1.查询参数：objName-巡查对象，spotName-巡查地点名称，spotCode-巡查地点编码
     * <p>2.调用平台封装方法查询并返回
     * 
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "IMDX01.query", new ImObjectSpot());
        return outInfo;
    }
    
    
    
    /**
     * 
     * 对象删除
     * <p>1.获取参数
     * <p>2.检验参数
     * <p>3.通过参数删除信息
     * @param inInfo
     * id    对象id
     * @return
     * 状态信息
     * 
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
     */
    public EiInfo delete(EiInfo inInfo) {
        //获取选中行id
        String id = (String)inInfo.get("id");
        //id为空，参数错误，返回
        if(StringUtils.isEmpty(id)) {
            inInfo.setStatus(-1);
            inInfo.setMsg("参数错误，请联系管理员");
            return inInfo;
        }
        //按id进行删除
        int delete = dao.delete("IMDX01.delete", id);
        //删除为0行，删除失败，返回
        if(delete == 0) {
            inInfo.setStatus(-1);
            inInfo.setMsg("删除失败，请联系管理员");
            return inInfo;
        }
        //返回成功状态信息
        inInfo.setStatus(1);
        inInfo.setMsg("删除成功，请联系管理员");
        return inInfo;
    }
}
