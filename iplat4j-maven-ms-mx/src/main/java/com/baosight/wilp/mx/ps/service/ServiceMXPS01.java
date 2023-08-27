package com.baosight.wilp.mx.ps.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.mx.ps.domain.MXPS01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 供配电系统（初始化调用、数据查询）
 *
 * @author: panlingfeng
 * @createDate: 2021/8/11 6:46 下午
 */
public class ServiceMXPS01 extends ServiceBase {

    /**
     * 初始化调用
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/10 4:14 下午
     * @params
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo = super.initLoad(inInfo, new MXPS01());
        return inInfo;
    }

    /**
     * 数据查询
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/10 4:14 下午
     * @params
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        Set List2 = new HashSet<>();  //树状存储
        Map map = new HashMap();
        Set SelectSet = new LinkedHashSet<>();
        Boolean bean = false;
        Boolean Highest = false; //最高权限
        List arrayList = new ArrayList<>();    //用来存放组信息
        Map<Object, Object> hashMap = new HashMap<>();
        String userUuid = UserSession.getUserUuid();  //获取登录ID
        hashMap.put("id", userUuid);
        String parentIdO = null;
        List<Map> listID = dao.query("MSPL02.relevance22", hashMap);   //通过ID调用组信息
//        for (int i = 0; i < listID.size(); i++) {
//            if (listID.get(i).get("groupCname").toString().equals("管理员组")) {
//                Highest = true;
//                break;
//            } else {
//                bean = false;
//                arrayList.add(listID.get(i).get("groupId").toString());
//            }
//        }
        Highest = true;
        //------------------------------------------
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        Map rows = (Map) inqu_status.getRows().get(0);
        String parentId = rows.get("parentId").toString();
        String tag = rows.get("tag").toString();
        String device = rows.get("device").toString();
        String name_ = rows.get("name_").toString();
        if (tag.trim().length() == 0) {
            rows.remove("tag");
        } else {
            map.put("tag", tag);
            bean = false;
        }
        if (device.trim().length() == 0) {
            rows.remove("device");
        } else {
            map.put("device", device);
            bean = false;
        }
        if (name_.trim().length() == 0) {
            rows.remove("name_");
        } else {
            map.put("name_", name_);
            bean = false;
        }
        if (parentId.equals("root")) {
            rows.put("parentId", "");
        }
        //--------------------------------------------------------------------
        if (bean.equals(false) && Highest.equals(false)) {
            //    List SelectList = new ArrayList<>();
            if (arrayList.size() > 0) {
                for (int i = 0; i < arrayList.size(); i++) {
                    hashMap.put("id", arrayList.get(i));
                    //查询菜单树ID
                    List<Map> listTreeID = dao.query("MSPL02.relevanceSelect", hashMap);
                    if (listTreeID.size() > 0) {
                        for (int j = 0; j < listTreeID.size(); j++) {
                            if (listTreeID.get(j).get("t_param_id") != null) {
                                if (parentId.equals("root")) {
                                    SelectSet.add(listTreeID.get(j).get("t_param_id"));
                                } else {
                                    SelectSet.add(parentId);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (bean.equals(false) && Highest.equals(false)) {
            int k = 0;
            if (SelectSet.size() > 0) {
                for (Object hash : SelectSet) {
                    map.put("parentId", hash);
                    List<MXPS01> listTree = dao.query("MXPS01.query", map);    //获取根节点
                    if (listTree.size() > 0) {
                        for (int i = 0; i < listTree.size(); i++) {
                            List2.add(listTree.get(i));
                        }
                    }
                }
                ArrayList<Object> arrayListTree = new ArrayList<>();
                for (Object hash : List2) {
                    arrayListTree.add(hash);
                }
                Map m = inInfo.getBlocks();
                EiBlock eiBlocks = (EiBlock) m.get("result");
                eiBlocks.setRows(arrayListTree);
            }
        } else {
            EiInfo outInfo = super.query(inInfo, "MXPS01.query", new MXPS01());
            return outInfo;
        }
        if (inInfo.getBlocks().get("result") != null) {
            EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
            Map attr = result.getAttr();
            attr.put("count", result.getRows().size());
        }
        return inInfo;
    }

    /*超额/低额报警*/
    public EiInfo getWarning(EiInfo info){
        List query = dao.query("MXPS01.getWarning", null);
        info.set("result",query);
        return info;
    }
}
