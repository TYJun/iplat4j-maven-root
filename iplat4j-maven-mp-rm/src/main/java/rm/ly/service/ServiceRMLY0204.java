package rm.ly.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 领用详情子页面Service
 * @ClassName: ServiceRMLY0204
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2022年09月15日 14:58
 *
 * 1.页面加载
 * 2.查询领用申请明细
 */
public class ServiceRMLY0204 extends ServiceBase {

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
        //获取领用主信息
        List<RmClaim> list = dao.query("RMLJ02.query", inInfo.getRow(RmConstant.QUERY_BLOCK, 0));
        inInfo.setRows(RmConstant.QUERY_BLOCK, list);
        //获取领用明细
        String id = inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "id");
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "claimId", id);
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 50);
        EiInfo outInfo = query(inInfo);
        inInfo.setBlock(outInfo.getBlock(RmConstant.RESULT_BLOCK));
        return inInfo;
    }

    /**
     * 领用明细查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        String statusCode = inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "statusCode");
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "claimId", inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "id"));
        if(RmConstant.CLAIM_STATUS_PART_OUT.equals(statusCode)) {
            inInfo.getBlock(RmConstant.RESULT_BLOCK).set(EiConstant.orderByStr, "d.out_num, d.sort_no asc");
        }
        EiInfo outInfo = super.query(inInfo, "RMLJ02.queryDetail", new RmClaimDetail());
        //处理库存量、处理预约量
        RmUtils.assignNum(outInfo.getBlock(RmConstant.RESULT_BLOCK).getRows(), claimService);
        return outInfo;
    }

}
