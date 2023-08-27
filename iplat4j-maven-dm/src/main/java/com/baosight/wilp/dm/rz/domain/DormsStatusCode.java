/**
* Generate time : 2022-02-10 21:55:27
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.rz.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsStatusCode
* 
*/
public class DormsStatusCode extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String codeNum = " ";		/* 编码*/
    private String codeName = " ";		/* 编码名称/意义*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("codeNum");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName("编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("codeName");
        eiColumn.setDescName("编码名称/意义");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsStatusCode() {
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
     * get the codeNum - 编码
     * @return the codeNum
     */
    public String getCodeNum() {
        return this.codeNum;
    }

    /**
     * set the codeNum - 编码
     */
    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    /**
     * get the codeName - 编码名称/意义
     * @return the codeName
     */
    public String getCodeName() {
        return this.codeName;
    }

    /**
     * set the codeName - 编码名称/意义
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCodeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("codeNum")), codeNum));
        setCodeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("codeName")), codeName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("codeNum",StringUtils.toString(codeNum, eiMetadata.getMeta("codeNum")));
        map.put("codeName",StringUtils.toString(codeName, eiMetadata.getMeta("codeName")));
        return map;
    }
}