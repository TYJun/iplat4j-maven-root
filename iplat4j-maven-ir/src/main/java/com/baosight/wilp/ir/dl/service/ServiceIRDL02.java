package com.baosight.wilp.ir.dl.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.wilp.ir.dl.app.domain.AppLoginToken;
import com.baosight.wilp.ir.dl.app.security.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @PackageName com.baosight.wilp.ir.dl.service
 * @ClassName ServiceIRDL02
 * @Description app登录 token 信息处理类
 * @Author chunchen2
 * @Date 2021/12/6 16:25
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/12/6 16:25
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceIRDL02 extends ServiceEPBase {

    @Autowired
    private TokenStore tokenStore;

    /**
     * @Title createLoginToken
     * @Author chunchen2
     * @Description // 登录成功，创建token, 返回tokenID
     * @Date 16:26 2021/12/6
       * @param inEiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo createLoginToken(EiInfo inEiInfo){

        EiInfo loginEiInfo = (EiInfo) inEiInfo.get("eiInfo");

        AppLoginToken appToken = new AppLoginToken();
        appToken.setInfo(loginEiInfo);
        String tokenId = tokenStore.createToken(appToken);
        loginEiInfo.set("auth", tokenId);

        return inEiInfo;
    }
}
