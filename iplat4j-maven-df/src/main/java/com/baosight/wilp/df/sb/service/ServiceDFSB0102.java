package com.baosight.wilp.df.sb.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.df.sb.domain.DFSB02;
import com.baosight.wilp.df.sb.domain.DFSB03;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * (特种设备档案编辑页面)
 * <p>编辑方法</p>
 * <p>特种设备数据更新方法</p> 
 * @Title: ServiceDFSB0102.java
 * @ClassName: ServiceDFSB0102
 * @Package：com.baosight.wilp.df.sb.service
 * @author: 24128
 * @date: 2021年8月16日 下午1:15:33
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFSB0102 extends ServiceBase{



    /**
     * 页面初始化
     * <p>1.查询一个设备的所有数据</p>
     * Title: initLoad;
     * @param : info
	 * id：                  设备id
     * machineCode：         设备编码
     * machineName：         设备名称
     * machineTypeId：       设备分类信息
     * classifyName：            设备分类
     * statusCode：          状态
     * models：              规格型号
     * fixedId：             安装地点ID
     * fixedPlace:          安装地点
     * innerMachineCode:        设备内部编码
     * documentNo：          档案号
     * workMedia：           工作介质
     * useCertNo：           使用证编号
     * useArea：             使用范围
     * registerCode：            注册号码
     * registerDate：            注册登记日期
     * outFactoryDate：      出厂日期
     * fixedTime：           安装时间
     * useTime：             使用日期
     * nonuseDate：          停用日期
     * managerDeptId：       管理科室ID
     * managerDeptName：     管理科室
     * managerManId：            管理员ID
     * managerManName：      管理员名称
     * chargeUserId：            负责人ID
     * chargeUserName：      负责人名称
     * useDeaprtId：         使用科室ID
     * useDeaprtName：       使用科室
     * relatedDevice：       关联设备
     * useFor：              用途
     * memo：                    备注
     * needScan：                是否扫描二维码
     * manufacturerName：        制造单位
     * manufacturerCertno：  制造单位资格号
     * fixedUnit：           安装单位
     * fixedCertno：         安装单位资格号
     * maintUnit：           维保单位
     * maintCertno：         维保单位资格号
     * checkUnit：           检查单位
     * checkCertno：         检查单位资格号
     * thisCheckDate：       本次检验日期
     * thisFinishDate：      本次检验完工日期
     * nextCheckDate：       下次检验日期
     * annualInspCycle：     周期
     * thisExpiredDate：     本次到检日期
     * thisChexpiredDate：   本次到检完工时间
     * nextExpiredDate：     下次检验时间
     * regularInspCycle：        周期
     * recCreator：          创建人
     * recCreateTime：       创建时间
     * recRevisor：          修改人
     * recReviseTime：       修改时间
     * dataGroupCode：        账套
     * @return :EiInfo
	 * id：                  设备id
     * machineCode：         设备编码
     * machineName：         设备名称
     * machineTypeId：       设备分类信息
     * classifyName：            设备分类
     * statusCode：          状态
     * models：              规格型号
     * fixedId：             安装地点ID
     * fixedPlace:          安装地点
     * innerMachineCode:        设备内部编码
     * documentNo：          档案号
     * workMedia：           工作介质
     * useCertNo：           使用证编号
     * useArea：             使用范围
     * registerCode：            注册号码
     * registerDate：            注册登记日期
     * outFactoryDate：      出厂日期
     * fixedTime：           安装时间
     * useTime：             使用日期
     * nonuseDate：          停用日期
     * managerDeptId：       管理科室ID
     * managerDeptName：     管理科室
     * managerManId：            管理员ID
     * managerManName：      管理员名称
     * chargeUserId：            负责人ID
     * chargeUserName：      负责人名称
     * useDeaprtId：         使用科室ID
     * useDeaprtName：       使用科室
     * relatedDevice：       关联设备
     * useFor：              用途
     * memo：                    备注
     * needScan：                是否扫描二维码
     * manufacturerName：        制造单位
     * manufacturerCertno：  制造单位资格号
     * fixedUnit：           安装单位
     * fixedCertno：         安装单位资格号
     * maintUnit：           维保单位
     * maintCertno：         维保单位资格号
     * checkUnit：           检查单位
     * checkCertno：         检查单位资格号
     * thisCheckDate：       本次检验日期
     * thisFinishDate：      本次检验完工日期
     * nextCheckDate：       下次检验日期
     * annualInspCycle：     周期
     * thisExpiredDate：     本次到检日期
     * thisChexpiredDate：   本次到检完工时间
     * nextExpiredDate：     下次检验时间
     * regularInspCycle：        周期
     * recCreator：          创建人
     * recCreateTime：       创建时间
     * recRevisor：          修改人
     * recReviseTime：       修改时间
     * dataGroupCode：        账套
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	public EiInfo initLoad(EiInfo info) {
        // 获取设备id
		String id = (String)info.getAttr().get("id");
		info.set("id",id);
        // 调用编辑方法
        return this.edit(info);

	}
	/**
	 * 
	 * (编辑方法)
	 *<p>1.编辑方法获取页面的所有数据</p>
	 *<p>2.将所有的设备信息,回显出来</p>
	 * @Title: edit 
	 * @param info
	 * id：                  设备id
	 * machineCode：         设备编码
	 * machineName：         设备名称
	 * machineTypeId：       设备分类信息
	 * classifyName：            设备分类
	 * statusCode：          状态
	 * models：              规格型号
	 * fixedId：             安装地点ID
	 * fixedPlace:          安装地点
	 * innerMachineCode:        设备内部编码
	 * documentNo：          档案号
	 * workMedia：           工作介质
	 * useCertNo：           使用证编号
	 * useArea：             使用范围
	 * registerCode：            注册号码
	 * registerDate：            注册登记日期
	 * outFactoryDate：      出厂日期
	 * fixedTime：           安装时间
	 * useTime：             使用日期
	 * nonuseDate：          停用日期
	 * managerDeptId：       管理科室ID
	 * managerDeptName：     管理科室
	 * managerManId：            管理员ID
	 * managerManName：      管理员名称
	 * chargeUserId：            负责人ID
	 * chargeUserName：      负责人名称
	 * useDeaprtId：         使用科室ID
	 * useDeaprtName：       使用科室
	 * relatedDevice：       关联设备
	 * useFor：              用途
	 * memo：                    备注
	 * needScan：                是否扫描二维码
	 * manufacturerName：        制造单位
	 * manufacturerCertno：  制造单位资格号
	 * fixedUnit：           安装单位
	 * fixedCertno：         安装单位资格号
	 * maintUnit：           维保单位
	 * maintCertno：         维保单位资格号
	 * checkUnit：           检查单位
	 * checkCertno：         检查单位资格号
	 * thisCheckDate：       本次检验日期
	 * thisFinishDate：      本次检验完工日期
	 * nextCheckDate：       下次检验日期
	 * annualInspCycle：     周期
	 * thisExpiredDate：     本次到检日期
	 * thisChexpiredDate：   本次到检完工时间
	 * nextExpiredDate：     下次检验时间
	 * regularInspCycle：        周期
	 * recCreator：          创建人
	 * recCreateTime：       创建时间
	 * recRevisor：          修改人
	 * recReviseTime：       修改时间
	 * dataGroupCode：        账套
	 * @return: EiInfo
	 * id：                  设备id
	 * machineCode：         设备编码
	 * machineName：         设备名称
	 * machineTypeId：       设备分类信息
	 * classifyName：            设备分类
	 * statusCode：          状态
	 * models：              规格型号
	 * fixedId：             安装地点ID
	 * fixedPlace:          安装地点
	 * innerMachineCode:        设备内部编码
	 * documentNo：          档案号
	 * workMedia：           工作介质
	 * useCertNo：           使用证编号
	 * useArea：             使用范围
	 * registerCode：            注册号码
	 * registerDate：            注册登记日期
	 * outFactoryDate：      出厂日期
	 * fixedTime：           安装时间
	 * useTime：             使用日期
	 * nonuseDate：          停用日期
	 * managerDeptId：       管理科室ID
	 * managerDeptName：     管理科室
	 * managerManId：            管理员ID
	 * managerManName：      管理员名称
	 * chargeUserId：            负责人ID
	 * chargeUserName：      负责人名称
	 * useDeaprtId：         使用科室ID
	 * useDeaprtName：       使用科室
	 * relatedDevice：       关联设备
	 * useFor：              用途
	 * memo：                    备注
	 * needScan：                是否扫描二维码
	 * manufacturerName：        制造单位
	 * manufacturerCertno：  制造单位资格号
	 * fixedUnit：           安装单位
	 * fixedCertno：         安装单位资格号
	 * maintUnit：           维保单位
	 * maintCertno：         维保单位资格号
	 * checkUnit：           检查单位
	 * checkCertno：         检查单位资格号
	 * thisCheckDate：       本次检验日期
	 * thisFinishDate：      本次检验完工日期
	 * nextCheckDate：       下次检验日期
	 * annualInspCycle：     周期
	 * thisExpiredDate：     本次到检日期
	 * thisChexpiredDate：   本次到检完工时间
	 * nextExpiredDate：     下次检验时间
	 * regularInspCycle：        周期
	 * recCreator：          创建人
	 * recCreateTime：       创建时间
	 * recRevisor：          修改人
	 * recReviseTime：       修改时间
	 * dataGroupCode：        账套
	 */
	public EiInfo edit(EiInfo info) {
	    //获取id
		String id = (String)info.get("id");
		List<Map<String, String>> list = dao.query("DFSB01.queryId",id);
        if(CollectionUtils.isEmpty(list)) {
            return info;
        }
        //获取attr中属性
        info.setAttr(list.get(0));
		//获取moduleId
		String moduleId = list.get(0).get("machineTypeId");
        //创建map集合
		Map<String, String> map = new HashMap<>();
		//封装参数
        map.put("moduleId", moduleId);
        //查询设备分类
        List<Map<String,String>> list2 = dao.query("DFSB01.queryModule",map);
        //将设备分类数据放入block块中
        info.addRows("resultA", list2);
		//获取设备id
		String machineId =(String)info.get("id");
		//数据查询
		List<Map<String,String>> list4 = dao.query("DFSB03.queryDevicePart",machineId);
		//放入行中
		info.addRows("resultB", list4);
        //创建map集合
		Map<String, String> map1 = new HashMap<>();
        //将设备id值赋给relateId
        String relateId = (String)info.get("id");
        //封装数据
		map1.put("relateId", relateId);
		map1.put("type","1");
        //查询上传文件
		List<Map<String,String>> list3 = dao.query("DFSB0101.queryFile",map1);
        //将设备分类数据放入block块中
		info.addRows("resultC", list3);
		Map<String, String> mapPic = new HashMap<>();
		//封装数据
		mapPic.put("relateId", relateId);
		mapPic.put("type","0");
		//查询上传文件
		List<Map<String,String>> listPic = dao.query("DFSB0101.queryFile",mapPic);
		//将设备分类数据放入block块中
		info.addRows("resultD", listPic);
		//返回
		return info;
	}


	/**
	 * 编辑特种设备档案
	 * <p>1.获取参数封装参数执行更新操作</p>
	 * @Title: update;
	 * @Description: 编辑特种设备档案
	 * @param :info更新参数
	 * id：                  设备id
	 * machineCode：         设备编码
	 * machineName：         设备名称
	 * machineTypeId：       设备分类信息
	 * classifyName：            设备分类
	 * statusCode：          状态
	 * models：              规格型号
	 * fixedId：             安装地点ID
	 * fixedPlace:          安装地点
	 * innerMachineCode:        设备内部编码
	 * documentNo：          档案号
	 * workMedia：           工作介质
	 * useCertNo：           使用证编号
	 * useArea：             使用范围
	 * registerCode：            注册号码
	 * registerDate：            注册登记日期
	 * outFactoryDate：      出厂日期
	 * fixedTime：           安装时间
	 * useTime：             使用日期
	 * nonuseDate：          停用日期
	 * managerDeptId：       管理科室ID
	 * managerDeptName：     管理科室
	 * managerManId：            管理员ID
	 * managerManName：      管理员名称
	 * chargeUserId：            负责人ID
	 * chargeUserName：      负责人名称
	 * useDeaprtId：         使用科室ID
	 * useDeaprtName：       使用科室
	 * relatedDevice：       关联设备
	 * useFor：              用途
	 * memo：                    备注
	 * needScan：                是否扫描二维码
	 * manufacturerName：        制造单位
	 * manufacturerCertno：  制造单位资格号
	 * fixedUnit：           安装单位
	 * fixedCertno：         安装单位资格号
	 * maintUnit：           维保单位
	 * maintCertno：         维保单位资格号
	 * checkUnit：           检查单位
	 * checkCertno：         检查单位资格号
	 * thisCheckDate：       本次检验日期
	 * thisFinishDate：      本次检验完工日期
	 * nextCheckDate：       下次检验日期
	 * annualInspCycle：     周期
	 * thisExpiredDate：     本次到检日期
	 * thisChexpiredDate：   本次到检完工时间
	 * nextExpiredDate：     下次检验时间
	 * regularInspCycle：        周期
	 * recCreator：          创建人
	 * recCreateTime：       创建时间
	 * recRevisor：          修改人
	 * recReviseTime：       修改时间
	 * dataGroupCode：        账套
	 * @return：EiInfo
	 * id：                  设备id
	 * machineCode：         设备编码
	 * machineName：         设备名称
	 * machineTypeId：       设备分类信息
	 * classifyName：            设备分类
	 * statusCode：          状态
	 * models：              规格型号
	 * fixedId：             安装地点ID
	 * fixedPlace:          安装地点
	 * innerMachineCode:        设备内部编码
	 * documentNo：          档案号
	 * workMedia：           工作介质
	 * useCertNo：           使用证编号
	 * useArea：             使用范围
	 * registerCode：            注册号码
	 * registerDate：            注册登记日期
	 * outFactoryDate：      出厂日期
	 * fixedTime：           安装时间
	 * useTime：             使用日期
	 * nonuseDate：          停用日期
	 * managerDeptId：       管理科室ID
	 * managerDeptName：     管理科室
	 * managerManId：            管理员ID
	 * managerManName：      管理员名称
	 * chargeUserId：            负责人ID
	 * chargeUserName：      负责人名称
	 * useDeaprtId：         使用科室ID
	 * useDeaprtName：       使用科室
	 * relatedDevice：       关联设备
	 * useFor：              用途
	 * memo：                    备注
	 * needScan：                是否扫描二维码
	 * manufacturerName：        制造单位
	 * manufacturerCertno：  制造单位资格号
	 * fixedUnit：           安装单位
	 * fixedCertno：         安装单位资格号
	 * maintUnit：           维保单位
	 * maintCertno：         维保单位资格号
	 * checkUnit：           检查单位
	 * checkCertno：         检查单位资格号
	 * thisCheckDate：       本次检验日期
	 * thisFinishDate：      本次检验完工日期
	 * nextCheckDate：       下次检验日期
	 * annualInspCycle：     周期
	 * thisExpiredDate：     本次到检日期
	 * thisChexpiredDate：   本次到检完工时间
	 * nextExpiredDate：     下次检验时间
	 * regularInspCycle：        周期
	 * recCreator：          创建人
	 * recCreateTime：       创建时间
	 * recRevisor：          修改人
	 * recReviseTime：       修改时间
	 * dataGroupCode：        账套
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo update(EiInfo info) {
	    //获取id
		String id = (String)info.get("id");
		//特种设备编码
		String machineCode = (String) info.get("machineCode");
		//特种设备名称
		String machineName = (String) info.get("machineName");
		//规格型号
		String models = (String) info.get("models");
		//特种设备分类id
		String machineTypeId = (String) info.get("machineTypeId");
		//状态
		String statusCode = (String) info.get("statusCode");
		//是否扫描二维码
		String needScan = (String) info.get("needScan");
		//内部设备编码
		String innerMachineCode = (String) info.get("innerMachineCode");
		//安装地点id
		String fixedId = (String) info.get("fixedId");
		//档案号
		String documentNo = (String) info.get("documentNo");
		//安装地点
		String fixedPlace = (String) info.get("fixedPlace");
		//楼
		String building = (String) info.get("building");
		//层
		String floor = (String) info.get("floor");
		//工作介质
		String workMedia = (String) info.get("workMedia");
		//使用证编号
		String useCertNo = (String) info.get("useCertNo");
		//使用范围
		String useArea = (String) info.get("useArea");
		//注册代码
		String registerCode = (String) info.get("registerCode");
		//注册登记时间
		String registerDate = (String) info.get("registerDate");
		//维保单位
		String maintUnit = (String) info.get("maintUnit");
		//出厂日期
		String outFactoryDate = (String) info.get("outFactoryDate");
		//安装时间
		String fixedTime = (String) info.get("fixedTime");
		//管理员id
		String managerManId = (String) info.get("managerManId");
		//管理员名称
		String managerManName = (String) info.get("managerManName");
		//使用时间
		String useTime = (String) info.get("useTime");
		//停用日期
		String nonuseDate = (String) info.get("nonuseDate");
		//管理科室ID
		String managerDeptId = (String) info.get("managerDeptId");
		//管理科室名称
		String managerDeptName = (String) info.get("managerDeptName");
		//负责人ID
		String chargeUserId = (String) info.get("chargeUserId");  
		//负责人名称
		String chargeUserName = (String) info.get("chargeUserName"); 
		//使用科室ID
		String useDeaprtId = (String) info.get("useDeaprtId"); 
		//使用科室名称
		String useDeaprtName = (String) info.get("useDeaprtName"); 
		//用途
		String useFor = (String) info.get("useFor"); 
		//关联设备
		String relatedDevice = (String) info.get("relatedDevice"); 
		//制造单位
		String manufacturerName = (String) info.get("manufacturerName"); 
		//制造单位资格证号
		String manufacturerCertno = (String) info.get("manufacturerCertno"); 
		//安装单位
		String fixedUnit = (String) info.get("fixedUnit"); 
		//安装单位资格证号
		String fixedCertno = (String) info.get("fixedCertno");
		//维保单位资格证号
		String maintCertno = (String) info.get("maintCertno"); 
		//检验单位
		String checkUnit = (String) info.get("checkUnit"); 
		//检验单位资格证号
		String checkCertno = (String) info.get("checkCertno"); 
		//本次年度检验日
		String thisCheckDate = (String) info.get("thisCheckDate"); 
		//本次年度检验完工日
		String thisFinishDate = (String) info.get("thisFinishDate"); 
		//下次年度检验日
		String nextCheckDate = info.getString("nextCheckDate");
		//周期
		String annualinspcycle = StringUtils.isBlank(info.getString("annualinspcycle")) ? "0" : info.getString("annualinspcycle"); 
		//本次到期检验日
		String thisExpiredDate = (String) info.get("thisExpiredDate");
		//本次到期检验完工日
		String thisChexpiredDate = (String) info.get("thisChexpiredDate"); 
		//下次到期检验日
		String nextExpiredDate = (String) info.get("nextExpiredDate"); 
		//周期
		String regularinspcycle = StringUtils.isBlank(info.getString("regularinspcycle")) ? "0":info.getString("regularinspcycle");
		//修改人
		String recRevisor = info.get("recRevisor") == null ? UserSession.getUser().getUsername() : info.getString("recCreator");
		//修改时间
		String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		//获取账套
		String dataGroupCode  =  info.get("dataGroupCode")== null ? DatagroupUtil.getDatagroupCode():info.getString("dataGroupCode");
		//创建map对象
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("machineCode", machineCode);
		map.put("machineName", machineName);
		map.put("models", models);
		map.put("machineTypeId", machineTypeId);
		map.put("statusCode", statusCode);
		map.put("needScan", needScan);
		map.put("innerMachineCode", innerMachineCode);
		map.put("fixedId", fixedId);
		map.put("documentNo", documentNo);
		map.put("fixedPlace", fixedPlace);
		map.put("building", building);
		map.put("floor", floor);
		map.put("workMedia", workMedia);
		map.put("useCertNo", useCertNo);
		map.put("useArea", useArea);
		map.put("registerCode", registerCode);
		map.put("registerDate", registerDate);
		map.put("maintUnit", maintUnit);
		map.put("outFactoryDate",  outFactoryDate);
		map.put("fixedTime", fixedTime);
		map.put("managerManId", managerManId);
		map.put("managerManName", managerManName);
		map.put("useTime", useTime);
		map.put("nonuseDate", nonuseDate);
		map.put("managerDeptId", managerDeptId);
		map.put("managerDeptName", managerDeptName);
		map.put("chargeUserId", chargeUserId );
		map.put("chargeUserName", chargeUserName);
		map.put("useDeaprtId", useDeaprtId);
		map.put("useDeaprtName", useDeaprtName );
		map.put("useFor", useFor);		
		map.put("relatedDevice", relatedDevice );
		map.put("manufacturerName",manufacturerName);
		map.put("manufacturerCertno", manufacturerCertno);
		
		map.put("fixedUnit",  fixedUnit );
		map.put("fixedCertno", fixedCertno);		
		map.put("maintCertno",maintCertno );
		map.put("checkUnit", checkUnit);
		map.put("checkCertno",checkCertno);
		
		map.put("thisCheckDate", thisCheckDate );
		map.put("thisFinishDate", thisFinishDate);		
		map.put("nextCheckDate",nextCheckDate);
		map.put("annualinspcycle", annualinspcycle);
		map.put("thisExpiredDate",thisExpiredDate );
		
		map.put("thisChexpiredDate", thisChexpiredDate);
		map.put("nextExpiredDate", nextExpiredDate);		
		map.put("regularinspcycle",regularinspcycle);
		map.put("spotCode",info.getString("spotCode"));
		map.put("recRevisor",recRevisor);
		map.put("recReviseTime",recReviseTime);
		map.put("dataGroupCode",dataGroupCode);
		dao.update("DFSB0101.update",map);
		// 如果不存在地点新增，设备为1
		// 否则地点数量变更
		// 新的地点
		// 查询地点
		List<Map<String, Object>> listMap = dao.query("DFSB01.querySpotId", map);
		// 如果查询地点集合为空
		if(CollectionUtils.isEmpty(listMap)) {
			// map设置spotId(下同)
			map.put("spotId" , fixedId.replaceAll("-", ""));
			map.put("id" , UUID.randomUUID().toString());
			map.put("deviceCount", "1");
			// 插入地点设备标
			dao.insert("DFSB01.insertMachineSpot",map);
		}else {
			// 新的增加
			Integer newDeviceCount = (Integer)listMap.get(0).get("deviceCount");
			// 存在的设备+1
			map.put("deviceCount", String.valueOf(newDeviceCount+1));
			// 更新数量
			dao.update("DFSB01.updateMachineSpot",map);
			// 旧的减少
			map.put("spotId", fixedId);
			// 查询旧的地点
			List<Map<String, Object>> oldListMap = dao.query("DFSB01.querySpotId", map);
			// 获取旧的数量
			Integer oldDeviceCount = (Integer)oldListMap.get(0).get("deviceCount");
			// 存在的设备-1
			map.put("deviceCount", String.valueOf(oldDeviceCount-1));
			// 更新数量
			dao.update("DFSB01.updateMachineSpot",map);
		}
		//获取文件集合
		Object fileObj = info.get("fileList");
		//创建实体类对象
		saveFile(fileObj,id);
		//获取设备零部件集合
		Object deviceObject = info.get("deviceList");
		//调用设备零部件方法
		saveDevicePart(deviceObject,id,machineCode,machineName);
		//获取图片集合
		Object picObject = info.get("picList");
		//调用保存图片方法
		savePicture(picObject,id);
		//执行更新操作
		return info;
	
	}

	/**
	 * 保存附件方法
	 * <p>1.获取前台传来的文件集合</p>
	 * <p>2.对附件字段赋值</p>
	 * <p>3.将字段保存插入附件表中</p>
	 * @MethodName:saveFile
	 * @param：fileObj
	 * id				设备id
	 * fileName:   		文件名
	 * filePath:		文件路径
	 * fileModule：  	文件类型
	 * relateId：		设备id关联附件id
	 * fileType：       附件类型
	 * dataGroupCode：	账套
	 * recCreator：		创建人
	 * recCreateTime：	创建时间
	 * @return：void
	 * @param：
	 * 	 fileName:   		文件名
	 * 	 filePath:		文件路径
	 * 	 fileModule：  	文件类型
	 * 	 relateId：		设备id关联附件id
	 * 	 fileType：       附件类型
	 * 	 dataGroupCode：	账套
	 * 	 recCreator：		创建人
	 * 	 recCreateTime：	创建时间
	 */
	@SuppressWarnings("unchecked")
	public void saveFile(Object fileObj, String id) {
		//创建集合
		List<Map<String,Object>> list = new ArrayList<>();
		//集合为空
		if(fileObj != null){
			list = (List<Map<String, Object>>) fileObj;
		}
		//删除旧的附件信息
		HashMap<String, String> pMap = new HashMap<>();
		pMap.put("id",id);
		pMap.put("type","1");
		pMap.put("module","0");
		dao.delete("DFSB0101.deleteFile", pMap);
		//获取账套
		String dataGroupCode = DatagroupUtil.getDatagroupCode()==null ? " " :DatagroupUtil.getDatagroupCode();
		//循环新增
		for (Map<String, Object> map : list) {
			//创建实体对象
			DFSB03 att = new DFSB03();
			//获取参数封装map中
			att.fromMap(map);
			//id
			att.setId(UUID.randomUUID().toString());
			//设备关联id
			att.setRelateId(id);
			//附件模块
			att.setFileModule("0");
			//文件类型
			att.setFileType("1");
			//账套
			att.setDataGroupCode(dataGroupCode);
			//创建人
			att.setRecCreator(UserSession.getUser().getUsername());
			//创建时间
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			//执行新增
			dao.insert("DFSB0101.insertFile",att);
		}
	}

	/**
	 * 
	 * (保存图片)
	 *<p>1.获取图片集合</p>
	 *<p>2.删除旧的图片,循环新增新的图片信息</p>
	 * @Title: savePicture 
	 * @param picObject
	 * @param id 
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	public void savePicture(Object picObject, String id){
		//创建集合
		List<Map<String,Object>> list = new ArrayList<>();
		if(picObject != null){
			list = (List<Map<String, Object>>) picObject;
		}
		//删除旧的图片信息
		HashMap<String, String> pMap = new HashMap<>();
		pMap.put("id",id);
		pMap.put("type","0");
		pMap.put("module","0");
		dao.delete("DFSB0101.deleteFile", pMap);
		//获取账套
		String dataGroupCode = DatagroupUtil.getDatagroupCode()==null ? " " :DatagroupUtil.getDatagroupCode();
		//循环新增
		for (Map<String, Object> map : list) {
			//创建实体
			DFSB03 att = new DFSB03();
			//获取的参数封装到map中
			att.fromMap(map);
			//主键赋值
			att.setId(UUID.randomUUID().toString());
			//设备关联的id
			att.setRelateId(id);
			//附件所属模块
			att.setFileModule("0");
			//文件类型
			att.setFileType("0");
			//获取账套
			att.setDataGroupCode(dataGroupCode);
			//创建人
			att.setRecCreator(UserSession.getUser().getUsername());
			//创建时间
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			//执行插入
			dao.insert("DFSB0101.insertFile",att);
		}
	}
	/**
	 * 
	 * (保存设备零部件)
	 * <p>1.获取设备零部件集合</p>
	 * <p>2.删除旧的数据,循环新增新的数据</p>
	 * @Title: saveDevicePart 
	 * @param deviceObject
	 * @param id
	 * @param machineCode
	 * @param machineName 
	 * @return: void
	 */
	public void saveDevicePart(Object deviceObject, String id,String machineCode,String machineName){
		//创建集合
		List<Map<String,Object>> list = new ArrayList<>();
		//集合不为空
		if(deviceObject != null){
			list = (List<Map<String, Object>>) deviceObject;
		}
		//删除旧的
		dao.delete("DFSB03.deleteDevicePart", id);
		//循环新增
		for (Map<String, Object> map : list) {
			//创建设备零部件实体
			DFSB02 devicePart = new DFSB02();
			//获取前台值循环插入数据库中
			devicePart.fromMap(map);
			//赋值id
			devicePart.setId(UUID.randomUUID().toString());
			//machineId
			devicePart.setMachineId(id);
			//设备零部件编码
			devicePart.setMachineCode(machineCode);
			//设备零部件名称
			devicePart.setMachineName(machineName);
			//获取账套
			devicePart.setDataGroupCode(DatagroupUtil.getDatagroupCode());
			//获取创建人
			devicePart.setRecCreator(UserSession.getUser().getUsername());
			//获取创建时间
			devicePart.setRecCreateTime(DateUtils.curDateTimeStr19());
			//执行插入
			dao.insert("DFSB03.insertDevicePart",devicePart);
		}	}

}
