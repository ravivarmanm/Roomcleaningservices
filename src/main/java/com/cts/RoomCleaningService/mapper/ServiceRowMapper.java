package com.cts.RoomCleaningService.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceRowMapper implements RowMapper<Service> {

    @Override
    public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Service service = new Service();
    	service.setS_no(rs.getInt("s_no"));
    	service.setService_id(rs.getString("service_id"));
    	service.setUser_id(rs.getString("user_id"));
    	service.setUser_uid(rs.getString("user_uid"));
    	service.setCleaner_id(rs.getString("cleaner_id"));
    	service.setCleaner_uid(rs.getString("cleaner_uid"));
    	service.setCreated(rs.getString("created"));
    	service.setModified(rs.getString("modified"));
    	service.setRoom_count(rs.getString("room_count"));
    	service.setAddress(rs.getString("address"));
    	service.setPincode(rs.getString("pincode"));
    	service.setTime_from(rs.getString("time_from"));
    	service.setTime_to(rs.getString("time_to"));
    	service.setContactNumber(rs.getString("contact_number"));
    	service.setStatus(rs.getString("status"));
    	service.setUser_read(rs.getBoolean("user_read"));
    	service.setAdmin_read(rs.getBoolean("admin_read"));
    	service.setCleaner_read(rs.getBoolean("cleaner_read"));
    	return service;
    }
}