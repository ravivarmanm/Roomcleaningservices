package com.cts.RoomCleaningService.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.cts.RoomCleaningService.model.Ticket;
import com.cts.RoomCleaningService.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Ticket ticket = new Ticket();
    	ticket.setS_no(rs.getInt("s_no"));
    	ticket.setTicket_id(rs.getString("ticket_id"));
    	ticket.setUser_uid(rs.getString("user_uid"));
    	ticket.setDescription(rs.getString("description"));
    	ticket.setIssue(rs.getString("issue"));
    	ticket.setStatus(rs.getString("status"));
    	ticket.setCreated(rs.getString("created"));
    	ticket.setModified(rs.getString("modified"));
    	ticket.setSolution(rs.getString("solution"));
    	ticket.setUser_id(rs.getString("user_id"));
    	ticket.setUser_read(rs.getBoolean("user_read"));
    	ticket.setAdmin_read(rs.getBoolean("admin_read"));
    	ticket.setCleaner_read(rs.getBoolean("cleaner_read"));    	
        return ticket;
    }
}