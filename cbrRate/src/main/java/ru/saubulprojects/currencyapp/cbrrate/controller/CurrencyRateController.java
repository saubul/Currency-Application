package ru.saubulprojects.currencyapp.cbrrate.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.saubulprojects.currencyapp.cbrrate.model.CurrencyRate;
import ru.saubulprojects.currencyapp.cbrrate.service.CurrencyRateService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "${app.rest.api.prefix}/v1")
public class CurrencyRateController {
	
	private final CurrencyRateService currencyRateService;
	
	@GetMapping("/currencyRate/{currency}/{date}")
	public HttpEntity<CurrencyRate> getCurrencyRate(@PathVariable("currency") String currency,
										@DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("date") LocalDate date)	{
		log.info("getCurrencyRate, currency: {}, date: {}", currency, date);
		CurrencyRate currencyRate = currencyRateService.getCurrencyRate(currency, date);
		log.info("Rate: {}", currencyRate);
		return new ResponseEntity<>(currencyRate, HttpStatus.OK);
	}
	
}
