package com.baosight.wilp.si.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.si.common.SiExcelHeader;
import com.baosight.wilp.si.web.service.SiExcelImportService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存Excel导入导出Service
 * @ClassName: SiStorageExcelServiceImpl
 * @Package com.baosight.wilp.si.web.service.impl
 * @date: 2023年02月17日 9:38
 */
@Service
public class SiStorageExcelServiceImpl implements SiExcelImportService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");


    /**
     * 构建Excel表头
     * @Title: buildHeader
     * @return java.util.List<com.baosight.wilp.si.common.SiExcelHeader>
     * @throws
     **/
    @Override
    public List<SiExcelHeader> buildHeader() {
        return null;
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
        List<String[]> listData = new ArrayList<>();
        SqlMapDao sqlDao = (SqlMapDao) dao;
        sqlDao.setMaxQueryCount(5000);
        List<LinkedHashMap> list = sqlDao.query("SIKC01.exportData", map);
        if(CollectionUtils.isNotEmpty(list)) {
            List<Object[]> collect = list.stream().map(m -> m.values().toArray(new Object[m.size()])).collect(Collectors.toList());
            listData = JSON.parseArray(JSON.toJSONString(collect), String[].class);
        }

        return listData;
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
        return null;
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
        return null;
    }
}
