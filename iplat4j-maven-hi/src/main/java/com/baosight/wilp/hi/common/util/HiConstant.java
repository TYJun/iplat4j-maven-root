package com.baosight.wilp.hi.common.util;

/**
 * 维修常量定义
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  MtConstant.java
 * @ClassName:  MtConstant
 * @Package com.baosight.wilp.mt.re.domain
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月10日 下午4:59:59
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class HiConstant {

    /**
     * 维修配置项: 是否与基础对接（是）
     */
    public static final String MTBASEBOCKING_YES = "Y";
    /**
     * 维修配置项: 是否与基础对接（是）
     */
    public static final String MTBASEBOCKING_NO = "N";
    /**
     *  维修配置项:是否多院区（是）
     */
    public static final String MTMULTICAMPUS_YES = "Y";
    /**
     *  维修配置项:是否多院区（否）
     */
    public static final String MTMULTICAMPUS_NO = "N";
    /**
     *  维修配置项:是否与库存对接（是）
     */
    public static final String MTRELATIONSTOCK_YES = "Y";
    /**
     *  维修配置项:是否与库存对接（否）
     */
    public static final String MTRELATIONSTOCK_NO = "N";
    /**
     *  维修配置项:工单重复提醒校验--条件：维修地点
     */
    public static final String REPEATREMAIND_SPOT = "维修地点";
    /**
     *  维修配置项:工单重复提醒校验--条件：维修事项
     */
    public static final String REPEATREMAIND_ITEM = "维修事项";
    /**
     *  维修配置项:工单重复提醒校验--条件：报修时间
     */
    public static final String REPEATREMAIND_TIME = "报修时间";

    /**
     *  维修配置项:是否开启无人值守模式（是）
     **/
    public static final String UNATTENDED_YES = "Y";

    /**
     *  维修配置项:是否开启自动派工（是）
     **/
    public static final String AUTODISPATCH_YES = "Y";

    /**
     * 调度中心角色（后缀）
     */
    public static final String ROLE_DDZX = "DDZX";
    /**
     * 维修组长角色（后缀）
     */
    public static final String ROLE_WXZZ = "WXZZ";
    /**
     * 维修员角色（后缀）
     */
    public static final String ROLE_WXY = "WXY";
    /**
     * 报修人角色（后缀）
     */
    public static final String ROLE_BXR = "BXR";

    /**
     * app工单查询未完工标记
     */
    public static final String APP_TYPE_UNFINISH = "1";
    /**
     * app工单查询未完工标记
     */
    public static final String APP_TYPE_FINISH = "2";

    /**
     * 工单状态 ---待受理
     */
    public static final String STATUS_UN_ACCPET = "01";
    /**
     * 工单状态 ---待派工
     */
    public static final String STATUS_UN_DISPATCH = "02";
    /**
     * 工单状态 ---待接受任务
     */
    public static final String STATUS_UN_TAKE = "03";
    /**
     * 工单状态 ---待完工
     */
    public static final String STATUS_UN_FINISH = "05";
    /**
     * 工单状态 ---挂单(搁置)
     */
    public static final String STATUS_EXPIRED = "08";
    /**
     * 工单状态 ---待评价
     */
    public static final String STATUS_UN_EVALUATE = "07";
    /**
     * 工单状态 ---退单待派工
     */
    public static final String STATUS_CHARGEBACK = "11";
    /**
     * 工单状态 ---退单待受理
     */
    public static final String STATUS_CHARGEBACK_TEAM = "12";
    /**
     * 工单状态 ---作废
     */
    public static final String STATUS_INVALID = "98";
    /**
     * 工单状态 ---结束
     */
    public static final String STATUS_END = "99";

    /**
     * 根节点
     **/
    public static final String ROOT_NODE = "root";

    /**
     * 流程有效标记 1=是，0=不是
     **/
    public static final String IS_ACTUAL = "1";
    /**
     * 当前流程标记 1=是，0=不是
     **/
    public static final String IS_CURRENT = "1";

    /**
     * 近七天
     **/
    public static final String TYPE_WEEK = "week";
    /**
     * 近一个月
     **/
    public static final String TYPE_MONTH = "month";
    /**
     * 近一年
     **/
    public static final String TYPE_HALF_YEAR = "halfYear";
    /**
     * 近一年
     **/
    public static final String TYPE_YEAR = "year";

}
