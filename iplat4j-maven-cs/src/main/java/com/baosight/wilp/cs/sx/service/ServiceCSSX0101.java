package com.baosight.wilp.cs.sx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.cs.common.CSUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 保洁事项：跳转新增页面、新增保存操作.
 * 一、跳转新增页面，页面初始化.
 * 二、新增保存操作.
 * 
 * @Title: ServiceCSSX0101.java
 * @ClassName: ServiceCSSX0101
 * @Package：com.baosight.wilp.cs.sx.service
 * @author: fangzekai
 * @date: 2021年11月19日 下午5:46:03
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSSX0101 extends ServiceBase {
	
	/**
	 * 一、跳转新增页面，页面初始化.
     *
     * Title: initLoad
	 * @param inInfo
	 * moduleId 项目分类id
	 * @return
	 * EiInfo
	 * @see ServiceBase#initLoad(EiInfo)
	 */
    public EiInfo initLoad(EiInfo inInfo) {
        // 事项分类ID
        String moduleId = (String)inInfo.getAttr().get("moduleId");
        Map<String, String> map = new HashMap<>();
        map.put("moduleId", moduleId);
        EiInfo outInfo=new EiInfo();
        outInfo.setAttr(map);
        return outInfo;
    }

	/**
	 * 二、新增保存操作.
     *
     * Title: insert
	 * @param eiInfo
	 * id  事项主键
	 * moduleId 事项分类id
     * itemCode 事项编码
     * itemName 事项名称
     * serviceDeptNum 绑定科室编码
     * serviceDeptName 绑定科室名称
     * comments 备注
	 * @return
	 * 新增成功，失败则执行回滚操作
	 * @see ServiceBase#insert(EiInfo)
	 */
    public EiInfo insert(EiInfo eiInfo) {
        new Thread(()->{
        	String id = UUID.randomUUID().toString();
        	String moduleId = (String)eiInfo.get("moduleId");
        	// 生成保洁事项编码
            String itemCode = CSUtils.generationSerialNo("clean_sx_num", "SX", "1");
            String itemName = (String)eiInfo.get("itemName");
            String serviceDeptNum = (String)eiInfo.get("serviceDeptNum");
            String serviceDeptName = (String)eiInfo.get("serviceDeptName");
            String comments = (String)eiInfo.get("comments");
            Map<String,String> map=new HashMap<>();
            map.put("id", id);
            map.put("moduleId", moduleId);
            map.put("itemCode", itemCode);
            map.put("itemName", itemName);
            map.put("serviceDeptNum", serviceDeptNum);
            map.put("serviceDeptName", serviceDeptName);
            map.put("comments", comments);
            // 执行插入语句
            dao.insert("CSSX01.insertItem",map);
        }).start();
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
