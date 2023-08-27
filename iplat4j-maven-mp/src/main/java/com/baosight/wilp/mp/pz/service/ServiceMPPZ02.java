package com.baosight.wilp.mp.pz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConfigCache;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.pz.domain.MpConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购配置页面Service
 * @ClassName: ServiceMPPZ02
 * @Package com.baosight.wilp.mp.pz.service
 * @date: 2022年11月30日 11:46
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.保存配置
 */
public class ServiceMPPZ02 extends ServiceBase {

    /**
     * 页面加载
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: initLoad
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 数据查询
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: query
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        //参数处理
        Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("id", "configCode", "dataGroupCode"));
        map.put("dataGroupCode", MpUtils.toString(map.get("dataGroupCode"), BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())));
        //数据查询
        List<MpConfig> list = dao.query("MPPZ02.query", map);
        inInfo = ValidatorUtils.blankInfo(inInfo, MpConstant.QUERY_BLOCK);
        inInfo.addRows(MpConstant.QUERY_BLOCK, list);
        return inInfo;
    }

    /**
     * 保存配置
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: save
     **/
    public EiInfo save(EiInfo inInfo) {
        //获取参数
        List<Map> rows = inInfo.getBlock(MpConstant.QUERY_BLOCK).getRows();
        if (CollectionUtils.isEmpty(rows)) {
            return ValidatorUtils.errorInfo("配置不能为空");
        }
        //遍历
        List<MpConfig> list = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            MpConfig config = new MpConfig();
            config.fromMap(rows.get(i));
            //校验参数
            EiInfo outInfo = ValidatorUtils.validateEntity(config);
            if (outInfo.getStatus() == -1) {
                return outInfo;
            }
            //补全参数
            if (StringUtils.isBlank(config.getId())) {
                config.setId(UUID.randomUUID().toString());
            }
            config.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
            config.setOrderNo(i);
            list.add(config);
        }
        //保存数据
        MpConfigCache.clearAll();
        dao.delete("MPPZ02.deleteAll", list.get(0).getDataGroupCode());
        dao.insert("MPPZ02.insert", list);
        return inInfo;
    }
}
