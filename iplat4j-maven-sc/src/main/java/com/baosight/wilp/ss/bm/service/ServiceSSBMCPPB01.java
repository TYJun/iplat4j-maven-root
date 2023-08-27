package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.*;
import com.baosight.wilp.ss.bm.utils.CalendarUtils;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.util.*;


/**
 * 
 * 菜品排班管理service
 * 
 * @Title: ServiceSSBMCPPB01.java
 * @ClassName: ServiceSSBMCPPB01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:46:01
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMCPPB01 extends ServiceBase {


    /**
     * 
     * 初始化加载
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMCpfl01());
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		// canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		List<HashMap<String, Object>> listCanteenType = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenType);

		//menuType:菜品类型
		List<HashMap<String,Object>> listMenuType = dao.query("SSBMCpfl01.getMenuTypeData", paramMap);
		initLoad.addBlock("menuType").addRows(listMenuType);
		return initLoad;
	}

	/**
	 * 查询菜品排班
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		// grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMCppb01.query", new SSBMCppb01());
		return outInfo;
	}
	

	/**
	 * 
     * 查询菜品信息
	 *
	 * @Title: queryMenuInfo 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:47:20
	 */
	public EiInfo queryMenuInfo(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		//获取传参
		Map attr = inInfo.getAttr();
		List<HashMap<String,Object>> listMenuType  = dao.query("SSBMCppb01.queryOrderMenu", attr);
		outInfo.addBlock("description").addRows(listMenuType);
		return outInfo;
	}
	

	/**
	 * 
     * 查询食堂信息
	 *
	 * @Title: queryCanteenData 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:47:32
	 */
	public EiInfo queryCanteenData(EiInfo inInfo) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		// canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		List<HashMap<String, Object>> listCanteenType = dao.query("SSBMStxx01.getCanteenData", paramMap);
		inInfo.addBlock("canteenData").addRows(listCanteenType);
		return inInfo;
	}
	

	/**
	 * 
	 * 查询餐次信息
	 *
	 * @Title: queryMealNum 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:48:14
	 */
	public EiInfo queryMealNum(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSBMStxx01.query", new SSBMStxx01());
		EiBlock block = outInfo.getBlock("result");
		Map row = block.getRow(0);
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		if(row != null && !row.isEmpty()) {
			String[] mealNum = row.get("mealNum").toString().split(",");
			String[] mealNumName = row.get("mealNumName").toString().split(",");
			for (int i = 0; i < mealNum.length; i++) {
				Map<String,String> map = new HashMap<String, String>();
				map.put("typeCode", mealNum[i]);
				map.put("typeName", mealNumName[i]);
				listMap.add(map);
			}
		}
		outInfo.addBlock("mealNum").addRows(listMap);
		return outInfo;
	}


	/**
	 * 
     * 删除排班信息
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			List<HashMap<String, Object>> rows = inInfo.getBlock("result").getRows();
			outInfo = super.delete(inInfo, "SSBMCppb01.delete");
			outInfo.setMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("操作失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}


	/**
	 * 
	 * 复制排班
	 *
	 * @Title: copyMenu 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:48:44
	 */
	public EiInfo copyMenu(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map attr = inInfo.getAttr();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String copyDate = StringUtils.toString(attr.get("copyDate"));
			String mealDate = StringUtils.toString(attr.get("mealDate"));
			String canteenNum = StringUtils.toString(attr.get("canteenNum"));
			String operateCode = "meal";
			if(!StringUtils.isNotEmpty(copyDate)||!StringUtils.isNotEmpty(canteenNum)||!StringUtils.isNotEmpty(operateCode)){
				outInfo.setMsg("请求参数异常!");
				return outInfo;
			}else if(!("meal".equals(operateCode)||"comboMeal".equals(operateCode))){
				outInfo.setMsg("请求参数异常!");
				return outInfo;
			}
			map.put("canteenNum", canteenNum);
			map.put("operateCode", operateCode);
			
			long current=System.currentTimeMillis();//当前时间毫秒数
		    long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
			Date date = CalendarUtils.String2Date(copyDate);
			long tt= date.getTime() - zero ;
			//long ttt=tt-24*60*60*1000;
			if(tt<0){
				outInfo.setMsg("无法复制,选择的时间不能是今天以前的时间!");
				return outInfo;
			}else{
				//复制的目标日期
				 
				map.put("bDate", copyDate);
				
				//判断目标日期否已经排班,如果已经有排班,停止复制
				List<SSBMCppb03> list2= new ArrayList<SSBMCppb03>();
				list2 = dao.query("SSBMCppb01.queryMenuScheListWeek", map);
				if(list2.size()!=0){
					System.out.println("食堂在目标时间已有排班,不能复制!");
					outInfo.setMsg("食堂在今天已有排班,不能复制!");
					return outInfo;
				}
				map.put("bDate", mealDate);
				//获取本周的所有排班菜品
				List<SSBMCppb03> list= new ArrayList<SSBMCppb03>();
			    list = dao.query("SSBMCppb01.queryMenuScheListWeek", map);
 				if(list.size() == 0){
					System.out.println("此食堂在今天尚未排班,不能复制!");
					outInfo.setMsg("此食堂在今天尚未排班,请先给今天排班后再复制!");
					return outInfo;
				}
				
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setId(UUID.randomUUID().toString());
					list.get(i).setMealDate(copyDate);
					list.get(i).setNum(list.get(i).getNum());
					list.get(i).setSurplus(list.get(i).getNum());
				}
				inInfo.addBlock("result").addRows(list);
				outInfo = super.insert(inInfo, "SSBMCppb01.copyScheList");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("复制失败");
		}
		return outInfo;
	}
	

	/**
	 * 新增排班数据
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			// 获取传参
			Map attr = inInfo.getAttr();
			// 接收弹窗数据
			ArrayList<HashMap<String, Object>> submitList = (ArrayList<HashMap<String, Object>>) attr.get("submit");
			if (submitList != null && submitList.size() > 0) {
				for (int i = 0; i < submitList.size(); i++) {
					HashMap<String, Object> map = submitList.get(i);
					// 修改数据
					map.put("id", UUIDUtils.getUUID32());
				}
				inInfo.addBlock("result").addRows(submitList);
				outInfo = super.insert(inInfo, "SSBMCppb01.insert");
				outInfo.setMsg("保存成功！");
			} else {
				outInfo.setMsg("数据提交失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("保存失败！");
		}
		return outInfo;
	}


	/**
	 * 编辑数据
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo update(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			Map attr = inInfo.getAttr();
			// 接收弹窗数据
			ArrayList<HashMap<String, Object>> updateList = (ArrayList<HashMap<String, Object>>) attr.get("submit");
			if (updateList != null && updateList.size() > 0) {
				// 将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
				// 更新数据
				outInfo = super.update(inInfo, "SSBMCpfl01.update");
				outInfo.setMsg("更新成功！");
			} else {
				outInfo.setMsg("数据提交失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("更新失败！");
		}
		return outInfo;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *	查询树节点
	 * @Title: queryTree
	 * @Param inInfo
	 * @return: EiInfo
	 */
	public EiInfo queryTree(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSBMCppb02.query", new Tdmcm02());
		return outInfo;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *	查询树信息
	 * @Title: queryTreeNode
	 * @Param inInfo
	 * @return: EiInfo
	 */
	public EiInfo queryTreeNode(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSBMCppb02.queryTree", new EiBlockMeta());
		String pEname = outInfo.getCellStr(EiConstant.queryBlock, 0, "node");
		outInfo.getBlocks().put(pEname, outInfo.getBlock(EiConstant.resultBlock));
		outInfo.getBlocks().remove(EiConstant.resultBlock);
		return outInfo;
	}
	
	/**
	 * 获取日期
	 */
	public EiInfo getWeekDays(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		
		ArrayList<String> thisWeekdays = CalendarUtils.getThisWeekdays();
		
		ArrayList<String> nextWeekDates = CalendarUtils.getNextWeekDates();
		
		String blockId = inInfo.getString(EiConstant.queryBlock + EiConstant.separator + "0-node");
		// 返回的的 blockId 的名称是传入的结点其值
		// 如：inqu_status-0-node => "$", 则返回的的 block 名称是"$"
		outInfo.addBlock(creatTreeNode(blockId,thisWeekdays,nextWeekDates));
		return outInfo;
	}

	/**
	 * 创建时间节点树
	 * @param blockId
	 * @param thisWeekDays
	 * @param nextWeekDays
	 * @return
	 */
	private static EiBlock creatTreeNode(String blockId, ArrayList<String> thisWeekDays,ArrayList<String> nextWeekDays) {
		EiBlock block = new EiBlock(blockId);
		block.addMeta(new EiColumn("label"));
		block.addMeta(new EiColumn("parent"));
		block.addMeta(new EiColumn("text"));
		block.addMeta(new EiColumn("hasChildren"));
		//本周
		block.setCell(0, "value", "thisWeek");
		block.setCell(0, "pid", 0);
		block.setCell(0, "text", "本周");
		block.setCell(0, "isLeaf", 0);
		//下周
		block.setCell(1, "value", "nextWeek");
		block.setCell(1, "pid", 0);
		block.setCell(1, "text", "下周");
		block.setCell(1, "isLeaf", 0);
		int start = 2;
		for (int i = 0; i < thisWeekDays.size(); i++) {
			String weekDay = thisWeekDays.get(i);
			block.setCell(i + start, "value", weekDay.split(" ")[1]);
			block.setCell(i + start, "pid", "thisWeek");
			block.setCell(i + start, "text", weekDay);
			block.setCell(i + start, "isLeaf", 1);
		}
		start = thisWeekDays.size()+2;
		for (int i = 0; i < nextWeekDays.size(); i++) {
			String weekDay = nextWeekDays.get(i);
			block.setCell(i + start, "value", weekDay.split(" ")[1]);
			block.setCell(i + start, "pid", "nextWeek");
			block.setCell(i + start, "text", weekDay);
			block.setCell(i + start, "isLeaf", 1);
		}

		return block;
	}
	
}
