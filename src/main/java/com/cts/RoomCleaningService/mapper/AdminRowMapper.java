package com.cts.RoomCleaningService.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {

    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Admin admin = new Admin();
    	admin.setS_no(rs.getInt("s_no"));
    	admin.setUid(rs.getString("uid"));
    	admin.setAdmin_id(rs.getString("admin_id"));
    	admin.setCreated(rs.getString("created"));
    	admin.setModified(rs.getString("modified"));
        return admin;
    }
}