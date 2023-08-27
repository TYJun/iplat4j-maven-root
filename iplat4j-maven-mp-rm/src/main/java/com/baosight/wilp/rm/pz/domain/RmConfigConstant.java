package com.baosight.wilp.rm.pz.domain;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用配置常量类
 * @ClassName: RmConfigConstant
 * @Package com.baosight.wilp.rm.pz.domain
 * @date: 2022年09月13日 16:28
 */
public class RmConfigConstant {

    /**
     * 领用模块配置--是否开启需求科室审批
     **/
    public static final String REQUIRE_APPROVAL = "requireApproval";

    /**
     * 领用模块配置--是否开启领用科室审批
     **/
    public static final String CLAIM_APPROVAL = "claimApproval";

    /**
     * 是否开启需求科室审批--否
     **/
    public static final String REQUIRE_APPROVAL_NO = "N";

    /**
     * 是否开启领用科室审批--否
     **/
    public static final String CLAIM_APPROVAL_NO = "N";
}
