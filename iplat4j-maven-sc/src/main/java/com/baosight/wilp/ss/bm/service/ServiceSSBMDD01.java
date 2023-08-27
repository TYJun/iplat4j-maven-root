package com.baosight.wilp.ss.bm.service;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMDD01;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 订餐数据字典管理service
 * 
 * @Title: ServiceSSBMDD01.java
 * @ClassName: ServiceSSBMDD01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:18:43
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMDD01 extends ServiceBase{
	
    private static Map map = new HashMap();
	

    /**
     * 
     * 初始化查询
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
            toLimit(inInfo, 100);
            if(inInfo.getAttr().get("flag") == null) {
            	map = new HashMap();
            }
            EiInfo outInfo = super.query(inInfo, "SSBMDD01.query", new SSBMDD01());
            EiBlock result2 = outInfo.addBlock("result2");
            result2.addBlockMeta(new SSBMDD01().eiMetadata);
            List<SSBMDD01> result2Param = dao.query("SSBMDD01.queryMealType", map);
            outInfo.getBlock("result2").setRows(result2Param);
            return outInfo;
    }
    
    

    /**
     * 
     * 查询数据字典分类子项
     *
     * @Title: queryMealType 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午4:19:48
     */
    public EiInfo queryMealType(EiInfo inInfo) {
        EiInfo initLoad = null;
        String object = inInfo.get("result").toString();
        if(StringUtil.isNotEmpty(object)) {
            String subString = subString(object,"[","]");
            map.put("id", subString);
            inInfo.set("flag", "queryInfo");
            initLoad = initLoad(inInfo);
        }else {
            inInfo.setMsg("请选择需要查看的数据字典分类");
            inInfo.setStatus(-1);
        }
        return initLoad;
    }
    
    

    /**
     * 
     * 新增数据字典分类
     *
     * @Title: insertMealTypeGroup 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午4:20:12
     */
    public EiInfo insertMealTypeGroup(EiInfo inInfo) {
        String paramStr = inInfo.get("result").toString();
        String subString = subString(paramStr,"[{","}]");

        try {
            if(StringUtil.isNotEmpty(subString)) {
                Map<String, Object> stringToMap = getStringToMap(subString);
                stringToMap.put("id", UUIDUtils.getUUID32());
                dao.insert("SSBMDD01.insertMealTypeGroup", stringToMap);
            }else {
                inInfo.setMsg("请选择需要新增的单条数据");
                inInfo.setStatus(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            inInfo.setMsg("出现异常,请检查参数" + e.getClass().getName());
            inInfo.setStatus(-1);
        }
        return inInfo;
    }
    
    
	/**
	 * grid数据集查询
	 */
    @Override
    public EiInfo query(EiInfo inInfo) {
        Map<String,EiBlock> blocks = inInfo.getBlocks();
		if(blocks.containsKey("result2")) {
		    //获取分页参数
		    EiBlock eiBlock = blocks.get("result2");
		    Map attr = eiBlock.getAttr();
		    Object object = attr.get("limit");
		    Integer limit = Integer.valueOf(object.toString());
		    //Map param = new HashMap();
		    //获取查询条件
		    //param.put("limit", limit);
		    map.put("limit", limit);
		    //用dao查询
		    List<SSBMDD01> query = dao.query("SSBMDD01.queryMealType", map);
		    //map.remove("limit", limit);
		    map.remove("limit");
		    //返回查询结果集
		    List list = new ArrayList();
		    EiBlock eiBlock2 = new EiBlock(new SSBMDD01().eiMetadata);
		    eiBlock2.setRows(list);
		    Map<String,EiBlock> paramMap = new HashMap<String,EiBlock>();
		    blocks.put("result2", eiBlock2);
		    inInfo.getBlock("result2").setRows(query);
		    return inInfo;
		}
        EiInfo outInfo = super.query(inInfo, "SSBMDD01.query", new SSBMDD01());
        return outInfo;
    }
    

    /**
     * 
     * 新增数据字典
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        Object object = map.get("id");
        EiBlock block = inInfo.getBlock("result2");
        List rows = block.getRows();
        Map paramMap = (Map)rows.get(0);
        paramMap.put("id", UUIDUtils.getUUID32());
        paramMap.put("groupid", object);
        try {
        	dao.insert("SSBMDD01.insert", paramMap);
        	inInfo.setStatus(1);
        	inInfo.setMsg("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setStatus(-1);
        	inInfo.setMsg("操作失败"+e.getMessage());
		}
        
        return inInfo;
    }
    
    

    /**
     * 
     * 更新数据字典
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
//        EiInfo outInfo = super.update(inInfo, "SSBMDD01.update");
//        return outInfo;
    	List rows = inInfo.getBlock("result2").getRows();
    	inInfo.addBlock("result").addRows(rows);
        EiInfo outInfo = super.update(inInfo, "SSBMDD01.update");
        return outInfo;
    }
    

    /**
     * 
     * 删除数据字典
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        EiBlock block = inInfo.getBlock("result2");
        if(block==null) {
             EiInfo outInfo = super.delete(inInfo, "SSBMDD01.delete");
             HashMap<String, Object> hashMap= (HashMap<String, Object>) inInfo.getBlock("result").getRows().get(0);
             dao.delete("SSBMDD01.deleteMealTypeByTypeGroupId",hashMap.get("id").toString());
             outInfo.setMsg("删除成功");
             return outInfo;
        }
        EiInfo typeInfo = new EiInfo();
        typeInfo.addBlock("result").addRows(block.getRows());
        EiInfo delete = super.delete(typeInfo, "SSBMDD01.deleteMealType");
        /*Map paramMap = (Map)rows.get(0);
        String id = paramMap.get("id").toString();
        System.out.println("id:"+id);
        int delete = dao.delete("SSBMDD01.deleteMealType", id);
        */
        if(delete.getStatus() == 1) {
            inInfo.setMsg("删除成功");
            return inInfo;
        }else {
            inInfo.setMsg("删除失败");
            inInfo.setStatus(-1);
        }
        return inInfo;
    }
    
    
    /**
     * 
     * 截取指定String工具类
     *
     * @Title: subString 
     * @param str
     * @param strStart
     * @param strEnd
     * @return 
     * @return: String
     */
    public static String subString(String str, String strStart, String strEnd) {

    	String result = null;
        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return result;
        }
        if (strEndIndex < 0) {
            return result;
        }
        /* 开始截取 */
        result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }
    
    
    /**
     * 
     * String转map
     * @param str
     * @return
     */
    public static Map<String,Object> getStringToMap(String str){
        String replaceAll = str.replaceAll(" ", "");
        //根据逗号截取字符串数组
        String[] str1 = replaceAll.split(",");
        //创建Map对象
        Map<String,Object> map = new HashMap<>();
        //循环加入map集合
        for (int i = 0; i < str1.length; i++) {
            //根据"="截取字符串数组
            String[] str2 = str1[i].split("=");
            //str2[0]为KEY,str2[1]为值
            map.put(str2[0],str2[1]);
        }
        return map;
    }
    
    /**
     * 
     * 修改分页
     *
     * @Title: test 
     * @param inInfo
     * @param i 
     * @return: void
     */
    public void toLimit(EiInfo inInfo,int i) {
        HashMap<String, Integer> hs = new HashMap<String, Integer>();
        HashMap<String, EiBlock> has = new HashMap<String, EiBlock>();
        hs.put("limit", i);
        EiBlockMeta e = new EiBlockMeta();
        EiBlock eib = new EiBlock(e);
        eib.setAttr(hs);
        has.put("result", eib);
        inInfo.setBlocks(has);
    }

	
}
