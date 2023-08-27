package com.baosight.wilp.si.ty.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 序列号（单号）Service
 * @ClassName: ServiceSITY01
 * @Package com.baosight.wilp.rm.ty.service
 * @date: 2022年09月13日 13:22
 *
 * 1.查询指定序列号
 * 2.保存序列号
 */
public class ServiceSITY01 extends ServiceBase {

    public static final String OPERATE_TYPE_ADD = "add";

    /**
     * 获取指定类型的最大序列号
     * @Title: querySerialNo
     * @param inInfo inInfo
     *     type: 序列号名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo querySerialNo (EiInfo inInfo) {
        List<String> list = dao.query("SITY01.querySerialNo", inInfo.getString("type"));
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
        if(OPERATE_TYPE_ADD.equals(op)){
            map.put("createTime", DateUtils.curDateTimeStr19());
            dao.insert("SITY01.insertSerialNo", map);
        } else {
            map.put("updateTime", DateUtils.curDateTimeStr19());
            dao.update("SITY01.updateSerialNo", map);
        }
        return inInfo;
    }




}
