package com.baosight.wilp.mp.hz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpRequireCollect;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 需求计划汇总页面Service
 * @ClassName: ServiceMPHZ01
 * @Package com.baosight.wilp.mp.hz.service
 * @date: 2022年10月18日 11:35
 *
 * 1.页面加载
 * 2.页面数据查询
 */
public class ServiceMPHZ01 extends ServiceBase {

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
        return super.query(inInfo, "MPLJ06.query", new MpRequireCollect());
    }

    /**delete
     * 删除
     * @param inInfo
     * id 主键
     * @return
     */
    public EiInfo delete(EiInfo inInfo) {
        //获取主键删除明细表和主表
        String id = inInfo.getString("id");
        if(StringUtils.isBlank(id)){
            inInfo.setStatus(-1);
            inInfo.setMsg("请选择一条采购计划进行提交");
            return inInfo;
        }
        String statusCode = inInfo.getString("statusCode");
        if(MpConstant.REQUIRE_COLLECT_STATUS_NEW.equals(statusCode)){
            int delete = dao.delete("MPHZ01.delete",id);
            if(delete > 0) {
                dao.delete("MPHZ01.deleteDetail", id);
                dao.delete("MPHZ01.deleteRequireRelation", id);
            }
        } else {
            inInfo.setStatus(-1);
            inInfo.setMsg("此数据无法删除");
        }
        return inInfo;
    }


    /**submit
     * 提交
     * @param info
     * id 主键
     * @return
     */
    public EiInfo submit(EiInfo info){
        String id = info.getString("id");
        if(StringUtils.isBlank(id)){
            info.setStatus(-1);
            info.setMsg("请选择一条需求计划进行提交");
            return info;
        }
        String statusCode = info.getString("statusCode");
        if(MpConstant.REQUIRE_COLLECT_STATUS_NEW.equals(statusCode) || MpConstant.REQUIRE_COLLECT_STATUS_REJECT.equals(statusCode)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("statusCode", MpConstant.REQUIRE_COLLECT_STATUS_UN_APPROVAL);
            map.put("statusName", "待审批");
            dao.update("MPHZ01.updateRequireCollectStatus", map);
        }else {
            info.setStatus(-1);
            info.setMsg("提交后无须提交");
        }
        return info;
    }

    /**withdraw
     * 撤回
     * @param info
     * id 主键
     * @return
     */
    public EiInfo withdraw(EiInfo info){
        String id = info.getString("id");
        if(StringUtils.isBlank(id)){
            info.setStatus(-1);
            info.setMsg("请选择一条需求汇总计划进行撤回");
            return info;
        }
        String statusCode = info.getString("statusCode");
        if(MpConstant.REQUIRE_COLLECT_STATUS_UN_APPROVAL.equals(statusCode)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("statusCode", MpConstant.REQUIRE_COLLECT_STATUS_NEW);
            map.put("statusName", "新建");
            dao.update("MPHZ01.updateRequireCollectStatus", map);
        }else {
            info.setStatus(-1);
            info.setMsg("此数据无法撤回");
        }
        return info;
    }
}
