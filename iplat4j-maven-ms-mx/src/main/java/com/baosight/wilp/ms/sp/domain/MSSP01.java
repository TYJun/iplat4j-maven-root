package com.baosight.wilp.ms.sp.domain;
import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liye
 * @Date 2022/4/2 11:20
 * @Version 1.0
 */
public class MSSP01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 父节节点编号
     */
    private String parentId;

    /**
     * 视频编号
     */
    private String monitorCode;

    /**
     * 视频名称
     */
    private String monitorName;

    /**
     * url
     */
    private String url;


    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName("父节节点编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorCode");
        eiColumn.setDescName("分类编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorName");
        eiColumn.setDescName("分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("url");
        eiColumn.setDescName("url地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyType");
        eiColumn.setDescName("树类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifySort");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

    }

    public MSSP01() {
        initMetaData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}

    public String getMonitorCode() {return monitorCode;}

    public void setMonitorCode(String monitorCode) {this.monitorCode = monitorCode;}

    public String getMonitorName() {return monitorName;}

    public void setMonitorName(String monitorName) {this.monitorName = monitorName;}

    @Override
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setMonitorCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorCode")), monitorCode));
        setMonitorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorName")), monitorName));
        setUrl(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("url")), url));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("parentId", StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("monitorCode", StringUtils.toString(monitorCode, eiMetadata.getMeta("monitorCode")));
        map.put("monitorName", StringUtils.toString(monitorName, eiMetadata.getMeta("monitorName")));
        map.put("url", StringUtils.toString(url, eiMetadata.getMeta("url")));
        return map;
    }

}