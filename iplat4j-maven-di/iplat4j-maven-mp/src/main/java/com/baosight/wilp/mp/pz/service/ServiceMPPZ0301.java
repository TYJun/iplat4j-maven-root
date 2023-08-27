package com.baosight.wilp.mp.pz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.pz.domain.MpMatTypeConfig;
import com.baosight.wilp.mp.pz.domain.MpPurchaseTypeConfig;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购份额配置子页面Service
 * @ClassName: ServiceMPPZ0301
 * @Package com.baosight.wilp.mp.pz.service
 * @date: 2022年08月25日 14:01
 *
 * 1.页面加载
 * 2.数据保存
 */
public class ServiceMPPZ0301 extends ServiceBase {

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
            List<MpMatTypeConfig> list = dao.query("MPPZ03.query", new HashMap(2) {{
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
        MpPurchaseTypeConfig config = new MpPurchaseTypeConfig();

        config.fromMap(inInfo.getBlock(MpConstant.QUERY_BLOCK).getRow(0));

        HashMap<String, String> map = new HashMap<>();
        map.put("purchaseYear", config.getPurchaseYear());
        map.put("purchaseType", config.getPurchaseType());

        List<MpPurchaseTypeConfig> query = dao.query("MPPZ03.query", map);
        if(CollectionUtils.isNotEmpty(query)){
            inInfo.setStatus(-1);
            inInfo.setMsg(query.get(0).getPurchaseYear()+"年-"+query.get(0).getPurchaseTypeName()+"数据已存在" );
            return inInfo;
        }

        /**2.判断是新增还是编辑**/
        if(MpConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(MpConstant.OPERATE_NAME))){
            /**3.新增,保存数据**/
            config.setId(UUID.randomUUID().toString());
            config.setRecCreateTime(DateUtils.curDateTimeStr19());
            config.setRecCreator(UserSession.getLoginName());
            config.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
            dao.insert("MPPZ03.insert", config);
        } else {
            /**4.编辑,更新数据**/
            config.setRecReviseTime(DateUtils.curDateTimeStr19());
            config.setRecRevisor(UserSession.getLoginName());
            dao.update("MPPZ03.update", config);
        }
        return inInfo;
    }
}
