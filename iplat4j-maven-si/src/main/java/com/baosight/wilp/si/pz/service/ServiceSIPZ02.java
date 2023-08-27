package com.baosight.wilp.si.pz.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import com.baosight.wilp.si.pz.domain.SiWzUser;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: TODO
 * @ClassName: ServiceSIPZ02
 * @Package com.baosight.wilp.si.pz.service
 * @date: 2023年07月14日 10:40
 */
public class ServiceSIPZ02 extends ServiceBase {

    /**frame数据库名称 */
    private final static String frameSchema = PlatApplicationContext.getProperty("projectSchema");

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 50);
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
        inInfo.setCell(EiConstant.queryBlock, 0, "frameSchema", frameSchema);
        return super.query(inInfo, "SIPZ02.query", new SiWzUser());
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
        dao.delete("SIPZ02.delete", new HashMap(2){{
            put("frameSchema", frameSchema);
            put("ids",ids);
        }});
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
        SiWzUser wzUser = new SiWzUser();
        wzUser.fromMap(inInfo.getRow("save_data", 0));
        String type = "edit"; String opType = "type";
        EiInfo eiInfo = ValidatorUtils.validateEntity(wzUser);
        if(eiInfo.getStatus() == -1) {
            return eiInfo;
        } else {
            wzUser.setFrameSchema(frameSchema);
            if(!type.equals(inInfo.get(opType))) {
                wzUser.setId(UUID.randomUUID().toString());
                dao.insert("SIPZ02.insert", wzUser);
            } else {
                dao.update("SIPZ02.update", wzUser);
            }

        }
        return inInfo;
    }

}
