/**
* Generate time : 2021-05-28 14:14:46
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.im.jz.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiDevicePacket
* 
*/
public class ImDevicePacket extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String packetId = " ";		
    private String deviceId = " ";		
    private String packetCode = " ";		
    private String packetName = " ";		
    private String contentClass = " ";		
    private String memo = " ";		
    private Timestamp createtime ;		
    private String createman = " ";		
    private String createip = " ";		
    private Timestamp modifytime ;		
    private String modifyman = " ";		
    private String modifyip = " ";		
    private String status = " ";	
    private String machineName = " ";	
    private String fixedPlace = " ";	
    private String machineCode = " ";	
    private String paramKey = " ";	
    private String paramName = " ";	
    private String classifyName = " ";	
    private String machineId = " ";	
    private String models = " ";	
    

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("packetCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("packetName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contentClass");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createip");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifytime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyip");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("machineName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("fixedPlace");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("machineCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("paramKey");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("paramName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("classifyName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("machineId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("models");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("packetId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("deviceId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ImDevicePacket() {
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
     * get the packetCode 
     * @return the packetCode
     */
    public String getPacketCode() {
        return this.packetCode;
    }

    /**
     * set the packetCode 
     */
    public void setPacketCode(String packetCode) {
        this.packetCode = packetCode;
    }

    /**
     * get the packetName 
     * @return the packetName
     */
    public String getPacketName() {
        return this.packetName;
    }

    /**
     * set the packetName 
     */
    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    /**
     * get the contentClass 
     * @return the contentClass
     */
    public String getContentClass() {
        return this.contentClass;
    }

    /**
     * set the contentClass 
     */
    public void setContentClass(String contentClass) {
        this.contentClass = contentClass;
    }

    /**
     * get the memo 
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo 
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the createtime 
     * @return the createtime
     */
    public Timestamp getCreatetime() {
        return this.createtime;
    }

    /**
     * set the createtime 
     */
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    /**
     * get the createman 
     * @return the createman
     */
    public String getCreateman() {
        return this.createman;
    }

    /**
     * set the createman 
     */
    public void setCreateman(String createman) {
        this.createman = createman;
    }

    /**
     * get the createip 
     * @return the createip
     */
    public String getCreateip() {
        return this.createip;
    }

    /**
     * set the createip 
     */
    public void setCreateip(String createip) {
        this.createip = createip;
    }

    /**
     * get the modifytime 
     * @return the modifytime
     */
    public Timestamp getModifytime() {
        return this.modifytime;
    }

    /**
     * set the modifytime 
     */
    public void setModifytime(Timestamp modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * get the modifyman 
     * @return the modifyman
     */
    public String getModifyman() {
        return this.modifyman;
    }

    /**
     * set the modifyman 
     */
    public void setModifyman(String modifyman) {
        this.modifyman = modifyman;
    }

    /**
     * get the modifyip 
     * @return the modifyip
     */
    public String getModifyip() {
        return this.modifyip;
    }

    /**
     * set the modifyip 
     */
    public void setModifyip(String modifyip) {
        this.modifyip = modifyip;
    }

    /**
     * get the status 
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getFixedPlace() {
        return fixedPlace;
    }

    public void setFixedPlace(String fixedPlace) {
        this.fixedPlace = fixedPlace;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
    
    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }
    
    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }
    
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setPacketCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("packetCode")), packetCode));
        setPacketName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("packetName")), packetName));
        setContentClass(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contentClass")), contentClass));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setCreatetime(DateUtils.toTimestamp(StringUtils.toString(map.get("createtime"))));
        setCreateman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createman")), createman));
        setCreateip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createip")), createip));
        setModifytime(DateUtils.toTimestamp(StringUtils.toString(map.get("modifytime"))));
        setModifyman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyman")), modifyman));
        setModifyip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyip")), modifyip));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setMachineName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineName")), machineName));
        setFixedPlace(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fixedPlace")), fixedPlace));
        setMachineCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineCode")), machineCode));
        setParamKey(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramKey")), paramKey));
        setParamName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramName")), paramName));
        setClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyName")), classifyName));
        setMachineId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineId")), machineId));
        setModels(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("models")), models));
        setPacketId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("packetId")), packetId));
        setDeviceId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceId")), deviceId));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("packetCode",StringUtils.toString(packetCode, eiMetadata.getMeta("packetCode")));
        map.put("packetName",StringUtils.toString(packetName, eiMetadata.getMeta("packetName")));
        map.put("contentClass",StringUtils.toString(contentClass, eiMetadata.getMeta("contentClass")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("createtime",StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        map.put("createman",StringUtils.toString(createman, eiMetadata.getMeta("createman")));
        map.put("createip",StringUtils.toString(createip, eiMetadata.getMeta("createip")));
        map.put("modifytime",StringUtils.toString(modifytime, eiMetadata.getMeta("modifytime")));
        map.put("modifyman",StringUtils.toString(modifyman, eiMetadata.getMeta("modifyman")));
        map.put("modifyip",StringUtils.toString(modifyip, eiMetadata.getMeta("modifyip")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("machineName",StringUtils.toString(machineName, eiMetadata.getMeta("machineName")));
        map.put("fixedPlace",StringUtils.toString(fixedPlace, eiMetadata.getMeta("fixedPlace")));
        map.put("machineCode",StringUtils.toString(machineCode, eiMetadata.getMeta("machineCode")));
        map.put("paramKey",StringUtils.toString(paramKey, eiMetadata.getMeta("paramKey")));
        map.put("paramName",StringUtils.toString(paramName, eiMetadata.getMeta("paramName")));
        map.put("classifyName",StringUtils.toString(classifyName, eiMetadata.getMeta("classifyName")));
        map.put("machineId",StringUtils.toString(machineId, eiMetadata.getMeta("machineId")));
        map.put("models",StringUtils.toString(models, eiMetadata.getMeta("models")));
        map.put("packetId",StringUtils.toString(packetId, eiMetadata.getMeta("packetId")));
        map.put("deviceId",StringUtils.toString(deviceId, eiMetadata.getMeta("deviceId")));
        return map;
    }
}