package com.baosight.wilp.dk.xm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保养项目：跳转编辑页面、编辑保存操作
 * <p>1.跳转编辑页面
 * <p>2.编辑保存操作
 * @Title: ServiceDKXM0102.java
 * @ClassName: ServiceDKXM0102
 * @Package：com.baosight.wilp.dk.xm.service
 * @author: LENOVO
 * @date: 2021年9月13日 下午2:12:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKXM0102 extends ServiceBase{

    /**
     * 跳转编辑页面
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>1.根据选中行获取项目id
     * <p>2.通过id查询获取该id对应的项目信息
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
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
    	//1.根据选中行获取项目id
        String id = (String)inInfo.getAttr().get("id");
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
      //2.通过id查询获取该id对应的项目信息
        List<Map<String, String>> query = dao.query("DKXM01.queryItem", param);
        EiInfo outInfo=new EiInfo();
        //3.将获取到的第一行数据返回页面
        outInfo.setAttr(query.get(0));
        return outInfo;
    }

    /**
     * 编辑保存操作
     * &lt;p&gt;Title: insert&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>1.将传递参数分装map
     * <p>2.调用修改方法将map作为参数
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
     * 操作成功，失败则执行回滚操作
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo update(EiInfo eiInfo) {
        new Thread(()->{
        	//1.将传递参数分装map
            //2.uuid获取id、编码、分类id
        	String id = (String)eiInfo.get("id");
        	//3.获取项目编码
            String code = (String)eiInfo.get("code");
            //4.获取项目分类id
            String moduleId = (String)eiInfo.get("moduleId");
            //5.获取项目名称
            String content = (String)eiInfo.get("content");
            //6.获取项目描述、巡检值、是实际值
            String projectDesc = (String)eiInfo.get("projectDesc");
            String referenceValue = (String)eiInfo.get("referenceValue");
            String actualValueUnit = (String)eiInfo.get("actualValueUnit");
            //7.获取项目巡检值
            String xunjianAbnormal = (String)eiInfo.get("xunjianAbnormal");
            //8.获取项目备注
            String memo = (String)eiInfo.get("memo");
           //9.获取map参数并赋值
            Map<String,String> map=new HashMap<>();
            map.put("id", id);
            //10.保存项目编码
            map.put("code", code);
            map.put("moduleId", moduleId);
            //11.保存项目名称
            map.put("content", content);
          //12.获取项目描述、巡检值、是实际值
            map.put("projectDesc", projectDesc);
            map.put("referenceValue", referenceValue);
            map.put("actualValueUnit", actualValueUnit);
            //13.保存项目巡检值
            map.put("xunjianAbnormal", xunjianAbnormal);
            //14.保存项目备注
            map.put("memo", memo);
          //15.调用修改方法将map作为参数
            dao.update("DKXM01.updateItem",map);
        }).start();
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }
	
}
