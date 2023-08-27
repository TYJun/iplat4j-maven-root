package com.baosight.wilp.ir.sy.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.ir.sy.service
 * @ClassName ServiceIRSY02
 * @Description 首页公告编辑窗口
 * @Author chunchen2
 * @Date 2022/1/10 14:11
 * @Version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2022/1/10 14:11
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceIRSY0202 extends ServiceBase {

    @Override
    public EiInfo initLoad(EiInfo inInfo) {

        // 参数里面会有一个id, 根据id查询
        inInfo.set(EiConstant.serviceName, "IRSY02");
        inInfo.set(EiConstant.methodName, "queryNoticeRecordById");
        EiInfo outInfo = XLocalManager.call(inInfo);

        return outInfo;
    }

    /**
     * @Title updateNoticeRecord
     * @Author chunchen2
     * @Description //根据公告id更新公告信息
     * @Date 10:29 2022/1/11
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo updateNoticeRecord(EiInfo inInfo){

        String platSchema = PlatApplicationContext.getProperty("platSchema");
        String workNo = UserSession.getLoginName();
        Map updateParam = new HashMap();
        updateParam.put("platSchema", platSchema);
        updateParam.put("revisor", workNo);

        updateParam.put("id", inInfo.getString("id"));
        updateParam.put("noticeTitle", inInfo.getString("noticeTitle"));
        updateParam.put("noticeContent", inInfo.getString("noticeContent"));
        updateParam.put("status", inInfo.getString("status"));

        try {
            int updateRet = dao.update("IRSY0202.updateNoticeRecord", updateParam);
            if(updateRet > 0){
                inInfo.setStatus(1);
                inInfo.setMsg("更新公告信息成功");
            } else {
                inInfo.setStatus(-2);
                inInfo.setMsg("更新公告信息失败");
            }
        } catch (Exception e){
            inInfo.setStatus(-1);
            inInfo.setMsg("更新公告信息失败：" + e.getCause());
        }

        return inInfo;
    }
}
