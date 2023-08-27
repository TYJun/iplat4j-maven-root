package com.baosight.wilp.cm.zh.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.cm.service.ServiceCMsendMsg;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 该页面为合同综合管理
 * 主要包含对合同综合查看功能
 * 合同模块：初始化查询、查询方法
 * <p>1.初始化查询 initLoad
 * <p>2.查询方法 query
 * @Title: ServiceCMZH01.java
 * @ClassName: ServiceCMZH01
 * @Package：com.baosight.wilp.cm.zx.service
 * @author: gaojidong
 * @date: 2021年11月18日 下午3:13:24
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMZH01 extends ServiceBase {

    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同执行管理界面
     * 通过合同号、合同名称、合同类型、签订日期起、签订日期止条件查询
     * 回显合同费用编码 、合同费用名称、备注
     * @param inInfo
     * contNo 合同号
     * contName 合同名称
     * contDeptName 合同科室
     * contTypeNum 合同类型
     * contSignTimeS 签订日期起
     * contSignTimeE 签订日期止
     * @return EiInfo
     * id 主键
     * contNo 合同号
     * contName 合同名称
     * contTypeNum 合同类型
     * contSignTime 合同签订日期
     * planTakeefTime 计划生效日期
     * planFinishTime 计划终止日期
     * currType 币种
     * budget 预算
     * quarPeriod 质保期
     * contStatus 合同状态
     * contAdmin 合同管理员
     * billMaker 制单人
     */
    public EiInfo initLoad(EiInfo inInfo) {
        // 调用查询方法
        return this.query(inInfo);
    }

    /**
     * @Title: query
     * @Description: 查询方法
     * 合同执行管理界面
     * 通过合同号、合同名称、合同类型、签订日期起、签订日期止条件查询
     * 回显合同费用编码 、合同费用名称、备注
     * @param inInfo
     * contNo 合同号
     * contName 合同名称
     * contDeptName 合同科室
     * contTypeNum 合同类型
     * contSignTimeS 签订日期起
     * contSignTimeE 签订日期止
     * @return EiInfo
     * id 主键
     * contNo 合同号
     * contName 合同名称
     * contTypeNum 合同类型
     * contSignTime 合同签订日期
     * planTakeefTime 计划生效日期
     * planFinishTime 计划终止日期
     * currType 币种
     * budget 预算
     * quarPeriod 质保期
     * contStatus 合同状态
     * contAdmin 合同管理员
     * billMaker 制单人
     */
    public EiInfo query(EiInfo inInfo) {
        // 调用查询方法
//		EiInfo eiInfo = query(inInfo, "CMZX01.query", new ContractBill());
//		return eiInfo;

        // 创建数组并赋值
        String[] parameter = {"contNo","contName","contTypeNum","contSignTimeS","contSignTimeE","contStatus"};
        // 数组转list
        List<String> plist = Arrays.asList(parameter);
        // 调用接口创建map
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        // 调用查询方法
        List<Map<String, ?>> result = dao.query("CMZH01.query", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
        // 获取查询信息总数
        int count = dao.count("CMZH01.query", map);
        // 如果获取查询信息不为空
        if(result != null && result.size() > 0){
            // 返回封装方法
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(result.get(0)), result, count);
        } else {
            // 返回参数
            return inInfo;
        }
    }
}
