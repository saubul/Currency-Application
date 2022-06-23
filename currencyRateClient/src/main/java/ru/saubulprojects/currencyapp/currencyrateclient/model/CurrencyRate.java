package ru.saubulprojects.currencyapp.currencyrateclient.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyRate {
	
	String charCode;
	String numCode;
	String value;
	
	@JsonCreator
	public CurrencyRate(@JsonProperty("charCode") String charCode,
						@JsonProperty("numCode") String numCode,
						@JsonProperty("value") String value) {
		this.charCode = charCode;
		this.numCode = numCode;
		this.value = value;
	}
	
}
