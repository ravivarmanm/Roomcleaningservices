package com.cts.RoomCleaningService.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.RoomCleaningService.dao.impl.userDaoImpl;
import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Question;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.model.Ticket;
import com.cts.RoomCleaningService.model.User;

@Component
public class UserService {
	
	@Autowired
	public userDaoImpl userdao;
	
	public String validateUser(String userid,String password) {
		List<User> users = userdao.findById(userid);
		if(users.isEmpty()) {
			return "User Id Not Present";
		}else {
			boolean match = userdao.matchPassword(userid, password);
			if(match) {
				return "matched";
			}else {
				return "Incorrect Password";
			}
		}
	}
	
	public boolean validateMail(String mail) {
		List<User> users = userdao.findByEmail(mail);
		if(users.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validateId(String id) {
		List<User> users = userdao.findById(id);
		if(users.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	public boolean register(User user,String password) {
		boolean res = userdao.add(user,password);
		return res;
	}

	public boolean addTicket(@Valid Ticket ticket, String user_uid) {	
		return userdao.addTicket(ticket,user_uid);
	}
	
	public List<FeedBack> getFeedBacks(String user_uid) {
		return userdao.getFeedBacks(user_uid);
	}

	public List<Question> getQuestions(String user_uid) {
		return userdao.getQuestions(user_uid);
	}

	public List<Ticket> getTickets(String user_uid) {
		return userdao.getTickets(user_uid);
	}

	public List<Service> getServices(String user_uid) {
		return userdao.getServices( user_uid);
	}

	public boolean bookService(@Valid Service bookService) {
		return userdao.bookService(bookService);
	}

	public Map<String, String> modifyReview(String service_id, String user_uid, String rating, String feedback) {
		boolean r = userdao.modifyReview(service_id,user_uid,rating,feedback);
		Map<String,String> res = new HashMap<>();
		if(r) {
			res.put("status","success");
		}else {			
			res.put("status","failed");
		}
		return res;
	}

	public Map<String, String> modifyQuestion(String question_id, String user_uid, String answer) {
		boolean r = userdao.modifyQuestion(question_id,user_uid,answer);
		Map<String,String> res = new HashMap<>();
		if(r) {
			res.put("status","success");
		}else {			
			res.put("status","failed");
		}
		return res;
	}

	public boolean confirmBookedService(String service_id, String user_uid,String payment_id,String payer_id,double amt) {
		return userdao.confirmBookedService(service_id,user_uid,payment_id,payer_id,amt);
	}
	
	public User getMyProfile(String uid) {
		return userdao.getMyProfile(uid).get(0);
		
	}

	public Service getServiceById(String service_id) {
			return userdao.getServiceById(service_id);
	}
	
	public boolean updatePassword(String pass,String id) {
		return userdao.updatePassword(pass, id);
				
	}
	
	public User findByemailWithQuestion(String email) {
		List<User> users = userdao.findByemailWithQuestion((email));
		return users.get(0);
		}
	public User findByIdWithQuestion(String userId) {
		List<User> users = userdao.findByIdWithQuestion((userId));
		return users.get(0);
		}

	public int cancelService(String service_id, String user_uid) {
		return userdao.cancelService(service_id,user_uid);
	}
}
