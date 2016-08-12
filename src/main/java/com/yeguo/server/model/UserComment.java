package com.yeguo.server.model;

import java.util.Date;

public class UserComment {
    private int id;
    private int publishId;
    private long commentUserId;
    private String comment;
    private String createTime;
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
    public long getCommentUserId() {
        return commentUserId;
    }
    public void setCommentUserId(long commentUserId) {
        this.commentUserId = commentUserId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}
