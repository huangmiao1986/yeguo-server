package com.yeguo.server.model;

import java.util.Date;

public class UserPublishImg {
    private int id;
    private int publishId;
    private String publishImgUrl;
    private Date createTime;
    private Date updateTime;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPublishId() {
        return publishId;
    }
    public void setPublishId(int publishId) {
        this.publishId = publishId;
    }
    public String getPublishImgUrl() {
        return publishImgUrl;
    }
    public void setPublishImgUrl(String publishImgUrl) {
        this.publishImgUrl = publishImgUrl;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    
}
