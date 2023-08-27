package com.baosight.wilp.ir.sy.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ir.sy.domain.BusinessShortCut;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @PackageName com.baosight.wilp.ir.sy.service
 * @ClassName ServiceIRSY01
 * @Description 首页需求处理
 * @Author chunchen2
 * @Date 2021/11/12 10:49
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/11/12 10:49
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceIRSY01 extends ServiceEPBase {

    /**
     * @Title queryLists
     * @Author chunchen2
     * @Description //获取首页配置快捷方式列表
     *                查询表：xs_user_business_shortcut(用户配置快捷方式)
     * @Date 10:31 2021/11/22
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryLists(EiInfo eiInfo){

        String platSchema = PlatApplicationContext.getProperty("platSchema");
        String workNo = UserSession.getLoginName();
        Map queryParam = new HashMap();
        queryParam.put("platSchema", platSchema);
        queryParam.put("workNo", workNo);
        List<BusinessShortCut> bsLists = dao.query("IRSY01.queryBusinessShortCut", queryParam);

        Comparator<BusinessShortCut> clickNumDesc = Comparator.comparing(BusinessShortCut::getClickNum).reversed();
        Comparator<BusinessShortCut> updateTimeDEsc = Comparator.comparing(BusinessShortCut::getUpdateTime).reversed();

        if(null != bsLists && bsLists.size() > 0){
            bsLists.sort(clickNumDesc.thenComparing(updateTimeDEsc)); // 根据点击次数、日期两个字段进行排序
            List<BusinessShortCut> retLists = null;
            int listSize = bsLists.size();
            if(listSize < 16){ // 最多截取16个, 返回前台显示
                retLists = bsLists.subList(0, listSize);
            } else {
                retLists = bsLists.subList(0, 16);
            }
            eiInfo.setStatus(1);
            eiInfo.set("result", retLists);
        } else {
            eiInfo.setStatus(1);
            eiInfo.set("result", new ArrayList<BusinessShortCut>());
            eiInfo.setMsg("获取列表为空！");
        }

        return eiInfo;
    }

    /**
     * @Title addLists
     * @Author chunchen2
     * @Description // 点击页面tab, 添加配置快捷记录
     *              表：TEDFA00   页面菜单配置
     *              表：xs_user_business_shortcut  配置快捷记录
     * @Date 10:36 2021/11/22
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo addLists(EiInfo eiInfo){
        String nodeCname = "";

        Map nodeParams = new HashMap();
        String nodeEname = String.valueOf(eiInfo.get("nodeEname"));
        nodeParams.put("nodeEname", nodeEname);

        String url = eiInfo.getString("url");
        String title = eiInfo.getString("title");
        // 本身是通过url跳转的
        if(null != url && url.length() > 0 && null != title && title.length() > 0) {
            nodeCname = title;
        } else {
            // 根据nodeEname 获取nodeCname
            List<Map<String, String>> nodeLists = dao.query("IRSY01.queryNodeInfo", nodeParams);
            if(null == nodeLists || nodeLists.size() == 0){
                eiInfo.setMsg("TEDFA00没有对应的记录，不进行记录");
                return eiInfo;
            }
            Map<String, String> NodeInfoMap = nodeLists.get(0);
            nodeCname = NodeInfoMap.get("nodeCname");
        }

        Map queryParam = new HashMap();
        queryParam.put("nodeEname", nodeEname);
        queryParam.put("url", url);
        String platSchema = PlatApplicationContext.getProperty("platSchema");
        String workNo = UserSession.getLoginName();

        queryParam.put("platSchema", platSchema);
        queryParam.put("workNo", workNo);
        List<BusinessShortCut> bsLists = dao.query("IRSY01.queryBusinessShortCut", queryParam);

        queryParam.put("updateTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

        if(null != bsLists && bsLists.size() > 0){ // 存在记录，点击次数 +1
            BusinessShortCut businessShortCut = bsLists.get(0);
            String clickNum = businessShortCut.getClickNum();
            queryParam.put("clickNum", String.valueOf(Integer.parseInt(clickNum) + 1));
            dao.update("IRSY01.updateBusinessShortCut", queryParam);
        } else { // 不存在， 添加点击记录
            queryParam.put("clickNum", "1");
            queryParam.put("nodeCname", nodeCname);
            dao.insert("IRSY01.addBusinessShortCut", queryParam);
        }

        return eiInfo;
    }

}
