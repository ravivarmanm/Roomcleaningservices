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
import org.springframework.web.bind.annotation.RestController;

import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Question;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.model.Ticket;
import com.cts.RoomCleaningService.service.AdminService;
import com.lowagie.text.DocumentException;

@RestController
public class RestAdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/get/cleaners",method=RequestMethod.GET)
	@ResponseBody
	public List<Cleaner> getAllCleaners() {
		
		return adminService.getCleaners();
	}
	
	@RequestMapping(value="/get/services",method=RequestMethod.GET)
	@ResponseBody
	public List<Service> getAllServices() {
		return adminService.getServices();
	}
	
	@RequestMapping(value="/get/tickets",method=RequestMethod.GET)
	@ResponseBody
	public List<Ticket> getAllTickets() {
		return adminService.getTickets();
	}
	
	
	
	@RequestMapping(value="/get/reviews",method=RequestMethod.GET)
	@ResponseBody
	public List<FeedBack> getFeedBacks() {
		return adminService.getFeedBacks();
	}
	
	
	@RequestMapping(value="/get/feedbacks",method=RequestMethod.GET)
	@ResponseBody
	public List<Question> getQuestions() {
		return adminService.getQuestions();
	}
	
}
