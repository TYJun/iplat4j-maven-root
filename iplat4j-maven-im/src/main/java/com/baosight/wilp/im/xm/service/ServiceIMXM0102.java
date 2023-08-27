package com.baosight.wilp.im.xm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 
 * 巡检项目：跳转编辑页面、编辑保存操作
 * <p>1.跳转编辑页面 initLoad
 * <p>2.编辑保存操作 update
 * @Title: ServiceDIXM0102.java
 * @ClassName: ServiceDIXM0102
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
@SuppressWarnings("all")
public class ServiceIMXM0102 extends ServiceBase {
	
    /**
     * 跳转编辑页面
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param inInfo
     * id 项目主键
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
    public EiInfo initLoad(EiInfo inInfo) {
        String id = (String)inInfo.getAttr().get("id");
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        List<Map<String, String>> query = dao.query("IMXM01.queryItem", param);
        EiInfo outInfo=new EiInfo();
        outInfo.setAttr(query.get(0));
        return outInfo;
    }

    /**
     * 编辑保存操作
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
    public EiInfo update(EiInfo eiInfo) {
        new Thread(()->{
        	String id = (String)eiInfo.get("id");
            String code = (String)eiInfo.get("code");
            String moduleId = (String)eiInfo.get("moduleId");
            String content = (String)eiInfo.get("content");
            String projectDesc = (String)eiInfo.get("projectDesc");
            String referenceValue = (String)eiInfo.get("referenceValue");
            String actualValueUnit = (String)eiInfo.get("actualValueUnit");
            String xunjianAbnormal = (String)eiInfo.get("xunjianAbnormal");
            String memo = (String)eiInfo.get("memo");
            String saftyType = (String)eiInfo.get("saftyType");
            String saftyTypeName = (String)eiInfo.get("saftyTypeName");
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
            map.put("saftyType", saftyType);
            map.put("saftyTypeName", saftyTypeName);
            dao.insert("IMXM01.updateItem",map);
        }).start();
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
