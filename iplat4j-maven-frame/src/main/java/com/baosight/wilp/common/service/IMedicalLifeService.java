package com.baosight.wilp.common.service;

import com.baosight.iplat4j.core.ei.EiInfo;

/**
 * @author pan
 * @version 1.0
 * @description: 医生活 业务处理层 接口
 * @date 2022/3/24 11:01
 */
public interface IMedicalLifeService {
    /**
     * @description: 公告信息查询
     * @param: null
     * @return: EiInfo 返回数据包装对象
     * @author pan
     * @date: 2022/3/24 13:30
     */
    EiInfo findNotice();

    /** 
     * @description: 修改密码
     * @param: workNo 工号
     * @param: newNo 新的手机号
     * @return:  
     * @author pan
     * @date: 2022/4/8 12:00
     */ 
    void telModify(String workNo, String newNo);

    /**
     * @description: 修改员工科室
     * @param: workNo 工号
     * @param: deptNum 新的科室
     * @return:
     * @author pan
     * @date: 2022/4/11 13:50
     */
    void deptModify(String workNo, String deptNum);
}
