package com.cts.RoomCleaningService.paypal;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.service.UserService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaypalController {

	@Autowired
	PaypalService service;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") Order order) {
		try {
			String success = "http://localhost:8080/pay/success/"+order.getService_id()+"/"+order.getUser_uid();
			String cancel = "http://localhost:8080/pay/cancel/"+ order.getService_id()+"/"+order.getUser_uid();
			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(),cancel,
					success);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	 @GetMapping(value = "pay/cancel/{service_id}/{user_uid}")
	 public ModelAndView cancelPay(ModelMap model,@PathVariable("service_id") String service_id,@PathVariable("user_uid") String user_uid) {
			Service service = userService.getServiceById(service_id);
	     	double amt = Integer.parseInt(service.getRoom_count()) * 5;
	     	model.put("service_id", service.service_id);
	     	model.put("user_id", service.user_id);
	     	model.put("created", service.created);
	     	model.put("cleaner_id", service.cleaner_id);
	     	model.put("time_from", service.time_from);
	     	model.put("time_to", service.time_to);
	     	model.put("room_count", service.room_count);
	     	model.put("address", service.address);
	     	model.put("pincode", service.pincode);
	     	model.put("amount",amt);
	     	return new ModelAndView("redirect:/payment_canceled",model);
	    }

	 
	 
	    @GetMapping(value = "pay/success/{service_id}/{user_uid}")
	    public ModelAndView successPay(ModelMap model,@PathVariable("service_id") String service_id,@PathVariable("user_uid") String user_uid,@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	            	Service service = userService.getServiceById(service_id);
	            	double amt = Integer.parseInt(service.getRoom_count()) * 5;
	            	model.put("service_id", service.service_id);
	            	model.put("user_id", service.user_id);
	            	model.put("created", service.created);
	            	model.put("cleaner_id", service.cleaner_id);
	            	model.put("time_from", service.time_from);
	            	model.put("time_to", service.time_to);
	            	model.put("room_count", service.room_count);
	            	model.put("address", service.address);
	            	model.put("pincode", service.pincode);
	            	model.put("amount",amt);
	            	boolean res = userService.confirmBookedService(service_id,user_uid,paymentId,payerId,amt);
	                if(res) 
	                		return new ModelAndView("redirect:/payment_success",model);
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return new ModelAndView("redirect:/");
	    }
	    @GetMapping("/payment_success")
	    public String successPage(@RequestParam Map<String,String> map,ModelMap model,HttpSession httpSession) {
	    	
	    	@SuppressWarnings("unchecked")
			Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
	    	
	    	if(authId == null || !authId.get("type").equals("user")) {
				return "redirect:/";
			}
	    	
	    	model.put("service_id", map.get("service_id"));
        	model.put("user_id", map.get("user_id"));
        	model.put("created", map.get("created"));
        	model.put("cleaner_id", map.get("cleaner_id"));
        	model.put("time_from", map.get("time_from"));
        	model.put("time_to", map.get("time_to"));
        	model.put("room_count", map.get("room_count"));
        	model.put("address", map.get("address"));
        	model.put("pincode", map.get("pincode"));
        	model.put("amount",map.get("amount"));
        
	    	
	    	return "success_payment";
	    }
	    
	    @GetMapping("/payment_canceled")
	    public String canceledPage(@RequestParam Map<String,String> map,ModelMap model,HttpSession httpSession) {
	    	
	    	@SuppressWarnings("unchecked")
			Map<String,String> authId = (HashMap<String,String>) httpSession.getAttribute("authId");
		
	    	
	    	if(authId == null || !authId.get("type").equals("user")) {
				return "redirect:/";
			}
	    	
	    	model.put("service_id", map.get("service_id"));
        	model.put("user_id", map.get("user_id"));
        	model.put("created", map.get("created"));
        	model.put("cleaner_id", map.get("cleaner_id"));
        	model.put("time_from", map.get("time_from"));
        	model.put("time_to", map.get("time_to"));
        	model.put("room_count", map.get("room_count"));
        	model.put("address", map.get("address"));
        	model.put("pincode", map.get("pincode"));
        	model.put("amount",map.get("amount"));
        
	    	
	    	return "canceled_payment";
	    }
}