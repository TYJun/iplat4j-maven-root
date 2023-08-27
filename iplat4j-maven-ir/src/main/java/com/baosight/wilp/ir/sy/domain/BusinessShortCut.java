package com.baosight.wilp.ir.sy.domain;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @PackageName com.baosight.wilp.sm.dl.domain
 * @ClassName BusinessShortCut
 * @Description 业务快捷方式
 * @Author chunchen2
 * @Date 2021/11/18 17:58
 * @Version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2021/11/18 17:58
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class BusinessShortCut implements Serializable {

    private String id;

    /**
     * 工号
     **/
    private String workNo;

    /**
     * 节点英文名
     **/
    private String nodeEname;

    /**
     * 节点中文名
     **/
    private String nodeCname;

    /**
     * 节点点击次数
     **/
    private String clickNum;

    /**
     *  最新的更新日期
     **/
    private String updateTime;

    /**
     * 是否有效
     **/
    private String flag;

    /**
     * url
     **/
    private String url;

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

    public String getNodeEname() {
        return nodeEname;
    }

    public void setNodeEname(String nodeEname) {
        this.nodeEname = nodeEname;
    }

    public String getNodeCname() {
        return nodeCname;
    }

    public void setNodeCname(String nodeCname) {
        this.nodeCname = nodeCname;
    }

    public String getClickNum() {
        return clickNum;
    }

    public void setClickNum(String clickNum) {
        this.clickNum = clickNum;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
