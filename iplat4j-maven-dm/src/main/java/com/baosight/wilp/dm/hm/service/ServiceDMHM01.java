/**
 *@Name ServiceDHRM01.java
 *@Description 宿舍入住申请
 *@Date 2021年5月2日 下午7:19:55
 *@Version 1.0
 **/

package com.baosight.wilp.dm.hm.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.dm.common.until.CsBaseDockingUtils;
import com.baosight.wilp.dm.common.until.DmBaseDockingUtils;
import com.baosight.wilp.dm.utils.StringUtil;

/**
 * TODO(宿舍申请入住信息管理-DMHM01)
 * 
 *      1.初始化页面加载已经申请入住的人员调用query方法
 *      2.查询操作：查询已经申请的宿舍人员
 *      3.初始化科室信息，在页面加载是将科室信息加载出来
 *      4.查询宿舍已入住页面加载
 *      5.删除一条数据申请入住宿舍信息
 *      6.新增操作：新增一条申请入住信息
 *      7.编辑操作：对已有的待审核的入住人信息进行修改
 *      8.更新申请入住人数据的审批状态
 * @Title: ServiceDMHM01.java
 * @ClassName: ServiceDMHM01
 * @Package：com.baosight.wilp.dm.hm.service
 * @author: 辉
 * @date: 2021年11月24日 上午9:45:10
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMHM01 extends ServiceBase{
	
	//格式化时间
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
     * TODO(初始化页面加载已经申请入住的人员调用query方法)
             *         调用query方法
     * @title initLoad
     * @param resquest 请求入参 {}
     * @return query(inInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    //调用query方法
	    return query(inInfo);
	}

	
   /**
     * TODO(查询操作：查询已经申请的宿舍人员)
     * 1.获取数据：employeeId-工号，manName-姓名，departmentNo-部门科室，employmentNature-用工性质，ifReview-是否通过审核
     * 2.查询入住人员信息表
     * 3.筛选掉数据为空的数据
     * @title query
     * @param resquest 请求入参 {employeeId-工号，manName-姓名，departmentNo-部门科室，employmentNature-用工性质，ifReview-是否通过审核}
     * @return inInfo
     */
    @SuppressWarnings("unchecked")
	@Override
    public EiInfo query(EiInfo inInfo) {
        /**1.获取数据：employeeId-工号，manName-姓名，departmentNo-部门科室，employmentNature-用工性质，ifReview-是否通过审核*/
    	String[] param = {"employeeId", "manName", "departmentName", "employmentNature","ifReview"};
        List<String> plist = Arrays.asList(param);
        Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
        /**2.查询入住人员信息表*/
    	List<Map<String, Object>> list = dao.query("dMHM01.query",map,Integer.parseInt((String)map.get("offset")), Integer.parseInt((String)map.get("limit")));
    	//统计查询结果
    	int count = super.count("dMHM01.count",map);
		/**3.筛选掉数据为空的数据*/
		if(list != null && list.size() > 0){
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else { 
		    //返回
			return inInfo; 
		}
        
    }
    
    /**
     * TODO(初始化科室信息，在页面加载是将科室信息加载出来)
     * @title queryDept
     * @param resquest 请求入参 {}
     * @return inInfo
     */
    //选择科室
    @SuppressWarnings("unchecked")
	public EiInfo queryDept(EiInfo inInfo) {
		inInfo.set(EiConstant.serviceId, "S_AC_FW_05");
		inInfo.set("deptNum", "deptNum");
		inInfo.set("deptName", "deptName");
		inInfo.set("offset", "0");
		inInfo.set("limit", "10");
		EiInfo outInfo = XServiceManager.call(inInfo);
		if(outInfo.getStatus() < 0){
			throw new PlatException(outInfo.getMsg());
		}
		return outInfo;




//		//获取参数
//		Map<String, Object> map = PrUtils.buildParamter(inInfo, "inqu_status", "dept");
//		//数据查询：查询数据库中tbmbd01表
//		List<Map<String, Object>> list = dao.query("dMHM01.queryDeptList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
//		//统计结果
//		int count = dao.count("dMHM01.queryDeptCount", map);
//		//筛选数据，返回
//		if(list != null && list.size() > 0){
//			return PrUtils.BuildOutEiInfo(inInfo, "dept", PrUtils.createBlockMeta(list.get(0)), list, count);
//		} else {
//		    //返回
//			return inInfo;
//		}
	}


	/**
	 * TODO(查询宿舍已入住页面加载)
	 * @title queryDept
	 * @param resquest 请求入参 {employeeId-工号，manName-姓名，departmentNo-当前地址，}
	 * @return inInfo
	 */

    @SuppressWarnings("unchecked")
    public EiInfo queryHiList(EiInfo inInfo) {
    	Map<String, Object> map = PrUtils.buildParamter(inInfo,	null, "result");
    	map.put("employeeId", inInfo.get("employeeId"));
	    map.put("manName", inInfo.get("manName"));
	    map.put("departmentName", inInfo.get("departmentName"));
	    map.put("employmentNature", inInfo.get("employmentNature"));
	    map.put("sexCode", inInfo.get("sexCode"));
	    map.put("ifReview",inInfo.get("ifReview"));
		System.out.println("form"+inInfo.get("ifReview"));
    	List<Map<String, Object>> list = dao.query("dMHM01.queryHiList",map,(Integer)map.get("offset"), (Integer)map.get("limit"));
    	// 过滤转换显示数据
    	list.forEach(e->{
    		if("1970-01-01 00:00:00.0".equals(e.get("outDate").toString())) {
    			e.put("outDate", "");
    		}
    	});
    	int count = super.count("dMHM01.count",map);
		//返回
		if(list != null && list.size() > 0){
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else { 
			return inInfo; 
		}
        
    }
    
    /**
     * TODO(删除一条数据申请入住宿舍信息)
     * @title delete
     * @param resquest 请求入参 {}
     * @return inInfo
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        //删除入住人员信息表中的数据
    	return super.delete(inInfo, "dMHM01.delete");
    }
    
    /**
     * TODO(新增操作：新增一条申请入住信息)
     * 1.获取数据：id-主键，employeeId-工号，manName-姓名，idNo-身份证，sexCode-性别，degreeCode-学位，departmentNo-部门科室，employmentNature-用工性质，
     *             phone-电话，deposit-押金编号，isNetwork-是否联网，isStay-是否办理暂住证，createName-记录创建人姓名，createDate-创建时间，updateName-最后修改人姓名，updateDate-最后修改时间，
     *             operatorType-最后操作，note-备注，age-员工年龄，ifMarried-婚否，permanentResidence-户口所在地，workTime-入职时间，ifReview-是否通过审核
     * 2.将获取的数据放入map集合中插入进入住人员信息表中  
     * 3.捕获异常        
     * @title insert
     * @param inInfo 请求入参 { id-主键，employeeId-工号，manName-姓名，idNo-身份证，sexCode-性别，degreeCode-学位，departmentNo-部门科室，employmentNature-用工性质，
     *             phone-电话，deposit-押金编号，isNetwork-是否联网，isStay-是否办理暂住证，createName-记录创建人姓名，createDate-创建时间，updateName-最后修改人姓名，updateDate-最后修改时间，
     *             operatorType-最后操作，note-备注，age-员工年龄，ifMarried-婚否，permanentResidence-户口所在地，workTime-入职时间，ifReview-是否通过审核}
     * @return inInfo
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
    	Map<String,Object> map = new HashMap<>(10);
    	try {
    	   /**1.获取信息id-主键，employeeId-工号，manName-姓名，idNo-身份证，sexCode-性别，degreeCode-学位，departmentNo-部门科室，employmentNature-用工性质，
                 phone-电话，deposit-押金编号，isNetwork-是否联网，isStay-是否办理暂住证，createName-记录创建人姓名，createDate-创建时间，updateName-最后修改人姓名，updateDate-最后修改时间，
                 operatorType-最后操作，note-备注，age-员工年龄，ifMarried-婚否，permanentResidence-户口所在地，workTime-入职时间，ifReview-是否通过审核*/
    		map.put("id", UUID.randomUUID().toString());
        	map.put("employeeId", inInfo.get("employeeId"));
        	map.put("manName", inInfo.get("manName"));
        	map.put("idNo", inInfo.get("idNo"));
        	map.put("sexCode", inInfo.get("sexCode"));
        	map.put("degreeCode", inInfo.get("degreeCode"));
        	map.put("departmentNo", inInfo.get("departmentNo"));
        	map.put("departmentName", inInfo.get("departmentName"));
        	map.put("employmentNature", inInfo.get("employmentNature"));
        	map.put("phone", inInfo.get("phone"));
        	map.put("deposit", "");
        	map.put("isNetwork", 0);
        	map.put("isStay", 0);
        	map.put("createName", "admin");
        	map.put("createDate", sdf.format(new Date()));
        	map.put("updateName", "admin");
           	map.put("updateDate", sdf.format(new Date()));
        	map.put("operatorType", "I");
        	map.put("note", inInfo.get("note"));
			//String age = (String) inInfo.get("age");
        	// 处理年龄问题
		/*	if (age.equals("")){
				map.put("age","");
			}else {
				map.put("age",Integer.valueOf(inInfo.get("age").toString()));
			}*/
        	//map.put("age",Integer.valueOf(inInfo.get("age").toString().isEmpty()? "" : inInfo.get("age").toString()));
			map.put("age",Integer.valueOf(inInfo.get("age").toString()));
        	//map.put("age", inInfo.get("age"));
        	map.put("ifMarried", inInfo.get("ifMarried"));
        	map.put("permanentResidence", inInfo.get("permanentResidence"));
        	map.put("workTime", inInfo.get("workTime").toString());
        	map.put("ifReview", -2);
        	/**2.将获取的数据放入map集合中插入进入住人员信息表中 */
        	dao.insert("dMHM01.insert",map);
        	EiInfo outInfo = new EiInfo();
        	//返回提示信息
            outInfo.setMsg("增加成功");
            return outInfo;
            /**3.捕获异常 */
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
    		EiInfo outInfo = new EiInfo();
    		//返回提示信息
            outInfo.setMsg("增加失败");
            return outInfo;
		}
    	
    }
    
    /**
     * TODO(编辑操作：对已有的待审核的入住人信息进行修改)
     * 1.获取数据id-主键，employeeId-工号，manName-姓名，idNo-身份证，sexCode-性别，degreeCode-学位，departmentNo-部门科室，employmentNature-用工性质，
     *            phone-电话，deposit-押金编号，isNetwork-是否联网，isStay-是否办理暂住证，createName-记录创建人姓名，createDate-创建时间，updateName-最后修改人姓名，updateDate-最后修改时间，
     *            operatorType-最后操作，note-备注，age-员工年龄，ifMarried-婚否，permanentResidence-户口所在地，workTime-入职时间，ifReview-是否通过审核
     * 2.将获取的数据放入map集合中更新进入住人员信息表中  
     * 3.捕获异常
     * @title update
     * @param inInfo 请求入参 {   .获取信息id-主键，employeeId-工号，manName-姓名，idNo-身份证，sexCode-性别，degreeCode-学位，departmentNo-部门科室，employmentNature-用工性质，
     *            phone-电话，deposit-押金编号，isNetwork-是否联网，isStay-是否办理暂住证，createName-记录创建人姓名，createDate-创建时间，updateName-最后修改人姓名，updateDate-最后修改时间，
     *            operatorType-最后操作，note-备注，age-员工年龄，ifMarried-婚否，permanentResidence-户口所在地，workTime-入职时间，ifReview-是否通过审核}
     * @return inInfo
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
    	Map<String,Object> map = new HashMap<>(10);
    	try {
    	    /**1.获取信息id-主键，employeeId-工号，manName-姓名，idNo-身份证，sexCode-性别，degreeCode-学位，departmentNo-部门科室，employmentNature-用工性质，
            phone-电话，deposit-押金编号，isNetwork-是否联网，isStay-是否办理暂住证，createName-记录创建人姓名，createDate-创建时间，updateName-最后修改人姓名，updateDate-最后修改时间，
            operatorType-最后操作，note-备注，age-员工年龄，ifMarried-婚否，permanentResidence-户口所在地，workTime-入职时间，ifReview-是否通过审核*/
    		map.put("id", inInfo.get("id"));
        	map.put("employeeId", inInfo.get("employeeId"));
        	map.put("manName", inInfo.get("manName"));
        	map.put("sexCode", inInfo.get("sexCode"));
        	map.put("degreeCode", inInfo.get("degreeCode"));
        	map.put("departmentNo", inInfo.get("departmentNo"));
        	map.put("departmentName", inInfo.get("departmentName"));
        	map.put("employmentNature", inInfo.get("employmentNature"));
        	map.put("phone", inInfo.get("phone"));
        	map.put("deposit", inInfo.get("deposit"));
        	// 处理是否联网问题
        	Integer isNetwork = "是".equals(inInfo.get("isNetwork")) ? 1 : 0;
        	map.put("isNetwork", isNetwork);
        	
        	// 处理是否暂住问题
        	Integer isStay = "是".equals(inInfo.get("isStay")) ? 1 : 0;
        	map.put("isStay", isStay);

        	map.put("createName", "admin");
        	map.put("createDate", sdf.format(new Date()));
        	map.put("updateName", "admin");
           	map.put("updateDate", sdf.format(new Date()));
        	map.put("operatorType", "I");
        	map.put("note", inInfo.get("note"));
        	
        	// 处理年龄问题
        	map.put("age",Integer.valueOf(inInfo.get("age").toString()));
        	map.put("ifMarried", inInfo.get("ifMarried"));
        	map.put("permanentResidence", inInfo.get("permanentResidence"));
        	map.put("workTime", inInfo.get("workTime"));
        	map.put("ifReview", -2);
        	/**2.将获取的数据放入map集合中更新进入住人员信息表中 */
        	dao.update("dMHM01.update",map);
        	EiInfo outInfo = new EiInfo();
        	//返回提示信息
            outInfo.setMsg("修改成功");
            return outInfo;
            /**3.捕获异常*/
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
    		EiInfo outInfo = new EiInfo();
    		//返回提示信息
            outInfo.setMsg("修改失败");
            return outInfo;
		}
    	
    }
    
    /**
     * TODO(更新申请入住人数据的审批状态)
     * 1.获取数据：id-主键，ifReview-是否通过审核
     * 2.将数据更新进入住员信息表中
     * 3.捕获异常
     * @title updateStatus
     * @param resquest 请求入参 {id-主键，ifReview-是否通过审核}
     * @return inInfo
     */
    public EiInfo updateStatus(EiInfo inInfo) {
    	Map<String,Object> map = new HashMap<>(10);
    	try {
    	    /**1.获取数据：id-主键，ifReview-是否通过审核*/
    		map.put("id", inInfo.get("id"));
    		map.put("ifReview", inInfo.get("ifReview"));
    		/**2.将数据更新进入住人员信息表中*/
    		dao.update("dMHM01.updateStatus",map);
    		EiInfo outInfo = new EiInfo();
    		//返回提示信息
            outInfo.setMsg("成功");
            return outInfo;
            /**3.捕获异常*/
		} catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
    		EiInfo outInfo = new EiInfo();
    		//返回提示信息
            outInfo.setMsg("失败");
            return outInfo;
		}
    	
		
	}


	/**
	 *  1.处理参数
	 *  2.调用微服务接口S_AC_FW_02，获取楼信息
	 *
	 * @Title: selectSpotBuildingName
	 * @Description: 楼补全
	 * @param:  @param inInfo
	 *      building ： 楼
	 * @param:  @return
	 * @return: EiInfo
	 *      building ： 楼
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo selectSpotId(EiInfo inInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", inInfo.getString("dataGroupCode"));
		map.put("workNo", inInfo.getString("buildingCode"));
		//调用微服务接口S_AC_FW_02，获取楼信息
		return CsBaseDockingUtils.selectSpotBuildingName(map);
	}

	/**
	 * @Title: queryPerson
	 * <p>1.接口调用获取人员信息</p>
	 * @Description: 接口改造(人员)
	 * @param ：info
	 * userName : 用户名
	 * @return info
	 * workNo : 工号
	 * userName : 用户名
	 */
	public EiInfo queryPerson(EiInfo info) {
		// 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "person");
		// 获取blockId
		String blockId = info.getBlockIds();
		// 如果blockId相等
		if(blockId.equals("inqu_status,person")) {
			// 实例化info
			EiInfo outinfo = new EiInfo();
			// 实例化block
			EiBlock block = new EiBlock("person");
			// 获取block中的数据的集合
			List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
			// 获取集合中的数据
			String manName = (String) listMap.get(0).get("name");
			map.put("manName", manName);

			String employeeId = (String) listMap.get(0).get("workNo");
			map.put("employeeId",employeeId);
			// 调用改造人员接口并返回
			return BaseDockingUtils.getStaffAllPage(map,"person");
		}

		// 调用改造人员接口并返回
		return BaseDockingUtils.getStaffAllPage(map, "person");
	}
	/**
	 * 获取人员信息（无分页）
	 * @Title: queryPersonnelList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		guaranteeNum：	员工工号
	 *		name		：	员工姓名
	 * @param:  @return
	 * @return: EiInfo
	 * 		workNo		：	员工工号
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称
	 * @throws
	 */
	public EiInfo queryPersonnelList(EiInfo inInfo) {
		//获取参数
		Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo,
				Arrays.asList(new String[]{"dataGroupCode","name","guaranteeNum"}));
		return DmBaseDockingUtils.queryPersonnelNoPage(map);
	}
}

