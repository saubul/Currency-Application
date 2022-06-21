package ru.saubulprojects.currencyapp.cbrrate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "cbr")
public class CbrConfig {
	
	private String url;
	
}
