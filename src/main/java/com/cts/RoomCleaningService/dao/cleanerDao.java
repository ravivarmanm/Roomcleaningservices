package com.cts.RoomCleaningService.dao;

import java.util.List;

import com.cts.RoomCleaningService.model.Cleaner;

public interface cleanerDao {
	public List<Cleaner> findById(String id);
	public boolean add(Cleaner cleaner,String password);
	public List<Cleaner> findByPhone(String phone);
}
