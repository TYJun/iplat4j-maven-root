/**
* Generate time : 2022-02-14 16:34:48
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.hs.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsChangeHistory
* 
*/
public class DormsChangeHistory extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String drmId = " ";		/* 宿舍人员绑定关系表ID*/
    private String roomId = " ";		/* 换宿前的宿舍id*/
    private String bedNo = "";		/* 床位号*/
    private String actualInDate = " ";		/* 实际入住时间*/
    private String changeRoomNote = " ";		/* 申请换宿时备注*/
    private String applyChangeDate = " ";		/* 申请换宿时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("drmId");
        eiColumn.setDescName("宿舍人员绑定关系表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roomId");
        eiColumn.setDescName("换宿前的宿舍id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName("床位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actualInDate");
        eiColumn.setDescName("实际入住时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("changeRoomNote");
        eiColumn.setDescName("申请换宿时备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyChangeDate");
        eiColumn.setDescName("申请换宿时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsChangeHistory() {
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
     * get the drmId - 宿舍人员绑定关系表ID
     * @return the drmId
     */
    public String getDrmId() {
        return this.drmId;
    }

    /**
     * set the drmId - 宿舍人员绑定关系表ID
     */
    public void setDrmId(String drmId) {
        this.drmId = drmId;
    }

    /**
     * get the roomId - 换宿前的宿舍id
     * @return the roomId
     */
    public String getRoomId() {
        return this.roomId;
    }

    /**
     * set the roomId - 换宿前的宿舍id
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * get the bedNo - 床位号
     * @return the bedNo
     */
    public String getBedNo() {
        return this.bedNo;
    }

    /**
     * set the bedNo - 床位号
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    /**
     * get the actualInDate - 实际入住时间
     * @return the actualInDate
     */
    public String getActualInDate() {
        return this.actualInDate;
    }

    /**
     * set the actualInDate - 实际入住时间
     */
    public void setActualInDate(String actualInDate) {
        this.actualInDate = actualInDate;
    }

    /**
     * get the changeRoomNote - 申请换宿时备注
     * @return the changeRoomNote
     */
    public String getChangeRoomNote() {
        return this.changeRoomNote;
    }

    /**
     * set the changeRoomNote - 申请换宿时备注
     */
    public void setChangeRoomNote(String changeRoomNote) {
        this.changeRoomNote = changeRoomNote;
    }

    /**
     * get the applyChangeDate - 申请换宿时间
     * @return the applyChangeDate
     */
    public String getApplyChangeDate() {
        return this.applyChangeDate;
    }

    /**
     * set the applyChangeDate - 申请换宿时间
     */
    public void setApplyChangeDate(String applyChangeDate) {
        this.applyChangeDate = applyChangeDate;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setDrmId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("drmId")), drmId));
        setRoomId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roomId")), roomId));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setActualInDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualInDate")), actualInDate));
        setChangeRoomNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("changeRoomNote")), changeRoomNote));
        setApplyChangeDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyChangeDate")), applyChangeDate));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("drmId",StringUtils.toString(drmId, eiMetadata.getMeta("drmId")));
        map.put("roomId",StringUtils.toString(roomId, eiMetadata.getMeta("roomId")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("actualInDate",StringUtils.toString(actualInDate, eiMetadata.getMeta("actualInDate")));
        map.put("changeRoomNote",StringUtils.toString(changeRoomNote, eiMetadata.getMeta("changeRoomNote")));
        map.put("applyChangeDate",StringUtils.toString(applyChangeDate, eiMetadata.getMeta("applyChangeDate")));
        return map;
    }
}