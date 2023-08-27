/**
* Generate time : 2021-05-28 14:37:40
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTmealOperation 订餐操作记录实体类
* 
*/
public class PSPCTmealOperation extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String operationRoute = " ";		/* 操作节点，01-创建时间，02-支付时间，03-确认时间，04-送餐时间，05-评价时间，06-退款发起时间，07-退款确认时间*/
    private String operationTime = " ";		/* 操作时间*/
    private String creatorId = " ";		/* 创建人工号*/
    private String creatorName = " ";		/* 创建人名称*/
    private String billNo = " ";		/* 订单主表bill_no*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationRoute");
        eiColumn.setDescName("操作节点，01-创建时间，02-支付时间，03-确认时间，04-送餐时间，05-评价时间，06-退款发起时间，07-退款确认时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationTime");
        eiColumn.setDescName("操作时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorId");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorName");
        eiColumn.setDescName("创建人名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("订单主表bill_no");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTmealOperation() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the operationRoute - 操作节点，01-创建时间，02-支付时间，03-确认时间，04-送餐时间，05-评价时间，06-退款发起时间，07-退款确认时间
     * @return the operationRoute
     */
    public String getOperationRoute() {
        return this.operationRoute;
    }

    /**
     * set the operationRoute - 操作节点，01-创建时间，02-支付时间，03-确认时间，04-送餐时间，05-评价时间，06-退款发起时间，07-退款确认时间
     */
    public void setOperationRoute(String operationRoute) {
        this.operationRoute = operationRoute;
    }

    /**
     * get the operationTime - 操作时间
     * @return the operationTime
     */
    public String getOperationTime() {
        return this.operationTime;
    }

    /**
     * set the operationTime - 操作时间
     */
    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * get the creatorId - 创建人工号
     * @return the creatorId
     */
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * set the creatorId - 创建人工号
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * get the creatorName - 创建人名称
     * @return the creatorName
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * set the creatorName - 创建人名称
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * get the billNo - 订单主表bill_no
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 订单主表bill_no
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setOperationRoute(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationRoute")), operationRoute));
        setOperationTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationTime")), operationTime));
        setCreatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorId")), creatorId));
        setCreatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorName")), creatorName));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("operationRoute",StringUtils.toString(operationRoute, eiMetadata.getMeta("operationRoute")));
        map.put("operationTime",StringUtils.toString(operationTime, eiMetadata.getMeta("operationTime")));
        map.put("creatorId",StringUtils.toString(creatorId, eiMetadata.getMeta("creatorId")));
        map.put("creatorName",StringUtils.toString(creatorName, eiMetadata.getMeta("creatorName")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        return map;
    }
}