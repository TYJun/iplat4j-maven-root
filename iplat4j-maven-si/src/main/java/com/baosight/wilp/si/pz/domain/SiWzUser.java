package com.baosight.wilp.si.pz.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.si.common.SiUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: TODO
 * @ClassName: SiWzUser
 * @Package com.baosight.wilp.si.pz.domain
 * @date: 2023年07月14日 14:27
 */
public class SiWzUser extends DaoEPBase {

    /**主键**/
    private String id;

    /**工号**/
    @NotBlank(message = "工号不能为空")
    private String workNo;

    /**姓名**/
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**科室编码**/
    @NotBlank(message = "业务科室编码不能为空")
    private String deptCode;

    /**科室名称**/
    @NotBlank(message = "业务科室名称不能为空")
    private String deptName;

    /**frame数据库名称**/
    private String frameSchema;

    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptCode");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);
    }

    public SiWzUser() { initMetaData();}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getFrameSchema() {
        return frameSchema;
    }

    public void setFrameSchema(String frameSchema) {
        this.frameSchema = frameSchema;
    }

    @Override
    public void fromMap(Map map) {
        setId(SiUtils.isEmpty(map.get("id"), id));
        setWorkNo(SiUtils.isEmpty(map.get("workNo"), workNo));
        setName(SiUtils.isEmpty(map.get("name"), name));
        setDeptCode(SiUtils.isEmpty(map.get("deptCode"), deptCode));
        setDeptName(SiUtils.isEmpty(map.get("deptName"), deptName));
    }

    @Override
    public Map toMap() {
        Map map = new HashMap(8);
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("workNo", StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("name", StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("deptCode", StringUtils.toString(deptCode, eiMetadata.getMeta("deptCode")));
        map.put("deptName", StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));

        return map;
    }
}
