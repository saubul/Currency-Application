package ru.saubulproject.currencyapp.moexrate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "moex")
public class MoexConfig {
	
	private String url;
	
}
