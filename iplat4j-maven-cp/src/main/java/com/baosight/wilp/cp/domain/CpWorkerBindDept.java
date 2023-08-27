package com.baosight.wilp.cp.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2023年06月23日 22:30
 */
public class CpWorkerBindDept extends DaoEPBase{
	private static final long serialVersionUID = 1L;

	private int id;
	private String workNo;
	private String workName;
	private String deptNo;
	private String deptName;

	public void initMetaData() {
		EiColumn eiColumn;

		eiColumn = new EiColumn("id");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("workNo");
		eiColumn.setDescName("工号");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("workName");
		eiColumn.setDescName("工人");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deptNo");
		eiColumn.setDescName("科室");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("deptName");
		eiColumn.setDescName("科室");
		eiMetadata.addMeta(eiColumn);
	}

	public CpWorkerBindDept() {
		initMetaData();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void fromMap(Map map) {
		setId(Integer.valueOf((StringUtils.toString(map.get("id"))), id));
		setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
		setWorkName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workName")), workName));
		setDeptNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNo")), deptNo));
		setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
	}

	public Map toMap() {
		Map map = new HashMap();
		map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
		map.put("workName",StringUtils.toString(workName, eiMetadata.getMeta("workName")));
		map.put("deptNo",StringUtils.toString(deptNo, eiMetadata.getMeta("deptNo")));
		map.put("deptName", StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
		return map;
	}
}
