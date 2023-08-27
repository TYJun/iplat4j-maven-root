package com.baosight.wilp.dm.pj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宿舍管理员评价查看评价履历页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMPJ0302.java
 * @ClassName: ServiceDMPJ0302
 * @Package：com.baosight.wilp.dm.pj.service
 * @author: fangzekai
 * @date: 2022年04月29日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMPJ0302 extends ServiceBase {
    /**
     * 页面内查询接口.
     *
     * @Title: initLoad
     * @param inInfo
     * @return
     *
     * @see ServiceBase#initLoad(EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 此方法用于宿舍管理员评价查看评价履历页面信息查询
     *
     * 逻辑处理：
     * 1.获取参数并转换
     * 2.调用sql方法DMPJ03.queryEvalByManId查询评价履历信息
     *
     * @Title: query
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    public EiInfo query(EiInfo inInfo) {
        Map<String, Object> userInfo = DMUtils.getUserInfo(inInfo.getString("workNo"));
        if(userInfo == null || userInfo.isEmpty()){
            inInfo.setMsg("您的账号存在问题，请联系管理员");
            return inInfo;
        }
        /**
         * 1.获取参数并转换
         */
        String manId = inInfo.getString("manId");
        // 调用工具类转换参数
        Map<String, Object> map = new HashMap<>();
        map.put("manId",manId);

        /**
         * 2.调用sql方法DMPJ03.queryEvalByManId查询评价履历信息
         */
        List<Map<String, Object>> list = dao.query("DMPJ03.queryEvalByManId",map);
        int count = super.count("DMPJ03.countEvalByManId",map);
        // 判断是否存在，存在则构建返回对象
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            inInfo.setMsg("没有查询到数据。");
            return inInfo;
        }
    }

}
