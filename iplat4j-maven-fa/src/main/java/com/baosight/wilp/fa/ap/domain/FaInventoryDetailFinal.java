/**
* Generate time : 2022-10-10 22:11:50
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.fa.ap.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* FaInventoryDetailFinal
* 固定资产盘点明细终表实体类
*/
public class FaInventoryDetailFinal extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 固定资产盘点明细终表主键*/
    private String subNo = " ";		/* 子项号*/
    private String inventoryId = " ";		/* 资产盘点主表id*/
    private String inventoryNo = " ";		/* 盘点单号*/
    private String goodsId = " ";		/* 固资主键*/
    private String goodsNum = " ";		/* 固资编码*/
    private String goodsName = " ";		/* 固资名称*/
    private Integer beforeInvenNum = new Integer(0);		/* 盘点前数量*/
    private Integer afterInvenNum = new Integer(0);		/* 盘点后数量*/
    private String spotNum = " ";		/* 盘点地点编码*/
    private String spotName = " ";		/* 盘点地点名称*/
    private String inventoryFlag = " ";		/* 盘点结果标记（值为盘盈/盘亏）*/
    private String remark = " ";		/* 盘点结果说明*/
    private String dealFlag = " ";		/* 处理标记（0/未处理，1/已处理）*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("固定资产盘点明细表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("subNo");
        eiColumn.setDescName("子项号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inventoryId");
        eiColumn.setDescName("资产盘点主表id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inventoryNo");
        eiColumn.setDescName("盘点单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsId");
        eiColumn.setDescName("固资主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsNum");
        eiColumn.setDescName("固资编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsName");
        eiColumn.setDescName("固资名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("beforeInvenNum");
        eiColumn.setDescName("盘点前数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("afterInvenNum");
        eiColumn.setDescName("盘点后数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotNum");
        eiColumn.setDescName("盘点地点编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spotName");
        eiColumn.setDescName("盘点地点名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inventoryFlag");
        eiColumn.setDescName("盘点结果标记（值为盘盈/盘亏）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("盘点结果说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealFlag");
        eiColumn.setDescName("处理标记（0/未处理，1/已处理）");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public FaInventoryDetailFinal() {
        initMetaData();
    }

    /**
     * get the id - 固定资产盘点明细表主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 固定资产盘点明细表主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the subNo - 子项号
     * @return the subNo
     */
    public String getSubNo() {
        return this.subNo;
    }

    /**
     * set the subNo - 子项号
     */
    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    /**
     * get the inventoryId - 资产盘点主表id
     * @return the inventoryId
     */
    public String getInventoryId() {
        return this.inventoryId;
    }

    /**
     * set the inventoryId - 资产盘点主表id
     */
    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * get the inventoryNo - 盘点单号
     * @return the inventoryNo
     */
    public String getInventoryNo() {
        return this.inventoryNo;
    }

    /**
     * set the inventoryNo - 盘点单号
     */
    public void setInventoryNo(String inventoryNo) {
        this.inventoryNo = inventoryNo;
    }

    /**
     * get the goodsId - 固资主键
     * @return the goodsId
     */
    public String getGoodsId() {
        return this.goodsId;
    }

    /**
     * set the goodsId - 固资主键
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * get the goodsNum - 固资编码
     * @return the goodsNum
     */
    public String getGoodsNum() {
        return this.goodsNum;
    }

    /**
     * set the goodsNum - 固资编码
     */
    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * get the goodsName - 固资名称
     * @return the goodsName
     */
    public String getGoodsName() {
        return this.goodsName;
    }

    /**
     * set the goodsName - 固资名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * get the beforeInvenNum - 盘点前数量
     * @return the beforeInvenNum
     */
    public Integer getBeforeInvenNum() {
        return this.beforeInvenNum;
    }

    /**
     * set the beforeInvenNum - 盘点前数量
     */
    public void setBeforeInvenNum(Integer beforeInvenNum) {
        this.beforeInvenNum = beforeInvenNum;
    }

    /**
     * get the afterInvenNum - 盘点后数量
     * @return the afterInvenNum
     */
    public Integer getAfterInvenNum() {
        return this.afterInvenNum;
    }

    /**
     * set the afterInvenNum - 盘点后数量
     */
    public void setAfterInvenNum(Integer afterInvenNum) {
        this.afterInvenNum = afterInvenNum;
    }

    /**
     * get the spotNum - 盘点地点编码
     * @return the spotNum
     */
    public String getSpotNum() {
        return this.spotNum;
    }

    /**
     * set the spotNum - 盘点地点编码
     */
    public void setSpotNum(String spotNum) {
        this.spotNum = spotNum;
    }

    /**
     * get the spotName - 盘点地点名称
     * @return the spotName
     */
    public String getSpotName() {
        return this.spotName;
    }

    /**
     * set the spotName - 盘点地点名称
     */
    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    /**
     * get the inventoryFlag - 盘点结果标记（值为盘盈/盘亏）
     * @return the inventoryFlag
     */
    public String getInventoryFlag() {
        return this.inventoryFlag;
    }

    /**
     * set the inventoryFlag - 盘点结果标记（值为盘盈/盘亏）
     */
    public void setInventoryFlag(String inventoryFlag) {
        this.inventoryFlag = inventoryFlag;
    }

    /**
     * get the remark - 盘点结果说明
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 盘点结果说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the dealFlag - 处理标记（0/未处理，1/已处理）
     * @return the dealFlag
     */
    public String getDealFlag() {
        return this.dealFlag;
    }

    /**
     * set the dealFlag - 处理标记（0/未处理，1/已处理）
     */
    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setSubNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("subNo")), subNo));
        setInventoryId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inventoryId")), inventoryId));
        setInventoryNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inventoryNo")), inventoryNo));
        setGoodsId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsId")), goodsId));
        setGoodsNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsNum")), goodsNum));
        setGoodsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsName")), goodsName));
        setBeforeInvenNum(NumberUtils.toInteger(StringUtils.toString(map.get("beforeInvenNum")), beforeInvenNum));
        setAfterInvenNum(NumberUtils.toInteger(StringUtils.toString(map.get("afterInvenNum")), afterInvenNum));
        setSpotNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotNum")), spotNum));
        setSpotName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spotName")), spotName));
        setInventoryFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inventoryFlag")), inventoryFlag));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setDealFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealFlag")), dealFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("subNo",StringUtils.toString(subNo, eiMetadata.getMeta("subNo")));
        map.put("inventoryId",StringUtils.toString(inventoryId, eiMetadata.getMeta("inventoryId")));
        map.put("inventoryNo",StringUtils.toString(inventoryNo, eiMetadata.getMeta("inventoryNo")));
        map.put("goodsId",StringUtils.toString(goodsId, eiMetadata.getMeta("goodsId")));
        map.put("goodsNum",StringUtils.toString(goodsNum, eiMetadata.getMeta("goodsNum")));
        map.put("goodsName",StringUtils.toString(goodsName, eiMetadata.getMeta("goodsName")));
        map.put("beforeInvenNum",StringUtils.toString(beforeInvenNum, eiMetadata.getMeta("beforeInvenNum")));
        map.put("afterInvenNum",StringUtils.toString(afterInvenNum, eiMetadata.getMeta("afterInvenNum")));
        map.put("spotNum",StringUtils.toString(spotNum, eiMetadata.getMeta("spotNum")));
        map.put("spotName",StringUtils.toString(spotName, eiMetadata.getMeta("spotName")));
        map.put("inventoryFlag",StringUtils.toString(inventoryFlag, eiMetadata.getMeta("inventoryFlag")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("dealFlag",StringUtils.toString(dealFlag, eiMetadata.getMeta("dealFlag")));
        return map;
    }
}