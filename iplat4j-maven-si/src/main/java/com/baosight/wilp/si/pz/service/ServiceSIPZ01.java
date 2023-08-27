package com.baosight.wilp.si.pz.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.SiConfigCache;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import com.baosight.wilp.si.pz.domain.SiConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用配置
 * @ClassName: ServiceSIPZ01
 * @Package com.baosight.wilp.rm.pz.service
 * @date: 2022年09月05日 11:42
 *
 * 1.页面加载
 * 2.页面数据数据
 * 3.保存配置
 */
public class ServiceSIPZ01 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
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
        //参数处理
        Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("id", "configCode", "dataGroupCode"));
        map.put("dataGroupCode", SiUtils.isEmpty(map.get("dataGroupCode"), BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())));
        //数据查询
        List<SiConfig> list = dao.query("SIPZ01.query", map);
        inInfo.addRows(EiConstant.queryBlock, list);
        return inInfo;
    }

    /**
     * 保存配置
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {
        //获取参数
        List<Map> rows = inInfo.getBlock(EiConstant.queryBlock).getRows();
        if(CollectionUtils.isEmpty(rows)) {
            return ValidatorUtils.errorInfo("配置不能为空");
        }
        //遍历
        List<SiConfig> list = new ArrayList<>();
        for (int i=0; i< rows.size(); i++) {
            SiConfig config = new SiConfig();
            config.fromMap(rows.get(i));
            //校验参数
            EiInfo outInfo = ValidatorUtils.validateEntity(config);
            if(outInfo.getStatus() == -1) {
                return outInfo;
            }
            //补全参数
            if(StringUtils.isBlank(config.getId())) {
                config.setId(UUID.randomUUID().toString());
            }
            config.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
            config.setOrderNo(i);
            list.add(config);
        }
        //保存数据
        SiConfigCache.clearAll();
        dao.delete("SIPZ01.deleteAll", list.get(0).getDataGroupCode());
        dao.insert("SIPZ01.insert", list);
        return inInfo;
    }

    /**
     * 获取所有的配置
     * @Title: getAllConfig
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo getAllConfig(EiInfo inInfo) {
        Map<String, SiConfig> configs = SiConfigCache.getConfigs(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        inInfo.set("configs", configs);
        return inInfo;
    }
}
