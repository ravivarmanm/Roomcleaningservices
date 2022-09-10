package com.cts.RoomCleaningService.report;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Report3RowMapper implements RowMapper<Report3> {

    @Override
    public Report3 mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Report3 fb = new Report3();
    	
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
 
    	return fb;
    }
}