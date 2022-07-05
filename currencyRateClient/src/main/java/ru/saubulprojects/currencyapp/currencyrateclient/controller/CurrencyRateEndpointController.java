package ru.saubulprojects.currencyapp.currencyrateclient.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.saubulprojects.currencyapp.currencyrateclient.model.CurrencyRate;
import ru.saubulprojects.currencyapp.currencyrateclient.model.RateType;
import ru.saubulprojects.currencyapp.currencyrateclient.service.CurrencyRateEndpointService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "${app.rest.api.prefix}/v1")
public class CurrencyRateEndpointController {
	
	private final CurrencyRateEndpointService currencyRateEndpointService;
	
	@GetMapping("/currencyRate/{type}/{currency}/{date}")
	public CurrencyRate getCurrencyRate(@PathVariable("type") RateType type,
										@PathVariable("currency") String currency,
										@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date) {
		log.info("getCurrencyRate, currency:{}, date:{}", currency, date);
		CurrencyRate currencyRate = currencyRateEndpointService.getCurrencyRate(type, currency, date);
		log.info("Rate: {}", currencyRate);
		return currencyRate;
	}
	
}
