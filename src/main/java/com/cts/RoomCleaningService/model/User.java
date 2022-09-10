package com.cts.RoomCleaningService.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class User {
	int s_no;
	String uid;
	String created;
	String modified;
	
	@NotBlank
	String question1;
	@NotBlank
	String question2;
	@NotBlank
	String question3;
	
	@NotBlank
	@Size(min = 5, max = 100,message = "Length of the user id must be between 5 and 100")
	String user_id;
	
	@NotBlank
    @Size(min = 1, max = 100,message = "Length of the first name must be between 1 and 100")
	String first;
	
	@NotBlank
    @Size(max = 100,message = "Length of the last name must be less than 100 letters")
	String last;
		
	@NotBlank
	String dob;
	
	@NotBlank
	String phone;
	
	@NotBlank
	@Email(message = "Please enter a valid e-mail address")  
	String mail;
	
	@NotBlank
	String gender;
	
	
		
	public String getQuestion1() {
		return question1;
	}
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}
	public String getQuestion2() {
		return question2;
	}
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}
	public String getQuestion3() {
		return question3;
	}
	public void setQuestion3(String question3) {
		this.question3 = question3;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
