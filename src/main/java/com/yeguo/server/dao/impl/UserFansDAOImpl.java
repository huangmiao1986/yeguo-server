package com.yeguo.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yeguo.server.base.CustomerContextHolder;
import com.yeguo.server.base.JdbcTemplateWrapper;
import com.yeguo.server.dao.UserFansDAO;
import com.yeguo.server.model.UserFans;
@Repository
public class UserFansDAOImpl implements UserFansDAO {
    
    @Resource
    private JdbcTemplateWrapper jdbcTemplate;

    @Override
    public int addUserFans(UserFans fans) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "insert into tb_yeguo_user_fans (user_id,fans_user_id,create_time,update_time) values (?,?,?,?)";
        int ret = jdbcTemplate.insertAndGetKey(sql, new Object[]{fans.getUserId(),fans.getFansUserId(),fans.getCreateTime(),fans.getUpdateTime()});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public int delUserFansById(int id) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "delete from tb_yeguo_user_fans where id = ?";
        int ret = jdbcTemplate.update(sql, new Object[]{id});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public UserFans getUserFansByUserIdAndFnasUserId(long userId, long fansUserId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_fans where user_id = ? and fans_user_id=?";
        UserFans fans = jdbcTemplate.queryForObject(sql, new Object[]{userId,fansUserId},new UserFansMapper());
        CustomerContextHolder.clearCustomerType();
        return fans;
    }

    @Override
    public List<UserFans> getFansListByUserId(long userId, int start, int count) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_fans where user_id = ? order by create_time desc limit ?,?";
        List<UserFans> list = jdbcTemplate.query(sql, new Object[]{userId,start,count},new UserFansMapper());
        CustomerContextHolder.clearCustomerType();
        return list;
    }

    @Override
    public int getFansCountByUserid(long userId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select count(*) from tb_yeguo_user_fans where user_id = ?";
        int count = jdbcTemplate.queryForInt(sql, new Object[]{userId});
        CustomerContextHolder.clearCustomerType();
        return count;
    }
    
    private static final class UserFansMapper implements RowMapper<UserFans> {
        public UserFans mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserFans fans = new UserFans();
            fans.setCreateTime(rs.getDate("create_time"));
            fans.setFansUserId(rs.getLong("fans_user_id"));
            fans.setId(rs.getInt("id"));
            fans.setUpdateTime(rs.getDate("update_time"));
            fans.setUserId(rs.getLong("user_id"));
            return fans;
        }
    }

}
