/**
* Generate time : 2022-12-10 15:15:30
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.fa.db.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* FaTransferDetail
* 
*/
public class FaTransferDetailVO extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 固定资产调拨明细表主键*/
    private String transferNo = " ";		/* 固定资产调拨表主键*/
    private String faInfoId = " ";		/* 固资主键*/
    private String goodsNum = " ";
    private String goodsName = " ";
    private String model = " ";
    private String spec = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("固定资产调拨明细表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("transferNo");
        eiColumn.setDescName("固定资产调拨表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("faInfoId");
        eiColumn.setDescName("固资主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsNum");
        eiColumn.setDescName("资产编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsName");
        eiColumn.setDescName("资产名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("model");
        eiColumn.setDescName("型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spec");
        eiColumn.setDescName("规格");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public FaTransferDetailVO() {
        initMetaData();
    }

    /**
     * get the id - 固定资产调拨明细表主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 固定资产调拨明细表主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the transferNo - 固定资产调拨表主键
     * @return the transferNo
     */
    public String getTransferNo() {
        return this.transferNo;
    }

    /**
     * set the transferNo - 固定资产调拨表主键
     */
    public void setTransferNo(String transferNo) {
        this.transferNo = transferNo;
    }

    /**
     * get the fainfoId - 固资主键
     * @return the fainfoId
     */
    public String getFaInfoId() {
        return this.faInfoId;
    }

    /**
     * set the fainfoId - 固资主键
     */
    public void setFaInfoId(String fainfoId) {
        this.faInfoId = fainfoId;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTransferNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("transferNo")), transferNo));
        setFaInfoId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("faInfoId")), faInfoId));
        setGoodsNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsNum")), goodsNum));
        setGoodsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsName")), goodsName));
        setModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("model")), model));
        setSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spec")), spec));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("transferNo",StringUtils.toString(transferNo, eiMetadata.getMeta("transferNo")));
        map.put("faInfoId",StringUtils.toString(faInfoId, eiMetadata.getMeta("faInfoId")));
        map.put("goodsNum",StringUtils.toString(goodsNum, eiMetadata.getMeta("goodsNum")));
        map.put("goodsName",StringUtils.toString(goodsName, eiMetadata.getMeta("goodsName")));
        map.put("model",StringUtils.toString(model, eiMetadata.getMeta("model")));
        map.put("spec",StringUtils.toString(spec, eiMetadata.getMeta("spec")));
        return map;
    }
}