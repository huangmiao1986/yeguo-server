package com.yeguo.server.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.yeguo.server.base.CustomerContextHolder;
import com.yeguo.server.base.JdbcTemplateWrapper;
import com.yeguo.server.dao.UserPraiseDAO;
import com.yeguo.server.model.UserPraise;
@Repository
public class UserPraiseDAOImpl implements UserPraiseDAO {

    @Resource
    private JdbcTemplateWrapper jdbcTemplate;
    
    @Override
    public int addUserPraise(UserPraise praise) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "insert into tb_yeguo_user_praise (publish_id,user_id,praise_user_id,count,create_time,update_time) values (?,?,?,?,?,?)";
        int ret = jdbcTemplate.insertAndGetKey(sql, new Object[]{praise.getPublishId(),praise.getUserId(),praise.getPraiseUserId(),1,praise.getCreateTime(),praise.getUpdateTime()});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }

    @Override
    public int getPraiseByUserIdAndPublishIdAndDate(long userId, int publishId,
                                                              String date) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select count(*) from tb_yeguo_user_praise where user_id = ? and publish_id=? and create_time like '"+date+"%'";
        int count = jdbcTemplate.queryForInt(sql, new Object[]{userId});
        CustomerContextHolder.clearCustomerType();
        return count;
    }

}
