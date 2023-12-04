package rm.ly.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidateRepeatCache;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 领用出库子页面Service
 * @ClassName: ServiceRMLY0203
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2022年09月16日 16:39
 *
 * 1.页面加载
 * 2.出库
 */
public class ServiceRMLY0203 extends ServiceBase {

    @Autowired
    private RmClaimService claimService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //获取领用申请
        RmClaim claim = new RmClaim();
        claim.fromMap(inInfo.getBlock(RmConstant.QUERY_BLOCK).getRow(0));
        inInfo.setRows(RmConstant.QUERY_BLOCK, claimService.queryClaimList(claim, null, null));
        //获取领用明细
        List<RmClaimDetail> detailList = claimService.queryOutClaimDetailList(claim.getId());
        //detailList = detailList.stream().filter(d -> RmUtils.doubleSub(d.getNum(), d.getOutNum()) > 0).collect(Collectors.toList());
        List<Map<String,Object>> wareListTwo = new ArrayList<>();
        for (RmClaimDetail map : detailList) {
            List<Map<String,Object>> wareList = dao.query("RMLJ02.queryWareHouseNo", map);
            wareListTwo.addAll(wareList);
        }
        List<Map<String,Object>> wareListDeduplication = wareListTwo.stream().distinct().collect(Collectors.toList());
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "wareHouseNo", CollectionUtils.isEmpty(wareListDeduplication)? "":wareListDeduplication.get(0).get("wareHouseNo"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "wareHouseName", CollectionUtils.isEmpty(wareListDeduplication)? "":wareListDeduplication.get(0).get("wareHouseName"));
        //处理库存量
        List<Map> list = JSON.parseArray(JSON.toJSONString(detailList), Map.class);
        RmUtils.assignNum(list, null, new HashMap(4) {{
            put("name", "outAmount");put("field1", "num");put("field2", "outNum"); put("field3", "stockNum");
        }});
        inInfo.setRows(RmConstant.RESULT_BLOCK, list);
        return inInfo;
    }

    /**
     * 页面数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        String id = inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "id");
        List<RmClaimDetail> detailList = claimService.queryOutClaimDetailList(id);
        //处理库存量
        List<Map> list = JSON.parseArray(JSON.toJSONString(detailList), Map.class);
        RmUtils.assignNum(list, null, new HashMap(4) {{
            put("name", "outAmount");put("field1", "num");put("field2", "outNum"); put("field3", "stockNum");
        }});
        inInfo.setRows(RmConstant.RESULT_BLOCK, list);
        return inInfo;
    }

    /**
     * 出库
     * <p>
     *     1.获取参数
     *     2.数据校验
     *     3.处理出库明细数据
     *     4.更新领用单和领用明细
     *     5.出库
     * </p>
     * @Title: outStock
     * @param inInfo inInfo
     *     claimId : 领用单ID
     *     deptNum : 领用科室编码
     *     deptName : 领用科室名称
     *     wareHouseNo : 出库仓库编码
     *     wareHouseName : 出库仓库名称
     *     list : 出库明细集合
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo outStock(EiInfo inInfo) {
        if(ValidateRepeatCache.validateAndPut(inInfo.getString("token"))){
            return ValidatorUtils.errorInfo(inInfo, "重复提交");
        };

        /**1.获取参数**/
        Map params = inInfo.getRow(RmConstant.QUERY_BLOCK, 0);
        List<RmClaimDetail> list = RmUtils.toList(inInfo.get("list"), RmClaimDetail.class);
        //过滤出库数量为0或为空数据**/
        list = list.stream().filter(detail -> detail.getOutAmount() != null && detail.getOutAmount() > 0)
                .collect(Collectors.toList());

        /**2.数据校验**/
        if(StringUtils.isBlank(RmUtils.toString(params.get("wareHouseNo")))) {
            return ValidatorUtils.errorInfo(inInfo, "出库仓库不能为空");
        }
        if(CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo(inInfo, "出库明细不能为空或出库数量不能全部为0或为负数");
        }
        String statusCode = claimService.queryClaimStatusCode(RmUtils.toString(params.get("id")));
        if(!Arrays.asList(RmConstant.CLAIM_STATUS_UN_OUT,RmConstant.CLAIM_STATUS_PART_OUT).contains(statusCode)) {
            return ValidatorUtils.errorInfo(inInfo, "已出库领用单无需再出库");
        }

        if(RmConstant.CLAIM_STATUS_PART_OUT.equals(statusCode) && !validateOutNum(RmUtils.toString(params.get("id")),list)) {
            return ValidatorUtils.errorInfo(inInfo, "出库数量不能大于领用数量");
        }

        /**3.处理出库明细数据**/
        Map<String, Object> outMap = new HashMap<>(32);
        RmClaim claim = calDetail(params, list, outMap);
        if(claim == null) {
            return ValidatorUtils.errorInfo(inInfo, "出库数量不能大于领用数量减去已出库数量");
        }
        /**4.更新领用单和领用明细**/
        claimService.update(claim);
        list.forEach(detail -> claimService.updateDetail(detail));

        /**5.出库**/
        EiInfo outInfo = RmUtils.invoke("RMJK03", "outStock", outMap);
        return outInfo;
    }

    /**
     * 校验出库数量是否大于领用数量
     * @Title: validateOutNum
     * @param id id 领用单ID
     * @param list list : 领用出库明细
     * @return boolean
     * @throws
     **/
    private boolean validateOutNum(String id, List<RmClaimDetail> list) {
        List<RmClaimDetail> detailList = claimService.queryClaimDetailList(id);
        for (RmClaimDetail d : list) {
            for (RmClaimDetail detail : detailList) {
                if(!d.getMatNum().equals(detail.getMatNum()) || detail.getOutNum() == 0) {continue;}
                Double sub = RmUtils.doubleSub(detail.getNum(), detail.getOutNum());
                if(RmUtils.doubleSub(d.getOutAmount(), sub) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 处理页面明细数据
     * <p>
     *     1.遍历出库物资列表
     *     2.构建出库数据
     *     3.判断物资是否已经全部出库
     *     4.构建领用单数据
     * </p>
     * @Title: calDetail
     * @param params params : 参数Map
     * @param list list : 页面出库明细集合
     * @param outMap outMap : 对接出库数据
     * @return com.baosight.wilp.rm.lj.domain.RmClaim
     * @throws
     **/
    private RmClaim calDetail(Map params, List<RmClaimDetail> list, Map<String, Object> outMap) {
        Double totalOutedNum = 0d; boolean hasOutAll = true;

        /**1.遍历出库物资列表**/
        List<Map<String, Object>> outDetailList = new ArrayList<>();
        for (RmClaimDetail claimDetail : list) {
            //出库数量 > 领用数量-已出库数量,返回null
            Double sub = RmUtils.doubleSub(claimDetail.getNum(), claimDetail.getOutNum());
            if(RmUtils.doubleSub(claimDetail.getOutAmount(), sub) > 0) {
                return null;
            }
            //计算总出库数量
            totalOutedNum = RmUtils.doubleAdd(totalOutedNum, claimDetail.getOutAmount());

            //构建出库明细Map
            outDetailList.add(buildOutDetail(claimDetail));

            //判断物资是否已经全部出库
            hasOutAll = hasOutAll && RmUtils.doubleSub(sub, claimDetail.getOutAmount()) == 0;
        }
        /**2.构建出库数据**/
        buildOut(params, outMap);
        outMap.put("list", outDetailList);

        /**3.判断物资是否已经全部出库**/
        if(hasOutAll) {
            hasOutAll = hasOutAll && claimAll(RmUtils.toString(params.get("id")), list);
        }

        /**4.构建领用单数据**/
        RmClaim claim = RmClaim.getStatusInstance(RmUtils.toString(params.get("id")),
                hasOutAll ? RmConstant.CLAIM_STATUS_STOCK_CONFRIM : RmConstant.CLAIM_STATUS_PART_OUT);
        claim.setOutNum(totalOutedNum);
        return claim;
    }

    /**
     * 判断是否领用完成
     * @Title: claimAll
     * @param id id : 领用ID
     * @param list list : 领用明细集合
     * @return boolean
     * @throws
     **/
    private boolean claimAll(String id, List<RmClaimDetail> list) {
        AtomicBoolean isAll = new AtomicBoolean(true);
        List<RmClaimDetail> detailList = claimService.queryOutClaimDetailList(id);
        if(list.size() == detailList.size()) {
            return true;
        }
        //获取差集
        List<RmClaimDetail> collect = detailList.stream().filter(detail -> !list.contains(detail)).collect(Collectors.toList());
        //遍历判断
        collect.forEach(detail -> isAll.set(isAll.get() && RmUtils.doubleSub(detail.getNum(), detail.getOutNum()) == 0));
        return isAll.get();
    }

    /**
     * 出库主体赋值
     * @Title: buildOut
     * @param params params : 页面表单参数Map
     * @param outMap outMap : 出库map
     * @return void
     * @throws
     **/
    private void buildOut(Map params, Map<String, Object> outMap) {
        outMap.put("outType", "06");
        outMap.put("outTypeName", "领用出库");
        outMap.put("originBillNo", params.get("claimNo"));
        outMap.put("originBillType", "03");
        outMap.put("originBillTypeName", "领用");
        outMap.put("remark", params.get("remark"));
        outMap.put("wareHouseNo", params.get("wareHouseNo"));
        outMap.put("wareHouseName", params.get("wareHouseName"));
        outMap.put("userDeptNum", params.get("costDeptNum"));
        outMap.put("userDeptName", params.get("costDeptName"));
        outMap.put("costDeptNum", params.get("costDeptNum"));
        outMap.put("costDeptName", params.get("costDeptName"));
        outMap.put("userWorkerNo", params.get("applyUserNo"));
        outMap.put("userWorkerName", params.get("applyUserName"));
        outMap.put("billMakerName", UserSession.getLoginCName());
        outMap.put("billMaker", UserSession.getLoginName());
        outMap.put("billMakeTime", DateUtils.curDateTimeStr19());
        outMap.put("dataGroupCode", BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
    }

    /**
     * 构建出库明细
     * @Title: buildOutDetail
     * @param claimDetail claimDetail : 领用明细对象
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @throws
     **/
    private Map<String, Object> buildOutDetail(RmClaimDetail claimDetail) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("matNum", claimDetail.getMatNum());
        map.put("matName", claimDetail.getMatName());
        map.put("matTypeNum", claimDetail.getMatTypeId());
        map.put("matTypeName", claimDetail.getMatTypeName());
        map.put("matModel", claimDetail.getMatModel());
        map.put("matSpec", claimDetail.getMatSpec());
        map.put("unit", claimDetail.getUnit());
        map.put("unitName", claimDetail.getUnitName());
        map.put("outNum", claimDetail.getOutAmount());
        map.put("outPrice", claimDetail.getPrice());
        map.put("outAmount", RmUtils.doubleMult(claimDetail.getOutAmount(), claimDetail.getPrice()));
        return map;
    }


}
