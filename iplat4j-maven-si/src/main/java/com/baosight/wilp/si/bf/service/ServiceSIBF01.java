package com.baosight.wilp.si.bf.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.bf.domain.SiScrap;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fangjian
 * @version V5.0.1
 * @Description: 物资报废管理页面Service
 * @ClassName: ServiceSIBF01
 * @Package com.baosight.wilp.si.bf
 * @date: 2022年09月21日 16:09
 *
 * 1.页面加载
 * 2.数据查询
 * 3.删除报废单
 * 4.提交报废单
 * 5.撤回报废单
 */
public class ServiceSIBF01 extends ServiceBase {

    /**
     *
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        SiUtils.initQueryTime(inInfo, false);
        inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 20);
        return query(inInfo);
    }

    /**
     * 数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        return super.query(inInfo, "SIBF01.query", new SiScrap());
    }

    /**
     * 删除报废单
     * @Title: delete
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        String id = inInfo.getString("id");
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo(inInfo, "参数不能为空");
        }
        int delete = dao.delete("SIBF01.delete", id);
        if(delete > 0) {
            dao.delete("SIBF01.deleteDetail",id);
            return inInfo;
        } else {
            return ValidatorUtils.errorInfo(inInfo, "已删除或无法删除");
        }
    }

    /**
     * 提交
     * @Title: submit
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo submit(EiInfo inInfo) {
        String id = inInfo.getString("id");
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo(inInfo, "参数不能为空");
        }
        SiScrap scrap = SiScrap.getInstance(id, SiConstant.SCRAP_STATUS_SUBMIT, SiConstant.SCRAP_STATUS_NEW);
        int update = dao.update("SIBF01.update", scrap);
        inInfo.setStatus(update > 0 ? 0 : -1);
        inInfo.setMsg(update > 0 ? "提交成功" : "数据已提交或不存在");
        return inInfo;
    }

    /**
     * 撤回
     * @Title: back
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo back(EiInfo inInfo) {
        String id = inInfo.getString("id");
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo(inInfo, "参数不能为空");
        }
        SiScrap scrap = SiScrap.getInstance(id, SiConstant.SCRAP_STATUS_NEW, SiConstant.SCRAP_STATUS_SUBMIT);
        int update = dao.update("SIBF01.update", scrap);
        inInfo.setStatus(update > 0 ? 0 : -1);
        inInfo.setMsg(update > 0 ? "撤回成功" : "数据无法撤回");
        return inInfo;
    }

}
