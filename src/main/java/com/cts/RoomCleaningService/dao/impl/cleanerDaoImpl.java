package com.cts.RoomCleaningService.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cts.RoomCleaningService.dao.cleanerDao;
import com.cts.RoomCleaningService.mapper.CleanerRowMapper;
import com.cts.RoomCleaningService.mapper.FeedBackRowMapper;
import com.cts.RoomCleaningService.mapper.ServiceRowMapper;
import com.cts.RoomCleaningService.model.Cleaner;
import com.cts.RoomCleaningService.model.FeedBack;
import com.cts.RoomCleaningService.model.Service;
import com.cts.RoomCleaningService.service.DBService;

@Component
public class cleanerDaoImpl implements cleanerDao{
	@Autowired
	public DBService db;
	
	public final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public boolean add(Cleaner cleaner,String password) {
		// TODO Auto-generated method stub
		String uid = UUID.randomUUID().toString();
        password = bCryptPasswordEncoder.encode(password);
        cleaner.setUid(uid);
		String sql = "INSERT INTO `cleaners` (`uid`, `cleaner_id`, `password`, `first`, `last`, `dob`, `gender`, `phone`) VALUES (?, ?, ?, ?, ?,?, ?,?)";
		int row = db.jdbcTemplate.update(sql,uid,cleaner.getCleaner_id(),password,cleaner.getFirst(),cleaner.getLast(),cleaner.getDob(),cleaner.getGender(),cleaner.getPhone());
		if(row > 0)
			return true;
		return false;
	}

	@Override
	public List<Cleaner> findById(String id) {
		// TODO Auto-generated method stub
		String sql = "select user_read,cleaner_read,admin_read,s_no,uid,cleaner_id,first,last,dob,gender,phone,profile_status,service_status,created,modified from cleaners where cleaner_id='"+id+"'";
		return db.jdbcTemplate.query(sql,new CleanerRowMapper());
	}

	@Override
	public List<Cleaner> findByPhone(String phone) {
		String sql = "select user_read,cleaner_read,admin_read,s_no,uid,cleaner_id,first,last,dob,gender,phone,profile_status,service_status,created,modified from cleaners where phone ='"+phone+"'";
		return db.jdbcTemplate.query(sql,new CleanerRowMapper());
	}
	
	public boolean matchPassword(String id,String pass) {
		
		String sql = "select password from cleaners where cleaner_id='"+id+"'";
		String password = db.jdbcTemplate.queryForObject(sql,String.class);
		return bCryptPasswordEncoder.matches(pass, password);
	}
	
	public List<Service> getMyServices(String cleaner_id) {
		String sql = "select s.user_read,s.cleaner_read,s.admin_read,s.s_no,u.user_id, s.service_id, s.user_uid,c.cleaner_id, s.cleaner_uid, s.room_count, s.address, s.pincode, s.time_from, s.time_to, s.contact_number, s.status, s.created, s.modified FROM services s,users u,cleaners c where (s.status = 'booked' or s.status = 'completed') and s.user_uid = u.uid and s.cleaner_uid = c.uid and s.cleaner_uid = '"+cleaner_id+"'";
		
		sql += " order by modified";
		return db.jdbcTemplate.query(sql,new ServiceRowMapper());
	}
	
	public List<FeedBack> getFeedBacks(String cleaner_id) {
		String sql = "select f.user_read,f.cleaner_read,f.admin_read,f.s_no,u.user_id, f.service_id,f.feedback,f.rating,f.user_uid,c.cleaner_id, f.cleaner_uid, s.address, s.pincode, s.time_from, s.time_to, f.status, f.created, f.modified FROM feedbacks f,services s,users u,cleaners c where f.service_id = s.service_id and f.user_uid = u.uid and f.cleaner_uid = c.uid and f.cleaner_uid = '"+cleaner_id+"'";
		
		sql += " order by modified desc";
		return db.jdbcTemplate.query(sql,new FeedBackRowMapper());
	}
	
	public boolean modifyMyService(String service_id,String cleaner_id,String user_uid,String status) {
		
		String sql = "update services set user_read=false,cleaner_read=false,admin_read=false,status = ?,modified=CURRENT_TIMESTAMP where service_id=? and cleaner_uid = ?";
		int	row = db.jdbcTemplate.update(sql,status,service_id,cleaner_id);
		
		if(status.equals("completed")) {
			db.jdbcTemplate.update("INSERT INTO `feedbacks` (`service_id`, `cleaner_uid`,user_uid,`rating`, `feedback`) VALUES (?,?,?,'0','Not Given')",service_id,cleaner_id,user_uid);
		}
		
		if(row > 0) {
			return true;
		}
		return false;
	}

	public List<Cleaner> getMyProfile(String uid) {
		String sql = "select user_read,cleaner_read,admin_read,s_no,uid,cleaner_id,first,last,dob,gender,phone,profile_status,service_status,created,modified from cleaners where uid='"+uid+"'";
		return db.jdbcTemplate.query(sql,new CleanerRowMapper());
	}
}
