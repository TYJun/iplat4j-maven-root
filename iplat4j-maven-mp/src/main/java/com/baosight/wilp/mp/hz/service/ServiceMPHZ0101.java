package com.baosight.wilp.mp.hz.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.*;
import com.baosight.wilp.mp.lj.domain.MpRequireCollect;
import com.baosight.wilp.mp.lj.domain.MpRequireCollectDetail;
import com.baosight.wilp.mp.lj.domain.MpRequireRelation;
import com.baosight.wilp.mp.lj.service.MpRequireCollectService;
import com.baosight.wilp.mp.pz.domain.MpMatTypeConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
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
public class ServiceMPHZ0101 extends ServiceBase {

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
        //获取专业科室绑定的物资分类
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        MpMatTypeConfig config = MpMatTypeConfigCache.getConfig(MpUtils.toString(deptMap.get("deptNum")));
        if(config == null) { return inInfo; }
        //新增
        if(MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
            inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "collectDate", DateUtils.curDateStr10());
            inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "recCreator", UserSession.getLoginName());
            inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
            inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "matTypeNum", config.getMatTypeNum());
            //获取明细
            EiInfo query = query(inInfo);
            inInfo.addBlock(query.getBlock(MpConstant.RESULT_BLOCK));
            inInfo.addBlock(query.getBlock("detail"));
        } else {
            //详情
            MpRequireCollect collect = collectService.queryRequireCollect(inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id"));
            inInfo.setRows(MpConstant.QUERY_BLOCK, new ArrayList(){{ add(collect); }});
            inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "collectDate", collect.getRecCreateTimeStr().substring(0,10));
            buildDetail(inInfo);
        }
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "matTypeId", config.getMatTypeId());
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "matTypeNum", config.getMatTypeNum());
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "matTypeName", config.getMatTypeName());
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
        //需求计划表格赋值
        inInfo.setRows(MpConstant.RESULT_BLOCK, MpUtils.toList(invoke.get("list"), Map.class));
        //汇总明细表格赋值
        List<MpRequireCollectDetail> details = collectService.queryDetailList(id);
        inInfo.setRows("detail", details);
    }

    /**
     * 物资分类下拉树
     * @Title: queryMatType
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryMatType(EiInfo inInfo) {
        //获取当前节点
        String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        if(ROOT.equals(node)) {
            Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
            List<Map<String, String>> typeList = MpMatTypeConfigCache.typeTree(MpUtils.toString(deptMap.get("deptNum")));
            if(CollectionUtils.isEmpty(typeList)) { return inInfo; }
            inInfo.setRows(node, typeList);
            return inInfo;
        } else {
            return MpUtils.invoke(inInfo, "MPTY01", "selectMatTypeTree");
        }
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

    /**
     * 获取明细
     * @Title: queryDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
   /* public EiInfo queryDetail(EiInfo inInfo) {
        String planIds = inInfo.getString("inqu_status-0-planIds");
        if(StringUtils.isBlank(planIds)){
            return ValidatorUtils.blankInfo("detail");
        }
        //调用微服务接口获取需求计划明细
        EiInfo eiInfo = new EiInfo();
        eiInfo.set("planIds", planIds.split(","));
        EiInfo invoke = MpUtils.invoke(eiInfo, "MPJK03", "queryDetailList");
        List<Map> detailList = MpUtils.toList(invoke.get("list"), Map.class);
        //合并明细中相同的物资
        mergeDetail(detailList);
        inInfo.setRows("detail", detailList);
        return inInfo;
    }*/

    /**
     * 汇总保存
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {
        //参数处理
        MpRequireCollect collect = new MpRequireCollect();
        collect.fromMap(inInfo.getRow(MpConstant.QUERY_BLOCK, 0));
        List<Map> planDetailList = MpUtils.toList(inInfo.get("planDetailList"), Map.class);
        List<MpRequireCollectDetail> details = MpUtils.toList(inInfo.get("detailList"), MpRequireCollectDetail.class);
        //参数校验
        EiInfo validateInfo = ValidatorUtils.validateEntity(collect);
        if(validateInfo.getStatus() == -1) {
            return validateInfo;
        }
        if(CollectionUtils.isEmpty(details)) {
            return ValidatorUtils.errorInfo("汇总明细不能为空");
        }
        //构建汇总主单据和明细集合、汇总关联集合
        List<MpRequireRelation> relationList = buildCollect(collect, planDetailList, details);
        //保存数据
        collectService.insert(collect);
        collectService.insertDetail(details);
        collectService.insertRelation(relationList);
        //更新需求计划状态
        List<String> planIdList = relationList.stream().map(relate -> relate.getRequireId()).collect(Collectors.toList());
        MpUtils.invoke("MPJK03", "updatePlan", Arrays.asList("planDetailList"), planDetailList);
        return inInfo;
    }

    /**
     * 构建汇总主单据和明细集合、汇总关联集合
     * @Title: buildCollect
     * @param collect collect : 汇总对象
     * @param planDetailList planDetailList 需求计划明细集合
     * @param details details : 汇总明细集合
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
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        collect.setDeptNum(MpUtils.toString(deptMap.get("deptNum")));
        collect.setDeptName(MpUtils.toString(deptMap.get("deptName")));

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
}
