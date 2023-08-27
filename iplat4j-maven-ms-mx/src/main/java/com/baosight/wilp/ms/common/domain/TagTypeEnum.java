package com.baosight.wilp.ms.common.domain;

/**
 * @description: tag类型的枚举对象
 * @author: panlingfeng
 * @createDate: 2021/7/24 3:30 下午
 */
public enum TagTypeEnum {

    TX("通讯量","0"), KG("开关量", "1"), MJ("枚举量", "2");

    private String name;
    private String  value;

    private TagTypeEnum(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
