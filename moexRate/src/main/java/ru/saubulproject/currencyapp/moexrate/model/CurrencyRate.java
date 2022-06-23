package ru.saubulproject.currencyapp.moexrate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyRate {
	
	String charCode;
	String value;
	
}
