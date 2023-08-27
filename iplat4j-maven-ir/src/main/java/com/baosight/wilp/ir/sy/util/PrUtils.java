package com.baosight.wilp.ir.sy.util;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.ir.sy.util
 * @ClassName PrUtils
 * @Description 复写框架里面的PrUtils工具类
 * @Author chunchen2
 * @Date 2022/1/12 15:19
 * @Version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2022/1/12 15:19
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class PrUtils {

    private static SimpleDateFormat nationDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * List转EiBlock @Title: ObjectToBlock @Description:
     * TODO(这里用一句话描述这个方法的作用) @param: @param data @param: @param
     * blockId @param: @return @return: EiBlock @throws
     */
    public static EiBlockMeta createBlockMeta(Map<String, Object> map) {
        EiBlockMeta eiBlockMeta = new EiBlockMeta();
        if (!map.isEmpty()) {
            for (String key : map.keySet()) {
                eiBlockMeta.addMeta(new EiColumn(key));
            }
        }
        return eiBlockMeta;
    }

    public static String getDataGroup() {
        return "IPLAT";
    }

    /**
     * 将格林威治时间转为北京时间 @Title: timeZoneExchange @Description:
     * TODO(这里用一句话描述这个方法的作用) @param: @param date @param: @return @return:
     * String @throws
     */
    public static String timeZoneExchange(String date) {
        Calendar cal = Calendar.getInstance();
        try {
            long time = nationDateFormat.parse(date).getTime();
            cal.setTimeInMillis(time);
            cal.add(Calendar.HOUR, +8);
        } catch (ParseException e) {
            return date;
        }
        return dateFormat.format(cal.getTime());
    }

    /**
     * 获取页面传过来的参数 @Title: buildParamter @Description:
     * TODO(这里用一句话描述这个方法的作用) @param: @param inInfo @param: @param
     * inBlock @param: @param outBlock @param: @return @return:
     * Map<String,Object> @throws
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> buildParamter(EiInfo inInfo, String inBlock, String outBlock) {
        Map<String, Object> param = new HashMap<>();
        // 获取参数
        EiBlock block = inInfo.getBlock(inBlock);
        if (block != null && block.getRowCount() > 0) {
            param = block.getRow(0);
        }
        // 获取分页信息
        EiBlock result = inInfo.getBlock(outBlock);
        Integer offset = 0;
        Integer limit = 10;
        if (result != null && !result.getAttr().isEmpty()) {
            offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
            limit = result.getAttr().get("limit") == null ? 0 : (Integer) result.getAttr().get("limit");
        }
        param.put("offset", offset);
        param.put("limit", limit);
        return param;
    }

    /**
     * 构建返回结果EiInfo @Title: BuildOutEiInfo @Description:
     * TODO(这里用一句话描述这个方法的作用) @param: @param inInfo @param: @param
     * outBlock @param: @param list @param: @param count @param: @return @return:
     * EiInfo @throws
     */
    @SuppressWarnings("unchecked")
    public static EiInfo BuildOutEiInfo(EiInfo inInfo, String outBlock, EiBlockMeta eiMetadata, List<?> list,
                                        int count) {
        EiBlock result = inInfo.getBlock(outBlock);
        if (result != null && !result.getAttr().isEmpty()) {
            result.setRows(list);
            result.getAttr().put("count", count);
            result.getAttr().put("showCount", "true"); // 解决部分以页面点击查询后无法翻页问题
        } else {
            EiBlock resultBlock = new EiBlock(outBlock);
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
        return inInfo;
    }

    /**
     * 前端参数转map
     *
     * @param inInfo
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> changeToMap(EiInfo inInfo, String blockName, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        Map param = inInfo.getAttr();
        // 获取分页信息
        EiBlock result = inInfo.getBlock(blockName);
        Integer offset = 0;
        Integer limit = 10;
        if (result != null && !result.getAttr().isEmpty()) {
            offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
            limit = result.getAttr().get("limit") == null ? 0 : (Integer) result.getAttr().get("limit");
        }
        // APP分页
        String page = inInfo.getString("page");
        if (StringUtils.isNotBlank(page)) {
            offset = (Integer.parseInt(page) - 1) * limit;
        }

        map.put("offset", offset.toString());
        map.put("limit", limit.toString());
        list.forEach(e -> map.put(e, (String) param.get(e)));
        return map;
    }

    /**
     * 将查询出的数据以eiinfo的格式还回去
     *
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static EiInfo listToEiInfo(List list, String blockName) {
        EiInfo outInfo = new EiInfo();

        EiBlock eiBlock = new EiBlock(new EiBlockMeta());
        eiBlock.setRows(list);
        HashMap<String, EiBlock> hashMap = new HashMap<String, EiBlock>();
        hashMap.put(blockName, eiBlock);
        outInfo.setBlocks(hashMap);
        if (!CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("查询成功");
        } else {
            outInfo.setMsg("未获取到数据");
        }
        return outInfo;
    }

    /**
     * 将查询出的数据以eiinfo的格式还回去
     *
     * @param
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static EiInfo mapToEiInfo(Map map) {
        EiInfo info = new EiInfo();
        info.setAttr(map);
        return info;
    }

    public static String getConfigure() {

        String projectSchema = PlatApplicationContext.getProperty("projectSchema");

        return projectSchema;
    }

    public static String getIplatConfigure() {

        String platSchema = PlatApplicationContext.getProperty("platSchema");

        return platSchema;
    }
}
