package com.baosight.wilp.si.common;

/**
 * 库存模块常量
 * All rights Reserved, Designed By www.bonawise.com
 * @author fangjian
 * @version V5.0.2
 * @Title: SiConstant.java
 * @ClassName: SiConstant
 * @Package com.baosight.wilp.si.common
 * @Description: TODO
 * @date: 2021年12月08日 10:43
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class SiConstant {

    /**
     * 仓库类型 ：低价值仓库
     **/
    public static final String WAREHOUSE_TYPE_LOW = "01";
    /**
     * 仓库类型 ：高价值仓库
     **/
    public static final String WAREHOUSE_TYPE_UP = "02";

    /**
     * 计价方式 ：先进先出批次计价
     **/
    public static final String PRICE_TYPE_FIFO = "01";
    /**
     * 计价方式 ：库存实时均价
     **/
    public static final String PRICE_TYPE_AVG = "02";

    /**
     * 入库来源类型 ：采购合同
     **/
    public static final String ENTER_RESOURCE_TYPE_CGHT = "01";
    /**
     * 入库来源类型 ：采购到货单
     **/
    public static final String ENTER_RESOURCE_TYPE_CGDHD = "02";
    /**
     * 入库来源类型 ：盘库（盘盈）
     **/
    public static final String ENTER_RESOURCE_TYPE_PY = "03";
    /**
     * 入库来源类型 ：调拨
     **/
    public static final String ENTER_RESOURCE_TYPE_DB = "04";
    /**
     * 入库来源类型 ：红冲
     **/
    public static final String ENTER_RESOURCE_TYPE_HC = "05";

    /**
     * 入库类型 ：直入直出
     **/
    public static final String ENTER_TYPE_ZRZC = "01";
    /**
     * 入库类型 ：调拨入库
     **/
    public static final String ENTER_TYPE_DB = "02";
    /**
     * 入库类型 ：盘盈入库
     **/
    public static final String ENTER_TYPE_PY = "03";
    /**
     * 入库类型 ：发票调差入库
     **/
    public static final String ENTER_TYPE_FPDC = "04";
    /**
     * 入库类型 ：红冲入库
     **/
    public static final String ENTER_TYPE_HC = "05";
    /**
     * 入库类型 ：采购入库
     **/
    public static final String ENTER_TYPE_CG = "06";
    /**
     * 入库类型 ：初始化入库
     **/
    public static final String ENTER_TYPE_CSH = "07";
    /**
     * 入库类型 ：手工入库
     **/
    public static final String ENTER_TYPE_SG = "08";

    /**
     * 出库来源类型 ：盘库
     **/
    public static final String OUT_RESOURCE_TYPE_PK = "01";
    /**
     * 出库来源类型 ：调拨
     **/
    public static final String OUT_RESOURCE_TYPE_DB = "02";
    /**
     * 出库来源类型 ：领用
     **/
    public static final String OUT_RESOURCE_TYPE_LY = "03";
    /**
     * 出库来源类型 ：维修
     **/
    public static final String OUT_RESOURCE_TYPE_WX = "04";
    /**
     * 出库来源类型 ：红冲
     **/
    public static final String OUT_RESOURCE_TYPE_HC = "05";
    /**
     * 出库来源类型 ：报废
     **/
    public static final String OUT_RESOURCE_TYPE_BF = "06";

    /**
     * 出库类型 ：直入直出
     **/
    public static final String OUT_TYPE_ZRZC = "01";
    /**
     * 出库类型 ：调拨出库
     **/
    public static final String OUT_TYPE_DB = "02";
    /**
     * 出库类型 ：盘亏出库
     **/
    public static final String OUT_TYPE_PK = "03";
    /**
     * 出库类型 ：发票调差出库
     **/
    public static final String OUT_TYPE_FPDC = "04";
    /**
     * 出库类型 ：红冲出库
     **/
    public static final String OUT_TYPE_HC = "05";
    /**
     * 出库类型 ：领用
     **/
    public static final String OUT_TYPE_LY = "06";
    /**
     * 出库类型 ：维修出库
     **/
    public static final String OUT_TYPE_WX = "07";
    /**
     * 出库类型 ：委外出库
     **/
    public static final String OUT_TYPE_WY = "08";
    /**
     * 出库类型 ：报废出库
     **/
    public static final String OUT_TYPE_BF = "09";

    /**
     * 出库单状态 ：待签收
     **/
    public static final String OUT_BULL_TOBE_SIGNED = "2";

    /**
     * 出库单状态 ：已签收
     **/
    public static final String OUT_BULL_SIGNED = "1";

    /**
     * 四则运算 ：加
     **/
    public static final String MATH_ADD = "add";

    /**
     * 四则运算 ：减
     **/
    public static final String MATH_SUB = "subtract";

    /**
     * 四则运算 ：乘
     **/
    public static final String MATH_MULTI = "multiply";

    /**
     * 四则运算 ：除
     **/
    public static final String MATH_DIVIDE = "divide";

    /**
     * 报废单状态： 新建
     **/
    public static final String SCRAP_STATUS_NEW = "01";

    /**
     * 报废单状态： 已提交
     **/
    public static final String SCRAP_STATUS_SUBMIT = "10";

    /**
     * 报废单状态：已审核
     **/
    public static final String SCRAP_STATUS_CONFIRM = "20";

    /**
     * 报废单状态： 新建
     **/
    public static final String REPLENISH_STATUS_NEW = "01";

    /**
     * 报废单状态： 已提交
     **/
    public static final String REPLENISH_STATUS_SUBMIT = "10";

    /**
     * 报废单状态： 审核驳回
     **/
    public static final String REPLENISH_STATUS_REJECT = "20";

    /**
     * 报废单状态： 审核通过
     **/
    public static final String REPLENISH_STATUS_PASS = "30";


}
