package com.baosight.wilp.di.fk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.di.common.util.DeviceEiUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 巡检发卡管理：发卡初始化、发卡查询、NFC发卡
 * <p>1.发卡初始化 initLoad()
 * <p>2.发卡查询     query()
 * <p>3.NFC发卡      sendCard()
 * @Title: ServiceDIFK01.java
 * @ClassName: ServiceDIFK01
 * @Package：com.baosight.wilp.di.fk.service
 * @author: LENOVO
 * @date: 2021年8月9日 下午4:59:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // gjd
 * <time>    // 2021年8月9日 下午4:59:22
 * <version> // v5.0.0
 * <desc>    // 代码注释
 */
@SuppressWarnings("all")
public class ServiceDIFK01 extends ServiceBase {

    /**
     * 发卡初始化
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param info 无
     * @return
     * spotNum ：地点编码
     * spotName：地点名称
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    public EiInfo initLoad(EiInfo info) {
        return query(info);
    }    
    
    /**
     * 发卡查询
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>获取参数
     * <p>返回查询结果
     * @param info
     * spotNum  :地点编码
     * spotName :地点名称
     * NFCCode  :NFC卡号
     * @return
     * spotNum ：地点编码
     * spotName：地点名称
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    public EiInfo query(EiInfo info) {
        Map<String, String> map=new HashMap<String, String>();
        //获取参数
        String[] param = {"spotNum","spotName","NFCCode"};
        Map<String, Object> buildParam = DeviceEiUtil.buildParam(info, Arrays.asList(param), "result");
        List<Map<String,String>> list = dao.query("DIFK01.queryCard",buildParam);
        int count = dao.count("DIFK01.countCard",buildParam);
        //返回查询结果
        return DeviceEiUtil.buildResult(info, list, count, "result");
    }
    /**
     * 查询卡片地址
     *
     * @Title: querySpotName 
     * @param info
     * cardCode   卡片编号
     * @return 
     * spotName   地址
     * @return: EiInfo
     */
    public EiInfo querySpotName(EiInfo info) {
        EiInfo outInfo=new EiInfo();
        String NFCCode=(String)info.get("cardCode");
        List<Map<String,String>> list=dao.query("DIFK01.querySpotName",NFCCode);
        if(CollectionUtils.isEmpty(list)) {
            outInfo.set("spot", "无");
            return outInfo;
        }
        outInfo.set("spot", list.get(0));
        return outInfo;
    }
    
    /**
     * NFC发卡
     * <p>将卡片号通过地址id和地址绑定
     *
     * @Title: sendCard 
     * @param info
     * cardCode  ：卡片号
     * checkedSpotNum ：地址编号
     * spotId ：地址id
     * @return 
     * 发卡成功或发卡失败
     * @return: EiInfo
     */
    @CrossOrigin
    public EiInfo sendCard(EiInfo info) {
        EiInfo outInfo=new EiInfo();
        String cardCode=(String)info.get("cardCode");
        String checkedSpotNum=(String)info.get("checkedSpotNum");
        String spotId=(String)info.get("spotId");
        //dao.update("DIFK01.updateOld",checkedSpotNum);
        Map<String, String> map=new HashMap<String, String>();
        map.put("cardCode", cardCode);
        map.put("spotId", spotId);
        dao.update("DIFK01.updateNew",map);
        return outInfo;
    }
    
}
