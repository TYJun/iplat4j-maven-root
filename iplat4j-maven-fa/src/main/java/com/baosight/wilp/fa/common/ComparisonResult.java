package com.baosight.wilp.fa.common;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年12月07日 20:44
 */
public class ComparisonResult {
	private String id;
	private String batchId;
	private String faInfoId;
	private String key;
	private String label;
	private String previous;
	private String later;
	private String changeValue;
	private String time;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getLater() {
		return later;
	}

	public void setLater(String later) {
		this.later = later;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getFaInfoId() {
		return faInfoId;
	}

	public void setFaInfoId(String faInfoId) {
		this.faInfoId = faInfoId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getChangeValue() {
		return changeValue;
	}

	public void setChangeValue(String changeValue) {
		this.changeValue = changeValue;
	}
}
