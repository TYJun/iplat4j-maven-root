package com.baosight.wilp.cu.yd.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.utils.AppLoginUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @PackageName com.baosight.wilp.cu.yd.service
 * @ClassName ServiceCUYD02
 * @Description app接口和frame模块调用的中转工具类
 * @Author chunchen2
 * @Date 2021/12/6 13:44
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/12/6 13:44
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceCUYD02 extends ServiceBase {

    /**
     * @Title getResource
     * @Author chunchen2
     * @Description // 根据员工id或者员工的工号获取app的资源权限列表
     * @Date 13:48 2021/12/6
     * @param
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo getResource(EiInfo inEiInfo){

        EiInfo outEiInfo = new EiInfo();

        String workNo = inEiInfo.getString("workNo");
        String userId = inEiInfo.getString("userId");

        if(StringUtils.isEmpty(workNo) && StringUtils.isEmpty(userId)){
            outEiInfo.setStatus(-1);
            outEiInfo.setMsg("员工工号或者id不能同时为空!");
        } else {
            inEiInfo.set(EiConstant.serviceId, "S_AU_FW_05");
            outEiInfo = AppLoginUtils.invoke(inEiInfo);
        }

        return outEiInfo;
    }
}
