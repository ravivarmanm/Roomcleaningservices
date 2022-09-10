package com.cts.RoomCleaningService.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.RoomCleaningService.service.AppService;
import com.cts.RoomCleaningService.service.FileDownloadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
public class AppController {
	
	@Autowired
	private AppService appService;
	
	@RequestMapping("")
	public String home(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null) {
			return "home";
		}else if(authId.get("type").equals("user")) {
			return "redirect:/user";
		}else if(authId.get("type").equals("admin")) {
			return "redirect:/admin";
		}else if(authId.get("type").equals("cleaner")) {
			return "redirect:/cleaner";
		}
		
		return "home";
	}
	
	@RequestMapping("/log_out")
	public String logout(HttpSession httpSession) {
		
		httpSession.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/downloadFile/{fileCode}")
	 public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();
         
        Resource resource = null;
        String ext = "pdf";
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
            
            ext = resource.getFile().getName();
            int lastIndexOf = ext.lastIndexOf(".");
            if (lastIndexOf == -1) {
                ext =  "pdf"; // empty extension
            }
            ext =  ext.substring(lastIndexOf);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
         
        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        
        String contentType = "application/pdf";
        String headerValue = "inline; filename=\"" + resource.getFilename() + "\"";
         
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);       
	    }
	
		
	@RequestMapping(value="/mark_read_service",method=RequestMethod.POST)
	@ResponseBody
	public String markReadService(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null) {
			return "";	
		}
		
		appService.markReadService(map.get("column"), map.get("service_id"));
		
		return "";
	}
	
	@RequestMapping(value="/mark_read_cleaner",method=RequestMethod.POST)
	@ResponseBody
	public String markReadCleaner(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null) {
			return "";	
		}
		
		appService.markReadCleaner(map.get("column"), map.get("cleaner_id"));
		
		return "";
	}
	
	@RequestMapping(value="/mark_read_feedback",method=RequestMethod.POST)
	@ResponseBody
	public String markReadFeedBack(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null) {
			return "";
		}
		
		appService.markReadFeedBack(map.get("column"), map.get("service_id"));
		
		return "";
	}
	
	@RequestMapping(value="/mark_read_question",method=RequestMethod.POST)
	@ResponseBody
	public String markReadQuestion(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null) {
			return "";
		}
		
		appService.markReadQuestion(map.get("column"), map.get("question_id"));
		
		return "";
	}
		
	@RequestMapping(value="/mark_read_ticket",method=RequestMethod.POST)
	@ResponseBody
	public String markReadTicket(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null) {
			return "";
		}
		
		appService.markReadTicket(map.get("column"), map.get("ticket_id"));
		
		return "";
	}
}
