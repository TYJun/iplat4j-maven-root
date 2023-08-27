package com.baosight.wilp.ir.sy.util;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.ir.sy.util
 * @ClassName EiInfoUtils
 * @Description EiInfo处理的一些工具类
 * @Author chunchen2
 * @Date 2022/1/14 14:33
 * @Version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2022/1/14 14:33
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class EiInfoUtils {

    /**
     * @Title createBlockMeta
     * @Author chunchen2
     * @Description // 从Map里面获取key
     * @Date 14:41 2022/1/14
     * @param map
     * @return com.baosight.iplat4j.core.ei.EiBlockMeta
     * @throws
     **/
    public static EiBlockMeta createBlockMeta(Map<String, Object> map){
        EiBlockMeta eiBlockMeta = null;

        if(null != map && !map.isEmpty()){
            eiBlockMeta = new EiBlockMeta();
            for (String key : map.keySet()) {
                eiBlockMeta.addMeta(new EiColumn(key));
            }
        }

        return eiBlockMeta;
    }

    /**
     * @Title buildOutEiInfo
     * @Author chunchen2
     * @Description // 构建返回的EiInfo, 将List数据添加到outBlockName这个名字的block块里面
     * @Date 14:53 2022/1/14
     * @param inInfo
     * @param outBlockName  EiInfo块名称
     * @param eiMetadata
     * @param list    数据list，这里是你要返回数据
     * @param count  数据库里面的总数，这里如果没有分页，就是list.size()
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public static EiInfo buildOutEiInfo(EiInfo inInfo, String outBlockName, EiBlockMeta eiMetadata, List<?> list,
                                         int count){
        EiBlock resultEiBlock = inInfo.getBlock(outBlockName);
        if(null == list || list.size() == 0){ // 没有数据
            resultEiBlock = new EiBlock(outBlockName);
            resultEiBlock.setBlockMeta(eiMetadata ==null ? new EiBlockMeta() : eiMetadata);
            resultEiBlock.addRows(list);
            inInfo.addBlock(resultEiBlock);
        } else {
            if (resultEiBlock != null && !resultEiBlock.getAttr().isEmpty()) {
                resultEiBlock.setRows(list);
                resultEiBlock.getAttr().put("count", count);
                resultEiBlock.getAttr().put("showCount", "true"); // 解决部分以页面点击查询后无法翻页问题
            } else {
                EiBlock resultBlock = new EiBlock(outBlockName);
                resultBlock.setBlockMeta(eiMetadata);
                resultBlock.addRows(list);
                Map<String, Object> rAttr = new HashMap<>();
                rAttr.put("count", count);
                rAttr.put("offset", 0);
                if (list.size() >= 10) {
                    rAttr.put("limit", list.size());
                } else {
                    rAttr.put("limit", 10);
                }
                resultBlock.setAttr(rAttr);
                inInfo.addBlock(resultBlock);
            }
        }

        return inInfo;
    }
}
