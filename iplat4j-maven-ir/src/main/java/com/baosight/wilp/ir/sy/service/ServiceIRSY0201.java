package com.baosight.wilp.ir.sy.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.ir.sy.service
 * @ClassName ServiceIRSY02
 * @Description 首页公告新增窗口
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
public class ServiceIRSY0201 extends ServiceBase {

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        // 直接就返回一个空的数据，不对数据进行渲染
        return inInfo;
    }

    public EiInfo insertNoticeRecord(EiInfo inEiInfo){

        String platSchema = PlatApplicationContext.getProperty("platSchema");
        String workNo = UserSession.getLoginName();
        Map insertParam = new HashMap();
        insertParam.put("platSchema", platSchema);
        insertParam.put("creator", workNo);

        insertParam.put("noticeTitle", inEiInfo.getString("noticeTitle"));
        insertParam.put("noticeContent", inEiInfo.getString("noticeContent"));

        try {
            dao.insert("IRSY0201.insertNoticeRecord", insertParam);
            inEiInfo.setStatus(1);
            inEiInfo.setMsg("添加公告信息成功");
        } catch (Exception e){
            inEiInfo.setStatus(-1);
            inEiInfo.setMsg("添加公告信息失败：" + e.getCause());
        }

        return inEiInfo;
    }
}
