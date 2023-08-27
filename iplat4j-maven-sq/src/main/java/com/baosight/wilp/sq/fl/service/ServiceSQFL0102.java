package com.baosight.wilp.sq.fl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.sq.fl.domain.SQFL01;

/**
 * 
 * 查看分组人员详情: 页面初始化方法,查询方法
     * <P>页面初始化方法initload
     * <P>查询方法query
 * @Title: ServiceSQFL0102.java
 * @ClassName: ServiceSQFL0102
 * @Package：com.baosight.wilp.sq.fl.service
 * @author: zhangjiaqian
 * @date: 2021年7月27日 上午11:00:17
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQFL0102 extends ServiceBase{

    
    /**
     * 用于分类查询
     */
    private String group = null;
    
    
    
    /**
     * 
     * 页面初始化方法
     * 
     * @param inInfo
     * perGroupNo       人员分组编号
     * @return
     *  workNo          人员工号
     *  name            人员名称    
     *  deptName        科室名称
     *  deptNum         科室编号
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取人员分组编号
        String perGroupNo = (String)inInfo.get("perGroupNo");
        group = perGroupNo;
        //封装参数
        Map map = new HashMap();
        map.put("perGroupNo", perGroupNo);
        //查询
        List<Map> query = dao.query("SQFL01.queryName", map);
        //返回封装信息
        EiBlock result = inInfo.addBlock("result");
        result.addBlockMeta(new SQFL01().eiMetadata);
        inInfo.getBlock("result").setRows(query);
        //返回
        return inInfo;
    }


    /**
     * 页面查询
     * @param inInfo
     * perGroupNo           人员组编号
     * @return
     * id                   人员组id
     * perGroupNo           人员组编号
     * perGroupName         人员组名称
     * workNo               人员工号
     * name                 人员名称
     * deptName             科室名称
     * deptNum              科室编号
     * createTime           创建时间
     * updateTime           修改时间
     * groupId              分组id
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
//        String perGroupNo = (String)inInfo.get("perGroupNo");
//        EiBlock block = inInfo.getBlock("result");
//        Map attr = block.getAttr();
//        Integer offset = (Integer)attr.get("offset");
//        Integer limit = (Integer)attr.get("limit");
//        Map map = new HashMap();
//        map.put("offset", offset);
//        map.put("limit", limit);
//        map.put("perGroupNo", perGroupNo);
//        
//        List<Map> query = dao.query("SQFL01.queryName", map);
//        //返回封装信息
//        EiBlock result = inInfo.addBlock("result");
//        result.addBlockMeta(new SQFL01().eiMetadata);
//        block.setRows(query);
//        return inInfo;
        //创建blcok对象
        EiBlock block = new EiBlock("inqu_status");
        block.setCell(0, "perGroupNo", group);
        inInfo.addBlock(block);
       //返回封装信息
        EiInfo outInfo = super.query(inInfo, "SQFL01.queryGroup", new SQFL01());
        return outInfo;
    }

           
}
