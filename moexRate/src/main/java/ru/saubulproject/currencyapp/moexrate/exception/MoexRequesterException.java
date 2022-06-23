package ru.saubulproject.currencyapp.moexrate.exception;

public class MoexRequesterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7488159851191696367L;
	public MoexRequesterException(Exception ex) {
		super(ex);
	}
}
