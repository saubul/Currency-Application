package ru.saubulprojects.currencyapp.cbrrate.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.ehcache.Cache;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.saubulprojects.currencyapp.cbrrate.config.CbrConfig;
import ru.saubulprojects.currencyapp.cbrrate.exception.CurrencyRateNotFoundException;
import ru.saubulprojects.currencyapp.cbrrate.model.CachedCurrencyRates;
import ru.saubulprojects.currencyapp.cbrrate.model.CurrencyRate;
import ru.saubulprojects.currencyapp.cbrrate.parser.CurrencyRateParser;
import ru.saubulprojects.currencyapp.cbrrate.requester.CbrRequester;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateService {
	
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	private final CurrencyRateParser currencyRateParser;
	private final CbrRequester cbrRequester;
	private final CbrConfig cbrConfig;
	private final Cache<LocalDate, CachedCurrencyRates> currencyRateCache;
	
	public CurrencyRate getCurrencyRate(String currency, LocalDate date) {
		
		log.info("getCurrencyRate. currency: {}, date: {}", currency, date);
		List<CurrencyRate> rates;
		CachedCurrencyRates cachedCurrencyRates = currencyRateCache.get(date);
		if(cachedCurrencyRates == null) {
			String urlWithParams = String.format("%s?date_req=%s", cbrConfig.getUrl(), DATE_FORMATTER.format(date));
			String rateAsXml = cbrRequester.getRatesAsXml(urlWithParams);
			rates = currencyRateParser.parse(rateAsXml);
			currencyRateCache.put(date, new CachedCurrencyRates(rates));
		} else {
			rates = cachedCurrencyRates.getCurrencyRates();
		}
		
		return rates.stream()
						.filter(rate -> rate.getCharCode().equalsIgnoreCase(currency))
						.findFirst()
						.orElseThrow(() -> new CurrencyRateNotFoundException("Currency rate not found. Currency: " + currency + ", date: " + date));
	}
	
}
