package com.baosight.wilp.si.pk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;
import com.baosight.wilp.si.pk.domain.SiInven;

/**
 * 盘库管理页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>插入库存主表数据</p>
 * <p>更新库存主表数据</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIPK01.java
 * @ClassName:  ServiceSIPK01
 * @Package com.baosight.wilp.si.pk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 下午6:36:35 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIPK01 extends ServiceBase {
	
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo initLoad(EiInfo inInfo) {
		SiUtils.initQueryTime(inInfo, false);
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 20);
		return query(inInfo);
    }
	
	/**
	 * 页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		wareHouseNo:仓库号
	 * 		beginTime：盘库单生成日期起（>=）
	 * 		endTime:盘库单生成日期止（<=）
	 * @return  
	 * 		invenBillNo : 盘点单号
	 *		wareHouseNo : 仓库号
	 *		wareHouseName : 仓库
	 *		billMakeTime : 制单日期
	 *		billMakerName : 制单人员
	 *		billCheckTime : 审核日期
	 *		billCheckerName : 审核人员 
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		inInfo.set("inqu_status-0-wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(UserSession.getLoginName()));
    	inInfo.set("inqu_status-0-dataGroupCode", SiUtils.getDataGroupCode(UserSession.getLoginName()));
        EiInfo outInfo = super.query(inInfo, "SIPK01.query", new SiInven());
        return outInfo;
    }
	
	/**
	 * 插入盘库主表数据
	 * @Title: insertInven
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		inven：盘库主表对象
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo insertInven(EiInfo inInfo) {
		//获取参数
		SiInven inven = inInfo.get("inven") == null ? null : (SiInven)inInfo.get("inven");
		//判断参数是否为空
		if(inven == null){
			inInfo.setStatus(-1);
			inInfo.setMsg("插入盘库主表数据失败"); 
			return inInfo;
		} else {
			dao.insert("SIPK01.insert", inven);
		}
        return inInfo;
    }
	
	/**
	 * 更新盘库主表数据
	 * @Title: updateInven
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		inven：盘库主表对象
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo updateInven(EiInfo inInfo) {
		//获取参数
		SiInven inven = inInfo.get("inven") == null ? null : (SiInven)inInfo.get("inven");
		//判断参数是否为空
		if(inven == null){
			inInfo.setStatus(-1);
			inInfo.setMsg("更新盘库主表数据失败"); 
			return inInfo;
		} else {
			dao.update("SIPK01.update", inven);
		}
        return inInfo;
	}

	/**
	 * 撤销盘库
	 * <>删除盘库单，解冻仓库</>
	 * @Title: revocation
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo revocation(EiInfo inInfo) {
		//获取盘库单号
		String invenBillNo = inInfo.getString("invenBillNo");
		String wareHouseNo = inInfo.getString("wareHouseNo");
		//删除盘库单
		dao.delete("SIPK01.delete", invenBillNo);
		dao.delete("SIPK0101.delete", invenBillNo);
		//解冻仓库
		SiUtils.invoke(null, "SIWH01", "djOrJd", new String[]{"type", "wareHouseNo"}, "jd", wareHouseNo);
		return inInfo;
	}

}
