package rm.pz.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.rm.common.RmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 科室常用物资表实体
 * RmDeptMatConfig
 * @author fangjian
 */
@SuppressWarnings("all")
public class RmDeptMatConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id ;

    /** 科室编码*/
    private String deptNum ;

    /** 科室名称*/
    private String deptName ;

    /** 物资编码*/
    private String matNum ;

    /** 物资名称*/
    private String matName ;

    /** 物资分类ID*/
    private String matTypeId ;

    /** 物资分类名称*/
    private String matTypeName ;

    /** 物资规格*/
    private String matSpec ;

    /** 物资型号*/
    private String matModel ;

    /** 计量单位*/
    private String unit ;

    /** 计量单位*/
    private String unitName ;

    /** 单价*/
    private Double price = new Double(0.00);

    /** 库存数量*/
    private Double stockNum = new Double(0);

    /** 预约数量*/
    private Double reserveNum = new Double(0);

    /**图片地址**/
    private String pictureUri;

    /**物资大类**/
    private String matTypeCode;

    /**包装系数**/

    private String packfactor;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matNum");
        eiColumn.setDescName("物资编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matName");
        eiColumn.setDescName("物资名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeId");
        eiColumn.setDescName("物资分类ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeName");
        eiColumn.setDescName("物资分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matSpec");
        eiColumn.setDescName("物资规格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matModel");
        eiColumn.setDescName("物资型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setDescName("计量单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitName");
        eiColumn.setDescName("计量单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("price");
        eiColumn.setDescName("单价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stockNum");
        eiColumn.setDescName("库存数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reserveNum");
        eiColumn.setDescName("预约量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pictureUri");
        eiColumn.setDescName("图片地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("packfactor");
        eiColumn.setDescName("包装系数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeCode");
        eiColumn.setDescName("物资大类");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public RmDeptMatConfig() {
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
     * get the deptNum - 科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 科室名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the matNum - 物资编码
     * @return the matNum
     */
    public String getMatNum() {
        return this.matNum;
    }

    /**
     * set the matNum - 物资编码
     */
    public void setMatNum(String matNum) {
        this.matNum = matNum;
    }

    /**
     * get the matName - 物资名称
     * @return the matName
     */
    public String getMatName() {
        return this.matName;
    }

    /**
     * set the matName - 物资名称
     */
    public void setMatName(String matName) {
        this.matName = matName;
    }

    /**
     * get the matTypeId - 物资分类ID
     * @return the matTypeId
     */
    public String getMatTypeId() {
        return this.matTypeId;
    }

    /**
     * set the matTypeId - 物资分类ID
     */
    public void setMatTypeId(String matTypeId) {
        this.matTypeId = matTypeId;
    }

    /**
     * get the matTypeName - 物资分类名称
     * @return the matTypeName
     */
    public String getMatTypeName() {
        return this.matTypeName;
    }

    /**
     * set the matTypeName - 物资分类名称
     */
    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    /**
     * get the matSpec - 物资规格
     * @return the matSpec
     */
    public String getMatSpec() {
        return this.matSpec;
    }

    /**
     * set the matSpec - 物资规格
     */
    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
    }

    /**
     * get the matModel - 物资型号
     * @return the matModel
     */
    public String getMatModel() {
        return this.matModel;
    }

    /**
     * set the matModel - 物资型号
     */
    public void setMatModel(String matModel) {
        this.matModel = matModel;
    }

    /**
     * get the unit - 计量单位
     * @return the unit
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * set the unit - 计量单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * get the unitName - 计量单位名称
     * @return the unitName
     */
    public String getUnitName() {
        return this.unitName;
    }

    /**
     * set the unitName - 计量单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * get the price - 单价
     * @return the price
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * set the price - 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * get the stockNum - 库存数量
     * @return the stockNum
     */
    public Double getStockNum() {
        return stockNum;
    }

    /**
     * set the stockNum - 库存数量
     */
    public void setStockNum(Double stockNum) {
        this.stockNum = stockNum;
    }

    /**
     * get the reserveNum - 预约数量
     * @return the reserveNum
     */
    public Double getReserveNum() {
        return reserveNum;
    }

    /**
     * set the reserveNum - 预约数量
     */
    public void setReserveNum(Double reserveNum) {
        this.reserveNum = reserveNum;
    }

    /**
     * get the pictureUri - 图片地址
     * @return the pictureUri
     */
    public String getPictureUri() { return pictureUri; }

    /**
     * set the pictureUri - 图片地址
     */
    public void setPictureUri(String pictureUri) { this.pictureUri = pictureUri; }


    public String getPackfactor() {
        return packfactor;
    }

    public void setPackfactor(String packfactor) {
        this.packfactor = packfactor;
    }

    public String getMatTypeCode() {
        return matTypeCode;
    }

    public void setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(RmUtils.toString(map.get("id"), id));
        setDeptNum(RmUtils.toString(map.get("deptNum"), deptNum));
        setDeptName(RmUtils.toString(map.get("deptName"), deptName));
        setMatNum(RmUtils.toString(map.get("matNum"), matNum));
        setMatName(RmUtils.toString(map.get("matName"), matName));
        setMatTypeId(RmUtils.toString(map.get("matTypeId"), matTypeId));
        setMatTypeName(RmUtils.toString(map.get("matTypeName"), matTypeName));
        setMatSpec(RmUtils.toString(map.get("matSpec"), matSpec));
        setMatModel(RmUtils.toString(map.get("matModel"), matModel));
        setUnit(RmUtils.toString(map.get("unit"), unit));
        setUnitName(RmUtils.toString(map.get("unitName"), unitName));
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
        setPictureUri(RmUtils.toString(map.get("pictureUri"), pictureUri));
        setPackfactor(RmUtils.toString(map.get("packfactor"),packfactor));
        setMatTypeCode(RmUtils.toString(map.get("matTypeCode"), matTypeCode));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matTypeId",StringUtils.toString(matTypeId, eiMetadata.getMeta("matTypeId")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("matModel",StringUtils.toString(matModel, eiMetadata.getMeta("matModel")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
        map.put("stockNum",StringUtils.toString(stockNum, eiMetadata.getMeta("stockNum")));
        map.put("reserveNum",StringUtils.toString(reserveNum, eiMetadata.getMeta("reserveNum")));
        map.put("pictureUri",StringUtils.toString(pictureUri, eiMetadata.getMeta("pictureUri")));
        map.put("packfactor",StringUtils.toString(packfactor,eiMetadata.getMeta("packfactor")));
        map.put("matTypeCode",StringUtils.toString(matTypeCode,eiMetadata.getMeta("matTypeCode")));
        return map;
    }
}
