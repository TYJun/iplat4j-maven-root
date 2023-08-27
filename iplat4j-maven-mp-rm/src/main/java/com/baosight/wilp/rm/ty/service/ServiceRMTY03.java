package com.baosight.wilp.rm.ty.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: TODO
 * @ClassName: ServiceRMTY03
 * @Package com.baosight.wilp.rm.ty.service
 * @date: 2023年02月03日 14:05
 */
public class ServiceRMTY03 extends ServiceBase {

    /**
     * 根据用户账号获取用户所属用户组
     * @Title: getParentGroupsByLoginName
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo getParentGroupsByLoginName(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        String msg = "";
        int status = -1;
        String loginName = inInfo.getString("loginName");
        String groupType = inInfo.getString("groupType");
        List parentList = null;
        if (loginName != null && groupType != null) {
            try {
                Map map = new HashMap(4);
                map.put("loginName", loginName);
                map.put("groupType", groupType);
                parentList = dao.query("RMTY03.queryParentGroupsByLoginName", map);
            } catch (Exception var9) {
                msg = var9.getCause().toString();
            }

            if (parentList != null && parentList.size() > 0) {
                status = 1;
                msg = "该用户存在对应的所属用户组";
            } else {
                msg = "没有查询到用户对应的所属用户组";
                status = 0;
            }
        } else {
            msg = "传入的用户登录账号与用户组类别均不能为空";
        }

        outInfo.setRows("result", parentList);
        outInfo.setStatus(status);
        outInfo.setMsg(msg);
        return outInfo;
    }


}
