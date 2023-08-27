package com.baosight.wilp.common.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.service.IMedicalLifeService;
import com.baosight.wilp.common.util.PrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author pan
 * @version 1.0
 * @description: 医生活 业务处理层 实现
 * @date 2022/3/24 11:02
 */
@Service
public class MedicalLifeServiceImpl implements IMedicalLifeService {

    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * @description: 公告信息查询
     * @param: null
     * @return: EiInfo 返回数据包装对象
     * @author pan
     * @date: 2022/3/24 13:32
     */
    @Override
    public EiInfo findNotice() {
        EiInfo ei = new EiInfo();
        ei.set(EiConstant.serviceId, "S_IR_SY_02");
        EiInfo out = XServiceManager.call(ei);
        if (out.getStatus() < 0) {
            throw new PlatException(out.getMsg());
        }
        return out;
    }

    /**
     * @description: 修改密码
     * @param: workNo 工号
     * @param: newNo 新的手机号
     * @return:
     * @author pan
     * @date: 2022/4/8 12:00
     */
    @Override
    public void telModify(String workNo, String newNo) {
        handleModify(workNo, newNo, "newNo");
    }

    /**
     * @description: 修改员工科室
     * @param: workNo 工号
     * @param: newDept 新的科室
     * @return:
     * @author pan
     * @date: 2022/4/11 13:50
     */
    @Override
    public void deptModify(String workNo, String newDept) {
        handleModify(workNo, newDept, "deptNum");
    }

    private void handleModify(String workNo, String deptNum, String targetFieldName) {
        if (StringUtils.isNotBlank(workNo) && StringUtils.isNotBlank(deptNum)) {
            HashMap<String, String> hashMap = new HashMap<>();
            String projectSchema = PrUtils.getConfigure();
            hashMap.put("projectSchema", projectSchema);
            hashMap.put("workNo", workNo);
            hashMap.put(targetFieldName, deptNum);
            dao.update("TCCM01.updateTel", hashMap);
        }
    }
}
