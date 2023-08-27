package com.baosight.wilp.dm.xs.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;

import java.util.Map;

/**
 * 宿舍退宿页面service
 *
 * @Title: ServiceDMTS01.java
 * @ClassName: ServiceDMTS01
 * @Package：com.baosight.wilp.dm.ts.service
 * @author: fangzekai
 * @date: 2022年02月09日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXSTS01 extends ServiceBase {
    /**
     * 页面内查询接口.
     * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
     * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
     * 3、将参数赋给inInfo并调用本地服务DMHS01.query()方法进行列表数据查询.
     * @Title: initLoad
     * @param inInfo
     * @return
     *
     * @see ServiceBase#initLoad(EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 查询宿舍退宿审核信息
     * 1、获取当前登录人信息，如果登录人不存在，提示错误信息；
     * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分；
     * 3、将参数赋给inInfo并调用本地服务DMHS01.queryHSInfoList方法进行列表数据查询.
     * @param inInfo
     * @return
     */
    public EiInfo query(EiInfo inInfo){
        /*
         * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
         */
        // 调用工具类CSUtils查登陆人的用户信息
        Map<String, Object> userInfo = DMUtils.getUserInfo(null);
        // 判断账号是否为空，为空则提示。
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        /*
         * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
         */
        String role = (String) userInfo.get("role");
        // 获取前端的人员大类值（manNature）
        String manNature = inInfo.getString("manNature");
        // 判断当前登录人的用户角色，如果为宿管/宿舍审批人，则查询的权限为管理员权限设置.
        // 否则只能查看自己的相关信息.
        if(role.contains("ADMIN")){
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null){
                manNature = "学生";
            }
            role = "DMADMIN";
        }else if(role.contains("DMSPR_XSSS")){
            // 为学生审批人时，查询属性隐藏，只查为学生的相关信息。
            manNature = "学生";
            role = "DMSPR_XSSS";
        }else if(role.contains("DMSPR_ZGSS")){
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null){
                manNature = "学生";
            }
            role = "DMSPR_ZGSS";
        }else {
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null){
                manNature = "学生";
            }
            role = "DMZSR";
        }
        /*
         * 3、将参数赋给inInfo并调用本地服务DMHS01.queryHSInfoList方法进行列表数据查询.
         * 添加参数view.
         * role：标记.
         */
        inInfo.set("manNature", manNature);
        inInfo.set("role", role);
        inInfo.set("statusCode","04");
        inInfo.set(EiConstant.serviceName, "DMHS01");
        inInfo.set(EiConstant.methodName, "queryHSInfoList");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }


}
