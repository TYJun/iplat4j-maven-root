package com.baosight.wilp.dm.zh.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 宿舍综合页面编辑实际费用子页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMZH0102.java
 * @ClassName: ServiceDMZH0102
 * @Package：com.baosight.wilp.dm.zh.service
 * @author: fangzekai
 * @date: 2022年03月16日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMZH0102 extends ServiceBase {
    /**
     * 页面初始化加载
     * @Title: initLoad
     * @param inInfo
     * @return
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

}
