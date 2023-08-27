package com.baosight.wilp.si.wh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.wh.domain.SiWarehouse;
import com.baosight.xservices.xs.util.UserSession;

/**
 *  仓库管理子页面Service
 * 
 * <p>页面加载</p>
 * <p>新增/编辑仓库</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIWH01.java
 * @ClassName:  ServiceSIWH01
 * @Package com.baosight.wilp.si.wh.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 上午9:08:37 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIWH0101 extends ServiceBase {
	
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo initLoad(EiInfo inInfo) {
		inInfo.set("workNo", UserSession.getUser().getUsername());
		if("see".equals(inInfo.getString("type"))){
			Map<String, String> map = new HashMap<>(2);
			map.put("id", inInfo.getString("id"));
			List<SiWarehouse> list = dao.query("SIWH01.query", map);
			SiWarehouse warehouse = list.get(0);
			inInfo.getAttr().putAll(warehouse.toMap());
		}
		//人员选择多选下拉框
		EiInfo invoke = SiUtils.invoke(null, "SITY02", "selectPerson", null);
		inInfo.addBlock(invoke.getBlock("person"));
		return inInfo;
    }

	/**
	 * 新增/编辑仓库
	 * 
	 * <p>1.获取参数,判断是新增还是编辑操作</br>
     * 	  2.新增操作，判断仓库号是否存在</br>
     * 	  3.仓库号存在，提示错误信息</br>
     * 	  4.仓库号不存在，保存仓库信息</br>
     *    5.编辑操作，更新仓库信息
     * </p>
	 * 
	 * <p>Title: insert</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		id:主键
	 * 		type:操作类型 add=新增，edit=编辑
	 * 		wareHouseNo:仓库号
	 * 		wareHouseName:仓库名称
	 * 		wareHouseType:仓库类型
	 * 		priceType:计价方式
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo insert(EiInfo inInfo) {
   	 	//封装参数
   	 	SiWarehouse kcst01 = new SiWarehouse();
		kcst01.fromMap(inInfo.getAttr());
        //新增
        if (inInfo.getString("type").equals("add")) {
        	//判断仓库号是否已存在，存在提示错误信息
       	 	int count = super.count("SIWH01.isExistwareHouseNo",inInfo.getString("wareHouseNo"));
       	 	if(count > 0){
            	inInfo.setMsg("仓库编号已经存在");
            	inInfo.setStatus(-1);
            	return inInfo;
       	 	}
        	//保存仓库信息
        	kcst01.setId(UUID.randomUUID().toString());
            kcst01.setFreezeFlag("N");//冻结标记
            kcst01.setRecCreateTime(DateUtils.curDateTimeStr19());
            kcst01.setRecCreator(UserSession.getUser().getUsername());
            kcst01.setDataGroupCode(SiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
            dao.insert("SIWH01.insert", kcst01);
            return inInfo;
        } else {//编辑
            kcst01.setRecReviseTime(DateUtils.curDateTimeStr19());
            kcst01.setRecRevisor(UserSession.getUser().getUsername());
            dao.update("SIWH01.update", kcst01);
            return inInfo;
        }
	}

}
