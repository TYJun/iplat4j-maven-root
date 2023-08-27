package com.baosight.wilp.ci.kc.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * (CiStatistics)实体类
 *
 * @author liu
 * @since 2022-09-21 17:55:58
 */
public class CiStatistics extends DaoEPBase {

    private static final long serialVersionUID = 1L;


    /**
     * uuid
     */
    private String id = " ";

    /**
     * 创建时间
     */
    private String createTime = " ";

    /**
     * 月份
     */
    private String month = " ";

    /**
     * 仓库号
     */
    private String wareHouseNo = " ";

    /**
     * 物资编码
     */
    private String matCode = " ";

    /**
     * 期初数据
     */
    private String startBalance = " ";

    /**
     * 期末数据
     */
    private String endBalance = " ";

    /**
     * 入库数据
     */
    private String enterNum = " ";

    /**
     * 出库数据
     */
    private String outNum = " ";

    /**
     * 仓库
     */
    private String wareHouseName = " ";

    /**
     * 物资
     */
    private String matName = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("uuid");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("month");
        eiColumn.setDescName("月份");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("wareHouseNo");
        eiColumn.setDescName("仓库号");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("matCode");
        eiColumn.setDescName("物资编码");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("startBalance");
        eiColumn.setDescName("期初数据");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("endBalance");
        eiColumn.setDescName("期末数据");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("enterNum");
        eiColumn.setDescName("入库数据");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("outNum");
        eiColumn.setDescName("出库数据");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("wareHouseName");
        eiColumn.setDescName("仓库");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("matName");
        eiColumn.setDescName("物资");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public CiStatistics() {
        initMetaData();
    }

    /**
     * get the id - uuid
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * set the id - uuid
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the createTime - 创建时间
     * @return the createTime
     */
    public String getCreateTime() {
        return createTime;
    }
    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the month - 月份
     * @return the month
     */
    public String getMonth() {
        return month;
    }
    /**
     * set the month - 月份
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * get the wareHouseNo - 仓库号
     * @return the wareHouseNo
     */
    public String getWareHouseNo() {
        return wareHouseNo;
    }
    /**
     * set the wareHouseNo - 仓库号
     */
    public void setWareHouseNo(String wareHouseNo) {
        this.wareHouseNo = wareHouseNo;
    }

    /**
     * get the matCode - 物资编码
     * @return the matCode
     */
    public String getMatCode() {
        return matCode;
    }
    /**
     * set the matCode - 物资编码
     */
    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    /**
     * get the startBalance - 期初数据
     * @return the startBalance
     */
    public String getStartBalance() {
        return startBalance;
    }
    /**
     * set the startBalance - 期初数据
     */
    public void setStartBalance(String startBalance) {
        this.startBalance = startBalance;
    }

    /**
     * get the endBalance - 期末数据
     * @return the endBalance
     */
    public String getEndBalance() {
        return endBalance;
    }
    /**
     * set the endBalance - 期末数据
     */
    public void setEndBalance(String endBalance) {
        this.endBalance = endBalance;
    }

    /**
     * get the enterNum - 入库数据
     * @return the enterNum
     */
    public String getEnterNum() {
        return enterNum;
    }
    /**
     * set the enterNum - 入库数据
     */
    public void setEnterNum(String enterNum) {
        this.enterNum = enterNum;
    }

    /**
     * get the outNum - 出库数据
     * @return the outNum
     */
    public String getOutNum() {
        return outNum;
    }
    /**
     * set the outNum - 出库数据
     */
    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    /**
     * get the wareHouseName - 仓库
     * @return the wareHouseName
     */
    public String getWareHouseName() {
        return wareHouseName;
    }
    /**
     * set the wareHouseName - 仓库
     */
    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    /**
     * get the matName - 物资
     * @return the matName
     */
    public String getMatName() {
        return matName;
    }
    /**
     * set the matName - 物资
     */
    public void setMatName(String matName) {
        this.matName = matName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
        setMonth(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("month")), month));
        setWareHouseNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseNo")), wareHouseNo));
        setMatCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matCode")), matCode));
        setStartBalance(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("startBalance")), startBalance));
        setEndBalance(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("endBalance")), endBalance));
        setEnterNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterNum")), enterNum));
        setOutNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outNum")), outNum));
        setWareHouseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseName")), wareHouseName));
        setMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("month",StringUtils.toString(month, eiMetadata.getMeta("month")));
        map.put("wareHouseNo",StringUtils.toString(wareHouseNo, eiMetadata.getMeta("wareHouseNo")));
        map.put("matCode",StringUtils.toString(matCode, eiMetadata.getMeta("matCode")));
        map.put("startBalance",StringUtils.toString(startBalance, eiMetadata.getMeta("startBalance")));
        map.put("endBalance",StringUtils.toString(endBalance, eiMetadata.getMeta("endBalance")));
        map.put("enterNum",StringUtils.toString(enterNum, eiMetadata.getMeta("enterNum")));
        map.put("outNum",StringUtils.toString(outNum, eiMetadata.getMeta("outNum")));
        map.put("wareHouseName",StringUtils.toString(wareHouseName, eiMetadata.getMeta("wareHouseName")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        return map;
    }

}

