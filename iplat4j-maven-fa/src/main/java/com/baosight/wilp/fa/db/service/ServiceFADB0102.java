package com.baosight.wilp.fa.db.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.fa.db.domain.FaTransferDetailVO;
import com.baosight.wilp.fa.db.domain.FaTransferVO;
import com.baosight.wilp.fa.db.domain.FaTransfterDTO;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 固定资产调拨管理详情.
 * 固定资产调拨管理初始化方法.
 * 固定资产调拨管理详情查询方法.
 * 固定资产调拨录入方法.
 * 固定资产调拨管理确认.
 * 固定资产调拨管理删除方法.
 * 固定资产调拨管理上传图片时回显.
 * 根据附件id获取附件路径.
 * 固定资产调拨管理保存图片.
 * 固定资产调拨管理图片回显.
 * 查询固定资产调拨信息.
 *
 * @author zhaowei
 * @date 2022年05月31日 10:06
 */
public class ServiceFADB0102 extends ServiceBase {
    /**
     * 固定资产调拨管理初始化方法.
     * 1.获取操作类型并进行逻辑判断
     * 1.1.查看分支
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/5/31 10:08
     */
    @Override
    public EiInfo initLoad(EiInfo info) {
        return info;
    }

    /**
     * 固定资产调拨管理详情查询方法.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/5/31 10:19
     */
    @Override
    public EiInfo query(EiInfo info) {
        return info;
    }

    /**
     * 固定资产调拨录入方法.
     * 获取操作类型(enter-新增，edit-编辑)
     * 获取参数集合(picObject-图片类型,allotObject-Tab列表)
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/1 20:55
     */
    public EiInfo saveFaTransferInfo(EiInfo info) {
        // 获取操作类型(enter-新增，edit-编辑)
        String type = info.getString("type");
        // 获取参数集合(picObject-图片类型,allotObject-Tab列表)
        Object picObject = info.get("picList");
        Object allotList = info.get("allotList");
        // 获取用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
        Map<String, Object> params = info.getBlock("info").getRow(0);
        params.put("faInfoId", params.get("goodsId"));
        params.put("goodsName", params.get("goodsNum_textField"));
        params.put("newUseDeptName", params.get("newUseDeptNum_textField"));
        params.put("allotList", allotList);
        switch (type) {
            // 录入分支
            case "enter":
                params.put("statusCode", "0");
                params.put("transNo", OneSelfUtils.publicCreateCode(FixedAssetsEum.DB.getAcronym()));
                params.put("archiveFlag", 1);
                params.put("recCreator", staffByUserId.get("workNo"));
                params.put("recCreateName", staffByUserId.get("name"));
                params.put("recCreateTime", DateUtils.curDateTimeStr19());
                dao.insert("FADB01.saveFaTransferInfo", params);
                dao.insert("FADB01.saveFaTransferDetailInfo", params);
                dao.update("FADB01.updateFaInfoLockFor", params);
                break;
            // 编辑分支
            case "edit":
                params.put("recRevisor", staffByUserId.get("workNo"));
                params.put("recReviseName", staffByUserId.get("name"));
                params.put("recReviseTime", DateUtils.curDateTimeStr19());
                dao.update("FADB01.updateFaTransferInfo", params);
                List<Map<String, Object>> queryFixedAssestsTabInfoList = dao.query("FADB01.queryFixedAssestsTabInfo", params);
                dao.update("FADA01.updateFaInfoUnlockList", queryFixedAssestsTabInfoList);
                dao.delete("FADB01.removeFaTransferDetailInfo", params);
                dao.insert("FADB01.saveFaTransferDetailInfo", params);
                dao.update("FADB01.updateFaInfoLockFor", params);
                break;
        }
        // 保存图片方法
        savePicInfo(picObject);
        return info;
    }


    /**
     * 固定资产调拨管理确认
     * 判断固定资产是否在盘库.
     * 判断固定资产是否在使用状态.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/5 17:56
     */
    public EiInfo confirmFaTransferInfo(EiInfo info) {
        // 判断固定资产是否在盘库
        if (OneSelfUtils.existsExWife(info.getAttr())) {
            info.setMsg("该科室正在盘库中，调拨操作请等盘库结束!");
            info.setStatus(-1);
            return info;
        }
        // 判断固定资产是否在使用状态
        if (OneSelfUtils.existsLock(info.getAttr())) {
            info.setMsg("该科室正在盘库中，调拨操作请等盘库结束!");
            info.setStatus(-1);
            return info;
        }
        dao.update("FADB01.confirmFaTransferInfo", info.getAttr());
        dao.update("FADB01.updateFaInfo", info.getAttr());
        dao.update("FADB01.updateFaInfoUnlock", info.getAttr());
        return info;
    }

    /**
     * 固定资产调拨管理删除方法.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/6 10:15
     */
    public EiInfo removeFaTransferInfo(EiInfo info) {
        List<Map<String, Object>> queryFixedAssestsTabInfoList = dao.query("FADB01.queryFixedAssestsTabInfo", info.getAttr());
        dao.update("FADA01.updateFaInfoUnlockList", queryFixedAssestsTabInfoList);
        dao.delete("FADB01.removeFaTransferInfo", info.getAttr());
        dao.delete("FADB01.removeFaTransferDetailInfo", info.getAttr());
        // 删除文件
        List<Map<String, Object>> picInfoList = dao.query("FADB01.queryPicInfo", info.getAttr());
        dao.delete("FADB01.removePicInfo", info.getAttr());
        // 物理删除
        if (CollectionUtils.isNotEmpty(picInfoList)) {
            for (Map<String, Object> picInfoMap : picInfoList) {
                if (StringUtils.isNotEmpty((String) picInfoMap.get("filePath"))) {
                    File file = new File((String) picInfoMap.get("filePath"));
                    //判断是否存在
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        }
        return info;
    }

    /**
     * 固定资产调拨管理上传图片时回显
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/1 17:34
     */
    public EiInfo showTempPic(EiInfo info) {
        Object object = info.get("picList");
        List<Map<String, String>> list = object == null ? new ArrayList<>() : (List<Map<String, String>>) object;
        list.forEach(map -> {
            map.put("base64", CommonUtils.imageToBase64Str(getFilePath(map.get("docId"))));
        });
        info.set("list", list);
        return info;
    }

    /**
     * 根据附件id获取附件路径
     *
     * @param docId
     * @return java.lang.String
     * @author zhaowei
     * @date 2022/6/1 17:36
     */
    public static String getFilePath(String docId) {
        //获取文件管理器
        PlatFileUploadManager fileUpLoadManager = (PlatFileUploadManager) PlatApplicationContext.getBean("fileUpLoadManager");
        //获取附件信息
        Map<String, String> docMap = fileUpLoadManager.getDocById(docId);
        //获取路径
        if (docMap == null || docMap.isEmpty()) {
            return "";
        } else {
            return docMap.get("realPath") + docMap.get("chgName");
        }
    }

    /**
     * 固定资产调拨管理保存图片
     *
     * @param picObject
     * @author zhaowei
     * @date 2022/6/6 14:00
     */
    public void savePicInfo(Object picObject) {
        List<Map<String, Object>> picList = new ArrayList<>();
        if (picObject != null) {
            picList = (List<Map<String, Object>>) picObject;
        }
        if (CollectionUtils.isNotEmpty(picList)) {
            dao.insert("FADB01.savePicInfo", picList);
        }
    }

    /**
     * 固定资产调拨管理图片回显
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/6/5 14:51
     */
    public EiInfo showPic(EiInfo info) {
        List<Map<String, String>> list = dao.query("FADB01.showPic", info.getAttr());
        list.forEach(map -> {
            map.put("base64", CommonUtils.imageToBase64Str(map.get("path")));
            map.put("path", "");
        });
        info.set("list", list);
        return info;
    }

    /**
     * 查询固定资产调拨信息.
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/7/8 13:56
     */
    public EiInfo queryFixedAssestsTabInfo(EiInfo info) {
        List<Map<String, Object>> queryFixedAssestsTabInfoList = dao.query("FADB01.queryFixedAssestsTabInfo", info.getAttr());
        info.setRows("resultFixedAssests", queryFixedAssestsTabInfoList);
        return info;
    }

    /**
     * 资产科调拨
     *
     * @param info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @author zhaowei
     * @date 2022/12/15 9:25
     */
    public EiInfo assetOutStorage(EiInfo info) {
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        if (info.get("newFaInfo") != null) {
            List<Map<String, Object>> newFaInfo = (List<Map<String, Object>>) info.get("newFaInfo");
            Map<String, Object> applyInfo = info.getBlock("info").getRow(0);
            StringBuilder stringBuilder = new StringBuilder();
            int inCount = dao.count("FADB01.queryOutFaInventoryInfo", applyInfo);
            if (inCount > 0) {
                stringBuilder.append("调入科室" + applyInfo.get("turnDeptName") + "正在进行盘库操作;");
                info.setStatus(-1);
                info.setMsg(stringBuilder.toString());
                return info;
            }
            if (CollectionUtils.isNotEmpty(newFaInfo)) {
                List<String> faInfoIdList = newFaInfo.stream().map(map -> (String) map.get("faInfoId")).collect(Collectors.toList());
                // 调用出库微服务
                info.set("userDeptNum", applyInfo.get("turnDeptNum"));
                info.set("userDeptName", applyInfo.get("turnDeptName"));
                info.set("billMakerName", staffByUserId.get("name"));
                info.set("billMaker", staffByUserId.get("workNo"));
                info.set("billMakeTime", DateUtils.toDateTimeStr19(new Date()));
                info.set("dataGroupCode", DatagroupUtil.getDatagroupCode());
                // 先将相同物资编码的数据进行合并
                List<FaTransfterDTO> collect = newFaInfo.stream().map(map -> {
                    FaTransfterDTO faTransfterDTO = new FaTransfterDTO();
                    faTransfterDTO.setFaInfoId((String) map.get("faInfoId"));
                    faTransfterDTO.setEnterBillNo((String) map.get("enterBillNo"));
                    faTransfterDTO.setMatNum((String) map.get("matNum"));
                    faTransfterDTO.setMatName((String) map.get("matName"));
                    faTransfterDTO.setMatTypeNum((String) map.get("matTypeNum"));
                    faTransfterDTO.setMatTypeName((String) map.get("matTypeName"));
                    faTransfterDTO.setMatModel((String) map.get("model"));
                    faTransfterDTO.setMatSpec((String) map.get("spec"));
                    faTransfterDTO.setUnit((String) map.get("unitNum"));
                    faTransfterDTO.setUnitName((String) map.get("unitName"));
                    faTransfterDTO.setOutNum(Double.valueOf("1"));
                    faTransfterDTO.setOutPrice(Double.valueOf(String.valueOf(map.get("price"))));
                    return faTransfterDTO;
                }).collect(Collectors.toList());
                Map<String, List<FaTransfterDTO>> collect1 = collect.stream()
                        .collect(Collectors.groupingBy(faTransfterDTO -> faTransfterDTO.getMatNum() + "&" + faTransfterDTO.getEnterBillNo()));
                List<FaTransfterDTO> list = new ArrayList<>();
                collect1.forEach((k, v) -> {
                    if (v.size() != 1) {
                        FaTransfterDTO faTransfterDTO = v.get(0);
                        Double aDouble = v.stream().collect(Collectors.summingDouble(FaTransfterDTO::getOutNum));
                        faTransfterDTO.setOutNum(aDouble);
                        faTransfterDTO.setOutAmount(aDouble * faTransfterDTO.getOutPrice());
                        list.add(faTransfterDTO);
                    } else {
                        list.addAll(v);
                    }
                });
                // 出库
                info.set("outType", "06");
                info.set("costDeptNum", applyInfo.get("turnDeptNum"));
                info.set("costDeptName", applyInfo.get("turnDeptName"));
                info.set("list", list);
                info.set("faInfoIds", faInfoIdList);
                EiInfo eiInfo = OneSelfUtils.invoke(info, "S_SI_FA_03");
                System.out.println("--------------" + eiInfo + "=============");
                if (eiInfo != null) {
                    String claimOutBillNo = eiInfo.getString("claimOutBillNo");
                    System.out.println("新出库单接口");
                    // 修改资产状态：新建->在用
                    dao.update("FADB01.updateFaInfoStatusNewToUse", new HashMap<String, Object>() {{
                        put("faInfoIdList", faInfoIdList);
                        put("deptNum", applyInfo.get("turnDeptNum"));
                        put("deptName", applyInfo.get("turnDeptName"));
                        put("build", applyInfo.get("confirmBuild"));
                        put("floor", applyInfo.get("confirmFloor"));
                        put("room", applyInfo.get("confirmRoom"));
                        put("installLocationNum", applyInfo.get("confirmLocationNum"));
                        put("installLocation", applyInfo.get("confirmLocationNum_textField"));
                        put("outRemark", applyInfo.get("applyReason"));
                        put("useDate", new Date());
                        put("outBillNo", claimOutBillNo);
                    }});
                    FaTransferVO faTransferVO = new FaTransferVO();
                    String transferNo = OneSelfUtils.publicCreateCode(FixedAssetsEum.DB.getAcronym());
                    applyInfo.put("applyTime", DateUtils.toDateStr10(new Date()));
                    applyInfo.put("confirmTime", DateUtils.toDateStr10(new Date()));
                    applyInfo.put("auditTime", DateUtils.toDateStr10(new Date()));
                    applyInfo.put("confirmDeptNum", applyInfo.get("turnDeptNum"));
                    applyInfo.put("confirmDeptName", applyInfo.get("turnDeptName"));
                    applyInfo.put("source", "PC");
                    applyInfo.put("billNo", claimOutBillNo);
                    applyInfo.put("outType", "06");
                    faTransferVO.fromMap(applyInfo);
                    faTransferVO.setId(UUID.randomUUID().toString());
                    faTransferVO.setTransferNo(transferNo);
                    faTransferVO.setApplyDeptNum((String) staffByUserId.get("deptNum"));
                    faTransferVO.setApplyDeptName((String) staffByUserId.get("deptName"));
                    faTransferVO.setApplyPerson((String) staffByUserId.get("name"));
                    faTransferVO.setTransferStatus("100");
                    // 新增调拨记录
                    dao.insert("FADB01.saveFaTransferInfo", faTransferVO);
                    // 新增调拨明细
                    List<FaTransferDetailVO> faTransferDetailVOList = newFaInfo.stream().map(map -> {
                        FaTransferDetailVO faTransferDetailVO = new FaTransferDetailVO();
                        faTransferDetailVO.setId(UUID.randomUUID().toString());
                        faTransferDetailVO.setTransferNo(transferNo);
                        faTransferDetailVO.setFaInfoId((String) map.get("faInfoId"));
                        faTransferDetailVO.setGoodsNum((String) map.get("goodsNum"));
                        faTransferDetailVO.setGoodsName((String) map.get("goodsName"));
                        faTransferDetailVO.setModel((String) map.get("model"));
                        faTransferDetailVO.setSpec((String) map.get("spec"));
                        return faTransferDetailVO;
                    }).collect(Collectors.toList());
                    dao.insert("FADB01.saveFaTransferDetailInfo", faTransferDetailVOList);
                    // 根据调拨的资产id查询资产信息并入账
                    dao.insert("FAAP01.putOutStorageAccount", new HashMap<String, Object>() {{
                        put("faInfoIdList", faInfoIdList);
                        put("deptNum", applyInfo.get("turnDeptNum"));
                        put("deptName", applyInfo.get("turnDeptName"));
                    }});
                } else {
                    info.setStatus(-1);
                    info.setMsg("库存不足，出库单号为空，调拨流程终止，请联系系统工程师。");
                    return info;
                }
            }
        }
        // 调拨方法判断
        if (info.get("useFaInfo") != null) {
            List<Map<String, Object>> useFaInfo = (List<Map<String, Object>>) info.get("useFaInfo");
            if (CollectionUtils.isNotEmpty(useFaInfo)) {
                Map<String, Object> applyInfo = info.getBlock("info").getRow(0);
                StringBuilder stringBuilder = new StringBuilder();
                int inCount = dao.count("FADB01.queryOutFaInventoryInfo", applyInfo.get("deptNum"));
                if (inCount > 0) {
                    stringBuilder.append("调入科室" + applyInfo.get("deptNum_textField") + "正在进行盘库操作;");
                    info.setStatus(-1);
                    info.setMsg(stringBuilder.toString());
                    return info;
                }
                for (int i = 0; i < useFaInfo.size(); i++) {
                    int outCount = dao.count("FADB01.queryOutFaInventoryInfo", useFaInfo.get(i).get("deptNum"));
                    if (outCount > 0) {
                        stringBuilder.append("调出科室:" + useFaInfo.get(i).get("deptName") + "正在进行盘库操作;");
                        info.setStatus(-1);
                        info.setMsg(stringBuilder.toString());
                        return info;
                    }
                }
                List<String> faInfoIdList = useFaInfo.stream().map(map -> (String) map.get("faInfoId")).collect(Collectors.toList());
                // 修改资产状态：在用->调拨中
                dao.update("FADB01.updateFaInfoStatusUsing", new HashMap<String, Object>() {{
                    put("faInfoIdList", faInfoIdList);
                    put("deptNum", applyInfo.get("turnDeptNum"));
                    put("deptName", applyInfo.get("turnDeptName"));
                    put("build", applyInfo.get("confirmBuild"));
                    put("floor", applyInfo.get("confirmFloor"));
                    put("room", applyInfo.get("confirmRoom"));
                    put("installLocationNum", applyInfo.get("confirmLocationNum"));
                    put("installLocation", applyInfo.get("confirmLocationNum_textField"));
                    put("statusCode", "031");
                }});
                FaTransferVO faTransferVO = new FaTransferVO();
                String transferNo = OneSelfUtils.publicCreateCode(FixedAssetsEum.DB.getAcronym());
                applyInfo.put("applyTime", DateUtils.toDateTimeStr19(new Date()));
                applyInfo.put("confirmTime", DateUtils.toDateTimeStr19(new Date()));
                applyInfo.put("auditTime", DateUtils.toDateTimeStr19(new Date()));
                applyInfo.put("confirmDeptNum", applyInfo.get("turnDeptNum"));
                applyInfo.put("confirmDeptName", applyInfo.get("turnDeptName"));
                applyInfo.put("source", "PC");
                faTransferVO.fromMap(applyInfo);
                faTransferVO.setId(UUID.randomUUID().toString());
                faTransferVO.setTransferNo(transferNo);
                faTransferVO.setApplyDeptNum((String) staffByUserId.get("deptNum"));
                faTransferVO.setApplyDeptName((String) staffByUserId.get("deptName"));
                faTransferVO.setConfirmLocationName((String) applyInfo.get("confirmLocationNum_textField"));
                faTransferVO.setApplyPerson((String) staffByUserId.get("name"));
                faTransferVO.setTransferStatus("100");
                // 新增调拨记录
                dao.insert("FADB01.saveFaTransferInfo", faTransferVO);
                // 新增调拨明细
                List<FaTransferDetailVO> faTransferDetailVOList = useFaInfo.stream().map(map -> {
                    FaTransferDetailVO faTransferDetailVO = new FaTransferDetailVO();
                    faTransferDetailVO.setId(UUID.randomUUID().toString());
                    faTransferDetailVO.setTransferNo(transferNo);
                    faTransferDetailVO.setFaInfoId((String) map.get("faInfoId"));
                    faTransferDetailVO.setGoodsNum((String) map.get("goodsNum"));
                    faTransferDetailVO.setGoodsName((String) map.get("goodsName"));
                    faTransferDetailVO.setModel((String) map.get("model"));
                    faTransferDetailVO.setSpec((String) map.get("spec"));
                    return faTransferDetailVO;
                }).collect(Collectors.toList());
                dao.insert("FADB01.saveFaTransferDetailInfo", faTransferDetailVOList);
            }
        }
        return info;
    }

    // 资产调拨回仓库
    public EiInfo assetOutWarehouse(EiInfo info) {
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
        if (info.get("backZFaInfo") != null && info.get("backSFaInfo") != null) {
            List<Map<String, Object>> backZFaInfo = (List<Map<String, Object>>) info.get("backZFaInfo");
            List<Map<String, Object>> backSFaInfo = (List<Map<String, Object>>) info.get("backSFaInfo");
            String transferNo = OneSelfUtils.publicCreateCode(FixedAssetsEum.DB.getAcronym());
            if (CollectionUtils.isNotEmpty(backZFaInfo)) {
                // 调用出库微服务
                // 先将相同物资编码的数据进行合并
                List<FaTransfterDTO> collect = backZFaInfo.stream().map(map -> {
                    FaTransfterDTO faTransfterDTO = new FaTransfterDTO();
                    faTransfterDTO.setFaInfoId((String) map.get("faInfoId"));
                    faTransfterDTO.setEnterBillNo((String) map.get("enterBillNo"));
                    faTransfterDTO.setOutBillNo((String) map.get("outBillNo"));
                    faTransfterDTO.setMatNum((String) map.get("matNum"));
                    faTransfterDTO.setMatName((String) map.get("matName"));
                    faTransfterDTO.setMatTypeNum((String) map.get("matTypeNum"));
                    faTransfterDTO.setMatTypeName((String) map.get("matTypeName"));
                    faTransfterDTO.setMatModel((String) map.get("model"));
                    faTransfterDTO.setMatSpec((String) map.get("spec"));
                    faTransfterDTO.setUnit((String) map.get("unitNum"));
                    faTransfterDTO.setUnitName((String) map.get("unitName"));
                    faTransfterDTO.setOutNum(Double.valueOf("1"));
                    faTransfterDTO.setOutPrice(Double.valueOf(String.valueOf(map.get("price"))));
                    return faTransfterDTO;
                }).collect(Collectors.toList());
                Map<String, List<FaTransfterDTO>> collect1 = collect.stream()
                        .collect(Collectors.groupingBy(faTransfterDTO -> faTransfterDTO.getMatNum() + "&" + faTransfterDTO.getOutBillNo()));
                List<FaTransfterDTO> list = new ArrayList<>();
                collect1.forEach((k, v) -> {
                    List<String> faInfoIdList = new ArrayList();
                    info.set("outType", "05");
                    info.set("outBillNo", k.split("&")[1]);
                    if (v.size() > 0) {
                        for (int i = 0; i < v.size(); i++) {
                            String faInfoId = v.get(i).getFaInfoId();
                            faInfoIdList.add(faInfoId);
                        }
                        FaTransfterDTO faTransfterDTO = v.get(0);
                        Double aDouble = v.stream().collect(Collectors.summingDouble(FaTransfterDTO::getOutNum));
                        faTransfterDTO.setOutNum(aDouble);
                        faTransfterDTO.setOutAmount(aDouble * faTransfterDTO.getOutPrice());
                        list.add(faTransfterDTO);
                    }
                    info.set("list", list);
                    info.set("faInfoIds", faInfoIdList);
                    info.set("billMaker", staffByUserId.get("workNo"));
                    info.set("billMakerName", staffByUserId.get("name"));
                    info.set("billMakeTime", DateUtils.toDateTimeStr19(new Date()));
                    // 退库
                    EiInfo eiInfo = OneSelfUtils.invoke(info, "S_SI_FA_03");
                    System.out.println("新退库单接口");
                    System.out.println("eiInfo:" + eiInfo);
                    if (eiInfo != null){
                        info.setStatus(-1);
                        info.setMsg("调拨回仓库操作失败，原因：'直入直出'状态的资产不能进行调拨回仓库，请在仓库模块进行退货操作");
                        Object o = eiInfo.get("outList");
                        if (o != null) {
                            List<Map> outList = (List<Map>) eiInfo.get("outList");
                            String outBillNo = (String) outList.get(0).get("outBillNo");
                            String originBillNo = (String) outList.get(0).get("originBillNo");
                            // 更新对应的资产状态、科室编码、科室名称、所在位置、资产状态从在用变成000
                            dao.update("FADB01.updateFaInfoStatusUsing", new HashMap<String, Object>() {{
                                put("faInfoIdList", faInfoIdList);
                                put("deptNum", "");
                                put("deptName", "");
                                put("build", "");
                                put("floor", "");
                                put("room", "");
                                put("installLocationNum", "");
                                put("installLocation", "");
                                put("statusCode", "039");
                            }});
                            FaTransferVO faTransferVO = new FaTransferVO();
                            Map<String, Object> applyInfo = new HashMap<>();
                            applyInfo.put("applyTime", DateUtils.toDateStr10(new Date()));
                            applyInfo.put("confirmTime", DateUtils.toDateStr10(new Date()));
                            applyInfo.put("auditTime", DateUtils.toDateStr10(new Date()));
                            applyInfo.put("source", "PC");
                            applyInfo.put("billNo", outBillNo);
                            applyInfo.put("outType", "05");
                            faTransferVO.fromMap(applyInfo);
                            faTransferVO.setId(UUID.randomUUID().toString());
                            faTransferVO.setTransferNo(transferNo);
                            faTransferVO.setApplyDeptNum((String) staffByUserId.get("deptNum"));
                            faTransferVO.setApplyDeptName((String) staffByUserId.get("deptName"));
                            faTransferVO.setApplyPerson((String) staffByUserId.get("name"));
                            faTransferVO.setTransferStatus("100");
                            // 新增调拨记录
                            dao.insert("FADB01.saveFaTransferInfo", faTransferVO);
                            // 新增调拨明细
                            List<FaTransferDetailVO> faTransferDetailVOList = backZFaInfo.stream().map(map -> {
                                FaTransferDetailVO faTransferDetailVO = new FaTransferDetailVO();
                                faTransferDetailVO.setId(UUID.randomUUID().toString());
                                faTransferDetailVO.setTransferNo(transferNo);
                                faTransferDetailVO.setFaInfoId((String) map.get("faInfoId"));
                                faTransferDetailVO.setGoodsNum((String) map.get("goodsNum"));
                                faTransferDetailVO.setGoodsName((String) map.get("goodsName"));
                                faTransferDetailVO.setModel((String) map.get("model"));
                                faTransferDetailVO.setSpec((String) map.get("spec"));
                                return faTransferDetailVO;
                            }).collect(Collectors.toList());
                            dao.insert("FADB01.saveFaTransferDetailInfo", faTransferDetailVOList);
                        } else {
                            info.setStatus(-1);
                            info.setMsg("退库操作失败，原因：退库接口返回数据为空");
                        }
                    } else {
                        info.setStatus(-1);
                        info.setMsg("退库操作失败，原因：退库接口调用失败");
                    }
                });
                return info;
            }
            // 手工入库
            if (CollectionUtils.isNotEmpty(backSFaInfo)) {
                // 调用出库微服务
                // 先将相同物资编码的数据进行合并
                List<FaTransfterDTO> collect = backSFaInfo.stream().map(map -> {
                    FaTransfterDTO faTransfterDTO = new FaTransfterDTO();
                    faTransfterDTO.setFaInfoId((String) map.get("faInfoId"));
                    faTransfterDTO.setEnterBillNo((String) map.get("enterBillNo"));
                    faTransfterDTO.setOutBillNo((String) map.get("outBillNo"));
                    faTransfterDTO.setMatNum((String) map.get("matNum"));
                    faTransfterDTO.setMatName((String) map.get("matName"));
                    faTransfterDTO.setMatTypeNum((String) map.get("matTypeNum"));
                    faTransfterDTO.setMatTypeName((String) map.get("matTypeName"));
                    faTransfterDTO.setMatModel((String) map.get("model"));
                    faTransfterDTO.setMatSpec((String) map.get("spec"));
                    faTransfterDTO.setUnit((String) map.get("unitNum"));
                    faTransfterDTO.setUnitName((String) map.get("unitName"));
                    faTransfterDTO.setOutNum(Double.valueOf("1"));
                    faTransfterDTO.setOutPrice(Double.valueOf(String.valueOf(map.get("price"))));
                    return faTransfterDTO;
                }).collect(Collectors.toList());
                Map<String, List<FaTransfterDTO>> collect1 = collect.stream()
                        .collect(Collectors.groupingBy(faTransfterDTO -> faTransfterDTO.getMatNum() + "&" + faTransfterDTO.getOutBillNo()));
                List<FaTransfterDTO> list = new ArrayList<>();
                collect1.forEach((k, v) -> {
                    List<String> faInfoIdList = new ArrayList();
                    info.set("outType", "05");
                    info.set("outBillNo", k.split("&")[1]);
                    if (v.size() > 0) {
                        for (int i = 0; i < v.size(); i++) {
                            String faInfoId = v.get(i).getFaInfoId();
                            faInfoIdList.add(faInfoId);
                        }
                        FaTransfterDTO faTransfterDTO = v.get(0);
                        Double aDouble = v.stream().collect(Collectors.summingDouble(FaTransfterDTO::getOutNum));
                        faTransfterDTO.setOutNum(aDouble);
                        faTransfterDTO.setOutAmount(aDouble * faTransfterDTO.getOutPrice());
                        list.add(faTransfterDTO);
                    }
                    info.set("list", list);
                    info.set("faInfoIds", faInfoIdList);
                    info.set("billMaker", staffByUserId.get("workNo"));
                    info.set("billMakerName", staffByUserId.get("name"));
                    info.set("billMakeTime", DateUtils.toDateTimeStr19(new Date()));
                    // 退库
                    EiInfo eiInfo = OneSelfUtils.invoke(info, "S_SI_FA_03");
                    System.out.println("新退库单接口");
                    System.out.println("eiInfo:" + eiInfo);
                    if (eiInfo != null){
                        Object o = eiInfo.get("outList");
                        if (o != null) {
                            List<Map> outList = (List<Map>) eiInfo.get("outList");
                            String outBillNo = (String) outList.get(0).get("outBillNo");
                            String originBillNo = (String) outList.get(0).get("originBillNo");
                            for (Map map : backSFaInfo) {
                                faInfoIdList.add((String) map.get("id"));
                            }
                            // 更新对应的资产状态、科室编码、科室名称、所在位置、资产状态从在用变成000
                            dao.update("FADB01.updateFaInfoStatusUsing", new HashMap<String, Object>() {{
                                put("faInfoIdList", faInfoIdList);
                                put("deptNum", "");
                                put("deptName", "");
                                put("build", "");
                                put("floor", "");
                                put("room", "");
                                put("installLocationNum", "");
                                put("installLocation", "");
                                put("statusCode", "010");
                            }});
                            FaTransferVO faTransferVO = new FaTransferVO();
                            Map<String, Object> applyInfo = new HashMap<>();
                            applyInfo.put("applyTime", DateUtils.toDateStr10(new Date()));
                            applyInfo.put("confirmTime", DateUtils.toDateStr10(new Date()));
                            applyInfo.put("auditTime", DateUtils.toDateStr10(new Date()));
                            applyInfo.put("source", "PC");
                            applyInfo.put("billNo", outBillNo);
                            applyInfo.put("outType", "05");
                            faTransferVO.fromMap(applyInfo);
                            faTransferVO.setId(UUID.randomUUID().toString());
                            faTransferVO.setTransferNo(transferNo);
                            faTransferVO.setApplyDeptNum((String) staffByUserId.get("deptNum"));
                            faTransferVO.setApplyDeptName((String) staffByUserId.get("deptName"));
                            faTransferVO.setApplyPerson((String) staffByUserId.get("name"));
                            faTransferVO.setTransferStatus("100");
                            // 新增调拨记录
                            dao.insert("FADB01.saveFaTransferInfo", faTransferVO);
                            // 新增调拨明细
                            List<FaTransferDetailVO> faTransferDetailVOList = backSFaInfo.stream().map(map -> {
                                FaTransferDetailVO faTransferDetailVO = new FaTransferDetailVO();
                                faTransferDetailVO.setId(UUID.randomUUID().toString());
                                faTransferDetailVO.setTransferNo(transferNo);
                                faTransferDetailVO.setFaInfoId((String) map.get("faInfoId"));
                                faTransferDetailVO.setGoodsNum((String) map.get("goodsNum"));
                                faTransferDetailVO.setGoodsName((String) map.get("goodsName"));
                                faTransferDetailVO.setModel((String) map.get("model"));
                                faTransferDetailVO.setSpec((String) map.get("spec"));
                                return faTransferDetailVO;
                            }).collect(Collectors.toList());
                            dao.insert("FADB01.saveFaTransferDetailInfo", faTransferDetailVOList);
                        } else {
                            info.setStatus(-1);
                            info.setMsg("退库操作失败，原因：退库接口返回数据为空");
                        }
                    } else {
                        info.setStatus(-1);
                        info.setMsg("退库操作失败，原因：退库接口调用失败");
                    }
                });
                return info;
            }
        } else {
            info.setStatus(-1);
            info.setMsg("调拨失败：调拨物资为空");
        }
        return info;
    }
}
