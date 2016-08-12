package com.yeguo.server.model;

import java.util.Date;
import java.util.List;

public class UserPublish {
    private int id;
    private long userId;
    private String publishDesc;
    private int praise;
    private List<String> images;
    private Date createTime;
    private Date updateTime;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getPublishDesc() {
        return publishDesc;
    }
    public void setPublishDesc(String publishDesc) {
        this.publishDesc = publishDesc;
    }
    public int getPraise() {
        return praise;
    }
    public void setPraise(int praise) {
        this.praise = praise;
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
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }
    
}
