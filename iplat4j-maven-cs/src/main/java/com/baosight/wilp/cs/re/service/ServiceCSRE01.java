 package com.baosight.wilp.cs.re.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.cs.common.CSUtils;
import com.baosight.wilp.cs.common.CsBaseDockingUtils;
import com.baosight.wilp.cs.common.MTUtils;
import com.baosight.wilp.cs.re.domain.CsConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  保洁登记页面Service，包含一些通用方法
 *  一、保洁登记页面加载.
 *  二、页面内查询接口.
 *  三、工单列表查询.
 *  四、未完工工单列表查询.
 *  五、工单详情查询.
 *  六、未完工工单详情查询.
 *  七、保洁主表工单保存接口.
 *  八、保洁工单更新.
 *  九、保洁工单更新状态.
 *  十、保洁明细表工单保存接口.
 *  十一、更新保洁工单明细表的工单状态.
 *  十二、保洁工单流程表工单流程保存接口.
 *  十三、工单撤销的实现接口.
 *  十四、获取指定用户所在的用户组.
 *  十五、查询工单状态的相关信息.
 *  十六、选择人员（有分页）.
 *  十七、工单登记来电人选择人员（有分页）.
 *  十八. 获取人员信息（无分页）.
 *  十九、科室查询.
 *  二十、获取所有的服务科室.
 *  二十一、通过科室查询地点信息，根据科室自动补全.
 *  二十二、楼补全.
 *  二十三、层补全.
 *  二十四、地点补全.
 *  二十五、来电电话补全查询科室/地点信息.
 *  二十六、查询单号.
 *  二十七、更新单号.
 *
 * @Title: ServiceCSRE01.java
 * @ClassName: ServiceCSRE01
 * @Package：com.baosight.wilp.cs.re.service
 * @author: fangzekai
 * @date: 2021年11月17日 下午7:55:20
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * 
 */
 public class ServiceCSRE01 extends ServiceBase {
     
     /**
      * 一、保洁登记页面加载.
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
      * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
      * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
      * 3、将参数赋给inInfo并调用本地服务CSRE01.queryTaskList()方法进行列表数据查询.
      *    添加参数、statusCode、department、view.
      *    statusCode：只查询已登记，未被确认的任务.
      *    view：标记，与department配合使用，表示查询当前登录人或当前登录人科室的人创建的任务.
      * 
      * @Title: query
      * @param inInfo
      *      taskNo：任务单号
      *      reqDeptName：来电科室名称
      *      recCreateTimeS：来电时间起(>=)
      *      recCreateTimeE：来电时间止(<=)
      * @return
      *      taskId ： 工单主键
      *      taskNo ： 工单号
      *      statusCode ： 工单状态
      *      codeName ： 工单状态名称
      *      reqStaffName ： 来电人姓名
      *      reqTelNum ： 来电电话
      *      reqDeptName ： 来电科室
      *      reqSpotName ： 保洁地点名
      *      itemName ： 工单保洁事项
      *      serDeptName ： 工单事项服务科室名称
      *      comments ： 备注
      *      recCreateName ： 登记人姓名
      *      recCreateTime ： 登记时间
      *      recReviseName ： 修改人姓名
      *      recReviseTime ： 修改时间
      *      
      * @see ServiceBase#query(EiInfo)
      */
     @Override
     public EiInfo query(EiInfo inInfo) {
         /*
          * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
          */
         // 调用工具类CSUtils查登陆人的用户信息
         Map<String, Object> userInfo = CSUtils.getUserInfo(null);
         // 判断账号是否为空，为空则提示。
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
         // 否则只能查看自己登记/自己班组登记的工单.
         if(role.contains(CsConstant.ROLE_DDZX) || role.contains(CsConstant.ROLE_ADMIN)){
             view = CsConstant.ROLE_DDZX;
         }else {
             view = CsConstant.ROLE_NORMAL;
         }
         /* 3、将参数赋给inInfo并调用本地服务CSRE01.queryTaskList()方法进行列表数据查询.
          * 添加参数、statusCode、department、view.
          * statusCode：只查询已登记，未被确认的任务.
          * view：标记，与department配合使用，表示查询当前登录人或当前登录人科室的人创建的任务.
          */
         inInfo.set("view", view);
         inInfo.set("statusCode", "01");
         inInfo.set("department", userInfo.get("deptNum") == null ? "" : userInfo.get("deptNum").toString());
         return queryTaskList(inInfo);
     }


    /**
     * 三、工单列表查询.
     * 1、将要查询的参数组成数组并调用工具类转换参数.
     * 2、对type参数进行判断处理.
     *   当为1未完工时，仅显示状态为01待确认和02待完工的工单.
     *   当为2已完工时，仅显示状态为03待评价,99已结束的工单.
     *   当为3我的工单时，仅显示自己登记的工单.
     * 3、处理app端角色权限的参数.
     *   对应的角色对应对应的限制：调度中心不做限制，普通角色/保洁班组人员只能看见自己的工号和自己班组的工单.
     * 4、对额外的参数进行处理.
     *   statusCode、statusCode1 方法传来的状态值.
     *   view    方法传来的查看角色值.
     * 5、将构建好的map传入CSRE01.queryTaskList进行查询并分页，同时查询列表数量.
     *   判断列表对象是否存在，存在则构建返回对象.
     *
     * @Title: queryTaskList
     * @param inInfo
     *     taskNo ： 工单号
     *     statusCode ： 工单状态
     *     printFlag ： 打印标记
     *     reqStaffId ： 来电人工号
     *     reqDeptNum ： 来电科室编码
     *     reqDeptName ： 来电科室名称
     *     recCreateTimeS ： 登记时间起
     *     recCreateTimeE ： 登记时间止
     * @return: EiInfo
     *      taskId ： 主键
     *      taskNo ： 工单号
     *      statusCode ： 工单状态
     *      codeName ： 工单状态名称
     *      reqStaffId ： 来电人工号
     *      reqStaffName ： 来电人姓名
     *      reqTelNum ： 来电电话
     *      reqDeptNum ： 来电科室编码
     *      reqDeptName ： 来电科室
     *      reqSpotNum ： 保洁地点编码
     *      reqSpotName ： 保洁地点名
     *      building ： 楼
     *      floor ： 层
     *      comments ： 备注
     *      recCreator ： 登记人工号
     *      recCreateName ： 登记人姓名
     *      recCreateTime ： 登记时间
     *      recRevisor ： 修改人工号
     *      recReviseName ： 修改人姓名
     *      recReviseTime ： 修改时间
     *      confirmUserNo ： 确认人工号
     *      confirmUserName ： 确认人
     *      confirmTime ： 确认时间
     *      finishUserNo ： 完工人工号
     *      finishUserName ： 完工人
     *      finishTime ： 完工时间
     *      evalGrade ： 评价等级
     *      evalOpinion ： 评价意见
     *      evalUserNo ： 评价人工号
     *      evalUserName ： 评价人
     *      evalTime ： 评价时间
     *      printFlag ： 打印标记
     *      archiveFlag ： 归档标记
     *      datagroupCode ： 账套
     *      itemTypeCode ： 工单保洁事项分类编码
     *      itemTypeName ： 工单保洁事项分类
     *      itemCode ： 工单保洁事项编码
     *      itemName ： 工单保洁事项
     *      serDeptNum ： 工单事项服务科室编码
     *      serDeptName ： 工单事项服务科室名称
     *      executeUserNo ： 保洁执行人工号
     *      executeUserName ： 保洁执行人姓名
     */
    @SuppressWarnings("unchecked")
    public EiInfo queryTaskList(EiInfo inInfo) {
        /*
         * 1、将要查询的参数组成数组并调用工具类转换参数
         */
        String[] param = {"taskNo", "statusCode","printFlag", "reqStaffId", "reqDeptNum", "reqDeptName",
                "recCreateTimeS", "recCreateTimeE"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        map.put("dataGroupCode", inInfo.getString("dataGroupCode"));
        /*
         * 2、对type参数进行判断处理。
         * 当为2已完工时，仅显示状态为03待评价,99已结束的工单
         * 当为3我的工单时，仅显示自己登记的工单.
         */
        //APP查询已完工（对参数type进行处理）
        if(CsConstant.APP_TYPE_FINISH.equals(inInfo.getString("type"))) {
            map.put("statusCodes", Arrays.asList(new String[]{"03","99"}));
        }else if (CsConstant.APP_TYPE_MYTASK.equals(inInfo.getString("type"))){
//             map.put("reqStaffId",inInfo.getString("workNo"));
        } //APP查询我的工单
        /*
         * 3、处理app端角色权限的参数.
         * 对应的角色对应对应的限制：调度中心不做限制，普通角色/保洁班组人员只能看见自己的工号和自己班组的工单.
         */
        // APP端权限处理
        if(StringUtils.isNotBlank(inInfo.getString("workNo"))) {
            map.put("workNo",inInfo.getString("workNo"));
            // 获取该工号的所有相关信息，包含权限
            Map<String, Object> userInfo = CSUtils.getUserInfo(inInfo.getString("workNo"));
            String role = userInfo.get("role").toString();
            // 判断角色值，当角色值为调度中心/保洁班组时，则查询所有状态的工单.
            // 当角色值为保洁执行人时，仅显示自己班组的所有工单.
            // 当角色值为普通角色时，显示自己的工单以及自己班组的工单.
            if(role.contains(CsConstant.ROLE_DDZX) || role.contains(CsConstant.ROLE_ADMIN)) {    // 调度中心
                map.put("role", CsConstant.ROLE_DDZX);
            }else if(!role.contains(CsConstant.ROLE_DDZX) && role.contains(CsConstant.ROLE_BJBZRY)){ //保洁班组人员
                map.put("role", CsConstant.ROLE_BJBZRY);
                map.put("department",CSUtils.isEmpty(userInfo.get("deptNum")));
            }else if(!role.contains(CsConstant.ROLE_DDZX) && role.contains(CsConstant.ROLE_BJZXR)){ //保洁执行人人员
                map.put("role", CsConstant.ROLE_BJZXR);
                map.put("workNo",inInfo.getString("workNo"));
            }else {
                map.put("role", CsConstant.ROLE_NORMAL);    // 普通角色
                map.put("workNo",inInfo.getString("workNo"));
                map.put("department",CSUtils.isEmpty(userInfo.get("deptNum")));
            }
        }
        /*
         * 4、对额外的参数进行处理.
         * statusCode、statusCode1 方法传来的状态值.
         * view    方法传来的查看角色值.
         */
        //额外参数处理
        // 额外参数状态的处理
        List<String> statusCodes = new ArrayList<>();
        if(inInfo.get("statusCode")!=null&&!"".equals(inInfo.get("statusCode"))) {
            statusCodes.add(inInfo.getString("statusCode"));
        }
        if(inInfo.get("statusCode1")!=null&&!"".equals(inInfo.get("statusCode1"))) {
            statusCodes.add(inInfo.getString("statusCode1"));
        }
        if(statusCodes.size() > 0) {
            map.put("statusCodes", statusCodes);
        }
        // 额外角色参数进行处理
        String view = inInfo.getString("view");
        // 判断传来的值是否为调度中心，是的话会将调度中心的权限传给sql.
        if(StringUtils.isNotBlank(view) && view.equals(CsConstant.ROLE_DDZX)) { // 调度中心
            map.put("role", view);
            // 否的话查出来的工单就只显示来电人/保洁班组自己或自己班组的工单.
        }else if(StringUtils.isNotBlank(view)){
            map.put("role", view);    // 来电人/保洁班组
            map.put("workNo",UserSession.getUser().getUsername());
            map.put("department",inInfo.getString("department"));
        }
        /*
         * 5. 将构建好的map传入CSRE01.queryTaskList进行查询并分页，同时查询列表数量
         *    判断列表对象是否存在，存在则构建返回对象。
         */
        // 执行CSRE01.queryTaskList查询工单列表
        List<Map<String, Object>> list = dao.query("CSRE01.queryTaskList",map,
                Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
        int count = super.count("CSRE01.count",map);
        // 判断是否存在，存在则构建返回对象
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            return inInfo;
        }
    }


    /**
     * 四、未完工工单列表查询.
     * 1、将要查询的参数组成数组并调用工具类转换参数.
     * 2、对type参数进行判断处理.
     *    当为1待完工时，仅显示状态为02待完工的工单.
     *    预留“我的工单”选项.
     * 3、处理app端角色权限的参数.
     *   对应的角色对应对应的限制：调度中心不做限制，普通角色/保洁班组人员只能看见自己的工号和自己班组的工单.
     * 4、对额外的参数进行处理.
     *   statusCode,statusCode1  方法传来的状态值.
     *   view    方法传来的查看角色值.
     * 5、将构建好的map传入CSRE01.queryUnFinishTaskList进行查询并分页，同时查询列表数量.
     *   判断列表对象是否存在，存在则构建返回对象.
     *
     * @Title: queryUnFinishTaskList
     * @param inInfo
     *     taskNo ： 工单号
     *     statusCode ： 工单状态
     *     printFlag ： 打印标记
     *     reqStaffId ： 来电人工号
     *     reqDeptNum ： 来电科室编码
     *     reqDeptName ： 来电科室名称
     *     recCreateTimeS ： 登记时间起
     *     recCreateTimeE ： 登记时间止
     * @return: EiInfo
     *      taskId ： 主键
     *      taskNo ： 工单号
     *      statusCode ： 工单状态
     *      codeName ： 工单状态名称
     *      reqStaffId ： 来电人工号
     *      reqStaffName ： 来电人姓名
     *      reqTelNum ： 来电电话
     *      reqDeptNum ： 来电科室编码
     *      reqDeptName ： 来电科室
     *      reqSpotNum ： 保洁地点编码
     *      reqSpotName ： 保洁地点名
     *      building ： 楼
     *      floor ： 层
     *      comments ： 备注
     *      recCreator ： 登记人工号
     *      recCreateName ： 登记人姓名
     *      recCreateTime ： 登记时间
     *      recRevisor ： 修改人工号
     *      recReviseName ： 修改人姓名
     *      recReviseTime ： 修改时间
     *      confirmUserNo ： 确认人工号
     *      confirmUserName ： 确认人
     *      confirmTime ： 确认时间
     *      finishUserNo ： 完工人工号
     *      finishUserName ： 完工人
     *      finishTime ： 完工时间
     *      evalGrade ： 评价等级
     *      evalOpinion ： 评价意见
     *      evalUserNo ： 评价人工号
     *      evalUserName ： 评价人
     *      evalTime ： 评价时间
     *      printFlag ： 打印标记
     *      archiveFlag ： 归档标记
     *      datagroupCode ： 账套
     *      itemTypeCode ： 工单保洁事项分类编码
     *      itemTypeName ： 工单保洁事项分类
     *      itemCode ： 工单保洁事项编码
     *      itemName ： 工单保洁事项
     *      serDeptNum ： 工单事项服务科室编码
     *      serDeptName ： 工单事项服务科室名称
     *      executeUserNo ： 保洁执行人工号
     *      executeUserName ： 保洁执行人姓名
     */
    @SuppressWarnings("unchecked")
    public EiInfo queryUnFinishTaskList(EiInfo inInfo) {
        /*
         * 1、将要查询的参数组成数组并调用工具类转换参数
         */
        String[] param = {"taskNo", "statusCode","printFlag", "reqStaffId", "reqDeptNum", "reqDeptName",
                "recCreateTimeS", "recCreateTimeE"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        map.put("dataGroupCode", inInfo.getString("dataGroupCode"));
        /*
         * 2、对type参数进行判断处理.
         * 当为1待完工时，仅显示状态为02待完工的工单.
         * 预留“我的工单”选项.
         */
        //APP查询待完工（对参数type进行处理）
        if(CsConstant.APP_TYPE_UNFINISH.equals(inInfo.getString("type"))) {
            map.put("statusCodes", Arrays.asList(new String[]{"02"}));
            //APP查询已完工
        }else { } //预留
        /*
         * 3、处理app端角色权限的参数.
         * 对应的角色对应对应的限制：调度中心不做限制，普通角色/保洁班组人员只能看见自己的工号和自己班组的工单.
         */
        // APP端权限处理
        if(StringUtils.isNotBlank(inInfo.getString("workNo"))) {
            map.put("workNo",inInfo.getString("workNo"));
            // 获取该工号的所有相关信息，包含权限
            Map<String, Object> userInfo = CSUtils.getUserInfo(inInfo.getString("workNo"));
            String role = userInfo.get("role").toString();
            // 判断角色值，当角色值为调度中心/保洁班组时，则查询所有状态的工单.
            // 当角色值为保洁执行人时，仅显示自己班组的所有工单.
            // 当角色值为普通角色时，显示自己的工单以及自己班组的工单.
            if(role.contains(CsConstant.ROLE_DDZX) || role.contains(CsConstant.ROLE_ADMIN)) {    // 调度中心
                map.put("role", CsConstant.ROLE_DDZX);
            }else if(!role.contains(CsConstant.ROLE_DDZX) && role.contains(CsConstant.ROLE_BJBZRY)){ //保洁班组人员
                map.put("role", CsConstant.ROLE_BJBZRY);
                map.put("department",CSUtils.isEmpty(userInfo.get("deptNum")));
            }else if(!role.contains(CsConstant.ROLE_DDZX) && role.contains(CsConstant.ROLE_BJZXR)){ //保洁执行人人员
                map.put("role", CsConstant.ROLE_BJZXR);
                map.put("workNo",inInfo.getString("workNo"));
            }else {
                map.put("role", CsConstant.ROLE_NORMAL);    // 普通角色
                map.put("workNo",inInfo.getString("workNo"));
                map.put("department",CSUtils.isEmpty(userInfo.get("deptNum")));
            }
        }
        /*
         * 4、对额外的参数进行处理.
         * statusCode,statusCode1  方法传来的状态值.
         * view    方法传来的查看角色值.
         */
        //额外参数处理
        // 额外参数状态的处理
        List<String> statusCodes = new ArrayList<>();
        if(inInfo.get("statusCode")!=null&&!"".equals(inInfo.get("statusCode"))) {
            statusCodes.add(inInfo.getString("statusCode"));
        }
        if(inInfo.get("statusCode1")!=null&&!"".equals(inInfo.get("statusCode1"))) {
            statusCodes.add(inInfo.getString("statusCode1"));
        }
        if(statusCodes.size() > 0) {
            map.put("statusCodes", statusCodes);
        }

        String view = inInfo.getString("view");
        if(StringUtils.isNotBlank(view) && view.equals(CsConstant.ROLE_DDZX)) { // 调度中心/管理员
            map.put("role", view);
        } else if (StringUtils.isNotBlank(view)){
            map.put("role", view);    // 保洁执行人
            map.put("workNo",UserSession.getUser().getUsername());
            map.put("department",inInfo.getString("department"));
        }
        /*
         * 5、将构建好的map传入CSRE01.queryUnFinishTaskList进行查询并分页，同时查询列表数量.
         *    判断列表对象是否存在，存在则构建返回对象.
         */
        //查询
        List<Map<String, Object>> list = dao.query("CSRE01.queryUnFinishTaskList",map,
                Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
        int count = super.count("CSRE01.unFinishCount",map);
        // 判断是否存在，存在则构建返回对象
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            inInfo.setMsg("没有查询到数据。");
            return inInfo;
        }
    }

     

     /**
      * 五、工单详情查询.
      * 根据工单号id，查询指定的工单.
      * 1、获取前端传来的taskId值.
      * 2、将taskId值放入map给CSRE01.queryTaskInfo 做参数去查询工单详情信息.
      * 3、判断是否取得数据.
      * 
      * @Title: queryTaskInfo
      * @param:  @param inInfo
      *      taskId： 任务单号id
      * @param:  @return
      * @return: EiInfo  
      *      taskId : 主键 
      *      taskNo :  工单号 
      *      statusCode :  状态代码
      *      codeName :  工单含义
      *      reqStaffId :  来电人工号 
      *      reqStaffName : 来电人姓名
      *      reqTelNum :  来电电话
      *      reqDeptNum :  来电科室编码
      *      reqDeptName :  来电科室名
      *      reqSpotNum : 保洁地点编码
      *      reqSpotName : 保洁地点名
      *      building : 楼
      *      floor : 层
      *      comments : 备注
      *      recCreator : 创建人工号
      *      recCreateName : 创建人姓名
      *      recCreateTime : 创建时间
      *      recRevisor : 修改人工号
      *      recReviseName : 修改人姓名
      *      recReviseTime : 修改时间
      *      confirmUserNo : 工单确认人工号
      *      confirmUserName : 工单确认人姓名
      *      confirmTime : 工单确认时间
      *      finishUserNo : 工单完工人工号
      *      finishUserName : 工单完工人姓名
      *      finishTime : 工单完工时间
      *      evalGrade : 评价等级
      *      evalOpinion : 评价意见
      *      evalUserNo : 评价人工号
      *      evalUserName : 评价人名称
      *      evalTime : 评价时间
      *      printFlag : 打印标记
      *      archiveFlag : 归档标记
      *      datagroupCode : 账套
      *      itemTypeCode : 工单保洁事项分类编码
      *      itemTypeName : 工单保洁事项分类
      *      itemCode : 工单保洁事项编码
      *      itemName : 工单保洁事项
      *      serDeptNum : 工单事项服务科室编码
      *      serDeptName : 工单事项服务科室名称
      *      executeUserNo ： 保洁执行人工号
      *      executeUserName ： 保洁执行人姓名
      *      
      * @throws
      */
     @SuppressWarnings("unchecked")
     public EiInfo queryTaskInfo(EiInfo inInfo) {
         /*
          * 1、获取前端传来的taskId值.
          */
         String taskId = "";
         if(inInfo.get("taskId") != null || !"".equals(inInfo.get("taskId"))) {
             taskId = inInfo.getString("taskId");
         }
         /*
          * 2、将taskId值放入map给CSRE01.queryTaskInfo 做参数去查询工单详情信息.
          */
         Map<String, String> map = new HashMap<>();
         map.put("taskId", taskId);
         EiInfo outInfo = new EiInfo();
         List<Map<String, String>> list = dao.query("CSRE01.queryTaskInfo", map);
         /*
          * 3、判断是否取得数据.
          */
         if (CollectionUtils.isEmpty(list)) {
             outInfo.setMsg("未查到数据");
             outInfo.setStatus(-1);
             return outInfo;
         }
         outInfo.setAttr(list.get(0));
         outInfo.setRows("taskDetailInfo",list);
         return outInfo;
     }


    /**
     * 六、未完工工单详情查询.
     * 根据工单号id，查询指定的工单.
     * 1、获取前端传来的taskId值和itemCode值.
     * 2、将taskId值放入map给CSRE01.queryUnFinishTaskInfo 做参数去查询未完工工单详情信息.
     * 3、判断是否取得数据.
     *
     * @Title: queryUnFinishTaskInfo
     * @param:  @param inInfo
     *      taskId： 任务单号id
     * @param:  @return
     * @return: EiInfo
     *      taskId : 主键
     *      taskNo :  工单号
     *      statusCode :  状态代码
     *      codeName :  工单含义
     *      reqStaffId :  来电人工号
     *      reqStaffName : 来电人姓名
     *      reqTelNum :  来电电话
     *      reqDeptNum :  来电科室编码
     *      reqDeptName :  来电科室名
     *      reqSpotNum : 保洁地点编码
     *      reqSpotName : 保洁地点名
     *      building : 楼
     *      floor : 层
     *      comments : 备注
     *      recCreator : 创建人工号
     *      recCreateName : 创建人姓名
     *      recCreateTime : 创建时间
     *      recRevisor : 修改人工号
     *      recReviseName : 修改人姓名
     *      recReviseTime : 修改时间
     *      confirmUserNo : 工单确认人工号
     *      confirmUserName : 工单确认人姓名
     *      confirmTime : 工单确认时间
     *      finishUserNo : 工单完工人工号
     *      finishUserName : 工单完工人姓名
     *      finishTime : 工单完工时间
     *      evalGrade : 评价等级
     *      evalOpinion : 评价意见
     *      evalUserNo : 评价人工号
     *      evalUserName : 评价人名称
     *      evalTime : 评价时间
     *      printFlag : 打印标记
     *      archiveFlag : 归档标记
     *      datagroupCode : 账套
     *      itemTypeCode : 工单保洁事项分类编码
     *      itemTypeName : 工单保洁事项分类
     *      itemCode : 工单保洁事项编码
     *      itemName : 工单保洁事项
     *      serDeptNum : 工单事项服务科室编码
     *      serDeptName : 工单事项服务科室名称
     *      executeUserNo ： 保洁执行人工号
     *      executeUserName ： 保洁执行人姓名
     *
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo queryUnFinishTaskInfo(EiInfo inInfo) {
        /*
         * 1、获取前端传来的taskId值和itemCode值.
         */
        String taskId = "";
        String itemCode = "";
        if(inInfo.get("taskId") != null || !"".equals(inInfo.get("taskId"))) {
            taskId = inInfo.getString("taskId");
        }
        if(inInfo.get("itemCode") != null || !"".equals(inInfo.get("itemCode"))) {
            itemCode = inInfo.getString("itemCode");
        }
        /*
         * 2、将taskId值放入map给CSRE01.queryUnFinishTaskInfo 做参数去查询未完工工单详情信息.
         */
        Map<String, String> map = new HashMap<>();
        map.put("taskId", taskId);
        map.put("itemCode", itemCode);
        EiInfo outInfo = new EiInfo();
        List<Map<String, String>> list = dao.query("CSRE01.queryUnFinishTaskInfo", map);
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
     
     /**
      * 七、保洁主表工单保存接口.
      * 对参数处理，然后保存到数据库.
      * 1、获取inInfo传来的参数.
      * 2、新建一个map用来存放获取的数据.
      * 3、3、以map作为参数执行 CSRE01.insertCSGD 进行数据的插入，插入保洁工单主表.
      * 4、返回一个EiInfo.
      * 
      * @Title: insertCSGD
      * @param:  @param inInfo
      *      id ：工单主键
      *      taskNo  : 工单号
      *      reqStaffId  : 来电人工号
      *      reqStaffName  : 来电人
      *      statusCode : 状态代码
      *      reqTelNum  : 来电号码
      *      reqDeptNum : 来电科室编号
      *      reqDeptName : 来电科室
      *      building  : 楼
      *      floor : 层
      *      reqSpotNum  : 保洁地点编码
      *      reqSpotName : 保洁地点
      *      comments : 备注
      *      recCreator ： 创建人
      *      dataGroupCode : 账套(院区)
      * @return: EiInfo
      * @throws
      */
     public EiInfo insertCSGD(EiInfo inInfo) {
         /*
          * 1、获取inInfo传来的参数.
          */
         String id = inInfo.get("taskId") == null ? "" : inInfo.getString("taskId");   /* 工单主键*/
         String taskNo = inInfo.get("taskNo") == null ? "" : inInfo.getString("taskNo");     /* 工单号*/
         String reqStaffId = inInfo.get("reqStaffId") == null ? "" : inInfo.getString("reqStaffId");     /* 来电人工号*/
         String reqStaffName = inInfo.get("reqStaffName") == null ? "" : inInfo.getString("reqStaffName");       /* 来电人*/
         String statusCode = inInfo.get("statusCode") == null ? "" : inInfo.getString("statusCode");     /* 状态代码*/
         String reqTelNum = inInfo.get("reqTelNum") == null ? "" : inInfo.getString("reqTelNum");        /* 来电号码*/
         String reqDeptNum = inInfo.get("reqDeptNum") == null ? "" : inInfo.getString("reqDeptNum");     /* 来电科室编码*/
         String reqDeptName = inInfo.get("reqDeptName") == null ? "" : inInfo.getString("reqDeptName");      /* 来电科室名称*/
         String building = inInfo.get("building") == null ? "" : inInfo.getString("building");       /* 楼*/
         String floor = inInfo.get("floor") == null ? "" : inInfo.getString("floor");        /* 层*/
         String reqSpotNum = inInfo.get("reqSpotNum") == null ? "" : inInfo.getString("reqSpotNum");     /* 保洁地点编码*/
         String reqSpotName = inInfo.get("reqSpotName") == null ? "" : inInfo.getString("reqSpotName");      /* 保洁地点*/
         String comments = inInfo.get("comments") == null ? "" : inInfo.getString("comments");       /* 备注*/
         String recCreator = inInfo.get("recCreator") == null ? UserSession.getUser().getUsername() : inInfo.getString("recCreator");        /* 创建人工号*/
         Map<String, Object> createUserInfo = CSUtils.getUserInfo(recCreator);
         String recCreateName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*创建人名称*/
         String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 创建时间*/
         String dataGroupCode = inInfo.get("dataGroupCode") == null ? "" : inInfo.getString("dataGroupCode");        /* 账套*/

         /*
          * 2、新建一个map用来存放获取的数据.
          */
         Map<String, String> map = new HashMap<>();
           
         map.put("id",id);
         map.put("taskNo",taskNo);
         map.put("reqStaffId",reqStaffId);
         map.put("reqStaffName",reqStaffName);
         map.put("statusCode",statusCode);
         map.put("reqTelNum",reqTelNum);
         map.put("reqDeptNum",reqDeptNum);
         map.put("reqDeptName",reqDeptName);
         map.put("building",building);
         map.put("floor",floor);
         map.put("reqSpotNum",reqSpotNum);
         map.put("reqSpotName",reqSpotName);
         map.put("comments",comments);
         map.put("recCreator",recCreator);
         map.put("recCreateName",recCreateName);
         map.put("recCreateTime",recCreateTime);
         map.put("dataGroupCode",dataGroupCode);

         /*
          * 3、以map作为参数执行 CSRE01.insertCSGD 进行数据的插入，插入保洁工单主表.
          */
         dao.insert("CSRE01.insertCSGD", map);
         /*
          * 4、返回一个EiInfo.
          */
         EiInfo outInfo = new EiInfo();
         outInfo.set("taskId", id);
         return outInfo;
     }
     
     
     /**
      * 八、保洁工单更新.
      * 根据指定任务工单号Id更新工单内容.
      * 1、获取inInfo传来的参数.
      * 2、新建一个map用来存放获取的数据.
      * 3、以map作为参数执行 CSRE01.updateCSGD 进行数据的更新,更新保洁工单主表.
      * 4、返回一个EiInfo.
      * 
      * @Title: updateCSGD
      * @param:  @param inInfo
      *      taskId ：工单主键
      *      taskNo  : 工单号
      *      reqStaffId  : 来电人工号
      *      reqStaffName  : 来电人
      *      statusCode : 状态代码
      *      reqTelNum  : 来电号码
      *      reqDeptNum : 来电科室编号
      *      reqDeptName : 来电科室
      *      building  : 楼
      *      floor : 层
      *      reqSpotNum  : 保洁地点编码
      *      reqSpotName : 保洁地点
      *      comments : 备注
      *      recRevisor ： 修改人
      *      dataGroupCode : 账套(院区)
      * @param:  @return
      * @return: EiInfo  
      * @throws
      * 
      */
     public EiInfo updateCSGD(EiInfo inInfo) {
         /*
          * 1、获取inInfo传来的参数.
          */
         String taskId = inInfo.get("taskId") == null ? "" : inInfo.getString("taskId");            /* 工单号Id*/
         String taskNo = inInfo.get("taskNo") == null ? "" : inInfo.getString("taskNo");            /* 工单号*/
         String reqStaffId = inInfo.get("reqStaffId") == null ? "" : inInfo.getString("reqStaffId");     /* 来电人工号*/
         String reqStaffName = inInfo.get("reqStaffName") == null ? "" : inInfo.getString("reqStaffName");       /* 来电人*/
         String reqTelNum = inInfo.get("reqTelNum") == null ? "" : inInfo.getString("reqTelNum");        /* 来电号码*/
         String reqDeptNum = inInfo.get("reqDeptNum") == null ? "" : inInfo.getString("reqDeptNum");     /* 来电科室编码*/
         String reqDeptName = inInfo.get("reqDeptName") == null ? "" : inInfo.getString("reqDeptName");      /* 来电科室名称*/
         String building = inInfo.get("building") == null ? "" : inInfo.getString("building");       /* 楼*/
         String floor = inInfo.get("floor") == null ? "" : inInfo.getString("floor");        /* 层*/
         String reqSpotNum = inInfo.get("reqSpotNum") == null ? "" : inInfo.getString("reqSpotNum");     /* 保洁地点编码*/
         String reqSpotName = inInfo.get("reqSpotName") == null ? "" : inInfo.getString("reqSpotName");      /* 保洁地点*/
         String comments = inInfo.get("comments") == null ? "" : inInfo.getString("comments");       /* 备注*/
         String recRevisor = inInfo.get("recRevisor") == null ? UserSession.getUser().getUsername() : inInfo.getString("recRevisor");        /* 修改人工号*/
         Map<String, Object> updateUserInfo = CSUtils.getUserInfo(recRevisor);
         String recReviseName =  updateUserInfo== null ? "" : updateUserInfo.get("name").toString(); /*修改人名称*/
         String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 修改时间*/
         String dataGroupCode = inInfo.get("dataGroupCode") == null ? "" : inInfo.getString("dataGroupCode");        /* 账套*/
         /*
          * 2、新建一个map用来存放获取的数据.
          */
         Map<String, String> map = new HashMap<>();
         
         map.put("taskId",taskId);
         map.put("taskNo",taskNo);
         map.put("reqStaffId",reqStaffId);
         map.put("reqStaffName",reqStaffName);
         map.put("reqTelNum",reqTelNum);
         map.put("reqDeptNum",reqDeptNum);
         map.put("reqDeptName",reqDeptName);
         map.put("building",building);
         map.put("floor",floor);
         map.put("reqSpotNum",reqSpotNum);
         map.put("reqSpotName",reqSpotName);
         map.put("comments",comments);
         map.put("recRevisor",recRevisor);
         map.put("recReviseName",recReviseName);
         map.put("recReviseTime",recReviseTime);
         map.put("dataGroupCode",dataGroupCode);
         /*
          * 3、以map作为参数执行 CSRE01.updateCSGD 进行数据的更新,更新保洁工单主表.
          */
         dao.update("CSRE01.updateCSGD", map);
         /*
          * 4、返回一个EiInfo.
          */
         EiInfo outInfo = new EiInfo();
         outInfo.set("taskId", taskId);
         return outInfo;
     }
     
     
     /**
      * 九、保洁工单更新状态.
      * 根据指定任务工单号Id更新工单状态.
      * 1、获取inInfo传来的参数.
      * 2、新建一个map用来存放获取的数据.
      * 3、以map作为参数执行 CSRE01.updateCSGD 进行状态的更新,更新工单主表的状态.
      * 4、返回一个EiInfo.
      * 
      * @Title: updateCSGDStatusCode
      * @param:  @param inInfo
      *      taskId : 任务单号id
      *      statusCode ： 工单状态
      * @param:  @return
      * @return: EiInfo  
      * @throws
      * 
      */
     public EiInfo updateCSGDStatusCode(EiInfo inInfo) {
         /*
          * 1、获取inInfo传来的参数.
          */
         String taskId = inInfo.getString("taskId");
         String backOutCode = inInfo.getString("statusCode");
         /*
          * 2、新建一个map用来存放获取的数据.
          */
         Map<String, String> map = new HashMap<>();
         
         map.put("taskId", taskId);
         map.put("statusCode", backOutCode);
         /*
          * 3、以map作为参数执行 CSRE01.updateCSGD 进行状态的更新,更新工单主表的状态.
          */
         dao.update("CSRE01.updateCSGD", map);
         /*
          * 4、返回一个EiInfo.
          */
         EiInfo outInfo = new EiInfo();
         outInfo.set("taskId", taskId);
         return outInfo;
     }
     
     
     /**
      * 十、保洁明细表工单保存接口.
      * 1、获取inInfo传来的事项列表dataItems列表参数.
      * 2、声明一个 allItems 的列表用来存储遍历的挨个挨个保洁事项.
      * 3、以 allItems 列表为参数 执行 CSRE01.insertCSGDMX 进行数据的插入，插入到保洁工单明细表中.
      * 4、返回一个EiInfo.
      *
      * @Title: insertCSGDMX 
      * @param inInfo
      * @return 
      * @return: EiInfo
      */
     @SuppressWarnings("unchecked")
     public EiInfo insertCSGDMX(EiInfo inInfo) {
         /*
          * 1、获取inInfo传来的事项列表dataItems列表参数.
          */
         List<Map<String, String>> itemList = null;
         // 判断事项列表是否为空，是或否从不同地方获取
         if(inInfo.get("dataItems") != null) {
             itemList = (List<Map<String, String>>) inInfo.get("dataItems");
         }else {
             itemList = (List<Map<String, String>>)inInfo.getAttr().get("dataItems");
         }
         /*
          * 2、声明一个 allItems 的列表用来存储遍历的挨个挨个保洁事项.
          */
         List<Map<String, String>> allItems = new LinkedList<>();
         //遍历事项信息集合，添加属性
         itemList.forEach(e -> {
             Map<String, String> item = new HashMap<>();
             item.put("taskId", inInfo.getString("taskId"));  //工单ID
             item.put("statusCode", inInfo.getString("statusCode")); //工单状态
             item.put("itemTypeCode", e.get("typeCode")); // 事项分类编码
             item.put("itemTypeName", e.get("typeName")); // 事项分类名称
             item.put("itemCode", e.get("itemCode")); // 事项编码
             item.put("itemName", e.get("itemName")); // 事项名称
             item.put("itemComments", e.get("comments")); // 备注
             item.put("serviceDeptNum", e.get("serviceDeptNum")); // 服务科室编码
             item.put("serviceDeptName", e.get("serviceDeptName")); // 服务科室名称
//             item.put("dataGroupCode", inInfo.getString("dataGroupCode"));
             item.put("id", UUID.randomUUID().toString()); // uuid
             allItems.add(item);
         });
         /*
          * 3、以 allItems 列表为参数 执行 CSRE01.insertCSGDMX 进行数据的插入，插入到保洁工单明细表中.
          */
         dao.insert("CSRE01.insertCSGDMX", allItems);//保存新的事项信息
         /*
          * 4、返回一个EiInfo.
          */
         EiInfo outInfo = new EiInfo();
         outInfo.set("taskId", inInfo.getString("taskId"));
         return outInfo;
     }
     
     
     /**
      * 十一、更新保洁工单明细表的工单状态.
      * 1、获取inInfo传来的参数.
      * 2、新建一个map用来存放获取的数据.
      * 3、以map作为参数执行 CSRE01.updateCSGDMX 进行数据状态的更新，更新到保洁工单明细表中.
      * 4、返回一个EiInfo.
      *
      * @Title: updateCSGDMX 
      * @param inInfo
      * taskId：工单号id
      * statusCode：更改的状态
      * @return 
      * @return: EiInfo
      */
     public EiInfo updateCSGDMX(EiInfo inInfo) {
         /*
          * 1、获取inInfo传来的参数.
          */
         String taskId = inInfo.get("taskId") == null ? "" : inInfo.getString("taskId");    /* 工单号Id*/
         String statusCode = inInfo.get("statusCode") == null ? "" : inInfo.getString("statusCode");   /*更新状态*/
         /*
          * 2、新建一个map用来存放获取的数据.
          */
         Map<String, String> map = new HashMap<>();

         map.put("taskId",taskId);
         map.put("statusCode",statusCode);
         /*
          * 3、以map作为参数执行 CSRE01.updateCSGDMX 进行数据状态的更新，更新到保洁工单明细表中.
          */
         dao.update("CSRE01.updateCSGDMX", map);
         /*
          * 4、返回一个EiInfo.
          */
         EiInfo outInfo = new EiInfo();
         outInfo.set("taskId", taskId);
         return outInfo;
     }
     
     /**
      * 十二、保洁工单流程表工单流程保存接口.
      * 1、获取inInfo传来的参数.
      * 2、新建一个map用来存放获取的数据.
      * 3、以map作为参数执行 CSRE01.queryCodeName 查询保洁工单状态表工单的状态码对应的状态含义.
      * 4、以map作为参数执行 CSRE01.insertCSGDLC 进行数据的插入,将流程信息插入到保洁工单流程表中.
      * 5、返回一个EiInfo.
      *
      * @Title: insertCSGDLC 
      * @param inInfo
      * @return 
      * @return: EiInfo
      */
     @SuppressWarnings("unchecked")
     public EiInfo insertCSGDLC(EiInfo inInfo) {
         /*
          * 1、获取inInfo传来的参数.
          */
         String id = UUID.randomUUID().toString();        /* 主键*/
         String taskId = inInfo.get("taskId") == null ? "" : inInfo.getString("taskId");        /* 保洁工单主表主键*/
         String statusCode = inInfo.get("statusCode") == null ? "" : inInfo.getString("statusCode");        /* 工单状态*/
         String operationUserNo = inInfo.get("operationUserNo") == null ? UserSession.getUser().getUsername() : inInfo.getString("operationUserNo");     /* 操作人工号*/
         Map<String, Object> userInfo = CSUtils.getUserInfo(operationUserNo);
         String operationUserName = userInfo == null ? inInfo.getString("operationUserName") : userInfo.get("name").toString();    /* 操作人*/
         String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());         /* 操作时间*/
         String dataGroupCode = inInfo.get("dataGroupCode") == null ? "" : inInfo.getString("dataGroupCode");       /* 账套*/
         /*
          * 2、新建一个map用来存放获取的数据.
          */
         Map<String, String> map = new HashMap<>();

         map.put("id",id);
         map.put("taskId",taskId);
         map.put("statusCode",statusCode);
         map.put("operationUserNo",operationUserNo);
         map.put("operationUserName",operationUserName);
         map.put("operationTime",operationTime);
         map.put("datagroupCode",dataGroupCode);
         /*
          * 3、以map作为参数执行 CSRE01.queryCodeName 查询保洁工单状态表工单的状态码对应的状态含义.
          */
         List<Map<String, Object>> result = dao.query("CSRE01.queryCodeName",map);
         // 判断
         if(result != null && result.size() > 0){
             // 将查询到的工单状态含义也一并放入map中
             String statusMean = (String) result.get(0).get("codeName");
             map.put("statusCodeMean", statusMean);
         } else {
             return inInfo;
         }
         /*
          * 4、以map作为参数执行 CSRE01.insertCSGDLC 进行数据的插入,将流程信息插入到保洁工单流程表中.
          */
         dao.insert("CSRE01.insertCSGDLC", map);
         /*
          * 5、返回一个EiInfo.
          */
         EiInfo outInfo = new EiInfo();
         outInfo.set("taskId", taskId);
         return outInfo;
     }
     
     
     /**
      * 十三、工单撤销的实现接口.
      * 1、获取inInfo传来的参数.
      * 2、调用本地服务CSRE01.updateCSGDStatusCode将工单撤销状态更新到保洁工单主表.
      * 3、调用本地服务CSRE01.updateCSGDMX将工单撤销状态更新到保洁工单明细表.
      * 4、调用本地服务CSRE01.insertCSGDLC将撤销流程插入保洁工单流程表中.
      * 5、将操作的结果返回.
      *
      * @Title: backout 
      * @param inInfo
      * @return 
      * @return: EiInfo
      */
     @SuppressWarnings("unchecked")
     public EiInfo backout(EiInfo inInfo) {
         /*
          * 1、获取inInfo传来的参数.
          */
         // 获取当前登录人信息
         String loginName=StringUtils.isBlank((String)inInfo.get("workNo"))?
             UserSession.getUser().getUsername():(String)inInfo.get("workNo");
         Map<String, Object> userInfo = CSUtils.getUserInfo(loginName);
         
         EiInfo outInfo = new EiInfo();
         
         Map<String, String> map = new HashMap<>();
         /*
          * 2、调用本地服务CSRE01.updateCSGDStatusCode将工单撤销状态更新到保洁工单主表.
          */
         inInfo.set(EiConstant.serviceName, "CSRE01");
         inInfo.set(EiConstant.methodName, "updateCSGDStatusCode");
         outInfo = XLocalManager.call(inInfo);
         /*
          * 3、调用本地服务CSRE01.updateCSGDMX将工单撤销状态更新到保洁工单明细表.
          */
         inInfo.set(EiConstant.serviceName, "CSRE01");
         inInfo.set(EiConstant.methodName, "updateCSGDMX");
         outInfo = XLocalManager.call(inInfo);
         /*
          * 4、调用本地服务CSRE01.insertCSGDLC将撤销流程插入保洁工单流程表中.
          */
         // 操作人
         inInfo.set("operationUserNo", loginName);
         inInfo.set("operationUserName", userInfo == null ? "" : userInfo.get("name"));
         // 调用本地服务CSRE01.insertCSGDLC将撤销流程插入保洁工单流程表中
         inInfo.set(EiConstant.serviceName, "CSRE01");
         inInfo.set(EiConstant.methodName, "insertCSGDLC");
         outInfo = XLocalManager.call(inInfo);
         /*
          * 5、将操作的结果返回.
          */
         outInfo.addMsg("操作成功");
         outInfo.setMsgKey("200");
         
         return outInfo;
     }
     
     
     /**
      * 十四、获取指定用户所在的用户组.
      * 1、获取inInfo传来的参数，同时放入pMap中.
      * 2、以pMap作为参数执行 CSRE01.getUserRole 进行工单状态信息的查询.
      * 3、将获取的list信息作为role置于 EiInfo 中并返回.
      *
      * @Title: getUserRole
      * @param:  @param info
      *      workNo ： 工号
      *      dataGroupCode ： 账套（院区）
      *      platSchema ： iplat平台数据库名称
      * @param:  @return
      * @return: EiInfo 
      *      roles ： 角色编码字符串
      *      roleNames ： 角色名称字符串
      * @throws
      */
     @SuppressWarnings("unchecked")
     public EiInfo getUserRole(EiInfo info) {
         /*
          * 1、获取inInfo传来的参数，同时放入pMap中.
          */
         Map<String, String> pMap = new HashMap<>();
         // 通过PlatApplicationContext 获取 platSchema 属性的值。
         String platSchema = PlatApplicationContext.getProperty("platSchema");
         pMap.put("workNo",info.getString("workNo"));
         pMap.put("dataGroupCode",info.getString("dataGroupCode"));
         pMap.put("platSchema",platSchema);
         /*
          * 2、以pMap作为参数执行 CSRE01.getUserRole 进行工单状态信息的查询.
          */
         List<Map<String, String>> list = dao.query("CSRE01.getUserRole",pMap);
         EiInfo outInfo = new EiInfo();
         /*
          * 3、将获取的list信息作为role置于 EiInfo 中并返回.
          */
         outInfo.set("role", list == null || list.size() == 0 ? null : list);
         return outInfo;
     }
     
     /**
      * 十五、查询工单状态的相关信息.
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
          * 2、将构建好的map传入CSRE01.queryStatusCodeList去保洁工单状态表查询保洁状态列表并分页.
          *    同时去保洁工单状态表查询状态列表数量.
          *    判断列表对象是否存在，存在则构建返回对象.
          */
         List<Map<String, Object>> list = dao.query("CSRE01.queryStatusCodeList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
         int count = dao.count("CSRE01.countStatusCodeList", map);
         // 返回
         if(list != null && list.size() > 0){
             return CommonUtils.BuildOutEiInfo(inInfo, "statusInfo", CommonUtils.createBlockMeta(list.get(0)), list, count);
         } else { 
             return inInfo; 
         }
     }

     
     /**
      * 十六、选择人员（有分页）.
      *  1、处理参数.
      *  2、调用微服务接口S_AC_FW_01，获取所有人员信息.
      * 
      * @Title: queryPersonnel
      * @Description:
      * @param:  @param inInfo
      *     userName： 姓名
      *     workNo ： 工号
      *     workNoEQ ： 工号（精确查询）
      *     wgroupNum ： 科室编码
      * @param:  @return
      * @return: EiInfo  
     *      workNo      ：   员工工号 
     *      name        ：   员工姓名
     *      gender      ：   员工性别
     *      contactTel  ：   联系电话
     *      deptNum     :   科室编码
     *      deptName    :   科室名称 
      * @throws
      */
    @SuppressWarnings("unchecked")
    public EiInfo queryPersonnel(EiInfo inInfo) {
        /*
         * 1、获取参数.
         */
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "person", 
                Arrays.asList(new String[]{"dataGroupCode","userName","workNo", "workNoEQ","wgroupNum"}));
        /*
         * 2、调用微服务接口S_AC_FW_01，获取所有人员信息.
         */
        EiInfo personnel = CsBaseDockingUtils.queryPersonnelPage(map);
        personnel.setBlockInfoValue("person", "showCount", "true");
        return personnel;
    }

    /**
     * 十七、工单登记来电人选择人员（有分页）.
     *  1、获取页面传过来的参数.
     *  2、调用微服务接口S_AC_FW_01，获取所有人员信息.
     *
     * @Title: queryPersonnelRE
     * @Description:
     * @param:  @param inInfo
     *     userName： 姓名
     *     workNo ： 工号
     *     workNoEQ ： 工号（精确查询）
     *     wgroupNum ： 科室编码
     * @param:  @return
     * @return: EiInfo
     *      workNo      ：   员工工号
     *      name        ：   员工姓名
     *      gender      ：   员工性别
     *      contactTel  ：   联系电话
     *      deptNum     :   科室编码
     *      deptName    :   科室名称
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo queryPersonnelRE(EiInfo inInfo) {
        /*
         * 1、获取页面传过来的参数.
         */
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status","person");
        /*
         * 2、调用微服务接口S_AC_FW_01，获取所有人员信息.
         */
        EiInfo personnel = CsBaseDockingUtils.queryPersonnelPage(map);
        personnel.setBlockInfoValue("person", "showCount", "true");
        return personnel;
    }
    
    
    /**
     * 十八. 获取人员信息（无分页）.
     * 1、获取页面传过来的参数(无分页).
     * 2、调用微服务接口S_AC_FW_01，获取所有人员信息.
     *
     * @Title: queryPersonnelList
     * @param:  @param inInfo
     *      guaranteeNum：   员工工号 
     *      name        ：   员工姓名
     * @param:  @return
     * @return: EiInfo 
     *      workNo      ：   员工工号 
     *      name        ：   员工姓名
     *      gender      ：   员工性别
     *      contactTel  ：   联系电话
     *      deptNum     :   科室编码
     *      deptName    :   科室名称  
     * @throws
     */
    public EiInfo queryPersonnelList(EiInfo inInfo) {
        /*
         * 1、获取页面传过来的参数(无分页).
         */
        Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo, 
                Arrays.asList(new String[]{"dataGroupCode","userName","guaranteeNum"}));
        /*
         * 2、调用微服务接口S_AC_FW_01，获取所有人员信息.
         */
        return CsBaseDockingUtils.queryPersonnelNoPage(map); 
    }


    /**
     * 十九、科室查询.
     *  1、获取页面传过来的参数(有分页).
     *  2、调用微服务接口S_AC_FW_05，获取科室信息.
     * 
     * @Title: queryDept
     * @param:  @param inInfo
     *      deptNum：科室编码
     *      deptName：科室名称
     *      dataGroupCode： 账套（院区）
     * @param:  @return
     * @return: EiInfo 
     *      deptNum：科室编码
     *      deptName：科室名称
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo queryDept(EiInfo inInfo) {
        /*
         * 1、获取页面传过来的参数(有分页).
         */
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "dept");
        map.put("dataGroupCode", inInfo.getString("dataGroupCode"));
        /*
         * 2、调用微服务接口S_AC_FW_05，获取科室信息.
         */
        EiInfo queryDept = CsBaseDockingUtils.queryDept(map);
        queryDept.setBlockInfoValue("dept", "showCount", "true");
        return queryDept;
    }


    /**
     * 二十、获取所有的服务科室.
     * 1、获取页面传过来的参数(有分页).
     * 2、调用微服务接口S_AC_FW_12，获取服务科室信息.
     *
     * @Title: queryServerDept
     * @param:  @param info
     *  dataGroupCode ： 账套（院区）
     * @param:  @return
     * @return: EiInfo 
     *  deptNum：科室编码
     *  deptName：科室名称
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo queryServerDept(EiInfo inInfo) {
        /*
         * 1、获取页面传过来的参数(有分页).
         */
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "dept");
        map.put("dataGroupCode",inInfo.getString("dataGroupCode"));
        /*
         * 2、调用微服务接口S_AC_FW_12，获取服务科室信息.
         */
        return CsBaseDockingUtils.queryServerDept(map);
    }


    /**
     * 二十一、通过科室查询地点信息，根据科室自动补全.
     *  1、获取传过来的参数.
     *  2、调用微服务接口S_AC_FW_09，通过科室查询地点信息.
     *  
     * @Title: selectSpotInfoByDeptName
     * @param:  @param inInfo
     *      building ： 楼
     * @param:  @return
     * @return: EiInfo 
     *      building ： 楼 
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo selectSpotInfoByDeptName(EiInfo inInfo) {
        /*
         * 1、获取传过来的参数.
         */
        Map<String, Object> map = new HashMap<>();
        map.put("dataGroupCode", inInfo.getString("dataGroupCode")); // 账套
        map.put("deptNum", inInfo.getString("deptNum")); //科室编码
        /*
         * 2、调用微服务接口S_AC_FW_09，通过科室查询地点信息，获取科室的地点信息.
         */
        return CsBaseDockingUtils.selectSpotInfoByDeptName(map);
    }
    
    
    /**
     * 二十二、楼补全.
     *  1、获取传过来的参数.
     *  2、调用微服务接口S_AC_FW_13，获取楼信息.
     *  
     * @Title: selectSpotBuildingName
     * @param:  @param inInfo
     *      building ： 楼
     * @param:  @return
     * @return: EiInfo 
     *      building ： 楼 
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo selectSpotBuildingName(EiInfo inInfo) {
        /*
         * 1、获取传过来的参数.
         */
        Map<String, Object> map = new HashMap<>();
        System.out.println(inInfo);
        map.put("dataGroupCode", inInfo.getString("dataGroupCode")); // 账套
        map.put("building", inInfo.getString("building")); // 楼
        /*
         * 2. 调用微服务接口S_AC_FW_13，获取楼信息。
         */
        return CsBaseDockingUtils.selectSpotBuildingName(map);
    }


    /**
     * 二十三、层补全.
     *  1、获取传过来的参数.
     *  2、调用微服务接口S_AC_FW_14，获取层信息.
     *  
     * @Title: selectSpotFloorName
     * @Description: 层补全
     * @param:  @param inInfo
     *      building ： 层
     *      floor ： 层
     * @param:  @return
     * @return: EiInfo 
     *      floor ： 层
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo selectSpotFloorName(EiInfo inInfo) {
        /*
         * 1、获取传过来的参数.
         */
        Map<String, Object> map = new HashMap<>();
        map.put("dataGroupCode", inInfo.getString("dataGroupCode")); // 账套
        System.out.println(inInfo);
        System.out.println(inInfo.getString("building")); // 楼
        // 判断楼是否为空，是与否从不同的地方取值.
        if(inInfo.get("building") == null) {
            map.put("building", inInfo.getMsg());
        }else {
            map.put("building", inInfo.getString("building"));
        }
        map.put("floor", inInfo.getString("floor")); // 层
        /*
         * 2、调用微服务接口S_AC_FW_14，获取层信息.
         */
        return CsBaseDockingUtils.selectSpotFloorName(map);
    }
    
    /**
     * 二十四、地点补全.
     *  1、获取传过来的参数.
     *  2、调用微服务接口S_AC_FW_15，获取地点信息(房间、地点编码、地点名称).
     * 
     * @Title: selectSpotRoomName
     * @param:  @param inInfo
     *      building ： 层
     *      floor ： 层
     *      spotName ： 地点名称 
     * @param:  @return
     * @return: EiInfo 
     *      reqSpotName ： 房间
     *      spotNum ： 地点编码
     *      spotName ： 地点名称 
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo selectSpotRoomName(EiInfo inInfo) {
        /*
         * 1、获取传过来的参数.
         */
        Map<String, Object> map = new HashMap<>();
        map.put("dataGroupCode", inInfo.getString("dataGroupCode")); // 账套
        // 判断楼和层是否为空，是与否从不同的地方取值.
        if(inInfo.get("building") == null || inInfo.get("floor") == null) {
            map.put("floor", inInfo.getMsg());
            map.put("building", inInfo.getName());
        }else {
            map.put("building", inInfo.getString("building"));
            map.put("floor", inInfo.getString("floor"));
        }
        map.put("spotName", inInfo.getString("reqSpotName"));
        /*
         * 2、调用微服务接口S_AC_FW_15，获取地点信息(房间、地点编码、地点名称).
         */
        return CsBaseDockingUtils.selectSpotRoomName(map);
    }
    
    
    /**
     * 二十五、来电电话补全查询科室/地点信息.
     *  1、获取传过来的参数.
     *  2、调用微服务接口S_AC_FW_10，通过电话获取科室和地点信息.
     * 
     * @Title: selectOfficePhone
     * @param:  @param inInfo
     *      reqTelNum : 电话（模糊查询）
     *      telNum ： 电话（精确查询）
     *      dataGroupCode ： 账套（院区）
     * @param:  @return
     * @return: EiInfo  
     *      telNum ： 联系电话
     *      deptNum ： 科室编码
     *      deptName ： 科室名称
     *      building ： 楼
     *      floor ： 层
     *      spotNum ：地点编码
     *      spotName ： 地点名
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo selectOfficePhone(EiInfo inInfo) {
        /*
         * 1、获取传过来的参数.
         */
        Map<String, Object> pMap = new HashMap<>();
        pMap.put("dataGroupCode", inInfo.getString("dataGroupCode"));
        pMap.put("reqTelNum", inInfo.getString("reqTelNum"));
        pMap.put("telNum", inInfo.getString("telNum"));
        /*
         * 2、调用微服务接口S_AC_FW_10，通过电话获取科室和地点信息.
         */
        return CsBaseDockingUtils.selectOfficePhone(pMap);
    }
    
    /**
     * 二十六、查询单号.
     * 1、查询获取单号标识，获取之前最大任务号.
     *
     * @Title: querySerialNo
     * @param:  @param inInfo
     *      type : 单号在cs_model_number表中的标识符
     * @param:  @return
     * @return: EiInfo  
     *      list<String> list : 单号集合
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo querySerialNo (EiInfo inInfo) {
        /*
         * 1、查询获取单号标识，获取之前的任务号.
         */
        // 以type类在"生成单号表"中.查询存在的任务号,
        List<String> list = dao.query("CSRE01.querySerialNo", inInfo.getString("type"));
        // 判断是否存在，不存在则设为空，存在则取存在的值。
        if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(list.get(0))) {
            inInfo.set("serialNo", "");
        } else {
            inInfo.set("serialNo", list.get(0));
        }
        return inInfo;
    }
    
    
    /**
     * 二十七、更新单号.
     * 1、获取参数.
     * 2、封装参数.
     * 3、当为insert插入操作的时候，以map为参数将生成的工单号插入到生成单号表中.
     *
     * @Title: updateSerialNo
     * @param:  @param inInfo
     *      taskNo： 任务单号
     *      type ： 单号在cs_model_number表中的标识符
     *      updateTime ： 更新时间
     * @param:  @return
     * @return: EiInfo  ： 无
     * @throws
     */
    public EiInfo updateSerialNo(EiInfo inInfo) {
        /*
         * 1、获取参数.
         */
        String type = inInfo.get("type") == null ? "" : inInfo.getString("type"); // 类别/单号标识
        String serialNo = inInfo.get("serialNo") == null ? "" : inInfo.getString("serialNo"); // 任务号
        /*
         * 2、封装参数.
         */
        Map<String, String> map = new HashMap<>();
        map.put("serialNo", serialNo);
        map.put("type", type);
        map.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        /*
         * 3、当为insert插入操作的时候，以map为参数将生成的工单号插入到生成单号表中.
         */
        if("insert".equals(inInfo.getString("op"))){
            dao.insert("CSRE01.insertSerialNo", map);
        } else {
            dao.update("CSRE01.updateSerialNo", map);
        }
        return inInfo;
    }

    /**
     * 保存图片
     *
     * <p>1.获取参数</br>
     * 	 2.获取图片的保存路径（从框架的配置信息管理【DEDCC03】中获取文件根路径）</br>
     * 	 3.遍历图片List</br>
     *   4.判断图片是pc端上传还是app端上传</br>
     *   5.pc端上传，直接保存图片信息到mt_tp表</br>
     *   6.app端上传，先将图片base64码转成图片文件保存，再保存图片信息到mt_tp表
     * </p>
     *
     * @Title: savePicture
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		taskNo: 任务单号
     * 		dataGroupCode： 账套（院区）
     * 		picList ： 图片集合List
     * 			fileName:图片名称
     * 			path： 图片路径（PC端使用）
     * 			base64 ： 图片Base64码(APP端使用)
     * 			type : 上传图片的流程 RE=登记时上传，FS=完工时上传
     * @param:  @return
     * @param:  @throws IOException
     * @return: EiInfo
     * 		msg : 提示消息
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo savePicture(EiInfo inInfo) throws IOException {
        //获取参数
        Object object = inInfo.get("picList");
        List<Map<String,String>> picList = object == null ? new ArrayList<>() : (List<Map<String,String>>)object;
        //获取操作人
        String recCreator = MTUtils.isEmpty(inInfo.get("recCreator"), UserSession.getUser().getUsername());		/* 创建人*/
        String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());		/* 创建时间*/
        //获取图片存储基础路径（从框架的配置信息管理【DEDCC03】中获取文件根路径）
        String docRootDir = PlatApplicationContext.getProperty("docRootDir");
        String destPath = docRootDir+ "/cs/img/";
        //遍历图片List
        picList.forEach(map -> {//{"fileName":"a.jpg","docId":"","base64":"","type":"RE||FS"}
            map.put("id", UUID.randomUUID().toString());
            map.put("recCreator", recCreator);
            map.put("recCreateTime", recCreateTime);
            map.put("dataGroupCode", inInfo.getString("dataGroupCode"));
            map.put("taskNo", inInfo.getString("taskNo"));
            //判断图片是pc端上传还是app端上传
            if(StringUtils.isBlank(map.get("docId"))){//App端
                //app端上传，先将图片base64码转成图片文件保存，再保存图片信息到mt_tp表
                String[] files = map.get("fileName").split(".");
                String path = destPath + Calendar.getInstance().getTimeInMillis()+ (files.length > 1 ? files[1] : ".png");
                CommonUtils.base64StrToImage(map.get("base64"), path);//存储图片
                map.put("path", path);
            } else {
                map.put("path", MTUtils.getFilePath(map.get("docId")));
            }
            map.put( "type","RE");
            dao.insert("CSRE01.insertPic", map);
        });
        inInfo.setMsg("操作成功");
        return inInfo;
    }

    /**
     * 回显上传的图片
     *
     * <p>根据图片的路径，获取到图片文件，再将图片文件转成base64码</p>
     *
     * @Title: showPic
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param info
     * 		taskNo ： 工单号
     * 		type ： 上传图片的流程 RE=登记时上传，FS=完工时上传
     * @param:  @return
     * @return: EiInfo
     * 		base64 ： 图片base64码
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo showPic(EiInfo info) {
        Map<String,String> pMap = new HashMap<>(4);
        pMap.put("taskNo", info.getString("taskNo"));
        pMap.put("type", info.getString("type"));

        List<Map<String,String>> list=dao.query("CSRE01.showPic",pMap);
        list.forEach(map ->{
            map.put("base64", CommonUtils.imageToBase64Str(map.get("path")));
            map.put("path","");
        });
        info.set("list", list);
        return info;
    }
    
}
