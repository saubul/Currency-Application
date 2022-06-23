package ru.saubulproject.currencyapp.moexrate.service;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.saubulproject.currencyapp.moexrate.config.MoexConfig;
import ru.saubulproject.currencyapp.moexrate.model.CurrencyRate;
import ru.saubulproject.currencyapp.moexrate.parser.MoexCurrencyRateParser;
import ru.saubulproject.currencyapp.moexrate.requester.MoexRequester;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoexCurrencyRateService {

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	private final MoexRequester moexRequester;
	private final MoexCurrencyRateParser moexCurrencyRateParser;
	private final MoexConfig moexConfig;
	
	@Cacheable
	public CurrencyRate getCurrencyRate(String currency, LocalDate date) {
		log.info("MoexCurrencyRateService: getCurrencyRate. Currency: {}, date: {}", currency, date);
		String url = String.format("%scurrency=%s/RUB&moment_start=%s&moment_end=%s", moexConfig.getUrl(), 
									currency, 
									DATE_FORMATTER.format(date), 
									DATE_FORMATTER.format(date));
		CurrencyRate currencyRate = moexCurrencyRateParser.parse(moexRequester.getRateAsXml(url));
		currencyRate.setCharCode(currency);
		return currencyRate;
	}
	
	
	
}
