package com.baosight.wilp.si.pz.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.si.common.SiUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 仓库与物资大类关联配置实体类
 * @ClassName: SiConfigMatRootType
 * @Package com.baosight.wilp.si.pz.domain
 * @date: 2023年07月24日 11:07
 */
public class SiConfigMatRootType extends DaoEPBase {

    /**主键**/
    private String id;

    /**仓库编码**/
    @NotBlank(message = "仓库编码不能为空")
    private String wareHouseNo;

    /**仓库名称**/
    @NotBlank(message = "仓库名称不能为空")
    private String wareHouseName;

    /**关联物资大类**/
    @NotBlank(message = "关联物资大类不能为空")
    private String relateMatType;

    /**关联物资大类名称**/
    @NotBlank(message = "关联物资大类名称不能为空")
    private String relateMatTypeName;

    /**可申领物资大类**/
    @NotBlank(message = "可申领物资大类不能为空")
    private String applyMatType;

    /**可申领物资大类名称**/
    @NotBlank(message = "可申领物资大类名称不能为空")
    private String applyMatTypeName;

    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseNo");
        eiColumn.setDescName("仓库编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseName");
        eiColumn.setDescName("仓库名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("relateMatType");
        eiColumn.setDescName("关联物资大类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("relateMatTypeName");
        eiColumn.setDescName("关联物资大类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyMatType");
        eiColumn.setDescName("可申领物资大类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyMatTypeName");
        eiColumn.setDescName("可申领物资大类名称");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public SiConfigMatRootType() {
        initMetaData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWareHouseNo() {
        return wareHouseNo;
    }

    public void setWareHouseNo(String wareHouseNo) {
        this.wareHouseNo = wareHouseNo;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getRelateMatType() {
        return relateMatType;
    }

    public void setRelateMatType(String relateMatType) {
        this.relateMatType = relateMatType;
    }

    public String getRelateMatTypeName() {
        return relateMatTypeName;
    }

    public void setRelateMatTypeName(String relateMatTypeName) {
        this.relateMatTypeName = relateMatTypeName;
    }

    public String getApplyMatType() {
        return applyMatType;
    }

    public void setApplyMatType(String applyMatType) {
        this.applyMatType = applyMatType;
    }

    public String getApplyMatTypeName() {
        return applyMatTypeName;
    }

    public void setApplyMatTypeName(String applyMatTypeName) {
        this.applyMatTypeName = applyMatTypeName;
    }

    @Override
    public void fromMap(Map map) {
        setId(SiUtils.isEmpty(map.get("id"), id));
        setWareHouseNo(SiUtils.isEmpty(map.get("wareHouseNo"), wareHouseNo));
        setWareHouseName(SiUtils.isEmpty(map.get("wareHouseName"), wareHouseName));
        setRelateMatType(SiUtils.isEmpty(map.get("relateMatType"), relateMatType));
        setRelateMatTypeName(SiUtils.isEmpty(map.get("relateMatTypeName"), relateMatTypeName));
        setApplyMatType(SiUtils.isEmpty(map.get("applyMatType"), applyMatType));
        setApplyMatTypeName(SiUtils.isEmpty(map.get("applyMatTypeName"), applyMatTypeName));
    }

    @Override
    public Map toMap() {
        Map map = new HashMap(8);
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("wareHouseNo", StringUtils.toString(wareHouseNo, eiMetadata.getMeta("wareHouseNo")));
        map.put("wareHouseName", StringUtils.toString(wareHouseName, eiMetadata.getMeta("wareHouseName")));
        map.put("relateMatType", StringUtils.toString(relateMatType, eiMetadata.getMeta("relateMatType")));
        map.put("relateMatTypeName", StringUtils.toString(relateMatTypeName, eiMetadata.getMeta("relateMatTypeName")));
        map.put("applyMatType", StringUtils.toString(applyMatType, eiMetadata.getMeta("applyMatType")));
        map.put("applyMatTypeName", StringUtils.toString(applyMatTypeName, eiMetadata.getMeta("applyMatTypeName")));
        return map;
    }
}
