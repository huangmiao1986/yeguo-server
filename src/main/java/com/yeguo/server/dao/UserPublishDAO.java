package com.yeguo.server.dao;

import java.util.List;

import com.yeguo.server.model.UserPublish;

public interface UserPublishDAO {
    /**
     * 新增发布
     * 
     * @param publish
     * @return
     */
    public int addUserPublish(UserPublish publish);
    /**
     * 增加发布图片
     * 
     * @param images
     * @param publishId
     */
    public int adduserPublishImgs(String image,int publishId);
    /**
     * 删除发布
     * 
     * @param id
     * @return
     */
    public int delUserPublishById(int id);
    /**
     * 删除图片
     * 
     * @param publishId
     * @return
     */
    public int delUserPublishImgByPublishId(int publishId);
    /**
     * 根据发布id发布的信息（保护删除操作）
     * 
     * @param id
     * @return
     */
    public UserPublish getPublishById(int id);
    /**
     * 根据发布id发布的图片（保护删除操作）
     * 
     * @param id
     * @return
     */
    public List<String> getPublishImagesById(int publishId);
    /**
     * 根据用户Id获取发布信息
     * 
     * @param userId
     * @return
     */
    public List<UserPublish> getUserPublishsByUserId(long userId,int start,int count);
    /**
     * 获取用户发布总数
     * 
     * @param userId
     * @return
     */
    public int getUserPublishCountByUserId(long userId);
}
