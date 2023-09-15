package com.baosight.wilp.hr.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.hr.common.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供与集成平台对接接口
 */
public class ServiceHRJK01 extends ServiceBase {

    public EiInfo getUserInfo(EiInfo info){
        //获取前端参数
        HashMap<String, Object> attr = (HashMap<String, Object>) info.getAttr().get("paramObject");

        String updateDate = StringUtil.toString(attr.get("updateDate"));

        Map map = new HashMap();
        map.put("updateDate", updateDate);

        //数据更新
        List<Map<String, String>> list = dao.query("HRXX01.getUserInfo", map);
        //页面返回

        info.set("obj", list);
        info.set("msg", "200");
        return info;

    }

    public EiInfo putUserInfo(EiInfo info){
        // 获取前台传来的数据
//		Map<String, Object> userInfo = HrUtils.getUserInfo(UserSession.getUser().getUsername());
//		String name =userInfo.get("name").toString();
//		String dataGroupCode = userInfo.get("dataGroupCode").toString();
        String serviceDeptNum = info.getString("serviceDeptNum");
        String deptNum = info.getString("deptNum");
        String workNo = info.getString("workNo");
        String realName = info.getString("realName");
        String sex = info.getString("sex");
        String birthPlace = info.getString("birthPlace");
        String kampong = info.getString("kampong");
        String manCode = info.getString("manCode");
        String schoolingCode = info.getString("schoolingCode");
        String jobCode = info.getString("jobCode");
        String politicalStatus = info.getString("politicalStatus");
        String salary = StringUtils.isBlank(info.getString("salary")) ? "0":info.getString("salary");
        String birthDate = info.getString("birthDate");
        String preInTime = info.getString("preInTime");
        String phone = info.getString("phone");
        String health = info.getString("health");
        String familyAddress = info.getString("familyAddress");
        String company = info.getString("company");
        String memo = info.getString("memo");
        String maritalStatus = info.getString("maritalStatus");
        String personnelCategory = info.getString("personnelCategory");
        String highestEducational = info.getString("highestEducational");
        String highestDegree = info.getString("highestDegree");
        String emergency = info.getString("emergency");
        String emergencyPhone = info.getString("emergencyPhone");
        Map map = new HashMap<>();
        // 获取附件集合
        List<Map<String,String>> fileArray = (List<Map<String, String>>) info.get("fileArray");

        // 封装数据
        map.put("serviceDeptNum", serviceDeptNum);
        map.put("deptNum", deptNum);
        map.put("workNo", workNo);
        map.put("realName", realName);
        map.put("sex", sex);
        map.put("birthPlace", birthPlace);
        map.put("kampong", kampong);
        map.put("manCode", manCode);
        map.put("schoolingCode", schoolingCode);
        map.put("jobCode", jobCode);
        map.put("politicalStatus", politicalStatus);
        map.put("salary", salary);
        map.put("birthDate", birthDate);
        map.put("preInTime", preInTime);
        map.put("phone", phone);
        map.put("health", health);
        map.put("familyAddress", familyAddress);
        map.put("company", company);
        map.put("memo", memo);
        map.put("maritalStatus", maritalStatus);
        map.put("personnelCategory", personnelCategory);
        map.put("highestEducational", highestEducational);
        map.put("highestDegree", highestDegree);
        map.put("emergency", emergency);
        map.put("emergencyPhone", emergencyPhone);
        // 状态为新建状态
        map.put("statusCode","01");

        info.set("obj", map);
        System.out.println("obj:"+info);
        return info;
    }
}
