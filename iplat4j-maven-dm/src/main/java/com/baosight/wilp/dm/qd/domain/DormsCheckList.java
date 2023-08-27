/**
* Generate time : 2022-03-15 20:09:47
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.qd.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsCheckList
* 
*/
public class DormsCheckList extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String roomManId = " ";		/* 宿舍人员绑定关系表id*/
    private String serialNumber = " ";		/* 序号*/
    private String itemName = " ";		/* 物品/项目名称*/
    private String existence = " ";		/* 是否有该物品*/
    private String isIntact = " ";		/* 是否完好/损坏*/
    private BigDecimal extraCharges = new BigDecimal("0");		/* 其他费用*/
    private String note = " ";		/* 备注信息*/
    private String operator = " ";		/* 操作人工号*/
    private String operationName = " ";		/* 操作人人姓名*/
    private String operationTime = " ";		/* 操作时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomManId");
        eiColumn.setDescName("宿舍人员绑定关系表id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serialNumber");
        eiColumn.setDescName("序号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemName");
        eiColumn.setDescName("物品/项目名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("existence");
        eiColumn.setDescName("是否有该物品");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isIntact");
        eiColumn.setDescName("是否完好/损坏");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("extraCharges");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("其他费用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("note");
        eiColumn.setDescName("备注信息");
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
    public DormsCheckList() {
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
     * get the roomManId - 宿舍人员绑定关系表id
     * @return the roomManId
     */
    public String getRoomManId() {
        return this.roomManId;
    }

    /**
     * set the roomManId - 宿舍人员绑定关系表id
     */
    public void setRoomManId(String roomManId) {
        this.roomManId = roomManId;
    }

    /**
     * get the serialNumber - 序号
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * set the serialNumber - 序号
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * get the itemName - 物品/项目名称
     * @return the itemName
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * set the itemName - 物品/项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * get the existence - 是否有该物品
     * @return the existence
     */
    public String getExistence() {
        return this.existence;
    }

    /**
     * set the existence - 是否有该物品
     */
    public void setExistence(String existence) {
        this.existence = existence;
    }

    /**
     * get the isIntact - 是否完好/损坏
     * @return the isIntact
     */
    public String getIsIntact() {
        return this.isIntact;
    }

    /**
     * set the isIntact - 是否完好/损坏
     */
    public void setIsIntact(String isIntact) {
        this.isIntact = isIntact;
    }

    /**
     * get the extraCharges - 其他费用
     * @return the extraCharges
     */
    public BigDecimal getExtraCharges() {
        return this.extraCharges;
    }

    /**
     * set the extraCharges - 其他费用
     */
    public void setExtraCharges(BigDecimal extraCharges) {
        this.extraCharges = extraCharges;
    }

    /**
     * get the note - 备注信息
     * @return the note
     */
    public String getNote() {
        return this.note;
    }

    /**
     * set the note - 备注信息
     */
    public void setNote(String note) {
        this.note = note;
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

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRoomManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomManId")), roomManId));
        setSerialNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serialNumber")), serialNumber));
        setItemName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemName")), itemName));
        setExistence(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("existence")), existence));
        setIsIntact(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isIntact")), isIntact));
        setExtraCharges(NumberUtils.toBigDecimal(StringUtils.toString(map.get("extraCharges")), extraCharges));
        setNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("note")), note));
        setOperator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operator")), operator));
        setOperationName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationName")), operationName));
        setOperationTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationTime")), operationTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("roomManId",StringUtils.toString(roomManId, eiMetadata.getMeta("roomManId")));
        map.put("serialNumber",StringUtils.toString(serialNumber, eiMetadata.getMeta("serialNumber")));
        map.put("itemName",StringUtils.toString(itemName, eiMetadata.getMeta("itemName")));
        map.put("existence",StringUtils.toString(existence, eiMetadata.getMeta("existence")));
        map.put("isIntact",StringUtils.toString(isIntact, eiMetadata.getMeta("isIntact")));
        map.put("extraCharges",StringUtils.toString(extraCharges, eiMetadata.getMeta("extraCharges")));
        map.put("note",StringUtils.toString(note, eiMetadata.getMeta("note")));
        map.put("operator",StringUtils.toString(operator, eiMetadata.getMeta("operator")));
        map.put("operationName",StringUtils.toString(operationName, eiMetadata.getMeta("operationName")));
        map.put("operationTime",StringUtils.toString(operationTime, eiMetadata.getMeta("operationTime")));
        return map;
    }
}