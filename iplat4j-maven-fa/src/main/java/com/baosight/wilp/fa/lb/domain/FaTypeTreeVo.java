package com.baosight.wilp.fa.lb.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年11月27日 17:13
 */
public class FaTypeTreeVo extends DaoEPBase {
	private String id;
	private String codeRule;
	private String parentId;
	private List<FaTypeTreeVo> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeRule() {
		return codeRule;
	}

	public void setCodeRule(String codeRule) {
		this.codeRule = codeRule;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public FaTypeTreeVo() {

	}

	public FaTypeTreeVo(String id, String codeRule, String parentId) {
		this.id = id;
		this.codeRule = codeRule;
		this.parentId = parentId;
	}

	public List<FaTypeTreeVo> getChildren() {
		return children;
	}

	public List<FaTypeTreeVo> setChildren(List<FaTypeTreeVo> children) {
		return this.children = children;
	}

	public static List<FaTypeTreeVo> makeTree(List<FaTypeTreeVo> faTypeTreeVoList, String parentId) {
		List<FaTypeTreeVo> children = faTypeTreeVoList.stream()
				.filter(x -> x.getParentId().equals(parentId)).collect(Collectors.toList());
		List<FaTypeTreeVo> successor = faTypeTreeVoList.stream()
				.filter(x -> !x.getParentId().equals(parentId)).collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(children)) {
			children.forEach(x -> {
				for (FaTypeTreeVo y : makeTree(successor, x.getId())) {
					x.getChildren().add(y);
				}
			});
		}
		return children;
	}

	public static List<FaTypeTreeVo> makeTreeByCode(List<FaTypeTreeVo> faTypeTreeVoList, String parentId) {
		List<FaTypeTreeVo> father = faTypeTreeVoList.stream()
				.filter(x -> x.getId().equals(parentId)).collect(Collectors.toList());
		List<FaTypeTreeVo> successor = faTypeTreeVoList.stream()
				.filter(x -> !x.getId().equals(parentId)).collect(Collectors.toList());
		father.forEach(x -> {
			System.out.println(father);
			if (!x.getParentId().equals("root")) {
				for (FaTypeTreeVo y : makeTreeByCode(successor, x.getParentId())) {
					x.getChildren().add(y);
				}
			}
		});
		return father;
	}

	public static List<FaTypeDTO> getParentObject(FaTypeDTO faTypeDTO, List<FaTypeDTO> allFaTypeDTOList, List<FaTypeDTO> returnFaTypeDTOList) {
		List<FaTypeDTO> faTypeDTOList = new ArrayList<>();
		if (faTypeDTO == null) {
			faTypeDTOList.add(faTypeDTO);
			return faTypeDTOList;
		}
		// 父节点为root代表是一级部门，直接返回
		if (faTypeDTO.getParentId().equals("root")) {
			returnFaTypeDTOList.add(faTypeDTO);
			return returnFaTypeDTOList;
		}
		List<FaTypeDTO> collect = allFaTypeDTOList.stream().filter(DTO ->
				DTO.getId().equals(faTypeDTO.getParentId())
		).collect(Collectors.toList());
		returnFaTypeDTOList.add(faTypeDTO);
		getParentObject(collect.get(0), allFaTypeDTOList, returnFaTypeDTOList);
		return returnFaTypeDTOList;
	}

	/**
	 * get the value from Map
	 */
	@Override
	public void fromMap(Map map) {
		setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
		setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
		setCodeRule(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("codeRule")), codeRule));
	}

	@Override
	public String toString() {
		return "FaTypeTreeVo{" +
				"id='" + id + '\'' +
				", codeRule='" + codeRule + '\'' +
				", parentId='" + parentId + '\'' +
				", children=" + children +
				'}';
	}
}
