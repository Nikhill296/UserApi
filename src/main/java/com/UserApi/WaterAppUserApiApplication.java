package com.UserApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.UserApi.Entities.UserDetails;
import com.UserApi.Repository.UserRepo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class WaterAppUserApiApplication implements CommandLineRunner{
	
    private static final Logger logger = LogManager.getLogger(WaterAppUserApiApplication.class);


	public static void main(String[] args) {
//		System.out.println("Helloooooooooooooooooooooooo");
		logger.info("In main Method ..........");
		
		SpringApplication.run(WaterAppUserApiApplication.class, args);
		
	}
     
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}

	@Override
	public void run(String... args) throws Exception {

//		repo.save(new UserDetails(1,"prince"));
//		repo.save(new UserDetails(2, "nik"));
		System.out.println("\n\n \t\t ############################ welcome to the API ########################### \n\n");
		
//		repo.deleteAll();
		
		
	}
}
