package com.baosight.wilp.hr.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hr.common.HrExcelHeader;
import com.baosight.wilp.hr.common.HrExcelUtils;
import com.baosight.wilp.hr.common.SerialNoUtils;
import com.baosight.wilp.hr.web.service.HrExcelImportService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 入库excel导入导出Service
 * @ClassName: SiEnterExcelServiceImpl
 * @Package com.baosight.wilp.si.web.service.impl
 * @date: 2023年02月15日 17:28
 */
@Service("hrExcelImportService")
public class HrExcelImportServiceImpl implements HrExcelImportService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    private static List<String> validDepartments = CommonUtils.getDataCode("wilp.hr.dept").stream().map(e -> e.get("value")).collect(Collectors.toList());

    private static List<String> deptNames = new ArrayList<>();

    /**入库Excel表头对应字段**/
    private String[] columnHeaders = new String[]{"realName", "phone", "sex", "birthDate", "manCode", "kampong",
        "birthPlace", "schoolingCode", "jobCode", "preInTime","health", "familyAddress", "maritalStatus", "highestEducational", "highestDegree", "company", "salary", "politicalStatus", "personnelCategory", "deptNum", "serviceDeptNum", "emergency", "emergencyPhone", "managementDeptNum", "memo"};

    /**
     * 构建Excel表头
     * @Title: buildHeader
     * @return java.util.List<com.baosight.wilp.hr.common.SiExcelHeader>
     * @throws
     **/
    @Override
    public List<HrExcelHeader> buildHeader() {
        List<String> enterHeads = Arrays.asList("姓名（必填）","联系电话（必填）", "性别","出生日期","身份证号码（必填）","民族（必填）",
                "籍贯","学历（必填）", "岗位（必填）", "预计入职时间","健康状况（必填）", "现住址（必填）","婚姻状况", "最高学历", "最高学位", "公司名称（必填）","基本工资","政治面貌","人员类别（必填）","所属部门（必填）","服务部门（必填）","紧急联系人（必填）","紧急联系人电话（必填）","管理部门（必填）","备注");
        List<HrExcelHeader> headerList = HrExcelHeader.toInputHeaders(enterHeads);
        cacheHeaders.addAll(headerList);
        return headerList;
    }

    /**
     * 构建Excel导出数据
     * @Title: buildData
     * @param map map : 参数
     * @return java.util.List<java.lang.String[]>
     * @throws
     **/
    @Override
    public List<String[]> buildData(Map<String, Object> map) {
        return null;
    }

    /**
     * 解析Excel导入数据行
     * @Title: parseRow
     * @param row row : excel数据行
     * @param dataList dataList : excel数据集合
     * @param errorList errorList : excel错误数据集合
     * @return void
     * @throws
     **/
    @Override
    public void parseRow(Row row, List<Object> dataList, List<String[]> errorList) {
        String[] errorRow = new String[columnHeaders.length+1];
        /**1.获取Excel行数据**/
        Map<String, String> map = new HashMap<>(columnHeaders.length);
        for (int i = 0; i < columnHeaders.length; i++) {
            map.put(columnHeaders[i], HrExcelUtils.getCellValue(row.getCell(i)));
            errorRow[i] = HrExcelUtils.getCellValue(row.getCell(i));
        }
        /**2.数据校验**/
        String errMsg = validate(map);

        /**3.数据赋值**/
        if(StringUtils.isBlank(errMsg)) {
            dataList.add(map);
        } else {
            errorRow[errorRow.length-1] = errMsg;
            errorList.add(errorRow);
        }

    }

    /**
     * Excel导入数据校验
     * @Title: validate
     * @param object object
     * @return java.lang.String
     * @throws
     **/
    @Override
    public String validate(Object object) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = (Map<String, String>) object;

        //校验姓名是否为空
        Map<String, String> wareHouseMap = null;
        if(StringUtils.isBlank(map.get("realName"))) {
            sb.append("姓名不能为空\r\n");
        }

        //校验手机号是否为空
        if(StringUtils.isBlank(map.get("phone"))) {
            sb.append("联系电话不能为空\r\n");
        }else {
            String phonePattern = "^1[3456789]\\d{9}$";  // 手机号正则表达式
            if(!map.get("phone").matches(phonePattern)) {
                sb.append("联系电话格式不正确\r\n");
            }
        }

        //校验身份证号码是否为空
        if(StringUtils.isBlank(map.get("manCode"))) {
            sb.append("身份证号码不能为空\r\n");
        }else {
            String idCardPattern = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";  // 身份证号码正则表达式
            if(!map.get("manCode").matches(idCardPattern)) {
                sb.append("身份证号码格式不正确\r\n");
            }
        }

        //校验民族是否为空
        if(StringUtils.isBlank(map.get("kampong"))) {
            sb.append("民族不能为空\r\n");
        }

        //校验学历是否为空
        if(StringUtils.isBlank(map.get("schoolingCode"))) {
            sb.append("学历不能为空\r\n");
        }

        //校验岗位是否为空
        if(StringUtils.isBlank(map.get("jobCode"))) {
            sb.append("岗位不能为空\r\n");
        }

        //校验健康状况是否为空
        if(StringUtils.isBlank(map.get("health"))) {
            sb.append("健康状况不能为空\r\n");
        }

        //校验现住址是否为空
        if(StringUtils.isBlank(map.get("familyAddress"))) {
            sb.append("现住址不能为空\r\n");
        }

        //校验公司名称是否为空
        if(StringUtils.isBlank(map.get("company"))) {
            sb.append("公司名称不能为空\r\n");
        }

        //校验人员类别是否为空
        if(StringUtils.isBlank(map.get("personnelCategory"))) {
            sb.append("人员类别不能为空\r\n");
        }else{
            //校验人员类别是否在有效值范围内
            String personnelCategory = map.get("personnelCategory");
            List<String> validCategories = Arrays.asList("后勤保障中心", "第三方人员");
            if(StringUtils.isBlank(personnelCategory) || !validCategories.contains(personnelCategory)) {
                sb.append("所填写人员类别信息有误\r\n");
            }
        }

        //校验所属部门是否为空
        if(StringUtils.isBlank(map.get("deptNum"))) {
            sb.append("所属部门不能为空\r\n");
        }else {
            if(!checkDeptName(map.get("deptNum"))){
                sb.append("所属部门名称不存在\r\n");
            }
        }

        //校验服务部门是否为空
        if(StringUtils.isBlank(map.get("serviceDeptNum"))) {
            sb.append("服务部门不能为空\r\n");
        }else {
            if(!checkDeptName(map.get("serviceDeptNum"))){
                sb.append("服务部门部门名称不存在\r\n");
            }
        }

        //校验紧急联系人是否为空
        if(StringUtils.isBlank(map.get("emergency"))) {
            sb.append("紧急联系人不能为空\r\n");
        }

        //校验紧急联系人电话是否为空
        if(StringUtils.isBlank(map.get("emergencyPhone"))) {
            sb.append("紧急联系人电话不能为空\r\n");
        }else {
            String phonePattern = "^1[3456789]\\d{9}$";  // 手机号正则表达式
            if(!map.get("emergencyPhone").matches(phonePattern)) {
                sb.append("紧急联系人电话格式不正确\r\n");
            }
        }

        //校验管理部门是否为空
        String managementDeptNum = map.get("managementDeptNum");
        if(StringUtils.isBlank(managementDeptNum)) {
            sb.append("管理部门不能为空\r\n");
        }else{
            //校验管理部门是否在有效值范围内
            if(!getDept().contains(managementDeptNum)) {
                sb.append("所填写管理部门不存在\r\n");
            }
        }

        //校验预计入职日期格式是否正确
        if(!checkDate(map.get("preInTime"))) {
            sb.append("预计入职日期格式错误(应为yyyy-MM-dd)\r\n");
        }

        //校验出生日期格式是否正确
        if(!checkDate(map.get("birthDate"))) {
            sb.append("出生日期格式错误(应为yyyy-MM-dd)\r\n");
        }


        return sb.toString();
    }

    private List<String> getDept() {
        if(validDepartments.isEmpty()){
            validDepartments = CommonUtils.getDataCode("wilp.hr.dept").stream().map(e -> e.get("value")).collect(Collectors.toList());
        }
        return validDepartments;
    }

    /**
     * 清理缓存
     * @Title: clearCache
     * @return void
     * @throws
     **/
    private void clearCache() {
        validDepartments.clear();
        deptNames.clear();
    }

    /**
     * 校验日期的格式
     * @Title: checkDate
     * @param dateStr dateStr
     * @return boolean
     * @throws
     **/
    private static boolean checkDate(String dateStr) {
        if(StringUtils.isBlank(dateStr)){
            return true;
        }
        try {
            DateUtils.toDate(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkDeptName(String deptName) {
        if(deptNames.isEmpty()){
            //查询科室名称，添加到集合
            deptNames = BaseDockingUtils.getDeptAllNoPage(new EiInfo()).stream().map(e -> e.get("deptName").toString()).collect(Collectors.toList());
        }
        // 去除（业）并在校验时忽略大小写
        String deptNameMage = deptName.replace("（业）", "").trim();

        return deptNames.contains(deptNameMage);
    }

    /**
     * 保存导入数据
     * @Title: saveData
     * @param list list : excel导入数据集合
     * @return java.lang.String
     * @throws
     **/
    @Override
    public String saveData(List<Object> list) {
        if(list.isEmpty()) { return null; }
        /**数据处理**/
        List<Map> dataList = JSON.parseArray(JSON.toJSONString(list), Map.class);
        /**生成工号**/
        for (Map map : dataList) {
            String workNo = SerialNoUtils.generateNumberSerialNo("hr_user", "TH", 5);
            String id =UUID.randomUUID().toString();
            map.put("id",id);
            map.put("workNo",workNo);
            map.put("statusCode","01");
            map.put("creatorName", "admin");
            map.put("createDate", DateUtils.curDateTimeStr19());
            dao.insert("HRXX01.insert",map);

        }
        clearCache();
        return null;
    }
}
