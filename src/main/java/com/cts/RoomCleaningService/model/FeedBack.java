package com.cts.RoomCleaningService.model;

public class FeedBack {
	
	public int s_no;
	public int rating;
	public String service_id;
	public String user_uid;
	public String user_id;
	public String cleaner_uid;
	public String cleaner_id;
	public String address;
	public String pincode;
	public String time_from;
	public String time_to;
	public String status;
	public String created;
	public String modified;
	public String feedback;
	
	public boolean user_read;
	public boolean cleaner_read;
	public boolean admin_read;
	
	public boolean isUser_read() {
		return user_read;
	}


	public void setUser_read(boolean user_read) {
		this.user_read = user_read;
	}


	public boolean isCleaner_read() {
		return cleaner_read;
	}


	public void setCleaner_read(boolean cleaner_read) {
		this.cleaner_read = cleaner_read;
	}


	public boolean isAdmin_read() {
		return admin_read;
	}


	public void setAdmin_read(boolean admin_read) {
		this.admin_read = admin_read;
	}

	
	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getFeedback() {
		return feedback;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	public String getCleaner_uid() {
		return cleaner_uid;
	}


	public void setCleaner_uid(String cleaner_uid) {
		this.cleaner_uid = cleaner_uid;
	}


	public int getS_no() {
		return s_no;
	}
	
	
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCleaner_id() {
		return cleaner_id;
	}
	public void setCleaner_id(String cleaner_id) {
		this.cleaner_id = cleaner_id;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getTime_from() {
		return time_from;
	}
	public void setTime_from(String time_from) {
		this.time_from = time_from;
	}
	public String getTime_to() {
		return time_to;
	}
	public void setTime_to(String time_to) {
		this.time_to = time_to;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
}
