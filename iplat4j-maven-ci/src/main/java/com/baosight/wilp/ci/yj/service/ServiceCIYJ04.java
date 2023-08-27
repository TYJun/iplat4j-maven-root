package com.baosight.wilp.ci.yj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.kc.domain.CiStorge;
import com.baosight.wilp.ci.kc.domain.CiStorgeDetail;
import com.baosight.wilp.common.util.BaseDockingUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 1、针对食堂物资低于最低库存量的情况，短信告知仓库管理员；
 * 2、针对过期但未消耗完毕的食品，短信告知仓库管理员；
 *
 * @Title: ServiceCIYJ04
 * @ClassName: ServiceCIYJ04
 * @Package: com.baosight.wilp.ci.yj.service
 * @author: liu
 * @date: 2022-07-01 11:04
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History: // 历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version> // 版本
 * <desc>   // 描述修改内容
 */
public class ServiceCIYJ04 extends ServiceBase {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCIYJ04.class);

    /**
     * 针对过期但未消耗完毕的食品，短信告知仓库管理员
     * 1.查询过期但未消耗完毕的食品
     * 2.查询工号集合
     * 3.查询短信配置模板
     * 4.获取短信发送参数
     * 5.调用短信推送接口发送消息
     * @Title  queryExpiredFood
     * @author liu
     * @date 2022-07-04 10:37
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     */
    public EiInfo queryExpiredFoodSendSMS(EiInfo inInfo){

        //工号的集合
        List<String> workNoList = new ArrayList<>();

        /**
         * 1.查询过期但未消耗完毕的食品
         */
        Map<String, Object> map = new HashMap<>();
        map.put("expirationDate", DateUtils.curDateStr10());
        List<CiStorgeDetail> storgeDetail = dao.query("CIYJ01.query", map);

        /**
         * 2.查询工号集合
         */
        EiInfo workInfo = CiUtils.callCode("S_ED_02", "wilp.ci.yj.sms", "work");
        List<HashMap<String,Object>> sendWorkList = (List<HashMap<String, Object>>) workInfo.get("list");
        if(sendWorkList != null && sendWorkList.size() > 0){
            for (HashMap<String, Object> sendWorkMap : sendWorkList) {
                workNoList.add(StringUtils.toString(sendWorkMap.get("label")));
            }
            /**
             * 3.查询短信配置模板
             */
            EiInfo templateInfo = CiUtils.callCode("S_ED_02", "wilp.ci.yj.sms", "templateCode");
            List<HashMap<String,Object>> templateList = (List<HashMap<String, Object>>) templateInfo.get("list");
            if(templateList != null && templateList.size() > 0){
                //短信配置模板
                String templateCode = StringUtils.toString( templateList.get(0).get("label"));

                /**
                 * 4.获取短信发送参数
                 */
                String collect = storgeDetail.stream().map(CiStorgeDetail::getMatName).distinct().collect(Collectors.joining(","))+"已过期,请注意!";
                List<String> paramList = new ArrayList<>();
                paramList.add(collect);

                logger.info("食堂进销存过期提示短信推送, 工号:{} | 参数:{} | 模板:{}",workNoList,paramList,templateCode);
                /**
                 * 5.调用短信推送接口发送消息
                 */
                boolean b = BaseDockingUtils.sendMsg(workNoList, paramList, templateCode);
                logger.info("短信推送结果:{}",b);
                if(!b){
                    inInfo.setMsg("短信发送失败");
                    inInfo.setStatus(-1);
                }

            }else{
                inInfo.setMsg("未查到配置的短信模板!");
                inInfo.setStatus(-1);
            }

        }else{
            inInfo.setMsg("未查到短信发送人员!");
            inInfo.setStatus(-1);
        }

        return inInfo;
    }

    /**
     * 食堂物资低于最低库存量的情况，短信告知仓库管理员
     * @Title  getMinNumSendSMS
     * @author liu
     * @date 2022-07-04 11:20
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     */
    public EiInfo getMinNumSendSMS(EiInfo inInfo){

        List<CiStorge> storgeList = dao.query("CIYJ01.getMinNumSendSMS", null);
        if(CollectionUtils.isEmpty(storgeList)){
            inInfo.setMsg("没有低于最低库存量物资");
            return inInfo;
        }
        /**
         * 2.查询工号集合
         */
        //工号的集合
        List<String> workNoList = new ArrayList<>();
        EiInfo workInfo = CiUtils.callCode("S_ED_02", "wilp.ci.yj.sms", "work");
        List<HashMap<String,Object>> sendWorkList = (List<HashMap<String, Object>>) workInfo.get("list");
        if(sendWorkList != null && sendWorkList.size() > 0){
            for (HashMap<String, Object> sendWorkMap : sendWorkList) {
                workNoList.add(StringUtils.toString(sendWorkMap.get("label")));
            }
            /**
             * 3.查询短信配置模板
             */
            EiInfo templateInfo = CiUtils.callCode("S_ED_02", "wilp.ci.yj.sms", "templateCode");
            List<HashMap<String,Object>> templateList = (List<HashMap<String, Object>>) templateInfo.get("list");
            if(templateList != null && templateList.size() > 0){
                //短信配置模板
                String templateCode = StringUtils.toString( templateList.get(0).get("label"));
                /**
                 * 4.获取短信发送参数
                 */
                for (CiStorge storge : storgeList) {
                    List<String> paramList = new ArrayList<>();
                    String collect = storge.getMatName()+"现库存:"+ storge.getTotalNum()+ "小于最低库存:" + storge.getMinNum();
                    paramList.add(collect);
                    logger.info("食堂进销存低于最低库存量提示短信推送, 工号:{} | 参数:{} | 模板:{}",workNoList,paramList,templateCode);
                    /**
                     * 5.调用短信推送接口发送消息
                     */
                    boolean b = BaseDockingUtils.sendMsg(workNoList, paramList, templateCode);
                    logger.info("短信推送结果:{}",b);
                    if(!b){
                        inInfo.setMsg("短信发送失败");
                        inInfo.setStatus(-1);
                    }
                }
            }else{
                inInfo.setMsg("未查到配置的短信模板!");
                inInfo.setStatus(-1);
            }

        }else{
            inInfo.setMsg("未查到短信发送人员!");
            inInfo.setStatus(-1);
        }

        return inInfo;
    }



}
