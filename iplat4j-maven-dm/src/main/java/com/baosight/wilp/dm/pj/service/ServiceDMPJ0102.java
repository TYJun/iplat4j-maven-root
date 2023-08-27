package com.baosight.wilp.dm.pj.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.dm.common.domain.DMConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 宿舍满意度评价历史查询页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMPJ0102.java
 * @ClassName: ServiceDMPJ0102
 * @Package：com.baosight.wilp.dm.pj.service
 * @author: fangzekai
 * @date: 2022年09月09日 下午2:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMPJ0102 extends ServiceBase {
    /**
     * 页面内查询接口.
     * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
     * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
     * 3、将参数赋给inInfo并调用本地服务DMHS01.query()方法进行列表数据查询.
     * 添加参数view.
     * view：标记.
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
     * 此方法用于查询历史评价信息
     *
     * 逻辑处理：
     * 1.将要查询的参数组成数组并调用工具类转换参数
     * 2.调用sql方法DMPJ01.querySelfEvalHistory查询历史评价信息
     *
     * @Title: query
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    public EiInfo query(EiInfo inInfo) {
        /*
         * 1、将要查询的参数组成数组并调用工具类转换参数
         */
        String[] param = {"evalMonth"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        String manId = inInfo.getString("manId");
        map.put("manId",manId);

        /**
         * 2.调用sql方法DMPJ01.querySelfEvalHistory查询历史评价信息
         */
        List<Map<String, Object>> list = dao.query("DMPJ01.querySelfEvalHistory",map);
        int count = super.count("DMPJ01.countSelfEvalHistory",map);
        // 判断是否存在，存在则构建返回对象
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            inInfo.setMsg("没有查询到数据。");
            return inInfo;
        }
    }

}
