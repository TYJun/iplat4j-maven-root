package rm.pz.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.rm.common.RmUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashMap;
import java.util.Map;

/**
 * 领用配置实体
 * RmConfig
 * @author fangjian
 */
public class RmConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id;

    /** 配置项编码*/
    @NotBlank(message = "配置项编码不能为空")
    private String configCode;

    /** 配置项名称*/
    @NotBlank(message = "配置项名称不能为空")
    private String configName;

    /** 配置项值（单选框的值）*/
    private String configValueRadio;

    /** 配置项值（多选框、输入框的值）*/
    private String configValueText;

    /** 账套*/
    private String dataGroupCode;

    /** 排序*/
    private Integer orderNo;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configCode");
        eiColumn.setDescName("配置项编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configName");
        eiColumn.setDescName("配置项名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configValueRadio");
        eiColumn.setDescName("配置项值（单选框的值）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configValueText");
        eiColumn.setDescName("配置项值（多选框、输入框的值）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderNo");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public RmConfig() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the configCode - 配置项编码
     * @return the configCode
     */
    public String getConfigCode() {
        return this.configCode;
    }

    /**
     * set the configCode - 配置项编码
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    /**
     * get the configName - 配置项名称
     * @return the configName
     */
    public String getConfigName() {
        return this.configName;
    }

    /**
     * set the configName - 配置项名称
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * get the configValueRedio - 配置项值（单选框的值）
     * @return the configValueRedio
     */
    public String getConfigValueRadio() {
        return this.configValueRadio;
    }

    /**
     * set the configValueRedio - 配置项值（单选框的值）
     */
    public void setConfigValueRadio(String configValueRadio) {
        this.configValueRadio = configValueRadio;
    }

    /**
     * get the configValueText - 配置项值（多选框、输入框的值）
     * @return the configValueText
     */
    public String getConfigValueText() {
        return this.configValueText;
    }

    /**
     * set the configValueText - 配置项值（多选框、输入框的值）
     */
    public void setConfigValueText(String configValueText) {
        this.configValueText = configValueText;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(RmUtils.toString(map.get("id"), id));
        setConfigCode(RmUtils.toString(map.get("configCode"), configCode));
        setConfigName(RmUtils.toString(map.get("configName"), configName));
        setConfigValueRadio(RmUtils.toString(map.get("configValueRadio"), configValueRadio));
        setConfigValueText(RmUtils.toString(map.get("configValueText"), configValueText));
        setDataGroupCode(RmUtils.toString(map.get("dataGroupCode"), dataGroupCode));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(8);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("configCode",StringUtils.toString(configCode, eiMetadata.getMeta("configCode")));
        map.put("configName",StringUtils.toString(configName, eiMetadata.getMeta("configName")));
        map.put("configValueRadio",StringUtils.toString(configValueRadio, eiMetadata.getMeta("configValueRadio")));
        map.put("configValueText",StringUtils.toString(configValueText, eiMetadata.getMeta("configValueText")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("orderNo",StringUtils.toString(orderNo, eiMetadata.getMeta("orderNo")));
        return map;
    }
}
