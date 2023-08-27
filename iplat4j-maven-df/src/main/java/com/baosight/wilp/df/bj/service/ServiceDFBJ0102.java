package com.baosight.wilp.df.bj.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.util.DFUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 该页面为保洁执行人人员选择
 * 主要包含对保洁执行人的新增、查询功能
 * <p>1.数据回显和加载 initLoad
 * <p>2.接口改造保洁执行人 query
 * @Title: ServiceDFBJ0102.java
 * @ClassName: ServiceDFBJ0102
 * @Package：com.baosight.wilp.df.bj.service
 * @author: liangyongfei
 * @date: 2022年6月27日 上午2:38:01
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDFBJ0102 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 数据回显和加载
     * 该页面为保洁执行人选择
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
     * @Description: 接口改造保洁执行人
     * 该页面为设备保洁管理
     * 通过员工工号、员工姓名条件查询
     * 回显工号、姓名、科室名称
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
        // 调用分页接口构建map
        Map<String, Object> map = CommonUtils.buildParamter(info, "result", plist);
        map.put("userName" , map.get("name"));
        // 获取blockId
        String blockId = info.getBlockIds();
        // 如果blockId相等
        if(blockId.equals("inqu_status,person")) {
            // 实例化info
            EiInfo outinfo = new EiInfo();
            // 实例化block
            EiBlock block = new EiBlock("person");
            // 获取block中的数据的集合
            List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
            // 获取集合中的数据
            String name = (String) listMap.get(0).get("name");
            // 设置userName
            map.put("userName", name);
            // 实例化List
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            // 初始化查询总数为0
            int count = 0;
            // 调用改造人员接口并返回
            EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "person");
            // 如果存在人员信息
            if(outInfo.getBlock("person") != null) {
                // 获取人员信息
                list = outInfo.getBlock("person").getRows();
                // 如果list为空
                if(list.isEmpty()) {
                    // 返回封装的方法：构建返回结果EiInfo
                    return info;
                }
                // 获取人员信息总数
                count = (int)outInfo.getBlock("person").getAttr().get("count");
                // 返回封装的方法：构建返回结果EiInfo
                return CommonUtils.BuildOutEiInfo(info, "person", DFUtils.createBlockMeta(list.get(0)), list, count);
            }else {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
        }
        // 调用改造人员接口并返回
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        // 初始化查询总数为0
        int count = 0;
        // 调用改造人员接口并返回
        EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "result");
        // 如果存在人员信息
        if(outInfo.getBlock("result") != null) {
            // 获取人员信息
            list = outInfo.getBlock("result").getRows();
            // 如果list为空
            if(list.isEmpty()) {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
            // 获取人员信息总数
            count = (int)outInfo.getBlock("result").getAttr().get("count");
            // 返回封装的方法：构建返回结果EiInfo
            return CommonUtils.BuildOutEiInfo(info, "result", DFUtils.createBlockMeta(list.get(0)), list, count);
         }else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
         }
    }
}
