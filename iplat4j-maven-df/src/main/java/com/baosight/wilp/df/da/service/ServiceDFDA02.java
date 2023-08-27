/** 
* @author zhaowei E-mail: zhaowei0425@foxmail.com
* @version 创建时间：2021年8月5日 下午8:29:30 
* 类说明 
*/ 
package com.baosight.wilp.df.da.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.DatagroupUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 设备档案：初始化查询、设备分类查询、设备档案编辑
 * <p>1.初始化查询 initLoad
 * <p>2.设备分类查询 query
 * <p>3.设备档案编辑 edit
 * 
 * @Title: ServiceDFDA02.java
 * @ClassName: ServiceDFDA02
 * @Package：com.baosight.wilp.df.da.service
 * @author: zhaow
 * @date: 2021年8月10日 下午9:22:29
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFDA02 extends ServiceBase{
    /**
     * 
     * @Title: initLoad
     * @Description: 初始化查询
     * @param info
     * @return info
     */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 获取attr中的id
		String id = (String)info.getAttr().get("id");
		// info中设置id
		info.set("id",id);
		// 调用设备分类查询
        return this.query(info);
	}
	
	/**
     * 
     * @Title: query
     * @Description: 设备分类查询
     * @param info
     * moduleId : 设备分类id
     * classifyName : 设备分类名称
     * classifyMemo : 设备分类备注
     * @return
     * id : 设备分类id
     * classifyName : 设备分类名称
     * classifyCode : 设备分类编码
     * memo : 设备分类备注
     */
	@Override
	public EiInfo query(EiInfo info) {
	    //
		String id = (String)info.get("id");
		//
		List<Map<String, String>> list = dao.query("DFDA01.queryId",id);
        //
		if(CollectionUtils.isEmpty(list)) {
            //
		    return info;
        }
		//
		info.setAttr(list.get(0));
		//
		String moduleId = list.get(0).get("machineTypeId");
		//
		Map<String, String> map = new HashMap<>();
        //
		map.put("moduleId", moduleId);
        //
		List<Map<String,String>> list2 = dao.query("DFFL10.query",map);
        //
		info.addRows("result", list2);
		//获取文件列表
		List<Map<String,Object>> fileList = dao.query("DFDA01.queryFile",id);
		info.addRows("resultC", fileList);
		//
		return info;
	}
	
	/**
     * 
     * @Title: edit
     * @Description: 设备档案编辑
     * @param info
     * id : 设备档案id
     * machineCode : 设备编码
     * machineName : 设备名称
     * models : 规格型号
     * machineTypeId : 设备分类编号
     * status : 设备状态
     * needScan : 是否扫二维码
     * buyTime : 购买日期
     * fixedTime : 安装日期
     * useTime : 使用日期
     * fixedPlace :安装地点
     * building : 楼
     * floor : 层
     * spotId : 安装地点id
     * spotCode : 安装地点编码
     * spotName : 安装地点
     * warrantyDate : 质保到期日
     * makerBrand : 品牌
     * supplierName : 供应商
     * supplierId : 供应商id
     * manufacturerName : 生产单位
     * maintUnit : 保养单位
     * lastMaintainTime : 上次保养日期
     * maintainRound : 保养周期
     * managerManId : 管理员id
     * managerMan : 管理员
     * managerDepartId : 管理科室id
     * useDeaprtId : 使用科室id
     * matNum : 物资编码
     * matName : 物资名称
     * outFactoryNo : 出厂编号
     * useLimit : 折旧年限
     * useFor : 用途
     * machineFolderId : 档案盒号
     * buyMode : 采购方式
     * assetPrice : 资产价格
     * memo : 备注
     * managerDepartName : 管理科室
     * useDeaprtName : 使用科室
     * @return info
     */
	public EiInfo edit(EiInfo info) {
	    // 实例化map
		Map<String, String> map = new HashMap<String, String>();
		// 获取id
		String id = (String)info.get("id"); 
		// 设备编码
		String machineCode = (String) info.get("machineCode"); 
		// 设备名称
		String machineName = (String) info.get("machineName"); 
		// 规格型号
		String models = (String) info.get("models"); 
		// 设备分类编号
		String machineTypeId = (String) info.get("machineTypeId"); 
		// 设备状态
		String status = (String) info.get("status"); 
		// 是否扫二维码
		String needScan = (String) info.get("needScan"); 
		// 购买日期
		String buyTime = (String) info.get("buyTime"); 
		// 安装日期
		String fixedTime = (String) info.get("fixedTime"); 
		// 使用日期
		String useTime = (String) info.get("useTime"); 
		// 安装地点编码
		String fixedPlace = (String) info.get("spotName"); 
		// 楼
		String building = (String) info.get("building"); 
		// 层
		String floor = (String) info.get("floor");
		// 安装地点id
		String spotId = info.getString("spotId");
		// 安装地点编码
		String spotCode = info.getString("spotCode");
		// 安装地点
		String spotName = info.getString("spotName");
		// 质保到期日
		String warrantyDate = (String) info.get("warrantyDate"); 
		// 品牌
		String makerBrand = (String) info.get("makerBrand"); 
		// 供应商名称
		String supplierName = (String) info.get("supplierName"); 
		// 供应商编号
		String supplierId = (String) info.get("supplierId"); 
		// 生产单位
		String manufacturerName = (String) info.get("manufacturerName"); 
		// 保养单位
		String maintUnit = (String) info.get("maintUnit"); 
		// 上次保养日期
		String lastMaintainTime = (String) info.get("lastMaintainTime"); 
		// 保养周期
		String maintainRound = (String) info.get("maintainRound"); 
		// 管理员编号
		String managerManId = (String) info.get("managerManId"); 
		// 管理员
		String managerMan = (String) info.get("managerMan"); 
		// 管理科室编号
		String managerDepartId = (String) info.get("managerDepartId"); 
		// 使用科室编号
		String useDeaprtId = (String) info.get("useDeaprtId"); 
		// 物资编码
		String matNum = (String) info.get("matNum"); 
		// 物资名称
		String matName = (String) info.get("matName"); 
		// 出厂编号
		String outFactoryNo = (String) info.get("outFactoryNo");
		// 折旧年限
		String useLimit = (String) info.get("useLimit"); 
		// 用途
		String useFor = (String) info.get("useFor");
		// 档案盒号
		String machineFolderId = (String) info.get("machineFolderId"); 
		// 采购方式
		String buyMode = (String) info.get("buyMode"); 
		// 资产价格
		String assetPrice = (String) info.get("assetPrice"); 
		// 备注
		String memo = (String) info.get("memo");
		// 能源方式
		String energyForm = (String) info.get("energyForm");
		//厂家联系方式
		String factoryTel = (String) info.get("factoryTel");
		//入库时间
		String inStorageTime = (String) info.get("inStorageTime");
		// 固定资产编码
		String goodsCode = (String) info.get("goodsCode");
		// 固定资产名称
		String goodsName = (String) info.get("goodsName");
		// 固定资产条码
		String goodsNo = (String) info.get("goodsNo");
		// 资产所属
		String assetBelongs = (String) info.get("assetBelongs");
		// 出库时间
		String outStorageTime = (String) info.get("outStorageTime");
		// 管理科室名称
		String managerDepartName = info.getString("managerDepartName");
		// 使用科室名称
		String useDeaprtName = info.getString("useDeaprtName");
		// 旧的地点
        String oldSpotId = info.getString("oldSpotId");
        //获取文件列表
        List<Map<String,Object>> fileList=(List<Map<String, Object>>)info.get("resultC");
        // map设置id(下同)
		map.put("id", id);
		map.put("spotId", spotId.replaceAll("-", ""));
		map.put("spotCode", spotCode);
		map.put("machineCode", machineCode);
		map.put("machineName", machineName);
		map.put("models", models);
		map.put("machineTypeId", machineTypeId);
		map.put("status", status);
		map.put("needScan", needScan);
		map.put("buyTime", buyTime);
		map.put("fixedTime", fixedTime);
		map.put("useTime", useTime);
		map.put("fixedPlace", fixedPlace);
		map.put("building", building);
		map.put("floor", floor);
		map.put("warrantyDate", warrantyDate);
		map.put("makerBrand", makerBrand);
		map.put("supplierId", supplierId);
		map.put("supplierName", supplierName);
		map.put("manufacturerName", manufacturerName);
		map.put("maintUnit", maintUnit);
		map.put("lastMaintainTime", lastMaintainTime);
		map.put("maintainRound", maintainRound);
		map.put("managerMan", managerMan);
		map.put("managerManId", managerManId);
		map.put("managerDepartId", managerDepartId);
		map.put("managerDepartName", managerDepartName);
		map.put("useDeaprtId", useDeaprtId);
		map.put("useDeaprtName", useDeaprtName);
		map.put("matNum", matNum);
		map.put("matName", matName);
		map.put("outFactoryNo", outFactoryNo);
		map.put("useLimit", useLimit);
		map.put("useFor", useFor);
		map.put("machineFolderId", machineFolderId);
		map.put("buyMode", buyMode);
		map.put("assetPrice", assetPrice);
		map.put("memo", memo);
		map.put("energyForm",energyForm);
		map.put("factoryTel", factoryTel);
		map.put("inStorageTime",inStorageTime);
		map.put("goodsCode",goodsCode);
		map.put("goodsName",goodsName);
		map.put("goodsNo",goodsNo);
		map.put("assetBelongs",assetBelongs);
		map.put("outStorageTime",outStorageTime);
		map.put("spotName", spotName);
		//map.put("recRevisor",  StringUtils.isNotBlank((String)BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername()).get("name"))?(String)BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername()).get("name"):"");
		map.put("recReviseTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		map.put("datagroupCode", StringUtils.isNotBlank(DatagroupUtil.getDatagroupCode())?DatagroupUtil.getDatagroupCode():"");
		//校验设备编码是否存在
		/*int count = dao.count("DFDA01.isExist", machineCode);
		if(count > 0){
			info.setStatus(-1);
			info.setMsg("设备编码已存在");
			return info;
		}*/
		// 更新
		dao.update("DFDA02.update",map);
		//更新设备地点表
		info.set(EiConstant.serviceName, "DFDA0101");
		info.set(EiConstant.methodName, "updateDeviceSpot");
		XLocalManager.call(info);
		/*// 如果不存在地点新增，设备为1
        // 否则地点数量变更
		// 新的地点
		// 查询地点
		List<Map<String, Object>> listMap = dao.query("DFDA02.query", map);
		// 如果查询地点集合为空
		if(CollectionUtils.isEmpty(listMap)) {
		    // map设置spotId(下同)
            map.put("spotId" , spotId.replaceAll("-", "")); 
            map.put("id" , UUID.randomUUID().toString());
            map.put("deviceCount", "1");
            // 插入地点设备标
            dao.insert("DFDA02.insertMachineSpot",map);
        }else {
            // 新的增加
            Integer newDeviceCount = (Integer)listMap.get(0).get("deviceCount");
            // 存在的设备+1
            map.put("deviceCount", String.valueOf(newDeviceCount+1));
            // 更新数量
            dao.update("DFDA02.updateMachineSpot",map);
            // 旧的减少
            map.put("spotId", oldSpotId);
            // 查询旧的地点
            List<Map<String, Object>> oldListMap = dao.query("DFDA02.query", map);
            // 获取旧的数量
            Integer oldDeviceCount = (Integer)oldListMap.get(0).get("deviceCount");
            // 存在的设备-1
            map.put("deviceCount", String.valueOf(oldDeviceCount-1));
            // 更新数量
            dao.update("DFDA02.updateMachineSpot",map);
        }*/
		//修改文件，如果文件列表为空则直接删除，否则先删除再新增
        if(!fileList.isEmpty()) {
            dao.delete("DFDA01.deleteFile",id);
            fileList.forEach(file -> {
                file.put("id", UUID.randomUUID().toString());
                file.put("machineId", id);
                dao.insert("DFDA01.insertFile",file);
            });
        }else {
            dao.delete("DFDA01.deleteFile",id);
        }
		// 返回
		return info;
	}
}
