package com.baosight.wilp.utils;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.soa.XServiceManager;

/**
 * @PackageName com.baosight.wilp.utils
 * @ClassName AppLoginUtils
 * @Description app登录工具类
 * @Author chunchen2
 * @Date 2021/9/15 11:06
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/9/15 11:06
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class AppLoginUtils {

    public static EiInfo invoke(EiInfo inInfo){
        EiInfo outInfo = new EiInfo();
        try {
            outInfo = XServiceManager.call(inInfo);
            if(null != outInfo && outInfo.getStatus() < 0){
                throw new PlatException(outInfo.getMsg());
            }
        } catch(Exception e){
            e.printStackTrace();
            outInfo.setMsg(e.getMessage());
        }

        return outInfo;
    }
}
