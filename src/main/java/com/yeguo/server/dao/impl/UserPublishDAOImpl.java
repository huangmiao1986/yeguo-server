package com.yeguo.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yeguo.server.base.CustomerContextHolder;
import com.yeguo.server.base.JdbcTemplateWrapper;
import com.yeguo.server.dao.UserPublishDAO;
import com.yeguo.server.model.UserPublish;
@Repository
public class UserPublishDAOImpl implements UserPublishDAO{

    @Resource
    private JdbcTemplateWrapper jdbcTemplate;
    
    @Override
    public int addUserPublish(UserPublish publish) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "insert into tb_yeguo_user_publish (user_id,publish_desc,create_time,update_time) values (?,?,?,?)";
        int ret = jdbcTemplate.insertAndGetKey(sql, new Object[]{publish.getUserId(),publish.getPublishDesc(),publish.getCreateTime(),publish.getUpdateTime()});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }
    
    @Override
    public int adduserPublishImgs(String image, int publishId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "insert into tb_yeguo_user_publish_img (publish_img_url,publish_id,create_time,update_time) values (?,?,?,?)";
        int key = jdbcTemplate.insertAndGetKey(sql, new Object[]{image,publishId,new Date(),new Date()});
        CustomerContextHolder.clearCustomerType();
        return key;
    }
    
    @Override
    public int delUserPublishById(int id) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "delete from tb_yeguo_user_publish where id = ?";
        int ret = jdbcTemplate.update(sql, new Object[]{id});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }
    
    @Override
    public int delUserPublishImgByPublishId(int publishId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "delete from tb_yeguo_user_publish_img where publish_id = ?";
        int ret = jdbcTemplate.update(sql, new Object[]{publishId});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public UserPublish getPublishById(int id) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_publish where id = ?";
        UserPublish publish = jdbcTemplate.queryForObject(sql, new Object[]{id},new UserPublishMapper());
        if(publish != null) {
            String imgSql = "select publish_img_url from tb_yeguo_user_publish_img where publish_id=?";
            List<String> images = jdbcTemplate.queryForList(imgSql, new Object[]{id},String.class);
            publish.setImages(images);
        }
        CustomerContextHolder.clearCustomerType();
        return publish;
    }
    
    @Override
    public List<String> getPublishImagesById(int publishId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String imgSql = "select publish_img_url from tb_yeguo_user_publish_img where publish_id=?";
        List<String> images = jdbcTemplate.queryForList(imgSql, new Object[]{publishId},String.class);
        CustomerContextHolder.clearCustomerType();
        return images;
    }

    @Override
    public List<UserPublish> getUserPublishsByUserId(long userId, int start, int count) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_publish where user_id = ?  order by create_time desc limit ?,?";
        List<UserPublish> publishs = jdbcTemplate.query(sql, new Object[]{userId,start,count},new UserPublishMapper());
        CustomerContextHolder.clearCustomerType();
        return publishs;
    }

    @Override
    public int getUserPublishCountByUserId(long userId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select count(*) from tb_yeguo_user_publish where user_id = ?";
        int count = jdbcTemplate.queryForInt(sql, new Object[]{userId});
        CustomerContextHolder.clearCustomerType();
        return count;
    }
    
    private static final class UserPublishMapper implements RowMapper<UserPublish> {
        public UserPublish mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserPublish publish = new UserPublish();
            publish.setCreateTime(rs.getDate("create_time"));
            publish.setId(rs.getInt("id"));
            publish.setPraise(rs.getInt("praise"));
            publish.setPublishDesc(rs.getString("publish_desc"));
            publish.setUpdateTime(rs.getDate("update_time"));
            publish.setUserId(rs.getLong("user_id"));
            return publish;
        }
    }
    

}
