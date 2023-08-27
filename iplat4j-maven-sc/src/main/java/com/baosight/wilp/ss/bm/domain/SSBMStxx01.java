/**
* Generate time : 2021-03-01 15:19:07
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* SHST01
* sc_canteen_liaison_conf
*/
public class SSBMStxx01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String canteenName = " ";		/* 食堂名称*/
    private String liaisonId = " ";		/* 联络员工号*/
    private String liaisonName = " ";		/* 联络员姓名*/
    private String canteenType = " ";		/* 食堂类型,(职工食堂/病员食堂，00/01）*/
    private String canteenTypeNum = " ";		/* 食堂分类编码*/
    private String canteenTypeName = " ";		/* 食堂分类名*/
    private String recCreateTime = " ";		
    private String recCreator = " ";		
    private String isAutoSche = "0";		/* 是否启动排班；0/1,不启动/启动*/
    private String operateCode = " ";		/* 业务code*/
    private String operateName = " ";		/* 业务名称*/
    private String datagroupCode = " ";		
    private String datagroupName = " ";		
    private String datagroupTreecode = " ";		
    private String statusCode = " ";		
    private String telephone = " ";		/* 联系电话*/
    private String mealNumName = " ";		/* 餐类名称，早餐,中餐,晚餐*/
    private String mealNum = " ";		/* 餐类编码，001,002,003*/
    private String canteenStatus = " ";		/* 状态编码，1 - 启用 ； 0 - 禁用*/
    private String canteenStatusName = " ";		/* 食堂状态名称*/
    private String minPrice = " ";		/* 食堂状态名称*/
    private String transFee = " ";		/* 食堂状态名称*/

    private Integer sendFee = 0;   /* 每单最低起送费(单位:分)*/
    private Integer shipFee = 0;   /* 每单配送费(单位:分)*/
    private String dishesNumber = " ";		/* 菜品数量*/

    private List<String> patiOrderDate;
    private List<SSBMDcsj01> canteenTimesInfo;
    
    private List<HashMap<String, String>> buildConfig;
    
    private List<SSBMCpfl01> mealType;
    
    private List<SSBMDcsj01> sendTime;
    
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("liaisonId");
        eiColumn.setDescName("联络员工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("liaisonName");
        eiColumn.setDescName("联络员姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenType");
        eiColumn.setDescName("食堂类型,(职工食堂/病员食堂，00/01）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenTypeNum");
        eiColumn.setDescName("食堂分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenTypeName");
        eiColumn.setDescName("食堂分类名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isAutoSche");
        eiColumn.setDescName("是否启动排班；0/1,不启动/启动");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateCode");
        eiColumn.setDescName("业务code");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateName");
        eiColumn.setDescName("业务名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("datagroupName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupTreecode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("telephone");
        eiColumn.setDescName("联系电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNumName");
        eiColumn.setDescName("餐类名称，早餐,中餐,晚餐");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNum");
        eiColumn.setDescName("餐类编码，001,002,003");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenStatus");
        eiColumn.setDescName("状态编码，1 - 启用 ； 0 - 禁用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenStatusName");
        eiColumn.setDescName("食堂状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dishesNumber");
        eiColumn.setDescName("菜品数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("shipFee");
        eiColumn.setDescName("配送费");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMStxx01() {
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
     * get the canteenName - 食堂名称
     * @return the canteenName
     */
    public String getCanteenName() {
        return this.canteenName;
    }

    /**
     * set the canteenName - 食堂名称
     */
    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    /**
     * get the liaisonId - 联络员工号
     * @return the liaisonId
     */
    public String getLiaisonId() {
        return this.liaisonId;
    }

    /**
     * set the liaisonId - 联络员工号
     */
    public void setLiaisonId(String liaisonId) {
        this.liaisonId = liaisonId;
    }

    /**
     * get the liaisonName - 联络员姓名
     * @return the liaisonName
     */
    public String getLiaisonName() {
        return this.liaisonName;
    }

    /**
     * set the liaisonName - 联络员姓名
     */
    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName;
    }

    /**
     * get the canteenType - 食堂类型,(职工食堂/病员食堂，00/01）
     * @return the canteenType
     */
    public String getCanteenType() {
        return this.canteenType;
    }

    /**
     * set the canteenType - 食堂类型,(职工食堂/病员食堂，00/01）
     */
    public void setCanteenType(String canteenType) {
        this.canteenType = canteenType;
    }

    /**
     * get the canteenTypeNum - 食堂分类编码
     * @return the canteenTypeNum
     */
    public String getCanteenTypeNum() {
        return this.canteenTypeNum;
    }

    /**
     * set the canteenTypeNum - 食堂分类编码
     */
    public void setCanteenTypeNum(String canteenTypeNum) {
        this.canteenTypeNum = canteenTypeNum;
    }

    /**
     * get the canteenTypeName - 食堂分类名
     * @return the canteenTypeName
     */
    public String getCanteenTypeName() {
        return this.canteenTypeName;
    }

    /**
     * set the canteenTypeName - 食堂分类名
     */
    public void setCanteenTypeName(String canteenTypeName) {
        this.canteenTypeName = canteenTypeName;
    }

    /**
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recCreator 
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator 
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the isAutoSche - 是否启动排班；0/1,不启动/启动
     * @return the isAutoSche
     */
    public String getIsAutoSche() {
        return this.isAutoSche;
    }

    /**
     * set the isAutoSche - 是否启动排班；0/1,不启动/启动
     */
    public void setIsAutoSche(String isAutoSche) {
        this.isAutoSche = isAutoSche;
    }

    /**
     * get the operateCode - 业务code
     * @return the operateCode
     */
    public String getOperateCode() {
        return this.operateCode;
    }

    /**
     * set the operateCode - 业务code
     */
    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    /**
     * get the operateName - 业务名称
     * @return the operateName
     */
    public String getOperateName() {
        return this.operateName;
    }

    /**
     * set the operateName - 业务名称
     */
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    /**
     * get the datagroupCode 
     * @return the datagroupCode
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the datagroupCode 
     */
    public void setDatagroupCode(String datagroupCode) {
        this.datagroupCode = datagroupCode;
    }

    /**
     * get the datagroupTreecode 
     * @return the datagroupTreecode
     */
    public String getDatagroupTreecode() {
        return this.datagroupTreecode;
    }

    /**
     * set the datagroupTreecode 
     */
    public void setDatagroupTreecode(String datagroupTreecode) {
        this.datagroupTreecode = datagroupTreecode;
    }

    /**
     * get the statusCode 
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode 
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the telephone - 联系电话
     * @return the telephone
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * set the telephone - 联系电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * get the mealNumName - 餐类名称，早餐,中餐,晚餐
     * @return the mealNumName
     */
    public String getMealNumName() {
        return this.mealNumName;
    }

    /**
     * set the mealNumName - 餐类名称，早餐,中餐,晚餐
     */
    public void setMealNumName(String mealNumName) {
        this.mealNumName = mealNumName;
    }

    /**
     * get the mealNum - 餐类编码，001,002,003
     * @return the mealNum
     */
    public String getMealNum() {
        return this.mealNum;
    }

    /**
     * set the mealNum - 餐类编码，001,002,003
     */
    public void setMealNum(String mealNum) {
        this.mealNum = mealNum;
    }

    /**
     * get the canteenStatus - 状态编码，1 - 启用 ； 0 - 禁用
     * @return the canteenStatus
     */
    public String getCanteenStatus() {
        return this.canteenStatus;
    }

    /**
     * set the canteenStatus - 状态编码，1 - 启用 ； 0 - 禁用
     */
    public void setCanteenStatus(String canteenStatus) {
        this.canteenStatus = canteenStatus;
    }

    /**
     * get the canteenStatusName - 食堂状态名称
     * @return the canteenStatusName
     */
    public String getCanteenStatusName() {
        return this.canteenStatusName;
    }

    
    
    public String getDatagroupName() {
        return datagroupName;
    }

    public void setDatagroupName(String datagroupName) {
        this.datagroupName = datagroupName;
    }

    /**
     * set the canteenStatusName - 食堂状态名称
     */
    public void setCanteenStatusName(String canteenStatusName) {
        this.canteenStatusName = canteenStatusName;
    }

    public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getTransFee() {
		return transFee;
	}

	public void setTransFee(String transFee) {
		this.transFee = transFee;
	}


	public List<SSBMDcsj01> getCanteenTimesInfo() {
		return canteenTimesInfo;
	}

	public void setCanteenTimesInfo(List<SSBMDcsj01> canteenTimesInfo) {
		this.canteenTimesInfo = canteenTimesInfo;
	}

	public List<String> getPatiOrderDate() {
		return patiOrderDate;
	}

	public void setPatiOrderDate(List<String> patiOrderDate) {
		this.patiOrderDate = patiOrderDate;
	}

	
	public List<HashMap<String, String>> getBuildConfig() {
		return buildConfig;
	}

	public void setBuildConfig(List<HashMap<String, String>> buildConfig) {
		this.buildConfig = buildConfig;
	}

	
	
	public List<SSBMCpfl01> getMealType() {
		return mealType;
	}

	public void setMealType(List<SSBMCpfl01> mealType) {
		this.mealType = mealType;
	}

	public List<SSBMDcsj01> getSendTime() {
		return sendTime;
	}

	public void setSendTime(List<SSBMDcsj01> sendTime) {
		this.sendTime = sendTime;
	}

    public Integer getSendFee() {
        return sendFee;
    }

    public void setSendFee(Integer sendFee) {
        this.sendFee = sendFee;
    }

    public Integer getShipFee() {
        return shipFee;
    }

    public void setShipFee(Integer shipFee) {
        this.shipFee = shipFee;
    }

    public String getDishesNumber() {
        return this.dishesNumber;
    }

    public void setDishesNumber(String dishesNumber) {
        this.dishesNumber = dishesNumber;
    }


    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setLiaisonId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("liaisonId")), liaisonId));
        setLiaisonName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("liaisonName")), liaisonName));
        setCanteenType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenType")), canteenType));
        setCanteenTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenTypeNum")), canteenTypeNum));
        setCanteenTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenTypeName")), canteenTypeName));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setIsAutoSche(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isAutoSche")), isAutoSche));
        setOperateCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operateCode")), operateCode));
        setOperateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operateName")), operateName));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
        setDatagroupName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupName")), datagroupName));
        setDatagroupTreecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupTreecode")), datagroupTreecode));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setTelephone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("telephone")), telephone));
        setMealNumName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNumName")), mealNumName));
        setMealNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNum")), mealNum));
        setCanteenStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenStatus")), canteenStatus));
        setCanteenStatusName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenStatusName")), canteenStatusName));
        setShipFee(NumberUtils.toInteger(StringUtils.toString(map.get("shipFee")), shipFee));
        setDishesNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dishesNumber")), dishesNumber));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("liaisonId",StringUtils.toString(liaisonId, eiMetadata.getMeta("liaisonId")));
        map.put("liaisonName",StringUtils.toString(liaisonName, eiMetadata.getMeta("liaisonName")));
        map.put("canteenType",StringUtils.toString(canteenType, eiMetadata.getMeta("canteenType")));
        map.put("canteenTypeNum",StringUtils.toString(canteenTypeNum, eiMetadata.getMeta("canteenTypeNum")));
        map.put("canteenTypeName",StringUtils.toString(canteenTypeName, eiMetadata.getMeta("canteenTypeName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("isAutoSche",StringUtils.toString(isAutoSche, eiMetadata.getMeta("isAutoSche")));
        map.put("operateCode",StringUtils.toString(operateCode, eiMetadata.getMeta("operateCode")));
        map.put("operateName",StringUtils.toString(operateName, eiMetadata.getMeta("operateName")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        map.put("datagroupName",StringUtils.toString(datagroupName, eiMetadata.getMeta("datagroupName")));
        map.put("datagroupTreecode",StringUtils.toString(datagroupTreecode, eiMetadata.getMeta("datagroupTreecode")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("telephone",StringUtils.toString(telephone, eiMetadata.getMeta("telephone")));
        map.put("mealNumName",StringUtils.toString(mealNumName, eiMetadata.getMeta("mealNumName")));
        map.put("mealNum",StringUtils.toString(mealNum, eiMetadata.getMeta("mealNum")));
        map.put("canteenStatus",StringUtils.toString(canteenStatus, eiMetadata.getMeta("canteenStatus")));
        map.put("canteenStatusName",StringUtils.toString(canteenStatusName, eiMetadata.getMeta("canteenStatusName")));
        map.put("shipFee",StringUtils.toString(shipFee, eiMetadata.getMeta("shipFee")));
        map.put("dishesNumber",StringUtils.toString(dishesNumber, eiMetadata.getMeta("dishesNumber")));
        return map;
    }
}