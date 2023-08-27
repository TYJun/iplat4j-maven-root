package com.baosight.wilp.ss.wx.domain;

public class WWExtAttrItem {
    // 扩展属性类型: 0-文本 1-网页
    private String Type;
    // 文本属性类型，扩展属性类型为0时填写
    private String Text;
    // 文本属性内容
    private String Value;
    // 网页类型属性，扩展属性类型为1时填写
    private String Web;
    // 网页的展示标题
    private String Title;
    // 网页的url
    private String Url;
}
