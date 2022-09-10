package com.cts.RoomCleaningService.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedBackRowMapper implements RowMapper<FeedBack> {

    @Override
    public FeedBack mapRow(ResultSet rs, int rowNum) throws SQLException {
    	FeedBack fb = new FeedBack();
    	fb.setS_no(rs.getInt("s_no"));
    	fb.setRating(rs.getInt("rating"));
    	fb.setService_id(rs.getString("service_id"));
    	fb.setUser_id(rs.getString("user_id"));
    	fb.setUser_uid(rs.getString("user_uid"));
    	fb.setCleaner_id(rs.getString("cleaner_id"));
    	fb.setCleaner_uid(rs.getString("cleaner_uid"));
    	fb.setCreated(rs.getString("created"));
    	fb.setModified(rs.getString("modified"));
    	fb.setAddress(rs.getString("address"));
    	fb.setPincode(rs.getString("pincode"));
    	fb.setTime_from(rs.getString("time_from"));
    	fb.setTime_to(rs.getString("time_to"));
    	fb.setStatus(rs.getString("status"));
    	fb.setFeedback(rs.getString("feedback"));
    	fb.setUser_read(rs.getBoolean("user_read"));
    	fb.setAdmin_read(rs.getBoolean("admin_read"));
    	fb.setCleaner_read(rs.getBoolean("cleaner_read"));
    	
    	return fb;
    }
}