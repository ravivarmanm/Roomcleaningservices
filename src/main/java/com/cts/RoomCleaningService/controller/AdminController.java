package com.cts.RoomCleaningService.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Question;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.model.Ticket;
import com.cts.RoomCleaningService.service.AdminService;
import com.lowagie.text.DocumentException;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String dashboard(ModelMap model,HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId != null && authId.get("type").equals("admin")) {
			return "admin";	
		}

		return "redirect:/";
	}

	
	@RequestMapping(value="/admin_login",method=RequestMethod.GET)
	public String login(ModelMap model) {
		return "admin_login";
	}
	
	@RequestMapping(value="/admin_login",method=RequestMethod.POST)
	public String validateLogin(String adminId,String password,ModelMap model,HttpServletRequest req) {
		
		String msg = adminService.validate(adminId, password);
		
		if(msg.equals("matched")) {
			
			Map<String,String> authId = new HashMap<>();
			authId.put("id",adminId);
			authId.put("type","admin");
			
			req.getSession().setAttribute("authId", authId);
	
			return "redirect:/admin";
		}
		
		model.put("adminId",adminId);
		model.put("password",password);
		model.put("message",msg);
		
		return "admin_login";
	}
	
	@RequestMapping(value="/get_cleaners",method=RequestMethod.GET)
	@ResponseBody
	public List<Cleaner> getAllCleaners(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return null;	
		}
		
		return adminService.getCleaners();
	}
	
	@RequestMapping(value="/get_services",method=RequestMethod.GET)
	@ResponseBody
	public List<Service> getAllServices(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return null;	
		}
		
		return adminService.getServices();
	}
	
	@RequestMapping(value="/get_tickets",method=RequestMethod.GET)
	@ResponseBody
	public List<Ticket> getAllTickets(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return null;	
		}
		
		return adminService.getTickets();
	}
	
	@RequestMapping(value="/modify_cleaner",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> modifyCleaner(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return null;	
		}
		
		return adminService.modifyCleaner(map.get("cleaner_id"),map.get("profile_status"));
	}
	
	@RequestMapping(value="/modify_ticket",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> modifyTicket(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return null;	
		}
		
		return adminService.modifyTicket(map.get("ticket_id"),map.get("solution"));
	}
	
	@RequestMapping(value="/modify_service",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> modifyService(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return null;	
		}
		
		return adminService.modifyService(map.get("service_id"),map.get("status"),map.get("cleaner_uid"));
	}
	
	@RequestMapping(value="/get_feedbacks",method=RequestMethod.GET)
	@ResponseBody
	public List<FeedBack> getFeedBacks(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return null;	
			
		}
		
		return adminService.getFeedBacks();
	}
	
	@RequestMapping(value="/add_question",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> AddQuestion(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return null;	
		}
		
		return adminService.addQuestion(map.get("service_id"),map.get("cleaner_uid"),map.get("user_uid"),map.get("question"));
	}
	
	@RequestMapping(value="/get_questions",method=RequestMethod.GET)
	@ResponseBody
	public List<Question> getQuestions(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return null;	
		}
		
		return adminService.getQuestions();
	}
	
	@RequestMapping(value="/generate_report",method=RequestMethod.POST)
	public void generateReport(@RequestParam String format,@RequestParam String type,@RequestParam String report_time_from,@RequestParam String report_time_to,HttpSession httpSession,HttpServletResponse response) throws DocumentException, IOException {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("admin")) {
			return;	
		}
		
		adminService.generateReport(response,type,format,report_time_from,report_time_to);
	}
}
