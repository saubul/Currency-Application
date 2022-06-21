package ru.saubulprojects.currencyapp.cbrrate.requester;

public class RequesterException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5053323300796959071L;

	public RequesterException(Exception ex) {
		super(ex);
	}
	
}
