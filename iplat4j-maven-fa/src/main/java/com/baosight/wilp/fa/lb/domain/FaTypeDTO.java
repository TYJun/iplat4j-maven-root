package com.baosight.wilp.fa.lb.domain;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年11月21日 11:33
 */
public class FaTypeDTO {
	private String id;
	/** 类别编码 */
	private String typeCode;
	/** 类别名称 */
	private String parentId;
	/** 编码规则 */
	private String codeRule;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCodeRule() {
		return codeRule;
	}

	public void setCodeRule(String codeRule) {
		this.codeRule = codeRule;
	}
}
