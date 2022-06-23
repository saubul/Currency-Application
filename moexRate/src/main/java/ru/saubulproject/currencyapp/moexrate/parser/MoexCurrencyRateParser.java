package ru.saubulproject.currencyapp.moexrate.parser;

import ru.saubulproject.currencyapp.moexrate.model.CurrencyRate;

public interface MoexCurrencyRateParser {
	
	CurrencyRate parse(String rateAsXml);
	
}
