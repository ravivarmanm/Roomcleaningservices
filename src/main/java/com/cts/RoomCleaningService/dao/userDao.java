package com.cts.RoomCleaningService.dao;

import java.util.List;

import com.cts.RoomCleaningService.model.User;

public interface userDao {
	public List<User> findById(String id);
	public List<User> findByEmail(String email);
	public boolean add(User user,String password);
}
