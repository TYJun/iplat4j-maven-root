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
import com.baosight.wilp.pr.gl.domain.PRGL03;
import com.baosight.wilp.pr.gl.domain.PRGL04;
import com.baosight.xservices.xs.util.UserSession;


/**
 * 
 * 问题挂账逻辑处理层:页面初始化方法,页面查询方法,取消挂账,安全问题分类下拉框
 * <p>1.initLoad        页面初始化方法
 * <p>2.query           页面查询方法
 * <p>3.upd             取消挂账
 * <p>4.safty           安全问题分类下拉框
 * 
 * 
 * @Title: ServicePRGL04.java
 * @ClassName: ServicePRGL04
 * @Package：com.baosight.wilp.pr.gl.servtypeNameice
 * @author: zhangjiaqian
 * @date: 2021年6月24日 下午4:31:18
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRGL04 extends ServiceBase{


    /**
     * 
     * 页面初始化加载方法
     * 
     * @param inInfo
     * <p>1.typecode        分类编码
     * <p>2.beginDate       开始时间
     * <p>3.endDate         结束时间
     * @return
     * <p>1.statusCode      流程状态
     * <p>2.id              问题id
     * <p>3.dangercode      问题编码
     * <p>4.dangerwhere     问题地点
     * <p>5.requiredesc     整改要求
     * <p>6.contentdesc     描述
     * <p>7.requiredtime    整改要求时间
     * <p>8.dangerlevel     问题等级
     * <p>9.typeName        问题分类名称
     * <p>10.rejectReason   拒绝原因
     * <p>11.realName       操作人
     * <p>12.logsTime       操作时间
     * <p>13.createman      创建人
     * <p>14.createtime     创建时间
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "PRGL04.query", new PRGL04());
        return outInfo;
    }


    /**
     * 
     * 页面查询方法
     * 
     * @param inInfo
     * <p>1.typecode        分类编码
     * <p>2.beginDate       开始时间
     * <p>3.endDate         结束时间
     * @return
     * <p>1.statusCode      流程状态
     * <p>2.id              问题id
     * <p>3.dangercode      问题编码
     * <p>4.dangerwhere     问题地点
     * <p>5.requiredesc     整改要求
     * <p>6.contentdesc     描述
     * <p>7.requiredtime    整改要求时间
     * <p>8.dangerlevel     问题等级
     * <p>9.typeName        问题分类名称
     * <p>10.rejectReason   拒绝原因
     * <p>11.realName       操作人
     * <p>12.logsTime       操作时间
     * <p>13.createman      创建人
     * <p>14.createtime     创建时间
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "PRGL04.query", new PRGL04());
        return outInfo;
    }

    
    /**
     * 
     * 取消挂账
     *
     * @Title: upd 
     * @param inInfo
     * <p>1.id  问题id
     * 代码逻辑:
     * 1.获取参数
     * 2.参数非空校验
     * 3.修改问题状态并记录日志操作
     * 4.返回问题
     * @return
     * @return: EiInfo
     */
    public EiInfo upd(EiInfo inInfo) {

        //1.获取参数
        //获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        //获取院区编码
        String dataGroupCode = DatagroupUtil.getDatagroupCode();
        //获取问题id
        String id = (String)inInfo.get("id");
        //2.参数非空校验
        if(null == id) {
            inInfo.setStatus(-1);
            inInfo.setMsg("系统错误，请联系管理员");
        }else {
            //3.修改问题状态并记录日志操作
            Map par = new HashMap();
            par.put("id", id);
            par.put("statusCode", "19");
            //修改问题状态为 挂账待认领
            int update = dao.update("PRGL04.updateStatusCode", par);
            //构建日志参数
            Map map = new HashMap();
            map.put("dangerID", id);
            map.put("logsMan", (String)staffByUserId.get("name"));
            map.put("logsNo", (String)staffByUserId.get("workNo"));
            map.put("logsTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
            map.put("rejectStatus", "19");
            map.put("rejectReason", " ");
            map.put("dataGroupCode", dataGroupCode);
            //保存问题日志
            dao.insert("PRGL04.logs", map);
            //判断数据库返回状态，构建返回信息给页面
            if(0 != update) {
                inInfo.setStatus(1);
                inInfo.setMsg("操作成功");
            }else {
                inInfo.setStatus(-1);
                inInfo.setMsg("系统错误，请联系管理员");
            }
        }
        //4.返回问题
        return inInfo;
    }



    /**
     * 安全问题分类下拉框
     * @param inInfo
     * @return
     */
    public EiInfo safty(EiInfo inInfo) {
        Map map = new HashMap();
        List<PRGL03> list = dao.query("PRGL03.querySafty", new HashMap());
        inInfo.addBlock("safty").addRows(list);
        inInfo.getBlock("safty").setBlockMeta(new PRGL03().eiMetadata);
        return inInfo;
    }
}
