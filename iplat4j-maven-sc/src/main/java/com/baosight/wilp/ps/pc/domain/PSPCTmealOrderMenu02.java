/**
* Generate time : 2021-06-01 14:35:00
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTmealOrderMenu02 菜品实体类2
* 
*/
public class PSPCTmealOrderMenu02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String billDetailNo = " ";		
    private String menuName = " ";		
    private String menuNum = " ";		
    private String menuPicUrl = " ";		
    private String menuNumber = " ";		
    private String menuTotalPrice = " ";		
    private BigDecimal menuPrice = new BigDecimal("0");		
    private BigDecimal evalLevel = new BigDecimal("0");		
    private String evalContent = " ";		
    private String done = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billDetailNo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuPicUrl");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNumber");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuTotalPrice");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(12);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalLevel");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(12);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalContent");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("done");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTmealOrderMenu02() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the billDetailNo 
     * @return the billDetailNo
     */
    public String getBillDetailNo() {
        return this.billDetailNo;
    }

    /**
     * set the billDetailNo 
     */
    public void setBillDetailNo(String billDetailNo) {
        this.billDetailNo = billDetailNo;
    }

    /**
     * get the menuName 
     * @return the menuName
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * set the menuName 
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * get the menuNum 
     * @return the menuNum
     */
    public String getMenuNum() {
        return this.menuNum;
    }

    /**
     * set the menuNum 
     */
    public void setMenuNum(String menuNum) {
        this.menuNum = menuNum;
    }

    /**
     * get the menuPicUrl 
     * @return the menuPicUrl
     */
    public String getMenuPicUrl() {
        return this.menuPicUrl;
    }

    /**
     * set the menuPicUrl 
     */
    public void setMenuPicUrl(String menuPicUrl) {
        this.menuPicUrl = menuPicUrl;
    }

    /**
     * get the menuNumber 
     * @return the menuNumber
     */
    public String getMenuNumber() {
        return this.menuNumber;
    }

    /**
     * set the menuNumber 
     */
    public void setMenuNumber(String menuNumber) {
        this.menuNumber = menuNumber;
    }

    /**
     * get the menuTotalPrice 
     * @return the menuTotalPrice
     */
    public String getMenuTotalPrice() {
        return this.menuTotalPrice;
    }

    /**
     * set the menuTotalPrice 
     */
    public void setMenuTotalPrice(String menuTotalPrice) {
        this.menuTotalPrice = menuTotalPrice;
    }

    /**
     * get the menuPrice 
     * @return the menuPrice
     */
    public BigDecimal getMenuPrice() {
        return this.menuPrice;
    }

    /**
     * set the menuPrice 
     */
    public void setMenuPrice(BigDecimal menuPrice) {
        this.menuPrice = menuPrice;
    }

    /**
     * get the evalLevel 
     * @return the evalLevel
     */
    public BigDecimal getEvalLevel() {
        return this.evalLevel;
    }

    /**
     * set the evalLevel 
     */
    public void setEvalLevel(BigDecimal evalLevel) {
        this.evalLevel = evalLevel;
    }

    /**
     * get the evalContent 
     * @return the evalContent
     */
    public String getEvalContent() {
        return this.evalContent;
    }

    /**
     * set the evalContent 
     */
    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    /**
     * get the done 
     * @return the done
     */
    public String getDone() {
        return this.done;
    }

    /**
     * set the done 
     */
    public void setDone(String done) {
        this.done = done;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillDetailNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billDetailNo")), billDetailNo));
        setMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuName")), menuName));
        setMenuNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNum")), menuNum));
        setMenuPicUrl(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuPicUrl")), menuPicUrl));
        setMenuNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNumber")), menuNumber));
        setMenuTotalPrice(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuTotalPrice")), menuTotalPrice));
        setMenuPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuPrice")), menuPrice));
        setEvalLevel(NumberUtils.toBigDecimal(StringUtils.toString(map.get("evalLevel")), evalLevel));
        setEvalContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalContent")), evalContent));
        setDone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("done")), done));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billDetailNo",StringUtils.toString(billDetailNo, eiMetadata.getMeta("billDetailNo")));
        map.put("menuName",StringUtils.toString(menuName, eiMetadata.getMeta("menuName")));
        map.put("menuNum",StringUtils.toString(menuNum, eiMetadata.getMeta("menuNum")));
        map.put("menuPicUrl",StringUtils.toString(menuPicUrl, eiMetadata.getMeta("menuPicUrl")));
        map.put("menuNumber",StringUtils.toString(menuNumber, eiMetadata.getMeta("menuNumber")));
        map.put("menuTotalPrice",StringUtils.toString(menuTotalPrice, eiMetadata.getMeta("menuTotalPrice")));
        map.put("menuPrice",StringUtils.toString(menuPrice, eiMetadata.getMeta("menuPrice")));
        map.put("evalLevel",StringUtils.toString(evalLevel, eiMetadata.getMeta("evalLevel")));
        map.put("evalContent",StringUtils.toString(evalContent, eiMetadata.getMeta("evalContent")));
        map.put("done",StringUtils.toString(done, eiMetadata.getMeta("done")));
        return map;
    }
}