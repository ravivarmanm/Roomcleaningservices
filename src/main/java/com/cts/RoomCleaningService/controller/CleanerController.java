package com.cts.RoomCleaningService.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.service.CleanerService;
import com.cts.RoomCleaningService.service.FileUploadUtil;

@Controller
public class CleanerController {
	
	@Autowired
	private CleanerService cleanerService;

	@RequestMapping(value="/cleaner_login",method=RequestMethod.GET)
	public String login(ModelMap model) {
		return "cleaner_login";
	}
	
	@RequestMapping(value="/cleaner",method=RequestMethod.GET)
	public String dashboard(ModelMap model,HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId != null && authId.get("type").equals("cleaner")) {
			return "cleaner";
		}

		return "redirect:/";
	}
	
	@RequestMapping(value="/cleaner_register",method=RequestMethod.GET)
	public String register(ModelMap model) {
		model.put("cleaner",new Cleaner());
		return "cleaner_register";
	}
	
	@RequestMapping(value="/cleaner_login",method=RequestMethod.POST)
	public String validateLogin(String cleanerId,String password,ModelMap model,HttpServletRequest req) {
		
		String msg = cleanerService.validateCleaner(cleanerId, password);
		
		if(msg.equals("matched")) {
			List<Cleaner> temp = cleanerService.cleanerdao.findById(cleanerId);
			Map<String,String> authId = new HashMap<>();
			authId.put("id",cleanerId);
			authId.put("uid",temp.get(0).getUid());
			authId.put("type","cleaner");
			
			req.getSession().setAttribute("authId", authId);
			
			return "redirect:/cleaner";
		}
		
		model.put("cleanerId",cleanerId);
		model.put("password",password);
		model.put("message",msg);
		
		return "cleaner_login";
	}
//	284586f3-dca1-400a-8c6d-c41fec1900f7health
	
	@RequestMapping(value="/cleaner_register",method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("cleaner") Cleaner cleaner,BindingResult result, @RequestParam("health") MultipartFile health,@RequestParam("education") MultipartFile education,@RequestParam("resume") MultipartFile resume,String password,ModelMap model) throws IOException {
		
		model.put("password",password);

		if(result.hasErrors()) {
			return "cleaner_register";
		}
		
		String msg = "";
		
		if(cleanerService.validateId(cleaner.getCleaner_id())) {
			if(cleanerService.validatePhone(cleaner.getPhone())) {
				boolean res = cleanerService.register(cleaner,password);
				if(res) {
				    String fileName = StringUtils.cleanPath(health.getOriginalFilename());     
				    String filecode = FileUploadUtil.saveFile(cleaner.getUid()+"health.pdf",fileName, health);
				    
				    fileName = StringUtils.cleanPath(resume.getOriginalFilename());     
				    filecode = FileUploadUtil.saveFile(cleaner.getUid()+"resume.pdf",fileName, resume);

				    fileName = StringUtils.cleanPath(education.getOriginalFilename());     
				    filecode = FileUploadUtil.saveFile(cleaner.getUid()+"education.pdf",fileName, education);
					
				    System.out.println(filecode);
				    return "redirect:/cleaner_login";
				}
			}else {
				msg = "Phone Number already taken";
			}
		}else {
			msg = "Cleaner Id already present";
		}
		
		model.put("message", msg);
		
		return "cleaner_register";
	}
	
	@RequestMapping(value="/modify_my_service",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> modifyMyService(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("cleaner")) {
			return null;	
		}
		
		return cleanerService.modifyMyService(map.get("service_id"),authId.get("uid"),map.get("user_uid"),map.get("status"));
	}
	
	@RequestMapping(value="/get_my_services",method=RequestMethod.GET)
	@ResponseBody
	public List<Service> getMyServices(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("cleaner")) {
			return null;	
		}
		
		return cleanerService.getMyServices(authId.get("uid"));
	}
	
	@RequestMapping(value="/get_my_feedbacks",method=RequestMethod.GET)
	@ResponseBody
	public List<FeedBack> getMyFeedBacks(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("cleaner")) {
			return null;	
		}
		
		return cleanerService.getMyFeedBacks(authId.get("uid"));
	}
	
	@RequestMapping(value="/get_cleaner_profile",method=RequestMethod.GET)
	@ResponseBody
	public Cleaner getMyProfile(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("cleaner")) {
			return null;	
		}
		
		return cleanerService.getMyProfile(authId.get("uid"));
	}
}
