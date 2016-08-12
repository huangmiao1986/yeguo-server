package com.yeguo.server.dao;

import com.yeguo.server.model.UserPraise;

public interface UserPraiseDAO {
    /**
     * 新增点赞
     * 
     * @param praise
     * @return
     */
    public int addUserPraise(UserPraise praise);
    /**
     * 用户指定日期内对指定发布点赞个数
     * 
     * @param userId
     * @param publishId
     * @param date
     * @return
     */
    public int getPraiseByUserIdAndPublishIdAndDate(long userId,int publishId,String date);
}
