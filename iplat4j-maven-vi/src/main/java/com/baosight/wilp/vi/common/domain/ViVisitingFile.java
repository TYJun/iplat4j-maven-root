/**
* Generate time : 2023-07-17 16:53:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.vi.common.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ViVisitingFile
* 
*/
public class ViVisitingFile extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer visitingId = new Integer(0);		/* 来访记录ID vi_visting_info(id)*/
    private String fileId = "";		/* 附加文件ID vi_attach_file(id)*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("visitingId");
        eiColumn.setDescName("来访记录ID vi_visting_info(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileId");
        eiColumn.setDescName("附加文件ID vi_attach_file(id)");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ViVisitingFile() {
        initMetaData();
    }

    /**
     * get the visitingId - 来访记录ID vi_visting_info(id)
     * @return the visitingId
     */
    public Integer getVisitingId() {
        return this.visitingId;
    }

    /**
     * set the visitingId - 来访记录ID vi_visting_info(id)
     */
    public void setVisitingId(Integer visitingId) {
        this.visitingId = visitingId;
    }

    /**
     * get the fileId - 附加文件ID vi_attach_file(id)
     * @return the fileId
     */
    public String getFileId() {
        return this.fileId;
    }

    /**
     * set the fileId - 附加文件ID vi_attach_file(id)
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setVisitingId(NumberUtils.toInteger(StringUtils.toString(map.get("visitingId")), visitingId));
        setFileId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileId")), fileId));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("visitingId",StringUtils.toString(visitingId, eiMetadata.getMeta("visitingId")));
        map.put("fileId",StringUtils.toString(fileId, eiMetadata.getMeta("fileId")));
        return map;
    }
}