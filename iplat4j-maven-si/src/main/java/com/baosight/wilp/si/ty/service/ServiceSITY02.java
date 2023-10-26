package com.baosight.wilp.si.ty.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存通用Service
 * @ClassName: ServiceSITY02
 * @Package com.baosight.wilp.si.ty.service
 * @date: 2022年10月08日 17:18
 *
 * 1.人员选择
 * 2.供应商选择
 * 3.获取指定物资的所有子级分类编码集合
 * 4.查询指定的物资分类
 * 5.获取指定人员的业务科室
 * 6.或群所有的业务科室
 */
public class ServiceSITY02 extends ServiceBase {

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
        params.put("datagroupCode", SiUtils.isEmpty(inInfo.get("dataGroupCode"), BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())));
        params.put("workNo", inInfo.get("workNo"));
        params.put("userName", inInfo.get("name"));
        //调用微服务接口S_AC_FW_01人员信息
        List<Map<String, Object>> list = BaseDockingUtils.getStaffAllNoPage(params);
        return CommonUtils.BuildOutEiInfo("person", list);
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
        Map<String, Object> map = null;
        //参数处理
        if(inInfo.getBlock(EiConstant.queryBlock) != null) {
            map = inInfo.getRow(EiConstant.queryBlock, 0);
        } else {
            map = new HashMap<>(4);
            map.put("supplierName", inInfo.get("supplierName"));
            map.put("supplierCode", inInfo.get("supplierNum"));
        }

        //调用微服务S_AC_FW_19获取数据
        List<Map<String, String>> list = BaseDockingUtils.getSupplierNoPage(map);
        inInfo.setRows("supplier", list);
        return inInfo;
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
     * 查询指定的物资分类
     * @Title: selectMatType
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo selectMatType (EiInfo inInfo) {
        //1 获取参数
        Map<String, String> queryMap = new HashMap<>(2);
        //2.查询数据,调用微服务接口S_AC_FW_18查询物资分类信息
        List<Map<String, String>> list = BaseDockingUtils.getMatType(queryMap);
        //过滤出分类编码为 30xxx的物资分类
        List<Map<String, String>> matList =
                list.stream().filter(m -> m.get("matClassCode").startsWith("3"))
                        .filter(x-> !"301".equals(x.get("matClassCode")))
                        .filter(x-> !"302".equals(x.get("matClassCode")))
                        .filter(x-> !"3".equals(x.get("matClassCode"))).collect(Collectors.toList());
        //判断是否需要添加固定资产的物资分类
        String rootType = WareHouseDataSplitUtils.getWareHouseMatRootType(UserSession.getLoginName());
        if(rootType.contains("6")) {
            matList.addAll(list.stream().filter(m -> m.get("matClassCode").startsWith("A"))
                    .filter(x -> x.get("matClassCode").matches("^A[0-9][1-9][0-9][1-9][0-9]{4}$")).collect(Collectors.toList()));
        }
        //3 增加节点 block 块
        inInfo.setRows("matType", matList);
        return inInfo;
    }

    /**
     * 获取指定人员的业务科室
     * @Title: selectUserBusinessDept
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo selectUserBusinessDept(EiInfo inInfo) {
        String workNo = SiUtils.isEmpty(inInfo.getString("workNo"), UserSession.getLoginName());
        Map<String, String> map = new HashMap<>(2);
        map.put("workNo", workNo);
        map.put("frameSchema", PlatApplicationContext.getProperty("projectSchema"));
        List<Map<String, String>> list = dao.query("SITY02.selectUserBusinessDept", map);
        inInfo.setRows("userDept", list);
        return inInfo;
    }

    /**
     * 获取所有业务科室
     * @Title: selectBusinessDept
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo selectBusinessDept(EiInfo inInfo) {
        Map<String, String> map = new HashMap<>(2);
        map.put("deptName", inInfo.getString("deptName"));
        map.put("frameSchema", PlatApplicationContext.getProperty("projectSchema"));
        List<Map<String, String>> list = dao.query("SITY02.selectBusinessDept", map);
        inInfo.setRows("b_dept", list);
        return inInfo;
    }

}
