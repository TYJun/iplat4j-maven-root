package com.baosight.wilp.ms.pa.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wzy
 * @title: MSPA01
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @date 2021/8/217:44
 */
public class MSPA01 extends DaoEPBase {
    private static final long serialVersionUID = 1L;

    private String id = " ";                     //uuid、非空
    private String staffid = " ";                  //员工工号
    private String name_ = " ";                  //姓名
    private String tel = " ";                  //移动电话
    private String email = " ";                //电子邮件
    private String grade_filter = " ";    //报警等级过滤
    private String t_param_classify_id = " ";             //所属参数分类主键
    private String group_id = " ";             //院区标识
    private String create_by = "";                //创建人
    private String create_date = "";                   //创建时间

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("staffid");
        eiColumn.setDescName("员工工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name_");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tel");
        eiColumn.setDescName("移动电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("email");
        eiColumn.setDescName("电子邮件");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("grade_filter");
        eiColumn.setDescName("报警等级过滤");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("t_param_classify_id");
        eiColumn.setDescName("所属参数分类主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("group_id");
        eiColumn.setDescName("院区标识");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("create_by");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("create_date");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public MSPA01() {
        initMetaData();
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade_filter() {
        return grade_filter;
    }

    public void setGrade_filter(String grade_filter) {
        this.grade_filter = grade_filter;
    }

    public String getT_param_classify_id() {
        return t_param_classify_id;
    }

    public void setT_param_classify_id(String t_param_classify_id) {
        this.t_param_classify_id = t_param_classify_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setStaffid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("staffid")), staffid));
        setName_(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name_")), name_));
        setTel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tel")), tel));
        setEmail(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("email")), email));
        setGrade_filter(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("grade_filter")), grade_filter));
        setT_param_classify_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("t_param_classify_id")), t_param_classify_id));
        setGroup_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("group_id")), group_id));
        setCreate_by(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("create_by")), create_by));
        setCreate_date(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("create_date")), create_date));
    }

    /**
     * 把所有字段全部放进map  然后页面映射
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("staffid", StringUtils.toString(staffid, eiMetadata.getMeta("staffid")));
        map.put("name_", StringUtils.toString(name_, eiMetadata.getMeta("name_")));
        map.put("tel", StringUtils.toString(tel, eiMetadata.getMeta("tel")));
        map.put("email", StringUtils.toString(email, eiMetadata.getMeta("email")));
        map.put("grade_filter", StringUtils.toString(grade_filter, eiMetadata.getMeta("grade_filter")));
        map.put("t_param_classify_id", StringUtils.toString(t_param_classify_id, eiMetadata.getMeta("t_param_classify_id")));
        map.put("group_id", StringUtils.toString(group_id, eiMetadata.getMeta("group_id")));
        map.put("create_by", StringUtils.toString(create_by, eiMetadata.getMeta("create_by")));
        map.put("create_date", StringUtils.toString(create_date, eiMetadata.getMeta("create_date")));
        return map;
    }
}
