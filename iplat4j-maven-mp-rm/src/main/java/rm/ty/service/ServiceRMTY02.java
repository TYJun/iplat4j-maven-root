package rm.ty.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.ValidatorUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 序列号（单号）Service
 * @ClassName: ServiceRMTY02
 * @Package com.baosight.wilp.rm.ty.service
 * @date: 2022年09月13日 13:22
 *
 * 1.查询指定序列号
 * 2.保存序列号
 */
public class ServiceRMTY02 extends ServiceBase {

    /**
     * 获取指定类型的最大序列号
     * @Title: querySerialNo
     * @param inInfo inInfo
     *     type: 序列号名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo querySerialNo (EiInfo inInfo) {
        List<String> list = dao.query("RMTY02.querySerialNo", inInfo.getString("type"));
        if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(list.get(0))) {
            inInfo.set("serialNo", "");
        } else {
            inInfo.set("serialNo", list.get(0));
        }
        return inInfo;
    }

   /**
    * 保存序列号
    * @Title: updateSerialNo
    * @param inInfo inInfo
    *     op: 操作类型
    *     type: 序列号名称
    *     serialNo: 序列号
    * @return com.baosight.iplat4j.core.ei.EiInfo
    * @throws
    **/
    public EiInfo updateSerialNo(EiInfo inInfo) {
        //获取参数
        String op = inInfo.getString("op");
        String type = inInfo.getString("type");
        String serialNo = inInfo.getString("serialNo");
        //参数校验
        if(StringUtils.isBlank(type) || StringUtils.isBlank(serialNo)) {
            return ValidatorUtils.errorInfo("参数为空错误");
        }
        //封装参数
        Map<String, String> map = new HashMap<>(4);
        map.put("serialNo", serialNo);
        map.put("type", type);
        //保存数据
        if(RmConstant.OPERATE_TYPE_ADD.equals(op)){
            map.put("createTime", DateUtils.curDateTimeStr19());
            dao.insert("RMTY02.insertSerialNo", map);
        } else {
            map.put("updateTime", DateUtils.curDateTimeStr19());
            dao.update("RMTY02.updateSerialNo", map);
        }
        return inInfo;
    }




}
