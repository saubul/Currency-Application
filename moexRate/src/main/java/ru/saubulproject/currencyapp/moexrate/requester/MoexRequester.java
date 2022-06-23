package ru.saubulproject.currencyapp.moexrate.requester;

public interface MoexRequester {
	
	String getRateAsXml(String url);
	
}
