package com.baosight.wilp.si.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.common.SiUtils;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存模块调用外部接口（系统其他模块）
 * @ClassName: ServiceSIJK03
 * @Package com.baosight.wilp.si.jk.service
 * @date: 2022年09月29日 15:31
 */
public class ServiceSIJK03 extends ServiceBase {

    /**
     * 一级模块: 物资领用
     **/
    public static final String MODULE_CODE_RM = "rm";

    /**
     * 获取物资领用预约量
     * <p>调用微服务接口S_RM_JK_03,获取物资领用预约量</p>
     * @Title: queryReserveNum
     * @param inInfo inInfo
     *     matNumList: 物资编码集合
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      list: 物资预约量信息
     *          matNum: 物资编码
     *          matName: 物资名称
     *          matTypeName: 物资分类名称
     *          totalNum: 预约量
     * @throws
     **/
    public EiInfo queryReserveNum(EiInfo inInfo) {
        if(SiUtils.isExistModule(MODULE_CODE_RM)) {
            EiInfo outInfo = SiUtils.invokeRemote(inInfo, "S_RM_JK_03");
            return outInfo;
        }
        return inInfo;
    }

    /**
     * 生成需求计划
     * <p>调用微服务接口S_RM_JK_05生成需求计划</p>
     * @Title: genRequirePlan
     * @param inInfo inInfo
     *     planTime : 需求计划时间
     *     planType : 01=年度、02=月度, 03=临时
     *     deptNum : 领用(申请)科室编码
     *     deptName : 领用(申请)科室名称
     *     deptPrincipal : 科室负责人工号
     *     deptPrincipalName : 科室负责人姓名
     *     planDesc : 需求计划描述
     *     remark : 备注/申请理由
     *     recCreator : 创建（申请）人
     *     recCreatorName : 创建（申请）人姓名
     *     dataGroupCode : 账套
     *     details : 需求计划明细集合
     *         matNum : 物资编码
     *         matName : 物资名称
     *         matTypeId : 物资分类编码
     *         matTypeName : 物资分类名称
     *         matSpec : 物资规格
     *         matModel : 物资型号
     *         unit : 计量单位
     *         unitName : 计量单位
     *         price : 单价
     *         num : 计划数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo genRequirePlan(EiInfo inInfo) {
        return SiUtils.invokeRemote(inInfo, "S_RM_JK_05");
    }
}
