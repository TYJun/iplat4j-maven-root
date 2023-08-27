package com.baosight.wilp.entity;

import java.util.List;

/**
 * @PackageName com.baosight.wilp.entity
 * @ClassName SignDataResponse
 * @Description 数据电子签名数据返回值对象
 * @Author chunchen2
 * @Date 2023/2/27 16:28
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/27 16:28
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class SignDataResponse {

    private String status;

    private String message;

    private List<SignDataItem> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SignDataItem> getData() {
        return data;
    }

    public void setData(List<SignDataItem> data) {
        this.data = data;
    }
}
