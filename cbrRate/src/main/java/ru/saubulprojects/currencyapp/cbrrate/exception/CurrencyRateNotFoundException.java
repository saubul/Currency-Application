package ru.saubulprojects.currencyapp.cbrrate.exception;

public class CurrencyRateNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8088580383018269611L;

	public CurrencyRateNotFoundException(String cause) {
		super(cause);
	}
	
}
