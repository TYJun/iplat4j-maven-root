package com.baosight.wilp.dm.xf.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.dm.common.domain.DMConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍在线选房页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMXF01.java
 * @ClassName: ServiceDMXF01
 * @Package：com.baosight.wilp.dm.xf.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXF01 extends ServiceBase {
    /**
     * 页面初始化加载
     * @Title: initLoad
     * @param inInfo
     * @return
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 在线选房信息查询
     * 逻辑处理
     *   1.将要查询的参数组成数组并调用工具类转换参数
     *   2.获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分
     *   3.调用MXF01.queryAllCanChoose方法查询信息
     *
     * @Title: query
     * @param inInfo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public EiInfo query(EiInfo inInfo) {
        String [] XSLX = null;
        List manNatureList = new ArrayList();
        Map<String, Object> userInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        /*
         * 1、将要查询的参数组成数组并调用工具类转换参数
         */
        String[] param = {"building", "floor","roomNo", "typeCode","dormProperties"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        map.put("roomId",inInfo.getString("roomId"));
        /*
         * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
         */
        String role = (String) userInfo.get("role");
        // 获取前端的人员大类值（manNature）
        String manNature = inInfo.getString("manNature");
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
        } else {
            if (manNature == null){
                String workNo = (String) userInfo.get("workNo");
                List<String> employmentNatureList = dao.query("DMXF01.queryEmploymentNatureByWorkNo", workNo);
                String employmentNature = employmentNatureList.get(0);
                XSLX = DMConstant.XSLX;
                manNatureList = Arrays.asList(XSLX);
                if(manNatureList.contains(employmentNature)){
                    manNature = "学生";
                }else {
                    manNature = "职工";
                }
            }
            role = "DMZSR";
        }
        // 判断人员大类的选择
        if (manNature!=null && manNature.equals("学生")){
            XSLX = DMConstant.XSLX;
            manNatureList = Arrays.asList(XSLX);
        }else if (manNature!=null && manNature.equals("职工")){
            String [] ZGLX = DMConstant.ZGLX;
            manNatureList = Arrays.asList(ZGLX);
        }
        map.put("manNatureList",manNatureList);
        // 将值回显到__ei
        inInfo.set("role", role);
        map.put("role", role);
        map.put("workNo",(String) userInfo.get("workNo"));
        map.put("roomId",inInfo.getString("roomId"));
        //查询
        List<Map<String, Object>> list = dao.query("DMXF01.queryAllCanChoose",map,
                Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
        int count = super.count("DMXF01.countCanChoose",map);
        // 判断是否存在，存在则构建返回对象
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            inInfo.setMsg("没有查询到数据。");
            return inInfo;
        }
    }

    /**
     * 	宿舍选房绑定登记.
     *  1、获取当前用户信息.
     * 	2、调用DMRZ02.existCount  sql语句判断是否在宿舍人员绑定关系表中就已经存在该用户的manId，
     *     如果不存在则执行插入操作，如果存在则执行更新操作
     *     0为不存在：调用本地服务DMXF01.selectOwnDrom将自己选择的宿舍信息与自己id绑定并插入宿舍人员绑定关系表.
     *     1为存在：a.调用sql语句 DMRZ02.queryId 查询该manId在宿舍人员绑定关系表中的表主键。
     *             b.调用本地服务DMQR0101.updateDormRoomMan 更新宿舍人员绑定关系表中相关内容
     * 	3、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态.
     *  4、返回操作结果.
     *
     * @Title: insert
     * @param inInfo
     * @return
     * @see ServiceBase#insert(EiInfo)
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        /*
         * 1、获取当前用户信息.
         */
        // 获取当前登陆工号
        String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
                UserSession.getUser().getUsername():(String)inInfo.get("workNo");
        Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
        EiInfo outInfo = new EiInfo();
        String manId = inInfo.getString("manId");
        String roomId = inInfo.getString("roomId");
        Map<String, String> pMap = new HashMap<>();
        pMap.put("manId",manId);
        // 操作人工号
        inInfo.set("operator", loginName);
        inInfo.set("statusCode", "03");
        //查询当前选房的人员信息在入住申请表中的性别。
        List queryList =dao.query("DMXF01.querySex",manId);
        String sex = (String) queryList.get(0);
        //查询当前选房的人员选择的宿舍的宿舍类型。
        List queryTypeCodeMeanList = dao.query("DMXF01.queryTypeCodeMean",roomId);
        String typeCodeMeaning = (String) queryTypeCodeMeanList.get(0);
        // 查询当前房间是否已经有人入住(判断是否有入住人即可)。
        int count = super.count("DMXF01.queryRoomRZCount",roomId);
        if(count == 0 && typeCodeMeaning.equals("待定")){
            Map<String, String> updateMap = new HashMap<>();
            updateMap.put("roomId",roomId);
            updateMap.put("typeCode",sex);
            dao.update("DMXF01.updateTypeCode",updateMap);
        }
        if((sex.equals("0") && typeCodeMeaning.equals("男生宿舍")) || (sex.equals("1") && typeCodeMeaning.equals("女生宿舍"))){
            outInfo.addMsg("请选择与自己的性别相符类型的宿舍!");
            outInfo.setMsgKey("201");
            return outInfo;
        }
        /*
         * 2、调用DMRZ02.existCount  sql语句判断是否在宿舍人员绑定关系表中就已经存在该用户的manId，
         * 如果不存在则执行插入操作，如果存在则执行更新操作
         */
        int existCount = super.count("DMRZ02.existCount",pMap);
        if (existCount == 0){
            // 生成宿舍与人员绑定的对应id
            String id = UUID.randomUUID().toString();
            inInfo.set("id", id);
            /*
             * 0为不存在：调用本地服务DMXF01.selectOwnDrom将自己选择的宿舍信息与自己id绑定并插入宿舍人员绑定关系表.
             */
            // 将工单信息插入宿舍信息表中
            inInfo.set(EiConstant.serviceName, "DMXF01");
            inInfo.set(EiConstant.methodName, "selectOwnDrom");
            outInfo = XLocalManager.call(inInfo);
        }else {
            /*
             * 1为存在：a.调用sql语句 DMRZ02.queryId 查询该manId在宿舍人员绑定关系表中的表主键。
             *  b.调用本地服务DMQR0101.updateDormRoomMan 更新宿舍人员绑定关系表中相关内容
             */
            List<Map<String,String>> idList = dao.query("DMRZ02.queryId",pMap);
            String id = idList.get(0).get("id");
            String bedNo = (getBedNo(inInfo.getString("roomId"))).toString();     /* 床位号*/
            inInfo.set("id", id);
            inInfo.set("bedNo", bedNo);
            inInfo.set(EiConstant.serviceName, "DMQR0101");
            inInfo.set(EiConstant.methodName, "updateDormRoomMan");
            outInfo = XLocalManager.call(inInfo);
        }
        /*
         * 用户选完房之后，对应的宿舍床位数-1
         */
        inInfo.set(EiConstant.serviceName, "DMXX01");
        inInfo.set(EiConstant.methodName, "decreaseDormBedNum");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
         */
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "updateLCStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
         */
        // 将申请流程插入宿舍操作流程历史表中
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "insertLCInfo");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 3、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态.
         */
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "updateStatusCode");
        outInfo = XLocalManager.call(inInfo);
        dao.delete("DMXF01.deleteChoose", manId); // 删除被分配的所有宿舍
        /*
         * 4、返回操作结果.
         */
        outInfo.setMsgKey("200");
        return outInfo;

    }

    /**
     * 宿舍人员绑定关系信息表保存接口.
     * 对参数处理，然后保存到数据库.
     * 1、获取inInfo传来的参数.
     * 2、新建一个map用来存放获取的数据.
     * 3、以map作为参数执行 DMRZ02.insertDMMANTable 将宿舍与人员的绑定信息插入到宿舍人员绑定关系表 .
     * 4、返回一个EiInfo.
     *
     * @Title: selectOwnDrom
     * @param:  @param inInfo
     *      id ：主键
     *      roomId  : 宿舍id
     *      manId  : 人员id
     *      bedNo  : 床位号
     *      keepRoomDays  : 房间保留天数
     *      actualRent ：实际房租
     *      actualManageFee ：实际管理费
     *      lastOperator ： 操作人工号
     *      lastOperationName : 操作人
     *      lastOperationTime : 操作时间
     * @return: EiInfo
     * @throws
     */
    public EiInfo selectOwnDrom(EiInfo inInfo) {
        /*
         * 1、获取inInfo传来的参数.
         */
        String id = inInfo.get("id") == null ? "" : inInfo.getString("id");   /*主键*/
        String roomId = inInfo.get("roomId") == null ? "" : inInfo.getString("roomId");     /* 宿舍id*/
        String manId = inInfo.get("manId") == null ? "" : inInfo.getString("manId");     /* 人员id */
        String keepRoomDays = inInfo.get("keepRoomDays") == null ? "" : inInfo.getString("keepRoomDays");     /* 房间保留天数 */
        String actualRent = inInfo.get("actualRent") == null ? "" : inInfo.getString("actualRent");     /* 实际房租 */
        String actualManageFee = inInfo.get("actualManageFee") == null ? "" : inInfo.getString("actualManageFee");     /* 实际管理费 */
        String annualFee = inInfo.get("annualFee") == null ? "" : inInfo.getString("annualFee");     /* 年费 */
        String bedNo = (getBedNo(inInfo.getString("roomId"))).toString();     /* 床位号*/
        String lastOperator = inInfo.get("operator") == null ? UserSession.getUser().getUsername() : inInfo.getString("operator");        /* 操作人工号*/
        Map<String, Object> operaUserInfo = DMUtils.getUserInfo(lastOperator);
        String lastOperationName =  operaUserInfo== null ? "" : operaUserInfo.get("name").toString(); /*操作人名称*/
        String lastOperationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 操作时间*/
        /*
         * 2、新建一个map用来存放获取的数据.
         */
        Map<String, String> map = new HashMap<>();

        map.put("id",id);
        map.put("roomId",roomId);
        map.put("manId",manId);
        map.put("keepRoomDays",keepRoomDays);
        map.put("actualRent",actualRent);
        map.put("actualManageFee",actualManageFee);
        map.put("annualFee",annualFee);
        map.put("bedNo",bedNo);
        map.put("lastOperator",lastOperator);
        map.put("lastOperationName",lastOperationName);
        map.put("lastOperationTime",lastOperationTime);
        /*
         * 3、以map作为参数执行 DMRZ02.insertDMMANTable 将宿舍与人员的绑定信息插入到宿舍人员绑定关系表 .
         */
        dao.insert("DMRZ02.insertDMMANTable", map);
        /*
         * 4、返回一个EiInfo.
         */
        EiInfo outInfo = new EiInfo();
        outInfo.set("id", id);
        return outInfo;
    }

    /**
     * 自动分配床位号
     * @Title: getBedNo
     * @param roomId
     * @return 0(该房间床位已分配完)
     */
    private Integer getBedNo(String roomId) {
        // TODO Auto-generated method stub
        Map<String, String> map=new HashMap<>();
        map.put("roomId", roomId);
        // 获取该房间已使用床位编号
        List<Map<String, String>> list = dao.query("DMXX01.countUsedBed",map);
        // 获取该房间总床位数
        List<Map<String, Integer>> bedNumList = dao.query("DMXX01.getBedNumByRoomId",map);
        Integer bedNum = bedNumList.get(0).get("bedNum");
        int step = 1;
        for (int i = 0; i < list.size(); i++,step++) {
            if((Integer.valueOf(list.get(i).get("usedBed"))) == step) {
                continue;
            }else {
                break;
            }
        }
        if(step <= bedNum) {
            return step;
        }else {
            return 0;
        }
    }



//
//    public EiInfo queryDetialInfo(EiInfo inInfo) {
//        /*
//         * 1、获取前端传来的roomId值.
//         */
//        String roomId = "";
//        if(inInfo.get("roomId") != null || !"".equals(inInfo.get("roomId"))) {
//            roomId = inInfo.getString("roomId");
//        }
//        /*
//         * 2、将taskId值放入map给CSRE01.queryTaskInfo 做参数去查询工单详情信息.
//         */
//        Map<String, String> map = new HashMap<>();
//        map.put("roomId", roomId);
//        EiInfo outInfo = new EiInfo();
//        List<Map<String, String>> list = dao.query("DMXF01.queryDetialInfo", map);
//        /*
//         * 3、判断是否取得数据.
//         */
//        if (CollectionUtils.isEmpty(list)) {
//            outInfo.setMsg("未查到数据");
//            outInfo.setStatus(-1);
//            return outInfo;
//        }
//        outInfo.setAttr(list.get(0));
//        outInfo.setRows("DetailInfo",list);
//        return outInfo;
//    }


    /**
     * 此方法用于查询可选房的楼
     *
     * 逻辑处理：
     * 1.获取工号并判断该工号的权限
     * 2.调用sql方法DMXF01.queryCanSelectBuilding查询可选房的楼
     *
     * @Title: queryCanSelectRoomBuilding
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    public EiInfo queryCanSelectRoomBuilding(EiInfo inInfo) {
        /**
         * 1.获取工号并判断该工号的权限
         */
        Map<String, Object> userInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        String role = (String) userInfo.get("role");
        if(role.contains("ADMIN") || role.contains("DMSPR")){
            role = "DMADMIN";
        }else {
            role = "DMZSR";
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("role",role);
        map.put("workNo",inInfo.getString("workNo"));
        EiInfo outInfo = new EiInfo();
        /**
         * 2.调用sql方法DMXF01.queryCanSelectBuilding查询可选房的楼
         */
        List<Map<String, String>> list = dao.query("DMXF01.queryCanSelectBuilding", map);
        if (CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("未查到数据");
            outInfo.setStatus(-1);
            return outInfo;
        }
        outInfo.setAttr(list.get(0));
        outInfo.setRows("building",list);
        return outInfo;
    }


    /**
     * 此方法用于查询可选房的层
     *
     * 逻辑处理：
     * 1.获取工号并判断该工号的权限
     * 2.调用sql方法DMXF01.queryCanSelectFloor查询可选房的层
     *
     * @Title: queryCanSelectRoomFloor
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    public EiInfo queryCanSelectRoomFloor(EiInfo inInfo) {
        /**
         * 1.获取工号并判断该工号的权限
         */
        Map<String, Object> userInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        String role = (String) userInfo.get("role");
        if(role.contains("ADMIN") || role.contains("DMSPR")){
            role = "DMADMIN";
        }else {
            role = "DMZSR";
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("role",role);
        map.put("workNo",inInfo.getString("workNo"));
        map.put("building",inInfo.getString("building"));
        EiInfo outInfo = new EiInfo();
        /**
         * 2.调用sql方法DMXF01.queryCanSelectFloor查询可选房的层
         */
        List<Map<String, String>> list = dao.query("DMXF01.queryCanSelectFloor", map);
        if (CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("未查到数据");
            outInfo.setStatus(-1);
            return outInfo;
        }
        outInfo.setAttr(list.get(0));
        outInfo.setRows("floor",list);
        return outInfo;
    }


    /**
     * 此方法用于查询可选房的房号
     *
     * 逻辑处理：
     * 1.获取工号并判断该工号的权限
     * 2.调用sql方法DMXF01.queryCanSelectRoomSpot查询可选房的房号
     *
     * @Title: queryCanSelectRoomNo
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    public EiInfo queryCanSelectRoomNo(EiInfo inInfo) {
        /**
         * 1.获取工号并判断该工号的权限
         */
        Map<String, Object> userInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        String role = (String) userInfo.get("role");
        if(role.contains("ADMIN") || role.contains("DMSPR")){
            role = "DMADMIN";
        }else {
            role = "DMZSR";
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("role",role);
        map.put("workNo",inInfo.getString("workNo"));
        map.put("building",inInfo.getString("building"));
        map.put("floor",inInfo.getString("floor"));
        EiInfo outInfo = new EiInfo();
        /**
         * 2.调用sql方法DMXF01.queryCanSelectRoomSpot查询可选房的房号
         */
        List<Map<String, String>> list = dao.query("DMXF01.queryCanSelectRoomSpot", map);
        if (CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("未查到数据");
            outInfo.setStatus(-1);
            return outInfo;
        }
        outInfo.setAttr(list.get(0));
        outInfo.setRows("roomNo",list);
        return outInfo;
    }

    /**
     * 定时任务：当到达宿舍保留时间且员工的住宿状态还没从02待选房变更为03待入住，
     * 则删除该员工的宿舍绑定关系，回退到待分配的状态。
     *
     * @Title: timeOutBack
     * @Param EiInfo
     * @return: EiInfo
     */
    public EiInfo timeOutBack(EiInfo inInfo){
        Map<String,Object> map = new HashMap<>();

        EiInfo outInfo = new EiInfo();
        /*
         * 1.调用DMQR01.queryOverTimeInfo查询到达宿舍保留时间且员工的住宿状态还没从待选房02变更为待入住03的相关信息
         */
        List<Map<String,Object>> listInfo = dao.query("DMFP0101.queryManId",null);
        if (listInfo != null && listInfo.size() > 0){
            for (int i = 0; i < listInfo.size(); i++){
                //保留时间
                int keepRoomDays = Integer.parseInt((String) listInfo.get(i).get("keepRoomDays"));
                //已过期时间
                Long time = (Long) listInfo.get(i).get("ExpirydateTime");
                //判断是否保留时间已过
                if (keepRoomDays <= time){
                    map.put("manId",listInfo.get(i).get("manId"));
                    map.put("statusCode","01");
                    map.put("statusCodeMeaning","选房保留时间过期导致状态退回");
                    map.put("isCurrent","1");
                    //获取当前时间
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    map.put("operationTime",format.format(new Date().getTime()));

                    map.put("id",UUID.randomUUID().toString());
                    //回退状态：02待选房状态更新为01待分配状态
                    dao.update("DMRZ01.updateStatusCode", map);
                    //删除宿舍选择临时表相关入住信息
                    dao.delete("DMFP0101.delete",map);
                    //将回退成01状态的流程记录待宿舍流程表中
                    dao.insert("DMRZ01.insertDMLCTable", map);
                }
            }
            outInfo.setMsg("定时任务执行成功");
            outInfo.setMsgKey("200");
        }else{
            return inInfo;
        }

        return outInfo;
    }

}
