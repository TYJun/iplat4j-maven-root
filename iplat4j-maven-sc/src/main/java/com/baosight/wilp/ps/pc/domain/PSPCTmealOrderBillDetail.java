/**
* Generate time : 2021-05-27 15:56:50
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTmealOrderBillDetail 订单详情实体类
* 
*/
public class PSPCTmealOrderBillDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = "1";		
    private String recCreator = " ";		/* 记录创建责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String billNo = " ";		
    private String billDetailNo = " ";		
    private String menuNum = " ";		
    private String menuName = " ";		
    private String menuPicUrl = " ";		
    private Integer menuNumber = new Integer(0);		
    private BigDecimal menuPrice = new BigDecimal("0");		
    private BigDecimal menuTotalPrice = new BigDecimal("0");		
    private String done = " ";		/* 是否已评价(Y/N)*/
    private Integer evalLevel = new Integer(0);		/* 评价等级(0-10)*/
    private String evalContent = " ";		/* 评价内容*/
    private String evalAvg = " ";		/* 评价内容*/
    private String scheId = "1";		/* paiban*/

	//订单菜品信息
	private List<?> billDetail;
	
	//订单评价信息
	private List<?> eavlDetail;
    
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
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
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billDetailNo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("menuName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNumber");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuTotalPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("done");
        eiColumn.setDescName("是否已评价(Y/N)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalLevel");
        eiColumn.setDescName("评价等级(0-10)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalContent");
        eiColumn.setDescName("评价内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scheId");
        eiColumn.setDescName("paiban");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTmealOrderBillDetail() {
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
     * get the billNo 
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo 
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
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
     * get the menuNumber 
     * @return the menuNumber
     */
    public Integer getMenuNumber() {
        return this.menuNumber;
    }

    /**
     * set the menuNumber 
     */
    public void setMenuNumber(Integer menuNumber) {
        this.menuNumber = menuNumber;
    }

    public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
     * get the menuTotalPrice 
     * @return the menuTotalPrice
     */
    public BigDecimal getMenuTotalPrice() {
        return this.menuTotalPrice;
    }

    /**
     * set the menuTotalPrice 
     */
    public void setMenuTotalPrice(BigDecimal menuTotalPrice) {
        this.menuTotalPrice = menuTotalPrice;
    }

    /**
     * get the done - 是否已评价(Y/N)
     * @return the done
     */
    public String getDone() {
        return this.done;
    }

    /**
     * set the done - 是否已评价(Y/N)
     */
    public void setDone(String done) {
        this.done = done;
    }

    /**
     * get the evalLevel - 评价等级(0-10)
     * @return the evalLevel
     */
    public Integer getEvalLevel() {
        return this.evalLevel;
    }

    /**
     * set the evalLevel - 评价等级(0-10)
     */
    public void setEvalLevel(Integer evalLevel) {
        this.evalLevel = evalLevel;
    }

    /**
     * get the evalContent - 评价内容
     * @return the evalContent
     */
    public String getEvalContent() {
        return this.evalContent;
    }

    /**
     * set the evalContent - 评价内容
     */
    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    /**
     * get the scheId - paiban
     * @return the scheId
     */
    public String getScheId() {
        return this.scheId;
    }

    /**
     * set the scheId - paiban
     */
    public void setScheId(String scheId) {
        this.scheId = scheId;
    }

    public String getMenuPicUrl() {
		return menuPicUrl;
	}

	public void setMenuPicUrl(String menuPicUrl) {
		this.menuPicUrl = menuPicUrl;
	}

	public List<?> getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(List<?> billDetail) {
		this.billDetail = billDetail;
	}

	public List<?> getEavlDetail() {
		return eavlDetail;
	}

	public void setEavlDetail(List<?> eavlDetail) {
		this.eavlDetail = eavlDetail;
	}

    public String getEvalAvg() {
        return evalAvg;
    }

    public void setEvalAvg(String evalAvg) {
        this.evalAvg = evalAvg;
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
        setBillDetailNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billDetailNo")), billDetailNo));
        setMenuNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNum")), menuNum));
        setMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuName")), menuName));
        setMenuNumber(NumberUtils.toInteger(StringUtils.toString(map.get("menuNumber")), menuNumber));
        setMenuPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuPrice")), menuPrice));
        setMenuTotalPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuTotalPrice")), menuTotalPrice));
        setDone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("done")), done));
        setEvalLevel(NumberUtils.toInteger(StringUtils.toString(map.get("evalLevel")), evalLevel));
        setEvalContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalContent")), evalContent));
        setScheId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scheId")), scheId));
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
        map.put("billDetailNo",StringUtils.toString(billDetailNo, eiMetadata.getMeta("billDetailNo")));
        map.put("menuNum",StringUtils.toString(menuNum, eiMetadata.getMeta("menuNum")));
        map.put("menuNumber",StringUtils.toString(menuNumber, eiMetadata.getMeta("menuNumber")));
        map.put("menuName",StringUtils.toString(menuName, eiMetadata.getMeta("menuName")));
        map.put("menuPrice",StringUtils.toString(menuPrice, eiMetadata.getMeta("menuPrice")));
        map.put("menuTotalPrice",StringUtils.toString(menuTotalPrice, eiMetadata.getMeta("menuTotalPrice")));
        map.put("done",StringUtils.toString(done, eiMetadata.getMeta("done")));
        map.put("evalLevel",StringUtils.toString(evalLevel, eiMetadata.getMeta("evalLevel")));
        map.put("evalContent",StringUtils.toString(evalContent, eiMetadata.getMeta("evalContent")));
        map.put("scheId",StringUtils.toString(scheId, eiMetadata.getMeta("scheId")));
        return map;
    }
}