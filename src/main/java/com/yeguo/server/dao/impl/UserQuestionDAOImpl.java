package com.yeguo.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yeguo.server.base.CustomerContextHolder;
import com.yeguo.server.base.JdbcTemplateWrapper;
import com.yeguo.server.dao.UserQuestionDAO;
import com.yeguo.server.model.UserQuestion;
@Repository
public class UserQuestionDAOImpl implements UserQuestionDAO {

    @Resource
    private JdbcTemplateWrapper jdbcTemplate;
    
    @Override
    public int addUserQuestion(UserQuestion question) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "insert into tb_yeguo_user_question (question,answer,user_id,create_time,update_time) values (?,?,?,?,?)";
        int key = jdbcTemplate.insertAndGetKey(sql, new Object[]{question.getQuestion(),question.getAnswer(),question.getUserId(),question.getCreateTime(),question.getUpdateTime()});
        CustomerContextHolder.clearCustomerType();
        return key;
    }

    @Override
    public int delUserQuestionById(int id) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "delete from tb_yeguo_user_question where id = ?";
        int ret = jdbcTemplate.update(sql, new Object[]{id});
        CustomerContextHolder.clearCustomerType();
        return ret;
    }
    
    @Override
    public UserQuestion getQuestionById(int id) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_question where id = ?";
        UserQuestion question = jdbcTemplate.queryForObject(sql, new Object[]{id},new UserQuestionMapper());
        CustomerContextHolder.clearCustomerType();
        return question;
    }

    @Override
    public List<UserQuestion> getQuestionListByUserId(long userId) {
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_WRITE);
        String sql = "select * from tb_yeguo_user_question where user_id = ?";
        List<UserQuestion> list = jdbcTemplate.query(sql, new Object[]{userId},new UserQuestionMapper());
        CustomerContextHolder.clearCustomerType();
        return list;
    }
    
    private static final class UserQuestionMapper implements RowMapper<UserQuestion> {
        public UserQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserQuestion question = new UserQuestion();
            question.setAnswer(rs.getString("answer"));
            question.setCreateTime(rs.getDate("create_time"));
            question.setId(rs.getInt("id"));
            question.setQuestion(rs.getString("question"));
            question.setUpdateTime(rs.getDate("update_time"));
            question.setUserId(rs.getLong("user_id"));
            return question;
        }
    }

}
