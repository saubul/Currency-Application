package ru.saubulprojects.currencyapp.currencytelbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import ru.saubulprojects.currencyapp.currencytelbot.entity.CurrencyRate;

@Service
@RequiredArgsConstructor
public class CurrencyService {
	
	private final RestTemplate restTemplate;
	
	@Value("${source.endpoint}")
	private String url;
	
	public String getCurrency(String source, String currency, String date) {
		return restTemplate.getForObject(String.join("/", url, source, currency, date), CurrencyRate.class).getValue();
	}
	
}
