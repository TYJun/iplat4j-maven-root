package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.mp.common.MpUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 合同关附件实体
 * @ClassName: MpContractFile
 * @Package com.baosight.wilp.mp.lj.domain
 * @date: 2022年10月11日 15:30
 */
public class MpContractFile extends DaoEPBase {

    /**
     * 主键
     **/
    private String id;

    /**
     * 合同Id
     **/
    private String contId;

    /**
     * 文件名称
     **/
    private String fileName;

    /**
     * 文件大小
     **/
    private String fileSize;

    /**
     * 说明
     **/
    private String remark;

    /**
     * 附件ID
     **/
    private String docId;

    /**
     * 创建人
     */
    private String recCreator;

    /**
     * 创建人姓名
     */
    private String recCreatorName;

    /**
     * 创建时间
     */
    private Date recCreateTime;
    /**
     * 创建时间
     */
    private String recCreateTimeStr;

    /**
     * 修改人
     */
    private String recRevisor;

    /**
     * 修改时间
     */
    private Date recReviseTime;

    /**
     * 账套
     */
    private String dataGroupCode;

    /**
     * 归档标记
     */
    private String archiveFlag;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;


        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contId");
        eiColumn.setDescName("合同ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("文件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setDescName("文件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("docId");
        eiColumn.setDescName("附件ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("附件说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorName");
        eiColumn.setDescName("创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTimeStr");
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
    }

    /**
     * the constructor
     */
    public MpContractFile() {
        initMetaData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContId() {
        return contId;
    }

    public void setContId(String contId) {
        this.contId = contId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getRecCreator() {
        return recCreator;
    }

    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    public String getRecCreatorName() {
        return recCreatorName;
    }

    public void setRecCreatorName(String recCreatorName) {
        this.recCreatorName = recCreatorName;
    }

    public Date getRecCreateTime() {
        return recCreateTime;
    }

    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    public String getRecRevisor() {
        return recRevisor;
    }

    public String getRecCreateTimeStr() {
        return recCreateTimeStr;
    }

    public void setRecCreateTimeStr(String recCreateTimeStr) {
        this.recCreateTimeStr = recCreateTimeStr;
        if(StringUtils.isNotEmpty(this.recCreateTimeStr)) {
            this.setRecCreateTime(DateUtils.toDateTime(this.recCreateTimeStr));
        }
    }

    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    public Date getRecReviseTime() {
        return recReviseTime;
    }

    public void setRecReviseTime(Date recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    public String getDataGroupCode() {
        return dataGroupCode;
    }

    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    public String getArchiveFlag() {
        return archiveFlag;
    }

    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(MpUtils.toString(map.get("id"), id));
        setContId(MpUtils.toString(map.get("contId"), contId));
        setFileName(MpUtils.toString(map.get("fileName"), fileName));
        setFileSize(MpUtils.toString(map.get("fileSize"),fileSize));
        setDocId(MpUtils.toString(map.get("docId"), docId));
        setRemark(MpUtils.toString(map.get("remark"), remark));
        setRecCreator(MpUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(MpUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(MpUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecCreateTimeStr(MpUtils.toString(map.get("recCreateTimeStr"), recCreateTimeStr));
        setRecRevisor(MpUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(MpUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(MpUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(MpUtils.toString(map.get("archiveFlag"), archiveFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("contId", StringUtils.toString(contId, eiMetadata.getMeta("contId")));
        map.put("fileName", StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("fileSize", StringUtils.toString(fileSize, eiMetadata.getMeta("fileSize")));
        map.put("docId", StringUtils.toString(docId, eiMetadata.getMeta("docId")));
        map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreateTimeStr",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }
}
