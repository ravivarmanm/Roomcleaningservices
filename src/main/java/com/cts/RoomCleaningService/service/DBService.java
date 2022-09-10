package com.cts.RoomCleaningService.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DBService {
	public JdbcTemplate jdbcTemplate;
	public DBService() {
		DriverManagerDataSource datasource = new DriverManagerDataSource("jdbc:mysql://localhost/rcs","root","");
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
}
