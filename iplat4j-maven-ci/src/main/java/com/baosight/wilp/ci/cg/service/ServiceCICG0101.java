package com.baosight.wilp.ci.cg.service;

import com.alibaba.fastjson.JSONArray;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;


import java.util.*;

/**
 *物资申请保存页面
 * <p>1.初始化查询 initLoad
 * <p>2.申请新增 insert
 * <p>3.查询科室 queryDept
 *
 * @Title: ServiceCICG0101.java
 * @ClassName: ServiceCICG0101
 * @Package：com.baosight.wilp.md.sq.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCICG0101 extends ServiceBase {

    /**
     * 页面加载
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
  return inInfo;
    }

	/**
	 * 新增保存操作
	 * &lt;p&gt;Title: insert&lt;/p&gt;
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param eiInfo
	 * deptNum  科室编码
     * deptName 科室名称
     * rows  物资明细
	 * @return
	 * 新增成功，失败则执行回滚操作
	 * @see ServiceBase#insert(EiInfo)
	 */
    public EiInfo insert(EiInfo eiInfo) {

        //获取当前登录人信息
        Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
        String workNo = CiUtils.isEmpty(eiInfo.get("workNo"), userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString());
        String name = CiUtils.isEmpty(eiInfo.get("name"), userInfo.get("name") == null ? "" : userInfo.get("name").toString());
        String dataGroupCode = CiUtils.isEmpty(eiInfo.get("dataGroupCode"), userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString());
        //生成工单
        String applyBillNo = "CGSQ" + DateUtils.curDateTimeStr14();
        //获取当前时间
        String time = DateUtils.curDateTimeStr19();
        /*String applyDeptNum = eiInfo.getString("deptNum");
        String applyDeptName = eiInfo.getString("deptName");*/
        String id = UUID.randomUUID().toString();
        //获取物资明细
        List<Map<String, String>> rows = (List<Map<String, String>>) eiInfo.get("rows");
        for (Map<String, String> pMap : rows) {
            //封装物资数据将数据插入明细表中
            pMap.put("id", UUID.randomUUID().toString());
            pMap.put("recCreator", name);
            pMap.put("recCreateTime", time);
            pMap.put("applyBillNo", applyBillNo);
            pMap.put("applyBillDetailNo", "D" + applyBillNo);
            dao.insert("CICG0101.insert", pMap);
        }
        //封装数据
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("recCreator", name);
        map.put("recCreateTime", time);
        map.put("applyBillNo", applyBillNo);
        /*map.put("applyDeptNum", applyDeptNum);
        map.put("applyDeptName", applyDeptName);*/
        map.put("applyStaffId", name);
        map.put("billMaker", workNo);
        map.put("billMakerName", name);
        map.put("billMakeTime", time);
        //如果是app申请则是直接进入待审核状态
        if ("app".equals(eiInfo.get("applySign"))){
            map.put("applySign", "-1");
        }else{
            map.put("applySign", "0");
        }
        map.put("dataGroupCode", dataGroupCode);
        //插入主表
        dao.insert("CICG01.insert", map);
        EiInfo outInfo = new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }


    /**
     * @Title: queryDept
     * <p>1.调用远程服务获取科室信息</p>
     * @Description: 接口改造(科室)
     * @param info
     * @return info
     * deptNum : 科室编码
     * deptName : 科室名称
     */
    public EiInfo queryDept(EiInfo info) {
        // 调用分页接口构建map
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
        return BaseDockingUtils.getDeptAllPage(map, "dept");
    }

    /**
     * 按采购申请单id查询所有记录
     * <p>Title: query</p>
     * <p>Description: </p>
     * @param inInfo
     * 		statusCode:工单状态
     * 		startTime:制单日期起（>=）
     * 		endTime:制单日期止（<=）
     * @return inInfo
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    public EiInfo queryDetailByApplyId(EiInfo inInfo) {
        String[] param = {"startTime","endTime","statusCode","applyBillNo","realPage"};
        //将取参数封装包含分页
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", Arrays.asList(param));
        if(map.get("realPage")!=null){
            map.put("limit",Integer.parseInt(map.get("realPage").toString()));
        }
        List<Map<String, String>> list = dao.query("CICG0101.query", map);
        int count = list.size();//dao.count("CICG01.count", map);
        EiInfo result=CommonUtils.BuildOutEiInfo(inInfo, null, null, list, count);
        //构建返回的json对象，用于采购入库单回填
        Object allRows= JSONArray.toJSON(list);
        if(allRows!=null){
            result.set("allApplyRows",((JSONArray)allRows).toJSONString());
        }
        return result;
    }

}
