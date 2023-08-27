package com.baosight.wilp.dm.pj.service;

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
 * 宿舍管理员评价页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMPJ03.java
 * @ClassName: ServiceDMPJ03
 * @Package：com.baosight.wilp.dm.pj.service
 * @author: fangzekai
 * @date: 2022年04月29日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMPJ03 extends ServiceBase {
    /**
     * 页面内查询接口.
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
     * 此方法用于宿舍管理员评价页面列表数据查询
     *
     * 逻辑处理：
     * 1、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
     * 2、将参数赋给inInfo并调用本地服务DMHS01.query()方法进行列表数据查询.
     * 3、调用本地服务DMPJ03.queryADMINPJInfoList()方法进行列表数据查询.
     *
     * @Title: query
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    public EiInfo query(EiInfo inInfo) {
        Map<String, Object> userInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        /*
         * 1、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
         */
        String role = (String) userInfo.get("role");
        // 获取前端的人员大类值（manNature）
        String manNature = inInfo.getString("manNature");
        // 判断当前登录人的用户角色，如果为宿管/宿舍审批人，则查询的权限为管理员权限设置.
        // 否则只能查看自己的相关信息.
        if(role.contains("ADMIN")){
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null){
                manNature = "职工";
            }
            role = "DMADMIN";
        }else if(role.contains("DMSPR_XSSS")){
            // 为学生审批人时，查询属性隐藏，只查为学生的相关信息。
            manNature = "学生";
            role = "DMSPR_XSSS";
        }else if(role.contains("DMSPR_ZGSS")){
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null){
                manNature = "职工";
            }
            role = "DMSPR_ZGSS";
        }else {
            // 首次加载的时候过滤条件会为null，所以得进行判定。
            if (manNature == null){
                manNature = "职工";
            }
            role = "DMZSR";
        }
        /*
         * 2、将参数赋给inInfo并调用本地服务DMHS01.query()方法进行列表数据查询.
         * 添加参数view.
         * role：角色.
         */
        inInfo.set("manNature", manNature);
        inInfo.set("role", role);

        /**
         * 3、调用本地服务DMPJ03.queryADMINPJInfoList()方法进行列表数据查询.
         */
        inInfo.set(EiConstant.serviceName, "DMPJ03");
        inInfo.set(EiConstant.methodName, "queryADMINPJInfoList");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }

    /**
     * 此方法用于管理员评价页面查询可评价的入住信息
     *
     * 逻辑处理：
     * 1、将要查询的参数组成数组并调用工具类转换参数
     * 2、调用sql方法DMPJ03.queryRZListInfo查询入住信息
     *
     * @Title: queryADMINPJInfoList
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    public EiInfo queryADMINPJInfoList(EiInfo inInfo) {
        /*
         * 1、将要查询的参数组成数组并调用工具类转换参数
         */
        String[] param = {"manNo", "manName","deptName","building", "floor","roomNo"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
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
        // 额外角色参数进行处理
        String role = inInfo.getString("role");
        if(StringUtils.isNotBlank(role) && role.contains("DMADMIN")) {
            map.put("role", role);
            map.put("manNatureList",manNatureList);
        }else if(StringUtils.isNotBlank(role) && role.contains("DMSPR")){
            map.put("role", "DMSPR");
            map.put("manNatureList", manNatureList);
        }else if(StringUtils.isNotBlank(role)){
            map.put("role", role);
            map.put("workNo",inInfo.getString("workNo"));
            map.put("manNatureList", manNatureList);
        }
        //查询
        /**
         * 2、调用sql方法DMPJ03.queryRZListInfo查询入住信息
         */
        List<Map<String, Object>> list = dao.query("DMPJ03.queryRZListInfo",map,
                Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
        int count = super.count("DMPJ03.countRZListInfo",map);
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
