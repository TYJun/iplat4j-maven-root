package com.baosight.wilp.rm.common;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用常量类
 * @ClassName: RmConstant
 * @Package com.baosight.wilp.mp.common
 * @date: 2022年08月25日 15:51
 */
public class RmConstant {

    /**
     * 默认查询Block块
     **/
    public static final String QUERY_BLOCK = "inqu_status";

    /**
     * 默认返回Block块
     **/
    public static final String RESULT_BLOCK = "result";

    /**
     * 操作类型--新增
     **/
    public static final String OPERATE_TYPE_ADD = "add";

    /**
     * 操作类型字段名称
     **/
    public static final String OPERATE_NAME = "type";

    /**
     * 领用配置结果值：是
     **/
    public static final String CONFIG_YES = "Y";

    /**
     * 需求计划类型: 年度
     **/
    public static final String PLAN_TYPE_YEAR = "01";

    /**
     * 需求计划类型: 月度
     **/
    public static final String PLAN_TYPE_MONTH = "02";

    /**
     * 需求计划类型: 临时
     **/
    public static final String PLAN_TYPE_TEMP = "03";

    /**
     * 需求计划状态: 新建
     **/
    public static final String REQUIRE_STATUS_NEW = "01";

    /**
     * 需求计划状态: 待审批
     **/
    public static final String REQUIRE_STATUS_UN_APPROVAL = "10";

    /**
     * 需求计划状态: 审批驳回
     **/
    public static final String REQUIRE_STATUS_REJECT = "20";

    /**
     * 需求计划状态: 审批通过
     **/
    public static final String REQUIRE_STATUS_PASS = "30";

    /**
     * 需求计划状态: 部分汇总
     **/
    public static final String REQUIRE_STATUS_PART = "40";

    /**
     * 需求计划状态: 已汇总
     **/
    public static final String REQUIRE_STATUS_OVER = "50";

    /**
     * 领用单状态: 新建
     **/
    public static final String CLAIM_STATUS_NEW = "01";

    /**
     * 领用单状态: 待科室审批
     **/
    public static final String CLAIM_STATUS_UN_DEPT_APPROVE = "10";

    /**
     * 领用单状态: 科室审批驳回
     **/
    public static final String CLAIM_STATUS_DEPT_REJECT = "20";

    /**
     * 领用单状态: 待仓库审批
     **/
    public static final String CLAIM_STATUS_UN_STOCK_APPROVE = "30";

    /**
     * 领用单状态: 仓库审批驳回
     **/
    public static final String CLAIM_STATUS_STOCK_REJECT = "40";

    /**
     * 领用单状态: 待出库
     **/
    public static final String CLAIM_STATUS_UN_OUT = "50";

    /**
     * 领用单状态: 部分出库
     **/
    public static final String CLAIM_STATUS_PART_OUT = "60";

    /**
     * 领用单状态: 待签收
     **/
    public static final String CLAIM_STATUS_UN_SIGN = "70";

    /**
     * 领用单状态: 已签收
     **/
    public static final String CLAIM_STATUS_SIGNED = "80";

    /**
     * 领用单状态: 已结束
     **/
    public static final String CLAIM_STATUS_OVER = "90";

    /**
     * 退库单状态: 新建
     **/
    public static final String BACK_OUT_STATUS_NEW = "01";

    /**
     * 退库单状态: 待审批
     **/
    public static final String BACK_OUT_STATUS_UN_APPROVAL = "10";

    /**
     * 退库单状态: 审批驳回
     **/
    public static final String BACK_OUT_STATUS_REJECT = "20";

    /**
     * 退库单状态: 待退库
     **/
    public static final String BACK_OUT_STATUS_UN_OUT = "30";

    /**
     * 退库单状态: 部分退库
     **/
    public static final String BACK_OUT_STATUS_PART_OUT = "40";

    /**
     * 退库单状态: 已结束
     **/
    public static final String BACK_OUT_STATUS_OVER = "50";
}