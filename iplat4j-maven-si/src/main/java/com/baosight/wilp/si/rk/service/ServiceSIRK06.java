package com.baosight.wilp.si.rk.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import com.baosight.wilp.si.wh.domain.SiWarehouse;
import com.baosight.xservices.xs.util.UserSession;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 入库明细组合查询
 */
public class ServiceSIRK06  extends ServiceBase {

    private final static String wareHouse = "wareHouse";
    private final static String supplier = "supplier";
    /**
     * 页面加载
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        // 初始化查询时间
        Calendar cal = Calendar.getInstance();
        inInfo.set("endTime", DateUtils.curDateStr10());
        cal.add(Calendar.MONTH, -1);
        inInfo.set("beginTime", DateUtils.toDateStr10(cal.getTime()));
        inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 50);
        //获取小代码报表地址配置;
        List<Map<String, String>> list = CommonUtils.getDataCode("wilp.si.frUrl");
        inInfo.addBlock("defaultFrUrl").addRows(list);
        return query(inInfo);
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        String[] param = {"supplierName","wareHouseName","matTypeName","endTime","beginTime"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        map.put("wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(UserSession.getUser().getUsername()));
        if(inInfo.getBlock(EiConstant.resultBlock) != null) {
            map.put("orderBy", inInfo.getBlock(EiConstant.resultBlock).getString("orderBy"));
        }
        List<HashMap<String,Object>> list = dao.query("SIRK06.query",map,inInfo.getString("offset")==null?
                Integer.parseInt(map.get("offset").toString()):Integer.parseInt(inInfo.getString("offset")),
                inInfo.getString("offset")==null?Integer.parseInt(map.get("limit").toString()):Integer.parseInt(inInfo.getString("limit")));
        list.forEach(date->{
            DecimalFormat df = new DecimalFormat("0.00");
            date.put("enterNum",df.format(date.get("enterNum")));
            date.put("enterPrice",df.format(date.get("enterPrice")));
            date.put("enterAmount",df.format(date.get("enterAmount")));
        });
        int count = super.count("SIRK06.count",map);
        if(list != null && list.size() > 0) {
            inInfo = CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
        } else {
            inInfo.addBlock("result");
        }
        inInfo.set("supplier",inInfo.getAttr().get("supplier"));
        inInfo.set("wareHouse",inInfo.getAttr().get("wareHouse"));
        initSupplierList(inInfo.addBlock(supplier));
        initWareHouseList(inInfo.addBlock(wareHouse));
        return inInfo;
    }

    /**
     * 供应商数据初始化
     * @param formTypeBlock
     */
    private void initSupplierList(EiBlock formTypeBlock) {
        EiBlockMeta metadata = new EiBlockMeta();
        EiColumn eiColumn = new EiColumn("supplier");
        eiColumn.setDescName("供应商");
        metadata.addMeta(eiColumn);
        formTypeBlock.setBlockMeta(metadata);
        EiInfo inInfo = new EiInfo();
        inInfo.set(EiConstant.serviceId, "S_AC_FW_19");
        EiInfo call = XServiceManager.call(inInfo);
        if(call.getStatus() < 0){
            //没查到
            inInfo.setMsg("查询供应商信息失败" + call.getMsg());
            inInfo.setStatus(-1);
        }
        List<HashMap<String,Object>> list = (List<HashMap<String, Object>>) call.get("result");
        formTypeBlock.addRows(list);
    }

    /**
     * 仓库数据初始化
     * @param formTypeBlock
     */
    private void initWareHouseList(EiBlock formTypeBlock) {
        EiBlockMeta metadata = new EiBlockMeta();
        EiColumn eiColumn = new EiColumn("wareHouse");
        eiColumn.setDescName("仓库");
        metadata.addMeta(eiColumn);
        formTypeBlock.setBlockMeta(metadata);
        EiInfo inInfo = new EiInfo();
        inInfo.set(EiConstant.serviceName, "SIWH01");
        inInfo.set(EiConstant.methodName, "selectUseWareHouse");
        EiInfo call = XLocalManager.call(inInfo);
        if(call.getStatus() < 0){
            //没查到
            inInfo.setMsg("查询仓库信息失败" + call.getMsg());
            inInfo.setStatus(-1);
        }
        List<SiWarehouse> list = (List<SiWarehouse>) call.getBlock("result").getRows();
        formTypeBlock.addRows(list);
    }

    /**
     * 根据供应商查询已入库明细中的物资分类
     * @param inInfo
     * @return
     */
    public EiInfo queryMatTypeBySupplier(EiInfo inInfo) {
        //获取参数
        String[] param = {"supplierName","matTypeName"};
        List<String> plist = Arrays.asList(param);
        // 调用工具类转换参数
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
        //数据查询
        List<SiEnterDetail> list = dao.query("SIRK06.queryMatTypeBySupplier", map);
        //返回
        inInfo.addBlock("matType").addRows(list);
        inInfo.getBlock("matType").setBlockMeta(new SiEnterDetail().eiMetadata);
        return inInfo;
    }
}
