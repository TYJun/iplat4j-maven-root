package com.baosight.wilp.mp.ty.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购通用Service
 * @ClassName: ServiceMPTY01
 * @Package com.baosight.wilp.md.ty.service
 * @date: 2022年08月25日 10:41
 *
 * 1.获取所有科室(无分页)
 * 2.获取所有科室(有分页)
 * 3.获取所有人员(无分页)
 * 4.获取物资分类树
 * 5.获取物资信息(有分页)
 * 6.根据物资ID获取物资的图片路径
 * 7.供应商选择(无分页)
 */
public class ServiceMPTY01 extends ServiceBase {

    /**
     * 物资blockId
     **/
    private final static String MAT_BLOCK = "mat";

   /**
    * 人员选择
    * @Title: selectPerson
    * @param inInfo inInfo
    *       workNo: 工号
    *       name: 姓名
    * @return com.baosight.iplat4j.core.ei.EiInfo
    * 			id			:员工ID
    *			workNo		:员工工号
    *			name		:员工姓名
    *			gender		:员工性别
    *			contactTel	:联系电话
    * 			deptId		:科室ID
    *			deptNum		:科室编码
    *			deptName	:科室名称
    *			idNo		:身份证号
    *			type		:员工类型编码
    *			staffType	:员工类型
    *			isService	:是否是服务人员
    *			status		:员工状态编码
    *			isStatus	:员工状态
    * @throws
    **/
    public EiInfo selectPerson(EiInfo inInfo) {
        //获取参数
        Map<String, Object> params = new HashMap<>(4);
        params.put("datagroupCode", BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        params.put("workNo", inInfo.get("workNo"));
        params.put("userName", inInfo.get("name"));
        //调用微服务接口S_AC_FW_01人员信息
        List<Map<String, Object>> list = BaseDockingUtils.getStaffAllNoPage(params);
        return CommonUtils.BuildOutEiInfo("person", list);
    }

    /**
     * 获取人员的对应的业务科室
     * @Title: queryUserBusinessDept
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryUserBusinessDept(EiInfo inInfo) {
        String workNo = MpUtils.toString(inInfo.getString("workNo"), UserSession.getLoginName());
        Map<String, String> map = new HashMap<>(2);
        map.put("workNo", workNo);
        map.put("frameSchema", PlatApplicationContext.getProperty("projectSchema"));
        List<Map<String, String>> list = dao.query("MPTY01.selectUserBusinessDept", map);
        inInfo.setRows("userDept", list);
        return inInfo;
    }

    /**
     * 获取业务科室
     * @Title: queryBusinessDept
     * @param inInfo inInfo
     *      deptName : 业务科室名称
     *      parentDeptCode : 管理科室编码
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryBusinessDept(EiInfo inInfo) {
        inInfo.set("frameSchema", PlatApplicationContext.getProperty("projectSchema"));
        List<Map<String, String>> list = dao.query("MPTY01.queryBusinessDeptWithDeptCode", inInfo.getAttr());
        inInfo.setRows("businessDept", list);
        return inInfo;
    }

    /**
     * 科室选择
     * @Title: selectDept
     * @param inInfo inInfo
     *      deptName: 科室名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * 		id				:	科室ID
     *		deptNum			:	科室编号
     *		deptName		:	科室名称
     *		parentId		:	父级ID
     *		parentDeptName	:	父级科室名称
     *		deptDescribe	:	科室描述
     *		type			:	科室类型
     *		isService		:	是否是服务科室
     *		status			:	科室状态
     *		isStop			:	是否停用
     * @throws
     **/
    public EiInfo selectDept(EiInfo inInfo) {
        //获取参数
        Map<String, Object> params = new HashMap<>(4);
        params.put("datagroupCode", BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        params.put("deptName", inInfo.get("deptName"));
        //调用微服务接口S_AC_FW_05查询科室信息
        List<Map<String, Object>> deptList = BaseDockingUtils.getDeptAllNoPage(params);
        return CommonUtils.BuildOutEiInfo("dept", deptList);
    }

    /**
     * 获取科室(有分页)
     * @Title: selectDeptBlock
     * @param inInfo inInfo
     *      deptNum: 科室编码
     *      deptName: 科室名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * 		id				:	科室ID
     *		deptNum			:	科室编号
     *		deptName		:	科室名称
     *		parentId		:	父级ID
     *		parentDeptName	:	父级科室名称
     *		deptDescribe	:	科室描述
     *		type			:	科室类型
     *		isService		:	是否是服务科室
     *		status			:	科室状态
     *		isStop			:	是否停用
     * @throws
     **/
    public EiInfo selectDeptBlock(EiInfo inInfo) {
        //参数处理
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "dept");
        map.put("datagroupCode", BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        //调用微服务接口S_AC_FW_05查询科室信息
        EiInfo dept = BaseDockingUtils.getDeptAllPage(map, "dept");
        dept.setBlockInfoValue("dept", "showCount", "true");
        return dept;
    }

    /**
     * 获取物资分类
     * @Title: selectMatTypeTree
     * @param inInfo inInfo
     *      matClassName: 物资分类名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      label: 物资分类ID
     *      code: 物资分类编码
     *      text: 物资分类名称
     *      pId: 上级物资分类ID
     *      leaf: 是否是叶子节点
     * @throws
     **/
    public EiInfo selectMatTypeTree(EiInfo inInfo) {
        //1 获取参数
        String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        Map<String, String> queryMap = new HashMap<>(2);
        queryMap.put("parentId", node);
        //2.查询数据
        List<Map<String, String>> rows = getMatTypeTree(queryMap);
        //3 增加节点 block 块
        EiInfo outInfo = new EiInfo();
        EiBlock outBlock = outInfo.addBlock(node);
        outBlock.addRows(rows);
        return outInfo;
    }

    /**
     * 构建物资分类树
     * @Title: getMatTypeTree
     * @param map map
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    private List<Map<String, String>> getMatTypeTree(Map<String, String> map){
        //调用微服务接口S_AC_FW_18查询物资分类信息
        List<Map<String, String>> list = BaseDockingUtils.getMatType(map);
        List<Map<String, String>> treeList = new ArrayList<>();
        list.forEach(pMap -> {
            Map<String, String> rMap = new HashMap<>(8);
            rMap.put("label", pMap.get("id"));
            rMap.put("code", pMap.get("matClassCode"));
            rMap.put("text", pMap.get("matClassName"));
            rMap.put("pId", StringUtils.isBlank(pMap.get("parentId")) ? "root" : pMap.get("parentId"));
            rMap.put("leaf", "");
            treeList.add(rMap);
        });
        return treeList;
    }

    /**
     * 获取指定物资的所有子级分类编码集合
     * @Title: getChildMatTypeNumList
     * @param inInfo inInfo
     *      rootMatTypeNum: 分类编码
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo getChildMatTypeNumList(EiInfo inInfo) {
        //根据分类编码获取分类信息
        String matTypeNum = inInfo.getString("rootMatTypeNum");
        List<Map<String, String>> list = BaseDockingUtils.getMatType(new HashMap(2) {{
            put("matClassCode", matTypeNum);
        }});
        Map<String, String> matTypeMap = list.stream().filter(map -> matTypeNum.equals(map.get("matClassCode"))).findFirst().orElse(null);
        //递归获取子分类
        List<String> matTypeList = new ArrayList<>();
        if(matTypeMap != null) {
            recursionMatTypeId(matTypeList, new ArrayList(){{ add(matTypeMap.get("id")); }});
        }
        matTypeList.add(matTypeNum);
        inInfo.set("matTypeList", matTypeList);
        return inInfo;
    }

    /**
     * 递归获取下级物资分类编码
     * @Title: recursionMatTypeId
     * @param matTypeList matTypeList
     * @param ids ids
     * @return void
     * @throws
     **/
    private void recursionMatTypeId(List<String> matTypeList, List<String> ids) {
        if(CollectionUtils.isNotEmpty(ids)) {
            for (String id : ids) {
                List<String> newIds = getChildIds(id, matTypeList);
                if(newIds == null) {
                    continue;
                }
                recursionMatTypeId(matTypeList, newIds);
            }
        }
    }

    /**
     * 获取指定分类的子分类数据
     * @Title: getChildIds
     * @param id id : 分类ID
     * @param matTypeList matTypeList : 物资分类编码集合
     * @return java.lang.String
     * @throws
     **/
    private List<String> getChildIds(String id, List<String> matTypeList) {
        //获取下级分类
        List<Map<String, String>> list = BaseDockingUtils.getMatType(new HashMap(2) {{
            put("parentId", id);
        }});
        //返回数据处理
        if(CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            matTypeList.addAll(list.stream().map(map -> map.get("matClassCode")).collect(Collectors.toList()));
            return list.stream().map(map -> map.get("id")).collect(Collectors.toList());
        }
    }

    /**
     * 获取物资
     * @Title: selectMat
     * @param inInfo inInfo
     *      matTypeName: 物资分类名称
     *      matName: 物资名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * 		id : 物资ID
     *		matNum : 物资编码
     *		matName : 物资名称
     *		matClassId : 物资分类ID
     *		matTypeNum : 物资分类编码
     *		matTypeName : 物资分类名称
     *		matSpec : 物资规格
     *		matModel : 物资型号
     *		unit : 单位
     *		price : 价格
     *		supplier : 供应商
     *		manufacturer : 制造商
     *		matTypeCode : 物资大类编码
     *		remark : 备注
     * @throws
     **/
    public EiInfo selectMat(EiInfo inInfo) {
        //获取参数
        Map<String, Object> map = new HashMap<>(4);
        map.put("datagroupCode", MpUtils.toString(inInfo.get("dataGroupCode"),BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())));
        map.put("matClassName", inInfo.get("matTypeName"));
        map.put("matCode", inInfo.get("matNum"));
        map.put("matName", inInfo.get("matName"));
        map.put("offset", inInfo.get("offset"));
        map.put("limit", inInfo.get("limit"));

        //调用微服务接口S_AC_FW_17查询物资信息
        EiInfo outInfo = BaseDockingUtils.getMatPage(map, "result");
        EiBlock block = outInfo.getBlock("result");
        if(block !=null && block.getRowCount() > 0){
            List<Map<String, Object>> rows = block.getRows();
            rows.forEach(pMap -> {
                pMap.put("matNum", pMap.get("matCode"));
                pMap.put("matTypeId", pMap.get("matClassCode"));
                pMap.put("matTypeNum", pMap.get("matClassCode"));
                pMap.put("matTypeName", pMap.get("matClassName"));
                pMap.put("unitName", pMap.get("unit") == null ? "" : CommonUtils.getDataCodeItemName("wilp.ac.gm.unit",
                        pMap.get("unit").toString()));
                pMap.put("pictureUri", "/mp/showImg/"+ pMap.get("id"));
            });
            if(inInfo.getBlock(MAT_BLOCK) == null) {
                inInfo.getBlocks().put(MAT_BLOCK, outInfo.getBlock(EiConstant.resultBlock));
            }
            inInfo.setBlockInfoValue(MAT_BLOCK, "showCount", "true");
        } else {
            if(inInfo.getBlock(MAT_BLOCK) == null) { inInfo.addBlock(new EiBlock(MAT_BLOCK));}
        }
        return inInfo;
    }

    /**
     * 根据物资ID获取物资的图片路径
     * @Title: queryMatImgById
     * @param inInfo inInfo
     *       id : 图片ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      matPath : 图片路径
     * @throws
     **/
    public EiInfo queryMatImgById(EiInfo inInfo) {
        List<Map<String, String>> list = BaseDockingUtils.getMatNoPage(inInfo);
        inInfo.set("matPath", CollectionUtils.isEmpty(list) ? "" : list.get(0).get("pictureUri"));
        return inInfo;
    }

    /**
     * 供应商选择
     * @Title: selectSupplier
     * @param inInfo inInfo
     *		id : 供应商ID
     *		supplierCode : 供应商编码
     *		supplierName : 供应商名称
     *		supplierType : 供应商类型
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * 		id : 供应商ID
     *		supplierCode : 供应商编码
     *		supplierName : 供应商名称
     *		supplierType : 供应商类型
     *		contacts : 联系人
     *		contactNumber : 联系电话
     *		contactAddress : 联系地址
     *		contactEmail : 联系邮箱
     *		zipCode : 邮编
     *		legalRepresentative : 法人代表
     *		icrNo : 工商注册号
     *		businessScope : 经营范围
     *		abbreviation : 供应商简称
     *		hrpCode : hrp编码
     * @throws
     **/
    public EiInfo selectSupplier(EiInfo inInfo) {
        //参数处理
        Map<String, Object> map = new HashMap<>(4);
        map.put("supplierName", inInfo.get("supplierName"));
        map.put("supplierCode", inInfo.get("supplierNum"));

        //调用微服务S_AC_FW_19获取数据
        List<Map<String, String>> list = BaseDockingUtils.getSupplierNoPage(map);
        inInfo.setRows("supplier", list);
        return inInfo;
    }

}
