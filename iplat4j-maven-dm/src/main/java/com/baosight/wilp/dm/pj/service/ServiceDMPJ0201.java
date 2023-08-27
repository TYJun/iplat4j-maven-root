package com.baosight.wilp.dm.pj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dm.common.DMGeneCode;
import com.baosight.wilp.dm.common.domain.DMGeneCodeType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * 评价项目项目：跳转新增页面、新增保存操作
 * <p>1.跳转新增页面 initLoad
 * <p>2.新增保存操作 insert
 * @Title: ServiceDMPJ0201.java
 * @ClassName: ServiceDMPJ0201
 * @Package：com.baosight.wilp.dm.pj.service
 * @author: fangzekai
 * @date: 2022年3月25日 下午1:55:55
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMPJ0201 extends ServiceBase {
	
	/**
	 * 跳转新增页面
	 * @param inInfo
	 * moduleId 项目分类id
	 * @return
	 * id  项目主键
	 * code 项目编码
	 * content 项目名称
	 * memo 备注
	 * @see ServiceBase#initLoad(EiInfo)
	 */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        String moduleId = (String)inInfo.getAttr().get("moduleId");
        Map<String, String> map = new HashMap<>();
        map.put("moduleId", moduleId);
        EiInfo outInfo=new EiInfo();
        outInfo.setAttr(map);
        return outInfo;
    }

	/**
	 * 新增保存操作
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
    @Override
    public EiInfo insert(EiInfo eiInfo) {
        new Thread(()->{
        	String id = UUID.randomUUID().toString();
            String code = DMGeneCode.geneCode(DMGeneCodeType.DM_ITEM);
            String moduleId = (String)eiInfo.get("moduleId");
            String content = (String)eiInfo.get("content");
            String memo = (String)eiInfo.get("memo");
            Map<String,String> map=new HashMap<>();
            map.put("id", id);
            map.put("code", code);
            map.put("moduleId", moduleId);
            map.put("content", content);
            map.put("memo", memo);
            dao.insert("DMPJ02.insertItem",map);
        }).start();
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
