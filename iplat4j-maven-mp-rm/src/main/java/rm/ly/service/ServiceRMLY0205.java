package rm.ly.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.common.WareHouseDataSplitUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 物资领用录入页面Service
 * @ClassName: ServiceRMLY0205
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2023年03月02日 9:11
 */
public class ServiceRMLY0205 extends ServiceBase {

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
        inInfo.addBlock("mat").set(EiConstant.limitStr, 50);
        inInfo.setCell("add_claim", 0, "wareHouseNo", "0002");
        inInfo.setCell("add_claim", 0, "wareHouseName", "物资仓库");
        //复制新增
        String id = inInfo.getCellStr(EiConstant.queryBlock, 0, "id");
        if(StringUtils.isNotBlank(id)) {
            RmClaim claim = claimService.queryClaimById(id);
            inInfo.setCell("add_claim", 0, "deptNum", claim.getDeptNum());
            inInfo.setCell("add_claim", 0, "deptName", claim.getDeptName());
            inInfo.setCell("add_claim", 0, "costDeptNum", claim.getCostDeptNum());
            inInfo.setCell("add_claim", 0, "costDeptName", claim.getCostDeptName());
            inInfo.setCell("add_claim", 0, "applyUserNo", claim.getApplyUserNo());
            inInfo.setCell("add_claim", 0, "applyUserName", claim.getApplyUserName());
            //判断是否出库
            if(NumberUtils.toInteger(claim.getStatusCode()) > 50) {
                List<Map<String, String>> list = dao.query("RMLJ02.queryOutWareHouse", claim.getClaimNo());
                if(CollectionUtils.isNotEmpty(list)) {
                    inInfo.setCell("add_claim", 0, "wareHouseNo", list.get(0).get("wareHouseNo"));
                    inInfo.setCell("add_claim", 0, "wareHouseName", list.get(0).get("wareHouseName"));
                }
            }
        }
        return queryStockMat(inInfo);
    }

    /**
     * 查询基础档案物资
     * @Title: queryBaseMat
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryBaseMat(EiInfo inInfo) {
        /**1.获取参数**/
        Map<String, Object> params = CommonUtils.buildParamter(inInfo, RmConstant.QUERY_BLOCK, "mat");
        params.put("dataGroupCode", RmUtils.toString(params.get("dataGroupCode"), BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())));


        /**2.调用本地服务获取物资信息**/
        EiInfo invoke = RmUtils.invoke("RMTY01", "selectMat", params);
        EiBlock matBlock = invoke.getBlock("mat");
        if(matBlock == null || matBlock.getRowCount() == 0){
            return ValidatorUtils.blankInfo(inInfo, "mat");
        }

        /**3.处理预约量和库存量**/
       /* List<Map> rows = matBlock.getRows();
        RmUtils.assignNum(rows, claimService);*/
        inInfo.getBlocks().put("mat", matBlock);
        return inInfo;
    }

    /**
     * 查询库存物资
     * @Title: queryStockMat
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryStockMat(EiInfo inInfo) {
        /**1.获取参数**/
        Map<String, Object> params = CommonUtils.buildParamter(inInfo, RmConstant.QUERY_BLOCK, "mat");
        params.put("dataGroupCode", RmUtils.toString(params.get("dataGroupCode"), BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())));
        params.put("isShowZero", "N");
        Map<String,Object> ordeyBy = inInfo.getBlock("mat").getAttr();
        params.put("orderBy",ordeyBy.get("orderBy"));
        /**2.调用本地服务获取物资信息**/
        EiInfo invoke = RmUtils.invoke("RMJK03", "dockMatStock", params);
        EiBlock resultBlock = invoke.getBlock(RmConstant.RESULT_BLOCK);
        if(resultBlock == null || resultBlock.getRowCount() == 0){
            return ValidatorUtils.blankInfo(inInfo, "mat");
        }

        /**3.如果存在科室,将科室信息带入返回结果**/
        List<Map<String, Object>> rows = resultBlock.getRows();
        for (Map<String, Object> matMap : rows) {
            matMap.put("reserveNum", claimService.queryReserveNum(RmUtils.toString(matMap.get("matNum"))));
            matMap.put("matTypeId", matMap.get("matTypeNum"));
            matMap.put("pictureUri", "/rm/showImg2/"+matMap.get("matTypeNum")+"-"+matMap.get("matNum"));
        }
        inInfo.getBlocks().put("mat", resultBlock);
        return inInfo;
    }

    /**
     * 保存领用单并出库
     * @Title: saveAndOutStock
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo saveAndOutStock(EiInfo inInfo) {
        //保存领用单
        String wareHouseNo = inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "wareHouseNo");
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "claimType",
                WareHouseDataSplitUtils.getClaimTypeByWareHouseNo(wareHouseNo));
        EiInfo invoke = RmUtils.invoke(inInfo, "RMLY0201", "save");
        //获取出库数据
        String claimId = invoke.getString("claimId");
        RmClaim claim = claimService.queryClaimById(claimId);
        List<RmClaimDetail> detailList = claimService.queryClaimDetailList(claimId);
        detailList.forEach(detail -> detail.setOutAmount(detail.getNum()));
        //构建出库数据
        EiInfo claimInfo = new EiInfo();
        claimInfo.setRows(RmConstant.QUERY_BLOCK, new ArrayList(){{add(claim);}});
        claimInfo.setCell(RmConstant.QUERY_BLOCK, 0, "wareHouseNo",
                inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "wareHouseNo"));
        claimInfo.setCell(RmConstant.QUERY_BLOCK, 0, "wareHouseName",
                inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "wareHouseName"));
        claimInfo.set("list", detailList);
        //出库
        claimInfo.set("token", claim.getClaimNo()+claim.getStatusCode());
        EiInfo eiInfo = RmUtils.invoke(claimInfo, "RMLY0203", "outStock");
        return eiInfo;
    }
}
