package com.baosight.wilp.cm.dj.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.cm.util.PMUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * 该页面为合同登记管理
 * 主要包含对合同登记的新增、删除、编辑、查看、审批功能
 * 合同模块：数据回显和加载、接口改造合同管理员
 * <p>1.数据回显和加载 initLoad
 * <p>2.接口改造合同管理员 query
 * @Title: ServiceCMDJ0103.java
 * @ClassName: ServiceCMDJ0103
 * @Package：com.baosight.wilp.cm.dj.service
 * @author: zhaow
 * @date: 2021年8月30日 下午2:38:01
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMDJ0103 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 数据回显和加载
     * 该页面为合同登记管理
     * 通过员工工号、员工姓名条件查询
     * 回显工号、姓名、联系方式、科室名称
     * @param inInfo
     * name 姓名
     * workNo 工号
     * @return EiInfo
     * workNo 工号
     * name 姓名
     * phone 联系方式
     * deptName 科室名称
     */
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
     * @Title: query
     * @Description: 接口改造合同管理员
     * <p>1.调用分页接口构建map
     * <p>2.获取blockId
     * <p>3.如果blockId相等
     * <p>4.获取block中的数据的集合
     * <p>5.调用改造人员接口并返回
     * <p>6.如果存在人员信息
     * <p>7.如果list为空
     * <p>8.返回封装的方法：构建返回结果EiInfo
     * <p>9.调用改造人员接口并返回
     * <p>10.调用改造人员接口并返回
     * <p>11.如果存在人员信息
     * <p>12.如果list为空
     * <p>13.返回封装的方法：构建返回结果EiInfo
     * 该页面为合同登记管理
     * 通过员工工号、员工姓名条件查询
     * 回显工号、姓名、联系方式、科室名称
     * @param info
     * name 姓名
     * workNo 工号
     * @return EiInfo
     * workNo 工号
     * name 姓名
     * phone 联系方式
     * deptName 科室名称
     */
    public EiInfo query(EiInfo info) {
        String[] parameter = {"workNo","name"};
        List<String> plist = Arrays.asList(parameter);
        // 1.调用分页接口构建map
        Map<String, Object> map = CommonUtils.buildParamter(info, "result", plist);
        map.put("userName" , map.get("name"));
        // 2.获取blockId
        String blockId = info.getBlockIds();
        // 3.如果blockId相等
        if(blockId.equals("inqu_status,person")) {
            // 实例化info
            EiInfo outinfo = new EiInfo();
            // 实例化block
            EiBlock block = new EiBlock("person");
            // 4.获取block中的数据的集合
            List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
            // 获取集合中的数据
            String name = (String) listMap.get(0).get("name");
            // 设置userName
            map.put("userName", name);
            // 实例化List
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            // 初始化查询总数为0
            int count = 0;
            // 5.调用改造人员接口并返回
            EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "person");
            // 6.如果存在人员信息
            if(outInfo.getBlock("person") != null) {
                // 获取人员信息
                list = outInfo.getBlock("person").getRows();
                // 7.如果list为空
                if(list.isEmpty()) {
                    // 返回封装的方法：构建返回结果EiInfo
                    return info;
                }
                // 获取人员信息总数
                count = (int)outInfo.getBlock("person").getAttr().get("count");
                // 8.返回封装的方法：构建返回结果EiInfo
                return CommonUtils.BuildOutEiInfo(info, "person", PMUtils.createBlockMeta(list.get(0)), list, count);
            }else {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
        }
        // 9.调用改造人员接口并返回
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        // 初始化查询总数为0
        int count = 0;
        // 10.调用改造人员接口并返回
        EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "result");
        // 11.如果存在人员信息
        if(outInfo.getBlock("result") != null) {
            // 获取人员信息
            list = outInfo.getBlock("result").getRows();
            // 12.如果list为空
            if(list.isEmpty()) {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
            // 获取人员信息总数
            count = (int)outInfo.getBlock("result").getAttr().get("count");
            // 13.返回封装的方法：构建返回结果EiInfo
            return CommonUtils.BuildOutEiInfo(info, "result", PMUtils.createBlockMeta(list.get(0)), list, count);
         }else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
         }
    }
}
