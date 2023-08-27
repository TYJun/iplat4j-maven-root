package com.baosight.wilp.ms.dc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *用来存放树节点
 */
public class MSDC02 extends DaoEPBase {
    private static final long serialVersionUID = 1L;

    private String id =" ";                     //主键
    private String parent_id = " ";             //依赖主键
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parent_id");
        eiColumn.setDescName("依赖主键");
        eiMetadata.addMeta(eiColumn);

    }
    /**
     * the constructor
     */
    public MSDC02() {
        initMetaData();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setParent_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parent_id")), parent_id));
        }
    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("parent_id",StringUtils.toString(parent_id, eiMetadata.getMeta("parent_id")));
        return map;
    }
}
