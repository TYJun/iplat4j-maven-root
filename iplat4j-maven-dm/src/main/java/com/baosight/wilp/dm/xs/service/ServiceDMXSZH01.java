package com.baosight.wilp.dm.xs.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.dm.common.domain.DMConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 宿舍综合查询页面service
 *
 * @Title: ServiceDMZH01.java
 * @ClassName: ServiceDMZH01
 * @Package：com.baosight.wilp.dm.zh.service
 * @author: fangzekai
 * @date: 2022年02月15日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXSZH01 extends ServiceBase {
    /**
     * 页面内查询接口.
     * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
     * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
     * 3、将参数赋给inInfo并调用本地服务DMHS01.query()方法进行列表数据查询.
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
     * 查询宿舍综合信息
     * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
     * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
     * 3、调用本地服务DMZH01.queryZHInfoList()方法进行列表数据查询.
     * @param inInfo
     * @return
     */
    public EiInfo query(EiInfo inInfo) {
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
        inInfo.set("manNature", manNature);
        inInfo.set("role",role);
        // 调用本地服务DMZH01.queryZHInfoList()方法进行列表数据查询.
        inInfo.set(EiConstant.serviceName, "DMZH01");
        inInfo.set(EiConstant.methodName, "queryZHInfoList");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }

    /**
     * 对传入的参数处理，并按照条件查询宿舍的综合信息
     * 1、将要查询的参数组成数组并调用工具类转换参数
     * 2、处理app端角色权限的参数
     * 3、对额外的参数进行处理（role）
     * 4、调用DMZH01.queryZHList方法查询数据
     * @param inInfo
     * @return
     */
    public EiInfo queryZHInfoList(EiInfo inInfo) {
        Map<String, Object> userInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        /*
         * 1、将要查询的参数组成数组并调用工具类转换参数
         */
        String[] param = {"manNo", "manName","deptName", "employmentNature","typeCode","building", "floor","roomNo","statusCode"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
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
        //查询
        List<Map<String, Object>> list = dao.query("DMZH01.queryZHList",map,
                Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
        int count = super.count("DMZH01.countZHList",map);
        // 判断是否存在，存在则构建返回对象
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            inInfo.setMsg("没有查询到数据。");
            return inInfo;
        }
    }

    /**
     * 查询人员状态的相关信息.
     * 1、获取页面传过来的参数(有分页).
     * 2、将构建好的map传入CSRE01.queryStatusCodeList去保洁工单状态表查询保洁状态列表并分页，同时查询状态列表数量.
     *    判断列表对象是否存在，存在则构建返回对象.
     *
     * @Title: queryStatusCodeInfo
     * @param inInfo
     * @return
     *     id ：主键
     *     codeNum ： 状态编码
     *     codeName ： 状态含义
     * @return: EiInfo
     */
    @SuppressWarnings("unchecked")
    public EiInfo queryStatusCodeInfo(EiInfo inInfo) {
        /*
         * 1、获取页面传过来的参数(有分页).
         */
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "statusInfo");
        /*
         * 2、将构建好的map传入DMRZ03.queryStatusCodeList去宿舍状态表查询人员状态列表并分页.
         *    同时去宿舍状态表查询状态列表数量.
         *    判断列表对象是否存在，存在则构建返回对象.
         */
        List<Map<String, Object>> list = dao.query("DMRZ03.queryStatusCodeList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
        int count = super.count("DMRZ03.countStatusCodeList", map);
        // 返回
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "statusInfo", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            return inInfo;
        }
    }



}
