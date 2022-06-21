package ru.saubulprojects.currencyapp.cbrrate.model;

import java.util.List;

import lombok.Value;

@Value
public class CachedCurrencyRates {
	
	List<CurrencyRate> currencyRates;
	
}
