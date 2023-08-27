package com.baosight.wilp.rm.pz.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.pz.domain.RmDeptMatConfig;

import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 科室常用物资配置页面service
 * @ClassName: ServiceRMPZ02
 * @Package com.baosight.wilp.rm.pz.service
 * @date: 2022年09月07日 10:08
 * <p>
 * 1.页面加载
 * 2.页面数据查询
 * 3.删除科室常用物资配置
 */
public class ServiceRMPZ02 extends ServiceBase {

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
        //添加科室查询条件
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 20);
        return query(inInfo);
    }

    /**
     * 页面数据查询
     *
     * @param inInfo inInfo
     *               matNum: 物资编码
     *               matName: 物资名称
     *               matTypeName: 物资分类名称
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
        //查询并返回
        return super.query(inInfo, "RMPZ02.query", new RmDeptMatConfig());
    }

    /**
     * 删除配常用物资配置
     * <p>根据ID物理删除</p>
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: delete
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        String id = inInfo.getString("id");
        dao.delete("RMPZ02.delete", id);
        return inInfo;
    }
}
