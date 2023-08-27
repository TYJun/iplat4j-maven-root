/**
* Generate time : 2021-05-27 14:19:15
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTmealOrderBillPatient 病员订单主表实体类
* 
*/
public class PSPCTmealOrderBillPatient extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String recCreator = " ";		/* 记录创建责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String billNo = " ";		/* 订单号*/
    private String openId = " ";		/* 微信ID*/
    private String userName = " ";		/* 用户姓名*/
    private String userTelNumber = " ";		/* 用户电话*/
    private String building = " ";		/* 所属大楼*/
    private String buildingName = " ";		
    private String floor = " ";		/* 楼层*/
    private String floorName = " ";		
    private String deptNum = " ";		/* 病区编码*/
    private String deptName = " ";		/* 病区名称*/
    private String wardCode = " ";		/* 病区编码*/
    private String wardName = " ";		/* 病区名称*/
    private String bedNo = " ";		/* 床位号*/
    private String mealNum = " ";		/* 餐次编码*/
    private String address = " ";		/* 订餐地点*/
    private String reqSendTime = " ";		/* 需求送达时间*/
    private BigDecimal billTotalPrice = new BigDecimal("0");		/* 订单总价*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String canteenName = " ";		/* 食堂名称*/
    private String comboName = " ";		/* 食堂编码*/
    private String billRemark = " ";		/* 订单备注*/
    private String payType = " ";		
    private String statusCode = " ";		/* 状态代码*/
    private String currentDealer = " ";		/* 当前操作人*/
    private String processInstId = " ";		/* 流程实例ID*/
    private String printFlag = " ";		/* 打印标记[已打印()Y/未打印(N)]*/
    private String orderWay = " ";		/* 下单方式(医护下单-01,病患/电话下单-02)*/
    private String classify = " ";		/* 分类*/
    private String needDate = " ";		/* 需要时间，Y/D,明天、今天*/
    private String rejectCode = null;		/* 作废标记。null/1/2，正常/审核作废/确定作废*/
    private String rejectReason = " ";		/* 作废原因*/
    private String payNo = " ";		/* 统一支付ID*/
    private BigDecimal transFee = new BigDecimal(0.00);		/* 配送费(不满足最低价时需要付配送费)*/
    private String cardid = " ";		/* 卡片编码*/
    private String alipayid = " ";		
    private String statusName = " ";
    private String roleNum = " ";		/* 角色类型编码*/

    private String mealName = " ";

    //订单菜品信息
  	private List<?> billDetail;
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("订单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("openId");
        eiColumn.setDescName("微信ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setDescName("用户姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userTelNumber");
        eiColumn.setDescName("用户电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("所属大楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildingName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("楼层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floorName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("病区编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("病区名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wardCode");
        eiColumn.setDescName("病区编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wardName");
        eiColumn.setDescName("病区名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName("床位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNum");
        eiColumn.setDescName("餐次编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("address");
        eiColumn.setDescName("订餐地点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqSendTime");
        eiColumn.setDescName("需求送达时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billTotalPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("订单总价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("comboName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billRemark");
        eiColumn.setDescName("订单备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentDealer");
        eiColumn.setDescName("当前操作人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processInstId");
        eiColumn.setDescName("流程实例ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("printFlag");
        eiColumn.setDescName("打印标记[已打印()Y/未打印(N)]");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderWay");
        eiColumn.setDescName("下单方式(医护下单-01,病患/电话下单-02)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classify");
        eiColumn.setDescName("分类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needDate");
        eiColumn.setDescName("需要时间，Y/D,明天、今天");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectCode");
        eiColumn.setDescName("作废标记。null/1/2，正常/审核作废/确定作废");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectReason");
        eiColumn.setDescName("作废原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payNo");
        eiColumn.setDescName("统一支付ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("transFee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(5);
        eiColumn.setDescName("配送费(不满足最低价时需要付配送费)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardid");
        eiColumn.setDescName("卡片编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alipayid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roleNum");
        eiColumn.setDescName("角色类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealName");
        eiColumn.setDescName("餐次");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTmealOrderBillPatient() {
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
     * get the recCreator - 记录创建责任者
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 记录创建责任者
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recReviseTime - 记录修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 记录修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the recRevisor - 记录修改责任者
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 记录修改责任者
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recCreateTime - 记录创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 记录创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
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
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the billNo - 订单号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 订单号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the openId - 微信ID
     * @return the openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * set the openId - 微信ID
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * get the userName - 用户姓名
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * set the userName - 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * get the userTelNumber - 用户电话
     * @return the userTelNumber
     */
    public String getUserTelNumber() {
        return this.userTelNumber;
    }

    /**
     * set the userTelNumber - 用户电话
     */
    public void setUserTelNumber(String userTelNumber) {
        this.userTelNumber = userTelNumber;
    }

    /**
     * get the building - 所属大楼
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 所属大楼
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * get the buildingName 
     * @return the buildingName
     */
    public String getBuildingName() {
        return this.buildingName;
    }

    /**
     * set the buildingName 
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * get the floor - 楼层
     * @return the floor
     */
    public String getFloor() {
        return this.floor;
    }

    /**
     * set the floor - 楼层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * get the floorName 
     * @return the floorName
     */
    public String getFloorName() {
        return this.floorName;
    }

    /**
     * set the floorName 
     */
    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    /**
     * get the deptNum - 病区编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 病区编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 病区名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 病区名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the wardCode - 病区编码
     * @return the wardCode
     */
    public String getWardCode() {
        return this.wardCode;
    }

    /**
     * set the wardCode - 病区编码
     */
    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    /**
     * get the wardName - 病区名称
     * @return the wardName
     */
    public String getWardName() {
        return this.wardName;
    }

    /**
     * set the wardName - 病区名称
     */
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    /**
     * get the bedNo - 床位号
     * @return the bedNo
     */
    public String getBedNo() {
        return this.bedNo;
    }

    /**
     * set the bedNo - 床位号
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    /**
     * get the mealNum - 餐次编码
     * @return the mealNum
     */
    public String getMealNum() {
        return this.mealNum;
    }

    /**
     * set the mealNum - 餐次编码
     */
    public void setMealNum(String mealNum) {
        this.mealNum = mealNum;
    }

    /**
     * get the address - 订餐地点
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * set the address - 订餐地点
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get the reqSendTime - 需求送达时间
     * @return the reqSendTime
     */
    public String getReqSendTime() {
        return this.reqSendTime;
    }

    /**
     * set the reqSendTime - 需求送达时间
     */
    public void setReqSendTime(String reqSendTime) {
        this.reqSendTime = reqSendTime;
    }

    /**
     * get the billTotalPrice - 订单总价
     * @return the billTotalPrice
     */
    public BigDecimal getBillTotalPrice() {
        return this.billTotalPrice;
    }

    /**
     * set the billTotalPrice - 订单总价
     */
    public void setBillTotalPrice(BigDecimal billTotalPrice) {
        this.billTotalPrice = billTotalPrice;
    }

    /**
     * get the canteenNum - 食堂编码
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return this.canteenNum;
    }

    /**
     * set the canteenNum - 食堂编码
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
    }

    /**
     * get the billRemark - 订单备注
     * @return the billRemark
     */
    public String getBillRemark() {
        return this.billRemark;
    }

    /**
     * set the billRemark - 订单备注
     */
    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }

    /**
     * get the payType 
     * @return the payType
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * set the payType 
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * get the statusCode - 状态代码
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态代码
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the currentDealer - 当前操作人
     * @return the currentDealer
     */
    public String getCurrentDealer() {
        return this.currentDealer;
    }

    /**
     * set the currentDealer - 当前操作人
     */
    public void setCurrentDealer(String currentDealer) {
        this.currentDealer = currentDealer;
    }

    /**
     * get the processInstId - 流程实例ID
     * @return the processInstId
     */
    public String getProcessInstId() {
        return this.processInstId;
    }

    /**
     * set the processInstId - 流程实例ID
     */
    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    /**
     * get the printFlag - 打印标记[已打印()Y/未打印(N)]
     * @return the printFlag
     */
    public String getPrintFlag() {
        return this.printFlag;
    }

    /**
     * set the printFlag - 打印标记[已打印()Y/未打印(N)]
     */
    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

    /**
     * get the orderWay - 下单方式(医护下单-01,病患/电话下单-02)
     * @return the orderWay
     */
    public String getOrderWay() {
        return this.orderWay;
    }

    /**
     * set the orderWay - 下单方式(医护下单-01,病患/电话下单-02)
     */
    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    /**
     * get the classify - 分类
     * @return the classify
     */
    public String getClassify() {
        return this.classify;
    }

    /**
     * set the classify - 分类
     */
    public void setClassify(String classify) {
        this.classify = classify;
    }

    /**
     * get the needDate - 需要时间，Y/D,明天、今天
     * @return the needDate
     */
    public String getNeedDate() {
        return this.needDate;
    }

    /**
     * set the needDate - 需要时间，Y/D,明天、今天
     */
    public void setNeedDate(String needDate) {
        this.needDate = needDate;
    }

    public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	/**
     * get the rejectCode - 作废标记。null/1/2，正常/审核作废/确定作废
     * @return the rejectCode
     */
    public String getRejectCode() {
        return this.rejectCode;
    }

    /**
     * set the rejectCode - 作废标记。null/1/2，正常/审核作废/确定作废
     */
    public void setRejectCode(String rejectCode) {
        this.rejectCode = rejectCode;
    }

    /**
     * get the rejectReason - 作废原因
     * @return the rejectReason
     */
    public String getRejectReason() {
        return this.rejectReason;
    }

    /**
     * set the rejectReason - 作废原因
     */
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }


    public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

    /**
     * get the roleNum - 角色
     * @return the roleNum
     */
    public String getRoleNum() {
        return this.roleNum;
    }

    /**
     * set the roleNum - 角色
     */
    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum;
    }

	/**
     * get the transFee - 配送费(不满足最低价时需要付配送费)
     * @return the transFee
     */
    public BigDecimal getTransFee() {
        return this.transFee;
    }

    /**
     * set the transFee - 配送费(不满足最低价时需要付配送费)
     */
    public void setTransFee(BigDecimal transFee) {
        this.transFee = transFee;
    }

    /**
     * get the cardid - 卡片编码
     * @return the cardid
     */
    public String getCardid() {
        return this.cardid;
    }

    /**
     * set the cardid - 卡片编码
     */
    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    /**
     * get the alipayid 
     * @return the alipayid
     */
    public String getAlipayid() {
        return this.alipayid;
    }

    /**
     * set the alipayid 
     */
    public void setAlipayid(String alipayid) {
        this.alipayid = alipayid;
    }

    public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

	public List<?> getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(List<?> billDetail) {
		this.billDetail = billDetail;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setOpenId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("openId")), openId));
        setUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userName")), userName));
        setUserTelNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userTelNumber")), userTelNumber));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setBuildingName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingName")), buildingName));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setFloorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floorName")), floorName));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setWardCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wardCode")), wardCode));
        setWardName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wardName")), wardName));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setMealNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNum")), mealNum));
        setAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("address")), address));
        setReqSendTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqSendTime")), reqSendTime));
        setBillTotalPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("billTotalPrice")), billTotalPrice));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setComboName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("comboName")), comboName));
        setBillRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billRemark")), billRemark));
        setPayType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payType")), payType));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setCurrentDealer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currentDealer")), currentDealer));
        setProcessInstId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processInstId")), processInstId));
        setPrintFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("printFlag")), printFlag));
        setOrderWay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("orderWay")), orderWay));
        setClassify(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classify")), classify));
        setNeedDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needDate")), needDate));
        setRejectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectCode")), rejectCode));
        setRejectReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectReason")), rejectReason));
        setPayNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payNo")), payNo));
        setTransFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("transFee")), transFee));
        setCardid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardid")), cardid));
        setAlipayid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alipayid")), alipayid));
        setStatusName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusName")), statusName));
        setRoleNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roleNum")), roleNum));
        setMealName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealName")), mealName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("openId",StringUtils.toString(openId, eiMetadata.getMeta("openId")));
        map.put("userName",StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("userTelNumber",StringUtils.toString(userTelNumber, eiMetadata.getMeta("userTelNumber")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("buildingName",StringUtils.toString(buildingName, eiMetadata.getMeta("buildingName")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("floorName",StringUtils.toString(floorName, eiMetadata.getMeta("floorName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("wardCode",StringUtils.toString(wardCode, eiMetadata.getMeta("wardCode")));
        map.put("wardName",StringUtils.toString(wardName, eiMetadata.getMeta("wardName")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("mealNum",StringUtils.toString(mealNum, eiMetadata.getMeta("mealNum")));
        map.put("address",StringUtils.toString(address, eiMetadata.getMeta("address")));
        map.put("reqSendTime",StringUtils.toString(reqSendTime, eiMetadata.getMeta("reqSendTime")));
        map.put("billTotalPrice",StringUtils.toString(billTotalPrice, eiMetadata.getMeta("billTotalPrice")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("comboName",StringUtils.toString(comboName, eiMetadata.getMeta("comboName")));
        map.put("billRemark",StringUtils.toString(billRemark, eiMetadata.getMeta("billRemark")));
        map.put("payType",StringUtils.toString(payType, eiMetadata.getMeta("payType")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("currentDealer",StringUtils.toString(currentDealer, eiMetadata.getMeta("currentDealer")));
        map.put("processInstId",StringUtils.toString(processInstId, eiMetadata.getMeta("processInstId")));
        map.put("printFlag",StringUtils.toString(printFlag, eiMetadata.getMeta("printFlag")));
        map.put("orderWay",StringUtils.toString(orderWay, eiMetadata.getMeta("orderWay")));
        map.put("classify",StringUtils.toString(classify, eiMetadata.getMeta("classify")));
        map.put("needDate",StringUtils.toString(needDate, eiMetadata.getMeta("needDate")));
        map.put("rejectCode",StringUtils.toString(rejectCode, eiMetadata.getMeta("rejectCode")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        map.put("payNo",StringUtils.toString(payNo, eiMetadata.getMeta("payNo")));
        map.put("transFee",StringUtils.toString(transFee, eiMetadata.getMeta("transFee")));
        map.put("cardid",StringUtils.toString(cardid, eiMetadata.getMeta("cardid")));
        map.put("alipayid",StringUtils.toString(alipayid, eiMetadata.getMeta("alipayid")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("roleNum",StringUtils.toString(roleNum, eiMetadata.getMeta("roleNum")));
        map.put("mealName",StringUtils.toString(mealName, eiMetadata.getMeta("mealName")));
        return map;
    }
    
	public Object deepClone() throws  ClassNotFoundException, java.io.IOException {
        // 将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);
        // 从流里读出来
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (oi.readObject());
    }

	@Override
	public String toString() {
		return "PSPCTmealOrderBillPatient [id=" + id + ", recCreator=" + recCreator + ", recReviseTime=" + recReviseTime
				+ ", recRevisor=" + recRevisor + ", recCreateTime=" + recCreateTime + ", archiveFlag=" + archiveFlag
				+ ", billNo=" + billNo + ", openId=" + openId + ", userName=" + userName + ", userTelNumber="
				+ userTelNumber + ", building=" + building + ", buildingName=" + buildingName + ", floor=" + floor
				+ ", floorName=" + floorName + ", deptNum=" + deptNum + ", deptName=" + deptName + ", wardCode="
				+ wardCode + ", wardName=" + wardName + ", bedNo=" + bedNo + ", mealNum=" + mealNum + ", address="
				+ address + ", reqSendTime=" + reqSendTime + ", billTotalPrice=" + billTotalPrice + ", canteenNum="
				+ canteenNum + ", canteenName=" + canteenName + ", comboName=" + comboName + ", billRemark="
				+ billRemark + ", payType=" + payType + ", statusCode=" + statusCode + ", currentDealer="
				+ currentDealer + ", processInstId=" + processInstId + ", printFlag=" + printFlag + ", orderWay="
				+ orderWay + ", classify=" + classify + ", needDate=" + needDate + ", rejectCode=" + rejectCode
				+ ", rejectReason=" + rejectReason + ", payNo=" + payNo + ", transFee=" + transFee + ", cardid="
				+ cardid + ", alipayid=" + alipayid + ", statusName=" + statusName + ",roleNum=" + roleNum + ", billDetail=" + billDetail + "]";
	}
	
	
}