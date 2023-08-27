package com.baosight.wilp.mp.hz.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpRequireCollect;
import com.baosight.wilp.mp.lj.domain.MpRequireCollectDetail;
import com.baosight.wilp.mp.lj.service.MpRequireCollectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 需求汇总子页面Service
 * @ClassName: ServiceMPHZ0101
 * @Package com.baosight.wilp.mp.hz.service
 * @date: 2022年10月18日 16:59
 *
 * 1.页面加载
 * 2.获取物资分类下拉树
 * 3.页面表格数据查询
 * 4.获取需求明细
 * 5.汇总保存
 */
public class ServiceMPHZ0201 extends ServiceBase {

    private static final String ROOT = "root";

    @Autowired
    private MpRequireCollectService collectService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {


        //详情
        MpRequireCollect collect = collectService.queryRequireCollect(inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id"));
        inInfo.setRows(MpConstant.QUERY_BLOCK, new ArrayList(){{ add(collect); }});
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "collectDate", collect.getRecCreateTimeStr().substring(0,10));
        buildDetail(inInfo);
        return inInfo;
    }

    /**
     * 构建查看明细时子页面表格数据
     * @Title: buildDetail
     * @param inInfo inInfo
     * @return void
     * @throws
     **/
    private void buildDetail(EiInfo inInfo) {
        String id = inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id");
        List<String> planDetailIds = collectService.queryRequireDetailIds(id);
        EiInfo invoke = MpUtils.invoke("MPJK03", "queryDetailList", Arrays.asList("detailIds"), planDetailIds);

        //汇总明细表格赋值
        List<MpRequireCollectDetail> details = collectService.queryDetailList(id);
        inInfo.setRows("detail", details);
    }


    /**
     * 页面表格数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        String matTypeNum = inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "matTypeNum");
        //调用对接接口,获取数据
        EiInfo invoke = MpUtils.invoke("MPJK03", "queryDetailList", Arrays.asList("matTypeNums"),
                getChild(matTypeNum));
        List<Map> planDetailList = MpUtils.toList(invoke.get("list"), Map.class);
        inInfo.setRows(MpConstant.RESULT_BLOCK, planDetailList);
        //明细相同数据合并
        List<Map> detailList = mergeDetail(planDetailList);
        inInfo.setRows("detail", detailList);
        return inInfo;
    }

    /**
     * 获取指定分类的子分类
     * @Title: getChild
     * @param matTypeId matTypeId
     * @return java.lang.String
     * @throws
     **/
    private List<String> getChild(String matTypeId) {
        EiInfo invoke = MpUtils.invoke("MPTY01", "getChildMatTypeNumList", Arrays.asList("rootMatTypeNum"), matTypeId);
        return MpUtils.toList(invoke.get("matTypeList"), String.class);
    }

    /**
     * 合并明细中相同物资信息
     * @Title: mergeDetail
     * @param detailList detailList
     * @return void
     * @throws
     **/
    private List<Map> mergeDetail(List<Map> detailList) {
        List<Map> result = new ArrayList<>();
        //按物资编码和物资分类编码分组
        Map<String, List<Map>> listMap = detailList.stream().collect(Collectors.groupingBy(map -> MpUtils.toString(map.get("matNum")) + map.get("matTypeId")));
        //遍历分组
        listMap.forEach((key, value) -> {
            //不存在相同的
            if(value.size() == 1) {
                value.get(0).remove("metas");
                result.addAll(value);
            } else {
                //存在相同的,累加数量
                Map map = value.remove(0);
                //防止改变detailList中的数据,深克隆map
                Map cMap = JSONObject.parseObject(JSONObject.toJSONBytes(map), Map.class);
                cMap.remove("metas");
                value.forEach(detail -> cMap.put("num", MpUtils.doubleAdd(NumberUtils.toDouble(cMap.get("num"), 0d),
                        NumberUtils.toDouble(detail.get("num"), 0d))));
                result.add(cMap);
            }
        });
        return result;
    }
}
