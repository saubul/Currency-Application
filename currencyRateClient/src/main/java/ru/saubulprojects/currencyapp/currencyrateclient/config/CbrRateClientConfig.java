package ru.saubulprojects.currencyapp.currencyrateclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "cbr-rate-client")
public class CbrRateClientConfig {
	String url;
}
