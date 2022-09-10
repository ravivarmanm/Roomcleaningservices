package com.cts.RoomCleaningService.model;

public class Ticket {
	
	public int s_no;
	public String ticket_id;
	public String user_uid;
	public String issue;
	public String description;
	public String created;
	public String modified;
	public String status;
	public String solution;
	public String user_id;
	
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

	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}
	public String getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
