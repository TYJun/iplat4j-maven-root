package com.baosight.wilp.mp.jk.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.mp.common.MpConfigCache;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpContract;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.pz.domain.MpConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购与固定资产对接接口
 * @ClassName: ServiceMPJK05
 * @Package com.baosight.wilp.mp.jk.service
 * @date: 2022年12月20日 14:02
 *
 * 1.固定资产信息入库推送
 */
public class ServiceMPJK05 extends ServiceBase {

    /**是否对接固定资产**/
    private static final String IS_DOCK_FA = "Y";

    /**分割符**/
    private static final String separator = ",";

    @Autowired
    private MpContractService contractService;

    /**
     * 固定资产信息入库推送
     * @Title: pushEnter
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo pushEnter(EiInfo inInfo) {
        //获取配置
        MpConfig config = MpConfigCache.getConfig(inInfo.getString("dataGroupCode"), MpConfigCache.MP_CONFIG_DOCK_FA);
        if(IS_DOCK_FA.equals(config.getConfigValueRadio())) {
            //获取固定资产的分类编码配置
            String configValueText = config.getConfigValueText();
            if(StringUtils.isBlank(configValueText)) { return inInfo; }
            //递归获取分类下的子分类编码
            List<String> matTypeNumList = new ArrayList<>();
            for (String matTypeNum : configValueText.split(separator)) {
                EiInfo invoke = MpUtils.invoke("MPTY01", "getChildMatTypeNumList", Arrays.asList("rootMatTypeNum"), matTypeNum);
                matTypeNumList.addAll(MpUtils.toList(invoke.get("matTypeList"), String.class));
            }
            //过滤出固定资产
            List<Map> details = MpUtils.toList(inInfo.get("list"), Map.class);
            details = details.stream().filter(map -> matTypeNumList.contains(MpUtils.toString(map.get("matTypeNum")))).collect(Collectors.toList());
            //数据处理
            List<Map> list = completeData(details, inInfo.getAttr());
            //调用微服务
            MpUtils.invoke("S_FA_CONFIRM_01", Arrays.asList("list"), list);
        }
        return inInfo;
    }

    /**
     * 完善参数信息
     * @Title: completeData
     * @param details details
     * @param map map
     * @return java.util.List<java.util.Map>
     * @throws
     **/
    private List<Map> completeData(List<Map> details, Map map) {
        MpContract contract = contractService.queryContractByNo(MpUtils.toString(map.get("contNo")));
        map.put("purchaseVouch", map.get("originBillNo"));
        map.put("purchaseStaffName", contract.getManagerName());
        map.put("fundingSourceNum", contract.getFundingSourceNum());
        map.put("fundingSourceName", contract.getFundingSourceName());
        map.put("acquitvDate", DateUtils.toDateStr(contract.getSignDate()));
        map.put("acquitvYear", contract.getSignDate().getYear());
        //数据处理
        List<Map> list = new ArrayList<>();
        for (Map detail : details) {
            Map pMap = JSON.parseObject(JSON.toJSONString(map), Map.class);
            pMap.putAll(detail);
            list.add(pMap);
        }
        return list;
    }
}
