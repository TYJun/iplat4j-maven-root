package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WWParty extends WWContactEvent {
    // 部门Id
    @JacksonXmlProperty(localName = "Id")
    private Integer Id;
    // 部门名称
    // 此字段从2019年12月30日起，对新创建第三方应用不再返回
    // 2020年6月30日起，对所有历史第三方应用不再返回真实Name字段，使用Id字段代替Name字段
    // 后续第三方仅通讯录应用可获取，第三方页面需要通过通讯录展示组件来展示名字。
    // 回收后普通第三方应用name变更不再回调
    @JacksonXmlProperty(localName = "Name")
    private String Name;
    // 父部门id
    @JacksonXmlProperty(localName = "ParentId")
    private Integer ParentId;
    // 部门排序
    @JacksonXmlProperty(localName = "Order")
    private Integer Order;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getParentId() {
        return ParentId;
    }

    public void setParentId(Integer parentId) {
        ParentId = parentId;
    }

    public Integer getOrder() {
        return Order;
    }

    public void setOrder(Integer order) {
        Order = order;
    }
}
