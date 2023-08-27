package com.baosight.wilp.ac.su.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  修改供应商子界面.
 *  <p>弹窗初始化方法, 通过ID查询供应商详情, 修改供应商信息.</p>
 *
 * @Title ServiceACSU0102.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACSU0102 extends ServiceBase {


    final String projectSchema = PrUtils.getConfigure();
    /**
     * 	弹窗初始化方法（用于回显数据）
     * 	作者：jzm
     * 	入参：EiInfo
     * 	出参：EiInfo
     * 	处理：
     * 	1.从入参读入id
     * 	2.从数据库中查询符合条件的供应商
     * 	3.返回
     */
	public EiInfo initLoad(EiInfo inInfo) {
        /**
         * 	1.从入参读入id
         */
        Map<String, String> map = new HashMap<>();
        map.put("projectSchema", projectSchema);
        map.put("id",inInfo.getString("id"));

        /**
         * 2.从数据库中查询符合条件的供应商
         */
        EiInfo outInfo = new EiInfo();
        List<Map<String, String>> list = dao.query("ACSU0102.querySuppInfo", map);

        /**
         * 3.返回
         */
        if (CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("未查到数据");
            outInfo.setStatus(-1);
            return outInfo;
        }
        outInfo.setAttr(list.get(0));
        return outInfo;
	}

	/**
     * 	修改供应商信息
     * 	作者：jzm
     *	入参：	主键 id   供应商名称 supplierName, 供应商类型 supplierType,联系人 contacts, 联系电话 contactNumber
     *			联系地址 contactAddress 联系邮箱contactEmail  zipCode 邮编  legalRepresentative 法人代表
     *			工商注册号 icrNo  经营范围business_scope   供应商简称abbreviation   HRP代码 hrp_code
     *	出参：EiInfo（操作结果）
      *  处理：
     *  1.从EiInfo中读取参数
     *  2.检查新供应商名称合法性
     *  3.如果新的供应商名称合法则执行修改SQL，并返回操作结果
     *  4.如果非法则返回错误信息
     */
	@Override
	@ArchivesLog(model = "AC", sign = "修改供应商")
	public EiInfo update(EiInfo inInfo) {
        /**
         * 1.从EiInfo中读取参数
         */
        EiInfo out = new EiInfo();
        String recRevisor = UserSession.getUser().getUsername();
        String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        /**
         *  2.检查新供应商名称合法性
         */
        // 判断一下 新的供应商名称和旧的供应商名称
        String oldSupplierName = inInfo.getString("oldSupplierName");
        String newSupplierName = inInfo.getString("supplierName");

        if (oldSupplierName != null && newSupplierName != null) {
            if (!oldSupplierName.equals(newSupplierName)) {
                // 如果 不一样 则需要查询一下新的名称是否已经存在了
                // 检查供应商名称是否重复
                //
                Map<String, String> map = inInfo.getAttr();
                map.put("projectSchema", projectSchema);
                int count = super.count("ACSU0101.isExistSupplierName", map);
                if (count > 0) {
                    out.setStatus(1);
                    out.setMsg("该供应商名称已存在");
                    return out;
                }
            }
            /**
             * 3.如果新的供应商名称合法则执行修改SQL，并返回操作结果
             */
            Map<String, String> map = inInfo.getAttr();
            map.put("projectSchema", projectSchema);
            map.put("recRevisor", recRevisor);
            map.put("recReviseTime", recReviseTime);
            dao.update("ACSU0102.update", map);

            out.setMsg("修改成功");
            out.setStatus(0);
            return out;
        }

        /**
         * 4.如果非法则返回错误信息
         */
        out.setMsg("新的供应商名称或旧的供应商名称为NULL");
        out.setStatus(1);
        return out;

	}
}
