package com.baosight.wilp.ms.pa.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.ms.dc.domain.MSDC01;
import com.baosight.wilp.ms.pa.domain.MSPA01;
import com.baosight.wilp.ms.pa.domain.T_Param_Classify;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wzy
 * 参数报警人页面的 插入生成员工信息类
 * @title: ServiceMSPA01
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021/8/217:44
 */
public class ServiceMSPA0103 extends ServiceBase {
    /**
     * 页面初始化方法
     * 根据对外服务接口然后生成出一进页面就展示10条的数据
     *
     * @param inInfo
     * @return
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @author Wzy
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.set(EiConstant.serviceId, "S_AC_FW_01");
        inInfo.set("offset", "0");
        inInfo.set("limit", "10");
        EiInfo outInfo = XServiceManager.call(inInfo);
        return outInfo;
    }

    /**
     * @throws
     * @title 参数报警人页面的 新增插入按钮的  查询功能
     * 1.根据对外服务接口来查询员工信息
     * 2.判断工号是否为空 精确查询
     * 3.判断姓名是否为空 模糊查询
     * 4.判断总页数和分页 然后进行塞值
     * @description
     * @author Wzy
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @updateTime 2021/9/7 14:14
     */
    public EiInfo query(EiInfo inInfo) {
        /**
         * 根据对外服务接口来查询员工信息
         */
        inInfo.set(EiConstant.serviceId, "S_AC_FW_01");
        if (inInfo.getBlocks().get("inqu_status") != null) {
            EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
            if (inqu_status.getRows().get(0) != null) {
                Map o = (Map) inqu_status.getRows().get(0);
                /**
                 * 判断工号是否为空 精确查询
                 */
                if (o.get("login") != null && !o.get("login").toString().trim().equals("")) {
                    String login = o.get("login").toString().trim();
                    inInfo.set("workNo", login);
                }
                /**
                 * 判断姓名是否为空 模糊查询
                 */
                if (o.get("name") != null && !o.get("name").toString().trim().equals("")) {
                    String name = (String) o.get("name");
                    inInfo.set("userName", name);
                }
            }
        }
        /**
         * 判断总页数和分页 然后进行塞值
         */
        if (inInfo.getBlocks().get("result") != null) {
            EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
            if (result.getAttr() != null) {
                if (result.getAttr().get("offset") != null) {
                    inInfo.set("offset", result.getAttr().get("offset"));
                }
                if (result.getAttr().get("limit") != null) {
                    inInfo.set("limit", result.getAttr().get("limit"));
                }
            }
        }
        EiInfo outInfo = XServiceManager.call(inInfo);
        return outInfo;
    }
}
