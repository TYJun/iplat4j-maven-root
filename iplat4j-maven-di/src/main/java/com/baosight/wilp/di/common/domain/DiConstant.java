package com.baosight.wilp.di.common.domain;

/**
 * 巡检常量
 * All rights Reserved, Designed By www.bonawise.com
 * @author fangjian
 * @version V5.0.2
 * @Title: DiConstant.java
 * @ClassName: DiConstant
 * @Package com.baosight.wilp.di.common.domain
 * @Description: TODO
 * @date: 2021年11月09日 17:40
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class DiConstant {

    /**
     * 巡检短信接收人--巡检负责人和执行人
     **/
    public final static String DI_ACCEPT_ALL = "00";
    /**
     * 巡检短信接收人--巡检负责人
     **/
    public final static String DI_ACCEPT_PRINCIPAL = "01";
    /**
     * 巡检短信接收人--巡检执行人
     **/
    public final static String DI_ACCEPT_EXCUTOR = "02";

    /**
     * 巡检短信类型--超期提醒短信
     **/
    public final static String MSG_TYPE_OUTTIME = "01";
    /**
     * 巡检短信类型--每日任务数提醒短信
     **/
    public final static String MSG_TYPE_TASKCOUNT = "02";

    /**
     * 巡检异常处理方式--现场处理
     **/
    public final static String PROCESS_TYPE_SCENE = "0";

    /**
     * 巡检异常处理方式--上报处理
     **/

    public final static String PROCESS_TYPE_REPORT = "1";

}
