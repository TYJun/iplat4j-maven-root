package com.baosight.wilp.pr.gl.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.pr.gl.domain.PRGL0202;
import com.baosight.xservices.xs.util.UserSession;


/**
 * 
 * 问题挂账逻辑处理:页面初始化加载,保存问题挂账原因
 * <p>1.initLoad    页面初始化加载
 * <p>2.ins         保存问题挂账原因
 * 
 * @Title: ServicePRGL0202.java
 * @ClassName: ServicePRGL0202
 * @Package：com.baosight.wilp.pr.gl.service
 * @author: zhangjiaqian
 * @date: 2021年6月24日 下午4:13:53
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL0202 extends ServiceBase{


    /**
     * 
     * 页面初始化加载
     * 
     * @param inInfo
     * <p>1.id          问题id
     * @return
     * <p>1.dangercode  问题编码
     * <p>2.dangerid    整改id
     * 代码逻辑：
     * 1.获取问题id
     * 2.校验参数
     * 3.获取问题信息
     * 4.将问题编码传递到前端展示
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        
        //1.获取问题id
        String id = (String)inInfo.get("id");
        //2.校验参数
        if(null == id) {
            inInfo.setStatus(-1);
            inInfo.setMsg("系统错误，请联系管理员");
            return inInfo;
        }
        //3.获取问题信息
        Map map = new HashMap();
        map.put("id", id);
        List<PRGL0202> query = dao.query("PRGL0202.query", map);
        if(query.isEmpty()){
            return inInfo;
        }
        PRGL0202 prgl0202 = query.get(0);
        //4.将问题编码传递到前端展示
        inInfo.set("dangercode", prgl0202.getDangercode());
        inInfo.set("id", id);
        return inInfo;
    }


    
    /**
     * 
     * 保存问题挂账原因
     *
     * @Title: ins 
     * @param inInfo
     * <p>1.rejectreason    挂账原因
     * <p>2.id              问题id
     * 代码逻辑：
     * 1.获取参数
     * 2.构建日志参数
     * 3.构建状态参数
     * 4.返回参数
     * @return 
     * @return: EiInfo
     */
    public EiInfo ins(EiInfo inInfo) {

        //1.获取参数
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        //挂账原因
        String rejectreason = (String)inInfo.get("rejectreason");
        //获取问题id
        String id = (String)inInfo.get("id");
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();


        //2.构建日志参数
        Map<String,String> map = new HashMap();
        map.put("dangerID", id);
        map.put("logsMan", (String)staffByUserId.get("name"));
        map.put("logsNo", (String)staffByUserId.get("workNo"));
        map.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
        map.put("rejectStatus", "20");
        map.put("rejectReason", rejectreason);
        map.put("dataGroupCode", dataGroupCode);
        //保存问题日志
        dao.insert("PRGL0202.logs", map);
        
        //3.构建状态参数
        Map stat = new HashMap();
        stat.put("id", id);
        stat.put("statusCode", "20");
        //修改当前问题状态
        int update = dao.update("PRGL0202.updateStatusCode", stat);
        //4.返回参数
        return inInfo;
    }


}
