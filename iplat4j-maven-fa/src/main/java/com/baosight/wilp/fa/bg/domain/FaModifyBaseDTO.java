package com.baosight.wilp.fa.bg.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.fa.common.AnjiDescription;
import com.baosight.wilp.fa.common.ValidatorUtils;
import javafx.scene.input.DataFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * 固定资产变更信息
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年12月06日 0:46
 */
public class FaModifyBaseDTO extends DaoEPBase {

	private String id = "";		/* 固定资产信息表主键*/
	private String faInfoId = "";
	private String recCreator = "";		/* 创建人*/
	private String recCreateName = "";		/* 创建人姓名*/
	private String dataGroupCode = "";		/* 账套*/
//	@AnjiDescription("入库单号")
	private String enterBillNo = "";		/* 入库单号*/
	private String contNo = "";		/* 合同号*/
	@AnjiDescription("资金来源")
	private String fundingSourceName;
	private String fundingSourceNum;		/* 资金来源编码*/
	@AnjiDescription("资产用途")
	private String assetUseName;
	private String assetUseCode;		/* 资产用途 资产的使用范围,值域参照【WS.599.4.004资产用途代码】*/
	@AnjiDescription("获取方式")
	private String assetGetWayName;
	private String assetGetWayCode;		/* 获取方式 资产增加的方式，如自购、盘盈,值域参照【WS.599.4.003获取方式代码】*/
//	@AnjiDescription("折旧方式")
	private String deprectCode = "";		/* 折旧方式*/
	private String matNum = "";		/* 物资编码*/
//	@AnjiDescription("物资名称")
	private String matName = "";		/* 物资名称*/
	@AnjiDescription("资产编码")
	private String goodsNum = "";		/* 固定资产编码*/
	@AnjiDescription("资产名称")
	private String goodsName = "";		/* 固定资产名称*/
	private String goodsClassifyCode = "";		/* 类别编码（一级）*/
	@AnjiDescription("资产类别")
	private String goodsClassifyName = "";		/* 类别名称编码（二级）*/
	private String goodsTypeCode = "";		/* 类别名称（一级）*/
	@AnjiDescription("类组")
	private String goodsTypeName = "";		/* 类别名称（二级）*/
	@AnjiDescription("末级类别")
	private String goodsCategoryName = "";
	private String model = "";		/* 型号*/
	@AnjiDescription("规格")
	private String spec = "";		/* 规格*/
	private String unitNum = "";		/* 计量单位*/
	@AnjiDescription("计量单位")
	private String unitName = "";		/* 计量单位*/
	private String deptNum = "";		/* 资产所属科室编码*/
	@AnjiDescription("所属科室")
	private String deptName = "";		/* 资产所属科室名称*/
	@AnjiDescription("制造厂商")
	private String manufacturer = "";		/* 制造厂商*/
	private String manufacturerNatyCode = "";		/* 制造商国别*/
	@AnjiDescription("国别")
	private String manufacturerNatyName = "";		/* 制造商国别*/
	private String surpNum = "";		/* 供应商编码*/
	@AnjiDescription("供应商")
	private String surpName = "";		/* 供应商编码*/
	@AnjiDescription("楼")
	private String build = "";		/* 楼*/
	@AnjiDescription("层")
	private String floor = "";		/* 层*/
	@AnjiDescription("具体位置")
	private String room = "";		/* 层*/
	private String installLocationNum = "";		/* 安装位置编码*/
	@AnjiDescription("地点")
	private String installLocation = "";		/* 安装位置*/
	@AnjiDescription("使用年限")
	private Integer useYears = new Integer(0);		/* 使用年限*/
	private Integer amount = new Integer(0);		/* 数量*/
	private BigDecimal price = new BigDecimal("0");		/* 单价*/
	@AnjiDescription("资产原值")
	private BigDecimal buyCost = new BigDecimal("0");		/* 资产原值*/
	private BigDecimal residualRate = new BigDecimal("0");		/* 残值率*/
	@AnjiDescription("资产净值")
	private BigDecimal netAssetValue = new BigDecimal("0");		/* 资产净值*/
	private BigDecimal estimateCost = new BigDecimal("0");		/* 暂估值*/
	private BigDecimal estimatedResidualValue = new BigDecimal("0");		/* 预计净残值*/
	private String depreciationWay = "";		/* 折旧方式*/
	@AnjiDescription("累计折旧")
	private BigDecimal totalDepreciation = new BigDecimal("0");		/* 资产累计折旧*/
	private BigDecimal monthDepreciation = new BigDecimal("0");		/* 平均月折旧*/
	private String finaceClassNum = "";		/* 财务类别编码*/
	private String invoiceNo = "";		/* 发票号*/
	private String deviceId = "";		/* 设备ID*/
	private String deviceName = "";		/* 设备名称*/
	private String deviceTypeName = "";		/* 设备分类名称*/
	private String remark = "";		/* 备注*/
	private String contractNo = "";		/* 合同号*/
	private String changeReason = "";		/* 变更原因*/
	private String approvalResult = "";		/* 审批结果(10-通过,20-驳回)*/
	private String modifyType = "";		/* 价值变更类型(00-不变,10-升值,20-贬值)*/
	@AnjiDescription("自有资金")
	private BigDecimal equityFund = new BigDecimal("0");
	@AnjiDescription("其他资金")
	private BigDecimal otherFund = new BigDecimal("0");
	@AnjiDescription("采购科室")
	private String purchaseDept = "";

	/**
	 * initialize the metadata
	 */
	public void initMetaData() {
		EiColumn eiColumn;

		eiColumn = new EiColumn("id");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("固定资产信息表主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("faInfoId");
		eiColumn.setDescName("固定资产信息表主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recCreator");
		eiColumn.setDescName("创建人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recCreateName");
		eiColumn.setDescName("创建人姓名");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("dataGroupCode");
		eiColumn.setDescName("账套");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("enterBillNo");
		eiColumn.setDescName("入库单号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("contNo");
		eiColumn.setDescName("合同号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("fundingSourceName");
		eiColumn.setDescName("资金来源");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("fundingSourceNum");
		eiColumn.setDescName("资金来源编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetUseName");
		eiColumn.setDescName("资产用途");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetUseCode");
		eiColumn.setDescName("资产用途 资产的使用范围,值域参照【WS.599.4.004资产用途代码】");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetGetWayName");
		eiColumn.setDescName("获取方式");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetGetWayCode");
		eiColumn.setDescName("获取方式 资产增加的方式，如自购、盘盈,值域参照【WS.599.4.003获取方式代码】");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deprectCode");
		eiColumn.setDescName("折旧方式");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("matNum");
		eiColumn.setDescName("物资编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("matName");
		eiColumn.setDescName("物资名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsNum");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("固定资产编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsName");
		eiColumn.setDescName("固定资产名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsClassifyCode");
		eiColumn.setDescName("类别编码（一级）");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsClassifyName");
		eiColumn.setDescName("类别名称（一级）");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsTypeCode");
		eiColumn.setDescName("类别名称编码（二级）");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsTypeName");
		eiColumn.setDescName("类别名称（二级）");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("goodsCategoryName");
		eiColumn.setDescName("末级类别");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("model");
		eiColumn.setDescName("型号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("spec");
		eiColumn.setDescName("规格");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("unitNum");
		eiColumn.setDescName("计量单位");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("unitName");
		eiColumn.setDescName("计量单位");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deptNum");
		eiColumn.setDescName("资产所属科室编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deptName");
		eiColumn.setDescName("资产所属科室名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("manufacturer");
		eiColumn.setDescName("制造厂商");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("manufacturerNatyCode");
		eiColumn.setDescName("制造商国别");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("manufacturerNatyName");
		eiColumn.setDescName("制造商国别");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("surpNum");
		eiColumn.setDescName("供应商编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("surpName");
		eiColumn.setDescName("供应商");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("build");
		eiColumn.setDescName("楼");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("floor");
		eiColumn.setDescName("层");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("room");
		eiColumn.setDescName("具体位置");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("installLocationNum");
		eiColumn.setDescName("安装位置编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("installLocation");
		eiColumn.setDescName("安装位置");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("useYears");
		eiColumn.setDescName("使用年限");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("amount");
		eiColumn.setDescName("数量");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("price");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(10);
		eiColumn.setDescName("单价");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("buyCost");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName("资产原值");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("residualRate");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName("残值率");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("netAssetValue");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName("资产净值");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("estimateCost");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName("暂估值");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("estimatedResidualValue");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName("预计净残值");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("depreciationWay");
		eiColumn.setDescName("折旧方式");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("totalDepreciation");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName("资产累计折旧");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("monthDepreciation");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName("平均月折旧");
		eiMetadata.addMeta(eiColumn);


		eiColumn = new EiColumn("finaceClassNum");
		eiColumn.setDescName("财务类别编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("invoiceNo");
		eiColumn.setDescName("发票号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deviceId");
		eiColumn.setDescName("设备ID");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deviceName");
		eiColumn.setDescName("设备名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deviceTypeName");
		eiColumn.setDescName("设备分类名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("remark");
		eiColumn.setDescName("备注");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("contractNo");
		eiColumn.setDescName("合同号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("changeReason");
		eiColumn.setDescName("变更原因");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("approvalResult");
		eiColumn.setDescName("审批结果(10-通过,20-驳回)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("modifyType");
		eiColumn.setDescName("价值变更类型(00-不变,10-升值,20-贬值)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("equityFund");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName("自有资金");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("otherFund");
		eiColumn.setType("N");
		eiColumn.setScaleLength(2);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName("其他资金");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("purchaseDept");
		eiColumn.setDescName("采购科室");
		eiMetadata.addMeta(eiColumn);
	}

	/**
	 * the constructor
	 */
	public FaModifyBaseDTO() {
		initMetaData();
	}

	/**
	 * get the id - 固定资产信息表主键
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set the id - 固定资产信息表主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getFaInfoId() {
		return faInfoId;
	}

	public void setFaInfoId(String faInfoId) {
		this.faInfoId = faInfoId;
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
	public void setRecCreator(String recCreator) {
		this.recCreator = recCreator;
	}

	/**
	 * get the recCreateName - 创建人姓名
	 * @return the recCreateName
	 */
	public String getRecCreateName() {
		return this.recCreateName;
	}

	/**
	 * set the recCreateName - 创建人姓名
	 */
	public void setRecCreateName(String recCreateName) {
		this.recCreateName = recCreateName;
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
	 * get the enterBillNo - 入库单号
	 * @return the enterBillNo
	 */
	public String getEnterBillNo() {
		return this.enterBillNo;
	}

	/**
	 * set the enterBillNo - 入库单号
	 */
	public void setEnterBillNo(String enterBillNo) {
		this.enterBillNo = enterBillNo;
	}

	/**
	 * get the contNo - 合同号
	 * @return the contNo
	 */
	public String getContNo() {
		return this.contNo;
	}

	/**
	 * set the contNo - 合同号
	 */
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getFundingSourceNum() {
		return fundingSourceNum;
	}

	public void setFundingSourceNum(String fundingSourceNum) {
		this.fundingSourceNum = fundingSourceNum;
	}

	/**
	 * get the assetUseCode - 资产用途 资产的使用范围,值域参照【WS.599.4.004资产用途代码】
	 * @return the assetUseCode
	 */
	public String getAssetUseCode() {
		return this.assetUseCode;
	}

	/**
	 * set the assetUseCode - 资产用途 资产的使用范围,值域参照【WS.599.4.004资产用途代码】
	 */
	public void setAssetUseCode(String assetUseCode) {
		this.assetUseCode = assetUseCode;
	}

	/**
	 * get the assetGetWayCode - 获取方式 资产增加的方式，如自购、盘盈,值域参照【WS.599.4.003获取方式代码】
	 * @return the assetGetWayCode
	 */
	public String getAssetGetWayCode() {
		return this.assetGetWayCode;
	}

	/**
	 * set the assetGetWayCode - 获取方式 资产增加的方式，如自购、盘盈,值域参照【WS.599.4.003获取方式代码】
	 */
	public void setAssetGetWayCode(String assetGetWayCode) {
		this.assetGetWayCode = assetGetWayCode;
	}

	/**
	 * get the deprectCode - 折旧方式
	 * @return the deprectCode
	 */
	public String getDeprectCode() {
		return this.deprectCode;
	}

	/**
	 * set the deprectCode - 折旧方式
	 */
	public void setDeprectCode(String deprectCode) {
		this.deprectCode = deprectCode;
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
	 * get the goodsNum - 固定资产编码
	 * @return the goodsNum
	 */
	public String getGoodsNum() {
		return this.goodsNum;
	}

	/**
	 * set the goodsNum - 固定资产编码
	 */
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	/**
	 * get the goodsName - 固定资产名称
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return this.goodsName;
	}

	/**
	 * set the goodsName - 固定资产名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * get the goodsClassifyCode - 类别编码（一级）
	 * @return the goodsClassifyCode
	 */
	public String getGoodsClassifyCode() {
		return this.goodsClassifyCode;
	}

	/**
	 * set the goodsClassifyCode - 类别编码（一级）
	 */
	public void setGoodsClassifyCode(String goodsClassifyCode) {
		this.goodsClassifyCode = goodsClassifyCode;
	}

	/**
	 * get the goodsClassifyName - 类别名称（一级）
	 * @return the goodsClassifyName
	 */
	public String getGoodsClassifyName() {
		return this.goodsClassifyName;
	}

	/**
	 * set the goodsClassifyName - 类别名称（一级）
	 */
	public void setGoodsClassifyName(String goodsClassifyName) {
		this.goodsClassifyName = goodsClassifyName;
	}

	/**
	 * get the goodsTypeCode - 类别名称编码（二级）
	 * @return the goodsTypeCode
	 */
	public String getGoodsTypeCode() {
		return this.goodsTypeCode;
	}

	/**
	 * set the goodsTypeCode - 类别名称编码（二级）
	 */
	public void setGoodsTypeCode(String goodsTypeCode) {
		this.goodsTypeCode = goodsTypeCode;
	}

	/**
	 * get the goodsTypeName - 类别名称（二级）
	 * @return the goodsTypeName
	 */
	public String getGoodsTypeName() {
		return this.goodsTypeName;
	}

	/**
	 * set the goodsTypeName - 类别名称（二级）
	 */
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	/**
	 * get the model - 型号
	 * @return the model
	 */
	public String getModel() {
		return this.model;
	}

	/**
	 * set the model - 型号
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * get the spec - 规格
	 * @return the spec
	 */
	public String getSpec() {
		return this.spec;
	}

	/**
	 * set the spec - 规格
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}

	/**
	 * get the unitNum - 计量单位
	 * @return the unitNum
	 */
	public String getUnitNum() {
		return this.unitNum;
	}

	/**
	 * set the unitNum - 计量单位
	 */
	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * get the deptNum - 资产所属科室编码
	 * @return the deptNum
	 */
	public String getDeptNum() {
		return this.deptNum;
	}

	/**
	 * set the deptNum - 资产所属科室编码
	 */
	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}

	/**
	 * get the deptName - 资产所属科室名称
	 * @return the deptName
	 */
	public String getDeptName() {
		return this.deptName;
	}

	/**
	 * set the deptName - 资产所属科室名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * get the manufacturer - 制造厂商
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * set the manufacturer - 制造厂商
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * get the manufacturerNatyCode - 制造商国别
	 * @return the manufacturerNatyCode
	 */
	public String getManufacturerNatyCode() {
		return this.manufacturerNatyCode;
	}

	/**
	 * set the manufacturerNatyCode - 制造商国别
	 */
	public void setManufacturerNatyCode(String manufacturerNatyCode) {
		this.manufacturerNatyCode = manufacturerNatyCode;
	}

	/**
	 * get the surpNum - 供应商编码
	 * @return the surpNum
	 */
	public String getSurpNum() {
		return this.surpNum;
	}

	/**
	 * set the surpNum - 供应商编码
	 */
	public void setSurpNum(String surpNum) {
		this.surpNum = surpNum;
	}

	public String getSurpName() {
		return surpName;
	}

	public void setSurpName(String surpName) {
		this.surpName = surpName;
	}

	/**
	 * get the build - 楼
	 * @return the build
	 */
	public String getBuild() {
		return this.build;
	}

	/**
	 * set the build - 楼
	 */
	public void setBuild(String build) {
		this.build = build;
	}

	/**
	 * get the floor - 层
	 * @return the floor
	 */
	public String getFloor() {
		return this.floor;
	}

	/**
	 * set the floor - 层
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * get the installLocationNum - 安装位置编码
	 * @return the installLocationNum
	 */
	public String getInstallLocationNum() {
		return this.installLocationNum;
	}

	/**
	 * set the installLocationNum - 安装位置编码
	 */
	public void setInstallLocationNum(String installLocationNum) {
		this.installLocationNum = installLocationNum;
	}

	/**
	 * get the installLocation - 安装位置
	 * @return the installLocation
	 */
	public String getInstallLocation() {
		return this.installLocation;
	}

	/**
	 * set the installLocation - 安装位置
	 */
	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}

	/**
	 * get the useYears - 使用年限
	 * @return the useYears
	 */
	public Integer getUseYears() {
		return this.useYears;
	}

	/**
	 * set the useYears - 使用年限
	 */
	public void setUseYears(Integer useYears) {
		this.useYears = useYears;
	}

	/**
	 * get the amount - 数量
	 * @return the amount
	 */
	public Integer getAmount() {
		return this.amount;
	}

	/**
	 * set the amount - 数量
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * get the price - 单价
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * set the price - 单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * get the buyCost - 资产原值
	 * @return the buyCost
	 */
	public BigDecimal getBuyCost() {
		return this.buyCost;
	}

	/**
	 * set the buyCost - 资产原值
	 */
	public void setBuyCost(BigDecimal buyCost) {
		this.buyCost = buyCost;
	}

	/**
	 * get the residualRate - 残值率
	 * @return the residualRate
	 */
	public BigDecimal getResidualRate() {
		return this.residualRate;
	}

	/**
	 * set the residualRate - 残值率
	 */
	public void setResidualRate(BigDecimal residualRate) {
		this.residualRate = residualRate;
	}

	/**
	 * get the netAssetValue - 资产净值
	 * @return the netAssetValue
	 */
	public BigDecimal getNetAssetValue() {
		return this.netAssetValue;
	}

	/**
	 * set the netAssetValue - 资产净值
	 */
	public void setNetAssetValue(BigDecimal netAssetValue) {
		this.netAssetValue = netAssetValue;
	}

	/**
	 * get the estimateCost - 暂估值
	 * @return the estimateCost
	 */
	public BigDecimal getEstimateCost() {
		return this.estimateCost;
	}

	/**
	 * set the estimateCost - 暂估值
	 */
	public void setEstimateCost(BigDecimal estimateCost) {
		this.estimateCost = estimateCost;
	}

	/**
	 * get the estimatedResidualValue - 预计净残值
	 * @return the estimatedResidualValue
	 */
	public BigDecimal getEstimatedResidualValue() {
		return this.estimatedResidualValue;
	}

	/**
	 * set the estimatedResidualValue - 预计净残值
	 */
	public void setEstimatedResidualValue(BigDecimal estimatedResidualValue) {
		this.estimatedResidualValue = estimatedResidualValue;
	}

	/**
	 * get the depreciationWay - 折旧方式
	 * @return the depreciationWay
	 */
	public String getDepreciationWay() {
		return this.depreciationWay;
	}

	/**
	 * set the depreciationWay - 折旧方式
	 */
	public void setDepreciationWay(String depreciationWay) {
		this.depreciationWay = depreciationWay;
	}

	/**
	 * get the totalDepreciation - 资产累计折旧
	 * @return the totalDepreciation
	 */
	public BigDecimal getTotalDepreciation() {
		return this.totalDepreciation;
	}

	/**
	 * set the totalDepreciation - 资产累计折旧
	 */
	public void setTotalDepreciation(BigDecimal totalDepreciation) {
		this.totalDepreciation = totalDepreciation;
	}

	/**
	 * get the monthDepreciation - 平均月折旧
	 * @return the monthDepreciation
	 */
	public BigDecimal getMonthDepreciation() {
		return this.monthDepreciation;
	}

	/**
	 * set the monthDepreciation - 平均月折旧
	 */
	public void setMonthDepreciation(BigDecimal monthDepreciation) {
		this.monthDepreciation = monthDepreciation;
	}

	/**
	 * get the finaceClassNum - 财务类别编码
	 * @return the finaceClassNum
	 */
	public String getFinaceClassNum() {
		return this.finaceClassNum;
	}

	/**
	 * set the finaceClassNum - 财务类别编码
	 */
	public void setFinaceClassNum(String finaceClassNum) {
		this.finaceClassNum = finaceClassNum;
	}

	/**
	 * get the invoiceNo - 发票号
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	/**
	 * set the invoiceNo - 发票号
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	/**
	 * get the deviceId - 设备ID
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return this.deviceId;
	}

	/**
	 * set the deviceId - 设备ID
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * get the deviceName - 设备名称
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return this.deviceName;
	}

	/**
	 * set the deviceName - 设备名称
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * get the deviceTypeName - 设备分类名称
	 * @return the deviceTypeName
	 */
	public String getDeviceTypeName() {
		return this.deviceTypeName;
	}

	/**
	 * set the deviceTypeName - 设备分类名称
	 */
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	/**
	 * get the remark - 备注
	 * @return the remark
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set the remark - 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * get the contractNo - 合同号
	 * @return the contractNo
	 */
	public String getContractNo() {
		return this.contractNo;
	}

	/**
	 * set the contractNo - 合同号
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * get the changeReason - 变更原因
	 * @return the changeReason
	 */
	public String getChangeReason() {
		return this.changeReason;
	}

	/**
	 * set the changeReason - 变更原因
	 */
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	/**
	 * get the approvalResult - 审批结果(10-通过,20-驳回)
	 * @return the approvalResult
	 */
	public String getApprovalResult() {
		return this.approvalResult;
	}

	/**
	 * set the approvalResult - 审批结果(10-通过,20-驳回)
	 */
	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	/**
	 * get the modifyType - 价值变更类型(00-不变,10-升值,20-贬值)
	 * @return the modifyType
	 */
	public String getModifyType() {
		return this.modifyType;
	}

	/**
	 * set the modifyType - 价值变更类型(00-不变,10-升值,20-贬值)
	 */
	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}

	public String getFundingSourceName() {
		return fundingSourceName;
	}

	public void setFundingSourceName(String fundingSourceName) {
		this.fundingSourceName = fundingSourceName;
	}

	public String getAssetUseName() {
		return assetUseName;
	}

	public void setAssetUseName(String assetUseName) {
		this.assetUseName = assetUseName;
	}

	public String getAssetGetWayName() {
		return assetGetWayName;
	}

	public void setAssetGetWayName(String assetGetWayName) {
		this.assetGetWayName = assetGetWayName;
	}

	public String getManufacturerNatyName() {
		return manufacturerNatyName;
	}

	public void setManufacturerNatyName(String manufacturerNatyName) {
		this.manufacturerNatyName = manufacturerNatyName;
	}

	public BigDecimal getEquityFund() {
		return equityFund;
	}

	public void setEquityFund(BigDecimal equityFund) {
		this.equityFund = equityFund;
	}

	public BigDecimal getOtherFund() {
		return otherFund;
	}

	public void setOtherFund(BigDecimal otherFund) {
		this.otherFund = otherFund;
	}

	public String getPurchaseDept() {
		return purchaseDept;
	}

	public void setPurchaseDept(String purchaseDept) {
		this.purchaseDept = purchaseDept;
	}

	/**
	 * get the value from Map
	 */
	public void fromMap(Map map) {

		setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setFaInfoId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("faInfoId")), faInfoId));
		setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
		setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
		setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
		setEnterBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterBillNo")), enterBillNo));
		setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
		setFundingSourceNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fundingSourceNum")), fundingSourceNum));
		setFundingSourceName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fundingSourceName")), fundingSourceName));
		setAssetUseCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assetUseCode")), assetUseCode));
		setAssetUseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assetUseName")), assetUseName));
		setAssetGetWayCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assetGetWayCode")), assetGetWayCode));
		setAssetGetWayName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("assetGetWayName")), assetGetWayName));
		setDeprectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deprectCode")), deprectCode));
		setMatNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matNum")), matNum));
		setMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
		setGoodsNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsNum")), goodsNum));
		setGoodsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsName")), goodsName));
		setGoodsClassifyCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsClassifyCode")), goodsClassifyCode));
		setGoodsClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsClassifyName")), goodsClassifyName));
		setGoodsTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsTypeCode")), goodsTypeCode));
		setGoodsTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsTypeName")), goodsTypeName));
		setGoodsCategoryName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsCategoryName")), goodsCategoryName));
		setModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("model")), model));
		setSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spec")), spec));
		setUnitNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitNum")), unitNum));
		setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
		setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
		setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
		setManufacturer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturer")), manufacturer));
		setManufacturerNatyCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturerNatyCode")), manufacturerNatyCode));
		setManufacturerNatyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturerNatyName")), manufacturerNatyName));
		setSurpNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpNum")), surpNum));
		setSurpName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpName")), surpName));
		setBuild(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("build")), build));
		setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
		setRoom(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("room")), room));
		setInstallLocationNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("installLocationNum")), installLocationNum));
		setInstallLocation(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("installLocation")), installLocation));
		setUseYears(NumberUtils.toInteger(StringUtils.toString(map.get("useYears")), useYears));
		setAmount(NumberUtils.toInteger(StringUtils.toString(map.get("amount")), amount));
		setPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("price")), price));
		setBuyCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("buyCost")), buyCost));
		setResidualRate(NumberUtils.toBigDecimal(StringUtils.toString(map.get("residualRate")), residualRate));
		setNetAssetValue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("netAssetValue")), netAssetValue));
		setEstimateCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("estimateCost")), estimateCost));
		setEstimatedResidualValue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("estimatedResidualValue")), estimatedResidualValue));
		setDepreciationWay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("depreciationWay")), depreciationWay));
		setTotalDepreciation(NumberUtils.toBigDecimal(StringUtils.toString(map.get("totalDepreciation")), totalDepreciation));
		setMonthDepreciation(NumberUtils.toBigDecimal(StringUtils.toString(map.get("monthDepreciation")), monthDepreciation));
		setFinaceClassNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finaceClassNum")), finaceClassNum));
		setInvoiceNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceNo")), invoiceNo));
		setDeviceId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceId")), deviceId));
		setDeviceName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceName")), deviceName));
		setDeviceTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceTypeName")), deviceTypeName));
		setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
		setContractNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contractNo")), contractNo));
		setChangeReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("changeReason")), changeReason));
		setApprovalResult(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("approvalResult")), approvalResult));
		setModifyType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyType")), modifyType));
		setEquityFund(NumberUtils.toBigDecimal(StringUtils.toString(map.get("equityFund")), equityFund));
		setOtherFund(NumberUtils.toBigDecimal(StringUtils.toString(map.get("otherFund")), otherFund));
		setPurchaseDept(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("purchaseDept")), purchaseDept));
	}

	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
		map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("faInfoId",StringUtils.toString(faInfoId, eiMetadata.getMeta("faInfoId")));
		map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
		map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
		map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
		map.put("enterBillNo",StringUtils.toString(enterBillNo, eiMetadata.getMeta("enterBillNo")));
		map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
		map.put("fundingSourceNum",StringUtils.toString(fundingSourceNum, eiMetadata.getMeta("fundingSourceNum")));
		map.put("fundingSourceName",StringUtils.toString(fundingSourceName, eiMetadata.getMeta("fundingSourceName")));
		map.put("assetUseCode",StringUtils.toString(assetUseCode, eiMetadata.getMeta("assetUseCode")));
		map.put("assetUseName",StringUtils.toString(assetUseName, eiMetadata.getMeta("assetUseName")));
		map.put("assetGetWayCode",StringUtils.toString(assetGetWayCode, eiMetadata.getMeta("assetGetWayCode")));
		map.put("assetGetWayName",StringUtils.toString(assetGetWayName, eiMetadata.getMeta("assetGetWayName")));
		map.put("deprectCode",StringUtils.toString(deprectCode, eiMetadata.getMeta("deprectCode")));
		map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
		map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
		map.put("goodsNum",StringUtils.toString(goodsNum, eiMetadata.getMeta("goodsNum")));
		map.put("goodsName",StringUtils.toString(goodsName, eiMetadata.getMeta("goodsName")));
		map.put("goodsClassifyCode",StringUtils.toString(goodsClassifyCode, eiMetadata.getMeta("goodsClassifyCode")));
		map.put("goodsClassifyName",StringUtils.toString(goodsClassifyName, eiMetadata.getMeta("goodsClassifyName")));
		map.put("goodsTypeCode",StringUtils.toString(goodsTypeCode, eiMetadata.getMeta("goodsTypeCode")));
		map.put("goodsTypeName",StringUtils.toString(goodsTypeName, eiMetadata.getMeta("goodsTypeName")));
		map.put("goodsCategoryName",StringUtils.toString(goodsCategoryName, eiMetadata.getMeta("goodsCategoryName")));
		map.put("model",StringUtils.toString(model, eiMetadata.getMeta("model")));
		map.put("spec",StringUtils.toString(spec, eiMetadata.getMeta("spec")));
		map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
		map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
		map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
		map.put("manufacturer",StringUtils.toString(manufacturer, eiMetadata.getMeta("manufacturer")));
		map.put("manufacturerNatyCode",StringUtils.toString(manufacturerNatyCode, eiMetadata.getMeta("manufacturerNatyCode")));
		map.put("manufacturerNatyName",StringUtils.toString(manufacturerNatyName, eiMetadata.getMeta("manufacturerNatyName")));
		map.put("surpNum",StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
		map.put("surpName",StringUtils.toString(surpName, eiMetadata.getMeta("surpName")));
		map.put("build",StringUtils.toString(build, eiMetadata.getMeta("build")));
		map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
		map.put("room",StringUtils.toString(room, eiMetadata.getMeta("room")));
		map.put("installLocationNum",StringUtils.toString(installLocationNum, eiMetadata.getMeta("installLocationNum")));
		map.put("installLocation",StringUtils.toString(installLocation, eiMetadata.getMeta("installLocation")));
		map.put("useYears",StringUtils.toString(useYears, eiMetadata.getMeta("useYears")));
		map.put("amount",StringUtils.toString(amount, eiMetadata.getMeta("amount")));
		map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
		map.put("buyCost",StringUtils.toString(buyCost, eiMetadata.getMeta("buyCost")));
		map.put("residualRate",StringUtils.toString(residualRate, eiMetadata.getMeta("residualRate")));
		map.put("netAssetValue",StringUtils.toString(netAssetValue, eiMetadata.getMeta("netAssetValue")));
		map.put("estimateCost",StringUtils.toString(estimateCost, eiMetadata.getMeta("estimateCost")));
		map.put("estimatedResidualValue",StringUtils.toString(estimatedResidualValue, eiMetadata.getMeta("estimatedResidualValue")));
		map.put("depreciationWay",StringUtils.toString(depreciationWay, eiMetadata.getMeta("depreciationWay")));
		map.put("totalDepreciation",StringUtils.toString(totalDepreciation, eiMetadata.getMeta("totalDepreciation")));
		map.put("monthDepreciation",StringUtils.toString(monthDepreciation, eiMetadata.getMeta("monthDepreciation")));
		map.put("finaceClassNum",StringUtils.toString(finaceClassNum, eiMetadata.getMeta("finaceClassNum")));
		map.put("invoiceNo",StringUtils.toString(invoiceNo, eiMetadata.getMeta("invoiceNo")));
		map.put("deviceId",StringUtils.toString(deviceId, eiMetadata.getMeta("deviceId")));
		map.put("deviceName",StringUtils.toString(deviceName, eiMetadata.getMeta("deviceName")));
		map.put("deviceTypeName",StringUtils.toString(deviceTypeName, eiMetadata.getMeta("deviceTypeName")));
		map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
		map.put("contractNo",StringUtils.toString(contractNo, eiMetadata.getMeta("contractNo")));
		map.put("changeReason",StringUtils.toString(changeReason, eiMetadata.getMeta("changeReason")));
		map.put("approvalResult",StringUtils.toString(approvalResult, eiMetadata.getMeta("approvalResult")));
		map.put("modifyType",StringUtils.toString(modifyType, eiMetadata.getMeta("modifyType")));
		map.put("equityFund",StringUtils.toString(equityFund, eiMetadata.getMeta("equityFund")));
		map.put("otherFund",StringUtils.toString(otherFund, eiMetadata.getMeta("otherFund")));
		map.put("purchaseDept",StringUtils.toString(purchaseDept, eiMetadata.getMeta("purchaseDept")));
		return map;
	}
}
