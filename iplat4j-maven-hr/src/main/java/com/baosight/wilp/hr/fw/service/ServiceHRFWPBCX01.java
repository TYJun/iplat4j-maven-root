package com.baosight.wilp.hr.fw.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.hr.pb.domain.HRPBSjpz01;

/**
 * (排班查询对外服务)
 *
 * @Title:
 * @ClassName:
 * @Package：
 * @author: xiajunqing
 * @date:
 * @version: V1.0
 * @Copyright: www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceHRFWPBCX01 extends ServiceBase {


    /**
     *
     * 初始化查询
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo initLoad = super.initLoad(inInfo, new HRPBSjpz01());
        return initLoad;
    }


    /**
     *
     * grid数据集查询订餐时间
     * @param inInfo
     * @return
     * @see ServiceBase#query(EiInfo)
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        return inInfo;
    }

    /**
     *
     * 删除订餐时间
     * @param inInfo
     * @return
     * @see ServiceBase#delete(EiInfo)
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        return outInfo;
    }


    /**
     *
     * 新增订餐时间
     * @param inInfo
     * @return
     * @see ServiceBase#insert(EiInfo)
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        return outInfo;
    }


    /**
     *
     * 编辑数据
     * @param inInfo
     * @return
     * @see ServiceBase#update(EiInfo)
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        return outInfo;
    }
}
