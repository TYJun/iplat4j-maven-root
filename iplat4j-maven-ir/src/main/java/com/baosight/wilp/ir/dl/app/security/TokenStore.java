package com.baosight.wilp.ir.dl.app.security;

import com.baosight.wilp.ir.dl.app.domain.AppLoginToken;

/**
 * @PackageName com.baosight.wilp.cu.yd.security
 * @ClassName TokenStore
 * @Description TokenStore 接口，最好是用redis来实现，毕竟单线程安全
 * @Author chunchen2
 * @Date 2021/11/30 20:06
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/11/30 20:06
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public interface TokenStore {

    AppLoginToken getToken(String tokenId);

    String createToken(AppLoginToken appToken);

    boolean verifyToken(String tokenId);

    void refreshToken(String tokenId);

    void remove(String tokenId);
}
