package com.baosight.wilp.dk.xm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.common.domain.DeviceBillType;
import com.baosight.wilp.dk.common.util.DeviceGeneCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * 保养项目：跳转新增页面、新增保存操作
 * <p>1.跳转新增页面 initLoad
 * <p>2.新增保存操作 insert
 * @Title: ServiceDKXM0101.java
 * @ClassName: ServiceDKXM0101
 * @Package：com.baosight.wilp.dk.xm.service
 * @author: LENOVO
 * @date: 2021年9月13日 下午1:55:55
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKXM0101 extends ServiceBase{

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
        //1.获取moduleId参数并返回ininfo
        String moduleId = (String)inInfo.getAttr().get("moduleId");
        Map<String, String> map = new HashMap<>();
        //2.将项目分类id放入map里
        map.put("moduleId", moduleId);
        EiInfo outInfo=new EiInfo();
        //3.返回页面
        outInfo.setAttr(map);
        return outInfo;
    }

    /**
     * 新增保存操作
     * &lt;p&gt;Title: insert&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>1.调用新增方法将map作为参数
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
            //1.uuid获取id、编码、分类id
        	String id = UUID.randomUUID().toString();
        	//2.获取作业项编码
            String code = DeviceGeneCode.geneCode(DeviceBillType.DK_ITEM);
            String moduleId = (String)eiInfo.get("moduleId");
            //3.获取作业项名称
            String content = (String)eiInfo.get("content");
            //4.获取项目描述、巡检值、是实际值
            String projectDesc = (String)eiInfo.get("projectDesc");
            String referenceValue = (String)eiInfo.get("referenceValue");
            String actualValueUnit = (String)eiInfo.get("actualValueUnit");
            //5.获取作业项巡检值
            String xunjianAbnormal = (String)eiInfo.get("xunjianAbnormal");
            //6.获取作业项备注
            String memo = (String)eiInfo.get("memo");
            //7.获取map参数并赋值
            Map<String,String> map=new HashMap<>();
            map.put("id", id);
            //8.保存作业项编码
            map.put("code", code);
            map.put("moduleId", moduleId);
            //9.保存作业项名称
            map.put("content", content);
           //10.保存项目描述、巡检值、是实际值
            map.put("projectDesc", projectDesc);
            map.put("referenceValue", referenceValue);
            map.put("actualValueUnit", actualValueUnit);
            //11.保存作业项巡检值
            map.put("xunjianAbnormal", xunjianAbnormal);
            map.put("memo", memo);
            //12.调用新增方法将map作为参数
            dao.insert("DKXM01.insertItem",map);
        }).start();
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
