package com.baosight.wilp.ss.wx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.wx.domain.SSWxThirdCompany;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * TODO(这里用一句话描述这个方法的作用)
 *
 * @Title: ServiceSCWXThirdCompany
 * @ClassName: ServiceSCWXThirdCompany
 * @Package: com.baosight.wilp.ss.wx.service
 * @author: liu
 * @date: 2022-10-27 9:54
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History: // 历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version> // 版本
 * <desc>   // 描述修改内容
 */
public class ServiceSSWXThirdCompany extends ServiceBase {


    public EiInfo getCompany(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

        List<SSWxThirdCompany> companyList = dao.query("SSWXThirdCompany.query", attr);

        if(CollectionUtils.isEmpty(companyList)){
            outInfo.setStatus(-1);
            return outInfo;
        }
        outInfo.set("company",companyList.get(0));
        return outInfo;
    }


    public EiInfo insert(EiInfo inInfo) {

        SSWxThirdCompany company = (SSWxThirdCompany) inInfo.getAttr().get("param");

        dao.insert("SSWXThirdCompany.insert",company);
        return inInfo;
    }




}
