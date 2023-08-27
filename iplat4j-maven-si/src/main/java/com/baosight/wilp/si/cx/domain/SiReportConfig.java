package com.baosight.wilp.si.cx.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.si.common.SiUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description:  库存报表配置实体
 * @ClassName: SiReportConfig
 * @Package com.baosight.wilp.si.cx.service.domain
 * @date: 2022年12月26日 9:07
 */
public class SiReportConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键**/
    private Integer id;

    /**报表名称**/
    private String reportName;

    /**报表路劲**/
    private String reportUrl;

    /**账套*/
    private String dataGroupCode ;

    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reportName");
        eiColumn.setDescName("报表名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reportUrl");
        eiColumn.setDescName("报表路劲");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);
    }

    public SiReportConfig() { initMetaData();}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getDataGroupCode() {
        return dataGroupCode;
    }

    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    @Override
    public void fromMap(Map map) {
        setId(NumberUtils.toInteger(map.get("id"), id));
        setReportName(SiUtils.isEmpty(map.get("reportName"), reportName));
        setReportUrl(SiUtils.isEmpty(map.get("reportUrl"), reportUrl));
        setDataGroupCode(SiUtils.isEmpty(map.get("dataGroupCode"), dataGroupCode));
    }

    @Override
    public Map toMap() {
        Map map = new HashMap(8);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("reportName",StringUtils.toString(reportName, eiMetadata.getMeta("reportName")));
        map.put("reportUrl",StringUtils.toString(reportUrl, eiMetadata.getMeta("reportUrl")));
        map.put("dataGroupCode",StringUtils.toString(reportName, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}
