package ru.saubulprojects.currencyapp.currencyrateclient.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.saubulprojects.currencyapp.currencyrateclient.client.RateClient;
import ru.saubulprojects.currencyapp.currencyrateclient.model.CurrencyRate;
import ru.saubulprojects.currencyapp.currencyrateclient.model.RateType;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyRateEndpointService {

	private final Map<String, RateClient> clients;
	
	public CurrencyRate getCurrencyRate(RateType rateType, String currency, LocalDate date) {
		log.info("getCurrencyRate. rateType:{}, currency:{}, date:{}", rateType, currency, date);
		RateClient client = clients.get(rateType.getServiceName());
	    return client.getCurrencyRate(currency, date);
	}

}
