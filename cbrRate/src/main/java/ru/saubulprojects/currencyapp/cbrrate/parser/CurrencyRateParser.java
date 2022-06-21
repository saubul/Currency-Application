package ru.saubulprojects.currencyapp.cbrrate.parser;

import java.util.List;

import ru.saubulprojects.currencyapp.cbrrate.model.CurrencyRate;

public interface CurrencyRateParser {
	
	List<CurrencyRate> parse(String rateAsString);
	
}
