package com.baosight.wilp.si.cx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.si.cx.domain.SiReportConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存报表配置页面Service
 * @ClassName: ServiceSICX02
 * @Package com.baosight.wilp.si.cx.service
 * @date: 2022年12月26日 10:03
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.数据删除
 * 4.数据插入
 * 5.数据更新
 */
public class ServiceSICX02 extends ServiceBase {

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
     * 页面数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        inInfo.setCell(EiConstant.queryBlock, 0, "dataGroupCode",
                BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        return super.query(inInfo, "SICX02.query", new SiReportConfig());
    }

    /**
     * 删除配置
     * @Title: delete
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        return super.delete(inInfo,"SICX02.delete");
    }

    /**
     * 新增配置
     * @Title: insert
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo insert(EiInfo inInfo) {
        Integer rowCount = inInfo.getBlock(EiConstant.resultBlock).getRowCount();
        for(int index = 0; index<rowCount; index++) {
            inInfo.setCell(EiConstant.queryBlock, index, "dataGroupCode",
                   BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        }
        return super.insert(inInfo,"SICX02.insert");
    }

    /**
     * 跟新配置
     * @Title: update
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo update(EiInfo inInfo) {
        return super.update(inInfo, "SICX02.update");
    }

    /**
     * 查询报表树
     * @Title: queryReportTree
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryReportTree(EiInfo inInfo) {
        //获取参数
        String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        //插叙数据
        List<Map<String, String>> list = dao.query("SICX02.queryReportTree", new HashMap(4){{
            put("node", node); put("dataGroupCode", BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        }});
        //数据返回
        EiInfo outInfo = new EiInfo();
        EiBlock outBlock = outInfo.addBlock(node);
        outBlock.addRows(list);
        return outInfo;
    }
}
