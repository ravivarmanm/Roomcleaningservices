package com.cts.RoomCleaningService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import com.cts.RoomCleaningService.dao.userDao;
import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Question;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.model.Ticket;
import com.cts.RoomCleaningService.model.User;
import com.cts.RoomCleaningService.paypal.Order;
import com.cts.RoomCleaningService.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/user_login",method=RequestMethod.GET)
	public String login(ModelMap model) {
		return "user_login";
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String dashboard(ModelMap model,HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId != null && authId.get("type").equals("user")) {
			return "user";	
		}

		return "redirect:/";
	}
	
	@RequestMapping(value="/user_help",method=RequestMethod.GET)
	public String userHelp(ModelMap model,HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId != null && authId.get("type").equals("user")) {
			model.put("ticket", new Ticket());
			return "user_help";	
		}

		return "redirect:/";
	}
	
	@RequestMapping(value="/user_cancel_service",method=RequestMethod.GET)
	public String useCancelService(ModelMap model,String service_id,String user_uid,HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId != null && authId.get("type").equals("user")) {
			int res = userService.cancelService(service_id,user_uid);
		
		}

		return "redirect:/";
	}

	
	@RequestMapping(value="/user_payment",method=RequestMethod.GET)
	public String userPayment(ModelMap model,String service_id,String user_uid,HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		if(authId != null && authId.get("type").equals("user")) {
			
			Service service = userService.getServiceById(service_id);
			Order order = new Order();
			order.setService_id(service_id);
			order.setUser_uid(user_uid);
			order.setPrice(Integer.parseInt(service.getRoom_count()) * 5);
			order.setCurrency("USD");
			order.setDescription("Payment for service that registered at "+service.getCreated()+"("+service.getAddress()+","+service.getPincode()+").Time slot is "+service.getTime_from()+" - "+service.getTime_to());
			order.setIntent("sale");
			order.setMethod("paypal");
			
			model.put("order",order);
			model.put("service", service);
			model.put("amount",order.getPrice());
			
			return "user_payment";	
		}

		return "redirect:/";
	}

//	@RequestMapping(value="/user_payment",method=RequestMethod.POST)
//	public String userPaid(ModelMap model,String service_id1, String user_uid1,HttpSession httpSession) {
//		@SuppressWarnings("unchecked")
//		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
//		if(authId == null || !authId.get("type").equals("user")) {
//			return "redirect:/";	
//		}
//
//		boolean res = userService.confirmBookedService(service_id1,user_uid1);
//		if(res) {
//			return "redirect:/user";
//		}
//		model.put("service_id",service_id1);
//		model.put("user_uid", user_uid1);
//		model.put("msg", "Booking service Failed, Please Try again");
//		
//		return "user_payment";
//	}

	
	@RequestMapping(value="/user_help",method=RequestMethod.POST)
	public String userHelpSubmit(@Valid @ModelAttribute("ticket") Ticket ticket,BindingResult result,ModelMap model,HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId != null && authId.get("type").equals("user")) {
			
			boolean res = userService.addTicket(ticket,authId.get("uid"));
			
			if(res) {				
				return "redirect:/user";
			}else {
				return "user_help";
			}
			
		}

		return "redirect:/";
	}

	
	@RequestMapping(value="/user_register",method=RequestMethod.GET)
	public String register(ModelMap model) {
		model.put("user",new User());
		return "user_register";
	}
	
	@RequestMapping(value="/service_booking",method=RequestMethod.GET)
	public String service_booking(ModelMap model,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("user")) {
			return "redirect:/";
		}
		
		model.put("booking",new Service());
		return "service_booking";
	}
	
	@RequestMapping(value="/modify_user_review",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> modifyMyService(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("user")) {
			return null;	
		}
		
		return userService.modifyReview(map.get("service_id"),map.get("user_uid"),map.get("rating"),map.get("feedback"));
	}
	
	@RequestMapping(value="/modify_user_question",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> modifyMyQuestion(@RequestBody Map<String, String> map,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("user")) {
			return null;	
		}
		
		return userService.modifyQuestion(map.get("question_id"),map.get("user_uid"),map.get("answer"));
	}
	
	@RequestMapping(value="/user_login",method=RequestMethod.POST)
	public String validateLogin(String userId,String password,ModelMap model,HttpServletRequest req) {
		
		String msg = userService.validateUser(userId, password);
		
		if(msg.equals("matched")) {
			
			List<User> users = userService.userdao.findById(userId);
			Map<String,String> authId = new HashMap<>();
			authId.put("id",userId);
			authId.put("uid",users.get(0).getUid());
			authId.put("type","user");
			
			req.getSession().setAttribute("authId", authId);
			
			return "redirect:/user";
		}
		
		model.put("userId",userId);
		model.put("password",password);
		model.put("message",msg);
		
		return "user_login";
	}
	
	@RequestMapping(value="/user_register",method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user,BindingResult result,String password,ModelMap model) {
		
		model.put("password",password);

		if(result.hasErrors()) {
			return "user_register";
		}
		
		String msg = "";
		
		if(userService.validateId(user.getUser_id())) {
			if(userService.validateMail(user.getMail())) {
				boolean res = userService.register(user,password);
				if(res) {
					return "redirect:/user_login";
				}
			}else {
				msg = "Email id already taken";
			}
		}else {
			msg = "User Id already present";
		}
		
		model.put("message", msg);
		
		return "user_register";
	}
	
	@RequestMapping(value = "/service_booking",method=RequestMethod.POST)
	public String Postservice_booking(@ModelAttribute("booking") @Valid Service bookService,HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("user")) {
			return "redirect:/";
		}
		
		bookService.setUser_uid(authId.get("uid"));
		
		boolean res = userService.bookService(bookService);
		
		if(res) {
			return "redirect:/user";
		}
		
		return "";
	}
	
	
	@RequestMapping(value="/get_user_services",method=RequestMethod.GET)
	@ResponseBody
	public List<Service> getAllServices(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("user")) {
			return null;	
		}
		
		return userService.getServices(authId.get("uid"));
	}
	
	@RequestMapping(value="/get_user_tickets",method=RequestMethod.GET)
	@ResponseBody
	public List<Ticket> getTickets(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("user")) {
			return null;	
		}
		
		return userService.getTickets(authId.get("uid"));
	}
	
	@RequestMapping(value="/get_user_feedbacks",method=RequestMethod.GET)
	@ResponseBody
	public List<FeedBack> getFeedBacks(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("user")) {
			return null;	
		}
		
		return userService.getFeedBacks(authId.get("uid"));
	}
	
	@RequestMapping(value="/get_user_questions",method=RequestMethod.GET)
	@ResponseBody
	public List<Question> getQuestions(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("user")) {
			return null;	
		}
		
		return userService.getQuestions(authId.get("uid"));
	}
	
	@RequestMapping(value="/get_user_profile",method=RequestMethod.GET)
	@ResponseBody
	public User getMyProfile(HttpSession httpSession) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
		if(authId == null || !authId.get("type").equals("user")) {
			return null;	
		}
		
		return userService.getMyProfile(authId.get("uid"));
	}
	
	@GetMapping("/forget_password")
	public String forget_password(HttpSession httpSession) {
//		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
//		
//
//		User findByIdWithQuestion = userService.findByemailWithQuestion(authId.get("uid"));
//		System.out.println(findByIdWithQuestion.toString());
		return "forget_password";
	}
	@PostMapping("/password")
	public String password(String questions,String answer,String userId,HttpServletRequest req,Model model) {
		System.out.println(questions+""+answer+""+userId);
		if(!userService. validateId(userId)) {
//			Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
			User findByIdWithQuestion = userService.findByIdWithQuestion(userId);
			if(validatequestionswithanswers(findByIdWithQuestion,questions,answer)) {	
			model.addAttribute("user",findByIdWithQuestion);
			req.getSession().setAttribute("forgotuserid", findByIdWithQuestion.getUser_id());
				return "password";
			}
			model.addAttribute("message","secret question answer wrong");
			return "forget_password";
			
		}
		model.addAttribute("message","UserId not Found");
		return "forget_password";
	}
	@PostMapping("/updatepassword")
	public String updatepassword(String password,HttpSession session,Model model) {
		String userId = (String) session.getAttribute("forgotuserid");
		boolean status = userService.updatePassword(password, userId);
		System.out.println("userid "+userId);
		System.out.println("status "+status);
		model.addAttribute("status", status);
		if(status) {			
			return "redirect:/user_login";
		}
		return "password";
	}
//	@PostMapping("/password")
//	public String password(String questions,String answer,String email,HttpSession httpSession,Model model) {
//		System.out.println(questions+""+answer+""+email);
//		if(!userService.validateMail(email)) {
//			Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
//			User findByIdWithQuestion = userService.findByemailWithQuestion(email);
//			if(validatequestionswithanswers(findByIdWithQuestion,questions,answer)) {			
//				return "password";
//			}
//			model.addAttribute("message","secret question answer wrong");
//			return "forget_password";
//			
//		}
//		model.addAttribute("message","Email not found");
//		return "forget_password";
//	}

private boolean validatequestionswithanswers(User user, String questions,String answer) {
	if(questions.equals("question1")) {
		
		return user.getQuestion1().equals(answer) ? true : false;
		
	} else if(questions.equals("question2")) {
		return user.getQuestion2().equals(answer) ? true : false;
	}
	else {
		return user.getQuestion3().equals(answer) ? true : false;
	}
	
}
@GetMapping("/forget_userid")
public String forget_userid() {
	return "forget_userid";
}
@PostMapping("/forget_userid")
public String post_forget_userid(String questions,String email,String answer,HttpSession httpSession,Model model) {
	if(!userService.validateMail(email)) {
		@SuppressWarnings({ "unused", "unchecked" })
		Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		User findByIdWithQuestion = userService.findByemailWithQuestion(email);
	if(validatequestionswithanswers(findByIdWithQuestion,questions,answer)) {	
		 model.addAttribute("userid", "Your userId is "+findByIdWithQuestion.getUser_id());
		 return "userIdView";
		}
		model.addAttribute("message","secret question answer wrong");
		return "forget_userid";
		
	}
	model.addAttribute("message","Email not found");
	return "forget_userid";
}

	
}
