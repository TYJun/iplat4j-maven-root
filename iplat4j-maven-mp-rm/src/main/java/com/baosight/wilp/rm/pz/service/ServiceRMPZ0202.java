package com.baosight.wilp.rm.pz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资选择页面Service
 * @ClassName: ServiceRMPZ0201
 * @Package com.baosight.wilp.rm.pz.service
 * @date: 2022年09月07日 10:10
 * <p>
 * 1.页面加载
 * 2.数据查询
 */
public class ServiceRMPZ0202 extends ServiceBase {

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
        return query(inInfo);
    }

    /**
     * 数据查询
     * 1.获取参数
     * 2.调用本地服务获取物资信息
     * 3.如果存在科室,将科室信息带入返回结果
     *
     * @param inInfo inInfo
     *               matTypeName: 物资分类名称
     *               matName: 物资名称
     *               matNum : 物资编码
     *               dataGroupCode : 账套(院区)
     *               deptNum : 科室编码(科室常用物资配置页面使用)
     *               deptName : 科室名称(科室常用物资配置页面使用)
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * id: 主键
     * deptNum: 科室编码
     * deptName: 科室名称
     * matNum: 物资编码
     * matName: 物资名称
     * matTypeId: 物资分类ID
     * matTypeName: 物资分类名称
     * matSpec: 物资规格
     * matModel: 物资型号
     * unit: 计量单位
     * price: 单价
     * @throws
     * @Title: query
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        /**1.获取参数**/
        Map<String, Object> params = CommonUtils.buildParamter(inInfo, RmConstant.QUERY_BLOCK, "mat");
        params.put("dataGroupCode", RmUtils.toString(params.get("dataGroupCode"), BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())));
        params.put("matTypeCode", containsDept() ? "2,7" : "2");

        /**2.调用本地服务获取物资信息**/
        EiInfo invoke = RmUtils.invoke("RMTY01", "selectMat", params);
        EiBlock matBlock = invoke.getBlock("mat");
        if (matBlock == null || matBlock.getRowCount() == 0) {
            return ValidatorUtils.blankInfo(inInfo, "mat");
        }

        inInfo.getBlocks().put("mat", matBlock);
        return inInfo;
    }

    /**
     * 校验指定人员是否在指定科室
     *
     * @return boolean
     * @throws
     * @Title: containsDept
     **/
    private boolean containsDept() {
        List<Map<String, String>> list = dao.query("RMTY01.selectUserBusinessDept", new HashMap(2) {{
            put("workNo", UserSession.getLoginName());
            put("frameSchema", PlatApplicationContext.getProperty("projectSchema"));
        }});
        return CollectionUtils.isNotEmpty(list) && list.stream()
                .anyMatch(map -> map.get("deptName") != null && map.get("deptName").contains("食堂"));
    }
}
