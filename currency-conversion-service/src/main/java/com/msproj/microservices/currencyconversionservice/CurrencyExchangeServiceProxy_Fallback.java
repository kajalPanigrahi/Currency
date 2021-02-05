package com.msproj.microservices.currencyconversionservice;

import org.springframework.stereotype.Component;


@Component
public class CurrencyExchangeServiceProxy_Fallback implements CurrencyExchangeServiceProxy {
	
	

	@Override
	public CurrencyExchangeBean retrieveExchangeValue(String currency) {
		System.out.println("getConversionFactor :: Returning default fallback conversionFactor value : 0.0");
		CurrencyExchangeBean defaultValues = new CurrencyExchangeBean();
		defaultValues.setId(null);
		defaultValues.setCountryCode("Fallback_Country");
		defaultValues.setCurrency("Fallback_Currency");
		defaultValues.setConversionFactor(0.00);
		defaultValues.setPort(0);
		
		return defaultValues;
	}



}
