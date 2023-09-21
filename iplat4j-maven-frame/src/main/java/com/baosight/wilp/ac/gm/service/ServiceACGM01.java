package com.baosight.wilp.ac.gm.service;

import com.baosight.iplat4j.common.ed.domain.TEDCM01;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 物资档案信息管理.
 * <p>
 * 物资档案初始化, 查询, 树结构查询, 数据导入, 更新物资状态, 停用物资分类.
 * </p>
 *
 * @Title ServiceACGM01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 */
@SuppressWarnings("unchecked")
public class ServiceACGM01 extends ServiceBase {

    final String projectSchema = PrUtils.getConfigure();
    final String platSchema = PrUtils.getIplatConfigure();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 页面初始化方法
     * 作者：jzm
     * 入参：EiInfo
     * 出参：EiInfo
     * 处理：调用query()方法
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 查询功能
     * 作者：jzm
     * 入参：物资分类编码"matClassCode", 物资分类名称"matClassName", 物资分类ID"matClassId",
     * 物资编码matCode， 物资名称 matName, 物资大类编码 matTypeCode, 状态 status
     * 出参：EiInfo 处理：
     * 1.将入参转换为map
     * 2.调用query()方法查询出符合条件的物资信息和count
     * 3.将查询结果封装在EiInfo中返
     */
    @Override
    public EiInfo query(EiInfo inInfo) {

        /**
         * 1.将入参转换为map
         */
        String[] param = {"matClassCode", "matClassName", "matClassId", "matCode", "matName", "matTypeCode",
                "status"};
        List<String> plist = Arrays.asList(param);
        Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
        // 默认查询启用的物资
        // 如果status是 null 则为默认初始化查询 需要置status为 1（启用）
        map.putIfAbsent("status", "1");

        /**
         * 2.调用query()方法查询出符合条件的物资信息
         */
        map.put("projectSchema", projectSchema);
        //筛选是MP-仓库账务员、MP-仓库发货员、MP-仓库主管进行判断不可查看进销存物资
        //获取工号ID
        String workNo = UserSession.getUserId();
        map.put("workNo",workNo);
        List<Map<String, String>> getUserRoleList = dao.query("ACGM01.getUserRole", map);
        //获取分组的编码名称
        String roles = getUserRoleList.get(0).get("roles");
        String[] split = roles.split(",");
        //定义用于明确有哪些分组不可见，若为0，则全部物资可见。
        int num = 0 ;
        for (int x = 0;roles.split(",").length > x ; x++ ){
            //MP-仓库账务员、MP-仓库发货员、MP-仓库主管不可见进销存物资
            if (split[x].equals("MPCK001")||split[x].equals("MPCK002")||split[x].equals("MPCK003")){
                num++;
                break;
            }
        }
        List<Map<String, Object>> matList = new ArrayList<Map<String, Object>>();
        int count = 0;
        //限制是否可以看到进销存物资树
        if (num==0){ //若数量为0则可以看到所有的物资树
            matList = dao.query("ACGM01.queryMaterialList", map,
                    Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
            count = super.count("ACGM01.queryMaterialListCount", map);
        }else {//若数量大于0则不可以看到所有的物资树
            matList = dao.query("ACGM01.queryMaterialListExclude", map,
                    Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
            count = super.count("ACGM01.queryMaterialListCountExclude", map);
        }

        /**
         * 3.将查询结果封装在EiInfo中返
         */
        if (!CollectionUtils.isEmpty(matList)) {
            return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(matList.get(0)), matList, count);
        } else {
            return inInfo;
        }

    }

    /**
     * 物资分类删除方法(暂时不使用)
     *
     */
//	public EiInfo delete(EiInfo inInfo) {
//
//		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");	/* 主键*/
//
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("projectSchema", projectSchema);
//		map.put("id", id);
//
//		//验证
//		if(getTrueOrFalse(id)) {
//			dao.delete("ACGM01.delete",map);
//			inInfo.setStatus(0);
//			inInfo.setMsg("删除成功");
//		}else {
//			inInfo.setStatus(-1);
//			inInfo.setMsg("所选物资分类有下级分类，无法删除，请先删除下级物资分类!!!");
//		}
//
//		return inInfo;
//	}

    /**
     * 物资删除方法（暂时不使用）
     *
     * @param inInfo
     * @return
     */
//	public EiInfo deleteMat(EiInfo inInfo) {
//
//		List<String> list = (List<String>)inInfo.get("list");
//
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("projectSchema", projectSchema);
//		map.put("list", list);
//		dao.delete("ACGM01.deleteMat",map);
//		inInfo.setStatus(0);
//		inInfo.setMsg("删除成功");
//		return inInfo;
//	}

    /**
     * 检查物资分类id下是否不存在状态为启用的id（暂时不使用）
     */
//	public Boolean getTrueOrFalse(String id) {
//
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("projectSchema", projectSchema);
//		map.put("id", id);
//		 List<Map<String, Object>> deptList = dao.query("ACGM01.getTrueOrFalse", map);
//		if (CollectionUtils.isNotEmpty(deptList)) {
//			return false;
//		}
//		return true;
//	}

    /**
     * 树结构查询方法
     * 作者：hzd
     * 入参：node (节点id)
     * 出参：EiInfo (以该node节点为双亲的孩子节点)
     * 过程：
     * 1.从inInfo中读取参数
     * 2.设置状态 status 为 1
     * 3.调用query()方法，得到满足上述条件的树形结果集。
     * 4.调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中的result域
     */
    public EiInfo queryTree(EiInfo inInfo) {

        /**
         * 1.从inInfo中读取参数
         */
        Map<String, Object> map = inInfo.getRow("inqu_status", 0);
        map.put("projectSchema", projectSchema);

        /**
         * 2.设置状态 status 为 1
         */
        map.put("status", "1");

        /**
         * 3.调用query()方法，得到满足上述条件的树形结果集。
         */
        //筛选是物资仓的人员进行判断不可查看进销存物资
        //获取工号ID
        String workNo = UserSession.getUserId();
        map.put("workNo",workNo);
        List<Map<String, String>> getUserRoleList = dao.query("ACGM01.getUserRole", map);
        //获取分组的编码名称
        String roles = getUserRoleList.get(0).get("roles");
        String[] split = roles.split(",");
        //定义用于明确有哪些分组不可见，若为0，则全部物资可见。
        int num = 0 ;
        for (int x = 0;roles.split(",").length > x ; x++ ){
            //MP-仓库账务员、MP-仓库发货员、MP-仓库主管不可见进销存物资
            if (split[x].equals("MPCK001")||split[x].equals("MPCK002")||split[x].equals("MPCK003")){
                num++;
                break;
            }
        }
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        //限制是否可以看到进销存物资树
        if (num==0){ //若数量为0则可以看到所有的物资树
             list = dao.query("ACGM01.queryMatTree", map);
        }else {//若数量不为0则不可以看到所有的物资树
             list = dao.query("ACGM01.queryMatTreeExclude", map);
        }

        /**
         * 4.调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中的result域
         */
        PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), list, list.size());
        String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
        inInfo.getBlocks().remove(EiConstant.resultBlock);
        return inInfo;
    }

    /**
     * 从 excel 导入物资数据
     * 作者：jzm
     * 入参：EiInfo（List<Map<String,String>> list 物资数据）
     * 出参：EiInfo（导入结果，如果有错误数据会将错误数据返回）
     * 处理：
     * 1.获取 计量单位集合 放入unitSet中 用于验证
     * 2.获取物资大类编码集合放入 typeSet中 用于验证
     * 3.字段长度，非空验证
     * 4.检查价格是否合法
     * 5.检查分类编号是否存在
     * 6.将正确的数据插入数据库，错误的返回
     */
    public EiInfo importMatFromExcel(EiInfo eiInfo) {
        List<Map<String, String>> inList = (List<Map<String, String>>) eiInfo.get("list");
        List<Map<String, String>> okList = new ArrayList<>();
        List<Map<String, String>> errorList = new ArrayList<>();

        /**
         * 1.获取 计量单位集合 放入unitSet中 用于验证
         */
        EiInfo ei = new EiInfo();
        ei.set(EiConstant.serviceId, "S_ED_36");
        ei.set("codesetCode", "wilp.ac.gm.unit");
        EiInfo out = XServiceManager.call(ei);
        ArrayList<TEDCM01> result = (ArrayList<TEDCM01>) out.getAttr().get("result");
        Set<String> unitSet = new HashSet<>();
        result.forEach(e -> unitSet.add(e.getItemCode()));

        /**
         * 2.获取物资大类编码集合放入 typeSet中 用于验证
         */
        ei.set(EiConstant.serviceId, "S_ED_36");
        ei.set("codesetCode", "wilp.ac.gm.type");
        out = XServiceManager.call(ei);
        result = (ArrayList<TEDCM01>) out.getAttr().get("result");
        Set<String> typeSet = new HashSet<>();
        result.forEach(e -> typeSet.add(e.getItemCode()));

        HashMap<String, String> mp = new HashMap<>();
        mp.put("projectSchema", projectSchema);
        // 拉取所有供应商信息
//        List<HashMap<String, String>> supplierNameAndCodeList = dao.query("ACSU01.queryAllSupplierNameAndCode", mp);
//        HashMap<String, String> supplierMap = new HashMap<>();
//        supplierNameAndCodeList.forEach(e -> supplierMap.put(e.get("supplierName"), e.get("supplierCode")));

        // 生成物资编号 标志位
        boolean matCodeGenByDB = true;
        String globalMatCode = null;

        for (Map<String, String> map : inList) {

            /**
             * 3.字段长度，非空验证
             */
            StringBuilder error = new StringBuilder();
            if (StringUtils.isEmpty(map.get("matName"))) {
                error.append("物资名称为空  ");
            }
            if (StringUtils.isEmpty(map.get("matClassId"))) {
                error.append("物资分类编号为空  ");
            }

            // 如果价格位置填空 则置为0.00
            if (StringUtils.isEmpty(map.get("price"))) {
                map.put("price", "0.00");
            }

            // 判断字段长度是否超出
            if (map.get("matName").length() > 100) {
                error.append("物资名称长度超过100  ");
            }
            if (map.get("matClassId").length() > 36) {
                error.append("物资分类编号长度超过36  ");
            }
            if (map.get("matSpec").length() > 100) {
                error.append("物资规格长度超过100  ");
            }
            if (map.get("matModel").length() > 100) {
                error.append("物资型号长度超过100  ");
            }
            if (map.get("unit").length() > 2) {
                error.append("计量单位编码长度超过2  ");
            }
            if (map.get("price").length() > 255) {
                error.append("最近订购价格长度超过255  ");
            }
//            if (map.get("supplier").length() > 255) {
//                error.append("供应商长度超过255  ");
//            }
            if (map.get("manufacturer").length() > 500) {
                error.append("制造商长度超过500  ");
            }
            if (map.get("matTypeCode").length() > 10) {
                error.append("物资大类长度超过10  ");
            }
            if (map.get("remark").length() > 500) {
                error.append("备注长度超过500  ");
            }

            if (!unitSet.contains(map.get("unit"))) {
                error.append("计量单位编码应填写 ").append(unitSet).append(" 中的一个  ");
            }

            if (!typeSet.contains(map.get("matTypeCode"))) {
                error.append("物资大类编码应填写 ").append(typeSet).append(" 中的一个  ");
            }

            /**
             * 4.检查价格是否合法
             */
            try {
                String price = map.get("price");
                double p = Double.parseDouble(price);
                if (p < 0) { // 如果价格小于零 显然不合理
                    error.append("最近订购价格小于零  ");
                }
            } catch (Exception e) {
                e.printStackTrace();
                error.append("最近订购价格格式有误  ");
            }

            /**
             * 5.检查分类编号是否存在
             */
            HashMap<String, String> map1 = new HashMap<>();
            map1.put("projectSchema", projectSchema);
            map1.put("matClassCode", map.get("matClassId"));
            int count1 = super.count("ACGM01.matClassIsExist", map1);
            if (count1 == 0) {
                error.append("分类编号不存在  ");
            }



            map.put("projectSchema", projectSchema);
            int count = super.count("ACGM01.matIsExist", map);
            if (count != 0) {
                error.append("物资在系统中重复  ");
            }
            map.remove("projectSchema");

            // 如果供应商不为空 则 检查供应商是否存在
//            if (!StringUtils.isEmpty(map.get("supplier"))) {
//                if (supplierMap.containsKey(map.get("supplier"))) {
//                    map.put("supplier", supplierMap.get(map.get("supplier")));
//                } else {
//                    error.append("供应商不存在  ");
//                }
//            }

            /**
             * 6.将正确的数据插入数据库，错误的返回
             */
            // 判断是否存在错误
            if (error.length() == 0) {
                // 无错误，则生成必要信息 然后存入okList
                String matCode;
                if (map.get("matCode") == null || "".equals(map.get("matCode").replace(" ", ""))) {
                    // 先查询出当前最新的 rec_create_time 取出该记录的matNum
                    // 由于 事务 导致 导入时每次查询的结果都是相同的
                    // 设置一个标志位 仅仅 第一次进入时查询数据库最 后一个合法编码 后入全部走自增方法
                    if (matCodeGenByDB) {
                        map.put("projectSchema", projectSchema);
                        List<Map<String, String>> list = dao.query("ACGM01.queryLastMatCode", map);
                        if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号MA00001
                            matCode = "MA00001";
                        } else {
                            matCode = genMatCode(list.get(0).get("matCode")); // 生成物编码
                        }
                        globalMatCode = matCode;
                        matCodeGenByDB = false;
                    } else {
                        matCode = genMatCode(globalMatCode);
                        globalMatCode = matCode;
                    }
                } else {
                    matCode = map.get("matCode");
                }

                String id = UUID.randomUUID().toString();
                String recCreateTime = sdf.format(new Date());
                String recCreater = UserSession.getLoginName();
                map.put("id", id);
                map.put("matCode", matCode);
                map.put("status", "1");
                map.put("recCreater", recCreater);
                map.put("recCreateTime", recCreateTime);
                // 存入okList
                okList.add(map);

            } else {
                // 有错误 添加错误字段 存入errorList
                map.put("error", error.toString());
                errorList.add(map);
            }

        }
        // 如果 okList 不为空,并且错误的为空 则插入数据库
        if (CollectionUtils.isNotEmpty(okList)&&errorList.isEmpty()) {
            // 批量导入okList
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("list", okList);
            map1.put("projectSchema", projectSchema);

            dao.insert("ACGM01.importMatFromExcel", map1);

        }

        // 返回
        EiInfo outInfo = new EiInfo();
        if (!errorList.isEmpty()) {
            outInfo.setStatus(1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("list", errorList);
            outInfo.setAttr(map);
            return outInfo;
        }
        outInfo.setStatus(0);
        return outInfo;
    }

    /**
     * 根据lastMatCode 生成 nextMatCode
     * 作者：jzm
     *
     * @param lastMatCode
     * @return nextMatCode
     */
    public static String genMatCode(String lastMatCode) {
        return "MA" + String.format("%05d", Integer.parseInt(lastMatCode.substring(2)) + 1);
    }

    /**
     * 更新物资状态
     * 作者：jzm
     * 入参：EiInfo(id list, status string)
     * 出参：EiInfo(操作结果)
     * 处理：
     * 1.从eiInfo中读取 list 和 status
     * 2.执行update()方法在数据库中修改
     * 3.返回 操作 结果
     *
     * @param eiInfo
     * @return
     */
    public EiInfo updateMatStatus(EiInfo eiInfo) {
        /**
         *1.从eiInfo中读取 list 和 status
         */
        List<Map<String, String>> list = (List<Map<String, String>>) eiInfo.get("list");
        String status = eiInfo.getString("status");

        /**
         * 2.执行update()方法在数据库中修改
         */
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("status", status);
        map.put("projectSchema", projectSchema);
        dao.update("ACGM01.updateMatStatus", map);

        /**
         * 3.返回 操作 结果
         */
        EiInfo out = new EiInfo();
        if ("0".equals(status)) {
            out.setMsg("停用成功");
        } else {
            out.setMsg("启用成功");
        }
        return out;
    }

    /**
     * 停用物资分类
     * 作者：hcd
     * 入参：EiInfo（matClassId 物资分类id）
     * 出参：EiInfo(操作结果)
     * 处理：
     * 1.首先检查下级分类是否存在，如果不存在 跳转3 如果存在 跳转2
     * 2.提示错误，并返回
     * 3.检查分类下的物资是否存在未停用 如果不存在 跳转4 如果存在 跳转5
     * 4.执行停用操作
     * 5.提示错误并返回
     *
     * @param eiInfo
     * @return
     */
    public EiInfo changeStatusMatClass(EiInfo eiInfo) {
        List<String> list = (List<String>) eiInfo.get("list");
        String status = (String) (eiInfo.get("status"));
        Map map1 = eiInfo.getAttr();
        map1.put("projectSchema", projectSchema);
        if ("1".equals(status)) {
            dao.update("ACGM01.updateClassStatus", map1);
            eiInfo.setMsg("启用成功");
        } else if ("0".equals(status)) {
            for (String id : list) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("projectSchema", projectSchema);
                map.put("id", id);
                int count = dao.count("ACGM01.subClassNotStopCount", map);
                if (count != 0) {
                    eiInfo.setMsg("下级存在启用分类，请先停用！");
                    return eiInfo;
                }

                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("projectSchema", projectSchema);
                map2.put("id", id);
                int count2 = dao.count("ACGM01.matNotStopCount", map2);
                if (count2 != 0) {
                    eiInfo.setMsg("当前分类下存在未停用物资，请先停用！");
                    return eiInfo;
                }
            }

            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("list", list);
            map3.put("status", "0");
            map3.put("projectSchema", projectSchema);
            dao.update("ACGM01.updateClassStatus", map3);
            eiInfo.setMsg("停用成功");
        }
        return eiInfo;
    }

    /**
     * 树形使用
     * 作者：hcd
     * 入参： 空
     * 出参：EiBlockMeta
     * 过程：
     * 1.物资主键
     * 2.物资分类编号
     * 3.物资分类名称
     * 4.父级ID列
     */
    private EiBlockMeta initMetaData() {

        /**
         *  1.物资主键
         */
        EiBlockMeta eiMetadata = new EiBlockMeta();
        EiColumn eiColumn = new EiColumn("label");
        eiColumn.setDescName("科室ID");
        eiColumn.setNullable(false);
        eiColumn.setPrimaryKey(true);
        eiMetadata.addMeta(eiColumn);

        /**
         *  2.物资分类编号
         */
        eiColumn = new EiColumn("matClassCode");
        eiColumn.setDescName("物资分类编号");
        eiColumn.setNullable(false);
        eiColumn.setPrimaryKey(true);
        eiMetadata.addMeta(eiColumn);

        /**
         * 3.物资分类名称
         */
        eiColumn = new EiColumn("matClassName");
        eiColumn.setDescName("物资分类名称");
        eiColumn.setNullable(false);
        eiMetadata.addMeta(eiColumn);

        /**
         * 4.父级ID列
         */
        eiColumn = new EiColumn("pId"); // 作为kendo.data.Model 不能出现id，parent列
        eiColumn.setDescName("父级ID");
        eiColumn.setNullable(true);
        eiMetadata.addMeta(eiColumn);

        return eiMetadata;
    }

    public EiInfo queryUnit(EiInfo info) {
        // 1.获取参数,处理参数
        Map<String, Object> map = new HashMap<>();
        map.put("platSchema",platSchema);
        List<Map<String, String>> result = dao.query("ACGM01.queryUnit",map);
        info.setRows("unit", result);
//        info.addBlock("unit").addRows(result);
        return info;
    }
}
