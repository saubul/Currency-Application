package ru.saubulprojects.currencyapp.currencyrateclient.client.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.saubulprojects.currencyapp.currencyrateclient.client.HttpClient;
import ru.saubulprojects.currencyapp.currencyrateclient.model.CurrencyRate;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpClientRest implements HttpClient{

	private final RestTemplate restTemplate;
	
	@Override
	public CurrencyRate performRequest(String url) {
		log.info("Http request, url: {}", url);
		ResponseEntity<CurrencyRate> currencyRateEntity = restTemplate.getForEntity(url, CurrencyRate.class);
		return currencyRateEntity.getBody();
	}

}
