package ru.saubulproject.currencyapp.moexrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MoexRateApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MoexRateApplication.class, args);
	}
	
}
