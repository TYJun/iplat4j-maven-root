package com.baosight.wilp.ci.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.ci.ck.domain.CiOut;
import com.baosight.wilp.ci.ck.domain.CiOutDetail;
import com.baosight.wilp.ci.common.CiConstant;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.rk.domain.CiEnter;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.*;

/**
 * 库存App接口Service
 * All rights Reserved, Designed By www.bonawise.com
 * @author 24128
 * @version V5.0.0
 * @Title: CIJKApp.java
 * @ClassName: CIJKApp
 * @Package com.baosight.wilp.ci.jk
 * @Description: TODO
 * @date: 2022年1月05日 16:09
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCIJKApp extends ServiceBase {
    /**
     * App入库明细接口
     * @Title: enterWarehousDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo、
     *              startTime       开始时间
     *               endTime        结束时间
     *               matName        物资名称
     *               enterTypeName    入库类型
     *               wareHouseName 仓库名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *          wareHouseName ： 仓库名称
     *		    matName : 物资名称
     *		    enterTypeName: 入库类型
     *		    matSpec : 规格
     *		    unitName：单位
     *		    enterNum： 入库数量
     *		    enterTime 入库时间
     * @throws
     **/
    public EiInfo enterWarehouseDetail(EiInfo inInfo) {
        String startTime= inInfo.getString("startTime");
        String endTime= inInfo.getString("endTime");
        String matName= inInfo.getString("matName");
        String enterTypeName= inInfo.getString("enterTypeName");
        String wareHouseName= inInfo.getString("wareHouseName");
        Map<String, Object> map= new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("matName",matName);
        map.put("enterTypeName",enterTypeName);
        map.put("wareHouseName",wareHouseName);
        List<Map<String,Object>> list= dao.query("CIJKApp.enterWarehouseDetail",map);
        inInfo.set("list",list);
        return inInfo;
    }
    /**
     * App入库明细接口
     * @Title: outWarehouseDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo、
     *              startTime       开始时间
     *               endTime        结束时间
     *               matName        物资名称
     *               outTypeName    出库类型
     *               wareHouseName 仓库名称
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *          wareHouseName ： 仓库名称
     *		    matName : 物资名称
     *		    outTypeName: 出库类型
     *		    matSpec : 规格
     *		    unitName：单位
     *		    outNum： 出库数量
     *		    outTime 出库时间
     * @throws
     **/
    public EiInfo outWarehouseDetail(EiInfo inInfo) {
        String startTime= inInfo.getString("startTime");
        String endTime= inInfo.getString("endTime");
        String matName= inInfo.getString("matName");
        String outTypeName= inInfo.getString("outTypeName");
        String wareHouseName= inInfo.getString("wareHouseName");
        Map<String, Object> map= new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("matName",matName);
        map.put("outTypeName",outTypeName);
        map.put("wareHouseName",wareHouseName);
        List<Map<String,Object>> list= dao.query("CIJKApp.outWarehouseDetail",map);
        inInfo.set("list",list);
        return inInfo;
    }

    /**
     * 食堂进销存选择对接接口
     * @Title: queryStMat
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
    public EiInfo queryStMat(EiInfo inInfo) {
//        HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("prames");
        String wareHouseNo = (String) inInfo.getAttr().get("wareHouseNo");
//        String wareHouseName = CiUtils.isEmpty(attr.get("wareHouseName"));
        //参数处理
        Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("matNum","wareHouseNo", "matTypeName", "matName","offset","limit"));
        //数据查询
        List<Map<String, String>> list = dao.query("CIJK01.queryMtMat", map);
        int count = dao.count("CIJK01.queryMtMatCount", map);
        //数据返回
        return CommonUtils.BuildOutEiInfo(inInfo,"result",null, list, count);
    }

    /**
     * 食堂进销存选择直入直出对接接口
     * @Title: queryStMatZRZC
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo
     *
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
    public EiInfo queryStMatZRZC(EiInfo inInfo) {
        //参数处理
        Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("matNum","matTypeName", "matName","offset","limit"));
        //数据查询
        List<Map<String, String>> list = dao.query("CIJK01.queryMtMatZRZC", map);
        int count = dao.count("CIJK01.queryMtMatZRZCCount", map);
        //数据返回
        return CommonUtils.BuildOutEiInfo(inInfo,"result",null, list, count);
    }


    /**
     * 食堂进销存出库对接接口
     * @Title: stOutStock
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo
     *
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
    public EiInfo stOutStock(EiInfo inInfo) {
        //将维修出库参数转成出库参数
        EiInfo info = BuildParam(inInfo.getAttr());
        //调用出库接口,进行出库
        EiInfo invoke = CiUtils.invoke(info, "CICK0101", "outStock", null, null);
        return invoke;
    }

    /**
     * 将食堂进销存出库参数转成出库对象
     * @Title: BuildParam
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param attr attr : 参数集合
     *
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
     *      List<CiOutDetail> ： 出库明细对象集合
     * @throws
     **/
    private EiInfo BuildParam(Map attr) {
        EiInfo info = new EiInfo();
        //获取参数

        String workNo = CiUtils.isEmpty(attr.get("workNo"));
        String wareHouseNo = CiUtils.isEmpty(attr.get("wareHouseNo"));
        String wareHouseName = CiUtils.isEmpty(attr.get("wareHouseName"));
        List<Map<String, String>> matList = (List<Map<String, String>>) attr.get("stuffList");
        //获取当前登录人信息
        Map<String, Object> userInfo = CiUtils.getUserInfo(workNo);
        String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
        String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
        //构建出库数据
        CiOut out = new CiOut();
        List<CiOutDetail> outDetailList = new ArrayList<>();
        //构建出库主表对象信息
        out.setRecCreator(workNo);
        out.setRecCreateTime(DateUtils.curDateTimeStr19());
        out.setDataGroupCode(dataGroupCode);
        out.setId(UUID.randomUUID().toString());
        String outBillNo = "OW" + DateUtils.curDateTimeStr14();
        out.setOutBillNo(outBillNo);
        out.setOutType(CiConstant.OUT_TYPE_ST);
        out.setOutTypeName("食堂进销存出库");
//        out.setOriginBillNo(taskNo);
        out.setOriginBillType(CiConstant.OUT_RESOURCE_TYPE_ST);
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
            CiOutDetail detail = new CiOutDetail();
            detail.setRecCreator(workNo);
            detail.setRecCreateTime(DateUtils.curDateTimeStr19());
            detail.setId(UUID.randomUUID().toString());
            String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
            detail.setOutBillNo(out.getOutBillNo());
            detail.setOutBillDetailNo(detailBillNo);
            detail.setOutType(CiConstant.OUT_TYPE_ST);
            detail.setOutTypeName("食堂进销存出库");
            detail.setMatNum(mat.get("matNum"));
            detail.setMatName(mat.get("matName"));
            detail.setMatModel(mat.get("matModel"));
            detail.setMatSpec(mat.get("matSpec"));
            detail.setOutNum(NumberUtils.toDouble(mat.get("num"),0d));
            detail.setOutPrice(NumberUtils.toDouble(mat.get("price"),0d));
            detail.setOutAmount(CiUtils.cal(detail.getOutNum(), detail.getOutPrice(), "multiply"));
            detail.setOutDate(DateUtils.curDateStr10());
            detail.setOutTime(DateUtils.curDateTimeStr19());
            outDetailList.add(detail);
        }
        info.set("outDetailList",outDetailList);
        return info;
    }

    /**
     * 食堂进销存直入直出出库对接接口
     * @Title: stOutStock
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo
     *
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
    public EiInfo stOutStockZRZC(EiInfo inInfo) {
        //将维修出库参数转成出库参数
        EiInfo info = BuildParamZRZC(inInfo.getAttr());
        //调用出库接口,进行出库
        EiInfo invoke = CiUtils.invoke(info, "CIRK0101", "enterStock", null, null);
        return invoke;
    }

    /**
     * 将食堂进销存直入直出
     * @Title: BuildParamZRZC
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param attr attr : 参数集合
     *
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
     *      List<CiOutDetail> ： 出库明细对象集合
     * @throws
     **/
    private EiInfo BuildParamZRZC(Map attr) {
        EiInfo info = new EiInfo();
        //获取参数

        String workNo = CiUtils.isEmpty(attr.get("workNo"));
        String wareHouseNo = CiUtils.isEmpty(attr.get("wareHouseNo"));
        String wareHouseName = CiUtils.isEmpty(attr.get("wareHouseName"));
        List<Map<String, String>> matList = (List<Map<String, String>>) attr.get("stuffList");
        //获取当前登录人信息
        Map<String, Object> userInfo = CiUtils.getUserInfo(workNo);
        String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
        String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
        //构建出库数据
        CiEnter out = new CiEnter();
        List<CiEnterDetail> outDetailList = new ArrayList<>();
        //构建出库主表对象信息
        out.setRecCreator(workNo);
        out.setRecCreateTime(DateUtils.curDateTimeStr19());
        out.setDataGroupCode(dataGroupCode);
        out.setId(UUID.randomUUID().toString());
        String outBillNo = "EBN" + DateUtils.curDateTimeStr14();
        out.setEnterBillNo(outBillNo);
        out.setEnterType("01");
        out.setEnterTypeName("食堂进销存直入直出");
//        out.setOriginBillNo(taskNo);
        out.setOriginBillType(CiConstant.ENTER_TYPE_ZRZC);
        out.setWareHouseNo(wareHouseNo);
        out.setWareHouseName(wareHouseName);
        out.setStorageNo("");
        out.setStackNo("");
        out.setBillMaker(workNo);
        out.setBillMakerName(name);
        out.setBillMakeTime(DateUtils.curDateTimeStr19());
        out.setIsCheck(0);
        info.set("enter",out);
        //构建出库明细对象
        for (Map<String, String> mat : matList) {
            CiEnterDetail detail = new CiEnterDetail();
            detail.setRecCreator(workNo);
            detail.setRecCreateTime(DateUtils.curDateTimeStr19());
            detail.setId(UUID.randomUUID().toString());
            String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
            detail.setEnterBillNo(out.getEnterBillNo());
            detail.setEnterBillDetailNo(detailBillNo);
            detail.setEnterType("01");
            detail.setEnterTypeName("食堂进销存直入直出");
            detail.setMatNum(mat.get("matNum"));
            detail.setMatName(mat.get("matName"));
            detail.setMatModel(mat.get("matModel"));
            detail.setMatSpec(mat.get("matSpec"));
            detail.setEnterNum(NumberUtils.toDouble(mat.get("num"),0d));
            detail.setEnterPrice(NumberUtils.toDouble(mat.get("price"),0d));
            detail.setEnterAmount(CiUtils.cal(detail.getEnterNum(), detail.getEnterPrice(), "multiply"));
            detail.setEnterDate(DateUtils.curDateStr10());
            detail.setEnterTime(DateUtils.curDateTimeStr19());
            outDetailList.add(detail);
        }
        info.set("enterDetailList",outDetailList);
        return info;
    }


}
