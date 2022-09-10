package com.cts.RoomCleaningService.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Question;
import com.cts.RoomCleaningService.model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Question q = new Question();
    	q.setS_no(rs.getInt("s_no"));
    	q.setService_id(rs.getString("service_id"));
    	q.setUser_id(rs.getString("user_id"));
    	q.setUser_uid(rs.getString("user_uid"));
    	q.setCleaner_id(rs.getString("cleaner_id"));
    	q.setQuestion_id(rs.getString("question_id"));
    	q.setCleaner_uid(rs.getString("cleaner_uid"));
    	q.setCreated(rs.getString("created"));
    	q.setModified(rs.getString("modified"));
    	q.setAddress(rs.getString("address"));
    	q.setPincode(rs.getString("pincode"));
    	q.setTime_from(rs.getString("time_from"));
    	q.setTime_to(rs.getString("time_to"));
    	q.setStatus(rs.getString("status"));
    	q.setQuestion(rs.getString("question"));
    	q.setAnswer(rs.getString("answer"));
    	q.setUser_read(rs.getBoolean("user_read"));
    	q.setAdmin_read(rs.getBoolean("admin_read"));
    	q.setCleaner_read(rs.getBoolean("cleaner_read"));
    	
    	return q;
    }
}