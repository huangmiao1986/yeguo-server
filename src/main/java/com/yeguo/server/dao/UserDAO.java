package com.yeguo.server.dao;

import com.yeguo.server.model.User;

public interface UserDAO {
    /**
     * 新增用户
     * 
     * @param user
     * @return
     */
    public long addUser(User user);
    /**
     * 更新用户
     * 
     * @param user
     * @return
     */
    public int updateUser(User user);
    /**
     * 根据用户Id获取用户信息
     * 
     * @param userId
     * @return
     */
    public User getUserById(long userId);
    /**
     * 更新用户昵称
     * 
     * @param userId
     * @return
     */
    public int updateUserNameByUserId(long userId,String userName);
    /**
     * 更新用户签名
     * 
     * @param userId
     * @return
     */
    public int updateUsersSignByUserId(long userId,String sign);
    /**
     * 根据手机号查询用户
     * 
     * @param phoneNum
     * @return
     */
    public User getUserByPhoneNum(String phoneNum);
}
