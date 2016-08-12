package com.yeguo.server.dao;

import java.util.List;

import com.yeguo.server.model.UserFans;

public interface UserFansDAO {
    /**
     * 新增用户粉丝
     * 
     * @param fans
     * @return
     */
    public int addUserFans(UserFans fans);
    /**
     * 删除
     * 
     * @param id
     * @return
     */
    public int delUserFansById(int id);
    /**
     * 根据用户id以及粉丝用户id获取信息（取消关注时保护）
     * 
     * @param userId
     * @param fansUserId
     * @return
     */
    public UserFans getUserFansByUserIdAndFnasUserId(long userId,long fansUserId);
    /**
     * 查询用户粉丝列表
     * 
     * @param userId
     * @param start
     * @param count
     * @return
     */
    public List<UserFans> getFansListByUserId(long userId,int start,int count);
    /**
     * 获取用户粉丝总数
     * 
     * @param userId
     * @return
     */
    public int getFansCountByUserid(long userId);
}
