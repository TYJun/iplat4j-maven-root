package com.baosight.wilp.sq.fl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.wilp.sq.fl.domain.SQFL02;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.sq.fl.domain.SQFL01;
import com.baosight.wilp.utils.DatagroupUtil;

/**
 * 
 * 新增人员查询弹窗 : 页面初始化，页面查询，选择科室
 * <p>1.初始化查询 initLoad
 * <p>2.页面查询 query
 * <p>3.选择科室 queryDept
 * @Title: ServiceSQFL010101.java
 * @ClassName: ServiceSQFL010101
 * @Package：com.baosight.wilp.sq.fl.service
 * @author: zhangjiaqian
 * @date: 2021年7月26日 下午2:59:26
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History: // 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQFL010101 extends ServiceBase{


    /**
     * 
     * 页面初始化方法
     * 
     * @param inInfo
     * @return
     * dept             科室编码
     * name             人员工号
     * datagroupCode    账套
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     * <author>  // 修改人
     * <time>    // 修改时间
     * <version> // 版本
     * <desc>    // 描述修改内容
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //取消EF:EFGrid blockId="result"加载页面时会出现空白行
//        EiInfo outInfo = new EiInfo();
//        EiBlock resultBlock = outInfo.addBlock(EiConstant.resultBlock);
//        resultBlock.addBlockMeta(new SQFL01().eiMetadata);
//        return outInfo;
       //调用本地query返回
        return this.query(inInfo);
    }


    /**
     * 
     * 查询方法
     * 
     * @param inInfo
     * dept             科室编码
     * name             人员工号
     * datagroupCode    账套
     * @return inInfo
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
//        // 科室编码
//        String dept = StringUtils.isEmpty((String)inInfo.get("dept"))?"":(String)inInfo.get("dept");
//        // 人员工号
//        String name = StringUtils.isEmpty((String)inInfo.get("name"))?"":(String)inInfo.get("name");
//        // 获取院区编码
//        String datagroupCode = DatagroupUtil.getDatagroupCode();
//        // 获取分页信息
//        EiBlock block = inInfo.getBlock("result");
//        // 初始化页面总数
//        Integer count = 0;
//        Integer offset = 0;
//        Integer limit = 10;
//        //封装数据
//        Map map = new HashMap();
//        map.put("datagroupCode", datagroupCode);
//        map.put("deptNum", dept);
//        map.put("userName", name);
//        map.put("offset", offset);
//        map.put("limit", limit);
//        //判断block是否为空
//        if(block!=null) {
//            //封装数据
//            Map<String,Integer> attr = block.getAttr();
//            offset = attr.get("offset");
//            limit = attr.get("limit");
//            map.put("offset", offset);
//            map.put("limit", limit);
//        }
//        EiInfo staffAll = BaseDockingUtils.getStaffAllPage(map,"result");
//        EiBlock block2 = staffAll.getBlock("result");
//        if(block2 == null) {
//            inInfo.setBlock(block).setAttr(map);
//            return inInfo;
//        }
//        count = (Integer)block2.getAttr().get("count");
//        //返回封装信息
//        EiBlock result = inInfo.addBlock("result");
//        if(block2 != null) {
//            result.setRows(block2.getRows());
//        }
//        result.addBlockMeta(new SQFL01().eiMetadata);
//        if(result.getAttr()==null){
//            Map<String,Object> rAttr = new HashMap<>();
//            rAttr.put("count", count);
//            rAttr.put("offset", offset);
//            rAttr.put("limit", limit);
//            rAttr.put("orderBy", "");
//            rAttr.put("showCount", "true");
//            result.setAttr(rAttr);
//        } else {
//            result.getAttr().put("count", count);
//        }
//        //返回
//        return inInfo;
        /*
         * 1.调用查询
         */
        EiInfo outInfo = super.query(inInfo, "SQFL01.queryPopInfo", new SQFL02());
        return outInfo;

    }


    /**
     * 
     * 科室下拉框
     *
     * @Title: queryDept 
     * @param inInfo
     * datagroupCode   账套
     * @return
     * deptAll         所有科室信息
     * @return: EiInfo
     */
    public EiInfo queryDept(EiInfo inInfo) {
        //获取院区编码
        String datagroupCode = DatagroupUtil.getDatagroupCode();
        //封装集合
        Map map = new HashMap();
        map.put("datagroupCode", datagroupCode);
        //获取科室列表
        EiInfo deptAll = BaseDockingUtils.getDeptAll(map);
        List deptList = (List)deptAll.get("result");
        inInfo.addBlock("dept").addRows(deptList);
        inInfo.getBlock("dept").setBlockMeta(new SQFL01().eiMetadata);
        //返回
        return inInfo;
    }
}
