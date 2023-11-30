package com.baosight.wilp.rm.common;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.common.util.CommonUtils;
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
        EiInfo invoke = RmUtils.invoke("SIWH01", "queryWareHouse", Arrays.asList("workNo"), workNo);
        EiBlock result = invoke.getBlock("result");
        if(result == null || result.getRowCount() == 0) {
            return null;
        }
        List<Map> wList = result.getRows();
        for (Map warehouse : wList) {
            Map<String, String> map = new HashMap<>(2);
            map.put("wareHouseNo", RmUtils.toString(warehouse.get("wareHouseNo")));
            map.put("wareHouseName", RmUtils.toString(warehouse.get("wareHouseName")));
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
        EiInfo invoke = RmUtils.invoke("SIPZ03", "queryMatRootTypes",
                Arrays.asList("wareHouseNos"), wareHouseNos);
        List<String> matRootTypes = RmUtils.toList(invoke.get("matRootTypes"), String.class);
        if(CollectionUtils.isEmpty(matRootTypes)) {
            return "no data";
        }
        List<String> list = new ArrayList<>();
        matRootTypes.forEach(rootType -> list.addAll(Arrays.asList(rootType.split(","))));
        return StringUtils.join(list.stream().distinct().collect(Collectors.toList()), ",");
    }

    /**
     * 根据申领的仓库获取对应的物资分类大类
     * @Title: getMatRootType
     * @param claimType claimType : 申领类型,取值为小代码:wilp.rm.claimType
     * @return java.util.List<java.lang.String>
     * @throws
     **/
    public static String getApplyMatRootType(String claimType) {
        String itemName = CommonUtils.getDataCodeItemName("wilp.rm.claimType", claimType);
        EiInfo invoke = RmUtils.invoke("SIPZ03", "queryApplyMatRootTypes",
                Arrays.asList("wareHouseNos"), Arrays.asList(itemName.split(",")));
        List<String> applyMatRootTypes = RmUtils.toList(invoke.get("applyMatRootTypes"), String.class);
        if(CollectionUtils.isEmpty(applyMatRootTypes)) {
            return "no data";
        }
        List<String> list = new ArrayList<>();
        applyMatRootTypes.forEach(rootType -> list.addAll(Arrays.asList(rootType.split(","))));
        return StringUtils.join(list.stream().distinct().collect(Collectors.toList()), ",");
    }

    /**
     * 获取仓库人员对应的领用类型
     * @Title: getClaimType
     * @param workNo workNo
     * @return java.lang.String
     * @throws
     **/
    public static String getClaimType(String workNo) {
        StringBuilder sb = new StringBuilder();
        List<String> wareHouseNos = getWareHouseNos(workNo);
        List<Map<String, String>> dataCode = CommonUtils.getDataCode("wilp.rm.claimType");
        for (Map<String, String> map : dataCode) {
            String label = map.get("label");
            if(wareHouseNos.containsAll(Arrays.asList(label.split(",")))){
                sb.append(map.get("value")).append(",");
            }
        }
        return sb.length() > 1 ? sb.deleteCharAt(sb.length()-1).toString() : "no data";

    }

    /**
     * 根据指定仓库号获取申领类型
     * @Title: getClaimTypeByWareHouseNo
     * @param wareHouseNo wareHouseNo
     * @return java.lang.String
     * @throws
     **/
    public static String getClaimTypeByWareHouseNo(String wareHouseNo) {
        if(StringUtils.isNotBlank(wareHouseNo)) {
            List<Map<String, String>> dataCode = CommonUtils.getDataCode("wilp.rm.claimType");
            for (Map<String, String> map : dataCode) {
                if(map.get("label").contains(wareHouseNo)){
                    return map.get("value");
                }
            }
        }
        return "01";
    }

}
