package com.cts.RoomCleaningService.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CleanerRowMapper implements RowMapper<Cleaner> {

    @Override
    public Cleaner mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Cleaner cleaner = new Cleaner();
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
    	cleaner.setUser_read(rs.getBoolean("user_read"));
    	cleaner.setAdmin_read(rs.getBoolean("admin_read"));
    	cleaner.setCleaner_read(rs.getBoolean("cleaner_read"));
    	
        return cleaner;
    }
}