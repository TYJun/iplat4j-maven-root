package com.baosight.wilp.ci.cg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.ci.ck.domain.CiOut;
import com.baosight.wilp.ci.ck.domain.CiOutDetail;
import com.baosight.wilp.ci.common.BeanExchangeUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *物资审核子页面
 * <p>1.初始化查询 initLoad
 * <p>2.审核物资 updateApprove
 * <p>3.更新物资主表数量方法 updateMainNum
 * <p>4.更新工单状态 updateReject
 *
 * @Title: ServiceCICG0201.java
 * @ClassName: ServiceCICG0201
 * @Package：com.baosight.wilp.md.sp.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCICG0201 extends ServiceBase {
	
	/**
	 * 跳转新增页面
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * moduleId 项目分类id
	 * @return
	 * id  项目主键
	 * code 项目编码
	 * content 项目名称
	 * projectDesc 项目描述
	 * referenceValue 项目参考值
	 * actualValueUnit 实际值单位
	 * xunjianAbnormal 异常描述
	 * memo 备注
	 * @see ServiceBase#initLoad(EiInfo)
	 */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String,String> pMap = new HashMap<>();
        //获取前台申请单号和科室名称，科室编码，回显到子页面
        pMap.put("applyBillNo", inInfo.getString("applyBillNo"));
        List<CiEnterDetail> list = dao.query("CICG0101.query", pMap);
        return CommonUtils.BuildOutEiInfo(inInfo, "result", new CiEnterDetail().eiMetadata, list, list.size());
    }

    /**
     * 物资审核页面
     *  1.获取前台出库类型，和出库信息，将出库信息保存到出库主表中
     *  2.生成配送的出库单
     *  3.通过查询的物资数量判断申请的数量是否足够，不够则返回错误信息
     *  4.根据物资号查询库存明细物资批次倒序
     *  5.获取出库物资按批次最早的一批出
     *  6.如果批次最早的一批大于申请的数量则直接减去这个数量，更新物资明细表物资数量和总价，更新物资主表数量和总价
     *  7.如果出库批次数量不够，则将这个批次的数量值为0，将申请数量更新为减去的值，进行下一次循环，更新物资明细表物资数量和总价，更新物资主表数量和总价
     * &lt;p&gt;Title: updateApprove&lt;/p&gt;
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param inInfo
     * outType  出库类型
     * rows     物资信息
     * moduleId 项目分类id
     * @see ServiceBase#initLoad(EiInfo)
     */
    @SuppressWarnings("all")
    public EiInfo updateApprove(EiInfo inInfo) {
        //获取驳回理由，和id
        Map map = new HashMap();
        map.put("id", inInfo.get("id"));
        //更新工单状态
        int count = dao.update("CICG02.updateApplySignS", map);
        if (count > 0) {
            inInfo.setStatus(1);
            inInfo.setMsg("审核成功");
            return inInfo;
        }
        //返回
        inInfo.setStatus(-1);
        inInfo.setMsg("审核失败");
        return inInfo;
    }




    /**
     * 更新工单状态
     * <p>Title: updateReject</p>
     * <p>Description: </p>
     * @param inInfo
     * id ：id
     * emo ：驳回理由
     * @return inInfo
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    public EiInfo updateReject(EiInfo inInfo) {
        //获取驳回理由，和id
        Map map = new HashMap();
        map.put("id", inInfo.get("id"));
        map.put("emo", inInfo.get("emo"));
        //更新工单状态
        int count = dao.update("CICG02.updateReject", map);
        if (count > 0) {
            inInfo.setStatus(1);
            inInfo.setMsg("驳回成功");
            return inInfo;
        }
        //返回
        inInfo.setStatus(-1);
        inInfo.setMsg("驳回失败");
        return inInfo;
    }
}
