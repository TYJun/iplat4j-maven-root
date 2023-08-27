package com.baosight.wilp.cs.re.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保洁查询工单详情子界面Service.
 * 一、页面加载.
 * 二、登记详情子页面保洁事项查询.
 * 三. 保洁完工子页面/详情子页面/保洁事项查询.
 * 
 * @Title: ServiceCSRE0201.java
 * @ClassName: ServiceCSRE0201
 * @Package：com.baosight.wilp.cs.re.service
 * @author: fangzekai
 * @date: 2021年11月24日 上午10:14:40
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSRE0201 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
        // 调用本地服务CSRE01.queryTaskInfo加载页面。
        inInfo.set(EiConstant.serviceName, "CSRE01");
        inInfo.set(EiConstant.methodName, "queryTaskInfo");
        EiInfo outInfo =XLocalManager.call(inInfo);
        return outInfo;
    }
	

    /**
     * 二、登记详情子页面保洁事项查询.
     * 1、获取工单ID.
     * 2、以工单ID作为参数执行CSRE01.queryItemsRE查询保洁工单明细表中工单登记的保洁事项以及对应的保洁执行人.
     * 3、判断列表是否存在，存在则构建对象并返回结果,不存在则直接将info返回.
     *
     * <p>Title: queryItemsRE</p>
     * @param inInfo
     *      taskId ： 主表工单号id
     * @return   
     *      itemId ：主键
     *      typeCode ： 事项分类编码
     *      typeName ： 事项分类名
     *      itemCode ： 事项编码
     *      itemName ： 事项名称
     *      comments  ： 事项备注
     *      serviceDeptNum : 绑定服务科室编码
     *      serviceDeptName : 绑定服务科室名
     *      
     * @see ServiceBase#queryItemsRE(EiInfo)
     */
    @SuppressWarnings("all")
    public EiInfo queryItemsRE(EiInfo inInfo) {
        /*
         * 1、获取工单ID.
         */
        String taskId = inInfo.getString("taskId");
        /*
         * 2、以工单ID作为参数执行CSRE01.queryItemsRE查询保洁工单明细表中工单登记的保洁事项以及对应的保洁执行人.
         */
        List<Map> list=dao.query("CSRE01.queryItemsRE", taskId);
        /*
         * 3、判断列表是否存在，存在则构建对象并返回结果,不存在则直接将info返回.
         */
        if(list !=null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "resultItem", CommonUtils.createBlockMeta(list.get(0)), list, list.size());
        } else {
            inInfo.setMsg("未获取到数据");
            return inInfo;
        }
    }


    /**
     * 三. 保洁完工子页面/详情子页面/保洁事项查询.
     * 1、获取工单ID和事项编码itemCode.
     * 2、以工单ID和事项编码itemCode作为参数执行CSRE01.queryUnFinishItemsRE 查询保洁工单明细表未完工子工单的保洁事项以及对应的保洁执行人.
     * 3、判断列表是否存在，存在则构建对象并返回结果,不存在则直接将info返回.
     *
     * <p>Title: queryUnFinishItemsRE</p>
     * @param inInfo
     *      taskId ： 主表工单号id
     *      itemCode ： 事项编码
     * @return
     *      itemId ：主键
     *      typeCode ： 事项分类编码
     *      typeName ： 事项分类名
     *      itemCode ： 事项编码
     *      itemName ： 事项名称
     *      comments  ： 事项备注
     *      serviceDeptNum : 绑定服务科室编码
     *      serviceDeptName : 绑定服务科室名
     *
     * @see ServiceBase#queryUnFinishItemsRE(EiInfo)
     */
    @SuppressWarnings("all")
    public EiInfo queryUnFinishItemsRE(EiInfo inInfo) {
        /*
         * 1、获取工单ID和事项编码itemCode.
         */
        Map<String,String> map = new HashMap();
        String taskId = inInfo.getString("taskId");
        String itemCode = inInfo.getString("itemCode");
        map.put("taskId",taskId);
        map.put("itemCode",itemCode);
        /*
         * 2、以工单ID和事项编码itemCode作为参数执行CSRE01.queryUnFinishItemsRE 查询保洁工单明细表未完工子工单的保洁事项以及对应的保洁执行人.
         */
        List<Map> list=dao.query("CSRE01.queryUnFinishItemsRE", map);
        /*
         * 3、判断列表是否存在，存在则构建对象并返回结果,不存在则直接将info返回.
         */
        if(list !=null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "resultItem", CommonUtils.createBlockMeta(list.get(0)), list, list.size());
        } else {
            inInfo.setMsg("未获取到数据");
            return inInfo;
        }
    }
    
}
