package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.utils.UUID;

import java.util.HashMap;
import java.util.Map;

/**
 * 合同附件履历
 * MpContractHistoryFile
 * @author fangjian
 */
public class MpContractHistoryFile extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**操作主表ID*/
    private String historyId ;

    /**合同ID*/
    private String contId ;

    /**附件名称*/
    private String fileName ;

    /**附件大小*/
    private String fileSize ;

    /**iplat库附件ID(下载使用)*/
    private String docId ;

    /**附件说明*/
    private String remark ;

    /**修改标记（0=新增，-1=删除）*/
    private Integer modifyType = new Integer(0);


    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("historyId");
        eiColumn.setDescName("操作主表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contId");
        eiColumn.setDescName("合同ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("附件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setDescName("附件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("docId");
        eiColumn.setDescName("iplat库附件ID(下载使用)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("附件说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyType");
        eiColumn.setDescName("修改标记（0=新增，-1=删除）");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public MpContractHistoryFile() {
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
     * get the historyId - 操作主表ID
     * @return the historyId
     */
    public String getHistoryId() {
        return this.historyId;
    }

    /**
     * set the historyId - 操作主表ID
     */
    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    /**
     * get the contId - 合同ID
     * @return the contId
     */
    public String getContId() {
        return this.contId;
    }

    /**
     * set the contId - 合同ID
     */
    public void setContId(String contId) {
        this.contId = contId;
    }

    /**
     * get the fileName - 附件名称
     * @return the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * set the fileName - 附件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * get the fileSize - 附件大小
     * @return the fileSize
     */
    public String getFileSize() {
        return this.fileSize;
    }

    /**
     * set the fileSize - 附件大小
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * get the docId - iplat库附件ID(下载使用)
     * @return the docId
     */
    public String getDocId() {
        return this.docId;
    }

    /**
     * set the docId - iplat库附件ID(下载使用)
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * get the remark - 附件说明
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 附件说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the modifyType - 修改标记（0=新增，-1=删除）
     * @return the modifyType
     */
    public Integer getModifyType() {
        return this.modifyType;
    }

    /**
     * set the modifyType - 修改标记（0=新增，-1=删除）
     */
    public void setModifyType(Integer modifyType) {
        this.modifyType = modifyType;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {
        setId(MpUtils.toString(map.get("id"), id));
        setHistoryId(MpUtils.toString(map.get("historyId"), historyId));
        setContId(MpUtils.toString(map.get("contId"), contId));
        setFileName(MpUtils.toString(map.get("fileName"), fileName));
        setFileSize(MpUtils.toString(map.get("fileSize"), fileSize));
        setDocId(MpUtils.toString(map.get("docId"), docId));
        setRemark(MpUtils.toString(map.get("remark"), remark));
        setModifyType(NumberUtils.toInteger(StringUtils.toString(map.get("remark")), modifyType));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(8);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("historyId",StringUtils.toString(historyId, eiMetadata.getMeta("historyId")));
        map.put("contId",StringUtils.toString(contId, eiMetadata.getMeta("contId")));
        map.put("fileName",StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("fileSize",StringUtils.toString(fileSize, eiMetadata.getMeta("fileSize")));
        map.put("docId",StringUtils.toString(docId, eiMetadata.getMeta("docId")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("modifyType",StringUtils.toString(modifyType, eiMetadata.getMeta("modifyType")));
        return map;
    }

    /**
     * 创建实例对象
     * @Title: newInstance
     * @param historyId historyId
     * @param modifyType modifyType
     * @return com.baosight.wilp.mp.lj.domain.MpContractHistoryFile
     * @throws
     **/
    public static MpContractHistoryFile newInstance(String historyId, Integer modifyType) {
        MpContractHistoryFile file = new MpContractHistoryFile();
        file.setHistoryId(historyId);
        file.setModifyType(modifyType);
        return file;
    }
}