package ru.saubulprojects.currencyapp.currencyrateclient.client.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.saubulprojects.currencyapp.currencyrateclient.client.HttpClient;
import ru.saubulprojects.currencyapp.currencyrateclient.client.RateClient;
import ru.saubulprojects.currencyapp.currencyrateclient.config.CbrRateClientConfig;
import ru.saubulprojects.currencyapp.currencyrateclient.model.CurrencyRate;

@Service("cbr")
@RequiredArgsConstructor
@Slf4j
public class CbrRateClient implements RateClient{
	
	private final static String DATE_FORMAT = "dd-MM-yyyy";
	private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	private final HttpClient httpClient;
	private final CbrRateClientConfig config;
	
	
	@Override
	public CurrencyRate getCurrencyRate(String currency, LocalDate date) {
		log.info("getCurrencyRate currency:{}, date:{}", currency, date);
		String urlWithParams = String.format("%s/%s/%s", config.getUrl(), currency, DATE_FORMATTER.format(date));
		return httpClient.performRequest(urlWithParams);
	}

}
