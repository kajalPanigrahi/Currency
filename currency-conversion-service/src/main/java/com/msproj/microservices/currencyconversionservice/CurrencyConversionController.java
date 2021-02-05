package com.msproj.microservices.currencyconversionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	// -------------------Get Conversion Factor and calculate total amount for a currency---------------------------------------------
	@RequestMapping(value = "/currency-exchange/amount/{amount}/currency/{currency}", method = RequestMethod.GET)
	public CurrencyConversionBean getConversionFactor (@PathVariable Double amount, @PathVariable String currency) {
		
		CurrencyExchangeBean forexResponse = proxy.retrieveExchangeValue(currency);
		CurrencyConversionBean conversionResponse = new CurrencyConversionBean();
		
		Double finalAmount = forexResponse.getConversionFactor()*amount;
		
		
		conversionResponse.setFromCurrency(forexResponse.getCurrency());
		conversionResponse.setToCurrency("USD");
		conversionResponse.setConversionFactor(forexResponse.getConversionFactor());
		conversionResponse.setAmount(amount);
		conversionResponse.setFinalAmount(finalAmount);
		conversionResponse.setPort(forexResponse.getPort());
		
		return conversionResponse;
			
	}
	
	//-------------------Get Conversion Factor between two currencies and calculate total amount ---------------------------------------------
	@RequestMapping(value = "/currency-exchange/amount/{amount}/fromCurrency/{fromCurrency}/toCurrency/{toCurrency}", method = RequestMethod.GET)
	public CurrencyConversionBean convertCurrency(@PathVariable Double amount,
			                                      @PathVariable String fromCurrency,
			                                      @PathVariable String toCurrency) {
		
		CurrencyConversionBean conversionResponse = new CurrencyConversionBean();
		CurrencyExchangeBean forexResponse = proxy.retrieveExchangeValue(fromCurrency);	
		System.out.println("From factor:" +forexResponse.getConversionFactor());
		Double fromAmount = forexResponse.getConversionFactor()*amount;		
		System.out.println("From amount:" +fromAmount);	
		CurrencyExchangeBean responseTo = proxy.retrieveExchangeValue(toCurrency);	
		System.out.println("To factor:" +responseTo.getConversionFactor());
		Double UsdInrVal = 1/responseTo.getConversionFactor();
		System.out.println("UsdInrVal:" +UsdInrVal);
		Double finalAmount = UsdInrVal*fromAmount;
		conversionResponse.setFromCurrency(fromCurrency);
		conversionResponse.setToCurrency(toCurrency);
		conversionResponse.setConversionFactor(forexResponse.getConversionFactor());
		conversionResponse.setAmount(amount);
		conversionResponse.setFinalAmount(finalAmount);
		conversionResponse.setPort(forexResponse.getPort());
		
		return conversionResponse;
		
		
	}

	
	
}
