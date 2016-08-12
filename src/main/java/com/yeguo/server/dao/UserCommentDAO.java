package com.yeguo.server.dao;

import java.util.List;

import com.yeguo.server.model.UserComment;

public interface UserCommentDAO {
    /**
     * 新增评论
     * 
     * @param comment
     * @return
     */
    public int addUserComment(UserComment comment);
    /**
     * 删除评论
     * 
     * @param id
     * @return
     */
    public int delCommentById(int id);
    /**
     * 根据发布Id获取评论总数
     * 
     * @param publishId
     * @return
     */
    public int getCommentCountByPublishId(int publishId);
    /**
     * 根据发布Id获取评论列表
     * 
     * @param publishId
     * @param start
     * @param count
     * @return
     */
    public List<UserComment> getCommentsByPublishId(int publishId,int start,int count);
    /**
     * 根据Id获取评论信息
     * 
     * @param id
     * @return
     */
    public UserComment getCommentById(int id);
}
