package com.baosight.wilp.security.util;

/**
 * @PackageName com.baosight.wilp.security.util
 * @ClassName Constants
 * @Description 一些常量字符串统一定义
 * @Author chunchen2
 * @Date 2023/2/20 13:43
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/20 13:43
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class SecurityConstants {

    public static final String WEB_XSS_ERROR_TIPS = "您的请求参数里面含有危险字符，请检查再继续访问！";

    public static final String REQUEST_HEADER_REFERER_ERROR_TIPS = "请求Referer: %s来源非法, 请联系系统管理员处理！";

    public static final String REQUEST_HEADER_HOST_ERROR_TIPS = "请求Host: %s来源非法, 请联系系统管理员处理！";

    public static final String USER_NO_AUTH_TIPS = "您没有访问的权限，请联系管理员！";

    public static final String REQUEST_AUTH_ERROR_TIPS = "App未登录或已过期，请重新登录再进行操作！";

    public static final String REQUEST_EXCEED_AUTHORITY_TIPS = "您没有权限操作，请先联系管理员授权！";
}
