package com.cts.RoomCleaningService.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cts.RoomCleaningService.dao.userDao;
import com.cts.RoomCleaningService.mapper.CleanerRowMapper;
import com.cts.RoomCleaningService.mapper.FeedBackRowMapper;
import com.cts.RoomCleaningService.mapper.QuestionRowMapper;
import com.cts.RoomCleaningService.mapper.ServiceRowMapper;
import com.cts.RoomCleaningService.mapper.TicketRowMapper;
import com.cts.RoomCleaningService.mapper.UserRowMapper;
import com.cts.RoomCleaningService.mapper.UserRowMapper2;
import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Question;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.model.Ticket;
import com.cts.RoomCleaningService.model.User;
import com.cts.RoomCleaningService.service.DBService;

@Component
public class userDaoImpl implements userDao{
	@Autowired
	public DBService db;
	
	public final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public boolean add(User user,String password) {
		// TODO Auto-generated method stub
		
		String uid = UUID.randomUUID().toString();
        password = bCryptPasswordEncoder.encode(password);
        
    	String sql = "INSERT INTO `users` (`uid`, `user_id`, `password`, `first`, `last`, `dob`, `gender`, `phone`, `email`,`question1`,`question2`,`question3`) VALUES (?, ?, ?, ?, ?,?, ?,?,?,?,?,?)";
		int row = db.jdbcTemplate.update(sql,uid,user.getUser_id(),password,user.getFirst(),user.getLast(),user.getDob(),user.getGender(),user.getPhone(),user.getMail(),user.getQuestion1(),user.getQuestion2(),user.getQuestion3());
		if(row > 0)
			return true;
		return false;
	}

	@Override
	public List<User> findById(String id) {
		// TODO Auto-generated method stub
		String sql = "select s_no,uid,user_id,first,last,dob,gender,phone,email,created,modified from users where user_id='"+id+"'";
		return db.jdbcTemplate.query(sql,new UserRowMapper());
	}

	@Override
	public List<User> findByEmail(String email) {
		String sql = "select s_no,uid,user_id,first,last,dob,gender,phone,email,created,modified from users where email ='"+email+"'";
		return db.jdbcTemplate.query(sql,new UserRowMapper());
	}
	
	public boolean matchPassword(String id,String pass) {
		
		String sql = "select password from users where user_id='"+id+"'";
		String password = db.jdbcTemplate.queryForObject(sql,String.class);
		return bCryptPasswordEncoder.matches(pass, password);
	}

	public boolean addTicket(@Valid Ticket ticket, String user_uid) {
		
		String uid = UUID.randomUUID().toString();
        
		String sql = "INSERT INTO `tickets` (`ticket_id`, `user_uid`, `issue`, `description`) VALUES (?,?,?,?)";
		int row = db.jdbcTemplate.update(sql,uid,user_uid,ticket.getIssue(),ticket.getDescription());
		if(row > 0)
			return true;
		return false;
	}
	
	public List<FeedBack> getFeedBacks(String user_uid) {
		String sql = "select f.user_read,f.cleaner_read,f.admin_read,f.s_no,u.user_id, f.service_id,f.feedback,f.rating,f.user_uid,c.cleaner_id, f.cleaner_uid, s.address, s.pincode, s.time_from, s.time_to, f.status, f.created, f.modified FROM feedbacks f,services s,users u,cleaners c where f.service_id = s.service_id and f.user_uid = u.uid and f.cleaner_uid = c.uid and f.user_uid = '"+user_uid+"'";
		
		sql += " order by modified desc";
		return db.jdbcTemplate.query(sql,new FeedBackRowMapper());
	}
	
	public List<Question> getQuestions(String user_uid) {
		String sql = "select q.user_read,q.cleaner_read,q.admin_read,q.s_no,u.user_id,q.question_id,q.service_id,q.question,q.answer,q.user_uid,c.cleaner_id, q.cleaner_uid, s.address, s.pincode, s.time_from, s.time_to, q.status, q.created, q.modified FROM questions q,services s,users u,cleaners c where q.service_id = s.service_id and q.user_uid = u.uid and q.cleaner_uid = c.uid and q.user_uid = '"+user_uid+"'";
		
		sql += " order by modified desc";
		return db.jdbcTemplate.query(sql,new QuestionRowMapper());
	}
	
	
	public List<Ticket> getTickets(String user_uid) {
		String sql = "select tickets.user_read,tickets.cleaner_read,tickets.admin_read,tickets.s_no,`ticket_id`, `user_uid`,`user_id`, tickets.`issue`, tickets.`description`,tickets.`solution`,tickets.`created`,tickets.`modified`,tickets.`status` from tickets,users where uid = user_uid and user_uid = '"+user_uid+"'";
		
		sql += " order by tickets.modified";
		return db.jdbcTemplate.query(sql,new TicketRowMapper());
	}

	public List<Service> getServices(String user_uid) {
		String sql = "select s.user_read,s.cleaner_read,s.admin_read,s.s_no,u.user_id, s.service_id, s.user_uid,c.cleaner_id, s.cleaner_uid, s.room_count, s.address, s.pincode, s.time_from, s.time_to, s.contact_number, s.status, s.created, s.modified FROM services s,users u,cleaners c where s.user_uid = u.uid and s.cleaner_uid = c.uid and s.user_uid = '"+user_uid+"' union select s.user_read,s.cleaner_read,s.admin_read,s.s_no,u.user_id, s.service_id, s.user_uid,NULL as cleaner_id, s.cleaner_uid, s.room_count, s.address, s.pincode, s.time_from, s.time_to, s.contact_number, s.status, s.created, s.modified FROM services s,users u,cleaners c where s.user_uid = u.uid and s.cleaner_uid is null and s.user_uid = '"+user_uid+"'";
		
		sql += " order by modified";
		return db.jdbcTemplate.query(sql,new ServiceRowMapper());
	}

	public boolean bookService(@Valid Service s) {
		String uid = UUID.randomUUID().toString();
        String sql = "INSERT INTO `services` (`service_id`, `user_uid`, `room_count`, `address`, `pincode`, `time_from`, `time_to`, `contact_number`) VALUES (?,?,?,?,?,?,?,?)";
        int row = db.jdbcTemplate.update(sql,uid,s.getUser_uid(),s.getRoom_count(),s.getAddress(),s.getPincode(),s.getTime_from(),s.getTime_to(),s.getContactNumber());
		if(row > 0)
			return true;
		return false;
	}

	public boolean modifyReview(String service_id, String user_uid, String rating, String feedback) {
		String sql = "update feedbacks set user_read=false,cleaner_read=false,admin_read=false,status='given',rating = ?,feedback=?,modified=CURRENT_TIMESTAMP where service_id=? and user_uid = ?";
		int	row = db.jdbcTemplate.update(sql,rating,feedback,service_id,user_uid);
		
		if(row > 0) {
			return true;
		}
		return false;
	}

	public boolean modifyQuestion(String question_id, String user_uid, String answer) {
		String sql = "update questions set user_read=false,cleaner_read=false,admin_read=false,status='answered',answer = ?,modified=CURRENT_TIMESTAMP where question_id=? and user_uid = ?";
		int	row = db.jdbcTemplate.update(sql,answer,question_id,user_uid);
		
		if(row > 0) {
			return true;
		}
		return false;
	}

	public boolean confirmBookedService(String service_id, String user_uid, String payment_id, String payer_id, double amt) {
		String sql = "update services set user_read=false,cleaner_read=false,admin_read=false,status='booked',modified=CURRENT_TIMESTAMP where service_id= ? and user_uid = ?";
		String tSql = "INSERT INTO `transactions` (`service_id`, `payment_id`, `payer_id`, `user_uid`, `status`,`amount`) VALUES (?,?,?,?,'approved',?)";
		int	row = db.jdbcTemplate.update(sql,service_id,user_uid);
		
		if(row > 0) {
			int	row1 = db.jdbcTemplate.update(tSql,service_id,payment_id,payer_id,user_uid,amt);
			if(row1 > 0)
				return true;
		}
		return false;
	}
	
	public List<User> getMyProfile(String uid) {
		String sql = "select s_no,uid,user_id,first,last,dob,gender,phone,email,created,modified from users where uid='"+uid+"'";
			return db.jdbcTemplate.query(sql,new UserRowMapper());
	}

	public Service getServiceById(String service_id) {
		String sql = "select s.user_read,s.cleaner_read,s.admin_read,s.s_no,u.user_id, s.service_id, s.user_uid,c.cleaner_id, s.cleaner_uid, s.room_count, s.address, s.pincode, s.time_from, s.time_to, s.contact_number, s.status, s.created, s.modified FROM services s,users u,cleaners c where s.user_uid = u.uid and s.cleaner_uid = c.uid and s.service_id = '"+service_id+"' union select s.user_read,s.cleaner_read,s.admin_read,s.s_no,u.user_id, s.service_id, s.user_uid,NULL as cleaner_id, s.cleaner_uid, s.room_count, s.address, s.pincode, s.time_from, s.time_to, s.contact_number, s.status, s.created, s.modified FROM services s,users u,cleaners c where s.user_uid = u.uid and s.cleaner_uid is null and s.service_id = '"+service_id+"'";	
		return db.jdbcTemplate.query(sql,new ServiceRowMapper()).get(0);

	}
	
	public List<User> findByemailWithQuestion(String email) {
		String sql = "select s_no,uid,user_id,first,last,dob,gender,phone,email,created,modified,question1,question2,question3 from users where email ='"+email+"'";
		return db.jdbcTemplate.query(sql,new UserRowMapper2());
	}
	
	public List<User> findByIdWithQuestion(String id) {
		String sql = "select s_no,uid,user_id,first,last,dob,gender,phone,email,created,modified,question1,question2,question3 from users where user_id='"+id+"'";
		return db.jdbcTemplate.query(sql,new UserRowMapper2());
	}
	
	   public boolean updatePassword(String pass,String id) {
		   String encodedpassword = bCryptPasswordEncoder.encode(pass);
		   System.out.println(pass);
		   System.out.println(encodedpassword);
		   String sql = "update users set password = '"+encodedpassword+"' where user_id='"+id+"'";
		   int row = db.jdbcTemplate.update(sql);
		   System.out.println("row value "+row);
		   return row>=1 ? true :false;
			
		}

	public int cancelService(String service_id, String user_uid) {
		return db.jdbcTemplate.update("delete from services where service_id = ? and user_uid = ?",service_id,user_uid);
	}

}
