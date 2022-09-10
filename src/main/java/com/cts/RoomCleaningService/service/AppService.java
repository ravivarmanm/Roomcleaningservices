package com.cts.RoomCleaningService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.RoomCleaningService.dao.impl.appDaoImpl;
import com.cts.RoomCleaningService.dao.impl.userDaoImpl;
import com.cts.RoomCleaningService.model.User;

@Component
public class AppService {
	
	@Autowired
	public appDaoImpl appImpl;
	
	public void markReadService(String column,String service_id) {
		appImpl.markReadService(column, service_id);
	}
	
	public void markReadFeedBack(String column,String service_id) {
		appImpl.markReadFeedBack(column, service_id);
	}
	
	public void markReadCleaner(String column,String cleaner_id) {
		appImpl.markReadCleaner(column, cleaner_id);
	}
	
	public void markReadQuestion(String column,String question_id) {
		appImpl.markReadQuestion(column, question_id);
	}
	
	public void markReadTicket(String column,String ticket_id) {
		appImpl.markReadTicket(column, ticket_id);
	}
	
}
