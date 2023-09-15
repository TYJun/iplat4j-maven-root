package com.baosight.wilp.rm.pz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.pz.domain.RmDeptMatConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 科室常用物资配置子页面service
 * @ClassName: ServiceRMPZ0201
 * @Package com.baosight.wilp.rm.pz.service
 * @date: 2022年09月07日 10:10
 * <p>
 * 1.页面加载
 * 2.保存科室常用物资信息
 */
public class ServiceRMPZ0201 extends ServiceBase {

    /**
     * 页面加载
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: initLoad
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 数据保存
     * <p>删除原先数据，再保存所有数据</p>
     *
     * @param inInfo inInfo
     *               List：list
     *               id: 主键
     *               matNum: 物资编码
     *               matName: 物资名称
     *               matTypeId: 物资分类ID
     *               matTypeName: 物资分类名称
     *               matSpec: 物资规格
     *               matModel: 物资型号
     *               unit: 计量单位
     *               price: 单价
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: save
     **/
    public EiInfo save(EiInfo inInfo) {
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        List<RmDeptMatConfig> list = RmUtils.toList(inInfo.get("list"), RmDeptMatConfig.class);
        if (CollectionUtils.isEmpty(list)) {
            return ValidatorUtils.errorInfo(inInfo, "物资信息不能为空");
        }

        //获取科室常用物资
        List<RmDeptMatConfig> dList = dao.query("RMPZ02.query", new HashMap(2) {{
            put("deptNum", deptMap.get("deptNum"));
        }});

        //过滤已经添加过的物资
        list = list.stream().filter(config -> dList.stream().noneMatch(deptConfig -> config.getMatNum().equals(deptConfig.getMatNum())
                && config.getMatTypeId().equals(deptConfig.getMatTypeId()))).collect(Collectors.toList());

        //存在数据,保存
        if (CollectionUtils.isNotEmpty(list)) {
            //参数补充赋值
            list.forEach(config -> {
                config.setId(UUID.randomUUID().toString());
                config.setDeptNum(RmUtils.toString(deptMap.get("deptNum")));
                config.setDeptName(RmUtils.toString(deptMap.get("deptName")));
            });
            //保存
            dao.insert("RMPZ02.insert", list);
        }
        return inInfo;
    }
}
