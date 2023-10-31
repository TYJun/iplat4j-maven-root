package com.baosight.wilp.si.common;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 仓库数据隔离工具类
 * @ClassName: WareHouseDataSplitUtils
 * @Package com.baosight.wilp.si.common
 * @date: 2023年07月19日 17:48
 */
public class WareHouseDataSplitUtils {

    /**
     * 获取人员关联的仓库
     * @Title: getUserWareHouse
     * @param workNo workNo 工号
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @throws
     **/
    public static List<Map<String, String>>  getUserWareHouse(String workNo) {
        List<Map<String, String>> list = new ArrayList<>();
        EiInfo invoke = SiUtils.invoke(null, "SIWH01", "queryWareHouse", new String[]{"workNo"}, workNo);
        EiBlock result = invoke.getBlock("result");
        if(result == null || result.getRowCount() == 0) {
            return null;
        }
        List<Map> wList = result.getRows();
        for (Map warehouse : wList) {
            Map<String, String> map = new HashMap<>(2);
            map.put("wareHouseNo", SiUtils.isEmpty(warehouse.get("wareHouseNo")));
            map.put("wareHouseName", SiUtils.isEmpty(warehouse.get("wareHouseName")));
            list.add(map);
        }
        return list;
    }

    /**
     * 获取仓库号集合
     * @Title: getWareHouseNos
     * @param workNo workNo 工号
     * @return java.util.List<java.lang.String>
     * @throws
     **/
    public static List<String> getWareHouseNos(String workNo) {
        List<Map<String, String>> list = getUserWareHouse(workNo);
        if(list == null) { return new ArrayList<>(); };
        return list.stream().map(map -> map.get("wareHouseNo")).collect(Collectors.toList());
    }

    /**
     * 获取仓库号字符串
     * @Title: getWareHouseNosStr
     * @param workNo workNo 工号
     * @return java.lang.String
     * @throws
     **/
    public static String getWareHouseNosStr(String workNo) {
        List<String> wareHouseNos = getWareHouseNos(workNo);
        if(wareHouseNos.isEmpty()) { return ""; }
        return StringUtils.join(wareHouseNos, ",");
    }

    /**
     * 获取指定仓库对应的物资分类大类
     * @Title: getWareHouseMatRootType
     * @param workNo workNo
     * @return java.lang.String
     * @throws
     **/
    public static String getWareHouseMatRootType(String workNo) {
        List<String> wareHouseNos = getWareHouseNos(workNo);
        EiInfo invoke = SiUtils.invoke(null, "SIPZ03", "queryMatRootTypes",
                new String[]{"wareHouseNos"}, wareHouseNos);
        List<String> matRootTypes = SiUtils.toList(invoke.get("matRootTypes"), String.class);
        if(CollectionUtils.isEmpty(matRootTypes)) {
            return "no data";
        }
        List<String> list = new ArrayList<>();
        matRootTypes.forEach(rootType -> list.addAll(Arrays.asList(rootType.split(","))));
        return StringUtils.join(list.stream().distinct().collect(Collectors.toList()), ",");
    }

}
