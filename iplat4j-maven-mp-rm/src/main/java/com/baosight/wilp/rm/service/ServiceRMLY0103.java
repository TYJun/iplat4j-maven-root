package com.baosight.wilp.rm.service;

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
import com.baosight.wilp.rm.common.WareHouseDataSplitUtils;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资选择页面Service
 * @ClassName: ServiceRMPZ0201
 * @Package com.baosight.wilp.rm.pz.service
 * @date: 2022年09月07日 10:10
 *
 * 1.页面加载
 * 2.数据查询
 */
public class ServiceRMLY0103 extends ServiceBase {

    @Autowired
    private RmClaimService claimService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 数据查询(查询库存)
     * @Title: query
     * @param inInfo inInfo
     *      matTypeName: 物资分类名称
     *      matName: 物资名称
     *		matNum : 物资编码
     *	    dataGroupCode : 账套(院区)
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      id: 主键
     *      matNum: 物资编码
     *      matName: 物资名称
     *      matTypeId: 物资分类ID
     *      matTypeName: 物资分类名称
     *      matSpec: 物资规格
     *      matModel: 物资型号
     *      unit: 计量单位
     *      price: 单价
     * @throws
     *
     * 1.获取参数
     * 2.调用本地服务获取物资信息
     * 3.如果存在科室,将科室信息带入返回结果
     **/
    public EiInfo queryOld(EiInfo inInfo) {
        /**1.获取参数**/
        Map<String, Object> params = CommonUtils.buildParamter(inInfo, RmConstant.QUERY_BLOCK, "mat");
        params.put("dataGroupCode", RmUtils.toString(params.get("dataGroupCode"), BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())));

        /**2.调用本地服务获取物资信息**/
        EiInfo invoke = RmUtils.invoke("RMJK03", "dockMatStock", params);
        EiBlock resultBlock = invoke.getBlock(RmConstant.RESULT_BLOCK);
        if(resultBlock == null || resultBlock.getRowCount() == 0){
            return ValidatorUtils.blankInfo(inInfo, "mat");
        }

        /**3.如果存在科室,将科室信息带入返回结果**/
        List<Map<String, Object>> rows = resultBlock.getRows();
        if(StringUtils.isNotBlank(RmUtils.toString(params.get("isClaim")))) {
            for (Map<String, Object> matMap : rows) {
                matMap.put("reserveNum", claimService.queryReserveNum(RmUtils.toString(matMap.get("matNum"))));
                matMap.put("matTypeId", matMap.get("matTypeNum"));
                matMap.put("pictureUri", "/rm/showImg2/"+matMap.get("matTypeNum")+"-"+matMap.get("matNum"));
            }
            UserSession.setOutRequestProperty("showStock", true);
        }
        inInfo.getBlocks().put("mat", resultBlock);
        return inInfo;
    }

    /**
     * 查询物资(查询基础档案)
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        /**1.获取参数**/
        Map<String, Object> params = CommonUtils.buildParamter(inInfo, RmConstant.QUERY_BLOCK, "mat");
        params.put("dataGroupCode", RmUtils.toString(params.get("dataGroupCode"), BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())));
        String claimType = inInfo.getString("claimType");
        if(StringUtils.isNotBlank(claimType)) {
            params.put("matTypeCode", WareHouseDataSplitUtils.getApplyMatRootType(claimType));
        } else {
            params.put("matTypeCode", containsDept() ? "2,7" : "2");
        }
        if(inInfo.getBlock("mat") != null) {
            params.put("orderBy", inInfo.getBlock("mat").getString("orderBy"));
        }

        /**2.调用本地服务获取物资信息**/
        EiInfo invoke = RmUtils.invoke("RMTY01", "selectMat", params);
        EiBlock matBlock = invoke.getBlock("mat");
        if(matBlock == null || matBlock.getRowCount() == 0){
            return ValidatorUtils.blankInfo(inInfo, "mat");
        }

        /**3.处理预约量和库存量**/
        List<Map> rows = matBlock.getRows();
        RmUtils.assignNum(rows, claimService);
        UserSession.setOutRequestProperty("showStock", true);
        inInfo.getBlocks().put("mat", matBlock);
        return inInfo;
    }

    /**
     * 获取物资分类树
     * @Title: getMateriaType
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		parentId ： 当前节点的id
     * @param:  @return
     * @return: EiInfo
     * 		id ： 当前节点的id
     *		text : 分类名称
     *		pId ： 上级节点的id
     *		leaf : 是否有子节点
     * @throws
     */
    public EiInfo getMateriaType (EiInfo inInfo) {
        //1 获取参数
        Map<String, String> queryMap = new HashMap<>(2);
        //queryMap.put("parentId", node);
        //2.查询数据
        List<Map<String, String>> rows = RmUtils.getMatTypeTree(queryMap);
        //3 增加节点 block 块
        EiInfo outInfo = new EiInfo();
        outInfo.addBlock("matTypeNum").addRows(rows);
        return outInfo;
    }

    /**
     * 校验指定人员是否在指定科室
     * @Title: containsDept
     * @return boolean
     * @throws
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
