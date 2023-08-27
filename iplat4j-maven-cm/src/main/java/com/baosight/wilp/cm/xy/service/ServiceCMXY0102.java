package com.baosight.wilp.cm.xy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cm.domain.ContractPayment;
import com.baosight.wilp.cm.domain.ContractPaymentDetails;
import com.baosight.wilp.common.util.CommonUtils;

/**
 * 该页面为付款协议定义
 * 主要包含对付款协议的新增、删除、编辑、查看功能
 * 合同模块：初始化查询、查询付款详情、生成合同编号、TabGrid查询方法
 * <p>1.初始化查询 initLoad
 * <p>2.查询付款详情 queryPay
 * <p>3.生成合同编号 generateCode
 * <p>4.TabGrid查询方法 queryTabGrid
 * @Title: ServiceCMXY0102.java
 * @ClassName: ServiceCMXY0102
 * @Package：com.baosight.wilp.cm.xy.service
 * @author: zhangjiaqian
 * @date: 2021年8月24日 上午10:08:53
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMXY0102 extends ServiceBase{
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 付款协议定义弹出界面
     * 通过付款协议操作类型
     * 进行合同操作
     * @param EiInfo
     * id 主键
     * type 操作类型 
     * @return EiInfo
     */
    public EiInfo initLoad(EiInfo inInfo) {
        // 获取参数
        String id = inInfo.getAttr().get("id").toString();
        EiBlock block = new EiBlock("inqu_status");
        block.addBlockMeta(new ContractPayment().eiMetadata);
        // 如果参数不为空
        if (StringUtils.isNotBlank(id)) {
            // 实例化map
            Map<String, String> map = new HashMap<>();
            // map赋值
            map.put("id", id);
            // 调用查询方法
            List<ContractPayment> list = dao.query("CMXY01.queryId", map);
            // 数据返回
            block.addRows(list);
        }
        inInfo.addBlock(block);
        return inInfo;
    }
    
    
    
    /**
     * @Title: queryPay
     * @Description: 查询付款详情
     * 付款协议定义界面
     * 通过付款类型编码查询信息
     * 回显合同付款协议
     * @param EiInfo
     * payTypeNum 付款类型编码
     * @return EiInfo
     * id 主键
     * recCreator 创建人
     * recCreateTime 创建时间
     * recRevsior 修改人
     * recReviseTime 修改时间
     * no 序号
     * lastTime 距离上次付款时间
     * payRate 付款比例
     * remark 备注
     */
    public EiInfo queryPay(EiInfo inInfo) {
        // 调用封装方法
        return queryTabGrid(inInfo,"CMXY0101.query","CMXY0101.count","resultA",new ContractPaymentDetails().eiMetadata);
    }

    /**
     * @Title: generateCode
     * @Description: 生成合同编号
     * 通过获取当前时间
     * 判断今天是否存在合同编号，不存在就返回新的合同号
     * 存在合同编号，对合同编号进行累加
     * @param
     * @return: String
     * String 合同编号
     */
    private String generateCode() {
        // 加锁
        synchronized (dao) {
            // 调用时间接口
            String date = DateUtils.curDateStr8();
            // 调用查询方法
            List<String> list = dao.query("CMXY01.queryContTypeNum", "CP" + date);
            // 如果结果为空
            if (list == null || list.size() == 0 || list.get(0) == null) {
                // 返回合同号
                return "CP" + date + "0001";
            } else {
                // 获取最大合同号
                String maxNo = list.get(0);
                // 对最大合同号进行拆分
                maxNo = maxNo.substring(2);
                // 返回合同号
                return "CP" + (Long.parseLong(maxNo) + 1L);
            }
        }
    }
    
    /**
     * TabGrid查询方法
     * @Title: queryTabGrid 
     * @param EiInfo
     * querySql 查询sql
     * countSql 统计sql
     * resultBlock blockId 
     * blockMeta EiBlockMeta
     * @return: EiInfo
     */
    private EiInfo queryTabGrid(EiInfo inInfo, String querySql,String countSql, String resultBlock, EiBlockMeta blockMeta){
        // 调用封装方法构造map
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", resultBlock);
        //查询数据
        List<?> list = dao.query(querySql, map, (Integer)map.get("offset"), (Integer)map.get("limit"));
        //获取总数
        int count = dao.count(countSql, map);
        //数据返回
        return CommonUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
    }
}
