package ru.saubulprojects.currencyapp.cbrrate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor
@Jacksonized @Builder
public class CurrencyRate {
	
	String numCode;
	String charCode;
	String nominal;
	String name;
	String value;
	
}
