package ru.saubulprojects.currencyapp.currencyrateclient.model;

public enum RateType {
	CBR("cbr"), MOEX("moex");

	String serviceName;	
	
	RateType(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return serviceName;
	}
}
