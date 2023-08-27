/**
* Generate time : 2021-03-03 9:06:05
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
* SHSK01
* sc_account_config
*/
public class SSBMStsk01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* UUID*/
    private String companyid = " ";		/* 单位编码*/
    private String companyname = " ";		/* 单位名称*/
    private String modulecode = " ";		/* 模块编码，如职工订餐、病患订餐*/
    private String modulename = " ";		/* 模块名称*/
    private String businesscode = " ";		/* 业务编码，如食堂编码*/
    private String businessname = " ";		/* 业务名称，如食堂名称*/
    private String partner = " ";		/* 合作者身份(PID)*/
    private String sellerId = " ";		
    private String checkKey = " ";		/* 安全校验码(Key)*/
    private String privateKey = " ";		/* 商户的私钥*/
    private String aliPublicKey = " ";		/* 支付宝的公钥*/
    private String creator = " ";		
    private String createname = " ";		
    private Timestamp createdate ;		
    private String updator = " ";		
    private String updatename = " ";		
    private Timestamp updatedate ;		
    private String remark = " ";		/* 备注信息*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("UUID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("companyid");
        eiColumn.setDescName("单位编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("companyname");
        eiColumn.setDescName("单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modulecode");
        eiColumn.setDescName("模块编码，如职工订餐、病患订餐");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modulename");
        eiColumn.setDescName("模块名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("businesscode");
        eiColumn.setDescName("业务编码，如食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("businessname");
        eiColumn.setDescName("业务名称，如食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("partner");
        eiColumn.setDescName("合作者身份(PID)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sellerId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkKey");
        eiColumn.setDescName("安全校验码(Key)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("privateKey");
        eiColumn.setDescName("商户的私钥");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("aliPublicKey");
        eiColumn.setDescName("支付宝的公钥");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdate");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatename");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedate");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注信息");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMStsk01() {
        initMetaData();
    }

    /**
     * get the id - UUID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - UUID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the companyid - 单位编码
     * @return the companyid
     */
    public String getCompanyid() {
        return this.companyid;
    }

    /**
     * set the companyid - 单位编码
     */
    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    /**
     * get the companyname - 单位名称
     * @return the companyname
     */
    public String getCompanyname() {
        return this.companyname;
    }

    /**
     * set the companyname - 单位名称
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    /**
     * get the modulecode - 模块编码，如职工订餐、病患订餐
     * @return the modulecode
     */
    public String getModulecode() {
        return this.modulecode;
    }

    /**
     * set the modulecode - 模块编码，如职工订餐、病患订餐
     */
    public void setModulecode(String modulecode) {
        this.modulecode = modulecode;
    }

    /**
     * get the modulename - 模块名称
     * @return the modulename
     */
    public String getModulename() {
        return this.modulename;
    }

    /**
     * set the modulename - 模块名称
     */
    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    /**
     * get the businesscode - 业务编码，如食堂编码
     * @return the businesscode
     */
    public String getBusinesscode() {
        return this.businesscode;
    }

    /**
     * set the businesscode - 业务编码，如食堂编码
     */
    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }

    /**
     * get the businessname - 业务名称，如食堂名称
     * @return the businessname
     */
    public String getBusinessname() {
        return this.businessname;
    }

    /**
     * set the businessname - 业务名称，如食堂名称
     */
    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    /**
     * get the partner - 合作者身份(PID)
     * @return the partner
     */
    public String getPartner() {
        return this.partner;
    }

    /**
     * set the partner - 合作者身份(PID)
     */
    public void setPartner(String partner) {
        this.partner = partner;
    }

    /**
     * get the sellerId 
     * @return the sellerId
     */
    public String getSellerId() {
        return this.sellerId;
    }

    /**
     * set the sellerId 
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * get the checkKey - 安全校验码(Key)
     * @return the checkKey
     */
    public String getCheckKey() {
        return this.checkKey;
    }

    /**
     * set the checkKey - 安全校验码(Key)
     */
    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }

    /**
     * get the privateKey - 商户的私钥
     * @return the privateKey
     */
    public String getPrivateKey() {
        return this.privateKey;
    }

    /**
     * set the privateKey - 商户的私钥
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * get the aliPublicKey - 支付宝的公钥
     * @return the aliPublicKey
     */
    public String getAliPublicKey() {
        return this.aliPublicKey;
    }

    /**
     * set the aliPublicKey - 支付宝的公钥
     */
    public void setAliPublicKey(String aliPublicKey) {
        this.aliPublicKey = aliPublicKey;
    }

    /**
     * get the creator 
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator 
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createname 
     * @return the createname
     */
    public String getCreatename() {
        return this.createname;
    }

    /**
     * set the createname 
     */
    public void setCreatename(String createname) {
        this.createname = createname;
    }

    /**
     * get the createdate 
     * @return the createdate
     */
    public Timestamp getCreatedate() {
        return this.createdate;
    }

    /**
     * set the createdate 
     */
    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    /**
     * get the updator 
     * @return the updator
     */
    public String getUpdator() {
        return this.updator;
    }

    /**
     * set the updator 
     */
    public void setUpdator(String updator) {
        this.updator = updator;
    }

    /**
     * get the updatename 
     * @return the updatename
     */
    public String getUpdatename() {
        return this.updatename;
    }

    /**
     * set the updatename 
     */
    public void setUpdatename(String updatename) {
        this.updatename = updatename;
    }

    /**
     * get the updatedate 
     * @return the updatedate
     */
    public Timestamp getUpdatedate() {
        return this.updatedate;
    }

    /**
     * set the updatedate 
     */
    public void setUpdatedate(Timestamp updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * get the remark - 备注信息
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCompanyid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("companyid")), companyid));
        setCompanyname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("companyname")), companyname));
        setModulecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modulecode")), modulecode));
        setModulename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modulename")), modulename));
        setBusinesscode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("businesscode")), businesscode));
        setBusinessname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("businessname")), businessname));
        setPartner(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("partner")), partner));
        setSellerId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sellerId")), sellerId));
        setCheckKey(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkKey")), checkKey));
        setPrivateKey(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("privateKey")), privateKey));
        setAliPublicKey(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("aliPublicKey")), aliPublicKey));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createname")), createname));
        setCreatedate(DateUtils.toTimestamp(StringUtils.toString(map.get("createdate"))));
        setUpdator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updator")), updator));
        setUpdatename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatename")), updatename));
        setUpdatedate(DateUtils.toTimestamp(StringUtils.toString(map.get("updatedate"))));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("companyid",StringUtils.toString(companyid, eiMetadata.getMeta("companyid")));
        map.put("companyname",StringUtils.toString(companyname, eiMetadata.getMeta("companyname")));
        map.put("modulecode",StringUtils.toString(modulecode, eiMetadata.getMeta("modulecode")));
        map.put("modulename",StringUtils.toString(modulename, eiMetadata.getMeta("modulename")));
        map.put("businesscode",StringUtils.toString(businesscode, eiMetadata.getMeta("businesscode")));
        map.put("businessname",StringUtils.toString(businessname, eiMetadata.getMeta("businessname")));
        map.put("partner",StringUtils.toString(partner, eiMetadata.getMeta("partner")));
        map.put("sellerId",StringUtils.toString(sellerId, eiMetadata.getMeta("sellerId")));
        map.put("checkKey",StringUtils.toString(checkKey, eiMetadata.getMeta("checkKey")));
        map.put("privateKey",StringUtils.toString(privateKey, eiMetadata.getMeta("privateKey")));
        map.put("aliPublicKey",StringUtils.toString(aliPublicKey, eiMetadata.getMeta("aliPublicKey")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createname",StringUtils.toString(createname, eiMetadata.getMeta("createname")));
        map.put("createdate",StringUtils.toString(createdate, eiMetadata.getMeta("createdate")));
        map.put("updator",StringUtils.toString(updator, eiMetadata.getMeta("updator")));
        map.put("updatename",StringUtils.toString(updatename, eiMetadata.getMeta("updatename")));
        map.put("updatedate",StringUtils.toString(updatedate, eiMetadata.getMeta("updatedate")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        return map;
    }
}