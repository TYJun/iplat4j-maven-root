package com.baosight.wilp.si.kc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.si.common.SiUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 库存日结算实体
 * SiDayStorage
 * @author fangjian
 */
public class SiDayStorage extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**统计日期*/
    private Date countDay;

    /**仓库号*/
    private String wareHouseNo ;

    /**仓库名称*/
    private String wareHouseName ;

    /**库位号*/
    private String storageNo ;

    /**货位号*/
    private String stackNo ;

    /**物资分类编码*/
    private String matTypeNum ;

    /**物资分类名称*/
    private String matTypeName ;

    /**物资编码*/
    private String matNum ;

    /**物资名称*/
    private String matName ;

    /**物资型号*/
    private String matModel ;

    /**物资规格*/
    private String matSpec ;

    /**计量单位编码*/
    private String unit ;

    /**计量单位名称*/
    private String unitName ;

    /**单价*/
    private Double price = new Double(0);

    /**日初库存总量*/
    private Double firstNum = new Double(0);

    /**日初库存总价*/
    private BigDecimal firstAmount = new BigDecimal("0");

    /**日末库存总量*/
    private Double lastNum = new Double(0);

    /**日末库存总价*/
    private BigDecimal lastAmount = new BigDecimal("0");

    /**入库数量*/
    private Double enterNum = new Double(0);

    /**入库总金额*/
    private BigDecimal enterAmount = new BigDecimal("0");

    /**调拨入库数量*/
    private Double transferEnterNum = new Double(0);

    /**调拨入库总金额*/
    private BigDecimal transferEnterAmount = new BigDecimal("0");

    /**出库数量*/
    private Double outNum = new Double(0);

    /**出库总金额*/
    private BigDecimal outAmount = new BigDecimal("0");

    /**调拨出库数量*/
    private Double transferOutNum = new Double(0);

    /**调拨出库总金额*/
    private BigDecimal transferOutAmount = new BigDecimal("0");

    /**创建人*/
    private String recCreator ;

    /**创建时间*/
    private Date recCreateTime ;

    /**修改人*/
    private String recRevisor ;

    /**修改时间*/
    private Date recReviseTime ;

    /**账套*/
    private String dataGroupCode ;

    /**归档标记*/
    private String archiveFlag ;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("countDay");
        eiColumn.setDescName("统计日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseNo");
        eiColumn.setDescName("仓库号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseName");
        eiColumn.setDescName("仓库名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("storageNo");
        eiColumn.setDescName("库位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stackNo");
        eiColumn.setDescName("货位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeNum");
        eiColumn.setDescName("物资分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeName");
        eiColumn.setDescName("物资分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matNum");
        eiColumn.setDescName("物资编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matName");
        eiColumn.setDescName("物资名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matModel");
        eiColumn.setDescName("物资型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matSpec");
        eiColumn.setDescName("物资规格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setDescName("计量单位编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitName");
        eiColumn.setDescName("计量单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("price");
        eiColumn.setDescName("单价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("firstNum");
        eiColumn.setDescName("日初库存总量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("firstAmount");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("日初库存总价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastNum");
        eiColumn.setDescName("日末库存总量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastAmount");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("日末库存总价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterNum");
        eiColumn.setDescName("入库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterAmount");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("入库总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("transferEnterNum");
        eiColumn.setDescName("调拨入库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("transferEnterAmount");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("调拨入库总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outNum");
        eiColumn.setDescName("出库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outAmount");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("出库总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("transferOutNum");
        eiColumn.setDescName("调拨出库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("transferOutAmount");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("调拨出库总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public SiDayStorage() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the countDay - 统计日期
     * @return the countDay
     */
    public Date getCountDay() {
        return this.countDay;
    }

    /**
     * set the countDay - 统计日期
     */
    public void setCountDay(Date countDay) {
        this.countDay = countDay;
    }

    /**
     * get the wareHouseNo - 仓库号
     * @return the wareHouseNo
     */
    public String getWareHouseNo() {
        return this.wareHouseNo;
    }

    /**
     * set the wareHouseNo - 仓库号
     */
    public void setWareHouseNo(String wareHouseNo) {
        this.wareHouseNo = wareHouseNo;
    }

    /**
     * get the wareHouseName - 仓库名称
     * @return the wareHouseName
     */
    public String getWareHouseName() {
        return this.wareHouseName;
    }

    /**
     * set the wareHouseName - 仓库名称
     */
    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    /**
     * get the storageNo - 库位号
     * @return the storageNo
     */
    public String getStorageNo() {
        return this.storageNo;
    }

    /**
     * set the storageNo - 库位号
     */
    public void setStorageNo(String storageNo) {
        this.storageNo = storageNo;
    }

    /**
     * get the stackNo - 货位号
     * @return the stackNo
     */
    public String getStackNo() {
        return this.stackNo;
    }

    /**
     * set the stackNo - 货位号
     */
    public void setStackNo(String stackNo) {
        this.stackNo = stackNo;
    }

    /**
     * get the matTypeNum - 物资分类编码
     * @return the matTypeNum
     */
    public String getMatTypeNum() {
        return this.matTypeNum;
    }

    /**
     * set the matTypeNum - 物资分类编码
     */
    public void setMatTypeNum(String matTypeNum) {
        this.matTypeNum = matTypeNum;
    }

    /**
     * get the matTypeName - 物资分类名称
     * @return the matTypeName
     */
    public String getMatTypeName() {
        return this.matTypeName;
    }

    /**
     * set the matTypeName - 物资分类名称
     */
    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    /**
     * get the matNum - 物资编码
     * @return the matNum
     */
    public String getMatNum() {
        return this.matNum;
    }

    /**
     * set the matNum - 物资编码
     */
    public void setMatNum(String matNum) {
        this.matNum = matNum;
    }

    /**
     * get the matName - 物资名称
     * @return the matName
     */
    public String getMatName() {
        return this.matName;
    }

    /**
     * set the matName - 物资名称
     */
    public void setMatName(String matName) {
        this.matName = matName;
    }

    /**
     * get the matModel - 物资型号
     * @return the matModel
     */
    public String getMatModel() {
        return this.matModel;
    }

    /**
     * set the matModel - 物资型号
     */
    public void setMatModel(String matModel) {
        this.matModel = matModel;
    }

    /**
     * get the matSpec - 物资规格
     * @return the matSpec
     */
    public String getMatSpec() {
        return this.matSpec;
    }

    /**
     * set the matSpec - 物资规格
     */
    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
    }

    /**
     * get the unit - 计量单位编码
     * @return the unit
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * set the unit - 计量单位编码
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * get the unitName - 计量单位名称
     * @return the unitName
     */
    public String getUnitName() {
        return this.unitName;
    }

    /**
     * set the unitName - 计量单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * get the price - 单价
     * @return the price
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * set the price - 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * get the firstNum - 日初库存总量
     * @return the firstNum
     */
    public Double getFirstNum() {
        return this.firstNum;
    }

    /**
     * set the firstNum - 日初库存总量
     */
    public void setFirstNum(Double firstNum) {
        this.firstNum = firstNum;
    }

    /**
     * get the firstAmount - 日初库存总价
     * @return the firstAmount
     */
    public BigDecimal getFirstAmount() {
        return this.firstAmount;
    }

    /**
     * set the firstAmount - 日初库存总价
     */
    public void setFirstAmount(BigDecimal firstAmount) {
        this.firstAmount = firstAmount;
    }

    /**
     * get the lastNum - 日末库存总量
     * @return the lastNum
     */
    public Double getLastNum() {
        return this.lastNum;
    }

    /**
     * set the lastNum - 日末库存总量
     */
    public void setLastNum(Double lastNum) {
        this.lastNum = lastNum;
    }

    /**
     * get the lastAmount - 日末库存总价
     * @return the lastAmount
     */
    public BigDecimal getLastAmount() {
        return this.lastAmount;
    }

    /**
     * set the lastAmount - 日末库存总价
     */
    public void setLastAmount(BigDecimal lastAmount) {
        this.lastAmount = lastAmount;
    }

    /**
     * get the enterNum - 入库数量
     * @return the enterNum
     */
    public Double getEnterNum() {
        return this.enterNum;
    }

    /**
     * set the enterNum - 入库数量
     */
    public void setEnterNum(Double enterNum) {
        this.enterNum = enterNum;
    }

    /**
     * get the enterAmount - 入库总金额
     * @return the enterAmount
     */
    public BigDecimal getEnterAmount() {
        return this.enterAmount;
    }

    /**
     * set the enterAmount - 入库总金额
     */
    public void setEnterAmount(BigDecimal enterAmount) {
        this.enterAmount = enterAmount;
    }

    /**
     * get the transferEnterNum - 调拨入库数量
     * @return the transferEnterNum
     */
    public Double getTransferEnterNum() {
        return this.transferEnterNum;
    }

    /**
     * set the transferEnterNum - 调拨入库数量
     */
    public void setTransferEnterNum(Double transferEnterNum) {
        this.transferEnterNum = transferEnterNum;
    }

    /**
     * get the transferEnterAmount - 调拨入库总金额
     * @return the transferEnterAmount
     */
    public BigDecimal getTransferEnterAmount() {
        return this.transferEnterAmount;
    }

    /**
     * set the transferEnterAmount - 调拨入库总金额
     */
    public void setTransferEnterAmount(BigDecimal transferEnterAmount) {
        this.transferEnterAmount = transferEnterAmount;
    }

    /**
     * get the outNum - 出库数量
     * @return the outNum
     */
    public Double getOutNum() {
        return this.outNum;
    }

    /**
     * set the outNum - 出库数量
     */
    public void setOutNum(Double outNum) {
        this.outNum = outNum;
    }

    /**
     * get the outAmount - 出库总金额
     * @return the outAmount
     */
    public BigDecimal getOutAmount() {
        return this.outAmount;
    }

    /**
     * set the outAmount - 出库总金额
     */
    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    /**
     * get the transferOutNum - 调拨出库数量
     * @return the transferOutNum
     */
    public Double getTransferOutNum() {
        return this.transferOutNum;
    }

    /**
     * set the outNum - 调拨出库数量
     */
    public void setTransferOutNum(Double transferOutNum) {
        this.transferOutNum = transferOutNum;
    }

    /**
     * get the transferOutAmount - 调拨出库总金额
     * @return the transferOutAmount
     */
    public BigDecimal getTransferOutAmount() {
        return this.transferOutAmount;
    }

    /**
     * set the transferOutAmount - 调拨出库总金额
     */
    public void setTransferOutAmount(BigDecimal transferOutAmount) {
        this.transferOutAmount = transferOutAmount;
    }

    /**
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public Date getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public Date getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(Date recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(SiUtils.isEmpty(map.get("id"), id));
        setCountDay(SiUtils.toDate(StringUtils.toString(map.get("countDay"))));
        setWareHouseNo(SiUtils.isEmpty(map.get("wareHouseNo"), wareHouseNo));
        setWareHouseName(SiUtils.isEmpty(map.get("wareHouseName"), wareHouseName));
        setStorageNo(SiUtils.isEmpty(map.get("storageNo"), storageNo));
        setStackNo(SiUtils.isEmpty(map.get("stackNo"), stackNo));
        setMatTypeNum(SiUtils.isEmpty(map.get("matTypeNum"), matTypeNum));
        setMatTypeName(SiUtils.isEmpty(map.get("matTypeName"), matTypeName));
        setMatNum(SiUtils.isEmpty(map.get("matNum"), matNum));
        setMatName(SiUtils.isEmpty(map.get("matName"), matName));
        setMatModel(SiUtils.isEmpty(map.get("matModel"), matModel));
        setMatSpec(SiUtils.isEmpty(map.get("matSpec"), matSpec));
        setUnit(SiUtils.isEmpty(map.get("unit"), unit));
        setUnitName(SiUtils.isEmpty(map.get("unitName"), unitName));
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
        setFirstNum(NumberUtils.toDouble(StringUtils.toString(map.get("firstNum")), firstNum));
        setFirstAmount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("firstAmount")), firstAmount));
        setLastNum(NumberUtils.toDouble(StringUtils.toString(map.get("lastNum")), lastNum));
        setLastAmount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("lastAmount")), lastAmount));
        setEnterNum(NumberUtils.toDouble(StringUtils.toString(map.get("enterNum")), enterNum));
        setEnterAmount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("enterAmount")), enterAmount));
        setTransferEnterNum(NumberUtils.toDouble(StringUtils.toString(map.get("transferEnterNum")), transferEnterNum));
        setTransferEnterAmount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("transferEnterAmount")), transferEnterAmount));
        setOutNum(NumberUtils.toDouble(StringUtils.toString(map.get("outNum")), outNum));
        setOutAmount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("outAmount")), outAmount));
        setTransferOutNum(NumberUtils.toDouble(StringUtils.toString(map.get("transferOutNum")), transferOutNum));
        setTransferOutAmount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("transferOutAmount")), transferOutAmount));
        setRecCreator(SiUtils.isEmpty(map.get("recCreator"), recCreator));
        setRecCreateTime(SiUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(SiUtils.isEmpty(map.get("recRevisor"), recRevisor));
        setRecReviseTime(SiUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(SiUtils.isEmpty(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(SiUtils.isEmpty(map.get("archiveFlag"), archiveFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(32);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("countDay",StringUtils.toString(countDay, eiMetadata.getMeta("countDay")));
        map.put("wareHouseNo",StringUtils.toString(wareHouseNo, eiMetadata.getMeta("wareHouseNo")));
        map.put("wareHouseName",StringUtils.toString(wareHouseName, eiMetadata.getMeta("wareHouseName")));
        map.put("storageNo",StringUtils.toString(storageNo, eiMetadata.getMeta("storageNo")));
        map.put("stackNo",StringUtils.toString(stackNo, eiMetadata.getMeta("stackNo")));
        map.put("matTypeNum",StringUtils.toString(matTypeNum, eiMetadata.getMeta("matTypeNum")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matModel",StringUtils.toString(matModel, eiMetadata.getMeta("matModel")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
        map.put("firstNum",StringUtils.toString(firstNum, eiMetadata.getMeta("firstNum")));
        map.put("firstAmount",StringUtils.toString(firstAmount, eiMetadata.getMeta("firstAmount")));
        map.put("lastNum",StringUtils.toString(lastNum, eiMetadata.getMeta("lastNum")));
        map.put("lastAmount",StringUtils.toString(lastAmount, eiMetadata.getMeta("lastAmount")));
        map.put("enterNum",SiUtils.toNumberString(enterNum, "0"));
        map.put("enterAmount",SiUtils.toNumberString(enterAmount, "0"));
        map.put("transferEnterNum",SiUtils.toNumberString(transferEnterNum, "0"));
        map.put("transferEnterAmount",SiUtils.toNumberString(transferEnterAmount, "0"));
        map.put("outNum",SiUtils.toNumberString(outNum, "0"));
        map.put("outAmount",SiUtils.toNumberString(outAmount, "0"));
        map.put("transferOutNum",SiUtils.toNumberString(transferOutNum, "0"));
        map.put("transferOutAmount",SiUtils.toNumberString(transferOutAmount, "0"));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }

}