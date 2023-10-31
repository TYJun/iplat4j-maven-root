package com.baosight.wilp.si.yj.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 生成需求计划子页面Service
 * @ClassName: ServiceSIYJ0201
 * @Package com.baosight.wilp.si.yj.service
 * @date: 2022年11月03日 16:37
 *
 * 1.页面加载
 * 2.生成需求计划信息
 */
public class ServiceSIYJ0201 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //参数初始赋值
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(EiConstant.queryBlock,0, "planTime", DateUtils.curDateStr("yyyy-MM"));
        inInfo.setCell(EiConstant.queryBlock,0, "deptNum", SiUtils.isEmpty(deptMap.get("deptNum")));
        inInfo.setCell(EiConstant.queryBlock,0, "deptName", SiUtils.isEmpty(deptMap.get("deptName")));
        //获取表格数据
        List list = dao.query("SIKC01.query", new HashMap(4) {{
            put("dataGroupCode", SiUtils.getDataGroupCode(UserSession.getLoginName()));
            put("earlyWarning", "Y");
            put("warningStock", "Y");
        }});
        inInfo.setRows(EiConstant.resultBlock, list);
        return inInfo;
    }

    /**
     * 生成需求计划
     * <p>
     *     1.参数处理
     *     2.参数校验
     *     3.将参数转成接口所需参数
     *     4.调用微服务接口，生成需求计划
     * </p>
     * @Title: genPlan
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo genPlan(EiInfo inInfo) {
        /**1.参数处理**/
        Map paramMap = inInfo.getRow(EiConstant.queryBlock, 0);
        List<Map> rows = SiUtils.toList(inInfo.get("rows"), Map.class);

        /**2.参数校验**/
        rows = rows.stream().filter(map -> NumberUtils.toDouble(map.get("num"),0d) > 0).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(rows)) {
            return ValidatorUtils.errorInfo("需求数量不能全部为空");
        }

        /**3.将参数转成接口所需参数**/
        EiInfo info = new EiInfo();
        info.setAttr(paramMap);
        info.set("recCreator", UserSession.getLoginName());
        info.set("recCreatorName", UserSession.getLoginCName());
        info.set("dataGroupCode", SiUtils.getDataGroupCode(null));
        //单价暂时没处理
        rows.forEach(row -> row.put("matTypeId", row.get("matTypeNum")));
        info.set("details", rows);

        /**4.调用微服务接口，生成需求计划**/
        SiUtils.invoke(info,"SIJK03","genRequirePlan",null);
        return inInfo;
    }
}
