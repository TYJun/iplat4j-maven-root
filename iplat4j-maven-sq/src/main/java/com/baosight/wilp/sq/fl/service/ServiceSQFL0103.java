package com.baosight.wilp.sq.fl.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.sq.fl.domain.SQFL01;

/**
 * 
 * 人员分组编辑逻辑处理层，编辑分组
 * <p>页面初始化 initLoad
 * <p>编辑分组 update
 * @Title: ServiceSQFL0103.java
 * @ClassName: ServiceSQFL0103
 * @Package：com.baosight.wilp.sq.fl.service
 * @author: zhangjiaqian
 * @date: 2021年7月27日 下午3:03:59
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQFL0103 extends ServiceBase{
    
    
    /**
            * 人员分组编号
     */
    public static String perGroup = null;


    /**
     * 
     * 页面初始化方法
     * 
     * @param inInfo
     * perGroupNo            人员分组编号
     * @return
     * perGroupName          人员分组名称
     * groupId               人员分组id
     * perGroupNo            人员分组编号 
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取人员分组编号
        String perGroupNo = (String)inInfo.get("perGroupNo");
        perGroup = perGroupNo;
        //封装数据
        Map param = new HashMap();
        param.put("perGroupNo", perGroupNo);
        //查询数据
        List<SQFL01> query = dao.query("SQFL01.queryGroup", param);
        //非空判断
        if(query.size() == 0) {
            inInfo.setStatus(-1);
            inInfo.setMsg("系统错误，请联系管理员");
            return inInfo;
        }
        //数据封装
        SQFL01 sqfl01 = query.get(0);
        inInfo.set("perGroupNo", sqfl01.getPerGroupNo());
        inInfo.set("perGroupName", sqfl01.getPerGroupName());
        inInfo.set("groupId", sqfl01.getGroupId());
        
        
        //返回封装信息
        EiBlock result = inInfo.addBlock("result");
        result.addBlockMeta(new SQFL01().eiMetadata);
        inInfo.getBlock("result").setRows(query);

        return inInfo;
    }


    /**
     * 
     * 编辑分组
     * 
     * @param inInfo
     * groupId         分组id   
     * @return
     * groupId         分组id         
     * perGroupNo       人员组编号   
     * perGroupName     人员组名称
     * createTime       创建时间
     * wrokNO           人员工号
     * name             人员名称
     * deptNum          科室编号
     * deptName         科室名称
     * groupId          分组id   
     * updateTime       更新时间
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
        List<Map> data = (List)inInfo.get("data");
        //分组编码
        String perGroupNo = (String)inInfo.get("perGroupNo");
        //分组名
        String perGroupName = (String)inInfo.get("perGroupName");
        //分组id
        String groupId = (String)inInfo.get("groupId");
        //获取当前时间
        String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //删除分组
        int delete = dao.delete("SQFL01.deleteperGroup", groupId);

        //保存分组
        Map param = new HashMap();
        //分组id
        String groupId1 = UUID.randomUUID().toString();
        //保存人员组信息
        for (Map map : data) {
            param.put("perGroupNo", perGroupNo);
            param.put("perGroupName", perGroupName);
            param.put("id", UUID.randomUUID().toString());
            param.put("workNo", map.get("workNo"));
            param.put("name", map.get("name"));
            param.put("deptNum", map.get("deptNum"));
            param.put("deptName", map.get("deptName"));
            param.put("createTime", updateTime);
            param.put("groupId", groupId1);
            dao.insert("SQFL01.insetPerGroup", param);
        }
        inInfo.setStatus(1);
        inInfo.setMsg("保存成功");
        return inInfo;
    }
}
