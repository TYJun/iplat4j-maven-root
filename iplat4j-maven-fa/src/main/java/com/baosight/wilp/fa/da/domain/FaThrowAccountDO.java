/**
* Generate time : 2022-12-20 13:07:46
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.fa.da.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.util.DateUtils;
import java.util.Date;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* FaThrowAccountDO
* 
*/
public class FaThrowAccountDO extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 资产抛帐主键*/
    private String fainfoId = " ";		/* 资产卡片主键*/
    private String goodsTypeCode = " ";		/* 资产类别*/
    private String goodsNum = " ";		/* 资产编码*/
    private String goodsName = " ";		/* 资产名称*/
    private BigDecimal netAssetValue = new BigDecimal("0");		/* 资产净值变化量*/
    private String accountType = " ";		/* 财务类型(010-调拨出库,021-资产升值变更,026-资产贬值变更,030-资产折旧,040-资产报废)*/
    private Date createDate;	/* 创建时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("资产抛帐主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fainfoId");
        eiColumn.setDescName("资产卡片主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsTypeCode");
        eiColumn.setDescName("资产类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsNum");
        eiColumn.setDescName("资产编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsName");
        eiColumn.setDescName("资产名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("netAssetValue");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(18);
        eiColumn.setDescName("资产净值变化量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accountType");
        eiColumn.setDescName("财务类型(010-调拨出库,021-资产升值变更,026-资产贬值变更,030-资产折旧,040-资产报废)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createDate");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public FaThrowAccountDO() {
        initMetaData();
    }

    /**
     * get the id - 资产抛帐主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 资产抛帐主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the fainfoId - 资产卡片主键
     * @return the fainfoId
     */
    public String getFainfoId() {
        return this.fainfoId;
    }

    /**
     * set the fainfoId - 资产卡片主键
     */
    public void setFainfoId(String fainfoId) {
        this.fainfoId = fainfoId;
    }

    /**
     * get the goodsTypeCode - 资产类别
     * @return the goodsTypeCode
     */
    public String getGoodsTypeCode() {
        return this.goodsTypeCode;
    }

    /**
     * set the goodsTypeCode - 资产类别
     */
    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    /**
     * get the goodsNum - 资产编码
     * @return the goodsNum
     */
    public String getGoodsNum() {
        return this.goodsNum;
    }

    /**
     * set the goodsNum - 资产编码
     */
    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * get the goodsName - 资产名称
     * @return the goodsName
     */
    public String getGoodsName() {
        return this.goodsName;
    }

    /**
     * set the goodsName - 资产名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * get the netAssetValue - 资产净值变化量
     * @return the netAssetValue
     */
    public BigDecimal getNetAssetValue() {
        return this.netAssetValue;
    }

    /**
     * set the netAssetValue - 资产净值变化量
     */
    public void setNetAssetValue(BigDecimal netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    /**
     * get the accountType - 财务类型(010-调拨出库,021-资产升值变更,026-资产贬值变更,030-资产折旧,040-资产报废)
     * @return the accountType
     */
    public String getAccountType() {
        return this.accountType;
    }

    /**
     * set the accountType - 财务类型(010-调拨出库,021-资产升值变更,026-资产贬值变更,030-资产折旧,040-资产报废)
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * get the createDate - 创建时间
     * @return the createDate
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * set the createDate - 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setFainfoId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fainfoId")), fainfoId));
        setGoodsTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsTypeCode")), goodsTypeCode));
        setGoodsNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsNum")), goodsNum));
        setGoodsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsName")), goodsName));
        setNetAssetValue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("netAssetValue")), netAssetValue));
        setAccountType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accountType")), accountType));
        setCreateDate(DateUtils.toDate(StringUtils.toString(map.get("createDate"))));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("fainfoId",StringUtils.toString(fainfoId, eiMetadata.getMeta("fainfoId")));
        map.put("goodsTypeCode",StringUtils.toString(goodsTypeCode, eiMetadata.getMeta("goodsTypeCode")));
        map.put("goodsNum",StringUtils.toString(goodsNum, eiMetadata.getMeta("goodsNum")));
        map.put("goodsName",StringUtils.toString(goodsName, eiMetadata.getMeta("goodsName")));
        map.put("netAssetValue",StringUtils.toString(netAssetValue, eiMetadata.getMeta("netAssetValue")));
        map.put("accountType",StringUtils.toString(accountType, eiMetadata.getMeta("accountType")));
        map.put("createDate",StringUtils.toString(createDate, eiMetadata.getMeta("createDate")));
        return map;
    }
}