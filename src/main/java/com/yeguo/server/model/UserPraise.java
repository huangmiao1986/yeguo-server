package com.yeguo.server.model;

import java.util.Date;

public class UserPraise {
    private int id;
    private int publishId;
    private long userId;
    private long praiseUserId;
    private int count;
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
    public long getPraiseUserId() {
        return praiseUserId;
    }
    public void setPraiseUserId(long praiseUserId) {
        this.praiseUserId = praiseUserId;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
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
    public int getPublishId() {
        return publishId;
    }
    public void setPublishId(int publishId) {
        this.publishId = publishId;
    }
    
}
