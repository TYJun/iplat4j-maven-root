package com.baosight.wilp.mp.common;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购计划常量类
 * @ClassName: MpConstant
 * @Package com.baosight.wilp.mp.common
 * @date: 2022年08月25日 15:51
 */
public class MpConstant {

    /**
     * 采购科室审批角色
     **/
    public static final String DEPT_ROLE = "MPCG002";

    /**分管领导角色**/
    public static final String LEADER_ROLE = "MPCG003";

    /**院长角色**/
    public static final String DEAN_ROLE = "MPCG004";

    /**需求科室审批角色**/
    public static final String REQUIRE_ROLE = "MP002";

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
     * 操作类型--编辑
     **/
    public static final String OPERATE_TYPE_EDIT = "edit";

    /**
     * 操作类型字段名称
     **/
    public static final String OPERATE_NAME = "type";

    /**
     * 采购计划状态: 新建
     **/
    public static final String PROCUREMENT_STATUS_NEW = "01";

    /**
     * 采购计划状态: 待审批
     **/
    public static final String PROCUREMENT_STATUS_UN_APPROVAL = "10";

    /**
     * 采购计划状态: 审批驳回
     **/
    public static final String PROCUREMENT_STATUS_REJECT = "20";

    /**
     * 采购计划状态: 审批通过
     **/
    public static final String PROCUREMENT_STATUS_PASS = "30";

    /**
     * 采购计划状态: 部分生成合同
     **/
    public static final String PROCUREMENT_STATUS_PART_GENERATE = "40";

    /**
     * 采购计划状态: 生成合同
     **/
    public static final String PROCUREMENT_STATUS_ALL_GENERATE = "50";

    /**
     * 采购计划状态: 分管领导审批
     **/
    public static final String PROCUREMENT_STATUS_SECOND = "60";

    /**
     * 采购计划状态: 院长审批
     **/
    public static final String PROCUREMENT_STATUS_THREE = "70";

    /**
     * 采购订单状态: 新建
     **/
    public static final String ORDER_STATUS_NEW = "01";

    /**
     * 采购订单状态: 部分入库
     **/
    public static final String ORDER_STATUS_PART_ENTER = "10";

    /**
     * 采购订单状态: 已入库
     **/
    public static final String ORDER_STATUS_ENTER = "20";

    /**
     * 发票状态: 发票登记
     **/
    public static final String INVOICE_STATUS_NEW = "01";

    /**
     * 发票状态: 提交
     **/
    public static final String INVOICE_STATUS_SUBMIT = "10";

    /**
     * 发票状态: 付款完成
     **/
    public static final String INVOICE_STATUS_PAY = "20";

    /**
     * 付款状态: 申请付款
     **/
    public static final String PAY_STATUS_NEW = "01";

    /**
     * 付款状态: 付款完成
     **/
    public static final String PAY_STATUS_OVER = "10";

    /**
     * 合同状态: 登记
     **/
    public static final String CONTRACT_STATUS_NEW = "01";

    /**
     * 合同状态: 生效
     **/
    public static final String CONTRACT_STATUS_USE = "10";

    /**
     * 合同状态: 终止
     **/
    public static final String CONTRACT_STATUS_STOP = "20";


    /**
     * 需求计划汇总状态: 新建
     **/
    public static final String REQUIRE_COLLECT_STATUS_NEW = "01";

    /**
     * 需求计划汇总状态: 已生成采购计划
     **/
    public static final String REQUIRE_COLLECT_STATUS_GENERATE = "10";

    /**
     * 需求计划汇总: 待审批
     **/
    public static final String REQUIRE_COLLECT_STATUS_UN_APPROVAL = "20";

    /**
     * 需求计划汇总: 审批驳回
     **/
    public static final String REQUIRE_COLLECT_STATUS_REJECT = "30";

    /**
     * 需求计划汇总: 审批通过
     **/
    public static final String REQUIRE_COLLECT_STATUS_PASS = "40";

}
