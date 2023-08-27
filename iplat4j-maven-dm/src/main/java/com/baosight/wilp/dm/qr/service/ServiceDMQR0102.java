package com.baosight.wilp.dm.qr.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宿舍确认入住页面编辑实际费用子页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMQR0102.java
 * @ClassName: ServiceDMQR0102
 * @Package：com.baosight.wilp.dm.qr.service
 * @author: fangzekai
 * @date: 2022年06月13日 下午2:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMQR0102 extends ServiceBase {
    /**
     * 此方法用于宿舍确认入住页面编辑实际费用子页面初始化
     *
     * 逻辑处理：
     * 1.调用本地服务DMQR0102.queryDetailInfoById 查询指定的宿舍入住详情信息.
     *
     * @Title: initLoad
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        // 调用本地服务DMQR0102.queryDetailInfoById 查询指定的宿舍入住详情信息.
        inInfo.set(EiConstant.serviceName, "DMQR0102");
        inInfo.set(EiConstant.methodName, "queryDetailInfoById");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }


    /**
     * 宿舍详情查询.
     * 根据宿舍人员关系绑定表的主键id，查询指定的宿舍入住详情信息.
     * 1、获取前端传来的id值.
     * 2、将id值放入map给DMZH01.queryZHList 做参数去查询宿舍详情信息.
     * 3、判断是否取得数据.
     *
     * @Title: queryDetailInfoById
     * @param:  @param inInfo
     *      id： 宿舍人员关系绑定表的主键id
     * @param:  @return
     * @return: EiInfo
     *      id ：主键
     *      building  : 宿舍楼
     *      floor  : 宿舍层
     *      roomNo  : 宿舍号
     *      roomName : 宿舍总称(楼+层+宿舍号)
     *      bedNum  : 床位数
     *      remainingBedNum ： 剩余床位数
     *      typeCode : 房间类型(1男生宿舍/0女生宿舍)
     *      dormPosition : 宿舍位置：院内、院外
     *      dormArea  : 房屋面积："<50㎡"、"50-100㎡"、">100㎡"
     *      priBathroom : 独立卫生间：有、没有
     *      rent  : 房租
     *      manageFee : 管理费
     *      note : 备注信息
     *
     * @throws
     */
    @SuppressWarnings("unchecked")
    public EiInfo queryDetailInfoById(EiInfo inInfo) {
        /*
         * 1、获取前端传来的id值.
         */
        String id = "";
        if(inInfo.get("id") != null || !"".equals(inInfo.get("id"))) {
            id = inInfo.getString("id");
        }
        /*
         * 2、将id值放入map给DMZH01.queryZHList 做参数去查询指定的宿舍入住详情信息.
         */
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        EiInfo outInfo = new EiInfo();
        List<Map<String, String>> list = dao.query("DMZH01.queryZHList", map);
        /*
         * 3、判断是否取得数据.
         */
        if (CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("未查到数据");
            outInfo.setStatus(-1);
            return outInfo;
        }
        outInfo.setAttr(list.get(0));
        outInfo.setRows("detailInfo",list);
        return outInfo;
    }

}
