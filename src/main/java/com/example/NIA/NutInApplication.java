package com.example.NIA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
@EnableFeignClients
public class NutInApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutInApplication.class,
				args);
	}

}
