package com.baosight.wilp.entity;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;

/**
 * @PackageName com.baosight.wilp.util
 * @ClassName URLConstants
 * @Description 医信签的api url常量
 * @Author chunchen2
 * @Date 2023/2/22 16:24
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/22 16:24
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class URLConstants {

    public static final String MEDI_SIGN_DOMAIN = StringUtil.isNotEmpty(PlatApplicationContext.getProperty("medisign_domain")) ?
            PlatApplicationContext.getProperty("medisign_domain") : "https://yxq-mz.linksign.cn/";

    public static final String APP_ID = "4179122420273358";

    public static final String APP_KEY = "PXETQZC4UDMRRP56BPOPPREG7RF1F69W";

    // 医信签 访问公网域名
    //public static final String MEDI_SIGN_DOMAIN = "https://yxq-mz.linksign.cn/";

    // 医信签 访问内网ip
    //public static final String MEDI_SIGN_DOMAIN_INTRANET = "http://199.168.200.79:8091/";

    // 获取token 的url
    public static final String GET_TOKEN = "api/v1.0/getAccessToken";

    // 扫码授权接口
    public static final String SCAN_AUTH = "doctor/api/v1.0/auth/oauth";

    // 导入用户
    public static final String IMPORT_USER = "doctor/api/v1.0/user/import";

    // 查询用户信息
    public static final String GET_USER_INFO = "doctor/api/v1.0/user/info";

    // 删除用户
    public static final String DEL_USER_INFO = "doctor/api/v1.0/user/delete";

    // 冻结用户
    public static final String FREEZE_USER = "doctor/api/v1.0/user/freeze";

    // 解冻用户
    public static final String UN_FREEZE_USER = "doctor/api/v1.0/user/unfreeze";

    // 获取授权状态
    public static final String GET_OAUTH_STATUS = "doctor/api/v1.0/auth/getOauthStatus";

    // 数据电子签名接口
    public static final String SIGN_DATA = "doctor/api/v1.0/sign/signdata";

    // 数据批量电子签名接口
    public static final String MULTI_SIGN_DATA = "doctor/api/v1.0/sign/multiSignData";

    // 查询签署状态接口
    public static final String GET_SIGN_STATUS = "doctor/api/v1.0/sign/getSignStatus";

    // 根据事务id 查询该编号对应的所有的签名的记录
    public static final String GET_SIGN_BY_TRANSACTIONID= "doctor/api/v1.0/sign/getSignByTransactionId";

    // 根据文件编号，撤销已签名的业务 流程状态变为已取消
    public static final String CANCEL_SIGN = "doctor/api/v1.0/sign/cancelSign";

    // 删除签名状态接口
    public static final String DELETE_SIGN = "doctor/api/v1.0/sign/deleteSign";

    // 下载文件接口
    public static final String DOWNLOAD_FILE = "doctor/api/v1.0/file/downFile";

    // 数据签名验证接口
    public static final String VERIFY_DATA = "doctor/api/v1.0/sign/verifyData";

    // 签署图片下载接口
    public static final String DOWNLOAD_SIGN_IMAGE = "doctor/api/v1.0/download/signImage";

    // 查询用户授权接口
    public static final String GET_OAUTH_BY_USERID = "doctor/api/v1.0/auth/getOauthByUserId";

    // 取消用户授权 根据transactionid
    public static final String CANCLE_AUTH_BY_TRANSACTIONID = "doctor/api/v1.0/cancel/auth/oauth";

    // 取消用户所有授权
    public static final String CANCLE_AUTH_BY_USERID = "doctor/api/v1.0/cancelAuthByUserId";
}
