package com.baosight.wilp.im.xm.domain;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 安全巡模块配置实体类
 * 
 * @Title: ImConfig.java
 * @ClassName: ImConfig
 * @Package：com.baosight.wilp.im.xm.domain
 * @author: zhangjiaqian
 * @date: 2021年9月16日 下午2:03:02
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ImConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;


    private String id = " ";        /* 主键*/
    private String typeName = ""; /* 数据项配置名*/
    private String typeCode = ""; /* 数据项配置编码*/



    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeName");
        eiColumn.setDescName("配置名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeCode");
        eiColumn.setDescName("配置名");
        eiMetadata.addMeta(eiColumn);

    }
    /**
     * the constructor
     */
    public ImConfig() {
        initMetaData();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }


    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeName")), typeName));
        setTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeCode")), typeCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("typeName",StringUtils.toString(typeName, eiMetadata.getMeta("typeName")));
        map.put("typeCode",StringUtils.toString(typeCode, eiMetadata.getMeta("typeCode")));
        return map;
    }


}
