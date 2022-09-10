package com.cts.RoomCleaningService.report;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Report4 {
	
	int s_no;
	String uid;
	String created;
	String modified;
	String service_status;
	String profile_status;
	
	@NotBlank
	@Size(min = 5, max = 100,message = "Length of the user id must be between 5 and 100")
	String cleaner_id;
	
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
	String gender;

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

	public String getService_status() {
		return service_status;
	}

	public void setService_status(String service_status) {
		this.service_status = service_status;
	}

	public String getProfile_status() {
		return profile_status;
	}

	public void setProfile_status(String profile_status) {
		this.profile_status = profile_status;
	}

	public String getCleaner_id() {
		return cleaner_id;
	}

	public void setCleaner_id(String cleaner_id) {
		this.cleaner_id = cleaner_id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
