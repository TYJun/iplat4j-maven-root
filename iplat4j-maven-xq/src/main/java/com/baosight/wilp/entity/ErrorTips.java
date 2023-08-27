package com.baosight.wilp.entity;

/**
 * @PackageName com.baosight.wilp.entity
 * @ClassName ErrorTips
 * @Description 错误提示
 * @Author chunchen2
 * @Date 2023/2/22 18:02
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/22 18:02
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ErrorTips {

    // 成功的编码
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_TIPS = "成功！";

    public static final int PARAMS_NOT_NULL_CODE = -1;
    public static final String PARAMS_NOT_NULL_TIPS = "参数不能为空！";

    // fileCode 不能为空
    public static final int FILE_CODE_NOT_NULL_CODE = -95;
    public static final String FILE_CODE_NOT_NULL_TIPS = "文件编号不能为空！";

    // mul 批量 data 不能为空
    public static final int MULTI_SIGN_DATA_NOT_NULL_CODE = -96;
    public static final String MULTI_SIGN_DATA_NOT_NULL_TIPS = "批量签名的数据不能为空！";

    // data 不能为空
    public static final int SIGN_DATA_NOT_NULL_CODE = -97;
    public static final String SIGN_DATA_NOT_NULL_TIPS = "要签名的数据不能为空！";

    // fileName 不能为空
    public static final int FILE_NAME_NOT_NULL_CODE = -98;
    public static final String FILE_NAME_NOT_NULL_TIPS = "文件名不能为空！";

    // 临时授权key不能为空
    public static final int AUTH_KEY_NOT_NULL_CODE = -99;
    public static final String AUTH_KEY_NOT_NULL_TIPS = "临时授权Key不能为空！";

    // 用户工号不能为空
    public static final int USER_ID_NOT_NULL_CODE = -100;
    public static final String USER_ID_NOT_NULL_TIPS = "用户工号不能为空！";

    // 扫码授权 调用接口异常
    public static final int SCAN_OAUTH_EXCEPTION_CODE = -101;
    public static final String SCAN_OAUTH_EXCEPTION_TIPS = "医信签扫码授权接口调用异常！";

    // 扫码授权接口调用 响应参数解析异常
    public static final int SCAN_OAUTH_RESPONSE_EXCEPTION_CODE = -102;
    public static final String SCAN_OAUTH_RESPONSE_EXCEPTION_TIPS = "医信签扫码授权返回值异常！";

    // transactionId 不能为空
    public static final int TRANSACTION_ID_NOT_NULL_CODE = -103;
    public static final String TRANSACTION_ID_NOT_NULL_TIPS = "身份授权事务编号不能为空！";

    // 获取事务授权状态接口调用异常
    public static final int GET_OAUTH_STATUS_EXCEPTION_CODE = -104;
    public static final String GET_OAUTH_STATUS_EXCEPTION_TIPS = "查询事务授权状态接口调用异常！";

    // 获取事务授权接口返回值解析异常
    public static final int GET_OAUTH_STATUS_RESPONSE_EXCEPTION_CODE = -105;
    public static final String GET_OAUTH_STATUS_RESPONSE_EXCEPTION_TIPS = "查询事务授权状态接口返回值异常！";

    // 获取 用户授权状态 调用异常
    public static final int GET_USER_OAUTH_STATUS_EXCEPTION_CODE = -106;
    public static final String GET_USER_OAUTH_STATUS_EXCEPTION_TIPS = "查询用户授权状态接口调用异常！";

    // 获取用户授权状态接口返回值解析异常
    public static final int GET_USER_OAUTH_STATUS_RESPONSE_EXCEPTION_CODE = -107;
    public static final String GET_USER_OAUTH_STATUS_RESPONSE_EXCEPTION_TIPS = "查询用户授权状态接口返回值异常！";

    // 根据事务id取消用户授权 接口调用异常
    public static final int CANCEL_USER_OAUTH_BY_TRANSACTIONID_EXCEPTION_CODE = -108;
    public static final String CANCEL_USER_OAUTH_BY_TRANSACTION_EXCEPTION_TIPS = "取消用户指定授权接口调用异常！";

    // 根据事务id取消用户授权 接口返回值解析异常
    public static final int CANCEL_USER_OAUTH_BY_TRANSACTIONID_RESPONSE_EXCEPTION_CODE = -109;
    public static final String CANCEL_USER_OAUTH_BY_TRANSACTIONID_RESPONSE_EXCEPTION_TIPS = "取消用户指定授权接口解析异常！";

    // 根据用户id取消用户授权 接口调用异常
    public static final int CANCEL_USER_OAUTH_BY_USERID_EXCEPTION_CODE = -110;
    public static final String CANCEL_USER_OAUTH_BY_USERID_EXCEPTION_TIPS = "取消用所有授权接口调用异常！";

    // 根据用户id取消用户所有授权 接口返回值解析异常
    public static final int CANCEL_USER_OAUTH_BY_USERID_RESPONSE_EXCEPTION_CODE = -111;
    public static final String CANCEL_USER_OAUTH_BY_USERID_RESPONSE_EXCEPTION_TIPS = "取消用户指定授权接口解析异常！";

    // 数据电子签名接口调用异常
    public static final int SIGN_DATA_EXCEPTION_CODE = -112;
    public static final String SIGN_DATA_EXCEPTION_TIPS = "数据电子签名接口调用有异常！";

    // 数据电子签名接口解析异常
    public static final int SIGN_DATA_RESPONSE_EXCEPTION_CODE = -113;
    public static final String SIGN_DATA_RESPONSE_EXCEPTION_TIPS = "数据电子签名接口解析异常！";

    // 数据电子签名 结果保存数据库异常
    public static final int SIGN_DATA_RESULT_SAVE_DB_EXCEPTION_CODE = -114;
    public static final String SIGN_DATA_RESULT_SAVE_DB_EXCEPTION_TIPS = "数据电子签名结果保存数据库异常！";

    // 签名图片下载接口 调用异常
    public static final int SIGN_IMAGE_DOWNLOAD_EXCEPTION_CODE = -115;
    public static final String SIGN_IMAGE_DOWNLOAD_EXCEPTION_TIPS = "下载签名图片接口调用异常！";

    // 签名图片下载接口返回值 解析异常
    public static final int SIGN_IMAGE_DOWNLOAD_RESPONSE_EXCEPTION_CODE = -116;
    public static final String SIGN_IMAGE_DOWNLOAD_RESPONSE_EXCEPTION_TIPS = "下载签名接口解析异常！";

    // 查询签名状态接口调用异常
    public static final int GET_SIGN_STATUS_EXCEPTION_CODE = -117;
    public static final String GET_SIGN_STATUS_EXCEPTION_TIPS = "查询签名状态接口调用异常！";

    // 查询签名接口解析异常
    public static final int GET_SIGN_STATUS_RESPONSE_EXCEPTION_CODE = -118;
    public static final String GET_SIGN_STATUS_RESPONSE_EXCEPTION_TIPS = "查询签名状态接口解析异常！";

    // 通过事务id 查询该事务对应的文件列表
    public static final int GET_SIGN_BY_TRANSACTION_ID_EXCEPTION_CODE = -119;
    public static final String GET_SIGN_BY_TRANSACTION_ID_EXCEPTION_TIPS = "通过事务编号查询文件接口调用异常！";

    // 通过事务id 查询该事务对应的文件列表 解析异常
    public static final int GET_SIGN_BY_TRANSACTION_ID_RESPONSE_EXCEPTION_CODE = -120;
    public static final String GET_SIGN_BY_TRANSACTION_ID_RESPONSE_EXCEPTION_TIPS = "通过事务编号查询文件接口解析异常！";

    // 根据文件编号，验证数据签名结果
    public static final int VERIFY_DATA_EXCEPTION_CODE = -121;
    public static final String VERIFY_DATA_EXCEPTION_TIPS = "数据签名验证接口调用异常！";

    // 撤销签名接口
    public static final int CANCEL_SIGN_EXCEPTION_CODE = -122;
    public static final String CANCEL_SIGN_EXCEPTION_TIPS = "撤销签名接口调用异常！";

    // 令牌 AccessToken 为空
    public static final int ACCESS_TOKEN_NOT_NULL_CODE = -200;
    public static final String ACCESS_TOKEN_NOT_NULL_TIPS = "访问令牌AccessToken为空，请检查！";

    // 临时授权密钥过期
    public static final int AUTH_KEY_EXPIRED_CODE = -201;
    public static final String AUTH_KEY_EXPIRED_TIPS = "临时授权密钥已过期！";

    // 用户未授权，或者授权已经全部过期，需要重新授权 unauthorized
    public static final int USER_UNAUTHORIZED_CODE = -202;
    public static final String USER_UNAUTHORIZED_TIPS = "用户未授权或已过期，请重新扫码授权！";


}
