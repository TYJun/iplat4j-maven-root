package com.baosight.wilp.fa.utils;
/**
 * 固定资产枚举类
 * @author zhaowei
 * @date 2022/8/25 19:00
 * @version V5.0.0
 */
public enum FixedAssetsEum {

	// 固资类别
	LB("固定资产类别","assettype","LB"),
	DA("固定资产档案","","DA"),
	BG("固定资产变更","","BG"),
	CF("固定资产拆分","","CF"),
	DB("固定资产调拨","","DB"),
	XZ("固定资产闲置","","XZ"),
	BS("固定资产报损","","BS"),
	BF("固定资产报废","","BF"),
	SH("固定资产上会","","SH"),
	PD("固定资产盘点","inventory","PD"),
	PDZX("固定资产盘点子项","inventoryDetail","PDZX"),
	common("科室资产管理员","","FA_COMMON"),
	user("科室负责人","","FA_USER"),
	guard("护理单元护士长","","FA_GUARD"),
	valuer("鉴定科","","FA_VALUER"),
	function("职能科","","FA_FUNCTION"),
	member("资产管理科下属","","FA_MEMBER"),
	leader("资产管理科","","FA_LEADER"),
	inventory("资产盘点员","","FA_INVENTORY");

	// 成员变量
	private String description;
	private String fullName;
	private String acronym;

	// 构造方法
	FixedAssetsEum(String description, String fullName, String acronym) {
		this.description = description;
		this.fullName = fullName;
		this.acronym = acronym;
	}

	// get方法
	public String getDescription() {
		return description;
	}

	public String getFullName() {
		return fullName;
	}

	public String getAcronym() {
		return acronym;
	}

}

