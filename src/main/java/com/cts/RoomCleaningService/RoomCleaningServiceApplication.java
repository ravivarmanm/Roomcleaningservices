package com.cts.RoomCleaningService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RoomCleaningServiceApplication {

	public static void main(String[] args) {
    	SpringApplication.run(RoomCleaningServiceApplication.class, args);
	}

}
