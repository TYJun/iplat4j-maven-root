package com.baosight.wilp.pr.ap.domain;

import java.io.Serializable;

/**
 * 
 * 整改后图片
 * 
 * @Title: ResultPic.java
 * @ClassName: ResultPic
 * @Package：com.baosight.zdyyaq.aq.ap.domain
 * @author: zhangjiaqian
 * @date: 2021年5月11日 上午10:28:42
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ResultPic implements Serializable{

    
    
    /**
     * @Fields serialVersionUID : 序列化
     */
    private static final long serialVersionUID = 8026227764754692199L;


    /**
     * id
     */
    private String id;
    
    
    /**
     * 整改表关联id
     */
    private String dangerResultID;
    
    
    /**
     * 整改后图片表id
     */
    private String fileId;
    
    
    /**
     * 图片名
     */
    private String fileName;
    
    
    /**
     * 图片路径
     */
    private String path;


    /**
     * 图片完整路径
     */
    private String storagePath;


    /**
     * base64
     */
    private String base64;



    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getDangerResultID() {
        return dangerResultID;
    }


    public void setDangerResultID(String dangerResultID) {
        this.dangerResultID = dangerResultID;
    }


    public String getFileId() {
        return fileId;
    }


    public void setFileId(String fileId) {
        this.fileId = fileId;
    }


    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public String getStoragePath() {
        return storagePath;
    }


    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }


    public String getBase64() {
        return base64;
    }


    public void setBase64(String base64) {
        this.base64 = base64;
    }


    @Override
    public String toString() {
        return "ResultPic{" +
                "id='" + id + '\'' +
                ", dangerResultID='" + dangerResultID + '\'' +
                ", fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", path='" + path + '\'' +
                ", storagePath='" + storagePath + '\'' +
                ", base64='" + base64 + '\'' +
                '}';
    }
}
