package com.baosight.wilp.si.pz.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import com.baosight.wilp.si.pz.domain.SiConfigMatRootType;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 仓库与物资大类关联配置页面service
 * @ClassName: ServiceSIPZ03
 * @Package com.baosight.wilp.si.pz.service
 * @date: 2023年07月24日 11:19
 */
public class ServiceSIPZ03 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //页面数据查询
        inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 50);
        EiInfo query = query(inInfo);
        //加载多选下拉框数据源
        EiInfo matRootType = queryMatRootType(inInfo);
        query.setBlock(matRootType.getBlock("matRootType"));
        return query;
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
        return super.query(inInfo, "SIPZ03.query", new SiConfigMatRootType());
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
        List<String> ids = SiUtils.toList(inInfo.get("ids"), String.class);
        if(CollectionUtils.isEmpty(ids)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        dao.delete("SIPZ03.delete", ids);
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
        SiConfigMatRootType config = new SiConfigMatRootType();
        config.fromMap(inInfo.getRow("save_data", 0));
        String type = "edit"; String opType = "type";
        EiInfo eiInfo = ValidatorUtils.validateEntity(config);
        if(eiInfo.getStatus() == -1) {
            return eiInfo;
        } else {
            if(!type.equals(inInfo.get(opType))) {
                config.setId(UUID.randomUUID().toString());
                dao.insert("SIPZ03.insert", config);
            } else {
                dao.update("SIPZ03.update", config);
            }
        }
        return inInfo;
    }

    /**
     * 获取物资大类
     * @Title: queryMatRootType
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryMatRootType(EiInfo inInfo) {
        List<Map<String, String>> list = CommonUtils.getDataCode("wilp.ac.gm.type");
        inInfo.setRows("matRootType", list);
        return inInfo;
    }

    /**
     * 获取指定仓库的物资大类
     * @Title: queryMatRootTypes
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryMatRootTypes(EiInfo inInfo) {
        List<String> wareHouseNos = SiUtils.toList(inInfo.get("wareHouseNos"), String.class);
        List<String> list = dao.query("SIPZ03.queryMatRootTypes", wareHouseNos);
        inInfo.set("matRootTypes", list);
        return inInfo;
    }

    public EiInfo queryApplyMatRootTypes(EiInfo inInfo) {
        List<String> wareHouseNos = SiUtils.toList(inInfo.get("wareHouseNos"), String.class);
        List<String> list = dao.query("SIPZ03.queryApplyMatRootTypes", wareHouseNos);
        inInfo.set("applyMatRootTypes", list);
        return inInfo;
    }
}
