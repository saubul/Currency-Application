package ru.saubulprojects.currencyapp.currencyrateclient.client.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.saubulprojects.currencyapp.currencyrateclient.client.HttpClient;
import ru.saubulprojects.currencyapp.currencyrateclient.client.RateClient;
import ru.saubulprojects.currencyapp.currencyrateclient.config.MoexRateClientConfig;
import ru.saubulprojects.currencyapp.currencyrateclient.model.CurrencyRate;

@Service("moex")
@RequiredArgsConstructor
@Slf4j
public class MoexRateClient implements RateClient{
	
	private final static String DATE_FORMAT = "yyyy-MM-dd";
	private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	private final MoexRateClientConfig moexConfig;
	private final HttpClient httpClient;
	
	@Override
	public CurrencyRate getCurrencyRate(String currency, LocalDate date) {
		log.info("MoexRateClient: getCurrencyRate. Currency: {}, date: {}", currency, date);
		String url = String.format("%s/%s/%s", moexConfig.getUrl(), currency, DATE_FORMATTER.format(date));
		return httpClient.performRequest(url);
	}

}
