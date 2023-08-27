package com.baosight.wilp.dm.au.service; /**
 *@Name ServiceDMHM0101.java
 *@Description TODO
 *@Date 2021年5月3日 下午2:49:24
 *@Version 1.0
 **/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;

/**
 * TODO(根据是新增操作还是编辑操作执行不同逻辑)
 *
 * @Title: ServiceDMHM0101.java
 * @ClassName: ServiceDMHM0101
 * @Package：com.baosight.wilp.dm.hm.service
 * @author: 辉
 * @date: 2021年11月24日 下午1:53:55
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMAU0101 extends ServiceBase{

    /**
     * TODO(初始化页面加载)
     * @title initLoad
     * @param resquest 请求入参 {}
     * @return inInfo
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        // {realName=李德胜, deptName=外科, deptNum=sc04, dataGroupCode=null, userName=admin, tellphone=13777777777}
        //获取当前人信息
        Map<String, Object> userInfo = getUserInfo();
//        if(userInfo !=null){
//            inInfo.set("employeeId", userInfo.get("userName"));
//            inInfo.set("manName",  userInfo.get("realName"));
//            inInfo.set("departmentNo", userInfo.get("deptNum"));
//            inInfo.set("departmentName", userInfo.get("deptName"));
//            inInfo.set("phone", userInfo.get("tellphone"));
//        }
        Map<String, String> map = new HashMap<>();
        map.put("id", inInfo.getAttr().get("id").toString());
        String type = inInfo.getAttr().get("type").toString();
        //如果是编辑操作查询入住人员信息表
        if("edit".equals(type)) {
            List<Map<String, Object>> list = dao.query("dMAU01.queryView", map);
            //判断查询结果是否为空
            if (!CollectionUtils.isEmpty(list)) {
                list.get(0).put("type", type);
                // 解决DatePicker 回显问题
                list.get(0).put("workTime", list.get(0).get("workTime").toString());
                inInfo.setAttr(list.get(0));
            }
        }
        return inInfo;
    }

    /**
     * TODO(这里用一句话描述这个方法的作用)
     * @Title: getUserInfo
     * @return
     * @return: Map<String,Object>
     */
    private Map<String, Object> getUserInfo() {
        // TODO Auto-generated method stub
        return null;
    }
}

