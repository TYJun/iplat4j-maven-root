package com.baosight.wilp.si.yj.service;

import java.util.HashMap;
import java.util.List;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.kc.domain.SiStorge;
import org.apache.commons.lang3.StringUtils;

/**
 * 库存预警保存子页面Service
 *
 * <p>页面加载</p>
 * <p>存库存预警</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIYJ0101.java
 * @ClassName:  ServiceSIYJ0101
 * @Package com.baosight.wilp.si.yj.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 上午10:11:08
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIYJ0101 extends ServiceBase {

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
    public EiInfo initLoad(EiInfo inInfo) {
		String ids = inInfo.getString("ids");
		if(StringUtils.isNotBlank(ids)) {
			List<SiStorge> list = dao.query("SIKC01.query", new HashMap(2) {{ put("ids", ids.split(","));}});
			inInfo.setRows(EiConstant.resultBlock, list);
		}
        return inInfo;
    }

	/**
	 * 保存库存预警
	 *
	 * <p>更新库存主表的最低和最高库存量</p>
	 *
	 * @Title: saveStockEarlyWarning
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		wareHouseNo：仓库号
	 * 		wareHouseName：仓库名称
	 * 		rows:物资集合
	 * 			matTypeName : 物资分类名称
	 *			matNum : 物资编码
	 *			matName : 物资名称
	 *			matModel : 物资型号
	 *			matSpec : 物资规格
	 *			unit : 单位
	 *			unitName : 单位
	 *			minNum : 最低库存量
	 *			maxNum : 最高库存量
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo saveStockEarlyWarning(EiInfo inInfo) {
		//获取参数
		String wareHouseNo = inInfo.getString("wareHouseNo");
		List<HashMap<String,String>> list = (List<HashMap<String,String>>)inInfo.get("rows");
		//遍历，保存
		list.forEach(map ->{
			map.put(wareHouseNo, wareHouseNo);
			dao.update("SIYJ01.update",map);
		});
		return inInfo;
	}

}
