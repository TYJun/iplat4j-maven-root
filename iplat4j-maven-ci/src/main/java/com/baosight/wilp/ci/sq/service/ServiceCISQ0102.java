package com.baosight.wilp.ci.sq.service;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *物资申请编辑页面
 * <p>1.初始化查询 initLoad
 * <p>2.申请编辑 update
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
@SuppressWarnings("all")
public class ServiceCISQ0102 extends ServiceBase {

    /**
     * 页面加载
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String,String> pMap = new HashMap<>(4);
        pMap.put("applyBillNo", inInfo.getString("applyBillNo"));

        inInfo.set("canteen_textField", inInfo.get("canteenName"));
        List<CiEnterDetail> list = dao.query("CISQ0101.query", pMap);
        return CommonUtils.BuildOutEiInfo(inInfo, "result", new CiEnterDetail().eiMetadata, list, list.size());
    }


    /**
     * 编辑申请操作
     * &lt;p&gt;Title: update&lt;/p&gt;
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param eiInfo
     * deptNum  科室编码
     * deptName 科室名称
     * rows  物资明细
     * @return
     * 新增成功，失败则执行回滚操作
     * @see ServiceBase#insert(EiInfo)
     */
    public EiInfo update(EiInfo eiInfo) {

        //获取当前登录人信息
        Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
        String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
        String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
        String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
        String time =DateUtils.curDateTimeStr19();
        //获取前台传来的数据
        String applyCanteenNum =eiInfo.getString("canteenNum");
        String applyCanteenName =eiInfo.getString("canteenName");
        String id = eiInfo.getString("id");
        String applyBillNo = eiInfo.getString("applyBillNo");
        //删除之前的物资
        dao.delete("CISQ0101.delete" ,applyBillNo);
        //获取当前物资信息
        List<Map<String, String>> rows  = (List<Map<String, String>>) eiInfo.get("rows");
        for (Map<String, String> pMap : rows) {
            pMap.put("id",UUID.randomUUID().toString());
            pMap.put("recCreator",name);
            pMap.put("recCreateTime",time);
            pMap.put("applyBillNo",applyBillNo);
            pMap.put("applyBillDetailNo","D"+applyBillNo);
            dao.insert("CISQ0101.insert",pMap);
        }
        //封装数据
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        map.put("recRevisor", name);
        map.put("recReviseTime", time);
        map.put("dataGroupCode", dataGroupCode);
        map.put("applyCanteenNum", applyCanteenNum);
        map.put("applyCanteenName", applyCanteenName);
        map.put("applyStaffId", name);
        map.put("billMaker", name);
        //更新状态
        dao.update("CISQ01.update",map);
        EiInfo outInfo=new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }

    public EiInfo queryCanteen(EiInfo info) {
        // 调用分页接口构建map
        /*List<DaoEPBase> canteen = CiUtils.getCanteen();
        info.addBlock("canteen").addRows(canteen);*/
        return info;
    }
	
}
