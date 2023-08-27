/**
* Generate time : 2022-03-28 17:54:06
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.pj.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsSatisfactionResult
* 
*/
public class DormsSatisfactionResult extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String manId = " ";		/* 人员id*/
    private String itemCode = " ";		/* 项目编码*/
    private String evalGrade = " ";		/* 评价等级(1-5) (1:不清楚；2:不满意；3:合格；4:比较满意；5:满意)*/
    private String currentYear = " ";		/* 本年所属年份*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("人员id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemCode");
        eiColumn.setDescName("项目编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalGrade");
        eiColumn.setDescName("评价等级(1-5) (1:不清楚；2:不满意；3:合格；4:比较满意；5:满意)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentYear");
        eiColumn.setDescName("本年所属年份");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsSatisfactionResult() {
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
     * get the itemCode - 项目编码
     * @return the itemCode
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * set the itemCode - 项目编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * get the evalGrade - 评价等级(1-5) (1:不清楚；2:不满意；3:合格；4:比较满意；5:满意)
     * @return the evalGrade
     */
    public String getEvalGrade() {
        return this.evalGrade;
    }

    /**
     * set the evalGrade - 评价等级(1-5) (1:不清楚；2:不满意；3:合格；4:比较满意；5:满意)
     */
    public void setEvalGrade(String evalGrade) {
        this.evalGrade = evalGrade;
    }

    /**
     * get the currentYear - 本年所属年份
     * @return the currentYear
     */
    public String getCurrentYear() {
        return this.currentYear;
    }

    /**
     * set the currentYear - 本年所属年份
     */
    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setItemCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemCode")), itemCode));
        setEvalGrade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalGrade")), evalGrade));
        setCurrentYear(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currentYear")), currentYear));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("itemCode",StringUtils.toString(itemCode, eiMetadata.getMeta("itemCode")));
        map.put("evalGrade",StringUtils.toString(evalGrade, eiMetadata.getMeta("evalGrade")));
        map.put("currentYear",StringUtils.toString(currentYear, eiMetadata.getMeta("currentYear")));
        return map;
    }
}