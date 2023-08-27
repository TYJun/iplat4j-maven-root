package com.baosight.wilp.rm.tk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmBackOut;
import com.baosight.wilp.rm.lj.domain.RmBackOutDetail;
import com.baosight.wilp.rm.lj.service.RmBackOutService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 退库管理页面Service
 * @ClassName: ServiceRMTK02
 * @Package com.baosight.wilp.rm.tk.service
 * @date: 2022年10月25日 9:23
 *
 * 1.页面加载
 * 2.页面数据查询
 */
public class ServiceRMTK02 extends ServiceBase {

    @Autowired
    private RmBackOutService backOutService;

    /**
     * 状态集合
     **/
    private static List<String> statusCodes = Arrays.asList(RmConstant.BACK_OUT_STATUS_UN_APPROVAL,
            RmConstant.BACK_OUT_STATUS_UN_OUT, RmConstant.BACK_OUT_STATUS_PART_OUT);

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        RmUtils.initQueryTime(inInfo, "beginTime", "endTime");
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 50);
        EiInfo info = query(inInfo); info.setRows("detail", new ArrayList());
        return info;
    }

    /**
     * 页面数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCodes", statusCodes);
        return super.query(inInfo, "RMLJ03.query", new RmBackOut());
    }

    /**
     * 查询退库明细
     * @Title: queryDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDetail(EiInfo inInfo) {
        return super.query(inInfo, "RMLJ03.queryDetail", new RmBackOutDetail(),false, null, RmConstant.QUERY_BLOCK, "detail", "detail", null);
    }

    /**
     * 结束领用退库单
     * @Title: over
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo over(EiInfo inInfo) {
        //获取参数
        String id = inInfo.getString("id");
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //构建对象
        backOutService.update(RmBackOut.getStatusInstance(id,RmConstant.BACK_OUT_STATUS_OVER));
        return inInfo;
    }
}
