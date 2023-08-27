package com.baosight.wilp.mp.hz.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpMatTypeConfigCache;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.SerialNoUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpRequireCollect;
import com.baosight.wilp.mp.lj.domain.MpRequireCollectDetail;
import com.baosight.wilp.mp.lj.domain.MpRequireRelation;
import com.baosight.wilp.mp.lj.service.MpRequireCollectService;
import com.baosight.wilp.mp.pz.domain.MpMatTypeConfig;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 物资领用录入页面Service
 * @ClassName: ServiceRMLY0205
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2023年03月02日 9:11
 */
public class ServiceMPHZ0103 extends ServiceBase {


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

        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "collectDate", DateUtils.curDateStr10());
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "recCreator", UserSession.getLoginName());
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
        /*inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "matTypeNum", config.getMatTypeNum());
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "matType", config.getMatTypeName());*/

        if(MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
            //获取明细
            EiInfo query = query(inInfo);
            inInfo.addBlock(query.getBlock("detail"));
        } else {
            MpRequireCollect collect = collectService.queryRequireCollect(inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id"));
            //详情
            List<MpRequireCollectDetail> detailList = collectService.queryDetailList(inInfo.getCellStr(MpConstant.QUERY_BLOCK
                    , 0, "id"));
            inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "deptNum", collect.getDeptNum());
            inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "collectDeptName", collect.getDeptName());
            inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "purchaseType", collect.getPurchaseType());

            inInfo.setRows("result", detailList);
        }


        return inInfo;
    }



    @Override
    public EiInfo query(EiInfo inInfo) {
        String matTypeNum = inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "matTypeNum");
        String deptNum = inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "deptNum");

        List<String> matTypeNums = StringUtils.isNotEmpty(matTypeNum) ? getChild(matTypeNum) : null;
        //调用对接接口,获取数据
        EiInfo invoke = MpUtils.invoke("MPJK03", "queryDetailList", Arrays.asList("matTypeNums"),
                matTypeNums);
        List<Map> planDetailList = MpUtils.toList(invoke.get("list"), Map.class);
        //明细相同数据合并
        List<Map> detailList = mergeDetail(planDetailList);
        inInfo.setRows("mat", detailList);
        return inInfo;
    }

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


    /**
     * 汇总保存
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {

        List<MpRequireCollectDetail> detailList = new ArrayList<>();

        List<Map> planDetailList = MpUtils.toList(inInfo.get("planDetailList"), Map.class);

        for (Map map : planDetailList) {
            map.put("id", UUID.randomUUID().toString());
            MpRequireCollectDetail detail = new MpRequireCollectDetail();
            detail.fromMap(map);
            detailList.add(detail);
        }


        MpRequireCollect collect = new MpRequireCollect();
        collect.setRecCreator(inInfo.getString("recCreator"));
        collect.setRecCreatorName(inInfo.getString("recCreatorName"));
        collect.setDeptNum(inInfo.getString("collectDeptNum"));
        collect.setDeptName(inInfo.getString("collectDeptName"));
        collect.setPurchaseType(inInfo.getString("purchaseType"));
        //构建汇总主单据和明细集合、汇总关联集合
        List<MpRequireRelation> relationList = buildCollect(collect, planDetailList ,detailList);

        //保存数据
        collectService.insert(collect);
        collectService.insertDetail(detailList);
        collectService.insertRelation(relationList);
        MpUtils.invoke("MPJK03", "updatePlan", Arrays.asList("planDetailList", "deptNum"), planDetailList, inInfo.getString("collectDeptNum"));
        return inInfo;
    }

    /**
     * 汇总编辑保存
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo update(EiInfo inInfo) {

        String collectId = inInfo.getString("collectId");

        //删除明细和关联表数据
        dao.delete("MPHZ01.deleteDetail", collectId);
        dao.delete("MPHZ01.deleteRequireRelation", collectId);


        MpRequireCollect collect = collectService.queryRequireCollect(collectId);

        collect.setPurchaseType(inInfo.getString("purchaseType"));
        collect.setRecRevisor(inInfo.getString("recCreator"));
        collect.setRecReviseTime(inInfo.getString("recCreatorName"));
        collect.setCollectNum(0.00);
        collect.setCollectCost(BigDecimal.ZERO);

        List<MpRequireCollectDetail> detailList = new ArrayList<>();
        List<Map> planDetailList = MpUtils.toList(inInfo.get("planDetailList"), Map.class);

        for (Map map : planDetailList) {
            map.put("id", UUID.randomUUID().toString());
            MpRequireCollectDetail detail = new MpRequireCollectDetail();
            detail.fromMap(map);
            detailList.add(detail);
        }

        //明细赋值
        detailList.forEach(detail -> {
            detail.setId(UUID.randomUUID().toString());
            detail.setCollectId(collect.getId());
            detail.calCost(detail.getNum(), detail.getPrice());
            collect.setCollectNum(MpUtils.doubleAdd(collect.getCollectNum(), detail.getNum()));
            collect.setCollectCost(collect.getCollectCost().add(detail.getCost()));
        });

        List<MpRequireRelation> relationList = new ArrayList<>();
        //关联信息赋值
        planDetailList.forEach(map -> {
            MpRequireRelation relation = new MpRequireRelation();
            relation.setId(UUID.randomUUID().toString());
            relation.setRequireCollectId(collect.getId());
            relation.setRequireId(MpUtils.toString(map.get("planId")));
            relation.setRequireDetailId(MpUtils.toString(map.get("id")));
            relationList.add(relation);
            //移除不必要的字段
            map.remove("eiMetadata");
        });

        //保存数据
        collectService.update(collect);
        collectService.insertDetail(detailList);
        collectService.insertRelation(relationList);
        MpUtils.invoke("MPJK03", "updatePlan", Arrays.asList("planDetailList", "deptNum"), planDetailList, collect.getDeptNum());

        return inInfo;
    }
    /**
     * 构建汇总主单据和明细集合、汇总关联集合
     * @Title: buildCollect
     * @param collect collect : 汇总对象
     * @param planDetailList planDetailList 需求计划明细集合
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpRequireRelation>
     * @throws
     **/
    private List<MpRequireRelation> buildCollect(MpRequireCollect collect, List<Map> planDetailList, List<MpRequireCollectDetail> details) {
        List<MpRequireRelation> list = new ArrayList<>();
        //主单据赋值
        collect.setId(UUID.randomUUID().toString());
        collect.setCollectNo(SerialNoUtils.generateNumberSerialNo("mp_require_collect", "MPS", 8));
        collect.setStatusCode("01");
        collect.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.require.collectStatus", collect.getStatusCode()));
        collect.setRecCreateTime(new Date());
        collect.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));

        //明细赋值
        details.forEach(detail -> {
            detail.setId(UUID.randomUUID().toString());
            detail.setCollectId(collect.getId());
            detail.calCost(detail.getNum(), detail.getPrice());
            collect.setCollectNum(MpUtils.doubleAdd(collect.getCollectNum(), detail.getNum()));
            collect.setCollectCost(collect.getCollectCost().add(detail.getCost()));
        });
        //关联信息赋值
        planDetailList.forEach(map -> {
            MpRequireRelation relation = new MpRequireRelation();
            relation.setId(UUID.randomUUID().toString());
            relation.setRequireCollectId(collect.getId());
            relation.setRequireId(MpUtils.toString(map.get("planId")));
            relation.setRequireDetailId(MpUtils.toString(map.get("id")));
            list.add(relation);
            //移除不必要的字段
            map.remove("eiMetadata");
        });
        return list;
    }


    /**
     * 物资分类联动查询
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryMatType(EiInfo inInfo) {

        String deptNum = inInfo.getCellStr(EiConstant.queryBlock, 0, "deptNum");
        List<Map<String, String>> typeList = MpMatTypeConfigCache.typeTree(deptNum);
        if(CollectionUtils.isEmpty(typeList)) { return inInfo; }

        inInfo.addBlock("matType").addRows(typeList);
        return inInfo;

    }

    public EiInfo queryDeptConfig(EiInfo inInfo) {

        List list = dao.query("MPPZ01.getConfigDept", null);

        inInfo.addBlock("deptNum").addRows(list);
        return inInfo;
    }
}
