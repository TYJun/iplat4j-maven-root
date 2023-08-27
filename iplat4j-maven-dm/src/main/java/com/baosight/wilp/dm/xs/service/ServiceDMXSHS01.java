package com.baosight.wilp.dm.xs.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.dm.common.domain.DMConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 宿舍换宿页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMHS01.java
 * @ClassName: ServiceDMHS01
 * @Package：com.baosight.wilp.dm.hs.service
 * @author: fangzekai
 * @date: 2022年02月09日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXSHS01 extends ServiceBase {
    /**
     * 页面内查询接口.
     * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
     * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
     * 3、将参数赋给inInfo并调用本地服务DMHS01.query()方法进行列表数据查询.
     * 添加参数view.
     * view：标记.
     *
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
     * 查询宿舍换宿申请信息
     * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
     * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
     * 3、将参数赋给inInfo并调用本地服务DMHS01.query()方法进行列表数据查询.
     * @param inInfo
     * @return
     */
    public EiInfo query(EiInfo inInfo) {
        /*
         * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
         */
        // 调用工具类DMUtils查登陆人的用户信息
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
            if (manNature == null){
                manNature = "学生";
            }
            role = "DMZSR";
        }
        /*
         * 3、将参数赋给inInfo并调用本地服务DMHS01.query()方法进行列表数据查询.
         * 添加参数view.
         * role：角色.
         */
        inInfo.set("manNature", manNature);
        inInfo.set("role", role);
        inInfo.set("statusCode","04");
        // 调用本地服务DMRZ01.queryRZInfoList()方法进行列表数据查询.
        inInfo.set(EiConstant.serviceName, "DMHS01");
        inInfo.set(EiConstant.methodName, "queryHSInfoList");
        EiInfo outInfo = XLocalManager.call(inInfo);
        //返回查询数据
        return outInfo;
    }

    /**
     * 查询换宿申请信息
     *
     * 1.将要查询的参数组成数组并调用工具类转换参数
     * 2.处理app端角色权限的参数
     * 3.对额外的参数进行处理.
     * 4.调用DMHS01.queryCanChangeList方法查询数据
     *
     * @Title: queryHSInfoList
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    public EiInfo queryHSInfoList(EiInfo inInfo) {
        Map<String, Object> userInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        /*
         * 1、将要查询的参数组成数组并调用工具类转换参数
         */
        String[] param = {"manNo", "manName","deptName","building", "floor","roomNo","statusCode"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        map.put("roomId",inInfo.getString("roomId"));
        /*
         * 2、处理app端角色权限的参数
         * 对应的角色对应对应的限制：管理员权限不做限制，普通角色只能看见自己的信息
         */
        // APP端权限处理
        if(StringUtils.isNotBlank(inInfo.getString("workNo"))) {
            map.put("workNo",inInfo.getString("workNo"));
            // 获取该工号的所有相关信息，包含权限
            Map<String, Object> userAllInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
            String role = userAllInfo.get("role").toString();
            if(role.contains("ADMIN") || role.contains("DMSPR")) {    // 管理员
                map.put("role", "DMADMIN");
            }else {
                map.put("role", "DMZSR");    // 普通角色
                map.put("workNo",inInfo.getString("workNo"));
            }
        }
        // 判断人员大类的选择
        List manNatureList = new ArrayList();
        String manNature = inInfo.getString("manNature");
        if (manNature!=null && manNature.equals("学生")){
            String [] XSLX = DMConstant.XSLX;
            manNatureList = Arrays.asList(XSLX);
        }else if (manNature!=null && manNature.equals("职工")){
            String [] ZGLX = DMConstant.ZGLX;
            manNatureList = Arrays.asList(ZGLX);
        }
        /*
         * 3、对额外的参数进行处理.
         * role    方法传来的查看角色值.
         */
        // 额外角色参数进行处理
        String role = inInfo.getString("role");
        // 判断传来的值是否为宿舍管理员权限，是的话会将管理员的权限传给sql.
        if(StringUtils.isNotBlank(role) && role.contains("DMADMIN")) {
            map.put("role", role);
            map.put("manNatureList",manNatureList);
        }else if(StringUtils.isNotBlank(role) && role.contains("DMSPR")){
            map.put("role", "DMSPR");
            map.put("manNatureList", manNatureList);
        }else if(StringUtils.isNotBlank(role)){
            map.put("role", role);
            map.put("workNo", UserSession.getUser().getUsername());
        }

        /**
         * 4.调用DMHS01.queryCanChangeList方法查询数据
         */
        List<Map<String, Object>> list = dao.query("DMHS01.queryCanChangeList",map,
                Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
        int count = super.count("DMHS01.countCanChangeList",map);
        // 判断是否存在，存在则构建返回对象
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            inInfo.setMsg("没有查询到数据。");
            return inInfo;
        }
    }

    /**
     * 人员与宿舍之间的信息详情查询.
     * 根据工号workNo，查询指定的详情.
     * 1、获取前端传来的人员工号.
     * 2、将workNo值放入map给DMHS01.queryManAboutInfo 做参数去查询未完工工单详情信息.
     * 3、判断是否取得数据.
     *
     * @Title: queryManAboutInfo
     * @param:  @param inInfo
     *      taskId： 任务单号id
     * @param:  @return
     * @return: EiInfo
     *
     * @throws
     */
    public EiInfo queryManAboutInfo(EiInfo inInfo) {
        /*
         * 1、获取前端传来的人员工号.
         */
        String workNo = "";
        String statusCode = "";
        if(inInfo.get("workNo") != null || !"".equals(inInfo.get("workNo"))) {
            workNo = inInfo.getString("workNo");
        }
        if(inInfo.get("statusCode") != null || !"".equals(inInfo.get("statusCode"))) {
            statusCode = inInfo.getString("statusCode");
        }
        /*
         * 2、将workNo值放入map给DMHS01.queryManAboutInfo 做参数去查询未完工工单详情信息.
         */
        Map<String, String> map = new HashMap<>();
        map.put("workNo", workNo);
        map.put("statusCode", statusCode);
        EiInfo outInfo = new EiInfo();
        List<Map<String, String>> list = dao.query("DMHS01.queryManAboutInfo", map);
        /*
         * 3、判断是否取得数据.
         */
        if (CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("未查到数据");
            outInfo.setStatus(-1);
            return outInfo;
        }
        outInfo.setAttr(list.get(0));
        return outInfo;
    }

}
