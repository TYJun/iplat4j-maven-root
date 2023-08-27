package com.baosight.wilp.mp.pz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpMatTypeConfigCache;
import com.baosight.wilp.mp.pz.domain.MpMatTypeConfig;

import java.util.*;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购科室配置子页面Service
 * @ClassName: ServiceMPPZ0101
 * @Package com.baosight.wilp.mp.pz.service
 * @date: 2022年08月25日 14:01
 *
 * 1.页面加载
 * 2.数据保存
 */
public class ServiceMPPZ0101 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //编辑，数据回显
        if(!MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
            List<MpMatTypeConfig> list = dao.query("MPPZ01.query", new HashMap(2) {{
                put("id", inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id"));
            }});
            inInfo.getBlock(MpConstant.QUERY_BLOCK).setRows(list);
        }
        return inInfo;
    }

    /**
     * 数据保存
     * <p>
     *     1.参数处理
     *     2.判断是新增还是编辑
     *     3.新增,保存数据
     *     4.编辑,更新数据
     * </p>
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {
        /**1.参数处理**/
        MpMatTypeConfig config = new MpMatTypeConfig();
        config.fromMap(inInfo.getBlock(MpConstant.QUERY_BLOCK).getRow(0));

        /**2.判断是新增还是编辑**/
        if(MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
            /**3.新增,保存数据**/
            config.setId(UUID.randomUUID().toString());
            config.setRecCreateTime(new Date());
            config.setRecCreator(UserSession.getLoginName());
            config.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
            dao.insert("MPPZ01.insert", config);
        } else {
            /**4.编辑,更新数据**/
            config.setRecReviseTime(new Date());
            config.setRecRevisor(UserSession.getLoginName());
            dao.update("MPPZ01.update", config);
        }
        //清空缓存
        MpMatTypeConfigCache.clear();
        return inInfo;
    }
}
