package com.baosight.wilp.hi.common.util;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.utils.DatagroupUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 维修过时的兼容代码工具类（在维修5.0.x上没用,等同废弃代码）
 *
 * <p>维修出现稳定版本前的兼容代码（在没有基础档案模块[iplat-maven-frame]前查询基础档案信息的代码）</p>
 *
 *  选择人员 -MTRE01
 *	科室查询 -MTRE01
 *	选择维修科室 -MTRE01
 *	报修电话补全查询 -MTRE01
 *	楼补全 -MTRE01
 *	层补全 -MTRE01
 *	地点补全 -MTRE01
 *	执行人查询 -MTAC0104
 *	物资查询 -MTFS0104
 *	指定人员账套查询 -MTUtils.getRole
 *	获取用户信息 -MTUtils.getUserInfo
 *  获取指定科室下人员信息 -MTUtils.getWXZZUser
 *  获取维修科室账套 -DataGroupCodeDealAop
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  MtBaseDockingUtils.java
 * @ClassName:  MtBaseDockingUtils
 * @Package com.baosight.wilp.mt.common
 * @Description: TODO
 * @author fangjian
 * @date:   2021年7月19日 上午9:56:15
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class MtObsoleteUtils {

    /**
     * 选择人员（有分页）
     *
     * <p>根据参数查询人员信息</br>
     *
     * @Title: queryPersonnel
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 	name： 姓名
     * 	workNo ： 工号
     * 	workNoEQ ： 工号（精确查询）
     * 	wgroupNum ： 科室编码
     * @param:  @return
     * @return: EiInfo
     *		workNo		：	员工工号
     *		name		：	员工姓名
     *		gender		：	员工性别
     *		contactTel	：	联系电话
     *		deptNum		:	科室编码
     *		deptName	:	科室名称
     * @throws
     */
    public static EiInfo queryPersonnel(Map<String, Object> map, EiInfo inInfo, Dao dao) {
        //数据查询
        List<Map<String, Object>> list = dao.query("MTRE01.queryPersonnelList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
        int count = dao.count("MTRE01.queryPersonnelCount", map);
        //返回
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "person", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            return inInfo;
        }
    }

    /**
     * 科室查询
     *
     * <p>根据参数查询科室信息</br>
     *
     * @Title: queryDept
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		deptNum：科室编码
     * 		deptName：科室名称
     * 		dataGroupCode： 账套（院区）
     * @param:  @return
     * @return: EiInfo
     * 		deptNum：科室编码
     * 		deptName：科室名称
     * @throws
     */
    public static EiInfo queryDept(Map<String, Object> map, EiInfo inInfo, Dao dao) {
        //数据查询
        List<Map<String, Object>> list = dao.query("MTRE01.queryDeptList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
        int count = dao.count("MTRE01.queryDeptCount", map);
        //返回
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "dept", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            return inInfo;
        }
    }

    /**
     * 维修科室查询
     *
     * <p>1.处理参数</br>
     *  2.获取维修配置项：是否与基础对接</br>
     *  3.判断是否与基础对接。是，调用微服务接口S_AC_FW_12，获取维修科室信息</br>
     *  4.否，查询表中相关信息</p>
     *
     * @Title: queryRepairDept
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		wgroupNum ：维修科室编码
     * 		wgroupName ： 维修科室名称
     * @param:  @return
     * @return: EiInfo
     * 		wgroupNum ：维修科室编码
     * 		wgroupName ： 维修科室名称
     * @throws
     */
    public static EiInfo queryRepairDept(Map<String, Object> map, EiInfo inInfo, Dao dao) {
        //数据查询
        List<Map<String, Object>> list = dao.query("MTRE01.queryRepairDeptList", map);
        //返回
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "dept", CommonUtils.createBlockMeta(list.get(0)), list, null);
        } else {
            return inInfo;
        }
    }

    /**
     * 报修电话补全查询
     *
     * <p>1.处理参数,查询表中相关信息</p>
     *
     * @Title: selectOfficePhone
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		reqTelNum : 电话（模糊查询）
     * 		telNum ： 电话（精确查询）
     * 		dataGroupCode ： 账套（院区）
     * @param:  @return
     * @return: EiInfo
     * 		telNum ： 联系电话
     * 		deptNum ： 科室编码
     * 		deptName ： 科室名称
     * 		building ： 楼
     * 		floor ： 层
     * @throws
     */
    @SuppressWarnings("unchecked")
    public static EiInfo selectOfficePhone(Map<String, Object> pMap, EiInfo inInfo, Dao dao) {
        //数据查询
        List<Map<String, Object>> list = dao.query("MTRE01.selectOfficePhone", pMap);
        //返回
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "phone", null, list, null);
        } else {
            return inInfo;
        }
    }

    /**
     * 楼补全
     *
     * <p>1.处理参数,查询表中相关信息</p>
     *
     * @Title: selectSpotBuildingName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		building ： 楼
     * @param:  @return
     * @return: EiInfo
     * 		building ： 楼
     * @throws
     */
    public static EiInfo selectSpotBuildingName(Map<String, Object> map, EiInfo inInfo, Dao dao) {
        List<Map<String, Object>> list = dao.query("HISQ01.selectSpotBuildingName", map);
        //返回
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "building", null, list, null);
        } else {
            return inInfo;
        }
    }

    /**
     * 层补全
     *
     * <p>1.处理参数，查询表中相关信息</p>
     *
     * @Title: selectSpotFloorName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		building ： 层
     * 		floor ： 层
     * @param:  @return
     * @return: EiInfo
     * 		floor ： 层
     * @throws
     */
    public static EiInfo selectSpotFloorName(Map<String, Object> map, EiInfo inInfo, Dao dao) {
        List<Map<String, Object>> list = dao.query("HISQ01.selectSpotFloorName", map);
        //返回
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "floor", null, list, null);
        } else {
            return inInfo;
        }
    }

    /**
     * 地点补全
     *
     * <p>1.处理参数，查询表中相关信息</p>
     *
     * @Title: selectSpotRoomName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		building ： 层
     * 		floor ： 层
     * 		spotName ： 地点名称
     * @param:  @return
     * @return: EiInfo
     * 		reqSpotName ： 房间
     * 		spotNum ： 地点编码
     * 		spotName ： 地点名称
     * @throws
     */
    public static EiInfo selectSpotRoomName(Map<String, Object> map, EiInfo inInfo, Dao dao) {
        List<Map<String, Object>> list = dao.query("HISQ01.selectSpotRoomName", map);
        //返回
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "room", null, list, null);
        } else {
            inInfo.setRows("room",list);
            return inInfo;
        }
    }

    /**
     * 执行人查询
     *
     * <p>1.处理参数，查询表中相关信息</p>
     *
     * @Title: queryExcutor
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * 		workNo ： 工号
     * 		name ： 姓名
     * 		deptNum ： 科室编码
     * @param:  @return
     * @return: EiInfo
     * 		workNo ： 工号
     *		name ： 姓名
     *		gender ： 性别
     *		deptNum ： 科室编码
     *		contactTel ： 联系电话
     * @throws
     */
    public static EiInfo queryExcutor(Map<String, Object> map, EiInfo inInfo, Dao dao) {
        //查询
        List<Map<String, Object>> list = dao.query("MTAC0104.query", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
        int count = dao.count("MTAC0104.query", map);
        //数据返回
        if(list != null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result",null, list, count);
        } else {
            return inInfo;
        }
    }

    /**
     * 查询物资
     * <p>Title: query</p>
     * <p>Description: </p>
     * @param inInfo
     * 		matTypeName ： 物资分类名称
     * 		matName ： 物资名称
     * 		dataGroupCode ： 账套（院区）
     * @return
     * 		matNum :  物资编码
     *		matName :  物资名称
     *		matClassTypeNum :  物资大类编码
     *		matClassTypeName :  物资大类名称
     *		surpNum :  供应商编码
     *		price :  最近订购单价
     *		matTypeNum :  物资分类编码
     *		matTypeName :  物资分类名称
     *		matModel :  物资型号
     *		matSpec :  物资规格
     *		unit :  计量单位编码
     *		unitName :  计量单位名称
     * @throws
     */
    public static EiInfo queryMat(Map<String, Object> map, EiInfo inInfo, Dao dao) {
        //查询
        List<Map<String, Object>> list=dao.query("MTFS0104.query", map);
        int count = dao.count("MTFS0104.count", map);
        if(list !=null && list.size() > 0){
            return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            inInfo.setMsg("未获取到数据");
            return inInfo;
        }
    }

    /**
     * 获取账套（院区）
     *
     * @Title: getDataGroupCode
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo ： 工号
     * @param:  @return
     * @return: String ： 账套（院区）
     * @throws
     */
    public static String getDataGroupCode(String workNo) {
        //PC端,获取当前登录人的账套(院区)
        if(StringUtils.isBlank(workNo)){
            return DatagroupUtil.getDatagroupCode();
        }
        //APP端或非当前登录人
        List<Map<String, String>> list = getDataGroups(workNo);
        if(list == null || list.size() == 0){
            return "";
        }
        //只有一个账套信息，直接返回
        if(list.size() == 1){
            return list.get(0).get("orgEname");
        }                                         
        //查询该账号最近一次的登录账套
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        List<String> queryList = dao.query("MTRE01.queryLastDataGroupCode", workNo);
        return queryList == null || queryList.size() == 0 ? "" : queryList.get(0);
    }

    /**
     * 获取账套（院区）
     *
     * @Title: getDataGroups
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo ： 工号
     * @param:  @return
     * @return: List<Map<String, String>> ： 账套（院区）信息集合
     *     groupType : 用户组类型
     *     groupEname : 用户组编码
     *     groupCname : 用户组名称
     *     orgCname : 账套名称
     *     orgEname : 账套编码
     * @throws
     */
    private static List<Map<String, String>> getDataGroups(String workNo) {
        EiInfo eiInfo = new EiInfo();
        eiInfo.set("loginName",workNo);
        eiInfo.set(EiConstant.serviceId,"S_XS_70");
        EiInfo outInfo = XServiceManager.call(eiInfo);
        Object object = outInfo.get("result");
        if(object != null){
            return (List<Map<String, String>>) object;
        }
        return null;
    }

    /**
     * 获取用户信息
     *
     * @Title: getUserInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo ： 工号
     * @param:  @return
     * @return: Map<String, String> ： 用户信息
     *     workNo ： 工号
     * 	   name ： 姓名
     * 	   contactTel ：联系电话
     * 	   deptNum ： 科室编码
     * 	   deptName ：科室名称
     * 	   dataGroupCode ： 账套
     * @throws
     */
    public static Map<String,Object> getUserInfo(String workNo) {
        Map<String,Object> map = new HashMap<>(16);
        //参数处理
        map.put("workNoEQ", workNo);
        //数据查询
        EiInfo infoLogin = queryPersonnel(map, new EiInfo(), (Dao)PlatApplicationContext.getBean("dao"));
        EiBlock block = infoLogin.getBlock("person");
        if(block !=null && block.getRowCount() > 0){
            map = block.getRow(0);
            map.put("dataGroupCode", getDataGroupCode(workNo));
        }
        return map;
    }

    /**
     * 获取指定科室下用户信息
     *
     * @Title: getUserInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param deptNum ： 科室编号
     * @param:  @return
     * @return: List<Map<String, Object>> ： 用户信息
     *     workNo ： 工号
     * 	   name ： 姓名
     * 	   contactTel ：联系电话
     * 	   deptNum ： 科室编码
     * 	   deptName ：科室名称
     * 	   dataGroupCode ： 账套
     * @throws
     */
    public static List<Map<String, Object>> getDeptUser (String deptNum) {
        //获取科室人员信息
        Map<String, Object> pMap = new HashMap<>(4);
        pMap.put("deptNumEQ",deptNum);
        EiInfo personInfo = queryPersonnel(pMap, new EiInfo(), (Dao) PlatApplicationContext.getBean("dao"));
        EiBlock person = personInfo.getBlock("person");
        if(person != null && person.getRowCount() > 0){
            return person.getRows();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 获取维修科室账套
     *
     * @Title: getRepireDataGroupCode
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param deptNum ： 科室编号
     * @param:  @return
     * @return: String ： 账套
     * @throws
     */
    public static String getRepireDataGroupCode (String deptNum) {
        //参数处理
        Map<String, Object> pMap = new HashMap<>();
        pMap.put("wgroupNum", deptNum);
        //数据查询
        EiInfo outInfo = queryRepairDept(pMap, new EiInfo(), (Dao)PlatApplicationContext.getBean("dao"));
        //返回结果处理
        EiBlock block = outInfo.getBlock("dept");
        if(block == null || block.getRowCount() == 0){
            return "";
        } else {
            Object dataGroupCode = block.getRow(0).get("dataGroupCode");
            return dataGroupCode == null ? "" : dataGroupCode.toString();
        }
    }

}
