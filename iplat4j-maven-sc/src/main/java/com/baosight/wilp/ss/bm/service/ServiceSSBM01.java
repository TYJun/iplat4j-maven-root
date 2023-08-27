package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.ss.bm.domain.SSBM01;

import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceSSBM01 extends ServiceBase {
    /**
     * 页面初始化方法
     * 作者：KWR
     * 入参：EiInfo
     * 出参：EiInfo
     * 处理：调用query()方法
     */

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 查询功能
     * 作者：KWR
     * 入参：EiInfo（员工工号 "workNo", 员工名字 "name", 科室名称 "deptName", 双亲ID "parentId"）
     * 出参：EiInfo（满足入参条件的人员信息）
     * 处理：
     * 1.获取当前用户的院区信息，和工号
     * 2.调用AUFW01.getUserDepts()方法获取该用户的所属科室信息
     * 3.查询满足入参条件和所属科室的人员信息
     * 4.将结果封装在EiInfo中的result域中返回
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        String typeName = (String)inInfo.getAttr().get("typeName");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("typeName", typeName);
        List<SSBM01> outInfo =dao.query("SSBM01.query", hashMap);
        inInfo.addRows("result", outInfo);
        return inInfo;

    }

    /**
     * 删除功能 入参：EiInfo（待删除人员的id list） 出参：EiInfo（删除操作结果信息） 处理： 1.从入参EiInfo中获取待删除人员的id
     * list 2.调用删除方法在数据库中删除满足条件的记录 3.返回删除结果
     */
	public EiInfo delete(EiInfo inInfo) {

        String id = inInfo.get("id") == null ? "" : inInfo.getString("id");
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
		dao.delete("SSBM01.delete",map);
		inInfo.setStatus(0);
		inInfo.setMsg("删除成功");
		return inInfo;
	}

    @Override
    public EiInfo insert(EiInfo inInfo) {
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String id = UUID.randomUUID().toString().replace("-", "");
        String typeName = inInfo.get("typeName") == null ? "" : inInfo.getString("typeName");
        String mealTimeName = inInfo.get("mealTimeName") == null ? "" : inInfo.getString("mealTimeName");
        String menuName = inInfo.get("menuName") == null ? "" : inInfo.getString("menuName");
        String discountAmount = inInfo.get("discountAmount") == null ? "" : inInfo.getString("discountAmount");
        String typeCode = inInfo.get("typeCode") == null ? "" : inInfo.getString("typeCode");
        String mealTimeCode = inInfo.get("mealTimeCode") == null ? "" : inInfo.getString("mealTimeCode");
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("createTime",createTime);
        map.put("typeName",typeName);
        map.put("mealTimeName",mealTimeName);
        map.put("menuName",menuName);
        map.put("discountAmount",discountAmount);
        map.put("typeCode",typeCode);
        map.put("mealTimeCode",mealTimeCode);
        dao.insert("SSBM01.insert", map);

        //获取列表数据
//        List<Map<String,String>> rows =(List<Map<String,String>>)inInfo.get("htb");
//        for (Map<String,String> map1:rows) {
//            //赋值map
//            map1.put("id", UUID.randomUUID().toString());
//            map1.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//            //插入项目
//            dao.insert("SSBM01.insert", map1);
//        }

        inInfo.setMsg("成功");
        return inInfo;


    }
    /**
     * 启用/停用功能
     * 作者：KWR
     * 入参：EiInfo（待启用/停用人员的id list）
     * 出参：EiInfo（启用/停用操作结果信息）
     * 处理：
     * 1.从入参EiInfo中获取待启用/停用人员的id list
     * 2.调用更新方法在数据库中更新满足条件的记录
     * 3.返回操作结果
     */
//    public EiInfo updateStatus(EiInfo inInfo) {
//
//    }

}
