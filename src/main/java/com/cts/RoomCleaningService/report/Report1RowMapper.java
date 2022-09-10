package com.cts.RoomCleaningService.report;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Report1RowMapper implements RowMapper<Report1> {

    @Override
    public Report1 mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Report1 r = new Report1();
    	
    	r.setS_no(rs.getInt("s_no"));
    	r.setService_id(rs.getString("service_id"));
    	r.setUser_id(rs.getString("user_id"));
    	r.setUser_uid(rs.getString("user_uid"));
    	r.setCleaner_id(rs.getString("cleaner_id"));
    	r.setCleaner_uid(rs.getString("cleaner_uid"));
    	r.setCreated(rs.getString("created"));
    	r.setModified(rs.getString("modified"));
    	r.setRoom_count(rs.getString("room_count"));
    	r.setAddress(rs.getString("address"));
    	r.setPincode(rs.getString("pincode"));
    	r.setTime_from(rs.getString("time_from"));
    	r.setTime_to(rs.getString("time_to"));
    	r.setContactNumber(rs.getString("contact_number"));
    	r.setStatus(rs.getString("status"));
    	
    	return r;
    }
}