/**
 * Generate time : 2022-12-19 23:02:05
 * Version : 6.0.731.201809200158
 */
package com.baosight.wilp.fa.da.domain;

import com.baosight.iplat4j.core.util.NumberUtils;

import java.math.BigDecimal;

import com.baosight.iplat4j.core.util.DateUtils;

import java.util.Date;
import java.sql.Timestamp;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.fa.utils.OneSelfUtils;

/**
 * FaInfoDO
 */
public class FaInfoDO extends DaoEPBase {

	private static final long serialVersionUID = 1L;

	private String id = " ";        /* 固定资产信息表主键*/
	private String faInfoId = " ";
	private String recCreator = " ";        /* 创建人*/
	private String recCreateName = " ";        /* 创建人姓名*/
	private Timestamp recCreateTime;        /* 创建时间*/
	private String recRevisor = " ";        /* 修改人*/
	private String recReviseName = " ";        /* 修改人姓名*/
	private Timestamp recReviseTime;        /* 修改时间*/
	private String dataGroupCode = " ";        /* 账套*/
	private String archiveFlag = " ";        /* 归档标记*/
	private String purchaseVouch = " ";
	private String purchaseDept = " ";
	private String purchaseStaffName = " ";
	private String acquitvYear = " ";
	private String enterBillNo = " ";        /* 入库单号*/
	private String outBillNo = " ";        /* 入库单号*/
	private String contNo = " ";        /* 合同号*/
	private String fundingSourceNum = " ";        /* 资金来源编码*/
	private String fundingSourceName = " ";
	private String assetUseCode = " ";        /* 资产用途 资产的使用范围,值域参照【WS.599.4.004资产用途代码】*/
	private String assetUseName = " ";
	private String assetGetWayCode = " ";        /* 获取方式 资产增加的方式，如自购、盘盈,值域参照【WS.599.4.003获取方式代码】*/
	private String assetGetWayName = " ";
	private String deprectCode = " ";        /* 折旧方式*/
	private String deprectName = " ";
	private String matNum = " ";        /* 物资编码*/
	private String matName = " ";        /* 物资名称*/
	private String matTypeNum = " ";
	private String matTypeName = " ";
	private String goodsNum = " ";        /* 固定资产编码*/
	private String goodsName = " ";        /* 固定资产名称*/
	private String goodsClassifyCode = " ";        /* 类别编码（一级）*/
	private String goodsClassifyName = " ";        /* 类别名称（一级）*/
	private String goodsTypeCode = " ";        /* 类别名称编码（二级）*/
	private String goodsTypeName = " ";        /* 类别名称（二级）*/
	private String rfidCode = " ";        /* RFID 芯片ID*/
	private String model = " ";        /* 型号*/
	private String spec = " ";        /* 规格*/
	private String unitNum = " ";        /* 计量单位*/
	private String unitName = " ";        /* 计量单位名称*/
	private String deptNum = " ";        /* 资产所属科室编码*/
	private String deptName = " ";        /* 资产所属科室名称*/
	private String brandDesc = " ";
	private String manufacturer = " ";        /* 制造厂商*/
	private String manufacturerNatyCode = " ";        /* 制造商国别*/
	private String manufacturerNatyName = " ";
	private String surpNum = " ";        /* 供应商编码*/
	private String surpName = " ";        /* 供应商名称*/
	private Date buyDate;    /* 购买日期*/
	private Date useDate;    /* 使用日期*/
	private String build = " ";        /* 楼*/
	private String floor = " ";        /* 层*/
	private String room = " ";        /* 层*/
	private String installLocationNum = " ";        /* 安装位置编码*/
	private String installLocation = " ";        /* 安装位置*/
	private Integer useYears = new Integer(0);        /* 使用年限*/
	private Integer amount = new Integer(0);        /* 数量*/
	private BigDecimal price = new BigDecimal("0");        /* 单价*/
	private BigDecimal buyCost = new BigDecimal("0");        /* 资产原值*/
	private BigDecimal residualRate = new BigDecimal("0");        /* 残值率*/
	private BigDecimal netAssetValue = new BigDecimal("0");        /* 资产净值*/
	private BigDecimal estimateCost = new BigDecimal("0");        /* 暂估值*/
	private BigDecimal estimatedResidualValue = new BigDecimal("0");        /* 预计净残值*/
	private String depreciationWay = " ";        /* 折旧方式*/
	private BigDecimal totalDepreciation = new BigDecimal("0");        /* 资产累计折旧*/
	private BigDecimal monthDepreciation = new BigDecimal("0");        /* 平均月折旧*/
	private Date inAccountDate;    /* 入账日期*/
	private String inAccountStatus = " ";        /* 0/未入账;1/入账;*/
	private String finaceClassNum = " ";        /* 财务类别编码*/
	private String invoiceNo = " ";        /* 发票号*/
	private Date invoiceDate;    /* 发票日期*/
	private String deviceId = " ";        /* 设备ID*/
	private String deviceName = " ";        /* 设备名称*/
	private String deviceTypeName = " ";        /* 设备分类名称*/
	private String remark = " ";        /* 备注*/
	private String lockFlag = " ";        /* 0/未锁定；1/锁定;2/变更;3/拆分;4/被拆分;*/
	private String cardStatus = " ";        /* 发卡状态;0是没发卡,1是发卡*/
	private String statusCode = " ";        /* 状态(00-新增，10-拆分，20-在用，30-闲置，40-报损，50-报废,60-报废中,70-调拨中)*/
	private String splitReason = " ";        /* 拆分原因*/
	private String contractNo = " ";        /* 合同号*/
	private String operationType = " ";        /* 操作类型(10-入库,20-出库)*/
	private String dockCode = " ";        /* 对接唯一标识*/
	private String modifyType = " ";        /* 价值变更类型(00-不变,10-升值,20-贬值)*/
	private BigDecimal equityFund = new BigDecimal("0");
	private BigDecimal otherFund = new BigDecimal("0");
	private String fundProjectCode;
	private String fundProject;
	private String outRemark = " ";
	private String assetType;
	private String warranty;
	private Date scrapDate;

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

		eiColumn = new EiColumn("recCreateTime");
		eiColumn.setDescName("创建时间");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recRevisor");
		eiColumn.setDescName("修改人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("recReviseName");
		eiColumn.setDescName("修改人姓名");
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

		eiColumn = new EiColumn("purchaseVouch");
		eiColumn.setDescName("采购凭证");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("purchaseDept");
		eiColumn.setDescName("采购科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("purchaseStaffName");
		eiColumn.setDescName("采购人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("acquitvYear");
		eiColumn.setDescName("购置年度");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("enterBillNo");
		eiColumn.setDescName("入库单号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("outBillNo");
		eiColumn.setDescName("出库单号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("contNo");
		eiColumn.setDescName("合同号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("fundingSourceNum");
		eiColumn.setDescName("资金来源编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("fundingSourceName");
		eiColumn.setDescName("资金来源");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetUseCode");
		eiColumn.setDescName("资产用途 资产的使用范围,值域参照【WS.599.4.004资产用途代码】");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetUseName");
		eiColumn.setDescName("资产用途");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetGetWayCode");
		eiColumn.setDescName("获取方式 资产增加的方式，如自购、盘盈,值域参照【WS.599.4.003获取方式代码】");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetGetWayName");
		eiColumn.setDescName("获取方式");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deprectCode");
		eiColumn.setDescName("折旧方式");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deprectName");
		eiColumn.setDescName("折旧方式");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("matNum");
		eiColumn.setDescName("物资编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("matName");
		eiColumn.setDescName("物资名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("matTypeNum");
		eiColumn.setDescName("物资分类编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("matTypeName");
		eiColumn.setDescName("物资分类名称");
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

		eiColumn = new EiColumn("rfidCode");
		eiColumn.setDescName("RFID 芯片ID");
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
		eiColumn.setDescName("计量单位名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deptNum");
		eiColumn.setDescName("资产所属科室编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deptName");
		eiColumn.setDescName("资产所属科室名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("brandDesc");
		eiColumn.setDescName("品牌 不同厂家的资产识别标志,");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("manufacturer");
		eiColumn.setDescName("制造厂商");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("manufacturerNatyCode");
		eiColumn.setDescName("国别");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("manufacturerNatyName");
		eiColumn.setDescName("国别");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("surpNum");
		eiColumn.setDescName("供应商编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("surpName");
		eiColumn.setDescName("供应商名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("buyDate");
		eiColumn.setDescName("购买日期");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("useDate");
		eiColumn.setDescName("使用日期");
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

		eiColumn = new EiColumn("inAccountDate");
		eiColumn.setDescName("入账日期");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("inAccountStatus");
		eiColumn.setDescName("0/未入账;1/入账;");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("finaceClassNum");
		eiColumn.setDescName("财务类别编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("invoiceNo");
		eiColumn.setDescName("发票号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("invoiceDate");
		eiColumn.setDescName("发票日期");
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

		eiColumn = new EiColumn("lockFlag");
		eiColumn.setDescName("0/未锁定；1/锁定;2/变更;3/拆分;4/被拆分;");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("cardStatus");
		eiColumn.setDescName("发卡状态;0是没发卡,1是发卡");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("statusCode");
		eiColumn.setDescName("状态(00-新增，10-拆分，20-在用，30-闲置，40-报损，50-报废,60-报废中,70-调拨中)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("splitReason");
		eiColumn.setDescName("拆分原因");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("contractNo");
		eiColumn.setDescName("合同号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("operationType");
		eiColumn.setDescName("操作类型(10-入库,20-出库)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("dockCode");
		eiColumn.setDescName("对接唯一标识");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("modifyType");
		eiColumn.setDescName("价值变更类型(00-不变,10-升值,20-贬值)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("outRemark");
		eiColumn.setDescName("出库备注");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("assetType");
		eiColumn.setDescName("资产类型");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("equityFund");
		eiColumn.setDescName("自有资金");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("otherFund");
		eiColumn.setDescName("其他资金");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("fundProjectCode");
		eiColumn.setDescName("资金项目编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("fundProject");
		eiColumn.setDescName("资金项目");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("warranty");
		eiColumn.setDescName("保质期(月)");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("scrapDate");
		eiColumn.setDescName("报废日期");
		eiMetadata.addMeta(eiColumn);
	}

	/**
	 * the constructor
	 */
	public FaInfoDO() {
		initMetaData();
	}

	/**
	 * get the id - 固定资产信息表主键
	 *
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
	 *
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
	 *
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
	 * get the recCreateTime - 创建时间
	 *
	 * @return the recCreateTime
	 */
	public Date getRecCreateTime() {
		return this.recCreateTime;
	}

	/**
	 * set the recCreateTime - 创建时间
	 */
	public void setRecCreateTime(Timestamp recCreateTime) {
		this.recCreateTime = recCreateTime;
	}

	/**
	 * get the recRevisor - 修改人
	 *
	 * @return the recRevisor
	 */
	public String getRecRevisor() {
		return this.recRevisor;
	}

	/**
	 * set the recRevisor - 修改人
	 */
	public void setRecRevisor(String recRevisor) {
		this.recRevisor = recRevisor;
	}

	/**
	 * get the recReviseName - 修改人姓名
	 *
	 * @return the recReviseName
	 */
	public String getRecReviseName() {
		return this.recReviseName;
	}

	/**
	 * set the recReviseName - 修改人姓名
	 */
	public void setRecReviseName(String recReviseName) {
		this.recReviseName = recReviseName;
	}

	/**
	 * get the recReviseTime - 修改时间
	 *
	 * @return the recReviseTime
	 */
	public Date getRecReviseTime() {
		return this.recReviseTime;
	}

	/**
	 * set the recReviseTime - 修改时间
	 */
	public void setRecReviseTime(Timestamp recReviseTime) {
		this.recReviseTime = recReviseTime;
	}

	/**
	 * get the dataGroupCode - 账套
	 *
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
	 *
	 * @return the archiveFlag
	 */
	public String getArchiveFlag() {
		return this.archiveFlag;
	}

	/**
	 * set the archiveFlag - 归档标记
	 */
	public void setArchiveFlag(String archiveFlag) {
		this.archiveFlag = archiveFlag;
	}

	/**
	 * get the enterBillNo - 入库单号
	 *
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

	public String getOutBillNo() {
		return outBillNo;
	}

	public void setOutBillNo(String outBillNo) {
		this.outBillNo = outBillNo;
	}

	/**
	 * get the contNo - 合同号
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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

	public String getMatTypeNum() {
		return matTypeNum;
	}

	public void setMatTypeNum(String matTypeNum) {
		this.matTypeNum = matTypeNum;
	}

	public String getMatTypeName() {
		return matTypeName;
	}

	public void setMatTypeName(String matTypeName) {
		this.matTypeName = matTypeName;
	}

	/**
	 * get the goodsNum - 固定资产编码
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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

	/**
	 * get the rfidCode - RFID 芯片ID
	 *
	 * @return the rfidCode
	 */
	public String getRfidCode() {
		return this.rfidCode;
	}

	/**
	 * set the rfidCode - RFID 芯片ID
	 */
	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

	/**
	 * get the model - 型号
	 *
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
	 *
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
	 *
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

	/**
	 * get the unitName - 计量单位名称
	 *
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
	 * get the deptNum - 资产所属科室编码
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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

	/**
	 * get the surpName - 供应商名称
	 *
	 * @return the surpName
	 */
	public String getSurpName() {
		return this.surpName;
	}

	/**
	 * set the surpName - 供应商名称
	 */
	public void setSurpName(String surpName) {
		this.surpName = surpName;
	}

	/**
	 * get the buyDate - 购买日期
	 *
	 * @return the buyDate
	 */
	public Date getBuyDate() {
		return this.buyDate;
	}

	/**
	 * set the buyDate - 购买日期
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	/**
	 * get the useDate - 使用日期
	 *
	 * @return the useDate
	 */
	public Date getUseDate() {
		return this.useDate;
	}

	/**
	 * set the useDate - 使用日期
	 */
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	/**
	 * get the build - 楼
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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
	 * get the inAccountDate - 入账日期
	 *
	 * @return the inAccountDate
	 */
	public Date getInAccountDate() {
		return this.inAccountDate;
	}

	/**
	 * set the inAccountDate - 入账日期
	 */
	public void setInAccountDate(Timestamp inAccountDate) {
		this.inAccountDate = inAccountDate;
	}

	/**
	 * get the inAccountStatus - 0/未入账;1/入账;
	 *
	 * @return the inAccountStatus
	 */
	public String getInAccountStatus() {
		return this.inAccountStatus;
	}

	/**
	 * set the inAccountStatus - 0/未入账;1/入账;
	 */
	public void setInAccountStatus(String inAccountStatus) {
		this.inAccountStatus = inAccountStatus;
	}

	/**
	 * get the finaceClassNum - 财务类别编码
	 *
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
	 *
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
	 * get the invoiceDate - 发票日期
	 *
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	/**
	 * set the invoiceDate - 发票日期
	 */
	public void setInvoiceDate(Timestamp invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * get the deviceId - 设备ID
	 *
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
	 *
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
	 *
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
	 *
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
	 * get the lockFlag - 0/未锁定；1/锁定;2/变更;3/拆分;4/被拆分;
	 *
	 * @return the lockFlag
	 */
	public String getLockFlag() {
		return this.lockFlag;
	}

	/**
	 * set the lockFlag - 0/未锁定；1/锁定;2/变更;3/拆分;4/被拆分;
	 */
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	/**
	 * get the cardStatus - 发卡状态;0是没发卡,1是发卡
	 *
	 * @return the cardStatus
	 */
	public String getCardStatus() {
		return this.cardStatus;
	}

	/**
	 * set the cardStatus - 发卡状态;0是没发卡,1是发卡
	 */
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	/**
	 * get the statusCode - 状态(00-新增，10-拆分，20-在用，30-闲置，40-报损，50-报废,60-报废中,70-调拨中)
	 *
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return this.statusCode;
	}

	/**
	 * set the statusCode - 状态(00-新增，10-拆分，20-在用，30-闲置，40-报损，50-报废,60-报废中,70-调拨中)
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * get the splitReason - 拆分原因
	 *
	 * @return the splitReason
	 */
	public String getSplitReason() {
		return this.splitReason;
	}

	/**
	 * set the splitReason - 拆分原因
	 */
	public void setSplitReason(String splitReason) {
		this.splitReason = splitReason;
	}

	/**
	 * get the contractNo - 合同号
	 *
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
	 * get the operationType - 操作类型(10-入库,20-出库)
	 *
	 * @return the operationType
	 */
	public String getOperationType() {
		return this.operationType;
	}

	/**
	 * set the operationType - 操作类型(10-入库,20-出库)
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	/**
	 * get the dockCode - 对接唯一标识
	 *
	 * @return the dockCode
	 */
	public String getDockCode() {
		return this.dockCode;
	}

	/**
	 * set the dockCode - 对接唯一标识
	 */
	public void setDockCode(String dockCode) {
		this.dockCode = dockCode;
	}

	/**
	 * get the modifyType - 价值变更类型(00-不变,10-升值,20-贬值)
	 *
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

	public String getPurchaseVouch() {
		return purchaseVouch;
	}

	public void setPurchaseVouch(String purchaseVouch) {
		this.purchaseVouch = purchaseVouch;
	}

	public String getPurchaseStaffName() {
		return purchaseStaffName;
	}

	public void setPurchaseStaffName(String purchaseStaffName) {
		this.purchaseStaffName = purchaseStaffName;
	}

	public String getAcquitvYear() {
		return acquitvYear;
	}

	public void setAcquitvYear(String acquitvYear) {
		this.acquitvYear = acquitvYear;
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

	public String getDeprectName() {
		return deprectName;
	}

	public void setDeprectName(String deprectName) {
		this.deprectName = deprectName;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getOutRemark() {
		return outRemark;
	}

	public void setOutRemark(String outRemark) {
		this.outRemark = outRemark;
	}

	public String getPurchaseDept() {
		return purchaseDept;
	}

	public void setPurchaseDept(String purchaseDept) {
		this.purchaseDept = purchaseDept;
	}

	public String getManufacturerNatyName() {
		return manufacturerNatyName;
	}

	public void setManufacturerNatyName(String manufacturerNatyName) {
		this.manufacturerNatyName = manufacturerNatyName;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
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

	public String getFundProjectCode() {
		return fundProjectCode;
	}

	public void setFundProjectCode(String fundProjectCode) {
		this.fundProjectCode = fundProjectCode;
	}

	public String getFundProject() {
		return fundProject;
	}

	public void setFundProject(String fundProject) {
		this.fundProject = fundProject;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public Date getScrapDate() {
		return scrapDate;
	}

	public void setScrapDate(Date scrapDate) {
		this.scrapDate = scrapDate;
	}

	/**
	 * get the value from Map
	 */
	public void fromMap(Map map) {

		setId(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setFaInfoId(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("faInfoId")), faInfoId));
		setRecCreator(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
		setRecCreateName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
		setRecCreateTime(OneSelfUtils.defaultTimestamp(map.get("recCreateTime")));
		setRecRevisor(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
		setRecReviseName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseName")), recReviseName));
		setDataGroupCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
		setArchiveFlag(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
		setEnterBillNo(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("enterBillNo")), enterBillNo));
		setOutBillNo(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("outBillNo")), outBillNo));
		setContNo(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
		setFundingSourceNum(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("fundingSourceNum")), fundingSourceNum));
		setAssetUseCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("assetUseCode")), assetUseCode));
		setAssetGetWayCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("assetGetWayCode")), assetGetWayCode));
		setDeprectCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("deprectCode")), deprectCode));
		setMatNum(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("matNum")), matNum));
		setMatName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
		setMatTypeNum(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeNum")), matTypeNum));
		setMatTypeName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeName")), matTypeName));
		setGoodsNum(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsNum")), goodsNum));
		setGoodsName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsName")), goodsName));
		setGoodsClassifyCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsClassifyCode")), goodsClassifyCode));
		setGoodsClassifyName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsClassifyName")), goodsClassifyName));
		setGoodsTypeCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsTypeCode")), goodsTypeCode));
		setGoodsTypeName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsTypeName")), goodsTypeName));
		setRfidCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("rfidCode")), rfidCode));
		setModel(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("model")), model));
		setSpec(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("spec")), spec));
		setUnitNum(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("unitNum")), unitNum));
		setUnitName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
		setDeptNum(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
		setDeptName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
		setManufacturer(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturer")), manufacturer));
		setManufacturerNatyCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturerNatyCode")), manufacturerNatyCode));
		setSurpNum(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("surpNum")), surpNum));
		setSurpName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("surpName")), surpName));
		setBuyDate(OneSelfUtils.defaultDate10(map.get("buyDate"), null));
		setUseDate(OneSelfUtils.defaultDate10(map.get("useDate"), null));
		setBuild(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("build")), build));
		setFloor(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
		setRoom(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("room")), room));
		setInstallLocationNum(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("installLocationNum")), installLocationNum));
		setInstallLocation(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("installLocation")), installLocation));
		setUseYears(NumberUtils.toInteger(StringUtils.toString(map.get("useYears")), useYears));
		setAmount(NumberUtils.toInteger(StringUtils.toString(map.get("amount")), amount));
		setPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("price")), price));
		setBuyCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("buyCost")), buyCost));
		setResidualRate(NumberUtils.toBigDecimal(StringUtils.toString(map.get("residualRate")), residualRate));
		setNetAssetValue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("netAssetValue")), netAssetValue));
		setEstimateCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("estimateCost")), estimateCost));
		setEstimatedResidualValue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("estimatedResidualValue")), estimatedResidualValue));
		setDepreciationWay(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("depreciationWay")), depreciationWay));
		setTotalDepreciation(NumberUtils.toBigDecimal(StringUtils.toString(map.get("totalDepreciation")), totalDepreciation));
		setMonthDepreciation(NumberUtils.toBigDecimal(StringUtils.toString(map.get("monthDepreciation")), monthDepreciation));
//        setInAccountDate(DateUtils.toDate(StringUtils.toString(map.get("inAccountDate"))));
		setInAccountStatus(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("inAccountStatus")), inAccountStatus));
		setFinaceClassNum(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("finaceClassNum")), finaceClassNum));
		setInvoiceNo(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceNo")), invoiceNo));
//        setInvoiceDate(DateUtils.toDate(StringUtils.toString(map.get("invoiceDate"))));
		setDeviceId(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceId")), deviceId));
		setDeviceName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceName")), deviceName));
		setDeviceTypeName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceTypeName")), deviceTypeName));
		setRemark(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
		setLockFlag(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("lockFlag")), lockFlag));
		setCardStatus(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("cardStatus")), cardStatus));
		setStatusCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
		setSplitReason(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("splitReason")), splitReason));
		setContractNo(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("contractNo")), contractNo));
		setOperationType(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("operationType")), operationType));
		setDockCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("dockCode")), dockCode));
		setModifyType(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyType")), modifyType));
		setPurchaseVouch(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("purchaseVouch")), purchaseVouch));
		setPurchaseStaffName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("purchaseStaffName")), purchaseStaffName));
		setAcquitvYear(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("acquitvYear")), acquitvYear));
		setFundingSourceName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("fundingSourceName")), fundingSourceName));
		setAssetUseName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("assetUseName")), assetUseName));
		setAssetGetWayName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("assetGetWayName")), assetGetWayName));
		setDeprectName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("deprectName")), deprectName));
		setBrandDesc(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("brandDesc")), brandDesc));
		setOutRemark(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("outRemark")), outRemark));
		setPurchaseDept(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("purchaseDept")), purchaseDept));
		setManufacturerNatyName(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturerNatyName")), manufacturerNatyName));
		setAssetType(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("assetType")), assetType));
		setEquityFund(NumberUtils.toBigDecimal(StringUtils.toString(map.get("equityFund")), equityFund));
		setOtherFund(NumberUtils.toBigDecimal(StringUtils.toString(map.get("otherFund")), otherFund));
		setFundProject(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("fundProject")), fundProject));
		setFundProjectCode(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("fundProjectCode")), fundProjectCode));
		setWarranty(OneSelfUtils.defaultIfEmpty(StringUtils.toString(map.get("warranty")), warranty));
		setScrapDate(OneSelfUtils.defaultDate(map.get("buyDate"), null));
	}

	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
		map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("faInfoId", StringUtils.toString(faInfoId, eiMetadata.getMeta("faInfoId")));
		map.put("recCreator", StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
		map.put("recCreateName", StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
		map.put("recCreateTime", StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
		map.put("recRevisor", StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
		map.put("recReviseName", StringUtils.toString(recReviseName, eiMetadata.getMeta("recReviseName")));
		map.put("recReviseTime", StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
		map.put("dataGroupCode", StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
		map.put("archiveFlag", StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
		map.put("enterBillNo", StringUtils.toString(enterBillNo, eiMetadata.getMeta("enterBillNo")));
		map.put("outBillNo", StringUtils.toString(outBillNo, eiMetadata.getMeta("outBillNo")));
		map.put("contNo", StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
		map.put("fundingSourceNum", StringUtils.toString(fundingSourceNum, eiMetadata.getMeta("fundingSourceNum")));
		map.put("assetUseCode", StringUtils.toString(assetUseCode, eiMetadata.getMeta("assetUseCode")));
		map.put("assetGetWayCode", StringUtils.toString(assetGetWayCode, eiMetadata.getMeta("assetGetWayCode")));
		map.put("deprectCode", StringUtils.toString(deprectCode, eiMetadata.getMeta("deprectCode")));
		map.put("matNum", StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
		map.put("matName", StringUtils.toString(matName, eiMetadata.getMeta("matName")));
		map.put("matTypeNum", StringUtils.toString(matTypeNum, eiMetadata.getMeta("matTypeNum")));
		map.put("matTypeName", StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
		map.put("goodsNum", StringUtils.toString(goodsNum, eiMetadata.getMeta("goodsNum")));
		map.put("goodsName", StringUtils.toString(goodsName, eiMetadata.getMeta("goodsName")));
		map.put("goodsClassifyCode", StringUtils.toString(goodsClassifyCode, eiMetadata.getMeta("goodsClassifyCode")));
		map.put("goodsClassifyName", StringUtils.toString(goodsClassifyName, eiMetadata.getMeta("goodsClassifyName")));
		map.put("goodsTypeCode", StringUtils.toString(goodsTypeCode, eiMetadata.getMeta("goodsTypeCode")));
		map.put("goodsTypeName", StringUtils.toString(goodsTypeName, eiMetadata.getMeta("goodsTypeName")));
		map.put("rfidCode", StringUtils.toString(rfidCode, eiMetadata.getMeta("rfidCode")));
		map.put("model", StringUtils.toString(model, eiMetadata.getMeta("model")));
		map.put("spec", StringUtils.toString(spec, eiMetadata.getMeta("spec")));
		map.put("unitNum", StringUtils.toString(unitNum, eiMetadata.getMeta("unitNum")));
		map.put("unitName", StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
		map.put("deptNum", StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
		map.put("deptName", StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
		map.put("manufacturer", StringUtils.toString(manufacturer, eiMetadata.getMeta("manufacturer")));
		map.put("manufacturerNatyCode", StringUtils.toString(manufacturerNatyCode, eiMetadata.getMeta("manufacturerNatyCode")));
		map.put("surpNum", StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
		map.put("surpName", StringUtils.toString(surpName, eiMetadata.getMeta("surpName")));
		map.put("buyDate", StringUtils.toString(buyDate, eiMetadata.getMeta("buyDate")));
		map.put("useDate", StringUtils.toString(useDate, eiMetadata.getMeta("useDate")));
		map.put("build", StringUtils.toString(build, eiMetadata.getMeta("build")));
		map.put("floor", StringUtils.toString(floor, eiMetadata.getMeta("floor")));
		map.put("room", StringUtils.toString(room, eiMetadata.getMeta("room")));
		map.put("installLocationNum", StringUtils.toString(installLocationNum, eiMetadata.getMeta("installLocationNum")));
		map.put("installLocation", StringUtils.toString(installLocation, eiMetadata.getMeta("installLocation")));
		map.put("useYears", StringUtils.toString(useYears, eiMetadata.getMeta("useYears")));
		map.put("amount", StringUtils.toString(amount, eiMetadata.getMeta("amount")));
		map.put("price", StringUtils.toString(price, eiMetadata.getMeta("price")));
		map.put("buyCost", StringUtils.toString(buyCost, eiMetadata.getMeta("buyCost")));
		map.put("residualRate", StringUtils.toString(residualRate, eiMetadata.getMeta("residualRate")));
		map.put("netAssetValue", StringUtils.toString(netAssetValue, eiMetadata.getMeta("netAssetValue")));
		map.put("estimateCost", StringUtils.toString(estimateCost, eiMetadata.getMeta("estimateCost")));
		map.put("estimatedResidualValue", StringUtils.toString(estimatedResidualValue, eiMetadata.getMeta("estimatedResidualValue")));
		map.put("depreciationWay", StringUtils.toString(depreciationWay, eiMetadata.getMeta("depreciationWay")));
		map.put("totalDepreciation", StringUtils.toString(totalDepreciation, eiMetadata.getMeta("totalDepreciation")));
		map.put("monthDepreciation", StringUtils.toString(monthDepreciation, eiMetadata.getMeta("monthDepreciation")));
		map.put("inAccountDate", StringUtils.toString(inAccountDate, eiMetadata.getMeta("inAccountDate")));
		map.put("inAccountStatus", StringUtils.toString(inAccountStatus, eiMetadata.getMeta("inAccountStatus")));
		map.put("finaceClassNum", StringUtils.toString(finaceClassNum, eiMetadata.getMeta("finaceClassNum")));
		map.put("invoiceNo", StringUtils.toString(invoiceNo, eiMetadata.getMeta("invoiceNo")));
		map.put("invoiceDate", StringUtils.toString(invoiceDate, eiMetadata.getMeta("invoiceDate")));
		map.put("deviceId", StringUtils.toString(deviceId, eiMetadata.getMeta("deviceId")));
		map.put("deviceName", StringUtils.toString(deviceName, eiMetadata.getMeta("deviceName")));
		map.put("deviceTypeName", StringUtils.toString(deviceTypeName, eiMetadata.getMeta("deviceTypeName")));
		map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
		map.put("lockFlag", StringUtils.toString(lockFlag, eiMetadata.getMeta("lockFlag")));
		map.put("cardStatus", StringUtils.toString(cardStatus, eiMetadata.getMeta("cardStatus")));
		map.put("statusCode", StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
		map.put("splitReason", StringUtils.toString(splitReason, eiMetadata.getMeta("splitReason")));
		map.put("contractNo", StringUtils.toString(contractNo, eiMetadata.getMeta("contractNo")));
		map.put("operationType", StringUtils.toString(operationType, eiMetadata.getMeta("operationType")));
		map.put("dockCode", StringUtils.toString(dockCode, eiMetadata.getMeta("dockCode")));
		map.put("modifyType", StringUtils.toString(modifyType, eiMetadata.getMeta("modifyType")));
		map.put("purchaseVouch", StringUtils.toString(purchaseVouch, eiMetadata.getMeta("purchaseVouch")));
		map.put("purchaseStaffName", StringUtils.toString(purchaseStaffName, eiMetadata.getMeta("purchaseStaffName")));
		map.put("acquitvYear", StringUtils.toString(acquitvYear, eiMetadata.getMeta("acquitvYear")));
		map.put("fundingSourceName", StringUtils.toString(fundingSourceName, eiMetadata.getMeta("fundingSourceName")));
		map.put("assetUseName", StringUtils.toString(assetUseName, eiMetadata.getMeta("assetUseName")));
		map.put("assetGetWayName", StringUtils.toString(assetGetWayName, eiMetadata.getMeta("assetGetWayName")));
		map.put("deprectName", StringUtils.toString(deprectName, eiMetadata.getMeta("deprectName")));
		map.put("brandDesc", StringUtils.toString(brandDesc, eiMetadata.getMeta("brandDesc")));
		map.put("outRemark", StringUtils.toString(outRemark, eiMetadata.getMeta("outRemark")));
		map.put("purchaseDept", StringUtils.toString(purchaseDept, eiMetadata.getMeta("purchaseDept")));
		map.put("manufacturerNatyName", StringUtils.toString(manufacturerNatyName, eiMetadata.getMeta("manufacturerNatyName")));
		map.put("assetType", StringUtils.toString(assetType, eiMetadata.getMeta("assetType")));
		map.put("equityFund", StringUtils.toString(equityFund, eiMetadata.getMeta("equityFund")));
		map.put("otherFund", StringUtils.toString(otherFund, eiMetadata.getMeta("otherFund")));
		map.put("fundProject", StringUtils.toString(fundProject, eiMetadata.getMeta("fundProject")));
		map.put("fundProjectCode", StringUtils.toString(fundProjectCode, eiMetadata.getMeta("fundProjectCode")));
		map.put("warranty", StringUtils.toString(warranty, eiMetadata.getMeta("warranty")));
		map.put("scrapDate", StringUtils.toString(scrapDate, eiMetadata.getMeta("scrapDate")));
		return map;
	}
}