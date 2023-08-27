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
 * @version V5.0.0
 * @Description: 日度库存Excel导出Service实现
 * @ClassName: SiDayStorageExportServiceImpl
 * @Package com.baosight.wilp.si.web.service.impl
 * @date: 2023年04月26日 19:21
 */
@Service("siDayStorageExportService")
public class SiDayStorageExportServiceImpl implements SiExcelImportService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    @Override
    public List<SiExcelHeader> buildHeader() {
        return null;
    }

    @Override
    public List<String[]> buildData(Map<String, Object> map) {
        List<String[]> listData = new ArrayList<>();
        SqlMapDao sqlDao = (SqlMapDao) dao;
        sqlDao.setMaxQueryCount(5000);
        List<LinkedHashMap> list = sqlDao.query("SIKC03.exportData", map);
        if(CollectionUtils.isNotEmpty(list)) {
            List<Object[]> collect = list.stream().map(m -> m.values().toArray(new Object[m.size()])).collect(Collectors.toList());
            listData = JSON.parseArray(JSON.toJSONString(collect), String[].class);
        }

        return listData;
    }

    @Override
    public void parseRow(Row row, List<Object> dataList, List<String[]> errorList) {

    }

    @Override
    public String validate(Object object) {
        return null;
    }

    @Override
    public String saveData(List<Object> list) {
        return null;
    }
}
