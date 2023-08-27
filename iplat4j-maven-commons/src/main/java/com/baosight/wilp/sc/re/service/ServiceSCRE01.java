package com.baosight.wilp.sc.re.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceSCRE01 extends ServiceBase {
    public EiInfo getWeibao(EiInfo inInfo) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> list1  = dao.query("SCRE01.faInfo",null);
        result.put("固定资产台账",list1);
        List<Map<String,Object>> list2  = dao.query("SCRE01.faTypeCount",null);
        result.put("固定资产分类占比",list2);
        List<Map<String,Object>> list3  = dao.query("SCRE01.faSumCount",null);
        result.put("固定资产增加量",list3);
        List<Map<String,Object>> list4  = dao.query("SCRE01.diTaskCountDay",null);
        result.put("日巡检工作量",list4);
        List<Map<String,Object>> list5  = dao.query("SCRE01.diTaskCountWeek",null);
        result.put("周巡检工作量",list5);
        List<Map<String,Object>> list6  = dao.query("SCRE01.diTaskCountMonth",null);
        result.put("月巡检工作量",list6);
        List<Map<String,Object>> list7  = dao.query("SCRE01.dkTaskCountDay",null);
        result.put("日保养工作量",list7);
        List<Map<String,Object>> list8  = dao.query("SCRE01.dkTaskCountWeek",null);
        result.put("周保养工作量",list8);
        List<Map<String,Object>> list9  = dao.query("SCRE01.dkTaskCountMonth",null);
        result.put("月保养工作量",list9);
        List<Map<String,Object>> list10  = dao.query("SCRE01.didkDetail",null);
        result.put("未完工任务",list10);
        List<Map<String,Object>> list11  = dao.query("SCRE01.dfTypeCount",null);
        result.put("设备类型",list11);
        List<Map<String,Object>> list12  = dao.query("SCRE01.dfStatusCount",null);
        result.put("设备状态",list12);
        List<Map<String,Object>> list13  = dao.query("SCRE01.didkTaskCount",null);
        result.put("巡检保养完工量",list13);
        List<Map<String,Object>> list14  = dao.query("SCRE01.dfTypeTop",null);
        result.put("通用设备类型前五",list14);
        EiInfo info = new EiInfo();
        info.set("result",result);
        return info;
    }

    public EiInfo getAnbao(EiInfo inInfo) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> list1  = dao.query("SCRE01.prTypeCount",null);
        result.put("安全问题报单类型",list1);
        List<Map<String,Object>> list2  = dao.query("SCRE01.mxStatus",null);
        result.put("供配电状态",list2);
        List<Map<String,Object>> list3  = dao.query("SCRE01.imTaskCount",null);
        result.put("安全巡查班组工作量",list3);
        List<Map<String,Object>> list4  = dao.query("SCRE01.imAreaTaskCount",null);
        result.put("安全巡查区域工单统计",list4);
        List<Map<String,Object>> list5  = dao.query("SCRE01.prSumCount",null);
        result.put("安全问题数据统计",list5);
        List<Map<String,Object>> list6  = dao.query("SCRE01.prManCount",null);
        result.put("安全问题上报人员",list6);
        EiInfo info = new EiInfo();
        info.set("result",result);
        return info;
    }

    public EiInfo getGuihua(EiInfo inInfo) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> list1  = dao.query("SCRE01.pmStatusCount",null);
        result.put("工程项目统计",list1);
        List<Map<String,Object>> list2  = dao.query("SCRE01.pmIncrease",null);
        result.put("工程项目环比增长量",list2);
        List<Map<String,Object>> list3  = dao.query("SCRE01.cmStatusCount",null);
        result.put("合同月度统计",list3);
        List<Map<String,Object>> list4  = dao.query("SCRE01.cmExecute",null);
        result.put("合同列表",list4);
        List<Map<String,Object>> list5  = dao.query("SCRE01.itStatusCount",null);
        result.put("独立任务状态",list5);
        List<Map<String,Object>> list6  = dao.query("SCRE01.wpStatusCount",null);
        result.put("工作计划状态",list6);
        List<Map<String,Object>> list9  = dao.query("SCRE01.itwpSumCount",null);
        result.put("工作计划独立任务",list9);
        List<Map<String,Object>> list7  = dao.query("SCRE01.hiAreaCount",null);
        result.put("医院标识区域",list7);
        List<Map<String,Object>> list8  = dao.query("SCRE01.hiStatusCount",null);
        result.put("医院标识状态",list8);
        List<Map<String,Object>> list11  = dao.query("SCRE01.hiClassifyCount",null);
        result.put("医院标识类型",list11);
        EiInfo info = new EiInfo();
        info.set("result",result);
        return info;
    }

    public EiInfo getZhikong(EiInfo inInfo) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> list1  = dao.query("SCRE01.csDeptCount",null);
        result.put("保洁热点科室",list1);
        List<Map<String,Object>> list2  = dao.query("SCRE01.csItemCount",null);
        result.put("保洁事项分布",list2);
        List<Map<String,Object>> list3  = dao.query("SCRE01.cpStatusCount",null);
        result.put("服务投诉",list3);
        List<Map<String,Object>> list4  = dao.query("SCRE01.hrManCount",null);
        result.put("第三方人员统计",list4);
        List<Map<String,Object>> list5  = dao.query("SCRE01.csStatusCount",null);
        result.put("保洁工单状态",list5);
        List<Map<String,Object>> list6  = dao.query("SCRE01.csTimeCount",null);
        result.put("保洁工单概览",list6);
        List<Map<String,Object>> list7  = dao.query("SCRE01.csTaskCount",null);
        result.put("近七天保洁工单",list7);
        List<Map<String,Object>> list8  = dao.query("SCRE01.sqScoreCount",null);
        result.put("满意度",list8);
        EiInfo info = new EiInfo();
        info.set("result",result);
        return info;
    }

    public EiInfo getShitang(EiInfo inInfo) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> list1  = dao.query("SCRE01.siMatNum",null);
        result.put("物资库存量",list1);
        List<Map<String,Object>> list2  = dao.query("SCRE01.siInOutCount",null);
        result.put("进出库物资数量",list2);
        List<Map<String,Object>> list3  = dao.query("SCRE01.siMatCount",null);
        result.put("物资数量分布",list3);
        List<Map<String,Object>> list4  = dao.query("SCRE01.mpOrderStatus",null);
        result.put("采购单状态",list4);
        List<Map<String,Object>> list5  = dao.query("SCRE01.mpOrderType",null);
        result.put("采购物资种类",list5);
        List<Map<String,Object>> list6  = dao.query("SCRE01.mpOrderMoney",null);
        result.put("月度采购金额",list6);
        List<Map<String,Object>> list7  = dao.query("SCRE01.scOrderCount",null);
        result.put("点餐人次",list7);
        List<Map<String,Object>> list8  = dao.query("SCRE01.scOrderArea",null);
        result.put("热门点餐区域",list8);
        List<Map<String,Object>> list9  = dao.query("SCRE01.scMealSale",null);
        result.put("本周菜品销量",list9);
        EiInfo info = new EiInfo();
        info.set("result",result);
        return info;
    }
}
