package com.baosight.wilp.rm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.WareHouseDataSplitUtils;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 录入领用单子页面Service
 * @ClassName: ServiceRMLY0201
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2022年09月16日 16:37
 *
 * 1.页面加载
 * 2.保存领用单
 */
public class ServiceRMLY0201 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 保存领用单
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {
        String claimType = inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "claimType");
        if(StringUtils.isBlank(claimType)) {
            claimType = getClaimType(inInfo);
        }
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "claimType", claimType);
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCode", RmConstant.CLAIM_STATUS_UN_OUT);
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusName", CommonUtils.getDataCodeItemName("wilp.rm.claim.status", RmConstant.CLAIM_STATUS_UN_OUT));
        inInfo.set("claimLimit","false");
        return RmUtils.invoke(inInfo, "RMLY0101", "save");
    }

    /**
     * 获取领用类型
     * @Title: getClaimType
     * @param inInfo inInfo
     * @return java.lang.String
     * @throws
     **/
    private String getClaimType(EiInfo inInfo) {
        List<RmClaimDetail> detailList = RmUtils.toList(inInfo.get("detailList"), RmClaimDetail.class);
        List<String> wareHouseList = new ArrayList<>();
        for (RmClaimDetail map : detailList) {
            List<Map<String,Object>> wareList = dao.query("RMLJ02.queryWareHouseNo", map);
            if(CollectionUtils.isEmpty(wareList)){ continue; }
            wareHouseList.addAll(wareList.stream().map(e -> e.get("wareHouseNo").toString()).collect(Collectors.toList()));
        }
        String wareHouseNo = wareHouseList.stream().collect(Collectors.toMap(w -> w, w -> 1, Integer::sum))
                .entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey).orElse("");
        return WareHouseDataSplitUtils.getClaimTypeByWareHouseNo(wareHouseNo);
    }
}
