package rm.tk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConfigCache;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmBackOut;
import com.baosight.wilp.rm.lj.service.RmBackOutService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 退库申请页面功能开发
 * @ClassName: ServiceRMTK01
 * @Package com.baosight.wilp.rm.tk.service
 * @date: 2022年09月21日 14:55
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.删除退库申请
 * 4.提交退库申请
 * 5.撤回退库申请
 */
public class ServiceRMTK01 extends ServiceBase {

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
        //添加科室查询条件
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptNum",deptMap.get("deptNum"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptName",deptMap.get("deptName"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "recCreatorName",UserSession.getLoginCName());
        RmUtils.initQueryTime(inInfo, "beginTime", "endTime");
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 20);
        return query(inInfo);
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
        return super.query(inInfo, "RMLJ03.query", new RmBackOut());
    }

    /**
     * 删除退库申请
     * @Title: delete
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        String id = inInfo.getString("id");
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //删除,如果删除成功,删除明细
        int delete = backOutService.delete(id);
        if(delete > 0) {
            backOutService.deleteDetail(id);
        }
        return inInfo;
    }

    /**
     * 提交退库申请
     * @Title: submit
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo submit(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //参数校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //校验是否可以提交
        RmBackOut back = backOutService.queryBackOut(id);
        if(back == null || !RmConstant.BACK_OUT_STATUS_NEW.equals(back.getStatusCode())) {
            return ValidatorUtils.errorInfo("退库申请不存在或已提交");
        }
        //提交, 根据配置判断是否需要审批
        String configRadioValue = RmConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                RmConfigCache.RM_CONFIG_BACK_APPROVAL);
        backOutService.update(RmBackOut.getStatusInstance(id, RmConstant.CONFIG_YES.equals(configRadioValue) ? RmConstant.BACK_OUT_STATUS_UN_APPROVAL
                : RmConstant.BACK_OUT_STATUS_UN_OUT));
        return inInfo;
    }

    /**
     * 需求计划撤回
     * @Title: withdraw
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo withdraw(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //参数校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //校验是否可以撤回
        RmBackOut back = backOutService.queryBackOut(id);
        String configRadioValue = RmConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                RmConfigCache.RM_CONFIG_BACK_APPROVAL);
        String backStatus = RmConstant.CONFIG_YES.equals(configRadioValue) ? RmConstant.BACK_OUT_STATUS_UN_APPROVAL : RmConstant.BACK_OUT_STATUS_UN_OUT;
        if(back == null || !backStatus.equals(back.getStatusCode())) {
            return ValidatorUtils.errorInfo("退库申请不存在或无法撤回");
        }
        //撤回
        backOutService.update(RmBackOut.getStatusInstance(id, RmConstant.BACK_OUT_STATUS_NEW));
        return inInfo;
    }
}
