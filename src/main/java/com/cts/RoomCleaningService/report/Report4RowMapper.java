package com.cts.RoomCleaningService.report;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.Admin;
import com.cts.RoomCleaningService.model.Cleaner;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Report4RowMapper implements RowMapper<Report4> {

    @Override
    public Report4 mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Report4 cleaner = new Report4();
    	cleaner.setS_no(rs.getInt("s_no"));
    	cleaner.setUid(rs.getString("uid"));
    	cleaner.setCleaner_id(rs.getString("cleaner_id"));
    	cleaner.setFirst(rs.getString("first"));
    	cleaner.setLast(rs.getString("last"));
    	cleaner.setDob(rs.getString("dob"));
    	cleaner.setGender(rs.getString("gender"));
    	cleaner.setPhone(rs.getString("phone"));
    	cleaner.setCreated(rs.getString("created"));
    	cleaner.setModified(rs.getString("modified"));
    	cleaner.setProfile_status(rs.getString("profile_status"));
    	cleaner.setService_status(rs.getString("service_status"));
    	
    	
        return cleaner;
    }
}