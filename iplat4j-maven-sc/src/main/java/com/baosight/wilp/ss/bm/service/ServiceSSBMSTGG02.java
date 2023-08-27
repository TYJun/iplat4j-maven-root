package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMStgg01;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * 订餐公告2service
 * 
 * @Title: ServiceSSBMSTGG02.java
 * @ClassName: ServiceSSBMSTGG02
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:27:02
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMSTGG02 extends ServiceBase{

    /**
     * 
     * 初始化查询
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return super.initLoad(inInfo, new SSBMStgg01());
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		//grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMStgg01.query", new SSBMStgg01());
		//追加noticeType
		//List<HashMap<String, Object>> query = dao.query("SSBMStgg01.getNoticeType", new HashMap<String, Object>());
		//outInfo.addBlock("noticeType").addRows(query);
		//outInfo.getBlock("typeName").setBlockMeta(new SSBMStgg01().eiMetadata);
		return outInfo;
	}


	/**
	 * 
	 * 删除公告数据
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		List<HashMap<String,Object>> rows = inInfo.getBlock("result").getRows();
		//判断是否有生效中的公告
		boolean flag = true;
		for (int i = 0; i < rows.size(); i++) {
			HashMap<String, Object> hashMap = rows.get(i);
			String isEffective = hashMap.get("isEffective")+"";
			if("1".equals(isEffective)) {
				flag = false;
			}
		}
		if(flag) {
			super.delete(inInfo, "SSBMStgg01.delete");
			outInfo.setMsg("操作成功！");
		}else {
			outInfo.setMsg("所选项中有正在生效的数据！");
		}
		return outInfo;
	}


	/**
	 * 
	 * 新增公告数据
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		//获取传参
		List<HashMap<String,Object>> rows = inInfo.getBlock("result").getRows();
		HashMap<String,Object> map = rows.get(0);
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = smf.format(new Date());
		//修改数据
		map.put("effectiveDate",format);
		map.put("recCreateTime",format);
		map.put("inputDate",format.substring(0, 10));
		map.put("id",UUIDUtils.getUUID32());
		EiInfo outInfo = super.insert(inInfo, "SSBMStgg01.insert");
		return outInfo;
	}
	

	/**
	 * 
	 * 编辑数据
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo update(EiInfo inInfo) {
		Map attr = inInfo.getAttr();
		//接收弹窗数据
		HashMap<String,Object> updateMap = (HashMap<String, Object>) attr.get("update");
		if(updateMap != null && updateMap.size() > 0) {
			SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String format = smf.format(new Date());
			//修改数据
			updateMap.put("effectiveDate",format);
			updateMap.put("recCreateTime",format);
			updateMap.put("inputDate",format.substring(0, 10));
			ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			list.add(updateMap);
			//将数据填充到result
			inInfo.addBlock("result").addRows(list);
		}
		//更新数据
		EiInfo outInfo = super.update(inInfo, "SSBMStgg01.update"); 
		outInfo.setMsg("更新成功！");
		return outInfo;
	}

	/**
	 * 
	 * 获取公告类型
	 *
	 * @Title: getNoticeType 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:28:17
	 */
	public EiInfo getNoticeType(EiInfo inInfo) {
		List<HashMap<String, Object>> query = dao.query("SSBMStgg01.getNoticeType", new HashMap<String, Object>());
        inInfo.addBlock("typeName").addRows(query);
        inInfo.getBlock("typeName").setBlockMeta(new SSBMStgg01().eiMetadata);
        return inInfo;
	}
}
