package com.cts.RoomCleaningService.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.RoomCleaningService.dao.impl.cleanerDaoImpl;
import com.cts.RoomCleaningService.dao.impl.userDaoImpl;
import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.model.User;

@Component
public class CleanerService {
	
	@Autowired
	public cleanerDaoImpl cleanerdao;
	
	public String validateCleaner(String cleaner_id,String password) {
		List<Cleaner> cleaners = cleanerdao.findById(cleaner_id);
		if(cleaners.isEmpty()) {
			return "Cleaner Id Not Present";
		}else {
			boolean match = cleanerdao.matchPassword(cleaner_id, password);
			if(match) {
				if(cleaners.get(0).getProfile_status().equals("denied")) {
					return "Your Profile rejected,please contact admin";
				}
				return "matched";
			}else {
				return "Incorrect Password";
			}
		}
	}
	
	public boolean validatePhone(String phone) {
		List<Cleaner> cleaners = cleanerdao.findByPhone(phone);
		if(cleaners.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validateId(String id) {
		List<Cleaner> cleaners = cleanerdao.findById(id);
		if(cleaners.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	public boolean register(Cleaner cleaner,String password) {
		boolean res = cleanerdao.add(cleaner, password);
		return res;
	}

	public Map<String, String> modifyMyService(String service_id, String cleaner_uid,String user_uid,String status) {
		boolean r = cleanerdao.modifyMyService(service_id,cleaner_uid,user_uid,status);
		Map<String,String> res = new HashMap<>();
		if(r) {
			res.put("status","success");
		}else {			
			res.put("status","failed");
		}
		return res;
	}

	public List<Service> getMyServices(String cleaner_uid) {
		return cleanerdao.getMyServices(cleaner_uid);
	}

	public List<FeedBack> getMyFeedBacks(String cleaner_uid) {
		return cleanerdao.getFeedBacks(cleaner_uid);
	}

	public Cleaner getMyProfile(String uid) {
		return cleanerdao.getMyProfile(uid).get(0);
		
	}
}
