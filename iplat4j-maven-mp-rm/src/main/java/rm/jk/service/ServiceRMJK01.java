package rm.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.SerialNoUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.domain.RmRequirePlan;
import com.baosight.wilp.rm.lj.domain.RmRequirePlanDetail;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import com.baosight.wilp.rm.lj.service.RmRequirePlanService;
import com.baosight.wilp.utils.UUID;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用对外接口(本地服务、微服务)
 * @ClassName: ServiceRMJK01
 * @Package com.baosight.wilp.rm.jk
 * @date: 2022年09月07日 13:51
 *
 * 1.获取需求计划明细集合
 * 2.需求计划汇总回调
 * 3.获取物资预约量
 * 4.红冲出库数据回退
 * 5.生成需求计划
 * 6.更新常用物资配置单价
 */
public class ServiceRMJK01 extends ServiceBase {

    private static final String ORIGINBILLTYPE_CLAIM = "03";

    @Autowired
    private RmRequirePlanService requirePlanService;

    @Autowired
    private RmClaimService claimService;

    /**
     * 获取需求计划明细
     * @Title: queryPlanDetailList
     * @param inInfo inInfo
     *       matTypeNums: 物资分类编码集合
     *       matNum : 物资编码
     *       mat_name : 物资名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *       planIds:	需求计划ID
     *       planNos:    需求计划单号
     *       matNum:	物资编码
     *       matName:	物资名称
     *       matTypeId:	物资分类编码
     *       matTypeName:	物资分类名称
     *       matModel:	物资型号
     *       matSpec:	物资规格
     *       unit:	计量单位
     *       price:	单价
     *       num:	需求数量和
     * @throws
     **/
    public EiInfo queryPlanDetailList(EiInfo inInfo) {
        return super.query(inInfo, "RMLJ01.queryBatchDetail", new RmRequirePlanDetail());
    }

    /**
     * 需求计划汇总回调
     * @Title: updateRequirePlan
     * @param inInfo inInfo
     *      detailList: 分配的需求计划明细集合
     *      backList: 退回的需求计划明细集合
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo requireCollectCallback(EiInfo inInfo) {
        //参数处理
        List<RmRequirePlanDetail> detailList = RmUtils.toList(inInfo.get("detailList"), RmRequirePlanDetail.class);
        List<RmRequirePlanDetail> backList = RmUtils.toList(inInfo.get("backList"), RmRequirePlanDetail.class);
        if(CollectionUtils.isEmpty(detailList) && CollectionUtils.isEmpty(backList)) {
            return ValidatorUtils.errorInfo(inInfo, "参数不能为空");
        }

        /**需求计划明细分配**/
        if(CollectionUtils.isNotEmpty(detailList)) {
            //修改需求计划明细状态
            for (RmRequirePlanDetail dDetail : detailList) {
                dDetail.setHasCollect(1);
                requirePlanService.updateRequirePlanDetailStatus(dDetail);
            }
            //更细需求计划明细分配
            requirePlanService.insertRequirePlanAllot(detailList);

            //更新需求计划状态(部分分配或已分配)
            List<String> planIds = detailList.stream().map(d -> d.getPlanId()).collect(Collectors.toList());
            requirePlanService.updateRequirePlanFinish(StringUtils.join(planIds, ","));
        }

        /**需求计划明细退回**/
        if(CollectionUtils.isNotEmpty(backList)) {
            for (RmRequirePlanDetail bDetail : backList) {
                bDetail.setHasCollect(0);
                requirePlanService.updateRequirePlanDetailStatus(bDetail);
                //更细需求计划明细退回
                requirePlanService.updateRequirePlanAllot(bDetail);
            }

            //更新需求计划状态(部分分配或未分配)
            List<String> planIds = backList.stream().map(d -> d.getPlanId()).distinct().collect(Collectors.toList());
            requirePlanService.updateRequirePlanUnFinish(StringUtils.join(planIds, ","));
        }
        return inInfo;
    }

    /**
     * 修改需求计划明细生成采购计划标记
     * @Title: updateAllotStatus
     * @param inInfo inInfo
     *    requireList: 需求计划明细信息集合(planId&matNum)
     *    status: 生成采购计划标记值
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo updateAllotStatus(EiInfo inInfo) {
        /**获取参数**/
        List<String> list = RmUtils.toList(inInfo.get("requireList"), String.class);
        String status = inInfo.getString("status");
        if(CollectionUtils.isEmpty(list) || StringUtils.isBlank(status)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }

        for (String str : list) {
            String[] split = str.split("&");
            HashMap<String, String> map = new HashMap<>(4);
            map.put("planId", split[0]);
            map.put("matNum", split[1]);
            map.put("status", status);
            dao.update("RMLJ01.updateAllotStatus", map);
        }
        return inInfo;
    }

    /**
     * 获取物资预约量
     * @Title: queryReserveNum
     * @param inInfo inInfo
     *      matNumList: 物资编码集合
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryReserveNum(EiInfo inInfo) {
        List<String> matNumList = RmUtils.toList(inInfo.get("matNumList"), String.class);
        List<Map<String, Object>> list = claimService.queryReserveNums(matNumList);
        inInfo.set("list", "list");
        return inInfo;
    }

    /**
     * 红冲出库数据回退
     * <p>
     *     1.获取参数
     *     2.参数校验
     *     3.领用红冲处理
     * </p>
     * @Title: outStockCallback
     * @param inInfo inInfo
     *      originBillNo:	来源单据号
     *      originBillType:	来源单据类型
     *      originBillTypeName:	来源单据类型名称
     *      list:	出库明细集合
     *          matNum:	物资编码
     *          matName:	物资名称
     *          matModel:	物资型号
     *          matSpec:	物资规格
     *          unitName:	计量单位
     *          outNum:	出库数量
     *          outPrice:	出库单价
     *          outAmount:	出库总价
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo outStockCallback (EiInfo inInfo) throws Exception {
        /**1.获取参数**/
        String originBillNo = inInfo.getString("originBillNo");
        String originBillType = inInfo.getString("originBillType");
        List<Map> list = RmUtils.toList(inInfo.get("list"), Map.class);
        /**2.参数校验**/
        if(StringUtils.isBlank(originBillNo)) {
            return ValidatorUtils.errorInfo(inInfo, "来源单据号参数不能为空");
        }
        if(StringUtils.isBlank(originBillType)) {
            return ValidatorUtils.errorInfo(inInfo, "来源单据类型参数不能为空");
        }
        list = list.stream().filter(map -> NumberUtils.toDouble(map.get("outNum"), 0D) > 0).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo(inInfo, "物资信息不能为空，或数量不能都为0");
        }
        /**3.领用红冲处理**/
        if(ORIGINBILLTYPE_CLAIM.equals(originBillType)) {
            claimCallback(originBillNo, list);
        }
        return inInfo;
    }

    /**
     * 领用红冲出库回调
     * @Title: claimCallback
     * @param originBillNo originBillNo
     * @param list list
     * @return void
     * @throws
     **/
    private void claimCallback(String originBillNo, List<Map> list) throws Exception {
        //获取领用单
        RmClaim claim = claimService.queryClaimByClaimNo(originBillNo);
        if(claim == null){ return; }

        //获取领用明细
        List<RmClaimDetail> detailList = claimService.queryClaimDetailList(claim.getId());

        //遍历、更新明细
        Double totalNum = 0d;
        for (Map map : list) {
            //获取领用明细
            RmClaimDetail claimDetail = detailList.stream().filter(detail -> detail.getOutNum().equals(map.get("matNum"))).findFirst().orElse(null);
            if(claimDetail == null) {continue; }

            //判断红冲出库数量是否大于领用数量
            Double outNum = NumberUtils.toDouble("outNum");
            if(RmUtils.doubleSub(outNum, claimDetail.getNum()) > 0) {
                throw new Exception("红冲出库数量不能大于领用数量");
            }

            //更新明细(减去红冲出库量)
            claimDetail.setOutAmount(-outNum);
            claimService.updateDetail(claimDetail);
            totalNum = RmUtils.doubleAdd(outNum, totalNum);
        }

        //更新领用单
        RmClaim rmClaim = RmClaim.getStatusInstance(claim.getId(), RmUtils.doubleSub(claim.getOutNum(), totalNum) > 0 ? RmConstant.CLAIM_STATUS_PART_OUT
                : RmConstant.CLAIM_STATUS_UN_OUT);
        rmClaim.setOutNum(RmUtils.doubleSub(claim.getOutNum(), totalNum));
        claimService.update(rmClaim);
    }

    /**
     * 生成需求计划
     * <p>
     *     1.获取参数
     *     2.参数校验
     *     3.完善需求需求计划和需求计划明细
     *     4.保存需求需求计划和需求计划明细
     * </p>
     * @Title: genRequirePlan
     * @param inInfo inInfo
     *     planTime : 需求计划时间
     *     planType : 01=年度、02=月度, 03=临时
     *     deptNum : 领用(申请)科室编码
     *     deptName : 领用(申请)科室名称
     *     deptPrincipal : 科室负责人工号
     *     deptPrincipalName : 科室负责人姓名
     *     planDesc : 需求计划描述
     *     remark : 备注/申请理由
     *     recCreator : 创建（申请）人
     *     recCreatorName : 创建（申请）人姓名
     *     dataGroupCode : 账套
     *     details : 需求计划明细集合
     *         matNum : 物资编码
     *         matName : 物资名称
     *         matTypeId : 物资分类编码
     *         matTypeName : 物资分类名称
     *         matSpec : 物资规格
     *         matModel : 物资型号
     *         unit : 计量单位
     *         unitName : 计量单位
     *         price : 单价
     *         num : 计划数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo genRequirePlan(EiInfo inInfo) {
        /**1.获取参数**/
        RmRequirePlan plan = new RmRequirePlan();
        plan.fromMap(inInfo.getAttr());
        List<RmRequirePlanDetail> detailList = RmUtils.toList(inInfo.get("details"), RmRequirePlanDetail.class);

        /**2.参数校验**/
        EiInfo validateInfo = ValidatorUtils.validateEntity(plan);
        if(validateInfo.getStatus() == -1) {
            return validateInfo;
        }
        detailList = detailList.stream().filter(detail -> detail.getNum() != null && detail.getNum() > 0).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(detailList)) {
            return ValidatorUtils.errorInfo("需求计划明细不能为空或需求数量全部为0");
        }

        /**3.完善需求需求计划和需求计划明细**/
        completeRequirePlan(plan, detailList);

        /**4.保存需求计划和需求计划明细**/
        requirePlanService.insertRequirePlan(plan);
        requirePlanService.insertRequirePlanDetail(detailList);
        return inInfo;
    }

    /**
     * 完善需求需求计划和需求计划明细
     * @Title: completeRequirePlan
     * @param plan plan : 需求计划对象
     * @param detailList detailList : 需求计划明细集合
     * @return void
     * @throws
     **/
    private void completeRequirePlan(RmRequirePlan plan, List<RmRequirePlanDetail> detailList) {
        //需求计划信息完善
        plan.setId(UUID.randomUUID().toString());
        //生成需求计划单号
        if(RmConstant.PLAN_TYPE_YEAR.equals(plan.getPlanType())) {
            plan.setPlanNo(SerialNoUtils.generateSerialNo("rm_year_require", "RLY", "yyyy", 8));
        } else if (RmConstant.PLAN_TYPE_MONTH.equals(plan.getPlanType())) {
            plan.setPlanNo(SerialNoUtils.generateSerialNo("rm_month_require", "RLM", "yyyyMM", 6));
        } else {
            plan.setPlanNo(SerialNoUtils.generateSerialNo("rm_temp_require", "RLT", DateUtils.DATE8_PATTERN));
        }
        plan.setPlanTypeName(CommonUtils.getDataCodeItemName("wilp.rm.require.planType", RmConstant.PLAN_TYPE_YEAR));
        plan.setStatusCode(RmConstant.REQUIRE_STATUS_NEW);
        plan.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.require.status", plan.getStatusCode()));

        //需求计划明细信息完善
        for (RmRequirePlanDetail detail : detailList) {
            detail.setId(UUID.randomUUID().toString());
            detail.setPlanId(plan.getId());
            detail.setCost(detail.getNum(), detail.getPrice());
            plan.setPlanNum(plan.getPlanNum() + detail.getNum());
            plan.setPlanCost(plan.getPlanCost().add(detail.getCost()));
        }
    }

    /**
     * 更新科室常用物资的单价
     * @Title: updateDeptMatPrice
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo updateDeptMatPrice(EiInfo inInfo) {
        List<String> matNumList = RmUtils.toList(inInfo.get("matNumList"), String.class);
        dao.update("RMPZ02.updatePrice", matNumList);
        return inInfo;
    }
}
