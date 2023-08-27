package com.baosight.wilp.pm.domain;

/**
 * 
 * 枚举配置类，配置sql查询sql名
 * 
 * @Title: RecevierEnum.java
 * @ClassName: RecevierEnum
 * @Package：com.baosight.wilp.pm.domain
 * @author: zhangjiaqian
 * @date: 2021年8月30日 下午6:10:21
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public enum RecevierEnum {
	
    
    /**
     * 查询项目负责人
     */
	PFZR("queryReceiverForPrincipal"),
	/**
	 * 查询项目联络人
	 */
	PLLR("queryReceiverForCons"),
	/**
	 * 查询执行人
	 */
	PZXR("queryReceiverForStaff"),
	/**
	 * 查询知会人
	 */
	PZHR("queryReceiverForKnow"),
	/**
	 * 查询督办人
	 */
	PDBR("queryReceiverForVia"),
	/**
	 * 查询验收人
	 */
	PYSR("queryReceiverForAccept"),
	/**
	 * 查询乙方提交人 
	 */
	YFTJR("queryReceiverForSubmitter"),
	/**
	 * 查询甲方收件人
	 */
	JFJSR("queryReceiverForRecipient"),
	/**
	 * 查询资料提交人
	 */
	ZLTJR("queryReceiverForDataSubmitter"),
	/**
	 * 查询审计接受人 
	 */
	SJJSR("queryReceiverForDataRecipient"),
	/**
	 * 查询报告交付人
	 */
	BGJFR("queryReceiverForDataSubmitterBack"),
	/**
	 * 查询报告接受人
	 */
	BGJSR("queryReceiverForDataRecipientBack");
	
	private String statement;
	
	RecevierEnum(String statement){
		this.statement = statement;
	}

	public String getStatement() {
		return statement;
	}
	
}
