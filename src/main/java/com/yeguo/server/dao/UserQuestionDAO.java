package com.yeguo.server.dao;

import java.util.List;

import com.yeguo.server.model.UserQuestion;

public interface UserQuestionDAO {
    /**
     * 新增用户问题
     * 
     * @param question
     * @return
     */
    public int addUserQuestion(UserQuestion question);
    /**
     * 删除
     * 
     * @param id
     * @return
     */
    public int delUserQuestionById(int id);
    /**
     * 根据id获取问题
     * 
     * @param id
     * @return
     */
    public UserQuestion getQuestionById(int id);
    /**
     * 获取用户问题列表
     * 
     * @param userId
     * @return
     */
    public List<UserQuestion> getQuestionListByUserId(long userId);
}
