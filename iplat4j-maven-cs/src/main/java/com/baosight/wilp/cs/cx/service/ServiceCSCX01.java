package com.baosight.wilp.cs.cx.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.cs.common.CSUtils;
import com.baosight.wilp.cs.re.domain.CsConstant;
import com.baosight.xservices.xs.util.UserSession;

import java.util.Map;

/**
 * 保洁综合查询管理主页面Service.
 * 一、保洁评价查询页面加载.
 * 二、页面内查询接口.
 *
 * @Title: ServiceCSCX01
 * @ClassName: ServiceCSCX01.java
 * @Package：com.baosight.wilp.cs.cx.service
 * @author: fangzekai
 * @date: 2021/11/20 1:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSCX01  extends ServiceBase{

    /**
     * 一.保洁综合查询页面加载
     *
     * @Title: initLoad
     * @param inInfo
     * @return   
     * @see ServiceBase#initLoad(EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 二、页面内查询接口.
     *  1、获取当前登录人信息，如果登录人不存在，提示错误信息.
     *  2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
     *  3. 将参数赋给inInfo并调用本地服务CSRE01.queryTaskList()方法进行列表数据查询.
     *     添加参数department、view.
     *     view：标记，与department配合使用，表示查询当前登录人或当前登录人科室的人创建的任务.
     *
     * @Title: query
     * @param inInfo
     *      taskNo：任务单号
     *      reqDeptName：来电科室名称
     *      recCreateTimeS：来电时间起(>=)
     *      recCreateTimeE：来电时间止(<=)
     * @return
     *      taskNo ： 工单号
     *      codeName ： 工单状态
     *      reqStaffName ： 来电人姓名
     *      reqTelNum ： 来电电话
     *      reqDeptName ： 来电科室
     *      building ： 楼
     *      floor ： 层
     *      reqSpotName ： 保洁地点
     *      serviceDept ： 服务科室
     *      comments ： 备注
     *      recCreateTime ： 创建时间
     *
     * @see ServiceBase#query(EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        /*
         * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
         */
        //获取当前登录人信息，如果登录人不存在，提示错误信息
        Map<String, Object> userInfo = CSUtils.getUserInfo(null);
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo; 
        }
        /*
         * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
         */
        String role = (String) userInfo.get("role");
        String view = "";
        // 判断当前登录人的用户角色，如果为调度中心或管理员，则查询的权限为调度中心的内容权限设置.
        // 否则为普通角色权限.
        if(role.contains(CsConstant.ROLE_DDZX) || role.contains(CsConstant.ROLE_ADMIN) || role.contains(CsConstant.ROLE_BJBZRY)){
            view = CsConstant.ROLE_DDZX;
        }else if(role.contains(CsConstant.ROLE_BJZXR)){
            view = CsConstant.ROLE_BJZXR;
        }else {
            view = CsConstant.ROLE_NORMAL;
        }
        inInfo.set("workNo", UserSession.getUser().getUsername());
        /*
         * 3. 将参数赋给inInfo并调用本地服务CSRE01.queryTaskList()方法进行列表数据查询.
         *    添加参数department、view.
         *    view：标记，与department配合使用，表示查询当前登录人或当前登录人科室的人创建的任务.
         */
        inInfo.set("department", userInfo.get("deptNum") == null ? "" : userInfo.get("deptNum").toString());
        inInfo.set("view", view);
        inInfo.set(EiConstant.serviceName, "CSRE01");
        inInfo.set(EiConstant.methodName, "queryTaskList");
        EiInfo outInfo =XLocalManager.call(inInfo);
        return outInfo;
    }
	
}
