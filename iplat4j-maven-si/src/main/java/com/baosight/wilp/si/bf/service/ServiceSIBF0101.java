package com.baosight.wilp.si.bf.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.bf.domain.SiScrap;
import com.baosight.wilp.si.bf.domain.SiScrapDetail;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 报废新增/编辑子页面Service
 * @ClassName: ServiceSIBF0101
 * @Package com.baosight.wilp.si.bf.service
 * @date: 2022年09月26日 15:21
 *
 * 1.页面加载
 * 2.保存
 */
public class ServiceSIBF0101 extends ServiceBase {

    /*** 操作类型--新增**/
    public static final String TYPE_ADD = "add";

    /*** 操作类型字段名称**/
    public static final String OPERATE_NAME = "type";

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //判断是编辑还是新增
        if(TYPE_ADD.equals(inInfo.getString(OPERATE_NAME))) {
            inInfo.setCell("inqu_status", 0, "scrapDate", DateUtils.curDateStr10());
        } else {
            //报废主单据
            List<SiScrap> list = dao.query("SIBF01.query", inInfo.getRow("inqu_status", 0));
            inInfo.setRows("inqu_status", list);
            //获取报废明细
            List<SiScrapDetail> detailList = dao.query("SIBF01.queryDetail", new HashMap(2) {{
                put("scrapId", inInfo.getString("inqu_status-0-id"));
            }});
            inInfo.setRows("result", detailList);
        }
        return inInfo;
    }

    /**
     * 保存
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {
        //获取参数
        SiScrap scrap = new SiScrap();
        scrap.fromMap(inInfo.getRow("inqu_status", 0));
        List<SiScrapDetail> detailList = SiUtils.toList(inInfo.get("list"), SiScrapDetail.class);

        //参数校验
        EiInfo validateInfo = ValidatorUtils.validateEntity(scrap);
        if(validateInfo.getStatus() == -1) {
            return validateInfo;
        }
        detailList = detailList.stream().filter(detail -> NumberUtils.toDouble(detail.getEnterNum()) > 0).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(detailList)) {
            return ValidatorUtils.errorInfo(inInfo, "报废明细不能为空或数量都为0");
        }

        //判断是编辑还是新增
        if(TYPE_ADD.equals(inInfo.getString(OPERATE_NAME))) {
            scrap.setId(UUID.randomUUID().toString());
            scrap.setScrapNo("SC"+DateUtils.curDateTimeStr14());
            scrap.setStatusCode("01");
            scrap.setStatusName(CommonUtils.getDataCodeItemName("wilp.si.scrapStatus", "01"));
            scrap.setRecCreator(UserSession.getLoginName());
            scrap.setRecCreatorName(UserSession.getLoginCName());
            scrap.setRecCreateTime(new Date());
            scrap.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
            dao.insert("SIBF01.insert", scrap);
        } else {
            scrap.setRecRevisor(UserSession.getLoginName());
            scrap.setRecReviseTime(new Date());
            dao.update("SIBF01.update", scrap);
            dao.delete("SIBF01.deleteDetail", scrap.getId());
        }
        detailList.forEach(detail -> {
            detail.setId(UUID.randomUUID().toString());
            detail.setScrapId(scrap.getId());
            detail.setScrapNo(scrap.getScrapNo());
            dao.insert("SIBF01.insertDetail", detail);
        });
        return inInfo;
    }
}
