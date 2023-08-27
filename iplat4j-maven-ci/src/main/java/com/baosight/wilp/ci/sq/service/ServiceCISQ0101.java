package com.baosight.wilp.ci.sq.service;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *物资申请保存页面
 * <p>1.初始化查询 initLoad
 * <p>2.申请新增 insert
 * <p>3.查询科室 queryDept
 *
 * @Title: ServiceCISQ0101.java
 * @ClassName: ServiceCISQ0101
 * @Package：com.baosight.wilp.md.sq.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCISQ0101 extends ServiceBase {

    /**
     * 页面加载
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
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
        String applyBillNo = "LYSQ" + DateUtils.curDateTimeStr14();
        //获取当前时间
        String time = DateUtils.curDateTimeStr19();
        String applyCanteenNum = eiInfo.getString("canteenNum");
        String applyCanteenName = eiInfo.getString("canteenName");
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
            dao.insert("CISQ0101.insert", pMap);
        }
        //封装数据
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("recCreator", name);
        map.put("recCreateTime", time);
        map.put("applyBillNo", applyBillNo);
        map.put("applyCanteenNum", applyCanteenNum);
        map.put("applyCanteenName", applyCanteenName);
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
        dao.insert("CISQ01.insert", map);
        EiInfo outInfo = new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }

}
