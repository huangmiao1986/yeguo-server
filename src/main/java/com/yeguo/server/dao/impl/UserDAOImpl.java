package com.yeguo.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yeguo.server.base.CustomerContextHolder;
import com.yeguo.server.base.JdbcTemplateWrapper;
import com.yeguo.server.dao.UserDAO;
import com.yeguo.server.model.User;
@Repository
public class UserDAOImpl implements UserDAO {

    @Resource
    private JdbcTemplateWrapper jdbcTemplate;
    
    @Override
    public long addUser(User user) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "insert into tb_yeguo_user (openid,user_name,headimgurl,city,province,sex,create_time,update_time) values (?,?,?,?,?,?,?,?)";
        int ret = jdbcTemplate.insertAndGetKey(sql, new Object[]{user.getOpenId(),user.getUserName(),user.getHeadImgUrl(),user.getCity(),
                                                                 user.getProvince(),user.getSex(),user.getCreateTime(),user.getUpdateTime()});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public int updateUser(User user) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "update tb_yeguo_user set user_name=?,headimgurl=?,city=?,province=?,sex=?,update_time=? where id=?";
        int ret = jdbcTemplate.update(sql, new Object[]{user.getUserName(),user.getHeadImgUrl(),user.getCity(),
                                                                 user.getProvince(),user.getSex(),user.getUpdateTime(),user.getUserId()});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public User getUserById(long userId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_READ);
        String sql = "select * from tb_yeguo_user where id=?";
        User user = jdbcTemplate.queryForObject(sql,new Object[]{userId}, new UserMapper());
        CustomerContextHolder.clearCustomerType();
        return user;
    }

    @Override
    public int updateUserNameByUserId(long userId,String userName) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "update tb_yeguo_user set user_name=?,update_time=? where id=?";
        int ret = jdbcTemplate.update(sql, new Object[]{userName,new Date(),userId});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public int updateUsersSignByUserId(long userId,String sign) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "update tb_yeguo_user set signature=?,update_time=? where id=?";
        int ret = jdbcTemplate.update(sql, new Object[]{sign,new Date(),userId});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }
    
    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setCity(rs.getString("city"));
            user.setCreateTime(rs.getDate("create_time"));
            user.setHeadImgUrl(rs.getString("headimgurl"));
            user.setOpenId(rs.getString("openid"));
            user.setPhoneNum(rs.getString("phone_num"));
            user.setProvince(rs.getString("province"));
            user.setSex(rs.getShort("sex"));
            user.setSignature(rs.getString("signature"));
            user.setType(rs.getShort("type"));
            user.setUpdateTime(rs.getDate("update_time"));
            user.setUserId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            return user;
        }
    }

    @Override
    public User getUserByPhoneNum(String phoneNum) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_READ);
        String sql = "select * from tb_yeguo_user where phone_num=?";
        User user = jdbcTemplate.queryForObject(sql,new Object[]{phoneNum}, new UserMapper());
        CustomerContextHolder.clearCustomerType();
        return user;
    }

}
