package com.yeguo.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yeguo.server.base.CustomerContextHolder;
import com.yeguo.server.base.JdbcTemplateWrapper;
import com.yeguo.server.dao.UserFollowDAO;
import com.yeguo.server.model.UserFollow;
@Repository
public class UserFollowDAOImpl implements UserFollowDAO {

    @Resource
    private JdbcTemplateWrapper jdbcTemplate;
    
    @Override
    public int addUserFollow(UserFollow follow) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "insert into tb_yeguo_user_follow (user_id,follow_user_id,create_time,update_time) values (?,?,?,?)";
        int ret = jdbcTemplate.insertAndGetKey(sql, new Object[]{follow.getUserId(),follow.getFollowUserId(),follow.getCreateTime(),follow.getUpdateTime()});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public int delUserFollow(int id) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "delete from tb_yeguo_user_follow where id = ?";
        int ret = jdbcTemplate.update(sql, new Object[]{id});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public UserFollow getUserFollowByUserIdAndFollowUserId(long userId, long followUserId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_follow where user_id = ? and follow_user_id=?";
        UserFollow follow = jdbcTemplate.queryForObject(sql, new Object[]{userId,followUserId},new UserFollowMapper());
        CustomerContextHolder.clearCustomerType();
        return follow;
    }

    @Override
    public List<UserFollow> getFollowListByUserId(long userId, int start, int count) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_follow where user_id = ? order by create_time desc limit ?,?";
        List<UserFollow> list = jdbcTemplate.query(sql, new Object[]{userId,start,count},new UserFollowMapper());
        CustomerContextHolder.clearCustomerType();
        return list;
    }

    @Override
    public int getFollowCountByUserId(long userId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select count(*) from tb_yeguo_user_follow where user_id = ?";
        int count = jdbcTemplate.queryForInt(sql, new Object[]{userId});
        CustomerContextHolder.clearCustomerType();
        return count;
    }
    
    private static final class UserFollowMapper implements RowMapper<UserFollow> {
        public UserFollow mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserFollow follow = new UserFollow();
            follow.setCreateTime(rs.getDate("create_time"));
            follow.setFollowUserId(rs.getLong("follow_user_id"));
            follow.setId(rs.getInt("id"));
            follow.setUpdateTime(rs.getDate("update_time"));
            follow.setUserId(rs.getLong("user_id"));
            return follow;
        }
    }

}
