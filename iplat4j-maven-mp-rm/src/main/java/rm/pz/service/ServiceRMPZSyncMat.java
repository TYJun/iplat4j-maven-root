package rm.pz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 定时更新科室常用物资信息
 * @ClassName: ServiceSycnMat
 * @Package com.baosight.wilp.mp.pz.service
 * @date: 2023年08月03日 17:38
 */
public class ServiceRMPZSyncMat extends ServiceBase {

    public EiInfo syncMat(EiInfo inInfo) {
        dao.update("RMPZ02.syncMat", new HashMap(2) {{
            put("frameSchema", PlatApplicationContext.getProperty("projectSchema"));
        }});
        return inInfo;
    }
}
