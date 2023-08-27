package com.baosight.wilp.cs.sx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保洁事项：跳转编辑页面、编辑保存操作.
 * 一、跳转编辑页面，页面初始化.
 * 二、编辑保存操作，更新保洁事项.
 * 
 * @Title: ServiceCSSX0102.java
 * @ClassName: ServiceCSSX0102
 * @Package：com.baosight.wilp.cs.sx.service
 * @author: kayze
 * @date: 2021年11月19日 下午6:34:52
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class ServiceCSSX0102 extends ServiceBase {
	
    /**
     * 一、跳转编辑页面.
     *
     * Title: initLoad
     * @param inInfo
     * itemId 事项主键
     * @return
     * itemid  事项主键
     * moduleId 事项分类id
     * itemCode 事项编码
     * itemName 事项名称
     * serviceDeptNum 绑定科室编码
     * serviceDeptName 绑定科室名称
     * comments 备注
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo initLoad(EiInfo inInfo) {
        // 获取前端传来的事项id
        String itemId = (String)inInfo.getAttr().get("itemId");
        Map<String, String> param = new HashMap<>();
        param.put("itemId", itemId);
        // 执行CSSX01.queryItem 查询保洁事项列表
        List<Map<String, String>> query = dao.query("CSSX01.queryItem", param);
        EiInfo outInfo=new EiInfo();
        outInfo.setAttr(query.get(0));
        return outInfo;
    }

    /**
     * 二、编辑保存操作，更新保洁事项.
     *
     * Title: update
     * @param eiInfo
     * id  事项主键
     * moduleId 事项分类id
     * itemName 事项名称
     * serviceDeptNum 绑定科室编码
     * serviceDeptName 绑定科室名称
     * comments 备注
     * @return
     * 更新成功，失败则执行回滚操作
     * @see ServiceBase#update(EiInfo)
     */
    public EiInfo update(EiInfo eiInfo) {
        // 以线程的形式获取值并用map存储然后执行CSSX01.updateItem更新保洁事项
        new Thread(()->{
            // 事项ID
        	String id = (String)eiInfo.get("id");
        	// 事项的分类ID
            String moduleId = (String)eiInfo.get("moduleId");
            // 事项名称
            String itemName = (String)eiInfo.get("itemName");
            String serviceDeptNum = (String)eiInfo.get("serviceDeptNum");
            String serviceDeptName = (String)eiInfo.get("serviceDeptName");
            String comments = (String)eiInfo.get("comments");
            Map<String,String> map=new HashMap<>();
            map.put("id", id);
            map.put("moduleId", moduleId);
            map.put("itemName", itemName);
            map.put("serviceDeptNum", serviceDeptNum);
            map.put("serviceDeptName", serviceDeptName);
            map.put("comments", comments);
            // 主要以事项ID为主去做更新
            dao.update("CSSX01.updateItem",map);
        }).start();
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
