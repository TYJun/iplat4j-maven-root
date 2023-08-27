/**
* Generate time : 2021-02-22 14:01:41
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.pm.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 项目归档结算表
 * 
 * @Title: TpmCalculate.java
 * @ClassName: TpmCalculate
 * @Package：com.baosight.wilp.pm.domain
 * @author: zhangjiaqian
 * @date: 2021年8月30日 下午6:21:00
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class TpmCalculate extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String documentsCode = " ";		/* 文档编码*/
    private String documentsName = " ";		/* 文档名称*/
    private String submitterName = " ";		/* 提交人*/
    private String submitterCode = " ";		/* 提交人工号*/
    private String submitter = " ";		/* 提交人工号*/
    private String recipientName = " ";		/* 审核人*/
    private String recipientCode = " ";		/* 审核人工号*/
    private String recipient = " ";		/* 审核人工号*/
    private Double charge = new Double(0);		/* 结算费用*/
    private String materialStatusCode = " ";		/* 决算材料*/
    private String statusCode = " ";		/* 施工确认*/
    private String dataSubmitterName = " ";		/* 资料提交人员*/
    private String dataSubmitterCode = " ";		/* 资料提交人员工号*/
    private String dataSubmitter = " ";		/* 资料提交人员*/
    private String dataRecipientName = " ";		/* 审计接受人*/
    private String dataRecipientCode = " ";		/* 审计接受人员工号*/
    private String dataRecipient = " ";		/* 审计接受人员*/
    private Double calCharge = new Double(0);		/* 审计确认金额*/
    private String dataCode = " ";		/* 提交资料编码*/
    private String dataName = " ";		/* 提交资料编码*/
    private String dataSubmitterCodeBack = " ";		/* 报告交付人员*/
    private String dataSubmitterCodeBackName = " ";		/* 报告交付人员*/
    private String dataRecipientCodeBack = " ";		/* 报告接收人员*/
    private String dataRecipientCodeBackName = " ";		/* 报告接收人员*/
    private String dataGroupCode = " ";		/* 账套*/
    private String docStatusCode = " ";		/* 归档文件状态编码*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("documentsCode");
        eiColumn.setDescName("文档编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("documentsName");
        eiColumn.setDescName("文档名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("submitterName");
        eiColumn.setDescName("提交人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("submitterCode");
        eiColumn.setDescName("提交人工号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("submitter");
        eiColumn.setDescName("提交人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recipientName");
        eiColumn.setDescName("审核人");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("recipientCode");
        eiColumn.setDescName("审核人工号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("recipient");
        eiColumn.setDescName("审核人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("charge");
        eiColumn.setDescName("结算费用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("materialStatusCode");
        eiColumn.setDescName("决算材料");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("施工确认");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataSubmitterName");
        eiColumn.setDescName("资料提交人员");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataSubmitterCode");
        eiColumn.setDescName("资料提交人员工号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataSubmitter");
        eiColumn.setDescName("资料提交人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataRecipientName");
        eiColumn.setDescName("审计接受人");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataRecipientCode");
        eiColumn.setDescName("审计接受人员工号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataRecipient");
        eiColumn.setDescName("审计接受人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("calCharge");
        eiColumn.setDescName("审计确认金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataCode");
        eiColumn.setDescName("提交资料编码");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataName");
        eiColumn.setDescName("提交资料名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataSubmitterCodeBack");
        eiColumn.setDescName("报告交付人员");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataSubmitterCodeBackName");
        eiColumn.setDescName("报告交付人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataRecipientCodeBack");
        eiColumn.setDescName("报告接收人员");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataRecipientCodeBackName");
        eiColumn.setDescName("报告接收人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("docStatusCode");
        eiColumn.setDescName("归档文件状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);
        
     


    }

    /**
     * the constructor
     */
    public TpmCalculate() {
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
     * get the documentsCode - 文档编码
     * @return the documentsCode
     */
    public String getDocumentsCode() {
        return this.documentsCode;
    }

    /**
     * set the documentsCode - 文档编码
     */
    public void setDocumentsCode(String documentsCode) {
        this.documentsCode = documentsCode;
    }

    /**
     * get the documentsName - 文档名称
     * @return the documentsName
     */
    public String getDocumentsName() {
        return this.documentsName;
    }

    /**
     * set the documentsName - 文档名称
     */
    public void setDocumentsName(String documentsName) {
        this.documentsName = documentsName;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    /**
     * get the submitterCode - 提交人工号
     * @return the submitterCode
     */
    public String getSubmitterCode() {
        return this.submitterCode;
    }

    /**
     * set the submitterCode - 提交人工号
     */
    public void setSubmitterCode(String submitterCode) {
        this.submitterCode = submitterCode;
    }

    public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    /**
     * get the recipientCode - 审核人工号
     * @return the recipientCode
     */
    public String getRecipientCode() {
        return this.recipientCode;
    }

    /**
     * set the recipientCode - 审核人工号
     */
    public void setRecipientCode(String recipientCode) {
        this.recipientCode = recipientCode;
    }

    /**
     * get the charge - 结算费用
     * @return the charge
     */
    public Double getCharge() {
        return this.charge;
    }

    /**
     * set the charge - 结算费用
     */
    public void setCharge(Double charge) {
        this.charge = charge;
    }

    /**
     * get the materialStatusCode - 决算材料
     * @return the materialStatusCode
     */
    public String getMaterialStatusCode() {
        return this.materialStatusCode;
    }

    /**
     * set the materialStatusCode - 决算材料
     */
    public void setMaterialStatusCode(String materialStatusCode) {
        this.materialStatusCode = materialStatusCode;
    }

    /**
     * get the statusCode - 施工确认
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 施工确认
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the dataSubmitterCode - 资料提交人员
     * @return the dataSubmitterCode
     */
    public String getDataSubmitterCode() {
        return this.dataSubmitterCode;
    }

    /**
     * set the dataSubmitterCode - 资料提交人员
     */
    public void setDataSubmitterCode(String dataSubmitterCode) {
        this.dataSubmitterCode = dataSubmitterCode;
    }

    /**
     * get the dataRecipientCode - 审计接受人员
     * @return the dataRecipientCode
     */
    public String getDataRecipientCode() {
        return this.dataRecipientCode;
    }

    /**
     * set the dataRecipientCode - 审计接受人员
     */
    public void setDataRecipientCode(String dataRecipientCode) {
        this.dataRecipientCode = dataRecipientCode;
    }

    /**
     * get the calCharge - 审计确认金额
     * @return the calCharge
     */
    public Double getCalCharge() {
        return this.calCharge;
    }

    /**
     * set the calCharge - 审计确认金额
     */
    public void setCalCharge(Double calCharge) {
        this.calCharge = calCharge;
    }

    /**
     * get the dataCode - 提交资料编码
     * @return the dataCode
     */
    public String getDataCode() {
        return this.dataCode;
    }

    /**
     * set the dataCode - 提交资料编码
     */
    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    /**
     * get the dataSubmitterCodeBack - 报告交付人员
     * @return the dataSubmitterCodeBack
     */
    public String getDataSubmitterCodeBack() {
        return this.dataSubmitterCodeBack;
    }

    /**
     * set the dataSubmitterCodeBack - 报告交付人员
     */
    public void setDataSubmitterCodeBack(String dataSubmitterCodeBack) {
        this.dataSubmitterCodeBack = dataSubmitterCodeBack;
    }

    /**
     * get the dataRecipientCodeBack - 报告接收人员
     * @return the dataRecipientCodeBack
     */
    public String getDataRecipientCodeBack() {
        return this.dataRecipientCodeBack;
    }

    /**
     * set the dataRecipientCodeBack - 报告接收人员
     */
    public void setDataRecipientCodeBack(String dataRecipientCodeBack) {
        this.dataRecipientCodeBack = dataRecipientCodeBack;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the datagroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the docStatusCode - 归档文件状态编码
     * @return the docStatusCode
     */
    public String getDocStatusCode() {
        return this.docStatusCode;
    }

    /**
     * set the docStatusCode - 归档文件状态编码
     */
    public void setDocStatusCode(String docStatusCode) {
        this.docStatusCode = docStatusCode;
    }

    /**
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the recCreator - 记录创建责任者
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 记录创建责任者
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 记录创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 记录创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 记录修改责任者
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 记录修改责任者
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 记录修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 记录修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getDataSubmitter() {
		return dataSubmitter;
	}

	public void setDataSubmitter(String dataSubmitter) {
		this.dataSubmitter = dataSubmitter;
	}

	public String getDataRecipient() {
		return dataRecipient;
	}

	public void setDataRecipient(String dataRecipient) {
		this.dataRecipient = dataRecipient;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataSubmitterCodeBackName() {
		return dataSubmitterCodeBackName;
	}

	public void setDataSubmitterCodeBackName(String dataSubmitterCodeBackName) {
		this.dataSubmitterCodeBackName = dataSubmitterCodeBackName;
	}

	public String getDataRecipientCodeBackName() {
		return dataRecipientCodeBackName;
	}

	public void setDataRecipientCodeBackName(String dataRecipientCodeBackName) {
		this.dataRecipientCodeBackName = dataRecipientCodeBackName;
	}

	public String getDataSubmitterName() {
        return dataSubmitterName;
    }

    public void setDataSubmitterName(String dataSubmitterName) {
        this.dataSubmitterName = dataSubmitterName;
    }

    public String getDataRecipientName() {
        return dataRecipientName;
    }

    public void setDataRecipientName(String dataRecipientName) {
        this.dataRecipientName = dataRecipientName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setDocumentsCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("documentsCode")), documentsCode));
        setDocumentsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("documentsName")), documentsName));
        setSubmitterName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("submitterName")), submitterName));
        setSubmitterCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("submitterCode")), submitterCode));
        setSubmitter(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("submitter")), submitter));
        setRecipientName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recipientName")), recipientName));
        setRecipientCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recipientCode")), recipientCode));
        setRecipient(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recipient")), recipient));
        setCharge(NumberUtils.toDouble(StringUtils.toString(map.get("charge")), charge));
        setMaterialStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("materialStatusCode")), materialStatusCode));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setDataSubmitterName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataSubmitterName")), dataSubmitterName));
        setDataSubmitterCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataSubmitterCode")), dataSubmitterCode));
        setDataSubmitter(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataSubmitter")), dataSubmitter));
        setDataRecipientName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataRecipientName")), dataRecipientName));
        setDataRecipientCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataRecipientCode")), dataRecipientCode));
        setDataRecipient(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataRecipient")), dataRecipient));
        setCalCharge(NumberUtils.toDouble(StringUtils.toString(map.get("calCharge")), calCharge));
        setDataCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataCode")), dataCode));
        setDataName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataName")), dataName));
        setDataSubmitterCodeBack(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataSubmitterCodeBack")), dataSubmitterCodeBack));
        setDataSubmitterCodeBackName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataSubmitterCodeBackName")), dataSubmitterCodeBackName));
        setDataRecipientCodeBack(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataRecipientCodeBack")), dataRecipientCodeBack));
        setDataRecipientCodeBackName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataRecipientCodeBackName")), dataRecipientCodeBackName));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setDocStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("docStatusCode")), docStatusCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("documentsCode",StringUtils.toString(documentsCode, eiMetadata.getMeta("documentsCode")));
        map.put("documentsName",StringUtils.toString(documentsName, eiMetadata.getMeta("documentsName")));
        map.put("submitterName",StringUtils.toString(submitterName, eiMetadata.getMeta("submitterName")));
        map.put("submitterCode",StringUtils.toString(submitterCode, eiMetadata.getMeta("submitterCode")));
        map.put("submitter",StringUtils.toString(submitter, eiMetadata.getMeta("submitter")));
        map.put("recipientName",StringUtils.toString(recipientName, eiMetadata.getMeta("recipientName")));
        map.put("recipientCode",StringUtils.toString(recipientCode, eiMetadata.getMeta("recipientCode")));
        map.put("recipient",StringUtils.toString(recipient, eiMetadata.getMeta("recipient")));
        map.put("charge",StringUtils.toString(charge, eiMetadata.getMeta("charge")));
        map.put("materialStatusCode",StringUtils.toString(materialStatusCode, eiMetadata.getMeta("materialStatusCode")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("dataSubmitterName",StringUtils.toString(dataSubmitterName, eiMetadata.getMeta("dataSubmitterName")));
        map.put("dataSubmitterCode",StringUtils.toString(dataSubmitterCode, eiMetadata.getMeta("dataSubmitterCode")));
        map.put("dataSubmitter",StringUtils.toString(dataSubmitter, eiMetadata.getMeta("dataSubmitter")));
        map.put("dataRecipientName",StringUtils.toString(dataRecipientName, eiMetadata.getMeta("dataRecipientName")));
        map.put("dataRecipientCode",StringUtils.toString(dataRecipientCode, eiMetadata.getMeta("dataRecipientCode")));
        map.put("dataRecipient",StringUtils.toString(dataRecipient, eiMetadata.getMeta("dataRecipient")));
        map.put("calCharge",StringUtils.toString(calCharge, eiMetadata.getMeta("calCharge")));
        map.put("dataCode",StringUtils.toString(dataCode, eiMetadata.getMeta("dataCode")));
        map.put("dataName",StringUtils.toString(dataName, eiMetadata.getMeta("dataName")));
        map.put("dataSubmitterCodeBack",StringUtils.toString(dataSubmitterCodeBack, eiMetadata.getMeta("dataSubmitterCodeBack")));
        map.put("dataSubmitterCodeBackName",StringUtils.toString(dataSubmitterCodeBackName, eiMetadata.getMeta("dataSubmitterCodeBackName")));
        map.put("dataRecipientCodeBack",StringUtils.toString(dataRecipientCodeBack, eiMetadata.getMeta("dataRecipientCodeBack")));
        map.put("dataRecipientCodeBackName",StringUtils.toString(dataRecipientCodeBackName, eiMetadata.getMeta("dataRecipientCodeBackName")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("docStatusCode",StringUtils.toString(docStatusCode, eiMetadata.getMeta("docStatusCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}