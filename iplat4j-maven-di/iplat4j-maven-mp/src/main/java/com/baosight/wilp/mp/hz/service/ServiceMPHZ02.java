package com.baosight.wilp.mp.hz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpRequireCollect;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 需求计划审批页面Service
 * @ClassName: ServiceMPHZ01
 * @Package com.baosight.wilp.mp.hz.service
 * @date: 2022年10月18日 11:35
 *
 * 1.页面加载
 * 2.页面数据查询
 */
public class ServiceMPHZ02 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
        MpUtils.initQueryTime(inInfo, "beginTime", "endTime");
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
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "statusCodes",
                Arrays.asList(MpConstant.REQUIRE_COLLECT_STATUS_UN_APPROVAL, MpConstant.REQUIRE_COLLECT_STATUS_PASS));
        return super.query(inInfo, "MPLJ06.query", new MpRequireCollect());
    }


    /**
     * 审批通过
     * @Title: pass
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo pass(EiInfo info) {
        String id = info.getString("id");
        if(StringUtils.isBlank(id)){
            info.setStatus(-1);
            info.setMsg("请选择一条采购计划进行审批");
            return info;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("statusCode", MpConstant.REQUIRE_COLLECT_STATUS_PASS);
        map.put("statusName", "审批通过");
        dao.update("MPHZ01.updateRequireCollectStatus", map);
        return info;
    }


    /**
     * 驳回
     * @Title: reject
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo reject(EiInfo info) {
        String id = info.getString("id");
        if(StringUtils.isBlank(id)){
            info.setStatus(-1);
            info.setMsg("请选择一条采购计划进行审批");
            return info;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("statusCode", MpConstant.REQUIRE_COLLECT_STATUS_REJECT);
        map.put("statusName", "审批驳回");
        dao.update("MPHZ01.updateRequireCollectStatus", map);
        return info;
    }

    /**
     * 采购计划管理删除回调
     * @Title  purchasePlanDelCallback
     * @author lt
     * @date 2023-05-09 17:31
     * @param inInfo 
     * @return com.baosight.iplat4j.core.ei.EiInfo
     */
    public EiInfo purchasePlanDelCallback(EiInfo inInfo) {
        String id = inInfo.getString("resourceId");
        if(StringUtils.isBlank(id)){
            return ValidatorUtils.errorInfo(inInfo, "参数不能为空");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("statusCode", MpConstant.REQUIRE_COLLECT_STATUS_PASS);
        map.put("statusName", "审批通过");
        dao.update("MPHZ01.updateRequireCollectStatus", map);
        return inInfo;
    }
}
