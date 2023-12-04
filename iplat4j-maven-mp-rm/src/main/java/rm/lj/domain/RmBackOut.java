package rm.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 物资退库申请实体
 * RmBackOut
 * @author fangjian
 */
public class RmBackOut extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id;

    /**退库申请单号*/
    private String backOutNo;

    /**状态编码*/
    private String statusCode;

    /**状态名称*/
    private String statusName;

    /**退库数量*/
    private Double backOutNum = new Double(0.00);

    /**已退库数量*/
    private Double outNum = new Double(0.00);

    /**申请科室编码*/
    private String deptNum;

    /**申请科室编码*/
    private String deptName;

    /** 成本归集科室编码*/
    private String costDeptNum;

    /** 成本归集科室名称*/
    private String costDeptName;

    /**退库原因*/
    private String backReason="";

    /**创建（申请）人*/
    private String recCreator;

    /**创建（申请）人姓名*/
    private String recCreatorName;

    /**创建时间*/
    private Date recCreateTime ;

    /**创建时间*/
    private String recCreateTimeStr ;

    /**修改人*/
    private String recRevisor;

    /**修改时间*/
    private Date recReviseTime ;

    /**账套*/
    private String dataGroupCode;

    /**归档标记*/
    private String archiveFlag;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("backOutNo");
        eiColumn.setDescName("退库单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("backOutNum");
        eiColumn.setDescName("退库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outNum");
        eiColumn.setDescName("已退库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("申请科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("申请科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("costDeptNum");
        eiColumn.setDescName("成本归集科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("costDeptName");
        eiColumn.setDescName("成本归集科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("backReason");
        eiColumn.setDescName("退库原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建（申请）人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorName");
        eiColumn.setDescName("创建（申请）人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTimeStr");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public RmBackOut() {
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
     * get the backOutNo - 领用单号
     * @return the backOutNo
     */
    public String getBackOutNo() {
        return this.backOutNo;
    }

    /**
     * set the backOutNo - 领用单号
     */
    public void setBackOutNo(String backOutNo) {
        this.backOutNo = backOutNo;
    }

    /**
     * get the statusCode - 状态编码
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态编码
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the statusName - 状态名称
     * @return the statusName
     */
    public String getStatusName() {
        return this.statusName;
    }

    /**
     * set the statusName - 状态名称
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * get the backOutNum - 退库数量
     * @return the backOutNum
     */
    public Double getBackOutNum() {
        return this.backOutNum;
    }

    /**
     * set the backOutNum - 退库数量
     */
    public void setBackOutNum(Double backOutNum) {
        this.backOutNum = backOutNum;
    }

    /**
     * get the outNum - 已退库数量
     * @return the outNum
     */
    public Double getOutNum() {
        return this.outNum;
    }

    /**
     * set the outNum - 已退库数量
     */
    public void setOutNum(Double outNum) {
        this.outNum = outNum;
    }

    /**
     * get the deptNum - 申请科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 申请科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 申请科室编码
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 申请科室编码
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCostDeptNum() {
        return costDeptNum;
    }

    public void setCostDeptNum(String costDeptNum) {
        this.costDeptNum = costDeptNum;
    }

    public String getCostDeptName() {
        return costDeptName;
    }

    public void setCostDeptName(String costDeptName) {
        this.costDeptName = costDeptName;
    }

    /**
     * get the backReason - 退库原因
     * @return the backReason
     */
    public String getBackReason() {
        return backReason;
    }

    /**
     * set the backReason - 退库原因
     */
    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    /**
     * get the recCreator - 创建（申请）人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建（申请）人
     */
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreatorName - 创建（申请）人姓名
     * @return the recCreatorName
     */
    public String getRecCreatorName() {
        return this.recCreatorName;
    }

    /**
     * set the recCreatorName - 创建（申请）人姓名
     */
    public void setRecCreatorName(String recCreatorName) {
        this.recCreatorName = recCreatorName;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public Date getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
        if(this.recCreateTime != null) {
            setRecCreateTimeStr(DateUtils.toDateTimeStr19(this.recCreateTime));
        }
    }

    /**
     * get the recCreateTimeStr - 创建时间
     * @return the recCreateTimeStr
     */
    public String getRecCreateTimeStr() {
        return this.recCreateTimeStr;
    }

    /**
     * set the recCreateTimeStr - 创建时间
     */
    public void setRecCreateTimeStr(String recCreateTimeStr) {
        this.recCreateTimeStr = recCreateTimeStr;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public Date getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(Date recReviseTime) {
        this.recReviseTime = recReviseTime;
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

    /**
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(RmUtils.toString(map.get("id"), id));
        setBackOutNo(RmUtils.toString(map.get("backOutNo"), backOutNo));
        setStatusCode(RmUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(RmUtils.toString(map.get("statusName"), statusName));
        setBackOutNum(NumberUtils.toDouble(StringUtils.toString(map.get("backOutNum")), backOutNum));
        setOutNum(NumberUtils.toDouble(StringUtils.toString(map.get("outNum")), outNum));
        setDeptNum(RmUtils.toString(map.get("deptNum"), deptNum));
        setCostDeptNum(RmUtils.toString(map.get("costDeptNum"), costDeptNum));
        setCostDeptName(RmUtils.toString(map.get("costDeptName"), costDeptName));
        setDeptName(RmUtils.toString(map.get("deptName"), deptName));
        setBackReason(RmUtils.toString(map.get("backReason"), backReason));
        setRecCreator(RmUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(RmUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(RmUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(RmUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(RmUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(RmUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(RmUtils.toString(map.get("archiveFlag"), archiveFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(32);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("backOutNo",StringUtils.toString(backOutNo, eiMetadata.getMeta("backOutNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("backOutNum",StringUtils.toString(backOutNum, eiMetadata.getMeta("backOutNum")));
        map.put("outNum",StringUtils.toString(outNum, eiMetadata.getMeta("outNum")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("costDeptNum",StringUtils.toString(costDeptNum, eiMetadata.getMeta("costDeptNum")));
        map.put("costDeptName",StringUtils.toString(costDeptName, eiMetadata.getMeta("costDeptName")));
        map.put("backReason",StringUtils.toString(backReason, eiMetadata.getMeta("backReason")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreateTimeStr",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }

    /**
     * 构建退库对象
     * @Title: getStatusInstance
     * @param id id : 主键
     * @param statusCode statusCode : 状态编码
     * @return com.baosight.wilp.rm.lj.domain.RmBackOut
     * @throws
     **/
    public static RmBackOut getStatusInstance(String id, String statusCode) {
        RmBackOut back = new RmBackOut();
        back.setId(id);
        back.setStatusCode(statusCode);
        back.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.backOut.status", statusCode));
        back.setRecRevisor(UserSession.getLoginName());
        back.setRecReviseTime(new Date());
        return back;
    }
}
