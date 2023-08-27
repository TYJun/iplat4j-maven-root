package com.baosight.wilp.di.xm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.di.common.domain.DeviceBillType;
import com.baosight.wilp.di.common.util.DeviceGeneCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * 巡检项目：跳转新增页面、新增保存操作
 * <p>1.跳转新增页面 initLoad
 * <p>2.新增保存操作 insert
 * @Title: ServiceDIXM0101.java
 * @ClassName: ServiceDIXM0101
 * @Package：com.baosight.wilp.di.xm.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午1:55:55
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDIXM0101 extends ServiceBase {
	
	/**
	 * 跳转新增页面
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * moduleId 项目分类id
	 * @return
	 * id  项目主键
	 * code 项目编码
	 * content 项目名称
	 * projectDesc 项目描述
	 * referenceValue 项目参考值
	 * actualValueUnit 实际值单位
	 * xunjianAbnormal 异常描述
	 * memo 备注
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
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
	 * &lt;p&gt;Title: insert&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param eiInfo
	 * moduleId 项目分类id
	 * id  项目主键
     * code 项目编码
     * content 项目名称
     * projectDesc 项目描述
     * referenceValue 项目参考值
     * actualValueUnit 实际值单位
     * xunjianAbnormal 异常描述
     * memo 备注
	 * @return
	 * 新增成功，失败则执行回滚操作
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
	 */
    @Override
    public EiInfo insert(EiInfo eiInfo) {
        new Thread(()->{
        	String id = UUID.randomUUID().toString();
            String code = DeviceGeneCode.geneCode(DeviceBillType.DI_ITEM);
            String moduleId = (String)eiInfo.get("moduleId");
            String content = (String)eiInfo.get("content");
            String projectDesc = (String)eiInfo.get("projectDesc");
            String referenceValue = (String)eiInfo.get("referenceValue");
            String actualValueUnit = (String)eiInfo.get("actualValueUnit");
            String xunjianAbnormal = (String)eiInfo.get("xunjianAbnormal");
            String memo = (String)eiInfo.get("memo");
            Map<String,String> map=new HashMap<>();
            map.put("id", id);
            map.put("code", code);
            map.put("moduleId", moduleId);
            map.put("content", content);
            map.put("projectDesc", projectDesc);
            map.put("referenceValue", referenceValue);
            map.put("actualValueUnit", actualValueUnit);
            map.put("xunjianAbnormal", xunjianAbnormal);
            map.put("memo", memo);
            dao.insert("DIXM01.insertItem",map);
        }).start();
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
