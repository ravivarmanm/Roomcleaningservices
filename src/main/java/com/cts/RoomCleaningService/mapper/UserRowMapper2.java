package com.cts.RoomCleaningService.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper2 implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    	User user = new User();
    	user.setS_no(rs.getInt("s_no"));
    	user.setUid(rs.getString("uid"));
    	user.setUser_id(rs.getString("user_id"));
    	user.setFirst(rs.getString("first"));
    	user.setLast(rs.getString("last"));
    	user.setDob(rs.getString("dob"));
    	user.setGender(rs.getString("gender"));
    	user.setPhone(rs.getString("phone"));
    	user.setMail(rs.getString("email"));
    	user.setCreated(rs.getString("created"));
    	user.setModified(rs.getString("modified"));
    	user.setQuestion1(rs.getString("question1"));
    	user.setQuestion2(rs.getString("question2"));
    	user.setQuestion3(rs.getString("question3"));
    	return user;
    }
}