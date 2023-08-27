package com.baosight.wilp.im.jz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.jz.domain.ImDevicePacket;



/**
 * 
 * 设备包管理：初始化查询、设备包查询、设备包删除、设备包启、禁用
 * <p>1.初始化查询 initLoad
 * <p>2.设备包查询 query
 * <p>3.设备包删除 delete
 * <p>4.设备包启、禁用 update
 * 
 * @Title: ServiceDIJZ02.java
 * @ClassName: ServiceDIJZ02
 * @Package：com.baosight.wilp.di.jz.service
 * @author: zhangjiaqian
 * @date: 2021年5月28日 下午2:17:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMJZ02 extends ServiceBase{


    /**
     * 初始化查询
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>调用下面query方法
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo query = super.query(inInfo, "IMJZ02.query", new ImDevicePacket());
        return query;
    }



    /**
     * 设备包查询
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param inInfo
     * machineCode  设备包编码
     * machineName  设备包名称
     * status
     * @return
     * id           主键
     * packetCode   设备包编码
     * packetName   设备包名称
     * status       状态
     * createtime   创建时间
     * createman    创建人
     * modifyman    修改人
     * memo         备注
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo query = super.query(inInfo, "IMJZ02.query", new ImDevicePacket());
        return query;
    }



    /**
     * 
     * 设备包删除
     * <p>1.删除设备包信息
     * <p>2.循环删除设备包明细
     * @param inInfo
     * idList  选中的id
     * @return
     * 删除成功，失败则执行回滚操作
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        List<String> ids = (List<String>) inInfo.get("idList");
        //删除设备包信息
        int delete = dao.delete("IMJZ02.deleteInspection", ids);
       //循环删除设备包明细
        for (String id : ids) {
            dao.delete("IMJZ02.deleteRelation", id);//删除设备包明细
        }

        if(0 == delete) {
            inInfo.setStatus(-1);
            inInfo.setMsg("删除失败，请联系管理员");
            return inInfo;
        }
        inInfo.setMsg("删除成功");
        return inInfo;
    }


    /**
     * 
     * 启用/禁用设备包
     * <p>1.循环修改设备包状态
     * @param inInfo
     * list  选中的id
     * @return
     * 操作成功，失败则执行回滚操作
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
        List<String> list = (List<String>) inInfo.get("list");
        Object object = inInfo.get("code");
        //循环修改设备包状态
        list.forEach(id -> {
            Map<String, Object> param = new HashMap<>();
            param.put("status", object);
            param.put("id", id);
            dao.update("IMJZ02.updateStatus", param);
        });
        EiInfo outInfo = new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }


}
