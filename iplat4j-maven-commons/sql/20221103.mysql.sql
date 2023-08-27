create table system_sso_log
(
	id                   bigint auto_increment primary key comment '日志主键',
    token_id             varchar(128)  comment '单点登录给的TOKEN',
    token_request_ip     varchar(64)   comment '请求的IP地址',
    request_time         datetime      comment '请求的时间',
    try_index            int           comment '重试的次数',
    response_success     int           comment '是否正常返回',
    response_content     text          comment '返回的原始内容',
    source_code_data     text          comment '返回的JSON中取出的原始数据',
    process_success_flag int           comment '处理结果说明',
    process_error_detial text          comment '登录异常信息说明'
) comment '单点登录日志表';
create index idx_system_sso_log_1 on system_sso_log(token_id,try_index);