package com.baosight.wilp.cs.re.domain;

/**
 * 保洁常量定义
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  CsConstant.java
 * @ClassName:   CsConstant
 * @Package com.baosight.wilp.cs.re.domain
 * @author fangzekai
 * @date:   2021年12月01日 下午7:18:59
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class CsConstant {
    /**
     * 管理员（后缀）
     */
    public static final String ROLE_ADMIN = "ADMIN";
    /**
     * 调度中心角色（后缀）
     */
    public static final String ROLE_DDZX = "DDZX";
    /**
     * 保洁班组（后缀）
     */
    public static final String ROLE_BJBZRY = "BJBZRY";
    /**
     * 保洁执行人（后缀）
     */
    public static final String ROLE_BJZXR = "BJZXR";
    /**
     * 普通角色（后缀）
     */
    public static final String ROLE_NORMAL = "NORMAL";

    /**
     * app工单查询未完工标记
     */
    public static final String APP_TYPE_UNFINISH = "1";
    /**
     * app工单查询完工标记
     */
    public static final String APP_TYPE_FINISH = "2";
    /**
     * app工单查询我的工单标记
     */
    public static final String APP_TYPE_MYTASK = "3";

    /**
     * 工单状态 ---待确认
     */
    public static final String STATUS_UN_CONFIRM = "01";
    /**
     * 工单状态 ---待完工
     */
    public static final String STATUS_UN_FINISH = "02";
    /**
     * 工单状态 ---待评价
     */
    public static final String STATUS_EVALUATE = "03";
    /**
     * 工单状态 ---撤销
     */
    public static final String STATUS_INVALID = "98";
    /**
     * 工单状态 ---结束
     */
    public static final String STATUS_END = "99";

}
