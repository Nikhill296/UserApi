package com.UserApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class WaterAppUserApiApplication implements CommandLineRunner{
	


	public static void main(String[] args) {
//		System.out.println("Helloooooooooooooooooooooooo");
		log.info("In main Method ..........");
		
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
