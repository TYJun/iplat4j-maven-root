package com.baosight.wilp.ss.wx.constant;

public class WeWork {
    /**
     * 企业微信服务器会定时（每十分钟）推送ticket。ticket会实时变更，并用于后续接口的调用。
     */
    public static final String INFO_TYPE_suite_ticket = "suite_ticket";
    /**
     * 接收临时授权码
     */
    public static final String INFO_TYPE_create_auth = "create_auth";
    public static final String INFO_TYPE_change_auth = "change_auth";
    public static final String INFO_TYPE_cancel_auth = "cancel_auth";
    // 重置授权企业的permanent_code
    public static final String INFO_TYPE_reset_permanent_code = "reset_permanent_code";
    public static final String INFO_TYPE_change_contact = "change_contact";
    public static final String INFO_TYPE_share_agent_change = "share_agent_change";
    // 可见范围变更时，员工回调
    public static final String INFO_TYPE_unsubscribe = "unsubscribe";
    public static final String INFO_TYPE_subscribe = "subscribe";

    public static final String CHANGE_TYPE_create_user = "create_user";
    public static final String CHANGE_TYPE_update_user = "update_user";
    public static final String CHANGE_TYPE_delete_user = "delete_user";

    public static final String CHANGE_TYPE_create_party = "create_party";
    public static final String CHANGE_TYPE_update_party = "update_party";
    public static final String CHANGE_TYPE_delete_party = "delete_party";

    public static final String CHANGE_TYPE_update_tag = "update_tag";


    public static final String INFO_TYPE_change_external_contact = "change_external_contact";
    public static final String INFO_TYPE_add_external_contact = "add_external_contact";
    public static final String INFO_TYPE_edit_external_contact = "edit_external_contact";
    public static final String INFO_TYPE_del_external_contact = "del_external_contact";
    public static final String INFO_TYPE_del_follow_user = "del_follow_user";
    public static final String INFO_TYPE_transfer_fail = "transfer_fail";
    public static final String INFO_TYPE_change_external_chat = "change_external_chat";
    public static final String INFO_TYPE_GROUP_add_member = "add_member";
    public static final String INFO_TYPE_GROUP_del_member = "del_member";
    public static final String INFO_TYPE_GROUP_change_owner = "change_owner";
    public static final String INFO_TYPE_GROUP_change_name = "change_name";
    public static final String INFO_TYPE_GROUP_change_notice = "change_notice";
    public static final String INFO_TYPE_change_external_tag = "change_external_tag";
    public static final String INFO_TYPE_create = "create";
    public static final String INFO_TYPE_update = "update";
    public static final String INFO_TYPE_dismiss = "dismiss";
    public static final String INFO_TYPE_delete = "delete";

    // 系统回调
    public static final String BATCH_JOB_RESULT = "batch_job_result";
}

