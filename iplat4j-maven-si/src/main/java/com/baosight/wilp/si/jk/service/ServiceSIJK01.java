package com.baosight.wilp.si.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.BeanExchangeUtils;
import com.baosight.wilp.si.common.SerialNoUtils;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.*;

/**
 * 库存与维修对接接口Service
 * All rights Reserved, Designed By www.bonawise.com
 * @author fangjian
 * @version V5.0.2
 * @Title: SIJK01.java
 * @ClassName: SIJK01
 * @Package com.baosight.wilp.si.jk
 * @Description: TODO
 * @date: 2021年12月07日 16:09
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceSIJK01 extends ServiceBase {

    /**
     * 维修耗材选择对接接口
     * @Title: queryMat
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo
     *      wareHouseNo： 仓库号
     *      matTypeName ： 物资分类名称
     *      matName ： 物资名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * 		matNum :  物资编码
     *		matName :  物资名称
     *		wareHouseNo :  仓库编码
     *		wareHouseName :  仓库名称
     *		surpNum :  供应商编码
     *		price :  最近订购单价
     *		matTypeNum :  物资分类编码
     *		matTypeName :  物资分类名称
     *		matModel :  物资型号
     *		matSpec :  物资规格
     *		unit :  计量单位编码
     *		unitName :  计量单位名称
     * @throws
     **/
    public EiInfo queryMtMat(EiInfo inInfo) {
        //参数处理
        Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("wareHouseNo", "matTypeName", "matName","offset","limit"));
        //数据查询
        List<Map<String, String>> list = dao.query("SIJK01.queryMtMat", map);
        int count = dao.count("SIJK01.queryMtMatCount", map);
        //数据返回
        return CommonUtils.BuildOutEiInfo(inInfo,"result",null, list, count);
    }

    /**
     * 维修出库对接接口
     * @Title: mtOutStock
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo
     *      taskNo : 工单号
     *      wareHouseNo : 仓库号
     *      wareHouseName ： 仓库名称
     *      stuffList： 物资集合
     * 		    matNum : 物资编码
     *		    matName : 物资名称
     *		    matTypeNum ： 物资分类编码
     *		    matTypeName : 物资分类名称
     *		    matSpec : 规格
     *		    matModel : 型号
     *		    num : 出库数量
     *		    price ： 单价
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo mtOutStock(EiInfo inInfo) {
        //将维修出库参数转成出库参数
        EiInfo info = BuildParam(inInfo.getAttr());
        //调用出库接口,进行出库
        EiInfo invoke = SiUtils.invoke(info, "SICK0101", "outStock", null, null);
        return invoke;
    }

    /**
     * 将维修出库参数转成出库对象
     * @Title: BuildParam
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param attr attr : 参数集合
     *      taskNo : 工单号
     *      workNo : 操作人工号
     *      wareHouseNo : 仓库号
     *      wareHouseName ： 仓库名称
     *      stuffList： 物资集合
     * 		    matNum : 物资编码
     *		    matName : 物资名称
     *		    matTypeNum ： 物资分类编码
     *		    matTypeName : 物资分类名称
     *		    matSpec : 规格
     *		    matModel : 型号
     *		    num : 出库数量
     *		    price ： 单价
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      siOut : 出库对象
     *      List<SiOutDetail> ： 出库明细对象集合
     * @throws
     **/
    private EiInfo BuildParam(Map attr) {
        EiInfo info = new EiInfo();
        //获取参数
        String taskNo = SiUtils.isEmpty(attr.get("taskNo"));
        String workNo = SiUtils.isEmpty(attr.get("workNo"));
        String wareHouseNo = SiUtils.isEmpty(attr.get("wareHouseNo"));
        String wareHouseName = SiUtils.isEmpty(attr.get("wareHouseName"));
        List<Map<String, String>> matList = (List<Map<String, String>>) attr.get("stuffList");
        //获取当前登录人信息
        Map<String, Object> userInfo = SiUtils.getUserInfo(workNo);
        String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
        String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
        //构建出库数据
        SiOut out = new SiOut();
        List<SiOutDetail> outDetailList = new ArrayList<>();
        //构建出库主表对象信息
        out.setRecCreator(workNo);
        out.setRecCreateTime(DateUtils.curDateTimeStr19());
        out.setDataGroupCode(dataGroupCode);
        out.setId(UUID.randomUUID().toString());
        //String outBillNo = "OW" + DateUtils.curDateTimeStr14();
        String outBillNo = SerialNoUtils.generateSerialNo("si_out", "OW");
        out.setOutBillNo(outBillNo);
        out.setOutType(SiConstant.OUT_TYPE_WX);
        out.setOutTypeName("维修出库");
        out.setOriginBillNo(taskNo);
        out.setOriginBillType(SiConstant.OUT_RESOURCE_TYPE_WX);
        out.setWareHouseNo(wareHouseNo);
        out.setWareHouseName(wareHouseName);
        out.setStorageNo("");
        out.setStackNo("");
        out.setBillMaker(workNo);
        out.setBillMakerName(name);
        out.setBillMakeTime(DateUtils.curDateTimeStr19());
        out.setIsCheck(0);
        info.set("out",out);
        //构建出库明细对象
        for (Map<String, String> mat : matList) {
            SiOutDetail detail = new SiOutDetail();
            detail.setRecCreator(workNo);
            detail.setRecCreateTime(DateUtils.curDateTimeStr19());
            detail.setId(UUID.randomUUID().toString());
            //String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
            String detailBillNo = SerialNoUtils.generateSerialNo("si_out_detail", "OBDN", DateUtils.DATE8_PATTERN, 6);
            detail.setOutBillNo(out.getOutBillNo());
            detail.setOutBillDetailNo(detailBillNo);
            detail.setOutType(SiConstant.OUT_TYPE_WX);
            detail.setOutTypeName("维修出库");
            detail.setMatNum(mat.get("matNum"));
            detail.setMatName(mat.get("matNum"));
            detail.setMatModel(mat.get("matModel"));
            detail.setMatSpec(mat.get("matSpec"));
            detail.setOutNum(NumberUtils.toDouble(mat.get("num"),0d));
            detail.setOutPrice(NumberUtils.toDouble(mat.get("price"),0d));
            detail.setOutAmount(SiUtils.cal(detail.getOutNum(), detail.getOutPrice(), "multiply"));
            detail.setOutDate(DateUtils.curDateStr10());
            detail.setOutTime(DateUtils.curDateTimeStr19());
            out.setTotalNum(out.getTotalNum() + detail.getOutNum());
            out.setTotalAmount(out.getTotalAmount() + detail.getOutAmount());
            outDetailList.add(detail);
        }
        info.set("outDetailList",outDetailList);
        return info;
    }
}
