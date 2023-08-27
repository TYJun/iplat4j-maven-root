package com.baosight.wilp.cm.dj.service;

import com.baosight.wilp.common.util.BaseDockingUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 该页面为合同登记管理
 * <p>1.初始化查询 initLoad
 * <p>2.合同数据查询 query
 * <p>3.合同审批 examine
 * <p>4.合同删除 delete
 * @Title: CmMsgService.java
 * @ClassName: ServiceCMDJ01
 * @Package：com.baosight.wilp.cm.dj.service
 * @author: zhangjiaqian
 * @date: 2021年8月24日 下午1:29:57
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
//@Service
//public class CmMsgService {
//
//    //@Scheduled(cron = "* */5 * * * ?")
//    public  void sendMsg(){
//        List<String> workNoList=new ArrayList<>();
//        List<String> paraList=new ArrayList<>();
//        workNoList.add("BN0120");
//        workNoList.add("XSB001");
//        paraList.add("快来签合同");
//        BaseDockingUtils.sendMsg(workNoList,paraList,"TP00006");
//    }
//
//
//}
