package rm.ly.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.WareHouseDataSplitUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 领用管理页面Service
 * @ClassName: ServiceRMLY02
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2022年09月16日 16:18
 *
 * 1.页面加载
 * 2.数据查询
 */
public class ServiceRMLY02 extends ServiceBase {

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
        RmUtils.initQueryTime(inInfo, "beginTime", "endTime");
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCodes","50,60");
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 50);
        EiInfo outInfo =  query(inInfo);
        //设置状态查询条件
        List<Map<String, String>> list = CommonUtils.getDataCode("wilp.rm.claim.status");
        list = list.stream().filter(map -> NumberUtils.toInteger(map.get("value")) > 20).collect(Collectors.toList());
        outInfo.setRows("status", list);
        //修复被覆盖的状态
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCodes","50,60");
        outInfo.setRows("detail", new ArrayList());
        return outInfo;
    }

    /**
     * 数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        String statusCodes = inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "statusCodes");
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "claimType",
                WareHouseDataSplitUtils.getClaimType(UserSession.getLoginName()));
        if(StringUtils.isNotBlank(statusCodes)) {
            inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCodes",statusCodes.split(","));
        }
        return super.query(inInfo, "RMLJ02.query", new RmClaim());
    }

    /**
     * 查询领用明细查询
     * @Title: queryDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDetail(EiInfo inInfo) {
        String statusCode = claimService.queryClaimStatusCode(inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "claimId"));
        if(RmConstant.CLAIM_STATUS_PART_OUT.equals(statusCode)) {
            inInfo.getBlock("detail").set(EiConstant.orderByStr, "d.out_num, d.sort_no asc");
        }
        EiInfo outInfo = super.query(inInfo, "RMLJ02.queryDetail", new RmClaimDetail(),false,null, null, "detail", "detail");
        //处理库存量、处理预约量
        RmUtils.assignNum(outInfo.getBlock("detail").getRows(), claimService);
        return outInfo;
    }

    /**
     * 领用单部分状态查询
     * @Title: queryStatus
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    /*public EiInfo queryStatus(EiInfo inInfo) {
        String data = "[{\"label\":\"待仓库审批\",\"value\":\"30\"},{\"label\":\"待出库\",\"value\":\"50\"},{\"label\":\"部分出库\",\"value\":\"60\"}]";
        List<Map> list = JSON.parseArray(data, Map.class);
        inInfo.setRows("status", list);
        return inInfo;
    }*/
}
