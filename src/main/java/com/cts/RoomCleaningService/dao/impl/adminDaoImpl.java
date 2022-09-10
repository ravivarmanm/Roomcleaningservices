package com.cts.RoomCleaningService.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cts.RoomCleaningService.dao.adminDao;
import com.cts.RoomCleaningService.mapper.AdminRowMapper;
import com.cts.RoomCleaningService.mapper.CleanerRowMapper;
import com.cts.RoomCleaningService.mapper.FeedBackRowMapper;
import com.cts.RoomCleaningService.mapper.QuestionRowMapper;
import com.cts.RoomCleaningService.mapper.ServiceRowMapper;
import com.cts.RoomCleaningService.mapper.TicketRowMapper;
import com.cts.RoomCleaningService.model.Admin;
import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Question;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.model.Ticket;
import com.cts.RoomCleaningService.report.Report1;
import com.cts.RoomCleaningService.report.Report1RowMapper;
import com.cts.RoomCleaningService.report.Report2;
import com.cts.RoomCleaningService.report.Report2RowMapper;
import com.cts.RoomCleaningService.report.Report3;
import com.cts.RoomCleaningService.report.Report3RowMapper;
import com.cts.RoomCleaningService.report.Report4;
import com.cts.RoomCleaningService.report.Report4RowMapper;
import com.cts.RoomCleaningService.service.DBService;

@Component
public class adminDaoImpl implements adminDao{
	@Autowired
	public DBService db;
	
	public final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public List<Admin> findById(String id) {
		// TODO Auto-generated method stub
		String sql = "select s_no,uid,admin_id,created,modified from admin where admin_id='"+id+"'";
		return db.jdbcTemplate.query(sql,new AdminRowMapper());
	}

	public List<Cleaner> getCleaners(String condition){
		String sql = "select user_read,cleaner_read,admin_read,s_no,uid,cleaner_id,first,last,dob,gender,phone,profile_status,service_status,created,modified from cleaners";
		if(condition.length() > 0) {
			sql += " where "+condition;
		}
		sql += " order by modified";
		return db.jdbcTemplate.query(sql,new CleanerRowMapper());
	}
	
	public boolean matchPassword(String id,String pass) {
		String sql = "select password from admin where admin_id='"+id+"'";
		String password = db.jdbcTemplate.queryForObject(sql,String.class);
		return bCryptPasswordEncoder.matches(pass, password);
	}

	public boolean modifyCleaner(String cleaner_id, String profile_status) {
		// TODO Auto-generated method stub
		String sql = "update cleaners set user_read=false,cleaner_read=false,admin_read=false,profile_status=?,modified=CURRENT_TIMESTAMP where cleaner_id=?";
		int row = db.jdbcTemplate.update(sql,profile_status,cleaner_id);
		if(row > 0) {
			return true;
		}
		return false;
	}
	
	public boolean modifyTicket(String ticket_id, String solution) {
		// TODO Auto-generated method stub
		String sql = "update tickets set user_read=false,cleaner_read=false,admin_read=false,solution = ?,status='resolved',modified=CURRENT_TIMESTAMP where ticket_id=?";
		int row = db.jdbcTemplate.update(sql,solution,ticket_id);
		if(row > 0) {
			return true;
		}
		return false;
	}

	public List<Ticket> getTickets(String condition) {
		String sql = "select tickets.user_read,tickets.cleaner_read,tickets.admin_read,tickets.s_no,`ticket_id`, `user_uid`,`user_id`, tickets.`issue`, tickets.`description`,tickets.`solution`,tickets.`created`,tickets.`modified`,tickets.`status` from tickets,users where uid = user_uid ";
		
		if(condition.length() > 0) {
			sql += " and "+condition;
		}
		sql += " order by tickets.modified";
		return db.jdbcTemplate.query(sql,new TicketRowMapper());
	}

	public List<Service> getServices(String condition) {
		String sql = "select s.user_read,s.cleaner_read,s.admin_read,s.s_no,u.user_id, s.service_id, s.user_uid,c.cleaner_id, s.cleaner_uid, s.room_count, s.address, s.pincode, s.time_from, s.time_to, s.contact_number, s.status, s.created, s.modified FROM services s,users u,cleaners c where s.user_uid = u.uid and s.cleaner_uid = c.uid  union select s.user_read,s.cleaner_read,s.admin_read,s.s_no,u.user_id, s.service_id, s.user_uid,NULL as cleaner_id, s.cleaner_uid, s.room_count, s.address, s.pincode, s.time_from, s.time_to, s.contact_number, s.status, s.created, s.modified FROM services s,users u,cleaners c where s.user_uid = u.uid and s.cleaner_uid is null";
		
		sql += " order by modified";
		return db.jdbcTemplate.query(sql,new ServiceRowMapper());
	}

	public boolean modifyService(String service_id, String status, String cleaner_uid) {
		
		String sql = "";
		int row = 0;
		if(cleaner_uid == null || cleaner_uid.equals("null")) {
			sql = "update services set user_read=false,cleaner_read=false,admin_read=false,status = ?,modified=CURRENT_TIMESTAMP where service_id=?";
			row = db.jdbcTemplate.update(sql,status,service_id);
		}else {
			sql = "update services set user_read=false,cleaner_read=false,admin_read=false,status = ?,modified=CURRENT_TIMESTAMP,cleaner_uid=? where service_id=?";
			row = db.jdbcTemplate.update(sql,status,cleaner_uid,service_id);
		}
		if(row > 0) {
			return true;
		}
		return false;
	}

	public List<FeedBack> getFeedBacks() {
		String sql = "select f.user_read,f.cleaner_read,f.admin_read,f.s_no,u.user_id, f.service_id,f.feedback,f.rating,f.user_uid,c.cleaner_id, f.cleaner_uid, s.address, s.pincode, s.time_from, s.time_to, f.status, f.created, f.modified FROM feedbacks f,services s,users u,cleaners c where f.service_id = s.service_id and f.user_uid = u.uid and f.cleaner_uid = c.uid";
		
		sql += " order by modified desc";
		return db.jdbcTemplate.query(sql,new FeedBackRowMapper());
	}
	
	public List<Question> getQuestions() {
		String sql = "select q.user_read,q.cleaner_read,q.admin_read,q.s_no,u.user_id,q.question_id,q.service_id,q.question,q.answer,q.user_uid,c.cleaner_id, q.cleaner_uid, s.address, s.pincode, s.time_from, s.time_to, q.status, q.created, q.modified FROM questions q,services s,users u,cleaners c where q.service_id = s.service_id and q.user_uid = u.uid and q.cleaner_uid = c.uid";
		
		sql += " order by modified desc";
		return db.jdbcTemplate.query(sql,new QuestionRowMapper());
	}
	
	public boolean addQuestion(String service_id,String cleaner_id,String user_uid,String question) {
		
		String uid = UUID.randomUUID().toString();
        
		int row = 	db.jdbcTemplate.update("INSERT INTO `questions` (`question_id`,`service_id`, `cleaner_uid`,user_uid,`question`, `answer`) VALUES (?,?,?,?,?,'Not Answered')",uid,service_id,cleaner_id,user_uid,question);
		if(row > 0) {
			return true;
		}
		return false;
	}

	public List<Report1> generateServicesReport(String time_from, String time_to) {
		String sql = "select s.s_no,u.user_id, s.service_id, s.user_uid,c.cleaner_id, s.cleaner_uid, s.room_count, s.address, s.pincode, s.time_from, s.time_to, s.contact_number, s.status, s.created, s.modified FROM services s,users u,cleaners c where s.user_uid = u.uid and s.cleaner_uid = c.uid  and s.status = 'completed' and s.modified >= '"+time_from+"' and s.modified <= '"+time_to+"' order by s.modified";
		return db.jdbcTemplate.query(sql,new Report1RowMapper());
	}

	public List<Report2> generateFeedBackReport(String time_from, String time_to) {
		String sql = "select q.s_no,u.user_id,q.question_id,q.service_id,q.question,q.answer,q.user_uid,c.cleaner_id, q.cleaner_uid, s.address, s.pincode, s.time_from, s.time_to, q.status, q.created, q.modified FROM questions q,services s,users u,cleaners c where q.service_id = s.service_id and q.user_uid = u.uid and q.cleaner_uid = c.uid and q.modified >= '"+time_from+"' and q.modified <= '"+time_to+"'";	
		sql += " order by q.status,q.modified desc";
		return db.jdbcTemplate.query(sql,new Report2RowMapper());
	}

	public List<Report3> generateReviewReport(String time_from, String time_to) {
		String sql = "select f.s_no,u.user_id, f.service_id,f.feedback,f.rating,f.user_uid,c.cleaner_id, f.cleaner_uid, s.address, s.pincode, s.time_from, s.time_to, f.status, f.created, f.modified FROM feedbacks f,services s,users u,cleaners c where f.service_id = s.service_id and f.user_uid = u.uid and f.cleaner_uid = c.uid and f.status = 'given' and f.modified >= '"+time_from+"' and f.modified <= '"+time_to+"'";
		sql += " order by modified desc";
		return db.jdbcTemplate.query(sql,new Report3RowMapper());
	}
	
	public List<Report4> generateCleanersReport(String time_from, String time_to){
		String sql = "select s_no,uid,cleaner_id,first,last,dob,gender,phone,profile_status,service_status,created,modified from cleaners";
		
		sql += " order by profile_status,modified";
		return db.jdbcTemplate.query(sql,new Report4RowMapper());
	}

}
