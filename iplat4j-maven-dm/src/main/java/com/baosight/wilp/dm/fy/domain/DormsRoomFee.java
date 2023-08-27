/**
* Generate time : 2022-03-22 15:17:40
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.fy.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsRoomFee
* 
*/
public class DormsRoomFee extends DaoEPBase {

    private static final long serialVersionUID = 1L;

//    private String id = " ";		/* 主键*/
    private String roomId = " ";		/* 房间id*/
    private String manId = " ";		/* 人员id*/
    private Double waterDegree = new Double(0);		/* 本月用水度数*/
    private Double elecDegree = new Double(0);		/* 本月用电度数*/
    private BigDecimal waterPriece = new BigDecimal("0");		/* 本月水费*/
    private BigDecimal elecPriece = new BigDecimal("0");		/* 本月电费*/
    private BigDecimal currentRent = new BigDecimal("0");		/* 本月实际月租*/
    private BigDecimal currentManageFee = new BigDecimal("0");		/* 本月实际管理费*/
    private BigDecimal extraCharges = new BigDecimal("0");		/* 额外费用*/
    private String remark = " ";		/* 备注*/
    private String currentMonth = " ";		/* 本月所属年月份*/
    private String operator = " ";		/* 操作人工号*/
    private String operationName = " ";		/* 操作人人姓名*/
    private String operationTime = " ";		/* 操作时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

//        eiColumn = new EiColumn("id");
//        eiColumn.setPrimaryKey(true);
//        eiColumn.setDescName("主键");
//        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomId");
        eiColumn.setDescName("房间id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("人员id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("waterDegree");
        eiColumn.setDescName("本月用水度数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("elecDegree");
        eiColumn.setDescName("本月用电度数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("waterPriece");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("本月水费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("elecPriece");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("本月电费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentRent");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("本月实际月租");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentManageFee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("本月实际管理费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("extraCharges");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("额外费用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentMonth");
        eiColumn.setDescName("本月所属年月份");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operator");
        eiColumn.setDescName("操作人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationName");
        eiColumn.setDescName("操作人人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationTime");
        eiColumn.setDescName("操作时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsRoomFee() {
        initMetaData();
    }

//    /**
//     * get the id - 主键
//     * @return the id
//     */
//    public String getId() {
//        return this.id;
//    }
//
//    /**
//     * set the id - 主键
//     */
//    public void setId(String id) {
//        this.id = id;
//    }

    /**
     * get the roomId - 房间id
     * @return the roomId
     */
    public String getRoomId() {
        return this.roomId;
    }

    /**
     * set the roomId - 房间id
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * get the manId - 人员id
     * @return the manId
     */
    public String getManId() {
        return this.manId;
    }

    /**
     * set the manId - 人员id
     */
    public void setManId(String manId) {
        this.manId = manId;
    }

    /**
     * get the waterDegree - 本月用水度数
     * @return the waterDegree
     */
    public Double getWaterDegree() {
        return this.waterDegree;
    }

    /**
     * set the waterDegree - 本月用水度数
     */
    public void setWaterDegree(Double waterDegree) {
        this.waterDegree = waterDegree;
    }

    /**
     * get the elecDegree - 本月用电度数
     * @return the elecDegree
     */
    public Double getElecDegree() {
        return this.elecDegree;
    }

    /**
     * set the elecDegree - 本月用电度数
     */
    public void setElecDegree(Double elecDegree) {
        this.elecDegree = elecDegree;
    }

    /**
     * get the waterPriece - 本月水费
     * @return the waterPriece
     */
    public BigDecimal getWaterPriece() {
        return this.waterPriece;
    }

    /**
     * set the waterPriece - 本月水费
     */
    public void setWaterPriece(BigDecimal waterPriece) {
        this.waterPriece = waterPriece;
    }

    /**
     * get the elecPriece - 本月电费
     * @return the elecPriece
     */
    public BigDecimal getElecPriece() {
        return this.elecPriece;
    }

    /**
     * set the elecPriece - 本月电费
     */
    public void setElecPriece(BigDecimal elecPriece) {
        this.elecPriece = elecPriece;
    }

    /**
     * get the currentRent - 本月实际月租
     * @return the currentRent
     */
    public BigDecimal getCurrentRent() {
        return this.currentRent;
    }

    /**
     * set the currentRent - 本月实际月租
     */
    public void setCurrentRent(BigDecimal currentRent) {
        this.currentRent = currentRent;
    }

    /**
     * get the currentManageFee - 本月实际管理费
     * @return the currentManageFee
     */
    public BigDecimal getCurrentManageFee() {
        return this.currentManageFee;
    }

    /**
     * set the currentManageFee - 本月实际管理费
     */
    public void setCurrentManageFee(BigDecimal currentManageFee) {
        this.currentManageFee = currentManageFee;
    }

    /**
     * get the extraCharges - 额外费用
     * @return the extraCharges
     */
    public BigDecimal getExtraCharges() {
        return this.extraCharges;
    }

    /**
     * set the extraCharges - 额外费用
     */
    public void setExtraCharges(BigDecimal extraCharges) {
        this.extraCharges = extraCharges;
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
     * get the currentMonth - 本月所属年月份
     * @return the currentMonth
     */
    public String getCurrentMonth() {
        return this.currentMonth;
    }

    /**
     * set the currentMonth - 本月所属年月份
     */
    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    /**
     * get the operator - 操作人工号
     * @return the operator
     */
    public String getOperator() {
        return this.operator;
    }

    /**
     * set the operator - 操作人工号
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * get the operationName - 操作人人姓名
     * @return the operationName
     */
    public String getOperationName() {
        return this.operationName;
    }

    /**
     * set the operationName - 操作人人姓名
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * get the operationTime - 操作时间
     * @return the operationTime
     */
    public String getOperationTime() {
        return this.operationTime;
    }

    /**
     * set the operationTime - 操作时间
     */
    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

//        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRoomId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomId")), roomId));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setWaterDegree(NumberUtils.toDouble(StringUtils.toString(map.get("waterDegree")), waterDegree));
        setElecDegree(NumberUtils.toDouble(StringUtils.toString(map.get("elecDegree")), elecDegree));
        setWaterPriece(NumberUtils.toBigDecimal(StringUtils.toString(map.get("waterPriece")), waterPriece));
        setElecPriece(NumberUtils.toBigDecimal(StringUtils.toString(map.get("elecPriece")), elecPriece));
        setCurrentRent(NumberUtils.toBigDecimal(StringUtils.toString(map.get("currentRent")), currentRent));
        setCurrentManageFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("currentManageFee")), currentManageFee));
        setExtraCharges(NumberUtils.toBigDecimal(StringUtils.toString(map.get("extraCharges")), extraCharges));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setCurrentMonth(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currentMonth")), currentMonth));
        setOperator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operator")), operator));
        setOperationName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationName")), operationName));
        setOperationTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationTime")), operationTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
//        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("roomId",StringUtils.toString(roomId, eiMetadata.getMeta("roomId")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("waterDegree",StringUtils.toString(waterDegree, eiMetadata.getMeta("waterDegree")));
        map.put("elecDegree",StringUtils.toString(elecDegree, eiMetadata.getMeta("elecDegree")));
        map.put("waterPriece",StringUtils.toString(waterPriece, eiMetadata.getMeta("waterPriece")));
        map.put("elecPriece",StringUtils.toString(elecPriece, eiMetadata.getMeta("elecPriece")));
        map.put("currentRent",StringUtils.toString(currentRent, eiMetadata.getMeta("currentRent")));
        map.put("currentManageFee",StringUtils.toString(currentManageFee, eiMetadata.getMeta("currentManageFee")));
        map.put("extraCharges",StringUtils.toString(extraCharges, eiMetadata.getMeta("extraCharges")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("currentMonth",StringUtils.toString(currentMonth, eiMetadata.getMeta("currentMonth")));
        map.put("operator",StringUtils.toString(operator, eiMetadata.getMeta("operator")));
        map.put("operationName",StringUtils.toString(operationName, eiMetadata.getMeta("operationName")));
        map.put("operationTime",StringUtils.toString(operationTime, eiMetadata.getMeta("operationTime")));
        return map;
    }
}