package ru.saubulprojects.currencyapp.currencyrateclient.client;

import ru.saubulprojects.currencyapp.currencyrateclient.model.CurrencyRate;

public interface HttpClient {
	
	CurrencyRate performRequest(String url);
	
}
