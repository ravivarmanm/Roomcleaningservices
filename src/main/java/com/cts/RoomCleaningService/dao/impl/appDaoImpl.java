package com.cts.RoomCleaningService.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cts.RoomCleaningService.dao.userDao;
import com.cts.RoomCleaningService.mapper.FeedBackRowMapper;
import com.cts.RoomCleaningService.mapper.QuestionRowMapper;
import com.cts.RoomCleaningService.mapper.ServiceRowMapper;
import com.cts.RoomCleaningService.mapper.TicketRowMapper;
import com.cts.RoomCleaningService.mapper.UserRowMapper;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Question;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.model.Ticket;
import com.cts.RoomCleaningService.model.User;
import com.cts.RoomCleaningService.service.DBService;

@Component
public class appDaoImpl{
	@Autowired
	public DBService db;
	
	public void markReadService(String column,String service_id) {
		String sql = "update services set "+column+"=true,modified=CURRENT_TIMESTAMP where service_id = ?";
		db.jdbcTemplate.update(sql,service_id);
	}
	
	public void markReadFeedBack(String column,String service_id) {
		String sql = "update feedbacks set "+column+"=true,modified=CURRENT_TIMESTAMP where service_id = ?";
		db.jdbcTemplate.update(sql,service_id);
	}
	
	public void markReadCleaner(String column,String cleaner_id) {
		String sql = "update cleaners set "+column+"=true,modified=CURRENT_TIMESTAMP where cleaner_id = ?";
		db.jdbcTemplate.update(sql,cleaner_id);
	}
	
	public void markReadQuestion(String column,String question_id) {
		String sql = "update questions set "+column+"=true,modified=CURRENT_TIMESTAMP where question_id = ?";
		db.jdbcTemplate.update(sql,question_id);
	}
	
	public void markReadTicket(String column,String ticket_id) {
		String sql = "update tickets set "+column+"=true,modified=CURRENT_TIMESTAMP where ticket_id = ?";
		db.jdbcTemplate.update(sql,ticket_id);
	}
}
