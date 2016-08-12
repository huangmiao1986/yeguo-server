package com.yeguo.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yeguo.server.base.CustomerContextHolder;
import com.yeguo.server.base.JdbcTemplateWrapper;
import com.yeguo.server.dao.UserCommentDAO;
import com.yeguo.server.model.UserComment;
@Repository
public class UserCommentDAOImpl implements UserCommentDAO {
    
    @Resource
    private JdbcTemplateWrapper jdbcTemplate;

    @Override
    public int addUserComment(UserComment comment) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "insert into tb_yeguo_user_comment (publish_id,comment_user_id,comment,create_time,update_time) values (?,?,?,?,?)";
        int ret = jdbcTemplate.insertAndGetKey(sql, new Object[]{comment.getPublishId(),comment.getCommentUserId(),comment.getComment(),comment.getCreateTime(),comment.getUpdateTime()});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public int delCommentById(int id) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "delete from tb_yeguo_user_comment where id = ?";
        int ret = jdbcTemplate.update(sql, new Object[]{id});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public int getCommentCountByPublishId(int publishId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select count(*) from tb_yeguo_user_comment where publish_id = ?";
        int count = jdbcTemplate.queryForInt(sql, new Object[]{publishId});
        CustomerContextHolder.clearCustomerType();
        return count;
    }

    @Override
    public List<UserComment> getCommentsByPublishId(int publishId, int start, int count) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_comment where publish_id = ?  order by create_time desc limit ?,?";
        List<UserComment> commnets = jdbcTemplate.query(sql, new Object[]{publishId,start,count},new UserCommentMapper());
        CustomerContextHolder.clearCustomerType();
        return commnets;
    }

    @Override
    public UserComment getCommentById(int id) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_comment where  id= ?";
        UserComment comment = jdbcTemplate.queryForObject(sql, new Object[]{id},new UserCommentMapper());
        CustomerContextHolder.clearCustomerType();
        return comment;
    }
    
    private static final class UserCommentMapper implements RowMapper<UserComment> {
        public UserComment mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserComment comment = new UserComment();
            comment.setComment(rs.getString("comment"));
            comment.setCommentUserId(rs.getLong("comment_user_id"));
            String createTime = rs.getString("create_time");
            if(StringUtils.isNotBlank(createTime)) {
                comment.setCreateTime(createTime.substring(0, createTime.indexOf(".")));
            }
            comment.setId(rs.getInt("id"));
            comment.setPublishId(rs.getInt("publish_id"));
            comment.setUpdateTime(rs.getDate("update_time"));
            return comment;
        }
    }

}
