package com.baosight.wilp.dm.rz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 入住申请表打印Service
 * All rights Reserved, Designed By www.bonawise.com
 * @author fangzekai
 * @version V5.0.2
 * @Title: ServiceDMRZ0104.java
 * @ClassName: ServiceDMRZ0104
 * @Package com.baosight.wilp.dm.rz.service
 * @date: 2022年02月09日 13:44
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMRZ0104 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 查询打印参数
     * @Title: queryInfoForReport
     * @param inInfo inInfo
     *      manId：人员入住信息表主键
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      id ：主键
     *      statusCode  : 工单状态(00待审核，01待分配，02待选房，03待入住，04已入住，10申请换宿，98申请退宿，99流程结束)
     *      manNo  : 工号
     *      manName  : 姓名
     *      sex : 性别
     *      age  : 员工年龄
     *      phone : 电话
     *      identityCard : 身份证
     *      deptName  : 部门科室名称
     *      deptNum : 部门科室编码
     *      maritalStatus  : 婚否:是，否
     *      educationBackground : 学历
     *      academicYear  : 学年
     *      employmentNature  : 职工属性
     *      isNetwork : 是否联网
     *      isStay  : 是否办暂住证
     *      permanentResidence : 户口所在地
     *      hiredate : 入职时间
     *      estimatedInDate  : 预计入住时间
     *      estimatedOutDate : 预计退房时间
     *      note : 备注信息
     * @throws
     **/
    public EiInfo queryInfoForReport(EiInfo inInfo) {
        //获取参数
        Map<String, String> pmap=new HashMap<String, String>();
        String manId = inInfo.getString("manId");
        pmap.put("manId",manId);
        //数据查询
        List<Map<String, Object>> list = dao.query("DMRZ01.queryRZApplyInfo", pmap);
        Map<String, Object> map = null;
        if(list != null && list.size() > 0){
            map = list.get(0);
        } else {
            map = new HashMap<>(16);
        }
        //返回数据
        inInfo.set("parameters", map);
        return inInfo;
    }
}
