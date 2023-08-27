/**
* Generate time : 2021-05-02 21:17:30
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.df.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ModuleBaseClassfy
* 
*/
public class ModuleBaseClassfy extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String moduleCode = " ";		/* 所属模块，DC-设备管理 XJ-巡检管理 BY-保养管理*/
    private String classifyCode = " ";		/* 分类CODE，业务主键，规则：根节点，模块CODE-ROOT;子节点，模块CODE-01/99(保留节点)-0001/9999(序列号)*/
    private String classifyName = " ";		/* 分类名称*/
    private String parentId = " ";		/* 父id*/
    private Integer level = new Integer(0);		/* 分类层级，0-根节点， 其他则是子节点所在的层次*/
    private Integer isLeaf = new Integer(0);		/* 0/1，否/是*/
    private String memo = " ";		/* 备注*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("moduleCode");
        eiColumn.setDescName("所属模块，DC-设备管理 XJ-巡检管理 BY-保养管理");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyCode");
        eiColumn.setDescName("分类CODE，业务主键，规则：根节点，模块CODE-ROOT;子节点，模块CODE-01/99(保留节点)-0001/9999(序列号)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyName");
        eiColumn.setDescName("分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName("父id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("level");
        eiColumn.setDescName("分类层级，0-根节点， 其他则是子节点所在的层次");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isLeaf");
        eiColumn.setDescName("0/1，否/是");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ModuleBaseClassfy() {
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
     * get the moduleCode - 所属模块，DC-设备管理 XJ-巡检管理 BY-保养管理
     * @return the moduleCode
     */
    public String getModuleCode() {
        return this.moduleCode;
    }

    /**
     * set the moduleCode - 所属模块，DC-设备管理 XJ-巡检管理 BY-保养管理
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * get the classifyCode - 分类CODE，业务主键，规则：根节点，模块CODE-ROOT;子节点，模块CODE-01/99(保留节点)-0001/9999(序列号)
     * @return the classifyCode
     */
    public String getClassifyCode() {
        return this.classifyCode;
    }

    /**
     * set the classifyCode - 分类CODE，业务主键，规则：根节点，模块CODE-ROOT;子节点，模块CODE-01/99(保留节点)-0001/9999(序列号)
     */
    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    /**
     * get the classifyName - 分类名称
     * @return the classifyName
     */
    public String getClassifyName() {
        return this.classifyName;
    }

    /**
     * set the classifyName - 分类名称
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * get the parentId - 父id
     * @return the parentId
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * set the parentId - 父id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * get the level - 分类层级，0-根节点， 其他则是子节点所在的层次
     * @return the level
     */
    public Integer getLevel() {
        return this.level;
    }

    /**
     * set the level - 分类层级，0-根节点， 其他则是子节点所在的层次
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * get the isLeaf - 0/1，否/是
     * @return the isLeaf
     */
    public Integer getIsLeaf() {
        return this.isLeaf;
    }

    /**
     * set the isLeaf - 0/1，否/是
     */
    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * get the memo - 备注
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo - 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setModuleCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleCode")), moduleCode));
        setClassifyCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyCode")), classifyCode));
        setClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyName")), classifyName));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setLevel(NumberUtils.toInteger(StringUtils.toString(map.get("level")), level));
        setIsLeaf(NumberUtils.toInteger(StringUtils.toString(map.get("isLeaf")), isLeaf));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("moduleCode",StringUtils.toString(moduleCode, eiMetadata.getMeta("moduleCode")));
        map.put("classifyCode",StringUtils.toString(classifyCode, eiMetadata.getMeta("classifyCode")));
        map.put("classifyName",StringUtils.toString(classifyName, eiMetadata.getMeta("classifyName")));
        map.put("parentId",StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("level",StringUtils.toString(level, eiMetadata.getMeta("level")));
        map.put("isLeaf",StringUtils.toString(isLeaf, eiMetadata.getMeta("isLeaf")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        return map;
    }
}