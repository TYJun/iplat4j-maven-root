package com.baosight.wilp.sq.fl.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.sq.fl.domain.SQFL01;

/**
 * 人员分组逻辑处理： 查询人员组信息初始化方法，查询方法，删除分组
 * <p>1.查询人员组信息初始化方法 initLoad
 * <p>2.查询方法 query
 * <p>3.删除人员分组 delete
 * @Title: ServiceSQFL01.java
 * @ClassName: ServiceSQFL01
 * @Package：com.baosight.wilp.sq.fl.service
 * @author: zhangjiaqian
 * @date: 2021年7月27日 上午10:29:04
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History: // 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQFL01 extends ServiceBase{

    /**
     * <p>
     * 1.调用本地查询
     * <p>
     * @Title: initLoad
     * @Description: 查询人员组信息初始化方法
     * @param: EiInfo
     * perGroupNo : 人员组编号
     * perGroupName : 人员组名称
     * @return: EiInfo
     * id : 主键
     * perGroupNo : 人员组编号
     * perGroupName : 人员组名称
     * createtime : 创建时间
     * <author>  // 修改人
     * <time>    // 修改时间
     * <version> // 版本
     * <desc>    // 描述修改内容
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        /*
         *  1.调用本地查询
         */
        return this.query(inInfo);
    }
    
    
    
    /**
     * <p>
     * 1.调用查询
     * <p>
     * @Title: query
     * @Description: 查询人员组方法
     * @param EiInfo
     * perGroupNo : 人员分组编号   
     * perGroupName : 人员分组名称
     * createTime : 创建时间
     * @return EiInfo
     * perGroupNo : 人员组编号
     * perGroupName : 人员组名称
     * createtime : 创建时间
     * <author>  // 修改人
     * <time>    // 修改时间
     * <version> // 版本
     * <desc>    // 描述修改内容
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        /* 
         * 1.调用查询
         */
        EiInfo outInfo = super.query(inInfo, "SQFL01.query", new SQFL01());
        return outInfo;
    }
    
    
    
    /**
     * <p>
     * 1.获取前端参数
     * 2.查询该分组是否已经被引用
     * <p>
     * @Title: delete
     * @Description: 删除分组
     * @param EiInfo
     * perGroupNo : 人员分组编号   
     * perGroupName : 人员分组名称
     * @return EiInfo
     * <author>  // 修改人
     * <time>    // 修改时间
     * <version> // 版本
     * <desc>    // 描述修改内容
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        /*
         * 1.获取前端参数
         */
    	// 获取人员分组编号
        String perGroupNo = (String)inInfo.get("perGroupNo");
        // 获取人员分组名称
        String perGroupName = (String)inInfo.get("perGroupName");
        // 封装数据
        Map map = new HashMap();
        map.put("perGroupNo", perGroupNo);
        map.put("perGroupName", perGroupName);
        /*
         * 2.查询该分组是否已经被引用
         */
        List<Map<String,Long>> query = dao.query("SQFL01.queryProject", map);
        Map<String, Long> map2 = query.get(0);
        Long count = map2.get("count");
        if(count != 0) {
            inInfo.setStatus(-1);
            inInfo.setMsg("分组已被应用，无法删除");
            return inInfo;
        }
        // 删除操作
        int delete = dao.delete("SQFL01.deleteperGroupNo", map);
        if(delete == 0) {
            inInfo.setStatus(-1);
            inInfo.setMsg("删除失败，请联系管理员");
            return inInfo;
        }
        inInfo.setStatus(1);
        inInfo.setMsg("删除成功");
        // 返回
        return inInfo;
    }
    
}
