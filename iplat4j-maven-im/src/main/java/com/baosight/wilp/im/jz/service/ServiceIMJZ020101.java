package com.baosight.wilp.im.jz.service;

import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.jz.domain.ImDevicePacket;

/**
 * 
 * 巡检设备包添加设备信息：跳转设备信息初始化、设备信息查询
 * 
 * @Title: ServiceDIJZ020101.java
 * @ClassName: ServiceDIJZ020101
 * @Package：com.baosight.wilp.di.jz.service
 * @author: zhangjiaqian
 * @date: 2021年5月31日 上午9:31:21
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人 <time>    // 修改时间 <version> // 版本 <desc>  // 描述修改内容
 */
public class ServiceIMJZ020101 extends ServiceBase {


    /**
     * 
     * 跳转设备信息初始化
     * 
     * @param inInfo
     * @return
     * id           主键
     * paramKey     设备代码
     * paramName    设备名称
     * classifyName 安装地点
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "IMJZ020101.query", new ImDevicePacket());
        return outInfo;
    }

    
    
    /**
     * 设备信息查询
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>获取树树节点id
     * <p>获取查询参数
     * <p>获取表单Block
     * <p>编辑查询参数
     * <p>封装参数返回
     * @param info
     * moduleId     设备分类id
     * @return
     * id           主键
     * paramKey     设备代码
     * paramName    设备名称
     * classifyName 安装地点
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo info) {
        //获取树树节点id
        String moduleId = (String)info.getAttr().get("machineTypeId");
        //获取查询参数
        EiBlock block = info.getBlock("inqu_status");
        List<Map<String, Object>> rows = block.getRows();
        Map<String, Object> map = rows.get(0);
        //获取表单Block
        EiBlock block2 = info.getBlock("result");
        Map<String,Object> attr = block2.getAttr();
        Object offset = attr.get("offset");
        Object limit = attr.get("limit");
        //编辑查询参数
        map.put("moduleId", moduleId);
        map.put("offset", offset);
        map.put("limit", limit);
        List<ImDevicePacket> query = dao.query("IMJZ020101.query", map);
        //封装参数返回
        EiInfo outInfo = new EiInfo();
        EiBlock result = outInfo.addBlock("result");
        result.addBlockMeta(new ImDevicePacket().eiMetadata);
        outInfo.getBlock("result").setRows(query);
        
        return outInfo;
    }
}
