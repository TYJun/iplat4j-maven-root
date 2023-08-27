/**
* Generate time : 2021-05-26 19:14:32
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ss.bm.domain.SSBMTyTmealPic;

/**
* PSPCTmealOrderMenu 菜品实体类
* 
*/
public class PSPCTmealOrderMenu extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String menuId = " ";		/* 主键*/
    private String menuNum = " ";		/* 菜品编码*/
    private String menuName = " ";		/* 菜品名称*/
    private String descption = " ";		/* 菜品描述*/
    private String nutrition = " ";		/* 营养价值*/
    private String heat = " ";		/* 热量*/
    private String menuPicUrl = " ";		/* 菜品图片地址*/
    private BigDecimal menuPrice = new BigDecimal("0");		/* 菜品单价*/
    private String notSuitGroup = " ";		/* 不适宜人群*/
    private String suitGroup = " ";		/* 适宜人群*/
    private Integer surplus = new Integer(100);		/* 剩余数量*/
    private Integer num = new Integer(0);		/* 剩余数量*/
    private BigDecimal priceAfterDiscount = new BigDecimal("0");		/* 折后价格*/
    private String menuTypeNum = " ";		/* 菜品类型编码*/
    private String menuTypeName = " ";		/* 菜品类型名称*/
    private BigDecimal menuFee = new BigDecimal("0"); //打包费

    private Long evalLevel = new Long(0);	

    private String comprise = "";
    
    private List<SSBMTyTmealPic> picList ;
    
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuId");
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNum");
        eiColumn.setDescName("菜品编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("comprise");
        eiColumn.setDescName("");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("menuName");
        eiColumn.setDescName("菜品名称");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("descption");
        eiColumn.setDescName("菜品描述");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("nutrition");
        eiColumn.setDescName("营养价值");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("heat");
        eiColumn.setDescName("热量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuPicUrl");
        eiColumn.setDescName("菜品图片地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("菜品单价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("notSuitGroup");
        eiColumn.setDescName("不适宜人群");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("suitGroup");
        eiColumn.setDescName("适宜人群");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surplus");
        eiColumn.setDescName("剩余数量");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("num");
        eiColumn.setDescName("总数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("priceAfterDiscount");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("折后价格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuTypeNum");
        eiColumn.setDescName("菜品类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuTypeName");
        eiColumn.setDescName("菜品类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalLevel");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTmealOrderMenu() {
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
     * get the menuId - 主键
     * @return the menuId
     */
    public String getMenuId() {
        return this.menuId;
    }

    /**
     * set the menuId - 主键
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * get the menuNum - 菜品编码
     * @return the menuNum
     */
    public String getMenuNum() {
        return this.menuNum;
    }

    /**
     * set the menuNum - 菜品编码
     */
    public void setMenuNum(String menuNum) {
        this.menuNum = menuNum;
    }

    /**
     * get the menuName - 菜品名称
     * @return the menuName
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * set the menuName - 菜品名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * get the menuPicUrl - 菜品图片地址
     * @return the menuPicUrl
     */
    public String getMenuPicUrl() {
        return this.menuPicUrl;
    }

    /**
     * set the menuPicUrl - 菜品图片地址
     */
    public void setMenuPicUrl(String menuPicUrl) {
        this.menuPicUrl = menuPicUrl;
    }

    /**
     * get the menuPrice - 菜品单价
     * @return the menuPrice
     */
    public BigDecimal getMenuPrice() {
        return this.menuPrice;
    }

    /**
     * set the menuPrice - 菜品单价
     */
    public void setMenuPrice(BigDecimal menuPrice) {
        this.menuPrice = menuPrice;
    }

    /**
     * get the notSuitGroup - 不适宜人群
     * @return the notSuitGroup
     */
    public String getNotSuitGroup() {
        return this.notSuitGroup;
    }

    /**
     * set the notSuitGroup - 不适宜人群
     */
    public void setNotSuitGroup(String notSuitGroup) {
        this.notSuitGroup = notSuitGroup;
    }

    /**
     * get the suitGroup - 适宜人群
     * @return the suitGroup
     */
    public String getSuitGroup() {
        return this.suitGroup;
    }

    /**
     * set the suitGroup - 适宜人群
     */
    public void setSuitGroup(String suitGroup) {
        this.suitGroup = suitGroup;
    }

    /**
     * get the surplus - 剩余数量
     * @return the surplus
     */
    public Integer getSurplus() {
        return this.surplus;
    }

    /**
     * set the surplus - 剩余数量
     */
    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    /**
     * get the priceAfterDiscount - 折后价格
     * @return the priceAfterDiscount
     */
    public BigDecimal getPriceAfterDiscount() {
        return this.priceAfterDiscount;
    }

    /**
     * set the priceAfterDiscount - 折后价格
     */
    public void setPriceAfterDiscount(BigDecimal priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    /**
     * get the menuTypeNum - 菜品类型编码
     * @return the menuTypeNum
     */
    public String getMenuTypeNum() {
        return this.menuTypeNum;
    }

    /**
     * set the menuTypeNum - 菜品类型编码
     */
    public void setMenuTypeNum(String menuTypeNum) {
        this.menuTypeNum = menuTypeNum;
    }

    /**
     * get the menuTypeName - 菜品类型名称
     * @return the menuTypeName
     */
    public String getMenuTypeName() {
        return this.menuTypeName;
    }

    /**
     * set the menuTypeName - 菜品类型名称
     */
    public void setMenuTypeName(String menuTypeName) {
        this.menuTypeName = menuTypeName;
    }

    /**
     * get the evalLevel 
     * @return the evalLevel
     */
    public Long getEvalLevel() {
        return this.evalLevel;
    }

    /**
     * set the evalLevel 
     */
    public void setEvalLevel(Long evalLevel) {
        this.evalLevel = evalLevel;
    }

    
    
    public String getDescption() {
		return descption;
	}

	public void setDescption(String descption) {
		this.descption = descption;
	}

	public String getNutrition() {
		return nutrition;
	}

	public void setNutrition(String nutrition) {
		this.nutrition = nutrition;
	}

	public String getHeat() {
		return heat;
	}

	public void setHeat(String heat) {
		this.heat = heat;
	}

	public String getComprise() {
		return comprise;
	}

	public void setComprise(String comprise) {
		this.comprise = comprise;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	
	
	public List<SSBMTyTmealPic> getPicList() {
		return picList;
	}

	public void setPicList(List<SSBMTyTmealPic> picList) {
		this.picList = picList;
	}

    public BigDecimal getMenuFee() {
        return menuFee;
    }

    public void setMenuFee(BigDecimal menuFee) {
        this.menuFee = menuFee;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMenuId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuId")), menuId));
        setMenuNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNum")), menuNum));
        setMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuName")), menuName));
        setMenuPicUrl(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuPicUrl")), menuPicUrl));
        setMenuPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuPrice")), menuPrice));
        setNotSuitGroup(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("notSuitGroup")), notSuitGroup));
        setSuitGroup(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("suitGroup")), suitGroup));
        setSurplus(NumberUtils.toInteger(StringUtils.toString(map.get("surplus")), surplus));
        setNum(NumberUtils.toInteger(StringUtils.toString(map.get("num")), num));
        setPriceAfterDiscount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("priceAfterDiscount")), priceAfterDiscount));
        setMenuTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuTypeNum")), menuTypeNum));
        setMenuTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuTypeName")), menuTypeName));
        setEvalLevel(NumberUtils.toLong(StringUtils.toString(map.get("evalLevel")), evalLevel));
        setDescption(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("descption")), descption));
        setNutrition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nutrition")), nutrition));
        setHeat(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("heat")), heat));
        setComprise(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("comprise")), comprise));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("menuId",StringUtils.toString(menuId, eiMetadata.getMeta("menuId")));
        map.put("menuNum",StringUtils.toString(menuNum, eiMetadata.getMeta("menuNum")));
        map.put("menuName",StringUtils.toString(menuName, eiMetadata.getMeta("menuName")));
        map.put("menuPicUrl",StringUtils.toString(menuPicUrl, eiMetadata.getMeta("menuPicUrl")));
        map.put("menuPrice",StringUtils.toString(menuPrice, eiMetadata.getMeta("menuPrice")));
        map.put("notSuitGroup",StringUtils.toString(notSuitGroup, eiMetadata.getMeta("notSuitGroup")));
        map.put("suitGroup",StringUtils.toString(suitGroup, eiMetadata.getMeta("suitGroup")));
        map.put("surplus",StringUtils.toString(surplus, eiMetadata.getMeta("surplus")));
        map.put("num",StringUtils.toString(num, eiMetadata.getMeta("num")));
        map.put("priceAfterDiscount",StringUtils.toString(priceAfterDiscount, eiMetadata.getMeta("priceAfterDiscount")));
        map.put("menuTypeNum",StringUtils.toString(menuTypeNum, eiMetadata.getMeta("menuTypeNum")));
        map.put("menuTypeName",StringUtils.toString(menuTypeName, eiMetadata.getMeta("menuTypeName")));
        map.put("evalLevel",StringUtils.toString(evalLevel, eiMetadata.getMeta("evalLevel")));
        map.put("descption",StringUtils.toString(descption, eiMetadata.getMeta("descption")));
        map.put("nutrition",StringUtils.toString(nutrition, eiMetadata.getMeta("nutrition")));
        map.put("heat",StringUtils.toString(heat, eiMetadata.getMeta("heat")));
        map.put("comprise",StringUtils.toString(comprise, eiMetadata.getMeta("comprise")));
        return map;
    }

	@Override
	public String toString() {
		return "PSPCTmealOrderMenu [id=" + id + ", menuId=" + menuId + ", menuNum=" + menuNum + ", menuName=" + menuName
				+ ", descption=" + descption + ", nutrition=" + nutrition + ", heat=" + heat + ", menuPicUrl="
				+ menuPicUrl + ", menuPrice=" + menuPrice + ", notSuitGroup=" + notSuitGroup + ", suitGroup="
				+ suitGroup + ", surplus=" + surplus + ", num=" + num + ", priceAfterDiscount=" + priceAfterDiscount
				+ ", menuTypeNum=" + menuTypeNum + ", menuTypeName=" + menuTypeName + ", evalLevel=" + evalLevel
				+ ", comprise=" + comprise + "]";
	}
    
    
}