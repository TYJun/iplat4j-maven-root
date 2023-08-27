package com.baosight.wilp.df.common.util;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.tools.CollectionUtils;

import java.util.*;

/**
 * 设备管理工具类
 * All rights Reserved, Designed By www.bonawise.com
 * @author fangjian
 * @version V5.0.2
 * @Title: DfUtils.java
 * @ClassName: DfUtils
 * @Package com.baosight.wilp.df.common.util
 * @Description: 设备管理工具类
 * @date: 2021年11月12日 17:21
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class DFUtils {

    /**
     * 执行本地服务调用
     * @Title: invoke
     * @Description: 执行本地服务调用
     * @param:  inInfo ： inInfo对象
     * @param:  serviceName ： 服务名
     * @param:  methodName ： 方法名
     * @param:  paramNames ： 参数名数组
     * @param:  params：参数
     * @return: EiInfo  ： 返回inInfo对象
     * @throws
     */
    public static EiInfo invoke(EiInfo inInfo, String serviceName, String methodName, String[] paramNames, Object... params) {
        EiInfo info = null;
        if(inInfo != null){
            info = inInfo;
        } else {
            info = new EiInfo();
        }
        //参数赋值
        if(paramNames != null){
            for (int i = 0; i < paramNames.length; i++) {
                info.set(paramNames[i], params[i]);
            }
        }
        //执行方法
        info.set(EiConstant.serviceName, serviceName);
        info.set(EiConstant.methodName, methodName);
        EiInfo outInfo = XLocalManager.call(info);
        return outInfo;
    }

    public static EiInfo invoke(EiInfo inInfo, String serverId, String[] paramNames, Object... params) {
        EiInfo info = null;
        if(inInfo != null){
            info = inInfo;
        } else {
            info = new EiInfo();
        }
        //参数赋值
        if(paramNames != null){
            for (int i = 0; i < paramNames.length; i++) {
                info.set(paramNames[i], params[i]);
            }
        }
        //执行方法
        info.set(EiConstant.serviceId, serverId);
        EiInfo outInfo = XLocalManager.call(info);
        return outInfo;
    }

    /**
     * 判断一级模块是否存在
     * @Title: isExistModule
     * @Description: 判断一级模块是否存在
     * @param moduleCode moduleCode : 一级模块编码
     * @return boolean
     * @throws
     **/
    public static boolean isExistModule(String moduleCode) {
        //设置参数
        EiInfo eiInfo = new EiInfo();
        String serviceId = "S_ED_31";
        eiInfo.set(EiConstant.serviceId,serviceId);
        eiInfo.set("moduleEnames1",moduleCode);
        //调用接口
        EiInfo outInfo = XServiceManager.call(eiInfo);
        //status=1模块存在 status=0模块不存在
        int status = outInfo.getStatus();
        return status > 0 ? true : false;
    }

    /**
     * @Title: isEmpty
     * @Description: 判断是否为空（String, Map, List）
     * @param object object
     * @return boolean
     * @throws
     **/
    public static boolean isEmpty(Object object) {
        if(object == null) { return true; }

        //字符串
        if(object instanceof String) {
            return StringUtils.isBlank(object.toString());
        }

        //Map
        if(object instanceof Map) {
            return MapUtils.isEmpty((Map) object);
        }

        //List
        if(object instanceof List) {
            return CollectionUtils.isEmpty((List) object);
        }
        return false;
    }





    /**
     * List转EiBlock
     * @Title: ObjectToBlock
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param data
     * @param:  @param blockId
     * @param:  @return
     * @return: EiBlock
     * @throws
     */
    public static EiBlockMeta createBlockMeta(Map<String,Object> map) {
        EiBlockMeta eiBlockMeta = new EiBlockMeta();
        if(!map.isEmpty()){
            for(String key : map.keySet()){
                eiBlockMeta.addMeta(new EiColumn(key));
            }
        }
        return eiBlockMeta;
    }



    /**
     * 获取页面传过来的参数
     * @Title: buildParamter
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * @param:  @param inBlock
     * @param:  @param outBlock
     * @param:  @return
     * @return: Map<String,Object>
     * @throws
     */
    @SuppressWarnings("unchecked")
    public static Map<String,Object> buildParamter(EiInfo inInfo, String inBlock, String outBlock){
        Map<String,Object> param = new HashMap<>();
        //获取参数
        EiBlock block = inInfo.getBlock(inBlock);
        if(block != null && block.getRowCount() > 0){
            param = block.getRow(0);
        }
        //获取分页信息
        EiBlock result = inInfo.getBlock(outBlock);
        Integer offset = 0;Integer limit = 10;
        if(result != null && !result.getAttr().isEmpty()){
            offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
            limit = result.getAttr().get("limit") == null ? 0 : (Integer) result.getAttr().get("limit");
        }
        param.put("offset", offset);
        param.put("limit", limit);
        return param;
    }

    /**
     * 将map切成段     *
     * @param chunkMap 被切段的 map
     * @param chunkNum 每段的大小
     * @param <k>      map 的 key类 型
     * @param <v>      map 的value 类型
     * @return List
     */
    public static <k, v> List<Map<k, v>> mapSplit(Map<k, v> chunkMap, int chunkNum) {
        if (chunkMap == null || chunkNum <= 0) {
            List<Map<k, v>> list = new ArrayList<>();
            list.add(chunkMap);
            return list;
        }
        Set<k> keySet = chunkMap.keySet();
        Iterator<k> iterator = keySet.iterator();
        int i = 1;
        List<Map<k, v>> total = new ArrayList<>();
        Map<k, v> tem = new HashMap<>(16);
        while (iterator.hasNext()) {
            k next = iterator.next();
            tem.put(next, chunkMap.get(next));
            if (i == chunkNum) {
                total.add(tem);
                tem = new HashMap<>();
                i = 0;
            }
            i++;
        }
        if (!tem.isEmpty()) {
            total.add(tem);
        }
        return total;
    }

}
