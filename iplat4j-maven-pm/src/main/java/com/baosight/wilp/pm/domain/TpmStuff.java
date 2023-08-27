/**
* Generate time : 2021-02-22 14:01:41
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.pm.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 项目材料表
 * 
 * @Title: TpmStuff.java
 * @ClassName: TpmStuff
 * @Package：com.baosight.wilp.pm.domain
 * @author: zhangjiaqian
 * @date: 2021年8月30日 下午6:23:21
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class TpmStuff extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 材料主键*/
    private String projectPk = " ";		/* 项目主键*/
    private String matTypeNum = " ";		/* 材料分类编号*/
    private String matTypeName = " ";		/* 材料类型名称*/
    private String matNum = " ";		/* 材料编号*/
    private String matName = " ";		/* 材料名称*/
    private String spec = " ";		/* 规格*/
    private String unitNum = " ";		/* 单位*/
    private String unitName = " ";		/* 单位名称*/
    private Double price = new Double(0);		/* 价格*/
    private Double num = new Double(0);		/* 数量*/
    private Double totalPrice = new Double(0);		/* 总价*/
    private String supType = " ";		/* 供应方*/
    private String fashion = " ";		/* 方式*/
    private String remark = " ";		/* 备注*/
    private String dataGroupCode = " ";		/* 账套*/
    private String brand = " ";		/* 品牌*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String recCreator = " ";		/* 记录创建人*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改人*/
    private String recReviseTime = " ";		/* 记录修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("材料主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectPk");
        eiColumn.setDescName("项目主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeNum");
        eiColumn.setDescName("材料分类编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeName");
        eiColumn.setDescName("材料类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matNum");
        eiColumn.setDescName("材料编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matName");
        eiColumn.setDescName("材料名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spec");
        eiColumn.setDescName("规格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitNum");
        eiColumn.setDescName("单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitName");
        eiColumn.setDescName("单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("price");
        eiColumn.setDescName("价格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("num");
        eiColumn.setDescName("数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalPrice");
        eiColumn.setDescName("总价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supType");
        eiColumn.setDescName("供应方");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fashion");
        eiColumn.setDescName("方式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("brand");
        eiColumn.setDescName("品牌");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public TpmStuff() {
        initMetaData();
    }

    /**
     * get the id - 材料主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 材料主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the projectPk - 项目主键
     * @return the projectPk
     */
    public String getProjectPk() {
        return this.projectPk;
    }

    /**
     * set the projectPk - 项目主键
     */
    public void setProjectPk(String projectPk) {
        this.projectPk = projectPk;
    }

    /**
     * get the matTypeNum - 材料分类编号
     * @return the matTypeNum
     */
    public String getMatTypeNum() {
        return this.matTypeNum;
    }

    /**
     * set the matTypeNum - 材料分类编号
     */
    public void setMatTypeNum(String matTypeNum) {
        this.matTypeNum = matTypeNum;
    }

    /**
     * get the matTypeName - 材料类型名称
     * @return the matTypeName
     */
    public String getMatTypeName() {
        return this.matTypeName;
    }

    /**
     * set the matTypeName - 材料类型名称
     */
    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    /**
     * get the matNum - 材料编号
     * @return the matNum
     */
    public String getMatNum() {
        return this.matNum;
    }

    /**
     * set the matNum - 材料编号
     */
    public void setMatNum(String matNum) {
        this.matNum = matNum;
    }

    /**
     * get the matName - 材料名称
     * @return the matName
     */
    public String getMatName() {
        return this.matName;
    }

    /**
     * set the matName - 材料名称
     */
    public void setMatName(String matName) {
        this.matName = matName;
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
     * get the unitNum - 单位
     * @return the unitNum
     */
    public String getUnitNum() {
        return this.unitNum;
    }

    /**
     * set the unitNum - 单位
     */
    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    /**
     * get the unitName - 单位名称
     * @return the unitName
     */
    public String getUnitName() {
        return this.unitName;
    }

    /**
     * set the unitName - 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * get the price - 价格
     * @return the price
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * set the price - 价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * get the num - 数量
     * @return the num
     */
    public Double getNum() {
        return this.num;
    }

    /**
     * set the num - 数量
     */
    public void setNum(Double num) {
        this.num = num;
    }

    /**
     * get the totalPrice - 总价
     * @return the totalPrice
     */
    public Double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * set the totalPrice - 总价
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * get the supType - 供应方
     * @return the supType
     */
    public String getSupType() {
        return this.supType;
    }

    /**
     * set the supType - 供应方
     */
    public void setSupType(String supType) {
        this.supType = supType;
    }

    /**
     * get the fashion - 方式
     * @return the fashion
     */
    public String getFashion() {
        return this.fashion;
    }

    /**
     * set the fashion - 方式
     */
    public void setFashion(String fashion) {
        this.fashion = fashion;
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
     * get the brand - 品牌
     * @return the brand
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * set the brand - 品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
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
     * get the recCreator - 记录创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 记录创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
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
     * get the recRevisor - 记录修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 记录修改人
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setProjectPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectPk")), projectPk));
        setMatTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeNum")), matTypeNum));
        setMatTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeName")), matTypeName));
        setMatNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matNum")), matNum));
        setMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
        setSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spec")), spec));
        setUnitNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitNum")), unitNum));
        setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
        setNum(NumberUtils.toDouble(StringUtils.toString(map.get("num")), num));
        setTotalPrice(NumberUtils.toDouble(StringUtils.toString(map.get("totalPrice")), totalPrice));
        setSupType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("supType")), supType));
        setFashion(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fashion")), fashion));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setBrand(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("brand")), brand));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("projectPk",StringUtils.toString(projectPk, eiMetadata.getMeta("projectPk")));
        map.put("matTypeNum",StringUtils.toString(matTypeNum, eiMetadata.getMeta("matTypeNum")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("spec",StringUtils.toString(spec, eiMetadata.getMeta("spec")));
        map.put("unitNum",StringUtils.toString(unitNum, eiMetadata.getMeta("unitNum")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
        map.put("num",StringUtils.toString(num, eiMetadata.getMeta("num")));
        map.put("totalPrice",StringUtils.toString(totalPrice, eiMetadata.getMeta("totalPrice")));
        map.put("supType",StringUtils.toString(supType, eiMetadata.getMeta("supType")));
        map.put("fashion",StringUtils.toString(fashion, eiMetadata.getMeta("fashion")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("brand",StringUtils.toString(brand, eiMetadata.getMeta("brand")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}