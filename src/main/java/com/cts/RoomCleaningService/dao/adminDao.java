package com.cts.RoomCleaningService.dao;

import java.util.List;

import com.cts.RoomCleaningService.model.Admin;

public interface adminDao {
	public List<Admin> findById(String id);
}
