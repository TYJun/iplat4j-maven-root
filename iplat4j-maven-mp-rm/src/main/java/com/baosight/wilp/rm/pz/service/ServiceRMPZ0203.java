package com.baosight.wilp.rm.pz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import com.baosight.wilp.rm.pz.domain.RmDeptMatConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 科室常用物资选择页面Service
 * @ClassName: ServiceRMPZ0201
 * @Package com.baosight.wilp.rm.pz.service
 * @date: 2022年09月07日 10:10
 * <p>
 * 1.页面加载
 * 2.数据查询
 */
public class ServiceRMPZ0203 extends ServiceBase {

    /**
     * 常用物资EiBlock的ID
     **/
    private static final String COMMON_USE_BLOCK = "commonMat";

    @Autowired
    private RmClaimService claimService;

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
     * 1.科室参数处理
     * 2.查询,没有数据直接返回
     * 3.判断是否是领用,是,获取库存量和预约量
     * 4.处理库存量和预约量
     *
     * @param inInfo inInfo
     *               matTypeName: 物资分类名称
     *               matName: 物资名称
     *               matNum : 物资编码
     *               deptNum : 科室编码
     *               isClaim: 是否是领用
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
     * stockNum: 库存量
     * reserveNum: 领用预约量
     * @throws
     * @Title: query
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        /**1.科室参数处理**/
        if (StringUtils.isBlank(inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "deptNum"))) {
            Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
            inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
        }
        /**2.查询,没有数据直接返回**/
        inInfo = super.query(inInfo, "RMPZ02.query", new RmDeptMatConfig(), false, null, null, COMMON_USE_BLOCK, COMMON_USE_BLOCK);
        if (inInfo.getBlock(COMMON_USE_BLOCK).getRowCount() == 0) {
            return inInfo;
        }
        /**3.判断是否是领用,是,获取库存量和预约量**/
        if (inInfo.getCell(RmConstant.QUERY_BLOCK, 0, "isClaim") != null) {
            List<Map> rows = inInfo.getBlock(COMMON_USE_BLOCK).getRows();

            /**4.处理库存量和预约量**/
            RmUtils.assignNum(rows, claimService);
            UserSession.setOutRequestProperty("showStock", true);
        }
        return inInfo;
    }

}
