package com.yeguo.server.dao;

import java.util.List;

import com.yeguo.server.model.UserFollow;

public interface UserFollowDAO {
    /**
     * 新增用户关注
     * 
     * @param follow
     * @return
     */
    public int addUserFollow(UserFollow follow);
    /**
     * 删除
     * 
     * @param id
     * @return
     */
    public int delUserFollow(int id);
    /**
     * 根据用户Id以及被关注人Id获取关注信息
     * 
     * @param userId
     * @param followUserId
     * @return
     */
    public UserFollow getUserFollowByUserIdAndFollowUserId(long userId,long followUserId);
    /**
     * 获取用户关注列表
     * 
     * @param userId
     * @param start
     * @param count
     * @return
     */
    public List<UserFollow> getFollowListByUserId(long userId,int start,int count);
    /**
     * 获取用户关注总数
     * 
     * @param userId
     * @return
     */
    public int getFollowCountByUserId(long userId);
}
