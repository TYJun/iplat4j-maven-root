package com.baosight.wilp.dm.pj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 评价项目项目：跳转编辑页面、编辑保存操作
 * <p>1.跳转编辑页面 initLoad
 * <p>2.编辑保存操作 update
 * @Title: ServiceDMPJ0202.java
 * @ClassName: ServiceDMPJ0202
 * @Package：com.baosight.wilp.di.xm.service
 * @author: fangzekai
 * @date: 2022年3月25日 下午1:55:55
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class ServiceDMPJ0202 extends ServiceBase {
	
    /**
     * 跳转编辑页面
     * @param inInfo
     * id 项目主键
     * @return
     * id  项目主键
     * code 项目编码
     * content 项目名称
     * memo 备注
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo initLoad(EiInfo inInfo) {
        String id = (String)inInfo.getAttr().get("id");
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        List<Map<String, String>> query = dao.query("DMPJ02.queryItem", param);
        EiInfo outInfo=new EiInfo();
        outInfo.setAttr(query.get(0));
        return outInfo;
    }

    /**
     * 编辑保存操作
     * @param eiInfo
     * moduleId 项目分类id
     * id  项目主键
     * code 项目编码
     * content 项目名称
     * memo 备注
     * @return
     * 新增成功，失败则执行回滚操作
     * @see ServiceBase#insert(EiInfo)
     */
    public EiInfo update(EiInfo eiInfo) {
        new Thread(()->{
        	String id = (String)eiInfo.get("id");
            String code = (String)eiInfo.get("code");
            String moduleId = (String)eiInfo.get("moduleId");
            String content = (String)eiInfo.get("content");
            String memo = (String)eiInfo.get("memo");
            Map<String,String> map=new HashMap<>();
            map.put("id", id);
            map.put("code", code);
            map.put("moduleId", moduleId);
            map.put("content", content);
            map.put("memo", memo);
            dao.insert("DMPJ02.updateItem",map);
        }).start();
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
