package com.cts.RoomCleaningService.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.RoomCleaningService.dao.impl.adminDaoImpl;
import com.cts.RoomCleaningService.model.Admin;
import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Question;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.model.Ticket;
import com.cts.RoomCleaningService.report.ExcelExporter;
import com.cts.RoomCleaningService.report.PDFExporter;
import com.cts.RoomCleaningService.report.Report1;
import com.cts.RoomCleaningService.report.Report2;
import com.cts.RoomCleaningService.report.Report3;
import com.cts.RoomCleaningService.report.Report4;
import com.lowagie.text.DocumentException;


@Component
public class AdminService {
	
	@Autowired
	public adminDaoImpl adminDao;
	
	public String validate(String adminId,String password) {
		List<Admin> admin = adminDao.findById(adminId);
		if(admin.isEmpty()) {
			return "Admin Id Not Present";
		}else {
			boolean match = adminDao.matchPassword(adminId, password);
			if(match) {
				return "matched";
			}else {
				return "Incorrect Password";
			}
		}
	}	
	
	public List<Cleaner> getCleaners() {
		List<Cleaner> cleaners = adminDao.getCleaners("");
		
		return cleaners;
	}
	
	public Map<String,String> modifyCleaner(String cleaner_id,String profile_status) {
		boolean r = adminDao.modifyCleaner(cleaner_id,profile_status);
		Map<String,String> res = new HashMap<>();
		if(r) {
			res.put("status","success");
		}else {			
			res.put("status","failed");
		}
		return res;
	}

	public Map<String,String> modifyTicket(String ticket_id,String solution) {
		boolean r = adminDao.modifyTicket(ticket_id,solution);
		Map<String,String> res = new HashMap<>();
		if(r) {
			res.put("status","success");
		}else {			
			res.put("status","failed");
		}
		return res;
	}
	
	public List<Ticket> getTickets() {
		return adminDao.getTickets("");
	}

	public List<Service> getServices() {
		return adminDao.getServices("");
	}

	public Map<String, String> modifyService(String service_id, String status, String cleaner_uid) {
		boolean r = adminDao.modifyService(service_id,status,cleaner_uid);
		Map<String,String> res = new HashMap<>();
		if(r) {
			res.put("status","success");
		}else {			
			res.put("status","failed");
		}
		return res;
	}

	public List<FeedBack> getFeedBacks() {
		return adminDao.getFeedBacks();
	}

	public List<Question> getQuestions() {
		return adminDao.getQuestions();
	}

	public Map<String, String> addQuestion(String service_id, String cleaner_uid,String user_uid,String question) {
		boolean r = adminDao.addQuestion(service_id, cleaner_uid, user_uid, question);
		Map<String,String> res = new HashMap<>();
		if(r) {
			res.put("status","success");
		}else {			
			res.put("status","failed");
		}
		return res;
	}

	public void generateReport(HttpServletResponse response, String type,String format,String time_from,String time_to) throws DocumentException, IOException {

		System.out.println(type);
		if(type.equals("services")) {
			generateServicesReport(response,format,time_from, time_to);
		}else if(type.equals( "feedback-review")) {
			generateFeedBackReviewReport(response,format, time_from, time_to);
		}else if(type.equals("cleaners")) {
			generateCleanersReport(response,format,time_from, time_to);
		}
	}

	public void generateServicesReport(HttpServletResponse response, String format,String time_from,String time_to) throws DocumentException, IOException {
		List<Report1> report = adminDao.generateServicesReport(time_from,time_to);
		  
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		  String currentDateTime = dateFormatter.format(new Date());
	      String headerKey = "Content-Disposition";
	      
		if(format.equals("pdf")) {
			response.setContentType("application/pdf");
	        String headerValue = "attachment; filename=services_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        PDFExporter.exportR1(response, report);
		}else if(format.equals("excel")) {
			response.setContentType("application/octet-stream");
			String headerValue = "attachment; filename=services_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	        ExcelExporter.exportR1(response, report);
		}
	}
	public void generateFeedBackReviewReport(HttpServletResponse response, String format,String time_from,String time_to) throws DocumentException, IOException {
		List<Report2> report2 = adminDao.generateFeedBackReport(time_from,time_to);
		List<Report3> report3 = adminDao.generateReviewReport(time_from,time_to);
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
	    String headerKey = "Content-Disposition";
		
		if(format.equals("pdf")) {
			response.setContentType("application/pdf");
	        String headerValue = "attachment; filename=feedback_review_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        PDFExporter.exportR23(response,report2,report3);
		}else if(format.equals("excel")) {
			response.setContentType("application/octet-stream");
			String headerValue = "attachment; filename=feedback_review_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	        ExcelExporter.exportR23(response, report2,report3);
		}
	}
	public void generateCleanersReport(HttpServletResponse response, String format,String time_from,String time_to) throws DocumentException, IOException {
		List<Report4> report =  adminDao.generateCleanersReport(time_from,time_to);
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		  String currentDateTime = dateFormatter.format(new Date());
	      String headerKey = "Content-Disposition";
	
		if(format.equals("pdf")) {
			response.setContentType("application/pdf");
	        String headerValue = "attachment; filename=cleaners_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        PDFExporter.exportR4(response, report);
		}else if(format.equals("excel")) {
			response.setContentType("application/octet-stream");
			String headerValue = "attachment; filename=cleaners_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	        ExcelExporter.exportR4(response, report);
		}
	}
	
}
