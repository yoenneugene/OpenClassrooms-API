package com.openclassrooms.api;

import com.openclassrooms.api.Service.Jsonfilereaderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext ;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.openclassrooms.*"})
public class ApiApplication implements CommandLineRunner {
	@Autowired
	private Jsonfilereaderservice jsonFileReaderService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
 		jsonFileReaderService.readAndPrintJsonFile();
	}


}

