package ru.saubulprojects.currencyapp.currencyrateclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "moex-rate-client")
public class MoexRateClientConfig {

	String url;
	
}
