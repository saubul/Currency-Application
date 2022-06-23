package ru.saubulproject.currencyapp.moexrate.controller;

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
import ru.saubulproject.currencyapp.moexrate.model.CurrencyRate;
import ru.saubulproject.currencyapp.moexrate.service.MoexCurrencyRateService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "${app.rest.api.prefix}/v1")
public class MoexCurrencyRateController {
	
	private final MoexCurrencyRateService moexCurrencyRateService;
	
	@GetMapping("/currencyRate/{currency}/{date}")
	public HttpEntity<CurrencyRate> getCurrencyRate(@PathVariable("currency") String currency,
													@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate date) {
		log.info("getCurrencyRate. Currency: {}, date: {}", currency, date);
		CurrencyRate currencyRate = moexCurrencyRateService.getCurrencyRate(currency, date);
		if(currencyRate == null) {
			return new ResponseEntity<>(currencyRate, HttpStatus.NO_CONTENT);
		}
		log.info("Currency rate: {}", currencyRate);
		return new ResponseEntity<>(currencyRate, HttpStatus.OK);
	}
	
}
