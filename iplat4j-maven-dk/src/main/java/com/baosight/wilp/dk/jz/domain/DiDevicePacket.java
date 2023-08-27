package com.baosight.wilp.dk.jz.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 设备包实体类
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: DiDevicePacket.java
 * @ClassName: DiDevicePacket
 * @Package：com.baosight.wilp.dk.jz.domain
 * @author: LENOVO
 * @date: 2021年10月25日 下午2:11:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class DiDevicePacket extends DaoEPBase{

	//序列id
	private static final long serialVersionUID = 1L;

    private String id = " ";		         //设备包id
    private String packetId = " ";		     //设备包id
    private String deviceId = " ";		     //设备id
    private String packetCode = " ";		 //包编码
    private String packetName = " ";		 //包名
    private String contentClass = " ";		 //class
    private String memo = " ";		         //备注
    private Timestamp createtime ;		     //创建时间
    private String createman = " ";		     //创建人
    private String createip = " ";		     //创建IP
    private Timestamp modifytime ;		     //修改时间
    private String modifyman = " ";		     //修改人
    private String modifyip = " ";		     //修改地址
    private String status = " ";	         //状态
    private String machineName = " ";	     //设备名
    private String fixedPlace = " ";	     //地址
    private String machineCode = " ";	     //设备编码
    private String paramKey = " ";	         //参数剑
    private String paramName = " ";	         //参数名字
    private String classifyName = " ";	     //分类名字
    private String machineId = " ";	         //设备id
    private String models = " ";	         //分类型
    

    /**
     * initialize the metadata
     */
    //初始化源数据名称
    public void initMetaData() {
        EiColumn eiColumn;
        //设备包id
        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //设备包编码
        eiColumn = new EiColumn("packetCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //设备名字
        eiColumn = new EiColumn("packetName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //备注类
        eiColumn = new EiColumn("contentClass");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //备注
        eiColumn = new EiColumn("memo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //创建时间
        eiColumn = new EiColumn("createtime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //创建人
        eiColumn = new EiColumn("createman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //创建地址
        eiColumn = new EiColumn("createip");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //修改时间
        eiColumn = new EiColumn("modifytime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //修改人
        eiColumn = new EiColumn("modifyman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //修改地址
        eiColumn = new EiColumn("modifyip");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //状态
        eiColumn = new EiColumn("status");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //设备名字
        eiColumn = new EiColumn("machineName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //地址
        eiColumn = new EiColumn("fixedPlace");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //设备编码
        eiColumn = new EiColumn("machineCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //参数key
        eiColumn = new EiColumn("paramKey");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //参数名
        eiColumn = new EiColumn("paramName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //分类名
        eiColumn = new EiColumn("classifyName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //设备id
        eiColumn = new EiColumn("machineId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //模型
        eiColumn = new EiColumn("models");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //设备包id
        eiColumn = new EiColumn("packetId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        //设备id
        eiColumn = new EiColumn("deviceId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DiDevicePacket() {
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

    /**
     * set the machineName 
     */
    public String getMachineName() {
        return machineName;
    }
    
    /**
     * get the machineName 
     */
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    /**
     * get the fixedPlace 
     */
    public String getFixedPlace() {
        return fixedPlace;
    }

    /**
     * set the fixedPlace 
     */
    public void setFixedPlace(String fixedPlace) {
        this.fixedPlace = fixedPlace;
    }

    /**
     * get the machineCode 
     */
    public String getMachineCode() {
        return machineCode;
    }

    /**
     * set the machineCode 
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    /**
     * get the paramKey 
     */
    public String getParamKey() {
        return paramKey;
    }

    /**
     * set the paramKey 
     */
    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    /**
     * get the paramName 
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * set the paramName 
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * get the classifyName 
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * set the classifyName 
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
    
    /**
     * get the machineId 
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * set the machineId 
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * get the models 
     */
    public String getModels() {
        return models;
    }

    /**
     * set the models 
     */
    public void setModels(String models) {
        this.models = models;
    }
    
    /**
     * get the packetId 
     */
    public String getPacketId() {
        return packetId;
    }

    /**
     * set the packetId 
     */
    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }
    
    /**
     * get the deviceId 
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * set the deviceId 
     */
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
