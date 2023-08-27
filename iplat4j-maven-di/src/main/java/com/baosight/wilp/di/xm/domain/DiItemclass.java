/**
* Generate time : 2021-05-03 10:19:20
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.di.xm.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiItemclass
* 
*/
public class DiItemclass extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String classifyCode = " ";		/* 分类CODE，业务主键，规则：根节点，模块CODE-ROOT;子节点，模块CODE-01/99(保留节点)-0001/9999(序列号)*/
    private String classifyName = " ";		/* 分类名称*/
    private String parentId = " ";		/* 父id*/
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

        eiColumn = new EiColumn("classifyCode");
        eiColumn.setDescName("分类CODE，业务主键，规则：根节点，模块CODE-ROOT;子节点，模块CODE-01/99(保留节点)-0001/9999(序列号)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyName");
        eiColumn.setDescName("分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName("父id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DiItemclass() {
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
    @Override
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setClassifyCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyCode")), classifyCode));
        setClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyName")), classifyName));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("classifyCode",StringUtils.toString(classifyCode, eiMetadata.getMeta("classifyCode")));
        map.put("classifyName",StringUtils.toString(classifyName, eiMetadata.getMeta("classifyName")));
        map.put("parentId",StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        return map;
    }
}