/**
* Generate time : 2021-06-08 11:07:51
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ac.pe.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * AcDictionaries
 * 
 */
public class AcDictionaries extends DaoEPBase {

	private static final long serialVersionUID = 1L;

	private String id = " "; /* 主键 */
	private String typegroupcode = " "; /* 分类编码 */
	private String typename = " "; /* 字典名称 */
	private String typecode = " "; /* 字典编码 */

	/**
	 * initialize the metadata
	 */
	public void initMetaData() {
		EiColumn eiColumn;

		eiColumn = new EiColumn("id");
		eiColumn.setPrimaryKey(true);
		eiColumn.setDescName("主键");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("typegroupcode");
		eiColumn.setDescName("分类编码");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("typename");
		eiColumn.setDescName("字典名称");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("typecode");
		eiColumn.setDescName("字典编码");
		eiMetadata.addMeta(eiColumn);

	}

	/**
	 * the constructor
	 */
	public AcDictionaries() {
		initMetaData();
	}

	/**
	 * get the id - 主键
	 * 
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
	 * get the typegroupcode - 分类编码
	 * 
	 * @return the typegroupcode
	 */
	public String getTypegroupcode() {
		return this.typegroupcode;
	}

	/**
	 * set the typegroupcode - 分类编码
	 */
	public void setTypegroupcode(String typegroupcode) {
		this.typegroupcode = typegroupcode;
	}

	/**
	 * get the typename - 字典名称
	 * 
	 * @return the typename
	 */
	public String getTypename() {
		return this.typename;
	}

	/**
	 * set the typename - 字典名称
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}

	/**
	 * get the typecode - 字典编码
	 * 
	 * @return the typecode
	 */
	public String getTypecode() {
		return this.typecode;
	}

	/**
	 * set the typecode - 字典编码
	 */
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	/**
	 * get the value from Map
	 */
	public void fromMap(Map map) {

		setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setTypegroupcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typegroupcode")), typegroupcode));
		setTypename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typename")), typename));
		setTypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typecode")), typecode));
	}

	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
		map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
		map.put("typegroupcode", StringUtils.toString(typegroupcode, eiMetadata.getMeta("typegroupcode")));
		map.put("typename", StringUtils.toString(typename, eiMetadata.getMeta("typename")));
		map.put("typecode", StringUtils.toString(typecode, eiMetadata.getMeta("typecode")));
		return map;
	}
}