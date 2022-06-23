package ru.saubulprojects.currencyapp.currencyrateclient.client;

import java.time.LocalDate;

import ru.saubulprojects.currencyapp.currencyrateclient.model.CurrencyRate;

public interface RateClient {

	CurrencyRate getCurrencyRate(String currency, LocalDate date);

}
