package com.baosight.wilp.cp.zh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.cp.domain.CpBill;
import com.baosight.wilp.cp.util.CPUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该页面为投诉综合管理
 * 主要包含对投诉综合查看功能
 * 投诉模块：初始化查询、查询方法
 * <p>1.初始化查询 initLoad
 * <p>2.查询方法 query
 * @Title: ServiceCPZH01.java
 * @ClassName: ServiceCPZH01
 * @Package：com.baosight.wilp.cp.zx.service
 * @author: liangyongfei
 * @date: 2022年04月20日 下午3:13:24
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCPZH01 extends ServiceBase {

    /**
     * @param inInfo billNo 投诉单号
     *               complaintDateS 发生日期起
     *               complaintDateE 发生日期止
     * @return EiInfo
     * id 主键
     * billNo 投诉单号
     * statusCode 单子状态
     * complaintDate 发生日期
     * complaintName 投诉人
     * complaintPhone 投诉人电话
     * complaintDept 部门/单位
     * complaintType 投诉类型
     * complaintWay投诉方式
     * complaintContent 投诉内容
     * dealWorkName 经办人
     * dealDate 处理日期
     * returnDesc 回访情况
     * returnWorkName回访人
     * returnDate回访日期
     * @Title: initLoad
     * @Description: 初始化查询
     */
    public EiInfo initLoad(EiInfo inInfo) {
        // 调用查询方法
        return this.query(inInfo);
    }

    /**
     * 查询投诉处理管理
     * <p>Title: query</p>
     * <p>Description: </p>
     * @param info
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo query(EiInfo info){
        List<String> labor = CPUtils.isRole("ZGXSGH");
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        String workNo = (String) staffByUserId.get("workNo");
        if (CollectionUtils.isNotEmpty(labor)){
            info.set("inqu_status-0-labor","labor");
            info.set("labor","labor");
        } else {
            info.set("inqu_status-0-labor","user");
            info.set("inqu_status-0-recCreator",workNo);
        }
        return super.query(info,"CPZH01.query",new CpBill());
    }
}
