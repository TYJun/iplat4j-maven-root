package rm.tk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.*;
import com.baosight.wilp.rm.lj.domain.RmBackOut;
import com.baosight.wilp.rm.lj.domain.RmBackOutDetail;
import com.baosight.wilp.rm.lj.service.RmBackOutService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资退库子页面Service
 * @ClassName: ServiceRMTK0101
 * @Package com.baosight.wilp.rm.tk.service
 * @date: 2022年10月24日 13:38
 *
 * 1.页面加载
 * 2.保存退库申请
 */
public class ServiceRMTK0101 extends ServiceBase {

    @Autowired
    private RmBackOutService backOutService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //编辑,数据回显
        if(!RmConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(RmConstant.OPERATE_NAME))) {
            //获取退库主信息
            List<RmBackOut> list = dao.query("RMLJ03.query", inInfo.getRow(RmConstant.QUERY_BLOCK, 0));
            inInfo.setRows(RmConstant.QUERY_BLOCK, list);
            //获取退库明细信息
            List<RmBackOutDetail> detailList = backOutService.queryBackOutDetailList(inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "id"));
            inInfo.setRows(RmConstant.RESULT_BLOCK, detailList);
        } else {
            Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
            inInfo.setCell(RmConstant.QUERY_BLOCK,0, "deptNum", RmUtils.toString(deptMap.get("deptNum")));
            inInfo.setCell(RmConstant.QUERY_BLOCK,0, "deptName", RmUtils.toString(deptMap.get("deptName")));
            inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "recCreator", UserSession.getLoginName());
            inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
        }
        return inInfo;
    }

    /**
     * 保存退库申请
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {
        //获取参数
        RmBackOut back = new RmBackOut();
        back.fromMap(inInfo.getRow(RmConstant.QUERY_BLOCK, 0));
        List<RmBackOutDetail> details = RmUtils.toList(inInfo.get("list"), RmBackOutDetail.class);
        //参数校验
        details = details.stream().filter(detail -> detail.getNum() != null && detail.getNum() > 0).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(details)) {
            return ValidatorUtils.errorInfo("退库明细不能为空或数量全部为0");
        }
        //校验退库数量是否合适
        EiInfo validateInfo = validateBackOutNum(details);
        if(validateInfo.getStatus() < 0) {
            return validateInfo;
        }
        //判断是新增还是编辑
        if(RmConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(RmConstant.OPERATE_NAME))) {
            //新增: 构建退库及完善退库明细
            buildBackOut(back, details, true);
            backOutService.insert(back);
        } else {
            //编辑: 完善退库明细,删除原先退库明细
            buildBackOut(back, details, false);
            backOutService.update(back);
            backOutService.deleteDetail(back.getId());
        }
        //新增退库明细
        backOutService.insertDetail(details);
        return inInfo;
    }

    /**
     * 校验退库数量是否大于实际领用数量
     * @Title: validateBackOutNum
     * @param details details
     * @return boolean
     * @throws
     **/
    private EiInfo validateBackOutNum(List<RmBackOutDetail> details) {
        long count = details.stream().filter(detail -> detail.getActualClaimNum() < detail.getCurOutNum()).count();
        if(count > 0) {
            return ValidatorUtils.errorInfo("退库数量不能大于实际领用数量");
        }
        //获取领用单之前对应的退库明细
        List<Map<String, Object>> list = backOutService.queryBackOutDetailListByClaimNo(details.get(0).getClaimNo());
        for (RmBackOutDetail detail : details) {
            Map<String, Object> cMap = list.stream().filter(map -> map.get("key").equals(detail.getMatNum().concat(detail.getMatTypeId()))).findFirst().orElse(null);
            Double subNum = RmUtils.doubleSub(detail.getActualClaimNum(), detail.getCurOutNum());
            if(cMap == null || RmUtils.doubleSub(subNum, NumberUtils.toDouble(cMap.get("sumNum"))) > 0){
                continue;
            } else {
                return ValidatorUtils.errorInfo(detail.getMatName()+"物资的本次退库数量不能大于实际领用数量减去累计退库数量");
            }
        }
        return new EiInfo();
    }

    /**
     * 构建退库对象及完善退库明细
     * @Title: buildBackOut
     * @param back back : 退库对象
     * @param details details : 退库明细集合
     * @param addFlag addFlag : 新增标记
     * @return void
     * @throws
     **/
    private void buildBackOut(RmBackOut back, List<RmBackOutDetail> details, boolean addFlag) {
        //根据配置,判断是否跳过审批
        if(RmConstant.BACK_OUT_STATUS_UN_APPROVAL.equals(back.getStatusCode())) {
            String configRadioValue = RmConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                    RmConfigCache.RM_CONFIG_BACK_APPROVAL);
            back.setStatusCode(RmConstant.CONFIG_YES.equals(configRadioValue) ? RmConstant.BACK_OUT_STATUS_UN_APPROVAL
                    : RmConstant.BACK_OUT_STATUS_UN_OUT);
        }
        //退库主单据数据
        back.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.backOut.status", back.getStatusCode()));
        if(addFlag) {
            back.setId(UUID.randomUUID().toString());
            back.setBackOutNo(SerialNoUtils.generateNumberSerialNo("rm_back_out", "RMO", 8));
            back.setRecCreateTime(new Date());
            back.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        } else {
            back.setRecRevisor(UserSession.getLoginName());
            back.setRecReviseTime(new Date());
        }
        //退库明细数据处理
        details.forEach(detail -> {
            detail.setId(UUID.randomUUID().toString());
            detail.setBackOutId(back.getId());
            back.setBackOutNum(RmUtils.doubleAdd(back.getBackOutNum(), detail.getNum()));
        });
    }
}
