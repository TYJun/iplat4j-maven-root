package com.baosight.wilp.ci.common;

/**
 * 库存模块常量
 * All rights Reserved, Designed By www.bonawise.com
 * @author fangjian
 * @version V5.0.2
 * @Title: CiConstant.java
 * @ClassName: CiConstant
 * @Package com.baosight.wilp.ci.common
 * @Description: TODO
 * @date: 2021年12月08日 10:43
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class CiConstant {

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
     * 出库来源类型 ：食堂进销存
     **/
    public static final String OUT_RESOURCE_TYPE_ST = "06";
    /**
     * 出库来源类型 ：红冲
     **/
    public static final String OUT_RESOURCE_TYPE_HC = "05";

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
     * 出库类型 ：食堂进销存出库
     **/
    public static final String OUT_TYPE_ST = "06";
    /**
     * 出库类型 ：委外出库
     **/
    public static final String OUT_TYPE_WY = "08";
    /**
     * 出库类型 ：报废出库
     **/
    public static final String OUT_TYPE_BF = "09";

}
